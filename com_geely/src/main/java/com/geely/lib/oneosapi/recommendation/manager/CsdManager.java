package com.geely.lib.oneosapi.recommendation.manager;

import android.content.Context;
import android.os.RemoteException;
import android.util.Log;
import com.geely.lib.oneosapi.recommendation.ICsdManager;
import com.geely.lib.oneosapi.recommendation.callback.ICsdRecCallback;

/* loaded from: classes.dex */
public class CsdManager {
    private static final String TAG = "CsdManager";
    protected Context mContext;
    protected ICsdManager mService;

    public CsdManager(Context context, ICsdManager service) {
        this.mContext = context;
        this.mService = service;
    }

    public void getCsdRecInfo() {
        Log.d(TAG, "getCsdRecInfo ICsdManager: " + this.mService);
        ICsdManager iCsdManager = this.mService;
        if (iCsdManager != null) {
            try {
                iCsdManager.getCsdRecInfos();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void subscribeCsdRecCallback(CsdRecCallback callback) {
        ICsdManager iCsdManager = this.mService;
        if (iCsdManager != null) {
            try {
                iCsdManager.subscribeCsdRecCallback(callback);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void unSubscribeCsdRecCallback(CsdRecCallback callback) {
        ICsdManager iCsdManager = this.mService;
        if (iCsdManager != null) {
            try {
                iCsdManager.unSubscribeCsdRecCallback(callback);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void getCsdPullInfo(boolean isOpen) {
        Log.d(TAG, "getCsdPullInfo ICsdManager: " + this.mService);
        ICsdManager iCsdManager = this.mService;
        if (iCsdManager != null) {
            try {
                iCsdManager.getCsdPullInfos(isOpen);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public static abstract class CsdRecCallback extends ICsdRecCallback.Stub {
        @Override // com.geely.lib.oneosapi.recommendation.callback.ICsdRecCallback
        public void onResult(String json) {
            int length = json.length();
            int i = 0;
            int i2 = 1000;
            int i3 = 0;
            while (i < 100) {
                if (length > i2) {
                    Log.d(CsdManager.TAG, "CsdRecCallback: CsdRecInfo " + json.substring(i3, i2));
                    i++;
                    i3 = i2;
                    i2 += 3000;
                } else {
                    Log.d(CsdManager.TAG, json.substring(i3, length));
                    return;
                }
            }
        }
    }
}