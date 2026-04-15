package com.salat.gbinder.entity

import androidx.compose.runtime.Immutable

@Immutable
data class DisplayKeyBind(
    val bindName: String,
    val keyNames: List<String>,
    val action: DisplayKeyAction,
    val type: String,
    val app: DeviceAppInfo?,
    val link: DeviceLinkInfo?,
    val phone: String?,
    val driveModes: String?,
    val lampModes: String?
)
