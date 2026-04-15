package com.salat.gbinder.features.launcher

import android.util.DisplayMetrics

const val DEFAULT_LAUNCHER_ICON_DENSITY = DisplayMetrics.DENSITY_XHIGH
const val DEFAULT_LAUNCHER_ICON_OUT_SPACE = 48
const val DEFAULT_LAUNCHER_ICON_INNER_SPACE = 48
const val DEFAULT_LAUNCHER_ICON_SIZE = 86
const val DEFAULT_LAUNCHER_ICON_TEXT_SIZE = 15
const val DEFAULT_LAUNCHER_ICON_TEXT_PADDING = 10
const val DEFAULT_LAUNCHER_ICON_ROUND = 18 // 8 = like system
const val DEFAULT_LAUNCHER_ICON_IS_ROUND = true
const val DEFAULT_LAUNCHER_WINDOW_HORIZONTAL_SPACE = 64
const val DEFAULT_LAUNCHER_WINDOW_VERTICAL_SPACE = 72
const val DEFAULT_LAUNCHER_WINDOW_ALPHA = .97f
const val DEFAULT_LAUNCHER_SHORTCUT_SIZE = 32
const val DEFAULT_LAUNCHER_DIVIDER_SIZE = 22
const val DEFAULT_AUTO_LIGHT_THEME_START = 7
const val DEFAULT_AUTO_LIGHT_THEME_END = 20

const val LAUNCHER_TOOLBAR_HEIGHT = 60

val OVERLAY_RESTRICTED_PKGS by lazy {
    setOf(
        "com.android.settings",
        "com.android.permissioncontroller",
        "com.google.android.permissioncontroller",
        "com.android.packageinstaller",
        "com.google.android.packageinstaller",
        // "com.android.systemui",
    )
}
