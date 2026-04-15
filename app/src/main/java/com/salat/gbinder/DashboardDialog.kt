package com.salat.gbinder

import androidx.annotation.FloatRange
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.salat.gbinder.ui.FullScreenDialog
import com.salat.gbinder.ui.TopShadow
import com.salat.gbinder.ui.theme.AppTheme
import kotlinx.coroutines.launch
import kotlin.math.cos
import kotlin.math.sin

private val INDICATOR_LENGTH = 14.dp
private val MAJOR_INDICATOR_LENGTH = 18.dp
private val INDICATOR_INITIAL_OFFSET = 6.dp

@Composable
fun DashboardDialog(
    uiScaleState: Float? = null,
    onDismiss: () -> Unit,
) = FullScreenDialog(
    modifier = Modifier,
    uiScaleState = uiScaleState,
    onDismissRequest = onDismiss
) {
    val viewModel: DashboardViewModel = hiltViewModel()

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Column(
            Modifier
                .fillMaxSize()
                .background(AppTheme.colors.surfaceBackground)
                .padding(innerPadding)
        ) {

            // Toolbar
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                IconButton(
                    modifier = Modifier
                        .size(56.dp)
                        .padding(start = 2.dp),
                    onClick = remember { { onDismiss() } }
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        tint = AppTheme.colors.contentPrimary,
                        contentDescription = stringResource(R.string.back)
                    )
                }

                Spacer(Modifier.weight(1f))
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(AppTheme.colors.surfaceSettingsLayer1)
            ) {
                TopShadow()

                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val coroutineScope = rememberCoroutineScope()

                    val speed = remember { Animatable(0f) }
                    Box(
                        modifier = Modifier
                            .padding(top = 26.dp)
                            .requiredSize(420.dp)
                    ) {

                        Text(
                            modifier = Modifier
                                .padding(top = 100.dp)
                                .align(Alignment.TopCenter),
                            text = "km/h",
                            style = AppTheme.typography.stubTitle,
                            color = AppTheme.colors.contentPrimary
                        )

                        Speedometer(
                            modifier = Modifier
                                .requiredSize(420.dp),
                            currentSpeed = speed.value
                        )
                    }

                    LaunchedEffect(Unit) {
                        coroutineScope.launch {
                            viewModel.speedValue.collect { value ->
                                speed.animateTo(
                                    value,
                                    animationSpec = tween(durationMillis = 50)
                                )
                            }
                        }
                    }
                }

            }
        }
    }
}

