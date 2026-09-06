package com.salat.gbinder.features.launcher

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.salat.gbinder.R
import com.salat.gbinder.entity.CarFunction
import com.salat.gbinder.entity.CarModel
import com.salat.gbinder.entity.DisplayLauncherConfig
import com.salat.gbinder.entity.DisplayLauncherItem
import com.salat.gbinder.entity.DisplayLauncherItemType
import com.salat.gbinder.ui.BaseButton
import com.salat.gbinder.ui.ProfileSwitch
import com.salat.gbinder.ui.theme.AppTheme

@Composable
fun ColumnScope.RenderLauncherAddCarFunctions(
    myApps: List<DisplayLauncherItem>,
    config: DisplayLauncherConfig,
    carModel: CarModel?,
    onSave: (List<DisplayLauncherItem>) -> Unit
) = Column(Modifier.fillMaxWidth().weight(1f)) {
    val context = LocalContext.current
    val offered = remember { CarFunction.availableForLauncher(carModel) }
    var selected by remember {
        mutableStateOf(
            myApps.filter { it.type == DisplayLauncherItemType.CAR_FUNCTION }
                .mapNotNull { CarFunction.fromValue(it.data) }
                .filter { it in offered }
                .toSet()
        )
    }

    fun saveAndExit() {
        val result = myApps.filter { item ->
            if (item.type != DisplayLauncherItemType.CAR_FUNCTION) return@filter true
            val function = CarFunction.fromValue(item.data)
            function !in offered || function in selected
        }.toMutableList()
        val existing = result.filter { it.type == DisplayLauncherItemType.CAR_FUNCTION }
            .mapNotNull { CarFunction.fromValue(it.data) }
            .toSet()
        offered.filter { it in selected && it !in existing }.forEach { function ->
            result.add(
                DisplayLauncherItem(
                    type = DisplayLauncherItemType.CAR_FUNCTION,
                    id = 0,
                    order = 0,
                    title = context.getString(function.titleRes),
                    iconRef = null,
                    customIcon = null,
                    packageName = "",
                    launchActivity = "",
                    data = function.name,
                    isCall = false,
                    isSplit = false,
                    isFrozen = false,
                    isSystem = false
                )
            )
        }
        onSave(result.assignIdsAndOrder())
    }

    LazyColumn(
        modifier = Modifier.fillMaxWidth().weight(1f),
        contentPadding = PaddingValues(vertical = 16.dp)
    ) {
        items(offered, key = { it.name }) { function ->
            Row(
                modifier = Modifier.fillMaxWidth()
                    .clickable {
                        selected = if (function in selected) selected - function else selected + function
                    }
                    .padding(vertical = 12.dp)
                    .padding(start = 36.dp, end = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier.size(46.dp).clip(RoundedCornerShape(12.dp))
                        .background(AppTheme.colors.launcherFunctionTile),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(function.iconRes),
                        contentDescription = null,
                        modifier = Modifier.size(26.dp),
                        tint = AppTheme.colors.launcherFunctionContent
                    )
                }
                Spacer(Modifier.width(16.dp))
                Column(Modifier.weight(1f)) {
                    Text(
                        text = stringResource(function.titleRes),
                        style = AppTheme.typography.overlayLauncherSettingsTitle,
                        color = AppTheme.colors.contentPrimary,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    function.descRes?.let { description ->
                        Text(
                            text = stringResource(description),
                            style = AppTheme.typography.overlayLauncherSettingsSubtitle,
                            color = AppTheme.colors.contentPrimary.copy(.5f)
                        )
                    }
                }
                Spacer(Modifier.width(32.dp))
                ProfileSwitch(
                    scale = .8f,
                    checked = function in selected,
                    enabled = true,
                    onCheckedChange = null
                )
                Spacer(Modifier.width(12.dp))
            }
        }
    }
    Spacer(Modifier.fillMaxWidth().height(1.dp).background(AppTheme.colors.launcherSurface1.copy(.5f)))
    Box(
        Modifier.fillMaxWidth().padding(36.dp)
            .then(if (!config.windowMode) Modifier.padding(bottom = 12.dp) else Modifier)
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
