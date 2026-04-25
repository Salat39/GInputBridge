package com.salat.gbinder.features.configurator

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.LocalOverscrollFactory
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.salat.gbinder.ConfiguratorPresetsViewModel
import com.salat.gbinder.R
import com.salat.gbinder.car.data.CarPropertyValue
import com.salat.gbinder.entity.SegmentTogglerItem
import com.salat.gbinder.ui.FuncCustomDialog
import com.salat.gbinder.ui.RenderListButton
import com.salat.gbinder.ui.RenderSwitcher
import com.salat.gbinder.ui.SegmentToggler
import com.salat.gbinder.ui.TopShadow
import com.salat.gbinder.ui.theme.AppTheme

@Composable
fun RenderSystemParams(
    uiScaleState: Float? = null,
    enableAdbHelper: Boolean,
    adbDimAutoStop: Boolean,
    onAdbDimAutoStopChanged: (Boolean) -> Unit,
    onClose: () -> Unit
) {
    val viewModel: ConfiguratorPresetsViewModel = hiltViewModel()

    BackHandler(onBack = onClose)

    Column(Modifier.fillMaxSize()) {

        // Toolbar
        RenderConfiguratorPresetsToolbar(onClose = onClose)

        RenderConfiguratorPresetsContent(
            uiScaleState = uiScaleState,
            enableAdbHelper = enableAdbHelper,
            adbDimAutoStop = adbDimAutoStop,
            onAdbDimAutoStopChanged = onAdbDimAutoStopChanged,
            viewModel = viewModel
        )
    }
}

@Composable
private fun RenderConfiguratorPresetsToolbar(
    onClose: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
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
}

@Composable
private fun ColumnScope.RenderConfiguratorPresetsContent(
    uiScaleState: Float?,
    enableAdbHelper: Boolean,
    adbDimAutoStop: Boolean,
    onAdbDimAutoStopChanged: (Boolean) -> Unit,
    viewModel: ConfiguratorPresetsViewModel
) {
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

                RenderBindCustomSection(
                    uiScaleState = uiScaleState,
                    viewModel = viewModel
                )

                Spacer(Modifier.height(16.dp))

                RenderWarningVolumeSection(viewModel = viewModel)

                RenderAdbDimAutoStopSwitcher(
                    enableAdbHelper = enableAdbHelper,
                    adbDimAutoStop = adbDimAutoStop,
                    onAdbDimAutoStopChanged = onAdbDimAutoStopChanged
                )

                Spacer(Modifier.height(90.dp))
            }
        }
    }
}

@Composable
private fun RenderBindCustomSection(
    uiScaleState: Float?,
    viewModel: ConfiguratorPresetsViewModel
) {
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
}

@Composable
private fun RenderWarningVolumeSection(
    viewModel: ConfiguratorPresetsViewModel
) {
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
}

@Composable
private fun RenderAdbDimAutoStopSwitcher(
    enableAdbHelper: Boolean,
    adbDimAutoStop: Boolean,
    onAdbDimAutoStopChanged: (Boolean) -> Unit
) {
    RenderSwitcher(
        modifier = Modifier.padding(horizontal = 20.dp),
        title = "[ADB] ${stringResource(R.string.stop_dim_interaction_on_boot_title)}",
        subtitle = stringResource(R.string.stop_dim_interaction_on_boot_desc),
        enable = enableAdbHelper,
        value = adbDimAutoStop,
        groupDivider = false,
        onChange = { onAdbDimAutoStopChanged(it) }
    )
}
