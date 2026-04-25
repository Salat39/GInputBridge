package com.salat.gbinder.ui

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.salat.gbinder.entity.HugeTogglerItem
import com.salat.gbinder.ui.theme.AppTheme
import kotlin.math.abs

@Composable
fun HugeSegmentToggler(
    modifier: Modifier = Modifier,
    selectedIndex: Int,
    items: List<HugeTogglerItem>,
    fontSize: Int = 13,
    textHorizontalPadding: Int = 4,
    dividerPadding: Int = 2,
    activeBackground: Color,
    itemContentColor: Color,
    dividerColor: Color = itemContentColor.copy(alpha = 0.1f),
    enabled: Boolean = true,
    multiline: Boolean = false,
    titleTextStyle: TextStyle = AppTheme.typography.screenTitle.copy(
        fontSize = fontSize.sp
    ),
    titleWithoutSubtitleTextStyle: TextStyle = AppTheme.typography.screenTitle.copy(
        fontSize = (fontSize - 1).sp
    ),
    subtitleTextStyle: TextStyle = AppTheme.typography.screenTitle.copy(
        fontSize = (fontSize - 6).sp
    ),
    onReSelect: (Int) -> Unit = {},
    onSelectionChange: (Int) -> Unit
) {
    val layoutDirection = LocalLayoutDirection.current
    val segments = remember(items.size) { items.size }
    BoxWithConstraints(
        modifier
            .clip(RoundedCornerShape(8.dp))
            .padding(horizontal = 1.dp, vertical = 1.dp)
    ) {
        if (items.isNotEmpty()) {
            val maxWidth = this.maxWidth
            val tabWidth = maxWidth / items.size

            // Flip index in RTL
            val correctedSelectedIndex = if (layoutDirection == LayoutDirection.Rtl) {
                items.size - 1 - selectedIndex
            } else {
                selectedIndex
            }

            val targetOffset = tabWidth * correctedSelectedIndex
            val indicatorOffset by animateDpAsState(
                targetValue = targetOffset,
                animationSpec = tween(durationMillis = 250, easing = FastOutSlowInEasing),
                label = "indicator offset"
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .drawWithContent {
                        val dividerWidthPx = 1.dp.toPx()
                        val vPadPx = 15.dp.toPx()
                        val dividerHeight = size.height - vPadPx
                        val dividerTop = (size.height - dividerHeight) / 2f

                        val cellWidth = size.width / segments

                        // isAnimating: compare current animated X to target X in px
                        val currentX = indicatorOffset.toPx()
                        val targetX = cellWidth * correctedSelectedIndex
                        val isAnimating = abs(currentX - targetX) > 0.5f

                        // Draw vertical dividers in background
                        for (i in 1 until segments) {
                            // hide neighbors when not animating
                            val isNeighborOfActive =
                                (i == correctedSelectedIndex) || (i == correctedSelectedIndex + 1)
                            if (isAnimating || !isNeighborOfActive) {
                                val x = cellWidth * i
                                drawRect(
                                    color = dividerColor,
                                    topLeft = Offset(x - dividerWidthPx / 2f, dividerTop),
                                    size = Size(dividerWidthPx, dividerHeight)
                                )
                            }
                        }

                        // Override active content color block
                        val padding = 2.dp.toPx()
                        val corner = 8
                        drawRoundRect(
                            topLeft = Offset(x = currentX + padding, padding),
                            size = Size(cellWidth - (padding * 2), size.height - (padding * 2)),
                            color = Color.White,
                            cornerRadius = CornerRadius(corner.dp.toPx(), corner.dp.toPx()),
                        )

                        drawWithLayer {
                            drawContent()

                            // Movement block
                            drawRoundRect(
                                topLeft = Offset(x = currentX, y = 0f),
                                size = Size(cellWidth, size.height),
                                color = activeBackground,
                                cornerRadius = CornerRadius(corner.dp.toPx(), corner.dp.toPx()),
                                blendMode = BlendMode.SrcOut
                            )
                        }
                    },
                verticalAlignment = Alignment.CenterVertically
            ) {
                items.forEachIndexed { index, item ->
                    Column(
                        modifier = Modifier
                            .width(tabWidth)
                            .fillMaxHeight()
                            .padding(horizontal = textHorizontalPadding.dp, vertical = 10.dp)
                            .clickableNoRipple {
                                if (enabled) {
                                    if (index != selectedIndex) {
                                        onSelectionChange(index)
                                    } else onReSelect(index)
                                }
                            },
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = item.text,
                            style = if (item.subtitle == null) {
                                titleWithoutSubtitleTextStyle
                            } else {
                                titleTextStyle
                            },
                            color = itemContentColor,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        item.subtitle?.let { subtitle ->
                            Spacer(Modifier.height(dividerPadding.dp))

                            Text(
                                text = subtitle,
                                style = subtitleTextStyle,
                                color = itemContentColor.copy(.6f),
                                maxLines = if(multiline) Int.MAX_VALUE else  1,
                                textAlign = TextAlign.Center,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }
                }
            }
        }
    }
}

private fun ContentDrawScope.drawWithLayer(block: ContentDrawScope.() -> Unit) {
    with(drawContext.canvas.nativeCanvas) {
        val checkPoint = saveLayer(null, null)
        block()
        restoreToCount(checkPoint)
    }
}
