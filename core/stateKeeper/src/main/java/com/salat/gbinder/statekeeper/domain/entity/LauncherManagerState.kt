package com.salat.gbinder.statekeeper.domain.entity

data class LauncherManagerState(
    val isAccessibilityReady: Boolean = false,
    val isHideWidgetEnabled: Boolean = false,
    val isOneOsApiReady: Boolean = false
)
