package com.salat.gbinder.features.launcher

import java.time.LocalTime

internal fun isNowWithinHours(start: Int, end: Int): Boolean {
    val s = start.coerceIn(0, 24) % 24
    val e = end.coerceIn(0, 24) % 24

    if (s == e) return true

    val now = LocalTime.now()
    val startTime = LocalTime.of(s, 0)
    val endTime = LocalTime.of(e, 0)

    return if (s < e) {
        !now.isBefore(startTime) && now.isBefore(endTime)
    } else {
        !now.isBefore(startTime) || now.isBefore(endTime)
    }
}
