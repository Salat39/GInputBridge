package com.salat.gbinder.adb.domain.repository

import com.salat.gbinder.adb.data.entity.AdbConnectionState
import com.salat.gbinder.entity.AdbRecentTaskInfo
import kotlinx.coroutines.flow.Flow

interface AdbRepository {
    val connectionState: Flow<AdbConnectionState>

    suspend fun execute(command: String): String

    suspend fun isAppInFreeform(packageName: String): Boolean?

    suspend fun getTaskId(packageName: String): Int?

    suspend fun forceStop(packageName: String): String

    suspend fun forceStop(vararg packageNames: String): String

    suspend fun minimize(taskId: Int)

    suspend fun getForegroundAppPackageName(): String?

    suspend fun getRecentTasksFromActivitiesDump(): List<AdbRecentTaskInfo>

    suspend fun pressHome()

    suspend fun pressBack()
}
