package com.salat.gbinder.features.adbTerminal

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.salat.gbinder.adb.domain.repository.AdbRepository
import com.salat.gbinder.datastore.DataStoreRepository
import com.salat.gbinder.datastore.NoBackupPrefs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import javax.inject.Inject

private const val MAX_OUTPUT_LINES = 2000
private const val MAX_RECENT_COMMANDS = 50

@Immutable
data class AdbTerminalBlock(
    val id: Long,
    val command: String,
    val lines: List<String>,
    val totalLines: Int,
    val exitCode: Int
)

@HiltViewModel
class AdbTerminalViewModel @Inject constructor(
    private val adb: AdbRepository,
    private val dataStore: DataStoreRepository
) : ViewModel() {

    private val _blocks = MutableStateFlow<List<AdbTerminalBlock>>(emptyList())
    val blocks = _blocks.asStateFlow()

    private val _running = MutableStateFlow(false)
    val running = _running.asStateFlow()

    private val _recent = MutableStateFlow<List<String>>(emptyList())
    val recent = _recent.asStateFlow()

    private val _deviceCommands = MutableStateFlow<List<String>>(emptyList())
    val deviceCommands = _deviceCommands.asStateFlow()

    private val _device = MutableStateFlow(DeviceLists())
    val device = _device.asStateFlow()

    // Keeps the two executeRaw callers of this model from overlapping in the repository
    private val commandLock = Mutex()

    private var nextBlockId = 0L
    private var harvestStarted = false
    private var workingDir = ROOT_DIR

    init {
        viewModelScope.launch {
            _recent.value = parseRecent(
                dataStore.getValueFlow(NoBackupPrefs.ADB_TERMINAL_RECENT).first()
            )
        }
    }

    fun execute(input: String) {
        if (_running.value) return
        val command = normalizeCommand(input)
        if (command.isEmpty()) return

        _running.value = true
        viewModelScope.launch {
            try {
                rememberRecent(command)
                val result = commandLock.withLock {
                    adb.executeRaw(shellCommand(command, workingDir))
                }

                // A successful cd answers with the directory it landed in
                val output = if (isChangeDirectory(command) && result.exitCode == 0) {
                    workingDir = result.output.trim().ifEmpty { ROOT_DIR }
                    workingDir
                } else {
                    result.output
                }

                val (lines, totalLines) = splitOutput(output)
                _blocks.value = _blocks.value + AdbTerminalBlock(
                    id = nextBlockId++,
                    command = command,
                    lines = lines,
                    totalLines = totalLines,
                    exitCode = result.exitCode
                )
            } finally {
                _running.value = false
            }
        }
    }

    private suspend fun read(command: String, parse: (String) -> List<String>): List<String>? {
        val result = commandLock.withLock { adb.executeRaw(command) }
        return if (result.exitCode == 0) parse(result.output) else null
    }

    fun stop() = adb.cancelTerminalCommand()

    fun harvestDeviceCommands() {
        if (harvestStarted) return
        harvestStarted = true
        viewModelScope.launch {
            var complete = true
            val found = LinkedHashSet<String>()

            for (dir in DEVICE_BINARY_DIRS) {
                // A user command must never wait behind the scan
                if (_running.value) {
                    complete = false
                    break
                }
                val result = commandLock.withLock { adb.executeRaw(listBinariesCommand(dir)) }
                if (result.exitCode == 0) found += parseBinaryNames(result.output) else complete = false
            }
            if (found.isNotEmpty()) _deviceCommands.value = found.sorted()

            if (complete) {
                val packages = read(LIST_PACKAGES_COMMAND, ::parsePackageNames)
                val permissions = read(LIST_PERMISSIONS_COMMAND, ::parsePermissionNames)
                val services = read(LIST_SERVICES_COMMAND, ::parseServiceNames)
                val dumpsysServices = read(LIST_DUMPSYS_SERVICES_COMMAND, ::parseServiceNames)

                _device.value = DeviceLists(
                    packages = packages.orEmpty(),
                    permissions = permissions.orEmpty(),
                    services = services.orEmpty(),
                    dumpsysServices = dumpsysServices.orEmpty()
                )
                complete = packages != null && permissions != null &&
                        services != null && dumpsysServices != null
            }

            // A failed scan retries the next time the screen opens
            if (!complete) harvestStarted = false
        }
    }

    fun clearOutput() {
        _blocks.value = emptyList()
    }

    private suspend fun splitOutput(output: String): Pair<List<String>, Int> =
        withContext(Dispatchers.Default) {
            if (output.isEmpty()) return@withContext emptyList<String>() to 0
            val all = output.lines()
            all.take(MAX_OUTPUT_LINES) to all.size
        }

    private suspend fun rememberRecent(command: String) {
        val updated = (listOf(command) + _recent.value.filterNot { it == command })
            .take(MAX_RECENT_COMMANDS)
        _recent.value = updated
        try {
            dataStore.saveValue(NoBackupPrefs.ADB_TERMINAL_RECENT, Json.encodeToString(updated))
        } catch (_: Exception) {
            // ignore errors
        }
    }

    private fun parseRecent(json: String?): List<String> {
        return try {
            if (json.isNullOrBlank()) emptyList() else Json.decodeFromString(json)
        } catch (_: Exception) {
            emptyList()
        }
    }
}
