package com.salat.gbinder.car.data.mappers

internal object CustomKeyValueMapping {
    // AdaptApi value -> HardwareApi value (for CustomKey)
    val adaptApiToHardwareApiCustomKeyMap: Map<Int, Int> = mapOf(
        3 to 0,
        0 to 1,
        1 to 2,
        2 to 3,
        4 to 4,
        5 to 5,
        99 to 6,
        6 to 7,
        100 to 8,
        101 to 9,
        102 to 10
    )

    // HardwareApi value -> AdaptApi value (for CustomKey)
    val hardwareApiToAdaptApiCustomKeyMap: Map<Int, Int> =
        adaptApiToHardwareApiCustomKeyMap.entries.associate { (adapt, hw) -> hw to adapt }
}

/* Converts AdaptApi CustomKey value to HardwareApi. Falls back to the same value if unmapped. */
fun Int.toHardwareApiCustomKey(): Int =
    CustomKeyValueMapping.adaptApiToHardwareApiCustomKeyMap[this] ?: this

/* Converts HardwareApi CustomKey value back to AdaptApi. Falls back to the same value if unmapped. */
fun Int.toAdaptApiCustomKey(): Int =
    CustomKeyValueMapping.hardwareApiToAdaptApiCustomKeyMap[this] ?: this
