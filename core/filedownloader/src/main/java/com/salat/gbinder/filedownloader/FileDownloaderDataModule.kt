package com.salat.gbinder.filedownloader

import android.content.Context
import com.salat.gbinder.filedownloader.data.repository.FileDownloaderRepositoryImpl
import com.salat.gbinder.filedownloader.domain.repository.FileDownloaderRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FileDownloaderDataModule {

    @Provides
    @Singleton
    fun provideFileDownloaderRepository(@ApplicationContext context: Context): FileDownloaderRepository =
        FileDownloaderRepositoryImpl(context)
}
