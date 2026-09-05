package com.salat.gbinder.adb.data.entity

data class AdbCommandResult(
    val exitCode: Int,
    val output: String
) {
    companion object {
        const val EXIT_TRANSPORT_ERROR = -1
        const val EXIT_TIMEOUT = -2
        const val EXIT_CANCELLED = -3
    }
}
