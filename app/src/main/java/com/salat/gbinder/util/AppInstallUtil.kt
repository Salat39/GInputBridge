package com.salat.gbinder.util

import android.content.ClipData
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Environment
import androidx.core.content.FileProvider
import androidx.core.net.toUri
import timber.log.Timber
import java.io.File

fun promptInstall(context: Context, apkUri: Uri) = runCatching {
    // English: Robust ACTION_VIEW for .apk with read grant + ClipData, no bad package pinning
    val intent = Intent(Intent.ACTION_VIEW).apply {
        setDataAndType(apkUri, "application/vnd.android.package-archive")
        addCategory(Intent.CATEGORY_DEFAULT)
        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_GRANT_READ_URI_PERMISSION)
        clipData = ClipData.newUri(context.contentResolver, "apk", apkUri)
    }

    // English: Optionally pin to a REAL package installer if present; otherwise don't pin at all
    val pm = context.packageManager
    val candidates = pm.queryIntentActivities(intent, 0)
    val knownInstallers = setOf(
        "com.google.android.packageinstaller",
        "com.android.packageinstaller",
        "com.android.permissioncontroller" // newer AOSP / Android 10+
    )
    val installerPkg = candidates.firstOrNull { it.activityInfo?.packageName in knownInstallers }
        ?.activityInfo?.packageName
    if (installerPkg != null) {
        intent.`package` = installerPkg
    } else {
        // Do NOT force `android` or anything else
    }

    context.startActivity(intent)
}.onFailure { Timber.e(it) }

/* @Suppress("DEPRECATION")
fun promptInstallViaActionInstall(context: Context, apkUri: Uri) = runCatching {
    // English: Use ACTION_INSTALL_PACKAGE; still grant read via ClipData + flag
    val intent = Intent(Intent.ACTION_INSTALL_PACKAGE).apply {
        data = apkUri
        // type is optional here; ACTION_INSTALL_PACKAGE infers from content
        putExtra(Intent.EXTRA_RETURN_RESULT, true) // if you startForResult
        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_GRANT_READ_URI_PERMISSION)
        clipData = ClipData.newUri(context.contentResolver, "apk", apkUri)
    }
    context.startActivity(intent)
} */

fun String.toContentUri(context: Context): Uri {
    // English: Normalize to Uri first
    val uri = runCatching { this.toUri() }.getOrNull()
    // Already content:// -> return as is
    if (uri != null && uri.scheme == "content") return uri

    // English: Build a File from either file:// or a raw path string
    val file = when {
        uri != null && uri.scheme == "file" -> File(uri.path ?: "")
        else -> File(this) // raw path
    }

    // English: Use FileProvider; ensure file is under paths declared in res/xml/file_paths.xml
    return FileProvider.getUriForFile(
        context,
        "${context.packageName}.fileprovider",
        file
    )
}

fun deletePreviousDownloadedApks(context: Context): Int {
    // English: App-specific "Download" dir (…/Android/data/<pkg>/files/Download)
    val downloadsDir = context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS)
    // English: Optional cache subdir you might be using (…/cache/apk)
    val cacheApkDir = File(context.cacheDir, "apk")

    var deleted = 0
    listOfNotNull(downloadsDir, cacheApkDir)
        .filter { it.exists() && it.isDirectory }
        .forEach { base ->
            val baseCanonical = base.canonicalFile
            // English: List only *.apk files, ignore subdirectories
            val candidates =
                base.listFiles { f -> f.isFile && f.name.endsWith(".apk", ignoreCase = true) }
                    ?.toList()
                    ?: emptyList()

            for (file in candidates) {
                runCatching {
                    // English: Double-check file really resides under our base dir
                    val canon = file.canonicalFile
                    if (!canon.path.startsWith(baseCanonical.path)) {
                        Timber.w("Skip deleting outside of base dir: %s", canon.path)
                        return@runCatching false
                    }
                    val ok = canon.delete()
                    if (!ok) {
                        Timber.w("Failed to delete: %s", canon.path)
                    }
                    ok
                }.onSuccess { ok ->
                    if (ok) deleted++
                }.onFailure { e ->
                    Timber.e(e, "Error deleting %s", file.absolutePath)
                }
            }
        }
    return deleted
}
