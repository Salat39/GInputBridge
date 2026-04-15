package com.salat.gbinder.entity.themes

import kotlinx.serialization.Serializable

@Serializable
data class BannerData(
    val elementDesc: String? = null,
    val elementName: String? = null,
    val elementType: Int = 0,
    val goodsId: String? = null,
    val iconUrl: String? = null,
    val imgUrl: String? = null,
    val isAssetsResources: Boolean = false,
    val isHomeShow: Int = 0,
    val mediaType: Int = 0,
    val mediaUrl: String? = null,
    val resType: Int = 0
)
