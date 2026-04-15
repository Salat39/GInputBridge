package com.salat.gbinder

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.graphics.PixelFormat
import android.os.Build
import android.os.IBinder
import android.provider.Settings
import android.util.TypedValue
import android.view.Gravity
import android.view.WindowManager
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.NotificationCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.setViewTreeLifecycleOwner
import androidx.savedstate.setViewTreeSavedStateRegistryOwner
import com.salat.gbinder.car.data.CarPropertyValue
import com.salat.gbinder.components.ComposeWindowLifecycleOwner
import com.salat.gbinder.components.toPxInt
import com.salat.gbinder.entity.SegmentTogglerItem
import com.salat.gbinder.statekeeper.domain.repository.StateKeeperRepository
import com.salat.gbinder.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.concurrent.atomic.AtomicBoolean
import javax.inject.Inject
import kotlin.math.roundToInt

@AndroidEntryPoint
class LampModeOverlayService : Service() {

    companion object {
        private const val CHANNEL_ID = "lm_overlay_service_channel"
        private const val LAMP_MODE_OVERLAY = 2004
        private const val CLOSE_DEBOUNCE_MS = 3500L
        private const val FADE_IN_MS = 180L
        private const val FADE_OUT_MS = 120L
    }

    @Inject
    lateinit var stateKeeper: StateKeeperRepository

    private lateinit var windowManager: WindowManager

    // lamp mode params
    private var lampModeContainer: ComposeView? = null
    private var lampModeWindowParams: WindowManager.LayoutParams? = null
    private lateinit var composeLifecycleOwner: ComposeWindowLifecycleOwner

    private val serviceScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    // Prolongation events: emit on create and on each non-null lamp mode change
    private val prolongSignal = MutableSharedFlow<Unit>(extraBufferCapacity = 64)

    // Close requests: collected in Compose to run fade-out then actually hide
    private val closeSignal = MutableSharedFlow<Unit>(extraBufferCapacity = 1)

    // Ensure we close exactly once
    private val isClosing = AtomicBoolean(false)

