package com.salat.gbinder.features.launcher

import com.salat.gbinder.car.domain.entity.CarFunctionChange
import com.salat.gbinder.car.domain.repository.CarRepository
import com.salat.gbinder.entity.CarFunction
import com.salat.gbinder.features.carFunctions.CarFunctionState
import com.salat.gbinder.features.carFunctions.CarFunctionStateReader
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber

private const val READ_ATTEMPTS = 3
private const val READ_RETRY_DELAY_MS = 2000L
private const val TAP_REFRESH_DELAY_MS = 400L

class LauncherCarFunctionStates(
    private val scope: CoroutineScope,
    private val car: CarRepository,
    private val reader: CarFunctionStateReader,
    private val trigger: suspend (CarFunction) -> Unit,
    private val simulate: Boolean
) {
    private val mutableStates = MutableStateFlow<Map<CarFunction, CarFunctionState>>(emptyMap())
    val states: StateFlow<Map<CarFunction, CarFunctionState>> = mutableStates.asStateFlow()
    private val refreshRequests = Channel<Pair<CarFunction, Int>>(Channel.UNLIMITED)
    @Volatile
    private var tracked = emptySet<CarFunction>()
    @Volatile
    private var watchKeys = emptyMap<CarFunction, List<Pair<Int, Int>>>()
    private var worker: Job? = null
    private var changes: Job? = null

    fun track(functions: Set<CarFunction>) {
        tracked = functions.toSet()
        watchKeys = functions.associateWith(reader::watchKeys).filterValues { it.isNotEmpty() }
        mutableStates.update { it.filterKeys(functions::contains) }
        if (simulate) {
            mutableStates.update { current ->
                current + functions.filter { it !in current }.associateWith(reader::placeholder)
            }
            return
        }
        if (functions.isEmpty()) {
            changes?.cancel()
            changes = null
            worker?.cancel()
            return
        }
        if (changes == null) {
            changes = scope.launch(Dispatchers.IO, start = CoroutineStart.UNDISPATCHED) {
                car.functionChangedFlow.collect { event ->
                    watchKeys.forEach { (function, keys) ->
                        if (keys.any { event.matches(it) }) enqueue(function)
                    }
                }
            }
        }
        if (worker?.isActive != true) {
            val previousWorker = worker
            worker = scope.launch(Dispatchers.IO) {
                previousWorker?.join()
                for (first in refreshRequests) {
                    val pending = linkedMapOf(first)
                    while (true) {
                        val (function, attempt) = refreshRequests.tryReceive().getOrNull() ?: break
                        pending[function] = minOf(attempt, pending[function] ?: attempt)
                    }
                    for ((function, attempt) in pending) {
                        if (function !in tracked) continue
                        val state = runCatching { reader.read(function) }.getOrElse {
                            if (it is CancellationException) throw it
                            Timber.e(it)
                            CarFunctionState.Unknown
                        }
                        currentCoroutineContext().ensureActive()
                        if (function !in tracked) continue
                        mutableStates.update { it + (function to state) }
                        if (state == CarFunctionState.Unknown && attempt < READ_ATTEMPTS) {
                            launch {
                                delay(READ_RETRY_DELAY_MS)
                                enqueue(function, attempt + 1)
                            }
                        }
                    }
                }
            }
        }
        functions.forEach { enqueue(it) }
    }

    fun tap(function: CarFunction) {
        if (function !in tracked) return
        scope.launch(Dispatchers.IO) {
            runCatching { trigger(function) }.onFailure {
                if (it is CancellationException) throw it
                Timber.e(it)
            }
            if (simulate) {
                mutableStates.update { current ->
                    current + (function to advance(current[function] ?: reader.placeholder(function)))
                }
                return@launch
            }
            enqueue(function)
            delay(TAP_REFRESH_DELAY_MS)
            enqueue(function)
        }
    }

    private fun advance(state: CarFunctionState): CarFunctionState = when (state) {
        is CarFunctionState.Level -> state.copy(index = (state.index + 1) % (state.count + 1))
        is CarFunctionState.Toggle -> state.copy(on = !state.on)
        CarFunctionState.Action, CarFunctionState.Unknown -> state
    }

    private fun enqueue(function: CarFunction, attempt: Int = 1) {
        if (function in tracked) refreshRequests.trySend(function to attempt)
    }

    private fun CarFunctionChange.matches(key: Pair<Int, Int>): Boolean {
        val (watchedPropertyId, watchedAreaId) = key
        return propertyId == watchedPropertyId &&
            (watchedAreaId == Integer.MIN_VALUE || areaId == Integer.MIN_VALUE || areaId == watchedAreaId)
    }
}
