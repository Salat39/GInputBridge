package com.salat.gbinder.adb.data.repository

import android.util.Base64
import com.salat.gbinder.BuildConfig
import com.salat.gbinder.NATIVE_LAUNCHER_BATCH_SIZE
import com.salat.gbinder.TELNET_HELPER_PORT
import com.salat.gbinder.adb.data.entity.AdbConnectionState
import com.salat.gbinder.adb.domain.repository.AdbRepository
import com.salat.gbinder.datastore.DataStoreRepository
import com.salat.gbinder.datastore.GeneralPrefs
import com.salat.gbinder.entity.AdbRecentTaskInfo
import com.tananaev.adblib.AdbBase64
import com.tananaev.adblib.AdbConnection
import com.tananaev.adblib.AdbCrypto
import com.tananaev.adblib.AdbStream
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.io.IOException
import java.net.InetSocketAddress
import java.net.Socket
import java.text.Normalizer

class AdbRepositoryImpl(private val dataStore: DataStoreRepository) : AdbRepository {

    companion object {
        private const val TIMEOUT_MS = 5_000
        private const val ATLAS_LOCALHOST_PORT = 5555
        private const val RECONNECT_DELAY_MS = 3_000L
        private const val MAX_RECONNECT_RETRIES = 5

        private const val DONE_PREFIX = "__ADB_DONE__:"
        private const val NATIVE_LAUNCHER_APP_INFO_URI =
            "content://com.geely.appstore.AppstoreProvider/appInfo"
        private const val NATIVE_LAUNCHER_PACKAGE_COLUMN = "package_name"
    }

    private val host
        get() = if (BuildConfig.DEBUG) "10.0.2.2" else "localhost"

    // Manages IO scope for background tasks.
    private val ioScope by lazy { CoroutineScope(SupervisorJob() + Dispatchers.IO) }
    private val lock = Mutex()
    private val reconnectMutex = Mutex()

    private val connGuard = Any()

    @Volatile
    private var reconnectJob: Job? = null

    @Volatile
    private var isManuallyDisconnected: Boolean = false

    @Volatile
    private var connectionEpoch: Long = 0L

    private val base64 = AdbBase64 { data -> Base64.encodeToString(data, Base64.NO_WRAP) }
    private val telnetDiscovery by lazy { TelnetShellDiscovery(::buildDoneMarker) }

    private val _connectionState =
        MutableStateFlow<AdbConnectionState>(AdbConnectionState.Disconnected)
    override val connectionState: StateFlow<AdbConnectionState> = _connectionState.asStateFlow()

    @Volatile
    private var socket: Socket? = null

    @Volatile
    private var connection: AdbConnection? = null

    @Volatile
    private var telnetTransport: TelnetShellTransport? = null

    private val taskIdRegex = Regex(
        pattern = """\bTask\{[^}]*#(\d+)\b""",
        options = setOf(RegexOption.MULTILINE)
    )

    private val foregroundPackageRegex = Regex(
        pattern = """\b([a-zA-Z0-9_]+(?:\.[a-zA-Z0-9_]+)+)(?=(?:/|\s|\}|,|\)|\]|$))"""
    )

    // Recents tasks
    private val taskHeaderRegex = Regex("""^\s{2}\*\sTask\{.*#(\d+).*?\btype=([a-zA-Z_]+)\b.*""")
    private val taskHeaderVisibleRegex = Regex("""\bvisible=(true|false)\b""")
    private val taskHeaderVisibleRequestedRegex = Regex("""\bvisibleRequested=(true|false)\b""")
    private val taskHeaderPackageFromARegex =
        Regex("""\bA=\d+:([a-zA-Z0-9_]+(?:\.[a-zA-Z0-9_]+)+)\b""")
    private val packageNameLineRegex =
        Regex("""\bpackageName=([a-zA-Z0-9_]+(?:\.[a-zA-Z0-9_]+)+)\b""")
    private val nativeLauncherPackageRegex =
        Regex("""\bpackage_name=([a-zA-Z0-9_]+(?:\.[a-zA-Z0-9_]+)+)\b""")
    private val activityStateRegex = Regex("""\bstate=([A-Z_]+)\b""")
    private val nowVisibleRegex = Regex("""\bnowVisible=(true|false)\b""")
    private val lastVisibleTimeRegex = Regex("""\blastVisibleTime=([^\s]+)\b""")
    private val baseDirRegex = Regex("""\bbaseDir=([^\s]+)\b""")
    private val dataDirRegex = Regex("""\bdataDir=([^\s]+)\b""")

    init {
        // Drop connection when external toggle becomes OFF.
        ioScope.launch {
            // Disconnect by disable
            launch {
                dataStore.getValueFlow(GeneralPrefs.ENABLE_ADB_HELPER, false).collect { enable ->
                    if (!enable) disconnect() else reconnect()
                }
            }
            // Disconnect by port changed
            launch {
                dataStore.getValueFlow(GeneralPrefs.ADB_HELPER_PORT, 5555).drop(1).collect { _ ->
                    disconnect()
                    val enable =
                        dataStore.getValueFlow(GeneralPrefs.ENABLE_ADB_HELPER, false).first()
                    if (enable) reconnect()
                }
            }
        }
    }

