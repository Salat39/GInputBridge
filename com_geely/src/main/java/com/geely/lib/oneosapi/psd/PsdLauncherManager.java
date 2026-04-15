package com.geely.lib.oneosapi.psd;

import android.app.WallpaperManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes.dex */
public class PsdLauncherManager {
    private static volatile PsdLauncherManager singleton;

    private PsdLauncherManager() {
    }

    public static PsdLauncherManager getInstance() {
        if (singleton == null) {
            synchronized (PsdLauncherManager.class) {
                if (singleton == null) {
                    singleton = new PsdLauncherManager();
                }
            }
        }
        return singleton;
    }

    public void setWallpaper(Context context, Bitmap bitmap) throws IOException {
        ((WallpaperManager) context.getSystemService(WallpaperManager.class)).setBitmap(bitmap, null, true, 2);
    }

    public void setWallpaper(Context context, InputStream bitmapData, Rect visibleCropHint, boolean allowBackup) throws IOException {
        ((WallpaperManager) context.getSystemService(WallpaperManager.class)).setStream(bitmapData, visibleCropHint, allowBackup, 2);
    }

    public void setWallpaper(Context context, int resourceId) throws IOException {
        ((WallpaperManager) context.getSystemService(WallpaperManager.class)).setResource(resourceId, 2);
    }
}