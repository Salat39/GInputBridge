package com.salat.gbinder.entity

import kotlinx.serialization.Serializable

@Serializable
enum class LauncherItemType {
    GROUP,
    APP,
    ACTIVITY,
    MACRO
}
