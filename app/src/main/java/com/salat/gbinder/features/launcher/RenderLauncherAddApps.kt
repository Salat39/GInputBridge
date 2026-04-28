package com.salat.gbinder.features.launcher

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.salat.gbinder.R
import com.salat.gbinder.entity.AddListItem
import com.salat.gbinder.entity.DisplayLauncherApp
import com.salat.gbinder.entity.DisplayLauncherConfig
import com.salat.gbinder.entity.DisplayLauncherItem
import com.salat.gbinder.entity.DisplayLauncherItemType
import com.salat.gbinder.ui.BaseButton
import com.salat.gbinder.ui.ProfileSwitch
import com.salat.gbinder.ui.theme.AppTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun ColumnScope.RenderLauncherAddApps(
    myApps: List<DisplayLauncherItem>,
    allApps: List<DisplayLauncherApp>,
    config: DisplayLauncherConfig,
    onSave: (List<DisplayLauncherItem>) -> Unit
) = Column(
    modifier = Modifier
        .fillMaxWidth()
        .weight(1f)
) {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    var listItems by remember { mutableStateOf<List<AddListItem>>(emptyList()) }

    // build all list
    LaunchedEffect(myApps, allApps) {
        val preparedItems = withContext(Dispatchers.Default) {
            val selectedApps = myApps
                .filter { it.type == DisplayLauncherItemType.APP }
                .map { it.packageName + it.launchActivity }
                .toHashSet()
            val selectedActivitiesSet = myApps
                .filter { it.type == DisplayLauncherItemType.ACTIVITY }
                .map { it.launchActivity }
                .toHashSet()

            buildList {
                allApps.forEachIndexed { index, app ->
                    val isShowActivity =
                        false // intersects(app.availableActivity, selectedActivity)
                    val appId = 2_000_000_000 + index

                    val initialSelectedCount = app.availableActivity
                        .asSequence()
                        .filter { it != app.launcherActivity }
                        .count { it in selectedActivitiesSet }

                    add(
                        AddListItem.App(
                            id = appId,
                            isSelected = (app.packageName + (app.launcherActivity ?: ""))
                                    in selectedApps,
                            showActivity = isShowActivity,
                            iconRef = app.iconRef,
                            customIcon = app.customIcon,
                            title = app.appName,
                            packageName = app.packageName,
                            launchActivity = app.launcherActivity,
                            isFrozen = app.isFrozen,
                            isSingleActivity = app.availableActivity.size < 2,
                            selectedCount = initialSelectedCount,
                            isSystem = app.isSystem
                        )
                    )

                    app.availableActivity.forEachIndexed { subindex, activity ->
                        // main activity == main app switch
                        if (activity != app.launcherActivity) {
                            add(
                                AddListItem.Activity(
                                    id = index * 1_000_000 + subindex,
                                    parentId = appId,
                                    isSelected = activity in selectedActivitiesSet,
                                    title = activity.afterLastDot(),
                                    isShow = isShowActivity,
                                    launchActivity = activity,
                                    packageName = app.packageName,
                                    isSystem = app.isSystem
                                )
                            )
                        }
                    }
                }
            }.toList()
        }
        listItems = preparedItems
    }

    fun saveAndExit() = scope.launch(Dispatchers.Default) {
        val selectedApps = listItems
            .filterIsInstance<AddListItem.App>()
            .filter { it.isSelected }
            .map { it.packageName + (it.launchActivity ?: "") }
        val selectedActivity = listItems
            .filterIsInstance<AddListItem.Activity>()
            .filter { it.isSelected }
            .map { it.launchActivity }

        // Filter removed items
        val filteredNewApps = myApps.filter {
            when (it.type) {
                DisplayLauncherItemType.GROUP -> true

                DisplayLauncherItemType.MACRO -> true

                DisplayLauncherItemType.APP -> (it.packageName + it.launchActivity) in selectedApps

                DisplayLauncherItemType.ACTIVITY -> it.launchActivity in selectedActivity
            }
        }.toMutableList()

        // Add new items to end
        val filteredApps = filteredNewApps
            .filter { it.type == DisplayLauncherItemType.APP }
            .map { it.packageName + it.launchActivity }
        val filteredActivity = filteredNewApps
            .filter { it.type == DisplayLauncherItemType.ACTIVITY }
            .map { it.launchActivity }

        listItems
            .filter { item ->
                when (item) {
                    is AddListItem.Activity -> item.isSelected
                    is AddListItem.App -> item.isSelected
                }
            }
            .forEach { item ->
                when (item) {
                    is AddListItem.App -> {
                        if ((item.packageName + (item.launchActivity ?: "")) !in filteredApps) {
                            filteredNewApps.add(
                                DisplayLauncherItem(
                                    type = DisplayLauncherItemType.APP,
                                    id = 0,
                                    order = 0,
                                    title = item.title,
                                    iconRef = item.iconRef,
                                    customIcon = item.customIcon,
                                    packageName = item.packageName,
                                    launchActivity = item.launchActivity ?: "",
                                    data = "",
                                    isCall = false,
                                    isSplit = false,
                                    isFrozen = item.isFrozen,
                                    isSystem = item.isSystem
                                )
                            )
                        }
                    }

                    is AddListItem.Activity -> {
                        if (item.launchActivity !in filteredActivity) {
                            filteredNewApps.add(
                                DisplayLauncherItem(
                                    type = DisplayLauncherItemType.ACTIVITY,
                                    id = 0,
                                    order = 0,
                                    title = item.title,
                                    iconRef = null,
                                    customIcon = null,
                                    packageName = item.packageName,
                                    launchActivity = item.launchActivity,
                                    data = "",
                                    isCall = false,
                                    isSplit = false,
                                    isFrozen = false,
                                    isSystem = item.isSystem
                                )
                            )
                        }
                    }
                }
            }

        val finalList = filteredNewApps.assignIdsAndOrder().toList()
        withContext(Dispatchers.Main) {
            onSave(finalList)
        }
    }

    fun togglePackageActivityVisible(id: Int) = scope.launch(Dispatchers.Default) {
        listItems = listItems.map { listItem ->
            when (listItem) {
                is AddListItem.App -> {
                    if (listItem.id == id) {
                        listItem.copy(showActivity = !listItem.showActivity)
                    } else listItem
                }

                is AddListItem.Activity -> {
                    if (listItem.parentId == id) {
                        listItem.copy(isShow = !listItem.isShow)
                    } else listItem
                }
            }
        }
    }

    fun toggleValue(id: Int) = scope.launch(Dispatchers.Default) {
        val prepared = listItems.map { listItem ->
            when (listItem) {
                is AddListItem.App -> {
                    if (listItem.id == id) {
                        listItem.copy(isSelected = !listItem.isSelected)
                    } else listItem
                }

                is AddListItem.Activity -> {
                    if (listItem.id == id) {
                        listItem.copy(isSelected = !listItem.isSelected)
                    } else listItem
                }
            }
        }
        listItems = prepared.withRecount()
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .weight(1f)
    ) {
        item(key = -1) {
            Spacer(
                Modifier
                    .height(16.dp)
            )
        }
        itemsIndexed(
            items = listItems,
            key = { _, item ->
                when (item) {
                    is AddListItem.Activity -> item.id
                    is AddListItem.App -> item.id
                }
            }
        ) { _, item ->
            when (item) {
                is AddListItem.App -> {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { toggleValue(item.id) }
                            .padding(vertical = 12.dp)
                            .padding(start = 36.dp, end = 16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        val iconSize = 46
                        val pxSize = with(LocalDensity.current) { iconSize.dp.roundToPx() }
                        val model = remember(item.iconRef, item.customIcon) {
                            val builder = ImageRequest.Builder(context)
                                .size(pxSize, pxSize)
                                .allowHardware(false)
                                .dispatcher(Dispatchers.IO)

                            if (item.customIcon != null) {
                                builder.data(item.customIcon)
                            } else builder.data(item.iconRef)

                            builder.build()
                        }
                        AsyncImage(
                            model = model,
                            contentDescription = item.title,
                            modifier = Modifier
                                .size(iconSize.dp)
                                .clip(RoundedCornerShape(6.dp)),
                            contentScale = ContentScale.Crop
                        )
                        Spacer(Modifier.width(16.dp))

                        Column(Modifier.weight(1f)) {
                            Text(
                                text = item.title,
                                style = AppTheme.typography.overlayLauncherSettingsTitle,
                                overflow = TextOverflow.Ellipsis,
                                maxLines = 1,
                                color = AppTheme.colors.contentPrimary
                            )
                            Text(
                                text = item.packageName,
                                style = AppTheme.typography.overlayLauncherSettingsSubtitle,
                                overflow = TextOverflow.Ellipsis,
                                maxLines = 1,
                                color = AppTheme.colors.contentPrimary.copy(.5f)
                            )
                        }

                        // Other activity button
                        if (!item.isSingleActivity) {
                            Row(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(6.dp))
                                    .background(
                                        if (item.selectedCount > 0) {
                                            AppTheme.colors.contentAccent.copy(.6f)
                                        } else AppTheme.colors.lampSelectorDivider
                                    )
                                    .clickable {
                                        togglePackageActivityVisible(item.id)
                                    }
                                    .padding(start = 8.dp, top = 8.dp, end = 12.dp, bottom = 8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    modifier = Modifier
                                        .size(24.dp),
                                    imageVector = if (item.showActivity) {
                                        Icons.Filled.KeyboardArrowUp
                                    } else Icons.Filled.KeyboardArrowDown,
                                    tint = AppTheme.colors.contentPrimary,
                                    contentDescription = "add"
                                )

                                Spacer(Modifier.width(6.dp))

                                Text(
                                    text = buildString {
                                        append(stringResource(R.string.other_activities))
                                        if (item.selectedCount > 0) {
                                            append(" ( ${item.selectedCount} )")
                                        }
                                    },
                                    color = AppTheme.colors.contentPrimary,
                                    style = AppTheme.typography.overlayLauncherSettingsSubtitle
                                )
                            }
                        }

                        Spacer(Modifier.width(32.dp))

                        ProfileSwitch(
                            scale = .8f,
                            checked = item.isSelected,
                            enabled = true,
                            onCheckedChange = null
                        )

                        Spacer(Modifier.width(12.dp))
                    }
                }

                is AddListItem.Activity -> if (item.isShow) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { toggleValue(item.id) }
                            .background(AppTheme.colors.launcherSurface1)
                            .padding(vertical = 12.dp)
                            .padding(start = 64.dp, end = 16.dp), // 56
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                            tint = AppTheme.colors.contentPrimary,
                            contentDescription = "activity",
                            modifier = Modifier
                                .size(24.dp)
                        )

                        Spacer(Modifier.width(16.dp))

                        Column(Modifier.weight(1f)) {
                            Text(
                                text = item.title,
                                style = AppTheme.typography.overlayLauncherSettingsTitle,
                                overflow = TextOverflow.Ellipsis,
                                maxLines = 1,
                                color = AppTheme.colors.contentPrimary
                            )
                            Text(
                                text = item.launchActivity,
                                style = AppTheme.typography.overlayLauncherSettingsSubtitle,
                                overflow = TextOverflow.Ellipsis,
                                maxLines = 1,
                                color = AppTheme.colors.contentPrimary.copy(.5f)
                            )
                        }

                        Spacer(Modifier.width(32.dp))

                        ProfileSwitch(
                            scale = .8f,
                            checked = item.isSelected,
                            enabled = true,
                            onCheckedChange = null
                        )

                        Spacer(Modifier.width(12.dp))
                    }
                } else Unit
            }
        }

        item(key = -2) {
            Spacer(
                Modifier
                    .height(16.dp)
            )
        }
    }

    // Bottom divider
    Spacer(
        Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(AppTheme.colors.launcherSurface1.copy(.5f))
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(36.dp)
            .then(
                if (!config.windowMode) {
                    Modifier.padding(bottom = 12.dp)
                } else Modifier
            )
    ) {
        BaseButton(
            modifier = Modifier.fillMaxWidth(),
            title = stringResource(R.string.save),
            style = AppTheme.typography.overlayLauncherSettingsGroup,
            textColor = Color.White,
            backgroundColor = AppTheme.colors.contentAccent
        ) { saveAndExit() }
    }
}

