package com.salat.gbinder

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Html
import android.text.Spanned
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.salat.gbinder.adb.domain.repository.AdbRepository
import com.salat.gbinder.components.cleanupShareTempFiles
import com.salat.gbinder.components.isNotificationServiceEnabled
import com.salat.gbinder.components.openAccessibilitySettings
import com.salat.gbinder.components.openUrlSmart
import com.salat.gbinder.components.requestNotificationServicePermission
import com.salat.gbinder.components.requireDisplayOverlay
import com.salat.gbinder.components.shareText
import com.salat.gbinder.components.shareTextAsGibbFile
import com.salat.gbinder.components.toAnnotatedString
import com.salat.gbinder.components.toast
import com.salat.gbinder.coroutines.IoCoroutineScope
import com.salat.gbinder.datastore.DataStoreBackupTask
import com.salat.gbinder.datastore.DataStoreRepository
import com.salat.gbinder.datastore.FavoriteStorageRepository
import com.salat.gbinder.datastore.GeneralPrefs
import com.salat.gbinder.datastore.KeyBindStorageRepository
import com.salat.gbinder.datastore.LauncherPrefs
import com.salat.gbinder.datastore.NoBackupPrefs
import com.salat.gbinder.entity.DISPLAY_LAMP_MODES
import com.salat.gbinder.entity.DeviceLinkInfo
import com.salat.gbinder.entity.DisplayAdbState
import com.salat.gbinder.entity.DisplayAppUpdate
import com.salat.gbinder.entity.DisplayKeyAction
import com.salat.gbinder.entity.DisplayKeyBind
import com.salat.gbinder.entity.HugeTogglerItem
import com.salat.gbinder.entity.KeyBindAction
import com.salat.gbinder.entity.UiDownloadState
import com.salat.gbinder.features.configurator.RenderConfigurator
import com.salat.gbinder.features.configurator.RenderConfiguratorPresets
import com.salat.gbinder.features.launcher.BACKUP_DIVIDER
import com.salat.gbinder.features.launcher.backupIconsToString
import com.salat.gbinder.features.launcher.restoreIconsFromString
import com.salat.gbinder.mappers.keyCodeMap
import com.salat.gbinder.mappers.toAllDisplay
import com.salat.gbinder.mappers.toDisplayAdbState
import com.salat.gbinder.mappers.toDisplayIcon
import com.salat.gbinder.statekeeper.domain.repository.StateKeeperRepository
import com.salat.gbinder.ui.BaseButton
import com.salat.gbinder.ui.ConfirmDialog
import com.salat.gbinder.ui.DrawableImage
import com.salat.gbinder.ui.HugeSegmentToggler
import com.salat.gbinder.ui.KeyBindingDialog
import com.salat.gbinder.ui.NotifPickerDialog
import com.salat.gbinder.ui.RenderListButton
import com.salat.gbinder.ui.RenderMediaAppsPickerDialog
import com.salat.gbinder.ui.RenderSwitcher
import com.salat.gbinder.ui.StatusLamp
import com.salat.gbinder.ui.TargetRestoreDMDialog
import com.salat.gbinder.ui.ValueSlider
import com.salat.gbinder.ui.theme.AppTheme
import com.salat.gbinder.util.SystemAppsLightRepository
import com.salat.gbinder.util.encodeBase64Jvm
import com.salat.gbinder.util.getDisplayDriveModeName
import com.salat.gbinder.util.millisToDateTimeString
import com.salat.gbinder.util.promptInstall
import com.salat.gbinder.util.toContentUri
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.DecimalFormat
import java.util.Locale
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine
import kotlin.math.roundToInt

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    companion object {
        private const val LINE_SEPARATOR = "\n"
        private const val HTML_LINE_SEPARATOR = "<br>"
    }

    @Inject
    @IoCoroutineScope
    lateinit var ioScope: CoroutineScope

    @Inject
    lateinit var systemApps: SystemAppsLightRepository

    @Inject
    lateinit var dataStore: DataStoreRepository

    @Inject
    lateinit var stateKeeper: StateKeeperRepository

    @Inject
    lateinit var keyBindStorage: KeyBindStorageRepository

    @Inject
    lateinit var favoriteStorage: FavoriteStorageRepository

    @Inject
    lateinit var adb: AdbRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            SystemBarStyle.dark(Color.Transparent.toArgb()),
            SystemBarStyle.dark(Color.Transparent.toArgb())
        )

        setContent {
            var uiScale by remember {
                mutableFloatStateOf(stateKeeper.uiScales.value?.first ?: DEFAULT_UI_SCALE)
            }
            val context = LocalContext.current
            val density = LocalDensity.current
            val scaledDensity = remember(density, uiScale) {
                Density(
                    density.density * uiScale,
                    density.fontScale * uiScale
                )
            }

            LaunchedEffect(Unit) {
                stateKeeper.uiScales.collect { updatedUiScale ->
                    uiScale = updatedUiScale?.first ?: DEFAULT_UI_SCALE
                }
            }

            AppTheme(
                darkTheme = true
            ) {
                val scope = rememberCoroutineScope()

                val canAccessibility = if (!BuildConfig.DEBUG) {
                    viewModel.canAccessibility.collectAsStateWithLifecycle()
                } else {
                    remember { mutableStateOf(true) }
                }
                val bindsImport by viewModel.bindsImport.collectAsStateWithLifecycle()
                val appUpdateInfo by viewModel.appUpdateInfo.collectAsStateWithLifecycle()
                val updateDownloadState by viewModel.updateDownloadState.collectAsStateWithLifecycle()

                var showConfigurator by rememberSaveable { mutableStateOf(Pair(false, false)) }
                var configuratorPresets by remember { mutableStateOf(false) }

                var isEnabled by remember { mutableStateOf(false) }
                var isDebugMode by remember { mutableStateOf(false) }
                var fullBroadcast by remember { mutableStateOf(false) }
                var trackKeyEvents by remember { mutableStateOf(false) }
                var enableCustomLongClick by remember { mutableStateOf(false) }
                var enabledCustomShortClick by remember { mutableStateOf(false) }
                var customLongClickTiming by remember { mutableIntStateOf(1000) }
                var lockDoubleClick by remember { mutableStateOf(false) }
                var doubleClickTime by remember { mutableIntStateOf(300) }
                var multiLongPressEnabled by remember { mutableStateOf(false) }
                var suppressionMode by remember { mutableStateOf(false) }
                var mediaControlEnabled by remember { mutableStateOf(false) }
                var disableOnClimate by remember { mutableStateOf(false) }
                var sourceManagement by remember { mutableStateOf(false) }
                var radioBtControl by remember { mutableStateOf(true) }
                var rememberDriveMode by remember { mutableStateOf(false) }
                var targetRecoveryDriveMode by remember { mutableIntStateOf(LAST_DM_ID) }
                var driveModeOverlay by remember { mutableStateOf(false) }
                var driveModeOverlayScale by remember { mutableFloatStateOf(0f) }
                var driveModeOverlayOffset by remember { mutableFloatStateOf(0f) }
                var configuratorWarning by remember { mutableStateOf(true) }
                var mediaDataTranslator by remember { mutableStateOf(false) }
                var deepLogs by remember { mutableStateOf(false) }
                var keyBinds by remember { mutableStateOf<List<DisplayKeyBind>?>(null) }
                var altMute by remember { mutableStateOf(false) }
                var altMenu by remember { mutableStateOf(false) }
                var altLongTime by remember { mutableIntStateOf(ADDITIONAL_KEYS_MIN_LONG_PRESS_TIME) }

                var adbConnectionState by remember {
                    mutableStateOf<DisplayAdbState>(
                        DisplayAdbState.Disconnected
                    )
                }
                var enableAdbHelper by remember { mutableStateOf(false) }
                var adbHelperPort by remember { mutableIntStateOf(5555) }
                var adbDimAutoStop by remember { mutableStateOf(false) }

                var readyUi by remember { mutableStateOf(false) }

                LaunchedEffect(Unit) {
                    launch {
                        adb.connectionState.collect { state ->
                            adbConnectionState = state.toDisplayAdbState()
                        }
                    }
                }
                LaunchedEffect(Unit) {
                    launch {
                        dataStore.valuesFlowWithDefaults(
                            MainScreenSettingsRow.keys,
                            MainScreenSettingsRow.defaults
                        ).collect { row ->
                            isEnabled = row[0] as Boolean
                            isDebugMode = row[1] as Boolean
                            fullBroadcast = row[2] as Boolean
                            trackKeyEvents = row[3] as Boolean
                            enableCustomLongClick = row[4] as Boolean
                            customLongClickTiming = row[5] as Int
                            lockDoubleClick = row[6] as Boolean
                            doubleClickTime = row[7] as Int
                            enabledCustomShortClick = row[8] as Boolean
                            multiLongPressEnabled = row[9] as Boolean
                            suppressionMode = row[10] as Boolean
                            disableOnClimate = row[11] as Boolean
                            sourceManagement = row[12] as Boolean
                            radioBtControl = row[13] as Boolean
                            rememberDriveMode = row[14] as Boolean
                            targetRecoveryDriveMode = row[15] as Int
                            driveModeOverlay = row[16] as Boolean
                            driveModeOverlayScale = row[17] as Float
                            driveModeOverlayOffset = row[18] as Float
                            configuratorWarning = row[19] as Boolean
                            mediaDataTranslator = row[20] as Boolean
                            deepLogs = row[21] as Boolean
                            mediaControlEnabled = row[22] as Boolean
                            enableAdbHelper = row[23] as Boolean
                            adbHelperPort = row[24] as Int
                            adbDimAutoStop = row[25] as Boolean
                            altMenu = row[26] as Boolean
                            altMute = row[27] as Boolean
                            altLongTime = row[28] as Int
                        }
                    }
                    launch {
                        dataStore.getValueFlow(GeneralPrefs.KEY_BINDS).collect { json ->
                            json?.let {
                                val apps = keyBindStorage.parseBinds(json)

                                keyBinds = apps.map { (bindName, action) ->
                                    val type = when {
                                        bindName.startsWith("sc") -> context.getString(R.string.kbd_pattern_short2)
                                        bindName.startsWith("ml") -> context.getString(R.string.kbd_pattern_multi2)
                                        bindName.startsWith("lp") -> context.getString(R.string.kbd_pattern_long2)
                                        bindName.startsWith("dc") -> context.getString(R.string.kbd_pattern_double2)
                                        else -> ""
                                    }
                                    val keys = extractInts(bindName).map { keyCodeMap[it] ?: "" }
                                    val app = if (action.action == KeyBindAction.LAUNCH_APP) {
                                        action.value.let {
                                            systemApps.getApps(APP_ICON_ROUND, APP_ICON_QUALITY, it)
                                        }.toAllDisplay().firstOrNull()
                                    } else null
                                    val link = if (action.action == KeyBindAction.LAUNCH_LINK) {
                                        try {
                                            val intent = Intent.parseUri(
                                                action.value,
                                                Intent.URI_INTENT_SCHEME
                                            )

                                            val title = intent.getStringExtra("gib_name")
                                                ?: "Shortcut"
                                            val subtitle = intent.getStringExtra("gib_package")
                                                ?: intent.component?.packageName
                                                ?: intent.action
                                                ?: "Shortcut"
                                            val appIcon = systemApps
                                                .getApps(APP_ICON_ROUND, APP_ICON_QUALITY, subtitle)
                                                .firstOrNull()
                                                ?.iconRef
                                                ?.toDisplayIcon()

                                            DeviceLinkInfo(
                                                title = title,
                                                subtitle = subtitle,
                                                icon = appIcon
                                            )
                                        } catch (e: Exception) {
                                            Timber.e(e)
                                            null
                                        }
                                    } else null

                                    val driveMode = when (action.action) {
                                        KeyBindAction.TOGGLE_DM -> action.value.toIntOrNull()
                                            ?.getDisplayDriveModeName() ?: ""

                                        KeyBindAction.CAROUSEL_DM -> {
                                            val driveModes = action.value
                                                .split("|")
                                                .map { it.toIntOrNull() ?: 0 }
                                                .filter { it != 0 }

                                            buildString {
                                                driveModes.forEachIndexed { index, id ->
                                                    if (index != 0) {
                                                        append(", ")
                                                    }
                                                    append(id.getDisplayDriveModeName())
                                                }
                                            }
                                        }

                                        else -> null
                                    }

                                    val lampModes =
                                        if (action.action == KeyBindAction.CAROUSEL_LAMP) {
                                            val lampModes = action.value
                                                .split("|")
                                                .map { it.toIntOrNull() ?: -1 }
                                                .filter { it != -1 }
                                            val names = buildMap {
                                                DISPLAY_LAMP_MODES.map {
                                                    put(it.id, context.getString(it.displayTitle))
                                                }
                                            }

                                            buildString {
                                                lampModes.forEachIndexed { index, id ->
                                                    if (index != 0) {
                                                        append(", ")
                                                    }
                                                    append(names.getOrDefault(id, ""))
                                                }
                                            }
                                        } else null

                                    val phone = if (action.action == KeyBindAction.PHONE_CALL) {
                                        action.value
                                    } else null

                                    DisplayKeyBind(
                                        bindName = bindName,
                                        keyNames = keys,
                                        action = when (action.action) {
                                            KeyBindAction.LAUNCH_APP -> DisplayKeyAction.LAUNCH_APP
                                            KeyBindAction.LAUNCH_LINK -> DisplayKeyAction.LAUNCH_LINK
                                            KeyBindAction.APP_LAUNCHER -> DisplayKeyAction.APP_LAUNCHER
                                            KeyBindAction.TOGGLE_DM -> DisplayKeyAction.TOGGLE_DM
                                            KeyBindAction.CAROUSEL_DM -> DisplayKeyAction.CAROUSEL_DM
                                            KeyBindAction.PHONE_CALL -> DisplayKeyAction.PHONE_CALL
                                            KeyBindAction.CAMERAS_360 -> DisplayKeyAction.CAMERAS_360
                                            KeyBindAction.CAROUSEL_LAMP -> DisplayKeyAction.CAROUSEL_LAMP
                                            KeyBindAction.TASK_MANAGER -> DisplayKeyAction.TASK_MANAGER
                                            KeyBindAction.ANDROID_BACK -> DisplayKeyAction.ANDROID_BACK
                                            KeyBindAction.ANDROID_HOME -> DisplayKeyAction.ANDROID_HOME
                                            KeyBindAction.NAVIGATE_TO_PAST_APP -> DisplayKeyAction.NAVIGATE_TO_PAST_APP
                                        },
                                        type = type,
                                        app = app,
                                        link = link,
                                        phone = phone,
                                        driveModes = driveMode,
                                        lampModes = lampModes
                                    )
                                }.filter { it.keyNames.isNotEmpty() }
                            }
                        }
                    }
                    readyUi = true
                }

                LaunchedEffect(updateDownloadState) {
                    runCatching {
                        if (updateDownloadState is UiDownloadState.Error) {
                            context.toast(context.getString(R.string.data_fetch_failed))
                            viewModel.clearUpdateDownloadState()
                        }

                        if (updateDownloadState is UiDownloadState.Success) {
                            val uri = (updateDownloadState as UiDownloadState.Success).uri
                                .toContentUri(context)
                            promptInstall(context, uri)
                            viewModel.clearUpdateDownloadState()
                        }
                    }
                }

                var isNotificationServiceEnabled by remember {
                    mutableStateOf(context.isNotificationServiceEnabled())
                }

                var isDebugMInstalled by remember { mutableStateOf(false) }
                var isMConfigMInstalled by remember { mutableStateOf(false) }

                val lifecycleOwner = LocalLifecycleOwner.current
                DisposableEffect(lifecycleOwner) {
                    val observer = LifecycleEventObserver { _, event ->
                        when (event) {
                            Lifecycle.Event.ON_RESUME -> {
                                isNotificationServiceEnabled =
                                    context.isNotificationServiceEnabled()

                                scope.launch(Dispatchers.IO) {
                                    isDebugMInstalled = systemApps.isDebugMInstalled()
                                    isMConfigMInstalled = systemApps.isMConfigInstalled()
                                }
                            }

                            else -> Unit
                        }
                    }
                    lifecycleOwner.lifecycle.addObserver(observer)
                    onDispose { lifecycleOwner.lifecycle.removeObserver(observer) }
                }

                CompositionLocalProvider(LocalDensity provides scaledDensity) {
                    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                        val importBindsConfirmDialog by remember { derivedStateOf { bindsImport.isNotEmpty() } }
                        if (importBindsConfirmDialog) {
                            ConfirmDialog(
                                title = stringResource(R.string._import),
                                message = stringResource(R.string.import_data_prompt),
                                uiScale = uiScale,
                                negativeAction = false,
                                onCancel = { viewModel.setBindsImport("") },
                                onDismiss = { viewModel.setBindsImport("") },
                                onClick = {
                                    scope.launch(Dispatchers.IO) {
                                        onImportBinds(bindsImport)
                                        viewModel.setBindsImport("")
                                    }
                                }
                            )
                        }

                        RenderSettingsImportDialog(uiScale, canAccessibility.value)

                        var deleteConfirmDialog by remember { mutableStateOf<String?>(null) }
                        deleteConfirmDialog?.let { _ ->
                            ConfirmDialog(
                                title = stringResource(R.string.confirm_delete_title),
                                message = stringResource(R.string.confirm_delete_message),
                                uiScale = uiScale,
                                negativeAction = true,
                                onCancel = { deleteConfirmDialog = null },
                                onDismiss = { deleteConfirmDialog = null },
                                onClick = {
                                    scope.launch(Dispatchers.IO) {
                                        deleteConfirmDialog?.let {
                                            keyBindStorage.deleteBind(it)
                                        }

                                        deleteConfirmDialog = null
                                    }
                                }
                            )
                        }

                        var actionBindLockConfirmDialog by remember { mutableStateOf<Boolean?>(null) }
                        actionBindLockConfirmDialog?.let { _ ->
                            ConfirmDialog(
                                title = stringResource(R.string.suppression_mode),
                                message = stringResource(R.string.confirm_block_button_actions),
                                uiScale = uiScale,
                                negativeAction = true,
                                onCancel = { actionBindLockConfirmDialog = null },
                                onDismiss = { actionBindLockConfirmDialog = null },
                                onClick = {
                                    scope.launch(Dispatchers.IO) {
                                        suppressionMode = true
                                        scope.launch {
                                            dataStore.saveValue(
                                                GeneralPrefs.SUPPRESSION_MODE,
                                                true
                                            )
                                        }
                                        actionBindLockConfirmDialog = null
                                    }
                                }
                            )
                        }

                        fun onDeleteDialog(bindCode: String) {
                            deleteConfirmDialog = bindCode
                        }

                        val scrollState = rememberScrollState()
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(AppTheme.colors.surfaceBackground)
                                .padding(innerPadding)
                                .then(
                                    if (showConfigurator.first || configuratorPresets) {
                                        Modifier
                                    } else Modifier.verticalScroll(scrollState)
                                ),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            if (!canAccessibility.value) {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text(
                                        modifier = Modifier
                                            .padding(horizontal = 24.dp),
                                        text = stringResource(R.string.enable_accessibility),
                                        textAlign = TextAlign.Center,
                                        style = AppTheme.typography.screenTitle.copy(
                                            lineHeight = 23.sp
                                        ),
                                        color = AppTheme.colors.contentPrimary
                                    )
                                    Spacer(Modifier.height(36.dp))
                                    BaseButton(
                                        title = stringResource(R.string.accessibility_features),
                                        onClick = { context.openAccessibilitySettings() })
                                }
                            } else if (showConfigurator.first) {
                                BackHandler { showConfigurator = false to false }

                                if (configuratorWarning) {

                                    Column(
                                        modifier = Modifier.fillMaxSize(),
                                        verticalArrangement = Arrangement.Center,
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {

                                        Text(
                                            modifier = Modifier
                                                .padding(horizontal = 24.dp),
                                            text = stringResource(R.string.warning_disclaimer),
                                            textAlign = TextAlign.Center,
                                            style = AppTheme.typography.screenTitle.copy(
                                                lineHeight = 23.sp
                                            ),
                                            color = AppTheme.colors.contentPrimary
                                        )
                                        Spacer(Modifier.height(36.dp))
                                        BaseButton(
                                            title = stringResource(R.string.im_scared_but_ready),
                                            backgroundColor = AppTheme.colors.addSplitTop,
                                            onClick = {
                                                scope.launch {
                                                    dataStore.saveValue(
                                                        NoBackupPrefs.CONFIGURATOR_WARNING,
                                                        false
                                                    )
                                                    configuratorWarning = false
                                                }
                                            })
                                        Spacer(Modifier.height(24.dp))
                                        BaseButton(
                                            title = stringResource(R.string.not_that_interested),
                                            backgroundColor = AppTheme.colors.surfaceMenu,
                                            onClick = {
                                                showConfigurator = false to false
                                            })
                                    }
                                } else RenderConfigurator(
                                    viewModel = viewModel,
                                    uiScaleState = uiScale,
                                    onlyFavorite = showConfigurator.second,
                                    favoriteStorage = remember { favoriteStorage },
                                    onClose = { showConfigurator = false to false }
                                )
                            } else if (configuratorPresets) {
                                RenderConfiguratorPresets(
                                    uiScaleState = uiScale,
                                    enableAdbHelper = enableAdbHelper,
                                    adbDimAutoStop = adbDimAutoStop,
                                    onAdbDimAutoStopChanged = {
                                        scope.launch {
                                            dataStore.saveValue(GeneralPrefs.ADB_DIM_AUTO_STOP, it)
                                        }
                                    },
                                    onClose = { configuratorPresets = false }
                                )
                            } else if (readyUi) {
                                Spacer(Modifier.height(26.dp))

                                Text(
                                    modifier = Modifier,
                                    text = stringResource(
                                        if (isEnabled) {
                                            R.string.binder_active
                                        } else {
                                            R.string.binder_disabled
                                        }
                                    ),
                                    style = AppTheme.typography.dialogTitle,
                                    color = if (isEnabled) {
                                        AppTheme.colors.contentAccent
                                    } else {
                                        AppTheme.colors.sliderPassive
                                    }
                                )

                                Spacer(Modifier.height(48.dp))

                                // App update ui
                                appUpdateInfo?.let { info ->
                                    RenderAppUpdate(info, updateDownloadState)
                                }

                                // is enable
                                RenderSwitcher(
                                    modifier = Modifier.padding(horizontal = 20.dp),
                                    title = stringResource(R.string.enable),
                                    subtitle = stringResource(R.string.key_binder_sync),
                                    value = isEnabled,
                                    enable = true,
                                    groupDivider = false,
                                    onChange = {
                                        scope.launch {
                                            dataStore.saveValue(GeneralPrefs.DATA_SYNC_ENABLED, it)
                                        }
                                    }
                                )

                                Spacer(Modifier.height(24.dp))
                                RenderGroupDivider()
                                Spacer(Modifier.height(24.dp))

                                RenderGroupTitle(stringResource(R.string.steering_wheel_buttons))

                                // custom long click enable
                                RenderSwitcher(
                                    modifier = Modifier.padding(horizontal = 20.dp),
                                    title = stringResource(R.string.custom_long_press_timing),
                                    subtitle = stringResource(R.string.custom_long_press_timing_desc),
                                    value = enableCustomLongClick,
                                    enable = true,
                                    groupDivider = false,
                                    onChange = { enable ->
                                        scope.launch {
                                            dataStore.saveValue(
                                                GeneralPrefs.CUSTOM_LONG_PRESS_ENABLED,
                                                enable
                                            )

                                            if (!enable) {
                                                dataStore.saveValue(
                                                    GeneralPrefs.ALT_MENU,
                                                    false
                                                )
                                                dataStore.saveValue(
                                                    GeneralPrefs.ALT_MUTE,
                                                    false
                                                )
                                            }
                                        }
                                    }
                                )

                                Spacer(Modifier.height(16.dp))

                                //  custom long click time
                                val sliderTitle = stringResource(R.string.long_press_trigger_timing)
                                Text(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 42.dp),
                                    textAlign = TextAlign.Left,
                                    text = "$sliderTitle: " +
                                            customLongClickTiming.toDecimalSecondString(),
                                    color = AppTheme.colors.contentPrimary
                                )
                                ValueSlider(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 36.dp),
                                    value = customLongClickTiming,
                                    valueRange = 50..1500,
                                    onValueChange = { newValue ->
                                        customLongClickTiming = newValue
                                        scope.launch {
                                            dataStore.saveValue(
                                                GeneralPrefs.CUSTOM_LONG_PRESS_TIME,
                                                newValue
                                            )
                                        }
                                    },
                                    enabled = true,
                                    defaultMark = 1000,
                                    step = 10
                                )

                                Spacer(Modifier.height(12.dp))

                                // custom short click enable
                                RenderSwitcher(
                                    modifier = Modifier.padding(horizontal = 20.dp),
                                    title = stringResource(R.string.custom_short_press_timing),
                                    subtitle = stringResource(R.string.custom_short_press_timing_desc),
                                    value = enabledCustomShortClick,
                                    enable = true,
                                    groupDivider = false,
                                    onChange = {
                                        scope.launch {
                                            dataStore.saveValue(
                                                GeneralPrefs.CUSTOM_SHORT_CLICK_ENABLED,
                                                it
                                            )
                                        }
                                    }
                                )

                                Spacer(Modifier.height(12.dp))

                                // multi-long click enable
                                RenderSwitcher(
                                    modifier = Modifier.padding(horizontal = 20.dp),
                                    title = stringResource(R.string.detect_multi_long),
                                    subtitle = stringResource(R.string.multi_long_trigger),
                                    value = multiLongPressEnabled,
                                    enable = true,
                                    groupDivider = false,
                                    onChange = {
                                        scope.launch {
                                            dataStore.saveValue(
                                                GeneralPrefs.MULTI_LONG_PRESS_ENABLED,
                                                it
                                            )
                                        }
                                    }
                                )

                                Spacer(Modifier.height(16.dp))

                                // lock double click
                                RenderSwitcher(
                                    modifier = Modifier.padding(horizontal = 20.dp),
                                    title = stringResource(R.string.detect_double_tap),
                                    subtitle = stringResource(R.string.double_click_description),
                                    value = lockDoubleClick,
                                    enable = true,
                                    groupDivider = false,
                                    onChange = {
                                        scope.launch {
                                            dataStore.saveValue(
                                                GeneralPrefs.DOUBLE_CLICK_ENABLED,
                                                it
                                            )
                                        }
                                    }
                                )

                                Spacer(Modifier.height(16.dp))

                                AnimatedVisibility(
                                    visible = lockDoubleClick,
                                    enter = expandVertically(
                                        expandFrom = Alignment.Top,
                                        animationSpec = tween(300)
                                    ),
                                    exit = shrinkVertically(
                                        shrinkTowards = Alignment.Top,
                                        animationSpec = tween(300)
                                    ),
                                ) {
                                    Column {
                                        val sliderDCTitle =
                                            stringResource(R.string.double_tap_window_timing)
                                        Text(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(horizontal = 42.dp),
                                            textAlign = TextAlign.Left,
                                            text = "$sliderDCTitle: " +
                                                    doubleClickTime.toDecimalSecondString(),
                                            color = AppTheme.colors.contentPrimary
                                        )
                                        ValueSlider(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(horizontal = 36.dp),
                                            value = doubleClickTime,
                                            valueRange = 50..1500,
                                            onValueChange = { newValue ->
                                                doubleClickTime = newValue
                                                scope.launch {
                                                    dataStore.saveValue(
                                                        GeneralPrefs.DOUBLE_CLICK_TIME,
                                                        newValue
                                                    )
                                                }
                                            },
                                            enabled = true,
                                            defaultMark = 300,
                                            step = 10
                                        )

                                        Spacer(Modifier.height(24.dp))
                                    }
                                }
                                RenderGroupDivider()
                                Spacer(Modifier.height(24.dp))

                                RenderGroupTitle(stringResource(R.string.additional_press_processing))

                                // custom menu
                                RenderSwitcher(
                                    modifier = Modifier.padding(horizontal = 20.dp),
                                    title = stringResource(R.string.alternative_menu_detection),
                                    subtitle = stringResource(R.string.alternative_menu_detection_desc),
                                    value = altMenu,
                                    enable = true,
                                    groupDivider = false,
                                    onChange = { enable ->
                                        scope.launch {
                                            dataStore.saveValue(
                                                GeneralPrefs.ALT_MENU,
                                                enable
                                            )
                                            if (enable) {
                                                dataStore.saveValue(
                                                    GeneralPrefs.CUSTOM_LONG_PRESS_ENABLED,
                                                    true
                                                )
                                            }
                                        }
                                    }
                                )

                                Spacer(Modifier.height(16.dp))

                                // custom mute
                                RenderSwitcher(
                                    modifier = Modifier.padding(horizontal = 20.dp),
                                    title = stringResource(R.string.alternative_mute_detection),
                                    subtitle = stringResource(R.string.alternative_mute_detection_desc),
                                    value = altMute,
                                    enable = true,
                                    groupDivider = false,
                                    onChange = { enable ->
                                        scope.launch {
                                            dataStore.saveValue(
                                                GeneralPrefs.ALT_MUTE,
                                                enable
                                            )
                                            if (enable) {
                                                dataStore.saveValue(
                                                    GeneralPrefs.CUSTOM_LONG_PRESS_ENABLED,
                                                    true
                                                )
                                            }
                                        }
                                    }
                                )

                                Spacer(Modifier.height(16.dp))

                                //  custom long click time
                                val altTimingSlider =
                                    stringResource(R.string.alt_long_press_trigger_timing)
                                Text(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 42.dp),
                                    textAlign = TextAlign.Left,
                                    text = "$altTimingSlider: " +
                                            altLongTime.toDecimalSecondString(),
                                    color = AppTheme.colors.contentPrimary
                                )
                                ValueSlider(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 36.dp),
                                    value = altLongTime,
                                    valueRange = 50..1500,
                                    onValueChange = { newValue ->
                                        altLongTime = newValue
                                        scope.launch {
                                            dataStore.saveValue(
                                                GeneralPrefs.ALT_LONG_TIME,
                                                newValue
                                            )
                                        }
                                    },
                                    enabled = true,
                                    defaultMark = ADDITIONAL_KEYS_MIN_LONG_PRESS_TIME,
                                    step = 10
                                )

                                Spacer(Modifier.height(24.dp))
                                RenderGroupDivider()
                                Spacer(Modifier.height(24.dp))

                                var showBindingDialog by remember { mutableStateOf(false) }
                                if (showBindingDialog) {
                                    KeyBindingDialog(
                                        uiScaleState = uiScale,
                                        systemApps = remember { systemApps },
                                        keyBindStorage = remember { keyBindStorage },
                                        onDismiss = { showBindingDialog = false }
                                    )
                                }

                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(IntrinsicSize.Min)
                                        .padding(horizontal = 42.dp),
                                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                                ) {
                                    BaseButton(
                                        modifier = Modifier.weight(1f),
                                        title = stringResource(R.string.add_keybind_button)
                                    ) {
                                        showBindingDialog = true
                                    }

                                    val showShareButton by remember { derivedStateOf { keyBinds?.isNotEmpty() == true } }
                                    if (showShareButton) {
                                        Box(
                                            modifier = Modifier
                                                .fillMaxHeight()
                                                .width(60.dp)
                                                .clip(RoundedCornerShape(8.dp))
                                                .background(AppTheme.colors.surfaceMenu)
                                                .clickable {
                                                    scope.launch(Dispatchers.IO) {
                                                        val code = keyBindStorage.getCode()
                                                        context.cleanupShareTempFiles()
                                                        context.shareTextAsGibbFile(code, "binds")
                                                    }
                                                },
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Icon(
                                                modifier = Modifier
                                                    .size(24.dp),
                                                imageVector = Icons.Filled.Share,
                                                tint = AppTheme.colors.contentPrimary,
                                                contentDescription = "delete"
                                            )
                                        }
                                    }
                                }

                                // Key binds list
                                keyBinds?.let { binds ->

                                    (binds).forEach { item ->
                                        Column(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(horizontal = 26.dp)
                                        ) {
                                            Spacer(Modifier.height(24.dp))

                                            Row(
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .padding(end = 6.dp),
                                                verticalAlignment = Alignment.CenterVertically
                                            ) {
                                                Spacer(Modifier.width(16.dp))

                                                Column(
                                                    verticalArrangement = Arrangement.Center,
                                                    horizontalAlignment = Alignment.CenterHorizontally
                                                ) {
                                                    item.keyNames.forEach { keyName ->
                                                        Text(
                                                            modifier = Modifier
                                                                .padding(
                                                                    horizontal = 4.dp,
                                                                    vertical = 2.dp
                                                                )
                                                                .clip(RoundedCornerShape(4.dp))
                                                                .background(AppTheme.colors.surfaceMenu)
                                                                .padding(
                                                                    horizontal = 4.dp,
                                                                    vertical = 2.dp
                                                                ),
                                                            text = keyName,
                                                            style = AppTheme.typography.dialogSubtitle,
                                                            overflow = TextOverflow.Ellipsis,
                                                            maxLines = 1,
                                                            color = AppTheme.colors.contentPrimary
                                                        )
                                                        if (keyName != item.keyNames.last()) {
                                                            Text(
                                                                text = "+",
                                                                style = AppTheme.typography.dialogSubtitle,
                                                                maxLines = 1,
                                                                color = AppTheme.colors.contentPrimary
                                                            )
                                                        }
                                                    }

                                                }

                                                Text(
                                                    modifier = Modifier.padding(
                                                        start = 8.dp,
                                                        end = 12.dp
                                                    ),
                                                    text = "${item.type.lowercase()}   =",
                                                    style = AppTheme.typography.dialogSubtitle,
                                                    color = AppTheme.colors.contentPrimary
                                                )

                                                when (item.action) {

                                                    DisplayKeyAction.LAUNCH_APP -> Row(
                                                        modifier = Modifier
                                                            .weight(1f),
                                                        verticalAlignment = Alignment.CenterVertically
                                                    ) {
                                                        item.app?.icon?.let { icon ->
                                                            DrawableImage(
                                                                icon = icon,
                                                                modifier = Modifier
                                                                    .size(32.dp)
                                                                    .clip(RoundedCornerShape(6.dp))
                                                            )
                                                            Spacer(Modifier.width(10.dp))
                                                        }

                                                        Column {
                                                            Text(
                                                                text = item.app?.appName
                                                                    ?: stringResource(R.string.not_found),
                                                                style = AppTheme.typography.dialogListTitle,
                                                                overflow = TextOverflow.Ellipsis,
                                                                maxLines = 1,
                                                                color = if (item.app == null) AppTheme.colors.deleteButton else AppTheme.colors.contentPrimary
                                                            )
                                                            Text(
                                                                text = item.app?.packageName
                                                                    ?: stringResource(R.string.no_such_app),
                                                                style = AppTheme.typography.dialogSubtitle,
                                                                overflow = TextOverflow.Ellipsis,
                                                                maxLines = 1,
                                                                color = AppTheme.colors.contentPrimary.copy(
                                                                    .5f
                                                                )
                                                            )
                                                        }
                                                    }

                                                    DisplayKeyAction.LAUNCH_LINK -> Row(
                                                        modifier = Modifier
                                                            .weight(1f),
                                                        verticalAlignment = Alignment.CenterVertically
                                                    ) {
                                                        item.link?.icon?.let { icon ->
                                                            Box {
                                                                DrawableImage(
                                                                    icon = icon,
                                                                    modifier = Modifier
                                                                        .size(32.dp)
                                                                        .clip(RoundedCornerShape(6.dp))
                                                                )

                                                                Icon(
                                                                    painterResource(R.drawable.ic_link),
                                                                    null,
                                                                    tint = AppTheme.colors.contentPrimary,
                                                                    modifier = Modifier
                                                                        .offset(x = 2.dp, y = 2.dp)
                                                                        .size(16.dp)
                                                                        .clip(CircleShape)
                                                                        .background(AppTheme.colors.contentPrimary)
                                                                        .padding(.5.dp)
                                                                        .clip(CircleShape)
                                                                        .background(AppTheme.colors.contentAccent)
                                                                        .padding(3.dp)
                                                                        .align(Alignment.BottomEnd)
                                                                )
                                                            }
                                                            Spacer(Modifier.width(10.dp))
                                                        }

                                                        Column {
                                                            Text(
                                                                text = item.link?.title
                                                                    ?: stringResource(R.string.not_found),
                                                                style = AppTheme.typography.dialogListTitle,
                                                                overflow = TextOverflow.Ellipsis,
                                                                maxLines = 1,
                                                                color = if (item.link == null) AppTheme.colors.deleteButton else AppTheme.colors.contentPrimary
                                                            )
                                                            Text(
                                                                text = item.link?.subtitle
                                                                    ?: stringResource(R.string.no_such_shortcut),
                                                                style = AppTheme.typography.dialogSubtitle,
                                                                overflow = TextOverflow.Ellipsis,
                                                                maxLines = 1,
                                                                color = AppTheme.colors.contentPrimary.copy(
                                                                    .5f
                                                                )
                                                            )
                                                        }
                                                    }

                                                    DisplayKeyAction.TOGGLE_DM -> Row(
                                                        modifier = Modifier.weight(1f),
                                                        verticalAlignment = Alignment.CenterVertically
                                                    ) {
                                                        val rawText = context.getString(
                                                            R.string.switch_to_mode_and_back,
                                                            item.driveModes
                                                        )

                                                        val driveModesText = item.driveModes ?: ""
                                                        val startIndex =
                                                            rawText.indexOf(driveModesText)
                                                        val endIndex =
                                                            startIndex + driveModesText.length

                                                        val annotatedText = buildAnnotatedString {
                                                            append(rawText)
                                                            addStyle(
                                                                style = SpanStyle(
                                                                    fontWeight = FontWeight.Medium,
                                                                    color = AppTheme.colors.contentLightAccent
                                                                ),
                                                                start = startIndex,
                                                                end = endIndex
                                                            )
                                                        }

                                                        Text(
                                                            text = annotatedText,
                                                            style = AppTheme.typography.cardFormatTitle,
                                                            color = AppTheme.colors.contentPrimary
                                                        )
                                                    }

                                                    DisplayKeyAction.CAROUSEL_DM -> Row(
                                                        modifier = Modifier.weight(1f),
                                                        verticalAlignment = Alignment.CenterVertically
                                                    ) {
                                                        val driveModesText =
                                                            item.driveModes.orEmpty()
                                                        val rawText = context.getString(
                                                            R.string.driving_mode_switching,
                                                            driveModesText
                                                        )

                                                        // Where the injected substring sits inside the full sentence
                                                        val baseStart =
                                                            rawText.indexOf(driveModesText)
                                                        val baseEnd =
                                                            if (baseStart >= 0) baseStart + driveModesText.length else -1

                                                        val tokens: List<String> = driveModesText
                                                            .split(',')
                                                            .map { it.trim() }
                                                            .filter { it.isNotEmpty() }

                                                        val annotated = buildAnnotatedString {
                                                            append(rawText)

                                                            if (baseStart >= 0) {
                                                                // Sequentially find each token within the injected span
                                                                var searchFrom = baseStart
                                                                for (t in tokens) {
                                                                    val idx = rawText.indexOf(
                                                                        t,
                                                                        startIndex = searchFrom
                                                                    )
                                                                    // ensure the match stays within the injected substring bounds
                                                                    if (idx >= baseStart && idx + t.length <= baseEnd) {
                                                                        addStyle(
                                                                            style = SpanStyle(
                                                                                fontWeight = FontWeight.Medium,
                                                                                color = AppTheme.colors.contentLightAccent
                                                                            ),
                                                                            start = idx,
                                                                            end = idx + t.length
                                                                        )
                                                                        searchFrom = idx + t.length
                                                                    }
                                                                }
                                                            }
                                                        }

                                                        Text(
                                                            text = annotated,
                                                            style = AppTheme.typography.cardFormatTitle,
                                                            color = AppTheme.colors.contentPrimary
                                                        )
                                                    }

                                                    DisplayKeyAction.PHONE_CALL -> Row(
                                                        modifier = Modifier.weight(1f),
                                                        verticalAlignment = Alignment.CenterVertically
                                                    ) {
                                                        val annotatedText = buildAnnotatedString {
                                                            append(stringResource(R.string.call))
                                                            append(" ")
                                                            withStyle(
                                                                SpanStyle(
                                                                    fontWeight = FontWeight.Medium,
                                                                    color = AppTheme.colors.contentLightAccent
                                                                )
                                                            ) { append(item.phone) }
                                                        }
                                                        Text(
                                                            text = annotatedText,
                                                            style = AppTheme.typography.cardFormatTitle,
                                                            color = AppTheme.colors.contentPrimary
                                                        )
                                                    }

                                                    DisplayKeyAction.CAMERAS_360 -> Row(
                                                        modifier = Modifier.weight(1f),
                                                        verticalAlignment = Alignment.CenterVertically
                                                    ) {
                                                        Text(
                                                            text = stringResource(R.string.circle_cameras_desc),
                                                            style = AppTheme.typography.cardFormatTitle,
                                                            color = AppTheme.colors.contentPrimary
                                                        )
                                                    }

                                                    DisplayKeyAction.CAROUSEL_LAMP -> Row(
                                                        modifier = Modifier.weight(1f),
                                                        verticalAlignment = Alignment.CenterVertically
                                                    ) {
                                                        val lampModesText =
                                                            item.lampModes.orEmpty()
                                                        val rawText = context.getString(
                                                            R.string.headlight_mode_switching,
                                                            lampModesText
                                                        )

                                                        // Where the injected substring sits inside the full sentence
                                                        val baseStart =
                                                            rawText.indexOf(lampModesText)
                                                        val baseEnd =
                                                            if (baseStart >= 0) baseStart + lampModesText.length else -1

                                                        val tokens: List<String> = lampModesText
                                                            .split(',')
                                                            .map { it.trim() }
                                                            .filter { it.isNotEmpty() }

                                                        val annotated = buildAnnotatedString {
                                                            append(rawText)

                                                            if (baseStart >= 0) {
                                                                // Sequentially find each token within the injected span
                                                                var searchFrom = baseStart
                                                                for (t in tokens) {
                                                                    val idx = rawText.indexOf(
                                                                        t,
                                                                        startIndex = searchFrom
                                                                    )
                                                                    // ensure the match stays within the injected substring bounds
                                                                    if (idx >= baseStart && idx + t.length <= baseEnd) {
                                                                        addStyle(
                                                                            style = SpanStyle(
                                                                                fontWeight = FontWeight.Medium,
                                                                                color = AppTheme.colors.contentLightAccent
                                                                            ),
                                                                            start = idx,
                                                                            end = idx + t.length
                                                                        )
                                                                        searchFrom = idx + t.length
                                                                    }
                                                                }
                                                            }
                                                        }

                                                        Text(
                                                            text = annotated,
                                                            style = AppTheme.typography.cardFormatTitle,
                                                            color = AppTheme.colors.contentPrimary
                                                        )
                                                    }

                                                    DisplayKeyAction.APP_LAUNCHER -> Row(
                                                        modifier = Modifier.weight(1f),
                                                        verticalAlignment = Alignment.CenterVertically
                                                    ) {
                                                        DrawableImage(
                                                            icon = R.mipmap.ic_app_launcher,
                                                            modifier = Modifier
                                                                .size(32.dp)
                                                                .clip(RoundedCornerShape(6.dp))
                                                        )
                                                        Spacer(Modifier.width(10.dp))
                                                        Text(
                                                            text = stringResource(R.string.launcher_name),
                                                            style = AppTheme.typography.cardFormatTitle,
                                                            color = AppTheme.colors.contentPrimary
                                                        )
                                                    }

                                                    DisplayKeyAction.TASK_MANAGER -> Row(
                                                        modifier = Modifier.weight(1f),
                                                        verticalAlignment = Alignment.CenterVertically
                                                    ) {
                                                        Text(
                                                            text = stringResource(R.string.recents),
                                                            style = AppTheme.typography.cardFormatTitle,
                                                            color = AppTheme.colors.contentPrimary
                                                        )
                                                    }

                                                    DisplayKeyAction.ANDROID_BACK -> Row(
                                                        modifier = Modifier.weight(1f),
                                                        verticalAlignment = Alignment.CenterVertically
                                                    ) {
                                                        Text(
                                                            text = stringResource(R.string.back),
                                                            style = AppTheme.typography.cardFormatTitle,
                                                            color = AppTheme.colors.contentPrimary
                                                        )
                                                    }

                                                    DisplayKeyAction.ANDROID_HOME -> Row(
                                                        modifier = Modifier.weight(1f),
                                                        verticalAlignment = Alignment.CenterVertically
                                                    ) {
                                                        Text(
                                                            text = stringResource(R.string.home),
                                                            style = AppTheme.typography.cardFormatTitle,
                                                            color = AppTheme.colors.contentPrimary
                                                        )
                                                    }

                                                    DisplayKeyAction.NAVIGATE_TO_PAST_APP -> Row(
                                                        modifier = Modifier.weight(1f),
                                                        verticalAlignment = Alignment.CenterVertically
                                                    ) {
                                                        Text(
                                                            text = stringResource(R.string.return_to_previous_app),
                                                            style = AppTheme.typography.cardFormatTitle,
                                                            color = AppTheme.colors.contentPrimary
                                                        )
                                                    }
                                                }

                                                Spacer(Modifier.width(10.dp))

                                                IconButton(
                                                    modifier = Modifier
                                                        .size(36.dp)
                                                        .padding(start = 2.dp),
                                                    onClick = { onDeleteDialog(item.bindName) }
                                                ) {
                                                    Icon(
                                                        modifier = Modifier.size(22.dp),
                                                        imageVector = Icons.Filled.Delete,
                                                        tint = AppTheme.colors.deleteButton,
                                                        contentDescription = "delete"
                                                    )
                                                }

                                                Spacer(Modifier.width(12.dp))
                                            }

                                        }
                                    }
                                }

                                Spacer(Modifier.height(24.dp))
                                RenderGroupDivider()
                                Spacer(Modifier.height(24.dp))

                                RenderGroupTitle(stringResource(R.string.playback_control))

                                // media control
                                RenderSwitcher(
                                    modifier = Modifier.padding(horizontal = 20.dp),
                                    title = stringResource(R.string.audio_control),
                                    subtitle = stringResource(R.string.media_control_desc),
                                    value = mediaControlEnabled,
                                    enable = true,
                                    groupDivider = false,
                                    onChange = {
                                        if (!isNotificationServiceEnabled) {
                                            context.requestNotificationServicePermission()
                                            return@RenderSwitcher
                                        }

                                        scope.launch {
                                            dataStore.saveValue(
                                                GeneralPrefs.MEDIA_CONTROL_ENABLED,
                                                it
                                            )
                                            dataStore.saveValue(
                                                GeneralPrefs.HAND_MEDIA_CONTROL_ENABLED,
                                                it
                                            )
                                        }
                                    }
                                )

                                Spacer(Modifier.height(12.dp))

                                var showMediaAppsDialog by remember { mutableStateOf(false) }
                                if (showMediaAppsDialog) {
                                    RenderMediaAppsPickerDialog(
                                        uiScaleState = uiScale,
                                        systemApps = remember { systemApps },
                                        dataStore = remember { dataStore },
                                        onDismiss = { showMediaAppsDialog = false }
                                    )
                                }

                                RenderListButton(
                                    modifier = Modifier.padding(horizontal = 20.dp),
                                    title = stringResource(R.string.media_apps),
                                    subtitle = stringResource(R.string.media_apps_desc),
                                    enable = mediaControlEnabled
                                ) {
                                    showMediaAppsDialog = true
                                }

                                Spacer(Modifier.height(12.dp))

                                RenderSwitcher(
                                    modifier = Modifier.padding(horizontal = 20.dp),
                                    title = stringResource(R.string.radio_bt_control_title),
                                    subtitle = stringResource(R.string.radio_bt_control_subtitle),
                                    value = radioBtControl,
                                    enable = mediaControlEnabled,
                                    groupDivider = false,
                                    onChange = {
                                        scope.launch {
                                            dataStore.saveValue(
                                                GeneralPrefs.RADIO_BT_CONTROL,
                                                it
                                            )
                                            if (it) {
                                                dataStore.saveValue(
                                                    GeneralPrefs.SOURCE_MANAGEMENT,
                                                    false
                                                )
                                            }
                                        }
                                    }
                                )

                                Spacer(Modifier.height(12.dp))

                                RenderSwitcher(
                                    modifier = Modifier.padding(horizontal = 20.dp),
                                    title = stringResource(R.string.audio_source_control),
                                    subtitle = stringResource(R.string.audio_source_control_desc),
                                    value = sourceManagement,
                                    enable = mediaControlEnabled && !radioBtControl,
                                    groupDivider = false,
                                    onChange = {
                                        scope.launch {
                                            dataStore.saveValue(GeneralPrefs.SOURCE_MANAGEMENT, it)
                                        }
                                    }
                                )

                                Spacer(Modifier.height(12.dp))

                                // disable when AC
                                RenderSwitcher(
                                    modifier = Modifier.padding(horizontal = 20.dp),
                                    title = stringResource(R.string.disable_on_climate),
                                    subtitle = stringResource(R.string.disable_on_climate_desc),
                                    value = disableOnClimate,
                                    enable = mediaControlEnabled,
                                    groupDivider = false,
                                    onChange = {
                                        scope.launch {
                                            dataStore.saveValue(GeneralPrefs.DISABLE_ON_CLIMATE, it)
                                        }
                                    }
                                )

                                Spacer(Modifier.height(12.dp))

                                // metadata translator
                                RenderSwitcher(
                                    modifier = Modifier.padding(horizontal = 20.dp),
                                    title = stringResource(R.string.send_media_session_data),
                                    subtitle = stringResource(R.string.send_media_session_data_desc),
                                    value = mediaDataTranslator,
                                    enable = true,
                                    groupDivider = false,
                                    onChange = {
                                        if (!isNotificationServiceEnabled) {
                                            context.requestNotificationServicePermission()
                                            return@RenderSwitcher
                                        }

                                        scope.launch {
                                            dataStore.saveValue(
                                                GeneralPrefs.MEDIA_DATA_TRANSLATOR,
                                                it
                                            )
                                        }
                                    }
                                )

                                Spacer(Modifier.height(16.dp))
                                RenderGroupDivider()
                                Spacer(Modifier.height(24.dp))

                                RenderGroupTitle(stringResource(R.string.driving_mode))

                                var restoreDMWarningDialog by remember { mutableStateOf(false) }
                                if (restoreDMWarningDialog) {
                                    ConfirmDialog(
                                        title = stringResource(R.string.attention),
                                        message = stringResource(R.string.driving_mode_restore_warning),
                                        uiScale = uiScale,
                                        negativeAction = false,
                                        okButtonTitle = stringResource(R.string.enable),
                                        onCancel = { restoreDMWarningDialog = false },
                                        onDismiss = { restoreDMWarningDialog = false },
                                        onClick = {
                                            scope.launch {
                                                dataStore.saveValue(
                                                    GeneralPrefs.REMEMBER_DRIVE_MODE,
                                                    true
                                                )
                                            }
                                            restoreDMWarningDialog = false
                                        }
                                    )
                                }
                                RenderSwitcher(
                                    modifier = Modifier.padding(horizontal = 20.dp),
                                    title = stringResource(R.string.remember_driving_mode),
                                    subtitle = stringResource(R.string.remember_driving_mode_desc),
                                    value = rememberDriveMode,
                                    groupDivider = false,
                                    onChange = { newValue ->
                                        if (newValue && (isDebugMInstalled || isMConfigMInstalled)) {
                                            restoreDMWarningDialog = true
                                        } else {
                                            scope.launch {
                                                dataStore.saveValue(
                                                    GeneralPrefs.REMEMBER_DRIVE_MODE,
                                                    newValue
                                                )
                                            }
                                        }
                                    }
                                )

                                var targetRecoveryDriveModeDialog by remember { mutableStateOf(false) }
                                if (targetRecoveryDriveModeDialog) {
                                    TargetRestoreDMDialog(
                                        uiScaleState = uiScale,
                                        driveMode = targetRecoveryDriveMode,
                                        onTargetDriveModeChanged = { targetMode ->
                                            scope.launch {
                                                dataStore.saveValue(
                                                    GeneralPrefs.TARGET_RECOVERY_DRIVE_MODE,
                                                    targetMode
                                                )
                                            }
                                        },
                                        onDismiss = { targetRecoveryDriveModeDialog = false }
                                    )
                                }
                                AnimatedVisibility(
                                    visible = rememberDriveMode,
                                    enter = expandVertically(
                                        expandFrom = Alignment.Top,
                                        animationSpec = tween(300)
                                    ),
                                    exit = shrinkVertically(
                                        shrinkTowards = Alignment.Top,
                                        animationSpec = tween(300)
                                    ),
                                ) {
                                    Column {
                                        Spacer(Modifier.height(12.dp))

                                        var isActive by remember { mutableStateOf(false) }
                                        val targetRecoveryDM = targetRecoveryDriveMode
                                            .getDisplayDriveModeName()
                                            .let { name ->
                                                if (name == "Unknown") {
                                                    isActive = false
                                                    stringResource(R.string.last)
                                                } else {
                                                    isActive = true
                                                    name
                                                }
                                            }
                                        RenderListButton(
                                            modifier = Modifier.padding(horizontal = 20.dp),
                                            title = stringResource(R.string.restore_mode),
                                            subtitle = stringResource(R.string.restore_mode_desc),
                                            content = {
                                                Text(
                                                    text = targetRecoveryDM,
                                                    modifier = Modifier
                                                        .clip(CircleShape)
                                                        .background(
                                                            if (isActive) {
                                                                AppTheme.colors.contentAccent
                                                            } else {
                                                                AppTheme.colors.surfaceMenu
                                                            }
                                                        )
                                                        .padding(
                                                            horizontal = 12.dp,
                                                            vertical = 8.dp
                                                        ),
                                                    color = AppTheme.colors.contentPrimary,
                                                    style = AppTheme.typography.liteBadge
                                                )
                                            }
                                        ) { targetRecoveryDriveModeDialog = true }
                                    }
                                }

                                Spacer(Modifier.height(12.dp))

                                var showNotifPicker by remember { mutableStateOf(false) }
                                if (showNotifPicker) {
                                    NotifPickerDialog(
                                        uiScaleState = uiScale,
                                        dataStore = remember { dataStore },
                                        playTest = viewModel::playNotifTest,
                                        onDismiss = { showNotifPicker = false }
                                    )
                                }
                                RenderListButton(
                                    modifier = Modifier.padding(horizontal = 20.dp),
                                    title = stringResource(R.string.driving_mode_change_sound),
                                    subtitle = stringResource(R.string.driving_mode_change_sound_desc)
                                ) {
                                    showNotifPicker = true
                                }

                                Spacer(Modifier.height(12.dp))

                                RenderSwitcher(
                                    modifier = Modifier.padding(horizontal = 20.dp),
                                    title = stringResource(R.string.display_driving_mode),
                                    subtitle = stringResource(R.string.display_driving_mode_desc),
                                    value = driveModeOverlay,
                                    groupDivider = false,
                                    onChange = {
                                        if (context.requireDisplayOverlay()) {
                                            scope.launch {
                                                dataStore.saveValue(
                                                    GeneralPrefs.DRIVE_MODE_OVERLAY,
                                                    it
                                                )
                                            }
                                        }
                                    }
                                )

                                AnimatedVisibility(
                                    visible = driveModeOverlay,
                                    enter = expandVertically(
                                        expandFrom = Alignment.Top,
                                        animationSpec = tween(300)
                                    ),
                                    exit = shrinkVertically(
                                        shrinkTowards = Alignment.Top,
                                        animationSpec = tween(300)
                                    ),
                                ) {
                                    Column {
                                        Spacer(Modifier.height(16.dp))

                                        //  drive mode overlay size slider
                                        val overlaySizeTitle = stringResource(R.string.overlay_size)
                                        Text(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(horizontal = 42.dp),
                                            textAlign = TextAlign.Left,
                                            text = "$overlaySizeTitle: " + (driveModeOverlayScale + uiScale).asOneDecimalX(),
                                            color = AppTheme.colors.contentPrimary
                                        )
                                        ValueSlider(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(horizontal = 36.dp),
                                            value = driveModeOverlayScale,
                                            valueRange = -1.2f..1.7f,
                                            onValueChange = { newValue ->
                                                driveModeOverlayScale = newValue
                                                scope.launch {
                                                    dataStore.saveValue(
                                                        GeneralPrefs.DM_OVERLAY_SCALE,
                                                        newValue
                                                    )
                                                }
                                            },
                                            enabled = true,
                                            defaultMark = 0f,
                                            step = 0.1f
                                        )

                                        Spacer(Modifier.height(16.dp))

                                        //  drive mode overlay offset slider
                                        val overlayOffsetTitle =
                                            stringResource(R.string.vertical_offset)
                                        val minOverlayOffset = DRIVE_MODE_DEFAULT_OVERLAY_OFFSET
                                        val maxOverlayOffset =
                                            90f - DRIVE_MODE_DEFAULT_OVERLAY_OFFSET
                                        Text(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(horizontal = 42.dp),
                                            textAlign = TextAlign.Left,
                                            text = "$overlayOffsetTitle: " +
                                                    driveModeOverlayOffset.toOverlayPercentString(
                                                        min = -minOverlayOffset,
                                                        mid = 0f,
                                                        max = maxOverlayOffset,
                                                        minPercent = 0f,
                                                        midPercent = minOverlayOffset,
                                                        maxPercent = 100f
                                                    ),
                                            color = AppTheme.colors.contentPrimary
                                        )
                                        ValueSlider(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(horizontal = 36.dp),
                                            value = driveModeOverlayOffset,
                                            valueRange = -minOverlayOffset..maxOverlayOffset,
                                            onValueChange = { newValue ->
                                                driveModeOverlayOffset = newValue
                                                scope.launch {
                                                    dataStore.saveValue(
                                                        GeneralPrefs.DM_OVERLAY_OFFSET,
                                                        newValue
                                                    )
                                                }
                                            },
                                            enabled = true,
                                            defaultMark = 0f,
                                            step = 1f
                                        )
                                    }
                                }

                                Spacer(Modifier.height(16.dp))
                                RenderGroupDivider()
                                Spacer(Modifier.height(24.dp))

                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(IntrinsicSize.Min)
                                        .padding(horizontal = 42.dp)
                                        .clip(RoundedCornerShape(8.dp))
                                        .background(AppTheme.colors.addSplitTop)
                                ) {
                                    Text(
                                        modifier = Modifier
                                            .clickable {
                                                showConfigurator = true to false
                                            }
                                            .weight(1f)
                                            .padding(
                                                horizontal = 4.dp,
                                                vertical = 14.dp
                                            ),
                                        text = stringResource(R.string.configurator),
                                        color = AppTheme.colors.contentPrimary,
                                        style = AppTheme.typography.buttonTitle,
                                        textAlign = TextAlign.Center
                                    )

                                    Spacer(
                                        Modifier
                                            .fillMaxHeight()
                                            .width(1.dp)
                                            .padding(vertical = 10.dp)
                                            .background(AppTheme.colors.contentPrimary.copy(.2f))
                                    )

                                    Box(
                                        modifier = Modifier
                                            .clickable {
                                                showConfigurator = true to true
                                            }
                                            .padding(horizontal = 32.dp)
                                            .fillMaxHeight(),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Icon(
                                            imageVector = Icons.Filled.Favorite,
                                            tint = AppTheme.colors.contentPrimary,
                                            contentDescription = stringResource(R.string.back)
                                        )
                                    }
                                }

                                Spacer(Modifier.height(28.dp))

                                BaseButton(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .align(Alignment.Start)
                                        .padding(horizontal = 42.dp),
                                    title = stringResource(R.string.system_parameters),
                                    backgroundColor = AppTheme.colors.addSplitBottom
                                ) {
                                    configuratorPresets = !configuratorPresets
                                }

                                Spacer(Modifier.height(24.dp))
                                RenderGroupDivider()
                                Spacer(Modifier.height(24.dp))

                                /* BaseButton(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .align(Alignment.Start)
                                        .padding(horizontal = 42.dp),
                                    title = stringResource(R.string.launcher_title),
                                    backgroundColor = AppTheme.colors.launcherAccent
                                ) {
                                    if (!context.requireDisplayOverlay()) {
                                        return@BaseButton
                                    }
                                    startOverlay<LauncherOverlayService>(this@MainActivity)
                                }

                                Spacer(Modifier.height(24.dp))
                                RenderGroupDivider()
                                Spacer(Modifier.height(24.dp)) */

                                var showDoc by remember { mutableStateOf(false) }
                                BaseButton(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .align(Alignment.Start)
                                        .padding(horizontal = 42.dp),
                                    title = stringResource(
                                        if (showDoc) {
                                            R.string.hide_documentation
                                        } else R.string.show_documentation
                                    ),
                                    backgroundColor = AppTheme.colors.surfaceMenu
                                ) {
                                    showDoc = !showDoc
                                    // enableWidgetUnlockCounter++
                                }
                                Spacer(Modifier.height(24.dp))

                                RenderGroupDivider()

                                AnimatedVisibility(
                                    visible = showDoc,
                                    enter = expandVertically(
                                        expandFrom = Alignment.Top,
                                        animationSpec = tween(300)
                                    ),
                                    exit = shrinkVertically(
                                        shrinkTowards = Alignment.Top,
                                        animationSpec = tween(300)
                                    ),
                                ) {

                                    Column(Modifier.fillMaxWidth()) {

                                        Spacer(Modifier.height(24.dp))

                                        SelectionContainer {
                                            Text(
                                                text = stringResource(R.string.api_text_launcher)
                                                    .spannedFromHtml()
                                                    .toAnnotatedString(),
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .padding(horizontal = 42.dp),
                                                color = AppTheme.colors.contentPrimary.copy(.8f),
                                                style = AppTheme.typography.dialogSubtitle
                                            )
                                        }

                                        Spacer(Modifier.height(24.dp))
                                        Spacer(
                                            Modifier
                                                .fillMaxWidth()
                                                .height(1.dp)
                                                .background(AppTheme.colors.contentPrimary.copy(.1f))
                                        )
                                        Spacer(Modifier.height(24.dp))

                                        SelectionContainer {
                                            Text(
                                                text = stringResource(R.string.api_text)
                                                    .spannedFromHtml()
                                                    .toAnnotatedString(),
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .padding(horizontal = 42.dp),
                                                color = AppTheme.colors.contentPrimary.copy(.8f),
                                                style = AppTheme.typography.dialogSubtitle
                                            )
                                        }

                                        Spacer(Modifier.height(24.dp))
                                        Spacer(
                                            Modifier
                                                .fillMaxWidth()
                                                .height(1.dp)
                                                .background(AppTheme.colors.contentPrimary.copy(.1f))
                                        )
                                        Spacer(Modifier.height(24.dp))

                                        SelectionContainer {
                                            Text(
                                                text = stringResource(R.string.api_text2)
                                                    .spannedFromHtml()
                                                    .toAnnotatedString(),
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .padding(horizontal = 42.dp),
                                                color = AppTheme.colors.contentPrimary.copy(.8f),
                                                style = AppTheme.typography.dialogSubtitle
                                            )
                                        }

                                        Spacer(Modifier.height(24.dp))
                                        Spacer(
                                            Modifier
                                                .fillMaxWidth()
                                                .height(1.dp)
                                                .background(AppTheme.colors.contentPrimary.copy(.1f))
                                        )
                                        Spacer(Modifier.height(24.dp))

                                        SelectionContainer {
                                            Text(
                                                text = stringResource(R.string.api_text3)
                                                    .spannedFromHtml()
                                                    .toAnnotatedString(),
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .padding(horizontal = 42.dp),
                                                color = AppTheme.colors.contentPrimary.copy(.8f),
                                                style = AppTheme.typography.dialogSubtitle
                                            )
                                        }

                                        Spacer(Modifier.height(24.dp))
                                        Spacer(
                                            Modifier
                                                .fillMaxWidth()
                                                .height(1.dp)
                                                .background(AppTheme.colors.contentPrimary.copy(.1f))
                                        )
                                        Spacer(Modifier.height(24.dp))

                                        SelectionContainer {
                                            Text(
                                                text = stringResource(R.string.api_text4)
                                                    .spannedFromHtml()
                                                    .toAnnotatedString(),
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .padding(horizontal = 42.dp),
                                                color = AppTheme.colors.contentPrimary.copy(.8f),
                                                style = AppTheme.typography.dialogSubtitle
                                            )
                                        }

                                        Spacer(Modifier.height(24.dp))
                                        Spacer(
                                            Modifier
                                                .fillMaxWidth()
                                                .height(1.dp)
                                                .background(AppTheme.colors.contentPrimary.copy(.1f))
                                        )
                                        Spacer(Modifier.height(24.dp))

                                        SelectionContainer {
                                            Text(
                                                text = stringResource(R.string.api_text5)
                                                    .spannedFromHtml()
                                                    .toAnnotatedString(),
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .padding(horizontal = 42.dp),
                                                color = AppTheme.colors.contentPrimary.copy(.8f),
                                                style = AppTheme.typography.dialogSubtitle
                                            )
                                        }

                                        Spacer(Modifier.height(24.dp))
                                        Spacer(
                                            Modifier
                                                .fillMaxWidth()
                                                .height(1.dp)
                                                .background(AppTheme.colors.contentPrimary.copy(.1f))
                                        )
                                        Spacer(Modifier.height(24.dp))

                                        SelectionContainer {
                                            Text(
                                                text = stringResource(R.string.api_text6)
                                                    .spannedFromHtml()
                                                    .toAnnotatedString(),
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .padding(horizontal = 42.dp),
                                                color = AppTheme.colors.contentPrimary.copy(.8f),
                                                style = AppTheme.typography.dialogSubtitle
                                            )
                                        }

                                        Spacer(Modifier.height(24.dp))
                                        Spacer(
                                            Modifier
                                                .fillMaxWidth()
                                                .height(1.dp)
                                                .background(AppTheme.colors.contentPrimary.copy(.1f))
                                        )
                                        Spacer(Modifier.height(24.dp))

                                        SelectionContainer {
                                            Text(
                                                text = stringResource(R.string.api_text7)
                                                    .spannedFromHtml()
                                                    .toAnnotatedString(),
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .padding(horizontal = 42.dp),
                                                color = AppTheme.colors.contentPrimary.copy(.8f),
                                                style = AppTheme.typography.dialogSubtitle
                                            )
                                        }

                                        Spacer(Modifier.height(24.dp))
                                        Spacer(
                                            Modifier
                                                .fillMaxWidth()
                                                .height(1.dp)
                                                .background(AppTheme.colors.contentPrimary.copy(.1f))
                                        )
                                        Spacer(Modifier.height(24.dp))

                                        SelectionContainer {
                                            Text(
                                                text = stringResource(R.string.api_text8)
                                                    .spannedFromHtml()
                                                    .toAnnotatedString(),
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .padding(horizontal = 42.dp),
                                                color = AppTheme.colors.contentPrimary.copy(.8f),
                                                style = AppTheme.typography.dialogSubtitle
                                            )
                                        }

                                        Spacer(Modifier.height(24.dp))
                                        Spacer(
                                            Modifier
                                                .fillMaxWidth()
                                                .height(1.dp)
                                                .background(AppTheme.colors.contentPrimary.copy(.1f))
                                        )
                                        Spacer(Modifier.height(24.dp))

                                        SelectionContainer {
                                            Text(
                                                text = stringResource(R.string.api_text9)
                                                    .spannedFromHtml()
                                                    .toAnnotatedString(),
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .padding(horizontal = 42.dp),
                                                color = AppTheme.colors.contentPrimary.copy(.8f),
                                                style = AppTheme.typography.dialogSubtitle
                                            )
                                        }

                                        Spacer(Modifier.height(24.dp))
                                        Spacer(
                                            Modifier
                                                .fillMaxWidth()
                                                .height(1.dp)
                                                .background(AppTheme.colors.contentPrimary.copy(.1f))
                                        )
                                        Spacer(Modifier.height(24.dp))

                                        SelectionContainer {
                                            Text(
                                                text = stringResource(R.string.api_text10)
                                                    .spannedFromHtml()
                                                    .toAnnotatedString(),
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .padding(horizontal = 42.dp),
                                                color = AppTheme.colors.contentPrimary.copy(.8f),
                                                style = AppTheme.typography.dialogSubtitle
                                            )
                                        }

                                        Spacer(Modifier.height(24.dp))
                                        RenderGroupDivider()
                                    }
                                }

                                Spacer(Modifier.height(24.dp))

                                RenderGroupTitle("ADB")

                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(
                                            start = 42.dp,
                                            end = 42.dp,
                                            top = 14.dp,
                                            bottom = 14.dp
                                        ),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    StatusLamp(state = adbConnectionState)

                                    Spacer(Modifier.width(20.dp))

                                    Column(
                                        modifier = Modifier.weight(1f),
                                        verticalArrangement = Arrangement.spacedBy(4.dp)
                                    ) {
                                        Text(
                                            modifier = Modifier,
                                            text = when (adbConnectionState) {
                                                DisplayAdbState.Connected -> stringResource(R.string.connected)

                                                DisplayAdbState.Connecting -> stringResource(R.string.connecting)

                                                DisplayAdbState.Disconnected -> stringResource(R.string.disconnected)

                                                is DisplayAdbState.Error -> stringResource(R.string.error)
                                            },
                                            style = AppTheme.typography.statusTitle,
                                            color = AppTheme.colors.contentPrimary
                                        )

                                        val conState = adbConnectionState
                                        if (conState is DisplayAdbState.Error) {
                                            Text(
                                                text = conState.message,
                                                style = AppTheme.typography.dialogSubtitle,
                                                color = AppTheme.colors.contentPrimary,
                                                overflow = TextOverflow.Ellipsis,
                                                maxLines = 2
                                            )
                                        }
                                    }
                                }
                                Spacer(Modifier.height(12.dp))

                                var inputPortDialog by remember { mutableStateOf(false) }
                                if (inputPortDialog) {
                                    InputPortDialog(
                                        title = when (adbHelperPort) {
                                            7777, 5555 -> ""
                                            else -> adbHelperPort.toString()
                                        },
                                        uiScaleState = uiScale,
                                        onFinishInput = { newPort ->
                                            scope.launch(Dispatchers.IO) {
                                                dataStore.saveValue(
                                                    GeneralPrefs.ADB_HELPER_PORT,
                                                    newPort
                                                )
                                                dataStore.saveValue(
                                                    GeneralPrefs.ENABLE_ADB_HELPER,
                                                    true
                                                )
                                            }
                                            inputPortDialog = false
                                        },
                                        onDismiss = {
                                            inputPortDialog = false
                                        }
                                    )
                                }

                                val offText = stringResource(R.string.off)
                                val list = remember(adbHelperPort) {
                                    listOf(
                                        HugeTogglerItem(text = "Atlas", subtitle = "5555"),
                                        HugeTogglerItem(text = "Preface", subtitle = "7777"),
                                        HugeTogglerItem(
                                            text = "Custom",
                                            subtitle = when {
                                                adbHelperPort != 7777 &&
                                                        adbHelperPort != 5555 &&
                                                        adbHelperPort != -1 -> adbHelperPort.toString()

                                                else -> null
                                            }
                                        ),
                                        HugeTogglerItem(text = offText),
                                    )
                                }
                                Box(
                                    Modifier
                                        .padding(horizontal = 42.dp)
                                        .clip(RoundedCornerShape(10.dp))
                                        .background(AppTheme.colors.surfaceMenu)
                                        .padding(2.dp)
                                ) {
                                    if (adbHelperPort != -1) {
                                        HugeSegmentToggler(
                                            modifier = Modifier
                                                .fillMaxWidth(),
                                            selectedIndex = when {
                                                adbHelperPort == 5555 && enableAdbHelper -> 0
                                                adbHelperPort == 7777 && enableAdbHelper -> 1
                                                adbHelperPort > 0 && enableAdbHelper -> 2
                                                !enableAdbHelper -> 3
                                                else -> 0
                                            },
                                            fontSize = 14,
                                            activeBackground = AppTheme.colors.contentAccent,
                                            itemContentColor = AppTheme.colors.contentPrimary,
                                            items = list,
                                            onReSelect = {
                                                if (it == 2) inputPortDialog = true
                                            }
                                        ) {
                                            when (it) {
                                                0 -> scope.launch(Dispatchers.IO) {
                                                    dataStore.saveValue(
                                                        GeneralPrefs.ADB_HELPER_PORT,
                                                        5555
                                                    )
                                                    dataStore.saveValue(
                                                        GeneralPrefs.ENABLE_ADB_HELPER,
                                                        true
                                                    )
                                                }

                                                1 -> scope.launch(Dispatchers.IO) {
                                                    dataStore.saveValue(
                                                        GeneralPrefs.ADB_HELPER_PORT,
                                                        7777
                                                    )
                                                    dataStore.saveValue(
                                                        GeneralPrefs.ENABLE_ADB_HELPER,
                                                        true
                                                    )
                                                }

                                                2 -> inputPortDialog = true

                                                3 -> scope.launch(Dispatchers.IO) {
                                                    dataStore.saveValue(
                                                        GeneralPrefs.ENABLE_ADB_HELPER,
                                                        false
                                                    )
                                                }
                                            }
                                        }
                                    }
                                }

                                Spacer(Modifier.height(14.dp))

                                Text(
                                    text = stringResource(R.string.launcher_extra_features_desc),
                                    modifier = Modifier.padding(horizontal = 42.dp),
                                    color = AppTheme.colors.contentPrimary.copy(.4f),
                                    style = AppTheme.typography.dialogSubtitle
                                )

                                Spacer(Modifier.height(24.dp))
                                RenderGroupDivider()
                                Spacer(Modifier.height(24.dp))

                                RenderGroupTitle(stringResource(R.string.general))

                                var uiScaleDialog by rememberSaveable { mutableStateOf(false) }
                                if (uiScaleDialog) {
                                    UiScaleDialog(
                                        uiScaleState = uiScale,
                                        onChangeUiScale = { newValue ->
                                            uiScale = newValue
                                            scope.launch {
                                                dataStore.saveValue(
                                                    GeneralPrefs.APP_UI_SCALE,
                                                    newValue
                                                )
                                            }
                                        },
                                        onDismiss = { uiScaleDialog = false }
                                    )
                                }

                                RenderListButton(
                                    modifier = Modifier.padding(horizontal = 20.dp),
                                    title = stringResource(R.string.interface_scale),
                                    subtitle = "${uiScale.roundScale()}x"
                                ) {
                                    uiScaleDialog = true
                                }

                                Spacer(Modifier.height(12.dp))

                                // is md target broadcast
                                RenderSwitcher(
                                    modifier = Modifier.padding(horizontal = 20.dp),
                                    title = stringResource(R.string.broadcast_intents),
                                    subtitle = stringResource(R.string.broadcast_intents_desc),
                                    value = fullBroadcast,
                                    enable = true,
                                    groupDivider = false,
                                    onChange = {
                                        scope.launch {
                                            dataStore.saveValue(GeneralPrefs.FULL_BROADCAST, it)
                                        }
                                    }
                                )

                                Spacer(Modifier.height(12.dp))

                                // track keycode event
                                RenderSwitcher(
                                    modifier = Modifier.padding(horizontal = 20.dp),
                                    title = stringResource(R.string.track_keycode_event),
                                    subtitle = stringResource(R.string.low_level_key_events),
                                    value = trackKeyEvents,
                                    enable = true,
                                    groupDivider = false,
                                    onChange = {
                                        scope.launch {
                                            dataStore.saveValue(GeneralPrefs.TRACK_KEY_EVENTS, it)
                                        }
                                    }
                                )

                                AnimatedVisibility(
                                    visible = isDebugMode,
                                    enter = expandVertically(
                                        expandFrom = Alignment.Top,
                                        animationSpec = tween(300)
                                    ),
                                    exit = shrinkVertically(
                                        shrinkTowards = Alignment.Top,
                                        animationSpec = tween(300)
                                    ),
                                ) {
                                    Column {
                                        Spacer(Modifier.height(16.dp))

                                        var logLines by remember {
                                            mutableStateOf<List<Pair<Long, String>>>(
                                                emptyList()
                                            )
                                        }
                                        LaunchedEffect(Unit) {
                                            GlobalState.logState.collect { logs ->
                                                logLines = logs.reversed()
                                            }
                                        }

                                        SelectionContainer(Modifier.fillMaxWidth()) {
                                            Box(
                                                Modifier
                                                    .fillMaxWidth()
                                                    .height(400.dp)
//                                                .height(IntrinsicSize.Min)
                                                    .padding(horizontal = 20.dp)
                                                    .clip(RoundedCornerShape(14.dp))
                                                    .background(Color.White.copy(.05f))
                                            ) {
                                                val listState = rememberLazyListState()
                                                LazyColumn(
                                                    state = listState,
                                                    modifier = Modifier
                                                        .fillMaxSize(),
                                                ) {
                                                    item(key = -1) {
                                                        Spacer(Modifier.height(16.dp))
                                                    }

                                                    items(
                                                        items = logLines,
                                                        key = { item -> item.first },
                                                    ) { (_, item) ->
                                                        Text(
                                                            text = item,
                                                            modifier = Modifier
                                                                .fillMaxWidth()
                                                                .padding(
                                                                    vertical = 2.dp,
                                                                    horizontal = 20.dp
                                                                ),
                                                            style = AppTheme.typography.dialogSubtitle,
                                                            color = AppTheme.colors.contentPrimary
                                                        )
                                                    }

                                                    item(key = -2) {
                                                        Spacer(Modifier.height(16.dp))
                                                    }
                                                }

                                                Row(
                                                    modifier = Modifier
                                                        .align(Alignment.TopEnd)
                                                        .padding(top = 16.dp, end = 16.dp),
                                                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                                                ) {
                                                    Box(
                                                        modifier = Modifier
                                                            .size(48.dp)
                                                            .clip(RoundedCornerShape(14.dp))
                                                            .background(Color.Black.copy(.24f))
                                                            .clickable {
                                                                scope.launch(Dispatchers.Default) {
                                                                    val logs =
                                                                        GlobalState.logState.value
                                                                    val text =
                                                                        buildShareableLogsText(logs)
                                                                    if (text.isBlank()) return@launch
                                                                    context.shareText(text)
                                                                }
                                                            },
                                                        contentAlignment = Alignment.Center
                                                    ) {
                                                        Icon(
                                                            imageVector = Icons.Filled.Share,
                                                            tint = AppTheme.colors.contentPrimary,
                                                            contentDescription = "share",
                                                            modifier = Modifier
                                                                .size(22.dp)
                                                        )
                                                    }

                                                    Box(
                                                        modifier = Modifier
                                                            .size(48.dp)
                                                            .clip(RoundedCornerShape(14.dp))
                                                            .background(Color.Black.copy(.24f))
                                                            .clickable {
                                                                scope.launch {
                                                                    GlobalState.logState.emit(
                                                                        emptyList()
                                                                    )
                                                                }
                                                            },
                                                        contentAlignment = Alignment.Center
                                                    ) {
                                                        Icon(
                                                            painter = painterResource(R.drawable.ic_delete),
                                                            tint = AppTheme.colors.contentPrimary,
                                                            contentDescription = "clear",
                                                            modifier = Modifier
                                                                .padding(6.dp)
                                                        )
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }

                                Spacer(Modifier.height(12.dp))

                                // is debug
                                RenderSwitcher(
                                    modifier = Modifier.padding(horizontal = 20.dp),
                                    title = stringResource(R.string.debug_mode),
                                    subtitle = stringResource(R.string.show_touch_logs),
                                    value = isDebugMode,
                                    enable = true,
                                    groupDivider = false,
                                    onChange = {
                                        scope.launch {
                                            dataStore.saveValue(GeneralPrefs.DEBUG_MODE, it)
                                            // if (it) scrollState.animateScrollTo(7000)
                                        }
                                    }
                                )

                                Spacer(Modifier.height(12.dp))

                                // deep logs
                                RenderSwitcher(
                                    modifier = Modifier.padding(horizontal = 20.dp),
                                    title = stringResource(R.string.state_logs),
                                    subtitle = stringResource(R.string.state_logs_desc),
                                    value = deepLogs,
                                    enable = isDebugMode,
                                    groupDivider = false,
                                    onChange = {
                                        scope.launch {
                                            dataStore.saveValue(GeneralPrefs.DEEP_LOGS, it)
                                        }
                                    }
                                )

                                Spacer(Modifier.height(12.dp))

                                // suppression mode
                                RenderSwitcher(
                                    modifier = Modifier.padding(horizontal = 20.dp),
                                    title = stringResource(R.string.suppression_mode),
                                    subtitle = stringResource(R.string.suppression_mode_desc),
                                    value = suppressionMode,
                                    enable = true,
                                    groupDivider = false,
                                    onChange = { newValue ->
                                        if (newValue) {
                                            actionBindLockConfirmDialog = true
                                        } else {
                                            scope.launch {
                                                dataStore.saveValue(
                                                    GeneralPrefs.SUPPRESSION_MODE,
                                                    false
                                                )
                                            }
                                        }
                                    }
                                )

                                Spacer(Modifier.height(16.dp))
                                RenderGroupDivider()
                                Spacer(Modifier.height(24.dp))

                                RenderGroupTitle(stringResource(R.string.import_export_settings))

                                RenderListButton(
                                    modifier = Modifier.padding(horizontal = 20.dp),
                                    title = stringResource(R.string.import_settings),
                                    subtitle = stringResource(R.string.import_settings_desc)
                                ) {
                                    scope.launch(Dispatchers.IO) {
                                        runCatching {
                                            openGibbLauncher.launch(
                                                arrayOf(
                                                    "application/gibb",
                                                    "application/octet-stream",
                                                    "*/*"
                                                )
                                            )
                                        }.onFailure { Timber.e(it) }
                                    }
                                }

                                Spacer(Modifier.height(12.dp))

                                var exportSettingsDialog by remember { mutableStateOf(false) }
                                if (exportSettingsDialog) {
                                    RenderSettingsExportDialog(uiScale, canAccessibility.value) {
                                        exportSettingsDialog = false
                                    }
                                }

                                RenderListButton(
                                    modifier = Modifier.padding(horizontal = 20.dp),
                                    title = stringResource(R.string.export_settings),
                                    subtitle = stringResource(R.string.export_settings_desc)
                                ) {
                                    exportSettingsDialog = true
                                }

                                Spacer(Modifier.height(90.dp))
                            }
                        }
                    }
                }
            }
        }
        handleIncomingIntent(intent)
    }

    // Launcher to open system file picker for .gibb using SAF
    private val openGibbLauncher = registerForActivityResult(
        ActivityResultContracts.OpenDocument()
    ) { uri ->
        if (uri != null) {
            // Persist read permission when provider supports it; safe on API 30
            try {
                contentResolver.takePersistableUriPermission(
                    uri,
                    Intent.FLAG_GRANT_READ_URI_PERMISSION
                )
            } catch (_: SecurityException) {
                // Some providers may not allow persist; transient grant is still fine
            }

            // Route through ACTION_VIEW to reuse your existing open/import flow
            startOpenWithFlow(uri)
        }
    }

    private fun startOpenWithFlow(uri: Uri) = runCatching {
        // Use your unique MIME so the intent-filter matches unambiguously
        val intent = Intent(Intent.ACTION_VIEW).apply {
            setDataAndType(uri, "application/gibb")
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            // Ensure permission propagation via ClipData for some choosers/providers
            clipData = ClipData.newRawUri("gibb", uri)
            // Direct back into this app (no chooser) to follow the same code path you already have
            setPackage(packageName)
            // Play nice with singleTop/singleTask setups
            addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        }
        startActivity(intent)
    }.onFailure {
        Timber.e(it)
        toast(getString(R.string.cannot_open_file))
    }

    private suspend fun onImportBinds(importCode: String) = keyBindStorage.applyCode(importCode)

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
        handleIncomingIntent(intent)
    }

    override fun onResume() {
        super.onResume()
        runCatching { viewModel.onResume(packageName) }
    }

    private fun handleIncomingIntent(intent: Intent) = ioScope.launch {
        val action = intent.action
        val uri = intent.data ?: return@launch

        if (action !in setOf(
                Intent.ACTION_VIEW,
                Intent.ACTION_OPEN_DOCUMENT,
                Intent.ACTION_GET_CONTENT
            )
        ) return@launch

        readTextFromUri(uri)?.let { text ->
            if (text.startsWith("binds")) {
                val importBinds = text.removePrefix("binds")
                viewModel.setBindsImport(importBinds)
                Timber.d("[IMPORT] $importBinds")
            }
            if (text.startsWith("full")) {
                val importSettings = text.removePrefix("full")
                viewModel.setSettingsImport(importSettings)
                Timber.d("[IMPORT] full settings $importSettings")
            }
        }
    }

    private suspend fun readTextFromUri(uri: Uri): String? = suspendCoroutine { cont ->
        val fileContent = try {
            contentResolver.openInputStream(uri)
                ?.bufferedReader()
                .use { it?.readText() }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
        cont.resume(fileContent)
    }

    private fun Int.toDecimalSecondString(digits: Int = 2) =
        String.format(Locale.US, "%.${digits}f сек", this / 1000.0)

    private fun String.spannedFromHtml(): Spanned {
        return fromHtml() as Spanned
    }

    @SuppressLint("ObsoleteSdkInt")
    private fun String.fromHtml(): CharSequence {
        // Keep original line breakers
        val htmlString = trim().replace(LINE_SEPARATOR, HTML_LINE_SEPARATOR)
        return if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            Html.fromHtml(htmlString, Html.FROM_HTML_MODE_LEGACY)
        } else {
            @Suppress("DEPRECATION") Html.fromHtml(htmlString)
        }
    }

    private fun extractInts(input: String): List<Int> {
        return Regex("\\d+")
            .findAll(input)
            .map { it.value.toInt() }
            .toList()
    }

    private fun Float.toOverlayPercentString(
        min: Float = -1f,
        mid: Float = 0f,
        max: Float = 0.4f,
        minPercent: Float = 15f,
        midPercent: Float = 100f,
        maxPercent: Float = 120f,
        decimals: Int = 0
    ): String {
        // Clamp input to [min, max]
        val v = when {
            this < min -> min
            this > max -> max
            else -> this
        }

        // Linear interpolation helper
        fun localLerp(a: Float, b: Float, t: Float) = a + (b - a) * t

        val percent = if (v <= mid) {
            val span = (mid - min).takeIf { it != 0f } ?: 1f
            val t = (v - min) / span
            localLerp(minPercent, midPercent, t)
        } else {
            val span = (max - mid).takeIf { it != 0f } ?: 1f
            val t = (v - mid) / span
            localLerp(midPercent, maxPercent, t)
        }

        return if (decimals <= 0) "${percent.roundToInt()}%" else "%.${decimals}f%%".format(percent)
    }

    private fun String.scaleSize(percent: Int): String {
        // Split strictly by space; collapse multiple spaces first.
        val parts = trim().split(" ").filter { it.isNotEmpty() }
        if (parts.size != 2) return this

        val numberPart = parts[0].replace(',', '.') // allow "1,5"
        val unitPart = parts[1]

        val value = numberPart.toBigDecimalOrNull() ?: return this

        // Scale: value * percent / 100, keep precision before final rounding
        val scaled = value.multiply(BigDecimal(percent))
            .divide(BigDecimal(100), 10, RoundingMode.HALF_UP)
            .setScale(1, RoundingMode.HALF_UP) // exactly one decimal place

        // Always dot as decimal separator, one decimal place
        val df = DecimalFormat("#0.0").apply {
            decimalFormatSymbols = decimalFormatSymbols.apply { decimalSeparator = '.' }
        }

        return "${df.format(scaled)} $unitPart"
    }

    private fun Float.asOneDecimalX(): String {
        val rounded = (this * 10f).roundToInt() / 10f
        return String.format(Locale.US, "%.1fx", rounded)
    }

    @Composable
    private fun RenderAppUpdate(info: DisplayAppUpdate, updateDownloadState: UiDownloadState?) {
        val context = LocalContext.current
        val targetColor = if (info.mandatory) {
            AppTheme.colors.deleteButton
        } else {
            AppTheme.colors.warning
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 42.dp)
                .clip(RoundedCornerShape(14.dp))
                .border(
                    shape = RoundedCornerShape(14.dp),
                    width = 1.dp,
                    color = targetColor
                )
                .background(targetColor.copy(.2f))
                .padding(vertical = 16.dp, horizontal = 18.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            if (info.version.isNotEmpty()) {
                Text(
                    text = "${stringResource(R.string.new_version_available)}: ${info.version}",
                    color = AppTheme.colors.contentPrimary,
                    style = AppTheme.typography.dialogListTitle
                )
            }

            if (info.text.isNotEmpty()) {
                Text(
                    text = info.text
                        .spannedFromHtml()
                        .toAnnotatedString(),
                    color = AppTheme.colors.contentPrimary,
                    style = AppTheme.typography.dialogSubtitle
                )
            }

            if (info.downloadUrl.isNotEmpty() || info.infoUrl.isNotEmpty()) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(
                        16.dp,
                        Alignment.End
                    ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (updateDownloadState is UiDownloadState.InProgress) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(
                                6.dp,
                                Alignment.CenterVertically
                            ),
                            modifier = Modifier.weight(1f)
                        ) {
                            val percent = updateDownloadState.percent
                            ThinWhiteProgress(
                                percent = percent,
                                modifier = Modifier.fillMaxWidth()
                            )

                            if (info.size.isNotEmpty()) {
                                val text =
                                    "${info.size.scaleSize(percent)} / ${info.size}"
                                Text(
                                    text = text,
                                    color = AppTheme.colors.contentPrimary,
                                    style = AppTheme.typography.dialogSubtitle.copy(
                                        fontSize = 8.sp
                                    )
                                )
                            }
                        }
                    }

                    if (info.downloadUrl.isNotEmpty()) {
                        Text(
                            text = stringResource(R.string.download),
                            modifier = Modifier
                                .clip(RoundedCornerShape(4.dp))
                                .background(
                                    AppTheme.colors.contentPrimary.copy(
                                        .04f
                                    )
                                )
                                // .clickable { context.shareText(info.downloadUrl) }
                                .clickable(updateDownloadState == null) {
                                    viewModel.downloadUpdate(
                                        info.downloadUrl
                                    )
                                }
                                .padding(
                                    horizontal = 12.dp,
                                    vertical = 8.dp
                                )
                                .then(
                                    if (updateDownloadState != null) {
                                        Modifier.alpha(.2f)
                                    } else Modifier
                                ),
                            color = AppTheme.colors.contentPrimary,
                            style = AppTheme.typography.sourceType
                        )
                    }

                    if (info.infoUrl.isNotEmpty()) {
                        Text(
                            text = stringResource(R.string.details),
                            modifier = Modifier
                                .clip(RoundedCornerShape(4.dp))
                                .background(
                                    AppTheme.colors.contentPrimary.copy(
                                        .04f
                                    )
                                )
                                .clickable { context.openUrlSmart(info.infoUrl) }
                                .padding(
                                    horizontal = 12.dp,
                                    vertical = 8.dp
                                ),
                            color = AppTheme.colors.contentPrimary,
                            style = AppTheme.typography.sourceType
                        )
                    }
                }
            }
        }

        Spacer(Modifier.height(16.dp))
    }

    @Suppress("SameParameterValue")
    @Composable
    private fun RenderSettingsExportDialog(
        uiScale: Float? = null,
        canAccessibility: Boolean,
        onDismiss: () -> Unit
    ) {
        val scope = rememberCoroutineScope()
        val context = LocalContext.current

        var generalPrefsSetting by remember { mutableStateOf(true) }
        var launcherPrefsSetting by remember { mutableStateOf(true) }

        ConfirmDialog(
            title = stringResource(R.string.export_settings),
            message = stringResource(R.string.select_settings_to_export),
            uiScale = uiScale,
            negativeAction = false,
            extraContent = {
                Spacer(
                    Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(AppTheme.colors.surfaceMenuDivider)
                )

                RenderSwitcher(
                    modifier = Modifier,
                    title = stringResource(R.string.general_settings),
                    value = generalPrefsSetting,
                    enable = true,
                    groupDivider = false,
                    clickRadius = 0,
                    titleStyle = AppTheme.typography.overlayLauncherSettingsTitle,
                    onChange = {
                        generalPrefsSetting = !generalPrefsSetting
                    }
                )

                Spacer(
                    Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(AppTheme.colors.surfaceMenuDivider)
                )

                RenderSwitcher(
                    modifier = Modifier,
                    title = stringResource(R.string.launcher_name),
                    value = launcherPrefsSetting,
                    enable = true,
                    groupDivider = false,
                    clickRadius = 0,
                    titleStyle = AppTheme.typography.overlayLauncherSettingsTitle,
                    onChange = {
                        launcherPrefsSetting = !launcherPrefsSetting
                    }
                )

                Spacer(
                    Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(AppTheme.colors.surfaceMenuDivider)
                )

                Spacer(Modifier.height(12.dp))
            },
            onCancel = { onDismiss() },
            onDismiss = { onDismiss() },
            onClick = {
                if (!canAccessibility) {
                    context.openAccessibilitySettings()
                    return@ConfirmDialog
                }

                if (!context.isNotificationServiceEnabled()) {
                    context.requestNotificationServicePermission()
                    return@ConfirmDialog
                }

                if (!context.requireDisplayOverlay()) {
                    return@ConfirmDialog
                }

                if (!generalPrefsSetting && !launcherPrefsSetting) return@ConfirmDialog

                // Do export
                scope.launch(Dispatchers.IO) {
                    runCatching {
                        val export = encodeBase64Jvm(
                            dataStore.exportAllSettings(
                                task = DataStoreBackupTask(
                                    withGeneral = generalPrefsSetting,
                                    withLauncher = launcherPrefsSetting
                                )
                            )
                        ).let { baseBackup ->
                            // With icons files
                            if (launcherPrefsSetting) {
                                baseBackup + BACKUP_DIVIDER + context.backupIconsToString()
                            } else baseBackup
                        }
                        context.cleanupShareTempFiles()
                        val nameType = when {
                            generalPrefsSetting && launcherPrefsSetting -> "full"
                            launcherPrefsSetting -> "launcher"
                            else -> "settings"
                        }
                        context.shareTextAsGibbFile(export, "full", nameType)
                    }.onFailure { Timber.e(it) }
                    onDismiss()
                }
            }
        )
    }

    @Suppress("SameParameterValue")
    @Composable
    private fun RenderSettingsImportDialog(uiScale: Float? = null, canAccessibility: Boolean) {
        val settingsImport by viewModel.settingsImport.collectAsStateWithLifecycle()
        val iconsImport by viewModel.iconsImport.collectAsStateWithLifecycle()
        val scope = rememberCoroutineScope()
        val context = LocalContext.current

        val importSettingsConfirmDialog by remember { derivedStateOf { settingsImport.isNotEmpty() } }
        if (importSettingsConfirmDialog) {
            var importTask by remember { mutableStateOf<DataStoreBackupTask?>(null) }

            LaunchedEffect(settingsImport) {
                scope.launch(Dispatchers.IO) {
                    val params = dataStore.collectBackupParams(settingsImport)
                    importTask = DataStoreBackupTask(
                        withGeneral = params.contains(GeneralPrefs.DATA_SYNC_ENABLED.name),
                        withLauncher = params.contains(LauncherPrefs.LAUNCHER_DATA.name)
                    )
                }
            }

            fun onDismissImport() {
                importTask = null
                viewModel.setSettingsImport("")
            }

            importTask?.let { task ->
                var generalPrefsSetting by remember {
                    mutableStateOf(
                        task.withGeneral
                    )
                }
                var launcherPrefsSetting by remember {
                    mutableStateOf(
                        task.withLauncher
                    )
                }

                ConfirmDialog(
                    title = stringResource(R.string._import),
                    message = stringResource(R.string.select_settings_to_import),
                    uiScale = uiScale,
                    negativeAction = false,
                    extraContent = {

                        Spacer(
                            Modifier
                                .fillMaxWidth()
                                .height(1.dp)
                                .background(AppTheme.colors.surfaceMenuDivider)
                        )

                        if (task.withGeneral) {
                            RenderSwitcher(
                                modifier = Modifier,
                                title = stringResource(R.string.general_settings),
                                value = generalPrefsSetting,
                                enable = true,
                                groupDivider = false,
                                clickRadius = 0,
                                titleStyle = AppTheme.typography.overlayLauncherSettingsTitle,
                                onChange = {
                                    generalPrefsSetting = !generalPrefsSetting
                                }
                            )

                            Spacer(
                                Modifier
                                    .fillMaxWidth()
                                    .height(1.dp)
                                    .background(AppTheme.colors.surfaceMenuDivider)
                            )
                        }

                        if (task.withLauncher) {
                            RenderSwitcher(
                                modifier = Modifier,
                                title = stringResource(R.string.launcher_name),
                                value = launcherPrefsSetting,
                                enable = true,
                                groupDivider = false,
                                clickRadius = 0,
                                titleStyle = AppTheme.typography.overlayLauncherSettingsTitle,
                                onChange = {
                                    launcherPrefsSetting = !launcherPrefsSetting
                                }
                            )
                        }

                        Spacer(
                            Modifier
                                .fillMaxWidth()
                                .height(1.dp)
                                .background(AppTheme.colors.surfaceMenuDivider)
                        )

                        Spacer(Modifier.height(12.dp))
                    },
                    onCancel = { onDismissImport() },
                    onDismiss = { onDismissImport() },
                    onClick = {
                        if (!canAccessibility) {
                            context.openAccessibilitySettings()
                            return@ConfirmDialog
                        }

                        if (!context.isNotificationServiceEnabled()) {
                            context.requestNotificationServicePermission()
                            return@ConfirmDialog
                        }

                        if (!context.requireDisplayOverlay()) {
                            return@ConfirmDialog
                        }

                        if (!generalPrefsSetting && !launcherPrefsSetting) return@ConfirmDialog

                        // Do import
                        scope.launch(Dispatchers.IO) {

                            // Restore icons
                            iconsImport.takeIf { launcherPrefsSetting && it.isNotEmpty() }
                                ?.let { icons ->
                                    context.restoreIconsFromString(icons)
                                }

                            // Restore settings
                            dataStore.importAllSettings(
                                serialized = settingsImport,
                                task = DataStoreBackupTask(
                                    withGeneral = generalPrefsSetting,
                                    withLauncher = launcherPrefsSetting
                                )
                            )
                            viewModel.rebuildLauncher()
                            onDismissImport()
                        }
                    }
                )
            }
        }
    }

    private fun buildShareableLogsText(logs: List<Pair<Long, String>>): String {
        return logs.joinToString(separator = "\n") { (timestamp, message) ->
            "${millisToDateTimeString(timestamp)} | $message"
        }
    }
}

