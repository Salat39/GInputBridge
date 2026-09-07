package com.salat.gbinder.features.carFunctions

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.graphics.PixelFormat
import android.os.IBinder
import android.provider.Settings
import android.view.Gravity
import android.view.MotionEvent
import android.view.WindowManager
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import androidx.core.app.NotificationCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.setViewTreeLifecycleOwner
import androidx.savedstate.setViewTreeSavedStateRegistryOwner
import com.salat.gbinder.APP_PANEL_STEP_DELAY_DEFAULT_MS
import com.salat.gbinder.App
import com.salat.gbinder.BuildConfig
import com.salat.gbinder.FN_PANEL_HIDE_DELAY_DEFAULT_MS
import com.salat.gbinder.ModelHelper
import com.salat.gbinder.R
import com.salat.gbinder.components.ComposeWindowLifecycleOwner
import com.salat.gbinder.components.inMainToast
import com.salat.gbinder.components.launchApp
import com.salat.gbinder.datastore.DataStoreRepository
import com.salat.gbinder.datastore.GeneralPrefs
import com.salat.gbinder.entity.AppPanelConfig
import com.salat.gbinder.entity.CarFunction
import com.salat.gbinder.entity.DisplayLauncherItem
import com.salat.gbinder.entity.DisplayLauncherItemType
import com.salat.gbinder.features.launcher.LauncherCarFunctionStates
import com.salat.gbinder.features.launcher.LauncherDataRepository
import com.salat.gbinder.features.launcher.RenderLauncherCarFunctionIcon
import com.salat.gbinder.features.launcher.RenderLauncherMyAppCell
import com.salat.gbinder.features.launcher.isNowWithinHours
import com.salat.gbinder.ui.clickableNoRipple
import com.salat.gbinder.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeoutOrNull
import timber.log.Timber
import java.util.concurrent.atomic.AtomicBoolean
import javax.inject.Inject

@AndroidEntryPoint
class CarFunctionPanelOverlayService : Service() {
    companion object {
        const val EXTRA_FUNCTIONS = "functions"
        const val EXTRA_APPS = "apps"
        const val EXTRA_VISIBLE_APP = "visible_app"
        private const val CHANNEL_ID = "fn_panel_overlay_service_channel"
        private const val NOTIFICATION_ID = 2005
        private const val APPS_WAIT_MS = 3_000L
        private const val SELECTION_ANIM_MS = 220
        private const val SELECTION_GAP_DP = 4
        private const val SELECTION_STROKE_DP = 4
        private const val SELECTION_GLOW_DP = 10
        private const val SELECTION_GLOW_STEPS = 5
        private const val SELECTION_GLOW_ALPHA = .25f
        private const val FADE_IN_MS = 180L
        private const val FADE_OUT_MS = 120L
        private const val SCREEN_GAP_DP = 48
        private const val BOTTOM_OFFSET_RATIO = .12f
        private const val SHADOW_ROOM_DP = 16
        private const val TOP_MARGIN_DP = 32
        private const val MAX_PANEL_PADDING_DP = 48
    }

    @Inject
    lateinit var data: LauncherDataRepository

    @Inject
    lateinit var dataStore: DataStoreRepository

    private lateinit var windowManager: WindowManager
    private var container: ComposeView? = null
    private lateinit var composeLifecycleOwner: ComposeWindowLifecycleOwner
    private val serviceScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
    private val prolongSignal = MutableSharedFlow<Unit>(replay = 1, extraBufferCapacity = 64)
    private val closeSignal = MutableSharedFlow<Unit>(replay = 1)
    private val stepSignal = MutableSharedFlow<Unit>(replay = 1, extraBufferCapacity = 64)
    private val isClosing = AtomicBoolean(false)
    private val functions = MutableStateFlow<List<CarFunction>>(emptyList())
    private val apps = MutableStateFlow<List<DisplayLauncherItem>>(emptyList())
    private val selectedIndex = MutableStateFlow(-1)
    private var request: String? = null
    private var stepMode = false
    private var visibleApp = ""
    private var pendingSteps = 0
    private var carFunctionStates: LauncherCarFunctionStates? = null
    private val carModel by lazy { ModelHelper.detectCarModel() }

