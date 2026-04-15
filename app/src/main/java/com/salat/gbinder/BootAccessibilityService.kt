package com.salat.gbinder

import android.accessibilityservice.AccessibilityService
import android.accessibilityservice.AccessibilityServiceInfo
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityWindowInfo
import com.salat.gbinder.repository.AccessibilityRepository
import com.salat.gbinder.repository.LogRepository
import com.salat.gbinder.statekeeper.domain.entity.AccessibilityServiceSignal
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class BootAccessibilityService : AccessibilityService() {
    private val handler = CoroutineExceptionHandler { _, e -> Timber.e(e) }
    private val serviceScope = CoroutineScope(SupervisorJob() + Dispatchers.IO + handler)

    private val stateChangeFlow = MutableSharedFlow<Pair<Int, String>>(
        replay = 0,
        extraBufferCapacity = 16,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    @Inject
    lateinit var accessibility: AccessibilityRepository

    @Inject
    lateinit var logs: LogRepository

    override fun onCreate() {
        super.onCreate()
        logs.deepLog("[AS] Created")

        serviceScope.launch(Dispatchers.Main) {
            stateChangeFlow
                //.debounce(150)
                .collect { (id, pkg) -> collectWindows(id, pkg) }
        }
        serviceScope.launch(Dispatchers.IO) {
            accessibility.actionSignalsFlow.collect {
                when (it) {
                    AccessibilityServiceSignal.GoHome -> goHome()

                    AccessibilityServiceSignal.GoBack -> goBack()
                }
            }
        }
    }

    override fun onServiceConnected() {
        super.onServiceConnected()

        configureAccessibilityService()
        accessibility.setCanAccessibility(true)
        logs.deepLog("[AS] Connected")
    }

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        event ?: return

        if (event.eventType == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) {
            val windowId = event.windowId
            val packageName = event.packageName?.toString() ?: return
            stateChangeFlow.tryEmit(windowId to packageName)
        }
    }

    private fun collectWindows(windowId: Int, pkg: String) {
        val pm = getSystemService(POWER_SERVICE) as android.os.PowerManager
        if (!pm.isInteractive) return

        // Access on main is allowed, but be careful to recycle window infos
        val list = windows // snapshot
        var matchedTypeApp = false

        // We must always recycle all window infos we touch
        try {
            for (w in list) {
                if (w.id == windowId) {
                    matchedTypeApp = (w.type == AccessibilityWindowInfo.TYPE_APPLICATION)
                }
            }
        } catch (e: Exception) {
            Timber.e(e)
            return
        } finally {
            try {
                for (w in list) {
                    try {
                        w.recycle()
                    } catch (_: Throwable) {
                    }
                }
            } catch (_: Throwable) {
            }
        }

        if (!matchedTypeApp) return
        try {
            accessibility.setVisibleApp(pkg, packageName == pkg)
        } catch (t: Throwable) {
            Timber.w(t)
        }
    }

    override fun onInterrupt() {}

    override fun onDestroy() {
        super.onDestroy()
        accessibility.setCanAccessibility(false)
        serviceScope.cancel()
    }

    // Performs system-wide "Home" action to return user to the launcher
    private suspend fun goHome() = withContext(Dispatchers.Main) {
        runCatching { performGlobalAction(GLOBAL_ACTION_HOME) }
    }

    // Performs system-wide "Back" action
    private suspend fun goBack() = withContext(Dispatchers.Main) {
        runCatching { performGlobalAction(GLOBAL_ACTION_BACK) }
    }

    /**
     * Configures the Accessibility Service parameters.
     */
    private fun configureAccessibilityService() {
        try {
            val info = AccessibilityServiceInfo().apply {
                eventTypes = AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED
                flags = AccessibilityServiceInfo.FLAG_RETRIEVE_INTERACTIVE_WINDOWS
                feedbackType = AccessibilityServiceInfo.FEEDBACK_GENERIC
                notificationTimeout = 100
            }
            serviceInfo = info
        } catch (e: Exception) {
            Timber.e(e)
        }
    }
}
