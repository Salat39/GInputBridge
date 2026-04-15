package com.salat.gbinder.entity.themes

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CardGoodsVoData(
    val id: Long? = null,
    val goodsName: String? = null,
    val goodsType: Int = 0,
    val goodsIcon: String? = null,
    val type: Int? = null,
    val name: String? = null,
    val url: String? = null,
    val goodsPrice: String? = null,
    val discountStartTime: String? = null,
    val discountEndTime: String? = null,
    val goodsId: String? = null,
    val tags: List<String>? = null,
    val author: String? = null,
    val goodsSize: String? = null,
    val purchaseStatus: Boolean = false,
    val isApplied: Boolean = false,
    val isAppliedPsd: Boolean = false,
    val isAppliedMeter: Boolean = false,
    val isCurrent: Boolean = false,
    val isCurrentPsd: Boolean = false,
    val isCurrentMeter: Boolean = false,
    @SerialName("_\$DownloadStatus80")
    val downloadStatus80: Boolean? = null,
    val mediaList: List<BannerData>? = null,
    val previewList: List<PreviewResouirce>? = null,
    val fileUrl: String? = null,
    val fid: Long? = null,
    val taskEntity: TaskEntity? = null,
    val isAssetsResources: Boolean = false,
    val assetNumber: Int = 0,
    val dueDate: String? = null,
    val dynamicInfo: Int? = null,
    val goodsDesc: String? = null,
    val icon: String? = null,
    val itemType: Int = 0,
    val categoriesName: String? = null
)
