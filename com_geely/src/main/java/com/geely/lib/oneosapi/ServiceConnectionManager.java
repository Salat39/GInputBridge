package com.geely.lib.oneosapi;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;

import com.geely.lib.oneosapi.listener.ApiConnectCallBack;
import com.geely.lib.oneosapi.listener.ServiceConnectionListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
class ServiceConnectionManager {
    private static final String CLASS_NAME = "com.geely.service.oneosapi.OneOSApiService";
    private static final int MSG_BIND_SERVICE = 100;
    private static final int MSG_DISCONNECT_BIND_SERVICE = 102;
    private static final int MSG_RETRY_BIND_SERVICE = 101;
    private static final int MSG_SERVICE_ALREADY_CONNECTED = 103;
    private static final String PACKAGE_NAME = "com.geely.service.oneosapi";
    private static final int STATE_CONNECTED = 1;
    private static final int STATE_DISCONNECTED = 0;
    private static final String TAG = "ServiceConnection";
    private static final int TIME_DISCONNECT_BIND_SERVICE = 3000;
    private static final int TIME_RETRY_BIND_SERVICE = 2000;
    private ApiConnectCallBack apiConnectCallBack;
    private boolean mBinderResult;
    private int mConnectionState;
    private final Context mContext;
    private ConnectionHandler mHandler;
    private IServiceManager mServiceManager;
    private ServiceManagerCallback mServiceManagerCallback;
    private final List<ServiceConnectionListener> mServiceListeners = new ArrayList();
    private final ServiceConnection mServiceConnection = new ServiceConnection() { // from class: com.geely.lib.oneosapi.ServiceConnectionManager.1
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            if (iBinder == null) {
                Log.w(ServiceConnectionManager.TAG, "onServiceConnected : iBinder is null");
                return;
            }
            try {
                if (iBinder.isBinderAlive()) {
                    ServiceConnectionManager.this.mServiceManager = IServiceManager.Stub.asInterface(iBinder);
                    ServiceConnectionManager.this.mConnectionState = 1;
                    iBinder.linkToDeath(ServiceConnectionManager.this.new ServiceDeathRecipient(iBinder), 0);
                    Log.d(ServiceConnectionManager.TAG, "onServiceConnected--updateServiceConnectionChangedCallback");
                    ServiceConnectionManager.this.updateServiceConnectionChangedCallback(true);
                    if (ServiceConnectionManager.this.mServiceManagerCallback == null) {
                        ServiceConnectionManager.this.mServiceManagerCallback = new ServiceManagerCallback();
                    }
                    ServiceConnectionManager.this.mServiceManager.registerCallback(ServiceConnectionManager.this.mServiceManagerCallback);
                    if (ServiceConnectionManager.this.apiConnectCallBack != null) {
                        ServiceConnectionManager.this.apiConnectCallBack.success();
                        return;
                    }
                    return;
                }
                Log.d(ServiceConnectionManager.TAG, "onServiceConnected--iBinder is died, retry bindservice.");
                ServiceConnectionManager.this.mConnectionState = 0;
                if (ServiceConnectionManager.this.mHandler.hasMessages(101)) {
                    ServiceConnectionManager.this.mHandler.removeMessages(101);
                }
                ServiceConnectionManager.this.mHandler.sendEmptyMessageDelayed(101, 2000L);
                ServiceConnectionManager.this.disconnect();
                ServiceConnectionManager.this.updateServiceConnectionChangedCallback(false);
                if (ServiceConnectionManager.this.apiConnectCallBack != null) {
                    ServiceConnectionManager.this.apiConnectCallBack.fail();
                }
            } catch (RemoteException e) {
                e.printStackTrace();
                if (ServiceConnectionManager.this.apiConnectCallBack != null) {
                    ServiceConnectionManager.this.apiConnectCallBack.fail();
                }
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            Log.d(ServiceConnectionManager.TAG, "onServiceDisconnected");
            ServiceConnectionManager.this.mServiceManager = null;
            if (ServiceConnectionManager.this.mConnectionState == 0) {
                return;
            }
            ServiceConnectionManager.this.disconnect();
            ServiceConnectionManager.this.updateServiceConnectionChangedCallback(false);
        }
    };

    ServiceConnectionManager(Context context) {
        this.mContext = context;
        if (this.mHandler == null) {
            this.mHandler = new ConnectionHandler(context.getMainLooper());
        }
    }

    boolean isServiceBound() {
        return this.mConnectionState == 1;
    }

    void connect() {
        Log.i(TAG, "connect mBinderResult:" + this.mBinderResult);
        if (!this.mBinderResult) {
            if (this.mHandler.hasMessages(100)) {
                this.mHandler.removeMessages(100);
            }
            this.mHandler.sendEmptyMessage(100);
        } else {
            ApiConnectCallBack apiConnectCallBack = this.apiConnectCallBack;
            if (apiConnectCallBack != null) {
                apiConnectCallBack.success();
            }
        }
    }

    void connect(ApiConnectCallBack connectCallBack) {
        Log.i(TAG, "connectCallBack:" + connectCallBack);
        this.apiConnectCallBack = connectCallBack;
        connect();
    }

