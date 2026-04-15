package com.salat.gbinder.filedownloader.domain.usecases

import com.salat.gbinder.filedownloader.domain.repository.FileDownloaderRepository

class ClearDownloadedFilesUseCase(private val repository: FileDownloaderRepository) {
    suspend fun execute() = repository.clear()
}
