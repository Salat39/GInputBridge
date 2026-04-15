package com.salat.gbinder.entity

import androidx.compose.runtime.Immutable

@Immutable
sealed class DraggableLampItem {
    data class LampMode(
        val index: Int,
        val item: DisplayLampMode,
        val showPos: Boolean
    ) : DraggableLampItem()

    object Divider : DraggableLampItem()
}
