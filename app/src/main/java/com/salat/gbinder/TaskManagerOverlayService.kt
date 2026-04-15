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
import android.view.WindowManager
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import androidx.core.app.NotificationCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.setViewTreeLifecycleOwner
import androidx.savedstate.setViewTreeSavedStateRegistryOwner
import com.salat.gbinder.adb.domain.repository.AdbRepository
import com.salat.gbinder.components.ComposeWindowLifecycleOwner
import com.salat.gbinder.entity.DisplayRecentTaskInfo
import com.salat.gbinder.features.launcher.LauncherDataRepository
import com.salat.gbinder.mappers.toDisplay
import com.salat.gbinder.statekeeper.domain.repository.StateKeeperRepository
import com.salat.gbinder.ui.clickableNoRipple
import com.salat.gbinder.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.util.concurrent.atomic.AtomicBoolean
import javax.inject.Inject

@AndroidEntryPoint
class TaskManagerOverlayService : Service() {

    companion object {
        private const val CHANNEL_ID = "tm_overlay_service_channel"
        private const val TASK_MANAGER_OVERLAY = 2008
    }

    @Inject
    lateinit var stateKeeper: StateKeeperRepository

    @Inject
    lateinit var data: LauncherDataRepository

    @Inject
    lateinit var adb: AdbRepository

    private lateinit var windowManager: WindowManager

    // task manager params
    private var taskManagerContainer: ComposeView? = null
    private var taskManagerWindowParams: WindowManager.LayoutParams? = null
    private lateinit var composeLifecycleOwner: ComposeWindowLifecycleOwner

    private val serviceScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    // Ensure we close exactly once
    private val isClosing = AtomicBoolean(false)

    @SuppressLint("ObsoleteSdkInt")
    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "Task Manager Overlay",
                NotificationManager.IMPORTANCE_LOW
            ).apply {
                description = "Show Task manager"
            }
            (getSystemService(NOTIFICATION_SERVICE) as NotificationManager)
                .createNotificationChannel(channel)
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int) = START_STICKY

    private fun buildNotification() = NotificationCompat.Builder(this, CHANNEL_ID)
        .setContentTitle("Task manager Overlay")
        .setContentText("Show Task manager")
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
        startForeground(TASK_MANAGER_OVERLAY, buildNotification())

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(this)) {
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

        if (taskManagerContainer != null) return

        setupTaskManagerOverlay()
    }

    private fun setupTaskManagerOverlay() {
        taskManagerContainer = ComposeView(this).apply {
            setViewTreeLifecycleOwner(composeLifecycleOwner)
            setViewTreeSavedStateRegistryOwner(composeLifecycleOwner)
            setContent {
                var uiScale by remember {
                    mutableFloatStateOf(stateKeeper.uiScales.value?.first ?: DEFAULT_UI_SCALE)
                }
                var items by remember { mutableStateOf<List<DisplayRecentTaskInfo>?>(null) }

                LaunchedEffect(Unit) {
                    withContext(Dispatchers.IO) {
                        val allApps = buildMap {
                            data.allApps.value.forEach { put(it.packageName, it) }
                        }
                        val tasks = adb.getRecentTasksFromActivitiesDump()
                        items = buildList {
                            tasks.forEach { task ->
                                if (task.packageName in allApps) add(task.toDisplay(allApps[task.packageName]!!))
                            }
                        }
                    }
                }

                val itm = items
                if (itm != null) {
                    val density = LocalDensity.current
                    val scaledDensity = remember(density) {
                        Density(
                            density.density * uiScale,
                            density.fontScale * uiScale
                        )
                    }

                    AppTheme(darkTheme = true) {
                        CompositionLocalProvider(LocalDensity provides scaledDensity) {
                            RenderTaskManager(itm)
                        }
                    }
                }
            }
        }

        // 3. Create LayoutParams with the required dimensions and position
        taskManagerWindowParams = WindowManager.LayoutParams(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or
                    WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL or
                    WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN,
            PixelFormat.TRANSLUCENT
        )

        runCatching { windowManager.addView(taskManagerContainer, taskManagerWindowParams) }
            .onFailure { Timber.e(it) }
    }

    @Composable
    private fun RenderTaskManager(items: List<DisplayRecentTaskInfo>) = Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White.copy(.1f))
            .clickableNoRipple(onClick = ::hideTaskManagerOverlay),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .padding(42.dp)
                .shadow(
                    elevation = 4.dp,
                    shape = RoundedCornerShape(12.dp)
                )
                .background(AppTheme.colors.launcherBackground)
                .clickableNoRipple {}
                .padding(20.dp)
        ) {
            items.forEach {
                Text(
                    it.appName,
                    color = Color.White,
                    style = AppTheme.typography.cardTitle
                )
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        // Finish Compose lifecycle to avoid leaks
        runCatching { composeLifecycleOwner.setCurrentState(Lifecycle.State.DESTROYED) }
        // Cancel service scope (stops debounce collector etc.)
        serviceScope.cancel()
        hideTaskManagerOverlay() // idemponent
    }

    override fun onBind(intent: Intent?): IBinder? = null

    private fun hideTaskManagerOverlay() {
        if (!isClosing.get()) isClosing.set(true)

        taskManagerContainer?.let { view ->
            runCatching {
                // Remove only if actually attached to avoid IllegalArgumentException
                if (view.isAttachedToWindow) windowManager.removeView(view)
            }
            taskManagerContainer = null
        }

        // Ensure no background coroutines survive
        serviceScope.cancel()

        // Keep your original contract:
        stopOverlay<TaskManagerOverlayService>(this) // If you prefer, stopSelf() is simpler inside the Service
    }
}
