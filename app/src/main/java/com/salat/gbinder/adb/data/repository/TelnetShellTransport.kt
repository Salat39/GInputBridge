package com.salat.gbinder.adb.data.repository

import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import java.net.InetSocketAddress
import java.net.Socket
import java.net.SocketTimeoutException
import java.nio.charset.StandardCharsets

internal class TelnetShellTransport private constructor(
    private val socket: Socket,
    private val input: InputStream,
    private val output: OutputStream
) {
    fun exec(command: String, marker: String): Pair<String, Int> {
        socket.soTimeout = READ_TIMEOUT_MS
        val effectiveCommand = appendMarker(command, marker) + "\n"
        output.write(effectiveCommand.toByteArray(StandardCharsets.UTF_8))
        output.flush()
        return readResponseUntilMarker(marker)
    }

    fun isClosed() = socket.isClosed

    fun close() = runCatching { socket.close() }

    private fun appendMarker(command: String, marker: String): String {
        val trimmed = command.trimEnd()
        return if (trimmed.endsWith(";")) {
            "$trimmed echo $marker\$?"
        } else {
            "$trimmed; echo $marker\$?"
        }
    }

    private fun readResponseUntilMarker(marker: String): Pair<String, Int> {
        val collected = ByteArrayOutputStream()
        val buf = ByteArray(4096)
        val markerBytes = marker.toByteArray(StandardCharsets.US_ASCII)
        var markerSeen = false

        while (true) {
            val len = try {
                input.read(buf)
            } catch (e: SocketTimeoutException) {
                if (markerSeen) break else throw e
            }
            if (len == -1) break

            var i = 0
            while (i < len) {
                val b = buf[i].toInt() and 0xFF
                if (b == IAC && i + 2 < len) {
                    val cmd = buf[i + 1].toInt() and 0xFF
                    val opt = buf[i + 2]
                    if (cmd == WILL || cmd == DO) {
                        output.write(byteArrayOf(IAC.toByte(), if (cmd == WILL) WONT.toByte() else DONT.toByte(), opt))
                        output.flush()
                    }
                    i += 3
                } else {
                    if (b != 0x00 && b != '\r'.code) {
                        collected.write(b)
                    }
                    i++
                }
            }

            if (!markerSeen && containsSequence(collected, markerBytes)) {
                markerSeen = true
                socket.soTimeout = TRAILING_DRAIN_MS
            }
        }

        return parseResponse(String(collected.toByteArray(), StandardCharsets.UTF_8), marker)
    }

    private fun parseResponse(full: String, marker: String): Pair<String, Int> {
        val markerPos = full.lastIndexOf(marker)
        if (markerPos < 0) throw IOException("Telnet stream closed before completion marker")

        val exit = parseLeadingInt(full.substring(markerPos + marker.length))
            ?: throw IOException("Telnet stream closed before exit code")
        var result = full.substring(0, markerPos)
        if (result.endsWith("\n")) {
            result = result.substring(0, result.length - 1)
        }

        val echoSuffix = "echo $marker\$?"
        val firstNewline = result.indexOf('\n')
        if (firstNewline >= 0) {
            val firstLine = result.substring(0, firstNewline)
            if (firstLine.endsWith(echoSuffix)) {
                result = result.substring(firstNewline + 1)
            }
        } else if (result.endsWith(echoSuffix)) {
            result = ""
        }

        return result.trim() to exit
    }

    companion object {
        private const val CONNECT_TIMEOUT_MS = 1_000
        private const val READ_TIMEOUT_MS = 5_000
        private const val BANNER_DRAIN_MS = 500
        private const val TRAILING_DRAIN_MS = 200
        private const val IAC = 0xFF
        private const val WILL = 0xFB
        private const val WONT = 0xFC
        private const val DO = 0xFD
        private const val DONT = 0xFE

        fun connect(host: String, port: Int): TelnetShellTransport {
            val socket = Socket()
            try {
                socket.connect(InetSocketAddress(host, port), CONNECT_TIMEOUT_MS)
                val input = socket.getInputStream()
                val output = socket.getOutputStream()
                drainBanner(socket, input, output)
                return TelnetShellTransport(socket, input, output)
            } catch (t: Throwable) {
                runCatching { socket.close() }
                throw t
            }
        }

        private fun drainBanner(socket: Socket, input: InputStream, output: OutputStream) {
            socket.soTimeout = BANNER_DRAIN_MS
            val buf = ByteArray(4096)
            while (true) {
                val len = try {
                    input.read(buf)
                } catch (_: SocketTimeoutException) {
                    break
                }
                if (len == -1) break
                handleIacInBuffer(buf, len, output)
            }
        }

        private fun handleIacInBuffer(buf: ByteArray, len: Int, output: OutputStream) {
            var i = 0
            while (i < len) {
                val b = buf[i].toInt() and 0xFF
                if (b == IAC && i + 2 < len) {
                    val cmd = buf[i + 1].toInt() and 0xFF
                    val opt = buf[i + 2]
                    if (cmd == WILL || cmd == DO) {
                        output.write(byteArrayOf(IAC.toByte(), if (cmd == WILL) WONT.toByte() else DONT.toByte(), opt))
                        output.flush()
                    }
                    i += 3
                } else {
                    i++
                }
            }
        }

        private fun parseLeadingInt(value: String): Int? {
            var i = 0
            while (i < value.length && value[i].isWhitespace()) i++
            if (i >= value.length || !value[i].isDigit()) return null

            var num = 0
            while (i < value.length && value[i].isDigit()) {
                num = num * 10 + (value[i] - '0')
                i++
            }
            return num
        }

        private fun containsSequence(haystack: ByteArrayOutputStream, needle: ByteArray): Boolean {
            val data = haystack.toByteArray()
            if (data.size < needle.size) return false
            for (i in 0..data.size - needle.size) {
                var matched = true
                for (j in needle.indices) {
                    if (data[i + j] != needle[j]) {
                        matched = false
                        break
                    }
                }
                if (matched) return true
            }
            return false
        }
    }
}
