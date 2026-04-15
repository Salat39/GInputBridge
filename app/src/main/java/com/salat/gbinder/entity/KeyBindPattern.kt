package com.salat.gbinder.entity

import androidx.compose.runtime.Immutable

@Immutable
sealed class KeyBindPattern {
    data class ShortClick(val keyCode: Int) : KeyBindPattern()
    data class LongPress(val keyCode: Int) : KeyBindPattern()
    data class MultiLong(val keyCodes: List<Int>) : KeyBindPattern()
    data class DoubleClick(val keyCode: Int) : KeyBindPattern()
}
