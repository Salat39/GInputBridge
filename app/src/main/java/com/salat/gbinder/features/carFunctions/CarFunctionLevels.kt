package com.salat.gbinder.features.carFunctions

import com.salat.gbinder.car.data.CarPropertyKey
import com.salat.gbinder.car.domain.repository.CarRepository
import com.salat.gbinder.entity.CarFunction
import com.salat.gbinder.entity.CarFunctionIds
import com.salat.gbinder.entity.CarModel
import kotlin.math.abs

internal const val DEFAULT_TEMP_LO = 16f

internal val IM_COLD_WATCH_KEYS = listOf(
    CarPropertyKey.HVAC_FUNC_SEAT_HEATING to CarFunctionIds.ZONE_DRIVER,
    CarPropertyKey.HVAC_FUNC_STEERING_WHEEL_HEAT to Integer.MIN_VALUE
)

internal fun imHotWatchKeys(model: CarModel?): List<Pair<Int, Int>> = listOfNotNull(
    CarPropertyKey.HVAC_FUNC_AC to Integer.MIN_VALUE,
    CarPropertyKey.HVAC_FUNC_CIRCULATION to Integer.MIN_VALUE,
    CarPropertyKey.HVAC_FUNC_TEMP to CarFunctionIds.ZONE_DRIVER,
    CarPropertyKey.HVAC_FUNC_AUTO_FAN_SETTING to CarFunctionIds.ZONE_ROW_1_ALL,
    (CarPropertyKey.HVAC_FUNC_SEAT_VENTILATION to CarFunctionIds.ZONE_DRIVER)
        .takeIf { model != CarModel.CITYRAY }
)

internal fun levelSpec(function: CarFunction): Triple<Int, Int, List<Int>>? = when (function) {
    CarFunction.WHEEL_HEAT -> Triple(
        CarPropertyKey.HVAC_FUNC_STEERING_WHEEL_HEAT,
        Integer.MIN_VALUE,
        CarFunctionIds.STEERING_HEAT_LEVELS
    )
    CarFunction.DRIVER_HEAT -> Triple(
        CarPropertyKey.HVAC_FUNC_SEAT_HEATING,
        CarFunctionIds.ZONE_DRIVER,
        CarFunctionIds.SEAT_HEAT_LEVELS
    )
    CarFunction.PASSENGER_HEAT -> Triple(
        CarPropertyKey.HVAC_FUNC_SEAT_HEATING,
        CarFunctionIds.ZONE_PASSENGER,
        CarFunctionIds.SEAT_HEAT_LEVELS
    )
    CarFunction.DRIVER_VENT -> Triple(
        CarPropertyKey.HVAC_FUNC_SEAT_VENTILATION,
        CarFunctionIds.ZONE_DRIVER,
        CarFunctionIds.SEAT_VENT_LEVELS
    )
    CarFunction.PASSENGER_VENT -> Triple(
        CarPropertyKey.HVAC_FUNC_SEAT_VENTILATION,
        CarFunctionIds.ZONE_PASSENGER,
        CarFunctionIds.SEAT_VENT_LEVELS
    )
    CarFunction.LIGHT -> Triple(
        CarPropertyKey.SETTING_FUNC_LAMP_EXTERIOR_LIGHT_CONTROL,
        Integer.MIN_VALUE,
        CarFunctionIds.LIGHT_LEVELS
    )
    else -> null
}

internal suspend fun CarRepository.readLevelValue(propertyId: Int, areaId: Int): Int {
    val primary = getIntProperty(propertyId, areaId)
    if (primary != -1) return primary
    if (areaId != Integer.MIN_VALUE) {
        val fallback = getIntProperty(propertyId, Integer.MIN_VALUE)
        if (fallback != -1) return fallback
    }
    return getIntProperty(propertyId)
}

internal suspend fun CarRepository.readTempLimit(propertyId: Int): Float {
    val global = getFloatProperty(propertyId, Integer.MIN_VALUE)
    if (global >= 0f) return global
    return getFloatProperty(propertyId, CarFunctionIds.ZONE_DRIVER)
}

