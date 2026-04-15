package com.salat.gbinder.ui.theme

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalRippleConfiguration
import androidx.compose.material3.RippleConfiguration
import androidx.compose.material3.RippleDefaults
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

object AppTheme {
    val colors: AppColors
        @Composable @ReadOnlyComposable get() = LocalAppColors.current

    val typography: AppTypography
        @Composable @ReadOnlyComposable get() = LocalAppTypography.current
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    typography: AppTypography = AppTheme.typography,
    content: @Composable () -> Unit
) {
    val colors = remember(darkTheme) {
        if (darkTheme) DarkAppColors else LightAppColors
    }

    val textSelectionColors = TextSelectionColors(
        handleColor = colors.contentAccent,
        backgroundColor = colors.surfaceLayerAccentPale
    )
    val rippleIndication = ripple()
    val oldRippleAlpha = RippleDefaults.RippleAlpha
    val oldRippleColor: Color =
        if (darkTheme) Color.White else Color.Black
    val myRippleConfig = RippleConfiguration(
        color = oldRippleColor,
        rippleAlpha = oldRippleAlpha,
    )
    CompositionLocalProvider(
        LocalAppColors provides colors,
        LocalAppTypography provides typography,
        LocalContentColor provides colors.contentPrimary,
        LocalTextSelectionColors provides textSelectionColors,
        LocalIndication provides rippleIndication,
        LocalRippleConfiguration provides myRippleConfig,
        content = content
    )
}

private val LocalAppColors = staticCompositionLocalOf<AppColors> {
    error("No colors provided")
}
