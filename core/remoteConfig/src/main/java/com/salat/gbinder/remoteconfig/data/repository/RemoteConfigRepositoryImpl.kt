package com.salat.gbinder.remoteconfig.data.repository

import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import com.salat.gbinder.remoteconfig.BuildConfig
import com.salat.gbinder.remoteconfig.R
import com.salat.gbinder.remoteconfig.domain.entity.AppUpdateInfo
import com.salat.gbinder.remoteconfig.domain.repository.RemoteConfigRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import org.json.JSONObject
import timber.log.Timber

@Suppress("SimplifyBooleanWithConstants")
class RemoteConfigRepositoryImpl : RemoteConfigRepository {
    private var isInit = false

    private val remoteConfig: FirebaseRemoteConfig by lazy {
        FirebaseRemoteConfig.getInstance()
    }

    override fun init() {
        try {
            remoteConfig.setDefaultsAsync(R.xml.remote_config_defaults)
            val configSettings = FirebaseRemoteConfigSettings.Builder()
                .setMinimumFetchIntervalInSeconds(
                    if (BuildConfig.DEBUG && BuildConfig.REMOTE_CONFIG_DEBUG) 0
                    else BuildConfig.REMOTE_CONFIG_FETCH_INTERVAL
                )
                .build()
            remoteConfig.setConfigSettingsAsync(configSettings)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Timber.d("[Firebase Remote Config] configuration installed")
                    }
                }
            syncData(remoteConfig)
            isInit = true
        } catch (e: Exception) {
            Timber.e(e)
        }
    }

    private fun syncData(remoteConfig: FirebaseRemoteConfig) {
        try {
            remoteConfig.fetchAndActivate()
                .addOnFailureListener { e ->
                    Timber.d("[Firebase Remote Config] update error")
                    Timber.e(e)
                }
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Timber.d("[Firebase Remote Config] values updated successfully")
                    } else {
                        Timber.d("[Firebase Remote Config] unable to update values")
                    }
                }
        } catch (e: Exception) {
            Timber.e(e)
        }
    }

    override val appUpdateFlow: Flow<Pair<Boolean, AppUpdateInfo?>>
        get() = callbackFlow {
            if (!isInit) init()
            var responded = false

            fun respond(success: Boolean, info: AppUpdateInfo?) {
                if (!responded) {
                    trySend(success to info).isSuccess
                    responded = true
                    close()
                }
            }

            try {
                remoteConfig.fetchAndActivate()
                    .addOnFailureListener { e ->
                        Timber.e(e)
                        respond(false, null)
                    }
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            try {
                                val appUpdateInfoValue =
                                    remoteConfig.getValue("gib_update_info").asString()

                                val jsonBody = JSONObject(appUpdateInfoValue)
                                val displaySize = if (jsonBody.has("display_size")) {
                                    jsonBody.getString("display_size")
                                } else null
                                val displayText = if (jsonBody.has("display_text")) {
                                    jsonBody.getString("display_text")
                                } else null
                                val displayVersion = if (jsonBody.has("display_version")) {
                                    jsonBody.getString("display_version")
                                } else null
                                val downloadUrl = if (jsonBody.has("download_url")) {
                                    jsonBody.getString("download_url")
                                } else null
                                val infoUrl = if (jsonBody.has("info_url")) {
                                    jsonBody.getString("info_url")
                                } else null
                                val versionCode = if (jsonBody.has("version_code")) {
                                    jsonBody.getInt("version_code")
                                } else null
                                val mandatory = if (jsonBody.has("mandatory")) {
                                    jsonBody.getBoolean("mandatory")
                                } else null

                                if (versionCode == null) {
                                    respond(false, null)
                                } else respond(
                                    success = true,
                                    info = AppUpdateInfo(
                                        version = displayVersion ?: "",
                                        size = displaySize ?: "",
                                        text = displayText ?: "",
                                        code = versionCode,
                                        downloadUrl = downloadUrl ?: "",
                                        infoUrl = infoUrl ?: "",
                                        mandatory = mandatory ?: false
                                    )
                                )
                            } catch (e: Exception) {
                                Timber.e(e)
                                respond(false, null)
                            }
                        } else {
                            respond(false, null)
                        }
                    }
            } catch (e: Exception) {
                Timber.e(e)
                respond(false, null)
            }

            awaitClose { }
        }

    // Helper functions for fetching values from Remote Config

    /**
     * Retrieves a string value for the specified key.
     * Returns [default] if the fetched value is empty or an error occurs.
     */
    override fun getString(key: String, default: String): String = runCatching {
        val remoteValue = remoteConfig.getValue(key)
        when (remoteValue.source) {
            FirebaseRemoteConfig.VALUE_SOURCE_REMOTE ->
                Timber.d("Value fetched from remote for key: $key")

            FirebaseRemoteConfig.VALUE_SOURCE_DEFAULT ->
                Timber.d("Value fetched from defaults for key: $key")

            FirebaseRemoteConfig.VALUE_SOURCE_STATIC ->
                Timber.d("Value fetched from static default for key: $key")

            else -> Unit
        }
        remoteValue.asString().takeIf { it.isNotEmpty() } ?: default
    }.getOrElse {
        Timber.e(it)
        default
    }

    /**
     * Retrieves a boolean value for the specified key.
     * Returns false if an error occurs.
     */
    override fun getBoolean(key: String): Boolean = runCatching { remoteConfig.getBoolean(key) }
        .getOrElse {
            Timber.e(it)
            false
        }

    /**
     * Retrieves a double value for the specified key.
     * Returns 0.0 if an error occurs.
     */
    override fun getDouble(key: String): Double = runCatching { remoteConfig.getDouble(key) }
        .getOrElse {
            Timber.e(it)
            0.0
        }

    /**
     * Retrieves a long value for the specified key.
     * Returns 0L if an error occurs.
     */
    override fun getLong(key: String): Long = runCatching { remoteConfig.getLong(key) }
        .getOrElse {
            Timber.e(it)
            0L
        }
}
