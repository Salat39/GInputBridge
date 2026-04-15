package com.salat.gbinder.filedownloader.domain.usecases

import com.salat.gbinder.filedownloader.domain.repository.FileDownloaderRepository

class DownloadFileUseCase(private val repository: FileDownloaderRepository) {
    suspend fun execute(url: String, fileName: String, userAgent: String) =
        repository.download(url, fileName, userAgent)
}