    @SuppressLint("ObsoleteSdkInt")
    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "Lamp Mode Overlay",
                NotificationManager.IMPORTANCE_LOW
            ).apply {
                description = "Show Lamp mode changes"
            }
            (getSystemService(NOTIFICATION_SERVICE) as NotificationManager)
                .createNotificationChannel(channel)
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int) = START_STICKY

    private fun buildNotification() = NotificationCompat.Builder(this, CHANNEL_ID)
        .setContentTitle("Lamp Mode Overlay")
        .setContentText("Show Lamp Mode changes")
        .setSmallIcon(R.drawable.ic_launcher_logo) // your existing icon
        .setPriority(NotificationCompat.PRIORITY_MIN)
        .setOngoing(true) // Foreground service best practice
        .build()

    @OptIn(FlowPreview::class)
    @Suppress("DEPRECATION")
    @SuppressLint("ObsoleteSdkInt")
    override fun onCreate() {
        super.onCreate()

        createNotificationChannel()
        startForeground(LAMP_MODE_OVERLAY, buildNotification())

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(this)) {
            stopSelf()
            return
        }
        windowManager = getSystemService(WINDOW_SERVICE) as WindowManager

        // Start close orchestration: close after 3s of silence since last prolongation
        serviceScope.launch {
            // Start with initial event so newly created overlay has 3s
            prolongSignal.emit(Unit)

            prolongSignal
                .debounce(CLOSE_DEBOUNCE_MS)
                .collect {
                    // No qualifying events for 3s -> request close (Compose will fade-out)
                    closeSignal.tryEmit(Unit)
                }
        }

        // Create Compose lifecycle owner
        val myOwner = ComposeWindowLifecycleOwner().apply {
            performRestore(null)
            setCurrentState(Lifecycle.State.RESUMED)
        }
        composeLifecycleOwner = myOwner // Set owner for menu overlay

        if (lampModeContainer != null) return

        setupLampModeOverlay()
    }

    private fun setupLampModeOverlay() {
        // 1. Calculate margin in pixels (10 dp → px)
        val marginPx = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            10f,
            resources.displayMetrics
        ).toInt()

        // 2. Get screen dimensions
        val displayMetrics = resources.displayMetrics
        val topMargin = ((displayMetrics.heightPixels.toFloat() / 100f) * 71f).toInt() // TODO TEST REAL OPTIMAL VALUE

        val scale = (stateKeeper.uiScales.value?.first ?: DEFAULT_UI_SCALE) + .5f // TODO TEST REAL OPTIMAL VALUE
        val baseWidth = 490
        val baseOutput = 16

        val overlayWidth =
            (baseWidth * scale).roundToInt().dp.toPxInt + ((baseOutput * 2) * scale).roundToInt().dp.toPxInt

        lampModeContainer = ComposeView(this).apply {
            setViewTreeLifecycleOwner(composeLifecycleOwner)
            setViewTreeSavedStateRegistryOwner(composeLifecycleOwner)
            setContent {

                AppTheme(darkTheme = true) {
                    val lampState by stateKeeper.lampModeInOverlay.collectAsStateWithLifecycle()


                    // animatable alpha for fade in/out
                    val alpha = remember { Animatable(0f) }

                    // On first composition, fade in
                    LaunchedEffect(Unit) {
                        /* Smooth fade-in on appear */
                        alpha.snapTo(0f)
                        alpha.animateTo(
                            targetValue = 1f,
                            animationSpec = tween(durationMillis = FADE_IN_MS.toInt())
                        )
                    }

                    // Prolong close timer on each non-null drive-mode emission
                    LaunchedEffect(lampState) {
                        if (lampState != null) {
                            // Extend close deadline by 3s from *this* change
                            prolongSignal.tryEmit(Unit)
                        }
                    }

                    // Close collector -> fade out then hide
                    LaunchedEffect(Unit) {
                        closeSignal.collect {
                            // Prevent multiple fade-outs competing
                            if (isClosing.compareAndSet(false, true)) {
                                // fade out
                                alpha.animateTo(
                                    targetValue = 0f,
                                    animationSpec = tween(
                                        durationMillis = FADE_OUT_MS.toInt()
                                    )
                                )
                                // now actually remove the view and stop service
                                hideLampModeOverlay()
                            }
                        }
                    }

                    // Scale config
                    val border =
                        remember { RoundedCornerShape((12 * scale).roundToInt().dp) }
                    // val height = 140
                    val width = remember { (baseWidth * scale).roundToInt().dp }
                    val titleFontSize = remember { (18 * scale).roundToInt().sp }
                    val verticalPadding = remember { (18 * scale).roundToInt().dp }
                    val innerVerticalPadding = remember { (13 * scale).roundToInt().dp }
                    val horizontalPadding = remember { (20 * scale).roundToInt().dp }
                    val selectorHorizontalPadding = remember { (18 * scale).roundToInt().dp }
                    val shadow = remember { (4 * scale).roundToInt().dp }
                    val shadowPadding = remember { (baseOutput * scale).roundToInt().dp }
                    val cornerSelector = remember { (10 * scale).roundToInt().dp }
                    val selectorItemsOffset = remember { (1 * scale).roundToInt().dp }

                    val titleStyle = AppTheme.typography.overlayNativeTitle.copy(
                        fontSize = titleFontSize
                    )

                    lampState?.let { (modes, current) ->

                        var list by remember {
                            mutableStateOf<List<SegmentTogglerItem>>(emptyList())
                        }
                        LaunchedEffect(modes) {
                            list = modes.map { id ->
                                SegmentTogglerItem(
                                    text = when (id) {
                                        CarPropertyValue.LAMP_EXTERIOR_LIGHT_CONTROL_OFF -> R.string.headlight_off
                                        CarPropertyValue.LAMP_EXTERIOR_LIGHT_CONTROL_AUTOMATIC -> R.string.headlight_auto
                                        else -> null
                                    },
                                    icon = when (id) {
                                        CarPropertyValue.LAMP_EXTERIOR_LIGHT_CONTROL_POS_LIGHT -> R.drawable.ic_position_light
                                        CarPropertyValue.LAMP_EXTERIOR_LIGHT_CONTROL_LOWBEAM -> R.drawable.ic_near_light_lamp
                                        else -> null
                                    }
                                )
                            }
                        }

                        Box(
                            modifier = Modifier
                                // .background(Color.Yellow)
                                .padding(shadowPadding)
                                // apply alpha to entire overlay
                                .graphicsLayer(alpha = alpha.value),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(
                                modifier = Modifier
                                    .width(
                                        when (modes.size) {
                                            3 -> (width.value / 1.3f).dp
                                            2 -> (width.value / 1.8f).dp
                                            1 -> (width.value / 3f).dp
                                            else -> width
                                        }
                                    )
                                    .shadow(
                                        elevation = shadow,
                                        shape = border
                                    )
                                    .background(AppTheme.colors.lampBackground)
                            ) {
                                Spacer(Modifier.height(verticalPadding))

                                val singleMode = modes.size == 1
                                Text(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = horizontalPadding),
                                    text = stringResource(if (singleMode) R.string.headlight else R.string.headlight_settings),
                                    style = titleStyle,
                                    textAlign = if (singleMode) TextAlign.Center else TextAlign.Start,
                                    color = AppTheme.colors.contentPrimary
                                )
                                Spacer(Modifier.height(innerVerticalPadding))

                                Box(
                                    Modifier
                                        .padding(horizontal = selectorHorizontalPadding)
                                        .clip(RoundedCornerShape(cornerSelector))
                                        .background(AppTheme.colors.lampSelectorBg)
                                        .padding(selectorItemsOffset)
                                ) {
                                    OverlaySegmentToggler(
                                        modifier = Modifier
                                            .fillMaxWidth(),
                                        scale = scale,
                                        selectedIndex = modes.indexOf(current).takeIf { it != -1 }
                                            ?: 0,
                                        items = list,
                                    ) {
                                        stateKeeper.setLampMode(modes.getOrNull(it) ?: 0)
                                    }
                                }

                                Spacer(Modifier.height(verticalPadding))
                            }

                        }
                    }
                }
            }
        }

        // 3. Create LayoutParams with the required dimensions and position
        lampModeWindowParams = WindowManager.LayoutParams(
            overlayWidth, // WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY, // TYPE_ACCESSIBILITY_OVERLAY
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or
                    WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL or
                    WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN,
            PixelFormat.TRANSLUCENT
        ).apply {
            // Position the window from the top-left corner with marginPx offset
            gravity = Gravity.TOP or Gravity.CENTER
            // x = marginPx
            y = marginPx + topMargin
        }

        runCatching { windowManager.addView(lampModeContainer, lampModeWindowParams) }
            .onFailure { Timber.e(it) }
    }

    override fun onDestroy() {
        super.onDestroy()
        // Finish Compose lifecycle to avoid leaks
        runCatching { composeLifecycleOwner.setCurrentState(Lifecycle.State.DESTROYED) }
        // Cancel service scope (stops debounce collector etc.)
        serviceScope.cancel()
        hideLampModeOverlay() // idemponent
    }

    override fun onBind(intent: Intent?): IBinder? = null

    private fun hideLampModeOverlay() {
        if (!isClosing.get()) isClosing.set(true)

        lampModeContainer?.let { view ->
            runCatching {
                // Remove only if actually attached to avoid IllegalArgumentException
                if (view.isAttachedToWindow) windowManager.removeView(view)
            }
            lampModeContainer = null
        }

        // Ensure no background coroutines survive
        serviceScope.cancel()

        // Keep your original contract:
        stopOverlay<LampModeOverlayService>(this) // If you prefer, stopSelf() is simpler inside the Service
    }
}
