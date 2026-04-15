package com.salat.gbinder.ui

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.salat.gbinder.LAST_DM_ID
import com.salat.gbinder.R
import com.salat.gbinder.car.data.CarPropertyValue
import com.salat.gbinder.entity.DISPLAY_DRIVE_MODES
import com.salat.gbinder.entity.DisplayDriveMode
import com.salat.gbinder.ui.theme.AppTheme

@Composable
fun TargetRestoreDMDialog(
    uiScaleState: Float? = null,
    driveMode: Int = LAST_DM_ID,
    onTargetDriveModeChanged: (Int) -> Unit = {},
    onDismiss: () -> Unit = {}
) = BaseDialog(uiScaleState = uiScaleState, onDismiss = onDismiss) {
    val context = LocalContext.current
    val lastItem = remember {
        DisplayDriveMode(
            id = LAST_DM_ID,
            originalName = "",
            displayName = context.getString(R.string.last),
            description = R.string.driving_mode_persistence
        )
    }
    val driveModes by remember { mutableStateOf(listOf(lastItem) + DISPLAY_DRIVE_MODES) }
    var dmToggleSelected by remember {
        mutableStateOf(
            if (driveMode == LAST_DM_ID) {
                lastItem
            } else driveModes.find { it.id == driveMode } ?: lastItem
        )
    }

    Column(modifier = Modifier.padding(top = 22.dp)) {
        Text(
            text = stringResource(R.string.restore_mode),
            modifier = Modifier.padding(horizontal = 24.dp),
            color = AppTheme.colors.contentPrimary,
            style = AppTheme.typography.dialogTitle,
            overflow = TextOverflow.Ellipsis,
            maxLines = 2
        )

        Spacer(Modifier.height(5.dp))

        Text(
            text = stringResource(R.string.restore_mode_desc),
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
                items = driveModes,
                key = { _, item -> item.id }
            ) { _, item ->

                Column {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { dmToggleSelected = item }
                            .padding(vertical = 12.dp)
                            .padding(end = 6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Spacer(Modifier.width(24.dp))

                        Column(Modifier.weight(1f)) {
                            Text(
                                text = item.displayName,
                                style = AppTheme.typography.cardTitle,
                                overflow = TextOverflow.Ellipsis,
                                maxLines = 1,
                                color = AppTheme.colors.contentPrimary
                            )

                            Spacer(Modifier.height(4.dp))

                            Text(
                                text = stringResource(item.description),
                                style = AppTheme.typography.idTitle,
                                color = AppTheme.colors.contentPrimary.copy(.5f)
                            )
                        }

                        Spacer(Modifier.width(16.dp))

                        RadioButton(
                            selected = (item == dmToggleSelected),
                            onClick = { dmToggleSelected = item },
                            colors = RadioButtonColors(
                                selectedColor = AppTheme.colors.contentAccent.copy(.8f),
                                unselectedColor = AppTheme.colors.contentPrimary.copy(
                                    .3f
                                ),
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

                    if (item.id == CarPropertyValue.DRIVE_MODE_SELECTION_ECO) {
                        Box(
                            Modifier
                                .fillMaxWidth()
                                .padding(vertical = 6.dp)
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
                    }

                    if (item.id == LAST_DM_ID) {
                        Box(
                            Modifier
                                .fillMaxWidth()
                                .padding(vertical = 6.dp)
                                .background(AppTheme.colors.surfaceMenu)
                        ) {
                            Text(
                                modifier = Modifier
                                    .padding(top = 16.dp, bottom = 12.dp)
                                    .padding(horizontal = 24.dp),
                                text = stringResource(R.string.standard),
                                style = AppTheme.typography.sourceType.copy(fontSize = 11.sp),
                                overflow = TextOverflow.Ellipsis,
                                maxLines = 1,
                                color = AppTheme.colors.contentPrimary
                            )
                        }
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
                    .clickable {
                        onDismiss()
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
                        onTargetDriveModeChanged(dmToggleSelected.id)
                        onDismiss()
                    }
                    .padding(horizontal = 14.dp, vertical = 8.dp),
                text = stringResource(android.R.string.ok).uppercase(),
                style = AppTheme.typography.dialogButton,
                color = AppTheme.colors.contentAccent
            )
        }
    }
}
