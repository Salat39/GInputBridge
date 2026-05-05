package com.salat.gbinder.adb.data.repository

import com.salat.gbinder.adb.data.entity.TelnetShellEndpoint
import timber.log.Timber
import java.io.File
import java.io.IOException
import java.net.Inet4Address
import java.net.Inet6Address
import java.net.InetAddress
import java.net.NetworkInterface
import java.util.LinkedHashSet
import java.util.TreeSet

internal class TelnetShellDiscovery(private val markerFactory: () -> String) {

    @Volatile
    private var cachedEndpoint: TelnetShellEndpoint? = null

    fun open(): Pair<TelnetShellEndpoint, TelnetShellTransport> {
        val cached = cachedEndpoint
        if (cached != null) {
            runCatching {
                val transport = TelnetShellTransport.connect(cached.host, cached.port)
                if (isTelnetShell(transport)) {
                    return cached to transport
                }
                transport.close()
            }
        }

        for (host in candidateHosts()) {
            for (port in listeningPorts(host)) {
                val transport = runCatching {
                    TelnetShellTransport.connect(host, port)
                }.getOrNull() ?: continue

                if (isTelnetShell(transport)) {
                    val endpoint = TelnetShellEndpoint(host, port)
                    cachedEndpoint = endpoint
                    return endpoint to transport
                }

                transport.close()
            }
        }

        throw IOException("Telnet shell endpoint not found")
    }

    fun clearCache() {
        cachedEndpoint = null
    }

    private fun isTelnetShell(transport: TelnetShellTransport): Boolean {
        return try {
            val (output, exitCode) = transport.exec("pm path android", markerFactory())
            exitCode == 0 && output.contains("package:")
        } catch (_: Throwable) {
            false
        }
    }

    private fun candidateHosts(): List<String> {
        val hosts = LinkedHashSet<String>()
        hosts.add("127.0.0.1")
        hosts.add("::1")
        try {
            val ifaces = NetworkInterface.getNetworkInterfaces()
            if (ifaces != null) {
                while (ifaces.hasMoreElements()) {
                    val ni = ifaces.nextElement()
                    if (!ni.isUp || ni.isLoopback) continue
                    val addrs = ni.inetAddresses
                    while (addrs.hasMoreElements()) {
                        addrs.nextElement().hostAddress?.let { hosts.add(it) }
                    }
                }
            }
        } catch (t: Throwable) {
            Timber.d(t, "[Telnet] network interface enumeration error")
        }
        return hosts.toList()
    }

    private fun listeningPorts(host: String): List<Int> {
        val addr = runCatching { InetAddress.getByName(host) }.getOrNull() ?: return emptyList()
        val isV6 = addr is Inet6Address
        val ports = TreeSet<Int>()

        if (!isV6) {
            val v4Hex = ipv4ToProcHex(addr)
            if (v4Hex != null) {
                runCatching { readProcTcp("/proc/net/tcp", v4Hex, false, ports) }
            }
        }

        val v6Hex = if (isV6) ipv6ToProcHex(addr) else null
        runCatching { readProcTcp("/proc/net/tcp6", v6Hex, true, ports) }

        return ports.toList()
    }

    private fun readProcTcp(path: String, hostHex: String?, ipv6: Boolean, portsOut: MutableSet<Int>) {
        val anyHex = if (ipv6) {
            "00000000000000000000000000000000"
        } else {
            "00000000"
        }

        File(path).bufferedReader().use { br ->
            br.readLine()
            while (true) {
                val line = br.readLine() ?: break
                val parts = line.trim().split(Regex("\\s+"))
                if (parts.size < 4) continue
                if (!parts[3].equals("0A", ignoreCase = true)) continue
                val addr = parts[1].split(':')
                if (addr.size != 2) continue
                val localHex = addr[0]
                val matches = anyHex.equals(localHex, ignoreCase = true) ||
                        (hostHex != null && hostHex.equals(localHex, ignoreCase = true))
                if (!matches) continue
                addr[1].toIntOrNull(16)?.let { portsOut.add(it) }
            }
        }
    }

    private fun ipv4ToProcHex(addr: InetAddress): String? {
        if (addr !is Inet4Address) return null
        val b = addr.address
        return hex2(b[3]) + hex2(b[2]) + hex2(b[1]) + hex2(b[0])
    }

    private fun ipv6ToProcHex(addr: InetAddress): String? {
        if (addr !is Inet6Address) return null
        val b = addr.address
        val sb = StringBuilder(32)
        for (word in 0 until 4) {
            val base = word * 4
            for (i in 3 downTo 0) {
                sb.append(hex2(b[base + i]))
            }
        }
        return sb.toString()
    }

    private fun hex2(value: Byte): String {
        val v = value.toInt() and 0xFF
        val chars = "0123456789ABCDEF"
        return "" + chars[v ushr 4] + chars[v and 0x0F]
    }
}
