package com.salat.gbinder.util

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.os.Build
import android.service.media.MediaBrowserService
import com.salat.gbinder.entity.IconRef
import com.salat.gbinder.entity.InstalledAppInfoRef
import timber.log.Timber
import java.text.Collator
import java.util.Locale

class SystemAppsLightRepositoryImpl(private val context: Context) : SystemAppsLightRepository {
    companion object {
        internal const val HAV_YAM_PACKAGE = "yandex.auto.music"
        private const val DEBUG_M_PACKAGE = "debug.monjaro"
        private const val M_CONFIG_PACKAGE = "ru.monjaro.mconfig"
        private const val LAUNCHER3_PACKAGE = "com.android.launcher3"
        private const val YAN_PACKAGE = "ru.yandex.yandexnavi"
    }

    // private val excludedPackages = emptySet<String>()
    private val excludedActivities = setOf(
        "com.salat.gbinder.AppLauncher",
        "androidx.compose.ui.tooling.PreviewActivity",
        "androidx.activity.ComponentActivity",
        "com.google.",
    )

    override suspend fun getAllApps(
        roundIcon: Boolean,
        mediaSort: Boolean,
        iconQuality: Int
    ): List<InstalledAppInfoRef> = context.getInstalledAppsRefs(roundIcon, mediaSort, iconQuality)

    override suspend fun getApps(
        roundIcon: Boolean,
        iconQuality: Int,
        vararg packageNames: String
    ): List<InstalledAppInfoRef> = try {
        context.getInstalledAppsInfoRefs(roundIcon, iconQuality, *packageNames)
    } catch (e: PackageManager.NameNotFoundException) {
        Timber.e(e)
        emptyList()
    }

