package com.salat.gbinder.features.launcher

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.salat.gbinder.R
import com.salat.gbinder.ui.BaseDialog
import com.salat.gbinder.ui.theme.AppTheme

@Suppress("SameParameterValue")
@Composable
fun InputMyAppNameDialog(
    title: String,
    uiScaleState: Float? = null,
    onNewGroup: (String) -> Unit,
    onDismiss: () -> Unit = {}
) = BaseDialog(uiScaleState = uiScaleState, onDismiss = onDismiss) {
    Column(modifier = Modifier.padding(top = 22.dp)) {
        Text(
            text = stringResource(if (title.isEmpty()) R.string.create_divider else R.string.rename),
            modifier = Modifier.padding(horizontal = 24.dp),
            color = AppTheme.colors.contentPrimary,
            style = AppTheme.typography.dialogTitle,
            overflow = TextOverflow.Ellipsis,
            maxLines = 2
        )

        Spacer(Modifier.height(5.dp))

        Text(
            text = stringResource(if (title.isEmpty()) R.string.divider_description else R.string.enter_new_title_my_apps),
            modifier = Modifier.padding(horizontal = 23.dp),
            color = AppTheme.colors.contentPrimary.copy(.4f),
            style = AppTheme.typography.dialogSubtitle
        )

        Spacer(modifier = Modifier.height(12.dp))

        Spacer(
            Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Color.White.copy(.1f))
        )

        val fieldTypography = AppTheme.typography.stubTitle
        val keyboardController = LocalSoftwareKeyboardController.current
        val border = RoundedCornerShape(12.dp)
        var inputValue: TextFieldValue by remember {
            mutableStateOf(
                TextFieldValue(
                    text = title,
                    selection = TextRange(title.length)
                )
            )
        }
        val enableOk by remember { derivedStateOf { inputValue.text.isNotEmpty() } }

        Column(
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 26.dp)
        ) {

            BasicTextField(
                value = inputValue,
                onValueChange = { inputValue = it },
                cursorBrush = SolidColor(AppTheme.colors.contentAccent),
                textStyle = fieldTypography.copy(color = AppTheme.colors.contentPrimary),
                decorationBox = { innerTextField ->
                    Box(
                        modifier = Modifier
                            .padding(vertical = 16.dp, horizontal = 20.dp),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        if (inputValue.text.isEmpty()) {
                            Text(
                                text = stringResource(R.string.enter_title),
                                style = fieldTypography.copy(
                                    color = AppTheme.colors.contentPrimary.copy(.4f)
                                )
                            )
                        }
                        innerTextField()
                    }
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done,
                    capitalization = KeyboardCapitalization.None,
                    keyboardType = KeyboardType.Text,
                    autoCorrectEnabled = false
                ),
                keyboardActions = KeyboardActions(onSearch = {
                    defaultKeyboardAction(ImeAction.Done)
                    keyboardController?.hide()
                }),
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(border)
                    .border(
                        shape = border,
                        width = 2.dp,
                        color = AppTheme.colors.surfaceMenu
                    )
                    .background(AppTheme.colors.surfaceSettingsLayer1.copy(.3f)),
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
                text = stringResource(android.R.string.cancel).uppercase(),
                style = AppTheme.typography.dialogButton,
                color = AppTheme.colors.contentAccent
            )

            Text(
                modifier = Modifier
                    .clip(RoundedCornerShape(4.dp))
                    .clickable(enabled = enableOk) {
                        onNewGroup(inputValue.text)
                    }
                    .padding(horizontal = 14.dp, vertical = 8.dp),
                text = stringResource(android.R.string.ok).uppercase(),
                style = AppTheme.typography.dialogButton,
                color = if (enableOk) {
                    AppTheme.colors.contentAccent
                } else AppTheme.colors.contentPrimary.copy(.3f)
            )
        }

    }
}
