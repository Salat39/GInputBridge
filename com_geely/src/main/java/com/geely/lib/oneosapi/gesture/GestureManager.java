package com.geely.lib.oneosapi.gesture;

import android.content.Context;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import com.geely.lib.oneosapi.common.ServiceBaseManager;
import com.geely.lib.oneosapi.gesture.IGestureEventNotifyCallback;
import com.geely.lib.oneosapi.gesture.IGestureManager;
import java.lang.ref.WeakReference;

/* loaded from: classes.dex */
public class GestureManager implements ServiceBaseManager {
    private static final int MSG_GESTURE_FUN_NOTIFY = 101;
    private static final String TAG = "GestureManager";
    private IGestureEventNotifyCallback gestureEventNotifyCallback = new IGestureEventNotifyCallback.Stub() { // from class: com.geely.lib.oneosapi.gesture.GestureManager.1
        @Override // com.geely.lib.oneosapi.gesture.IGestureEventNotifyCallback
        public String getUID() throws RemoteException {
            return "";
        }

        @Override // com.geely.lib.oneosapi.gesture.IGestureEventNotifyCallback
        public void onGestureNotify(GestureModel notifyModel) throws RemoteException {
            Message message = new Message();
            message.what = 101;
            message.obj = notifyModel;
            GestureManager.this.mHandler.sendMessage(message);
        }
    };
    private IGestureNotifyListener gestureNotifyListener;
    private final Context mContext;
    private SyncHandler mHandler;
    private IGestureManager mService;

    private static class SyncHandler extends Handler {
        WeakReference<GestureManager> weakReference;

        private SyncHandler(GestureManager gestureManager, Looper looper) {
            super(looper);
            this.weakReference = new WeakReference<>(gestureManager);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            GestureManager gestureManager = this.weakReference.get();
            if (msg.what == 101 && gestureManager.gestureNotifyListener != null) {
                GestureModel gestureModel = (GestureModel) msg.obj;
                Log.d(GestureManager.TAG, "onGestureNotify: " + gestureModel.toString());
                gestureManager.gestureNotifyListener.onGestureNotify(gestureModel);
            }
        }
    }

    public GestureManager(Context context, IBinder binder) {
        this.mContext = context;
        this.mService = IGestureManager.Stub.asInterface(binder);
//        this.mHandler = new SyncHandler(context.getMainLooper());
        Log.d(TAG, "GestureManager: mService=" + this.mService);
    }

    @Override // com.geely.lib.oneosapi.common.ServiceBaseManager
    public void setService(IBinder binder) {
        this.mService = IGestureManager.Stub.asInterface(binder);
        Log.d(TAG, "setService: mService=" + this.mService);
    }

    public boolean addGestureDetectEventNotify(IGestureNotifyListener notifyListener) {
        try {
            if (this.mService == null) {
                return false;
            }
            this.gestureNotifyListener = notifyListener;
            return this.mService.addGestureDetectEventNotify(this.gestureEventNotifyCallback);
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean removeGestureDetectEventNotify() {
        try {
            if (this.mService == null) {
                return false;
            }
            this.gestureNotifyListener = null;
            return this.mService.removeGestureDetectEventNotify(this.gestureEventNotifyCallback);
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }
}