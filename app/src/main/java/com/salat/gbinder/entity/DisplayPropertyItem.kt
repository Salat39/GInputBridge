package com.salat.gbinder.entity

import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.AnnotatedString

@Immutable
data class DisplayPropertyItem(
    val alias: AnnotatedString,
    val type: Int,
    val key: AnnotatedString,
    val value: Int,
    val description: AnnotatedString?,
    val possibleValues: Map<String, Int>
)
