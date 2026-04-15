package com.salat.gbinder.components

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.ClipData
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.content.FileProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

private const val MACRO_DROID_PKG = "com.arlosoft.macrodroid"

suspend fun Context.shareTextAsGibbFile(
    text: String,
    prefix: String? = null,
    nameType: String? = prefix
) {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss")
    val timestamp = LocalDateTime.now().format(formatter)
    val fileName = "${timestamp}_${nameType}.gibb"
    val fileContent = buildString {
        prefix?.let { append(it) }
        append(text)
    }
    val file = File(cacheDir, fileName).apply {
        writeText(fileContent)
    }

    val authority = "${packageName}.fileprovider"
    val uri = FileProvider.getUriForFile(this, authority, file)

    val sendIntent = Intent(Intent.ACTION_SEND).apply {
        type = "application/gibb"

        putExtra(Intent.EXTRA_STREAM, uri)

        clipData = ClipData.newUri(contentResolver, "file", uri)

        addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
    }

    val chooser = Intent.createChooser(sendIntent, "Share .gibb file").apply {
        addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
    }

    withContext(Dispatchers.Main) {
        startActivity(chooser.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
    }
}

suspend fun Context.openMacroInMacroDroid(macroExportText: String) {
    val ts = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"))
    val file = File(cacheDir, "$ts.macro").apply { writeText(macroExportText) }

    val uri: Uri = FileProvider.getUriForFile(
        this,
        "${packageName}.fileprovider",
        file
    )

    val viewIntent = Intent(Intent.ACTION_VIEW).apply {
        setDataAndType(uri, "application/octet-stream")
        setPackage(MACRO_DROID_PKG)
        addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        clipData = ClipData.newUri(contentResolver, "macro", uri)
    }

    grantUriPermission(
        MACRO_DROID_PKG,
        uri,
        Intent.FLAG_GRANT_READ_URI_PERMISSION
    )

    try {
        withContext(Dispatchers.Main) { startActivity(viewIntent) }
        return
    } catch (_: ActivityNotFoundException) {
    }

    val sendIntent = Intent(Intent.ACTION_SEND).apply {
        type = "application/octet-stream"
        setPackage(MACRO_DROID_PKG)
        putExtra(Intent.EXTRA_STREAM, uri)
        addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        clipData = ClipData.newUri(contentResolver, "macro", uri)
    }

    grantUriPermission(
        MACRO_DROID_PKG,
        uri,
        Intent.FLAG_GRANT_READ_URI_PERMISSION
    )

    try {
        withContext(Dispatchers.Main) { startActivity(sendIntent) }
    } catch (_: ActivityNotFoundException) {
        val chooser = Intent.createChooser(sendIntent, "Open in MacroDroid").apply {
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }
        withContext(Dispatchers.Main) { startActivity(chooser) }
    }
}

suspend fun Context.cleanupShareTempFiles(): Int = withContext(Dispatchers.IO) {
    val cacheRoot = cacheDir.canonicalFile
    val authority = "${packageName}.fileprovider"

    fun hasTargetExtension(name: String): Boolean {
        val lower = name.lowercase()
        return lower.endsWith(".gibb") || lower.endsWith(".macro")
    }

    var deleted = 0
    val files = cacheRoot.listFiles().orEmpty()

    for (f in files) {
        runCatching {
            if (!f.isFile) return@runCatching

            val canonical = f.canonicalFile
            if (canonical.parentFile?.canonicalFile != cacheRoot) return@runCatching
            if (!hasTargetExtension(canonical.name)) return@runCatching

            runCatching {
                val uri =
                    FileProvider.getUriForFile(this@cleanupShareTempFiles, authority, canonical)
                revokeUriPermission(
                    uri,
                    Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION
                )
            }

            val ok = canonical.delete()
            if (!ok) {
                runCatching {
                    val uri =
                        FileProvider.getUriForFile(this@cleanupShareTempFiles, authority, canonical)
                    contentResolver.delete(uri, null, null)
                }
                if (canonical.delete()) {
                    deleted++
                }
            } else {
                deleted++
            }
        }
    }
    deleted
}

fun Context.shareText(
    text: String,
    subject: String? = null,
    chooserTitle: String? = null
): Boolean {
    val sendIntent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, text)
        if (!subject.isNullOrEmpty()) {
            putExtra(Intent.EXTRA_SUBJECT, subject)
        }
    }

    val chooser = Intent.createChooser(sendIntent, chooserTitle).apply {
        if (this@shareText !is Activity) {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
    }

    return try {
        startActivity(chooser)
        true
    } catch (_: ActivityNotFoundException) {
        false
    }
}
