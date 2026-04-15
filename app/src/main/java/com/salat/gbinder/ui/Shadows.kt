package com.salat.gbinder.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex

@Composable
fun TopShadow(modifier: Modifier = Modifier, alpha: Float = .05f, height: Dp = 5.dp) {
    HorizontalShadow(modifier, alpha, height)
}

@Composable
fun BottomShadow(modifier: Modifier = Modifier, alpha: Float = .05f, height: Dp = 5.dp) {
    HorizontalShadow(modifier, alpha, height, true)
}

@Composable
fun HorizontalShadow(
    modifier: Modifier = Modifier,
    alpha: Float = .05f,
    height: Dp = 5.dp,
    invert: Boolean = false
) {
    val colors = if (invert) listOf(Color.Transparent, Color.Black.copy(alpha = alpha))
    else listOf(Color.Black.copy(alpha = alpha), Color.Transparent)
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(height)
            .background(brush = Brush.verticalGradient(colors = colors))
            .zIndex(2f)
    )
}
