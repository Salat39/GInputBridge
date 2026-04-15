package com.salat.gbinder.datastore

import com.salat.gbinder.entity.LauncherItem
import kotlinx.coroutines.flow.Flow

interface LauncherStorageRepository {
    val flow: Flow<String?>

    val dataStore: DataStoreRepository

    suspend fun save(data: List<LauncherItem>)

    suspend fun getAll(): List<LauncherItem>

    fun parse(json: String): List<LauncherItem>
}
