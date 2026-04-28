package com.salat.gbinder.features.launcher

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.salat.gbinder.R
import com.salat.gbinder.entity.DisplayLauncherApp
import com.salat.gbinder.entity.DisplayLauncherItemType
import com.salat.gbinder.ui.DrawableImage
import com.salat.gbinder.util.rememberTimeLockedBoolean

@Composable
fun RenderLauncherAllAppCell(
    app: DisplayLauncherApp,
    cellSize: Int,
    enableText: Boolean,
    iconRound: Int,
    textSize: Int,
    textPadding: Int,
    enableMultiline: Boolean,
    enableShortcuts: Boolean = false,
    shortcutSize: Int = 0,
    shortcutType: DisplayLauncherItemType? = null,
    sizeSensitive: Boolean = true,
    frozenIconColorFilter: ColorFilter,
    onClick: () -> Unit = {},
    onLongClick: (offset: Offset) -> Unit
) {
    var clickLock by rememberTimeLockedBoolean(1000L)
    var rootOffset by remember { mutableStateOf(Offset.Zero) }
    val frozenModifier = if (app.isFrozen) Modifier.alpha(.6f) else Modifier

    Column(
        modifier = Modifier
            .onGloballyPositioned { coordinates ->
                rootOffset = Offset(coordinates.positionInRoot().x, coordinates.positionInRoot().y)
            }
            .pointerInput(Unit) {
                detectTapGestures(
                    onLongPress = {
                        onLongClick(
                            Offset(
                                x = it.x + rootOffset.x,
                                y = it.y + rootOffset.y
                            )
                        )
                    },
                    onTap = {
                        if (!clickLock) {
                            onClick()
                        }
                        clickLock = true
                    }
                )
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val ctx = LocalContext.current
        val pxSize = with(LocalDensity.current) { cellSize.dp.roundToPx() }
        val ir = app.iconRef

        val model = remember(app.iconRef, app.customIcon, pxSize.takeIf { sizeSensitive }) {
            val builder = ImageRequest.Builder(ctx)
                .size(pxSize, pxSize)
                .precision(coil.size.Precision.EXACT)
                .allowHardware(false)
                .memoryCachePolicy(coil.request.CachePolicy.ENABLED)
                .diskCachePolicy(coil.request.CachePolicy.DISABLED)
                .dispatcher(kotlinx.coroutines.Dispatchers.IO)

            if (app.customIcon != null) {
                builder.data(app.customIcon)
            } else {
                val stableKey =
                    "pkg:${ir.packageName}|res:${ir.resId}|dpi:${ir.densityDpi}|vc:${ir.versionCode}|w:${pxSize}|h:${pxSize}"

                builder.data(app.iconRef)
                    .memoryCacheKey(stableKey)
                    .placeholderMemoryCacheKey(stableKey)
            }

            builder.build()
        }
        Box(Modifier.size(cellSize.dp)) {
            AsyncImage(
                model = model,
                contentDescription = app.appName,
                modifier = Modifier
                    .size(cellSize.dp)
                    .then(if (iconRound != 0) Modifier.clip(RoundedCornerShape(iconRound.dp)) else Modifier)
                    .then(frozenModifier),
                contentScale = ContentScale.Crop,
                colorFilter = if (app.isFrozen) frozenIconColorFilter else null
            )

            // Settings preview
            if (enableShortcuts) {
                DrawableImage(
                    if (shortcutType == DisplayLauncherItemType.ACTIVITY) {
                        R.drawable.ic_l_cursor
                    } else {
                        R.drawable.ic_l_link
                    },
                    sizeDp = shortcutSize,
                    modifier = Modifier
                        .offset(x = 2.dp, y = 2.dp)
                        .size(shortcutSize.dp)
                        .align(Alignment.BottomEnd)
                )
            }
        }

        if (enableText) {
            Spacer(Modifier.height(textPadding.dp))

            LauncherAppTitle(
                title = app.appName,
                isFrozen = app.isFrozen,
                textSize = textSize,
                enableMultiline = enableMultiline,
                modifier = Modifier
                    .then(frozenModifier)
            )
        }
    }
}