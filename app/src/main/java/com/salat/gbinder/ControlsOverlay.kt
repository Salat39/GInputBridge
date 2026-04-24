package com.salat.gbinder

import android.annotation.SuppressLint
import android.app.ActivityManager
import android.app.ForegroundServiceStartNotAllowedException
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.provider.Settings
import androidx.core.net.toUri
import com.salat.gbinder.features.launcher.LauncherOverlayService
import timber.log.Timber

@SuppressLint("ObsoleteSdkInt")
fun <T : Service> startOverlay(
    context: Context,
    serviceClass: Class<T>,
    requireOverlayPermission: Boolean = true
) {
    if (serviceClass == LauncherOverlayService::class.java) {
        startLauncherOverlayService(context, requireOverlayPermission)
        return
    }
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
    if (serviceClass == LauncherOverlayService::class.java) {
        stopLauncherOverlayService(context)
        return
    }
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
    if (T::class.java == LauncherOverlayService::class.java) {
        return LauncherOverlayService.isAlive
    }
    return isServiceRunning(context, T::class.java)
}

// Helper function to check if service is running
fun <T> isServiceRunning(context: Context, serviceClass: Class<T>): Boolean {
    if (serviceClass == LauncherOverlayService::class.java) {
        return LauncherOverlayService.isAlive
    }
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

@SuppressLint("ObsoleteSdkInt")
private fun startLauncherOverlayService(
    context: Context,
    requireOverlayPermission: Boolean
) {
    runCatching {
        if (LauncherOverlayService.isStarting || LauncherOverlayService.isAlive) return@runCatching

        if (requireOverlayPermission &&
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
            !Settings.canDrawOverlays(context)
        ) {
            val intent = Intent(
                Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                "package:${context.packageName}".toUri()
            ).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
            return@runCatching
        }

        LauncherOverlayService.isStarting = true
        val serviceIntent = Intent(context, LauncherOverlayService::class.java)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            try {
                context.startForegroundService(serviceIntent)
            } catch (e: Exception) {
                LauncherOverlayService.isStarting = false
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S &&
                    e is ForegroundServiceStartNotAllowedException
                ) {
                    Timber.e(e, "[LAUNCHER] FGS not allowed in current state")
                } else {
                    throw e
                }
            }
        } else {
            context.startService(serviceIntent)
        }
    }.onFailure {
        Timber.e(it)
        LauncherOverlayService.isStarting = false
    }
}

private fun stopLauncherOverlayService(context: Context) {
    runCatching {
        when {
            LauncherOverlayService.isAlive -> {
                val serviceIntent = Intent(context, LauncherOverlayService::class.java)
                context.stopService(serviceIntent)
            }
            LauncherOverlayService.isStarting && !LauncherOverlayService.isAlive -> {
                val serviceIntent = Intent(context, LauncherOverlayService::class.java)
                Handler(Looper.getMainLooper()).postDelayed(
                    {
                        if (LauncherOverlayService.isAlive) {
                            runCatching { context.stopService(serviceIntent) }
                        }
                    },
                    250L
                )
            }
            else -> Unit
        }
    }.onFailure { Timber.e(it) }
}
