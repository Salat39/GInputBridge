package com.salat.gbinder.adb

import com.salat.gbinder.adb.data.repository.AdbRepositoryImpl
import com.salat.gbinder.adb.domain.repository.AdbRepository
import com.salat.gbinder.datastore.DataStoreRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AdbRepositoryDataModule {

    @Provides
    @Singleton
    fun provideAdbRepository(dataStore: DataStoreRepository): AdbRepository = AdbRepositoryImpl(dataStore)
}
