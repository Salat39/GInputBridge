package com.salat.gbinder.features.geelyLauncher

import android.content.ContentResolver
import android.content.ContentUris
import android.content.ContentValues
import android.content.Context
import androidx.core.net.toUri
import timber.log.Timber

private val APP_INFO_URI = "content://com.geely.appstore.AppstoreProvider/appInfo".toUri()
private const val ID_COLUMN = "_id"
private const val PACKAGE_COLUMN = "package_name"

fun isGeelyAppStoreProviderRegistered(context: Context): Boolean {
    return try {
        context.contentResolver.acquireContentProviderClient(APP_INFO_URI)?.use {
            true
        } ?: false
    } catch (e: Exception) {
        Timber.e(e)
        false
    }
}

fun ContentResolver.queryNativeLauncherPackages(): List<String> =
    queryNativeLauncherRows().map { it.second }.distinct()

fun ContentResolver.addNativeLauncherApps(apps: List<Pair<String, String>>) {
    apps.forEach { (packageName, appName) ->
        insert(APP_INFO_URI, nativeLauncherValues(packageName, appName))
    }
}

// Geely appstore ignores the where clause on appInfo and clears the whole table - delete rows one by one by id
fun ContentResolver.removeNativeLauncherApps(packageNames: Collection<String>) {
    if (packageNames.isEmpty()) return
    queryNativeLauncherRows()
        .filter { (_, packageName) -> packageName in packageNames }
        .forEach { (id, _) -> delete(ContentUris.withAppendedId(APP_INFO_URI, id), null, null) }
}

private fun ContentResolver.queryNativeLauncherRows(): List<Pair<Long, String>> {
    val cursor = query(APP_INFO_URI, arrayOf(ID_COLUMN, PACKAGE_COLUMN), null, null, null)
        ?: return emptyList()
    return cursor.use {
        buildList {
            while (it.moveToNext()) {
                val packageName = it.getString(1) ?: continue
                add(it.getLong(0) to packageName)
            }
        }
    }
}

private fun nativeLauncherValues(packageName: String, appName: String) = ContentValues().apply {
    put(PACKAGE_COLUMN, packageName)
    put("apk_name", appName.ifBlank { packageName })
    put("apk_icon", "none")
    put("apk_size", "0")
    put("apk_version_name", "1.0")
    put("apk_version_code", 1L)
    put("apk_type", "third")
    put("apk_type_name", "third")
    put("display_screen", "0")
}
