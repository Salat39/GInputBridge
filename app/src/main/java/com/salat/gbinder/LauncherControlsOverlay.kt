package com.salat.gbinder

import android.annotation.SuppressLint
import android.app.ForegroundServiceStartNotAllowedException
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
fun startLauncherOverlay(
    context: Context,
    requireOverlayPermission: Boolean = true
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

fun stopLauncherOverlay(context: Context) {
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

@Suppress("UNUSED_PARAMETER")
fun isLauncherServiceRunning(context: Context): Boolean = LauncherOverlayService.isAlive
