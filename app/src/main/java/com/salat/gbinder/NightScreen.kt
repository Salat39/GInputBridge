package com.salat.gbinder

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.lifecycle.Lifecycle
import java.lang.ref.WeakReference

class NightScreen : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activeRef = WeakReference(this)
        setContentView(View(this).apply {
            setBackgroundColor(Color.BLACK)
            setOnClickListener { finish() }
        })
    }

    override fun onStart() {
        super.onStart()
        notifyPomogatorNightMode(on = true)
    }

    override fun onStop() {
        notifyPomogatorNightMode(on = false)
        super.onStop()
    }

    override fun onDestroy() {
        if (activeRef?.get() === this) activeRef = null
        super.onDestroy()
    }

    private fun notifyPomogatorNightMode(on: Boolean) {
        runCatching {
            sendBroadcast(
                Intent(POMOGATOR_NIGHT_MODE_ACTION).apply {
                    setPackage(POMOGATOR_PACKAGE)
                    putExtra(POMOGATOR_NIGHT_MODE_EXTRA, if (on) "ON" else "OFF")
                }
            )
        }
    }

    companion object {
        private const val POMOGATOR_PACKAGE = "ru.pomogator"
        private const val POMOGATOR_NIGHT_MODE_ACTION = "ru.pomogator.NIGHT_MODE"
        private const val POMOGATOR_NIGHT_MODE_EXTRA = "state"

        @Volatile
        private var activeRef: WeakReference<NightScreen>? = null

        fun closeIfVisible(): Boolean {
            val activity = activeRef?.get() ?: return false
            if (!activity.lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)) return false
            activity.finish()
            return true
        }
    }
}
