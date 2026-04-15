package com.geely.lib.oneosapi.theme;

import android.content.Context;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.geely.lib.oneosapi.common.ServiceBaseManager;
import com.geely.lib.oneosapi.theme.IApplyByLauncherCallback;
import com.geely.lib.oneosapi.theme.IGetOwnerStaticWallpaperCallback;
import com.geely.lib.oneosapi.theme.IGetOwnerVideoWallpaperCallback;
import com.geely.lib.oneosapi.theme.IThemeService;

/* loaded from: classes.dex */
public class ThemeManager implements ServiceBaseManager {
    private static final String TAG = ThemeManager.class.getSimpleName();
    private Context mContetx;
    private IThemeService mService;

    @Override // com.geely.lib.oneosapi.common.ServiceBaseManager
    public void setService(IBinder binder) {
        this.mService = IThemeService.Stub.asInterface(binder);
    }

    public ThemeManager(Context context, IBinder binder) {
        this.mContetx = context;
        this.mService = IThemeService.Stub.asInterface(binder);
    }

    public void applyStaticWallpaper() {
        if (isServiceAlive()) {
            try {
                this.mService.applyStaticWallpaper();
                return;
            } catch (RemoteException e) {
                e.printStackTrace();
                return;
            }
        }
        Log.e(TAG, "applyStaticWallpaper: service is not alive");
    }

    public void applyLiveWallpaper() {
        if (isServiceAlive()) {
            try {
                this.mService.applyLiveWallpaper();
                return;
            } catch (RemoteException e) {
                e.printStackTrace();
                return;
            }
        }
        Log.e(TAG, "applyLiveWallpaper: service is not alive");
    }

    public void downloadDress(String cardGoodsVoDataJson, String taskEntityJson, boolean isStatic, IDressDlCallback iDressDlCb) {
        if (isServiceAlive()) {
            try {
                this.mService.downloadDress(cardGoodsVoDataJson, taskEntityJson, iDressDlCb);
                return;
            } catch (RemoteException e) {
                Log.e(TAG, "downloadDress: RemoteException:" + e.getMessage());
                e.printStackTrace();
                return;
            }
        }
        Log.e(TAG, "downloadDress: service is not alive");
    }

    public void applyDress(String cardGoodsVoDataJson, String taskEntityJson, IDressApplyCallback iDressApplyCb) {
        if (isServiceAlive()) {
            try {
                this.mService.applyDress(cardGoodsVoDataJson, taskEntityJson, iDressApplyCb);
                return;
            } catch (RemoteException e) {
                Log.e(TAG, "applyDress: RemoteException:" + e.getMessage());
                e.printStackTrace();
                return;
            }
        }
        Log.e(TAG, "applyDress: service is not alive");
    }

    public void delDressFile(String filePath, String goodsId, IDressDelCallback iDressDelCallback) {
        if (isServiceAlive()) {
            try {
                this.mService.delDressFile(filePath, goodsId, iDressDelCallback);
                return;
            } catch (RemoteException e) {
                Log.e(TAG, "delDressFile: RemoteException:" + e.getMessage());
                e.printStackTrace();
                return;
            }
        }
        Log.e(TAG, "delDressFile: service is not alive");
    }

    public int getCurrentWallpaperType() {
        int currentWallpaperType = 0;
        if (isServiceAlive()) {
            try {
                currentWallpaperType = this.mService.getCurrentWallpaperType();
            } catch (RemoteException e) {
                Log.e(TAG, "getCurrentWallpaperType: RemoteException:" + e.getMessage());
                e.printStackTrace();
            }
            Log.d(TAG, "getCurrentWallpaperType: retType" + currentWallpaperType);
            return currentWallpaperType;
        }
        Log.e(TAG, "getCurrentWallpaperType: service is not alive");
        currentWallpaperType = -1;
        Log.d(TAG, "getCurrentWallpaperType: retType" + currentWallpaperType);
        return currentWallpaperType;
    }

