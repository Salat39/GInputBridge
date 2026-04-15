package com.salat.gbinder.features.launcher

import android.annotation.SuppressLint
import android.content.ContentResolver
import android.content.ContentUris
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.media.ExifInterface
import android.net.Uri
import android.os.Build
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.provider.OpenableColumns
import androidx.core.graphics.scale
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.util.UUID
import kotlin.math.max
import kotlin.math.min
import kotlin.math.roundToInt

fun copyAndResizeIcon(
    context: Context,
    sourceUri: Uri,
    desiredMinSizePx: Int = 192,
    fileName: String? = null
): File {
    val iconsDir = File(context.filesDir, "icons").apply { if (!exists()) mkdirs() }

    val rawExt = guessExtension(context.contentResolver, sourceUri) ?: "png"
    val ext = normalizeImageExtensionForWrite(rawExt)
    val safeName = (fileName?.takeIf { it.isNotBlank() } ?: UUID.randomUUID().toString())
        .replace(Regex("""[^\w\-.]"""), "_")
    val outFile = File(iconsDir, "$safeName.$ext")

    val boundsOpts = BitmapFactory.Options().apply { inJustDecodeBounds = true }
    openForReadWithFallback(context, sourceUri).use {
        BitmapFactory.decodeStream(it, null, boundsOpts)
    }

    val target = desiredMinSizePx.coerceAtLeast(1)
    val sample = calculateInSampleSize(boundsOpts.outWidth, boundsOpts.outHeight, target)

    val decodeOpts = BitmapFactory.Options().apply {
        inSampleSize = sample
        inPreferredConfig = Bitmap.Config.ARGB_8888
    }
    val decoded = openForReadWithFallback(context, sourceUri).use {
        BitmapFactory.decodeStream(it, null, decodeOpts)
    } ?: error("Failed to decode bitmap")

    val rotated = openForReadWithFallback(context, sourceUri).use { input ->
        applyExifOrientationIfNeeded(decoded, input)
    }

    val scaled = scaleToMinSide(rotated, target)

    FileOutputStream(outFile).use { fos ->
        val format = compressFormatForNormalizedExt(ext)
        val quality = if (format == Bitmap.CompressFormat.JPEG) 94 else 100
        check(scaled.compress(format, quality, fos)) { "Failed to write bitmap" }
    }

    scaled.recycle()
    if (scaled !== rotated) rotated.recycle()
    if (rotated !== decoded) decoded.recycle()

    return outFile
}

fun copyAndResizeIcon(
    context: Context,
    sourceBitmap: Bitmap,
    desiredMinSizePx: Int = 192,
    fileName: String? = null
): File {
    val iconsDir = File(context.filesDir, "icons").apply { if (!exists()) mkdirs() }

    val rawExt = fileName
        ?.substringAfterLast('.', missingDelimiterValue = "")
        ?.lowercase()
        ?.takeIf { it in setOf("jpg", "jpeg", "png", "webp") }
        ?: "png"

    val ext = normalizeImageExtensionForWrite(rawExt)

    val safeName = (fileName?.takeIf { it.isNotBlank() } ?: UUID.randomUUID().toString())
        .replace(Regex("""[^\w\-.]"""), "_")
        .let { base -> if (base.contains('.')) base.substringBeforeLast('.') else base }

    val outFile = File(iconsDir, "$safeName.$ext")

    val owned = sourceBitmap.copy(Bitmap.Config.ARGB_8888, false)
        ?: error("Failed to copy source bitmap")

    val target = desiredMinSizePx.coerceAtLeast(1)
    val scaled = scaleToMinSide(owned, target)

    FileOutputStream(outFile).use { fos ->
        val format = compressFormatForNormalizedExt(ext)
        val quality = if (format == Bitmap.CompressFormat.JPEG) 94 else 100
        check(scaled.compress(format, quality, fos)) { "Failed to write bitmap" }
    }

    scaled.recycle()
    if (scaled !== owned) owned.recycle()

    return outFile
}

private fun normalizeImageExtensionForWrite(ext: String, api: Int = Build.VERSION.SDK_INT): String {
    val lower = ext.lowercase()
    return when (lower) {
        "jpg", "jpeg" -> "jpg"
        "png" -> "png"
        "webp" -> if (api >= Build.VERSION_CODES.R) "webp" else "png"
        else -> "png"
    }
}

private fun compressFormatForNormalizedExt(normExt: String): Bitmap.CompressFormat {
    return when (normExt) {
        "jpg" -> Bitmap.CompressFormat.JPEG
        "webp" -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                Bitmap.CompressFormat.WEBP_LOSSY
            } else {
                @Suppress("DEPRECATION")
                Bitmap.CompressFormat.WEBP
            }
        }

        else -> Bitmap.CompressFormat.PNG
    }
}

private fun openForReadWithFallback(context: Context, uri: Uri): InputStream {
    val cr = context.contentResolver
    try {
        cr.openInputStream(uri)?.let { return it }
    } catch (e: SecurityException) {
        throw SecurityException("No read permission for $uri", e)
    }
    if (DocumentsContract.isDocumentUri(context, uri)) {
        val docId = DocumentsContract.getDocumentId(uri)
        val parts = docId.split(":")
        if (parts.size == 2 && parts[0] == "image") {
            val id = parts[1].toLongOrNull()
            if (id != null) {
                val mediaUri =
                    ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, id)
                try {
                    cr.openInputStream(mediaUri)?.let { return it }
                } catch (e: SecurityException) {
                    throw SecurityException("No read permission for $mediaUri", e)
                }
            }
        }
    }
    error("Unable to open input stream for: $uri")
}

