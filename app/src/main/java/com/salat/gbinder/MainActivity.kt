package com.salat.gbinder

import android.content.ClipData
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
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
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
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
import com.salat.gbinder.components.roundScale
import com.salat.gbinder.components.shareTextAsGibbFile
import com.salat.gbinder.components.spannedFromHtml
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
import com.salat.gbinder.entity.DISPLAY_AUDIO_SOURCES
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
import com.salat.gbinder.features.configurator.RenderSystemParams
import com.salat.gbinder.features.launcher.BACKUP_DIVIDER
import com.salat.gbinder.features.launcher.backupIconsToString
import com.salat.gbinder.features.launcher.restoreIconsFromString
import com.salat.gbinder.mappers.keyCodeMap
import com.salat.gbinder.mappers.toAllDisplay
import com.salat.gbinder.mappers.toDisplayAdbState
import com.salat.gbinder.mappers.toDisplayIcon
import com.salat.gbinder.screenParts.InputPortDialog
import com.salat.gbinder.screenParts.RenderDebugSettingsBlock
import com.salat.gbinder.screenParts.RenderDocumentationBlock
import com.salat.gbinder.screenParts.RenderGroupDivider
import com.salat.gbinder.screenParts.RenderGroupTitle
import com.salat.gbinder.screenParts.RenderKeyBinds
import com.salat.gbinder.screenParts.UiScaleDialog
import com.salat.gbinder.statekeeper.domain.repository.StateKeeperRepository
import com.salat.gbinder.ui.BaseButton
import com.salat.gbinder.ui.ConfirmDialog
import com.salat.gbinder.ui.HugeSegmentToggler
import com.salat.gbinder.ui.KeyBindingDialog
import com.salat.gbinder.ui.NotifPickerDialog
import com.salat.gbinder.ui.RenderListButton
import com.salat.gbinder.ui.RenderMediaAppsPickerDialog
import com.salat.gbinder.ui.RenderSwitcher
import com.salat.gbinder.ui.StatusLamp
import com.salat.gbinder.ui.TargetRestoreDMDialog
import com.salat.gbinder.ui.ThinWhiteProgress
import com.salat.gbinder.ui.ValueSlider
import com.salat.gbinder.ui.theme.AppTheme
import com.salat.gbinder.util.SystemAppsLightRepository
import com.salat.gbinder.util.encodeBase64Jvm
import com.salat.gbinder.util.getDisplayDriveModeName
import com.salat.gbinder.util.promptInstall
import com.salat.gbinder.util.toContentUri
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.DecimalFormat
import java.util.Locale
import javax.inject.Inject
import kotlin.math.roundToInt

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

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

        setContent { RenderScreen() }
        handleIncomingIntent(intent)
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
        handleIncomingIntent(intent)
    }

    override fun onResume() {
        super.onResume()
        runCatching { viewModel.onResume(packageName) }
    }

    @Composable
    private fun RenderScreen() {
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
            var showSystemParams by remember { mutableStateOf(false) }

            var mainScreenState by rememberSaveable(
                stateSaver = MainScreenState.saver
            ) { mutableStateOf(MainScreenState.Default) }
            var keyBinds by remember { mutableStateOf<List<DisplayKeyBind>?>(null) }

            var adbConnectionState by remember {
                mutableStateOf<DisplayAdbState>(
                    DisplayAdbState.Disconnected
                )
            }
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
                        mainScreenState = mainScreenState.updateFrom(row)
                    }
                }
                launch {
                    dataStore.getValueFlow(GeneralPrefs.KEY_BINDS).collect { json ->
                        json?.let {
                            keyBinds = withContext(Dispatchers.IO) {
                                buildDisplayKeyBinds(it, context)
                            }
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
                                val installedState = withContext(Dispatchers.IO) {
                                    systemApps.isDebugMInstalled() to systemApps.isMConfigInstalled()
                                }
                                isDebugMInstalled = installedState.first
                                isMConfigMInstalled = installedState.second
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
                                mainScreenState = mainScreenState.copy(suppressionMode = true)
                                actionBindLockConfirmDialog = null
                                scope.launch {
                                    dataStore.saveValue(
                                        GeneralPrefs.SUPPRESSION_MODE,
                                        true
                                    )
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
                                if (showConfigurator.first || showSystemParams) {
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

                            if (mainScreenState.configuratorWarning) {

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
                                                mainScreenState =
                                                    mainScreenState.copy(configuratorWarning = false)
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
                        } else if (showSystemParams) {
                            RenderSystemParams(
                                uiScaleState = uiScale,
                                enableAdbHelper = mainScreenState.enableAdbHelper,
                                adbDimAutoStop = mainScreenState.adbDimAutoStop,
                                onAdbDimAutoStopChanged = {
                                    mainScreenState = mainScreenState.copy(adbDimAutoStop = it)
                                    scope.launch {
                                        dataStore.saveValue(GeneralPrefs.ADB_DIM_AUTO_STOP, it)
                                    }
                                },
                                onClose = { showSystemParams = false }
                            )
                        } else if (readyUi) {
                            RenderMainContent(
                                mainScreenState = mainScreenState,
                                updateMainScreenState = { mainScreenState = it },
                                uiScale = uiScale,
                                updateUiScale = { uiScale = it },
                                appUpdateInfo = appUpdateInfo,
                                updateDownloadState = updateDownloadState,
                                keyBinds = keyBinds,
                                canAccessibility = canAccessibility.value,
                                isNotificationServiceEnabled = isNotificationServiceEnabled,
                                isDebugMInstalled = isDebugMInstalled,
                                isMConfigMInstalled = isMConfigMInstalled,
                                adbConnectionState = adbConnectionState,
                                openConfigurator = { onlyFavorite ->
                                    showConfigurator = true to onlyFavorite
                                },
                                openSystemParams = { showSystemParams = true },
                                showActionBindLockConfirmDialog = {
                                    actionBindLockConfirmDialog = true
                                },
                                showDeleteBindConfirmDialog = { bindCode ->
                                    onDeleteDialog(bindCode)
                                }
                            )
                        }
                    }
                }
            }
        }
    }

    @Suppress("AssignedValueIsNeverRead")
    @Composable
    private fun ColumnScope.RenderMainContent(
        mainScreenState: MainScreenState,
        updateMainScreenState: (MainScreenState) -> Unit,
        uiScale: Float,
        updateUiScale: (Float) -> Unit,
        appUpdateInfo: DisplayAppUpdate?,
        updateDownloadState: UiDownloadState?,
        keyBinds: List<DisplayKeyBind>?,
        canAccessibility: Boolean,
        isNotificationServiceEnabled: Boolean,
        isDebugMInstalled: Boolean,
        isMConfigMInstalled: Boolean,
        adbConnectionState: DisplayAdbState,
        openConfigurator: (onlyFavorite: Boolean) -> Unit,
        openSystemParams: () -> Unit,
        showActionBindLockConfirmDialog: () -> Unit,
        showDeleteBindConfirmDialog: (String) -> Unit
    ) {
        val context = LocalContext.current
        val scope = rememberCoroutineScope()

        Spacer(Modifier.height(26.dp))

        Text(
            modifier = Modifier,
            text = stringResource(
                if (mainScreenState.isEnabled) {
                    R.string.binder_active
                } else {
                    R.string.binder_disabled
                }
            ),
            style = AppTheme.typography.dialogTitle,
            color = if (mainScreenState.isEnabled) {
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
            value = mainScreenState.isEnabled,
            enable = true,
            groupDivider = false,
            onChange = {
                updateMainScreenState(mainScreenState.copy(isEnabled = it))
                scope.launch(Dispatchers.IO) {
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
            value = mainScreenState.enableCustomLongClick,
            enable = true,
            groupDivider = false,
            onChange = { enable ->
                updateMainScreenState(
                    mainScreenState.copy(
                        enableCustomLongClick = enable,
                        altMenu = if (enable) mainScreenState.altMenu else false,
                        altMute = if (enable) mainScreenState.altMute else false
                    )
                )
                scope.launch(Dispatchers.IO) {
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
                    mainScreenState.customLongClickTiming.toDecimalSecondString(),
            color = AppTheme.colors.contentPrimary
        )
        ValueSlider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 36.dp),
            value = mainScreenState.customLongClickTiming,
            valueRange = 50..1500,
            onValueChange = { newValue ->
                updateMainScreenState(mainScreenState.copy(customLongClickTiming = newValue))
                scope.launch(Dispatchers.IO) {
                    dataStore.saveValue(
                        GeneralPrefs.CUSTOM_LONG_PRESS_TIME,
                        newValue
                    )
                }
            },
            enabled = true,
            defaultMark = MainScreenState.Default.customLongClickTiming,
            step = 10
        )

        Spacer(Modifier.height(12.dp))

        // custom short click enable
        RenderSwitcher(
            modifier = Modifier.padding(horizontal = 20.dp),
            title = stringResource(R.string.custom_short_press_timing),
            subtitle = stringResource(R.string.custom_short_press_timing_desc),
            value = mainScreenState.enabledCustomShortClick,
            enable = true,
            groupDivider = false,
            onChange = {
                updateMainScreenState(mainScreenState.copy(enabledCustomShortClick = it))
                scope.launch(Dispatchers.IO) {
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
            value = mainScreenState.multiLongPressEnabled,
            enable = true,
            groupDivider = false,
            onChange = {
                updateMainScreenState(mainScreenState.copy(multiLongPressEnabled = it))
                scope.launch(Dispatchers.IO) {
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
            value = mainScreenState.lockDoubleClick,
            enable = true,
            groupDivider = false,
            onChange = {
                updateMainScreenState(mainScreenState.copy(lockDoubleClick = it))
                scope.launch(Dispatchers.IO) {
                    dataStore.saveValue(
                        GeneralPrefs.DOUBLE_CLICK_ENABLED,
                        it
                    )
                }
            }
        )

        Spacer(Modifier.height(16.dp))

        AnimatedVisibility(
            visible = mainScreenState.lockDoubleClick,
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
                            mainScreenState.doubleClickTime.toDecimalSecondString(),
                    color = AppTheme.colors.contentPrimary
                )
                ValueSlider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 36.dp),
                    value = mainScreenState.doubleClickTime,
                    valueRange = 50..1500,
                    onValueChange = { newValue ->
                        updateMainScreenState(mainScreenState.copy(doubleClickTime = newValue))
                        scope.launch(Dispatchers.IO) {
                            dataStore.saveValue(
                                GeneralPrefs.DOUBLE_CLICK_TIME,
                                newValue
                            )
                        }
                    },
                    enabled = true,
                    defaultMark = MainScreenState.Default.doubleClickTime,
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
            value = mainScreenState.altMenu,
            enable = true,
            groupDivider = false,
            onChange = { enable ->
                updateMainScreenState(
                    mainScreenState.copy(
                        altMenu = enable,
                        enableCustomLongClick = if (enable) true else mainScreenState.enableCustomLongClick
                    )
                )
                scope.launch(Dispatchers.IO) {
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
            value = mainScreenState.altMute,
            enable = true,
            groupDivider = false,
            onChange = { enable ->
                updateMainScreenState(
                    mainScreenState.copy(
                        altMute = enable,
                        enableCustomLongClick = if (enable) true else mainScreenState.enableCustomLongClick
                    )
                )
                scope.launch(Dispatchers.IO) {
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
                    mainScreenState.altLongTime.toDecimalSecondString(),
            color = AppTheme.colors.contentPrimary
        )
        ValueSlider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 36.dp),
            value = mainScreenState.altLongTime,
            valueRange = 50..1500,
            onValueChange = { newValue ->
                updateMainScreenState(mainScreenState.copy(altLongTime = newValue))
                scope.launch(Dispatchers.IO) {
                    dataStore.saveValue(
                        GeneralPrefs.ALT_LONG_TIME,
                        newValue
                    )
                }
            },
            enabled = true,
            defaultMark = MainScreenState.Default.altLongTime,
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
        RenderKeyBinds(
            keyBinds = keyBinds,
            onDeleteDialog = { bindCode -> showDeleteBindConfirmDialog(bindCode) }
        )

        Spacer(Modifier.height(24.dp))
        RenderGroupDivider()
        Spacer(Modifier.height(24.dp))

        RenderGroupTitle(stringResource(R.string.playback_control))

        // media control
        RenderSwitcher(
            modifier = Modifier.padding(horizontal = 20.dp),
            title = stringResource(R.string.audio_control),
            subtitle = stringResource(R.string.media_control_desc),
            value = mainScreenState.mediaControlEnabled,
            enable = true,
            groupDivider = false,
            onChange = {
                if (!isNotificationServiceEnabled) {
                    context.requestNotificationServicePermission()
                    return@RenderSwitcher
                }

                updateMainScreenState(mainScreenState.copy(mediaControlEnabled = it))
                scope.launch(Dispatchers.IO) {
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
            enable = mainScreenState.mediaControlEnabled
        ) {
            showMediaAppsDialog = true
        }

        Spacer(Modifier.height(16.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .alpha(if (mainScreenState.mediaControlEnabled) 1f else .25f)
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 42.dp),
                text = "Тип управления",
                style = AppTheme.typography.screenTitle,
                color = AppTheme.colors.contentPrimary
            )

            Spacer(Modifier.height(12.dp))

            Box(
                Modifier
                    .padding(horizontal = 42.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(AppTheme.colors.surfaceMenu)
                    .padding(2.dp)
            ) {
                val list = remember(mainScreenState.adbHelperPort) {
                    listOf(
                        HugeTogglerItem(
                            text = context.getString(R.string.media_control_mode_full_title),
                            subtitle = context.getString(R.string.media_control_mode_full_subtitle)
                        ),
                        HugeTogglerItem(
                            text = context.getString(R.string.media_control_mode_session_title),
                            subtitle = context.getString(R.string.media_control_mode_session_subtitle)
                        )
                    )
                }
                HugeSegmentToggler(
                    modifier = Modifier
                        .fillMaxWidth(),
                    enabled = mainScreenState.mediaControlEnabled,
                    selectedIndex = when {
                        mainScreenState.sourceManagement -> 1
                        else -> 0
                    },
                    textHorizontalPadding = 14,
                    dividerPadding = 3,
                    multiline = true,
                    titleTextStyle = AppTheme.typography.togglerTitle,
                    subtitleTextStyle = AppTheme.typography.togglerSubtitle,
                    activeBackground = AppTheme.colors.contentAccent,
                    itemContentColor = AppTheme.colors.contentPrimary,
                    items = list
                ) {
                    when (it) {
                        0 -> {
                            updateMainScreenState(
                                mainScreenState.copy(
                                    radioBtControl = true,
                                    sourceManagement = false
                                )
                            )
                            scope.launch(Dispatchers.IO) {
                                dataStore.saveValue(
                                    GeneralPrefs.RADIO_BT_CONTROL,
                                    true
                                )
                                dataStore.saveValue(
                                    GeneralPrefs.LEGACY_SOURCE_MANAGEMENT,
                                    false
                                )
                            }
                        }

                        1 -> {
                            updateMainScreenState(
                                mainScreenState.copy(
                                    radioBtControl = false,
                                    sourceManagement = true
                                )
                            )
                            scope.launch(Dispatchers.IO) {
                                dataStore.saveValue(
                                    GeneralPrefs.RADIO_BT_CONTROL,
                                    false
                                )
                                dataStore.saveValue(
                                    GeneralPrefs.LEGACY_SOURCE_MANAGEMENT,
                                    true
                                )
                            }
                        }
                    }
                }
            }
        }

        Spacer(Modifier.height(18.dp))

        // disable when AC
        RenderSwitcher(
            modifier = Modifier.padding(horizontal = 20.dp),
            title = stringResource(R.string.disable_on_climate),
            subtitle = stringResource(R.string.disable_on_climate_desc),
            value = mainScreenState.disableOnClimate,
            enable = mainScreenState.mediaControlEnabled,
            groupDivider = false,
            onChange = {
                updateMainScreenState(mainScreenState.copy(disableOnClimate = it))
                scope.launch(Dispatchers.IO) {
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
            value = mainScreenState.mediaDataTranslator,
            enable = true,
            groupDivider = false,
            onChange = {
                if (!isNotificationServiceEnabled) {
                    context.requestNotificationServicePermission()
                    return@RenderSwitcher
                }

                updateMainScreenState(mainScreenState.copy(mediaDataTranslator = it))
                scope.launch(Dispatchers.IO) {
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
                    updateMainScreenState(mainScreenState.copy(rememberDriveMode = true))
                    scope.launch(Dispatchers.IO) {
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
            value = mainScreenState.rememberDriveMode,
            groupDivider = false,
            onChange = { newValue ->
                if (newValue && (isDebugMInstalled || isMConfigMInstalled)) {
                    restoreDMWarningDialog = true
                } else {
                    updateMainScreenState(mainScreenState.copy(rememberDriveMode = newValue))
                    scope.launch(Dispatchers.IO) {
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
                driveMode = mainScreenState.targetRecoveryDriveMode,
                onTargetDriveModeChanged = { targetMode ->
                    updateMainScreenState(
                        mainScreenState.copy(
                            targetRecoveryDriveMode = targetMode
                        )
                    )
                    scope.launch(Dispatchers.IO) {
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
            visible = mainScreenState.rememberDriveMode,
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
                val targetRecoveryDM = mainScreenState.targetRecoveryDriveMode
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
            value = mainScreenState.driveModeOverlay,
            groupDivider = false,
            onChange = {
                if (context.requireDisplayOverlay()) {
                    updateMainScreenState(mainScreenState.copy(driveModeOverlay = it))
                    scope.launch(Dispatchers.IO) {
                        dataStore.saveValue(
                            GeneralPrefs.DRIVE_MODE_OVERLAY,
                            it
                        )
                    }
                }
            }
        )

        AnimatedVisibility(
            visible = mainScreenState.driveModeOverlay,
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
                    text = "$overlaySizeTitle: " + (mainScreenState.driveModeOverlayScale + uiScale).asOneDecimalX(),
                    color = AppTheme.colors.contentPrimary
                )
                ValueSlider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 36.dp),
                    value = mainScreenState.driveModeOverlayScale,
                    valueRange = -1.2f..1.7f,
                    onValueChange = { newValue ->
                        updateMainScreenState(mainScreenState.copy(driveModeOverlayScale = newValue))
                        scope.launch(Dispatchers.IO) {
                            dataStore.saveValue(
                                GeneralPrefs.DM_OVERLAY_SCALE,
                                newValue
                            )
                        }
                    },
                    enabled = true,
                    defaultMark = MainScreenState.Default.driveModeOverlayScale,
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
                            mainScreenState.driveModeOverlayOffset.toOverlayPercentString(
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
                    value = mainScreenState.driveModeOverlayOffset,
                    valueRange = -minOverlayOffset..maxOverlayOffset,
                    onValueChange = { newValue ->
                        updateMainScreenState(mainScreenState.copy(driveModeOverlayOffset = newValue))
                        scope.launch(Dispatchers.IO) {
                            dataStore.saveValue(
                                GeneralPrefs.DM_OVERLAY_OFFSET,
                                newValue
                            )
                        }
                    },
                    enabled = true,
                    defaultMark = MainScreenState.Default.driveModeOverlayOffset,
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
                        openConfigurator(false)
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
                        openConfigurator(true)
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
            openSystemParams()
        }

        Spacer(Modifier.height(24.dp))
        RenderGroupDivider()
        Spacer(Modifier.height(24.dp))

        RenderDocumentationBlock()

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
                title = when (mainScreenState.adbHelperPort) {
                    7777, 5555 -> ""
                    else -> mainScreenState.adbHelperPort.toString()
                },
                uiScaleState = uiScale,
                onFinishInput = { newPort ->
                    updateMainScreenState(
                        mainScreenState.copy(
                            adbHelperPort = newPort,
                            enableAdbHelper = true
                        )
                    )
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
        val list = remember(mainScreenState.adbHelperPort) {
            listOf(
                HugeTogglerItem(text = "Atlas", subtitle = "5555"),
                HugeTogglerItem(text = "Preface", subtitle = "7777"),
                HugeTogglerItem(
                    text = "Custom",
                    subtitle = when {
                        mainScreenState.adbHelperPort != 7777 &&
                                mainScreenState.adbHelperPort != 5555 &&
                                mainScreenState.adbHelperPort != -1 -> mainScreenState.adbHelperPort.toString()

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
            if (mainScreenState.adbHelperPort != -1) {
                HugeSegmentToggler(
                    modifier = Modifier
                        .fillMaxWidth(),
                    selectedIndex = when {
                        mainScreenState.adbHelperPort == 5555 && mainScreenState.enableAdbHelper -> 0
                        mainScreenState.adbHelperPort == 7777 && mainScreenState.enableAdbHelper -> 1
                        mainScreenState.adbHelperPort > 0 && mainScreenState.enableAdbHelper -> 2
                        !mainScreenState.enableAdbHelper -> 3
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
                        0 -> {
                            updateMainScreenState(
                                mainScreenState.copy(
                                    adbHelperPort = 5555,
                                    enableAdbHelper = true
                                )
                            )
                            scope.launch(Dispatchers.IO) {
                                dataStore.saveValue(
                                    GeneralPrefs.ADB_HELPER_PORT,
                                    5555
                                )
                                dataStore.saveValue(
                                    GeneralPrefs.ENABLE_ADB_HELPER,
                                    true
                                )
                            }
                        }

                        1 -> {
                            updateMainScreenState(
                                mainScreenState.copy(
                                    adbHelperPort = 7777,
                                    enableAdbHelper = true
                                )
                            )
                            scope.launch(Dispatchers.IO) {
                                dataStore.saveValue(
                                    GeneralPrefs.ADB_HELPER_PORT,
                                    7777
                                )
                                dataStore.saveValue(
                                    GeneralPrefs.ENABLE_ADB_HELPER,
                                    true
                                )
                            }
                        }

                        2 -> inputPortDialog = true

                        3 -> {
                            updateMainScreenState(
                                mainScreenState.copy(
                                    enableAdbHelper = false
                                )
                            )
                            scope.launch(Dispatchers.IO) {
                                dataStore.saveValue(
                                    GeneralPrefs.ENABLE_ADB_HELPER,
                                    false
                                )
                            }
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
                    updateUiScale(newValue)
                    scope.launch(Dispatchers.IO) {
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
            value = mainScreenState.fullBroadcast,
            enable = true,
            groupDivider = false,
            onChange = {
                updateMainScreenState(mainScreenState.copy(fullBroadcast = it))
                scope.launch(Dispatchers.IO) {
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
            value = mainScreenState.trackKeyEvents,
            enable = true,
            groupDivider = false,
            onChange = {
                updateMainScreenState(mainScreenState.copy(trackKeyEvents = it))
                scope.launch(Dispatchers.IO) {
                    dataStore.saveValue(GeneralPrefs.TRACK_KEY_EVENTS, it)
                }
            }
        )

        // debug options
        RenderDebugSettingsBlock(
            isDebugMode = mainScreenState.isDebugMode,
            deepLogs = mainScreenState.deepLogs,
            onSaveBooleanPref = { pref, value ->
                updateMainScreenState(
                    when (pref) {
                        GeneralPrefs.DEBUG_MODE -> {
                            mainScreenState.copy(isDebugMode = value)
                        }

                        GeneralPrefs.DEEP_LOGS -> {
                            mainScreenState.copy(deepLogs = value)
                        }

                        else -> mainScreenState
                    }
                )
                scope.launch(Dispatchers.IO) { dataStore.saveValue(pref, value) }
            }
        )

        Spacer(Modifier.height(12.dp))

        // suppression mode
        RenderSwitcher(
            modifier = Modifier.padding(horizontal = 20.dp),
            title = stringResource(R.string.suppression_mode),
            subtitle = stringResource(R.string.suppression_mode_desc),
            value = mainScreenState.suppressionMode,
            enable = true,
            groupDivider = false,
            onChange = { newValue ->
                if (newValue) {
                    showActionBindLockConfirmDialog()
                } else {
                    updateMainScreenState(mainScreenState.copy(suppressionMode = false))
                    scope.launch(Dispatchers.IO) {
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

        Spacer(Modifier.height(12.dp))

        var exportSettingsDialog by remember { mutableStateOf(false) }
        if (exportSettingsDialog) {
            RenderSettingsExportDialog(uiScale, canAccessibility) {
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

    private suspend fun readTextFromUri(uri: Uri): String? = withContext(Dispatchers.IO) {
        try {
            contentResolver.openInputStream(uri)
                ?.bufferedReader()
                .use { it?.readText() }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    private fun Int.toDecimalSecondString(digits: Int = 2) =
        String.format(Locale.US, "%.${digits}f сек", this / 1000.0)

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

    private suspend fun buildDisplayKeyBinds(json: String, context: Context): List<DisplayKeyBind> {
        val apps = keyBindStorage.parseBinds(json)

        return apps.map { (bindName, action) ->
            DisplayKeyBind(
                bindName = bindName,
                keyNames = extractInts(bindName).map { keyCodeMap[it] ?: "" },
                action = action.action.toDisplayKeyAction(),
                type = bindName.toBindType(context),
                app = resolveBindApp(action.action, action.value),
                link = resolveBindLink(action.action, action.value),
                phone = resolveBindPhone(action.action, action.value),
                driveModes = resolveBindDriveModes(action.action, action.value),
                lampModes = resolveBindLampModes(context, action.action, action.value),
                audioSources = resolveBindAudioSources(context, action.action, action.value)
            )
        }.filter { it.keyNames.isNotEmpty() }
    }

    private fun String.toBindType(context: Context): String {
        return when {
            startsWith("sc") -> context.getString(R.string.kbd_pattern_short2)
            startsWith("ml") -> context.getString(R.string.kbd_pattern_multi2)
            startsWith("lp") -> context.getString(R.string.kbd_pattern_long2)
            startsWith("dc") -> context.getString(R.string.kbd_pattern_double2)
            else -> ""
        }
    }

    private suspend fun resolveBindApp(action: KeyBindAction, value: String) =
        if (action == KeyBindAction.LAUNCH_APP) {
            systemApps.getApps(APP_ICON_ROUND, APP_ICON_QUALITY, value)
                .toAllDisplay()
                .firstOrNull()
        } else null

    private suspend fun resolveBindLink(action: KeyBindAction, value: String): DeviceLinkInfo? {
        if (action != KeyBindAction.LAUNCH_LINK) return null

        return try {
            val intent = Intent.parseUri(
                value,
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
    }

    private fun resolveBindPhone(action: KeyBindAction, value: String): String? {
        return if (action == KeyBindAction.PHONE_CALL) {
            value
        } else null
    }

    private fun resolveBindDriveModes(action: KeyBindAction, value: String): String? {
        return when (action) {
            KeyBindAction.TOGGLE_DM -> value.toIntOrNull()
                ?.getDisplayDriveModeName() ?: ""

            KeyBindAction.CAROUSEL_DM -> value
                .split("|")
                .map { it.toIntOrNull() ?: 0 }
                .filter { it != 0 }
                .joinToString(", ") { it.getDisplayDriveModeName() }

            else -> null
        }
    }

    private fun resolveBindLampModes(
        context: Context,
        action: KeyBindAction,
        value: String
    ): String? {
        if (action != KeyBindAction.CAROUSEL_LAMP) return null

        val names = DISPLAY_LAMP_MODES.associate {
            it.id to context.getString(it.displayTitle)
        }

        return value
            .split("|")
            .map { it.toIntOrNull() ?: -1 }
            .filter { it != -1 }
            .joinToString(", ") { names.getOrDefault(it, "") }
    }

    private fun resolveBindAudioSources(
        context: Context,
        action: KeyBindAction,
        value: String
    ): String? {
        if (action != KeyBindAction.CAROUSEL_AUDIO_SOURCE) return null

        val keyToLabel = DISPLAY_AUDIO_SOURCES.associate {
            it.key to context.getString(it.displayTitle)
        }
        return value
            .split("|")
            .map { it.trim() }
            .filter { it.isNotEmpty() }
            .joinToString(", ") { keyToLabel[it] ?: it }
    }

    private fun KeyBindAction.toDisplayKeyAction() = when (this) {
        KeyBindAction.LAUNCH_APP -> DisplayKeyAction.LAUNCH_APP
        KeyBindAction.LAUNCH_LINK -> DisplayKeyAction.LAUNCH_LINK
        KeyBindAction.APP_LAUNCHER -> DisplayKeyAction.APP_LAUNCHER
        KeyBindAction.TOGGLE_DM -> DisplayKeyAction.TOGGLE_DM
        KeyBindAction.CAROUSEL_DM -> DisplayKeyAction.CAROUSEL_DM
        KeyBindAction.PHONE_CALL -> DisplayKeyAction.PHONE_CALL
        KeyBindAction.CAMERAS_360 -> DisplayKeyAction.CAMERAS_360
        KeyBindAction.CAROUSEL_LAMP -> DisplayKeyAction.CAROUSEL_LAMP
        KeyBindAction.CAROUSEL_AUDIO_SOURCE -> DisplayKeyAction.CAROUSEL_AUDIO_SOURCE
        KeyBindAction.TASK_MANAGER -> DisplayKeyAction.TASK_MANAGER
        KeyBindAction.ANDROID_BACK -> DisplayKeyAction.ANDROID_BACK
        KeyBindAction.ANDROID_HOME -> DisplayKeyAction.ANDROID_HOME
        KeyBindAction.NAVIGATE_TO_PAST_APP -> DisplayKeyAction.NAVIGATE_TO_PAST_APP
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
        GeneralPrefs.LEGACY_SOURCE_MANAGEMENT,
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

    val defaults: List<Any?> = MainScreenState.Default.toSettingsRow()
}
