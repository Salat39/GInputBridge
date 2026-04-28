package com.salat.gbinder.mappers

import android.content.Context
import com.salat.gbinder.entity.DisplayLauncherApp
import com.salat.gbinder.entity.DisplayLauncherItem
import com.salat.gbinder.entity.DisplayLauncherItemType
import com.salat.gbinder.entity.InstalledAppInfoRef
import com.salat.gbinder.entity.LauncherItem
import com.salat.gbinder.entity.LauncherItemType
import com.salat.gbinder.features.launcher.IconUriUtils

// Maps data-layer type to UI-layer type.
fun LauncherItemType.toDisplayType(): DisplayLauncherItemType = when (this) {
    LauncherItemType.GROUP -> DisplayLauncherItemType.GROUP
    LauncherItemType.APP -> DisplayLauncherItemType.APP
    LauncherItemType.ACTIVITY -> DisplayLauncherItemType.ACTIVITY
    LauncherItemType.MACRO -> DisplayLauncherItemType.MACRO
}

fun LauncherItem.toDisplayItem(
    context: Context,
    allPackages: Map<String, DisplayLauncherApp>
): DisplayLauncherItem {
    val appInfo = when (type) {
        LauncherItemType.APP -> allPackages[packageName + launchActivity]
        LauncherItemType.ACTIVITY -> allPackages[packageName + launchActivity]
            ?: allPackages.firstByPrefix(packageName)?.second
        LauncherItemType.GROUP,
        LauncherItemType.MACRO -> null
    }

    return DisplayLauncherItem(
        type = type.toDisplayType(),
        id = id,
        order = order,
        title = title,
        iconRef = if (type == LauncherItemType.APP) {
            allPackages[packageName + launchActivity]?.iconRef
        } else {
            allPackages.firstByPrefix(packageName)?.second?.iconRef
        },
        customIcon = customIcon?.let {
            IconUriUtils.iconFileNameToContentUri(context, it)
        },
        packageName = packageName,
        launchActivity = launchActivity,
        data = data,
        isCall = data.isPhoneCallIntent,
        isSplit = data.isSplitIntent,
        isFrozen = appInfo?.isFrozen ?: false,
        isSystem = appInfo?.isSystem ?: false
    )
}

fun List<LauncherItem>.toDisplayItems(
    context: Context,
    sortedByOrder: Boolean = false,
    allPackages: Map<String, DisplayLauncherApp>
): List<DisplayLauncherItem> {
    val base = if (sortedByOrder) this.sortedBy { it.order } else this
    return base.map { it.toDisplayItem(context, allPackages) }
}

fun DisplayLauncherItemType.toDataType(): LauncherItemType = when (this) {
    DisplayLauncherItemType.GROUP -> LauncherItemType.GROUP
    DisplayLauncherItemType.APP -> LauncherItemType.APP
    DisplayLauncherItemType.ACTIVITY -> LauncherItemType.ACTIVITY
    DisplayLauncherItemType.MACRO -> LauncherItemType.MACRO
}

fun DisplayLauncherItem.toDataItem(context: Context): LauncherItem = LauncherItem(
    type = type.toDataType(),
    id = id,
    order = order,
    title = title,
    customIcon = customIcon?.let { IconUriUtils.contentUriToIconFileName(context, it) },
    packageName = packageName,
    launchActivity = launchActivity,
    data = data
)

fun List<DisplayLauncherItem>.toDataItems(
    context: Context,
    sortedByOrder: Boolean = false
): List<LauncherItem> {
    val base = if (sortedByOrder) this.sortedBy { it.order } else this
    return base.map { it.toDataItem(context) }
}

fun InstalledAppInfoRef.toDisplayLauncherApp(
    context: Context,
    order: Int,
    customIcons: Map<String, String>
) = DisplayLauncherApp(
    id = id,
    order = order,
    packageName = packageName,
    appName = appName,
    iconRef = iconRef.toDisplayIcon(),
    customIcon = customIcons[packageName]
        ?.let { IconUriUtils.iconFileNameToContentUri(context, it) },
    isMedia = isMedia,
    launcherActivity = launcherActivity,
    availableActivity = availableActivity,
    isFrozen = isFrozen,
    isSystem = isSystem
)

fun List<InstalledAppInfoRef>.toDisplayLauncherApps(
    context: Context,
    customIcons: Map<String, String>
): List<DisplayLauncherApp> =
    mapIndexed { index, app -> app.toDisplayLauncherApp(context, index, customIcons) }

fun DisplayLauncherApp.toInstalledAppInfoRef() = InstalledAppInfoRef(
    id = id,
    packageName = packageName,
    appName = appName,
    iconRef = iconRef.toDataIcon(),
    isMedia = isMedia,
    launcherActivity = launcherActivity,
    availableActivity = availableActivity,
    isFrozen = isFrozen,
    isSystem = isSystem
)

fun List<DisplayLauncherApp>.toInstalledAppInfoRefs(): List<InstalledAppInfoRef> =
    sortedBy { it.order }.map { it.toInstalledAppInfoRef() }

fun <V> Map<String, V>.firstByPrefix(
    prefix: String,
    ignoreCase: Boolean = false
): Pair<String, V>? {
    // Linear scan in the map's iteration order; good when the map is insertion-ordered or small
    val e = entries.firstOrNull { it.key.startsWith(prefix, ignoreCase) }
    return e?.toPair()
}

internal val String.isPhoneCallIntent
    get() = "intent.action.CALL" in this || "action.QUICK_CONTACT" in this

internal val String.isSplitIntent
    get() = ".PresetLauncherActivity;" in this
