@file:Suppress("unused")

package com.salat.gbinder.components

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import kotlin.coroutines.cancellation.CancellationException
import kotlin.math.min

fun CoroutineScope.launchRetry(
    delayMillis: Long = 2_000,
    initBlock: () -> Boolean,
    cancelBlock: () -> Unit
): Job = launch {
    var completedSuccessfully = false

    try {
        while (isActive) {
            val success = try {
                initBlock()
            } catch (e: Throwable) {
                if (e is CancellationException) throw e
                Timber.e(e, "Init failed with exception, will retry")
                false
            }

            if (success) {
                Timber.i("Initialization succeeded")
                completedSuccessfully = true
                break
            }

            delay(delayMillis)
        }
    } finally {
        if (!completedSuccessfully) {
            Timber.w("Initialization coroutine ended without success, invoking cancelBlock()")
            cancelBlock()
        }
    }
}

fun CoroutineScope.launchDynamicRetry(
    delayMinMillis: Long = 2_000L,
    delayMaxMillis: Long = 6_000L,
    delayStepMillis: Long = 500L,
    maxAttempts: Int = 0,
    dispatcher: CoroutineDispatcher = Dispatchers.IO,
    initBlock: () -> Boolean,
    cancelBlock: () -> Unit = {},
    successBlock: () -> Unit = {},
): Job = launch {
    var completedSuccessfully = false
    var attempt = 0
    var nextDelay = delayMinMillis.coerceAtLeast(0L)

    try {
        while (isActive) {
            if (maxAttempts in 1..attempt) {
                Timber.w("launchRetry: reached maxAttempts=$maxAttempts, giving up")
                break
            }

            attempt++
            Timber.d("launchRetry: attempt #$attempt starting")

            val success = try {
                withContext(dispatcher) {
                    initBlock()
                }
            } catch (e: Throwable) {
                if (e is CancellationException) throw e
                Timber.e(e, "launchRetry: initBlock threw, will retry")
                false
            }

            if (success) {
                Timber.i("launchRetry: initialization succeeded on attempt #$attempt")
                completedSuccessfully = true
                successBlock()
                break
            }

            Timber.i("launchRetry: attempt #$attempt failed, delaying ${nextDelay}ms before retry")
            delay(nextDelay)
            nextDelay = min(nextDelay + delayStepMillis, delayMaxMillis)
        }
    } finally {
        if (!completedSuccessfully) {
            Timber.w("launchRetry: finished without success (completedSuccessfully=$completedSuccessfully), calling cancelBlock")
            try {
                cancelBlock()
            } catch (e: Throwable) {
                Timber.e(e, "launchRetry: cancelBlock threw exception")
            }
        }
    }
}
