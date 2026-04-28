package com.salat.gbinder.entity

import android.net.Uri
import androidx.compose.runtime.Immutable

@Immutable
data class DisplayRecentTaskInfo(
    // App data
    val id: String,
    val packageName: String,
    val appName: String,
    val iconRef: DisplayIconRef,
    val customIcon: Uri?,
    val isMedia: Boolean,
    val isFrozen: Boolean = false,
    val launcherActivity: String? = null,
    val availableActivity: List<String> = emptyList(),

    // Task data
    val taskId: Int,
    val visible: Boolean,
    val visibleRequested: Boolean,
    val topResumed: Boolean,
    val activityState: String?,
    val nowVisible: Boolean?,
    val lastVisibleTime: String?,
    val baseDir: String?,
    val dataDir: String?
)
