package com.salat.gbinder.features.launcher

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.salat.gbinder.R
import com.salat.gbinder.ui.theme.AppTheme

@Composable
fun RowScope.RenderLauncherAddAppsToolbar(onCloseClick: () -> Unit) {
    Spacer(Modifier.width(8.dp))

    IconButton(
        modifier = Modifier
            .size(56.dp),
        onClick = onCloseClick
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            tint = AppTheme.colors.contentPrimary,
            contentDescription = stringResource(R.string.back)
        )
    }

    Spacer(Modifier.width(16.dp))

    Text(
        modifier = Modifier.weight(1f),
        text = stringResource(R.string.select_apps),
        style = AppTheme.typography.overlayLauncherToolbarTitle,
        color = AppTheme.colors.contentPrimary
    )

    Spacer(Modifier.width(36.dp))
}
