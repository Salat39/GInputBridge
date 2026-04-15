package com.salat.gbinder.ui

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import com.salat.gbinder.ui.theme.AppTheme

@Composable
fun ProfileSwitch(
    modifier: Modifier = Modifier,
    scale: Float = .75f,
    checked: Boolean,
    enabled: Boolean = true,
    onCheckedChange: ((Boolean) -> Unit)?,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
) {
    val colors = SwitchDefaults.colors(
        checkedThumbColor = AppTheme.colors.surfaceSettingsLayer1, // background in enable
        checkedTrackColor = AppTheme.colors.contentAccent,
        uncheckedThumbColor = AppTheme.colors.sliderPassive,
        uncheckedTrackColor = AppTheme.colors.surfaceSettingsLayer1, // background in disable
        uncheckedBorderColor = AppTheme.colors.sliderPassive,
        disabledCheckedThumbColor = AppTheme.colors.surfaceSettingsLayer1,
        disabledCheckedTrackColor = AppTheme.colors.contentAccent.copy(.5f),
        disabledUncheckedThumbColor = AppTheme.colors.sliderPassive.copy(.5f),
        disabledUncheckedTrackColor = AppTheme.colors.surfaceSettingsLayer1.copy(.5f),
        disabledUncheckedBorderColor = AppTheme.colors.sliderPassive.copy(.5f)
    )
    Switch(
        modifier = modifier.graphicsLayer {
            scaleX = scale
            scaleY = scale
        },
        checked = checked,
        enabled = enabled,
        onCheckedChange = onCheckedChange,
        interactionSource = interactionSource,
        colors = colors
    )
}
