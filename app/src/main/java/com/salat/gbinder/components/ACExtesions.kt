package com.salat.gbinder.components

import android.content.ComponentName
import android.content.Context
import android.content.pm.PackageManager
import com.salat.gbinder.BootAccessibilityService
import timber.log.Timber

fun Context.restartAccessibilityService() {
    try {
        val comp = ComponentName(this, BootAccessibilityService::class.java)
        val pm = packageManager

        pm.setComponentEnabledSetting(
            comp,
            PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
            PackageManager.DONT_KILL_APP
        )
        pm.setComponentEnabledSetting(
            comp,
            PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
            PackageManager.DONT_KILL_APP
        )
    } catch (e: Exception) {
        Timber.e(e)
    }
}
