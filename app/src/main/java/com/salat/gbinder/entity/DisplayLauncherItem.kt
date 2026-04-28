package com.salat.gbinder.entity

import android.net.Uri
import androidx.compose.runtime.Immutable

@Immutable
data class DisplayLauncherItem(
    val type: DisplayLauncherItemType,
    val id: Long,
    val order: Int,
    val title: String,
    val iconRef: DisplayIconRef?,
    val customIcon: Uri?,
    val packageName: String,
    val launchActivity: String,
    val data: String,
    val isCall: Boolean,
    val isSplit: Boolean,
    val isFrozen: Boolean,
    val isSystem: Boolean = false
)

val List<DisplayLauncherItem>.biggestId: Long
    get() = if (isEmpty()) 0L else maxOfOrNull { it.id } ?: 0L

val List<DisplayLauncherItem>.biggestOrder: Int
    get() = if (isEmpty()) 0 else maxOfOrNull { it.order } ?: 0
