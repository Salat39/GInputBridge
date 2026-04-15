@file:Suppress("unused")

package com.salat.gbinder.ui

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.FrameLayout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.Density
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.window.DialogWindowProvider
import com.salat.gbinder.ui.theme.AppTheme

@Composable
fun FullScreenDialog(
    modifier: Modifier = Modifier,
    uiScaleState: Float? = null,
    onDismissRequest: () -> Unit,
    properties: DialogProperties = DialogProperties(),
    content: @Composable () -> Unit
) {
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(
            dismissOnBackPress = properties.dismissOnBackPress,
            dismissOnClickOutside = properties.dismissOnClickOutside,
            securePolicy = properties.securePolicy,
            usePlatformDefaultWidth = true,
            decorFitsSystemWindows = false
        ),
        content = {
            val activityWindow = getActivityWindow()
            val dialogWindow = getDialogWindow()
            val parentView = LocalView.current.parent as View

            val originalAttributes = remember {
                activityWindow?.attributes?.let {
                    WindowManager.LayoutParams().apply { copyFrom(it) }
                }
            }

            DisposableEffect(Unit) {
                if (activityWindow != null && dialogWindow != null) {
                    val attributes = WindowManager.LayoutParams().apply {
                        copyFrom(activityWindow.attributes)
                        type = dialogWindow.attributes.type
                    }
                    dialogWindow.attributes = attributes
                    parentView.layoutParams =
                        FrameLayout.LayoutParams(
                            activityWindow.decorView.width,
                            activityWindow.decorView.height
                        )
                }

                onDispose {
                    if (activityWindow != null && originalAttributes != null) {
                        activityWindow.attributes = originalAttributes
                    }
                }
            }

            val density = LocalDensity.current
            val scaledDensity = remember(density, uiScaleState ?: 1f) {
                Density(
                    density.density * (uiScaleState ?: 1f),
                    density.fontScale * (uiScaleState ?: 1f)
                )
            }

            CompositionLocalProvider(LocalDensity provides scaledDensity) {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .then(modifier),
                    color = Color.Transparent
                ) {
                    content()
                }
            }
        }
    )
}

@Composable
fun BaseFullScreenDialog(
    onDismissRequest: () -> Unit,
    properties: DialogProperties = DialogProperties(),
    content: @Composable () -> Unit
) {
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(
            dismissOnBackPress = properties.dismissOnBackPress,
            dismissOnClickOutside = properties.dismissOnClickOutside,
            securePolicy = properties.securePolicy,
            usePlatformDefaultWidth = true,
            decorFitsSystemWindows = false
        ),
        content = {
            val activityWindow = getActivityWindow()
            val dialogWindow = getDialogWindow()
            val parentView = LocalView.current.parent as View

            // Use remember to store the original attributes
            val originalAttributes = remember {
                activityWindow?.attributes?.let {
                    WindowManager.LayoutParams().apply { copyFrom(it) }
                }
            }

            DisposableEffect(Unit) {
                if (activityWindow != null && dialogWindow != null) {
                    // Safely modify the dialog window's attributes
                    val attributes = WindowManager.LayoutParams().apply {
                        copyFrom(activityWindow.attributes)
                        type = dialogWindow.attributes.type
                    }
                    dialogWindow.attributes = attributes

                    // Adjust parent view layout params
                    parentView.layoutParams = FrameLayout.LayoutParams(
                        activityWindow.decorView.width,
                        activityWindow.decorView.height
                    )
                }

                onDispose {
                    // Restore the original attributes if they were saved
                    if (activityWindow != null && originalAttributes != null) {
                        activityWindow.attributes = originalAttributes
                    }
                }
            }

            // Use a Surface for the dialog content
            Surface(modifier = Modifier.fillMaxSize(), color = Color.Transparent) {
                content()
            }
        }
    )
}

@Composable
private fun getDialogWindow(): Window? = (LocalView.current.parent as? DialogWindowProvider)?.window

@Composable
private fun getActivityWindow(): Window? = LocalView.current.context.getActivityWindow()

private tailrec fun Context.getActivityWindow(): Window? = when (this) {
    is Activity -> window
    is ContextWrapper -> baseContext.getActivityWindow()
    else -> null
}
