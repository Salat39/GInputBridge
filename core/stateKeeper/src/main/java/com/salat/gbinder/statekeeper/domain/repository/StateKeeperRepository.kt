package com.salat.gbinder.statekeeper.domain.repository

import com.salat.gbinder.statekeeper.domain.entity.AccessibilityServiceSignal
import com.salat.gbinder.statekeeper.domain.entity.ActionPropertyTask
import com.salat.gbinder.statekeeper.domain.entity.HandleMediaSessionState
import com.salat.gbinder.statekeeper.domain.entity.LauncherActivitySignal
import com.salat.gbinder.statekeeper.domain.entity.LauncherManagerState
import com.salat.gbinder.statekeeper.domain.entity.LauncherOverlaySignal
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

interface StateKeeperRepository {
    val launcherManagerState: StateFlow<LauncherManagerState>
    val handleMediaSessionState: StateFlow<HandleMediaSessionState>
    val canAccessibility: StateFlow<Boolean>

    fun setLauncherManagerState(state: LauncherManagerState)
    fun setHandleMediaSessionState(state: HandleMediaSessionState)
    fun setCanAccessibility(value: Boolean)

    val logChannel: SharedFlow<Pair<String, Boolean>>
    fun sendLog(msg: String, isDeep: Boolean)

    val visibleAppsState: StateFlow<List<String>>
    val visibleAppState: StateFlow<String>
    fun setVisibleApp(pkg: String, skipHistory: Boolean = false)

    val toggleCameraFlow: SharedFlow<Boolean>
    fun setToggleCamera(value: Boolean)

    val funCustomKeyFlow: SharedFlow<Int>
    fun setFunCustomKey(key: Int)

    val funCustomKeySupportFlow: SharedFlow<Unit>
    fun sendCustomKeySupport()

    val propertyTaskFlow: SharedFlow<ActionPropertyTask>
    fun setPropertyTask(task: ActionPropertyTask)

    val setLampModeFlow: SharedFlow<Int>
    fun setLampMode(mode: Int)

    val configuratorOpenedFunction: StateFlow<Int?>
    fun setConfiguratorOpenedFunction(id: Int?)

    val configuratorIntFunctionValueFlow: SharedFlow<Triple<Int, Int, Int>>
    fun sendConfiguratorIntFunctionValue(id: Int, area: Int, value: Int)

    val configuratorFloatFunctionValueFlow: SharedFlow<Triple<Int, Int, Float>>
    fun sendConfiguratorFloatFunctionValue(id: Int, area: Int, value: Float)

    val configuratorOpenedSensor: StateFlow<Int?>
    fun setConfiguratorOpenedSensor(id: Int?)

    val configuratorIntSensorValueFlow: SharedFlow<Pair<Int, Int>>
    fun sendConfiguratorIntSensorValue(id: Int, value: Int)

    val configuratorFloatSensorValueFlow: SharedFlow<Pair<Int, Float>>
    fun sendConfiguratorFloatSensorValue(id: Int, value: Float)

    val notifPlayTestFlow: SharedFlow<Pair<Int, Float>> // sampleId, volume
    fun sendNotifPlayTest(sampleId: Int, volume: Float)

    val driveModeInOverlay: StateFlow<Int?>
    fun setDriveModeInOverlay(id: Int?)

    val lampModeInOverlay: StateFlow<Pair<List<Int>, Int>?>
    fun setLampModeInOverlay(lampState: Pair<List<Int>, Int>?)

    val launcherOverlayEnabled: StateFlow<Boolean>
    fun setLauncherOverlayEnabled(isEnabled: Boolean)

    val launcherActivityEnabled: StateFlow<Boolean>
    fun setLauncherActivityEnabled(isEnabled: Boolean)

    val launcherActivitySignalFlow: SharedFlow<LauncherActivitySignal>
    fun sendLauncherActivitySignal(action: LauncherActivitySignal)

    val launcherOverlaySignalFlow: SharedFlow<LauncherOverlaySignal>
    fun sendLauncherOverlaySignal(action: LauncherOverlaySignal)

    val accessibilityServiceSignalFlow: SharedFlow<AccessibilityServiceSignal>
    fun sendAccessibilityServiceSignal(action: AccessibilityServiceSignal)

    val toggleLauncherFlow: SharedFlow<Unit>
    fun toggleLauncher()

    val taskManagerFlow: SharedFlow<Unit>
    fun callTaskManager()

    val launcherActivityCloseTime: StateFlow<Long>
    fun setLauncherActivityCloseTime(timestamp: Long)

    val uiScales: StateFlow<Pair<Float, Float>?>
    fun setUiScale(data: Pair<Float, Float>?)
}
