package com.salat.gbinder.ui.theme

import androidx.compose.ui.graphics.Color

internal val LightAppColors = AppColors(
    isDark = false,

    surfaceLayerAccentPale = ColorPalette.BrandBlue400.copy(alpha = 0.12f),
    surfaceBackground = Color(0xFFF7F8FA),
    surfaceLayer1 = Color(0xFFFFFFFF),
    surfaceMenu = Color(0xFF2563EB),
    surfaceMenuDivider = Color(0xFF1D4ED8),
    surfaceSettings = Color(0xFFFFFFFF),
    surfaceSettingsLayer1 = Color(0xFFF3F6FA),
    cardItemBackground = Color(0xFFFFFFFF),
    contentAccent = Color(0xFF2563EB),
    contentLightAccent = Color(0xFF3B82F6),
    deleteButton = Color(0xFFfd928d),
    accentDelete = Color(0xFFF44336),
    contentPrimary = Color(0xFF0F172A),
    contentWarning = ColorPalette.Yellow700,
    addSplitTop = Color(0xFF2E7D32),
    addSplitBottom = Color(0xFF0EA5E9),
    launcherAccent = Color(0xFFB42318),
    menuIcon = ColorPalette.BaseWhite,
    sliderPassive = Color(0xFF94A3B8),
    autoStart = Color(0xFF64748B),
    warning = Color(0xFF72EA78),
    lampBackground = Color(0xFFF3F4F6),
    lampSelectorBg = Color(0xFFE5E7EB),
    lampSelectorActive = Color(0xFF2563EB),
    lampSelectorInActive = Color(0xFF676A73),
    lampSelectorDivider = Color(0xFFD1D5DB),
    launcherBackground = Color(0xFFEEF2F7),
    greenAccent = Color(0xFF43A047),
    launcherSurface1 = Color(0x26323234),
    statusSuccess = Color(0xFF86b474),
    statusError = Color(0xFFdb4135),
    statusDisabled = Color(0xFF888888),
    statusWarning = Color(0xFFeb995d),
)

internal val DarkAppColors = AppColors(
    isDark = true,

    surfaceLayerAccentPale = ColorPalette.BrandBlue800.copy(alpha = 0.3f),
    surfaceBackground = Color(0xFF1f1f1f),
    surfaceLayer1 = Color(0xFF121212),
    surfaceMenu = Color(0xFF2D2D2D), // 0xFF2A2A2A
    surfaceMenuDivider = Color(0xFF232323),
    surfaceSettings = Color(0xFF1A1A1A), // 0xFF1C1C1C
    surfaceSettingsLayer1 = Color(0xFF262626), // 0xFF262626
    cardItemBackground = Color(0xFF1e1e1e),
    contentAccent = Color(0xFF1975d0),
    contentLightAccent = Color(0xFF0090FF),
    deleteButton = Color(0xFFF44336),
    accentDelete = Color(0xFFF44336),
    contentPrimary = Color(0xFFe5e5e5),
    contentWarning = ColorPalette.Yellow500,
    addSplitTop = Color(0xFF2E7D32), // 0xFF388E3C
    addSplitBottom = Color(0xFF283593), // 0xFF512DA8
    launcherAccent = Color(0xFF942320), // 0xFFC62828
    menuIcon = ColorPalette.BaseWhite,
    sliderPassive = Color(0xFF626262),
    autoStart = Color(0xFF01579B),
    warning = Color(0xFFBC7F00),
    lampBackground = Color(0xFF080808),
    lampSelectorBg = Color(0xFF1b1b1d),
    lampSelectorActive = Color(0xFF48494d),
    lampSelectorInActive = Color(0xFF99989d),
    lampSelectorDivider = Color(0xFF242426),
    launcherBackground = Color(0xFF181818),
    greenAccent = Color(0xFF43A047),
    launcherSurface1 = Color(0x66323234),
    statusSuccess = Color(0xFF86b474),
    statusError = Color(0xFFdb4135),
    statusDisabled = Color(0xFF888888),
    statusWarning = Color(0xFFeb995d),
)