private fun calculateInSampleSize(srcWidth: Int, srcHeight: Int, desiredMinSide: Int): Int {
    if (srcWidth <= 0 || srcHeight <= 0) return 1
    val minSide = min(srcWidth, srcHeight).toFloat()
    if (minSide <= desiredMinSide) return 1
    var sample = 1
    var current = minSide
    while (current / 2 >= desiredMinSide) {
        sample *= 2
        current /= 2f
    }
    return max(1, sample)
}

private fun scaleToMinSide(bmp: Bitmap, targetMinSide: Int): Bitmap {
    val w = bmp.width
    val h = bmp.height
    val minSide = min(w, h)
    if (minSide == targetMinSide) return bmp
    val scale = targetMinSide.toFloat() / minSide.toFloat()
    val newW = (w * scale).roundToInt().coerceAtLeast(1)
    val newH = (h * scale).roundToInt().coerceAtLeast(1)
    if (newW == w && newH == h) return bmp
    return bmp.scale(newW, newH).also {
        if (it !== bmp) bmp.recycle()
    }
}

@SuppressLint("ExifInterface")
private fun applyExifOrientationIfNeeded(bitmap: Bitmap, exifInput: InputStream): Bitmap {
    val exif = ExifInterface(exifInput)
    val orientation =
        exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL)
    val matrix = Matrix()
    when (orientation) {
        ExifInterface.ORIENTATION_ROTATE_90 -> matrix.postRotate(90f)
        ExifInterface.ORIENTATION_ROTATE_180 -> matrix.postRotate(180f)
        ExifInterface.ORIENTATION_ROTATE_270 -> matrix.postRotate(270f)
        ExifInterface.ORIENTATION_FLIP_HORIZONTAL -> matrix.postScale(-1f, 1f)
        ExifInterface.ORIENTATION_FLIP_VERTICAL -> matrix.postScale(1f, -1f)
        ExifInterface.ORIENTATION_TRANSPOSE -> {
            matrix.postRotate(90f); matrix.postScale(-1f, 1f)
        }

        ExifInterface.ORIENTATION_TRANSVERSE -> {
            matrix.postRotate(270f); matrix.postScale(-1f, 1f)
        }

        else -> return bitmap
    }
    return Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true).also {
        if (it !== bitmap) bitmap.recycle()
    }
}

private fun guessExtension(resolver: ContentResolver, uri: Uri): String? {
    val mime = resolver.getType(uri)?.lowercase() ?: return null
    return when (mime) {
        "image/jpeg" -> "jpg"
        "image/png" -> "png"
        "image/webp" -> "webp"
        "image/heic" -> "heic"
        "image/heif" -> "heif"
        else -> null
    }
}

fun Context.listIconFileNames(): List<String> {
    val dir = File(filesDir, "icons")
    if (!dir.exists() || !dir.isDirectory) return emptyList()

    return try {
        val names = dir.list { parent, name ->
            File(parent, name).isFile
        } ?: emptyArray()

        names.asList().sorted()
    } catch (_: Throwable) {
        emptyList()
    }
}

fun List<String>.pickLatestByPrefix(): Map<String, String> {
    val bestNumberByKey = HashMap<String, Long>(this.size)
    val bestFileByKey = HashMap<String, String>(this.size)

    for (name in this) {
        val us = name.indexOf('_')
        if (us <= 0) continue

        val dot = name.lastIndexOf('.')
        if (dot <= us + 1) continue

        val key = name.substring(0, us)
        val numStr = name.substring(us + 1, dot)

        val num = numStr.toLongOrNull() ?: continue

        val prev = bestNumberByKey[key]
        if (prev == null || num > prev) {
            bestNumberByKey[key] = num
            bestFileByKey[key] = name
        }
    }

    return bestFileByKey
}

fun pruneUnusedIcons(context: Context, usedIconUris: List<Uri>): Int {
    val keepNames = HashSet<String>(usedIconUris.size.coerceAtLeast(16))
    for (u in usedIconUris) {
        resolveIconFileName(context, u)?.let { keepNames.add(it) }
    }

    val iconsDir = File(context.filesDir, "icons")
    if (!iconsDir.exists() || !iconsDir.isDirectory) return 0

    val files = iconsDir.listFiles() ?: return 0
    var deleted = 0
    for (f in files) {
        if (f.isFile && !keepNames.contains(f.name)) {
            if (f.delete()) deleted++
        }
    }
    return deleted
}

private fun resolveIconFileName(context: Context, uri: Uri): String? {
    if ("file".equals(uri.scheme, ignoreCase = true)) {
        val path = uri.path ?: return null
        return File(path).name
    }
    if ("content".equals(uri.scheme, ignoreCase = true)) {
        val projection = arrayOf(OpenableColumns.DISPLAY_NAME)
        try {
            context.contentResolver.query(uri, projection, null, null, null)?.use { c ->
                val idx = c.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                if (idx >= 0 && c.moveToFirst()) {
                    val dn = c.getString(idx)
                    if (!dn.isNullOrEmpty()) return dn
                }
            }
        } catch (_: Throwable) {
        }
    }
    val lps = uri.lastPathSegment ?: return null
    val slash = lps.lastIndexOf('/')
    return if (slash >= 0) lps.substring(slash + 1) else lps
}

fun Context.deleteIconsByPrefix(prefix: String): Int {
    if (prefix.isBlank()) return 0

    val iconsDir = File(filesDir, "icons")
    if (!iconsDir.exists() || !iconsDir.isDirectory) return 0

    val files = iconsDir.listFiles() ?: return 0
    var deleted = 0
    for (f in files) {
        if (f.isFile && f.name.contains(prefix)) {
            if (f.delete()) deleted++
        }
    }
    return deleted
}
