package com.salat.gbinder.entity

import androidx.compose.runtime.Immutable

@Immutable
sealed class DraggableAudioSourceItem {
    data class Source(
        val index: Int,
        val item: DisplayAudioSource,
        val showPos: Boolean
    ) : DraggableAudioSourceItem()

    object Divider : DraggableAudioSourceItem()
}
