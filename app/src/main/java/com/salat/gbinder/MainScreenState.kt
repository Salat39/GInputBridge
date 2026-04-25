package com.salat.gbinder

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.saveable.listSaver

// The default parameter values are defined here
@Immutable
internal data class MainScreenState(
    val isEnabled: Boolean = false,
    val isDebugMode: Boolean = false,
    val fullBroadcast: Boolean = true,
    val trackKeyEvents: Boolean = false,
    val enableCustomLongClick: Boolean = false,
    val customLongClickTiming: Int = 1000,
    val lockDoubleClick: Boolean = false,
    val doubleClickTime: Int = 300,
    val enabledCustomShortClick: Boolean = false,
    val multiLongPressEnabled: Boolean = false,
    val suppressionMode: Boolean = false,
    val disableOnClimate: Boolean = false,
    val sourceManagement: Boolean = false,
    val radioBtControl: Boolean = true,
    val rememberDriveMode: Boolean = false,
    val targetRecoveryDriveMode: Int = LAST_DM_ID,
    val driveModeOverlay: Boolean = false,
    val driveModeOverlayScale: Float = 0f,
    val driveModeOverlayOffset: Float = 0f,
    val configuratorWarning: Boolean = true,
    val mediaDataTranslator: Boolean = false,
    val deepLogs: Boolean = false,
    val mediaControlEnabled: Boolean = false,
    val enableAdbHelper: Boolean = false,
    val adbHelperPort: Int = 5555,
    val adbDimAutoStop: Boolean = false,
    val altMenu: Boolean = true,
    val altMute: Boolean = true,
    val altLongTime: Int = ADDITIONAL_KEYS_MIN_LONG_PRESS_TIME
) {
    fun updateFrom(row: List<Any?>): MainScreenState {
        return copy(
            isEnabled = row[0] as Boolean,
            isDebugMode = row[1] as Boolean,
            fullBroadcast = row[2] as Boolean,
            trackKeyEvents = row[3] as Boolean,
            enableCustomLongClick = row[4] as Boolean,
            customLongClickTiming = row[5] as Int,
            lockDoubleClick = row[6] as Boolean,
            doubleClickTime = row[7] as Int,
            enabledCustomShortClick = row[8] as Boolean,
            multiLongPressEnabled = row[9] as Boolean,
            suppressionMode = row[10] as Boolean,
            disableOnClimate = row[11] as Boolean,
            sourceManagement = row[12] as Boolean,
            radioBtControl = row[13] as Boolean,
            rememberDriveMode = row[14] as Boolean,
            targetRecoveryDriveMode = row[15] as Int,
            driveModeOverlay = row[16] as Boolean,
            driveModeOverlayScale = row[17] as Float,
            driveModeOverlayOffset = row[18] as Float,
            configuratorWarning = row[19] as Boolean,
            mediaDataTranslator = row[20] as Boolean,
            deepLogs = row[21] as Boolean,
            mediaControlEnabled = row[22] as Boolean,
            enableAdbHelper = row[23] as Boolean,
            adbHelperPort = row[24] as Int,
            adbDimAutoStop = row[25] as Boolean,
            altMenu = row[26] as Boolean,
            altMute = row[27] as Boolean,
            altLongTime = row[28] as Int
        )
    }

    fun toSettingsRow() = listOf(
        isEnabled,
        isDebugMode,
        fullBroadcast,
        trackKeyEvents,
        enableCustomLongClick,
        customLongClickTiming,
        lockDoubleClick,
        doubleClickTime,
        enabledCustomShortClick,
        multiLongPressEnabled,
        suppressionMode,
        disableOnClimate,
        sourceManagement,
        radioBtControl,
        rememberDriveMode,
        targetRecoveryDriveMode,
        driveModeOverlay,
        driveModeOverlayScale,
        driveModeOverlayOffset,
        configuratorWarning,
        mediaDataTranslator,
        deepLogs,
        mediaControlEnabled,
        enableAdbHelper,
        adbHelperPort,
        adbDimAutoStop,
        altMenu,
        altMute,
        altLongTime
    )

    companion object {
        val Default = MainScreenState()

        val saver = listSaver(
            save = { it.toSettingsRow() },
            restore = { values -> Default.updateFrom(values) }
        )
    }
}
