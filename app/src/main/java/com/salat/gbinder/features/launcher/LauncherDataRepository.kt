package com.salat.gbinder.features.launcher

import android.graphics.Bitmap
import android.net.Uri
import com.salat.gbinder.entity.DisplayLauncherApp
import com.salat.gbinder.entity.DisplayLauncherConfig
import com.salat.gbinder.entity.DisplayLauncherItem
import kotlinx.coroutines.flow.StateFlow

interface LauncherDataRepository {
    val myAppsItems: StateFlow<List<DisplayLauncherItem>?>

    val allApps: StateFlow<List<DisplayLauncherApp>>

    val settingsConfig: StateFlow<DisplayLauncherConfig?>

    suspend fun saveMyApps(items: List<DisplayLauncherItem>)

    suspend fun rebuildMyApps()

    suspend fun applyIcon(id: Long, packageName: String, image: Uri)

    suspend fun clearIcon(id: Long, packageName: String)

    suspend fun saveIcon(id: Long, image: Bitmap): String
}
