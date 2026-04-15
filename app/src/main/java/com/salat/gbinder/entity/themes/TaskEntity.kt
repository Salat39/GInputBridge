package com.salat.gbinder.entity.themes

import kotlinx.serialization.Serializable

@Serializable
data class TaskEntity(
    val fid: Long? = null,
    var downloadName: String? = null,
    var downLoadUrl: String? = null,
    var downLoadPath: String? = null,
    var downloadId: String? = null,
    var taskStatus: Int = 0,
    var downLoadProgress: Float = 0f,
    var installProgress: Float = 0f,
    var errorCode: Int = 0
)
