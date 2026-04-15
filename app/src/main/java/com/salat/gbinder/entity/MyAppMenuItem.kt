package com.salat.gbinder.entity

import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.IntOffset

@Immutable
data class MyAppMenuItem(
    val app: DisplayLauncherItem,
    val offset: IntOffset,
    val appData: DisplayLauncherApp?,
    val launchedStatus: AppLaunchedState
)
