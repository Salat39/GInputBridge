package com.salat.gbinder.entity

import kotlinx.serialization.Serializable

@Serializable
data class KeyBindConfig(
    val action: KeyBindAction,
    val value: String
)
