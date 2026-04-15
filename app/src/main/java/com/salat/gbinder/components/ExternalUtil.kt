package com.salat.gbinder.components

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.widget.Toast
import androidx.core.net.toUri
import com.salat.gbinder.entity.OpenUrlResult

fun Context.openUrl(url: String): Boolean {
    val normalized = url.trim().let { raw ->
        if (raw.startsWith("http://") || raw.startsWith("https://")) raw
        else "https://$raw"
    }

    val uri = runCatching { normalized.toUri() }.getOrNull() ?: return false

    val intent = Intent(Intent.ACTION_VIEW, uri).apply {
        addCategory(Intent.CATEGORY_BROWSABLE)
        if (this@openUrl !is Activity) {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
    }

    return try {
        startActivity(intent)
        true
    } catch (_: ActivityNotFoundException) {
        false
    } catch (_: SecurityException) {
        false
    }
}

fun Context.openUrlSmart(rawUrl: String, chooserTitle: String = "Open link"): OpenUrlResult {
    val normalized = rawUrl.trim().let { s ->
        if (s.startsWith("http://") || s.startsWith("https://")) s else "https://$s"
    }

    val uri: Uri =
        runCatching { normalized.toUri() }.getOrNull() ?: return OpenUrlResult.INVALID_URL

    val viewIntent = Intent(Intent.ACTION_VIEW, uri).apply {
        addCategory(Intent.CATEGORY_BROWSABLE)
        if (this@openUrlSmart !is Activity) addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    }

    val pm = packageManager

    val viewHandlers = pm.queryIntentActivities(viewIntent, PackageManager.MATCH_DEFAULT_ONLY)
    if (viewHandlers.isNotEmpty()) {
        val chooser = Intent.createChooser(viewIntent, chooserTitle).apply {
            if (this@openUrlSmart !is Activity) addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        runCatching { startActivity(chooser) }
            .onFailure { /* swallow ActivityNotFound/Security just in case */ }
        return OpenUrlResult.OPENED_IN_APP
    }

    val shareIntent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, normalized)
        if (this@openUrlSmart !is Activity) addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    }
    val shareHandlers = pm.queryIntentActivities(shareIntent, PackageManager.MATCH_DEFAULT_ONLY)
    if (shareHandlers.isNotEmpty()) {
        val chooser = Intent.createChooser(shareIntent, "Share link").apply {
            if (this@openUrlSmart !is Activity) addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        runCatching { startActivity(chooser) }
        return OpenUrlResult.SHARED
    }

    val cm = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    cm.setPrimaryClip(ClipData.newPlainText("URL", normalized))
    Toast.makeText(this, "Link copied to clipboard", Toast.LENGTH_SHORT).show()
    return OpenUrlResult.COPIED_TO_CLIPBOARD
}
