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
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.hideFromAccessibility
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.NotificationCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.setViewTreeLifecycleOwner
import androidx.savedstate.setViewTreeSavedStateRegistryOwner
import com.geely.lib.oneosapi.navi.base.DrawableRes
import com.salat.gbinder.car.data.CarPropertyValue
import com.salat.gbinder.components.ComposeWindowLifecycleOwner
import com.salat.gbinder.components.toPxInt
import com.salat.gbinder.datastore.DataStoreRepository
import com.salat.gbinder.datastore.GeneralPrefs
import com.salat.gbinder.statekeeper.domain.repository.StateKeeperRepository
import com.salat.gbinder.ui.clickableNoRipple
import com.salat.gbinder.ui.theme.AppTheme
import com.salat.gbinder.util.getOverlayDriveModeName
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.util.concurrent.atomic.AtomicBoolean
import javax.inject.Inject
import kotlin.math.roundToInt

const val DRIVE_MODE_DEFAULT_OVERLAY_OFFSET = 10f // in percentage terms

@AndroidEntryPoint
class DriveModeOverlayService : Service() {

    companion object {
        private const val CHANNEL_ID = "dm_overlay_service_channel"
        private const val DRIVE_MODE_OVERLAY = 2001
        private const val CLOSE_DEBOUNCE_MS = 3500L
        private const val FADE_IN_MS = 180L
        private const val FADE_OUT_MS = 120L
    }

    @Inject
    lateinit var stateKeeper: StateKeeperRepository

    @Inject
    lateinit var dataStore: DataStoreRepository

    private lateinit var windowManager: WindowManager

    // drive mode params
    private var driveModeContainer: ComposeView? = null
    private var driveModeWindowParams: WindowManager.LayoutParams? = null
    private lateinit var composeLifecycleOwner: ComposeWindowLifecycleOwner

