package com.salat.gbinder.entity

data class AppPanelConfig(val stepMode: Boolean, val packages: List<String>) {
    fun encode(): String = (listOf(if (stepMode) STEP else FREE) + packages).joinToString("|")

    companion object {
        private const val STEP = "step"
        private const val FREE = "free"

        fun parse(value: String): AppPanelConfig {
            val parts = value.split('|').filter { it.isNotBlank() }
            return when (parts.firstOrNull()) {
                STEP -> AppPanelConfig(true, parts.drop(1))
                FREE -> AppPanelConfig(false, parts.drop(1))
                else -> AppPanelConfig(false, parts)
            }
        }
    }
}
