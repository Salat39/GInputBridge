package com.salat.gbinder.features.launcher

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.Hyphens
import androidx.compose.ui.text.style.LineBreak
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import com.salat.gbinder.ui.theme.AppTheme

private const val FrozenLockInlineId = "frozen_lock"

@Composable
internal fun LauncherAppTitle(
    title: String,
    isFrozen: Boolean,
    textSize: Int,
    enableMultiline: Boolean,
    modifier: Modifier = Modifier
) {
    val textStyle = AppTheme.typography.overlayLauncherIconTitle.copy(
        lineBreak = LineBreak.Simple,
        hyphens = Hyphens.None,
        fontSize = textSize.sp,
        lineHeight = textSize.sp
    )

    if (!isFrozen) {
        Text(
            text = title,
            style = textStyle,
            maxLines = if (enableMultiline) 2 else 1,
            textAlign = TextAlign.Center,
            overflow = TextOverflow.Ellipsis,
            modifier = modifier
        )
        return
    }

    val inlineContent = remember(textSize) {
        mapOf(
            FrozenLockInlineId to InlineTextContent(
                placeholder = Placeholder(
                    width = (textSize * 0.85f).sp,
                    height = (textSize * 0.85f).sp,
                    placeholderVerticalAlign = PlaceholderVerticalAlign.TextCenter
                )
            ) {
                Icon(
                    imageVector = Icons.Filled.Lock,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize()
                )
            }
        )
    }

    val text = remember(title) {
        buildAnnotatedString {
            appendInlineContent(FrozenLockInlineId)
            append(" ")
            append(title)
        }
    }

    Text(
        text = text,
        inlineContent = inlineContent,
        style = textStyle,
        maxLines = if (enableMultiline) 2 else 1,
        textAlign = TextAlign.Center,
        overflow = TextOverflow.Ellipsis,
        modifier = modifier
    )
}
