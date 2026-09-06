package com.salat.gbinder.features.launcher

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.salat.gbinder.R
import com.salat.gbinder.entity.DisplayLauncherItem
import com.salat.gbinder.entity.DisplayLauncherItemType
import com.salat.gbinder.ui.theme.AppTheme
import com.salat.gbinder.util.rememberTimeLockedBoolean

@Composable
fun RenderLauncherMyAppCell(
    app: DisplayLauncherItem,
    cellSize: Int,
    enableText: Boolean,
    iconRound: Int,
    textSize: Int,
    textPadding: Int,
    enableShortcuts: Boolean,
    shortcutSize: Int,
    enableMultiline: Boolean,
    sizeSensitive: Boolean = true,
    frozenIconColorFilter: ColorFilter,
    lockMode: Boolean,
    enableClick: Boolean,
    onHideApp: (item: DisplayLauncherItem) -> Unit,
    onClick: (item: DisplayLauncherItem) -> Unit = {},
    onLongClick: (item: DisplayLauncherItem, offset: Offset) -> Unit,
    iconContent: (@Composable BoxScope.(pressed: Boolean) -> Unit)? = null
) {
    var clickLock by rememberTimeLockedBoolean(1000L)
    var rootOffset by remember { mutableStateOf(Offset.Zero) }
    var pressed by remember(app.id) { mutableStateOf(false) }
    val frozenModifier = if (app.isFrozen) Modifier.alpha(DISABLED_APP_TRANSPARENCY) else Modifier

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .onGloballyPositioned { coordinates ->
                rootOffset = Offset(coordinates.positionInRoot().x, coordinates.positionInRoot().y)
            }
            .then(
                if (enableClick) {
                    Modifier
                        .pointerInput(app) {
                            detectTapGestures(
                                onPress = {
                                    pressed = true
                                    try {
                                        tryAwaitRelease()
                                    } finally {
                                        pressed = false
                                    }
                                },
                                onLongPress = {
                                    onLongClick(
                                        app,
                                        Offset(
                                            x = it.x + rootOffset.x,
                                            y = it.y + rootOffset.y
                                        )
                                    )
                                },
                                onTap = {
                                    if (!clickLock) {
                                        onClick(app)
                                    }
                                    // Car function tiles cycle levels by repeated taps
                                    if (app.type != DisplayLauncherItemType.CAR_FUNCTION) {
                                        clickLock = true
                                    }
                                }
                            )
                        }
                } else Modifier
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val ctx = LocalContext.current
        val pxSize = with(LocalDensity.current) { cellSize.dp.roundToPx() }
        Box(Modifier.size(cellSize.dp)) {
            val ir = app.iconRef

            if (iconContent != null) {
                iconContent(pressed)
            } else if (ir != null) {

                val model = remember(app.iconRef, app.customIcon, pxSize.takeIf { sizeSensitive }) {
                    launcherIconRequest(ctx, app.iconRef, app.customIcon, pxSize)
                }
                AsyncImage(
                    model = model,
                    contentDescription = app.title,
                    modifier = Modifier
                        .size(cellSize.dp)
                        .then(if (iconRound != 0) Modifier.clip(RoundedCornerShape(iconRound.dp)) else Modifier)
                        .then(frozenModifier),
                    contentScale = ContentScale.Crop,
                    colorFilter = if (app.isFrozen) frozenIconColorFilter else null
                )
            } else {
                Box(
                    Modifier
                        .size(cellSize.dp)
                        .then(if (iconRound != 0) Modifier.clip(RoundedCornerShape(iconRound.dp)) else Modifier)
                        .background(
                            if (AppTheme.colors.isDark) {
                                AppTheme.colors.surfaceSettingsLayer1
                            } else AppTheme.colors.contentAccent
                        )
                ) {
                    Icon(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .size(38.dp),
                        painter = painterResource(R.drawable.ic_empty),
                        tint = Color.White,
                        contentDescription = "empty"
                    )
                }
            }

            if (enableShortcuts && (app.type == DisplayLauncherItemType.ACTIVITY || app.type == DisplayLauncherItemType.MACRO)) {
                Image(
                    painter = painterResource(
                        when {
                            app.type == DisplayLauncherItemType.ACTIVITY -> R.drawable.ic_l_cursor

                            app.isCall -> R.drawable.ic_l_phone

                            app.isSplit -> R.drawable.ic_l_split

                            else -> R.drawable.ic_l_link
                        }
                    ),
                    contentDescription = null,
                    modifier = Modifier
                        .offset(x = 2.dp, y = 2.dp)
                        .size(shortcutSize.dp)
                        .align(Alignment.BottomEnd)
                )
            }

            if (!lockMode) {
                // Icon offset hack
                var hideAppSignal by remember { mutableStateOf(true) }
                LaunchedEffect(hideAppSignal) {
                    if (!hideAppSignal) onHideApp(app)
                }
                if (hideAppSignal) {
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .offset(x = 6.dp, y = (-6).dp)
                            .padding(start = 8.dp)
                            .clip(CircleShape)
                            .background(AppTheme.colors.accentDelete)
                            .padding(4.dp)
                            .clickable { hideAppSignal = false }
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

        if (enableText) {
            Spacer(Modifier.height(textPadding.dp))

            LauncherAppTitle(
                title = app.title,
                isFrozen = app.isFrozen,
                textSize = textSize,
                enableMultiline = enableMultiline,
                modifier = Modifier
                    .then(frozenModifier)
            )
        }
    }
}
