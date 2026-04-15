package com.salat.gbinder.entity

import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.IntOffset

@Immutable
data class AllAppMenuItem(
    val app: DisplayLauncherApp,
    val offset: IntOffset,
    val inMyApps: Boolean,
    val launchedStatus: AppLaunchedState
)
