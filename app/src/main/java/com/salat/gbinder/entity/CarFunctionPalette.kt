package com.salat.gbinder.entity

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import com.salat.gbinder.R

data class CarFunctionPaletteColors(
    val tile: Color,
    val softTile: Color,
    val softContent: Color,
    val customIconLit: Color
)

enum class CarFunctionPalette(
    @StringRes val titleRes: Int,
    val light: CarFunctionPaletteColors,
    val dark: CarFunctionPaletteColors
) {
    BLUE(
        R.string.car_function_palette_blue,
        light = CarFunctionPaletteColors(
            tile = Color(0xFF2563EB),
            softTile = Color(0xFFAFD0FF),
            softContent = Color(0xFF2563EB),
            customIconLit = Color(0xFF6BA9FE)
        ),
        dark = CarFunctionPaletteColors(
            tile = Color(0xFF1975D0),
            softTile = Color(0xFF253A54),
            softContent = Color(0xFFAFD0FF),
            customIconLit = Color(0xFF6BA9FE)
        )
    ),
    TEAL(
        R.string.car_function_palette_teal,
        light = CarFunctionPaletteColors(
            tile = Color(0xFF0F8A7C),
            softTile = Color(0xFFBFE9E2),
            softContent = Color(0xFF0B6B60),
            customIconLit = Color(0xFF5FD4C4)
        ),
        dark = CarFunctionPaletteColors(
            tile = Color(0xFF12907F),
            softTile = Color(0xFF1E3D39),
            softContent = Color(0xFF5FD4C4),
            customIconLit = Color(0xFF5FD4C4)
        )
    ),
    EMERALD(
        R.string.car_function_palette_emerald,
        light = CarFunctionPaletteColors(
            tile = Color(0xFF0F9160),
            softTile = Color(0xFFC3EBD8),
            softContent = Color(0xFF0B6B47),
            customIconLit = Color(0xFF5DD3A0)
        ),
        dark = CarFunctionPaletteColors(
            tile = Color(0xFF159A66),
            softTile = Color(0xFF1D3A2E),
            softContent = Color(0xFF5DD3A0),
            customIconLit = Color(0xFF5DD3A0)
        )
    ),
    VIOLET(
        R.string.car_function_palette_violet,
        light = CarFunctionPaletteColors(
            tile = Color(0xFF6D4FD6),
            softTile = Color(0xFFDED6FA),
            softContent = Color(0xFF5537B8),
            customIconLit = Color(0xFFB4A6F5)
        ),
        dark = CarFunctionPaletteColors(
            tile = Color(0xFF6B5BD0),
            softTile = Color(0xFF302A52),
            softContent = Color(0xFFB4A6F5),
            customIconLit = Color(0xFFB4A6F5)
        )
    );

    fun colors(dark: Boolean) = if (dark) this.dark else light

    companion object {
        fun fromOrdinal(value: Int) = entries.getOrElse(value) { BLUE }
    }
}
