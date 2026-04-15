package com.geely.lib.oneosapi.appstore;

import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.geely.lib.oneosapi.appstore.IAppStoreInterface;
import com.geely.lib.oneosapi.appstore.bean.AppStoreTaskInfo;
import com.geely.lib.oneosapi.common.ServiceBaseManager;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class AppStoreManager implements ServiceBaseManager {
    private static final String TAG = AppStoreManager.class.getSimpleName();
    private IAppStoreInterface mService;

    public AppStoreManager(IBinder binder) {
        initAppStoreManager(binder);
    }

    @Override // com.geely.lib.oneosapi.common.ServiceBaseManager
    public void setService(IBinder binder) {
        initAppStoreManager(binder);
    }

    private void initAppStoreManager(IBinder binder) {
        if (binder != null) {
            this.mService = IAppStoreInterface.Stub.asInterface(binder);
        }
    }

    private void serviceIsNull() {
        Log.d(TAG, "AppStoreServiceIsNull");
    }

    public void startDownload(String taskId) {
        IAppStoreInterface iAppStoreInterface = this.mService;
        if (iAppStoreInterface != null) {
            try {
                iAppStoreInterface.startDownload(taskId);
                return;
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }
        serviceIsNull();
    }

    public void stopDownload(String taskId) {
        IAppStoreInterface iAppStoreInterface = this.mService;
        if (iAppStoreInterface != null) {
            try {
                iAppStoreInterface.stopDownload(taskId);
                return;
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }
        serviceIsNull();
    }

    public void cancelDownload(String taskId) {
        IAppStoreInterface iAppStoreInterface = this.mService;
        if (iAppStoreInterface != null) {
            try {
                iAppStoreInterface.cancelDownload(taskId);
                return;
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }
        serviceIsNull();
    }

    public List<AppStoreTaskInfo> getUnfinishedApp() {
        IAppStoreInterface iAppStoreInterface = this.mService;
        if (iAppStoreInterface != null) {
            try {
                return iAppStoreInterface.getAllPendingDownloadApp();
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }
        serviceIsNull();
        return new ArrayList();
    }

    public void registerTaskCallBack(IAppStoreTaskCallback iAppStoreCallback) {
        IAppStoreInterface iAppStoreInterface = this.mService;
        if (iAppStoreInterface != null) {
            try {
                iAppStoreInterface.registerTaskCallBack(iAppStoreCallback);
                return;
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }
        serviceIsNull();
    }

    public void unregisterTaskCallBack(IAppStoreTaskCallback iAppStoreCallback) {
        IAppStoreInterface iAppStoreInterface = this.mService;
        if (iAppStoreInterface != null) {
            try {
                iAppStoreInterface.unregisterTaskCallBack(iAppStoreCallback);
                return;
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }
        serviceIsNull();
    }
}