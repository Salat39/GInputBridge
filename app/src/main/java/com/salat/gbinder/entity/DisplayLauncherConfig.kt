package com.salat.gbinder.entity

import androidx.compose.runtime.Immutable

@Immutable
data class DisplayLauncherConfig(
    val uiScale: Float,
    val iconQuality: Int,
    val iconOutSpace: Int,
    val iconInnerSpace: Int,
    val iconSize: Int,
    val iconTextSize: Int,
    val iconTextPadding: Int,
    val iconTextEnable: Boolean,
    val iconTextMultiline: Boolean,
    val iconRound: Int,
    val defaultTab: Int,
    val windowMode: Boolean,
    val windowShowFrame: Boolean,
    val windowHorizontalSpace: Int,
    val windowVerticalSpace: Int,
    val windowAlpha: Float,
    val lightTheme: Boolean,
    val enableShortcuts: Boolean,
    val shortcutSize: Int,
    val dividerTextSize: Int,
    val dividerTextBold: Boolean,
    val recentsEnable: Boolean,
    val autoLightTheme: Boolean,
    val autoLightThemeStart: Int,
    val autoLightThemeEnd: Int,
    val enableAdbHelper: Boolean
)
