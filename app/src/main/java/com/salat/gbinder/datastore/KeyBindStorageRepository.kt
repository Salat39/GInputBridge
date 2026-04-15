package com.salat.gbinder.datastore

import com.salat.gbinder.entity.KeyBindConfig
import com.salat.gbinder.entity.KeyBindPattern

interface KeyBindStorageRepository {
    suspend fun getCode(): String

    suspend fun applyCode(import: String)

    fun parseBinds(bindsJson: String): Map<String, KeyBindConfig>

    suspend fun saveBinds(key: String, config: KeyBindConfig)

    suspend fun deleteBind(bindName: String)

    fun getBindName(bind: KeyBindPattern): String
}