private object MainScreenSettingsRow {
    val keys: Array<Preferences.Key<*>> = arrayOf(
        GeneralPrefs.DATA_SYNC_ENABLED,
        GeneralPrefs.DEBUG_MODE,
        GeneralPrefs.FULL_BROADCAST,
        GeneralPrefs.TRACK_KEY_EVENTS,
        GeneralPrefs.CUSTOM_LONG_PRESS_ENABLED,
        GeneralPrefs.CUSTOM_LONG_PRESS_TIME,
        GeneralPrefs.DOUBLE_CLICK_ENABLED,
        GeneralPrefs.DOUBLE_CLICK_TIME,
        GeneralPrefs.CUSTOM_SHORT_CLICK_ENABLED,
        GeneralPrefs.MULTI_LONG_PRESS_ENABLED,
        GeneralPrefs.SUPPRESSION_MODE,
        GeneralPrefs.DISABLE_ON_CLIMATE,
        GeneralPrefs.SOURCE_MANAGEMENT,
        GeneralPrefs.RADIO_BT_CONTROL,
        GeneralPrefs.REMEMBER_DRIVE_MODE,
        GeneralPrefs.TARGET_RECOVERY_DRIVE_MODE,
        GeneralPrefs.DRIVE_MODE_OVERLAY,
        GeneralPrefs.DM_OVERLAY_SCALE,
        GeneralPrefs.DM_OVERLAY_OFFSET,
        NoBackupPrefs.CONFIGURATOR_WARNING,
        GeneralPrefs.MEDIA_DATA_TRANSLATOR,
        GeneralPrefs.DEEP_LOGS,
        GeneralPrefs.MEDIA_CONTROL_ENABLED,
        GeneralPrefs.ENABLE_ADB_HELPER,
        GeneralPrefs.ADB_HELPER_PORT,
        GeneralPrefs.ADB_DIM_AUTO_STOP,
        GeneralPrefs.ALT_MENU,
        GeneralPrefs.ALT_MUTE,
        GeneralPrefs.ALT_LONG_TIME,
    )

    val defaults: List<Any?> = listOf(
        false,
        false,
        true,
        false,
        false,
        1000,
        false,
        300,
        false,
        false,
        false,
        false,
        false,
        true,
        false,
        LAST_DM_ID,
        false,
        0f,
        0f,
        true,
        false,
        false,
        false,
        false,
        5555,
        false,
        true,
        true,
        ADDITIONAL_KEYS_MIN_LONG_PRESS_TIME,
    )
}
