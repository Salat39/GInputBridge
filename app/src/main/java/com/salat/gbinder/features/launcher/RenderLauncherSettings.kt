package com.salat.gbinder.features.launcher

import android.util.DisplayMetrics
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.salat.gbinder.BuildConfig
import com.salat.gbinder.R
import com.salat.gbinder.components.toast
import com.salat.gbinder.datastore.LauncherPrefs
import com.salat.gbinder.datastore.LauncherStorageRepository
import com.salat.gbinder.entity.DisplayLauncherApp
import com.salat.gbinder.entity.DisplayLauncherConfig
import com.salat.gbinder.entity.DisplayLauncherItemType
import com.salat.gbinder.entity.SegmentTogglerItem
import com.salat.gbinder.ui.RangeValueSlider
import com.salat.gbinder.ui.RenderSwitcher
import com.salat.gbinder.ui.SegmentToggler
import com.salat.gbinder.ui.ValueSlider
import com.salat.gbinder.ui.theme.AppTheme
import kotlinx.coroutines.launch

@Composable
fun ColumnScope.RenderLauncherSettings(
    items: List<DisplayLauncherApp>,
    config: DisplayLauncherConfig,
    storage: LauncherStorageRepository
) = Column(
    modifier = Modifier
        .fillMaxWidth()
        .weight(1f)
        .verticalScroll(rememberScrollState())
) {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    Spacer(Modifier.height(36.dp))

    // Window settings section
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 42.dp),
        text = stringResource(R.string.appearance),
        style = AppTheme.typography.overlayLauncherSettingsGroup,
        color = AppTheme.colors.contentAccent
    )

    Spacer(Modifier.height(24.dp))

    Box(
        Modifier
            .padding(horizontal = 42.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(AppTheme.colors.lampSelectorDivider)
            .padding(2.dp)
    ) {
        val displayModeTabTypes = remember {
            listOf(
                SegmentTogglerItem(text = R.string.window),
                SegmentTogglerItem(text = R.string.fullscreen),
            )
        }
        SegmentToggler(
            modifier = Modifier.fillMaxWidth(),
            selectedIndex = if (config.windowMode) 0 else 1,
            fontSize = 17,
            items = displayModeTabTypes,
        ) {
            scope.launch {
                storage.dataStore.saveValue(
                    LauncherPrefs.LAUNCHER_WINDOW_MODE, when (it) {
                        0 -> true
                        else -> false
                    }
                )
            }
        }
    }

    // Only window settings
    AnimatedVisibility(
        visible = config.windowMode,
        enter = expandVertically(
            expandFrom = Alignment.Top,
            animationSpec = tween(300)
        ),
        exit = shrinkVertically(
            shrinkTowards = Alignment.Top,
            animationSpec = tween(300)
        ),
    ) {
        Column {
            Spacer(Modifier.height(26.dp))

            // Window frame
            RenderSwitcher(
                modifier = Modifier.padding(horizontal = 18.dp),
                title = stringResource(R.string.window_border),
                value = config.windowShowFrame,
                enable = true,
                groupDivider = false,
                titleStyle = AppTheme.typography.overlayLauncherSettingsTitle,
                onChange = {
                    scope.launch {
                        storage.dataStore.saveValue(LauncherPrefs.LAUNCHER_WINDOW_SHOW_FRAME, it)
                    }
                }
            )

            Spacer(Modifier.height(26.dp))

            // Horizontal padding
            Text(
                modifier = Modifier.padding(horizontal = 42.dp),
                text = buildString {
                    append(stringResource(R.string.horizontal_padding))
                    append(":  ")
                    append(config.windowHorizontalSpace)
                },
                style = AppTheme.typography.overlayLauncherSettingsTitle,
                color = AppTheme.colors.contentPrimary
            )
            ValueSlider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 36.dp),
                value = config.windowHorizontalSpace,
                valueRange = 0..255,
                onValueChange = { newValue ->
                    scope.launch {
                        storage.dataStore.saveValue(
                            LauncherPrefs.LAUNCHER_WINDOW_HORIZONTAL_SPACE,
                            newValue
                        )
                    }
                },
                enabled = true,
                defaultMark = DEFAULT_LAUNCHER_WINDOW_HORIZONTAL_SPACE,
                step = 1
            )

            Spacer(Modifier.height(26.dp))

            // Vertical padding
            Text(
                modifier = Modifier.padding(horizontal = 42.dp),
                text = buildString {
                    append(stringResource(R.string.vertical_padding))
                    append(":  ")
                    append(config.windowVerticalSpace)
                },
                style = AppTheme.typography.overlayLauncherSettingsTitle,
                color = AppTheme.colors.contentPrimary
            )
            ValueSlider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 36.dp),
                value = config.windowVerticalSpace,
                valueRange = 0..(if (BuildConfig.DEBUG) 420 else 645),
                onValueChange = { newValue ->
                    scope.launch {
                        storage.dataStore.saveValue(
                            LauncherPrefs.LAUNCHER_WINDOW_VERTICAL_SPACE,
                            newValue
                        )
                    }
                },
                enabled = true,
                defaultMark = DEFAULT_LAUNCHER_WINDOW_VERTICAL_SPACE,
                step = 1
            )
        }
    }

    Spacer(Modifier.height(26.dp))

    // Alpha
    Text(
        modifier = Modifier.padding(horizontal = 42.dp),
        text = buildString {
            append(stringResource(R.string.window_transparency))
            append(":  ")
            append(toPercent(config.windowAlpha))
            append("%")
        },
        style = AppTheme.typography.overlayLauncherSettingsTitle,
        color = AppTheme.colors.contentPrimary
    )
    ValueSlider(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 36.dp),
        value = config.windowAlpha,
        valueRange = .6f..1f,
        onValueChange = { newValue ->
            scope.launch {
                storage.dataStore.saveValue(LauncherPrefs.LAUNCHER_WINDOW_ALPHA, newValue)
            }
        },
        enabled = true,
        defaultMark = DEFAULT_LAUNCHER_WINDOW_ALPHA,
        step = .01f
    )

    Spacer(Modifier.height(18.dp))

    // Theme
    Text(
        modifier = Modifier.padding(horizontal = 42.dp),
        text = stringResource(R.string.theme),
        style = AppTheme.typography.overlayLauncherSettingsTitle,
        color = AppTheme.colors.contentPrimary
    )

    Spacer(Modifier.height(12.dp))

    Box(
        Modifier
            .padding(horizontal = 42.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(AppTheme.colors.lampSelectorDivider)
            .padding(2.dp)
    ) {
        val iconQualityTypes = remember {
            listOf(
                SegmentTogglerItem(text = R.string.dark_theme),
                SegmentTogglerItem(text = R.string.light_theme),
                SegmentTogglerItem(text = R.string.auto)
            )
        }
        SegmentToggler(
            modifier = Modifier.fillMaxWidth(),
            selectedIndex = when {
                config.autoLightTheme -> 2
                config.lightTheme -> 1
                else -> 0
            },
            fontSize = 15,
            items = iconQualityTypes,
        ) { index ->
            scope.launch {
                storage.dataStore.saveValue(LauncherPrefs.LAUNCHER_AUTO_LIGHT_THEME, index == 2)
                storage.dataStore.saveValue(LauncherPrefs.LAUNCHER_LIGHT_THEME, index == 1)
            }
        }
    }

    Spacer(Modifier.height(18.dp))

    // Light theme time range
    AnimatedVisibility(
        visible = config.autoLightTheme,
        enter = expandVertically(
            expandFrom = Alignment.Top,
            animationSpec = tween(300)
        ),
        exit = shrinkVertically(
            shrinkTowards = Alignment.Top,
            animationSpec = tween(300)
        ),
    ) {
        Column {
            Spacer(Modifier.height(8.dp))

            val displayEndLightThemeTime = if (config.autoLightThemeEnd == 24) {
                "00"
            } else config.autoLightThemeEnd
            Text(
                modifier = Modifier.padding(horizontal = 42.dp),
                text = context.getString(
                    R.string.light_theme_schedule,
                    "${config.autoLightThemeStart}:00",
                    "$displayEndLightThemeTime:00"
                ),
                style = AppTheme.typography.overlayLauncherSettingsTitle,
                color = AppTheme.colors.contentPrimary
            )

            RangeValueSlider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 36.dp),
                startValue = config.autoLightThemeStart,
                endValue = config.autoLightThemeEnd,
                valueRange = 3..24,
                step = 1,
                defaultStartMark = DEFAULT_AUTO_LIGHT_THEME_START,
                defaultEndMark = DEFAULT_AUTO_LIGHT_THEME_END,
                onValueChange = { newStart, newEnd ->
                    scope.launch {
                        storage.dataStore.saveValue(
                            LauncherPrefs.LAUNCHER_AUTO_LIGHT_THEME_START,
                            newStart
                        )
                        storage.dataStore.saveValue(
                            LauncherPrefs.LAUNCHER_AUTO_LIGHT_THEME_END,
                            newEnd
                        )
                    }
                }
            )
        }
    }

    Spacer(Modifier.height(18.dp))

    // Icon settings title
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 42.dp),
        text = stringResource(R.string.icons_and_grid),
        style = AppTheme.typography.overlayLauncherSettingsGroup,
        color = AppTheme.colors.contentAccent
    )

    Spacer(Modifier.height(24.dp))

    // Icon quality
    Text(
        modifier = Modifier.padding(horizontal = 42.dp),
        text = stringResource(R.string.icon_quality),
        style = AppTheme.typography.overlayLauncherSettingsTitle,
        color = AppTheme.colors.contentPrimary
    )

    Spacer(Modifier.height(12.dp))

    Box(
        Modifier
            .padding(horizontal = 42.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(AppTheme.colors.lampSelectorDivider)
            .padding(2.dp)
    ) {
        val iconQualityTypes = remember {
            listOf(
                SegmentTogglerItem(text = R.string.dpi_medium),
                SegmentTogglerItem(text = R.string.dpi_tv),
                SegmentTogglerItem(text = R.string.dpi_high),
                SegmentTogglerItem(text = R.string.dpi_xhigh),
                SegmentTogglerItem(text = R.string.dpi_xxhigh),
                SegmentTogglerItem(text = R.string.dpi_xxxhigh)
            )
        }
        SegmentToggler(
            modifier = Modifier.fillMaxWidth(),
            selectedIndex = when (config.iconQuality) {
                DisplayMetrics.DENSITY_TV -> 1
                DisplayMetrics.DENSITY_HIGH -> 2
                DisplayMetrics.DENSITY_XHIGH -> 3
                DisplayMetrics.DENSITY_XXHIGH -> 4
                DisplayMetrics.DENSITY_XXXHIGH -> 5
                else -> 0
            },
            fontSize = 15,
            items = iconQualityTypes,
        ) {
            scope.launch {
                storage.dataStore.saveValue(
                    LauncherPrefs.LAUNCHER_ICON_QUALITY,
                    when (it) {
                        1 -> DisplayMetrics.DENSITY_TV
                        2 -> DisplayMetrics.DENSITY_HIGH
                        3 -> DisplayMetrics.DENSITY_XHIGH
                        4 -> DisplayMetrics.DENSITY_XXHIGH
                        5 -> DisplayMetrics.DENSITY_XXXHIGH
                        else -> DisplayMetrics.DENSITY_MEDIUM
                    }
                )
            }
        }
    }

    Spacer(Modifier.height(12.dp))

    Text(
        modifier = Modifier.padding(horizontal = 42.dp),
        text = stringResource(R.string.icon_quality_performance_desc),
        style = AppTheme.typography.overlayLauncherSettingsSubtitle,
        color = AppTheme.colors.contentPrimary.copy(.7f)
    )

    Spacer(Modifier.height(32.dp))

    /**
     * Icons settings
     */

    Text(
        modifier = Modifier.padding(horizontal = 42.dp),
        text = buildString {
            append(stringResource(R.string.icon_view_preview))
            append(":")
        },
        style = AppTheme.typography.overlayLauncherSettingsTitle,
        color = AppTheme.colors.contentPrimary
    )

    Spacer(Modifier.height(12.dp))

    // Grid preview
    val previewGridState: LazyGridState = rememberLazyGridState()
    val previewIconTitleHeight = if (config.iconTextEnable) {
        config.iconTextPadding + config.iconTextSize
    } else 0
    val previewExtraHeight = 10
    val previewHeight =
        config.iconSize + config.iconOutSpace + previewIconTitleHeight + previewExtraHeight
    LazyVerticalGrid(
        state = previewGridState,
        columns = GridCells.Adaptive(minSize = config.iconSize.dp),
        userScrollEnabled = false,
        modifier = Modifier
            .fillMaxWidth()
            .height(previewHeight.dp)
            .background(AppTheme.colors.launcherSurface1),
        contentPadding = PaddingValues(config.iconOutSpace.dp),
        verticalArrangement = Arrangement.spacedBy(config.iconInnerSpace.dp),
        horizontalArrangement = Arrangement.spacedBy(config.iconInnerSpace.dp),
    ) {
        itemsIndexed(
            items = items,
            key = { index, item -> item.id }
        ) { index, app ->
            RenderLauncherAllAppCell(
                app = app,
                cellSize = config.iconSize,
                enableText = config.iconTextEnable,
                iconRound = config.iconRound,
                textSize = config.iconTextSize,
                textPadding = config.iconTextPadding,
                enableShortcuts = config.enableShortcuts && (index == 0 || index == 1),
                shortcutSize = config.shortcutSize,
                shortcutType = if (index == 0) {
                    DisplayLauncherItemType.ACTIVITY
                } else DisplayLauncherItemType.MACRO,
                enableMultiline = false,
                sizeSensitive = false,
                onClick = {
                    context.toast(context.getString(R.string.icon_view_preview))
                },
                onLongClick = { }
            )
        }
    }
    Spacer(
        Modifier
            .fillMaxWidth()
            .height((config.iconOutSpace - previewExtraHeight).dp)
            .background(AppTheme.colors.launcherSurface1)
    )

    Spacer(Modifier.height(36.dp))

    // Out space
    Text(
        modifier = Modifier.padding(horizontal = 42.dp),
        text = buildString {
            append(stringResource(R.string.window_edge_padding))
            append(":  ")
            append(config.iconOutSpace)
        },
        style = AppTheme.typography.overlayLauncherSettingsTitle,
        color = AppTheme.colors.contentPrimary
    )
    ValueSlider(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 36.dp),
        value = config.iconOutSpace,
        valueRange = 10..150,
        onValueChange = { newValue ->
            scope.launch {
                storage.dataStore.saveValue(LauncherPrefs.LAUNCHER_ICON_OUT_SPACE, newValue)
            }
        },
        enabled = true,
        defaultMark = DEFAULT_LAUNCHER_ICON_OUT_SPACE,
        step = 1
    )

    Spacer(Modifier.height(26.dp))

    // Inner space
    Text(
        modifier = Modifier.padding(horizontal = 42.dp),
        text = buildString {
            append(stringResource(R.string.icon_spacing))
            append(":  ")
            append(config.iconInnerSpace)
        },
        style = AppTheme.typography.overlayLauncherSettingsTitle,
        color = AppTheme.colors.contentPrimary
    )
    ValueSlider(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 36.dp),
        value = config.iconInnerSpace,
        valueRange = 10..150,
        onValueChange = { newValue ->
            scope.launch {
                storage.dataStore.saveValue(LauncherPrefs.LAUNCHER_ICON_INNER_SPACE, newValue)
            }
        },
        enabled = true,
        defaultMark = DEFAULT_LAUNCHER_ICON_INNER_SPACE,
        step = 1
    )

    Spacer(Modifier.height(26.dp))

    // Icon size
    Text(
        modifier = Modifier.padding(horizontal = 42.dp),
        text = buildString {
            append(stringResource(R.string.icon_size))
            append(":  ")
            append(config.iconSize)
        },
        style = AppTheme.typography.overlayLauncherSettingsTitle,
        color = AppTheme.colors.contentPrimary
    )
    ValueSlider(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 36.dp),
        value = config.iconSize,
        valueRange = 36..250,
        onValueChange = { newValue ->
            scope.launch {
                storage.dataStore.saveValue(LauncherPrefs.LAUNCHER_ICON_SIZE, newValue)
            }
        },
        enabled = true,
        defaultMark = DEFAULT_LAUNCHER_ICON_SIZE,
        step = 1
    )

    Spacer(Modifier.height(26.dp))

    // Icon round
    Text(
        modifier = Modifier.padding(horizontal = 42.dp),
        text = buildString {
            append(stringResource(R.string.icon_corner_radius))
            append(":  ")
            append(config.iconRound)
        },
        style = AppTheme.typography.overlayLauncherSettingsTitle,
        color = AppTheme.colors.contentPrimary
    )
    ValueSlider(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 36.dp),
        value = config.iconRound,
        valueRange = 0..125,
        onValueChange = { newValue ->
            scope.launch {
                storage.dataStore.saveValue(LauncherPrefs.LAUNCHER_ICON_ROUND, newValue)
            }
        },
        enabled = true,
        defaultMark = DEFAULT_LAUNCHER_ICON_ROUND,
        step = 1
    )

    Spacer(Modifier.height(14.dp))

    // Enable icon text
    RenderSwitcher(
        modifier = Modifier.padding(horizontal = 18.dp),
        title = stringResource(R.string.show_app_names),
        value = config.iconTextEnable,
        enable = true,
        groupDivider = false,
        titleStyle = AppTheme.typography.overlayLauncherSettingsTitle,
        onChange = {
            scope.launch {
                storage.dataStore.saveValue(LauncherPrefs.LAUNCHER_ICON_TEXT_ENABLE, it)
            }
        }
    )

    AnimatedVisibility(
        visible = config.iconTextEnable,
        enter = expandVertically(
            expandFrom = Alignment.Top,
            animationSpec = tween(300)
        ),
        exit = shrinkVertically(
            shrinkTowards = Alignment.Top,
            animationSpec = tween(300)
        ),
    ) {
        Column {

            Spacer(Modifier.height(14.dp))

            // Enable icon text multiline
            RenderSwitcher(
                modifier = Modifier.padding(horizontal = 18.dp),
                title = stringResource(R.string.allow_app_name_wrapping),
                value = config.iconTextMultiline,
                enable = true,
                groupDivider = false,
                titleStyle = AppTheme.typography.overlayLauncherSettingsTitle,
                onChange = {
                    scope.launch {
                        storage.dataStore.saveValue(LauncherPrefs.LAUNCHER_ICON_TEXT_MULTILINE, it)
                    }
                }
            )

            Spacer(Modifier.height(26.dp))

            // Icon text size
            Text(
                modifier = Modifier.padding(horizontal = 42.dp),
                text = buildString {
                    append(stringResource(R.string.app_name_text_size))
                    append(":  ")
                    append(config.iconTextSize)
                },
                style = AppTheme.typography.overlayLauncherSettingsTitle,
                color = AppTheme.colors.contentPrimary
            )
            ValueSlider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 36.dp),
                value = config.iconTextSize,
                valueRange = 9..36,
                onValueChange = { newValue ->
                    scope.launch {
                        storage.dataStore.saveValue(LauncherPrefs.LAUNCHER_ICON_TEXT_SIZE, newValue)
                    }
                },
                enabled = true,
                defaultMark = DEFAULT_LAUNCHER_ICON_TEXT_SIZE,
                step = 1
            )

            Spacer(Modifier.height(26.dp))

            // Icon text padding
            Text(
                modifier = Modifier.padding(horizontal = 42.dp),
                text = buildString {
                    append(stringResource(R.string.icon_name_spacing))
                    append(":  ")
                    append(config.iconTextPadding)
                },
                style = AppTheme.typography.overlayLauncherSettingsTitle,
                color = AppTheme.colors.contentPrimary
            )
            ValueSlider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 36.dp),
                value = config.iconTextPadding,
                valueRange = 2..42,
                onValueChange = { newValue ->
                    scope.launch {
                        storage.dataStore.saveValue(
                            LauncherPrefs.LAUNCHER_ICON_TEXT_PADDING,
                            newValue
                        )
                    }
                },
                enabled = true,
                defaultMark = DEFAULT_LAUNCHER_ICON_TEXT_PADDING,
                step = 1
            )
        }
    }

    Spacer(Modifier.height(14.dp))

    // Enable icon shortcuts
    RenderSwitcher(
        modifier = Modifier.padding(horizontal = 18.dp),
        title = stringResource(R.string.show_shortcut_type_icons),
        value = config.enableShortcuts,
        enable = true,
        groupDivider = false,
        titleStyle = AppTheme.typography.overlayLauncherSettingsTitle,
        onChange = {
            scope.launch {
                storage.dataStore.saveValue(LauncherPrefs.LAUNCHER_ENABLE_SHORTCUTS, it)
            }
        }
    )

    Spacer(Modifier.height(26.dp))

    // Shortcut size
    Text(
        modifier = Modifier.padding(horizontal = 42.dp),
        text = buildString {
            append(stringResource(R.string.shortcut_type_icon_size))
            append(":  ")
            append(config.shortcutSize)
        },
        style = AppTheme.typography.overlayLauncherSettingsTitle,
        color = AppTheme.colors.contentPrimary
    )
    ValueSlider(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 36.dp),
        value = config.shortcutSize,
        valueRange = 22..64,
        onValueChange = { newValue ->
            scope.launch {
                storage.dataStore.saveValue(LauncherPrefs.LAUNCHER_SHORTCUT_SIZE, newValue)
            }
        },
        enabled = true,
        defaultMark = DEFAULT_LAUNCHER_SHORTCUT_SIZE,
        step = 1
    )

    Spacer(Modifier.height(32.dp))

    // Group divider preview
    Text(
        modifier = Modifier.padding(horizontal = 42.dp),
        text = buildString {
            append(stringResource(R.string.divider_current_view))
            append(":")
        },
        style = AppTheme.typography.overlayLauncherSettingsTitle,
        color = AppTheme.colors.contentPrimary
    )

    Spacer(Modifier.height(12.dp))

    Box(
        modifier = Modifier
            .background(AppTheme.colors.launcherSurface1)
            .padding(horizontal = config.iconOutSpace.dp, vertical = config.iconInnerSpace.dp)
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(R.string.divider_title_preview),
            color = AppTheme.colors.contentPrimary,
            style = AppTheme.typography.overlayLauncherSection.copy(
                fontSize = config.dividerTextSize.sp,
                lineHeight = config.dividerTextSize.sp,
                fontWeight = if (config.dividerTextBold) FontWeight.Bold else FontWeight.Medium
            )
        )
    }

    Spacer(Modifier.height(36.dp))

    // Group divider text size
    Text(
        modifier = Modifier.padding(horizontal = 42.dp),
        text = buildString {
            append(stringResource(R.string.divider_text_size))
            append(":  ")
            append(config.dividerTextSize)
        },
        style = AppTheme.typography.overlayLauncherSettingsTitle,
        color = AppTheme.colors.contentPrimary
    )
    ValueSlider(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 36.dp),
        value = config.dividerTextSize,
        valueRange = 18..56,
        onValueChange = { newValue ->
            scope.launch {
                storage.dataStore.saveValue(LauncherPrefs.LAUNCHER_DIVIDER_SIZE, newValue)
            }
        },
        enabled = true,
        defaultMark = DEFAULT_LAUNCHER_DIVIDER_SIZE,
        step = 1
    )

    Spacer(Modifier.height(14.dp))

    // Group divider text bold
    RenderSwitcher(
        modifier = Modifier.padding(horizontal = 18.dp),
        title = stringResource(R.string.bold_font),
        value = config.dividerTextBold,
        enable = true,
        groupDivider = false,
        titleStyle = AppTheme.typography.overlayLauncherSettingsTitle,
        onChange = {
            scope.launch {
                storage.dataStore.saveValue(LauncherPrefs.LAUNCHER_DIVIDER_BOLD, it)
            }
        }
    )

    Spacer(Modifier.height(32.dp))

    // Other settings section
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 42.dp),
        text = stringResource(R.string.other),
        style = AppTheme.typography.overlayLauncherSettingsGroup,
        color = AppTheme.colors.contentAccent
    )

    Spacer(Modifier.height(24.dp))

    // Default tab
    Text(
        modifier = Modifier.padding(horizontal = 42.dp),
        text = stringResource(R.string.default_tab),
        style = AppTheme.typography.overlayLauncherSettingsTitle,
        color = AppTheme.colors.contentPrimary
    )

    Spacer(Modifier.height(12.dp))

    Box(
        Modifier
            .padding(horizontal = 42.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(AppTheme.colors.lampSelectorDivider)
            .padding(2.dp)
    ) {
        val defaultTabTypes = remember {
            listOf(
                SegmentTogglerItem(text = R.string.my_apps),
                SegmentTogglerItem(text = R.string.all),
            )
        }
        SegmentToggler(
            modifier = Modifier.fillMaxWidth(),
            selectedIndex = config.defaultTab,
            fontSize = 17,
            items = defaultTabTypes,
        ) {
            scope.launch { storage.dataStore.saveValue(LauncherPrefs.LAUNCHER_DEFAULT_TAB, it) }
        }
    }

    Spacer(Modifier.height(26.dp))

    // Recents enable
    RenderSwitcher(
        modifier = Modifier.padding(horizontal = 18.dp),
        title = stringResource(R.string.show_recent_apps),
        subtitle = stringResource(R.string.show_recent_apps_desc),
        value = config.recentsEnable,
        enable = true,
        groupDivider = false,
        subtitleColor = AppTheme.colors.contentPrimary.copy(.7f),
        titleStyle = AppTheme.typography.overlayLauncherSettingsTitle,
        subtitleStyle = AppTheme.typography.overlayLauncherSettingsSubtitle,
        onChange = {
            scope.launch {
                storage.dataStore.saveValue(LauncherPrefs.LAUNCHER_RECENTS_ENABLE, it)
            }
        }
    )

    Spacer(Modifier.height(64.dp))
}

/**
 * Converts a 0f..1f float into an integer percent 0..100.
 * Clamps out-of-range values; treats NaN/Infinity as 0 for safety.
 */
fun toPercent(value: Float): Int {
    val safe = if (value.isNaN() || value.isInfinite()) 0f else value.coerceIn(0f, 1f)
    // Use +0.5f and toInt() to round correctly without extra imports
    return (safe * 100f + 0.5f).toInt()
}
