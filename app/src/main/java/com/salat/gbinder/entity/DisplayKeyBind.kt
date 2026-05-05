package com.salat.gbinder.entity

import androidx.compose.runtime.Immutable

@Immutable
data class DisplayKeyBind(
    val bindName: String,
    val keyNames: List<String>,
    val action: DisplayKeyAction,
    val type: String,
    val app: DeviceAppInfo?,
    val appCarouselSummaries: String?,
    val link: DeviceLinkInfo?,
    val phone: String?,
    val carplayScreen: String?,
    val driveModes: String?,
    val lampModes: String?,
    val audioSources: String?
)
