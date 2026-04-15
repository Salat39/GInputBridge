package com.salat.gbinder.util

import android.os.SystemClock
import java.util.Locale
import kotlin.math.max

class SimpleTimer {
    private val startNs: Long = SystemClock.elapsedRealtimeNanos()

    /**
     * Returns true if at least [seconds] seconds have elapsed since startNs.
     * Uses a monotonic clock and guards against negative deltas and overflow.
     */
    fun hasElapsed(seconds: Int): Boolean {
        // Non-positive threshold means "already elapsed"
        if (seconds <= 0) return true

        // Convert seconds -> nanoseconds safely (avoid overflow on multiplication)
        val nanosPerSecond = 1_000_000_000L
        val safeSeconds = seconds.toLong().coerceAtMost(Long.MAX_VALUE / nanosPerSecond)
        val requiredNs = safeSeconds * nanosPerSecond

        // Monotonic time since boot (safe for elapsed measurement)
        val nowNs = SystemClock.elapsedRealtimeNanos()
        val elapsedNs = max(0L, nowNs - startNs)

        return elapsedNs >= requiredNs
    }

    /**
     * Returns elapsed time since init as:
     * - "X.XX sec" for < 60 seconds
     * - "M min S sec" for >= 60 seconds and < 60 minutes
     * - "H hr M min S sec" for >= 60 minutes
     *
     * Safe: protects against unexpected exceptions and negative deltas.
     */
    val time: String
        get() = runCatching {
            val nowNs = SystemClock.elapsedRealtimeNanos()
            val deltaNs = max(0L, nowNs - startNs) // monotonic, but be defensive
            formatElapsed(deltaNs)
        }.getOrElse { "0.00sec" }

    /* Formats elapsed time according to the rules above. */
    private fun formatElapsed(elapsedNs: Long): String {
        // Fast-path for sub-second precision when < 60s
        val totalSeconds = elapsedNs / 1_000_000_000L
        if (totalSeconds < 60L) {
            val seconds = elapsedNs / 1_000_000_000.0
            return String.format(Locale.US, "%.2fsec", seconds)
        }

        // 60s+ -> use whole units
        val hours = totalSeconds / 3600
        val minutes = (totalSeconds % 3600) / 60
        val seconds = totalSeconds % 60

        return if (hours > 0) {
            // e.g., "1 hr 02 min 05 sec" or "2 hr 0 min 3 sec" (leading zeros optional)
            "${hours}hr ${minutes}min ${seconds}sec"
        } else {
            // e.g., "1 min 5 sec"
            "${minutes}min ${seconds}sec"
        }
    }
}

fun Int.hasElapsedSinceBoot(): Boolean {
    // Guard for non-positive input: zero/negative means condition is trivially satisfied
    if (this <= 0) return true

    // Use monotonic clock that resets only on real reboot
    val elapsedMs: Long = SystemClock.elapsedRealtime()
    val thresholdMs: Long = this.toLong() * 1000L

    // Compare in milliseconds using Long to avoid overflow
    return elapsedMs >= thresholdMs
}
