package com.salat.gbinder.features.launcher

import android.net.Uri
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.EaseOutCubic
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.salat.gbinder.R
import com.salat.gbinder.entity.CarFunction
import com.salat.gbinder.entity.CarFunctionPalette
import com.salat.gbinder.features.carFunctions.CarFunctionState
import com.salat.gbinder.features.carFunctions.ToggleSprite
import com.salat.gbinder.features.carFunctions.isLauncherAction
import com.salat.gbinder.features.carFunctions.toggleSprite
import com.salat.gbinder.ui.theme.AppTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

// Layout ratios are relative to the cell size
private const val GLYPH_RATIO = 44f / 86f
private const val GAP_RATIO = 7f / 86f
private const val BAR_HEIGHT_RATIO = 4f / 86f
private const val BAR_WIDTH_RATIO = 14f / 86f
private const val BAR_SPACING_RATIO = 5f / 86f
private const val TOGGLE_WIDTH_RATIO = 36f / 86f
// Content shrinks as the corner radius ratio grows from the start value to a circle
private const val SHRINK_START_CORNER_RATIO = 18f / 86f
private const val CIRCLE_CORNER_RATIO = .5f
private const val GLYPH_SHRINK = .10f
private const val BAR_SHRINK = .30f
private const val PRESSED_SCALE = .93f
private const val COLOR_ANIMATION_MS = 150
private const val GLYPH_SWAP_MS = 180
private const val GLYPH_SWAP_SCALE = .6f
private const val FAN_SPIN_MS = 150
private const val FAN_STOP_MS = 650
private const val SNOWFLAKE_SCALE = 1.1f

@Composable
fun RenderLauncherCarFunctionIcon(
    function: CarFunction?,
    state: CarFunctionState?,
    customIcon: Uri?,
    cellSize: Int,
    iconRound: Int,
    available: Boolean,
    palette: CarFunctionPalette,
    accent: Boolean,
    pressed: Boolean
) {
    val indicator = state is CarFunctionState.Level || state is CarFunctionState.Toggle
    val indicatorLayout = function?.isLauncherAction() == false
    val cornerRatio = iconRound.toFloat() / cellSize
    val circleness = ((cornerRatio - SHRINK_START_CORNER_RATIO) /
        (CIRCLE_CORNER_RATIO - SHRINK_START_CORNER_RATIO)).coerceIn(0f, 1f)
    val glyphFactor = 1f - GLYPH_SHRINK * circleness
    val barFactor = 1f - BAR_SHRINK * circleness
    val glyphSize = (cellSize * GLYPH_RATIO * glyphFactor).dp
    val gap = (cellSize * GAP_RATIO * glyphFactor).dp
    val barHeight = maxOf((cellSize * BAR_HEIGHT_RATIO * barFactor).dp, 2.dp)
    val barWidth = (cellSize * BAR_WIDTH_RATIO * barFactor).dp
    val barSpacing = (cellSize * BAR_SPACING_RATIO * barFactor).dp
    val toggleWidth = (cellSize * TOGGLE_WIDTH_RATIO * barFactor).dp
    val active = (state is CarFunctionState.Level && state.index > 0) ||
        (state is CarFunctionState.Toggle && state.on)
    val colors = AppTheme.colors
    val paletteColors = palette.colors(colors.isDark)
    val tileActive = if (accent) paletteColors.tile else paletteColors.softTile
    val contentActive = if (accent) Color.White else paletteColors.softContent
    val indicatorActive = if (accent) Color.White.copy(alpha = .35f) else colors.launcherFunctionIndicator
    val tileColor by animateColorAsState(
        targetValue = if (active) tileActive else colors.launcherFunctionTile,
        animationSpec = tween(COLOR_ANIMATION_MS),
        label = "carFunctionTileColor"
    )
    val contentColor by animateColorAsState(
        targetValue = if (active) contentActive else colors.launcherFunctionContent,
        animationSpec = tween(COLOR_ANIMATION_MS),
        label = "carFunctionContentColor"
    )
    val scale by animateFloatAsState(
        targetValue = if (pressed) PRESSED_SCALE else 1f,
        animationSpec = spring(stiffness = Spring.StiffnessMediumLow),
        label = "carFunctionPressScale"
    )
    val litColor = if (customIcon != null) paletteColors.customIconLit else contentColor
    val unlitColor = when {
        customIcon != null -> Color.White.copy(.3f)
        active -> indicatorActive
        else -> colors.launcherFunctionIndicator
    }
    val title = function?.let { stringResource(it.titleRes) }
    val stateText = when (state) {
        is CarFunctionState.Toggle ->
            stringResource(if (state.on) R.string.launcher_fn_on else R.string.launcher_fn_off)
        is CarFunctionState.Level -> "${state.index}/${state.count}"
        CarFunctionState.Unknown, CarFunctionState.Action, null -> null
    }
    Box(
        modifier = Modifier.size(cellSize.dp)
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
            }
            .then(if (!available) Modifier.alpha(DISABLED_APP_TRANSPARENCY) else Modifier)
            .clip(RoundedCornerShape(iconRound.dp))
            .then(if (customIcon == null) Modifier.background(tileColor) else Modifier)
            .semantics {
                if (stateText != null) stateDescription = stateText
            }
    ) {
        if (customIcon != null) {
            val context = LocalContext.current
            val px = with(LocalDensity.current) { cellSize.dp.roundToPx() }
            val request = remember(context, customIcon, px) {
                launcherIconRequest(context, null, customIcon, px)
            }
            AsyncImage(
                model = request,
                contentDescription = title,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            if (indicator) {
                Box(
                    Modifier.align(Alignment.BottomCenter).fillMaxWidth().height((cellSize * .5f).dp)
                        .background(
                            Brush.verticalGradient(
                                0f to Color.Transparent,
                                0.4f to Color.Black.copy(.55f),
                                1f to Color.Black.copy(.65f)
                            )
                        )
                )
            }
        }
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (customIcon == null) {
                val glyphRes = when {
                    function == null -> R.drawable.ic_empty
                    state is CarFunctionState.Level -> function.iconResForLevel(state.index)
                    state is CarFunctionState.Toggle -> function.toggleSprite()?.let { spriteFrame(it, state.on) }
                        ?: function.iconRes
                    else -> function.iconRes
                }
                val glyphModifier = Modifier.size(glyphSize).then(
                    when (function) {
                        CarFunction.ME_HOT, CarFunction.ME_COLD -> activationAnimation(function, state)
                        else -> Modifier
                    }
                )
                val glyphSwap = glyphSwap(function)
                if (glyphSwap != null) {
                    AnimatedContent(
                        targetState = glyphRes,
                        transitionSpec = { glyphSwap },
                        label = "carFunctionGlyphSwap"
                    ) { res ->
                        Icon(
                            painter = painterResource(res),
                            contentDescription = title,
                            tint = contentColor,
                            modifier = glyphModifier
                        )
                    }
                } else {
                    Icon(
                        painter = painterResource(glyphRes),
                        contentDescription = title,
                        tint = contentColor,
                        modifier = glyphModifier
                    )
                }
            } else {
                Spacer(Modifier.size(glyphSize))
            }
            if (indicatorLayout) {
                Spacer(Modifier.height(gap))
                Row(
                    modifier = Modifier.height(barHeight),
                    horizontalArrangement = Arrangement.spacedBy(barSpacing),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    when (state) {
                        is CarFunctionState.Level -> repeat(state.count) { index ->
                            val lit = if (state.cumulative) index < state.index else index == state.index - 1
                            Box(
                                Modifier.size(barWidth, barHeight)
                                    .background(if (lit) litColor else unlitColor, RoundedCornerShape(50))
                            )
                        }
                        is CarFunctionState.Toggle -> Box(
                            Modifier.size(toggleWidth, barHeight)
                                .background(if (state.on) litColor else unlitColor, RoundedCornerShape(50))
                        )
                        CarFunctionState.Unknown, CarFunctionState.Action, null -> Unit
                    }
                }
            }
        }
    }
}

