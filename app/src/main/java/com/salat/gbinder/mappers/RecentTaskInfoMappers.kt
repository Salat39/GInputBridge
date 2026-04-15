package com.salat.gbinder.mappers

import com.salat.gbinder.entity.AdbRecentTaskInfo
import com.salat.gbinder.entity.DisplayLauncherApp
import com.salat.gbinder.entity.DisplayRecentTaskInfo

fun AdbRecentTaskInfo.toDisplay(app: DisplayLauncherApp): DisplayRecentTaskInfo =
    DisplayRecentTaskInfo(
        // App
        id = app.id,
        appName = app.appName,
        iconRef = app.iconRef,
        customIcon = app.customIcon,
        isMedia = app.isMedia,
        launcherActivity = app.launcherActivity,
        availableActivity = app.availableActivity,

        // task
        taskId = taskId,
        packageName = packageName,
        visible = visible,
        visibleRequested = visibleRequested,
        topResumed = topResumed,
        activityState = activityState,
        nowVisible = nowVisible,
        lastVisibleTime = lastVisibleTime,
        baseDir = baseDir,
        dataDir = dataDir
    )