private fun List<AddListItem>.withRecount(): List<AddListItem> {
    // Build counts per parentId
    val counts = HashMap<Int, Int>()
    for (item in this) {
        if (item is AddListItem.Activity && item.isSelected) {
            counts[item.parentId] = (counts[item.parentId] ?: 0) + 1
        }
    }
    // Apply counts to corresponding apps
    return this.map { item ->
        if (item is AddListItem.App) {
            item.copy(selectedCount = counts[item.id] ?: 0)
        } else item
    }
}

/* private fun intersects(
    a: Collection<String>,
    b: Collection<String>,
    ignoreCase: Boolean = false
): Boolean {
    if (a.isEmpty() || b.isEmpty()) return false

    // Build set from the smaller side to minimize memory and hashing work.
    val (small, large) = if (a.size <= b.size) a to b else b to a

    if (!ignoreCase) {
        // Case-sensitive fast path
        val set = HashSet<String>(small.size * 2)
        for (s in small) set.add(s)
        for (x in large) if (x in set) return true
        return false
    } else {
        // Case-insensitive path with stable Locale
        val set = HashSet<String>(small.size * 2)
        for (s in small) set.add(s.lowercase(Locale.ROOT))
        for (x in large) if (x.lowercase(Locale.ROOT) in set) return true
        return false
    }
}*/

