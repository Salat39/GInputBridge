package com.salat.gbinder.datastore

import com.salat.gbinder.entity.KeyBindConfig
import com.salat.gbinder.entity.KeyBindPattern
import kotlinx.coroutines.flow.first
import kotlinx.serialization.json.Json

class KeyBindStorageRepositoryImpl(private val dataStore: DataStoreRepository) :
    KeyBindStorageRepository {
    override suspend fun getCode(): String {
        return dataStore.getValueFlow(GeneralPrefs.KEY_BINDS).first() ?: ""
    }

    override suspend fun applyCode(import: String) {
        val importBinds = try {
            parseBinds(import)
        } catch (_: Exception) {
            emptyMap()
        }

        val currentBinds = getBinds()

        val merged = currentBinds + importBinds
        try {
            val jsonString: String = Json.encodeToString(merged)
            dataStore.saveValue(GeneralPrefs.KEY_BINDS, jsonString)
        } catch (_: Exception) {
        }
    }

    private suspend fun getBinds(): Map<String, KeyBindConfig> {
        val bindsJson = dataStore.getValueFlow(GeneralPrefs.KEY_BINDS).first() ?: ""
        return parseBinds(bindsJson)
    }

    override fun parseBinds(bindsJson: String): Map<String, KeyBindConfig> {
        return try {
            Json.decodeFromString(bindsJson)
        } catch (_: Exception) {
            emptyMap()
        }
    }

    override suspend fun saveBinds(key: String, config: KeyBindConfig) {
        val currentBinds = getBinds()
        val updated = currentBinds + (key to config)
        try {
            val jsonString: String = Json.encodeToString(updated)
            dataStore.saveValue(GeneralPrefs.KEY_BINDS, jsonString)
        } catch (_: Exception) {
        }
    }

    override suspend fun deleteBind(bindName: String) {
        val currentBinds = getBinds()
        val updated = currentBinds.filter { it.key != bindName }
        try {
            val jsonString: String = Json.encodeToString(updated)
            dataStore.saveValue(GeneralPrefs.KEY_BINDS, jsonString)
        } catch (_: Exception) {
        }
    }

    override fun getBindName(bind: KeyBindPattern): String {
        val bindName = when (bind) {
            is KeyBindPattern.DoubleClick -> "dc"
            is KeyBindPattern.LongPress -> "lp"
            is KeyBindPattern.MultiLong -> "ml"
            is KeyBindPattern.ShortClick -> "sc"
        }
        val keys = when (bind) {
            is KeyBindPattern.DoubleClick -> listOf(bind.keyCode)
            is KeyBindPattern.LongPress -> listOf(bind.keyCode)
            is KeyBindPattern.MultiLong -> bind.keyCodes
            is KeyBindPattern.ShortClick -> listOf(bind.keyCode)
        }
        val keyName = keys
            .sorted()
            .joinToString("+")

        return bindName + keyName
    }
}
