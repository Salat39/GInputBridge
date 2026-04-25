package com.salat.gbinder.screenParts

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.salat.gbinder.R
import com.salat.gbinder.components.spannedFromHtml
import com.salat.gbinder.components.toAnnotatedString
import com.salat.gbinder.ui.BaseButton
import com.salat.gbinder.ui.theme.AppTheme

@Composable
internal fun ColumnScope.RenderDocumentationBlock() {
    var showDoc by remember { mutableStateOf(false) }
    BaseButton(
        modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.Start)
            .padding(horizontal = 42.dp),
        title = stringResource(
            if (showDoc) {
                R.string.hide_documentation
            } else R.string.show_documentation
        ),
        backgroundColor = AppTheme.colors.surfaceMenu
    ) {
        showDoc = !showDoc
    }
    Spacer(Modifier.height(24.dp))

    RenderGroupDivider()

    AnimatedVisibility(
        visible = showDoc,
        enter = expandVertically(
            expandFrom = Alignment.Top,
            animationSpec = tween(300)
        ),
        exit = shrinkVertically(
            shrinkTowards = Alignment.Top,
            animationSpec = tween(300)
        ),
    ) {
        Column(Modifier.fillMaxWidth()) {

            Spacer(Modifier.height(24.dp))

            SelectionContainer {
                Text(
                    text = stringResource(R.string.api_text_launcher)
                        .spannedFromHtml()
                        .toAnnotatedString(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 42.dp),
                    color = AppTheme.colors.contentPrimary.copy(.8f),
                    style = AppTheme.typography.dialogSubtitle
                )
            }

            Spacer(Modifier.height(24.dp))
            Spacer(
                Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(AppTheme.colors.contentPrimary.copy(.1f))
            )
            Spacer(Modifier.height(24.dp))

            SelectionContainer {
                Text(
                    text = stringResource(R.string.api_text)
                        .spannedFromHtml()
                        .toAnnotatedString(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 42.dp),
                    color = AppTheme.colors.contentPrimary.copy(.8f),
                    style = AppTheme.typography.dialogSubtitle
                )
            }

            Spacer(Modifier.height(24.dp))
            Spacer(
                Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(AppTheme.colors.contentPrimary.copy(.1f))
            )
            Spacer(Modifier.height(24.dp))

            SelectionContainer {
                Text(
                    text = stringResource(R.string.api_text2)
                        .spannedFromHtml()
                        .toAnnotatedString(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 42.dp),
                    color = AppTheme.colors.contentPrimary.copy(.8f),
                    style = AppTheme.typography.dialogSubtitle
                )
            }

            Spacer(Modifier.height(24.dp))
            Spacer(
                Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(AppTheme.colors.contentPrimary.copy(.1f))
            )
            Spacer(Modifier.height(24.dp))

            SelectionContainer {
                Text(
                    text = stringResource(R.string.api_text3)
                        .spannedFromHtml()
                        .toAnnotatedString(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 42.dp),
                    color = AppTheme.colors.contentPrimary.copy(.8f),
                    style = AppTheme.typography.dialogSubtitle
                )
            }

            Spacer(Modifier.height(24.dp))
            Spacer(
                Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(AppTheme.colors.contentPrimary.copy(.1f))
            )
            Spacer(Modifier.height(24.dp))

            SelectionContainer {
                Text(
                    text = stringResource(R.string.api_text4)
                        .spannedFromHtml()
                        .toAnnotatedString(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 42.dp),
                    color = AppTheme.colors.contentPrimary.copy(.8f),
                    style = AppTheme.typography.dialogSubtitle
                )
            }

            Spacer(Modifier.height(24.dp))
            Spacer(
                Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(AppTheme.colors.contentPrimary.copy(.1f))
            )
            Spacer(Modifier.height(24.dp))

            SelectionContainer {
                Text(
                    text = stringResource(R.string.api_text5)
                        .spannedFromHtml()
                        .toAnnotatedString(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 42.dp),
                    color = AppTheme.colors.contentPrimary.copy(.8f),
                    style = AppTheme.typography.dialogSubtitle
                )
            }

            Spacer(Modifier.height(24.dp))
            Spacer(
                Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(AppTheme.colors.contentPrimary.copy(.1f))
            )
            Spacer(Modifier.height(24.dp))

            SelectionContainer {
                Text(
                    text = stringResource(R.string.api_text6)
                        .spannedFromHtml()
                        .toAnnotatedString(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 42.dp),
                    color = AppTheme.colors.contentPrimary.copy(.8f),
                    style = AppTheme.typography.dialogSubtitle
                )
            }

            Spacer(Modifier.height(24.dp))
            Spacer(
                Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(AppTheme.colors.contentPrimary.copy(.1f))
            )
            Spacer(Modifier.height(24.dp))

            SelectionContainer {
                Text(
                    text = stringResource(R.string.api_text7)
                        .spannedFromHtml()
                        .toAnnotatedString(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 42.dp),
                    color = AppTheme.colors.contentPrimary.copy(.8f),
                    style = AppTheme.typography.dialogSubtitle
                )
            }

            Spacer(Modifier.height(24.dp))
            Spacer(
                Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(AppTheme.colors.contentPrimary.copy(.1f))
            )
            Spacer(Modifier.height(24.dp))

            SelectionContainer {
                Text(
                    text = stringResource(R.string.api_text8)
                        .spannedFromHtml()
                        .toAnnotatedString(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 42.dp),
                    color = AppTheme.colors.contentPrimary.copy(.8f),
                    style = AppTheme.typography.dialogSubtitle
                )
            }

            Spacer(Modifier.height(24.dp))
            Spacer(
                Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(AppTheme.colors.contentPrimary.copy(.1f))
            )
            Spacer(Modifier.height(24.dp))

            SelectionContainer {
                Text(
                    text = stringResource(R.string.api_text9)
                        .spannedFromHtml()
                        .toAnnotatedString(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 42.dp),
                    color = AppTheme.colors.contentPrimary.copy(.8f),
                    style = AppTheme.typography.dialogSubtitle
                )
            }

            Spacer(Modifier.height(24.dp))
            Spacer(
                Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(AppTheme.colors.contentPrimary.copy(.1f))
            )
            Spacer(Modifier.height(24.dp))

            SelectionContainer {
                Text(
                    text = stringResource(R.string.api_text10)
                        .spannedFromHtml()
                        .toAnnotatedString(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 42.dp),
                    color = AppTheme.colors.contentPrimary.copy(.8f),
                    style = AppTheme.typography.dialogSubtitle
                )
            }

            Spacer(Modifier.height(24.dp))
            RenderGroupDivider()
        }
    }

    Spacer(Modifier.height(24.dp))
}
