package com.geely.lib.oneosapi.device;

import android.content.Context;
import android.os.IBinder;
import com.geely.lib.oneosapi.common.ServiceBaseManager;
import com.geely.lib.oneosapi.device.IDevice;

/* loaded from: classes.dex */
public class DeviceManager implements ServiceBaseManager {
    private static final String TAG = "DeviceManager";
    private final Context mContext;
    private IDevice mService;

    public DeviceManager(Context context, IBinder binder) {
        this.mContext = context;
        this.mService = IDevice.Stub.asInterface(binder);
    }

    @Override // com.geely.lib.oneosapi.common.ServiceBaseManager
    public void setService(IBinder binder) {
        if (binder != null) {
            this.mService = IDevice.Stub.asInterface(binder);
        }
    }
}