package com.salat.gbinder.entity

import kotlinx.serialization.Serializable

@Serializable
data class LauncherItem(
    val type: LauncherItemType,
    val id: Long,
    val order: Int,
    val title: String,
    val customIcon: String? = null,
    val packageName: String,
    val launchActivity: String,
    val data: String
)
