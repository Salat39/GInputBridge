package com.salat.gbinder.entity

import androidx.compose.runtime.Immutable

@Immutable
sealed class DraggableDMItem {
    data class DriveMode(
        val index: Int,
        val item: DisplayDriveMode,
        val showPos: Boolean
    ) : DraggableDMItem()

    object Divider : DraggableDMItem()
}
