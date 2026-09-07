package com.salat.gbinder.datastore

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object NoBackupPrefs {
    val CONFIGURATOR_WARNING = booleanPreferencesKey("CONFIGURATOR_WARNING")
    val ADB_TERMINAL_WARNING = booleanPreferencesKey("ADB_TERMINAL_WARNING")
    val ADVANCED_SETTINGS_EXPANDED = booleanPreferencesKey("ADVANCED_SETTINGS_EXPANDED")
    val ADB_TERMINAL_RECENT = stringPreferencesKey("ADB_TERMINAL_RECENT")
}
