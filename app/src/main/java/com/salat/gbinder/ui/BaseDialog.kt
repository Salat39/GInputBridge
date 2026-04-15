package com.salat.gbinder.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.salat.gbinder.ui.theme.AppTheme
import com.salat.gbinder.util.rememberIsLandscape

private const val BASE_DIALOG_WIDTH = 640

@Composable
fun BaseDialog(
    modifier: Modifier = Modifier,
    uiScaleState: Float? = null,
    properties: DialogProperties = DialogProperties(),
    onDismiss: () -> Unit = {},
    maxWidth: Int = BASE_DIALOG_WIDTH,
    content: @Composable () -> Unit
) {
    val configuration = LocalConfiguration.current
    val isLandscape = rememberIsLandscape()
    val dialogFillerSize = if (isLandscape) .2f else .1f
    val dialogHeightFiller =
        remember(configuration) { (configuration.screenHeightDp * dialogFillerSize).dp } // 20% of height

    Dialog(
        onDismissRequest = { onDismiss() },
        properties = DialogProperties(
            dismissOnBackPress = properties.dismissOnBackPress,
            dismissOnClickOutside = false,
            securePolicy = properties.securePolicy,
            usePlatformDefaultWidth = false,
            decorFitsSystemWindows = properties.decorFitsSystemWindows,
        )
    ) {
        val density = LocalDensity.current
        val scaledDensity = remember(density, uiScaleState ?: 1f) {
            Density(
                density.density * (uiScaleState ?: 1f),
                density.fontScale * (uiScaleState ?: 1f)
            )
        }

        CompositionLocalProvider(LocalDensity provides scaledDensity) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clickableNoRipple(enabled = properties.dismissOnClickOutside) { onDismiss() },
                contentAlignment = Alignment.Center
            ) {
                Surface(
                    modifier = modifier
                        .widthIn(max = maxWidth.dp)
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(
                            bottom = dialogHeightFiller,
                            top = if (isLandscape) 4.dp else dialogHeightFiller
                        )
                        .padding(horizontal = 32.dp)
                        .clickableNoRipple {},
                    shape = RoundedCornerShape(10.dp),
                    color = AppTheme.colors.surfaceBackground,
                    content = content
                )
            }
        }
    }
}
