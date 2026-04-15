package com.salat.gbinder.datastore

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object LauncherPrefs {
    val LAUNCHER_DATA = stringPreferencesKey("LAUNCHER_DATA")
    val LAUNCHER_SCALE = floatPreferencesKey("LAUNCHER_SCALE")
    val LAUNCHER_ICON_QUALITY = intPreferencesKey("LAUNCHER_ICON_QUALITY")
    val LAUNCHER_ICON_OUT_SPACE = intPreferencesKey("LAUNCHER_ICON_OUT_SPACE")
    val LAUNCHER_ICON_INNER_SPACE = intPreferencesKey("LAUNCHER_ICON_INNER_SPACE")
    val LAUNCHER_ICON_SIZE = intPreferencesKey("LAUNCHER_ICON_SIZE")
    val LAUNCHER_ICON_TEXT_SIZE = intPreferencesKey("LAUNCHER_ICON_TEXT_SIZE")
    val LAUNCHER_ICON_TEXT_PADDING = intPreferencesKey("LAUNCHER_ICON_TEXT_PADDING")
    val LAUNCHER_ICON_TEXT_ENABLE = booleanPreferencesKey("LAUNCHER_ICON_TEXT_ENABLE")
    val LAUNCHER_ICON_TEXT_MULTILINE = booleanPreferencesKey("LAUNCHER_ICON_TEXT_MULTILINE")
    val LAUNCHER_ICON_ROUND = intPreferencesKey("LAUNCHER_ICON_ROUND")
    val LAUNCHER_DEFAULT_TAB = intPreferencesKey("LAUNCHER_DEFAULT_TAB")
    val LAUNCHER_WINDOW_MODE = booleanPreferencesKey("LAUNCHER_WINDOW_MODE")
    val LAUNCHER_WINDOW_SHOW_FRAME = booleanPreferencesKey("LAUNCHER_WINDOW_SHOW_FRAME")
    val LAUNCHER_WINDOW_HORIZONTAL_SPACE = intPreferencesKey("LAUNCHER_WINDOW_HORIZONTAL_SPACE")
    val LAUNCHER_WINDOW_VERTICAL_SPACE = intPreferencesKey("LAUNCHER_WINDOW_VERTICAL_SPACE")
    val LAUNCHER_WINDOW_ALPHA = floatPreferencesKey("LAUNCHER_WINDOW_ALPHA")
    val LAUNCHER_LIGHT_THEME = booleanPreferencesKey("LAUNCHER_LIGHT_THEME")
    val LAUNCHER_ENABLE_SHORTCUTS = booleanPreferencesKey("LAUNCHER_ENABLE_SHORTCUTS")
    val LAUNCHER_SHORTCUT_SIZE = intPreferencesKey("LAUNCHER_SHORTCUT_SIZE")
    val LAUNCHER_DIVIDER_SIZE = intPreferencesKey("LAUNCHER_DIVIDER_SIZE")
    val LAUNCHER_DIVIDER_BOLD = booleanPreferencesKey("LAUNCHER_DIVIDER_BOLD")
    val LAUNCHER_RECENTS_ENABLE = booleanPreferencesKey("LAUNCHER_RECENTS_ENABLE")
    val LAUNCHER_AUTO_LIGHT_THEME = booleanPreferencesKey("LAUNCHER_AUTO_LIGHT_THEME")
    val LAUNCHER_AUTO_LIGHT_THEME_START = intPreferencesKey("LAUNCHER_AUTO_LIGHT_THEME_START")
    val LAUNCHER_AUTO_LIGHT_THEME_END = intPreferencesKey("LAUNCHER_AUTO_LIGHT_THEME_END")

    val ALL_KEYS
        get() = listOf(
            LAUNCHER_DATA,
            LAUNCHER_SCALE,
            LAUNCHER_ICON_QUALITY,
            LAUNCHER_ICON_OUT_SPACE,
            LAUNCHER_ICON_INNER_SPACE,
            LAUNCHER_ICON_SIZE,
            LAUNCHER_ICON_TEXT_SIZE,
            LAUNCHER_ICON_TEXT_PADDING,
            LAUNCHER_ICON_TEXT_ENABLE,
            LAUNCHER_ICON_TEXT_MULTILINE,
            LAUNCHER_ICON_ROUND,
            LAUNCHER_DEFAULT_TAB,
            LAUNCHER_WINDOW_MODE,
            LAUNCHER_WINDOW_SHOW_FRAME,
            LAUNCHER_WINDOW_HORIZONTAL_SPACE,
            LAUNCHER_WINDOW_VERTICAL_SPACE,
            LAUNCHER_WINDOW_ALPHA,
            LAUNCHER_LIGHT_THEME,
            LAUNCHER_ENABLE_SHORTCUTS,
            LAUNCHER_SHORTCUT_SIZE,
            LAUNCHER_DIVIDER_SIZE,
            LAUNCHER_DIVIDER_BOLD,
            LAUNCHER_RECENTS_ENABLE,
            LAUNCHER_AUTO_LIGHT_THEME,
            LAUNCHER_AUTO_LIGHT_THEME_START,
            LAUNCHER_AUTO_LIGHT_THEME_END,
        )
}
