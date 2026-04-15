package com.geely.lib.oneosapi.smart;

import android.content.Context;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.geely.lib.oneosapi.common.ServiceBaseManager;
import com.geely.lib.oneosapi.smart.IResultCallback;
import com.geely.lib.oneosapi.smart.ISmartApi;

/* loaded from: classes.dex */
public class SmartHomeManager implements ServiceBaseManager {
    private static final String TAG = "SmartHomeManager";
    private Context mContext;
    private ISmartApi mService;

    public SmartHomeManager(Context mContext, IBinder binder) {
        this.mContext = mContext;
        this.mService = ISmartApi.Stub.asInterface(binder);
    }

    @Override // com.geely.lib.oneosapi.common.ServiceBaseManager
    public void setService(IBinder binder) {
        if (binder != null) {
            this.mService = ISmartApi.Stub.asInterface(binder);
        }
    }

    private boolean isServiceAlive() {
        ISmartApi iSmartApi = this.mService;
        return iSmartApi != null && iSmartApi.asBinder().isBinderAlive();
    }

    public void controlDeviceSwitch(String location, int deviceType, boolean isOpen, BaseSmartHomeAPICallback callback) {
        if (isServiceAlive()) {
            try {
                this.mService.controlDeviceSwitch(location, deviceType, isOpen, callback);
                return;
            } catch (RemoteException e) {
                e.printStackTrace();
                return;
            }
        }
        Log.d(TAG, "controlDeviceSwitch: service is not alive");
    }

    public void controlDeviceMode(String location, int deviceType, int mode, BaseSmartHomeAPICallback callback) {
        if (isServiceAlive()) {
            try {
                this.mService.controlDeviceMode(location, deviceType, mode, callback);
                return;
            } catch (RemoteException e) {
                e.printStackTrace();
                return;
            }
        }
        Log.d(TAG, "controlDeviceMode: service is not alive");
    }

    public void controlDeviceSpeed(String location, int deviceType, int speed, BaseSmartHomeAPICallback callback) {
        if (isServiceAlive()) {
            try {
                this.mService.controlDeviceSpeed(location, deviceType, speed, callback);
                return;
            } catch (RemoteException e) {
                e.printStackTrace();
                return;
            }
        }
        Log.d(TAG, "controlDeviceSpeed: service is not alive");
    }

    public void controlACTemp(String location, String setType, int targetTemp, int adjustTemp, BaseSmartHomeAPICallback callback) {
        if (isServiceAlive()) {
            try {
                this.mService.controlACTemp(location, setType, targetTemp, adjustTemp, callback);
                return;
            } catch (RemoteException e) {
                e.printStackTrace();
                return;
            }
        }
        Log.d(TAG, "controlACTemp: service is not alive");
    }

    public void controlSceneMode(boolean isOpen, String sceneName, BaseSmartHomeAPICallback callback) {
        if (isServiceAlive()) {
            try {
                this.mService.controlSceneMode(isOpen, sceneName, callback);
                return;
            } catch (RemoteException e) {
                e.printStackTrace();
                return;
            }
        }
        Log.d(TAG, "controlSceneMode: service is not alive");
    }

    public static abstract class BaseSmartHomeAPICallback extends IResultCallback.Stub {
        @Override // com.geely.lib.oneosapi.smart.IResultCallback
        public void callback(int code, String message) {
            Log.d(SmartHomeManager.TAG, "BaseSmartHomeAPICallback: code=" + code + "/message=" + message);
        }
    }
}