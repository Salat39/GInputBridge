package com.salat.gbinder.ui

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.salat.gbinder.entity.DisplayAdbState
import com.salat.gbinder.ui.theme.AppTheme

@Composable
fun StatusLamp(state: DisplayAdbState, modifier: Modifier = Modifier) {
    val targetColor = when (state) {
        DisplayAdbState.Connected -> AppTheme.colors.statusSuccess
        DisplayAdbState.Connecting -> AppTheme.colors.statusWarning
        DisplayAdbState.Disconnected -> AppTheme.colors.statusDisabled
        is DisplayAdbState.Error -> AppTheme.colors.statusError
    }

    val baseColor by animateColorAsState(
        targetValue = targetColor,
        animationSpec = tween(durationMillis = 350),
        label = "lampColor"
    )

    Box(
        modifier = modifier
            .size(80.dp)
            .clip(CircleShape)
            .background(Color.White.copy(alpha = 0.2f))
            .padding(6.dp)
            .clip(CircleShape)
            .drawWithCache {
                val radius = size.minDimension / 2f

                val volumeBrush = Brush.radialGradient(
                    colorStops = arrayOf(
                        0.0f to baseColor.copy(alpha = 1.0f),
                        0.55f to baseColor.copy(alpha = 0.95f),
                        1.0f to baseColor.copy(alpha = 1.0f)
                    ),
                    center = Offset(x = size.width * 0.35f, y = size.height * 0.30f),
                    radius = radius * 1.25f
                )

                val vignetteBrush = Brush.radialGradient(
                    colorStops = arrayOf(
                        0.65f to Color.Transparent,
                        1.0f to Color.Black.copy(alpha = 0.22f)
                    ),
                    center = Offset(x = size.width / 2f, y = size.height / 2f),
                    radius = radius
                )

                val specularBrush = Brush.radialGradient(
                    colorStops = arrayOf(
                        0.0f to Color.White.copy(alpha = 0.3f),
                        1.0f to Color.Transparent
                    ),
                    center = Offset(x = size.width * 0.28f, y = size.height * 0.22f),
                    radius = radius * 0.55f
                )

                onDrawBehind {
                    drawCircle(brush = volumeBrush)
                    drawCircle(brush = specularBrush)
                    drawCircle(brush = vignetteBrush)
                    drawCircle(
                        color = Color.White.copy(alpha = 0.12f),
                        radius = radius * 0.98f,
                        style = Stroke(width = radius * 0.06f)
                    )
                }
            }
    )
}