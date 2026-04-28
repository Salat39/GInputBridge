package com.salat.gbinder.features.launcher

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.salat.gbinder.R
import com.salat.gbinder.components.launchApp
import com.salat.gbinder.components.toast
import com.salat.gbinder.entity.DisplayLauncherApp
import com.salat.gbinder.entity.DisplayLauncherConfig
import timber.log.Timber

@Composable
fun ColumnScope.RenderLauncherAllApps(
    items: List<DisplayLauncherApp>,
    config: DisplayLauncherConfig,
    gridState: LazyGridState,
    onLongClick: (item: DisplayLauncherApp, offset: Offset) -> Unit,
    onCancelLauncher: () -> Unit
) = Box(
    modifier = Modifier
        .fillMaxWidth()
        .weight(1f)
) {
    val context = LocalContext.current
    val frozenIconColorFilter = remember {
        ColorFilter.colorMatrix(ColorMatrix().apply { setToSaturation(0f) })
    }

    LazyVerticalGrid(
        state = gridState,
        columns = GridCells.Adaptive(minSize = config.iconSize.dp),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(config.iconOutSpace.dp),
        verticalArrangement = Arrangement.spacedBy(config.iconInnerSpace.dp),
        horizontalArrangement = Arrangement.spacedBy(config.iconInnerSpace.dp),
    ) {
        items(
            items = items,
            key = { it.id }
        ) { app ->
            RenderLauncherAllAppCell(
                app = app,
                cellSize = config.iconSize,
                enableText = config.iconTextEnable,
                iconRound = config.iconRound,
                textSize = config.iconTextSize,
                textPadding = config.iconTextPadding,
                enableMultiline = config.iconTextMultiline,
                frozenIconColorFilter = frozenIconColorFilter,
                onClick = {
                    if (app.isFrozen) {
                        context.toast(context.getString(R.string.app_frozen_launch_blocked))
                        return@RenderLauncherAllAppCell
                    }
                    Timber.d("[LAUNCHER] open ${app.id}")
                    context.launchApp(app.packageName, app.launcherActivity)
                    onCancelLauncher()
                },
                onLongClick = { offset -> onLongClick(app, offset) }
            )
        }
    }
}
