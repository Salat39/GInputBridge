package com.salat.gbinder.remoteconfig

import com.salat.gbinder.remoteconfig.data.repository.RemoteConfigRepositoryImpl
import com.salat.gbinder.remoteconfig.domain.repository.RemoteConfigRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteConfigDataModule {

    @Provides
    @Singleton
    fun provideRemoteConfigRepository(): RemoteConfigRepository = RemoteConfigRepositoryImpl()
}
