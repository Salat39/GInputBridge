package com.salat.gbinder.features.configurator

import android.content.Context
import android.content.Intent
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.LocalOverscrollFactory
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.salat.gbinder.ConfiguratorPresetsViewModel
import com.salat.gbinder.R
import com.salat.gbinder.SegmentToggler
import com.salat.gbinder.car.data.CarPropertyValue
import com.salat.gbinder.entity.SegmentTogglerItem
import com.salat.gbinder.ui.ConfirmDialog
import com.salat.gbinder.ui.FuncCustomDialog
import com.salat.gbinder.ui.RenderListButton
import com.salat.gbinder.ui.RenderSwitcher
import com.salat.gbinder.ui.TopShadow
import com.salat.gbinder.ui.clickableNoRipple
import com.salat.gbinder.ui.theme.AppTheme

@Composable
fun RenderConfiguratorPresets(
    uiScaleState: Float? = null,
    enableAdbHelper: Boolean,
    adbDimAutoStop: Boolean,
    onAdbDimAutoStopChanged: (Boolean) -> Unit,
    onClose: () -> Unit
) {
    val viewModel: ConfiguratorPresetsViewModel = hiltViewModel()

    BackHandler(onBack = onClose)

    Column(Modifier.fillMaxSize()) {
        var showSecret by remember { mutableIntStateOf(0) }

        // Toolbar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .clickableNoRipple {
                    showSecret++
                },
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                modifier = Modifier
                    .size(56.dp)
                    .padding(start = 2.dp),
                onClick = remember { { onClose() } }
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    tint = AppTheme.colors.contentPrimary,
                    contentDescription = stringResource(R.string.back)
                )
            }

            Spacer(Modifier.width(16.dp))

            Text(
                modifier = Modifier.weight(1f),
                text = stringResource(R.string.system_parameters),
                style = AppTheme.typography.stubTitle,
                color = AppTheme.colors.contentPrimary
            )

            Spacer(Modifier.width(36.dp))
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(AppTheme.colors.lampBackground.copy(.3f))
        ) {
            TopShadow()

            CompositionLocalProvider(LocalOverscrollFactory provides null) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                ) {
                    Spacer(Modifier.height(16.dp))

                    var showBindCustom by remember { mutableStateOf(false) }
                    if (showBindCustom) {
                        FuncCustomDialog(
                            uiScaleState = uiScaleState,
                            setFuncCustomKey = {
                                viewModel.setFuncCustomKey(it)
                            },
                            onDismiss = { showBindCustom = false }
                        )
                    }

                    RenderListButton(
                        modifier = Modifier.padding(horizontal = 20.dp),
                        title = stringResource(R.string.assign_action_star),
                        subtitle = stringResource(R.string.free_button_action_custom)
                    ) {
                        showBindCustom = true
                    }

                    Spacer(Modifier.height(16.dp))

                    val warningVolume by viewModel.warningVolume.collectAsStateWithLifecycle()
                    warningVolume?.let { volume ->
                        val list = remember {
                            listOf(
                                SegmentTogglerItem(text = R.string.low),
                                SegmentTogglerItem(text = R.string.medium),
                                SegmentTogglerItem(text = R.string.high),
                            )
                        }

                        Text(
                            modifier = Modifier.padding(horizontal = 42.dp),
                            text = stringResource(R.string.turn_signal_volume),
                            style = AppTheme.typography.screenTitle,
                            color = AppTheme.colors.contentPrimary
                        )

                        Spacer(Modifier.height(12.dp))

                        Box(
                            Modifier
                                .padding(horizontal = 42.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .background(AppTheme.colors.lampSelectorBg)
                                .padding(2.dp)
                        ) {
                            SegmentToggler(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                selectedIndex = when (volume) {
                                    CarPropertyValue.SOUND_WARNING_VOLUME_LEVEL_MID -> 1
                                    CarPropertyValue.SOUND_WARNING_VOLUME_LEVEL_HIGH -> 2
                                    else -> 0
                                },
                                items = list,
                            ) {
                                val value = when (it) {
                                    1 -> CarPropertyValue.SOUND_WARNING_VOLUME_LEVEL_MID
                                    2 -> CarPropertyValue.SOUND_WARNING_VOLUME_LEVEL_HIGH
                                    else -> CarPropertyValue.SOUND_WARNING_VOLUME_LEVEL_LOW
                                }
                                viewModel.setWarningVolume(value)
                            }
                        }

                        Spacer(Modifier.height(16.dp))
                    }

                    RenderSwitcher(
                        modifier = Modifier.padding(horizontal = 20.dp),
                        title = "[ADB] ${stringResource(R.string.stop_dim_interaction_on_boot_title)}",
                        subtitle = stringResource(R.string.stop_dim_interaction_on_boot_desc),
                        enable = enableAdbHelper,
                        value = adbDimAutoStop,
                        groupDivider = false,
                        onChange = { onAdbDimAutoStopChanged(it) }
                    )

                    if (showSecret >= 10) {
                        val context = LocalContext.current
                        var intelligentMenuWarningDialog by remember { mutableStateOf(false) }
                        if (intelligentMenuWarningDialog) {
                            ConfirmDialog(
                                title = stringResource(R.string.attention_warning),
                                message = stringResource(R.string.system_dialog_warning_desc),
                                uiScale = uiScaleState,
                                negativeAction = false,
                                onCancel = { intelligentMenuWarningDialog = false },
                                onDismiss = { intelligentMenuWarningDialog = false },
                                onClick = {
                                    context.openIntelligentMenu()
                                    intelligentMenuWarningDialog = false
                                }
                            )
                        }

                        RenderListButton(
                            modifier = Modifier.padding(horizontal = 20.dp),
                            title = stringResource(R.string.open_optimization_settings),
                            subtitle = stringResource(R.string.optimization_settings_desc)
                        ) {
                            intelligentMenuWarningDialog = true
                        }
                    }

                    Spacer(Modifier.height(90.dp))
                }
            }
        }
    }
}

private fun Context.openIntelligentMenu() = runCatching {
    val intent = Intent("com.geely.setting.ACTION_SETTING_SEARCH").apply {
        setClassName(
            "com.geely.settings",
            "com.geely.settings.GlySettingsActivity"
        )
        putExtra("ext1", 6011)
        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
    }
    startActivity(intent)
}
