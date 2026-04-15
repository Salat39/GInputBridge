package com.salat.gbinder.filedownloader.domain.entity

import android.net.Uri

sealed class DownloadState {
    data class Progress(val percent: Int) : DownloadState()
    data class Success(val uri: Uri) : DownloadState()
    data class Error(val message: String) : DownloadState()
}
