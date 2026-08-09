package com.salat.gbinder.entity

import androidx.annotation.StringRes
import androidx.datastore.preferences.core.Preferences
import com.salat.gbinder.BuildConfig
import com.salat.gbinder.R
import com.salat.gbinder.datastore.GeneralPrefs
import kotlinx.serialization.Serializable

enum class CarModel {
    PREFACE,
    ATLAS,
    CITYRAY,
    ;
}

@Serializable
enum class CarFunction(
    @StringRes val titleRes: Int,
    @StringRes val descRes: Int? = null,
) {
    CLIMATE_MENU(R.string.car_fn_climate_menu, R.string.car_fn_climate_menu_desc),
    WHEEL_HEAT(R.string.car_fn_wheel_heat, R.string.car_fn_wheel_heat_desc),
    DRIVER_HEAT(R.string.car_fn_driver_heat, R.string.car_fn_driver_heat_desc),
    PASSENGER_HEAT(R.string.car_fn_passenger_heat, R.string.car_fn_passenger_heat_desc),
    DRIVER_VENT(R.string.car_fn_driver_vent, R.string.car_fn_driver_vent_desc),
    PASSENGER_VENT(R.string.car_fn_passenger_vent, R.string.car_fn_passenger_vent_desc),
    FRONT_DEFROST(R.string.car_fn_front_defrost, R.string.car_fn_front_defrost_desc),
    REAR_DEFROST(R.string.car_fn_rear_defrost, R.string.car_fn_rear_defrost_desc),
    MAX_DEFROST(R.string.car_fn_max_defrost, R.string.car_fn_max_defrost_desc),
    RECIRCULATION(R.string.car_fn_recirculation, R.string.car_fn_recirculation_desc),
    ME_HOT(R.string.car_fn_me_hot, R.string.car_fn_me_hot_desc),
    ME_COOLED(R.string.car_fn_me_cooled),
    ME_COLD(R.string.car_fn_me_cold, R.string.car_fn_me_cold_desc),
    ME_WARMED(R.string.car_fn_me_warmed),
    ANTIBUKS(R.string.car_fn_antibuks, R.string.car_fn_antibuks_desc),
    MIRRORS(R.string.car_fn_mirrors, R.string.car_fn_mirrors_desc),
    LIGHT(R.string.car_fn_light, R.string.car_fn_light_desc),
    SEAT_MEMORY(R.string.car_fn_seat_memory, R.string.car_fn_seat_memory_desc),
    TRUNK(R.string.car_fn_trunk, R.string.car_fn_trunk_desc),
    WIPERS(R.string.car_fn_wipers, R.string.car_fn_wipers_desc),
    ;

    fun isAvailableFor(model: CarModel?): Boolean = when (this) {
        LIGHT -> model == CarModel.PREFACE
        FRONT_DEFROST -> model != null
        SEAT_MEMORY, MAX_DEFROST, REAR_DEFROST -> model == CarModel.ATLAS || model == CarModel.CITYRAY
        DRIVER_VENT, PASSENGER_VENT -> model == CarModel.PREFACE || model == CarModel.ATLAS
        else -> true
    }

    fun hasConfigurableDefaultLevel(): Boolean = when (this) {
        WHEEL_HEAT, DRIVER_HEAT, PASSENGER_HEAT, DRIVER_VENT, PASSENGER_VENT -> true
        else -> false
    }

    fun defaultLevelPrefKey(): Preferences.Key<Int>? = when (this) {
        WHEEL_HEAT -> GeneralPrefs.CAR_FN_DEFAULT_WHEEL_HEAT
        DRIVER_HEAT -> GeneralPrefs.CAR_FN_DEFAULT_DRIVER_HEAT
        PASSENGER_HEAT -> GeneralPrefs.CAR_FN_DEFAULT_PASSENGER_HEAT
        DRIVER_VENT -> GeneralPrefs.CAR_FN_DEFAULT_DRIVER_VENT
        PASSENGER_VENT -> GeneralPrefs.CAR_FN_DEFAULT_PASSENGER_VENT
        else -> null
    }

    companion object {
        const val DEFAULT_HEAT_VENT_LEVEL = 3

        fun fromValue(raw: String): CarFunction? =
            entries.firstOrNull { it.name == raw.trim() }

        fun availableFor(model: CarModel?): List<CarFunction> =
            entries.filter { (BuildConfig.DEBUG || it.isAvailableFor(model)) && it.isListedInMenu() }

        private fun CarFunction.isListedInMenu(): Boolean = when (this) {
            ME_COOLED, ME_WARMED -> false
            else -> true
        }
    }
}

