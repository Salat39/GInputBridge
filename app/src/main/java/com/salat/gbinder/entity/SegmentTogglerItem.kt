package com.salat.gbinder.entity

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable

@Immutable
data class SegmentTogglerItem(
    @StringRes val text: Int?,
    @DrawableRes val icon: Int? = null,
)
