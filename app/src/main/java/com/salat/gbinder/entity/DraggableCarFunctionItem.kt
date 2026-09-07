package com.salat.gbinder.entity

import androidx.compose.runtime.Immutable

@Immutable
sealed class DraggableCarFunctionItem {
    data class Function(val item: CarFunction) : DraggableCarFunctionItem()
    object Divider : DraggableCarFunctionItem()
}
