package com.salat.gbinder.features.carFunctions

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import com.salat.gbinder.R
import com.salat.gbinder.car.data.CarPropertyKey
import com.salat.gbinder.car.domain.repository.CarRepository
import com.salat.gbinder.components.inMainToast
import com.salat.gbinder.entity.CarFunction
import com.salat.gbinder.entity.CarFunctionIds
import com.salat.gbinder.entity.CarModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import timber.log.Timber
import java.util.concurrent.atomic.AtomicReference

class CarFunctionController(
    private val context: Context,
    private val car: CarRepository,
    private val scope: CoroutineScope,
    private val launchPackage: (String) -> Unit,
    private val lockMediaControl: (seconds: Int, restartIfActive: Boolean) -> Unit,
    private val climatePackage: String,
    private val isClimateVisible: () -> Boolean,
    private val resolveCarModel: () -> CarModel?,
    private val defaultHeatVentLevel: (CarFunction) -> Int = { CarFunction.DEFAULT_HEAT_VENT_LEVEL },
    private val isIgnitionDriving: () -> Boolean = { true },
) {
    private val mutex = Mutex()
    private val session = AtomicReference<PadSession?>(null)
    private var levelExpireJob: Job? = null

    private val carModel: CarModel? get() = resolveCarModel()

    private var tempLo = DEFAULT_TEMP_LO
    private var tempHi = DEFAULT_TEMP_HI
    private var imHotCooledNextIsHot = true
    private var imColdWarmedNextIsCold = true

    private sealed class PadSession {
        data class Climate(var fanMode: Boolean) : PadSession()
        data class LevelCycle(
            val function: CarFunction,
            val includeOff: Boolean = true,
        ) : PadSession()
    }

    fun onVisibleAppChanged(packageName: String) {
        if (packageName != climatePackage) {
            val current = session.get()
            if (current is PadSession.Climate) {
                session.compareAndSet(current, null)
            }
        }
    }

    suspend fun handleMediaKey(keyCode: Int): Boolean = mutex.withLock {
        val current = session.get() ?: return false

        when (current) {
            is PadSession.LevelCycle -> {
                when (keyCode) {
                    KEY_PREV -> {
                        cycleLevel(current.function, forward = false, includeOff = current.includeOff)
                        lockMediaControl(LEVEL_LOCK_SEC, false)
                        true
                    }
                    KEY_NEXT -> {
                        cycleLevel(current.function, forward = true, includeOff = current.includeOff)
                        lockMediaControl(LEVEL_LOCK_SEC, false)
                        true
                    }
                    else -> false
                }
            }

            is PadSession.Climate -> {
                if (!isClimateVisible()) return false
                when (keyCode) {
                    KEY_PLAY -> {
                        current.fanMode = !current.fanMode
                        session.set(current)
                        lockMediaControl(LEVEL_LOCK_SEC, false)
                        toast(
                            if (current.fanMode) R.string.car_fn_toast_fan
                            else R.string.car_fn_toast_temperature
                        )
                        true
                    }
                    KEY_PREV, KEY_NEXT -> {
                        val up = keyCode == KEY_NEXT
                        if (current.fanMode) adjustFan(up) else adjustTemp(up)
                        lockMediaControl(LEVEL_LOCK_SEC, false)
                        true
                    }
                    else -> false
                }
            }
        }
    }

    suspend fun trigger(function: CarFunction, triggerKeyCode: Int = -1): Boolean {
        var consumePadEcho = false
        runCatching {
            mutex.withLock {
                if (!function.isAvailableFor(carModel)) {
                    return@withLock
                }
                if (function.requiresIgnition() && !isIgnitionDriving()) {
                    return@withLock
                }
                when (function) {
                    CarFunction.CLIMATE_MENU -> {
                        openClimateMenu()
                        consumePadEcho = triggerKeyCode.isMediaPadKey()
                    }
                    CarFunction.SEAT_MEMORY -> openSeatMemory()
                    CarFunction.WHEEL_HEAT,
                    CarFunction.DRIVER_HEAT,
                    CarFunction.PASSENGER_HEAT,
                    CarFunction.DRIVER_VENT,
                    CarFunction.PASSENGER_VENT -> {
                        val turnedOn = toggleHeatVent(function)
                        renewLevelSession(
                            function = function,
                            includeOff = !turnedOn,
                        )
                        lockMediaControl(LEVEL_LOCK_SEC, true)
                        consumePadEcho = triggerKeyCode.isMediaPadKey()
                    }
                    CarFunction.RECIRCULATION -> toggleRecirculation()
                    CarFunction.ANTIBUKS -> toggleAntiSlip()
                    CarFunction.LIGHT -> {
                        val turnedOn = triggerLight()
                        renewLevelSession(
                            function = CarFunction.LIGHT,
                            includeOff = !turnedOn,
                        )
                        lockMediaControl(LEVEL_LOCK_SEC, true)
                        consumePadEcho = triggerKeyCode.isMediaPadKey()
                    }
                    CarFunction.FRONT_DEFROST -> toggleFrontDefrost()
                    CarFunction.MAX_DEFROST -> toggleMaxFan()
                    CarFunction.REAR_DEFROST -> toggleRearDefrost()
                    CarFunction.ME_HOT, CarFunction.ME_COOLED -> toggleImHotCooled()
                    CarFunction.ME_COLD, CarFunction.ME_WARMED -> toggleImColdWarmed()
                    CarFunction.TRUNK -> toggleTrunk()
                    CarFunction.MIRRORS -> toggleMirrors()
                    CarFunction.WIPERS -> toggleWipers()
                }
            }
        }.onFailure { Timber.e(it) }
        return consumePadEcho
    }

    private fun CarFunction.requiresIgnition(): Boolean = when (this) {
        CarFunction.WHEEL_HEAT,
        CarFunction.DRIVER_HEAT,
        CarFunction.PASSENGER_HEAT,
        CarFunction.DRIVER_VENT,
        CarFunction.PASSENGER_VENT,
        CarFunction.FRONT_DEFROST,
        CarFunction.REAR_DEFROST,
        CarFunction.RECIRCULATION -> true
        else -> false
    }

    private suspend fun openClimateMenu() {
        refreshTempLimits()
        launchPackage(climatePackage)
        levelExpireJob?.cancel()
        levelExpireJob = null
        session.set(PadSession.Climate(fanMode = false))
        lockMediaControl(LEVEL_LOCK_SEC, true)
        toast(R.string.car_fn_toast_temperature)
    }

    private fun openSeatMemory() {
        scope.launch(Dispatchers.Main) {
            runCatching {
                context.startActivity(
                    Intent().apply {
                        component = ComponentName(
                            SEAT_MEMORY_PACKAGE,
                            SEAT_MEMORY_ACTIVITY,
                        )
                        flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    }
                )
            }.onFailure { Timber.e(it) }
        }
    }

    private suspend fun toggleHeatVent(function: CarFunction): Boolean {
        val (propertyId, areaId, levels) = levelSpec(function) ?: return false
        val raw = readLevelValue(propertyId, areaId)
        val current = normalizeLevelValue(function, raw)
        val isOn = current != 0
        val nextIndex = if (isOn) {
            0
        } else {
            defaultHeatVentLevel(function).coerceIn(1, levels.lastIndex)
        }
        car.setPropertyIntValue(propertyId, areaId, levels[nextIndex])
        toastLevel(function, nextIndex)
        return !isOn
    }

    private suspend fun cycleLevel(
        function: CarFunction,
        forward: Boolean,
        includeOff: Boolean = true,
    ) {
        if (function.requiresIgnition() && !isIgnitionDriving()) return
        val (propertyId, areaId, allLevels) = levelSpec(function) ?: return
        val levels = if (!includeOff || function == CarFunction.LIGHT) {
            allLevels.drop(1)
        } else {
            allLevels
        }
        if (levels.isEmpty()) return
        val raw = if (function == CarFunction.LIGHT) {
            car.getIntProperty(propertyId)
        } else {
            readLevelValue(propertyId, areaId)
        }
        val current = if (function == CarFunction.LIGHT) {
            raw
        } else {
            normalizeLevelValue(function, raw)
        }
        val index = levels.indexOf(current).takeIf { it >= 0 } ?: 0
        val nextIndex = if (forward) {
            if (index >= levels.lastIndex) 0 else index + 1
        } else {
            if (index <= 0) levels.lastIndex else index - 1
        }
        val next = levels[nextIndex]
        car.setPropertyIntValue(propertyId, areaId, next)
        if (function == CarFunction.LIGHT) {
            toastLight(next)
        } else {
            toastLevel(function, allLevels.indexOf(next).coerceAtLeast(0))
        }
    }

    private fun renewLevelSession(
        function: CarFunction,
        includeOff: Boolean = true,
    ) {
        levelExpireJob?.cancel()
        levelExpireJob = null
        session.set(PadSession.LevelCycle(function, includeOff = includeOff))
        levelExpireJob = scope.launch {
            delay(LEVEL_LOCK_SEC * 1000L)
            val current = session.get()
            if (current is PadSession.LevelCycle && current.function == function) {
                session.compareAndSet(current, null)
            }
        }
    }

    private fun levelSpec(function: CarFunction): Triple<Int, Int, List<Int>>? = when (function) {
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

    private suspend fun readLevelValue(propertyId: Int, areaId: Int): Int {
        val primary = car.getIntProperty(propertyId, areaId)
        if (primary != -1) return primary
        if (areaId != Integer.MIN_VALUE) {
            val fallback = car.getIntProperty(propertyId, Integer.MIN_VALUE)
            if (fallback != -1) return fallback
        }
        return car.getIntProperty(propertyId)
    }

    private fun normalizeLevelValue(function: CarFunction, value: Int): Int = when (function) {
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

    private suspend fun toastLevel(function: CarFunction, levelIndex: Int) {
        val (offRes, levelRes, onMaxRes) = when (function) {
            CarFunction.WHEEL_HEAT -> Triple(
                R.string.car_fn_toast_wheel_heat_off,
                R.string.car_fn_toast_wheel_heat_level,
                R.string.car_fn_toast_wheel_heat
            )
            CarFunction.DRIVER_HEAT -> Triple(
                R.string.car_fn_toast_driver_heat_off,
                R.string.car_fn_toast_driver_heat_level,
                R.string.car_fn_toast_driver_heat
            )
            CarFunction.PASSENGER_HEAT -> Triple(
                R.string.car_fn_toast_passenger_heat_off,
                R.string.car_fn_toast_passenger_heat_level,
                R.string.car_fn_toast_passenger_heat
            )
            CarFunction.DRIVER_VENT -> Triple(
                R.string.car_fn_toast_driver_vent_off,
                R.string.car_fn_toast_driver_vent_level,
                R.string.car_fn_toast_driver_vent
            )
            CarFunction.PASSENGER_VENT -> Triple(
                R.string.car_fn_toast_passenger_vent_off,
                R.string.car_fn_toast_passenger_vent_level,
                R.string.car_fn_toast_passenger_vent
            )
            else -> return
        }
        when {
            levelIndex <= 0 -> toast(offRes)
            levelIndex >= 3 -> toast(onMaxRes)
            else -> toast(context.getString(levelRes, levelIndex))
        }
    }

    private suspend fun toggleRecirculation() {
        val current = car.getIntProperty(CarPropertyKey.HVAC_FUNC_CIRCULATION)
        val enable = current != CarFunctionIds.CIRCULATION_ON
        val target = if (enable) CarFunctionIds.CIRCULATION_ON else CarFunctionIds.CIRCULATION_OFF
        car.setPropertyIntValue(CarPropertyKey.HVAC_FUNC_CIRCULATION, Integer.MIN_VALUE, target)
        toast(
            if (enable) R.string.car_fn_toast_recirculation_on
            else R.string.car_fn_toast_recirculation_off
        )
    }

    private suspend fun toggleAntiSlip() {
        val current = car.getIntProperty(CarPropertyKey.SETTING_FUNC_ESC_SPORT_MODE)
        val turnOff = current == 0
        car.setPropertyIntValue(
            CarPropertyKey.SETTING_FUNC_ESC_SPORT_MODE,
            Integer.MIN_VALUE,
            if (turnOff) 1 else 0
        )
        toast(
            if (turnOff) R.string.car_fn_toast_antibuks_off
            else R.string.car_fn_toast_antibuks_on
        )
    }

    private suspend fun triggerLight(): Boolean {
        val current = car.getIntProperty(CarPropertyKey.SETTING_FUNC_LAMP_EXTERIOR_LIGHT_CONTROL)
        val turnOn = current == 0
        val next = if (turnOn) CarFunctionIds.LIGHT_AUTO else 0
        car.setPropertyIntValue(
            CarPropertyKey.SETTING_FUNC_LAMP_EXTERIOR_LIGHT_CONTROL,
            Integer.MIN_VALUE,
            next
        )
        toastLight(next)
        return turnOn
    }

    private suspend fun toastLight(value: Int) {
        toast(
            when (value) {
                CarFunctionIds.LIGHT_POS -> R.string.car_fn_toast_light_pos
                CarFunctionIds.LIGHT_LOW -> R.string.car_fn_toast_light_low
                CarFunctionIds.LIGHT_AUTO -> R.string.car_fn_toast_light_auto
                else -> R.string.car_fn_toast_light_off
            }
        )
    }

    private suspend fun toggleFrontDefrost() {
        val model = carModel ?: return
        val id = CarFunctionIds.frontDefrostId(model)
        val current = car.getIntProperty(id)
        val enable = current != 1
        car.setPropertyIntValue(id, Integer.MIN_VALUE, if (enable) 1 else 0)
        toast(if (enable) R.string.car_fn_toast_front_on else R.string.car_fn_toast_front_off)
    }

    private suspend fun toggleMaxFan() {
        val current = car.getIntProperty(CarPropertyKey.HVAC_FUNC_DEFROST_FRONT_MAX)
        val enable = current != 1
        car.setPropertyIntValue(
            CarPropertyKey.HVAC_FUNC_DEFROST_FRONT_MAX,
            Integer.MIN_VALUE,
            if (enable) 1 else 0
        )
        toast(
            if (enable) R.string.car_fn_toast_max_defrost_on
            else R.string.car_fn_toast_max_defrost_off
        )
    }

    private suspend fun toggleRearDefrost() {
        val current = car.getIntProperty(CarPropertyKey.HVAC_FUNC_DEFROST_REAR)
        val enable = current != 1
        car.setPropertyIntValue(
            CarPropertyKey.HVAC_FUNC_DEFROST_REAR,
            Integer.MIN_VALUE,
            if (enable) 1 else 0
        )
        toast(if (enable) R.string.car_fn_toast_rear_on else R.string.car_fn_toast_rear_off)
    }

    private suspend fun toggleTrunk() {
        val area = CarFunctionIds.ZONE_TRUNK
        val current = readLevelValue(CarPropertyKey.BCM_FUNC_DOOR, area)
        val open = current != CarFunctionIds.TRUNK_OPEN
        car.setPropertyIntValue(
            CarPropertyKey.BCM_FUNC_DOOR,
            area,
            if (open) CarFunctionIds.TRUNK_OPEN else CarFunctionIds.TRUNK_CLOSE
        )
        toast(if (open) R.string.car_fn_toast_trunk_open else R.string.car_fn_toast_trunk_close)
    }

    private suspend fun toggleMirrors() {
        val current = car.getIntProperty(CarPropertyKey.BCM_FUNC_FOLD_REAR_MIRROR)
        val fold = current != 1
        car.setPropertyIntValue(
            CarPropertyKey.BCM_FUNC_FOLD_REAR_MIRROR,
            Integer.MIN_VALUE,
            if (fold) 1 else 0
        )
        toast(
            if (fold) R.string.car_fn_toast_mirrors_fold
            else R.string.car_fn_toast_mirrors_unfold
        )
    }

    private suspend fun toggleWipers() {
        val area = CarFunctionIds.ZONE_DRIVER
        val current = readLevelValue(
            CarPropertyKey.SETTING_FUNC_WINDSCREEN_SERVICE_POSITION,
            area
        )
        val enable = current != 1
        car.setPropertyIntValue(
            CarPropertyKey.SETTING_FUNC_WINDSCREEN_SERVICE_POSITION,
            area,
            if (enable) 1 else 0
        )
    }

    private suspend fun toggleImHotCooled() {
        if (imHotCooledNextIsHot) applyImHot() else applyImCooled()
        imHotCooledNextIsHot = !imHotCooledNextIsHot
    }

    private suspend fun toggleImColdWarmed() {
        if (imColdWarmedNextIsCold) applyImCold() else applyImWarmed()
        imColdWarmedNextIsCold = !imColdWarmedNextIsCold
    }

    private suspend fun applyImHot() {
        refreshTempLimits()
        car.setPropertyIntValue(
            CarPropertyKey.HVAC_FUNC_AC,
            Integer.MIN_VALUE,
            1
        )
        car.setPropertyIntValue(
            CarPropertyKey.HVAC_FUNC_CIRCULATION,
            Integer.MIN_VALUE,
            CarFunctionIds.CIRCULATION_ON
        )
        car.setPropertyFloatValue(
            CarPropertyKey.HVAC_FUNC_TEMP,
            CarFunctionIds.ZONE_DRIVER,
            tempLo
        )
        car.setPropertyIntValue(
            CarPropertyKey.HVAC_FUNC_AUTO_FAN_SETTING,
            CarFunctionIds.ZONE_ROW_1_ALL,
            CarFunctionIds.AUTO_FAN_HIGH
        )
        if (carModel != CarModel.CITYRAY) {
            car.setPropertyIntValue(
                CarPropertyKey.HVAC_FUNC_SEAT_VENTILATION,
                CarFunctionIds.ZONE_DRIVER,
                CarFunctionIds.SEAT_VENT_L3
            )
        }
        toast(R.string.car_fn_toast_me_hot)
    }

    private suspend fun applyImCooled() {
        car.setPropertyIntValue(
            CarPropertyKey.HVAC_FUNC_CIRCULATION,
            Integer.MIN_VALUE,
            CarFunctionIds.CIRCULATION_OFF
        )
        car.setPropertyFloatValue(
            CarPropertyKey.HVAC_FUNC_TEMP,
            CarFunctionIds.ZONE_DRIVER,
            19f
        )
        car.setPropertyIntValue(
            CarPropertyKey.HVAC_FUNC_AUTO_FAN_SETTING,
            CarFunctionIds.ZONE_ROW_1_ALL,
            CarFunctionIds.AUTO_FAN_LOW
        )
        if (carModel != CarModel.CITYRAY) {
            car.setPropertyIntValue(
                CarPropertyKey.HVAC_FUNC_SEAT_VENTILATION,
                CarFunctionIds.ZONE_DRIVER,
                0
            )
        }
        toast(R.string.car_fn_toast_me_cooled)
    }

    private suspend fun applyImCold() {
        car.setPropertyIntValue(
            CarPropertyKey.HVAC_FUNC_SEAT_HEATING,
            CarFunctionIds.ZONE_DRIVER,
            CarFunctionIds.SEAT_HEAT_L3
        )
        car.setPropertyIntValue(
            CarPropertyKey.HVAC_FUNC_STEERING_WHEEL_HEAT,
            Integer.MIN_VALUE,
            CarFunctionIds.STEERING_HEAT_L3
        )
        toast(R.string.car_fn_toast_me_cold)
    }

    private suspend fun applyImWarmed() {
        car.setPropertyIntValue(
            CarPropertyKey.HVAC_FUNC_SEAT_HEATING,
            CarFunctionIds.ZONE_DRIVER,
            0
        )
        car.setPropertyIntValue(
            CarPropertyKey.HVAC_FUNC_STEERING_WHEEL_HEAT,
            Integer.MIN_VALUE,
            0
        )
        toast(R.string.car_fn_toast_me_warmed)
    }

    private suspend fun adjustTemp(up: Boolean) {
        refreshTempLimits()
        val current = car.getFloatProperty(
            CarPropertyKey.HVAC_FUNC_TEMP,
            CarFunctionIds.ZONE_DRIVER
        )
        val base = if (current < 0f) 22f else current
        val rawNext = base + if (up) TEMP_STEP else -TEMP_STEP
        val next = when {
            rawNext <= tempLo -> tempLo
            rawNext >= tempHi -> tempHi
            else -> rawNext
        }
        if (next == base) return
        car.setPropertyFloatValue(
            CarPropertyKey.HVAC_FUNC_TEMP,
            CarFunctionIds.ZONE_DRIVER,
            next
        )
    }

    private suspend fun refreshTempLimits() {
        val lo = readTempLimit(CarPropertyKey.HVAC_FUNC_TEMP_MIN)
        val hi = readTempLimit(CarPropertyKey.HVAC_FUNC_TEMP_MAX)
        if (lo >= 0f && hi >= 0f && hi > lo) {
            tempLo = lo
            tempHi = hi
        }
    }

    private suspend fun readTempLimit(propertyId: Int): Float {
        val global = car.getFloatProperty(propertyId, Integer.MIN_VALUE)
        if (global >= 0f) return global
        return car.getFloatProperty(propertyId, CarFunctionIds.ZONE_DRIVER)
    }

    private suspend fun adjustFan(up: Boolean) {
        val zone = CarFunctionIds.ZONE_ROW_1_ALL
        when (carModel) {
            CarModel.ATLAS -> adjustFanByLevel(up, zone)
            CarModel.PREFACE, CarModel.CITYRAY -> adjustFanBlower(up, zone)
            null -> Unit
        }
    }

    private suspend fun adjustFanBlower(up: Boolean, zone: Int) {
        car.setPropertyIntValue(
            CarPropertyKey.HVAC_FUNC_FAN_SPEED_BLOWER,
            zone,
            if (up) CarFunctionIds.FAN_BLOWER_UP else CarFunctionIds.FAN_BLOWER_DOWN
        )
    }

    private suspend fun adjustFanByLevel(up: Boolean, zone: Int) {
        if (isFanAutoMode(zone)) {
            val levels = if (carModel == CarModel.ATLAS) {
                CarFunctionIds.ATLAS_AUTO_FAN_LEVELS
            } else {
                CarFunctionIds.AUTO_FAN_LEVELS
            }
            cycleAbsoluteLevel(CarPropertyKey.HVAC_FUNC_AUTO_FAN_SETTING, zone, levels, up)
        } else {
            cycleAbsoluteLevel(
                CarPropertyKey.HVAC_FUNC_FAN_SPEED,
                zone,
                CarFunctionIds.MANUAL_FAN_LEVELS,
                up,
            )
        }
    }

    private suspend fun isFanAutoMode(zone: Int): Boolean {
        val current = car.getIntProperty(CarPropertyKey.HVAC_FUNC_AUTO_FAN_SETTING, zone)
        return current in CarFunctionIds.ATLAS_AUTO_FAN_LEVELS
    }

    private suspend fun cycleAbsoluteLevel(
        propertyId: Int,
        zone: Int,
        levels: List<Int>,
        up: Boolean,
    ) {
        if (levels.isEmpty()) return
        val current = car.getIntProperty(propertyId, zone)
        val index = levels.indexOf(current).takeIf { it >= 0 }
            ?: if (up) 0 else levels.lastIndex
        val nextIndex = if (up) {
            if (index >= levels.lastIndex) levels.lastIndex else index + 1
        } else {
            if (index <= 0) 0 else index - 1
        }
        car.setPropertyIntValue(propertyId, zone, levels[nextIndex])
    }

    private suspend fun toast(@androidx.annotation.StringRes res: Int) {
        context.inMainToast(context.getString(res))
    }

    private suspend fun toast(text: String) {
        context.inMainToast(text)
    }

    private fun Int.isMediaPadKey(): Boolean =
        this == KEY_PREV || this == KEY_NEXT || this == KEY_PLAY

    companion object {
        private const val LEVEL_LOCK_SEC = 5
        private const val TEMP_STEP = 0.5f
        private const val DEFAULT_TEMP_LO = 16f
        private const val DEFAULT_TEMP_HI = 28f
        private const val SEAT_MEMORY_PACKAGE = "com.geely.hvac"
        private const val SEAT_MEMORY_ACTIVITY =
            "com.geely.hvac.activity.SeatSetDetailActivity"
        const val KEY_PREV = 200088
        const val KEY_NEXT = 200087
        const val KEY_PLAY = 200085
    }
}
