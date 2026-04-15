package com.salat.gbinder.statekeeper.data.repository

import com.salat.gbinder.statekeeper.domain.entity.AccessibilityServiceSignal
import com.salat.gbinder.statekeeper.domain.entity.ActionPropertyTask
import com.salat.gbinder.statekeeper.domain.entity.HandleMediaSessionState
import com.salat.gbinder.statekeeper.domain.entity.LauncherActivitySignal
import com.salat.gbinder.statekeeper.domain.entity.LauncherManagerState
import com.salat.gbinder.statekeeper.domain.entity.LauncherOverlaySignal
import com.salat.gbinder.statekeeper.domain.repository.StateKeeperRepository
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class StateKeeperRepositoryImpl : StateKeeperRepository {
    private companion object {
        const val MAX_PKG_STORY_SIZE = 12
        val SKIP_PKG_STORY = listOf(
            "com.android.launcher3"
        )
    }

    private val _launcherManagerState = MutableStateFlow(LauncherManagerState())
    override val launcherManagerState = _launcherManagerState.asStateFlow()

    private val _handleMediaSessionState = MutableStateFlow(HandleMediaSessionState())
    override val handleMediaSessionState = _handleMediaSessionState.asStateFlow()

    private val _canAccessibility = MutableStateFlow(false)
    override val canAccessibility = _canAccessibility.asStateFlow()

    override fun setLauncherManagerState(state: LauncherManagerState) {
        _launcherManagerState.update { state }
    }

    override fun setHandleMediaSessionState(state: HandleMediaSessionState) {
        _handleMediaSessionState.update { state }
    }

    override fun setCanAccessibility(value: Boolean) {
        _canAccessibility.update { value }
    }

    private val _logChannel = MutableSharedFlow<Pair<String, Boolean>>(
        replay = 0,
        extraBufferCapacity = 25,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    override val logChannel = _logChannel.asSharedFlow()

    override fun sendLog(msg: String, isDeep: Boolean) {
        _logChannel.tryEmit(msg to isDeep)
    }

    /**
     * Accessibility send new app visible flow
     */
    private val _visibleApps = MutableStateFlow(emptyList<String>())
    override val visibleAppsState = _visibleApps.asStateFlow()

    private val _visibleApp = MutableStateFlow("")
    override val visibleAppState = _visibleApp.asStateFlow()

    override fun setVisibleApp(pkg: String, skipHistory: Boolean) {
        if (pkg.isEmpty()) return
        val changed = _visibleApp.value != pkg

        // Update last package
        _visibleApp.update { pkg }

        if (skipHistory || !changed || pkg in SKIP_PKG_STORY) return
        // Update packages story
        _visibleApps.update { current ->
            val withoutPkg = current.filterNot { it == pkg }
            val tail = if (withoutPkg.size >= MAX_PKG_STORY_SIZE) {
                withoutPkg.subList(0, MAX_PKG_STORY_SIZE - 1)
            } else withoutPkg
            listOf(pkg) + tail
        }
    }

    /**
     * Enable|Disable cameras
     */
    private val _toggleCameraFlow = MutableSharedFlow<Boolean>(
        replay = 0,
        extraBufferCapacity = 25,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    override val toggleCameraFlow = _toggleCameraFlow.asSharedFlow()

    override fun setToggleCamera(value: Boolean) {
        _toggleCameraFlow.tryEmit(value)
    }

    /**
     * Apply custom * action
     */
    private val _funCustomKeyFlow = MutableSharedFlow<Int>(
        replay = 0,
        extraBufferCapacity = 25,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    override val funCustomKeyFlow: SharedFlow<Int> = _funCustomKeyFlow.asSharedFlow()

    override fun setFunCustomKey(key: Int) {
        _funCustomKeyFlow.tryEmit(key)
    }

    /**
     * Toggle light from overlay
     */
    private val _setLampModeFlow = MutableSharedFlow<Int>(
        replay = 0,
        extraBufferCapacity = 25,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    override val setLampModeFlow: SharedFlow<Int> = _setLampModeFlow.asSharedFlow()

    override fun setLampMode(mode: Int) {
        _setLampModeFlow.tryEmit(mode)
    }

    /**
     * Support function request
     */
    private val _sendCustomKeySupportFlow = MutableSharedFlow<Unit>(
        replay = 0,
        extraBufferCapacity = 25,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    override val funCustomKeySupportFlow = _sendCustomKeySupportFlow.asSharedFlow()

    override fun sendCustomKeySupport() {
        _sendCustomKeySupportFlow.tryEmit(Unit)
    }

    /**
     * Set car function property value
     */
    private val _propertyTaskFlow = MutableSharedFlow<ActionPropertyTask>(
        replay = 0,
        extraBufferCapacity = 25,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    override val propertyTaskFlow = _propertyTaskFlow.asSharedFlow()

    override fun setPropertyTask(task: ActionPropertyTask) {
        _propertyTaskFlow.tryEmit(task)
    }

    /**
     * Watch function changes in configurator
     */
    private val _configuratorOpenedFunction = MutableStateFlow<Int?>(null)
    override val configuratorOpenedFunction = _configuratorOpenedFunction.asStateFlow()
    override fun setConfiguratorOpenedFunction(id: Int?) {
        _configuratorOpenedFunction.value = id
    }

    private val _configuratorIntFunctionValueFlow = MutableSharedFlow<Triple<Int, Int, Int>>(
        replay = 0,
        extraBufferCapacity = 25,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    override val configuratorIntFunctionValueFlow =
        _configuratorIntFunctionValueFlow.asSharedFlow()

    override fun sendConfiguratorIntFunctionValue(id: Int, area: Int, value: Int) {
        _configuratorIntFunctionValueFlow.tryEmit(Triple(id, area, value))
    }

    private val _configuratorFloatFunctionValueFlow = MutableSharedFlow<Triple<Int, Int, Float>>(
        replay = 0,
        extraBufferCapacity = 25,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    override val configuratorFloatFunctionValueFlow =
        _configuratorFloatFunctionValueFlow.asSharedFlow()

    override fun sendConfiguratorFloatFunctionValue(id: Int, area: Int, value: Float) {
        _configuratorFloatFunctionValueFlow.tryEmit(Triple(id, area, value))
    }

    /**
     * Watch sensor changes in configurator
     */
    private val _configuratorOpenedSensor = MutableStateFlow<Int?>(null)
    override val configuratorOpenedSensor = _configuratorOpenedSensor.asStateFlow()
    override fun setConfiguratorOpenedSensor(id: Int?) {
        _configuratorOpenedSensor.value = id
    }

    private val _configuratorSensorIntValueFlow = MutableSharedFlow<Pair<Int, Int>>(
        replay = 0,
        extraBufferCapacity = 25,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    override val configuratorIntSensorValueFlow =
        _configuratorSensorIntValueFlow.asSharedFlow()

    override fun sendConfiguratorIntSensorValue(id: Int, value: Int) {
        _configuratorSensorIntValueFlow.tryEmit(id to value)
    }

    private val _configuratorSensorFloatValueFlow = MutableSharedFlow<Pair<Int, Float>>(
        replay = 0,
        extraBufferCapacity = 25,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    override val configuratorFloatSensorValueFlow =
        _configuratorSensorFloatValueFlow.asSharedFlow()

    override fun sendConfiguratorFloatSensorValue(id: Int, value: Float) {
        _configuratorSensorFloatValueFlow.tryEmit(id to value)
    }

    private val _notifPlayTestFlow = MutableSharedFlow<Pair<Int, Float>>(
        replay = 0,
        extraBufferCapacity = 12,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    override val notifPlayTestFlow = _notifPlayTestFlow.asSharedFlow()

    override fun sendNotifPlayTest(sampleId: Int, volume: Float) {
        _notifPlayTestFlow.tryEmit(sampleId to volume)
    }

    /**
     * Displayed drive mode in overlay
     */
    private val _driveModeInOverlay = MutableStateFlow<Int?>(null)
    override val driveModeInOverlay = _driveModeInOverlay.asStateFlow()
    override fun setDriveModeInOverlay(id: Int?) {
        _driveModeInOverlay.value = id
    }

    /**
     * Displayed lamp mode in overlay (list + current value)
     */
    private val _lampModeInOverlay = MutableStateFlow<Pair<List<Int>, Int>?>(null)
    override val lampModeInOverlay = _lampModeInOverlay.asStateFlow()
    override fun setLampModeInOverlay(lampState: Pair<List<Int>, Int>?) {
        _lampModeInOverlay.value = lampState
    }

    /**
     * Activity + Overlay chain state
     */
    private val _launcherOverlayEnabled = MutableStateFlow(false)
    override val launcherOverlayEnabled = _launcherOverlayEnabled.asStateFlow()
    override fun setLauncherOverlayEnabled(isEnabled: Boolean) {
        _launcherOverlayEnabled.value = isEnabled
    }

    private val _launcherActivityEnabled = MutableStateFlow(false)
    override val launcherActivityEnabled = _launcherActivityEnabled.asStateFlow()
    override fun setLauncherActivityEnabled(isEnabled: Boolean) {
        _launcherActivityEnabled.value = isEnabled
    }

    /**
     * Lifecycle actions
     */
    private val _launcherActivitySignalsFlow = MutableSharedFlow<LauncherActivitySignal>(
        replay = 0,
        extraBufferCapacity = 12,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    override val launcherActivitySignalFlow = _launcherActivitySignalsFlow.asSharedFlow()
    override fun sendLauncherActivitySignal(action: LauncherActivitySignal) {
        _launcherActivitySignalsFlow.tryEmit(action)
    }

    private val _launcherOverlaySignalFlow = MutableSharedFlow<LauncherOverlaySignal>(
        replay = 0,
        extraBufferCapacity = 12,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    override val launcherOverlaySignalFlow = _launcherOverlaySignalFlow.asSharedFlow()
    override fun sendLauncherOverlaySignal(action: LauncherOverlaySignal) {
        _launcherOverlaySignalFlow.tryEmit(action)
    }

    /**
     * Special Accessibility Service actions
     */
    private val _accessibilityServiceSignalsFlow = MutableSharedFlow<AccessibilityServiceSignal>(
        replay = 0,
        extraBufferCapacity = 12,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    override val accessibilityServiceSignalFlow = _accessibilityServiceSignalsFlow.asSharedFlow()
    override fun sendAccessibilityServiceSignal(action: AccessibilityServiceSignal) {
        _accessibilityServiceSignalsFlow.tryEmit(action)
    }

    /**
     * Toggle launcher event
     */
    private val _toggleLauncherFlow = MutableSharedFlow<Unit>(
        replay = 0,
        extraBufferCapacity = 12,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    override val toggleLauncherFlow = _toggleLauncherFlow.asSharedFlow()

    override fun toggleLauncher() {
        _toggleLauncherFlow.tryEmit(Unit)
    }

    /**
     * Task manager event
     */
    private val _taskManagerFlow = MutableSharedFlow<Unit>(
        replay = 0,
        extraBufferCapacity = 12,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    override val taskManagerFlow = _taskManagerFlow.asSharedFlow()

    override fun callTaskManager() {
        _taskManagerFlow.tryEmit(Unit)
    }

    /**
     * Close launcher activity time
     */
    private val _launcherActivityCloseTime = MutableStateFlow(0L)
    override val launcherActivityCloseTime = _launcherActivityCloseTime.asStateFlow()
    override fun setLauncherActivityCloseTime(timestamp: Long) {
        _launcherActivityCloseTime.value = timestamp
    }

    /**
     * Ui scale data
     */
    private val _uiScales = MutableStateFlow<Pair<Float, Float>?>(null)
    override val uiScales = _uiScales.asStateFlow()
    override fun setUiScale(data: Pair<Float, Float>?) {
        _uiScales.value = data
    }

}
