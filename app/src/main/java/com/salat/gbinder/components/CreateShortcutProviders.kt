package com.salat.gbinder.components

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import androidx.compose.runtime.Immutable
import com.salat.gbinder.entity.DisplayIconRef

@Immutable
data class CreateShortcutProvider(
    val label: String,
    val packageName: String,
    val activityName: String,
    val icon: DisplayIconRef?
) {
    fun createIntent(): Intent =
        Intent(Intent.ACTION_CREATE_SHORTCUT).apply {
            addCategory(Intent.CATEGORY_DEFAULT)
            component = ComponentName(packageName, activityName)
        }
}

private val HIDE_SHORTCUT_PACKAGES = setOf(
    "com.alphainventor.filemanager",
    "com.android.settings",
)

private fun isHideShortcutProvider(packageName: String, activityName: String): Boolean {
    if (packageName in HIDE_SHORTCUT_PACKAGES) return true
    if (packageName == "com.android.contacts" &&
        activityName.substringAfterLast('.') == "ContactShortcut"
    ) {
        return true
    }
    return false
}

fun Context.queryCreateShortcutProviders(): List<CreateShortcutProvider> {
    val pm = packageManager
    val found = LinkedHashMap<String, CreateShortcutProvider>()

    fun addFromResolve(intent: Intent, flags: Int) {
        @Suppress("DEPRECATION")
        val resolves = pm.queryIntentActivities(intent, flags)
        for (ri in resolves) {
            val ai = ri.activityInfo ?: continue
            if (!ai.exported) continue
            if (isHideShortcutProvider(ai.packageName, ai.name)) continue
            val key = "${ai.packageName}/${ai.name}"
            if (key in found) continue
            val label = ri.loadLabel(pm)?.toString()?.ifBlank { null }
                ?: ai.loadLabel(pm)?.toString()?.ifBlank { null }
                ?: ai.packageName
            val iconRes = when {
                ai.icon != 0 -> ai.icon
                ai.applicationInfo.icon != 0 -> ai.applicationInfo.icon
                else -> 0
            }
            val versionCode = pm.getPackageInfo(ai.packageName, 0).longVersionCode
            found[key] = CreateShortcutProvider(
                label = label,
                packageName = ai.packageName,
                activityName = ai.name,
                icon = iconRes.takeIf { it != 0 }?.let {
                    DisplayIconRef(
                        packageName = ai.packageName,
                        resId = it,
                        densityDpi = resources.displayMetrics.densityDpi,
                        versionCode = versionCode
                    )
                }
            )
        }
    }

    val base = Intent(Intent.ACTION_CREATE_SHORTCUT).apply {
        addCategory(Intent.CATEGORY_DEFAULT)
    }
    addFromResolve(base, 0)

    @Suppress("DEPRECATION")
    val apps = pm.getInstalledApplications(0)
    for (app in apps) {
        val scoped = Intent(Intent.ACTION_CREATE_SHORTCUT).apply {
            addCategory(Intent.CATEGORY_DEFAULT)
            setPackage(app.packageName)
        }
        addFromResolve(scoped, 0)
    }

    return found.values.sortedBy { it.label.lowercase() }
}
