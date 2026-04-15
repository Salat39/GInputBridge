package com.salat.gbinder.datastore

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.serialization.json.Json

class FavoriteStorageRepositoryImpl(private val dataStore: DataStoreRepository) :
    FavoriteStorageRepository {

    override fun getFavoritesFlow(): Flow<List<String>> {
        return dataStore.getValueFlow(GeneralPrefs.FAVORITES)
            .map { json ->
                if (json.isNullOrBlank()) emptyList() else parseFavorites(json)
            }
    }

    override suspend fun getFavorites(): List<String> {
        val json = dataStore.getValueFlow(GeneralPrefs.FAVORITES).first() ?: ""
        return parseFavorites(json)
    }

    override suspend fun saveFavorites(ids: List<String>) {
        try {
            val jsonString = Json.encodeToString(ids)
            dataStore.saveValue(GeneralPrefs.FAVORITES, jsonString)
        } catch (_: Exception) {
            // ignore errors
        }
    }

    override suspend fun addFavorite(id: String) {
        val currentFavorites = getFavorites().toMutableSet()
        if (currentFavorites.add(id)) {
            saveFavorites(currentFavorites.toList())
        }
    }

    override suspend fun removeFavorite(id: String) {
        val currentFavorites = getFavorites().toMutableSet()
        if (currentFavorites.remove(id)) {
            saveFavorites(currentFavorites.toList())
        }
    }

    private fun parseFavorites(json: String): List<String> {
        return try {
            if (json.isBlank()) emptyList() else Json.decodeFromString(json)
        } catch (_: Exception) {
            emptyList()
        }
    }
}
