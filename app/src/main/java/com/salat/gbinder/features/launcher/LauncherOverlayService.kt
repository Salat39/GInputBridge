package com.salat.gbinder.features.launcher

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.content.pm.ServiceInfo
import android.graphics.Bitmap
import android.graphics.PixelFormat
import android.os.Build
import android.os.IBinder
import android.provider.Settings
import android.view.WindowManager
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.LocalOverscrollFactory
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Build
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.NotificationCompat
import androidx.core.app.ServiceCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.setViewTreeLifecycleOwner
import androidx.savedstate.setViewTreeSavedStateRegistryOwner
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import coil.size.Precision
import com.salat.gbinder.BuildConfig
import com.salat.gbinder.R
import com.salat.gbinder.adb.data.entity.AdbConnectionState
import com.salat.gbinder.adb.domain.repository.AdbRepository
import com.salat.gbinder.components.ComposeWindowLifecycleOwner
import com.salat.gbinder.components.launchApp
import com.salat.gbinder.components.launchStringIntent
import com.salat.gbinder.components.openAccessibilitySettings
import com.salat.gbinder.components.openAppNotifications
import com.salat.gbinder.components.openAppSystemSettings
import com.salat.gbinder.components.requestUninstall
import com.salat.gbinder.datastore.LauncherPrefs
import com.salat.gbinder.datastore.LauncherStorageRepository
import com.salat.gbinder.entity.AllAppMenuItem
import com.salat.gbinder.entity.AppLaunchedState
import com.salat.gbinder.entity.DisplayLauncherApp
import com.salat.gbinder.entity.DisplayLauncherConfig
import com.salat.gbinder.entity.DisplayLauncherItem
import com.salat.gbinder.entity.DisplayLauncherItemType
import com.salat.gbinder.entity.LauncherScreen
import com.salat.gbinder.entity.LauncherTabs
import com.salat.gbinder.entity.MyAppMenuItem
import com.salat.gbinder.entity.biggestId
import com.salat.gbinder.entity.biggestOrder
import com.salat.gbinder.mappers.isPhoneCallIntent
import com.salat.gbinder.mappers.isSplitIntent
import com.salat.gbinder.statekeeper.domain.entity.LauncherActivitySignal
import com.salat.gbinder.statekeeper.domain.entity.LauncherOverlaySignal
import com.salat.gbinder.statekeeper.domain.repository.StateKeeperRepository
import com.salat.gbinder.stopLauncherOverlay
import com.salat.gbinder.ui.BottomShadow
import com.salat.gbinder.ui.OptionsMenuItem
import com.salat.gbinder.ui.RenderOptionsMenuItem
import com.salat.gbinder.ui.TopShadow
import com.salat.gbinder.ui.clickableNoRipple
import com.salat.gbinder.ui.mirror
import com.salat.gbinder.ui.theme.AppTheme
import com.salat.gbinder.util.rememberTimeLockedBoolean
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.util.concurrent.atomic.AtomicBoolean
import javax.inject.Inject
import kotlin.math.roundToInt

@AndroidEntryPoint
class LauncherOverlayService : Service() {

    companion object {
        private const val CHANNEL_ID = "lcr_overlay_service_channel"
        private const val LAUNCHER_OVERLAY = 2007
        private const val SHORT_TOOLBAR_THRESHOLD = 150

        @Volatile
        @JvmField
        var isAlive: Boolean = false

        @Volatile
        @JvmField
        var isStarting: Boolean = false
    }

    private val serviceScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    @Inject
    lateinit var stateKeeper: StateKeeperRepository

    @Inject
    lateinit var storage: LauncherStorageRepository

    @Inject
    lateinit var data: LauncherDataRepository

    @Inject
    lateinit var adb: AdbRepository

    private lateinit var windowManager: WindowManager

    private var launcherContainer: ComposeView? = null
    private var launcherWindowParams: WindowManager.LayoutParams? = null
    private lateinit var composeLifecycleOwner: ComposeWindowLifecycleOwner

    private val saveQueue = Channel<List<DisplayLauncherItem>>(capacity = Channel.CONFLATED)

    // Ensure we close exactly once
    private val isClosing = AtomicBoolean(false)

    // Add this property in the service
    private var restoreAfterExternalDialog = false
    private var skipCloseOnNextOnPause = false

