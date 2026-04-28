package com.salat.gbinder.features.launcher

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.salat.gbinder.entity.DisplayLauncherConfig
import com.salat.gbinder.entity.DisplayLauncherItem
import com.salat.gbinder.entity.DisplayLauncherItemType
import com.salat.gbinder.ui.reordable.ReorderableItem
import com.salat.gbinder.ui.reordable.ScrollMoveMode
import com.salat.gbinder.ui.reordable.rememberReorderableLazyGridState
import com.salat.gbinder.ui.theme.AppTheme

@Composable
fun ColumnScope.RenderLauncherMyApps(
    items: List<DisplayLauncherItem>,
    config: DisplayLauncherConfig,
    lockMode: Boolean,
    gridState: LazyGridState,
    onClick: (item: DisplayLauncherItem) -> Unit,
    onLongClick: (item: DisplayLauncherItem, offset: Offset) -> Unit,
    onHideApp: (item: DisplayLauncherItem) -> Unit,
    onMoveItem: (fromIndex: Int, toIndex: Int) -> Unit,
    onReorderDrop: () -> Unit
) = Column(
    modifier = Modifier
        .fillMaxWidth()
        .weight(1f)
) {

    // Reorderable state bound to your existing LazyGridState
    val reorderState = rememberReorderableLazyGridState(
        lazyGridState = gridState,
        scrollMoveMode = ScrollMoveMode.INSERT,
        onMove = { from, to -> onMoveItem(from.index, to.index) }
    )
    val frozenIconColorFilter = remember {
        ColorFilter.colorMatrix(ColorMatrix().apply { setToSaturation(0f) })
    }

    LazyVerticalGrid(
        state = gridState,
        columns = GridCells.Adaptive(minSize = config.iconSize.dp),
        modifier = Modifier
            .fillMaxWidth()
            .weight(1f),
        contentPadding = PaddingValues(config.iconOutSpace.dp),
        verticalArrangement = Arrangement.spacedBy(config.iconInnerSpace.dp),
        horizontalArrangement = Arrangement.spacedBy(config.iconInnerSpace.dp),
    ) {
        items(
            items = items,
            key = { it.id },
            span = { item ->
                if (item.type == DisplayLauncherItemType.GROUP) {
                    GridItemSpan(maxLineSpan)
                } else {
                    GridItemSpan(1)
                }
            }
        ) { app ->
            ReorderableItem(
                state = reorderState,
                key = app.id,
                enabled = !lockMode
            ) { _  /* isDragging */ ->
                val dragModifier = if (!lockMode) {
                    Modifier.draggableHandle(
                        onDragStopped = { onReorderDrop() }
                    )
                } else Modifier

                when (app.type) {
                    DisplayLauncherItemType.APP, DisplayLauncherItemType.ACTIVITY, DisplayLauncherItemType.MACRO -> Box(
                        dragModifier
                    ) {
                        RenderLauncherMyAppCell(
                            app = app,
                            cellSize = config.iconSize,
                            enableText = config.iconTextEnable,
                            iconRound = config.iconRound,
                            textSize = config.iconTextSize,
                            textPadding = config.iconTextPadding,
                            enableShortcuts = config.enableShortcuts,
                            shortcutSize = config.shortcutSize,
                            enableMultiline = config.iconTextMultiline,
                            frozenIconColorFilter = frozenIconColorFilter,
                            lockMode = lockMode,
                            enableClick = lockMode,
                            onHideApp = onHideApp,
                            onClick = { onClick(app) },
                            onLongClick = { offset -> onLongClick(app, offset) }
                        )
                    }

                    DisplayLauncherItemType.GROUP -> Row(dragModifier) {
                        Text(
                            modifier = Modifier
                                .weight(1f, false)
                                .offset(y = 6.dp)
                                .then(
                                    if (lockMode) {
                                        Modifier
                                            .pointerInput(app.title) {
                                                detectTapGestures(
                                                    onLongPress = { onLongClick(app, it) }
                                                )
                                            }
                                    } else Modifier
                                ),
                            text = app.title,
                            color = AppTheme.colors.contentPrimary,
                            style = AppTheme.typography.overlayLauncherSection.copy(
                                fontSize = config.dividerTextSize.sp,
                                lineHeight = config.dividerTextSize.sp,
                                fontWeight = if (config.dividerTextBold) FontWeight.Bold else FontWeight.Medium
                            )
                        )
                        if (!lockMode) {
                            Box(
                                modifier = Modifier
                                    .padding(start = 8.dp)
                                    .clip(CircleShape)
                                    .background(AppTheme.colors.accentDelete)
                                    .padding(4.dp)
                                    .clickable { onHideApp(app) }
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Close,
                                    modifier = Modifier
                                        .size(18.dp),
                                    tint = Color.White,
                                    contentDescription = "unlock"
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
