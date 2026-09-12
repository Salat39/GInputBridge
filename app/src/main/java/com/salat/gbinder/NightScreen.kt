package com.salat.gbinder

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

    override fun onDestroy() {
        if (activeRef?.get() === this) activeRef = null
        super.onDestroy()
    }

    companion object {
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
