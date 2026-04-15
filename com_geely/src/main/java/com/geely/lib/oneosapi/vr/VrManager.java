package com.geely.lib.oneosapi.vr;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.geely.lib.oneosapi.common.ServiceBaseManager;
import com.geely.lib.oneosapi.vr.ISpeechRecognitionCallback;
import com.geely.lib.oneosapi.vr.ITtsCallback;
import com.geely.lib.oneosapi.vr.IVRDialogStatusChangedListener;
import com.geely.lib.oneosapi.vr.IVREngineStatusChangedListener;
import com.geely.lib.oneosapi.vr.IVrService;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class VrManager implements ServiceBaseManager {
    private static final String SDK_VERSION = "==20221020==";
    private static final String TAG = "VrManager==20221020==";
    private Context mContext;
    private String mPackageName;
    private IVrService mService;
    private List<IVRDialogStatusChangedListener.Stub> mRegisterListListener = new ArrayList();
    private List<IVRDialogStatusChangedListener.Stub> mUnregisterListListener = new ArrayList();
    private List<IVREngineStatusChangedListener.Stub> mRegisterVREngineStatusListListener = new ArrayList();
    private List<IVREngineStatusChangedListener.Stub> mUnregisterVREngineStatusListListener = new ArrayList();
    private List<String> mTogglePermissionList = new ArrayList();

    public static class DialogType {
        public static int TYPE_INPUT_TEXT = 1;
    }

    public VrManager(Context context, IBinder binder) {
        this.mPackageName = context.getPackageName();
        Log.d(TAG, "VrManager(" + context + "," + binder + ")" + this.mPackageName);
        this.mContext = context;
        this.mTogglePermissionList.add("com.geely.hicar");
        this.mTogglePermissionList.add("com.geely.carlink");
        initVrManger(binder);
    }

    @Override // com.geely.lib.oneosapi.common.ServiceBaseManager
    public void setService(IBinder binder) {
        Log.d(TAG, "setService(" + binder + ")" + this.mPackageName);
        initVrManger(binder);
    }

    public void speak(String ttsContent, boolean isRender, int priority) {
        Log.d(TAG, "speak(" + ttsContent + "," + isRender + "," + priority + ")" + this.mPackageName);
        if (isAlive()) {
            try {
                this.mService.speak(ttsContent, isRender, priority, this.mPackageName);
                return;
            } catch (RemoteException e) {
                e.printStackTrace();
                return;
            }
        }
        serviceIsNull();
    }

    public String startDialogue(String query) {
        String startDialogue = "";
        Log.d(TAG, "startDialogue(" + query + ")" + this.mPackageName);
        if (isAlive()) {
            try {
                startDialogue = this.mService.startDialogue(query, this.mPackageName);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            Log.d(TAG, "startDialogue:result = " + startDialogue + this.mPackageName);
            return startDialogue;
        }
        serviceIsNull();
        startDialogue = null;
        Log.d(TAG, "startDialogue:result = " + startDialogue + this.mPackageName);
        return startDialogue;
    }

    public void closeDialogue(String token) {
        Log.d(TAG, "closeDialogue(" + token + ")" + this.mPackageName);
        if (isAlive()) {
            try {
                this.mService.closeDialogue(token, this.mPackageName);
                return;
            } catch (RemoteException e) {
                e.printStackTrace();
                return;
            }
        }
        serviceIsNull();
    }

    public void speakIncomingCall(String text) {
        Log.d(TAG, "speakIncomingCall(" + text + ")" + this.mPackageName);
        if (isAlive()) {
            try {
                this.mService.speakIncomingCall(text, this.mPackageName);
                return;
            } catch (RemoteException e) {
                e.printStackTrace();
                return;
            }
        }
        serviceIsNull();
    }

    public String startDialogueByType(int type) {
        String startDialogueByType ="";
        Log.d(TAG, "startDialogueByType(" + type + ")" + this.mPackageName);
        if (isAlive()) {
            try {
                startDialogueByType = this.mService.startDialogueByType(type, this.mPackageName);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            Log.d(TAG, "startDialogueByType:result = " + startDialogueByType + this.mPackageName);
            return startDialogueByType;
        }
        serviceIsNull();
        startDialogueByType = null;
        Log.d(TAG, "startDialogueByType:result = " + startDialogueByType + this.mPackageName);
        return startDialogueByType;
    }

    public String startDialogue(String tts, ITtsCallback.Stub ttsCallback) {
        String startDialogWithCallback ="";
        Log.d(TAG, "startDialogue(" + tts + "," + ttsCallback + ")" + this.mPackageName);
        if (isAlive()) {
            try {
                startDialogWithCallback = this.mService.startDialogWithCallback(this.mPackageName, tts, ttsCallback);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            Log.d(TAG, "startDialogue:result = " + startDialogWithCallback);
            return startDialogWithCallback;
        }
        serviceIsNull();
        startDialogWithCallback = null;
        Log.d(TAG, "startDialogue:result = " + startDialogWithCallback);
        return startDialogWithCallback;
    }

    public void disableVR() {
        Log.d(TAG, "disableVR()" + this.mPackageName);
        if (isAlive()) {
            try {
                this.mService.disableVR(this.mPackageName);
                return;
            } catch (RemoteException e) {
                e.printStackTrace();
                return;
            }
        }
        serviceIsNull();
    }

    public void enableVR() {
        Log.d(TAG, "enableVR()" + this.mPackageName);
        if (isAlive()) {
            try {
                this.mService.enableVR(this.mPackageName);
                return;
            } catch (RemoteException e) {
                e.printStackTrace();
                return;
            }
        }
        serviceIsNull();
    }

    public void trackData(String event, JSONObject jsonObject) throws IllegalStateException {
        if (isAlive()) {
            try {
                if (jsonObject == null) {
                    Log.e(TAG, "trackData>>jsonObject is null");
                    throw new IllegalStateException("jsonObject is null");
                }
                this.mService.trackData(getAppName(), getAppVersionName(), getProcessName(), event, jsonObject.toString());
                return;
            } catch (RemoteException e) {
                e.printStackTrace();
                return;
            }
        }
        serviceIsNull();
    }

    public void onPageChange(String packageName, String pageName) {
        if (isAlive()) {
            try {
                this.mService.onPageChange(packageName, pageName);
                return;
            } catch (RemoteException e) {
                e.printStackTrace();
                return;
            }
        }
        Log.d(TAG, "VrServiceIsNull" + packageName);
    }

    public boolean startSpeechRecognition(long recordTimeout, long vadTimeout, ISpeechRecognitionCallback.Stub callback) {
        boolean startSpeechRecognition = false;
        Log.d(TAG, "startSpeechRecognition(" + recordTimeout + "," + vadTimeout + callback + ")" + this.mPackageName);
        if (isAlive()) {
            try {
                startSpeechRecognition = this.mService.startSpeechRecognition(this.mPackageName, recordTimeout, vadTimeout, callback);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            Log.d(TAG, "startSpeechRecognition:result=" + startSpeechRecognition + this.mPackageName);
            return startSpeechRecognition;
        }
        serviceIsNull();
        startSpeechRecognition = false;
        Log.d(TAG, "startSpeechRecognition:result=" + startSpeechRecognition + this.mPackageName);
        return startSpeechRecognition;
    }

    @Deprecated
    public void stopSpeechRecognition() {
        Log.d(TAG, "stopSpeechRecognition()" + this.mPackageName);
        if (isAlive()) {
            try {
                this.mService.stopSpeechRecognition(this.mPackageName);
                return;
            } catch (RemoteException e) {
                e.printStackTrace();
                return;
            }
        }
        serviceIsNull();
    }

    public void stopSpeechRecognitionByTime(long time) {
        Log.d(TAG, "stopSpeechRecognitionByTime(" + time + ")" + this.mPackageName);
        if (isAlive()) {
            try {
                this.mService.stopSpeechRecognitionByTime(this.mPackageName, time);
                return;
            } catch (RemoteException e) {
                e.printStackTrace();
                return;
            }
        }
        serviceIsNull();
    }

    public boolean isAlive() {
        Log.d(TAG, "isAlive()" + this.mPackageName);
        if (this.mService == null) {
            serviceIsNull();
        }
        IVrService iVrService = this.mService;
        boolean z = iVrService != null && iVrService.asBinder().isBinderAlive();
        Log.d(TAG, "isAlive：result = " + z);
        return z;
    }

    public String toggleDialogue() {
        String str ="";
        Log.d(TAG, "toggleDialogue()" + this.mPackageName);
        if (this.mTogglePermissionList.contains(this.mPackageName)) {
            if (isAlive()) {
                try {
                    str = this.mService.toggleDialogue(this.mPackageName);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                Log.d(TAG, "toggleDialogue：result = " + str);
                return str;
            }
            serviceIsNull();
        } else {
            Log.d(TAG, "无权调用 toggleDialogue()" + this.mPackageName);
        }
        str = null;
        Log.d(TAG, "toggleDialogue：result = " + str);
        return str;
    }

    public void registerVRDialogStatusChangedListener(IVRDialogStatusChangedListener.Stub listener) {
        Log.d(TAG, "registerVRDialogStatusChangedListener(" + listener + ")" + this.mPackageName);
        if (isAlive()) {
            try {
                this.mService.registerVRDialogStatusChangedListener(listener, this.mPackageName);
            } catch (RemoteException e) {
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

    public void unregisterVRDialogStatusChangedListener(IVRDialogStatusChangedListener.Stub listener) {
        Log.d(TAG, "unregisterVRDialogStatusChangedListener(" + listener + ")" + this.mPackageName);
        if (isAlive()) {
            try {
                this.mService.unregisterVRDialogStatusChangedListener(listener, this.mPackageName);
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

    public int getVREngineStatus() {
        int vREngineStatus =0;
        Log.d(TAG, "getVREngineStatus()" + this.mPackageName);
        if (isAlive()) {
            try {
                vREngineStatus = this.mService.getVREngineStatus(this.mPackageName);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            Log.d(TAG, "getVREngineStatus：result = " + vREngineStatus);
            return vREngineStatus;
        }
        serviceIsNull();
        vREngineStatus = 1;
        Log.d(TAG, "getVREngineStatus：result = " + vREngineStatus);
        return vREngineStatus;
    }

    public void registerVREngineStatusChangedListener(IVREngineStatusChangedListener.Stub listener) {
        Log.d(TAG, "registerVREngineStatusChangedListener(" + listener + ")" + this.mPackageName);
        if (isAlive()) {
            try {
                this.mService.registerVREngineStatusChangedListener(listener, this.mPackageName);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            serviceIsNull();
            if (!this.mRegisterVREngineStatusListListener.contains(listener)) {
                this.mRegisterVREngineStatusListListener.add(listener);
            }
        }
        Log.d(TAG, "registerVREngineStatusChangedListener：result = " + ((String) null));
    }

    public void unregisterVREngineStatusChangedListener(IVREngineStatusChangedListener.Stub listener) {
        Log.d(TAG, "unregisterVREngineStatusChangedListener(" + listener + ")" + this.mPackageName);
        if (isAlive()) {
            try {
                this.mService.unregisterVREngineStatusChangedListener(listener, this.mPackageName);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            serviceIsNull();
            if (!this.mUnregisterVREngineStatusListListener.contains(listener)) {
                this.mUnregisterVREngineStatusListListener.add(listener);
            }
        }
        Log.d(TAG, "unregisterVREngineStatusChangedListener：result = " + ((String) null));
    }

    private void initVrManger(IBinder binder) {
        if (binder != null) {
            this.mService = IVrService.Stub.asInterface(binder);
            if (isAlive()) {
                Log.d(TAG, "mRegisterListListener.size = " + this.mRegisterListListener.size());
                if (this.mRegisterListListener.size() > 0) {
                    Iterator<IVRDialogStatusChangedListener.Stub> it = this.mRegisterListListener.iterator();
                    while (it.hasNext()) {
                        registerVRDialogStatusChangedListener(it.next());
                    }
                    this.mRegisterListListener.clear();
                }
                Log.d(TAG, "mUnregisterListListener.size = " + this.mUnregisterListListener.size());
                if (this.mUnregisterListListener.size() > 0) {
                    Iterator<IVRDialogStatusChangedListener.Stub> it2 = this.mUnregisterListListener.iterator();
                    while (it2.hasNext()) {
                        unregisterVRDialogStatusChangedListener(it2.next());
                    }
                    this.mUnregisterListListener.clear();
                }
                Log.d(TAG, "mRegisterVREngineStatusListListener.size = " + this.mRegisterVREngineStatusListListener.size());
                if (this.mRegisterVREngineStatusListListener.size() > 0) {
                    Iterator<IVREngineStatusChangedListener.Stub> it3 = this.mRegisterVREngineStatusListListener.iterator();
                    while (it3.hasNext()) {
                        registerVREngineStatusChangedListener(it3.next());
                    }
                    this.mRegisterVREngineStatusListListener.clear();
                }
                Log.d(TAG, "mUnregisterVREngineStatusListListener.size = " + this.mUnregisterVREngineStatusListListener.size());
                if (this.mUnregisterVREngineStatusListListener.size() > 0) {
                    Iterator<IVREngineStatusChangedListener.Stub> it4 = this.mUnregisterVREngineStatusListListener.iterator();
                    while (it4.hasNext()) {
                        unregisterVREngineStatusChangedListener(it4.next());
                    }
                    this.mUnregisterVREngineStatusListListener.clear();
                }
            }
        }
    }

    private void serviceIsNull() {
        Log.d(TAG, "VrServiceIsNull" + this.mPackageName);
    }

    private String getAppName() {
        Context context = this.mContext;
        if (context == null) {
            return "";
        }
        try {
            PackageManager packageManager = context.getPackageManager();
            return packageManager.getApplicationInfo(this.mContext.getPackageName(), 128).loadLabel(packageManager).toString();
        } catch (Throwable th) {
            Log.i(TAG, "getAppName:" + th.getMessage());
            return "";
        }
    }

    private String getAppVersionName() {
        Context context = this.mContext;
        if (context == null) {
            return "";
        }
        try {
            return context.getPackageManager().getPackageInfo(this.mContext.getPackageName(), 0).versionName;
        } catch (Exception e) {
            Log.i(TAG, "getAppVersionName:" + e.getMessage());
            return "1.0.0";
        }
    }

    private String getProcessName() {
        Context context = this.mContext;
        if (context == null) {
            return "";
        }
        try {
            return context.getApplicationInfo().processName;
        } catch (Exception e) {
            Log.i(TAG, "getProcessName:" + e.getMessage());
            return "";
        }
    }
}