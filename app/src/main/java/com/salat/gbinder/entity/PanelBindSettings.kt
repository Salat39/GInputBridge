package com.salat.gbinder.entity

// Bind value of a panel action - legacy values hold only the payload, v2 adds panel settings
data class PanelBindSettings(
    val maxFirst: Boolean,
    val offsetPx: Int,
    val payload: String
) {
    fun encode(): String = "$VERSION$SEPARATOR${if (maxFirst) 1 else 0}$SEPARATOR$offsetPx$SEPARATOR$payload"

    companion object {
        private const val VERSION = "v2"
        private const val SEPARATOR = ';'

        fun parse(value: String): PanelBindSettings {
            if (!value.startsWith("$VERSION$SEPARATOR")) return PanelBindSettings(false, 0, value)
            val parts = value.split(SEPARATOR, limit = 4)
            return PanelBindSettings(
                maxFirst = parts.getOrNull(1) == "1",
                offsetPx = parts.getOrNull(2)?.toIntOrNull() ?: 0,
                payload = parts.getOrElse(3) { "" }
            )
        }

        fun payloadOf(value: String): String = parse(value).payload
    }
}
