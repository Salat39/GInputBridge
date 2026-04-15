package com.salat.gbinder.repository

import com.salat.gbinder.statekeeper.domain.repository.StateKeeperRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ServiceComponent

@Module
@InstallIn(ServiceComponent::class)
object AccessibilityModule {

    @Provides
    fun provideAccessibilityRepository(stateKeeper: StateKeeperRepository): AccessibilityRepository =
        AccessibilityRepositoryImpl(stateKeeper)
}
