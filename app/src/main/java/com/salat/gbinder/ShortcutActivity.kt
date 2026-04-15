package com.salat.gbinder

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.ContextThemeWrapper
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.salat.gbinder.features.launcher.LauncherEntryActivity

class ShortcutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showOptionDialog()
    }

    private fun showOptionDialog() {
        val dialogContext: Context = ContextThemeWrapper(this, R.style.Theme_GBinder)

        val titles = buildList {
            add(getString(R.string.toggle_glauncher))
        }

        AlertDialog.Builder(dialogContext)
            .setTitle(getString(R.string.select_action))
            .setItems((titles).toTypedArray()) { _, which ->
                if (which == 0) {
                    performToggleLauncher()
                }
            }
            .setOnCancelListener {
                finish()
            }
            .show()
    }

    @Suppress("DEPRECATION")
    private fun performToggleLauncher() {
        val shortcutIntent = Intent(this, LauncherEntryActivity::class.java).apply {
            action = Intent.ACTION_VIEW
        }

        val legacyShortcutIntent = Intent().apply {
            putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent)
            putExtra(Intent.EXTRA_SHORTCUT_NAME, getString(R.string.launcher_name)) // TODO
            putExtra(
                Intent.EXTRA_SHORTCUT_ICON_RESOURCE,
                Intent.ShortcutIconResource.fromContext(
                    this@ShortcutActivity,
                    R.mipmap.ic_app_launcher
                )
            )
        }
        setResult(RESULT_OK, legacyShortcutIntent)
        finish()
    }
}
