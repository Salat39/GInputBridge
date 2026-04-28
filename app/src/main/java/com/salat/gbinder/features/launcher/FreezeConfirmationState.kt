package com.salat.gbinder.features.launcher

internal data class FreezeConfirmationState(
    val packageName: String,
    val isSystem: Boolean
)

internal data class UnfreezeLaunchConfirmationState(
    val packageName: String,
    val launchActivity: String?,
    val appDisplayName: String
)
