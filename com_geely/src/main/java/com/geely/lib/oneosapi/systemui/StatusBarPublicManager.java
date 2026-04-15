package com.geely.lib.oneosapi.systemui;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.geely.lib.oneosapi.common.ServiceBaseManager;
import com.geely.lib.oneosapi.systemui.IStatusBarService;
import com.geely.lib.oneosapi.systemui.listener.IStatusBarModeChangeListener;

/* loaded from: classes.dex */
public class StatusBarPublicManager implements ServiceBaseManager {
    public static final int DARK = 2;
    public static final int DEFAULT = 0;
    public static final int LIGHT = 1;
    private static final String STATUS_BAR_MODE = "StatusBarMode";
    private static final String TAG = "StatusBarPublicManager";
    private IStatusBarService mService;

    public StatusBarPublicManager(Context mContext, IBinder binder) {
        this.mService = IStatusBarService.Stub.asInterface(binder);
    }

    @Override // com.geely.lib.oneosapi.common.ServiceBaseManager
    public void setService(IBinder binder) {
        this.mService = IStatusBarService.Stub.asInterface(binder);
    }

    private boolean isServiceAlive() {
        IStatusBarService iStatusBarService = this.mService;
        return iStatusBarService != null && iStatusBarService.asBinder().isBinderAlive();
    }

    public void showWifiDialog() {
        showDialogByName("Wifi");
    }

    public void showBluetoothDialog() {
        showDialogByName("Bluetooth");
    }

    public void showAccessPointDialog() {
        showDialogByName("AccessPoint");
    }

    public void closeWifiDialog() {
        closeDialogByName("Wifi");
    }

    public void closeBluetoothDialog() {
        closeDialogByName("Bluetooth");
    }

    public void closeAccessPointDialog() {
        closeDialogByName("AccessPoint");
    }

    public void showPsdBluetoothDialog() {
        showDialogByName("PsdBluetooth");
    }

    public void setLeftTempStateMediaVisibility(int visibility) {
        setLeftTempStateVisibility("music", visibility);
    }

    private void showDialogByName(String dialogName) {
        Log.d(TAG, "dialogName." + dialogName);
        if (isServiceAlive()) {
            try {
                this.mService.showDialogByName(dialogName);
                return;
            } catch (RemoteException e) {
                e.printStackTrace();
                return;
            }
        }
        Log.d(TAG, "showDialogByName: service is not alive");
    }

    public void setPsdStatusVisibility(int visibility) {
        Log.d(TAG, "setPsdStatusVisibility visibility" + visibility);
        if (isServiceAlive()) {
            try {
                this.mService.setPsdStatusVisibility(visibility);
                return;
            } catch (RemoteException e) {
                e.printStackTrace();
                return;
            }
        }
        Log.d(TAG, "setPsdStatusVisibility: service is not alive");
    }

    public void setCsdActivity(IBinder csdActitvity) {
        Log.d(TAG, "setCsdActivity csdActitvity" + csdActitvity);
        if (isServiceAlive()) {
            try {
                this.mService.setCsdActivity(csdActitvity);
                return;
            } catch (RemoteException e) {
                e.printStackTrace();
                return;
            }
        }
        Log.d(TAG, "setCsdActivity: service is not alive");
    }

    public void setPsdActivity(IBinder psdAcitvity) {
        Log.d(TAG, "setPsdActivity psdAcitvity" + psdAcitvity);
        if (isServiceAlive()) {
            try {
                this.mService.setPsdActivity(psdAcitvity);
                return;
            } catch (RemoteException e) {
                e.printStackTrace();
                return;
            }
        }
        Log.d(TAG, "setPsdActivity: service is not alive");
    }

    private void closeDialogByName(String dialogName) {
        Log.d(TAG, "closeDialogByName--dialogName." + dialogName);
        if (isServiceAlive()) {
            try {
                this.mService.closeDialogByName(dialogName);
                return;
            } catch (RemoteException e) {
                e.printStackTrace();
                return;
            }
        }
        Log.d(TAG, "closeDialogByName: service is not alive");
    }

    public void showCsdBluetoothDialogInPsd() {
        showDialogByName("CsdBluetoothInPsd");
    }

    public int getPsdStatusBarVisibility() {
        Log.d(TAG, "getPsdStatusBarVisibility");
        if (isServiceAlive()) {
            try {
                return this.mService.getPsdStatusBarVisibility();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            Log.d(TAG, "getPsdStatusBarVisibility: service is not alive");
        }
        return 0;
    }

    public void setLeftTempStateVisibility(String leftTempStateContents, int visibility) {
        Log.d(TAG, "setLeftTempStateMediaVisibility leftTempStateContents : " + leftTempStateContents + " visibility :" + visibility);
        if (isServiceAlive()) {
            try {
                this.mService.setLeftTempStateVisibility(leftTempStateContents, visibility);
                return;
            } catch (RemoteException e) {
                e.printStackTrace();
                return;
            }
        }
        Log.d(TAG, "setPsdStatusVisibility: service is not alive");
    }

    public Bundle getStatusBarWindowMode() {
        Log.d(TAG, "getStatusBarWindowMode  ");
        if (isServiceAlive()) {
            try {
                return this.mService.getStatusBarWindowMode();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            Log.d(TAG, "getStatusBarWindowMode: service is not alive");
        }
        return null;
    }

    public void registerStatusBarModeChangeListener(IStatusBarModeChangeListener listener, String pkg) {
        Log.d(TAG, "registerStatusBarModeChangeListener pkg: " + pkg);
        Log.d(TAG, "registerStatusBarModeChangeListener mService " + this.mService);
        IStatusBarService iStatusBarService = this.mService;
        if (iStatusBarService == null || !iStatusBarService.asBinder().isBinderAlive()) {
            return;
        }
        try {
            this.mService.registerStatusBarModeChangeListener(listener, pkg);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void unRegisterStatusBarModeChangeListener(IStatusBarModeChangeListener listener, String pkg) {
        Log.d(TAG, "unRegisterStatusBarModeChangeListener pkg: " + pkg);
        Log.d(TAG, "unRegisterStatusBarModeChangeListener mService " + this.mService);
        IStatusBarService iStatusBarService = this.mService;
        if (iStatusBarService == null || !iStatusBarService.asBinder().isBinderAlive()) {
            return;
        }
        try {
            this.mService.unRegisterStatusBarModeChangeListener(listener, pkg);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}