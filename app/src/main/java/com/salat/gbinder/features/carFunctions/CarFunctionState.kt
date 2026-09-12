package com.salat.gbinder.features.carFunctions

import com.salat.gbinder.car.data.CarPropertyKey
import com.salat.gbinder.car.domain.repository.CarRepository
import com.salat.gbinder.entity.CarFunction
import com.salat.gbinder.entity.CarFunctionIds
import com.salat.gbinder.entity.CarModel

sealed interface CarFunctionState {
    data object Unknown : CarFunctionState
    data object Action : CarFunctionState
    data class Toggle(val on: Boolean) : CarFunctionState
    data class Level(val index: Int, val count: Int, val cumulative: Boolean) : CarFunctionState
}

fun CarFunction.isLauncherAction(): Boolean = when (this) {
    CarFunction.SEAT_MEMORY, CarFunction.NIGHT_MODE, CarFunction.ME_COOLED, CarFunction.ME_WARMED -> true
    else -> false
}

fun CarFunction.opensExternalScreen(): Boolean = when (this) {
    CarFunction.SEAT_MEMORY, CarFunction.NIGHT_MODE -> true
    else -> false
}

class CarFunctionStateReader(
    private val car: CarRepository,
    private val resolveCarModel: () -> CarModel?
) {
    private val carModel by lazy { resolveCarModel() }

    fun placeholder(function: CarFunction): CarFunctionState {
        levelSpec(function)?.let { (_, _, levels) ->
            return CarFunctionState.Level(
                index = 0,
                count = levels.size - 1,
                cumulative = function != CarFunction.LIGHT
            )
        }
        if (function.isLauncherAction()) return CarFunctionState.Action
        return CarFunctionState.Toggle(on = false)
    }

    suspend fun read(function: CarFunction): CarFunctionState {
        levelSpec(function)?.let { (propertyId, areaId, levels) ->
            val raw = if (function == CarFunction.LIGHT) {
                car.getIntProperty(propertyId)
            } else {
                car.readLevelValue(propertyId, areaId)
            }
            if (raw == -1) return CarFunctionState.Unknown
            val value = normalizeLevelValue(function, raw)
            return CarFunctionState.Level(
                index = levels.indexOf(value).coerceAtLeast(0),
                count = levels.size - 1,
                cumulative = function != CarFunction.LIGHT
            )
        }
        when (function) {
            CarFunction.ME_HOT -> return car.readImHotActive(carModel).toToggle()
            CarFunction.ME_COLD -> return car.readImColdActive().toToggle()
            else -> Unit
        }
        if (function.isLauncherAction()) return CarFunctionState.Action
        val (propertyId, areaId) = toggleKey(function) ?: return CarFunctionState.Unknown
        val raw = car.readLevelValue(propertyId, areaId)
        if (raw == -1) return CarFunctionState.Unknown
        val onValue = when (function) {
            CarFunction.RECIRCULATION -> CarFunctionIds.CIRCULATION_ON
            CarFunction.ANTIBUKS -> 0
            else -> 1
        }
        return CarFunctionState.Toggle(raw == onValue)
    }

    fun watchKeys(function: CarFunction): List<Pair<Int, Int>> = when (function) {
        CarFunction.ME_HOT -> imHotWatchKeys(carModel)
        CarFunction.ME_COLD -> IM_COLD_WATCH_KEYS
        else -> listOfNotNull(
            levelSpec(function)?.let { (propertyId, areaId, _) -> propertyId to areaId }
                ?: toggleKey(function)
        )
    }

    private fun Boolean?.toToggle(): CarFunctionState =
        this?.let { CarFunctionState.Toggle(it) } ?: CarFunctionState.Unknown

    private fun toggleKey(function: CarFunction): Pair<Int, Int>? = when (function) {
        CarFunction.RECIRCULATION -> CarPropertyKey.HVAC_FUNC_CIRCULATION to Integer.MIN_VALUE
        CarFunction.ANTIBUKS -> CarPropertyKey.SETTING_FUNC_ESC_SPORT_MODE to Integer.MIN_VALUE
        CarFunction.FRONT_DEFROST -> carModel?.let {
            CarFunctionIds.frontDefrostId(it) to Integer.MIN_VALUE
        }
        CarFunction.MAX_DEFROST -> CarPropertyKey.HVAC_FUNC_DEFROST_FRONT_MAX to Integer.MIN_VALUE
        CarFunction.REAR_DEFROST -> CarPropertyKey.HVAC_FUNC_DEFROST_REAR to Integer.MIN_VALUE
        CarFunction.MIRRORS -> CarPropertyKey.BCM_FUNC_FOLD_REAR_MIRROR to Integer.MIN_VALUE
        CarFunction.WIPERS ->
            CarPropertyKey.SETTING_FUNC_WINDSCREEN_SERVICE_POSITION to CarFunctionIds.ZONE_DRIVER
        CarFunction.TRUNK -> CarPropertyKey.BCM_FUNC_DOOR to CarFunctionIds.ZONE_TRUNK
        else -> null
    }
}
