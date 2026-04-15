package com.salat.gbinder.datastore

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

class DataStoreRepositoryImpl(private val context: Context) : DataStoreRepository {
    private val Context.dataStore by preferencesDataStore(name = "settings")

    override suspend fun <T> saveValue(key: Preferences.Key<T>, value: T) {
        context.dataStore.edit { preferences ->
            preferences[key] = value
        }
    }

    override fun <T> getValueFlow(key: Preferences.Key<T>): Flow<T?> =
        context.dataStore.data
            .map { preferences -> preferences[key] }
            .distinctUntilChanged()

    override fun <T> getValueFlow(key: Preferences.Key<T>, defaultValue: T): Flow<T> =
        context.dataStore.data
            .map { preferences -> preferences[key] ?: defaultValue }
            .distinctUntilChanged()

    override suspend fun <T> removeValue(key: Preferences.Key<T>) {
        context.dataStore.edit { preferences ->
            preferences.remove(key)
        }
    }

    override suspend fun <T> exists(key: Preferences.Key<T>): Boolean {
        val prefs = context.dataStore.data.first()
        return prefs.contains(key)
    }

    override fun valuesFlow(vararg keys: Preferences.Key<*>): Flow<List<Any?>> =
        context.dataStore.data
            .map { prefs ->
                keys.map { key ->
                    @Suppress("UNCHECKED_CAST")
                    prefs[key as Preferences.Key<Any?>]
                }
            }
            .distinctUntilChanged()

    override fun valuesFlowWithDefaults(
        keys: Array<out Preferences.Key<*>>,
        defaults: List<Any?>
    ): Flow<List<Any?>> {
        require(keys.size == defaults.size) { "defaults.size must equal keys.size" }
        return context.dataStore.data
            .map { prefs ->
                keys.indices.map { i ->
                    @Suppress("UNCHECKED_CAST")
                    val v = prefs[keys[i] as Preferences.Key<Any?>]
                    v ?: defaults[i]
                }
            }
            .distinctUntilChanged()
    }

    override suspend fun exportAllSettings(task: DataStoreBackupTask): String {
        val snapshot = context.dataStore.data.first()

        val generalPrefsNames = GeneralPrefs.ALL_KEYS.map { it.name }.toSet()
        val generalDynamicPrefixes = GeneralPrefs.DYNAMIC_PREFIX_KEYS.toSet()
        val launcherPrefsNames = LauncherPrefs.ALL_KEYS.map { it.name }.toSet()

        fun isAllowed(name: String): Boolean {
            val inGeneral = name in generalPrefsNames || name.startsWithAny(generalDynamicPrefixes)
            val inLauncher = name in launcherPrefsNames
            return (task.withGeneral && inGeneral) || (task.withLauncher && inLauncher)
        }

        val entries = snapshot.asMap()
            .mapNotNull { (k, v) ->
                val name = k.name
                if (!isAllowed(name)) return@mapNotNull null

                when (v) {
                    is Int -> PrefEntry(name, "int", v.toString())
                    is Long -> PrefEntry(name, "long", v.toString())
                    is Boolean -> PrefEntry(name, "bool", v.toString())
                    is String -> PrefEntry(name, "string", v)
                    is Float -> PrefEntry(name, "float", v.toString())
                    is Double -> PrefEntry(name, "double", v.toString())
                    is Set<*> -> {
                        val set = v.filterIsInstance<String>().toList()
                        PrefEntry(name, "stringSet", json.encodeToString(set))
                    }

                    else -> null
                }
            }

        return json.encodeToString(entries)
    }

    override fun collectBackupParams(serialized: String) =
        json.decodeFromString<List<PrefEntry>>(serialized).map { it.name }.toSet()

    override suspend fun importAllSettings(serialized: String, task: DataStoreBackupTask) {
        val entries = json.decodeFromString<List<PrefEntry>>(serialized)

        val generalPrefsNames = GeneralPrefs.ALL_KEYS.map { it.name }.toSet()
        val generalDynamicPrefsNames = GeneralPrefs.DYNAMIC_PREFIX_KEYS.toSet()
        val launcherPrefsNames = LauncherPrefs.ALL_KEYS.map { it.name }.toSet()

        context.dataStore.edit { prefs ->
            if (task.withGeneral) {
                val keysToRemove = prefs.asMap().keys.filter {
                    it.name in generalPrefsNames || it.name.startsWithAny(generalDynamicPrefsNames)
                }
                keysToRemove.forEach { key -> prefs.remove(key) }
            }

            if (task.withLauncher) {
                val keysToRemove = prefs.asMap().keys.filter { it.name in launcherPrefsNames }
                keysToRemove.forEach { key -> prefs.remove(key) }
            }

            entries.forEach { e ->
                if (!task.withGeneral && (e.name in generalPrefsNames || e.name.startsWithAny(
                        generalDynamicPrefsNames
                    ))
                ) {
                    return@forEach
                }
                if (!task.withLauncher && e.name in launcherPrefsNames) {
                    return@forEach
                }

                runCatching {
                    when (e.type) {
                        "int" -> prefs[intPreferencesKey(e.name)] = e.value.toInt()
                        "long" -> prefs[longPreferencesKey(e.name)] = e.value.toLong()
                        "bool" -> {
                            val parsed = e.value.toBooleanStrictOrNull() ?: (e.value == "true")
                            prefs[booleanPreferencesKey(e.name)] = parsed
                        }

                        "string" -> prefs[stringPreferencesKey(e.name)] = e.value
                        "float" -> prefs[floatPreferencesKey(e.name)] = e.value.toFloat()
                        "double" -> prefs[doublePreferencesKey(e.name)] = e.value.toDouble()
                        "stringSet" -> {
                            val setList = runCatching {
                                json.decodeFromString<List<String>>(e.value)
                            }.getOrElse { emptyList() }
                            prefs[stringSetPreferencesKey(e.name)] = setList.toSet()
                        }

                        else -> { /* skip unknown */
                        }
                    }
                }
            }
        }
    }

    private fun String.startsWithAny(prefixes: Set<String>, ignoreCase: Boolean = false): Boolean {
        for (p in prefixes) {
            if (this.startsWith(p, ignoreCase)) return true
        }
        return false
    }

    @Serializable
    private data class PrefEntry(
        @SerialName("name") val name: String,
        @SerialName("type") val type: String,
        @SerialName("value") val value: String
    )

    private companion object {
        private val json: Json = Json {
            encodeDefaults = true
            ignoreUnknownKeys = true
            prettyPrint = false
        }
    }
}