    @SuppressLint("ObsoleteSdkInt")
    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "App Launcher Overlay",
                NotificationManager.IMPORTANCE_LOW
            ).apply {
                description = "Show App Launcher menu"
            }
            (getSystemService(NOTIFICATION_SERVICE) as NotificationManager)
                .createNotificationChannel(channel)
        }
    }

    private fun buildNotification() = NotificationCompat.Builder(this, CHANNEL_ID)
        .setContentTitle("App Launcher Overlay")
        .setContentText("Show App Launcher menu")
        .setSmallIcon(R.drawable.ic_launcher_logo) // your existing icon
        .setPriority(NotificationCompat.PRIORITY_MIN)
        .setOngoing(true) // Foreground service best practice
        .build()

    private fun buildMinimalNotification() = NotificationCompat.Builder(this, CHANNEL_ID)
        .setContentTitle("App Launcher Overlay")
        .setSmallIcon(R.drawable.ic_launcher_logo)
        .setPriority(NotificationCompat.PRIORITY_MIN)
        .setOngoing(true)
        .build()

    @Suppress("DEPRECATION")
    @SuppressLint("ObsoleteSdkInt")
    private fun tryEnterForeground(): Boolean {
        var ok = false
        runCatching {
            ServiceCompat.startForeground(
                this,
                LAUNCHER_OVERLAY,
                buildNotification(),
                ServiceInfo.FOREGROUND_SERVICE_TYPE_MANIFEST
            )
            ok = true
        }.onFailure { e1 ->
            Timber.e(e1, "[LAUNCHER] startForeground failed, fallback")
            runCatching {
                startForeground(LAUNCHER_OVERLAY, buildMinimalNotification())
                ok = true
            }.onFailure { e2 ->
                Timber.e(e2, "[LAUNCHER] minimal startForeground failed")
            }
        }
        return ok
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (!tryEnterForeground()) {
            isAlive = false
            isStarting = false
            stopSelf()
            return START_NOT_STICKY
        }
        isAlive = true
        isStarting = false
        return START_STICKY
    }

    @OptIn(FlowPreview::class)
    @Suppress("DEPRECATION")
    @SuppressLint("ObsoleteSdkInt")
    override fun onCreate() {
        super.onCreate()

        runCatching { createNotificationChannel() }
        val started = tryEnterForeground()
        isStarting = false
        if (!started) {
            isAlive = false
            stopSelf()
            return
        }
        isAlive = true

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(this)) {
            isAlive = false
            stopSelf()
            return
        }
        windowManager = getSystemService(WINDOW_SERVICE) as WindowManager


        // Create Compose lifecycle owner
        val myOwner = ComposeWindowLifecycleOwner().apply {
            performRestore(null)
            setCurrentState(Lifecycle.State.RESUMED)
        }
        composeLifecycleOwner = myOwner // Set owner for menu overlay

        if (launcherContainer != null) return

        // Build ui
        setupLauncherOverlay()
        serviceScope.launch {
            for (payload in saveQueue) {
                runCatching { data.saveMyApps(payload) }
                    .onFailure { Timber.e(it, "[LAUNCHER] saveMyApps failed") }
            }
        }

        // Set state + Launch activity if not launched yet
        stateKeeper.setLauncherOverlayEnabled(true)
        // Activity not launched -> launch now
        if (!stateKeeper.launcherActivityEnabled.value) {
            val intent = Intent(this, LauncherEntryActivity::class.java)
                .addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
        Timber.d("[LAUNCHER] onCreated")
    }

    private fun setupLauncherOverlay() {
        launcherContainer = ComposeView(this).apply {
            setViewTreeLifecycleOwner(composeLifecycleOwner)
            setViewTreeSavedStateRegistryOwner(composeLifecycleOwner)
            setContent {
                val items by data.myAppsItems.collectAsStateWithLifecycle()
                val config by data.settingsConfig.collectAsStateWithLifecycle()

                val itm = items
                val cnf = config
                if (itm != null && cnf != null) {
                    val isDarkTheme = if (cnf.autoLightTheme) {
                        remember(cnf.autoLightThemeStart, cnf.autoLightThemeEnd) {
                            !isNowWithinHours(cnf.autoLightThemeStart, cnf.autoLightThemeEnd)
                        }
                    } else !cnf.lightTheme

                    AppTheme(darkTheme = isDarkTheme) {
                        RenderLauncher(itm, cnf)
                    }
                }
            }
        }

        launcherWindowParams = WindowManager.LayoutParams(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or
                    WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL or
                    WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN,
            PixelFormat.TRANSLUCENT
        )

        runCatching { windowManager.addView(launcherContainer, launcherWindowParams) }
            .onFailure { Timber.e(it) }
    }

    @Composable
    private fun RenderLauncher(
        items: List<DisplayLauncherItem>,
        config: DisplayLauncherConfig
    ) = Box(
        modifier = Modifier
            .fillMaxSize()
            .then(
                if (!config.windowMode) {
                    Modifier.background(
                        if (config.windowAlpha != 1f) {
                            AppTheme.colors.launcherBackground.copy(config.windowAlpha)
                        } else AppTheme.colors.launcherBackground
                    )
                } else Modifier
            )
            .clickableNoRipple(onClick = ::hideLauncherOverlay),
        contentAlignment = Alignment.Center
    ) {
        val density = LocalDensity.current
        val scaledDensity = remember(density) {
            Density(
                density.density * config.uiScale,
                //density.fontScale * config.uiScale
            )
        }
        var screen by remember { mutableStateOf(LauncherScreen.MAIN) }

        // Add new element menu
        var addMenu by remember { mutableStateOf<IntOffset?>(null) }
        // All apps context menu
        var lockMode by remember { mutableStateOf(true) }
        val pagerState = rememberPagerState(
            pageCount = { LauncherTabs.entries.size },
            initialPage = config.defaultTab
        )

        var allAppItemMenu by remember { mutableStateOf<AllAppMenuItem?>(null) }
        fun onOpenAllAppMenu(item: DisplayLauncherApp, offset: Offset) =
            serviceScope.launch(Dispatchers.Default) {
                data.myAppsItems.value?.let { myApps ->
                    val inMyApps = myApps.find {
                        it.packageName == item.packageName && it.type == DisplayLauncherItemType.APP
                    }

                    allAppItemMenu = AllAppMenuItem(
                        app = item,
                        offset = IntOffset(
                            x = offset.x.toInt(),
                            y = offset.y.toInt()
                        ),
                        inMyApps = inMyApps != null,
                        launchedStatus = if (!config.enableAdbHelper) {
                            AppLaunchedState.NO_DETECT
                        } else if (adb.isAppLaunched(item.packageName)) {
                            AppLaunchedState.LAUNCHED
                        } else AppLaunchedState.NO
                    )
                }
            }

        var myAppItemMenu by remember { mutableStateOf<MyAppMenuItem?>(null) }
        fun onOpenMyAppMenu(item: DisplayLauncherItem, offset: Offset) =
            serviceScope.launch(Dispatchers.Default) {
                data.allApps.value.let { apps ->
                    myAppItemMenu = MyAppMenuItem(
                        app = item,
                        offset = IntOffset(
                            x = offset.x.toInt(),
                            y = offset.y.toInt()
                        ),
                        appData = apps.find { it.packageName == item.packageName },
                        launchedStatus = if (!config.enableAdbHelper) {
                            AppLaunchedState.NO_DETECT
                        } else if (adb.isAppLaunched(item.packageName)) {
                            AppLaunchedState.LAUNCHED
                        } else AppLaunchedState.NO
                    )
                }
            }

        // Scale calc
        val borderRadius = remember { (16f * config.uiScale).roundToInt() }
        val elevation = remember { (4f * config.uiScale).roundToInt() }
        val borderWidth = remember { (2f * config.uiScale).roundToInt() }

        val border = remember { RoundedCornerShape(borderRadius.dp) }
        val windowFrame = if (config.windowShowFrame) {
            Modifier
                .border(
                    shape = border,
                    width = borderWidth.dp,
                    color = AppTheme.colors.contentAccent
                )
                .padding(1.dp) // TODO border compensator
        } else Modifier
        Box(
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding()
                .then(
                    if (config.windowMode) {
                        Modifier
                            .padding(
                                vertical = config.windowVerticalSpace.dp,
                                horizontal = config.windowHorizontalSpace.dp
                            )
                            .shadow(elevation.dp, shape = border)
                            .then(windowFrame)
                            .background(
                                if (config.windowAlpha != 1f) {
                                    AppTheme.colors.launcherBackground.copy(config.windowAlpha)
                                } else AppTheme.colors.launcherBackground,
                                border
                            )
                    } else Modifier
                )
                .clickableNoRipple(onClick = {})
        ) {
            CompositionLocalProvider(LocalDensity provides scaledDensity) {
                Column {
                    // Activity signals handler
                    LaunchedEffect(Unit) {
                        stateKeeper.launcherActivitySignalFlow.collect {
                            when (it) {
                                LauncherActivitySignal.OnBackPressed -> {
                                    if (allAppItemMenu != null || myAppItemMenu != null || addMenu != null) {
                                        allAppItemMenu = null
                                        myAppItemMenu = null
                                        addMenu = null
                                        return@collect
                                    }
                                    if (screen in setOf(
                                            LauncherScreen.SETTINGS,
                                            LauncherScreen.ADD_APPS
                                        )
                                    ) {
                                        screen = LauncherScreen.MAIN
                                        return@collect
                                    }
                                    if (pagerState.currentPage == 0 && !lockMode) {
                                        lockMode = true
                                        return@collect
                                    }
                                    hideLauncherOverlay()
                                }

                                LauncherActivitySignal.OnPause -> {
                                    if (skipCloseOnNextOnPause) {
                                        skipCloseOnNextOnPause = false
                                    } else {
                                        hideLauncherOverlay()
                                    }
                                }

                                LauncherActivitySignal.OnResume -> restoreOverlayIfNeeded()

                                is LauncherActivitySignal.ApplyGroupDivider -> {
                                    if (it.id == -1L) {
                                        createNewGroupDivider(it.title)
                                    } else {
                                        renameMyAppItem(it.id, it.title)
                                    }
                                    restoreOverlayIfNeeded()
                                }

                                is LauncherActivitySignal.CreateShortcut -> {
                                    createNewShortcut(
                                        title = it.title,
                                        packageName = it.packageName,
                                        intent = it.intent,
                                        bitmap = it.bitmap
                                    )
                                    restoreOverlayIfNeeded()
                                }

                                is LauncherActivitySignal.ApplyNewIcon ->
                                    data.applyIcon(it.id, it.packageName, it.uri)

                                is LauncherActivitySignal.CancelIcon -> {
                                    data.clearIcon(it.id, it.packageName)
                                    restoreOverlayIfNeeded()
                                }

                                is LauncherActivitySignal.ApplyFreezeApp -> {
                                    togglePackageFreeze(it.packageName, isFrozen = false)
                                    restoreOverlayIfNeeded()
                                }
                            }
                        }
                    }

                    // Toolbar
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(LAUNCHER_TOOLBAR_HEIGHT.dp)
                            .then(if (!config.windowMode) Modifier.padding(end = 36.dp) else Modifier),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        when (screen) {
                            LauncherScreen.MAIN -> RenderLauncherMainToolbar(
                                isLock = lockMode,
                                isShort = config.windowHorizontalSpace > SHORT_TOOLBAR_THRESHOLD,
                                pagerState = pagerState,
                                onAddClick = { offset ->
                                    addMenu = IntOffset(
                                        x = offset.x.toInt(),
                                        y = offset.y.toInt()
                                    )
                                },
                                onToggleLock = { lockMode = !lockMode },
                                onSettingsClick = { screen = LauncherScreen.SETTINGS },
                                onCloseClick = { hideLauncherOverlay() }
                            )

                            LauncherScreen.SETTINGS -> RenderLauncherSettingsToolbar {
                                screen = LauncherScreen.MAIN
                            }

                            LauncherScreen.ADD_APPS -> RenderLauncherAddAppsToolbar {
                                screen = LauncherScreen.MAIN
                            }
                        }
                    }

                    // Toolbar divider
                    Spacer(
                        Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(AppTheme.colors.launcherSurface1.copy(.5f))
                    )

                    // Content
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    ) {
                        // keep scroll state when user navigate to other screens
                        val myAppsGridState: LazyGridState = rememberLazyGridState()
                        val allAppsGridState: LazyGridState = rememberLazyGridState()

                        when (screen) {
                            LauncherScreen.MAIN -> CompositionLocalProvider(LocalOverscrollFactory provides null) {
                                HorizontalPager(
                                    state = pagerState,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .weight(1f)
                                        .animateContentSize(animationSpec = spring(stiffness = 1000f))
                                ) { index ->
                                    Column(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .weight(1f),
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.Center
                                    ) {
                                        when (LauncherTabs.entries[index]) {

                                            LauncherTabs.MyApss -> {
                                                if (items.isEmpty()) {
                                                    Text(
                                                        modifier = Modifier
                                                            .padding(horizontal = 100.dp),
                                                        text = stringResource(R.string.empty_grid_message),
                                                        style = AppTheme.typography.overlayLauncherToolbarTitle,
                                                        textAlign = TextAlign.Center,
                                                        color = AppTheme.colors.contentPrimary.copy(
                                                            .6f
                                                        )
                                                    )
                                                } else {
                                                    var uiItems by remember {
                                                        mutableStateOf(items)
                                                    }
                                                    LaunchedEffect(items) {
                                                        uiItems = items
                                                    }

                                                    RenderLauncherMyApps(
                                                        items = uiItems,
                                                        config = config,
                                                        lockMode = lockMode,
                                                        gridState = myAppsGridState,
                                                        onLongClick = { item, offset ->
                                                            when (item.type) {
                                                                DisplayLauncherItemType.GROUP -> {
                                                                    minimizeOverlayForSystemDialog()
                                                                    stateKeeper.sendLauncherOverlaySignal(
                                                                        LauncherOverlaySignal.ChangeGroupName(
                                                                            id = item.id,
                                                                            title = item.title
                                                                        )
                                                                    )
                                                                }

                                                                else -> onOpenMyAppMenu(
                                                                    item = item,
                                                                    offset = offset
                                                                )
                                                            }
                                                        },
                                                        onHideApp = { item ->
                                                            removeItemById(item.id)
                                                        },
                                                        onCancelLauncher = ::hideLauncherOverlay,
                                                        onMoveItem = { fromIndex, toIndex ->
                                                            uiItems = uiItems
                                                                .toMutableList()
                                                                .apply {
                                                                    add(
                                                                        toIndex,
                                                                        removeAt(fromIndex)
                                                                    )
                                                                }
                                                        },
                                                        onReorderDrop = {
                                                            serviceScope.launch {
                                                                var index = 0
                                                                val snapshot = uiItems.map { item ->
                                                                    index++
                                                                    item.copy(order = index)
                                                                }
                                                                saveQueue.trySend(snapshot)
                                                            }
                                                        }
                                                    )
                                                }
                                            }

                                            LauncherTabs.AllApps -> {
                                                val list by data.allApps.collectAsStateWithLifecycle()

                                                // Prewarm
                                                // PrewarmIcons(list, config)

                                                RenderLauncherAllApps(
                                                    items = list,
                                                    config = config,
                                                    gridState = allAppsGridState,
                                                    onLongClick = ::onOpenAllAppMenu,
                                                    onCancelLauncher = ::hideLauncherOverlay
                                                )
                                            }
                                        }
                                    }
                                }

                                // Recents
                                if (config.recentsEnable) {
                                    val context = LocalContext.current
                                    RenderRecents(
                                        config = config,
                                        onClick = { app ->
                                            Timber.d("[LAUNCHER] open ${app.id}")
                                            context.launchApp(app.packageName, app.launcherActivity)
                                            hideLauncherOverlay()
                                        },
                                        onLongClick = ::onOpenAllAppMenu
                                    )
                                }
                            }

                            LauncherScreen.SETTINGS -> {
                                val list by data.allApps.collectAsStateWithLifecycle()

                                RenderLauncherSettings(
                                    items = list,
                                    config = config,
                                    storage = storage
                                )
                            }

                            LauncherScreen.ADD_APPS -> {
                                val list by data.allApps.collectAsStateWithLifecycle()

                                RenderLauncherAddApps(
                                    allApps = list,
                                    myApps = items,
                                    config = config
                                ) { newMyApps ->
                                    serviceScope.launch {
                                        // Save new dataset
                                        data.saveMyApps(newMyApps)
                                        // Back to main
                                        screen = LauncherScreen.MAIN
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        // Add menu popup
        addMenu?.let { offset ->
            OverlayPopupMenu(
                offset = offset,
                horizontalOffset = 0.dp,
                verticalOffset = if (BuildConfig.DEBUG) -(42).dp else (-80).dp,
                uiScale = config.uiScale,
                onDismiss = { addMenu = null }
            ) {
                OptionsMenuItem(
                    icon = R.drawable.ic_add_apps,
                    title = stringResource(R.string.apps),
                    textColor = Color.White,
                    scale = .97f
                ) {
                    screen = LauncherScreen.ADD_APPS
                    addMenu = null
                }
                OptionsMenuItem(
                    icon = R.drawable.ic_divider,
                    title = stringResource(R.string.divider),
                    textColor = Color.White,
                    scale = .97f
                ) {
                    minimizeOverlayForSystemDialog()
                    stateKeeper.sendLauncherOverlaySignal(
                        LauncherOverlaySignal.ChangeGroupName(
                            id = -1L,
                            title = ""
                        )
                    )
                    addMenu = null
                }
                OptionsMenuItem(
                    icon = R.drawable.ic_link,
                    title = stringResource(R.string.shortcut),
                    textColor = Color.White,
                    scale = .97f
                ) {
                    skipCloseOnNextOnPause = true
                    minimizeOverlayForSystemDialog()
                    stateKeeper.sendLauncherOverlaySignal(
                        LauncherOverlaySignal.CreateShortcut
                    )
                    addMenu = null
                }
            }
        }

        // My apps menu popup
        myAppItemMenu?.let { item ->
            val context = LocalContext.current
            OverlayPopupMenu(
                offset = item.offset,
                onDismiss = { myAppItemMenu = null },
                uiScale = config.uiScale
            ) {
                if (item.app.iconRef == null) {
                    Box(
                        modifier = Modifier
                            .background(AppTheme.colors.surfaceMenuDivider)
                    ) {
                        Text(
                            modifier = Modifier
                                .padding(horizontal = 22.dp, vertical = 14.dp)
                                .widthIn(max = 280.dp),
                            text = stringResource(R.string.app_not_installed, item.app.packageName),
                            color = AppTheme.colors.deleteButton,
                            style = AppTheme.typography.overlayLauncherMenuTitle
                        )

                        BottomShadow(modifier = Modifier.align(Alignment.BottomCenter))
                    }
                } else {
                    RenderAppActionsMenu(
                        packageName = item.app.packageName,
                        isFrozen = item.app.isFrozen,
                        canUninstall = config.allowSystemAppUninstall || !item.app.isSystem,
                        enableAdbHelper = config.enableAdbHelper,
                        launchedStatus = item.launchedStatus,
                        onOpen = {
                            // TODO create a single source of truth
                            if (item.app.type == DisplayLauncherItemType.MACRO) {
                                context.launchStringIntent(item.app.data)
                            } else {
                                context.launchApp(item.app.packageName, item.app.launchActivity)
                            }
                            Timber.d("[LAUNCHER] open ${item.app.id}")
                            hideLauncherOverlay()
                        },
                        onForceStop = {
                            myAppItemMenu = myAppItemMenu?.copy(
                                launchedStatus = AppLaunchedState.NO
                            )
                        },
                        onToggleFreeze = if (item.app.type == DisplayLauncherItemType.APP) {
                            {
                                requestTogglePackageFreeze(
                                    packageName = item.app.packageName,
                                    isFrozen = item.app.isFrozen,
                                    isSystem = item.app.isSystem
                                )
                                myAppItemMenu = null
                            }
                        } else null
                    ) {
                        myAppItemMenu = null
                    }

                    RenderMenuDivider()
                }

                if (item.app.type == DisplayLauncherItemType.APP) {
                    item.appData?.let { appData ->
                        val showActivities = appData.availableActivity.isNotEmpty()
                        if (showActivities) {
                            RenderActivitiesMenu(
                                availableActivity = appData.availableActivity,
                                launcherActivity = appData.launcherActivity ?: "",
                                packageName = appData.packageName,
                            ) {
                                Timber.d("[LAUNCHER] open ${appData.id}")
                                myAppItemMenu = null
                            }
                        }
                    }
                }

                RenderAppLabelAndIconMenu(
                    packageName = item.app.packageName,
                    isFrozen = item.app.isFrozen,
                    enableAdbHelper = config.enableAdbHelper,
                    onRename = {
                        minimizeOverlayForSystemDialog()
                        stateKeeper.sendLauncherOverlaySignal(
                            LauncherOverlaySignal.ChangeGroupName(
                                id = item.app.id,
                                title = item.app.title
                            )
                        )
                        myAppItemMenu = null
                    },
                    onChangeIcon = {
                        skipCloseOnNextOnPause = true
                        minimizeOverlayForSystemDialog()
                        val action = LauncherOverlaySignal.ChangeAppIconById(
                            item.app.id,
                            item.app.customIcon != null
                        )
                        stateKeeper.sendLauncherOverlaySignal(action)
                        myAppItemMenu = null
                    },
                    onToggleFreeze = if (item.app.type == DisplayLauncherItemType.APP) {
                        {
                            requestTogglePackageFreeze(
                                packageName = item.app.packageName,
                                isFrozen = item.app.isFrozen,
                                isSystem = item.app.isSystem
                            )
                            myAppItemMenu = null
                        }
                    } else null
                )

                if (item.app.type == DisplayLauncherItemType.APP && item.app.iconRef != null) {
                    RenderMenuDivider()
                    RenderSystemActionsMenu(item.app.packageName) {
                        myAppItemMenu = null
                    }
                }
            }
        }

        // All apps menu popup
        allAppItemMenu?.let { item ->
            val context = LocalContext.current
            val allAppsList by data.allApps.collectAsStateWithLifecycle()
            val liveAllApp =
                allAppsList.find { it.packageName == item.app.packageName } ?: item.app
            OverlayPopupMenu(
                offset = item.offset,
                onDismiss = { allAppItemMenu = null },
                uiScale = config.uiScale
            ) {
                RenderAppActionsMenu(
                    packageName = item.app.packageName,
                    isFrozen = liveAllApp.isFrozen,
                    canUninstall = config.allowSystemAppUninstall || !item.app.isSystem,
                    enableAdbHelper = config.enableAdbHelper,
                    launchedStatus = item.launchedStatus,
                    onOpen = {
                        // TODO create a single source of truth
                        Timber.d("[LAUNCHER] open ${item.app.id}")
                        context.launchApp(item.app.packageName, item.app.launcherActivity)
                        hideLauncherOverlay()
                    },
                    onForceStop = {
                        allAppItemMenu = allAppItemMenu?.copy(
                            launchedStatus = AppLaunchedState.NO
                        )
                    },
                    onToggleFreeze = {
                        requestTogglePackageFreeze(
                            packageName = liveAllApp.packageName,
                            isFrozen = liveAllApp.isFrozen,
                            isSystem = liveAllApp.isSystem
                        )
                        allAppItemMenu = null
                    }
                ) {
                    allAppItemMenu = null
                }

                RenderMenuDivider()

                val showAddInMyApps = !item.inMyApps
                val showActivities = item.app.availableActivity.isNotEmpty()

                if (showAddInMyApps) {
                    val icon = rememberVectorPainter(image = Icons.Filled.AddCircle)
                    RenderOptionsMenuItem(
                        icon = icon,
                        title = stringResource(R.string.add_to_my_apps),
                        textColor = Color.White,
                        scale = .97f
                    ) {
                        item.app.createMyApp()
                        allAppItemMenu = null
                    }
                }

                // Activities
                if (showActivities) {
                    RenderActivitiesMenu(
                        availableActivity = item.app.availableActivity,
                        launcherActivity = item.app.launcherActivity ?: "",
                        packageName = item.app.packageName,
                    ) {
                        Timber.d("[LAUNCHER] open ${item.app.id}")
                        allAppItemMenu = null
                    }
                }

                RenderAppLabelAndIconMenu(
                    packageName = item.app.packageName,
                    isFrozen = liveAllApp.isFrozen,
                    enableAdbHelper = config.enableAdbHelper,
                    onChangeIcon = {
                        skipCloseOnNextOnPause = true
                        minimizeOverlayForSystemDialog()
                        val action = LauncherOverlaySignal.ChangeAppIconByPackage(
                            item.app.packageName,
                            liveAllApp.customIcon != null
                        )
                        stateKeeper.sendLauncherOverlaySignal(action)
                        allAppItemMenu = null
                    },
                    onToggleFreeze = {
                        requestTogglePackageFreeze(
                            packageName = liveAllApp.packageName,
                            isFrozen = liveAllApp.isFrozen,
                            isSystem = liveAllApp.isSystem
                        )
                        allAppItemMenu = null
                    }
                )

                if (showAddInMyApps || showActivities) RenderMenuDivider()

                RenderSystemActionsMenu(item.app.packageName) {
                    allAppItemMenu = null
                }
            }
        }
    }

    override fun onDestroy() {
        isAlive = false
        isStarting = false
        super.onDestroy()
        // Finish Compose lifecycle to avoid leaks
        runCatching {
            if (::composeLifecycleOwner.isInitialized) {
                composeLifecycleOwner.setCurrentState(Lifecycle.State.DESTROYED)
            }
        }
        serviceScope.cancel()
        stateKeeper.setLauncherOverlayEnabled(false)
        hideLauncherOverlay()
    }

    override fun onBind(intent: Intent?): IBinder? = null

    private fun requestTogglePackageFreeze(
        packageName: String,
        isFrozen: Boolean,
        isSystem: Boolean
    ) {
        if (packageName.isBlank()) return

        if (isFrozen) {
            togglePackageFreeze(packageName, isFrozen = true)
        } else {
            minimizeOverlayForSystemDialog()
            stateKeeper.sendLauncherOverlaySignal(
                LauncherOverlaySignal.ConfirmFreezeApp(
                    packageName = packageName,
                    isSystem = isSystem
                )
            )
        }
    }

    private fun togglePackageFreeze(packageName: String, isFrozen: Boolean) {
        if (packageName.isBlank()) return

        serviceScope.launch(Dispatchers.IO) {
            runCatching {
                if (isFrozen) {
                    adb.enablePackage(packageName)
                } else {
                    adb.disableUserPackage(packageName)
                }
            }.onFailure { Timber.e(it) }
        }
    }

    /* @Composable
    private fun PrewarmIcons(list: List<DisplayLauncherApp>, config: DisplayLauncherConfig) {
        val ctx = LocalContext.current
        val imageLoader = LocalContext.current.imageLoader
        val pxSize = with(LocalDensity.current) { config.iconSize.dp.roundToPx() }
        LaunchedEffect(Unit) {
            val prewarmCount = minOf(list.size, 50)
            repeat(prewarmCount) { i ->
                val ir = list[i].iconRef
                val stableKey =
                    "pkg:${ir.packageName}|res:${ir.resId}|dpi:${ir.densityDpi}|vc:${ir.versionCode}|w:${pxSize}|h:${pxSize}"
                imageLoader.enqueue(
                    Builder(ctx)
                        .data(ir)
                        .size(pxSize, pxSize)
                        .precision(coil.size.Precision.EXACT)
                        .allowHardware(true)
                        .memoryCacheKey(stableKey)
                        .placeholderMemoryCacheKey(stableKey)
                        .memoryCachePolicy(CachePolicy.ENABLED)
                        .diskCachePolicy(CachePolicy.DISABLED)
                        .build()
                )
            }
        }
    } */

    private fun removeItemById(id: Long) = serviceScope.launch(Dispatchers.Default) {
        data.myAppsItems.value?.let { myApps ->
            data.saveMyApps(
                myApps.filter { it.id != id }
            )
        }
    }

    private fun createNewGroupDivider(title: String) = serviceScope.launch(Dispatchers.Default) {
        data.myAppsItems.value?.let { myApps ->
            data.saveMyApps(
                myApps + DisplayLauncherItem(
                    type = DisplayLauncherItemType.GROUP,
                    id = myApps.biggestId + 1L,
                    order = myApps.biggestOrder + 1,
                    title = title,
                    iconRef = null,
                    customIcon = null,
                    packageName = "",
                    launchActivity = "",
                    data = "",
                    isCall = false,
                    isSplit = false,
                    isFrozen = false,
                    isSystem = false
                )
            )
        }
    }

    private fun createNewShortcut(
        title: String,
        packageName: String,
        intent: String,
        bitmap: Bitmap?
    ) = serviceScope.launch(Dispatchers.Default) {
        data.myAppsItems.value?.let { myApps ->
            val id = myApps.biggestId + 1L

            // Save icon if exist
            val iconName = bitmap?.let { icon ->
                val filename = data.saveIcon(id, icon)
                IconUriUtils.iconFileNameToContentUri(this@LauncherOverlayService, filename)
            }

            data.saveMyApps(
                myApps + DisplayLauncherItem(
                    type = DisplayLauncherItemType.MACRO,
                    id = id,
                    order = myApps.biggestOrder + 1,
                    title = title,
                    iconRef = null,
                    customIcon = iconName,
                    packageName = packageName,
                    launchActivity = "",
                    data = intent,
                    isCall = intent.isPhoneCallIntent,
                    isSplit = intent.isSplitIntent,
                    isFrozen = false,
                    isSystem = false
                )
            )
        }
    }

    private fun DisplayLauncherApp.createMyApp() = serviceScope.launch(Dispatchers.Default) {
        data.myAppsItems.value?.let { myApps ->
            data.saveMyApps(
                myApps + DisplayLauncherItem(
                    type = DisplayLauncherItemType.APP,
                    id = myApps.biggestId + 1L,
                    order = myApps.biggestOrder + 1,
                    title = this@createMyApp.appName,
                    iconRef = this@createMyApp.iconRef,
                    customIcon = this@createMyApp.customIcon,
                    packageName = this@createMyApp.packageName,
                    launchActivity = this@createMyApp.launcherActivity ?: "",
                    data = "",
                    isCall = false,
                    isSplit = false,
                    isFrozen = this@createMyApp.isFrozen,
                    isSystem = this@createMyApp.isSystem
                )
            )
        }
    }

    private fun renameMyAppItem(id: Long, title: String) =
        serviceScope.launch(Dispatchers.Default) {
            data.myAppsItems.value?.let { myApps ->
                data.saveMyApps(
                    myApps.map {
                        if (it.id == id) {
                            it.copy(title = title)
                        } else it
                    }
                )
            }
        }

    // Call this to make overlay invisible and untouchable without stopping service
    private fun minimizeOverlayForSystemDialog() {
        // Make overlay non-interactive and invisible
        launcherContainer?.let { view ->
            // visually hide
            view.alpha = 0f
            // disable touches
            launcherWindowParams = launcherWindowParams?.apply {
                flags = flags or WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
            }
            windowManager.updateViewLayout(view, launcherWindowParams)
        }
        restoreAfterExternalDialog = true
    }

    // Call this when returning from the external dialog to restore overlay
    private fun restoreOverlayIfNeeded() {
        if (!restoreAfterExternalDialog) return
        launcherContainer?.let { view ->
            view.alpha = 1f
            launcherWindowParams = launcherWindowParams?.apply {
                flags = flags and WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE.inv()
            }
            windowManager.updateViewLayout(view, launcherWindowParams)
        }
        restoreAfterExternalDialog = false
    }

    private fun hideLauncherOverlay() {
        if (!isClosing.get()) isClosing.set(true)

        launcherContainer?.let { view ->
            runCatching {
                // Remove only if actually attached to avoid IllegalArgumentException
                if (view.isAttachedToWindow) windowManager.removeView(view)
            }
            launcherContainer = null
        }

        // Ensure no background coroutines survive
        serviceScope.cancel()

        // Keep your original contract:
        stopLauncherOverlay(this)
    }

    @Composable
    private fun RenderSystemActionsMenu(packageName: String, onAction: () -> Unit) {
        val context = LocalContext.current
        OptionsMenuItem(
            icon = R.drawable.ic_info,
            title = stringResource(R.string.about_app),
            textColor = Color.White,
            scale = .97f
        ) {
            skipCloseOnNextOnPause = true
            minimizeOverlayForSystemDialog()
            context.openAppSystemSettings(packageName)
            onAction()
        }

        OptionsMenuItem(
            icon = R.drawable.ic_notifications,
            title = stringResource(R.string.notifications),
            textColor = Color.White,
            scale = .99f
        ) {
            skipCloseOnNextOnPause = true
            minimizeOverlayForSystemDialog()
            context.openAppNotifications(packageName)
            onAction()
        }

//        RenderMenuDivider()
//
//        OptionsMenuItem(
//            icon = R.drawable.ic_delete,
//            title = stringResource(R.string.confirm_delete_title),
//            iconColor = AppTheme.colors.deleteButton,
//            textColor = AppTheme.colors.deleteButton
//        ) {
//            skipCloseOnNextOnPause = true
//            minimizeOverlayForSystemDialog()
//            context.requestUninstall(packageName)
//            onAction()
//        }
    }

    @Composable
    private fun RenderAppActionsMenu(
        packageName: String,
        isFrozen: Boolean,
        canUninstall: Boolean,
        enableAdbHelper: Boolean,
        launchedStatus: AppLaunchedState,
        onOpen: () -> Unit,
        onForceStop: () -> Unit,
        onToggleFreeze: (() -> Unit)? = null,
        onAction: () -> Unit
    ) {
        val context = LocalContext.current
        val adbState by adb.connectionState.collectAsStateWithLifecycle(
            initialValue = AdbConnectionState.Disconnected
        )
        val adbConnected = adbState is AdbConnectionState.Connected
        val showUnfreeze = isFrozen && adbConnected && onToggleFreeze != null
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .width(IntrinsicSize.Min)
                .height(IntrinsicSize.Min)
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .wrapContentHeight()
                    .clickable { onOpen() },
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Spacer(Modifier.height(14.dp))
                val color = AppTheme.colors.menuIcon
                Icon(
                    painter = painterResource(R.drawable.ic_open_window),
                    tint = color,
                    contentDescription = "menu icon",
                    modifier = Modifier
                        .alpha(.9f)
                        .size(20.dp)
                        .mirror()
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    modifier = Modifier.padding(horizontal = 10.dp),
                    text = stringResource(R.string.open),
                    color = color,
                    style = AppTheme.typography.cardFormatTitle,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
                Spacer(Modifier.height(12.dp))
            }

            if (canUninstall) {
                Spacer(
                    Modifier
                        .fillMaxHeight()
                        .width(1.dp)
                        .padding(vertical = 6.dp)
                        .background(AppTheme.colors.surfaceMenuDivider.copy(.5f))
                )

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .wrapContentHeight()
                        .clickable {
                            skipCloseOnNextOnPause = true
                            minimizeOverlayForSystemDialog()
                            context.requestUninstall(packageName)
                            onAction()
                        },
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Spacer(Modifier.height(14.dp))
                    val color = AppTheme.colors.deleteButton
                    Icon(
                        painter = painterResource(R.drawable.ic_delete),
                        tint = color,
                        contentDescription = "menu icon",
                        modifier = Modifier
                            .alpha(.9f)
                            .size(22.dp)
                            .mirror()
                    )
                    Spacer(Modifier.height(3.dp))
                    Text(
                        modifier = Modifier.padding(horizontal = 10.dp),
                        text = stringResource(R.string.confirm_delete_title),
                        color = color,
                        style = AppTheme.typography.cardFormatTitle,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1
                    )
                    Spacer(Modifier.height(12.dp))
                }
            }

            if (enableAdbHelper) {
                Spacer(
                    Modifier
                        .fillMaxHeight()
                        .width(1.dp)
                        .padding(vertical = 6.dp)
                        .background(AppTheme.colors.surfaceMenuDivider.copy(.5f))
                )

                val isLaunched = launchedStatus == AppLaunchedState.LAUNCHED
                val actionEnabled = showUnfreeze || (isLaunched && adbConnected)
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .wrapContentHeight()
                        .clickable(enabled = actionEnabled) {
                            if (showUnfreeze) {
                                onToggleFreeze()
                            } else {
                                serviceScope.launch(Dispatchers.IO) {
                                    adb.forceStop(packageName)
                                }
                                onForceStop()
                            }
                        },
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Spacer(Modifier.height(14.dp))
                    val color = if (showUnfreeze) {
                        AppTheme.colors.greenAccent
                    } else if (isLaunched) {
                        if (AppTheme.colors.isDark) {
                            AppTheme.colors.contentWarning
                        } else AppTheme.colors.statusWarning
                    } else {
                        if (AppTheme.colors.isDark) {
                            AppTheme.colors.contentPrimary.copy(.3f)
                        } else AppTheme.colors.menuIcon.copy(.3f)
                    }
                    Icon(
                        painter = painterResource(
                            if (showUnfreeze) {
                                R.drawable.ic_unlock
                            } else {
                                R.drawable.ic_warning
                            }
                        ),
                        tint = color,
                        contentDescription = "menu icon",
                        modifier = Modifier
                            .offset(y = 1.dp)
                            .alpha(.9f)
                            .size(22.dp)
                            .then(if (showUnfreeze) Modifier.padding(2.dp) else Modifier)
                            .mirror()
                    )
                    Spacer(Modifier.height(3.dp))
                    Text(
                        modifier = Modifier.padding(horizontal = 10.dp),
                        text = stringResource(
                            if (showUnfreeze) {
                                R.string.unfreeze_app
                            } else {
                                R.string.stop
                            }
                        ),
                        color = color,
                        style = AppTheme.typography.cardFormatTitle,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1
                    )
                    Spacer(Modifier.height(12.dp))
                }
            }
        }
    }

    @Composable
    private fun RenderRecents(
        config: DisplayLauncherConfig,
        onClick: (item: DisplayLauncherApp) -> Unit = {},
        onLongClick: (item: DisplayLauncherApp, offset: Offset) -> Unit,
    ) {
        var clickLock by rememberTimeLockedBoolean(1000L)
        var recents by remember {
            mutableStateOf(emptyList<DisplayLauncherApp>())
        }
        var canEmpty by remember { mutableStateOf(false) }
        LaunchedEffect(Unit) {
            withContext(Dispatchers.Default) {
                data.allApps.collect { allApps ->
                    val r = stateKeeper
                        .visibleAppsState
                        .value
                    val bp = allApps.associateBy {
                        it.packageName
                    }
                    recents = r.mapNotNull { bp[it] }
                    canEmpty = true
                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            // divider
            Spacer(
                Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(
                        AppTheme.colors.launcherSurface1.copy(
                            .5f
                        )
                    )
            )

            Spacer(Modifier.height(16.dp))

            val iconSize = 42
            val vPadding = 12
            val tContent = 6
            val bContent = 26

            // No AS warning
            var canAccessibility by remember { mutableStateOf(stateKeeper.canAccessibility.value) }
            LaunchedEffect(Unit) {
                stateKeeper.canAccessibility.collect { canAccessibility = it }
            }
            if (!canAccessibility) {
                val scope = rememberCoroutineScope()
                val context = LocalContext.current
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = config.iconOutSpace.dp,
                            end = config.iconOutSpace.dp,
                            top = tContent.dp,
                            bottom = bContent.dp,
                        )
                        .height(((vPadding * 2) + iconSize).dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier.weight(1f),
                        text = stringResource(R.string.accessibility_service_disabled),
                        style = AppTheme.typography.overlayLauncherSettingsTitle,
                        color = AppTheme.colors.contentPrimary
                    )
                    Spacer(Modifier.width(14.dp))
                    Row(
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .background(AppTheme.colors.contentAccent)
                            .clickable {
                                context.openAccessibilitySettings()
                                skipCloseOnNextOnPause = true
                                minimizeOverlayForSystemDialog()
                            }
                            .padding(horizontal = 14.dp, vertical = 12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            modifier = Modifier,
                            text = stringResource(R.string.enable),
                            color = Color.White,
                            style = AppTheme.typography.overlayLauncherSettingsGroup.copy(
                                fontSize = 18.sp
                            )
                        )
                    }
                    Spacer(Modifier.width(16.dp))
                    Row(
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .background(AppTheme.colors.surfaceMenu)
                            .clickable {
                                scope.launch {
                                    storage.dataStore.saveValue(
                                        LauncherPrefs.LAUNCHER_RECENTS_ENABLE,
                                        false
                                    )
                                }
                            }
                            .padding(horizontal = 14.dp, vertical = 12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            modifier = Modifier,
                            text = stringResource(R.string.close),
                            color = Color.White,
                            style = AppTheme.typography.overlayLauncherSettingsGroup.copy(
                                fontSize = 18.sp
                            )
                        )
                    }
                }
                return@Column
            }

            if (canEmpty && recents.isEmpty()) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = config.iconOutSpace.dp,
                            end = config.iconOutSpace.dp,
                            top = tContent.dp,
                            bottom = bContent.dp,
                        )
                        .height(((vPadding * 2) + iconSize).dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier.weight(1f),
                        text = stringResource(R.string.no_recent_apps),
                        style = AppTheme.typography.overlayLauncherSettingsTitle,
                        color = AppTheme.colors.contentPrimary
                    )
                }
                return@Column
            }

            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(((vPadding * 2) + iconSize + bContent + tContent).dp),
                contentPadding = PaddingValues(
                    start = config.iconOutSpace.dp,
                    end = config.iconOutSpace.dp,
                    top = tContent.dp,
                    bottom = bContent.dp
                ),
                horizontalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                itemsIndexed(
                    items = recents,
                    key = { _, item -> item.id }
                ) { _, app ->
                    var rootOffset by remember { mutableStateOf(Offset.Zero) }

                    Row(
                        modifier = Modifier
                            .clip(RoundedCornerShape(16.dp))
                            .background(AppTheme.colors.launcherSurface1)
                            .padding(
                                horizontal = 16.dp,
                                vertical = vPadding.dp
                            )
                            .onGloballyPositioned { coordinates ->
                                rootOffset = Offset(
                                    coordinates.positionInRoot().x,
                                    coordinates.positionInRoot().y
                                )
                            }
                            .pointerInput(app) {
                                detectTapGestures(
                                    onLongPress = {
                                        onLongClick(
                                            app,
                                            Offset(
                                                x = it.x + rootOffset.x,
                                                y = it.y + rootOffset.y
                                            )
                                        )
                                    },
                                    onTap = {
                                        if (!clickLock) {
                                            onClick(app)
                                        }
                                        clickLock = true
                                    }
                                )
                            },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        val ctx = LocalContext.current
                        val pxSize =
                            with(LocalDensity.current) { config.iconSize.dp.roundToPx() }
                        val ir = app.iconRef

                        val model = remember(app.iconRef, app.customIcon) {
                            val builder = ImageRequest.Builder(ctx)
                                .size(pxSize, pxSize)
                                .precision(Precision.EXACT)
                                .allowHardware(false)
                                .memoryCachePolicy(CachePolicy.ENABLED)
                                .diskCachePolicy(CachePolicy.DISABLED)
                                .dispatcher(Dispatchers.IO)

                            if (app.customIcon != null) {
                                builder.data(app.customIcon)
                            } else {
                                val stableKey =
                                    "pkg:${ir.packageName}|res:${ir.resId}|dpi:${ir.densityDpi}|vc:${ir.versionCode}|w:${pxSize}|h:${pxSize}"
                                builder.data(app.iconRef)
                                    .memoryCacheKey(stableKey)
                                    .placeholderMemoryCacheKey(stableKey)
                            }

                            builder.build()
                        }
                        AsyncImage(
                            model = model,
                            contentDescription = app.appName,
                            modifier = Modifier
                                .size(iconSize.dp)
                                .clip(RoundedCornerShape(10.dp)),
                            contentScale = ContentScale.Crop
                        )

                        Spacer(Modifier.width(14.dp))

                        Text(
                            modifier = Modifier,
                            text = app.appName,
                            style = AppTheme.typography.overlayLauncherIconTitle.copy(
                                fontSize = 17.sp,
                                lineHeight = 17.sp
                            ),
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }
        }
    }

    @Composable
    private fun RenderAppLabelAndIconMenu(
        packageName: String,
        isFrozen: Boolean,
        enableAdbHelper: Boolean,
        onRename: (() -> Unit)? = null,
        onChangeIcon: () -> Unit,
        onToggleFreeze: (() -> Unit)? = null
    ) {
        val adbState by adb.connectionState.collectAsStateWithLifecycle(
            initialValue = AdbConnectionState.Disconnected
        )
        val showFreeze = enableAdbHelper && packageName.isNotBlank() &&
                adbState is AdbConnectionState.Connected && onToggleFreeze != null
        var expanded by remember { mutableStateOf(false) }
        val editIcon = rememberVectorPainter(image = Icons.Filled.Build)
        RenderOptionsMenuItem(
            icon = editIcon,
            title = stringResource(R.string.launcher_context_edit),
            offsetX = 1f,
            textColor = Color.White,
            iconColor = Color.White,
            scale = .88f
        ) { expanded = !expanded }
        AnimatedVisibility(
            visible = expanded,
            enter = fadeIn(tween(120)),
            exit = fadeOut(tween(120))
        ) {
            Box(
                modifier = Modifier
                    .padding(top = 4.dp, start = 10.dp, end = 10.dp, bottom = 10.dp)
                    .background(
                        AppTheme.colors.surfaceMenuDivider,
                        RoundedCornerShape(12.dp)
                    )
            ) {
                Column(modifier = Modifier.padding(vertical = 4.dp)) {
                    onRename?.let { rename ->
                        OptionsMenuItem(
                            icon = R.drawable.ic_rename,
                            title = stringResource(R.string.rename),
                            textColor = Color.White,
                            scale = .97f
                        ) { rename() }
                    }
                    OptionsMenuItem(
                        icon = R.drawable.ic_pic_change,
                        title = stringResource(R.string.change_icon),
                        textColor = Color.White,
                        scale = .97f
                    ) { onChangeIcon() }
                    if (showFreeze) {
                        val deleteLikeRed = AppTheme.colors.deleteButton
                        val unfreezeGreen = AppTheme.colors.greenAccent
                        val freezeMenuColor = if (isFrozen) unfreezeGreen else deleteLikeRed
                        OptionsMenuItem(
                            icon = if (isFrozen) {
                                R.drawable.ic_unlock
                            } else {
                                R.drawable.ic_lock
                            },
                            title = stringResource(
                                if (isFrozen) {
                                    R.string.unfreeze_app
                                } else {
                                    R.string.freeze_app
                                }
                            ),
                            iconColor = freezeMenuColor,
                            textColor = freezeMenuColor,
                            scale = .85f
                        ) { onToggleFreeze() }
                    }
                }
            }
        }
    }

    @Composable
    private fun RenderActivitiesMenu(
        availableActivity: List<String>,
        launcherActivity: String,
        packageName: String,
        onAction: () -> Unit
    ) {
        val context = LocalContext.current
        var showActivity by remember { mutableStateOf(false) }
        OptionsMenuItem(
            icon = if (showActivity) {
                R.drawable.ic_hide
            } else R.drawable.ic_show,
            title = stringResource(
                if (showActivity) {
                    R.string.hide_available_activities
                } else R.string.show_available_activities
            ),
            textColor = Color.White
        ) { showActivity = !showActivity }
        AnimatedVisibility(
            visible = showActivity,
            enter = fadeIn(tween(120)),
            exit = fadeOut(tween(120))
        ) {
            CompositionLocalProvider(LocalOverscrollFactory provides null) {
                Box(
                    modifier = Modifier
                        .padding(top = 4.dp, start = 10.dp, end = 10.dp, bottom = 10.dp)
                        .background(
                            AppTheme.colors.surfaceMenuDivider,
                            RoundedCornerShape(12.dp)
                        )
                ) {
                    Column(
                        modifier = Modifier
                            .heightIn(max = 260.dp)
                            .verticalScroll(rememberScrollState())
                    ) {
                        val icon =
                            rememberVectorPainter(image = Icons.AutoMirrored.Filled.ArrowForward)
                        availableActivity.forEach { launchActivity ->
                            RenderOptionsMenuItem(
                                icon = icon,
                                title = launchActivity,
                                iconColor = if (!AppTheme.colors.isDark) {
                                    if (launchActivity == launcherActivity) {
                                        AppTheme.colors.warning
                                    } else Color.White
                                } else {
                                    if (launchActivity == launcherActivity) {
                                        AppTheme.colors.contentLightAccent
                                    } else AppTheme.colors.menuIcon
                                },
                                textColor = if (!AppTheme.colors.isDark) {
                                    if (launchActivity == launcherActivity) {
                                        AppTheme.colors.warning
                                    } else Color.White
                                } else {
                                    if (launchActivity == launcherActivity) {
                                        AppTheme.colors.contentLightAccent
                                    } else AppTheme.colors.contentPrimary
                                }
                            ) {
                                minimizeOverlayForSystemDialog()
                                context.launchApp(packageName, launchActivity)
                                onAction()
                            }
                        }
                    }
                }
            }
        }
    }

    @Composable
    private fun RenderMenuDivider() = Box(
        modifier = Modifier
            .background(AppTheme.colors.surfaceMenuDivider)
            .height(16.dp)
    ) {
        TopShadow()
        BottomShadow(modifier = Modifier.align(Alignment.BottomCenter))
    }
}
