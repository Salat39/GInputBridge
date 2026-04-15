package com.salat.gbinder.entity

import androidx.compose.runtime.Immutable

@Immutable
sealed interface DisplayAdbState {
    data object Connected : DisplayAdbState
    data object Disconnected : DisplayAdbState
    data object Connecting : DisplayAdbState
    data class Error(val message: String) : DisplayAdbState
}
