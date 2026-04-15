package com.salat.gbinder.ui

import android.content.res.Resources
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp

val Int.toDp: Dp get() = Dp(value = (this / Resources.getSystem().displayMetrics.density).toInt().toFloat())

val Dp.toPxFloat: Float get() = this.value * Resources.getSystem().displayMetrics.density

val Dp.toPxInt: Int get() = this.toPxFloat.toInt()

@Composable
fun Float.pxToDp(): Dp {
    return pxToDp(LocalDensity.current)
}

fun Float.pxToDp(density: Density): Dp {
    return with(density) { this@pxToDp.toDp() }
}

@Composable
fun Int.pxToDp(): Dp {
    return pxToDp(LocalDensity.current)
}

fun Int.pxToDp(density: Density): Dp {
    return with(density) { this@pxToDp.toDp() }
}
