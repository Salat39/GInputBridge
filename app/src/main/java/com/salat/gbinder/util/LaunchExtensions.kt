package com.salat.gbinder.util

import android.content.Context
import android.content.Intent
import android.provider.Settings
import android.view.KeyEvent
import com.salat.gbinder.entity.AppMediaAction
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import timber.log.Timber

fun Context.sendYmAutoPlayCompat() {
    try {
        val intent = Intent("action.startPlayback").apply {
            setClassName("ru.yandex.music", "ru.yandex.music.main.MainScreenActivity")
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            setPackage("ru.yandex.music")
        }
        startActivity(intent)
    } catch (e: Exception) {
        Timber.e(e)
    }
}

fun Context.sendMurglarAutoPlayCompat() {
    try {
        val intent = Intent("com.badmanners.murglar2.action.SHOW_PLAYER").apply {
            setClassName("com.badmanners.murglar2", "com.badmanners.murglar.MainActivity")
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
        }
        startActivity(intent)
    } catch (e: Exception) {
        Timber.e(e)
    }
}

fun Context.sendVkxAutoPlayCompat() {
    try {
        val intent = Intent("ua.itaysonlab.vkx.action.OPEN_PLAYER").apply {
            setClassName("ua.itaysonlab.vkx", "ua.itaysonlab.vkx.activity.AppActivity")
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
        }
        startActivity(intent)
    } catch (e: Exception) {
        Timber.e(e)
    }
}

suspend fun Context.sendMediaActionToApp(
    packageName: String,
    action: AppMediaAction = AppMediaAction.PLAY,
    enableToggleFallback: Boolean = true,
    upDelayMs: Long = 30L // small gap helps some receivers
) = withContext(Dispatchers.Default) {
    val keyCode = when (action) {
        AppMediaAction.PLAY -> KeyEvent.KEYCODE_MEDIA_PLAY
        AppMediaAction.PAUSE -> KeyEvent.KEYCODE_MEDIA_PAUSE
        AppMediaAction.NEXT -> KeyEvent.KEYCODE_MEDIA_NEXT
        AppMediaAction.PREVIOUS -> KeyEvent.KEYCODE_MEDIA_PREVIOUS
        AppMediaAction.TOGGLE -> KeyEvent.KEYCODE_MEDIA_PLAY_PAUSE
    }
    val ok = sendMediaKeyToPackage(packageName, keyCode, upDelayMs)
    if (!ok && enableToggleFallback && (action == AppMediaAction.PLAY || action == AppMediaAction.PAUSE)) {
        // Some players only implement toggle
        sendMediaKeyToPackage(packageName, KeyEvent.KEYCODE_MEDIA_PLAY_PAUSE, upDelayMs)
    }
}

/* Returns true if broadcast was sent without throwing.
 * It does NOT guarantee the target app handled it.
 */
suspend fun Context.sendMediaKeyToPackage(
    packageName: String,
    keyCode: Int,
    upDelayMs: Long = 30L
): Boolean = withContext(Dispatchers.Default) {
    fun send(action: Int): Boolean = runCatching {
        val intent = Intent(Intent.ACTION_MEDIA_BUTTON).apply {
            putExtra(Intent.EXTRA_KEY_EVENT, KeyEvent(action, keyCode))
            setPackage(packageName) // explicit target
            // Optional: mark as foreground to bump priority (best-effort)
            addFlags(Intent.FLAG_RECEIVER_FOREGROUND)
        }
        sendOrderedBroadcast(intent, null)
    }.onFailure { Timber.e(it) }.isSuccess

    val downOk = send(KeyEvent.ACTION_DOWN)
    // tiny pause so some receivers treat it as a real press
    if (upDelayMs > 0) delay(upDelayMs)
    val upOk = send(KeyEvent.ACTION_UP)
    downOk && upOk
}

fun Context.openApp(packageName: String?) = packageName?.let { id ->
    val launchIntent = packageManager.getLaunchIntentForPackage(id)
    if (launchIntent != null) {
        startActivity(launchIntent)
    }
}

fun Context.softOpenApp(packageName: String?) = runCatching {
    val pkg = packageName?.trim().orEmpty()
    if (pkg.isEmpty()) return@runCatching

    if (pkg == "com.android.settings") {
        val settingsIntent =
            Intent(Settings.ACTION_SETTINGS).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(settingsIntent)
        return@runCatching
    }

    val launchIntent = packageManager.getLaunchIntentForPackage(pkg) ?: return@runCatching
    launchIntent.addFlags(
        Intent.FLAG_ACTIVITY_NEW_TASK or
                Intent.FLAG_ACTIVITY_REORDER_TO_FRONT or
                Intent.FLAG_ACTIVITY_SINGLE_TOP
    )
    startActivity(launchIntent)
}
