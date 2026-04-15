package com.salat.gbinder.entity

import androidx.compose.runtime.Immutable

@Immutable
data class DeviceLinkInfo(
    val title: String,
    val subtitle: String,
    val icon: DisplayIconRef?
)
