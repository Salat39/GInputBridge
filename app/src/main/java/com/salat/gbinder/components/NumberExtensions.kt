package com.salat.gbinder.components

import android.content.res.Resources
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import timber.log.Timber
import java.util.Locale

val Int.toDp: Dp
    get() = Dp(
        value = (this / Resources.getSystem().displayMetrics.density).toInt().toFloat()
    )

val Dp.toPxFloat: Float get() = this.value * Resources.getSystem().displayMetrics.density

val Dp.toPxInt: Int get() = this.toPxFloat.toInt()

@Composable
fun Float.pxToDp(): Dp {
    return pxToDp(LocalDensity.current)
}

fun Float.pxToDp(density: Density): Dp {
    return with(density) { this@pxToDp.toDp() }
}

fun Long.printRuntime(tag: String = "Execute time") =
    Timber.d("[RUNTIME] $tag: ${((System.nanoTime() - this) / 1_000_000.0).roundTo(1)} ms")

private fun Double.roundTo(decimals: Int): String =
    "%.${decimals}f".format(Locale.US, this).removeSuffix(".0")

fun Int.convertToFloat(): Float {
    val clampedValue = coerceIn(0, 100)
    return clampedValue / 100.0f
}
