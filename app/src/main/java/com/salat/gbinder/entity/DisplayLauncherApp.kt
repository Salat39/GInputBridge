package com.salat.gbinder.entity

import android.net.Uri
import androidx.compose.runtime.Immutable

@Immutable
data class DisplayLauncherApp(
    val id: String,
    val order: Int,
    val packageName: String,
    val appName: String,
    val iconRef: DisplayIconRef,
    val customIcon: Uri?,
    val isMedia: Boolean,
    val launcherActivity: String? = null,
    val availableActivity: List<String> = emptyList(),
    val isFrozen: Boolean = false,
    val isSystem: Boolean = false
)
