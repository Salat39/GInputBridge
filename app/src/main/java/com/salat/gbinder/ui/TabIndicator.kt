package com.salat.gbinder.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun TabIndicator(currentTabPosition: TabPosition, tabsAccent: Color) {
    Box(
        modifier = Modifier
            .tabIndicatorGraphicOffset(currentTabPosition, 4)
            .padding(horizontal = 28.dp)
            .background(
                color = tabsAccent,
                shape = remember { RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp) }
            )
    )
}

@Composable
fun MaterialTabIndicator(currentTabPosition: androidx.compose.material3.TabPosition, tabsAccent: Color) {
    Box(
        modifier = Modifier
            .tabIndicatorOffset(currentTabPosition)
            .height(4.dp)
            .padding(horizontal = 28.dp)
            .background(
                color = tabsAccent,
                shape = remember { RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp) }
            )
    )
}
