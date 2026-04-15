package com.geely.lib.oneosapi.phone;

import android.content.Context;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.geely.lib.oneosapi.common.ServiceBaseManager;
import com.geely.lib.oneosapi.phone.inter.IBluetoothService;
import com.geely.lib.oneosapi.phone.inter.IBluetoothServicesListener;
import com.geely.lib.oneosapi.phone.inter.IBtStateChangeListener;
import com.geely.lib.oneosapi.phone.inter.ICallLogDateListener;
import com.geely.lib.oneosapi.phone.inter.IPhonebookDownloadStateListener;
import com.geely.lib.oneosapi.phone.telecom.GlyCallItem;
import com.geely.lib.oneosapi.phone.telecom.GlyCallLogInfo;
import java.util.List;

/* loaded from: classes.dex */
public class PhoneManager implements ServiceBaseManager {
    private static final String TAG = "PhoneManager";
    private final Context mContext;
    private IBluetoothService mService;

    public PhoneManager(Context context, IBinder binder) {
        Log.i(TAG, "PhoneManager binder " + binder);
        this.mContext = context;
        this.mService = IBluetoothService.Stub.asInterface(binder);
    }

    @Override // com.geely.lib.oneosapi.common.ServiceBaseManager
    public void setService(IBinder binder) {
        Log.i(TAG, "setService binder " + binder);
        if (binder != null) {
            this.mService = IBluetoothService.Stub.asInterface(binder);
        }
    }

    public boolean isAlive() {
        IBluetoothService iBluetoothService = this.mService;
        return iBluetoothService != null && iBluetoothService.asBinder().isBinderAlive();
    }

