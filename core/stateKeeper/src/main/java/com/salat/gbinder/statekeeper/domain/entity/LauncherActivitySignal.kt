package com.salat.gbinder.statekeeper.domain.entity

import android.graphics.Bitmap
import android.net.Uri

sealed class LauncherActivitySignal() {
    data object OnBackPressed : LauncherActivitySignal()
    data object OnPause : LauncherActivitySignal()
    data object OnResume : LauncherActivitySignal()
    class ApplyGroupDivider(val id: Long, val title: String) : LauncherActivitySignal()
    class CreateShortcut(
        val title: String,
        val packageName: String,
        val intent: String,
        val bitmap: Bitmap?,
    ) : LauncherActivitySignal()

    class ApplyNewIcon(val id: Long, val packageName: String, val uri: Uri) :
        LauncherActivitySignal()

    class CancelIcon(val id: Long, val packageName: String) : LauncherActivitySignal()
    class ApplyFreezeApp(val packageName: String) : LauncherActivitySignal()
}