    suspend fun connect(host: String, port: Int) =
        if (isTelnetMode(port)) connectTelnet() else connectAdb(host, port)

    private suspend fun connectAdb(host: String, port: Int): Boolean = withContext(Dispatchers.IO) {
        lock.withLock {
            val myEpoch = synchronized(connGuard) { connectionEpoch }

            isManuallyDisconnected = false

            if (isAdbConnectedUnsafe()) {
                _connectionState.value = AdbConnectionState.Connected
                Timber.d("[ADB] connect skipped: already connected")
                cancelReconnectLoop()
                return@withLock true
            }

            _connectionState.value = AdbConnectionState.Connecting
            Timber.d("[ADB] connect to %s:%d", host, port)
            try {
                val s = Socket()
                s.connect(InetSocketAddress(host, port), TIMEOUT_MS)

                val crypto: AdbCrypto = AdbCrypto.generateAdbKeyPair(base64)
                val conn = AdbConnection.create(s, crypto)
                conn.connect()

                val canPublish = synchronized(connGuard) {
                    connectionEpoch == myEpoch && !isManuallyDisconnected
                }
                if (!canPublish) {
                    runCatching { conn.close() }
                    runCatching { s.close() }
                    _connectionState.value = AdbConnectionState.Disconnected
                    return@withLock false
                }

                synchronized(connGuard) {
                    if (connectionEpoch != myEpoch || isManuallyDisconnected) {
                        runCatching { conn.close() }
                        runCatching { s.close() }
                        _connectionState.value = AdbConnectionState.Disconnected
                        return@withLock false
                    }
                    val oldTelnet = telnetTransport
                    socket = s
                    connection = conn
                    telnetTransport = null
                    runCatching { oldTelnet?.close() }
                }

                _connectionState.value = AdbConnectionState.Connected
                Timber.d("[ADB] connected to %s:%d", host, port)
                cancelReconnectLoop()
                true
            } catch (t: Throwable) {
                _connectionState.value = AdbConnectionState.Error(t.message ?: "ADB connect error")
                Timber.w(t, "[ADB] connect error")
                safeClose()
                scheduleReconnect(host, port, "connect error")
                false
            }
        }
    }

    private suspend fun reconnect() {
        isManuallyDisconnected = false

        if (_connectionState.value is AdbConnectionState.Disconnected) {
            _connectionState.value = AdbConnectionState.Connecting
        }

        val port = dataStore.getValueFlow(GeneralPrefs.ADB_HELPER_PORT, 5555).first()
        if (!isConnectedForPortUnsafe(port)) {
            val ok = connect(host, port)
            if (!ok) {
                scheduleReconnect(host, port, "initial reconnect")
            }
        }
    }

    private suspend fun scheduleReconnect(host: String, port: Int, reason: String) {
        if (isManuallyDisconnected || _connectionState.value is AdbConnectionState.Disconnected) {
            Timber.d(
                "[ADB] reconnect suppressed: manual disconnect/state disconnected (%s)",
                reason
            )
            cancelReconnectLoop()
            return
        }

        reconnectMutex.withLock {
            if (reconnectJob?.isActive == true) return

            Timber.w("[ADB] scheduling reconnect: %s", reason)
            reconnectJob = ioScope.launch {
                var attempt = 0
                while (isActive && attempt < MAX_RECONNECT_RETRIES) {
                    if (isManuallyDisconnected || _connectionState.value is AdbConnectionState.Disconnected) {
                        break
                    }
                    val enable =
                        dataStore.getValueFlow(GeneralPrefs.ENABLE_ADB_HELPER, false).first()
                    if (!enable) {
                        disconnect()
                        break
                    }
                    if (isConnectedForPortUnsafe(port)) {
                        break
                    }

                    attempt++
                    Timber.d(
                        "[ADB] reconnect attempt %d/%d in %dms",
                        attempt,
                        MAX_RECONNECT_RETRIES,
                        RECONNECT_DELAY_MS,
                    )
                    delay(RECONNECT_DELAY_MS)
                    val ok = connect(host, port)
                    if (ok) {
                        Timber.d("[ADB] reconnected")
                        break
                    }
                }

                reconnectMutex.withLock {
                    reconnectJob = null
                }
            }
        }
    }