    override fun isPackageInstalled(packageName: String): Boolean {
        val pkg = packageName.trim()
        if (pkg.isEmpty()) return false

        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                context.packageManager.getPackageInfo(pkg, PackageManager.PackageInfoFlags.of(0))
            } else {
                @Suppress("DEPRECATION")
                context.packageManager.getPackageInfo(pkg, 0)
            }
            return true
        } catch (_: PackageManager.NameNotFoundException) {
            return false
        } catch (_: Exception) {
        }

        return try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                context.packageManager.getApplicationInfo(
                    pkg,
                    PackageManager.ApplicationInfoFlags.of(0)
                )
            } else {
                @Suppress("DEPRECATION")
                context.packageManager.getApplicationInfo(pkg, 0)
            }
            true
        } catch (_: Exception) {
            false
        }
    }

    override fun isDebugMInstalled(): Boolean = isPackageInstalled(DEBUG_M_PACKAGE)

    override fun isMConfigInstalled(): Boolean = isPackageInstalled(M_CONFIG_PACKAGE)

    override fun isSystemApp(packageName: String): Boolean {
        if (packageName.isBlank()) return false
        val pm = context.packageManager
        return try {
            val ai = pm.getApplicationInfo(packageName, 0)
            val flags = ai.flags
            ((flags and ApplicationInfo.FLAG_SYSTEM) != 0) ||
                    ((flags and ApplicationInfo.FLAG_UPDATED_SYSTEM_APP) != 0)
        } catch (_: PackageManager.NameNotFoundException) {
            false
        } catch (_: SecurityException) {
            false
        }
    }

    private fun Context.getInstalledAppsRefs(
        roundIcon: Boolean,
        mediaSort: Boolean,
        iconQuality: Int
    ): List<InstalledAppInfoRef> {
        // Builds the list of launchable apps with stable ids and icon references for the UI.
        val pm = packageManager
        val launcherIntent = Intent(Intent.ACTION_MAIN, null).apply {
            addCategory(Intent.CATEGORY_LAUNCHER)
        }
        val resolveInfos = pm.queryIntentActivities(launcherIntent, 0)
        val appList = mutableListOf<InstalledAppInfoRef>()
        val actCache = mutableMapOf<String, List<String>>()

        // Tracks (packageName + launcherActivity) pairs to keep only the first occurrence.
        val seen = HashSet<String>(resolveInfos.size * 2)

        // Some vendors ship multiple launcher aliases/activities under the same package.
        // We intentionally keep only a single entry for Launcher3 to avoid duplicates.
        var launcher3Added = false

        // val pkgBlock = excludedPackages.asSequence().map { it.lowercase() }.toHashSet()
        val actBlock = excludedActivities.asSequence().map { it.lowercase() }.toHashSet()

        for (info in resolveInfos) {
            val packageName = info.activityInfo.packageName
            val activityName = info.activityInfo.name

            // if (packageName.lowercase() in pkgBlock) continue
            if (activityName.lowercase() in actBlock) continue

            // Deduplicate by (packageName + launcherActivity), keeping only the first match.
            // For Launcher3 we deduplicate by package only.
            val dedupeKey = if (packageName == LAUNCHER3_PACKAGE) {
                if (launcher3Added) continue
                launcher3Added = true
                packageName
            } else {
                "$packageName|$activityName"
            }
            if (!seen.add(dedupeKey)) continue

            val appName = info.loadLabel(pm).toString()
            val appInfo = runCatching { pm.getApplicationInfo(packageName, 0) }.getOrNull()

            // Round icon defined on activity/alias (API 25+)
            val actRoundResId = readRoundIconResId(info.activityInfo)
            val actPkg = info.activityInfo.packageName

            // Resolve-level icon (handles <activity> and <activity-alias>)
            val riResId = runCatching { info.getIconResource() }.getOrDefault(0)
            val riPkg = info.resolvePackageName ?: packageName

            // Direct activity icon (can be set on alias)
            val actResId = info.activityInfo.icon

            // App-level round icon for forced round fallback
            val appRoundResId = appInfo?.let { readRoundIconResId(it) } ?: 0

            // Density stays the same; we do not rely on chooseIconResTriple for the round pick
            val density = iconQuality

            val (finalPkgForIcon, finalResId) = when {
                iconQuality == 0 -> packageName to 0
                // Prefer activity/alias round icon if requested
                roundIcon && actRoundResId != 0 -> actPkg to actRoundResId
                // Then prefer app-level round icon if activity/alias has no round
                roundIcon && appRoundResId != 0 -> packageName to appRoundResId
                // Fall back to alias/resolve normal icons
                riResId != 0 -> riPkg to riResId
                actResId != 0 -> actPkg to actResId
                // Final fallback to application's manifest icon
                else -> packageName to (appInfo?.icon ?: 0)
            }

            // Recompute versionCode for the actual package that owns the icon resource
            val vcFinal = readVersionCode(pm, finalPkgForIcon)

            val isMedia = pm.isMediaApp(packageName, appInfo)
            val iconRef = IconRef(finalPkgForIcon, finalResId, density, vcFinal)
            val id = ComponentName(packageName, activityName).flattenToString()

            appList.add(
                InstalledAppInfoRef(
                    id = id,
                    packageName = packageName,
                    appName = appName,
                    iconRef = iconRef,
                    isMedia = isMedia,
                    launcherActivity = activityName,
                    availableActivity = actCache.getOrPut(packageName) {
                        pm.listLaunchableActivities(packageName, actBlock, activityName)
                    }
                )
            )
        }

        // HAV_YAM_PACKAGE case
        runCatching { packageManager.getPackageInfo(HAV_YAM_PACKAGE, 0) }.getOrNull()
            ?.let { pkgInfo ->
                val packageName = pkgInfo.packageName
                val appName = pkgInfo.applicationInfo?.loadLabel(pm).toString()
                val (resId, density, vc) = chooseIconResTriple(
                    pm,
                    packageName,
                    roundIcon,
                    iconQuality
                )

                val appInfo = runCatching { pm.getApplicationInfo(packageName, 0) }.getOrNull()
                val finalResId = when {
                    iconQuality == 0 -> 0
                    resId != 0 -> resId
                    else -> appInfo?.icon ?: 0
                }

                val isMedia = pm.isMediaApp(packageName, appInfo)
                val id = packageName
                val shouldAdd = (iconQuality == 0) || (finalResId != 0)
                if (shouldAdd) {
                    appList.add(
                        InstalledAppInfoRef(
                            id = id,
                            packageName = packageName,
                            appName = appName,
                            iconRef = IconRef(packageName, finalResId, density, vc),
                            isMedia = isMedia,
                            launcherActivity = null,
                            availableActivity = pm.listLaunchableActivities(
                                packageName,
                                actBlock,
                                null
                            )
                        )
                    )
                }
            }
        return if (mediaSort) {
            appList.sortedWith(
                compareBy<InstalledAppInfoRef> {
                    when {
                        it.packageName.lowercase().contains("yandex") -> 0
                        it.appName.lowercase().contains("tv") -> 1
                        it.appName.lowercase().contains("music") -> 2
                        it.appName.lowercase().contains("maps") -> 3
                        it.appName.lowercase().contains("radio") -> 4
                        else -> 5
                    }
                }.thenBy { it.appName }
            )
        } else {
            val collator = Collator.getInstance(Locale.getDefault()).apply {
                strength = Collator.PRIMARY
            }
            appList.sortedWith { a, b ->
                val byName = collator.compare(a.appName, b.appName)
                if (byName != 0) byName else a.packageName.compareTo(b.packageName)
            }
        }
    }

    private fun Context.getInstalledAppsInfoRefs(
        roundIcon: Boolean,
        iconQuality: Int,
        vararg packageNames: String
    ): List<InstalledAppInfoRef> {
        val pm = packageManager
        val actBlock = excludedActivities.asSequence().map { it.lowercase() }.toHashSet()
        return packageNames.mapNotNull { pkgName ->
            try {
                val appInfo = pm.getApplicationInfo(pkgName, 0)
                val activityName = appInfo.name
                val appName = pm.getApplicationLabel(appInfo).toString()
                val id = if (activityName != null) {
                    ComponentName(pkgName, activityName).flattenToString()
                } else pkgName

                val (resId, density, vc) = chooseIconResTriple(
                    pm,
                    appInfo.packageName,
                    roundIcon,
                    iconQuality
                )

                val finalResId = when {
                    iconQuality == 0 -> 0
                    resId != 0 -> resId
                    else -> appInfo.icon
                }

                val isMedia = pm.isMediaApp(pkgName, appInfo)
                InstalledAppInfoRef(
                    id = id,
                    packageName = pkgName,
                    appName = appName,
                    iconRef = IconRef(pkgName, finalResId, density, vc),
                    isMedia = isMedia,
                    launcherActivity = activityName,
                    availableActivity = pm.listLaunchableActivities(pkgName, actBlock, activityName)
                )
            } catch (_: Exception) {
                Timber.e("App not found: $pkgName")
                null
            }
        }
    }

    private fun chooseIconResTriple(
        pm: PackageManager,
        packageName: String,
        preferRound: Boolean,
        densityDpi: Int
    ): Triple<Int, Int, Long> {
        return try {
            val appInfo = pm.getApplicationInfo(packageName, 0)
            val roundRes = readRoundIconResId(appInfo)
            val normalRes = appInfo.icon
            val chosenResId = if (preferRound) {
                if (roundRes != 0) roundRes else normalRes
            } else {
                if (normalRes != 0) normalRes else roundRes
            }
            Triple(chosenResId, densityDpi, readVersionCode(pm, packageName))
        } catch (_: Exception) {
            Triple(0, densityDpi, 0L)
        }
    }

    private fun readRoundIconResId(appInfo: ApplicationInfo): Int {
        return try {
            val f = ApplicationInfo::class.java.getField("roundIcon")
            f.getInt(appInfo)
        } catch (_: Throwable) {
            0
        }
    }

    private fun readRoundIconResId(activityInfo: android.content.pm.ActivityInfo): Int {
        return try {
            val f = activityInfo.javaClass.getField("roundIcon")
            f.getInt(activityInfo)
        } catch (_: Throwable) {
            0
        }
    }

    private fun readVersionCode(pm: PackageManager, packageName: String): Long {
        return try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                pm.getPackageInfo(packageName, 0).longVersionCode
            } else {
                @Suppress("DEPRECATION")
                pm.getPackageInfo(packageName, 0).versionCode.toLong()
            }
        } catch (_: Exception) {
            0L
        }
    }

    /**
     * Checks if the package contains a service implementing a media session via MediaBrowserService.
     *
     * @param packageName the package name of the application to check
     * @param applicationInfo information about the installed application; if null or disabled, returns false
     * @return true if the package has at least one service with the MediaBrowserService.SERVICE_INTERFACE
     */
    private fun PackageManager.isMediaApp(
        packageName: String,
        applicationInfo: ApplicationInfo?
    ): Boolean {
        if (applicationInfo?.enabled != true) return false

        // Exclude Yandex Navigator v25 from media detection to avoid false-positive activation tracking.
        // if (packageName == YAN_PACKAGE && isYandexNaviMajorVersion(25)) return false

        val mediaIntent = Intent(MediaBrowserService.SERVICE_INTERFACE).apply {
            `package` = packageName
        }
        val browserServices = queryIntentServices(mediaIntent, PackageManager.GET_META_DATA)
        if (browserServices.isNotEmpty()) return true

        return false
    }

    private fun PackageManager.isYandexNaviMajorVersion(major: Int): Boolean {
        return try {
            val pkgInfo = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                getPackageInfo(YAN_PACKAGE, PackageManager.PackageInfoFlags.of(0))
            } else {
                @Suppress("DEPRECATION")
                getPackageInfo(YAN_PACKAGE, 0)
            }
            val versionName = pkgInfo.versionName ?: return false
            versionName.substringBefore('.').toIntOrNull() == major
        } catch (_: Exception) {
            false
        }
    }

    // Collect all startable activities for this package: exported+enabled declared ones
    // and package-scoped launcher entries (aliases, etc.). Return distinct class names.
    @Suppress("DEPRECATION")
    private fun PackageManager.listLaunchableActivities(
        packageName: String,
        actBlock: Set<String>,
        mainActivity: String?
    ): List<String> {
        // Declared activities from manifest
        val declared = runCatching {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                val flags = PackageManager.PackageInfoFlags.of(
                    (PackageManager.GET_ACTIVITIES or PackageManager.GET_DISABLED_COMPONENTS).toLong()
                )
                getPackageInfo(packageName, flags).activities
            } else {
                @Suppress("DEPRECATION")
                getPackageInfo(
                    packageName,
                    PackageManager.GET_ACTIVITIES or PackageManager.GET_DISABLED_COMPONENTS
                ).activities
            }
        }.getOrNull().orEmpty()

        val exportedDeclared = declared.asSequence()
            .filter { it != null && it.enabled && it.exported }
            .mapNotNull { it.name }
            .filter { s -> actBlock.none { s.lowercase().contains(it) } }
            .toSet()

        // Launcher aliases/activities for this package
        val launcherIntent = Intent(Intent.ACTION_MAIN).apply {
            addCategory(Intent.CATEGORY_LAUNCHER)
            `package` = packageName
        }
        val launcher = queryIntentActivities(launcherIntent, 0).asSequence()
            .mapNotNull { it.activityInfo?.name }
            .filter { s -> actBlock.none { s.lowercase().contains(it) } }
            .toSet()

        // Union + stable order
        val merged = (exportedDeclared + launcher).distinct().sorted()

        return if (!mainActivity.isNullOrEmpty()) {
            val idx = merged.indexOf(mainActivity)
            if (idx > 0) {
                // Keep relative order of others; only move mainActivity to the front
                buildList {
                    add(merged[idx])
                    for (i in merged.indices) if (i != idx) add(merged[i])
                }
            } else {
                merged // either mainActivity already first or absent
            }
        } else {
            merged
        }
    }
}
