package com.salat.gbinder.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.salat.gbinder.ui.theme.AppTheme

@Composable
fun BaseButton(
    modifier: Modifier = Modifier,
    title: String,
    backgroundColor: Color = AppTheme.colors.contentAccent,
    textColor: Color = AppTheme.colors.contentPrimary,
    enable: Boolean = true,
    style: TextStyle = AppTheme.typography.buttonTitle,
    onClick: () -> Unit
) = Button(
    modifier = modifier,
    colors = ButtonColors(
        containerColor = backgroundColor,
        contentColor = AppTheme.colors.contentPrimary,
        disabledContainerColor = backgroundColor,
        disabledContentColor = AppTheme.colors.contentPrimary
    ),
    enabled = enable,
    shape = RoundedCornerShape(8.dp),
    onClick = onClick
) {
    Text(
        modifier = Modifier.padding(4.dp),
        text = title,
        color = textColor,
        style = style,
        textAlign = TextAlign.Center
    )
}
