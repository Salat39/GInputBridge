package com.salat.gbinder.remoteconfig

import com.salat.gbinder.remoteconfig.domain.repository.RemoteConfigRepository
import com.salat.gbinder.remoteconfig.domain.usecases.GetAppUpdateFlowUseCase
import com.salat.gbinder.remoteconfig.domain.usecases.InitRemoteConfigUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object RemoteConfigDomainModule {

    @Provides
    fun provideInitRemoteConfigUseCase(remoteConfig: RemoteConfigRepository) =
        InitRemoteConfigUseCase(remoteConfig)

    @Provides
    fun provideGetAppUpdateFlowUseCase(remoteConfig: RemoteConfigRepository) =
        GetAppUpdateFlowUseCase(remoteConfig)
}