    private val serviceScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    // Prolongation events: emit on create and on each non-null drive mode change
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
                "Drive Mode Overlay",
                NotificationManager.IMPORTANCE_LOW
            ).apply {
                description = "Show drive mode changes"
            }
            (getSystemService(NOTIFICATION_SERVICE) as NotificationManager)
                .createNotificationChannel(channel)
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int) = START_STICKY

    private fun buildNotification() = NotificationCompat.Builder(this, CHANNEL_ID)
        .setContentTitle("Drive Mode Overlay")
        .setContentText("Show Drive Mode changes")
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
        startForeground(DRIVE_MODE_OVERLAY, buildNotification())

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

        if (driveModeContainer != null) return

        serviceScope.launch {
            val initOverlayExtraScale = withContext(Dispatchers.IO) {
                dataStore
                    .getValueFlow(GeneralPrefs.DM_OVERLAY_SCALE)
                    .first() ?: 0f
            }
            val initOverlayExtraHeight = withContext(Dispatchers.IO) {
                dataStore
                    .getValueFlow(GeneralPrefs.DM_OVERLAY_OFFSET)
                    .first() ?: 0f
            }
            setupDriveModeOverlay(initOverlayExtraScale, initOverlayExtraHeight)
        }
    }

    private fun setupDriveModeOverlay(initOverlayExtraScale: Float, initOverlayExtraHeight: Float) {
        // 1. Calculate margin in pixels (10 dp → px)
        val marginPx = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            10f,
            resources.displayMetrics
        ).toInt()

        // 2. Get screen dimensions
        val displayMetrics = resources.displayMetrics
        val correctHeight =
            ((displayMetrics.heightPixels.toFloat() / 100f) * initOverlayExtraHeight).toInt()
        val topMargin =
            ((displayMetrics.heightPixels / 100f) * DRIVE_MODE_DEFAULT_OVERLAY_OFFSET).toInt() + correctHeight

        val scale = (stateKeeper.uiScales.value?.first ?: DEFAULT_UI_SCALE) + initOverlayExtraScale
        val baseWidth = 280
        val baseOutput = 16

        val overlayWidth =
            (baseWidth * scale).roundToInt().dp.toPxInt + ((baseOutput * 2) * scale).roundToInt().dp.toPxInt

        driveModeContainer = ComposeView(this).apply {
            setViewTreeLifecycleOwner(composeLifecycleOwner)
            setViewTreeSavedStateRegistryOwner(composeLifecycleOwner)
            setContent {

                // Scale config
                val border =
                    remember { RoundedCornerShape((16 * scale).roundToInt().dp) }
                // val height = 140
                val width = remember { (baseWidth * scale).roundToInt().dp }
                val fontStroke = remember { (3 * scale).roundToInt().dp }
                val fontSize = remember { (42 * scale).roundToInt().sp }
                val topPadding = remember { (12 * scale).roundToInt().dp }
                val shadow = remember { (8 * scale).roundToInt().dp }
                val shadowPadding = remember { (baseOutput * scale).roundToInt().dp }

                /* val overlayExtraScale by DataStoreManager(this@DriveModeOverlayService)
                    .getValueFlow(Prefs.DM_OVERLAY_SCALE)
                    .map { it ?: initOverlayExtraScale }
                    .collectAsStateWithLifecycle(initialValue = initOverlayExtraScale) */

                AppTheme(darkTheme = true) {
                    val driveMode by stateKeeper.driveModeInOverlay.collectAsStateWithLifecycle()

                    // animatable alpha for fade in/out
                    val alpha = remember { androidx.compose.animation.core.Animatable(0f) }

                    // On first composition, fade in
                    LaunchedEffect(Unit) {
                        /* Smooth fade-in on appear */
                        alpha.snapTo(0f)
                        alpha.animateTo(
                            targetValue = 1f,
                            animationSpec = androidx.compose.animation.core.tween(durationMillis = FADE_IN_MS.toInt())
                        )
                    }

                    // Prolong close timer on each non-null drive-mode emission
                    LaunchedEffect(driveMode) {
                        if (driveMode != null) {
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
                                    animationSpec = androidx.compose.animation.core.tween(
                                        durationMillis = FADE_OUT_MS.toInt()
                                    )
                                )
                                // now actually remove the view and stop service
                                hideDriveModeOverlay()
                            }
                        }
                    }

                    driveMode?.let { dm ->
                        Box(
                            modifier = Modifier
                                // .background(Color.Yellow.copy(.2f))
                                .clickableNoRipple {
                                    // Immediate close request (will fade out safely)
                                    closeSignal.tryEmit(Unit)
                                }
                                .padding(shadowPadding)
                                // apply alpha to entire overlay
                                .graphicsLayer(alpha = alpha.value)
                        ) {

                            val contrast = remember(dm) {
                                dm in listOf(
                                    CarPropertyValue.DRIVE_MODE_SELECTION_PURE,
                                    CarPropertyValue.DRIVE_MODE_SELECTION_SNOW
                                )
                            }

                            Box(
                                modifier = Modifier
                                    .width(width)
                                    .aspectRatio(2f)
                                    .shadow(
                                        elevation = shadow,
                                        shape = border
                                    )
                            ) {
                                @DrawableRes val resId = remember(dm) {
                                    when (dm) {
                                        570491158 -> R.drawable.dm_smart
                                        570491150 -> R.drawable.dm_awd
                                        570491138 -> R.drawable.dm_comfort
                                        570491200 -> R.drawable.dm_custom
                                        570491139 -> R.drawable.dm_sport
                                        570491154 -> R.drawable.dm_eawd
                                        570491137 -> R.drawable.dm_eco
                                        570491152 -> R.drawable.dm_eco_phev
                                        570491141 -> R.drawable.dm_hdc
                                        570491143 -> R.drawable.dm_hybrid
                                        570491146 -> R.drawable.dm_mud
                                        570491153 -> R.drawable.dm_normal
                                        570491155 -> R.drawable.dm_offroad
                                        570491148 -> R.drawable.dm_phev
                                        570491144 -> R.drawable.dm_power
                                        570491142 -> R.drawable.dm_pure
                                        570491147 -> R.drawable.dm_rock
                                        570491149 -> R.drawable.dm_sand
                                        570491151 -> R.drawable.dm_save
                                        570491145 -> R.drawable.dm_snow
                                        570491140 -> R.drawable.dm_xc
                                        570491157 -> R.drawable.dm_sport_plus
                                        else -> R.drawable.dm_unknown
                                    }
                                }

                                Image(
                                    painterResource(resId),
                                    contentScale = ContentScale.Crop,
                                    contentDescription = "drive mode",
                                    modifier = Modifier.fillMaxWidth(),
                                )

                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Spacer(Modifier.height(topPadding))

                                    val baseTitle = AppTheme.typography.overlayTitle.copy(
                                        fontSize = fontSize
                                    )
                                    OutlinedText(
                                        text = dm.getOverlayDriveModeName(),
                                        style = baseTitle,
                                        fillColor = if (contrast) {
                                            AppTheme.colors.surfaceBackground
                                        } else Color.White,
                                        strokeColor = Color.Black.copy(.3f),
                                        strokeWidth = fontStroke
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

        // 3. Create LayoutParams with the required dimensions and position
        driveModeWindowParams = WindowManager.LayoutParams(
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

        runCatching { windowManager.addView(driveModeContainer, driveModeWindowParams) }
            .onFailure { Timber.e(it) }
    }

    override fun onDestroy() {
        super.onDestroy()
        // Finish Compose lifecycle to avoid leaks
        runCatching { composeLifecycleOwner.setCurrentState(Lifecycle.State.DESTROYED) }
        // Cancel service scope (stops debounce collector etc.)
        serviceScope.cancel()
        hideDriveModeOverlay() // idemponent
    }

    override fun onBind(intent: Intent?): IBinder? = null

    private fun hideDriveModeOverlay() {
        if (!isClosing.get()) isClosing.set(true)

        driveModeContainer?.let { view ->
            runCatching {
                // Remove only if actually attached to avoid IllegalArgumentException
                if (view.isAttachedToWindow) windowManager.removeView(view)
            }
            driveModeContainer = null
        }

        // Ensure no background coroutines survive
        serviceScope.cancel()

        // Keep your original contract:
        stopOverlay<DriveModeOverlayService>(this) // If you prefer, stopSelf() is simpler inside the Service
    }

    @Composable
    fun OutlinedText(
        modifier: Modifier = Modifier,
        text: String,
        style: TextStyle,
        fillColor: Color,
        strokeColor: Color = Color.Black,
        strokeWidth: Dp = 1.dp
    ) {
        val strokePx = with(LocalDensity.current) { strokeWidth.toPx() }

        Box(modifier) {
            // Outline (hidden from accessibility to avoid duplicate announcement)
            Text(
                text = text,
                color = strokeColor,
                style = style.copy(
                    drawStyle = Stroke(width = strokePx),
                    // keep color param as the source of color; style.color may stay Unspecified
                ),
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                modifier = Modifier.semantics { hideFromAccessibility() }
            )
            // Fill
            Text(
                text = text,
                color = fillColor,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                style = style
            )
        }
    }
}
