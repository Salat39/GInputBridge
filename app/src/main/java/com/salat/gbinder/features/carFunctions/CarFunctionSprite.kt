package com.salat.gbinder.features.carFunctions

import androidx.annotation.DrawableRes
import com.salat.gbinder.R
import com.salat.gbinder.entity.CarFunction

// Each sequence ends with the static frame of its state, a single frame plays as no animation
class ToggleSprite(
    @DrawableRes val turnOn: List<Int>,
    @DrawableRes val turnOff: List<Int>,
)

fun CarFunction.toggleSprite(): ToggleSprite? = when (this) {
    CarFunction.RECIRCULATION -> ToggleSprite(
        turnOn = listOf(
            R.drawable.ic_fn_recirculation_on_00,
            R.drawable.ic_fn_recirculation_on_01,
            R.drawable.ic_fn_recirculation_on_02,
            R.drawable.ic_fn_recirculation_on_03,
            R.drawable.ic_fn_recirculation_on_04,
            R.drawable.ic_fn_recirculation_on_05,
            R.drawable.ic_fn_recirculation_on_06,
            R.drawable.ic_fn_recirculation_on_07,
            R.drawable.ic_fn_recirculation_on_08,
            R.drawable.ic_fn_recirculation_on_09,
            R.drawable.ic_fn_recirculation_on_10,
            R.drawable.ic_fn_recirculation_on_11,
            R.drawable.ic_fn_recirculation_on_12,
            R.drawable.ic_fn_recirculation_on_13,
            R.drawable.ic_fn_recirculation_on_14,
        ),
        turnOff = listOf(
            R.drawable.ic_fn_recirculation_off_00,
            R.drawable.ic_fn_recirculation_off_01,
            R.drawable.ic_fn_recirculation_off_02,
            R.drawable.ic_fn_recirculation_off_03,
            R.drawable.ic_fn_recirculation_off_04,
            R.drawable.ic_fn_recirculation_off_05,
            R.drawable.ic_fn_recirculation_off_06,
            R.drawable.ic_fn_recirculation_off_07,
            R.drawable.ic_fn_recirculation_off_08,
            R.drawable.ic_fn_recirculation_off_09,
            R.drawable.ic_fn_recirculation_off_10,
            R.drawable.ic_fn_recirculation_off_11,
            R.drawable.ic_fn_recirculation_off_12,
            R.drawable.ic_fn_recirculation_off_13,
            R.drawable.ic_fn_recirculation_off_14,
        ),
    )
    CarFunction.FRONT_DEFROST -> ToggleSprite(
        turnOn = listOf(
            R.drawable.ic_fn_front_defrost_on_00,
            R.drawable.ic_fn_front_defrost_on_01,
            R.drawable.ic_fn_front_defrost_on_02,
            R.drawable.ic_fn_front_defrost_on_03,
            R.drawable.ic_fn_front_defrost_on_04,
            R.drawable.ic_fn_front_defrost_on_05,
            R.drawable.ic_fn_front_defrost_on_06,
            R.drawable.ic_fn_front_defrost_on_07,
            R.drawable.ic_fn_front_defrost_on_08,
            R.drawable.ic_fn_front_defrost_on_09,
            R.drawable.ic_fn_front_defrost_on_10,
            R.drawable.ic_fn_front_defrost_on_11,
            R.drawable.ic_fn_front_defrost_on_12,
            R.drawable.ic_fn_front_defrost_on_13,
            R.drawable.ic_fn_front_defrost_on_14,
        ),
        turnOff = listOf(iconRes),
    )
    CarFunction.REAR_DEFROST -> ToggleSprite(
        turnOn = listOf(
            R.drawable.ic_fn_rear_defrost_on_00,
            R.drawable.ic_fn_rear_defrost_on_01,
            R.drawable.ic_fn_rear_defrost_on_02,
            R.drawable.ic_fn_rear_defrost_on_03,
            R.drawable.ic_fn_rear_defrost_on_04,
            R.drawable.ic_fn_rear_defrost_on_05,
            R.drawable.ic_fn_rear_defrost_on_06,
            R.drawable.ic_fn_rear_defrost_on_07,
            R.drawable.ic_fn_rear_defrost_on_08,
            R.drawable.ic_fn_rear_defrost_on_09,
            R.drawable.ic_fn_rear_defrost_on_10,
            R.drawable.ic_fn_rear_defrost_on_11,
            R.drawable.ic_fn_rear_defrost_on_12,
            R.drawable.ic_fn_rear_defrost_on_13,
            R.drawable.ic_fn_rear_defrost_on_14,
        ),
        turnOff = listOf(iconRes),
    )
    else -> null
}
