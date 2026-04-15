package com.salat.gbinder.entity

data class IconRef(
    val packageName: String,
    val resId: Int,
    val densityDpi: Int,
    val versionCode: Long
)
