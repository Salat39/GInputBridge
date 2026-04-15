package com.salat.gbinder.components

import android.Manifest
import android.app.Activity
import android.app.ActivityManager
import android.app.KeyguardManager
import android.content.ActivityNotFoundException
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.LauncherApps
import android.content.pm.PackageManager
import android.graphics.Rect
import android.hardware.display.DisplayManager
import android.os.Build
import android.os.PowerManager
import android.os.UserHandle
import android.provider.Settings
import android.view.Display
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

fun Context.isPackageInstalled(packageName: String): Boolean = try {
    packageManager.getPackageInfo(packageName, 0)
    true
} catch (_: PackageManager.NameNotFoundException) {
    false
}

fun Context.openAppSystemSettings(packageName: String) {
    try {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
            data = "package:$packageName".toUri()
        }
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    } catch (e: Exception) {
        Timber.e(e)
    }
}

@Suppress("DEPRECATION")
fun Context.requestUninstall(pkg: String) {
    val intent = Intent(Intent.ACTION_UNINSTALL_PACKAGE).apply {
        data = "package:$pkg".toUri()
        putExtra(Intent.EXTRA_RETURN_RESULT, true)
        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    }
    startActivity(intent)
}

fun Context.openAppNotifications(pkg: String) {
    val intent = Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS).apply {
        putExtra(Settings.EXTRA_APP_PACKAGE, pkg)
        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    }
    startActivity(intent)
}

fun Context.openBatteryOpt(pkg: String) {
    val intent = Intent(Settings.ACTION_IGNORE_BATTERY_OPTIMIZATION_SETTINGS).apply {
        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    }
    startActivity(intent)
}

fun Context.launchStringIntent(data: String) = runCatching {
    val intent = Intent.parseUri(data, Intent.URI_INTENT_SCHEME)
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    startActivity(intent)
}

fun Context.launchApp(
    packageName: String,
    activityName: String?,
    user: UserHandle? = null
) = runCatching {
    val flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED

    if (!activityName.isNullOrBlank()) {
        val fqcn = if (activityName.startsWith(".")) packageName + activityName else activityName
        val explicit = Intent(Intent.ACTION_MAIN)
            .addCategory(Intent.CATEGORY_LAUNCHER)
            .setComponent(ComponentName(packageName, fqcn))
            .addFlags(flags)

        if (user != null && Build.VERSION.SDK_INT >= 24) {
            val la = getSystemService(LauncherApps::class.java)
            try {
                la?.startMainActivity(ComponentName(packageName, fqcn), user, Rect(), null)
                return@runCatching
            } catch (_: Exception) {
            }
        }

        try {
            startActivity(explicit)
            return@runCatching
        } catch (_: Exception) {
        }
    }

    packageManager.getLaunchIntentForPackage(packageName)?.let { launch ->
        launch.addFlags(flags)
        if (Build.VERSION.SDK_INT >= 24 && user != null) {
            val la = getSystemService(LauncherApps::class.java)
            val component = launch.component ?: return@let run {
                null
            }
            try {
                la?.startMainActivity(component, user, Rect(), null)
                return@runCatching
            } catch (_: Exception) {
            }
        }
        try {
            startActivity(launch)
        } catch (_: ActivityNotFoundException) {
            null
        }
        return@runCatching
    }

    val probe = Intent(Intent.ACTION_MAIN)
        .addCategory(Intent.CATEGORY_LAUNCHER)
        .setPackage(packageName)

    val resolveInfo = if (Build.VERSION.SDK_INT >= 33) {
        val flagsResolve =
            PackageManager.ResolveInfoFlags.of(PackageManager.MATCH_DEFAULT_ONLY.toLong())
        packageManager.queryIntentActivities(probe, flagsResolve).firstOrNull()
    } else {
        @Suppress("DEPRECATION")
        packageManager.queryIntentActivities(probe, PackageManager.MATCH_DEFAULT_ONLY).firstOrNull()
    } ?: return@runCatching

    val component =
        ComponentName(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name)
    val launch = Intent(Intent.ACTION_MAIN)
        .addCategory(Intent.CATEGORY_LAUNCHER)
        .setComponent(component)
        .addFlags(flags)

    if (Build.VERSION.SDK_INT >= 24 && user != null) {
        val la = getSystemService(LauncherApps::class.java)
        try {
            la?.startMainActivity(component, user, Rect(), null)
        } catch (_: Exception) {
            try {
                startActivity(launch)
            } catch (_: Exception) {
            }
        }
        return@runCatching
    }

    try {
        startActivity(launch)
    } catch (_: Exception) {
    }
}

