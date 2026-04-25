package com.salat.gbinder.components

import kotlin.math.roundToInt

internal fun Float.roundScale() = (this * 100f).roundToInt() / 100f
