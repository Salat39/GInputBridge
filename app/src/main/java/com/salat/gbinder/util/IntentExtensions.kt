package com.salat.gbinder.util

import android.content.Intent
import kotlin.math.roundToInt

fun Intent.getSafeInt(name: String): Int {
    val likeInt = try {
        getIntExtra(name, 0)
    } catch (_: Exception) {
        0
    }
    if (likeInt != 0) return likeInt

    val likeDouble = try {
        getDoubleExtra(name, 0.0)
    } catch (_: Exception) {
        0.0
    }
    if (likeDouble != 0.0) return likeDouble.roundToInt()

    val likeString = try {
        getStringExtra(name)
    } catch (_: Exception) {
        ""
    }
    if (likeString?.isNotEmpty() == true) return likeString.toIntOrNull() ?: 0

    return 0
}

fun Intent.getSafeFloat(name: String): Float {
    val likeFloat = try {
        getFloatExtra(name, 0f)
    } catch (_: Exception) {
        0f
    }
    if (likeFloat != 0f) return likeFloat

    val likeDouble = try {
        getDoubleExtra(name, 0.0)
    } catch (_: Exception) {
        0.0
    }
    if (likeDouble != 0.0) return likeDouble.toFloat()

    val likeInt = try {
        getIntExtra(name, 0)
    } catch (_: Exception) {
        0
    }
    if (likeInt != 0) return likeInt.toFloat()

    val likeString = try {
        getStringExtra(name)
    } catch (_: Exception) {
        ""
    }
    if (!likeString.isNullOrEmpty()) {
        return likeString.trim()
            .replace(',', '.')
            .toFloatOrNull()
            ?: 0f
    }

    return 0f
}
