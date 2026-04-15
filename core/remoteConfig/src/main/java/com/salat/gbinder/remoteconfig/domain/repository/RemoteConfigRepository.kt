package com.salat.gbinder.remoteconfig.domain.repository

import com.salat.gbinder.remoteconfig.domain.entity.AppUpdateInfo
import kotlinx.coroutines.flow.Flow

interface RemoteConfigRepository {
    fun init()

    val appUpdateFlow: Flow<Pair<Boolean, AppUpdateInfo?>>

    fun getString(key: String, default: String): String

    fun getBoolean(key: String): Boolean

    fun getDouble(key: String): Double

    fun getLong(key: String): Long
}
