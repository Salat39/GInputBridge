package com.salat.gbinder.remoteconfig.domain.entity

data class AppUpdateInfo(
    val version: String,
    val size: String,
    val text: String,
    val code: Int,
    val downloadUrl: String,
    val infoUrl: String,
    val mandatory: Boolean
)
