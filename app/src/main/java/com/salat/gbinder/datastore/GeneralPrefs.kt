package com.salat.gbinder.datastore

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object GeneralPrefs {
    val DATA_SYNC_ENABLED = booleanPreferencesKey("MEDIA_DATA_SYNC_ENABLED")
    val DEBUG_MODE = booleanPreferencesKey("DEBUG_MODE")
    val FULL_BROADCAST = booleanPreferencesKey("FULL_BROADCAST")
    val TRACK_KEY_EVENTS = booleanPreferencesKey("TRACK_KEY_EVENTS")
    val CUSTOM_LONG_PRESS_ENABLED = booleanPreferencesKey("CUSTOM_LONG_PRESS_ENABLED")
    val CUSTOM_LONG_PRESS_TIME = intPreferencesKey("CUSTOM_LONG_PRESS_TIME")
    val CUSTOM_SHORT_CLICK_ENABLED = booleanPreferencesKey("CUSTOM_SHORT_CLICK_ENABLED")
    val DOUBLE_CLICK_ENABLED = booleanPreferencesKey("DOUBLE_CLICK_ENABLED")
    val DOUBLE_CLICK_TIME = intPreferencesKey("DOUBLE_CLICK_TIME")
    val MULTI_LONG_PRESS_ENABLED = booleanPreferencesKey("MULTI_LONG_PRESS_ENABLED")
    val SUPPRESSION_MODE = booleanPreferencesKey("SUPPRESSION_MODE")
    val MEDIA_CONTROL_ENABLED = booleanPreferencesKey("MEDIA_CONTROL_ENABLED")
    val HAND_MEDIA_CONTROL_ENABLED = booleanPreferencesKey("HAND_MEDIA_CONTROL_ENABLED")
    val DISABLE_ON_CLIMATE = booleanPreferencesKey("DISABLE_ON_CLIMATE")
    val DISABLE_DURING_CALLS = booleanPreferencesKey("DISABLE_DURING_CALLS")
    val LEGACY_SOURCE_MANAGEMENT = booleanPreferencesKey("LEGACY_SOURCE_MANAGEMENT")
    val RADIO_BT_CONTROL = booleanPreferencesKey("RADIO_BT_CONTROL")
    val HIDE_MEDIA_WIDGET = booleanPreferencesKey("HIDE_MEDIA_WIDGET")
    val MEDIA_DATA_TRANSLATOR = booleanPreferencesKey("MEDIA_DATA_TRANSLATOR")
    val DEEP_LOGS = booleanPreferencesKey("DEEP_LOGS")
    val KEY_BINDS = stringPreferencesKey("KEY_BINDS")
    val FAVORITES = stringPreferencesKey("FAVORITES")
    val REMEMBER_DRIVE_MODE = booleanPreferencesKey("REMEMBER_DRIVE_MODE2")
    val REMEMBERED_DRIVE_MODE = intPreferencesKey("REMEMBERED_DRIVE_MODE")
    val TARGET_RECOVERY_DRIVE_MODE = intPreferencesKey("TARGET_RECOVERY_DRIVE_MODE")
    val DRIVE_MODE_OVERLAY = booleanPreferencesKey("DRIVE_MODE_OVERLAY")
    val DM_OVERLAY_SCALE = floatPreferencesKey("DM_OVERLAY_SCALE")
    val DM_OVERLAY_OFFSET = floatPreferencesKey("DM_OVERLAY_OFFSET")
    val TOGGLE_DM_TASK = stringPreferencesKey("TOGGLE_DM_TASK")
    val APP_UI_SCALE = floatPreferencesKey("APP_UI_SCALE")
    val ENABLE_ADB_HELPER = booleanPreferencesKey("ENABLE_ADB_HELPER")
    val ADB_HELPER_PORT = intPreferencesKey("ADB_HELPER_PORT")
    val ADB_DIM_AUTO_STOP = booleanPreferencesKey("ADB_DIM_AUTO_STOP")
    val ALT_MUTE = booleanPreferencesKey("ALT_MUTE")
    val ALT_MENU = booleanPreferencesKey("ALT_MENU")
    val ALT_LONG_TIME = intPreferencesKey("ALT_LONG_TIME")

    val ALL_KEYS
        get() = listOf(
            DATA_SYNC_ENABLED,
            DEBUG_MODE,
            FULL_BROADCAST,
            TRACK_KEY_EVENTS,
            CUSTOM_LONG_PRESS_ENABLED,
            CUSTOM_LONG_PRESS_TIME,
            CUSTOM_SHORT_CLICK_ENABLED,
            DOUBLE_CLICK_ENABLED,
            DOUBLE_CLICK_TIME,
            MULTI_LONG_PRESS_ENABLED,
            SUPPRESSION_MODE,
            MEDIA_CONTROL_ENABLED,
            HAND_MEDIA_CONTROL_ENABLED,
            DISABLE_ON_CLIMATE,
            DISABLE_DURING_CALLS,
            LEGACY_SOURCE_MANAGEMENT,
            RADIO_BT_CONTROL,
            HIDE_MEDIA_WIDGET,
            MEDIA_DATA_TRANSLATOR,
            DEEP_LOGS,
            KEY_BINDS,
            FAVORITES,
            REMEMBER_DRIVE_MODE,
            REMEMBERED_DRIVE_MODE,
            TARGET_RECOVERY_DRIVE_MODE,
            DRIVE_MODE_OVERLAY,
            DM_OVERLAY_SCALE,
            DM_OVERLAY_OFFSET,
            TOGGLE_DM_TASK,
            APP_UI_SCALE,
            ENABLE_ADB_HELPER,
            ADB_HELPER_PORT,
            ADB_DIM_AUTO_STOP,
            ALT_MUTE,
            ALT_MENU,
            ALT_LONG_TIME,
        )

    val DYNAMIC_PREFIX_KEYS
        get() = listOf(
            "DM_NOTIF_SAMPLE_",
            "DM_NOTIF_VOLUME_"
        )
}
