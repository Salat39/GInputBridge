package com.salat.gbinder.entity.themes

import kotlinx.serialization.Serializable

@Serializable
data class PreviewResouirce(
    val previewName: String? = null,
    val previewType: Int = 0,
    val previewUrl: String? = null,
    val screenId: Int = 0,
    val screenMode: Int = 0
)
