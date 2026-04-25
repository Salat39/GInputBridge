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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.salat.gbinder.R
import com.salat.gbinder.car.data.CarPropertyValue
import com.salat.gbinder.ui.theme.AppTheme

@Composable
fun FuncCustomDialog(
    uiScaleState: Float? = null,
    setFuncCustomKey: (Int) -> Unit = {},
    onDismiss: () -> Unit = {}
) = BaseDialog(uiScaleState = uiScaleState, onDismiss = onDismiss) {

    // Remember map to keep the insertion order for list rendering
    val customActions = remember {
        mapOf(
            // "DVR (Custom)" to CarPropertyValue.CUSTOM_KEY_TYPE_DVR,
            "Navigate To Home (Custom)" to CarPropertyValue.CUSTOM_KEY_TYPE_NAVIGATION,

            "360 Cameras" to CarPropertyValue.CUSTOM_KEY_TYPE_360_PANORAMA,
            "Toggle Audio" to CarPropertyValue.CUSTOM_KEY_TYPE_SOUND_SWITCH,
            "Adjust Mirror" to CarPropertyValue.CUSTOM_KEY_TYPE_REAR_MIRROR_ADJUST,
            "Open Trunk" to CarPropertyValue.CUSTOM_KEY_TYPE_UNLCKTRUNK,
            "Change Drive Mode" to CarPropertyValue.CUSTOM_KEY_TYPE_DRIVING_MODE,

            // "Speaker Volume" to CarPropertyValue.CUSTOM_KEY_TYPE_LOUD_SPEAKER,
            // "Auto Parking" to CarPropertyValue.CUSTOM_KEY_TYPE_AUTO_PARK,
            "Collect Fav" to CarPropertyValue.CUSTOM_KEY_TYPE_COLLECT_FAV,
            "Dim Full Screen Map" to CarPropertyValue.CUSTOM_KEY_TYPE_DIM_FULL_SCREEN_MAP
        )
    }

    Column(modifier = Modifier.padding(top = 22.dp)) {
        Text(
            text = stringResource(R.string.assign_action_star),
            modifier = Modifier.padding(horizontal = 24.dp),
            color = AppTheme.colors.contentPrimary,
            style = AppTheme.typography.dialogTitle,
            overflow = TextOverflow.Ellipsis,
            maxLines = 2
        )

        Spacer(modifier = Modifier.height(12.dp))

        Spacer(
            Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Color.White.copy(alpha = .1f))
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f, fill = false),
            horizontalAlignment = Alignment.CenterHorizontally,
            content = {
                // Top spacing to match previous layout
                item { Spacer(Modifier.height(22.dp)) }

                // Render items in the same order as in the original map
                items(
                    items = customActions.toList(),
                    key = { (_, key) -> key } // Stable key based on action id
                ) { (name, key) ->

                    // Custom section title
                    if (key == CarPropertyValue.CUSTOM_KEY_TYPE_DVR) {
                        Text(
                            text = stringResource(R.string.free_button_action_title),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp),
                            color = AppTheme.colors.contentPrimary,
                            style = AppTheme.typography.dialogSubtitle
                        )
                        Spacer(Modifier.height(14.dp))
                    }

                    // Second section title
                    if (key == CarPropertyValue.CUSTOM_KEY_TYPE_360_PANORAMA) {
                        Text(
                            text = stringResource(R.string.standard_system_actions),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp),
                            color = AppTheme.colors.contentPrimary,
                            style = AppTheme.typography.dialogSubtitle
                        )
                        Spacer(Modifier.height(14.dp))
                    }

                    // Unsupported section title
                    if (key == CarPropertyValue.CUSTOM_KEY_TYPE_LOUD_SPEAKER) {
                        Text(
                            text = stringResource(R.string.cut_system_actions),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp),
                            color = AppTheme.colors.contentPrimary,
                            style = AppTheme.typography.dialogSubtitle
                        )
                        Spacer(Modifier.height(14.dp))
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        BaseButton(
                            modifier = Modifier.fillMaxWidth(),
                            title = name,
                            backgroundColor = if (key in setOf(0, 2)) {
                                AppTheme.colors.addSplitTop
                            } else AppTheme.colors.surfaceMenu
                        ) {
                            // Set selected key and close dialog, same as before
                            setFuncCustomKey(key)
                            onDismiss()
                        }
                    }

                    // Preserve conditional dividers/spacers for specific keys
                    if (key in setOf(
                            CarPropertyValue.CUSTOM_KEY_TYPE_DRIVING_MODE,
                            CarPropertyValue.CUSTOM_KEY_TYPE_NAVIGATION
                        )
                    ) {
                        Spacer(Modifier.height(20.dp))
                        Spacer(
                            Modifier
                                .fillMaxWidth()
                                .height(1.dp)
                                .background(Color.White.copy(alpha = .035f))
                        )
                        Spacer(Modifier.height(6.dp))
                    }

                    Spacer(Modifier.height(14.dp))
                }

                // Bottom spacing to match previous layout
                item { Spacer(Modifier.height(14.dp)) }
            }
        )

        Spacer(
            Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Color.White.copy(alpha = .1f))
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
                text = stringResource(android.R.string.cancel).uppercase(),
                style = AppTheme.typography.dialogButton,
                color = AppTheme.colors.contentAccent
            )
        }
    }
}