    public void registerListener(IBluetoothServicesListener listener, String pkg) {
        Log.i(TAG, "registerListener pkg: " + pkg);
        Log.i(TAG, "registerListener mService " + this.mService);
        IBluetoothService iBluetoothService = this.mService;
        if (iBluetoothService == null || !iBluetoothService.asBinder().isBinderAlive()) {
            return;
        }
        try {
            this.mService.registerListener(listener, pkg);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void unRegisterListener(IBluetoothServicesListener listener, String pkg) {
        Log.i(TAG, "unRegisterListener pkg: " + pkg);
        IBluetoothService iBluetoothService = this.mService;
        if (iBluetoothService == null || !iBluetoothService.asBinder().isBinderAlive()) {
            return;
        }
        try {
            this.mService.unRegisterListener(listener);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void registerDownloadStateListener(IPhonebookDownloadStateListener listener, String pkg) {
        Log.i(TAG, "registerDownloadStateListener pkg: " + pkg);
        IBluetoothService iBluetoothService = this.mService;
        if (iBluetoothService == null || !iBluetoothService.asBinder().isBinderAlive()) {
            return;
        }
        try {
            this.mService.registerDownloadStateListener(listener, pkg);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void registerCallLogListener(ICallLogDateListener listener, String pkg) {
        Log.i(TAG, "registerCallLogListener pkg: " + pkg);
        IBluetoothService iBluetoothService = this.mService;
        if (iBluetoothService == null || !iBluetoothService.asBinder().isBinderAlive()) {
            return;
        }
        try {
            this.mService.registerCallLogListener(listener, pkg);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void placeCall(String number) {
        Log.i(TAG, "placeCall number: " + number);
        IBluetoothService iBluetoothService = this.mService;
        if (iBluetoothService == null || !iBluetoothService.asBinder().isBinderAlive()) {
            return;
        }
        try {
            this.mService.placeCall(number);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void rejectCall() {
        Log.i(TAG, "rejectCall: ");
        IBluetoothService iBluetoothService = this.mService;
        if (iBluetoothService == null || !iBluetoothService.asBinder().isBinderAlive()) {
            return;
        }
        try {
            this.mService.rejectCall();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void answerCall() {
        Log.i(TAG, "answerCall: ");
        IBluetoothService iBluetoothService = this.mService;
        if (iBluetoothService == null || !iBluetoothService.asBinder().isBinderAlive()) {
            return;
        }
        try {
            this.mService.answerCall();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void disconnectCall() {
        Log.i(TAG, "disconnectCall: ");
        IBluetoothService iBluetoothService = this.mService;
        if (iBluetoothService == null || !iBluetoothService.asBinder().isBinderAlive()) {
            return;
        }
        try {
            this.mService.disconnectCall();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void playDTM(char digit) {
        Log.i(TAG, "playDTM digit: " + digit);
        IBluetoothService iBluetoothService = this.mService;
        if (iBluetoothService == null || !iBluetoothService.asBinder().isBinderAlive()) {
            return;
        }
        try {
            this.mService.playDTM(digit);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void stopDtmfTone() {
        Log.i(TAG, "stopDtmfTone");
        IBluetoothService iBluetoothService = this.mService;
        if (iBluetoothService == null || !iBluetoothService.asBinder().isBinderAlive()) {
            return;
        }
        try {
            this.mService.stopDtmfTone();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void holdCall(boolean isHold) {
        Log.i(TAG, "holdCall isHold: " + isHold);
        IBluetoothService iBluetoothService = this.mService;
        if (iBluetoothService == null || !iBluetoothService.asBinder().isBinderAlive()) {
            return;
        }
        try {
            this.mService.holdCall(isHold);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void setMicrophoneAudio(boolean enable) {
        Log.i(TAG, "setMicrophoneAudio enable: " + enable);
        IBluetoothService iBluetoothService = this.mService;
        if (iBluetoothService == null || !iBluetoothService.asBinder().isBinderAlive()) {
            return;
        }
        try {
            this.mService.setMicrophoneAudio(enable);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void setRingtoneMute(boolean mute) {
        Log.i(TAG, "setRingtoneMute mute: " + mute);
        IBluetoothService iBluetoothService = this.mService;
        if (iBluetoothService == null || !iBluetoothService.asBinder().isBinderAlive()) {
            return;
        }
        try {
            this.mService.setMicrophoneAudio(mute);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public boolean isMicrophoneMute() {
        Log.i(TAG, "isMicrophoneMute");
        IBluetoothService iBluetoothService = this.mService;
        if (iBluetoothService != null && iBluetoothService.asBinder().isBinderAlive()) {
            try {
                return this.mService.isMicrophoneMute();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public void actionAcceptOrDisconnectCall() {
        Log.i(TAG, "actionAcceptOrDisconnectCall");
        IBluetoothService iBluetoothService = this.mService;
        if (iBluetoothService == null || !iBluetoothService.asBinder().isBinderAlive()) {
            return;
        }
        try {
            this.mService.actionAcceptOrDisconnectCall();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void actionRejectOrTerminated() {
        Log.i(TAG, "actionRejectOrTerminated");
        IBluetoothService iBluetoothService = this.mService;
        if (iBluetoothService == null || !iBluetoothService.asBinder().isBinderAlive()) {
            return;
        }
        try {
            this.mService.actionRejectOrTerminated();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void rejectRingOrTerminatedActive() {
        Log.i(TAG, "rejectRingOrTerminatedActive");
        IBluetoothService iBluetoothService = this.mService;
        if (iBluetoothService == null || !iBluetoothService.asBinder().isBinderAlive()) {
            return;
        }
        try {
            this.mService.rejectRingOrTerminatedActive();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void switchActiveCall() {
        Log.i(TAG, "switchActiveCall");
        IBluetoothService iBluetoothService = this.mService;
        if (iBluetoothService == null || !iBluetoothService.asBinder().isBinderAlive()) {
            return;
        }
        try {
            this.mService.switchActiveCall();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void setCallViewStatus(int status) {
        Log.i(TAG, "setCallViewStatus status: " + status);
        IBluetoothService iBluetoothService = this.mService;
        if (iBluetoothService == null || !iBluetoothService.asBinder().isBinderAlive()) {
            return;
        }
        try {
            this.mService.setCallViewStatus(status);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public List<GlyCallLogInfo> getCallLogList() {
        Log.i(TAG, "setCallViewStatus");
        IBluetoothService iBluetoothService = this.mService;
        if (iBluetoothService != null && iBluetoothService.asBinder().isBinderAlive()) {
            try {
                return this.mService.getCallLogList();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public int getDownloadStateListener(int type) {
        Log.i(TAG, "getDownloadStateListener type: " + type);
        IBluetoothService iBluetoothService = this.mService;
        if (iBluetoothService != null && iBluetoothService.asBinder().isBinderAlive()) {
            try {
                return this.mService.getDownloadStateListener(type, -1);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    public void rebroadcastCall() {
        Log.i(TAG, "rebroadcastCall: ");
        IBluetoothService iBluetoothService = this.mService;
        if (iBluetoothService == null || !iBluetoothService.asBinder().isBinderAlive()) {
            return;
        }
        try {
            this.mService.rebroadcastCall();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void callbackCall() {
        Log.i(TAG, "callbackCall: ");
        IBluetoothService iBluetoothService = this.mService;
        if (iBluetoothService == null || !iBluetoothService.asBinder().isBinderAlive()) {
            return;
        }
        try {
            this.mService.callbackCall();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public List<GlyCallItem> getGlyCallItems() {
        Log.i(TAG, "getGlyCallItems");
        IBluetoothService iBluetoothService = this.mService;
        if (iBluetoothService != null && iBluetoothService.asBinder().isBinderAlive()) {
            try {
                return this.mService.getGlyCallItem();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public GlyCallItem getRebroadcastCallItem() {
        Log.i(TAG, "getRebroadcastCallItem");
        IBluetoothService iBluetoothService = this.mService;
        if (iBluetoothService != null && iBluetoothService.asBinder().isBinderAlive()) {
            try {
                return this.mService.getRebroadcastCallItem();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public GlyCallItem getCallbackCallItem() {
        Log.i(TAG, "getCallbackCallItem");
        IBluetoothService iBluetoothService = this.mService;
        if (iBluetoothService != null && iBluetoothService.asBinder().isBinderAlive()) {
            try {
                return this.mService.getCallbackCallItem();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public void registerBtStateListener(IBtStateChangeListener listener, String pkg) {
        Log.i(TAG, "registerBtStateListener pkg: " + pkg);
        IBluetoothService iBluetoothService = this.mService;
        if (iBluetoothService == null || !iBluetoothService.asBinder().isBinderAlive()) {
            return;
        }
        try {
            this.mService.registerBtStateListener(listener, pkg);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}