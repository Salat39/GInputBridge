package com.salat.gbinder.mappers

import com.salat.gbinder.entity.DisplayAppUpdate
import com.salat.gbinder.entity.UiDownloadState
import com.salat.gbinder.filedownloader.domain.entity.DownloadState
import com.salat.gbinder.remoteconfig.domain.entity.AppUpdateInfo

fun AppUpdateInfo.toDisplay() = DisplayAppUpdate(
    version = version,
    size = size,
    text = text,
    code = code,
    downloadUrl = downloadUrl,
    infoUrl = infoUrl,
    mandatory = mandatory
)

fun DownloadState.toUi(): UiDownloadState = when (this) {
    is DownloadState.Progress -> UiDownloadState.InProgress(percent.coerceIn(0, 100))

    is DownloadState.Success -> UiDownloadState.Success(uri.toString())

    is DownloadState.Error -> UiDownloadState.Error(message)
}
