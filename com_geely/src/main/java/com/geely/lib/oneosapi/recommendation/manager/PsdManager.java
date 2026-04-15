package com.geely.lib.oneosapi.recommendation.manager;

import android.content.Context;
import android.os.RemoteException;
import android.util.Log;
import com.geely.lib.oneosapi.recommendation.IPsdManager;
import com.geely.lib.oneosapi.recommendation.callback.IPsdRecCallback;
import com.geely.lib.oneosapi.recommendation.callback.IWidgetCallback;

/* loaded from: classes.dex */
public class PsdManager {
    private static final String TAG = "PsdManager";
    protected Context mContext;
    protected IPsdManager mService;

    public PsdManager(Context context, IPsdManager service) {
        this.mContext = context;
        this.mService = service;
    }

    public void getPsdRecInfo() {
        Log.d(TAG, "getPsdRecInfo IPsdManager: " + this.mService);
        IPsdManager iPsdManager = this.mService;
        if (iPsdManager != null) {
            try {
                iPsdManager.getPsdRecInfos();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void subscribePsdRecCallback(PsdRecCallback callback) {
        IPsdManager iPsdManager = this.mService;
        if (iPsdManager != null) {
            try {
                iPsdManager.subscribePsdRecCallback(callback);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void unSubscribePsdRecCallback(PsdRecCallback callback) {
        IPsdManager iPsdManager = this.mService;
        if (iPsdManager != null) {
            try {
                iPsdManager.unSubscribePsdRecCallback(callback);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void getWidgetInfo() {
        Log.d(TAG, "getWidgetInfo IPsdManager: " + this.mService);
        IPsdManager iPsdManager = this.mService;
        if (iPsdManager != null) {
            try {
                iPsdManager.getWidgetInfos();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void subscribeWidgetCallback(IWidgetCallback callback) {
        IPsdManager iPsdManager = this.mService;
        if (iPsdManager != null) {
            try {
                iPsdManager.subscribeWidgetCallback(callback);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void unSubscribeWidgetCallback(IWidgetCallback callback) {
        IPsdManager iPsdManager = this.mService;
        if (iPsdManager != null) {
            try {
                iPsdManager.unSubscribeWidgetCallback(callback);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void getPsdPullInfo(boolean isOpen) {
        Log.d(TAG, "getPsdPullInfo IPsdManager: " + this.mService);
        IPsdManager iPsdManager = this.mService;
        if (iPsdManager != null) {
            try {
                iPsdManager.getPsdPullInfos(isOpen);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void getWidgetPullInfo(boolean isOpen) {
        Log.d(TAG, "getWidgetPullInfo IPsdManager: " + this.mService);
        IPsdManager iPsdManager = this.mService;
        if (iPsdManager != null) {
            try {
                iPsdManager.getWidgetPullInfos(isOpen);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public static abstract class PsdRecCallback extends IPsdRecCallback.Stub {
        @Override // com.geely.lib.oneosapi.recommendation.callback.IPsdRecCallback
        public void onResult(String json) {
            Log.d(PsdManager.TAG, "PsdRecCallback: psdRecInfo " + json);
        }
    }

    public static abstract class WidgetCallback extends IWidgetCallback.Stub {
        @Override // com.geely.lib.oneosapi.recommendation.callback.IWidgetCallback
        public void onResult(String json) {
            int length = json.length();
            int i = 0;
            int i2 = 1000;
            int i3 = 0;
            while (i < 100) {
                if (length > i2) {
                    Log.d(PsdManager.TAG, "WidgetCallback: WidgetInfo " + json.substring(i3, i2));
                    i++;
                    i3 = i2;
                    i2 += 3000;
                } else {
                    Log.d(PsdManager.TAG, json.substring(i3, length));
                    return;
                }
            }
        }
    }
}