@Composable
private fun Speedometer(
    modifier: Modifier = Modifier,
    @FloatRange(from = 0.0, to = 240.0) currentSpeed: Float,
    enableRange: Boolean = false,
    selectStart: Float = 0f,
    selectEnd: Float = 100f
) {
    val textMeasurer = rememberTextMeasurer()
    val textColor = AppTheme.colors.contentPrimary
    val indicatorColor = AppTheme.colors.contentPrimary
    val arcColor = AppTheme.colors.surfaceMenu
    val rangeColor = AppTheme.colors.contentAccent
    val subIndicatorsColor = AppTheme.colors.contentPrimary.copy(.4f)
    val textStyle = TextStyle.Default
    Canvas(modifier = modifier, onDraw = {
        drawArc(
            color = arcColor,
            startAngle = 30f,
            sweepAngle = -240f,
            useCenter = false,
            style = Stroke(width = 3.0.dp.toPx())
        )

        // Range
        if (enableRange) {
            val startRange = 150f + selectStart
            val endRange = selectEnd - selectStart
            drawArc(
                color = rangeColor.copy(.05f),
                startAngle = startRange,
                sweepAngle = endRange,
                useCenter = true
            )
            drawArc(
                color = rangeColor,
                startAngle = startRange,
                sweepAngle = endRange,
                useCenter = false,
                style = Stroke(width = 3.0.dp.toPx())
            )
        }

        for (angle in 300 downTo 60 step 2) {
            val speed = 300 - angle

            val startOffset =
                pointOnCircle(
                    thetaInDegrees = angle.toDouble(),
                    radius = size.height / 2 - INDICATOR_INITIAL_OFFSET.toPx(),
                    cX = center.x,
                    cY = center.y
                )

            if (speed % 20 == 0) {
                val markerOffset = pointOnCircle(
                    thetaInDegrees = angle.toDouble(),
                    radius = size.height / 2 - MAJOR_INDICATOR_LENGTH.toPx(),
                    cX = center.x,
                    cY = center.y
                )
                speedMarker(startOffset, markerOffset, SolidColor(indicatorColor), 4.dp.toPx())
                speedText(
                    speed = speed,
                    angle = angle,
                    textMeasurer = textMeasurer,
                    textColor = textColor,
                    textStyle = textStyle
                )
            } else if (speed % 10 == 0) {
                val endOffset = pointOnCircle(
                    thetaInDegrees = angle.toDouble(),
                    radius = size.height / 2 - INDICATOR_LENGTH.toPx(),
                    cX = center.x,
                    cY = center.y
                )
                speedMarker(startOffset, endOffset, SolidColor(subIndicatorsColor), 2.dp.toPx())
            } else {
                val endOffset = pointOnCircle(
                    thetaInDegrees = angle.toDouble(),
                    radius = size.height / 2 - INDICATOR_LENGTH.toPx(),
                    cX = center.x,
                    cY = center.y
                )
                speedMarker(startOffset, endOffset, SolidColor(subIndicatorsColor), 1.dp.toPx())
            }
        }

        val correctSpeed = if (currentSpeed > 240) 240f else currentSpeed
        speedIndicator(speedAngle = 300 - correctSpeed)

        drawCircle(
            center = Offset(x = center.x, y = center.y),
            color = arcColor,
            radius = 20.dp.toPx()
        )
    })
}

private fun DrawScope.speedMarker(
    startPoint: Offset,
    endPoint: Offset,
    brush: Brush,
    strokeWidth: Float
) {
    drawLine(brush = brush, start = startPoint, end = endPoint, strokeWidth = strokeWidth)
}

private fun DrawScope.speedText(
    speed: Int,
    angle: Int,
    textColor: Color,
    textMeasurer: TextMeasurer,
    textStyle: TextStyle
) {
    val textLayoutResult = textMeasurer.measure(
        text = speed.toString(),
        style = textStyle.copy(lineHeight = TextUnit(0.0f, TextUnitType.Sp))
    )
    val textWidth = textLayoutResult.size.width
    val textHeight = textLayoutResult.size.height

    val textOffset = pointOnCircle(
        thetaInDegrees = angle.toDouble(),
        radius = size.height / 2 - MAJOR_INDICATOR_LENGTH.toPx() - textWidth / 2 - INDICATOR_INITIAL_OFFSET.toPx(), // Adjusting radius with text width
        cX = center.x,
        cY = center.y
    )

    drawContext.canvas.save()
    // Translate to the text offset point, adjusting for vertical centering.
    drawContext.canvas.translate(
        textOffset.x - textWidth / 2,
        textOffset.y - textHeight / 2
    )

    drawText(textLayoutResult, color = textColor)

    drawContext.canvas.restore()
}

private fun DrawScope.speedIndicator(
    speedAngle: Float
) {
    val endOffset = pointOnCircle(
        thetaInDegrees = speedAngle.toDouble(),
        radius = size.height / 2 - INDICATOR_LENGTH.toPx(),
        cX = center.x,
        cY = center.y
    )

    drawLine(
        color = Color(0xFFE51400),
        start = center,
        end = endOffset,
        strokeWidth = 6.dp.toPx(),
        cap = StrokeCap.Round,
        alpha = .6f
    )
}

private fun pointOnCircle(
    thetaInDegrees: Double,
    radius: Float,
    cX: Float = 0f,
    cY: Float = 0f
): Offset {
    val x = cX + (radius * sin(Math.toRadians(thetaInDegrees)).toFloat())
    val y = cY + (radius * cos(Math.toRadians(thetaInDegrees)).toFloat())

    return Offset(x, y)
}
