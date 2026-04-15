package com.salat.gbinder.statekeeper.domain.entity

sealed class LauncherOverlaySignal() {
    data class ChangeGroupName(val id: Long, val title: String) : LauncherOverlaySignal()
    data object CreateShortcut : LauncherOverlaySignal()
    data class ChangeAppIconById(
        val id: Long, val
        withClear: Boolean
    ) : LauncherOverlaySignal()

    data class ChangeAppIconByPackage(
        val packageName: String,
        val withClear: Boolean
    ) : LauncherOverlaySignal()
}
