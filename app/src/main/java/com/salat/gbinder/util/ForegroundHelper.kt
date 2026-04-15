package com.salat.gbinder.util

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import com.salat.gbinder.R

object ForegroundHelper {
    private const val CHANNEL_ID = "gbinder_foreground"
    const val NOTIF_ID = 1

    fun ensureChannel(context: Context) {
        val mgr = context.getSystemService(NotificationManager::class.java)
        if (mgr.getNotificationChannel(CHANNEL_ID) == null) {
            val ch = NotificationChannel(
                CHANNEL_ID,
                context.getString(R.string.key_service_operation),
                NotificationManager.IMPORTANCE_MIN
            )
            mgr.createNotificationChannel(ch)
        }
    }

    fun buildNotification(context: Context): Notification =
        NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_logo)
            .setContentTitle(context.getString(R.string.app_name))
            .setContentText(context.getString(R.string.key_listener_active))
            .setOngoing(true)
            .setShowWhen(false)
            .build()
}