private fun String.afterLastDot(): String {
    val idx = this.lastIndexOf('.')
    return if (idx >= 0 && idx + 1 < this.length) this.substring(idx + 1) else ""
}

/* Assigns stable ids (Long) and orders (Int) only to new items (id/order == 0).
 * If there are existing non-zero values, continues from max+1.
 * If none exist, starts id from currentTimeMillis() and order from a timestamp Int.
 */
private fun List<DisplayLauncherItem>.assignIdsAndOrder(): List<DisplayLauncherItem> {
    // find last non-zero id/order among existing items
    val lastId: Long = this.asSequence().map { it.id }.filter { it != 0L }.maxOrNull() ?: 0L
    val lastOrder: Int = this.asSequence().map { it.order }.filter { it != 0 }.maxOrNull() ?: 0

    // timestamps: Long for id, Int for order (safe modulo to fit Int range)
    val now = System.currentTimeMillis()
    val tsId: Long = now
    val tsOrder: Int = (now % Int.MAX_VALUE).toInt()

    // choose starting points
    var nextId: Long = if (lastId == 0L) tsId else lastId + 1L
    var nextOrder: Int = if (lastOrder == 0) tsOrder else lastOrder + 1

    // fill only zeros, keep existing values intact
    return this.map { item ->
        val newId = if (item.id == 0L) nextId.also { nextId++ } else item.id
        val newOrder = if (item.order == 0) nextOrder.also { nextOrder++ } else item.order
        item.copy(id = newId, order = newOrder)
    }
}