private const val SPRITE_ANIMATION_MS = 500L

// Frames play only on a state change, the first composition shows the static frame
@Composable
private fun spriteFrame(sprite: ToggleSprite, on: Boolean): Int {
    var shownOn by remember { mutableStateOf(on) }
    var playing by remember { mutableStateOf<Int?>(null) }
    LaunchedEffect(on) {
        if (on == shownOn) return@LaunchedEffect
        shownOn = on
        val frameMs = SPRITE_ANIMATION_MS / sprite.turnOn.size
        for (frame in if (on) sprite.turnOn else sprite.turnOff) {
            playing = frame
            delay(frameMs)
        }
        playing = null
    }
    return playing ?: (if (shownOn) sprite.turnOn else sprite.turnOff).last()
}

private fun glyphSwap(function: CarFunction?): ContentTransform? = when (function) {
    CarFunction.LIGHT ->
        (scaleIn(tween(GLYPH_SWAP_MS), GLYPH_SWAP_SCALE) + fadeIn(tween(GLYPH_SWAP_MS)))
            .togetherWith(scaleOut(tween(GLYPH_SWAP_MS), GLYPH_SWAP_SCALE) + fadeOut(tween(GLYPH_SWAP_MS)))
    else -> null
}

// Plays once when a toggle turns on, the first composition never animates
@Composable
private fun activationAnimation(function: CarFunction, state: CarFunctionState?): Modifier {
    val on = state is CarFunctionState.Toggle && state.on
    val rotation = remember { Animatable(0f) }
    val scale = remember { Animatable(1f) }
    var shownOn by remember { mutableStateOf(on) }
    LaunchedEffect(on) {
        if (on == shownOn) return@LaunchedEffect
        shownOn = on
        if (!on) return@LaunchedEffect
        when (function) {
            CarFunction.ME_HOT -> {
                rotation.snapTo(0f)
                rotation.animateTo(360f, tween(FAN_SPIN_MS, easing = LinearEasing))
                rotation.animateTo(720f, tween(FAN_STOP_MS, easing = EaseOutCubic))
                rotation.snapTo(0f)
            }
            CarFunction.ME_COLD -> {
                launch {
                    scale.animateTo(1f, keyframes {
                        durationMillis = 450
                        SNOWFLAKE_SCALE at 120 using FastOutSlowInEasing
                        SNOWFLAKE_SCALE at 320
                    })
                }
                rotation.animateTo(0f, keyframes {
                    durationMillis = 360
                    -7f at 60
                    7f at 140
                    -5f at 220
                    3f at 290
                })
            }
            else -> Unit
        }
    }
    return Modifier.graphicsLayer {
        rotationZ = rotation.value
        scaleX = scale.value
        scaleY = scale.value
    }
}
