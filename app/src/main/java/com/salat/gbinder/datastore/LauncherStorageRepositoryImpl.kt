package com.salat.gbinder.datastore

import com.salat.gbinder.entity.LauncherItem
import kotlinx.coroutines.flow.first
import kotlinx.serialization.json.Json

class LauncherStorageRepositoryImpl(private val storage: DataStoreRepository) :
    LauncherStorageRepository {

    override val flow
        get() = storage.getValueFlow(LauncherPrefs.LAUNCHER_DATA)

    override val dataStore
        get() = storage

    override suspend fun save(data: List<LauncherItem>) {
        try {
            val jsonString: String = Json.encodeToString(data)
            storage.saveValue(LauncherPrefs.LAUNCHER_DATA, jsonString)
        } catch (_: Exception) {
        }
    }

    override suspend fun getAll(): List<LauncherItem> =
        parse(storage.getValueFlow(LauncherPrefs.LAUNCHER_DATA).first() ?: "")

    override fun parse(json: String): List<LauncherItem> {
        return try {
            Json.decodeFromString(json)
        } catch (_: Exception) {
            emptyList()
        }
    }
}
