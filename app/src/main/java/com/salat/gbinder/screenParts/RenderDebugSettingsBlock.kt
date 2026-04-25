package com.salat.gbinder.screenParts

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.datastore.preferences.core.Preferences
import com.salat.gbinder.GlobalState
import com.salat.gbinder.R
import com.salat.gbinder.components.shareText
import com.salat.gbinder.datastore.GeneralPrefs
import com.salat.gbinder.ui.RenderSwitcher
import com.salat.gbinder.ui.theme.AppTheme
import com.salat.gbinder.util.millisToDateTimeString
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
internal fun RenderDebugSettingsBlock(
    isDebugMode: Boolean,
    deepLogs: Boolean,
    onSaveBooleanPref: (Preferences.Key<Boolean>, Boolean) -> Unit
) {
    val scope = rememberCoroutineScope()

    AnimatedVisibility(
        visible = isDebugMode,
        enter = expandVertically(
            expandFrom = Alignment.Top,
            animationSpec = tween(300)
        ),
        exit = shrinkVertically(
            shrinkTowards = Alignment.Top,
            animationSpec = tween(300)
        ),
    ) {
        val context = LocalContext.current
        Column {
            Spacer(Modifier.height(16.dp))

            var logLines by remember {
                mutableStateOf<List<Pair<Long, String>>>(
                    emptyList()
                )
            }
            LaunchedEffect(Unit) {
                GlobalState.logState.collect { logs ->
                    logLines = withContext(Dispatchers.Default) {
                        logs.reversed()
                    }
                }
            }

            SelectionContainer(Modifier.fillMaxWidth()) {
                Box(
                    Modifier
                        .fillMaxWidth()
                        .height(400.dp)
                        //                                                .height(IntrinsicSize.Min)
                        .padding(horizontal = 20.dp)
                        .clip(RoundedCornerShape(14.dp))
                        .background(Color.White.copy(.05f))
                ) {
                    val listState = rememberLazyListState()
                    LazyColumn(
                        state = listState,
                        modifier = Modifier
                            .fillMaxSize(),
                    ) {
                        item(key = -1) {
                            Spacer(Modifier.height(16.dp))
                        }

                        items(
                            items = logLines,
                            key = { item -> item.first },
                        ) { (_, item) ->
                            Text(
                                text = item,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(
                                        vertical = 2.dp,
                                        horizontal = 20.dp
                                    ),
                                style = AppTheme.typography.dialogSubtitle,
                                color = AppTheme.colors.contentPrimary
                            )
                        }

                        item(key = -2) {
                            Spacer(Modifier.height(16.dp))
                        }
                    }

                    Row(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(top = 16.dp, end = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(48.dp)
                                .clip(RoundedCornerShape(14.dp))
                                .background(Color.Black.copy(.24f))
                                .clickable {
                                    scope.launch(Dispatchers.Default) {
                                        val logs =
                                            GlobalState.logState.value
                                        val text =
                                            buildShareableLogsText(logs)
                                        if (text.isBlank()) return@launch
                                        context.shareText(text)
                                    }
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Share,
                                tint = AppTheme.colors.contentPrimary,
                                contentDescription = "share",
                                modifier = Modifier
                                    .size(22.dp)
                            )
                        }

                        Box(
                            modifier = Modifier
                                .size(48.dp)
                                .clip(RoundedCornerShape(14.dp))
                                .background(Color.Black.copy(.24f))
                                .clickable {
                                    scope.launch {
                                        GlobalState.logState.emit(
                                            emptyList()
                                        )
                                    }
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.ic_delete),
                                tint = AppTheme.colors.contentPrimary,
                                contentDescription = "clear",
                                modifier = Modifier.padding(6.dp)
                            )
                        }
                    }
                }
            }
        }
    }

    Spacer(Modifier.height(12.dp))

    // is debug
    RenderSwitcher(
        modifier = Modifier.padding(horizontal = 20.dp),
        title = stringResource(R.string.debug_mode),
        subtitle = stringResource(R.string.show_touch_logs),
        value = isDebugMode,
        enable = true,
        groupDivider = false,
        onChange = { onSaveBooleanPref(GeneralPrefs.DEBUG_MODE, it) }
    )

    Spacer(Modifier.height(12.dp))

    // deep logs
    RenderSwitcher(
        modifier = Modifier.padding(horizontal = 20.dp),
        title = stringResource(R.string.state_logs),
        subtitle = stringResource(R.string.state_logs_desc),
        value = deepLogs,
        enable = isDebugMode,
        groupDivider = false,
        onChange = { onSaveBooleanPref(GeneralPrefs.DEEP_LOGS, it) }
    )
}

private fun buildShareableLogsText(logs: List<Pair<Long, String>>): String {
    return logs.joinToString(separator = "\n") { (timestamp, message) ->
        "${millisToDateTimeString(timestamp)} | $message"
    }
}
