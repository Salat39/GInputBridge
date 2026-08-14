package com.salat.gbinder.features.carFunctions

import android.content.Context
import android.graphics.PixelFormat
import android.view.Gravity
import android.view.WindowManager
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.setViewTreeLifecycleOwner
import androidx.savedstate.setViewTreeSavedStateRegistryOwner
import com.salat.gbinder.components.ComposeWindowLifecycleOwner
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

object CarFunctionToast {
    private const val SHOW_MS = 3_000L
    private const val HEIGHT_CUSTOM_TOAST = 575

    private const val PLAQUE_ALPHA = 0.75f
    private val plaqueColor = Color(0xFF15181C).copy(alpha = PLAQUE_ALPHA)
    private val plaqueShape = RoundedCornerShape(60.dp)
    private val textColor = Color.White

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    private var title by mutableStateOf("")
    private var subtitle by mutableStateOf("")
    private var composeView: ComposeView? = null
    private var windowManager: WindowManager? = null
    private var layoutParams: WindowManager.LayoutParams? = null
    private var lifecycleOwner: ComposeWindowLifecycleOwner? = null
    private var hideJob: Job? = null

    suspend fun show(context: Context, title: String, subtitle: String) {
        withContext(Dispatchers.Main) {
            showOnMain(context.applicationContext, title, subtitle)
        }
    }

    suspend fun show(context: Context, text: String) {
        val newline = text.indexOf('\n')
        if (newline >= 0) {
            show(context, text.substring(0, newline), text.substring(newline + 1).trimStart())
        } else {
            show(context, text, "")
        }
    }

    suspend fun hide() {
        withContext(Dispatchers.Main) {
            detachOverlay()
        }
    }

    private fun showOnMain(context: Context, titleText: String, subtitleText: String) {
        hideJob?.cancel()
        title = titleText
        subtitle = subtitleText

        if (composeView == null) {
            runCatching { attachOverlay(context) }
                .onFailure {
                    Timber.e(it)
                    return
                }
        }

        hideJob = scope.launch {
            delay(SHOW_MS)
            detachOverlay()
        }
    }

    private fun attachOverlay(context: Context) {
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        windowManager = wm

        val owner = ComposeWindowLifecycleOwner().apply {
            performRestore(null)
            setCurrentState(Lifecycle.State.RESUMED)
        }
        lifecycleOwner = owner

        val params = WindowManager.LayoutParams(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE or
                WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL or
                WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN or
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            PixelFormat.TRANSLUCENT,
        ).apply {
            gravity = Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL
            y = HEIGHT_CUSTOM_TOAST
        }
        layoutParams = params

        val view = ComposeView(context).apply {
            setViewTreeLifecycleOwner(owner)
            setViewTreeSavedStateRegistryOwner(owner)
            setContent {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center,
                ) {
                    Box(
                        modifier = Modifier
                            .wrapContentWidth(unbounded = true)
                            .background(plaqueColor, plaqueShape)
                            .padding(horizontal = 30.dp, vertical = 30.dp),
                        contentAlignment = Alignment.Center,
                    ) {
                        Column(
                            modifier = Modifier.wrapContentWidth(unbounded = true),
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            ToastLine(title)
                            if (subtitle.isNotEmpty()) {
                                ToastLine(subtitle)
                            }
                        }
                    }
                }
            }
        }
        composeView = view
        wm.addView(view, params)
    }

    @Composable
    private fun ToastLine(text: String) {
        Text(
            text = text,
            color = textColor,
            fontSize = 40.sp,
            fontFamily = FontFamily.Default,
            textAlign = TextAlign.Center,
            maxLines = 1,
            softWrap = false,
            overflow = TextOverflow.Visible,
        )
    }

    private fun detachOverlay() {
        hideJob?.cancel()
        hideJob = null

        val wm = windowManager
        val view = composeView
        composeView = null
        windowManager = null
        layoutParams = null

        lifecycleOwner?.setCurrentState(Lifecycle.State.DESTROYED)
        lifecycleOwner = null

        if (wm != null && view != null) {
            runCatching { wm.removeView(view) }.onFailure { Timber.e(it) }
        }
    }
}
