package com.salat.gbinder.filedownloader

import com.salat.gbinder.filedownloader.domain.repository.FileDownloaderRepository
import com.salat.gbinder.filedownloader.domain.usecases.ClearDownloadedFilesUseCase
import com.salat.gbinder.filedownloader.domain.usecases.DownloadFileUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object FileDownloaderDomainModule {

    @Provides
    fun provideDownloadFileUseCase(repository: FileDownloaderRepository) =
        DownloadFileUseCase(repository)

    @Provides
    fun provideClearDownloadedFilesUseCase(repository: FileDownloaderRepository) =
        ClearDownloadedFilesUseCase(repository)
}
