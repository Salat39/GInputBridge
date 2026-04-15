package com.salat.gbinder.features.launcher

import android.content.ContentResolver
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.OpenableColumns
import androidx.core.content.FileProvider
import androidx.documentfile.provider.DocumentFile
import timber.log.Timber
import java.io.File

object IconUriUtils {

    fun iconFileNameToContentUri(context: Context, fileName: String): Uri? {
        try {
            val file = File(File(context.filesDir, "icons"), fileName)
            if (!file.exists() || !file.isFile) return null

            val authority = "${context.packageName}.fileprovider"
            return FileProvider.getUriForFile(context, authority, file)
        } catch (e: Exception) {
            Timber.e(e)
        }
        return null
    }

    fun contentUriToIconFileName(context: Context, uri: Uri): String? {
        try {
            queryDisplayName(context.contentResolver, uri)?.let { return it }

            DocumentFile.fromSingleUri(context, uri)?.name?.let { return it }

            return uri.lastPathSegment?.substringAfterLast('/')
        } catch (e: Exception) {
            Timber.e(e)
        }
        return null
    }

    private fun queryDisplayName(resolver: ContentResolver, uri: Uri): String? {
        var cursor: Cursor? = null
        return try {
            cursor = resolver.query(
                uri,
                arrayOf(OpenableColumns.DISPLAY_NAME),
                null,
                null,
                null
            )
            if (cursor != null && cursor.moveToFirst()) {
                val idx = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                if (idx != -1) cursor.getString(idx) else null
            } else null
        } catch (_: Exception) {
            null
        } finally {
            cursor?.close()
        }
    }
}
