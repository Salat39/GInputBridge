package com.salat.gbinder.entity

import androidx.compose.runtime.Immutable

@Immutable
data class DeviceAppInfo(
    val packageName: String,
    val appName: String,
    val icon: DisplayIconRef,
    val isMediaApp: Boolean,
    val isSelected: Boolean,
    val isDefault: Boolean
)
