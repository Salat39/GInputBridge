package com.salat.gbinder.components

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import java.net.URISyntaxException

fun Intent.extractPackageName(context: Context): String? {
    return try {
        this.component?.packageName
            ?: this.`package`
            ?: context.packageManager
                .resolveActivity(this, PackageManager.MATCH_DEFAULT_ONLY)
                ?.activityInfo
                ?.packageName
    } catch (e: URISyntaxException) {
        e.printStackTrace()
        null
    }
}
