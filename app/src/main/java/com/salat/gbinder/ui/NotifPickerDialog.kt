package com.salat.gbinder.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonColors
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import com.salat.gbinder.R
import com.salat.gbinder.car.data.CarPropertyValue
import com.salat.gbinder.datastore.DataStoreRepository
import com.salat.gbinder.entity.DISPLAY_DRIVE_MODES
import com.salat.gbinder.entity.DisplayDriveMode
import com.salat.gbinder.ui.theme.AppTheme
import com.salat.gbinder.util.driveModeNotifStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.math.roundToInt

@Composable
fun NotifPickerDialog(
    uiScaleState: Float? = null,
    dataStore: DataStoreRepository,
    playTest: (Int, Float) -> Unit = { _, _ -> },
    onDismiss: () -> Unit = {}
) = BaseDialog(uiScaleState = uiScaleState, onDismiss = onDismiss) {
    var dmList by remember { mutableStateOf<List<DisplayDriveMode>?>(null) }
    var pickedDm by remember { mutableStateOf<DisplayDriveMode?>(null) }

    var samples by remember { mutableStateOf(driveModeNotifStore) }

    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        dmList = DISPLAY_DRIVE_MODES
            .map { driveMode ->
                driveMode.copy(
                    notifSample = dataStore.getValueFlow(intPreferencesKey("DM_NOTIF_SAMPLE_${driveMode.id}"))
                        .first() ?: -1,
                    notifVolume = dataStore.getValueFlow(floatPreferencesKey("DM_NOTIF_VOLUME_${driveMode.id}"))
                        .first() ?: 1f
                )
            }
            .onEach { driveMode ->
                launch {
                    dataStore.getValueFlow(intPreferencesKey("DM_NOTIF_SAMPLE_${driveMode.id}"))
                        .collect { prefValue ->
                            dmList = dmList?.map {
                                if (driveMode.id == it.id) it.copy(
                                    notifSample = prefValue ?: -1
                                ) else it
                            }
                        }
                }
            }
    }

    // Break second step
    BackHandler(enabled = pickedDm != null) { pickedDm = null }

    if (dmList == null || dmList?.isEmpty() == true) {
        RenderScan(stringResource(R.string.loading))
    } else if (pickedDm != null) {

        Column(modifier = Modifier.padding(top = 22.dp)) {
            Text(
                text = pickedDm?.displayName ?: "",
                modifier = Modifier.padding(horizontal = 24.dp),
                color = AppTheme.colors.contentPrimary,
                style = AppTheme.typography.dialogTitle,
                overflow = TextOverflow.Ellipsis,
                maxLines = 2
            )

            Spacer(Modifier.height(5.dp))

            Text(
                text = stringResource(R.string.select_switch_sound),
                modifier = Modifier.padding(horizontal = 23.dp),
                color = AppTheme.colors.contentPrimary.copy(.4f),
                style = AppTheme.typography.dialogSubtitle
            )

            Spacer(modifier = Modifier.height(12.dp))

            Spacer(
                Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(Color.White.copy(.1f))
            )

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .background(AppTheme.colors.surfaceMenu.copy(.4f))
            ) {
                item(key = -2) {
                    Spacer(
                        Modifier
                            .height(.8.dp)
                    )
                }
                itemsIndexed(
                    items = samples,
                    key = { _, item -> item.id }
                ) { _, sample ->

                    fun onSelect() = scope.launch {
                        samples = samples.map {
                            it.copy(isSelected = it.id == sample.id)
                        }
                        if (sample.id != -1) {
                            val volume = pickedDm?.notifVolume ?: 1f
                            withContext(Dispatchers.Main) { playTest(sample.id, volume) }
                        }
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                onSelect()
                            }
                            .padding(vertical = 10.dp)
                            .padding(end = 6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Spacer(Modifier.width(24.dp))

                        val isNo = sample.id == -1

//                        if (!isNo && sample.isSelected) {
//                            Icon(
//                                imageVector = Icons.Filled.PlayArrow,
//                                tint = AppTheme.colors.contentAccent,
//                                contentDescription = null,
//                                modifier = Modifier
//                                    .size(22.dp)
//                                    .clip(RoundedCornerShape(6.dp))
//                            )
//
//                            Spacer(Modifier.width(6.dp))
//                        }

                        Text(
                            text = if (isNo) stringResource(R.string.no) else sample.name,
                            style = AppTheme.typography.sourceType.copy(fontSize = 13.sp),
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1,
                            color = if (isNo || !sample.isSelected) {
                                AppTheme.colors.contentPrimary.copy(.8f)
                            } else {
                                AppTheme.colors.contentAccent
                            }
                        )

                        Spacer(Modifier.weight(1f))

                        RadioButton(
                            selected = (sample.isSelected),
                            onClick = { onSelect() },
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

                        Spacer(Modifier.width(12.dp))
                    }

                    if (samples.last().id != sample.id) {
                        Spacer(
                            Modifier
                                .fillMaxWidth()
                                .height(.8.dp)
                                .background(Color.White.copy(.1f))
                        )
                    }
                }
            }

            pickedDm?.let { dmValue ->

                Spacer(
                    Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(Color.White.copy(.1f))
                )

                Spacer(Modifier.height(16.dp))

                fun Float.toPercent() = (this * 100f).roundToInt().toString()
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 42.dp),
                    textAlign = TextAlign.Left,
                    text = "${stringResource(R.string.notification_volume)}: ${dmValue.notifVolume.toPercent()}%",
                    color = AppTheme.colors.contentPrimary
                )
                ValueSlider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 36.dp),
                    value = dmValue.notifVolume,
                    valueRange = .1f..4f,
                    onValueChange = { newValue ->
                        pickedDm = dmValue.copy(
                            notifVolume = newValue
                        )
                    },
                    enabled = true,
                    defaultMark = 1f,
                    step = .1f
                )

                Spacer(Modifier.height(4.dp))
            }

            Spacer(
                Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(Color.White.copy(.1f))
            )

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
                        .clickable {
                            pickedDm = null
                        }
                        .padding(horizontal = 14.dp, vertical = 8.dp),
                    text = stringResource(android.R.string.cancel).uppercase(),
                    style = AppTheme.typography.dialogButton,
                    color = AppTheme.colors.contentAccent
                )
                Text(
                    modifier = Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .clickable {
                            scope.launch {
                                val pickedId = samples.find { it.isSelected }?.id ?: -1
                                pickedDm?.let { dm ->
                                    // update dm notif sample in prefs
                                    dataStore.saveValue(
                                        key = intPreferencesKey("DM_NOTIF_SAMPLE_${dm.id}"),
                                        value = pickedId
                                    )

                                    // update volume in dialog list
                                    dmList = dmList?.map {
                                        if (it.id == dm.id) {
                                            it.copy(notifVolume = dm.notifVolume)
                                        } else it
                                    }
                                    // update volume in prefs
                                    dataStore.saveValue(
                                        key = floatPreferencesKey("DM_NOTIF_VOLUME_${dm.id}"),
                                        value = dm.notifVolume
                                    )
                                }
                                pickedDm = null
                            }
                        }
                        .padding(horizontal = 14.dp, vertical = 8.dp),
                    text = stringResource(android.R.string.ok).uppercase(),
                    style = AppTheme.typography.dialogButton,
                    color = AppTheme.colors.contentAccent
                )
            }
        }

    } else {
        Column(modifier = Modifier.padding(top = 22.dp)) {
            Text(
                text = stringResource(R.string.driving_mode_change_sound),
                modifier = Modifier.padding(horizontal = 24.dp),
                color = AppTheme.colors.contentPrimary,
                style = AppTheme.typography.dialogTitle,
                overflow = TextOverflow.Ellipsis,
                maxLines = 2
            )

            Spacer(Modifier.height(5.dp))

            Text(
                text = stringResource(R.string.select_driving_mode_sound),
                modifier = Modifier.padding(horizontal = 23.dp),
                color = AppTheme.colors.contentPrimary.copy(.4f),
                style = AppTheme.typography.dialogSubtitle
            )

            Spacer(modifier = Modifier.height(12.dp))

            Spacer(
                Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(Color.White.copy(.1f))
            )

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
                    items = dmList ?: emptyList(),
                    key = { _, item -> item.id }
                ) { _, item ->

                    Column {

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    scope.launch {
                                        samples = samples.map {
                                            it.copy(isSelected = it.id == item.notifSample)
                                        }
                                        pickedDm = item
                                    }
                                }
                                .padding(vertical = 10.dp)
                                .padding(end = 6.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Spacer(Modifier.width(24.dp))

                            Column {
                                Text(
                                    text = item.displayName,
                                    style = AppTheme.typography.cardTitle,
                                    overflow = TextOverflow.Ellipsis,
                                    maxLines = 1,
                                    color = AppTheme.colors.contentPrimary
                                )

                                Spacer(Modifier.height(4.dp))

                                Text(
                                    text = "${item.id}",
                                    style = AppTheme.typography.idTitle.copy(fontSize = 9.sp),
                                    overflow = TextOverflow.Ellipsis,
                                    maxLines = 1,
                                    color = AppTheme.colors.contentPrimary.copy(.5f)
                                )
                            }

                            Spacer(Modifier.weight(1f))

                            Row(
                                Modifier
                                    .clip(RoundedCornerShape(16.dp))
                                    .background(
                                        if (item.notifSample == -1) {
                                            AppTheme.colors.surfaceMenu
                                        } else {
                                            AppTheme.colors.addSplitTop
                                        }
                                    )
                                    .padding(horizontal = 14.dp, vertical = 7.dp)
                            ) {
                                Text(
                                    text = when (item.notifSample) {
                                        -1 -> stringResource(R.string.no)
                                        else -> driveModeNotifStore
                                            .find { it.id == item.notifSample }
                                            ?.name
                                            ?: ""
                                    },
                                    style = AppTheme.typography.settingsTitle.copy(fontSize = 12.sp),
                                    overflow = TextOverflow.Ellipsis,
                                    maxLines = 1,
                                    color = if (item.notifSample == -1) {
                                        AppTheme.colors.contentPrimary.copy(.5f)
                                    } else {
                                        AppTheme.colors.contentPrimary
                                    }
                                )
                            }

                            Spacer(Modifier.width(12.dp))
                        }

                        if (item.id == CarPropertyValue.DRIVE_MODE_SELECTION_ECO) {
                            Box(
                                Modifier
                                    .fillMaxWidth()
                                    .background(AppTheme.colors.surfaceMenu)
                            ) {
                                Text(
                                    modifier = Modifier
                                        .padding(top = 16.dp, bottom = 12.dp)
                                        .padding(horizontal = 24.dp),
                                    text = stringResource(R.string.additional),
                                    style = AppTheme.typography.sourceType.copy(fontSize = 11.sp),
                                    overflow = TextOverflow.Ellipsis,
                                    maxLines = 1,
                                    color = AppTheme.colors.contentPrimary
                                )
                            }
                        } else if (dmList?.last()?.id != item.id) {
                            Spacer(
                                Modifier
                                    .fillMaxWidth()
                                    .height(.8.dp)
                                    .background(Color.White.copy(.04f))
                            )
                        }
                    }
                }
            }

            Spacer(
                Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(Color.White.copy(.1f))
            )

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
                        .clickable { onDismiss() }
                        .padding(horizontal = 14.dp, vertical = 8.dp),
                    text = stringResource(android.R.string.ok).uppercase(),
                    style = AppTheme.typography.dialogButton,
                    color = AppTheme.colors.contentAccent
                )
            }
        }
    }
}
