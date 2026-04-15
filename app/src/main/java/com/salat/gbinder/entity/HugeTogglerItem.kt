package com.salat.gbinder.entity

import androidx.compose.runtime.Immutable

@Immutable
data class HugeTogglerItem(
    val text: String,
    val subtitle: String? = null
)
