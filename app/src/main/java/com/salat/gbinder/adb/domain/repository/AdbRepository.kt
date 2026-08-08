package com.salat.gbinder.adb.domain.repository

import com.salat.gbinder.adb.data.entity.AdbConnectionState
import com.salat.gbinder.entity.AdbRecentTaskInfo
import kotlinx.coroutines.flow.StateFlow

interface AdbRepository {
    val connectionState: StateFlow<AdbConnectionState>

    suspend fun execute(command: String): String

    suspend fun setAtlasWheelSettings(): String

    suspend fun isAppInFreeform(packageName: String): Boolean?

    suspend fun isAppLaunched(packageName: String): Boolean

    suspend fun getTaskId(packageName: String): Int?

    suspend fun forceStop(packageName: String): String

    suspend fun forceStop(vararg packageNames: String): String

    suspend fun allowActivateVpnAppOp(packageName: String): String

    suspend fun enablePackage(packageName: String): String

    suspend fun enableAndLaunchApp(packageName: String, launchActivity: String?): String

    suspend fun disableUserPackage(packageName: String): String

    suspend fun addAppToCarLauncher(packageName: String, appName: String): String

    suspend fun removeAppFromCarLauncher(packageName: String): String

    suspend fun clearCarLauncherApps(): String

    suspend fun removeAppsFromCarLauncher(
        packageNames: List<String>,
        onProgressTick: (suspend () -> Unit)?
    ): String

    suspend fun addAppsToCarLauncher(
        apps: List<Pair<String, String>>,
        delay: Long = 0L,
        onProgressTick: (suspend () -> Unit)? = null
    ): String

    suspend fun getNativeLauncherApps(): List<String>

    suspend fun restartLauncher3(): String

    suspend fun minimize(taskId: Int)

    suspend fun getForegroundAppPackageName(): String?

    suspend fun getRecentTasksFromActivitiesDump(): List<AdbRecentTaskInfo>

    suspend fun pressHome()

    suspend fun pressBack()
}
