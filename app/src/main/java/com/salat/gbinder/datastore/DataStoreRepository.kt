package com.salat.gbinder.datastore

import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.flow.Flow

interface DataStoreRepository {
    suspend fun <T> saveValue(key: Preferences.Key<T>, value: T)

    fun <T> getValueFlow(key: Preferences.Key<T>): Flow<T?>

    fun <T> getValueFlow(key: Preferences.Key<T>, defaultValue: T): Flow<T>

    suspend fun <T> removeValue(key: Preferences.Key<T>)

    suspend fun <T> exists(key: Preferences.Key<T>): Boolean

    fun valuesFlow(vararg keys: Preferences.Key<*>): Flow<List<Any?>>

    fun valuesFlowWithDefaults(
        keys: Array<out Preferences.Key<*>>,
        defaults: List<Any?>
    ): Flow<List<Any?>>

    fun collectBackupParams(serialized: String): Set<String>

    suspend fun exportAllSettings(task: DataStoreBackupTask): String

    suspend fun importAllSettings(
        serialized: String,
        task: DataStoreBackupTask
    )
}