    override suspend fun execute(command: String): String = withContext(Dispatchers.IO) {
        val enable = dataStore.getValueFlow(GeneralPrefs.ENABLE_ADB_HELPER, false).first()
        if (!enable) {
            disconnect()
            return@withContext "ADB helper disabled"
        }

        if (isManuallyDisconnected || _connectionState.value is AdbConnectionState.Disconnected) {
            return@withContext "ADB disconnected"
        }

        val port = dataStore.getValueFlow(GeneralPrefs.ADB_HELPER_PORT, 5555).first()

        if (!isConnectedForPortUnsafe(port)) {
            val ok = connect(host, port)
            if (!ok) return@withContext "ADB connect failed"
        }

        if (command.isEmpty()) return@withContext "empty command"

        try {
            if (isTelnetMode(port)) executeTelnetLocked(command) else executeLocked(command)
        } catch (t: CommandFailedException) {
            Timber.w(
                t,
                "[ADB] command failed (exit=%d), output=%s",
                t.exitCode,
                t.output.trim().takeLast(300)
            )
            t.message ?: "ADB command failed"
        } catch (t: Throwable) {
            if (isManuallyDisconnected || _connectionState.value is AdbConnectionState.Disconnected) {
                return@withContext "ADB disconnected"
            }

            Timber.w(t, "[ADB] execute failed")
            dropConnectionForRetry(t)
            scheduleReconnect(host, port, "execute error")
            t.message ?: "ADB execute error"
        }
    }