// Null means the car did not answer
internal suspend fun CarRepository.readImColdActive(): Boolean? {
    val seat = readLevelValue(CarPropertyKey.HVAC_FUNC_SEAT_HEATING, CarFunctionIds.ZONE_DRIVER)
    val wheel = readLevelValue(CarPropertyKey.HVAC_FUNC_STEERING_WHEEL_HEAT, Integer.MIN_VALUE)
    if (seat == -1 || wheel == -1) return null
    return normalizeLevelValue(CarFunction.DRIVER_HEAT, seat) == CarFunctionIds.SEAT_HEAT_L3 &&
        normalizeLevelValue(CarFunction.WHEEL_HEAT, wheel) == CarFunctionIds.STEERING_HEAT_L3
}

// Null means the car did not answer
internal suspend fun CarRepository.readImHotActive(model: CarModel?): Boolean? {
    val ac = getIntProperty(CarPropertyKey.HVAC_FUNC_AC)
    val circulation = getIntProperty(CarPropertyKey.HVAC_FUNC_CIRCULATION)
    val fan = getIntProperty(CarPropertyKey.HVAC_FUNC_AUTO_FAN_SETTING, CarFunctionIds.ZONE_ROW_1_ALL)
    val temp = getFloatProperty(CarPropertyKey.HVAC_FUNC_TEMP, CarFunctionIds.ZONE_DRIVER)
    if (ac == -1 || circulation == -1 || fan == -1 || temp < 0f) return null
    val ventActive = model == CarModel.CITYRAY || normalizeLevelValue(
        CarFunction.DRIVER_VENT,
        readLevelValue(CarPropertyKey.HVAC_FUNC_SEAT_VENTILATION, CarFunctionIds.ZONE_DRIVER)
    ) == CarFunctionIds.SEAT_VENT_L3
    val tempLo = readTempLimit(CarPropertyKey.HVAC_FUNC_TEMP_MIN).takeIf { it >= 0f } ?: DEFAULT_TEMP_LO
    return ac == 1 && circulation == CarFunctionIds.CIRCULATION_ON &&
        fan == CarFunctionIds.AUTO_FAN_HIGH && abs(temp - tempLo) < 0.01f && ventActive
}

internal fun normalizeLevelValue(function: CarFunction, value: Int): Int = when (function) {
    CarFunction.WHEEL_HEAT -> when (value) {
        1 -> CarFunctionIds.STEERING_HEAT_L1
        2 -> CarFunctionIds.STEERING_HEAT_L2
        3 -> CarFunctionIds.STEERING_HEAT_L3
        CarFunctionIds.STEERING_HEAT_L1,
        CarFunctionIds.STEERING_HEAT_L2,
        CarFunctionIds.STEERING_HEAT_L3 -> value
        else -> 0
    }
    CarFunction.DRIVER_HEAT, CarFunction.PASSENGER_HEAT -> when (value) {
        1 -> CarFunctionIds.SEAT_HEAT_L1
        2 -> CarFunctionIds.SEAT_HEAT_L2
        3 -> CarFunctionIds.SEAT_HEAT_L3
        CarFunctionIds.SEAT_HEAT_L1,
        CarFunctionIds.SEAT_HEAT_L2,
        CarFunctionIds.SEAT_HEAT_L3 -> value
        0x1005020F -> CarFunctionIds.SEAT_HEAT_L3
        else -> 0
    }
    CarFunction.DRIVER_VENT, CarFunction.PASSENGER_VENT -> when (value) {
        1 -> CarFunctionIds.SEAT_VENT_L1
        2 -> CarFunctionIds.SEAT_VENT_L2
        3 -> CarFunctionIds.SEAT_VENT_L3
        CarFunctionIds.SEAT_VENT_L1,
        CarFunctionIds.SEAT_VENT_L2,
        CarFunctionIds.SEAT_VENT_L3 -> value
        0x1005010F -> CarFunctionIds.SEAT_VENT_L3
        else -> 0
    }
    else -> value
}

