package com.salat.gbinder.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.salat.gbinder.APP_ICON_QUALITY
import com.salat.gbinder.APP_ICON_ROUND
import com.salat.gbinder.R
import com.salat.gbinder.datastore.DataStoreRepository
import com.salat.gbinder.datastore.NoBackupPrefs
import com.salat.gbinder.entity.DeviceAppInfo
import com.salat.gbinder.entity.IGNORED_MEDIA_APPS
import com.salat.gbinder.mappers.toDisplay
import com.salat.gbinder.ui.theme.AppTheme
import com.salat.gbinder.util.SystemAppsLightRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

private enum class MediaAppsDialogStep {
    PICK_MEDIA_APPS,
    PICK_DEFAULT_MEDIA_APP
}

@Composable
fun RenderMediaAppsPickerDialog(
    uiScaleState: Float? = null,
    systemApps: SystemAppsLightRepository,
    dataStore: DataStoreRepository,
    onDismiss: () -> Unit = {}
) = BaseDialog(uiScaleState = uiScaleState, onDismiss = onDismiss) {

    var apps: List<DeviceAppInfo>? by remember { mutableStateOf(null) }
    var enabled: List<String>? by remember { mutableStateOf(null) }
    var default: String? by remember { mutableStateOf(null) }
    var step: MediaAppsDialogStep by remember { mutableStateOf(MediaAppsDialogStep.PICK_MEDIA_APPS) }

    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    LaunchedEffect(true) {
        withContext(Dispatchers.IO) {
            val installedApps = async {
                val ownPackage = context.packageName
                systemApps
                    .getAllApps(APP_ICON_ROUND, true, APP_ICON_QUALITY)
                    .filter { it.packageName != ownPackage }
                    .sortedByDescending { it.isMedia }
            }
            val enabledApps = async {
                enabled =
                    (dataStore.getValueFlow(NoBackupPrefs.ENABLED_MEDIA_APPS).firstOrNull() ?: "")
                        .split("|")
                        .filter { it.isNotEmpty() }
                default =
                    dataStore.getValueFlow(NoBackupPrefs.DEFAULT_MEDIA_APP).firstOrNull() ?: ""
            }

            enabledApps.await().also {
                try {
                    apps = installedApps.await()
                        .toDisplay(enabled!!, default!!, IGNORED_MEDIA_APPS)
                        .sortedByDescending { it.isSelected }
                } catch (e: Exception) {
                    Timber.e(e)
                }
            }
        }
    }

    if (apps == null || apps?.isEmpty() == true) {
        RenderScan()
    } else {
        Column(modifier = Modifier.padding(top = 22.dp)) {
            Text(
                text = stringResource(
                    when (step) {
                        MediaAppsDialogStep.PICK_MEDIA_APPS -> R.string.media_apps
                        MediaAppsDialogStep.PICK_DEFAULT_MEDIA_APP -> R.string.default_
                    }
                ),
                modifier = Modifier.padding(horizontal = 24.dp),
                color = AppTheme.colors.contentPrimary,
                style = AppTheme.typography.dialogTitle,
                overflow = TextOverflow.Ellipsis,
                maxLines = 2
            )

            Spacer(Modifier.height(5.dp))

            Text(
                text = stringResource(
                    when (step) {
                        MediaAppsDialogStep.PICK_MEDIA_APPS -> R.string.media_apps_desc
                        MediaAppsDialogStep.PICK_DEFAULT_MEDIA_APP -> R.string.select_default_media_app
                    }
                ),
                modifier = Modifier.padding(horizontal = 23.dp),
                color = AppTheme.colors.contentPrimary.copy(.4f),
                style = AppTheme.typography.dialogSubtitle
            )

            // Compat guide
            if (MediaAppsDialogStep.PICK_MEDIA_APPS == step) {
                Spacer(Modifier.height(5.dp))

                Row(
                    modifier = Modifier.padding(horizontal = 24.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.CheckCircle,
                        tint = AppTheme.colors.addSplitTop,
                        contentDescription = null,
                        modifier = Modifier
                            .offset(y = 1.dp)
                            .size(20.dp)
                            .clip(RoundedCornerShape(6.dp))
                    )
                    Text(
                        text = "- ${stringResource(R.string.supported)}",
                        style = AppTheme.typography.dialogSubtitle,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                        color = AppTheme.colors.contentPrimary.copy(.4f),
                    )
                }

                Spacer(Modifier.height(6.dp))

                Row(
                    modifier = Modifier.padding(horizontal = 24.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Info,
                        tint = AppTheme.colors.warning,
                        contentDescription = null,
                        modifier = Modifier
                            .offset(y = 1.dp)
                            .size(20.dp)
                            .clip(RoundedCornerShape(6.dp))
                    )
                    Text(
                        text = "- ${stringResource(R.string.possibly_supported)}",
                        style = AppTheme.typography.dialogSubtitle,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                        color = AppTheme.colors.contentPrimary.copy(.4f),
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            RenderDivider()

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                item(key = -1) {
                    Spacer(
                        Modifier
                            .height(.8.dp)
                    )
                }
                itemsIndexed(
                    items = when (step) {
                        MediaAppsDialogStep.PICK_MEDIA_APPS -> apps as List<DeviceAppInfo>
                        MediaAppsDialogStep.PICK_DEFAULT_MEDIA_APP -> (apps as List<DeviceAppInfo>).filter { it.isSelected }
                    },
                    key = { index, _ -> index }
                ) { _, item ->

                    fun selectDefault() {
                        apps = apps?.map {
                            if (it == item) {
                                it.copy(isDefault = true)
                            } else it.copy(isDefault = false)
                        }
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                scope.launch {
                                    when (step) {
                                        MediaAppsDialogStep.PICK_MEDIA_APPS -> {
                                            withContext(Dispatchers.Default) {
                                                apps = apps?.map {
                                                    if (it == item) {
                                                        it.copy(isSelected = !it.isSelected)
                                                    } else it
                                                }
                                            }
                                        }

                                        MediaAppsDialogStep.PICK_DEFAULT_MEDIA_APP -> {
                                            withContext(Dispatchers.Default) {
                                                selectDefault()
                                            }
                                        }
                                    }

                                }
                            }
                            .padding(vertical = 8.dp)
                            .padding(end = 6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Spacer(Modifier.width(16.dp))

                        item.icon.let { icon ->
                            DrawableImage(
                                icon = icon,
                                modifier = Modifier
                                    .size(32.dp)
                                    .clip(RoundedCornerShape(6.dp))
                            )
                            Spacer(Modifier.width(10.dp))
                        }

                        Column {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Text(
                                    text = item.appName,
                                    style = AppTheme.typography.dialogListTitle,
                                    overflow = TextOverflow.Ellipsis,
                                    maxLines = 1,
                                    color = AppTheme.colors.contentPrimary
                                )

                                Icon(
                                    imageVector = if (item.isMediaApp) {
                                        Icons.Filled.CheckCircle
                                    } else {
                                        Icons.Filled.Info
                                    },
                                    tint = if (item.isMediaApp) {
                                        AppTheme.colors.addSplitTop
                                    } else {
                                        AppTheme.colors.warning
                                    },
                                    contentDescription = stringResource(R.string.back),
                                    modifier = Modifier
                                        .offset(y = 1.dp)
                                        .size(20.dp)
                                        .clip(RoundedCornerShape(6.dp))
                                )
                            }
                            Text(
                                text = item.packageName,
                                style = AppTheme.typography.dialogSubtitle,
                                overflow = TextOverflow.Ellipsis,
                                maxLines = 1,
                                color = AppTheme.colors.contentPrimary.copy(.5f)
                            )
                        }

                        Spacer(Modifier.weight(1f))

                        when (step) {
                            MediaAppsDialogStep.PICK_MEDIA_APPS -> {
                                ProfileSwitch(
                                    scale = .8f,
                                    checked = item.isSelected,
                                    enabled = true,
                                    onCheckedChange = null
                                )
                            }

                            MediaAppsDialogStep.PICK_DEFAULT_MEDIA_APP -> {
                                RadioButton(
                                    selected = (item.isDefault),
                                    onClick = {
                                        scope.launch {
                                            withContext(Dispatchers.Default) {
                                                selectDefault()
                                            }
                                        }
                                    },
                                    colors = RadioButtonColors(
                                        selectedColor = AppTheme.colors.contentAccent.copy(.8f),
                                        unselectedColor = AppTheme.colors.contentPrimary.copy(.3f),
                                        disabledSelectedColor = AppTheme.colors.contentPrimary.copy(
                                            .3f
                                        ),
                                        disabledUnselectedColor = AppTheme.colors.contentPrimary.copy(
                                            .3f
                                        )
                                    )
                                )
                            }
                        }

                        Spacer(Modifier.width(12.dp))
                    }
                }
            }

            RenderDivider()

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp, horizontal = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.End)
            ) {
                Text(
                    modifier = Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .clickable(onClick = {
                            when (step) {
                                MediaAppsDialogStep.PICK_MEDIA_APPS -> onDismiss()
                                MediaAppsDialogStep.PICK_DEFAULT_MEDIA_APP -> step =
                                    MediaAppsDialogStep.PICK_MEDIA_APPS
                            }
                        }
                        )
                        .padding(horizontal = 14.dp, vertical = 8.dp),
                    text = stringResource(
                        when (step) {
                            MediaAppsDialogStep.PICK_MEDIA_APPS -> android.R.string.cancel
                            MediaAppsDialogStep.PICK_DEFAULT_MEDIA_APP -> R.string.back
                        }
                    ).uppercase(),
                    style = AppTheme.typography.dialogButton,
                    color = AppTheme.colors.contentAccent
                )
                val enableOk by remember {
                    derivedStateOf {
                        when (step) {
                            MediaAppsDialogStep.PICK_MEDIA_APPS -> apps?.any { it.isSelected } == true
                            MediaAppsDialogStep.PICK_DEFAULT_MEDIA_APP -> apps?.any { it.isDefault } == true
                        }
                    }
                }
                Text(
                    modifier = Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .clickable(enabled = enableOk) {
                            if (step == MediaAppsDialogStep.PICK_MEDIA_APPS) {
                                scope.launch {
                                    val alreadyHasDefault =
                                        apps?.any { it.isSelected && it.isDefault } == true
                                    if (!alreadyHasDefault) {
                                        apps = apps?.map {
                                            it.copy(isDefault = false)
                                        }
                                    }
                                    step = MediaAppsDialogStep.PICK_DEFAULT_MEDIA_APP
                                }
                            } else {
                                scope.launch {
                                    val enabledMediaApps = apps
                                        ?.filter { it.isSelected }
                                        ?.joinToString("|") { it.packageName } ?: ""
                                    val newDefaultApp =
                                        apps?.find { it.isDefault }?.packageName ?: ""

                                    dataStore.saveValue(
                                        NoBackupPrefs.ENABLED_MEDIA_APPS,
                                        enabledMediaApps
                                    )
                                    dataStore.saveValue(
                                        NoBackupPrefs.DEFAULT_MEDIA_APP,
                                        newDefaultApp
                                    )

                                    onDismiss()
                                }
                            }
                        }
                        .padding(horizontal = 14.dp, vertical = 8.dp),
                    text = stringResource(
                        when (step) {
                            MediaAppsDialogStep.PICK_MEDIA_APPS -> R.string.next
                            MediaAppsDialogStep.PICK_DEFAULT_MEDIA_APP -> android.R.string.ok
                        }
                    ).uppercase(),
                    style = AppTheme.typography.dialogButton,
                    color = if (enableOk) {
                        AppTheme.colors.contentAccent
                    } else AppTheme.colors.contentPrimary.copy(.3f)
                )
            }
        }

    }
}

@Composable
private fun RenderDivider() = Spacer(
    Modifier
        .fillMaxWidth()
        .height(1.dp)
        .background(Color.White.copy(.1f))
)
