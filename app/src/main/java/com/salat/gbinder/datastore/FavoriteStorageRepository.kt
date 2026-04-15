package com.salat.gbinder.datastore

import kotlinx.coroutines.flow.Flow

interface FavoriteStorageRepository {
    fun getFavoritesFlow(): Flow<List<String>>

    suspend fun getFavorites(): List<String>

    suspend fun saveFavorites(ids: List<String>)

    suspend fun addFavorite(id: String)

    suspend fun removeFavorite(id: String)
}
