package com.salat.gbinder.entity

data class InstalledAppInfoRef(
    val id: String,
    val packageName: String,
    val appName: String,
    val iconRef: IconRef,
    val isMedia: Boolean,
    val launcherActivity: String?,
    val availableActivity: List<String>
)
