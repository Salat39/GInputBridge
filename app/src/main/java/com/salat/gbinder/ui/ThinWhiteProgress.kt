package com.salat.gbinder.ui

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ThinWhiteProgress(
    percent: Int,
    modifier: Modifier = Modifier
) {
    // English: Clamp input and animate to keep updates smooth
    val clamped = percent.coerceIn(0, 100)
    val progress by animateFloatAsState(
        targetValue = clamped / 100f,
        animationSpec = tween(durationMillis = 300),
        label = "progressAnim"
    )

    LinearProgressIndicator(
        progress = { progress },
        modifier = modifier
            .fillMaxWidth()
            .height(4.dp),
        color = Color.White, // indicator color
        trackColor = Color.White.copy(alpha = 0.2f) // subtle track; adjust as needed
    )
}
