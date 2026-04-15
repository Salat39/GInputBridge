package com.salat.gbinder.features.launcher

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.salat.gbinder.R
import com.salat.gbinder.entity.LauncherTabs
import com.salat.gbinder.ui.MaterialTabIndicator
import com.salat.gbinder.ui.theme.AppTheme
import kotlinx.coroutines.launch

@Composable
fun RowScope.RenderLauncherMainToolbar(
    isLock: Boolean,
    isShort: Boolean,
    pagerState: PagerState,
    onAddClick: (Offset) -> Unit,
    onToggleLock: () -> Unit,
    onSettingsClick: () -> Unit,
    onCloseClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .weight(1f)
            .fillMaxHeight(),
    ) {
        val scope = rememberCoroutineScope()
        val selectedIndex = remember { derivedStateOf { pagerState.currentPage } }
        val tabsAccent = AppTheme.colors.contentAccent
        val tabsPassive = AppTheme.colors.contentPrimary.copy(.75f)

        ScrollableTabRow(
            selectedTabIndex = selectedIndex.value,
            modifier = Modifier
                .fillMaxHeight()
                .wrapContentWidth(),
            containerColor = Color.Transparent,
            contentColor = tabsAccent,
            edgePadding = 26.dp,
            indicator = { tabPositions ->
                val currentTabPosition = tabPositions[selectedIndex.value]
                MaterialTabIndicator(
                    currentTabPosition = currentTabPosition,
                    tabsAccent = tabsAccent
                )
            },
            divider = {},
        ) {
            LauncherTabs.entries.forEachIndexed { index, profileTabs ->
                val isSelected = selectedIndex.value == index
                val textColor = if (isSelected) tabsAccent else tabsPassive

                Tab(
                    selected = selectedIndex.value == index,
                    modifier = Modifier.requiredHeight(LAUNCHER_TOOLBAR_HEIGHT.dp),
                    onClick = {
                        pagerState.currentPage
                        scope.launch { pagerState.animateScrollToPage(profileTabs.ordinal) }
                    },
                    text = {
                        Text(
                            modifier = Modifier.padding(horizontal = 20.dp),
                            text = stringResource(
                                when (profileTabs) {
                                    LauncherTabs.MyApss -> if (isShort) {
                                        R.string.my
                                    } else R.string.my_apps

                                    LauncherTabs.AllApps -> profileTabs.title
                                }
                            ),
                            color = textColor,
                            style = AppTheme.typography.overlayLauncherSection
                        )
                    }
                )
            }
        }
    }

    AnimatedVisibility(
        visible = pagerState.currentPage == 0,
        enter = fadeIn(tween(120)),
        exit = fadeOut(tween(120)),
    ) {
        Row(
            modifier = Modifier.fillMaxHeight(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                modifier = Modifier
                    .size(LAUNCHER_TOOLBAR_HEIGHT.dp),
                onClick = onToggleLock
            ) {
                Icon(
                    painter = painterResource(if (isLock) R.drawable.ic_lock else R.drawable.ic_unlock),
                    modifier = Modifier
                        .size(20.dp),
                    tint = if (isLock) AppTheme.colors.contentPrimary else AppTheme.colors.greenAccent,
                    contentDescription = "unlock"
                )
            }

            Spacer(Modifier.width(14.dp))

            var rootOffset by remember { mutableStateOf(Offset.Zero) }
            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(6.dp))
                    .background(AppTheme.colors.contentAccent)
                    .onGloballyPositioned { coordinates ->
                        rootOffset = Offset(
                            coordinates.positionInRoot().x,
                            coordinates.positionInRoot().y
                        )
                    }
                    .clickable { onAddClick(rootOffset) }
                    .padding(start = 8.dp, top = 8.dp, end = 12.dp, bottom = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier
                        .size(24.dp),
                    imageVector = Icons.Filled.Add,
                    tint = Color.White,
                    contentDescription = "add"
                )

                Spacer(Modifier.width(6.dp))

                Text(
                    modifier = Modifier,
                    text = stringResource(R.string.add),
                    color = Color.White,
                    style = AppTheme.typography.overlayLauncherSettingsGroup.copy(
                        fontSize = 18.sp
                    )
                )
            }
        }
    }

    Spacer(Modifier.width(14.dp))
    IconButton(
        modifier = Modifier
            .size(LAUNCHER_TOOLBAR_HEIGHT.dp),
        onClick = onSettingsClick
    ) {
        Icon(
            modifier = Modifier
                .size(24.dp),
            imageVector = Icons.Filled.Settings,
            tint = AppTheme.colors.contentPrimary,
            contentDescription = "settings"
        )
    }
    IconButton(
        modifier = Modifier
            .size(LAUNCHER_TOOLBAR_HEIGHT.dp)
            .padding(start = 2.dp),
        onClick = onCloseClick
    ) {
        Icon(
            modifier = Modifier
                .size(26.dp),
            imageVector = Icons.Filled.Close,
            tint = AppTheme.colors.contentPrimary,
            contentDescription = "close"
        )
    }
    Spacer(Modifier.width(8.dp))
}
