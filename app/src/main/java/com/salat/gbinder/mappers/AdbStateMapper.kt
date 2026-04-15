package com.salat.gbinder.mappers

import com.salat.gbinder.adb.data.entity.AdbConnectionState
import com.salat.gbinder.entity.DisplayAdbState

fun AdbConnectionState.toDisplayAdbState(): DisplayAdbState = when (this) {
    AdbConnectionState.Connected -> DisplayAdbState.Connected
    AdbConnectionState.Disconnected -> DisplayAdbState.Disconnected
    AdbConnectionState.Connecting -> DisplayAdbState.Connecting
    is AdbConnectionState.Error -> DisplayAdbState.Error(message = message)
}
