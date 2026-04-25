package com.salat.gbinder.screenParts

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.salat.gbinder.ui.theme.AppTheme

@Composable
fun RenderGroupTitle(title: String) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 42.dp),
        text = title,
        style = AppTheme.typography.settingsTitle,
        color = AppTheme.colors.contentAccent
    )

    Spacer(Modifier.height(8.dp))
}
