package com.geely.lib.oneosapi.ota;

import android.content.Context;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.geely.lib.oneosapi.common.ServiceBaseManager;
import com.geely.lib.oneosapi.ota.IOtaService;
import com.geely.lib.oneosapi.ota.IOtaVersionChangedListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class OtaManager implements ServiceBaseManager {
    private static final String SDK_VERSION = "==20230222==";
    private static final String TAG = "OtaManager==20230222==";
    private Context mContext;
    private String mPackageName;
    private IOtaService mService;
    private List<IOtaVersionChangedListener.Stub> mRegisterListListener = new ArrayList();
    private List<IOtaVersionChangedListener.Stub> mUnregisterListListener = new ArrayList();

    public OtaManager(Context context, IBinder binder) {
        this.mPackageName = context.getPackageName();
        Log.d(TAG, "OtaManager(" + context + "," + binder + ")" + this.mPackageName);
        this.mContext = context;
        initOtaManger(binder);
    }

    @Override // com.geely.lib.oneosapi.common.ServiceBaseManager
    public void setService(IBinder binder) {
        Log.d(TAG, "setService(" + binder + ")" + this.mPackageName);
        initOtaManger(binder);
    }

    public String getUpgradeVersionName() {
        String upgradeVersionName = "";
        Log.d(TAG, "getUpgradeVersionName");
        IOtaService iOtaService = this.mService;
        if (iOtaService != null) {
            try {
                upgradeVersionName = iOtaService.getUpgradeVersionName();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            Log.d(TAG, "getUpgradeVersionName:result = " + upgradeVersionName + this.mPackageName);
            return upgradeVersionName;
        }
        serviceIsNull();
        upgradeVersionName = null;
        Log.d(TAG, "getUpgradeVersionName:result = " + upgradeVersionName + this.mPackageName);
        return upgradeVersionName;
    }

    public String getCurrentCarVersionName() {
        String currentCarVersionName ="";
        Log.d(TAG, "getCurrentCarVersionName");
        IOtaService iOtaService = this.mService;
        if (iOtaService != null) {
            try {
                currentCarVersionName = iOtaService.getCurrentCarVersionName();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            Log.d(TAG, "getCurrentCarVersionName:result = " + currentCarVersionName + this.mPackageName);
            return currentCarVersionName;
        }
        serviceIsNull();
        currentCarVersionName = null;
        Log.d(TAG, "getCurrentCarVersionName:result = " + currentCarVersionName + this.mPackageName);
        return currentCarVersionName;
    }

    public String getSysBssId() {
        String sysBssId = "";
        Log.d(TAG, "getSysBssId");
        IOtaService iOtaService = this.mService;
        if (iOtaService != null) {
            try {
                sysBssId = iOtaService.getSysBssId();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            Log.d(TAG, "getSysBssId:result = " + sysBssId + this.mPackageName);
            return sysBssId;
        }
        serviceIsNull();
        sysBssId = null;
        Log.d(TAG, "getSysBssId:result = " + sysBssId + this.mPackageName);
        return sysBssId;
    }

    public boolean hasNewVersion() {
        boolean hasNewVersion = false;
        Log.d(TAG, "hasNewVersion");
        IOtaService iOtaService = this.mService;
        if (iOtaService != null) {
            try {
                hasNewVersion = iOtaService.hasNewVersion();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            Log.d(TAG, "hasNewVersion:result = " + hasNewVersion + this.mPackageName);
            return hasNewVersion;
        }
        serviceIsNull();
        hasNewVersion = false;
        Log.d(TAG, "hasNewVersion:result = " + hasNewVersion + this.mPackageName);
        return hasNewVersion;
    }

    public void registerOtaVersionChangedListener(IOtaVersionChangedListener.Stub listener) {
        Log.d(TAG, "registerOtaVersionChangedListener(" + listener + ")" + this.mPackageName);
        if (isAlive()) {
            try {
                this.mService.registerOtaVersionChangedListener(listener, this.mPackageName);
            } catch (RemoteException e) {
                Log.d(TAG, "registerOtaVersionChangedListener(" + listener + ")" + this.mPackageName + " throw excetption!!");
                e.printStackTrace();
            }
        } else {
            serviceIsNull();
            if (!this.mRegisterListListener.contains(listener)) {
                this.mRegisterListListener.add(listener);
            }
        }
        Log.d(TAG, "registerVRDialogStatusChangedListener：result = " + ((String) null));
    }

    public void unregisterOtaVersionChangedListener(IOtaVersionChangedListener.Stub listener) {
        Log.d(TAG, "unregisterOtaVersionChangedListener(" + listener + ")" + this.mPackageName);
        if (isAlive()) {
            try {
                this.mService.unregisterOtaVersionChangedListener(listener, this.mPackageName);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            serviceIsNull();
            if (!this.mUnregisterListListener.contains(listener)) {
                this.mUnregisterListListener.add(listener);
            }
        }
        Log.d(TAG, "unregisterVRDialogStatusChangedListener：result = " + ((String) null));
    }

    public boolean isAlive() {
        Log.d(TAG, "isAlive()" + this.mPackageName);
        if (this.mService == null) {
            serviceIsNull();
        }
        IOtaService iOtaService = this.mService;
        boolean z = iOtaService != null && iOtaService.asBinder().isBinderAlive();
        Log.d(TAG, "isAlive：result = " + z);
        return z;
    }

    private void initOtaManger(IBinder binder) {
        if (binder != null) {
            this.mService = IOtaService.Stub.asInterface(binder);
            if (isAlive()) {
                Log.d(TAG, "mRegisterListListener.size = " + this.mRegisterListListener.size());
                if (this.mRegisterListListener.size() > 0) {
                    Iterator<IOtaVersionChangedListener.Stub> it = this.mRegisterListListener.iterator();
                    while (it.hasNext()) {
                        registerOtaVersionChangedListener(it.next());
                    }
                    this.mRegisterListListener.clear();
                }
                Log.d(TAG, "mUnregisterListListener.size = " + this.mUnregisterListListener.size());
                if (this.mUnregisterListListener.size() > 0) {
                    Iterator<IOtaVersionChangedListener.Stub> it2 = this.mUnregisterListListener.iterator();
                    while (it2.hasNext()) {
                        unregisterOtaVersionChangedListener(it2.next());
                    }
                    this.mUnregisterListListener.clear();
                }
            }
        }
    }

    private void serviceIsNull() {
        Log.d(TAG, "OtaServiceIsNull" + this.mPackageName);
    }
}