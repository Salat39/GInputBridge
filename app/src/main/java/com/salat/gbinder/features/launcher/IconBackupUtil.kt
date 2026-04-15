package com.salat.gbinder.features.launcher

import android.content.Context
import android.util.Base64
import android.util.Base64InputStream
import android.util.Base64OutputStream
import androidx.annotation.CheckResult
import java.io.BufferedInputStream
import java.io.BufferedOutputStream
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.nio.charset.Charset
import java.util.zip.Deflater
import java.util.zip.ZipEntry
import java.util.zip.ZipInputStream
import java.util.zip.ZipOutputStream

private const val ICONS_DIR_NAME = "icons"
private const val BUFFER_SIZE = 8 * 1024
const val BACKUP_DIVIDER = "[DIV]"

@CheckResult
fun Context.backupIconsToString(): String {
    try {
        val iconsDir = File(filesDir, ICONS_DIR_NAME)
        val files = iconsDir.listFiles()?.filter { it.isFile } ?: return ""
        if (files.isEmpty()) return ""

        val outB64Bytes = ByteArrayOutputStream()

        Base64OutputStream(outB64Bytes, Base64.NO_WRAP).use { b64 ->
            ZipOutputStream(BufferedOutputStream(b64, BUFFER_SIZE)).use { zos ->
                zos.setLevel(Deflater.BEST_SPEED)

                val buf = ByteArray(BUFFER_SIZE)
                for (f in files) {
                    try {
                        val entry = ZipEntry(f.name).apply { time = f.lastModified() }
                        zos.putNextEntry(entry)
                        FileInputStream(f).use { fis ->
                            BufferedInputStream(fis, BUFFER_SIZE).use { bis ->
                                while (true) {
                                    val r = bis.read(buf)
                                    if (r <= 0) break
                                    zos.write(buf, 0, r)
                                }
                            }
                        }
                        zos.closeEntry()
                    } catch (_: Throwable) {
                        runCatching { zos.closeEntry() }
                    }
                }
            }
        }

        return outB64Bytes.toString(Charsets.US_ASCII.name())
    } catch (_: Throwable) {
        return ""
    }
}

@CheckResult
fun Context.restoreIconsFromString(backupBase64: String): Int {
    if (backupBase64.isBlank()) return 0

    return try {
        val iconsDir = File(filesDir, ICONS_DIR_NAME)
        if (!iconsDir.exists()) runCatching { iconsDir.mkdirs() }

        var restored = 0
        val asciiBytes = backupBase64.toByteArray(Charset.forName("US-ASCII"))

        ByteArrayInputStream(asciiBytes).use { bin ->
            Base64InputStream(bin, Base64.NO_WRAP).use { b64in ->
                ZipInputStream(BufferedInputStream(b64in, BUFFER_SIZE)).use { zis ->
                    val buf = ByteArray(BUFFER_SIZE)

                    while (true) {
                        val entry = runCatching { zis.nextEntry }.getOrNull() ?: break
                        try {
                            if (entry.isDirectory) {
                                runCatching { zis.closeEntry() }
                                continue
                            }

                            val outFile = File(iconsDir, entry.name)
                            val canonicalIcons = iconsDir.canonicalPath
                            val canonicalOut = outFile.canonicalPath
                            if (!canonicalOut.startsWith(canonicalIcons)) {
                                runCatching { zis.closeEntry() }
                                continue
                            }

                            FileOutputStream(outFile, false).use { fos ->
                                BufferedOutputStream(fos, BUFFER_SIZE).use { bos ->
                                    while (true) {
                                        val r = zis.read(buf)
                                        if (r <= 0) break
                                        bos.write(buf, 0, r)
                                    }
                                    bos.flush()
                                }
                            }

                            if (entry.time > 0L) {
                                runCatching { outFile.setLastModified(entry.time) }
                            }

                            restored++
                        } catch (_: Throwable) {
                        } finally {
                            runCatching { zis.closeEntry() }
                        }
                    }
                }
            }
        }
        restored
    } catch (_: Throwable) {
        0
    }
}

fun String.cutBeforeDiv(): String {
    val i = this.indexOf(BACKUP_DIVIDER)
    return if (i >= 0) this.substring(0, i) else this
}
