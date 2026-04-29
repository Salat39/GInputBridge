package com.salat.gbinder.features.launcher

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import kotlinx.coroutines.delay
import java.time.LocalTime

internal fun isNowWithinHours(start: Int, end: Int): Boolean {
    val s = start.coerceIn(0, 24) % 24
    val e = end.coerceIn(0, 24) % 24

    if (s == e) return true

    val now = LocalTime.now()
    val startTime = LocalTime.of(s, 0)
    val endTime = LocalTime.of(e, 0)

    return if (s < e) {
        !now.isBefore(startTime) && now.isBefore(endTime)
    } else {
        !now.isBefore(startTime) || now.isBefore(endTime)
    }
}

internal suspend fun Context.waitPackageReadyAfterUnfreeze(packageName: String): Boolean {
    repeat(8) {
        if (isPackageReadyAfterUnfreeze(packageName)) return true
        delay(150)
    }

    return isPackageReadyAfterUnfreeze(packageName)
}

private fun Context.isPackageReadyAfterUnfreeze(packageName: String): Boolean {
    val enabledState = runCatching {
        packageManager.getApplicationEnabledSetting(packageName)
    }.getOrElse {
        return false
    }

    if (enabledState == PackageManager.COMPONENT_ENABLED_STATE_DISABLED ||
        enabledState == PackageManager.COMPONENT_ENABLED_STATE_DISABLED_USER ||
        enabledState == PackageManager.COMPONENT_ENABLED_STATE_DISABLED_UNTIL_USED
    ) {
        return false
    }

    return runCatching {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            packageManager.getApplicationInfo(
                packageName,
                PackageManager.ApplicationInfoFlags.of(0)
            ).enabled
        } else {
            @Suppress("DEPRECATION")
            packageManager.getApplicationInfo(packageName, 0).enabled
        }
    }.getOrDefault(false)
}
