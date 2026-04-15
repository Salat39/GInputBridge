package com.geely.lib.oneosapi.launcher;

import android.content.Context;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.geely.lib.oneosapi.common.ServiceBaseManager;
import com.geely.lib.oneosapi.launcher.IShotCutsService;
import com.geely.lib.oneosapi.launcher.bean.ShortcutCompat;
import java.util.Collections;
import java.util.List;

/* loaded from: classes.dex */
public class ShortcutManager implements ServiceBaseManager {
    private static final String TAG = "ShortcutManager";
    private IShotCutsService mService;

    public ShortcutManager(Context mContext, IBinder binder) {
        this.mService = IShotCutsService.Stub.asInterface(binder);
    }

    @Override // com.geely.lib.oneosapi.common.ServiceBaseManager
    public void setService(IBinder binder) {
        this.mService = IShotCutsService.Stub.asInterface(binder);
    }

    private boolean isServiceAlive() {
        IShotCutsService iShotCutsService = this.mService;
        return iShotCutsService != null && iShotCutsService.asBinder().isBinderAlive();
    }

    public List<ShortcutCompat> getShortCuts(String packageName) {
        if (isServiceAlive()) {
            try {
                return this.mService.getShortCuts(packageName);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            Log.d(TAG, "getShortCuts: service is not alive");
        }
        return Collections.emptyList();
    }
}