    public void getOwnerStaticWallpaperData(IGetOwnerStaticWallpaperCallback getOwnerStaticWallpaperCallback) {
        if (isServiceAlive()) {
            try {
                Log.d(TAG, "getOwnerStaticWallpaperCallback is :" + getOwnerStaticWallpaperCallback);
                this.mService.getOwnerStaticWallpaperData(getOwnerStaticWallpaperCallback);
                return;
            } catch (RemoteException e) {
                Log.e(TAG, "getOwnerStaticWallpaperData: RemoteException:" + e.getMessage());
                e.printStackTrace();
                return;
            }
        }
        Log.e(TAG, "getOwnerStaticWallpaperData: service is not alive");
    }

    public void getOwnerVideoWallpaperData(IGetOwnerVideoWallpaperCallback getOwnerVideoWallpaperCallback) {
        if (isServiceAlive()) {
            try {
                this.mService.getOwnerVideoWallpaperData(getOwnerVideoWallpaperCallback);
                return;
            } catch (RemoteException e) {
                Log.e(TAG, "getOwnerVideoWallpaperData: RemoteException:" + e.getMessage());
                e.printStackTrace();
                return;
            }
        }
        Log.e(TAG, "getOwnerVideoWallpaperData: service is not alive");
    }

    public void applyWallpaperByLauncher(String cardGoodsVoDataJson, boolean isApplyCSD, boolean isApplyPSD, IApplyByLauncherCallback applyByLauncherCallback) {
        if (isServiceAlive()) {
            try {
                this.mService.applyWallpaperByLauncher(cardGoodsVoDataJson, isApplyCSD, isApplyPSD, applyByLauncherCallback);
                return;
            } catch (RemoteException e) {
                Log.e(TAG, "applyWallpaperByLauncher: RemoteException:" + e.getMessage());
                e.printStackTrace();
                return;
            }
        }
        Log.e(TAG, "applyWallpaperByLauncher: service is not alive");
    }

    public void dressStaticWallpaper(int screenType) {
        if (isServiceAlive()) {
            try {
                this.mService.dressStaticWallpaper(screenType);
                return;
            } catch (RemoteException e) {
                Log.e(TAG, "dressStaticWallpaper: RemoteException:" + e.getMessage());
                e.printStackTrace();
                return;
            }
        }
        Log.e(TAG, "dressStaticWallpaper: service is not alive");
    }

    private boolean isServiceAlive() {
        IThemeService iThemeService = this.mService;
        return iThemeService != null && iThemeService.asBinder().isBinderAlive();
    }

    public static abstract class BaseGetOwnerVideoWallpaperCallback extends IGetOwnerVideoWallpaperCallback.Stub {
        @Override // com.geely.lib.oneosapi.theme.IGetOwnerVideoWallpaperCallback
        public void getOwnerVideoWallpaperCallback(int code, String data) throws RemoteException {
            Log.d(ThemeManager.TAG, "BaseGetOwnerVideoWallpaperCallback() called with: code = [" + code + "], message = [" + data + "]");
        }
    }

    public static abstract class BaseGetOwnerStaticWallpaperCallback extends IGetOwnerStaticWallpaperCallback.Stub {
        @Override // com.geely.lib.oneosapi.theme.IGetOwnerStaticWallpaperCallback
        public void getOwnerStaticWallpaperCallback(int code, String data) throws RemoteException {
            Log.d(ThemeManager.TAG, "BaseGetOwnerStaticWallpaperCallback() called with: code = [" + code + "], message = [" + data + "]");
        }
    }

    public static abstract class BaseApplyByLauncherCallback extends IApplyByLauncherCallback.Stub {
        @Override // com.geely.lib.oneosapi.theme.IApplyByLauncherCallback
        public void applyByLauncherCallback(int code, float downLoadProgress) throws RemoteException {
            Log.d(ThemeManager.TAG, "BaseApplyByLauncherCallback() called with: code = [" + code + "], downLoadProgress = [" + downLoadProgress + "]");
        }
    }
}