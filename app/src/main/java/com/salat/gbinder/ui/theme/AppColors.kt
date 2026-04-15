package com.salat.gbinder.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

@Immutable
data class AppColors(
    val isDark: Boolean,

    val surfaceLayerAccentPale: Color,
    val surfaceBackground: Color,
    val surfaceLayer1: Color,
    val surfaceMenu: Color,
    val surfaceMenuDivider: Color,
    val surfaceSettings: Color,
    val surfaceSettingsLayer1: Color,
    val cardItemBackground: Color,
    val contentAccent: Color,
    val contentLightAccent: Color,
    val deleteButton: Color,
    val accentDelete: Color,
    val contentPrimary: Color,
    val contentWarning: Color,
    val addSplitTop: Color,
    val addSplitBottom: Color,
    val launcherAccent: Color,
    val menuIcon: Color,
    val sliderPassive: Color,
    val autoStart: Color,
    val warning: Color,
    val lampBackground: Color,
    val lampSelectorBg: Color,
    val lampSelectorActive: Color,
    val lampSelectorInActive: Color,
    val lampSelectorDivider: Color,
    val launcherBackground: Color,
    val greenAccent: Color,
    val launcherSurface1: Color,
    val statusSuccess: Color,
    val statusError: Color,
    val statusDisabled: Color,
    val statusWarning: Color,
)
