package com.salat.gbinder.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.RangeSliderState
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderColors
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.SliderState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.salat.gbinder.ui.theme.AppTheme
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterial3Api::class)
@Suppress("UNCHECKED_CAST", "UNUSED_EXPRESSION")
@Composable
fun <T> ValueSlider(
    modifier: Modifier = Modifier,
    value: T,
    valueRange: ClosedRange<T>,
    onValueChange: (T) -> Unit,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    defaultMark: T? = null,
    step: T? = null
) where T : Number, T : Comparable<T> {
    val thumbColor: Color
    val activeTrackColor: Color
    val inactiveTrackColor: Color
    val isDark = AppTheme.colors.isDark

    /*
    // Colors for rendering (will be used in Canvas as well)
    val colors = SliderDefaults.colors(
        thumbColor = if (enabled) AppTheme.colors.contentAccent else AppTheme.colors.sliderPassive,
        activeTrackColor = if (enabled) AppTheme.colors.contentAccent else AppTheme.colors.sliderPassive,
        activeTickColor = if (enabled) AppTheme.colors.contentAccent else AppTheme.colors.sliderPassive,
        inactiveTickColor = AppTheme.colors.sliderPassive,
        inactiveTrackColor = AppTheme.colors.sliderPassive,
        disabledThumbColor = AppTheme.colors.sliderPassive,
        disabledActiveTrackColor = AppTheme.colors.sliderPassive,
        disabledInactiveTrackColor = AppTheme.colors.sliderPassive
    )
    // Replace default track with transparent colors to draw everything in Canvas
    val sliderColors = SliderDefaults.colors(
        thumbColor = if (enabled) AppTheme.colors.contentAccent else AppTheme.colors.sliderPassive,
        activeTrackColor = Color.Transparent,
        inactiveTrackColor = Color.Transparent,
        activeTickColor = Color.Transparent,
        inactiveTickColor = Color.Transparent,
        disabledThumbColor = AppTheme.colors.sliderPassive,
        disabledActiveTrackColor = Color.Transparent,
        disabledInactiveTrackColor = Color.Transparent
    )
     */

    if (isDark) {
        thumbColor = if (enabled) AppTheme.colors.contentAccent else AppTheme.colors.sliderPassive
        activeTrackColor =
            if (enabled) AppTheme.colors.contentAccent else AppTheme.colors.sliderPassive
        inactiveTrackColor = AppTheme.colors.sliderPassive
    } else {
        thumbColor = if (enabled) AppTheme.colors.contentAccent else AppTheme.colors.sliderPassive
        activeTrackColor = AppTheme.colors.contentAccent
        inactiveTrackColor = AppTheme.colors.sliderPassive
    }

    // Make the Slider's track transparent (the built-in M3 track is not drawn)
    val colors: SliderColors = SliderDefaults.colors(
        thumbColor = thumbColor,
        activeTrackColor = Color.Transparent,
        inactiveTrackColor = Color.Transparent,
        disabledThumbColor = AppTheme.colors.sliderPassive,
        disabledActiveTrackColor = AppTheme.colors.sliderPassive,
        disabledInactiveTrackColor = AppTheme.colors.sliderPassive
    )

    val minValue = valueRange.start.toFloat()
    val maxValue = valueRange.endInclusive.toFloat()

    val discreteSteps = if (step != null && step.toFloat() > 0) {
        val stepsCalculated = ((maxValue - minValue) / step.toFloat()).toInt() - 1
        if (stepsCalculated < 0) 0 else stepsCalculated
    } else 0

    fun normalizeToStep(valueF: Float): Float {
        val coerced = valueF.coerceIn(minValue, maxValue)
        val stepValue = step?.toFloat() ?: return coerced
        if (stepValue <= 0f) return coerced
        val index = ((coerced - minValue) / stepValue).roundToInt()
        return (minValue + index * stepValue).coerceIn(minValue, maxValue)
    }

    val layoutDir = LocalLayoutDirection.current

    Column(modifier = modifier.fillMaxWidth()) {
        Spacer(Modifier.height(12.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 2.dp, end = 4.dp)
        ) {
            // Canvas: Draw defaultMark (background), then the track (inactive + active)
            Canvas(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center)
            ) {
                val fullWidth = size.width
                val fullHeight = size.height

                // Track parameters
                val trackHeightPx = 4.dp.toPx()
                val trackTop = (fullHeight - trackHeightPx) / 2f
                val trackRadius = trackHeightPx / 2f

                // Calculate thumb's horizontal position
                val minF = valueRange.start.toFloat()
                val maxF = valueRange.endInclusive.toFloat()
                val curF = value.toFloat().coerceIn(minF, maxF)
                val frac = if (maxF > minF) (curF - minF) / (maxF - minF) else 0f
                val thumbRadiusPx = 10.dp.toPx() // 20.dp / 2
                val xCenter = if (layoutDir == LayoutDirection.Ltr) {
                    thumbRadiusPx + (fullWidth - 2 * thumbRadiusPx) * frac
                } else {
                    fullWidth - thumbRadiusPx - (fullWidth - 2 * thumbRadiusPx) * frac
                }

                // If defaultMark exists, draw a background rectangle (6dp height, 1dp radius),
                // centered in the track, with the central part hidden by the track
                defaultMark?.let { markValue ->
                    val markF = markValue.toFloat().coerceIn(minF, maxF)
                    val markFrac = if (maxF > minF) (markF - minF) / (maxF - minF) else 0f
                    val xMark = if (layoutDir == LayoutDirection.Ltr) {
                        thumbRadiusPx + (fullWidth - 2 * thumbRadiusPx) * markFrac
                    } else {
                        fullWidth - thumbRadiusPx - (fullWidth - 2 * thumbRadiusPx) * markFrac
                    }

                    val extraPx = 4.dp.toPx() // Default mark height
                    val markHeightPx = trackHeightPx + 2 * extraPx
                    val markTop = trackTop - extraPx

                    val markWidthPx = 2.dp.toPx()
                    drawRoundRect(
                        color = inactiveTrackColor,
                        topLeft = Offset(xMark - markWidthPx / 2f, markTop),
                        size = Size(markWidthPx, markHeightPx),
                        cornerRadius = CornerRadius(markWidthPx / 2f, markWidthPx / 2f)
                    )
                }

                val xTrackShift = 4.dp.toPx()

                // Draw the "inactive" track (background), 4dp height, with rounded ends (radius = 2dp)
                drawRoundRect(
                    color = inactiveTrackColor,
                    topLeft = Offset(xTrackShift, trackTop),
                    size = Size(fullWidth - (xTrackShift * 2), trackHeightPx),
                    cornerRadius = CornerRadius(trackRadius, trackRadius)
                )

                // Draw the "active" part of the track up to the thumb's center (xCenter)
                drawRoundRect(
                    color = activeTrackColor,
                    topLeft = Offset(xTrackShift, trackTop),
                    size = Size(xCenter, trackHeightPx),
                    cornerRadius = CornerRadius(trackRadius, trackRadius)
                )
            }

            // Material 3 Slider with a transparent track and a 20dp round thumb
            Slider(
                // Specify all parameters by name to call the correct overload
                value = value.toFloat(),
                onValueChange = { newF ->
                    val normalized = normalizeToStep(newF)
                    val converted: T = when (value) {
                        is Int -> normalized.roundToInt() as T
                        is Long -> normalized.roundToInt().toLong() as T
                        is Double -> normalized.toDouble() as T
                        is Float -> normalized as T
                        is Short -> normalized.roundToInt().toShort() as T
                        is Byte -> normalized.roundToInt().toByte() as T
                        else -> normalized as T
                    }
                    onValueChange(converted)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(24.dp)
                    .align(Alignment.Center),
                enabled = enabled,
                valueRange = valueRange.start.toFloat()..valueRange.endInclusive.toFloat(),
                interactionSource = interactionSource,
                colors = colors,
                steps = discreteSteps,
                onValueChangeFinished = null,

                // Track: transparent (4dp), only serves as a touch area
                track = { state: SliderState ->
                    SliderDefaults.Track(
                        sliderState = state,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(4.dp),
                        enabled = enabled,
                        colors = colors,
                        thumbTrackGapSize = 0.dp,
                        trackInsideCornerSize = 0.dp,
                        drawStopIndicator = { null },
                        drawTick = { _, _ -> }
                    )
                },
                // Thumb: 16dp × 1.25 = 20dp circle
                thumb = { state: SliderState ->
                    Box(
                        modifier = Modifier
                            .size(16.dp)
                            .scale(scaleX = 1.25f, scaleY = 1.25f)
                            .background(color = thumbColor, shape = CircleShape)
                    )
                }
            )
        }

        Spacer(Modifier.height(12.dp))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Suppress("UNCHECKED_CAST")
@Composable
fun <T> RangeValueSlider(
    modifier: Modifier = Modifier,
    startValue: T,
    endValue: T,
    valueRange: ClosedRange<T>,
    onValueChange: (startValue: T, endValue: T) -> Unit,
    enabled: Boolean = true,
    startInteractionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    endInteractionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    defaultMark: T? = null,
    defaultStartMark: T? = null,
    defaultEndMark: T? = null,
    step: T? = null
) where T : Number, T : Comparable<T> {
    val thumbColor: Color
    val activeTrackColor: Color
    val inactiveTrackColor: Color
    val isDark = AppTheme.colors.isDark

    if (isDark) {
        thumbColor = if (enabled) AppTheme.colors.contentAccent else AppTheme.colors.sliderPassive
        activeTrackColor =
            if (enabled) AppTheme.colors.contentAccent else AppTheme.colors.sliderPassive
        inactiveTrackColor = AppTheme.colors.sliderPassive
    } else {
        thumbColor = if (enabled) AppTheme.colors.contentAccent else AppTheme.colors.sliderPassive
        activeTrackColor = AppTheme.colors.contentAccent
        inactiveTrackColor = AppTheme.colors.sliderPassive
    }

    // Make the RangeSlider's track transparent (the built-in M3 track is not drawn)
    val colors: SliderColors = SliderDefaults.colors(
        thumbColor = thumbColor,
        activeTrackColor = Color.Transparent,
        inactiveTrackColor = Color.Transparent,
        disabledThumbColor = AppTheme.colors.sliderPassive,
        disabledActiveTrackColor = AppTheme.colors.sliderPassive,
        disabledInactiveTrackColor = AppTheme.colors.sliderPassive
    )

    val minValue = valueRange.start.toFloat()
    val maxValue = valueRange.endInclusive.toFloat()

    val discreteSteps = if (step != null && step.toFloat() > 0) {
        val stepsCalculated = ((maxValue - minValue) / step.toFloat()).toInt() - 1
        if (stepsCalculated < 0) 0 else stepsCalculated
    } else 0

    val minDistanceF = step?.toFloat()?.takeIf { it > 0f } ?: 0f

    fun normalizeToStep(valueF: Float): Float {
        val coerced = valueF.coerceIn(minValue, maxValue)
        val stepValue = step?.toFloat() ?: return coerced
        if (stepValue <= 0f) return coerced
        val index = ((coerced - minValue) / stepValue).roundToInt()
        return (minValue + index * stepValue).coerceIn(minValue, maxValue)
    }

    fun enforceMinDistance(
        currentStartF: Float,
        currentEndF: Float,
        requestedStartF: Float,
        requestedEndF: Float
    ): ClosedFloatingPointRange<Float> {
        if (minDistanceF <= 0f) {
            val startF = min(requestedStartF, requestedEndF)
            val endF = max(requestedStartF, requestedEndF)
            return startF..endF
        }

        val available = maxValue - minValue
        if (available < minDistanceF) {
            // If the range is smaller than one step, the constraint cannot be satisfied.
            return minValue..maxValue
        }

        var startF = min(requestedStartF, requestedEndF)
        var endF = max(requestedStartF, requestedEndF)

        if (endF - startF >= minDistanceF) {
            return startF..endF
        }

        // Detect which thumb the user is dragging by comparing deltas to current values.
        val movedStart = abs(requestedStartF - currentStartF) >= abs(requestedEndF - currentEndF)

        if (movedStart) {
            startF = endF - minDistanceF
            if (startF < minValue) {
                startF = minValue
                endF = (startF + minDistanceF).coerceAtMost(maxValue)
            }
        } else {
            endF = startF + minDistanceF
            if (endF > maxValue) {
                endF = maxValue
                startF = (endF - minDistanceF).coerceAtLeast(minValue)
            }
        }

        startF = normalizeToStep(startF)
        endF = normalizeToStep(endF)

        // Snapping can re-introduce a violation; fix it deterministically.
        if (endF - startF < minDistanceF) {
            if (movedStart) {
                startF = normalizeToStep((endF - minDistanceF).coerceAtLeast(minValue))
            } else {
                endF = normalizeToStep((startF + minDistanceF).coerceAtMost(maxValue))
            }
        }

        if (endF - startF < minDistanceF) {
            endF = normalizeToStep((startF + minDistanceF).coerceAtMost(maxValue))
            startF = normalizeToStep((endF - minDistanceF).coerceAtLeast(minValue))
        }

        startF = min(startF, endF)
        endF = max(startF, endF)
        return startF..endF
    }

    val layoutDir = LocalLayoutDirection.current

    val rawStartF = startValue.toFloat().coerceIn(minValue, maxValue)
    val rawEndF = endValue.toFloat().coerceIn(minValue, maxValue)
    val rangeStartF = min(rawStartF, rawEndF)
    val rangeEndF = max(rawStartF, rawEndF)

    val sliderRange = enforceMinDistance(
        currentStartF = rangeStartF,
        currentEndF = rangeEndF,
        requestedStartF = rangeStartF,
        requestedEndF = rangeEndF
    )
    val sliderStartF = sliderRange.start
    val sliderEndF = sliderRange.endInclusive

    val resolvedStartMark: T? = defaultStartMark ?: defaultMark
    val resolvedEndMark: T? = defaultEndMark ?: defaultMark

    Column(modifier = modifier.fillMaxWidth()) {
        Spacer(Modifier.height(12.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 2.dp, end = 4.dp)
                .pointerInput(enabled) {
                    if (!enabled) return@pointerInput
                    // Consume move events after the slider processes them to prevent parent vertical scroll.
                    awaitPointerEventScope {
                        while (true) {
                            val event = awaitPointerEvent()
                            event.changes.forEach { change ->
                                if (change.pressed) {
                                    val dy = change.position.y - change.previousPosition.y
                                    val dx = change.position.x - change.previousPosition.x
                                    // Consume any meaningful movement to avoid vertical scroll while dragging the range.
                                    if (abs(dx) > 0f || abs(dy) > 0f) {
                                        change.consume()
                                    }
                                }
                            }
                        }
                    }
                }
        ) {
            // Canvas: Draw default marks (background), then the track (inactive + active-range)
            Canvas(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center)
            ) {
                val fullWidth = size.width
                val fullHeight = size.height

                val trackHeightPx = 4.dp.toPx()
                val trackTop = (fullHeight - trackHeightPx) / 2f
                val trackRadius = trackHeightPx / 2f

                val thumbRadiusPx = 10.dp.toPx() // Keep consistent with the 20dp thumb
                val minF = valueRange.start.toFloat()
                val maxF = valueRange.endInclusive.toFloat()

                fun valueToX(valueF: Float): Float {
                    val coerced = valueF.coerceIn(minF, maxF)
                    val frac = if (maxF > minF) (coerced - minF) / (maxF - minF) else 0f
                    return if (layoutDir == LayoutDirection.Ltr) {
                        thumbRadiusPx + (fullWidth - 2 * thumbRadiusPx) * frac
                    } else {
                        fullWidth - thumbRadiusPx - (fullWidth - 2 * thumbRadiusPx) * frac
                    }
                }

                fun drawDefaultMarkAt(valueF: Float) {
                    val xMark = valueToX(valueF)

                    val extraPx = 4.dp.toPx()
                    val markHeightPx = trackHeightPx + 2 * extraPx
                    val markTop = trackTop - extraPx

                    val markWidthPx = 2.dp.toPx()
                    drawRoundRect(
                        color = inactiveTrackColor,
                        topLeft = Offset(xMark - markWidthPx / 2f, markTop),
                        size = Size(markWidthPx, markHeightPx),
                        cornerRadius = CornerRadius(markWidthPx / 2f, markWidthPx / 2f)
                    )
                }

                val xStart = valueToX(sliderStartF)
                val xEnd = valueToX(sliderEndF)

                val epsilonPx = 0.5.dp.toPx()
                val startMarkF = resolvedStartMark?.toFloat()
                val endMarkF = resolvedEndMark?.toFloat()

                if (startMarkF != null) {
                    drawDefaultMarkAt(startMarkF)
                }
                if (endMarkF != null) {
                    if (startMarkF == null) {
                        drawDefaultMarkAt(endMarkF)
                    } else {
                        val x1 = valueToX(startMarkF)
                        val x2 = valueToX(endMarkF)
                        if (abs(x1 - x2) > epsilonPx) {
                            drawDefaultMarkAt(endMarkF)
                        }
                    }
                }

                val xTrackShift = 4.dp.toPx()

                // Inactive track
                drawRoundRect(
                    color = inactiveTrackColor,
                    topLeft = Offset(xTrackShift, trackTop),
                    size = Size(fullWidth - (xTrackShift * 2), trackHeightPx),
                    cornerRadius = CornerRadius(trackRadius, trackRadius)
                )

                // Active range segment (between thumbs)
                val left = max(xTrackShift, min(xStart, xEnd))
                val right = min(fullWidth - xTrackShift, max(xStart, xEnd))
                val width = max(0f, right - left)

                drawRoundRect(
                    color = activeTrackColor,
                    topLeft = Offset(left, trackTop),
                    size = Size(width, trackHeightPx),
                    cornerRadius = CornerRadius(trackRadius, trackRadius)
                )
            }

            RangeSlider(
                value = sliderStartF..sliderEndF,
                onValueChange = { newRange ->
                    val normalizedStartF = normalizeToStep(newRange.start)
                    val normalizedEndF = normalizeToStep(newRange.endInclusive)

                    val enforced = enforceMinDistance(
                        currentStartF = sliderStartF,
                        currentEndF = sliderEndF,
                        requestedStartF = normalizedStartF,
                        requestedEndF = normalizedEndF
                    )

                    val newStartF = enforced.start
                    val newEndF = enforced.endInclusive

                    val convertedStart: T = when (startValue) {
                        is Int -> newStartF.roundToInt() as T
                        is Long -> newStartF.roundToInt().toLong() as T
                        is Double -> newStartF.toDouble() as T
                        is Float -> newStartF as T
                        is Short -> newStartF.roundToInt().toShort() as T
                        is Byte -> newStartF.roundToInt().toByte() as T
                        else -> newStartF as T
                    }
                    val convertedEnd: T = when (endValue) {
                        is Int -> newEndF.roundToInt() as T
                        is Long -> newEndF.roundToInt().toLong() as T
                        is Double -> newEndF.toDouble() as T
                        is Float -> newEndF as T
                        is Short -> newEndF.roundToInt().toShort() as T
                        is Byte -> newEndF.roundToInt().toByte() as T
                        else -> newEndF as T
                    }

                    onValueChange(convertedStart, convertedEnd)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(24.dp)
                    .align(Alignment.Center),
                enabled = enabled,
                valueRange = minValue..maxValue,
                startInteractionSource = startInteractionSource,
                endInteractionSource = endInteractionSource,
                colors = colors,
                steps = discreteSteps,
                onValueChangeFinished = null,
                track = { state: RangeSliderState ->
                    SliderDefaults.Track(
                        rangeSliderState = state,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(4.dp),
                        enabled = enabled,
                        colors = colors,
                        thumbTrackGapSize = 0.dp,
                        trackInsideCornerSize = 0.dp,
                        drawStopIndicator = { null },
                        drawTick = { _, _ -> }
                    )
                },
                startThumb = { state: RangeSliderState ->
                    Box(
                        modifier = Modifier
                            .size(16.dp)
                            .scale(scaleX = 1.25f, scaleY = 1.25f)
                            .background(color = thumbColor, shape = CircleShape)
                    )
                },
                endThumb = { state: RangeSliderState ->
                    Box(
                        modifier = Modifier
                            .size(16.dp)
                            .scale(scaleX = 1.25f, scaleY = 1.25f)
                            .background(color = thumbColor, shape = CircleShape)
                    )
                }
            )
        }

        Spacer(Modifier.height(12.dp))
    }
}
