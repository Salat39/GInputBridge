package com.salat.gbinder.features.launcher

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.salat.gbinder.ui.clickableNoRipple
import com.salat.gbinder.ui.theme.AppTheme
import kotlin.math.roundToInt

@Composable
fun OverlayPopupMenu(
    offset: IntOffset,
    onDismiss: () -> Unit,
    horizontalOffset: Dp = 36.dp,
    verticalOffset: Dp = 24.dp,
    uiScale: Float = 1f,
    header: @Composable ColumnScope.() -> Unit = {},
    content: @Composable ColumnScope.() -> Unit
) {
    var contentSize by remember { mutableStateOf(IntSize.Zero) }
    val containerSize = LocalWindowInfo.current.containerSize

    val density = LocalDensity.current
    val scaledDensity = remember(density) {
        Density(
            density.density * uiScale
        )
    }

    val horizontalShiftPx = with(density) { horizontalOffset.roundToPx() }
    val verticalShiftPx = with(density) { verticalOffset.roundToPx() }

    val maxX = (containerSize.width - contentSize.width).coerceAtLeast(0)
    val maxY = (containerSize.height - contentSize.height).coerceAtLeast(0)

    val shiftedX = offset.x - horizontalShiftPx
    val shiftedY = offset.y - verticalShiftPx
    val clampedX = shiftedX.coerceIn(0, maxX)
    val clampedY = shiftedY.coerceIn(0, maxY)

    val scaledElevation = remember { (4f * uiScale).roundToInt() }
    val borderRadius = remember { (8f * uiScale).roundToInt() }
    val menuBorder = remember { RoundedCornerShape(borderRadius.dp) }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickableNoRipple(onClick = onDismiss)
    ) {
        Column(
            modifier = Modifier
                .offset { IntOffset(clampedX, clampedY) }
                .width(IntrinsicSize.Max)
                .height(IntrinsicSize.Max)
                .onGloballyPositioned { coordinates ->
                    contentSize = coordinates.size
                }
        ) {
            header()
            Box(
                modifier = Modifier
                    .shadow(scaledElevation.dp, shape = menuBorder)
            ) {
                CompositionLocalProvider(LocalDensity provides scaledDensity) {
                    Column(
                        modifier = Modifier
                            .clip(menuBorder)
                            .background(AppTheme.colors.surfaceMenu, menuBorder)
                    ) {
                        content()
                    }
                }
            }
        }
    }
}
