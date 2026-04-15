package com.salat.gbinder

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.salat.gbinder.ui.theme.AppTheme

@Composable
fun RenderGroupDivider() = Spacer(
    Modifier
        .fillMaxWidth()
        .height(2.dp)
        .background(AppTheme.colors.contentPrimary.copy(.15f))
)