    void registerServiceConnectionListener(ServiceConnectionListener listener) {
        if (listener != null) {
            synchronized (this.mServiceListeners) {
                if (!this.mServiceListeners.contains(listener)) {
                    Log.i(TAG, "setServiceConnectionListener add listener:" + listener);
                    this.mServiceListeners.add(listener);
                }
            }
            if (isServiceBound()) {
                this.mHandler.sendMessage(this.mHandler.obtainMessage(103, listener));
            }
        }
    }

    void unregisterServiceConnectionListener(ServiceConnectionListener listener) {
        if (listener != null) {
            synchronized (this.mServiceListeners) {
                Log.i(TAG, "removeServiceConnectionListener listener:" + listener);
                this.mServiceListeners.remove(listener);
            }
        }
    }

    void release() {
        synchronized (this.mServiceListeners) {
            if (this.mServiceListeners.size() > 0) {
                Log.d(TAG, "destroy listener");
                this.mServiceListeners.clear();
            }
        }
        unBindService();
    }

    void bindService() {
        Intent intent = new Intent();
        intent.setClassName(PACKAGE_NAME, CLASS_NAME);
        Context context = this.mContext;
        if (context != null) {
            this.mBinderResult = context.bindService(intent, this.mServiceConnection, 1);

            // TODO TEST
            Log.i(TAG, "bindService  PackageName:com.geely.service.oneosapi,mBinderResult:" + this.mBinderResult);

            if (this.mBinderResult) {
                return;
            }
            if (this.mHandler.hasMessages(101)) {
                this.mHandler.removeMessages(101);
            }
            this.mHandler.sendEmptyMessageDelayed(101, 2000L);
        }
    }

    void unBindService() {
        try {
            if (this.mConnectionState == 1 && this.mBinderResult) {
                ServiceManagerCallback serviceManagerCallback = this.mServiceManagerCallback;
                if (serviceManagerCallback != null) {
                    try {
                        this.mServiceManager.unregisterCallback(serviceManagerCallback);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
                this.mContext.unbindService(this.mServiceConnection);
                this.mConnectionState = 0;
                this.mBinderResult = false;
            }
            ConnectionHandler connectionHandler = this.mHandler;
            if (connectionHandler != null) {
                connectionHandler.removeCallbacksAndMessages(null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    IServiceManager getServiceManager() {
        return this.mServiceManager;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reBindService() {
        if (this.mConnectionState != 1) {
            bindService();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void disconnect() {
        if (this.mConnectionState == 0) {
            return;
        }
        ConnectionHandler connectionHandler = this.mHandler;
        if (connectionHandler != null) {
            connectionHandler.removeCallbacksAndMessages(null);
        }
        this.mConnectionState = 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateServiceConnectionChangedCallback(boolean connectionState) {
        synchronized (this.mServiceListeners) {
            Log.i(TAG, "updateServiceConnectionChangedCallback--binderState:" + connectionState);
            if (this.mServiceListeners.size() > 0) {
                Iterator<ServiceConnectionListener> it = this.mServiceListeners.iterator();
                while (it.hasNext()) {
                    it.next().onServiceConnectionChanged(connectionState);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateServiceBinderUpdatedCallback(int binderType) {
        synchronized (this.mServiceListeners) {
            Log.i(TAG, "updateServiceBinderUpdatedCallback--binderType:" + binderType);
            if (this.mServiceListeners.size() > 0) {
                Iterator<ServiceConnectionListener> it = this.mServiceListeners.iterator();
                while (it.hasNext()) {
                    it.next().onServiceBinderUpdated(binderType);
                }
            }
        }
    }

    private class ServiceDeathRecipient implements IBinder.DeathRecipient {
        private final IBinder mListenerBinder;

        ServiceDeathRecipient(IBinder listenerBinder) {
            this.mListenerBinder = listenerBinder;
        }

        @Override // android.os.IBinder.DeathRecipient
        public void binderDied() {
            this.mListenerBinder.unlinkToDeath(this, 0);
            Log.w(ServiceConnectionManager.TAG, "binderDied--retry binder");
            ServiceConnectionManager.this.mConnectionState = 0;
            if (ServiceConnectionManager.this.mHandler.hasMessages(101)) {
                ServiceConnectionManager.this.mHandler.removeMessages(101);
            }
            ServiceConnectionManager.this.mHandler.sendEmptyMessageDelayed(101, 2000L);
            ServiceConnectionManager.this.disconnect();
            ServiceConnectionManager.this.updateServiceConnectionChangedCallback(false);
        }
    }

    private class ServiceManagerCallback extends IServiceManagerCallback.Stub {
        private ServiceManagerCallback() {
        }

        @Override // com.geely.lib.oneosapi.IServiceManagerCallback
        public void onServiceChanged(int type) {
            ServiceConnectionManager.this.updateServiceBinderUpdatedCallback(type);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleServiceAlreadyConnected(Message msg) {
        if (msg.obj instanceof ServiceConnectionListener) {
            ((ServiceConnectionListener) msg.obj).onServiceConnectionChanged(true);
        }
    }

    private class ConnectionHandler extends Handler {
        private ConnectionHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 100:
                    ServiceConnectionManager.this.bindService();
                    break;
                case 101:
                case 102:
                    ServiceConnectionManager.this.reBindService();
                    break;
                case 103:
                    ServiceConnectionManager.this.handleServiceAlreadyConnected(msg);
                    break;
            }
        }
    }
}