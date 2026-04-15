package com.salat.gbinder.mappers

import com.salat.gbinder.entity.DeviceAppInfo
import com.salat.gbinder.entity.InstalledAppInfoRef

internal fun List<InstalledAppInfoRef>.toDisplay(
    enabledApps: List<String>,
    default: String,
    defaultIgnore: List<String>
) = map { it.toDisplay(enabledApps, default, defaultIgnore) }

internal fun InstalledAppInfoRef.toDisplay(
    enabledApps: List<String>,
    default: String,
    defaultIgnore: List<String>
) = DeviceAppInfo(
    packageName = packageName,
    appName = appName,
    icon = iconRef.toDisplayIcon(),
    isMediaApp = isMedia,
    isSelected = if (enabledApps.isEmpty()) {
        isMedia && packageName !in defaultIgnore
    } else packageName in enabledApps,
    isDefault = packageName == default
)

internal fun List<InstalledAppInfoRef>.toAllDisplay() = map {
    it.toAllDisplay()
}

internal fun InstalledAppInfoRef.toAllDisplay() = DeviceAppInfo(
    packageName = packageName,
    appName = appName,
    icon = iconRef.toDisplayIcon(),
    isMediaApp = isMedia,
    isSelected = false,
    isDefault = false
)
