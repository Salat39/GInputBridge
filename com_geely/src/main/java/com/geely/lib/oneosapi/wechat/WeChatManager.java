package com.geely.lib.oneosapi.wechat;

import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.geely.lib.oneosapi.common.ServiceBaseManager;
import com.geely.lib.oneosapi.wechat.IWeChatManager;

/* loaded from: classes.dex */
public class WeChatManager implements ServiceBaseManager {
    private static final String TAG = WeChatManager.class.getSimpleName();
    private IWeChatManager mService;

    public WeChatManager(IBinder binder) {
        initService(binder);
    }

    @Override // com.geely.lib.oneosapi.common.ServiceBaseManager
    public void setService(IBinder binder) {
        initService(binder);
    }

    private void initService(IBinder binder) {
        if (binder != null) {
            this.mService = IWeChatManager.Stub.asInterface(binder);
        }
    }

    public int getWeChatVoidState() {
        IWeChatManager iWeChatManager = this.mService;
        if (iWeChatManager != null) {
            try {
                return iWeChatManager.getWeChatVoipState();
            } catch (RemoteException e) {
                Log.e(TAG, Log.getStackTraceString(e));
            }
        }
        Log.d(TAG, "mService == null");
        return -1;
    }
}