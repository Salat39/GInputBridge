@file:Suppress("DEPRECATION")

package com.salat.gbinder

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import com.salat.gbinder.coroutines.IoCoroutineScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class PhoneCallActivity : ComponentActivity() {

    @Inject
    @IoCoroutineScope
    lateinit var ioScope: CoroutineScope

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(0, 0)

        if (intent?.action == Intent.ACTION_CALL) {
            val uri: Uri? = intent.data
            val number: String? = uri?.schemeSpecificPart

            number?.let {
                val num = it.replace(" ", "")
                ioScope.launch { GlobalState.requestPhoneCallFlow.emit(num) }
                Timber.d("Phone call signal $num")
            }
        }

        // Terminate the activity immediately after creation
        finish()
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(0, 0)
    }
}
