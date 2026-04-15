package com.salat.gbinder.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.salat.gbinder.ui.theme.AppTheme

@Composable
fun RenderSwitcher(
    modifier: Modifier,
    title: String,
    subtitle: String? = null,
    value: Boolean? = null,
    enable: Boolean = true,
    groupDivider: Boolean = true,
    clickRadius: Int = 8,
    titleStyle: TextStyle = AppTheme.typography.screenTitle,
    subtitleColor: Color = AppTheme.colors.contentPrimary.copy(.4f),
    subtitleStyle: TextStyle = AppTheme.typography.dialogSubtitle,
    switchScale: Float = .8f,
    onChange: (Boolean) -> Unit = {}
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(clickRadius.dp))
            .clickable(value != null && enable) {
                onChange(value?.let { !it } ?: true)
            }
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            Modifier
                .weight(1f)
                .then(if (!enable) Modifier.graphicsLayer { alpha = .25f } else Modifier)
        ) {
            Text(
                modifier = Modifier.padding(horizontal = 23.dp),
                text = title,
                style = titleStyle,
                color = AppTheme.colors.contentPrimary
            )

            if (subtitle != null) {
                Spacer(Modifier.height(5.dp))

                Text(
                    text = subtitle,
                    modifier = Modifier.padding(horizontal = 23.dp),
                    color = subtitleColor,
                    style = subtitleStyle
                )
            }
        }
        if (groupDivider) {
            Spacer(Modifier.width(14.dp))
            Spacer(
                Modifier
                    .width(1.dp)
                    .height(22.dp)
                    .background(AppTheme.colors.sliderPassive)
            )
            Spacer(Modifier.width(14.dp))
        }
        value?.let { value ->
            ProfileSwitch(
                scale = switchScale,
                checked = value,
                enabled = enable,
                onCheckedChange = null
            )
        } ?: run { Spacer(Modifier.width(52.dp)) }
        Spacer(Modifier.width(20.dp))
    }
}