    @OptIn(FlowPreview::class)
    override fun onCreate() {
        super.onCreate()
        val channel = NotificationChannel(
            CHANNEL_ID,
            "Function Panel Overlay",
            NotificationManager.IMPORTANCE_LOW
        )
        (getSystemService(NOTIFICATION_SERVICE) as NotificationManager)
            .createNotificationChannel(channel)
        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Function Panel Overlay")
            .setContentText("Function Panel Overlay")
            .setSmallIcon(R.drawable.ic_launcher_logo)
            .setPriority(NotificationCompat.PRIORITY_MIN)
            .setOngoing(true)
            .build()
        startForeground(NOTIFICATION_ID, notification)

        if (!Settings.canDrawOverlays(this)) {
            stopSelf()
            return
        }
        windowManager = getSystemService(WINDOW_SERVICE) as WindowManager
        carFunctionStates = (application as App).panelCarFunctionStates
        serviceScope.launch {
            val hideDelay = dataStore
                .getValueFlow(GeneralPrefs.FN_PANEL_HIDE_DELAY, FN_PANEL_HIDE_DELAY_DEFAULT_MS)
                .first()
                .toLong()
            prolongSignal.debounce(hideDelay).collect {
                requestClose()
            }
        }
        serviceScope.launch {
            val stepDelay = dataStore
                .getValueFlow(GeneralPrefs.APP_PANEL_STEP_DELAY, APP_PANEL_STEP_DELAY_DEFAULT_MS)
                .first()
                .toLong()
            stepSignal.debounce(stepDelay).collect {
                apps.value.getOrNull(selectedIndex.value)?.let { launchAndClose(it) }
            }
        }
        composeLifecycleOwner = ComposeWindowLifecycleOwner().apply {
            performRestore(null)
            setCurrentState(Lifecycle.State.RESUMED)
        }
        setupOverlay()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (isClosing.get()) return START_NOT_STICKY
        val appsValue = intent?.getStringExtra(EXTRA_APPS)
        if (functions.value.isNotEmpty() || request != null) {
            if (stepMode && appsValue == request) advanceSelection() else requestClose()
            return START_NOT_STICKY
        }
        if (appsValue != null) {
            val config = AppPanelConfig.parse(appsValue)
            request = appsValue
            stepMode = config.stepMode
            visibleApp = intent.getStringExtra(EXTRA_VISIBLE_APP).orEmpty()
            serviceScope.launch { showApps(config.packages) }
            return START_NOT_STICKY
        }
        val list = CarFunction.parsePanel(
            intent?.getStringExtra(EXTRA_FUNCTIONS).orEmpty(),
            carModel
        )
        if (list.isEmpty()) {
            stopSelf()
        } else {
            functions.value = list
            prolongSignal.tryEmit(Unit)
        }
        return START_NOT_STICKY
    }

    private fun requestClose() {
        if (isClosing.compareAndSet(false, true)) closeSignal.tryEmit(Unit)
    }

    private fun launchAndClose(item: DisplayLauncherItem) {
        if (!isClosing.compareAndSet(false, true)) return
        closeSignal.tryEmit(Unit)
        launchApp(item.packageName, item.launchActivity)
    }

    private fun advanceSelection() {
        val size = apps.value.size
        if (size == 0) {
            pendingSteps++
            return
        }
        selectedIndex.value = (selectedIndex.value + 1) % size
        prolongSignal.tryEmit(Unit)
        stepSignal.tryEmit(Unit)
    }

