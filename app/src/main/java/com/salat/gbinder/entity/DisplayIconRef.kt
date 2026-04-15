package com.salat.gbinder.entity

import androidx.compose.runtime.Immutable

@Immutable
data class DisplayIconRef(
    val packageName: String,
    val resId: Int,
    val densityDpi: Int,
    val versionCode: Long
)
