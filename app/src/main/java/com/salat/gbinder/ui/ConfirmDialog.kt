package com.salat.gbinder.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.salat.gbinder.ui.theme.AppTheme

// private const val BASE_WIDTH = 320

@Composable
fun ConfirmDialog(
    modifier: Modifier = Modifier,
    title: String = "",
    message: String = "",
    okButtonTitle: String = "",
    closeButtonTitle: String = "",
    negativeAction: Boolean = false,
    disableNegative: Boolean = false,
    isShort: Boolean = false,
    uiScaleState: State<Float>? = null,
    uiScale: Float? = null,
    extraContent: @Composable (ColumnScope.() -> Unit)? = null,
    onDismiss: () -> Unit = {},
    onCancel: () -> Unit = { onDismiss() },
    onClick: () -> Unit
) {
    val scale = uiScale ?: (uiScaleState?.value ?: 1f)
    BaseDialog(
        modifier = modifier,
        uiScaleState = scale,
        maxWidth = 560,
        onDismiss = onDismiss
    ) {
        Column(modifier = Modifier.padding(top = 22.dp)) {
            // Title
            if (title.isNotEmpty()) {
                Text(
                    text = title,
                    modifier = Modifier.padding(horizontal = 24.dp),
                    color = AppTheme.colors.contentPrimary,
                    style = AppTheme.typography.confirmDialogTitle,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 2
                )
                Spacer(modifier = Modifier.height(12.dp))
            } else {
                Spacer(modifier = Modifier.height(2.dp))
            }

            // Message
            if (message.isNotEmpty()) {
                Text(
                    text = message,
                    modifier = Modifier.padding(horizontal = 24.dp),
                    color = AppTheme.colors.contentPrimary,
                    style = AppTheme.typography.dialogListTitle
                )
                Spacer(modifier = Modifier.height(if (isShort) 14.dp else 24.dp))
            }

            // More content here
            extraContent?.let { it() }

            // Buttons
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp, bottom = 8.dp),
                horizontalArrangement = Arrangement.End
            ) {
                if (!disableNegative) {
                    // Cancel
                    Text(
                        text = closeButtonTitle.ifEmpty {
                            stringResource(android.R.string.cancel)
                        },
                        modifier = Modifier
                            .clip(RoundedCornerShape(4.dp))
                            .clickable {
                                onCancel()
                            }
                            .padding(horizontal = 12.dp, vertical = 8.dp),
                        color = AppTheme.colors.contentAccent,
                        style = AppTheme.typography.alertDialogButton
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                }
                // Ok
                Text(
                    text = okButtonTitle.ifEmpty { stringResource(android.R.string.ok) },
                    modifier = Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .clickable(onClick = onClick)
                        .padding(horizontal = 12.dp, vertical = 8.dp),
                    color = if (negativeAction) {
                        AppTheme.colors.deleteButton.copy(alpha = .9f)
                    } else {
                        AppTheme.colors.contentAccent.copy(alpha = .9f)
                    },
                    style = AppTheme.typography.alertDialogButton
                )
            }
        }
    }
}
