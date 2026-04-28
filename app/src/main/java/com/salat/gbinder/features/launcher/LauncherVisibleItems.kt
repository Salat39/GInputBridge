package com.salat.gbinder.features.launcher

import com.salat.gbinder.entity.DisplayLauncherApp
import com.salat.gbinder.entity.DisplayLauncherItem

fun filterVisibleLauncherApps(
    items: List<DisplayLauncherApp>,
    showFrozenApps: Boolean
): List<DisplayLauncherApp> {
    if (showFrozenApps) return items
    return items.filterNot { it.isFrozen }
}

fun filterVisibleLauncherItems(
    items: List<DisplayLauncherItem>,
    showFrozenApps: Boolean
): List<DisplayLauncherItem> {
    if (showFrozenApps) return items
    return items.filterNot { it.isFrozen }
}
