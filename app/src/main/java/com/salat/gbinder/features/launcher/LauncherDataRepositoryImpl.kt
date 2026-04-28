package com.salat.gbinder.features.launcher

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import com.salat.gbinder.BuildConfig
import com.salat.gbinder.DEFAULT_UI_SCALE
import com.salat.gbinder.GlobalState
import com.salat.gbinder.datastore.GeneralPrefs
import com.salat.gbinder.datastore.LauncherPrefs
import com.salat.gbinder.datastore.LauncherStorageRepository
import com.salat.gbinder.entity.DisplayLauncherApp
import com.salat.gbinder.entity.DisplayLauncherConfig
import com.salat.gbinder.entity.DisplayLauncherItem
import com.salat.gbinder.entity.DisplayLauncherItemType
import com.salat.gbinder.entity.LauncherItem
import com.salat.gbinder.entity.LauncherItemType
import com.salat.gbinder.entity.PackagesChangedEvent
import com.salat.gbinder.mappers.firstByPrefix
import com.salat.gbinder.mappers.toDataItems
import com.salat.gbinder.mappers.toDisplayItems
import com.salat.gbinder.mappers.toDisplayLauncherApps
import com.salat.gbinder.util.SystemAppsLightRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

class LauncherDataRepositoryImpl(
    private val context: Context,
    ioScope: CoroutineScope,
    private val storage: LauncherStorageRepository,
    private val systemApps: SystemAppsLightRepository
) : LauncherDataRepository {
    private val _myAppsItems = MutableStateFlow<List<DisplayLauncherItem>?>(null)
    override val myAppsItems = _myAppsItems.asStateFlow()

    private val _allApps = MutableStateFlow<List<DisplayLauncherApp>>(emptyList())
    override val allApps = _allApps.asStateFlow()

    private var rawAllApps: List<DisplayLauncherApp> = emptyList()

    private val _settingsConfig = MutableStateFlow<DisplayLauncherConfig?>(null)
    override val settingsConfig = _settingsConfig.asStateFlow()

    private var baseQuality: Int? = null
    private var baseShowFrozenApps: Boolean? = null

    init {
        ioScope.launch {
            // Collect general settings config
            launch {
                storage.dataStore.valuesFlowWithDefaults(
                    arrayOf(
                        LauncherPrefs.LAUNCHER_SCALE,
                        LauncherPrefs.LAUNCHER_ICON_QUALITY,
                        LauncherPrefs.LAUNCHER_ICON_OUT_SPACE,
                        LauncherPrefs.LAUNCHER_ICON_INNER_SPACE,
                        LauncherPrefs.LAUNCHER_ICON_SIZE,
                        LauncherPrefs.LAUNCHER_ICON_TEXT_SIZE,
                        LauncherPrefs.LAUNCHER_ICON_TEXT_PADDING,
                        LauncherPrefs.LAUNCHER_ICON_TEXT_ENABLE,
                        LauncherPrefs.LAUNCHER_ICON_ROUND,
                        LauncherPrefs.LAUNCHER_DEFAULT_TAB,
                        LauncherPrefs.LAUNCHER_WINDOW_MODE,
                        LauncherPrefs.LAUNCHER_WINDOW_SHOW_FRAME,
                        LauncherPrefs.LAUNCHER_WINDOW_HORIZONTAL_SPACE,
                        LauncherPrefs.LAUNCHER_WINDOW_VERTICAL_SPACE,
                        LauncherPrefs.LAUNCHER_WINDOW_ALPHA,
                        LauncherPrefs.LAUNCHER_LIGHT_THEME,
                        LauncherPrefs.LAUNCHER_ENABLE_SHORTCUTS,
                        LauncherPrefs.LAUNCHER_SHORTCUT_SIZE,
                        LauncherPrefs.LAUNCHER_DIVIDER_SIZE,
                        LauncherPrefs.LAUNCHER_DIVIDER_BOLD,
                        LauncherPrefs.LAUNCHER_ICON_TEXT_MULTILINE,
                        LauncherPrefs.LAUNCHER_RECENTS_ENABLE,
                        LauncherPrefs.LAUNCHER_AUTO_LIGHT_THEME,
                        LauncherPrefs.LAUNCHER_AUTO_LIGHT_THEME_START,
                        LauncherPrefs.LAUNCHER_AUTO_LIGHT_THEME_END,
                        GeneralPrefs.ENABLE_ADB_HELPER,
                        LauncherPrefs.LAUNCHER_SHOW_FROZEN_APPS,
                        LauncherPrefs.LAUNCHER_ALLOW_SYSTEM_APP_UNINSTALL
                    ),
                    listOf(
                        if (BuildConfig.DEBUG) .85f else DEFAULT_UI_SCALE,
                        DEFAULT_LAUNCHER_ICON_DENSITY,
                        DEFAULT_LAUNCHER_ICON_OUT_SPACE,
                        DEFAULT_LAUNCHER_ICON_INNER_SPACE,
                        DEFAULT_LAUNCHER_ICON_SIZE,
                        DEFAULT_LAUNCHER_ICON_TEXT_SIZE,
                        DEFAULT_LAUNCHER_ICON_TEXT_PADDING,
                        true, // icon text enabled
                        DEFAULT_LAUNCHER_ICON_ROUND,
                        0, // default tab
                        true, // window mode
                        true, // show frame
                        DEFAULT_LAUNCHER_WINDOW_HORIZONTAL_SPACE,
                        DEFAULT_LAUNCHER_WINDOW_VERTICAL_SPACE,
                        DEFAULT_LAUNCHER_WINDOW_ALPHA,
                        false, // light theme
                        true, // enable shortcuts
                        DEFAULT_LAUNCHER_SHORTCUT_SIZE,
                        DEFAULT_LAUNCHER_DIVIDER_SIZE,
                        false, // group divider bold
                        true, // icon text multiline
                        true, // recents
                        false, // auto light theme
                        DEFAULT_AUTO_LIGHT_THEME_START, // auto light theme start
                        DEFAULT_AUTO_LIGHT_THEME_END, // auto light theme end
                        false, // adb helper
                        true, // show frozen apps in all-apps grid
                        false // allow system app uninstall
                    )
                )
                    .flowOn(Dispatchers.IO)
                    .collect { prefs ->
                        val quality = prefs[1] as Int
                        _settingsConfig.update {
                            DisplayLauncherConfig(
                                uiScale = prefs[0] as Float,
                                iconQuality = quality,
                                iconOutSpace = prefs[2] as Int,
                                iconInnerSpace = prefs[3] as Int,
                                iconSize = prefs[4] as Int,
                                iconTextSize = prefs[5] as Int,
                                iconTextPadding = prefs[6] as Int,
                                iconTextEnable = prefs[7] as Boolean,
                                iconRound = prefs[8] as Int,
                                defaultTab = prefs[9] as Int,
                                windowMode = prefs[10] as Boolean,
                                windowShowFrame = prefs[11] as Boolean,
                                windowHorizontalSpace = prefs[12] as Int,
                                windowVerticalSpace = prefs[13] as Int,
                                windowAlpha = prefs[14] as Float,
                                lightTheme = prefs[15] as Boolean,
                                enableShortcuts = prefs[16] as Boolean,
                                shortcutSize = prefs[17] as Int,
                                dividerTextSize = prefs[18] as Int,
                                dividerTextBold = prefs[19] as Boolean,
                                iconTextMultiline = prefs[20] as Boolean,
                                recentsEnable = prefs[21] as Boolean,
                                autoLightTheme = prefs[22] as Boolean,
                                autoLightThemeStart = prefs[23] as Int,
                                autoLightThemeEnd = prefs[24] as Int,
                                enableAdbHelper = prefs[25] as Boolean,
                                showFrozenApps = prefs[26] as Boolean,
                                allowSystemAppUninstall = prefs[27] as Boolean,
                            )
                        }
                    }
            }

            // Settings changed
            launch {
                settingsConfig
                    .filterNotNull()
                    .distinctUntilChanged()
                    .collect { config ->

                        // TODO UPDATE MY APP BY CHANGE QUALITY

                        val needUpdate = baseQuality != config.iconQuality && baseQuality != null
                        val needVisibilityUpdate = baseShowFrozenApps != config.showFrozenApps &&
                                baseShowFrozenApps != null
                        // Use a single rebuild point to avoid duplication
                        if (rawAllApps.isEmpty() || needUpdate) {
                            rebuildAllApps(config)
                            rebuildMyApps()
                            clearOldIcons()
                        } else if (needVisibilityUpdate) {
                            _allApps.update {
                                filterVisibleLauncherApps(
                                    rawAllApps,
                                    config.showFrozenApps
                                )
                            }
                            rebuildMyApps()
                        }
                        baseQuality = config.iconQuality
                        baseShowFrozenApps = config.showFrozenApps
                    }
            }

            // Rebuild app lists by device packages changed
            launch {
                GlobalState.devicePackagesChangedFlow.collect { event ->

                    // Delete from my apps if system package removed
                    if (event is PackagesChangedEvent.Removed
                        && !systemApps.isPackageInstalled(event.packageName)
                    ) {
                        withContext(Dispatchers.IO) {
                            val storedItems = storage.getAll()
                            if (storedItems.any { it.packageName == event.packageName }) {
                                storage.save(storedItems.filter { it.packageName != event.packageName })
                            }
                        }
                    }

                    val config = settingsConfig.value ?: return@collect
                    rebuildAllApps(config)
                    rebuildMyApps()
                    clearOldIcons()
                }
            }
        }
    }

    override suspend fun saveMyApps(items: List<DisplayLauncherItem>) {
        withContext(Dispatchers.IO) {
            val saveItems = items.toDataItems(context, true)
            val mergedItems = mergeHiddenFrozenItems(saveItems)
            storage.save(mergedItems)
            mergedItems.applyMyApps()
        }
    }

    // Build all list apps
    private suspend fun rebuildAllApps(config: DisplayLauncherConfig) =
        withContext(Dispatchers.IO) {

            // Custom icons collecting
            val packagesCustomIcons = context
                .listIconFileNames()
                .filter { '_' in it }
                .pickLatestByPrefix()

            // All apps preparing
            val apps = systemApps
                .getLauncherApps(DEFAULT_LAUNCHER_ICON_IS_ROUND, false, config.iconQuality)
                .toDisplayLauncherApps(context, packagesCustomIcons)
            rawAllApps = apps
            _allApps.update { filterVisibleLauncherApps(apps, config.showFrozenApps) }
            Timber.d("[LAUNCHER DATA] all apps updated")
        }

    // Parse from storage and set my apps list
    override suspend fun rebuildMyApps() = withContext(Dispatchers.Default) {
        // My apps preparing
        val myAppsJson = storage.flow.first()
        storage.parse(myAppsJson ?: "").applyMyApps()
    }

    private suspend fun clearOldIcons() {
        val allAppsIcons: List<Uri> = rawAllApps.filter { it.customIcon != null }.map {
            it.customIcon!!
        }
        val myAppsIcons = withContext(Dispatchers.IO) {
            storage.getAll().mapNotNull { item ->
                item.customIcon?.let { IconUriUtils.iconFileNameToContentUri(context, it) }
            }
        }

        val used = buildList {
            addAll(allAppsIcons)
            addAll(myAppsIcons)
        }
        runCatching { pruneUnusedIcons(context, used) }
            .onFailure { Timber.w(it) }
    }

    // Prepare display items and apply my apps list
    private suspend fun List<LauncherItem>.applyMyApps() = withContext(Dispatchers.Default) {
        // TODO APPLY CUSTOM ICONS FROM DISC?

        val config = settingsConfig.value
        val allAppsByPackages =
            rawAllApps.associateBy { it.packageName + (it.launcherActivity ?: "") }

        val newMyApps = this@applyMyApps.toDisplayItems(
            context = context,
            sortedByOrder = true,
            allPackages = allAppsByPackages
        ).let { items ->
            filterVisibleLauncherItems(items, config?.showFrozenApps ?: true)
        }
        _myAppsItems.update { newMyApps }
        Timber.d("[LAUNCHER DATA] my apps updated")
    }

    private suspend fun mergeHiddenFrozenItems(items: List<LauncherItem>): List<LauncherItem> {
        val config = settingsConfig.value ?: return items
        if (config.showFrozenApps) return items

        val storedItems = storage.getAll().sortedBy { it.order }
        if (storedItems.isEmpty()) return items

        val appsByPackages = rawAllApps.associateBy { it.packageName + (it.launcherActivity ?: "") }
        val hiddenStoredItems = storedItems
            .filter { it.isHiddenFrozen(appsByPackages) }
            .map { it.id }
            .toHashSet()
        if (hiddenStoredItems.isEmpty()) return items

        val iterator = items.iterator()
        val merged = buildList {
            storedItems.forEach { item ->
                if (item.id in hiddenStoredItems) {
                    add(item)
                } else if (iterator.hasNext()) {
                    add(iterator.next())
                }
            }
            while (iterator.hasNext()) {
                add(iterator.next())
            }
        }

        var order = 0
        return merged.map { item ->
            order++
            item.copy(order = order)
        }
    }

    private fun LauncherItem.isHiddenFrozen(
        allPackages: Map<String, DisplayLauncherApp>
    ): Boolean {
        val appInfo = when (type) {
            LauncherItemType.APP -> allPackages[packageName + launchActivity]
            LauncherItemType.ACTIVITY -> allPackages[packageName + launchActivity]
                ?: allPackages.firstByPrefix(packageName)?.second

            LauncherItemType.GROUP,
            LauncherItemType.MACRO -> null
        }
        return appInfo?.isFrozen == true
    }

    override suspend fun applyIcon(id: Long, packageName: String, image: Uri) {
        withContext(Dispatchers.IO) {
            runCatching {
                if (id != 0L) {
                    // Change my app icon by id
                    val item = myAppsItems.value?.find { it.id == id } ?: return@runCatching
                    val packageFilename =
                        item.packageName.takeIf { item.type == DisplayLauncherItemType.APP }?.let {
                            it + "_" + System.currentTimeMillis()
                        }

                    val file = copyAndResizeIcon(context, image, 192, packageFilename)

                    storage.getAll().map {
                        if (it.id == item.id) {
                            it.copy(customIcon = file.name)
                        } else it
                    }.let { storage.save(it) }
                } else {
                    // Change app icon by package
                    val item =
                        allApps.value.find { it.packageName == packageName } ?: return@runCatching

                    val packageFilename = packageName + "_" + System.currentTimeMillis()
                    val file = copyAndResizeIcon(context, image, 192, packageFilename)

                    storage.getAll().map {
                        if (it.packageName == item.packageName && it.type == LauncherItemType.APP) {
                            it.copy(customIcon = file.name)
                        } else it
                    }.let { storage.save(it) }
                }

                val config = settingsConfig.value ?: return@runCatching
                rebuildAllApps(config)
                rebuildMyApps()
                clearOldIcons()
            }.onFailure { Timber.e(it) }
        }
    }

    override suspend fun saveIcon(id: Long, image: Bitmap): String {
        return withContext(Dispatchers.IO) {
            val file = copyAndResizeIcon(context, image, 192)
            file.name
        }
    }

    override suspend fun clearIcon(id: Long, packageName: String) {
        withContext(Dispatchers.IO) {
            if (id != 0L) {
                storage.getAll().map {
                    if (it.id == id) {

                        if (it.type == LauncherItemType.APP) {
                            // Delete package files
                            context.deleteIconsByPrefix(it.packageName)
                        }

                        it.copy(customIcon = null)
                    } else it
                }.let { storage.save(it) }
            } else {
                // Delete package files
                context.deleteIconsByPrefix(packageName)

                storage.getAll().map {
                    if (it.packageName == packageName && it.type == LauncherItemType.APP) {
                        it.copy(customIcon = null)
                    } else it
                }.let { storage.save(it) }
            }
        }

        val config = settingsConfig.value ?: return
        rebuildAllApps(config)
        rebuildMyApps()
        clearOldIcons()
    }
}
