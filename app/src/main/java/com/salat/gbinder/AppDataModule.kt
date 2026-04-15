package com.salat.gbinder

import android.content.Context
import com.salat.gbinder.coroutines.IoCoroutineScope
import com.salat.gbinder.datastore.DataStoreRepository
import com.salat.gbinder.datastore.DataStoreRepositoryImpl
import com.salat.gbinder.datastore.FavoriteStorageRepository
import com.salat.gbinder.datastore.FavoriteStorageRepositoryImpl
import com.salat.gbinder.datastore.KeyBindStorageRepository
import com.salat.gbinder.datastore.KeyBindStorageRepositoryImpl
import com.salat.gbinder.datastore.LauncherStorageRepository
import com.salat.gbinder.datastore.LauncherStorageRepositoryImpl
import com.salat.gbinder.features.launcher.LauncherDataRepository
import com.salat.gbinder.features.launcher.LauncherDataRepositoryImpl
import com.salat.gbinder.util.SystemAppsLightRepository
import com.salat.gbinder.util.SystemAppsLightRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppDataModule {

    @Provides
    @Singleton
    fun provideSystemAppsLightRepository(
        @ApplicationContext context: Context
    ): SystemAppsLightRepository = SystemAppsLightRepositoryImpl(context)

    @Provides
    @Singleton
    fun provideDataStoreRepository(
        @ApplicationContext context: Context
    ): DataStoreRepository = DataStoreRepositoryImpl(context)

    @Provides
    @Singleton
    fun provideKeyBindStorageRepository(
        dataStore: DataStoreRepository
    ): KeyBindStorageRepository = KeyBindStorageRepositoryImpl(dataStore)

    @Provides
    @Singleton
    fun provideFavoriteStorageRepository(
        dataStore: DataStoreRepository
    ): FavoriteStorageRepository = FavoriteStorageRepositoryImpl(dataStore)

    @Provides
    @Singleton
    fun provideLauncherStorageRepository(
        dataStore: DataStoreRepository
    ): LauncherStorageRepository = LauncherStorageRepositoryImpl(dataStore)

    @Provides
    @Singleton
    fun provideLauncherDataRepository(
        @ApplicationContext context: Context,
        @IoCoroutineScope scope: CoroutineScope,
        storage: LauncherStorageRepository,
        systemApps: SystemAppsLightRepository
    ): LauncherDataRepository = LauncherDataRepositoryImpl(context, scope, storage, systemApps)
}