    private suspend fun showApps(packages: List<String>) {
        val installed = withTimeoutOrNull(APPS_WAIT_MS) {
            data.allApps.first { it.isNotEmpty() }
        }.orEmpty()
        val items = packages.distinct()
            .mapNotNull { pkg -> installed.firstOrNull { it.packageName == pkg } }
            .filter { !it.isFrozen }
            .map {
                DisplayLauncherItem(
                    type = DisplayLauncherItemType.APP,
                    id = it.packageName.hashCode().toLong(),
                    order = 0,
                    title = it.appName,
                    iconRef = it.iconRef,
                    customIcon = it.customIcon,
                    packageName = it.packageName,
                    launchActivity = it.launcherActivity.orEmpty(),
                    data = "",
                    isCall = false,
                    isSplit = false,
                    isFrozen = false
                )
            }
        if (items.isEmpty()) {
            inMainToast(getString(R.string.app_panel_no_apps))
            stopSelf()
        } else {
            apps.value = items
            prolongSignal.tryEmit(Unit)
            if (stepMode) {
                val current = items.indexOfFirst { it.packageName == visibleApp }
                selectedIndex.value = (current + 1 + pendingSteps) % items.size
                stepSignal.tryEmit(Unit)
            }
        }
    }

    @OptIn(ExperimentalLayoutApi::class, ExperimentalFoundationApi::class)
    @SuppressLint("ClickableViewAccessibility")
    private fun setupOverlay() {
        val carFunctionStates = carFunctionStates ?: return

        container = ComposeView(this).apply {
            setViewTreeLifecycleOwner(composeLifecycleOwner)
            setViewTreeSavedStateRegistryOwner(composeLifecycleOwner)
            setOnTouchListener { _, event ->
                if (event.actionMasked == MotionEvent.ACTION_OUTSIDE) requestClose()
                false
            }
            setContent {
                val config by data.settingsConfig.collectAsStateWithLifecycle()
                val cnf = config ?: return@setContent
                val isDark = if (cnf.autoLightTheme) {
                    !isNowWithinHours(cnf.autoLightThemeStart, cnf.autoLightThemeEnd)
                } else {
                    !cnf.lightTheme
                }
                AppTheme(darkTheme = isDark) {
                    CompositionLocalProvider(
                        LocalDensity provides Density(LocalDensity.current.density * cnf.uiScale)
                    ) {
                        val fns by functions.collectAsStateWithLifecycle()
                        val appItems by apps.collectAsStateWithLifecycle()
                        val selected by selectedIndex.collectAsStateWithLifecycle()
                        val states by carFunctionStates.states.collectAsStateWithLifecycle()
                        val items by data.myAppsItems.collectAsStateWithLifecycle()
                        LaunchedEffect(states) {
                            if (fns.isNotEmpty()) prolongSignal.tryEmit(Unit)
                        }

                        val alpha = remember { Animatable(0f) }
                        LaunchedEffect(Unit) {
                            alpha.snapTo(0f)
                            alpha.animateTo(1f, tween(durationMillis = FADE_IN_MS.toInt()))
                        }
                        LaunchedEffect(Unit) {
                            closeSignal.collect {
                                alpha.animateTo(0f, tween(durationMillis = FADE_OUT_MS.toInt()))
                                hideOverlay()
                            }
                        }

                        val frozenIconColorFilter = remember {
                            ColorFilter.colorMatrix(ColorMatrix().apply { setToSaturation(0f) })
                        }
                        val space = cnf.iconInnerSpace.dp
                        val panelPadding = minOf(space, MAX_PANEL_PADDING_DP.dp)
                        val sidePadding = (panelPadding - space / 2).coerceAtLeast(0.dp)
                        val cellWidth = cnf.iconSize.dp + space
                        val shape = RoundedCornerShape(16.dp)
                        val scrollState = rememberScrollState()
                        LaunchedEffect(scrollState.isScrollInProgress) {
                            if (scrollState.isScrollInProgress) prolongSignal.tryEmit(Unit)
                        }

                        BoxWithConstraints(
                            Modifier
                                .fillMaxWidth()
                                .graphicsLayer(alpha = alpha.value)
                                .clickableNoRipple { requestClose() },
                            contentAlignment = Alignment.BottomCenter
                        ) {
                            val bottomOffset = maxHeight * BOTTOM_OFFSET_RATIO
                            val panelMaxHeight =
                                maxHeight - bottomOffset - (TOP_MARGIN_DP + SHADOW_ROOM_DP * 2).dp
                            FlowRow(
                                modifier = Modifier
                                    .padding(horizontal = SCREEN_GAP_DP.dp, vertical = SHADOW_ROOM_DP.dp)
                                    .padding(bottom = bottomOffset)
                                    .heightIn(max = panelMaxHeight)
                                    .shadow(4.dp, shape)
                                    .clip(shape)
                                    .background(AppTheme.colors.launcherBackground.copy(cnf.windowAlpha))
                                    .clickableNoRipple { prolongSignal.tryEmit(Unit) }
                                    .verticalScroll(scrollState)
                                    .padding(
                                        start = sidePadding,
                                        end = sidePadding,
                                        top = panelPadding * .75f,
                                        bottom = panelPadding / 2
                                    ),
                                horizontalArrangement = Arrangement.Center,
                                verticalArrangement = Arrangement.spacedBy(space / 2)
                            ) {
                                fns.forEach { function ->
                                    key(function) {
                                        val item = items?.firstOrNull {
                                            it.type == DisplayLauncherItemType.CAR_FUNCTION &&
                                                it.data == function.name
                                        } ?: DisplayLauncherItem(
                                            type = DisplayLauncherItemType.CAR_FUNCTION,
                                            id = function.ordinal.toLong(),
                                            order = 0,
                                            title = stringResource(function.titleRes),
                                            iconRef = null,
                                            customIcon = null,
                                            packageName = "",
                                            launchActivity = "",
                                            data = function.name,
                                            isCall = false,
                                            isSplit = false,
                                            isFrozen = false
                                        )
                                        Box(Modifier.width(cellWidth).padding(horizontal = space / 4)) {
                                            RenderLauncherMyAppCell(
                                                app = item,
                                                cellSize = cnf.iconSize,
                                                enableText = cnf.iconTextEnable,
                                                iconRound = cnf.iconRound,
                                                textSize = cnf.iconTextSize,
                                                textPadding = cnf.iconTextPadding,
                                                enableShortcuts = false,
                                                shortcutSize = cnf.shortcutSize,
                                                enableMultiline = cnf.iconTextMultiline,
                                                frozenIconColorFilter = frozenIconColorFilter,
                                                lockMode = true,
                                                enableClick = true,
                                                onHideApp = {},
                                                onClick = {
                                                    carFunctionStates.tap(function)
                                                    prolongSignal.tryEmit(Unit)
                                                    if (function.opensExternalScreen()) {
                                                        requestClose()
                                                    }
                                                },
                                                onLongClick = { _, _ -> },
                                                iconContent = { pressed ->
                                                    LaunchedEffect(pressed) {
                                                        if (pressed) prolongSignal.tryEmit(Unit)
                                                    }
                                                    RenderLauncherCarFunctionIcon(
                                                        function = function,
                                                        state = states[function],
                                                        customIcon = item.customIcon,
                                                        cellSize = cnf.iconSize,
                                                        iconRound = cnf.iconRound,
                                                        available = BuildConfig.DEBUG ||
                                                            function.isAvailableFor(carModel),
                                                        amber = cnf.carFunctionAmber,
                                                        accent = cnf.carFunctionAccent,
                                                        pressed = pressed
                                                    )
                                                }
                                            )
                                        }
                                    }
                                }
                                appItems.forEachIndexed { index, item ->
                                    key(item.packageName) {
                                        val isSelected = stepMode && index == selected
                                        val progress by animateFloatAsState(
                                            if (isSelected) 1f else 0f,
                                            tween(SELECTION_ANIM_MS),
                                            label = "selection"
                                        )
                                        val bringIntoView = remember { BringIntoViewRequester() }
                                        val density = LocalDensity.current
                                        LaunchedEffect(isSelected) {
                                            if (!isSelected) return@LaunchedEffect
                                            with(density) {
                                                val outset =
                                                    (SELECTION_GAP_DP + SELECTION_STROKE_DP + SELECTION_GLOW_DP).dp.toPx()
                                                val icon = cnf.iconSize.dp.toPx()
                                                bringIntoView.bringIntoView(
                                                    Rect(-outset, -outset, icon + outset, icon + outset)
                                                )
                                            }
                                        }
                                        val accent = AppTheme.colors.contentAccent
                                        Box(Modifier.width(cellWidth).padding(horizontal = space / 4)) {
                                            RenderLauncherMyAppCell(
                                                app = item,
                                                cellSize = cnf.iconSize,
                                                enableText = cnf.iconTextEnable,
                                                iconRound = cnf.iconRound,
                                                textSize = cnf.iconTextSize,
                                                textPadding = cnf.iconTextPadding,
                                                enableShortcuts = false,
                                                shortcutSize = cnf.shortcutSize,
                                                enableMultiline = cnf.iconTextMultiline,
                                                frozenIconColorFilter = frozenIconColorFilter,
                                                lockMode = true,
                                                enableClick = true,
                                                onHideApp = {},
                                                onClick = { launchAndClose(item) },
                                                onLongClick = { _, _ -> },
                                                iconModifier = Modifier
                                                    .bringIntoViewRequester(bringIntoView)
                                                    .drawBehind {
                                                        if (progress == 0f) return@drawBehind
                                                        val stroke = SELECTION_STROKE_DP.dp.toPx()
                                                        val inset = (SELECTION_GAP_DP.dp.toPx() + stroke / 2) * progress
                                                        val radius = if (cnf.iconRound == 0) {
                                                            CornerRadius.Zero
                                                        } else {
                                                            CornerRadius(cnf.iconRound.dp.toPx() + inset)
                                                        }
                                                        val glowStep = SELECTION_GLOW_DP.dp.toPx() / SELECTION_GLOW_STEPS
                                                        for (i in SELECTION_GLOW_STEPS downTo 1) {
                                                            val glow = glowStep * i
                                                            val fade = 1f - (i - 1f) / SELECTION_GLOW_STEPS
                                                            drawRoundRect(
                                                                color = accent.copy(alpha = SELECTION_GLOW_ALPHA * fade * progress),
                                                                topLeft = Offset(-inset - glow / 2, -inset - glow / 2),
                                                                size = Size(size.width + inset * 2 + glow, size.height + inset * 2 + glow),
                                                                cornerRadius = CornerRadius(radius.x + glow / 2),
                                                                style = Stroke(stroke + glow)
                                                            )
                                                        }
                                                        drawRoundRect(
                                                            color = accent.copy(alpha = progress),
                                                            topLeft = Offset(-inset, -inset),
                                                            size = Size(size.width + inset * 2, size.height + inset * 2),
                                                            cornerRadius = radius,
                                                            style = Stroke(stroke)
                                                        )
                                                    }
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        val params = WindowManager.LayoutParams(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or
                WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL or
                WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN or
                WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH,
            PixelFormat.TRANSLUCENT
        ).apply {
            gravity = Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL
        }
        runCatching { windowManager.addView(container, params) }
            .onFailure {
                Timber.e(it)
                stopSelf()
            }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (::composeLifecycleOwner.isInitialized) {
            composeLifecycleOwner.setCurrentState(Lifecycle.State.DESTROYED)
        }
        serviceScope.cancel()
        hideOverlay()
    }

    override fun onBind(intent: Intent?): IBinder? = null

    private fun hideOverlay() {
        isClosing.set(true)
        container?.let { view ->
            runCatching {
                if (view.isAttachedToWindow) windowManager.removeView(view)
            }
        }
        container = null
        serviceScope.cancel()
        stopSelf()
    }
}