    override suspend fun setAtlasWheelSettings(): String {
        return executeAtlasCommand("""settings put system wheel_settings "1"""")
    }

    private suspend fun executeAtlasCommand(command: String): String = withContext(Dispatchers.IO) {
        isManuallyDisconnected = false
        lock.withLock { forceCloseNow() }
        val ok = connect(host, ATLAS_LOCALHOST_PORT)
        if (!ok) return@withContext "ADB connect failed"

        if (command.isEmpty()) return@withContext "empty command"

        try {
            executeLocked(command)
        } catch (t: CommandFailedException) {
            Timber.w(
                t,
                "[ADB] atlas command failed (exit=%d), output=%s",
                t.exitCode,
                t.output.trim().takeLast(300)
            )
            t.message ?: "ADB atlas command failed"
        } catch (t: Throwable) {
            if (isManuallyDisconnected || _connectionState.value is AdbConnectionState.Disconnected) {
                return@withContext "ADB disconnected"
            }

            Timber.w(t, "[ADB] atlas execute failed")
            dropConnectionForRetry(t)
            scheduleReconnect(host, ATLAS_LOCALHOST_PORT, "atlas execute error")
            t.message ?: "ADB atlas execute error"
        }
    }

    override suspend fun isAppInFreeform(packageName: String): Boolean? {
        val pkg = packageName.trim()
        if (pkg.isEmpty() || pkg.equals("unknown", ignoreCase = true) || !isValidPackageName(pkg)) {
            return false
        }

        if (getTaskId(packageName) == null) return null

        val r = execute(
            "dumpsys activity activities | " +
                    "grep -i -E \"WindowingMode|mWindowingMode|windowingMode|$pkg\""
        )
        return r.contains("mode=freeform ")
    }

    override suspend fun isAppLaunched(packageName: String): Boolean {
        val pkg = packageName.trim()
        if (pkg.isEmpty() || pkg.equals("unknown", ignoreCase = true) || !isValidPackageName(pkg)) {
            return false
        }

        val result = execute("pidof $pkg 2>/dev/null || true").trim()
        if (result.isEmpty()) return false

        return result.split(' ', '\n', '\r', '\t').any { pid ->
            pid.toIntOrNull()?.let { it > 0 } == true
        }
    }

    override suspend fun getTaskId(packageName: String): Int? {
        val pkg = packageName.trim()
        if (pkg.isEmpty() || pkg.equals("unknown", ignoreCase = true) || !isValidPackageName(pkg)) {
            return null
        }
        return parseTaskId(execute("dumpsys activity activities | grep -E \"Task\\{.*$packageName|taskId=\""))
    }

    private fun parseTaskId(dumpsysOutput: String): Int? {
        val m = taskIdRegex.find(dumpsysOutput) ?: return null
        return m.groupValues[1].toIntOrNull()
    }

    override suspend fun forceStop(packageName: String): String {
        val pkg = packageName.trim()
        if (pkg.isEmpty() || pkg.equals("unknown", ignoreCase = true) || !isValidPackageName(pkg)) {
            return "no valid package names"
        }
        return execute("am force-stop --user 0 $pkg")
    }

    override suspend fun forceStop(vararg packageNames: String): String {
        val sb = StringBuilder(64)

        for (i in packageNames.indices) {
            val raw = packageNames[i]
            if (raw.isEmpty()) continue

            val pkg = raw.trim()
            if (pkg.isEmpty()) continue
            if (pkg.equals("unknown", ignoreCase = true)) continue
            if (!isValidPackageName(pkg)) continue

            if (sb.isNotEmpty()) sb.append("; ")
            sb.append("am force-stop --user 0 ").append(pkg)
        }

        if (sb.isEmpty()) return "no valid package names"
        return execute(sb.toString())
    }

    override suspend fun enablePackage(packageName: String): String {
        val pkg = packageName.trim()
        if (pkg.isEmpty() || pkg.equals("unknown", ignoreCase = true) || !isValidPackageName(pkg)) {
            return "no valid package names"
        }
        return execute("pm enable $pkg")
    }

    override suspend fun allowActivateVpnAppOp(packageName: String): String {
        val pkg = packageName.trim()
        if (pkg.isEmpty() || pkg.equals("unknown", ignoreCase = true) || !isValidPackageName(pkg)) {
            return "no valid package names"
        }
        return execute("appops set $pkg ACTIVATE_VPN allow")
    }

    override suspend fun enableAndLaunchApp(packageName: String, launchActivity: String?): String {
        val pkg = packageName.trim()
        if (pkg.isEmpty() || pkg.equals("unknown", ignoreCase = true) || !isValidPackageName(pkg)) {
            return "no valid package names"
        }
        val enableOut = enablePackage(pkg)
        val trimmedActivity = launchActivity?.trim()?.takeUnless { it.isEmpty() }
        val launchCmd = if (trimmedActivity != null) {
            val fqcn = if (trimmedActivity.startsWith(".")) {
                pkg + trimmedActivity
            } else {
                trimmedActivity
            }
            "am start --user 0 -n $pkg/$fqcn"
        } else {
            "monkey -p $pkg -c android.intent.category.LAUNCHER 1"
        }
        val launchOut = execute(launchCmd)
        return "$enableOut\n$launchOut"
    }

    override suspend fun disableUserPackage(packageName: String): String {
        val pkg = packageName.trim()
        if (pkg.isEmpty() || pkg.equals("unknown", ignoreCase = true) || !isValidPackageName(pkg)) {
            return "no valid package names"
        }
        return execute("pm disable-user --user 0 $pkg")
    }

    override suspend fun addAppToCarLauncher(packageName: String, appName: String): String {
        val pkg = packageName.trim()
        if (pkg.isEmpty() || pkg.equals("unknown", ignoreCase = true) || !isValidPackageName(pkg)) {
            return "no valid package names"
        }

        val removeOutput = removeAppFromCarLauncher(pkg)
        val insertOutput = execute(buildNativeLauncherInsertCommand(pkg, appName))

        return buildString {
            if (removeOutput.isNotBlank()) append(removeOutput.trim())
            if (isNotEmpty()) append('\n')
            append(insertOutput.trim())
        }
    }

    override suspend fun removeAppFromCarLauncher(packageName: String): String {
        val pkg = packageName.trim()
        if (pkg.isEmpty() || pkg.equals("unknown", ignoreCase = true) || !isValidPackageName(pkg)) {
            return "no valid package names"
        }

        return execute(buildNativeLauncherDeleteCommand(pkg))
    }

    override suspend fun removeAppsFromCarLauncher(
        packageNames: List<String>,
        onProgressTick: (suspend () -> Unit)?
    ): String {
        val validPackages = packageNames.mapNotNull { packageName ->
            val pkg = packageName.trim()
            if (pkg.isEmpty() || pkg.equals("unknown", ignoreCase = true) || !isValidPackageName(pkg)) {
                null
            } else {
                pkg
            }
        }.distinct()

        if (validPackages.isEmpty()) {
            return if (packageNames.isEmpty()) "" else "no valid package names"
        }

        val output = StringBuilder()
        validPackages.chunked(NATIVE_LAUNCHER_BATCH_SIZE).forEach { chunk ->
            val command = chunk.joinToString("; ") { buildNativeLauncherDeleteCommand(it) }
            appendCommandOutput(output, execute(command).trim())
            onProgressTick?.invoke()
        }
        return output.toString()
    }

    override suspend fun clearCarLauncherApps(): String {
        return execute("content delete --uri $NATIVE_LAUNCHER_APP_INFO_URI")
    }

    override suspend fun addAppsToCarLauncher(
        apps: List<Pair<String, String>>,
        delay: Long,
        onProgressTick: (suspend () -> Unit)?
    ): String {
        val validApps = apps.mapNotNull { (packageName, appName) ->
            val pkg = packageName.trim()
            if (pkg.isEmpty() || pkg.equals(
                    "unknown",
                    ignoreCase = true
                ) || !isValidPackageName(pkg)
            ) {
                null
            } else {
                pkg to appName
            }
        }.distinctBy { it.first }

        if (validApps.isEmpty()) {
            return if (apps.isEmpty()) "" else "no valid package names"
        }

        val output = StringBuilder()
        validApps.chunked(NATIVE_LAUNCHER_BATCH_SIZE).forEach { chunk ->
            val command = chunk.joinToString("; ") { (pkg, appName) ->
                buildNativeLauncherInsertCommand(pkg, appName)
            }
            appendCommandOutput(output, execute(command).trim())
            if (delay != 0L) delay(delay)
            onProgressTick?.invoke()
        }
        return output.toString()
    }

    override suspend fun getNativeLauncherApps(): List<String> {
        val output = execute("content query --uri $NATIVE_LAUNCHER_APP_INFO_URI")
        return nativeLauncherPackageRegex.findAll(output)
            .map { it.groupValues[1] }
            .filter { isValidPackageName(it) }
            .distinct()
            .toList()
    }

    override suspend fun restartLauncher3(): String {
        return execute("am force-stop com.android.launcher3; monkey -p com.android.launcher3 1")
    }

    override suspend fun minimize(taskId: Int) {
        if (taskId == -1 || taskId == 0) return
        execute("am stack remove $taskId")
    }

    override suspend fun getForegroundAppPackageName(): String? {
        val windowDump = execute("dumpsys window windows")
        if (windowDump.isNotBlank()) {
            sequenceOf("mFocusedApp=", "mCurrentFocus=")
                .mapNotNull { marker -> extractPackageAroundMarker(windowDump, marker) }
                .firstOrNull { pkg -> !isSystemOverlayPackage(pkg) && isValidPackageName(pkg) }
                ?.let { return it }
        }

        val activityDump = execute("dumpsys activity activities")
        if (activityDump.isBlank()) return null

        val resumedLine = activityDump.lineSequence().firstOrNull { line ->
            line.contains("topResumedActivity") ||
                    line.contains("mTopResumedActivity") ||
                    line.contains("mResumedActivity") ||
                    line.contains("ResumedActivity")
        } ?: return null

        val match = foregroundPackageRegex.find(resumedLine) ?: return null
        val pkg = match.groupValues[1]
        return pkg.takeIf { it.isNotBlank() && !isSystemOverlayPackage(it) && isValidPackageName(it) }
    }

    private fun extractPackageAroundMarker(dump: String, marker: String): String? {
        val idx = dump.indexOf(marker)
        if (idx < 0) return null

        val endExclusive = (idx + 600).coerceAtMost(dump.length)
        val chunk = dump.substring(idx, endExclusive)

        val match = foregroundPackageRegex.find(chunk) ?: return null
        return match.groupValues[1].trim()
    }

    private fun isSystemOverlayPackage(pkg: String): Boolean {
        return pkg == "com.android.systemui" ||
                pkg == "android" ||
                pkg.startsWith("com.android.launcher") ||
                pkg.startsWith("com.google.android.apps.nexuslauncher")
    }

    override suspend fun getRecentTasksFromActivitiesDump(): List<AdbRecentTaskInfo> {
        val dump = execute("dumpsys activity activities")
        if (dump.isBlank()) return emptyList()
        return parseRecentTasksFromActivitiesDump(dump)
    }

    private fun parseRecentTasksFromActivitiesDump(dump: String): List<AdbRecentTaskInfo> {
        val out = ArrayList<AdbRecentTaskInfo>(16)

        var inDisplay0 = false

        var taskId: Int? = null
        var type: String? = null
        var visible = false
        var visibleRequested = false
        var packageName: String? = null
        var topResumed = false
        var activityState: String? = null
        var nowVisible: Boolean? = null
        var lastVisibleTime: String? = null
        var baseDir: String? = null
        var dataDir: String? = null

        fun flushCurrentTask() {
            val id = taskId ?: return
            val t = type ?: return
            val pkg = packageName ?: return
            if (isSystemTask(t, pkg)) return

            out.add(
                AdbRecentTaskInfo(
                    taskId = id,
                    packageName = pkg,
                    visible = visible,
                    visibleRequested = visibleRequested,
                    topResumed = topResumed,
                    activityState = activityState,
                    nowVisible = nowVisible,
                    lastVisibleTime = lastVisibleTime,
                    baseDir = baseDir,
                    dataDir = dataDir
                )
            )
        }

        fun resetForNextTask() {
            taskId = null
            type = null
            visible = false
            visibleRequested = false
            packageName = null
            topResumed = false
            activityState = null
            nowVisible = null
            lastVisibleTime = null
            baseDir = null
            dataDir = null
        }

        for (line in dump.lineSequence()) {
            if (!inDisplay0) {
                if (line.startsWith("Display #0")) {
                    inDisplay0 = true
                }
                continue
            }

            if (line.startsWith("Resumed activities in task display areas")) {
                flushCurrentTask()
                break
            }

            if (line.startsWith("  * Task{")) {
                flushCurrentTask()
                resetForNextTask()

                val headerMatch = taskHeaderRegex.find(line) ?: continue
                taskId = headerMatch.groupValues[1].toIntOrNull()
                type = headerMatch.groupValues[2]

                taskHeaderVisibleRegex.find(line)?.groupValues?.get(1)?.toBooleanStrictOrNull()
                    ?.let { visible = it }
                taskHeaderVisibleRequestedRegex.find(line)?.groupValues?.get(1)
                    ?.toBooleanStrictOrNull()
                    ?.let { visibleRequested = it }

                taskHeaderPackageFromARegex.find(line)?.groupValues?.get(1)
                    ?.let { packageName = it }

                continue
            }

            val id = taskId ?: continue

            if (!topResumed && line.contains("topResumedActivity=") && line.contains("t$id")) {
                topResumed = true
            }

            packageNameLineRegex.find(line)?.groupValues?.get(1)?.let { packageName = it }

            if (activityState == null) {
                activityStateRegex.find(line)?.groupValues?.get(1)?.let { activityState = it }
            }

            if (nowVisible == null) {
                nowVisibleRegex.find(line)?.groupValues?.get(1)?.toBooleanStrictOrNull()
                    ?.let { nowVisible = it }
            }

            if (lastVisibleTime == null) {
                lastVisibleTimeRegex.find(line)?.groupValues?.get(1)?.let { lastVisibleTime = it }
            }

            if (baseDir == null) {
                baseDirRegex.find(line)?.groupValues?.get(1)?.let { baseDir = it }
            }

            if (dataDir == null) {
                dataDirRegex.find(line)?.groupValues?.get(1)?.let { dataDir = it }
            }
        }

        return out
    }

    private fun isSystemTask(type: String, packageName: String): Boolean {
        if (type == "home") return true
        if (packageName == "com.android.systemui") return true

        if (packageName == "com.google.android.apps.nexuslauncher") return true
        if (packageName == "com.google.android.apps.pixel.launcher") return true

        if (packageName.startsWith("com.android.launcher")) return true
        if (packageName.startsWith("com.google.android.apps.launcher")) return true
        if (packageName.endsWith(".launcher")) return true

        return false
    }

    private fun String.toBooleanStrictOrNull(): Boolean? {
        return when (this) {
            "true" -> true
            "false" -> false
            else -> null
        }
    }

    override suspend fun pressHome() {
        execute("input keyevent KEYCODE_HOME")
    }

    override suspend fun pressBack() {
        execute("input keyevent KEYCODE_BACK")
    }

    suspend fun disconnect() = withContext(Dispatchers.IO) {
        Timber.d("[ADB] disconnect")
        isManuallyDisconnected = true
        cancelReconnectLoop()

        // Invalidate any in-flight connect/execute so they can't resurrect the connection.
        synchronized(connGuard) {
            connectionEpoch++
        }

        // Close transport immediately, even if a command is currently executing.
        forceCloseNow()

        _connectionState.value = AdbConnectionState.Disconnected

        // Best-effort cleanup under lock; do not wait if a command holds the mutex.
        if (lock.tryLock()) {
            try {
                // No-op: state/refs are already cleared by forceCloseNow().
            } finally {
                lock.unlock()
            }
        }
    }

    private suspend fun executeLocked(command: String): String = lock.withLock {
        val (conn, myEpoch) = synchronized(connGuard) {
            val c = checkNotNull(connection) { "ADB is not connected" }
            c to connectionEpoch
        }

        Timber.d("[ADB] execute: %s", command)

        // If disconnect() happened before we start, abort early.
        synchronized(connGuard) {
            check(connectionEpoch == myEpoch && !isManuallyDisconnected) { "ADB disconnected" }
        }

        val marker = buildDoneMarker()
        val effectiveCommand = appendMarker(command, marker)

        val stream: AdbStream = conn.open("shell:$effectiveCommand")
        return@withLock try {
            val (output, exitCode) = readUntilMarker(stream, marker)

            // If disconnect() happened during execution, do not treat output as valid.
            synchronized(connGuard) {
                check(connectionEpoch == myEpoch && !isManuallyDisconnected) { "ADB disconnected" }
            }

            if (exitCode != 0) {
                throw CommandFailedException(exitCode, output)
            }

            output.also { Timber.d("[ADB] result length=%d", it.length) }
        } finally {
            try {
                stream.close()
            } catch (_: Throwable) {
                // ignore
            }
        }
    }

    private suspend fun executeTelnetLocked(command: String): String = lock.withLock {
        val (transport, myEpoch) = synchronized(connGuard) {
            val t = checkNotNull(telnetTransport) { "Telnet is not connected" }
            t to connectionEpoch
        }

        Timber.d("[Telnet] execute: %s", command)

        synchronized(connGuard) {
            check(connectionEpoch == myEpoch && !isManuallyDisconnected) { "Telnet disconnected" }
        }

        val marker = buildDoneMarker()
        val (output, exitCode) = transport.exec(command, marker)

        synchronized(connGuard) {
            check(connectionEpoch == myEpoch && !isManuallyDisconnected) { "Telnet disconnected" }
        }

        if (exitCode != 0) {
            throw CommandFailedException(exitCode, output)
        }

        output.also { Timber.d("[Telnet] result length=%d", it.length) }
    }

    private fun buildDoneMarker(): String {
        // Uses nanoTime to avoid collisions without extra allocations/overhead.
        return DONE_PREFIX + System.nanoTime().toString() + ":"
    }

    private fun appendMarker(command: String, marker: String): String {
        // Keeps the original command intact; just appends a deterministic trailer.
        val trimmed = command.trimEnd()
        return if (trimmed.endsWith(";")) {
            "$trimmed echo $marker\$?"
        } else {
            "$trimmed; echo $marker\$?"
        }
    }

    private fun readUntilMarker(stream: AdbStream, marker: String): Pair<String, Int> {
        val out = StringBuilder(256)
        var markerIndex = -1
        while (!stream.isClosed) {
            val chunk = stream.read() ?: break
            if (chunk.isEmpty()) break
            out.append(String(chunk))

            if (markerIndex < 0) {
                markerIndex = out.indexOf(marker)
            }
            if (markerIndex >= 0) {
                val after = out.substring(markerIndex + marker.length)
                val exit = parseLeadingInt(after)
                if (exit != null) {
                    val output = out.substring(0, markerIndex).trimEnd()
                    return output to exit
                }
            }
        }
        throw IOException("ADB stream closed before completion marker")
    }

    private fun parseLeadingInt(value: String): Int? {
        var i = 0
        while (i < value.length && value[i].isWhitespace()) i++
        if (i >= value.length || !value[i].isDigit()) return null

        var num = 0
        while (i < value.length && value[i].isDigit()) {
            num = num * 10 + (value[i] - '0')
            i++
        }
        return num
    }

    private class CommandFailedException(
        val exitCode: Int,
        val output: String,
    ) : IOException("ADB command failed (exit=$exitCode)")

    private suspend fun dropConnectionForRetry(t: Throwable) {
        lock.withLock {
            if (isManuallyDisconnected) return@withLock
            _connectionState.value = AdbConnectionState.Error(t.message ?: "ADB error")
            safeClose()
        }
    }

    private fun cancelReconnectLoop() {
        val job = reconnectJob
        if (job?.isActive == true) {
            job.cancel()
        }
        reconnectJob = null
    }

    private fun isConnectedForPortUnsafe(port: Int): Boolean {
        return if (isTelnetMode(port)) isTelnetConnectedUnsafe() else isAdbConnectedUnsafe()
    }

    private fun isAdbConnectedUnsafe(): Boolean = synchronized(connGuard) {
        val s = socket
        val c = connection
        s != null && !s.isClosed && c != null && !isManuallyDisconnected
    }

    private fun isTelnetConnectedUnsafe(): Boolean = synchronized(connGuard) {
        val t = telnetTransport
        t != null && !t.isClosed() && !isManuallyDisconnected
    }

    private fun forceCloseNow() {
        // Closes transport immediately, without waiting for the main execution lock.
        val toCloseConn: AdbConnection?
        val toCloseSocket: Socket?
        val toCloseTelnet: TelnetShellTransport?

        synchronized(connGuard) {
            toCloseConn = connection
            toCloseSocket = socket
            toCloseTelnet = telnetTransport
            connection = null
            socket = null
            telnetTransport = null
        }

        runCatching { toCloseConn?.close() }
        runCatching { toCloseSocket?.close() }
        runCatching { toCloseTelnet?.close() }
    }

    private fun safeClose() {
        val toCloseConn: AdbConnection?
        val toCloseSocket: Socket?
        val toCloseTelnet: TelnetShellTransport?

        synchronized(connGuard) {
            toCloseConn = connection
            toCloseSocket = socket
            toCloseTelnet = telnetTransport
            connection = null
            socket = null
            telnetTransport = null
        }

        try {
            toCloseConn?.close()
        } catch (t: Throwable) {
            Timber.w(t, "[ADB] connection close error")
        }
        try {
            toCloseSocket?.close()
        } catch (t: Throwable) {
            Timber.w(t, "[ADB] socket close error")
        }
        try {
            toCloseTelnet?.close()
        } catch (t: Throwable) {
            Timber.w(t, "[Telnet] socket close error")
        }
    }

    private suspend fun connectTelnet(): Boolean = withContext(Dispatchers.IO) {
        lock.withLock {
            val myEpoch = synchronized(connGuard) { connectionEpoch }

            isManuallyDisconnected = false

            val existing = synchronized(connGuard) { telnetTransport }
            if (existing != null && !existing.isClosed()) {
                _connectionState.value = AdbConnectionState.Connected
                Timber.d("[Telnet] connect skipped: already connected")
                cancelReconnectLoop()
                return@withLock true
            }

            _connectionState.value = AdbConnectionState.Connecting
            Timber.d("[Telnet] discovery started")

            try {
                val (endpoint, transport) = telnetDiscovery.open()

                val canPublish = synchronized(connGuard) {
                    connectionEpoch == myEpoch && !isManuallyDisconnected
                }
                if (!canPublish) {
                    runCatching { transport.close() }
                    _connectionState.value = AdbConnectionState.Disconnected
                    return@withLock false
                }

                synchronized(connGuard) {
                    if (connectionEpoch != myEpoch || isManuallyDisconnected) {
                        runCatching { transport.close() }
                        _connectionState.value = AdbConnectionState.Disconnected
                        return@withLock false
                    }
                    runCatching { connection?.close() }
                    runCatching { socket?.close() }
                    connection = null
                    socket = null
                    telnetTransport = transport
                }

                _connectionState.value = AdbConnectionState.Connected
                Timber.d("[Telnet] connected to %s:%d", endpoint.host, endpoint.port)
                cancelReconnectLoop()
                true
            } catch (t: Throwable) {
                _connectionState.value =
                    AdbConnectionState.Error(t.message ?: "Telnet connect error")
                Timber.w(t, "[Telnet] connect error")
                telnetDiscovery.clearCache()
                safeClose()
                scheduleReconnect(host, TELNET_HELPER_PORT, "telnet connect error")
                false
            }
        }
    }

    private fun isTelnetMode(port: Int) = port == TELNET_HELPER_PORT

    private fun buildNativeLauncherDeleteCommand(packageName: String): String {
        return "content delete " +
                "--uri $NATIVE_LAUNCHER_APP_INFO_URI " +
                "--where \"$NATIVE_LAUNCHER_PACKAGE_COLUMN='$packageName'\""
    }

    private fun buildNativeLauncherInsertCommand(packageName: String, appName: String): String {
        val name = normalizeNativeLauncherBindValue(
            value = appName,
            fallbackValue = packageName
        )

        return "content insert " +
                "--uri $NATIVE_LAUNCHER_APP_INFO_URI " +
                "--bind package_name:s:$packageName " +
                "--bind apk_name:s:$name " +
                "--bind apk_icon:s:none " +
                "--bind apk_size:s:0 " +
                "--bind apk_version_name:s:1.0 " +
                "--bind apk_version_code:s:1 " +
                "--bind apk_type:s:third " +
                "--bind apk_type_name:s:third " +
                "--bind display_screen:s:0"
    }

    private fun appendCommandOutput(output: StringBuilder, commandOutput: String) {
        if (commandOutput.isBlank()) return
        if (output.isNotEmpty()) output.append('\n')
        output.append(commandOutput)
    }

    private fun normalizeNativeLauncherBindValue(
        value: String,
        fallbackValue: String
    ): String {
        val asciiValue = Normalizer.normalize(transliterateCyrillic(value.trim()), Normalizer.Form.NFD)
            .replace(Regex("\\p{Mn}+"), "")
            .replace(Regex("[\\r\\n\\t ]+"), "_")
            .replace(Regex("[^A-Za-z0-9_.-]+"), "_")
            .trim('_', '.', '-')
            .takeUnless { it.isEmpty() }

        return asciiValue ?: fallbackValue
    }

    private fun transliterateCyrillic(value: String): String {
        val out = StringBuilder(value.length)

        for (ch in value) {
            out.append(
                when (ch) {
                    'А' -> "A"
                    'Б' -> "B"
                    'В' -> "V"
                    'Г' -> "G"
                    'Д' -> "D"
                    'Е' -> "E"
                    'Ё' -> "E"
                    'Ж' -> "Zh"
                    'З' -> "Z"
                    'И' -> "I"
                    'Й' -> "Y"
                    'К' -> "K"
                    'Л' -> "L"
                    'М' -> "M"
                    'Н' -> "N"
                    'О' -> "O"
                    'П' -> "P"
                    'Р' -> "R"
                    'С' -> "S"
                    'Т' -> "T"
                    'У' -> "U"
                    'Ф' -> "F"
                    'Х' -> "Kh"
                    'Ц' -> "Ts"
                    'Ч' -> "Ch"
                    'Ш' -> "Sh"
                    'Щ' -> "Sch"
                    'Ъ' -> ""
                    'Ы' -> "Y"
                    'Ь' -> ""
                    'Э' -> "E"
                    'Ю' -> "Yu"
                    'Я' -> "Ya"
                    'а' -> "a"
                    'б' -> "b"
                    'в' -> "v"
                    'г' -> "g"
                    'д' -> "d"
                    'е' -> "e"
                    'ё' -> "e"
                    'ж' -> "zh"
                    'з' -> "z"
                    'и' -> "i"
                    'й' -> "y"
                    'к' -> "k"
                    'л' -> "l"
                    'м' -> "m"
                    'н' -> "n"
                    'о' -> "o"
                    'п' -> "p"
                    'р' -> "r"
                    'с' -> "s"
                    'т' -> "t"
                    'у' -> "u"
                    'ф' -> "f"
                    'х' -> "kh"
                    'ц' -> "ts"
                    'ч' -> "ch"
                    'ш' -> "sh"
                    'щ' -> "sch"
                    'ъ' -> ""
                    'ы' -> "y"
                    'ь' -> ""
                    'э' -> "e"
                    'ю' -> "yu"
                    'я' -> "ya"
                    else -> ch.toString()
                }
            )
        }

        return out.toString()
    }

    private fun isValidPackageName(value: String): Boolean {
        if (!value.contains('.')) return false
        for (ch in value) {
            val ok = ch.isLetterOrDigit() || ch == '_' || ch == '.'
            if (!ok) return false
        }
        return true
    }
}
