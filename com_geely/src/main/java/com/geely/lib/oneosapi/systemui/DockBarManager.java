package com.geely.lib.oneosapi.systemui;

import android.content.Context;
import android.os.IBinder;
import android.util.Log;
import com.geely.lib.oneosapi.common.ServiceBaseManager;
import com.geely.lib.oneosapi.systemui.IDockBarService;

/* loaded from: classes.dex */
public class DockBarManager implements ServiceBaseManager {
    public static final int DOCK_HIDE = 3;
    public static final int DOCK_HIDE_NO_ANIM = 5;
    public static final int DOCK_SHOW_ALL = 1;
    public static final int DOCK_SHOW_LINE = 2;
    public static final int DOCK_SHOW_NO_ANIM = 4;
    private static final String TAG = "DockBarManager";
    private IDockBarService mService;

    public DockBarManager(Context mContext, IBinder binder) {
        this.mService = IDockBarService.Stub.asInterface(binder);
    }

    @Override // com.geely.lib.oneosapi.common.ServiceBaseManager
    public void setService(IBinder binder) {
        this.mService = IDockBarService.Stub.asInterface(binder);
    }

    private boolean isServiceAlive() {
        IDockBarService iDockBarService = this.mService;
        return iDockBarService != null && iDockBarService.asBinder().isBinderAlive();
    }

    public void showDock(int status) {
        Log.d(TAG, "showDock." + status);
        if (isServiceAlive()) {
            try {
                this.mService.showDock(status);
                return;
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        Log.d(TAG, "showDock: service is not alive");
    }

    public void onScrollChanged(float progress) {
        Log.d(TAG, "onScrollChanged." + progress);
        if (isServiceAlive()) {
            try {
                this.mService.onScrollChanged(progress);
                return;
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        Log.d(TAG, "showDock: service is not alive");
    }

    public void showDockNoAnim(int status) {
        Log.d(TAG, "showDockNoAnim." + status);
        if (isServiceAlive()) {
            try {
                this.mService.showDockNoAnim(status);
                return;
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        Log.d(TAG, "showDockNoAnim: service is not alive");
    }

    public void canSlide(boolean flag) {
        Log.d(TAG, "canSlide." + flag);
        if (isServiceAlive()) {
            try {
                this.mService.canSlide(flag);
                return;
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        Log.d(TAG, "canSlide: service is not alive");
    }

    public void playAnimSub(boolean isPlay) {
        Log.d(TAG, "playAnimSub." + isPlay);
        if (isServiceAlive()) {
            try {
                this.mService.playAnimSub(isPlay);
                return;
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        Log.d(TAG, "playAnimSub: service is not alive");
    }

    public void playAnimSubPsd(boolean isPlay) {
        Log.d(TAG, "playAnimSub." + isPlay);
        if (isServiceAlive()) {
            try {
                this.mService.playAnimSubPsd(isPlay);
                return;
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        Log.d(TAG, "playAnimSub: service is not alive");
    }

    public int getDockStatus() {
        Log.d(TAG, "getDockStatus.");
        if (isServiceAlive()) {
            try {
                return this.mService.getDockStatus();
            } catch (Exception e) {
                e.printStackTrace();
                return -1;
            }
        }
        Log.d(TAG, "getDockStatus: service is not alive");
        return -1;
    }
}