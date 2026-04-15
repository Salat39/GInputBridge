package com.salat.gbinder.car

import android.content.Context
import com.salat.gbinder.car.data.repository.CarRepositoryImpl
import com.salat.gbinder.car.domain.repository.CarRepository
import com.salat.gbinder.coroutines.IoCoroutineScope
import com.salat.gbinder.statekeeper.domain.repository.StateKeeperRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CarDataModule {

    @Provides
    @Singleton
    fun provideCarRepository(
        @ApplicationContext context: Context,
        @IoCoroutineScope scope: CoroutineScope,
        stateKeeper: StateKeeperRepository
    ): CarRepository = CarRepositoryImpl(context, scope, stateKeeper)
}
