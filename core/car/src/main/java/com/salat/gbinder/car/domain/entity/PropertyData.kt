package com.salat.gbinder.car.domain.entity

data class PropertyData(
    val alias: String,
    val type: Int,
    val key: String,
    val value: Int,
    val description: String? = null,
    val possibleValues: Map<String, Int>
)
