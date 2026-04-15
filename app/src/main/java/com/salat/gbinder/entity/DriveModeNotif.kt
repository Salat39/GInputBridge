package com.salat.gbinder.entity

import androidx.annotation.RawRes
import androidx.compose.runtime.Immutable

@Immutable
data class DriveModeNotif(
    val id: Int,
    @RawRes val res: Int,
    val name: String,
    val isSelected: Boolean = false
)
