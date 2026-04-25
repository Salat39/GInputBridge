package com.salat.gbinder

import android.annotation.SuppressLint
import android.app.ActivityManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.provider.Settings
import androidx.core.net.toUri
import timber.log.Timber

@SuppressLint("ObsoleteSdkInt")
fun <T : Service> startOverlay(
    context: Context,
    serviceClass: Class<T>,
    requireOverlayPermission: Boolean = true
) {
    runCatching {
        // Ensure we don't start the same service twice
        if (isServiceRunning(context, serviceClass)) return

        // Check overlay permission if required
        if (requireOverlayPermission &&
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
            !Settings.canDrawOverlays(context)
        ) {
            // Redirect user to settings to grant overlay permission
            val intent = Intent(
                Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                "package:${context.packageName}".toUri()
            ).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
            return
        }

        // Start the service (foreground on O+)
        val serviceIntent = Intent(context, serviceClass)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.startForegroundService(serviceIntent)
        } else {
            context.startService(serviceIntent)
        }
    }.onFailure { Timber.e(it) }
}

/* Convenience overload using reified type parameter */
inline fun <reified T : Service> startOverlay(
    context: Context,
    requireOverlayPermission: Boolean = true
) = startOverlay(context, T::class.java, requireOverlayPermission)

@SuppressLint("ObsoleteSdkInt")
fun <T : Service> stopOverlay(
    context: Context,
    serviceClass: Class<T>
) {
    runCatching {
        // Don't call stop if it's not running
        if (!isServiceRunning(context, serviceClass)) return

        // Stop the service
        val serviceIntent = Intent(context, serviceClass)
        context.stopService(serviceIntent)
    }.onFailure { Timber.e(it) }
}

/* Convenience overload using reified type parameter */
inline fun <reified T : Service> stopOverlay(
    context: Context
) = stopOverlay(context, T::class.java)

// Helper function to check if service is running
inline fun <reified T : Service> isServiceRunning(context: Context): Boolean {
    return isServiceRunning(context, T::class.java)
}

// Helper function to check if service is running
fun <T> isServiceRunning(context: Context, serviceClass: Class<T>): Boolean {
    try {
        val manager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager

        @Suppress("DEPRECATION")
        val runningServices = manager.getRunningServices(Integer.MAX_VALUE)
        return runningServices.any { it.service.className == serviceClass.name }
    } catch (e: Exception) {
        Timber.e(e)
    }
    return false
}
