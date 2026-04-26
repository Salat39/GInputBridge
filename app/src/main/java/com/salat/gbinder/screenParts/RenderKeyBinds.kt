package com.salat.gbinder.screenParts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.salat.gbinder.R
import com.salat.gbinder.entity.DisplayKeyAction
import com.salat.gbinder.entity.DisplayKeyBind
import com.salat.gbinder.ui.DrawableImage
import com.salat.gbinder.ui.theme.AppTheme

@Composable
internal fun RenderKeyBinds(
    keyBinds: List<DisplayKeyBind>?,
    onDeleteDialog: (String) -> Unit
) {
    val context = LocalContext.current

    keyBinds?.let { binds ->
        (binds).forEach { item ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 26.dp)
            ) {
                Spacer(Modifier.height(24.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Spacer(Modifier.width(16.dp))

                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        item.keyNames.forEach { keyName ->
                            Text(
                                modifier = Modifier
                                    .padding(
                                        horizontal = 4.dp,
                                        vertical = 2.dp
                                    )
                                    .clip(RoundedCornerShape(4.dp))
                                    .background(AppTheme.colors.surfaceMenu)
                                    .padding(
                                        horizontal = 4.dp,
                                        vertical = 2.dp
                                    ),
                                text = keyName,
                                style = AppTheme.typography.dialogSubtitle,
                                overflow = TextOverflow.Ellipsis,
                                maxLines = 1,
                                color = AppTheme.colors.contentPrimary
                            )
                            if (keyName != item.keyNames.last()) {
                                Text(
                                    text = "+",
                                    style = AppTheme.typography.dialogSubtitle,
                                    maxLines = 1,
                                    color = AppTheme.colors.contentPrimary
                                )
                            }
                        }

                    }

                    Text(
                        modifier = Modifier.padding(
                            start = 8.dp,
                            end = 12.dp
                        ),
                        text = "${item.type.lowercase()}   =",
                        style = AppTheme.typography.dialogSubtitle,
                        color = AppTheme.colors.contentPrimary
                    )

                    when (item.action) {

                        DisplayKeyAction.LAUNCH_APP -> Row(
                            modifier = Modifier
                                .weight(1f),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            item.app?.icon?.let { icon ->
                                DrawableImage(
                                    icon = icon,
                                    modifier = Modifier
                                        .size(32.dp)
                                        .clip(RoundedCornerShape(6.dp))
                                )
                                Spacer(Modifier.width(10.dp))
                            }

                            Column {
                                Text(
                                    text = item.app?.appName
                                        ?: stringResource(R.string.not_found),
                                    style = AppTheme.typography.dialogListTitle,
                                    overflow = TextOverflow.Ellipsis,
                                    maxLines = 1,
                                    color = if (item.app == null) AppTheme.colors.deleteButton else AppTheme.colors.contentPrimary
                                )
                                Text(
                                    text = item.app?.packageName
                                        ?: stringResource(R.string.no_such_app),
                                    style = AppTheme.typography.dialogSubtitle,
                                    overflow = TextOverflow.Ellipsis,
                                    maxLines = 1,
                                    color = AppTheme.colors.contentPrimary.copy(
                                        .5f
                                    )
                                )
                            }
                        }

                        DisplayKeyAction.LAUNCH_LINK -> Row(
                            modifier = Modifier
                                .weight(1f),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            item.link?.icon?.let { icon ->
                                Box {
                                    DrawableImage(
                                        icon = icon,
                                        modifier = Modifier
                                            .size(32.dp)
                                            .clip(RoundedCornerShape(6.dp))
                                    )

                                    Icon(
                                        painterResource(R.drawable.ic_link),
                                        null,
                                        tint = AppTheme.colors.contentPrimary,
                                        modifier = Modifier
                                            .offset(x = 2.dp, y = 2.dp)
                                            .size(16.dp)
                                            .clip(CircleShape)
                                            .background(AppTheme.colors.contentPrimary)
                                            .padding(.5.dp)
                                            .clip(CircleShape)
                                            .background(AppTheme.colors.contentAccent)
                                            .padding(3.dp)
                                            .align(Alignment.BottomEnd)
                                    )
                                }
                                Spacer(Modifier.width(10.dp))
                            }

                            Column {
                                Text(
                                    text = item.link?.title
                                        ?: stringResource(R.string.not_found),
                                    style = AppTheme.typography.dialogListTitle,
                                    overflow = TextOverflow.Ellipsis,
                                    maxLines = 1,
                                    color = if (item.link == null) AppTheme.colors.deleteButton else AppTheme.colors.contentPrimary
                                )
                                Text(
                                    text = item.link?.subtitle
                                        ?: stringResource(R.string.no_such_shortcut),
                                    style = AppTheme.typography.dialogSubtitle,
                                    overflow = TextOverflow.Ellipsis,
                                    maxLines = 1,
                                    color = AppTheme.colors.contentPrimary.copy(
                                        .5f
                                    )
                                )
                            }
                        }

                        DisplayKeyAction.TOGGLE_DM -> Row(
                            modifier = Modifier.weight(1f),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            val rawText = context.getString(
                                R.string.switch_to_mode_and_back,
                                item.driveModes
                            )

                            val driveModesText = item.driveModes ?: ""
                            val startIndex =
                                rawText.indexOf(driveModesText)
                            val endIndex =
                                startIndex + driveModesText.length

                            val annotatedText = buildAnnotatedString {
                                append(rawText)
                                addStyle(
                                    style = SpanStyle(
                                        fontWeight = FontWeight.Medium,
                                        color = AppTheme.colors.contentLightAccent
                                    ),
                                    start = startIndex,
                                    end = endIndex
                                )
                            }

                            Text(
                                text = annotatedText,
                                style = AppTheme.typography.cardFormatTitle,
                                color = AppTheme.colors.contentPrimary
                            )
                        }

                        DisplayKeyAction.CAROUSEL_DM -> Row(
                            modifier = Modifier.weight(1f),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            val driveModesText =
                                item.driveModes.orEmpty()
                            val rawText = context.getString(
                                R.string.driving_mode_switching,
                                driveModesText
                            )

                            // Where the injected substring sits inside the full sentence
                            val baseStart =
                                rawText.indexOf(driveModesText)
                            val baseEnd =
                                if (baseStart >= 0) baseStart + driveModesText.length else -1

                            val tokens: List<String> = driveModesText
                                .split(',')
                                .map { it.trim() }
                                .filter { it.isNotEmpty() }

                            val annotated = buildAnnotatedString {
                                append(rawText)

                                if (baseStart >= 0) {
                                    // Sequentially find each token within the injected span
                                    var searchFrom = baseStart
                                    for (t in tokens) {
                                        val idx = rawText.indexOf(
                                            t,
                                            startIndex = searchFrom
                                        )
                                        // ensure the match stays within the injected substring bounds
                                        if (idx >= baseStart && idx + t.length <= baseEnd) {
                                            addStyle(
                                                style = SpanStyle(
                                                    fontWeight = FontWeight.Medium,
                                                    color = AppTheme.colors.contentLightAccent
                                                ),
                                                start = idx,
                                                end = idx + t.length
                                            )
                                            searchFrom = idx + t.length
                                        }
                                    }
                                }
                            }

                            Text(
                                text = annotated,
                                style = AppTheme.typography.cardFormatTitle,
                                color = AppTheme.colors.contentPrimary
                            )
                        }

                        DisplayKeyAction.PHONE_CALL -> Row(
                            modifier = Modifier.weight(1f),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            val annotatedText = buildAnnotatedString {
                                append(stringResource(R.string.call))
                                append(" ")
                                withStyle(
                                    SpanStyle(
                                        fontWeight = FontWeight.Medium,
                                        color = AppTheme.colors.contentLightAccent
                                    )
                                ) { append(item.phone) }
                            }
                            Text(
                                text = annotatedText,
                                style = AppTheme.typography.cardFormatTitle,
                                color = AppTheme.colors.contentPrimary
                            )
                        }

                        DisplayKeyAction.CAMERAS_360 -> Row(
                            modifier = Modifier.weight(1f),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = stringResource(R.string.circle_cameras_desc),
                                style = AppTheme.typography.cardFormatTitle,
                                color = AppTheme.colors.contentPrimary
                            )
                        }

                        DisplayKeyAction.CAROUSEL_LAMP -> Row(
                            modifier = Modifier.weight(1f),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            val lampModesText =
                                item.lampModes.orEmpty()
                            val rawText = context.getString(
                                R.string.headlight_mode_switching,
                                lampModesText
                            )

                            // Where the injected substring sits inside the full sentence
                            val baseStart =
                                rawText.indexOf(lampModesText)
                            val baseEnd =
                                if (baseStart >= 0) baseStart + lampModesText.length else -1

                            val tokens: List<String> = lampModesText
                                .split(',')
                                .map { it.trim() }
                                .filter { it.isNotEmpty() }

                            val annotated = buildAnnotatedString {
                                append(rawText)

                                if (baseStart >= 0) {
                                    // Sequentially find each token within the injected span
                                    var searchFrom = baseStart
                                    for (t in tokens) {
                                        val idx = rawText.indexOf(
                                            t,
                                            startIndex = searchFrom
                                        )
                                        // ensure the match stays within the injected substring bounds
                                        if (idx >= baseStart && idx + t.length <= baseEnd) {
                                            addStyle(
                                                style = SpanStyle(
                                                    fontWeight = FontWeight.Medium,
                                                    color = AppTheme.colors.contentLightAccent
                                                ),
                                                start = idx,
                                                end = idx + t.length
                                            )
                                            searchFrom = idx + t.length
                                        }
                                    }
                                }
                            }

                            Text(
                                text = annotated,
                                style = AppTheme.typography.cardFormatTitle,
                                color = AppTheme.colors.contentPrimary
                            )
                        }

                        DisplayKeyAction.CAROUSEL_AUDIO_SOURCE -> Row(
                            modifier = Modifier.weight(1f),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            val audioListText = item.audioSources.orEmpty()
                            val rawText = context.getString(
                                R.string.audio_source_switching_list,
                                audioListText
                            )
                            val baseStart = rawText.indexOf(audioListText)
                            val baseEnd =
                                if (baseStart >= 0) baseStart + audioListText.length else -1
                            val tokens: List<String> = audioListText
                                .split(',')
                                .map { it.trim() }
                                .filter { it.isNotEmpty() }

                            val annotated = buildAnnotatedString {
                                append(rawText)
                                if (baseStart >= 0) {
                                    var searchFrom = baseStart
                                    for (t in tokens) {
                                        val idx = rawText.indexOf(
                                            t,
                                            startIndex = searchFrom
                                        )
                                        if (idx >= baseStart && idx + t.length <= baseEnd) {
                                            addStyle(
                                                style = SpanStyle(
                                                    fontWeight = FontWeight.Medium,
                                                    color = AppTheme.colors.contentLightAccent
                                                ),
                                                start = idx,
                                                end = idx + t.length
                                            )
                                            searchFrom = idx + t.length
                                        }
                                    }
                                }
                            }
                            Text(
                                text = annotated,
                                style = AppTheme.typography.cardFormatTitle,
                                color = AppTheme.colors.contentPrimary
                            )
                        }

                        DisplayKeyAction.APP_LAUNCHER -> Row(
                            modifier = Modifier.weight(1f),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            DrawableImage(
                                icon = R.mipmap.ic_app_launcher,
                                modifier = Modifier
                                    .size(32.dp)
                                    .clip(RoundedCornerShape(6.dp))
                            )
                            Spacer(Modifier.width(10.dp))
                            Text(
                                text = stringResource(R.string.launcher_name),
                                style = AppTheme.typography.cardFormatTitle,
                                color = AppTheme.colors.contentPrimary
                            )
                        }

                        DisplayKeyAction.TASK_MANAGER -> Row(
                            modifier = Modifier.weight(1f),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = stringResource(R.string.recents),
                                style = AppTheme.typography.cardFormatTitle,
                                color = AppTheme.colors.contentPrimary
                            )
                        }

                        DisplayKeyAction.ANDROID_BACK -> Row(
                            modifier = Modifier.weight(1f),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = stringResource(R.string.back),
                                style = AppTheme.typography.cardFormatTitle,
                                color = AppTheme.colors.contentPrimary
                            )
                        }

                        DisplayKeyAction.ANDROID_HOME -> Row(
                            modifier = Modifier.weight(1f),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = stringResource(R.string.home),
                                style = AppTheme.typography.cardFormatTitle,
                                color = AppTheme.colors.contentPrimary
                            )
                        }

                        DisplayKeyAction.NAVIGATE_TO_PAST_APP -> Row(
                            modifier = Modifier.weight(1f),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = stringResource(R.string.return_to_previous_app),
                                style = AppTheme.typography.cardFormatTitle,
                                color = AppTheme.colors.contentPrimary
                            )
                        }
                    }

                    Spacer(Modifier.width(10.dp))

                    IconButton(
                        modifier = Modifier
                            .size(36.dp)
                            .padding(start = 2.dp),
                        onClick = { onDeleteDialog(item.bindName) }
                    ) {
                        Icon(
                            modifier = Modifier.size(22.dp),
                            imageVector = Icons.Filled.Delete,
                            tint = AppTheme.colors.deleteButton,
                            contentDescription = "delete"
                        )
                    }

                    Spacer(Modifier.width(12.dp))
                }

            }
        }
    }
}