fun Context.isNotificationServiceEnabled(): Boolean {
    val packageName = packageName
    val enabledListeners =
        Settings.Secure.getString(contentResolver, "enabled_notification_listeners")
    return enabledListeners?.contains(packageName) ?: false
}

fun Context.isReadPhoneStateGranted(): Boolean {
    return ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) ==
            PackageManager.PERMISSION_GRANTED
}

fun Context.requestNotificationServicePermission() {
    val intent = Intent(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS)
    if (this !is Activity) {
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    }
    startActivity(intent)
}

fun Context.openAccessibilitySettings() = try {
    val intent = Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS)
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    startActivity(intent)
} catch (e: Exception) {
    println(e)
}

fun Context.toast(text: String) {
    try {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show()
    } catch (e: Exception) {
        Timber.e(e)
    }
}

suspend fun Context.inMainToast(text: String) {
    withContext(Dispatchers.Main) {
        try {
            Toast.makeText(this@inMainToast, text, Toast.LENGTH_LONG).show()
        } catch (e: Exception) {
            Timber.e(e)
        }
    }
}

fun Context.deviceIsActive(tag: String, logs: Boolean): Boolean {
    val pm = getSystemService(Context.POWER_SERVICE) as PowerManager
    val isScreenOn = pm.isInteractive

    if (logs) println("[$tag] Screen is currently interactive = $isScreenOn")
    val isDozing = pm.isDeviceIdleMode
    if (logs) println("[$tag] Device is in Doze mode = $isDozing")

    val isSaving = pm.isPowerSaveMode
    if (logs) println("[$tag] Power Save Mode = $isSaving")

    val km = getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager
    val isLocked = km.isDeviceLocked
    if (logs) println("[$tag] Device locked = $isLocked")

    val info = ActivityManager.RunningAppProcessInfo()
    ActivityManager.getMyMemoryState(info)
    val importance = info.importance
    if (logs) println("[$tag] Process importance = $importance")

    val dm = getSystemService(Context.DISPLAY_SERVICE) as DisplayManager
    val displays = dm.displays
    val displayInfo = displays.joinToString(", ") {
        "display${it.displayId} = ${stateToString(it.state)}"
    }
    if (logs) println("[$tag] Number of $displayInfo")

    val verdict = !isDozing && !isLocked && displays.any { it.state == Display.STATE_ON }
    if (logs) println("[$tag] Verdict = ${if (verdict) "device is active" else "device inactive"}")
    return verdict
}

private fun stateToString(state: Int): String = when (state) {
    Display.STATE_OFF -> "OFF"
    Display.STATE_ON -> "ON"
    Display.STATE_DOZE -> "DOZE"
    Display.STATE_DOZE_SUSPEND -> "DOZE_SUSPEND"
    Display.STATE_UNKNOWN -> "UNKNOWN"
    else -> "STATE[$state]"
}

fun Context.requireDisplayOverlay(): Boolean {
    try {
        if (!Settings.canDrawOverlays(this)) {
            val intent = Intent(
                Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                "package:${packageName}".toUri()
            )
            startActivity(intent)
            return false
        } else return true
    } catch (_: Exception) {
        return false
    }
}
