package com.salat.gbinder.screenParts

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.salat.gbinder.R
import com.salat.gbinder.ui.theme.AppTheme

@Composable
fun RenderSpoilerButton(
    modifier: Modifier = Modifier,
    title: String,
    expanded: Boolean,
    backgroundColor: Color = AppTheme.colors.surfaceMenu,
    onClick: () -> Unit
) {
    val chevronRotation by animateFloatAsState(if (expanded) 180f else 0f)
    val chevronSize = 18.dp
    val chevronGap = 6.dp

    Button(
        modifier = modifier,
        colors = ButtonColors(
            containerColor = backgroundColor,
            contentColor = AppTheme.colors.contentPrimary,
            disabledContainerColor = backgroundColor,
            disabledContentColor = AppTheme.colors.contentPrimary
        ),
        shape = RoundedCornerShape(8.dp),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(Modifier.width(chevronSize + chevronGap))

            Text(
                modifier = Modifier.padding(4.dp),
                text = title,
                color = AppTheme.colors.contentPrimary,
                style = AppTheme.typography.buttonTitle,
                textAlign = TextAlign.Center
            )

            Spacer(Modifier.width(chevronGap))

            Icon(
                modifier = Modifier
                    .padding(top = 2.dp)
                    .size(chevronSize)
                    .rotate(chevronRotation),
                painter = painterResource(R.drawable.ic_chevron_down),
                tint = AppTheme.colors.contentPrimary.copy(.35f),
                contentDescription = null
            )
        }
    }
}
