package com.salat.gbinder

import android.content.ComponentName
import android.provider.Settings
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import java.util.concurrent.atomic.AtomicBoolean

class MediaNotificationListenerService : NotificationListenerService() {
    private val isConnected = AtomicBoolean(false)

    private fun isListenerEnabled(): Boolean {
        val enabled = Settings.Secure.getString(contentResolver, "enabled_notification_listeners")
            ?: return false
        val me = ComponentName(this, MediaNotificationListenerService::class.java)
        return enabled.split(':').any { it.equals(me.flattenToString(), ignoreCase = true) }
    }

    override fun onListenerConnected() {
        super.onListenerConnected()
        isConnected.set(true)
    }

    override fun onListenerDisconnected() {
        // System manages normal rebinds; only ask rebind if user disabled the listener
        if (!isListenerEnabled()) {
            requestRebind(ComponentName(this, MediaNotificationListenerService::class.java))
        }
        isConnected.set(false)
        super.onListenerDisconnected()
    }

    override fun onNotificationPosted(sbn: StatusBarNotification?) {
        // Ignore work if we already known we are disconnected
        if (!isConnected.get()) return
        // do nothing (lightweight)
    }

    override fun onNotificationRemoved(sbn: StatusBarNotification?) {
        if (!isConnected.get()) return
        // do nothing (lightweight)
    }

    override fun onDestroy() {
        isConnected.set(false)
        super.onDestroy()
    }
}