object CarFunctionIds {
    const val ZONE_DRIVER = 1
    const val ZONE_PASSENGER = 4
    const val ZONE_ROW_1_ALL = 8
    const val ZONE_TRUNK = 536870912
    const val AREA_DOOR_REAR = ZONE_TRUNK
    const val TRUNK_CLOSE = 0
    const val TRUNK_OPEN = 1

    const val STEERING_HEAT_L1 = 269025537
    const val STEERING_HEAT_L2 = 269025538
    const val STEERING_HEAT_L3 = 269025539

    const val SEAT_HEAT_L1 = 268763649
    const val SEAT_HEAT_L2 = 268763650
    const val SEAT_HEAT_L3 = 268763651

    const val SEAT_VENT_L1 = 268763393
    const val SEAT_VENT_L2 = 268763394
    const val SEAT_VENT_L3 = 268763395

    const val CIRCULATION_ON = 268632321
    const val CIRCULATION_OFF = 268632322

    const val FAN_BLOWER_UP = 269752065
    const val FAN_BLOWER_DOWN = 269752066

    const val AUTO_FAN_LOW = 268567041
    const val AUTO_FAN_MEDIUM = 268567042
    const val AUTO_FAN_HIGH = 268567043
    const val AUTO_FAN_QUIETER = 268567044
    const val AUTO_FAN_HIGHER = 268567045

    const val FAN_SPEED_LEVEL_1 = 268566785
    const val FAN_SPEED_OFF = 0

    val ATLAS_AUTO_FAN_LEVELS = listOf(
        AUTO_FAN_QUIETER,
        AUTO_FAN_LOW,
        AUTO_FAN_MEDIUM,
        AUTO_FAN_HIGH,
        AUTO_FAN_HIGHER,
    )
    val AUTO_FAN_LEVELS = listOf(AUTO_FAN_LOW, AUTO_FAN_MEDIUM, AUTO_FAN_HIGH)
    val MANUAL_FAN_LEVELS = (1..9).map { FAN_SPEED_LEVEL_1 + it - 1 }

    const val FRONT_DEFROST_PREFACE = 269753088
    const val FRONT_DEFROST_ATLAS = 269027328
    const val FRONT_DEFROST_CITYRAY = 269619968

    const val LIGHT_POS = 537136641
    const val LIGHT_LOW = 537136642
    const val LIGHT_AUTO = 537136643

    val STEERING_HEAT_LEVELS = listOf(0, STEERING_HEAT_L1, STEERING_HEAT_L2, STEERING_HEAT_L3)
    val SEAT_HEAT_LEVELS = listOf(0, SEAT_HEAT_L1, SEAT_HEAT_L2, SEAT_HEAT_L3)
    val SEAT_VENT_LEVELS = listOf(0, SEAT_VENT_L1, SEAT_VENT_L2, SEAT_VENT_L3)
    val LIGHT_LEVELS = listOf(0, LIGHT_POS, LIGHT_LOW, LIGHT_AUTO)

    fun frontDefrostId(model: CarModel): Int = when (model) {
        CarModel.PREFACE -> FRONT_DEFROST_PREFACE
        CarModel.ATLAS -> FRONT_DEFROST_ATLAS
        CarModel.CITYRAY -> FRONT_DEFROST_CITYRAY
    }
}
