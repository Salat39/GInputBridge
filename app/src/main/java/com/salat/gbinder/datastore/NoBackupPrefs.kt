package com.salat.gbinder.datastore

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object NoBackupPrefs {
    val DEFAULT_MEDIA_APP = stringPreferencesKey("DEFAULT_MEDIA_APP")
    val ENABLED_MEDIA_APPS = stringPreferencesKey("ENABLED_MEDIA_APPS")
    val CONFIGURATOR_WARNING = booleanPreferencesKey("CONFIGURATOR_WARNING")
}
