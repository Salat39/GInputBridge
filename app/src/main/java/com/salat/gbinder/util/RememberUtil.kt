package com.salat.gbinder.util

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.platform.LocalConfiguration
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter

@Composable
fun rememberIsLandscape(): Boolean {
    val configuration = LocalConfiguration.current

    return remember(configuration.orientation) {
        configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
    }
}

@Composable
fun rememberTimeLockedBoolean(delay: Long = 1000L): MutableState<Boolean> {
    val lock = remember { mutableStateOf(false) }
    LaunchedEffect(lock) {
        snapshotFlow { lock.value }
            .distinctUntilChanged()
            .filter { it }
            .collect { _ ->
                delay(delay)
                lock.value = false
            }
    }
    return lock
}
