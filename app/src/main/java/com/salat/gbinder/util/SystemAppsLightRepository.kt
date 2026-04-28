package com.salat.gbinder.util

import com.salat.gbinder.entity.InstalledAppInfoRef

interface SystemAppsLightRepository {
    suspend fun getAllApps(
        roundIcon: Boolean,
        mediaSort: Boolean,
        iconQuality: Int
    ): List<InstalledAppInfoRef>

    suspend fun getLauncherApps(
        roundIcon: Boolean,
        mediaSort: Boolean,
        iconQuality: Int
    ): List<InstalledAppInfoRef>

    suspend fun getApps(
        roundIcon: Boolean,
        iconQuality: Int,
        vararg packageNames: String
    ): List<InstalledAppInfoRef>

    fun isPackageInstalled(packageName: String): Boolean

    fun isSystemApp(packageName: String): Boolean

    fun isDebugMInstalled(): Boolean

    fun isMConfigInstalled(): Boolean
}
