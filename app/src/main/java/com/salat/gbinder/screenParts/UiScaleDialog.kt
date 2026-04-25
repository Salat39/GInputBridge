package com.salat.gbinder.screenParts

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.salat.gbinder.DEFAULT_UI_SCALE
import com.salat.gbinder.R
import com.salat.gbinder.components.roundScale
import com.salat.gbinder.ui.BaseDialog
import com.salat.gbinder.ui.ValueSlider
import com.salat.gbinder.ui.theme.AppTheme
import kotlin.math.roundToInt

@Suppress("SameParameterValue")
@Composable
fun UiScaleDialog(
    uiScaleState: Float? = null,
    onChangeUiScale: (Float) -> Unit,
    onDismiss: () -> Unit = {}
) = BaseDialog(uiScaleState = uiScaleState, onDismiss = onDismiss) {
    Column(modifier = Modifier.padding(top = 22.dp)) {
        Text(
            text = stringResource(R.string.interface_scale),
            modifier = Modifier.padding(horizontal = 24.dp),
            color = AppTheme.colors.contentPrimary,
            style = AppTheme.typography.dialogTitle,
            overflow = TextOverflow.Ellipsis,
            maxLines = 2
        )

        Spacer(modifier = Modifier.height(16.dp))

        Spacer(
            Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Color.White.copy(.1f))
        )

        Column(
            modifier = Modifier
                .padding(vertical = 26.dp)
        ) {

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 42.dp),
                textAlign = TextAlign.Left,
                text = "${(uiScaleState ?: 1f).roundScale()}x",
                color = AppTheme.colors.contentPrimary
            )
            ValueSlider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 36.dp),
                value = uiScaleState ?: 1f,
                valueRange = 0.85f..1.8f,
                onValueChange = { newValue -> onChangeUiScale(newValue) },
                enabled = true,
                defaultMark = DEFAULT_UI_SCALE,
                step = 0.05f
            )
        }

        Spacer(
            Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Color.White.copy(.1f))
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp, horizontal = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.End)
        ) {
            Text(
                modifier = Modifier
                    .clip(RoundedCornerShape(4.dp))
                    .clickable { onDismiss() }
                    .padding(horizontal = 14.dp, vertical = 8.dp),
                text = stringResource(android.R.string.ok).uppercase(),
                style = AppTheme.typography.dialogButton,
                color = AppTheme.colors.contentAccent
            )
        }

    }
}
