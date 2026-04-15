package com.salat.gbinder.coil

import android.content.Context
import android.content.pm.PackageManager
import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.LruCache
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.createBitmap
import androidx.core.graphics.drawable.toDrawable
import coil.decode.DataSource
import coil.fetch.DrawableResult
import coil.fetch.FetchResult
import coil.fetch.Fetcher
import coil.request.Options
import com.salat.gbinder.entity.DisplayIconRef

class IconRefFetcher(
    private val context: Context,
    private val data: DisplayIconRef
) : Fetcher {

    override suspend fun fetch(): FetchResult {
        val pm = context.packageManager
        val res = Cache.resourcesForPackage(pm, data.packageName)
            ?: return DrawableResult(
                drawable = 0x00000000.toDrawable(),
                isSampled = false,
                dataSource = DataSource.MEMORY
            )

        val density = if (data.densityDpi > 0) data.densityDpi else res.displayMetrics.densityDpi

        val key = Cache.keyOf(data.packageName, data.resId, density, data.versionCode)

        Cache.constantStateCache.get(key)?.let { cs ->
            val fastDrawable = cs.newDrawable(res)
            return DrawableResult(
                drawable = fastDrawable,
                isSampled = false,
                dataSource = DataSource.MEMORY
            )
        }

        val drawable = runCatching {
            val loaded = ResourcesCompat.getDrawableForDensity(res, data.resId, density, null)
                ?: pm.getApplicationIcon(data.packageName)

            loaded.constantState?.let { cs ->
                Cache.constantStateCache.put(key, cs)
            }
            loaded
        }.getOrElse {
            0x00000000.toDrawable()
        }

        val targetDpi = if (data.densityDpi > 0) data.densityDpi else density
        val targetPx by lazy {
            val launcherDp = when (targetDpi) {
                in 0..160 -> 48
                in 161..213 -> 64
                in 214..240 -> 72
                in 241..320 -> 96
                in 321..480 -> 144
                else -> 192
            }
            ((launcherDp * targetDpi + 80) / 160).coerceAtLeast(1)
        }
        val finalDrawable: Drawable = when (val d = drawable) {
            is BitmapDrawable -> {
                val bw = d.bitmap.width
                val bh = d.bitmap.height
                if (bw == targetPx && bh == targetPx) {
                    d
                } else {
                    val bmp = createBitmap(targetPx, targetPx)
                    val canvas = Canvas(bmp)
                    val old = d.bounds
                    d.setBounds(0, 0, targetPx, targetPx)
                    d.draw(canvas)
                    d.bounds = old
                    bmp.toDrawable(res)
                }
            }

            else -> {
                val bmp = createBitmap(targetPx, targetPx)
                val canvas = Canvas(bmp)
                val old = d.bounds
                d.setBounds(0, 0, targetPx, targetPx)
                d.draw(canvas)
                d.bounds = old

                bmp.toDrawable(res)
            }
        }

        finalDrawable.constantState?.let { cs -> Cache.constantStateCache.put(key, cs) }

        return DrawableResult(
            drawable = finalDrawable,
            isSampled = false,
            dataSource = DataSource.DISK
        )
    }

    private object Cache {
        private val resCache = object : LruCache<String, Resources>(128) {}

        val constantStateCache =
            object : LruCache<String, Drawable.ConstantState>(512) {}

        fun keyOf(pkg: String, resId: Int, dpi: Int, vc: Long): String =
            "pkg:$pkg|res:$resId|dpi:$dpi|vc:$vc"

        fun resourcesForPackage(pm: PackageManager, packageName: String): Resources? {
            resCache.get(packageName)?.let { return it }
            return try {
                pm.getResourcesForApplication(packageName).also { resCache.put(packageName, it) }
            } catch (_: Exception) {
                null
            }
        }
    }

    class Factory(private val context: Context) : Fetcher.Factory<DisplayIconRef> {
        override fun create(
            data: DisplayIconRef,
            options: Options,
            imageLoader: coil.ImageLoader
        ): Fetcher = IconRefFetcher(context, data)
    }
}
