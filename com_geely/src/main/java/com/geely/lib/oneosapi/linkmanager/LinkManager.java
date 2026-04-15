package com.geely.lib.oneosapi.linkmanager;

import android.content.Context;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.geely.lib.oneosapi.common.ServiceBaseManager;
import com.geely.lib.oneosapi.linkmanager.ILinkListener;
import com.geely.lib.oneosapi.linkmanager.ILinkManager;
import com.geely.lib.oneosapi.linkmanager.IModemCallStateListener;
import com.geely.lib.oneosapi.linkmanager.IMusicStateListener;
import com.geely.lib.oneosapi.linkmanager.ITryConnectCallback;

/* loaded from: classes.dex */
public class LinkManager implements ServiceBaseManager {
    private static final String TAG = "LinkManager";
    private final Context mContext;
    private ILinkManager mService;

    public LinkManager(Context context, IBinder binder) {
        this.mContext = context;
        this.mService = ILinkManager.Stub.asInterface(binder);
    }

    @Override // com.geely.lib.oneosapi.common.ServiceBaseManager
    public void setService(IBinder binder) {
        if (binder != null) {
            this.mService = ILinkManager.Stub.asInterface(binder);
        }
    }

    public void sessionConnected(int brand, int type) {
        Log.i(TAG, "sessionConnected");
        ILinkManager iLinkManager = this.mService;
        if (iLinkManager == null || !iLinkManager.asBinder().isBinderAlive()) {
            return;
        }
        try {
            this.mService.sessionConnected(brand, type);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void sessionDisconnected(int brand, int type) {
        Log.i(TAG, "sessionDisconnected");
        ILinkManager iLinkManager = this.mService;
        if (iLinkManager == null || !iLinkManager.asBinder().isBinderAlive()) {
            return;
        }
        try {
            this.mService.sessionDisconnected(brand, type);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public boolean isSessionConnected() {
        Log.i(TAG, "isSessionConnected");
        ILinkManager iLinkManager = this.mService;
        if (iLinkManager != null && iLinkManager.asBinder().isBinderAlive()) {
            try {
                return this.mService.isSessionConnected();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public int getConnectedSessionBrand() {
        Log.i(TAG, "getConnectedSessionBrand");
        ILinkManager iLinkManager = this.mService;
        if (iLinkManager != null && iLinkManager.asBinder().isBinderAlive()) {
            try {
                return this.mService.getConnectedSessionBrand();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    public int getConnectedSessionType() {
        Log.i(TAG, "getConnectedSessionType");
        ILinkManager iLinkManager = this.mService;
        if (iLinkManager != null && iLinkManager.asBinder().isBinderAlive()) {
            try {
                return this.mService.getConnectedSessionType();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    public void tryConnect(int brand, int type, ITryConnectCallback callback) {
        Log.i(TAG, "tryConnect brand = " + brand + " type = " + type);
        ILinkManager iLinkManager = this.mService;
        if (iLinkManager == null || !iLinkManager.asBinder().isBinderAlive()) {
            return;
        }
        try {
            this.mService.tryConnect(brand, type, callback);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void registerListener(BaseLinkListener listener, String packageName) {
        Log.i(TAG, "registerListener " + packageName);
        ILinkManager iLinkManager = this.mService;
        if (iLinkManager == null || !iLinkManager.asBinder().isBinderAlive()) {
            Log.e(TAG, "registerListener: service null");
            return;
        }
        try {
            this.mService.registerListener(listener, packageName);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void unregisterListener(BaseLinkListener listener, String packageName) {
        Log.i(TAG, "unregisterListener " + packageName);
        ILinkManager iLinkManager = this.mService;
        if (iLinkManager == null || !iLinkManager.asBinder().isBinderAlive()) {
            return;
        }
        try {
            this.mService.unregisterListener(listener, packageName);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void registerMusicStateListener(MusicStateListener listener, String packageName) {
        Log.i(TAG, "registerListener " + packageName);
        ILinkManager iLinkManager = this.mService;
        if (iLinkManager == null || !iLinkManager.asBinder().isBinderAlive()) {
            Log.e(TAG, "registerListener: service null");
            return;
        }
        try {
            this.mService.registerMusicStateListener(listener, packageName);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void unregisterMusicStateListener(MusicStateListener listener, String packageName) {
        Log.i(TAG, "unregisterListener " + packageName);
        ILinkManager iLinkManager = this.mService;
        if (iLinkManager == null || !iLinkManager.asBinder().isBinderAlive()) {
            return;
        }
        try {
            this.mService.unregisterMusicStateListener(listener, packageName);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void registerModemCallStateListener(ModemCallStateListener listener, String packageName) {
        Log.i(TAG, "registerListener " + packageName);
        ILinkManager iLinkManager = this.mService;
        if (iLinkManager == null || !iLinkManager.asBinder().isBinderAlive()) {
            Log.e(TAG, "registerListener: service null");
            return;
        }
        try {
            this.mService.registerModemCallStateListener(listener, packageName);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void unregisterModemCallStateListener(ModemCallStateListener listener, String packageName) {
        Log.i(TAG, "unregisterListener " + packageName);
        ILinkManager iLinkManager = this.mService;
        if (iLinkManager == null || !iLinkManager.asBinder().isBinderAlive()) {
            return;
        }
        try {
            this.mService.unregisterModemCallStateListener(listener, packageName);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void updatePlayState(int state, int brand) {
        StringBuilder sb = new StringBuilder();
        sb.append("updatePlayState brand  ");
        sb.append(brand == 1 ? "HiCar" : "CarLink");
        sb.append(" state = ");
        sb.append(state == 1 ? "play" : "stop");
        Log.d(TAG, sb.toString());
        ILinkManager iLinkManager = this.mService;
        if (iLinkManager == null || !iLinkManager.asBinder().isBinderAlive()) {
            return;
        }
        try {
            this.mService.updatePlayState(state, brand);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public boolean isSourceActivated() {
        Log.d(TAG, "isSourceActivated: ");
        ILinkManager iLinkManager = this.mService;
        if (iLinkManager != null && iLinkManager.asBinder().isBinderAlive()) {
            try {
                Log.d(TAG, "isSourceActivated: " + this.mService.isSourceActivated());
                return this.mService.isSourceActivated();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public void play() {
        Log.d(TAG, "play: ");
        ILinkManager iLinkManager = this.mService;
        if (iLinkManager == null || !iLinkManager.asBinder().isBinderAlive()) {
            return;
        }
        try {
            this.mService.play();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        Log.d(TAG, "stop: ");
        ILinkManager iLinkManager = this.mService;
        if (iLinkManager == null || !iLinkManager.asBinder().isBinderAlive()) {
            return;
        }
        try {
            this.mService.stop();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void next() {
        Log.d(TAG, "next: ");
        ILinkManager iLinkManager = this.mService;
        if (iLinkManager == null || !iLinkManager.asBinder().isBinderAlive()) {
            return;
        }
        try {
            this.mService.next();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void previous() {
        Log.d(TAG, "previous: ");
        ILinkManager iLinkManager = this.mService;
        if (iLinkManager == null || !iLinkManager.asBinder().isBinderAlive()) {
            return;
        }
        try {
            this.mService.previous();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void fastForward() {
        Log.d(TAG, "fastForward: ");
        ILinkManager iLinkManager = this.mService;
        if (iLinkManager == null || !iLinkManager.asBinder().isBinderAlive()) {
            return;
        }
        try {
            this.mService.fastForward();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void fastBackward() {
        Log.d(TAG, "fastBackward: ");
        ILinkManager iLinkManager = this.mService;
        if (iLinkManager == null || !iLinkManager.asBinder().isBinderAlive()) {
            return;
        }
        try {
            this.mService.fastBackward();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void setMusicInfo(String artistName, String albumName, String coverArt, String lyrics, long totalTimesMs, String title, String authorName, String writerName, String composerName, int playingCurrentTimeMs, boolean isFavorite, boolean isPlaying) {
        Log.i(TAG, "setMusicInfo");
        ILinkManager iLinkManager = this.mService;
        if (iLinkManager == null || !iLinkManager.asBinder().isBinderAlive()) {
            return;
        }
        try {
            this.mService.setMusicInfo(artistName, albumName, coverArt, lyrics, totalTimesMs, title, authorName, writerName, composerName, playingCurrentTimeMs, isFavorite, isPlaying);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void setModemCallState(int state) {
        Log.i(TAG, "setMusicInfo");
        ILinkManager iLinkManager = this.mService;
        if (iLinkManager == null || !iLinkManager.asBinder().isBinderAlive()) {
            return;
        }
        try {
            this.mService.setModemCallState(state);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public static abstract class MusicStateListener extends IMusicStateListener.Stub {
        @Override // com.geely.lib.oneosapi.linkmanager.IMusicStateListener
        public void onMusicStatusChange(String artistName, String albumName, String coverArt, String lyrics, long totalTimesMs, String title, String authorName, String writerName, String composerName, int playingCurrentTimeMs, boolean isFavorite, boolean isPlaying) throws RemoteException {
            Log.d(LinkManager.TAG, "onMusicStatusChange = " + artistName);
        }
    }

    public static abstract class ModemCallStateListener extends IModemCallStateListener.Stub {
        @Override // com.geely.lib.oneosapi.linkmanager.IModemCallStateListener
        public void onModemCallStateChange(int state) throws RemoteException {
            Log.d(LinkManager.TAG, "onModemCallStateChange = " + state);
        }
    }

    public static abstract class BaseLinkListener extends ILinkListener.Stub {
        @Override // com.geely.lib.oneosapi.linkmanager.ILinkListener
        public void onFastBackward() throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.linkmanager.ILinkListener
        public void onFastForward() throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.linkmanager.ILinkListener
        public void onNext() throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.linkmanager.ILinkListener
        public void onPlay() throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.linkmanager.ILinkListener
        public void onPrevious() throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.linkmanager.ILinkListener
        public void onSourceActivated(boolean active) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.linkmanager.ILinkListener
        public void onStop() throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.linkmanager.ILinkListener
        public void onConnectStatusChange(int connect, int brand, int type) {
            Log.d(LinkManager.TAG, "onConnectStatusChange connect = " + connect + " brand = " + brand + " type = " + type);
        }

        @Override // com.geely.lib.oneosapi.linkmanager.ILinkListener
        public void onDisconnectRequest(int brand, int type) {
            Log.d(LinkManager.TAG, "onDisconnectRequest brand = " + brand + " type = " + type);
        }
    }

    public static abstract class BaseTryConnectCallback extends ITryConnectCallback.Stub {
        @Override // com.geely.lib.oneosapi.linkmanager.ITryConnectCallback
        public void permitted() {
            Log.d(LinkManager.TAG, "permitted: ");
        }

        @Override // com.geely.lib.oneosapi.linkmanager.ITryConnectCallback
        public void denied() {
            Log.d(LinkManager.TAG, "denied: ");
        }
    }
}