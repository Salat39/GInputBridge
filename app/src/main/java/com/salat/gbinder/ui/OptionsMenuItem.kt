package com.salat.gbinder.ui

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.salat.gbinder.ui.theme.AppTheme

@Composable
fun OptionsMenuItem(
    @DrawableRes icon: Int,
    title: String,
    offsetX: Float = 0f,
    scale: Float = 1f,
    mirror: Boolean = false,
    iconColor: Color = AppTheme.colors.menuIcon,
    textColor: Color = AppTheme.colors.contentPrimary,
    onClick: () -> Unit
) = RenderOptionsMenuItem(
    icon = painterResource(icon),
    title = title,
    offsetX = offsetX,
    scale = scale,
    mirror = mirror,
    iconColor = iconColor,
    textColor = textColor,
    onClick = onClick
)

@Composable
fun OptionsMenuItem(
    @DrawableRes icon: Int,
    @StringRes title: Int,
    offsetX: Float = 0f,
    scale: Float = 1f,
    mirror: Boolean = false,
    iconColor: Color = AppTheme.colors.menuIcon,
    textColor: Color = AppTheme.colors.contentPrimary,
    onClick: () -> Unit
) = RenderOptionsMenuItem(
    icon = painterResource(icon),
    title = stringResource(title),
    offsetX = offsetX,
    scale = scale,
    mirror = mirror,
    iconColor = iconColor,
    textColor = textColor,
    onClick = onClick
)

@Composable
fun RenderOptionsMenuItem(
    icon: Painter,
    title: String,
    offsetX: Float = 0f,
    scale: Float = 1f,
    mirror: Boolean = false,
    iconColor: Color = AppTheme.colors.menuIcon,
    textColor: Color = AppTheme.colors.contentPrimary,
    onClick: () -> Unit
) = Row(
    verticalAlignment = Alignment.CenterVertically,
    modifier = Modifier
        .clickable { onClick() }
        .padding(top = 13.dp, bottom = 13.dp, start = 20.dp, end = 22.dp)
) {
    Icon(
        painter = icon,
        tint = iconColor,
        contentDescription = "menu icon",
        modifier = Modifier
            .alpha(.9f)
            .size(22.dp)
            .graphicsLayer {
                scaleX = scale * (if (mirror) -1f else 1f)
                scaleY = scale
                translationX = offsetX
            }
            .mirror()
    )
    Text(
        modifier = Modifier
            .padding(start = 21.dp)
            .weight(1f),
        text = title,
        color = textColor,
        style = AppTheme.typography.overlayLauncherMenuTitle,
        overflow = TextOverflow.Ellipsis,
        maxLines = 1
    )
}
