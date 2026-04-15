package com.salat.gbinder.filedownloader.data.repository

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Build
import android.os.Environment
import androidx.core.net.toUri
import com.salat.gbinder.filedownloader.domain.entity.DownloadState
import com.salat.gbinder.filedownloader.domain.repository.FileDownloaderRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeoutOrNull
import timber.log.Timber
import java.io.File

class FileDownloaderRepositoryImpl(private val context: Context) : FileDownloaderRepository {

    companion object {
        private const val CYCLE_TIME = 250L
    }

    @Suppress("DEPRECATION")
    override suspend fun download(
        url: String,
        fileName: String,
        userAgent: String
    ): Flow<DownloadState> = flow {

        val request = DownloadManager.Request(url.toUri())
        request.allowScanningByMediaScanner()
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        if (Build.VERSION.SDK_INT >= 29) {
            // Use Scoped Storage for API 29 and above
            val destinationFile =
                File(context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), fileName)
            request.setDestinationUri(Uri.fromFile(destinationFile))
        } else {
            // For API < 29 we use the public directory
            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName)
        }

        // Request auth
        if (userAgent.isNotEmpty()) {
            request.addRequestHeader("User-Agent", userAgent)
        }
        /*try {
            request.addRequestHeader("Cookie", CookieManager.getInstance().getCookie(url))
        } catch (e: Exception) {
            Timber.e(e, "Error adding cookies to the request")
        }*/

        try {
            val dm = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
            val downloadId = try {
                dm.enqueue(request)
            } catch (_: SecurityException) {
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE)
                try {
                    dm.enqueue(request)
                } catch (ex: Exception) {
                    Timber.e(ex, "Error enqueuing the request after SecurityException")
                    emit(DownloadState.Error("Error enqueuing the download: ${ex.message}"))
                    return@flow
                }
            }

            emit(DownloadState.Progress(0))

            // Track download progress
            val finalState = withTimeoutOrNull(60_000) {
                while (true) {
                    val query = DownloadManager.Query().setFilterById(downloadId)
                    dm.query(query)?.use { cursor ->
                        if (cursor.moveToFirst()) {
                            val totalIndex =
                                cursor.getColumnIndex(DownloadManager.COLUMN_TOTAL_SIZE_BYTES)
                            val downloadedIndex =
                                cursor.getColumnIndex(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR)
                            val progress = if (totalIndex >= 0 && downloadedIndex >= 0) {
                                val total = cursor.getLong(totalIndex)
                                val downloaded = cursor.getLong(downloadedIndex)
                                if (total > 0) ((downloaded * 100) / total).toInt() else 0
                            } else {
                                // Column missing, can't calculate progress
                                -1
                            }
                            if (progress >= 0) {
                                emit(DownloadState.Progress(progress))
                            }

                            val statusIndex = cursor.getColumnIndex(DownloadManager.COLUMN_STATUS)
                            val reasonIndex = cursor.getColumnIndex(DownloadManager.COLUMN_REASON)
                            val reason = if (reasonIndex >= 0) cursor.getInt(reasonIndex) else -1
                            if (statusIndex >= 0) {
                                when (cursor.getInt(statusIndex)) {
                                    DownloadManager.STATUS_SUCCESSFUL -> {
                                        val uriCol =
                                            cursor.getColumnIndex(DownloadManager.COLUMN_LOCAL_URI)
                                        val uriString =
                                            if (uriCol >= 0) cursor.getString(uriCol) else null
                                        val fileUri = uriString?.toUri()
                                        return@withTimeoutOrNull DownloadState.Success(
                                            fileUri ?: Uri.EMPTY
                                        )
                                    }

                                    DownloadManager.STATUS_FAILED -> {
                                        return@withTimeoutOrNull DownloadState.Error(
                                            "Download failed"
                                        )
                                    }

                                    DownloadManager.STATUS_PAUSED -> when (reason) {
                                        DownloadManager.PAUSED_WAITING_FOR_NETWORK,
                                        DownloadManager.PAUSED_QUEUED_FOR_WIFI -> {
                                            return@withTimeoutOrNull DownloadState.Error(
                                                "No network"
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                    delay(CYCLE_TIME)
                }
            }

            if (finalState is DownloadState) {
                emit(finalState)
            } else {
                emit(DownloadState.Error("Timeout"))
            }
        } catch (e: IllegalArgumentException) {
            Timber.e(e, "Download Manager app is disabled")
            emit(DownloadState.Error("Download Manager disabled"))
            return@flow
        }
    }

    override suspend fun clear(): Int = withContext(Dispatchers.IO) {
        // English: App-specific Download dir (…/Android/data/<pkg>/files/Download)
        val downloadsDir = context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS)
        // English: Optional cache subdir you might be using for temporary APKs
        val cacheApkDir = File(context.cacheDir, "apk")

        var deleted = 0

        fun deleteApkFilesSafely(base: File) {
            if (!base.exists() || !base.isDirectory) return
            val baseCanonical = runCatching { base.canonicalFile }.getOrNull() ?: return

            // English: Only delete *.apk files directly under the base directory
            val candidates = base.listFiles { f -> f.isFile && f.name.endsWith(".apk", ignoreCase = true) }
                ?.toList()
                ?: emptyList()

            for (file in candidates) {
                runCatching {
                    val canon = file.canonicalFile
                    // English: Ensure file is inside the expected directory (avoid path traversal)
                    if (!canon.path.startsWith(baseCanonical.path)) {
                        Timber.w("Skip deleting outside of base dir: %s", canon.path)
                        false
                    } else {
                        val ok = canon.delete()
                        if (!ok) Timber.w("Failed to delete: %s", canon.path)
                        ok
                    }
                }.onSuccess { ok ->
                    if (ok) deleted++
                }.onFailure { e ->
                    // English: Never crash, just log
                    Timber.e(e, "Error deleting %s", file.absolutePath)
                }
            }
        }

        listOfNotNull(downloadsDir, cacheApkDir).forEach { deleteApkFilesSafely(it) }

        // English: For API < 29 you previously stored to public Downloads.
        // We deliberately DO NOT delete from public Downloads to avoid permissions & accidental removal.
        // If needed, consider switching old <29 flow to app-specific dir or tracking DownloadManager IDs.

        deleted
    }
}
