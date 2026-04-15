package com.geely.lib.oneosapi.mediacenter;

import android.content.Context;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;
import com.geely.lib.oneosapi.common.ServiceBaseManager;
import com.geely.lib.oneosapi.mediacenter.IHeartbeatPacket;
import com.geely.lib.oneosapi.mediacenter.IMediaCenter;
import com.geely.lib.oneosapi.mediacenter.base.BaseMusicManager;
import com.geely.lib.oneosapi.mediacenter.constant.MediaCenterConstant;
import com.geely.lib.oneosapi.mediacenter.listener.IRebootPlayChangeListener;
import com.geely.lib.oneosapi.mediacenter.listener.ISourceStateListener;
import com.geely.lib.oneosapi.mediacenter.listener.RebootPlayChangeListener;
import com.geely.lib.oneosapi.mediacenter.listener.SourceStateListener;
import com.geely.lib.oneosapi.mediacenter.manager.BtMusicManager;
import com.geely.lib.oneosapi.mediacenter.manager.CPAAMusicManager;
import com.geely.lib.oneosapi.mediacenter.manager.CarManager;
import com.geely.lib.oneosapi.mediacenter.manager.MusicAdapterManager;
import com.geely.lib.oneosapi.mediacenter.manager.OnlineMusicManager;
import com.geely.lib.oneosapi.mediacenter.manager.RadioManager;
import com.geely.lib.oneosapi.mediacenter.manager.UsbMusicManager;
import com.geely.lib.oneosapi.mediacenter.manager.YunTingManager;
import com.geely.lib.oneosapi.mediacenter.vr.VrAdapterManager;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes.dex */
public class MediaCenterManager implements ServiceBaseManager {
    private static final String TAG = "MediaCenterManager";
    private final BtMusicManager mBtMusicManager;
    private final CPAAMusicManager mCPAAMusicManager;
    private final CarManager mCarManager;
    private Context mContext;
    private final Handler mHandler;
    private final MusicAdapterManager mMusicAdapterManager;
    private final Map<MediaCenterConstant.AudioSource, BaseMusicManager> mMusicManagerMap;
    private final OnlineMusicManager mOnlineMusicManager;
    private MediaCenterManager mPsdMediaCenterManager;
    private boolean mPsdMode;
    private final RadioManager mRadioManager;
    private final RebootPlayChangeListenerImpl mRebootPlayChangeListenerImpl;
    private final CopyOnWriteArrayList<RebootPlayChangeListener> mRebootPlayChangeListeners;
    private IMediaCenter mService;
    private final UsbMusicManager mUsbMusicManager;
    private final VrAdapterManager mVrAdapterManager;
    private final YunTingManager mYunTingManager;
    private final CopyOnWriteArrayList<SourceStateListener> mSourceStateListeners = new CopyOnWriteArrayList<>();
    private final SourceStateListenerImpl mSourceStateListenerImpl = new SourceStateListenerImpl(this);
    private final SourceStateListenerImpl mPsdSourceStateListenerImpl = new SourceStateListenerImpl(true);

    public MediaCenterManager(Context context, IBinder binder) {
        Map<MediaCenterConstant.AudioSource, BaseMusicManager> synchronizedMap = Collections.synchronizedMap(new HashMap());
        this.mMusicManagerMap = synchronizedMap;
        this.mRebootPlayChangeListeners = new CopyOnWriteArrayList<>();
        this.mRebootPlayChangeListenerImpl = new RebootPlayChangeListenerImpl();
        this.mHandler = new Handler(Looper.getMainLooper());
        this.mContext = context;
        BtMusicManager btMusicManager = new BtMusicManager(this.mContext, null, this);
        this.mBtMusicManager = btMusicManager;
        synchronizedMap.put(MediaCenterConstant.AudioSource.AUDIO_SOURCE_BT, btMusicManager);
        UsbMusicManager usbMusicManager = new UsbMusicManager(this.mContext, null, this);
        this.mUsbMusicManager = usbMusicManager;
        synchronizedMap.put(MediaCenterConstant.AudioSource.AUDIO_SOURCE_USB, usbMusicManager);
        CPAAMusicManager cPAAMusicManager = new CPAAMusicManager(context, null, this);
        this.mCPAAMusicManager = cPAAMusicManager;
        synchronizedMap.put(MediaCenterConstant.AudioSource.AUDIO_SOURCE_CPAA, cPAAMusicManager);
        OnlineMusicManager onlineMusicManager = new OnlineMusicManager(this.mContext, null, this);
        this.mOnlineMusicManager = onlineMusicManager;
        synchronizedMap.put(MediaCenterConstant.AudioSource.AUDIO_SOURCE_ONLINE, onlineMusicManager);
        YunTingManager yunTingManager = new YunTingManager(this.mContext, null, this);
        this.mYunTingManager = yunTingManager;
        synchronizedMap.put(MediaCenterConstant.AudioSource.AUDIO_SOURCE_YUNTING, yunTingManager);
        this.mRadioManager = new RadioManager(this.mContext, null, this);
        this.mCarManager = new CarManager(this.mContext, null, this);
        initMediaCenterManger(binder);
        this.mVrAdapterManager = new VrAdapterManager(context, this);
        this.mMusicAdapterManager = new MusicAdapterManager(context, this);
    }

    @Override // com.geely.lib.oneosapi.common.ServiceBaseManager
    public void setService(IBinder binder) {
        MediaCenterManager mediaCenterManager = this.mPsdMediaCenterManager;
        if (mediaCenterManager != null) {
            mediaCenterManager.setService(binder);
        }
        initMediaCenterManger(binder);
    }

    public boolean isAlive() {
        IMediaCenter iMediaCenter = this.mService;
        return iMediaCenter != null && iMediaCenter.asBinder().isBinderAlive();
    }

    private void initMediaCenterManger(IBinder binder) {
        if (binder != null) {
            this.mService = IMediaCenter.Stub.asInterface(binder);
            initMusicManager();
            initRadioManager();
            initCarManager();
            try {
                this.mService.addSourceStateListener(this.mSourceStateListenerImpl);
                this.mService.addPsdSourceStateListener(this.mPsdSourceStateListenerImpl);
                this.mService.addRebootPlayChangeListener(this.mRebootPlayChangeListenerImpl);
                return;
            } catch (RemoteException e) {
                e.printStackTrace();
                return;
            }
        }
        notifyMediaCenterDied();
    }

    private void initCarManager() {
        IMediaCenter iMediaCenter = this.mService;
        if (iMediaCenter != null) {
            try {
                this.mCarManager.setService(iMediaCenter.getCarManager());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    private void notifyMediaCenterDied() {
        this.mService = null;
        BtMusicManager btMusicManager = this.mBtMusicManager;
        if (btMusicManager != null) {
            btMusicManager.setService(null);
        }
        UsbMusicManager usbMusicManager = this.mUsbMusicManager;
        if (usbMusicManager != null) {
            usbMusicManager.setService(null);
        }
        CPAAMusicManager cPAAMusicManager = this.mCPAAMusicManager;
        if (cPAAMusicManager != null) {
            cPAAMusicManager.setService(null);
        }
        OnlineMusicManager onlineMusicManager = this.mOnlineMusicManager;
        if (onlineMusicManager != null) {
            onlineMusicManager.setService(null);
        }
        RadioManager radioManager = this.mRadioManager;
        if (radioManager != null) {
            radioManager.setService(null);
        }
    }

    private void initMusicManager() {
        Log.d(TAG, "initMusicManager() called " + this.mService);
        IMediaCenter iMediaCenter = this.mService;
        if (iMediaCenter != null) {
            try {
                IMusicManager musicManager = iMediaCenter.getMusicManager();
                OnlineMusicManager onlineMusicManager = this.mOnlineMusicManager;
                if (onlineMusicManager != null) {
                    onlineMusicManager.setService(musicManager);
                }
                UsbMusicManager usbMusicManager = this.mUsbMusicManager;
                if (usbMusicManager != null) {
                    usbMusicManager.setService(musicManager);
                }
                CPAAMusicManager cPAAMusicManager = this.mCPAAMusicManager;
                if (cPAAMusicManager != null) {
                    cPAAMusicManager.setService(musicManager);
                }
                BtMusicManager btMusicManager = this.mBtMusicManager;
                if (btMusicManager != null) {
                    btMusicManager.setService(musicManager);
                }
                YunTingManager yunTingManager = this.mYunTingManager;
                if (yunTingManager != null) {
                    yunTingManager.setService(musicManager);
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    private void initRadioManager() {
        IMediaCenter iMediaCenter = this.mService;
        if (iMediaCenter != null) {
            try {
                this.mRadioManager.setService(iMediaCenter.getRadioManager());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void addSourceStateListener(SourceStateListener listener) {
        if (listener == null || this.mSourceStateListeners.contains(listener)) {
            return;
        }
        this.mSourceStateListeners.add(listener);
    }

    public void removeSourceStateListener(SourceStateListener listener) {
        if (listener != null) {
            this.mSourceStateListeners.remove(listener);
        }
    }

    public UsbMusicManager getUsbMusicManager() {
        return this.mUsbMusicManager;
    }

    public CPAAMusicManager getCPAAMusicManager() {
        return this.mCPAAMusicManager;
    }

    public BtMusicManager getBtMusicManager() {
        return this.mBtMusicManager;
    }

    public YunTingManager getYunTingManager() {
        return this.mYunTingManager;
    }

    public OnlineMusicManager getOnlineMusicManager() {
        return this.mOnlineMusicManager;
    }

    public RadioManager getRadioManager() {
        return this.mRadioManager;
    }

    public CarManager getCarManager() {
        return this.mCarManager;
    }

    public VrAdapterManager getVrAdapterManager() {
        return this.mVrAdapterManager;
    }

    public MusicAdapterManager getMusicAdapterManager() {
        return this.mMusicAdapterManager;
    }

    public Map<MediaCenterConstant.AudioSource, BaseMusicManager> getMusicManagerMap() {
        return this.mMusicManagerMap;
    }

    public void requestAudioSource(MediaCenterConstant.AudioSource source, MediaCenterConstant.AppSource app) {
        Log.d(TAG, "requestAudioSource() called with: source = [" + source + "], app = [" + app + "] by " + this.mContext.getPackageName());
        IMediaCenter iMediaCenter = this.mService;
        if (iMediaCenter != null) {
            try {
                iMediaCenter.requestAudioSource(source.ordinal(), app.ordinal());
                return;
            } catch (RemoteException e) {
                e.printStackTrace();
                return;
            }
        }
        Log.w(TAG, "requestMediaSource: mService is null");
    }

    public void requestAudioSource(MediaCenterConstant.AudioSource source) {
        requestAudioSource(source, MediaCenterConstant.AppSource.UNKNOWN);
    }

    public MediaCenterConstant.AudioSource getCurrentAudioSource() {
        int currentAudioSource;
        if (this.mService != null) {
            try {
                if (isPsdMode()) {
                    currentAudioSource = this.mService.getCurrentPsdAudioSource();
                } else {
                    currentAudioSource = this.mService.getCurrentAudioSource();
                }
                return MediaCenterConstant.getAudioSourceEnum(currentAudioSource);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return MediaCenterConstant.AudioSource.AUDIO_SOURCE_UNKNOWN;
    }

    public MediaCenterConstant.AppSource getCurrentAppSource() {
        int currentAppSource;
        if (this.mService != null) {
            try {
                if (isPsdMode()) {
                    currentAppSource = this.mService.getCurrentPsdAppSource();
                } else {
                    currentAppSource = this.mService.getCurrentAppSource();
                }
                return MediaCenterConstant.getAppSourceEnum(currentAppSource);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return MediaCenterConstant.AppSource.UNKNOWN;
    }

    protected class SourceStateListenerImpl extends ISourceStateListener.Stub {
        private final boolean isPsdCallback;

        public SourceStateListenerImpl(final MediaCenterManager this$0) {
            this(false);
        }

        public SourceStateListenerImpl(boolean isPsdCallback) {
            this.isPsdCallback = isPsdCallback;
        }

        @Override // com.geely.lib.oneosapi.mediacenter.listener.ISourceStateListener
        public void onSourceChanged(final int audioSource, final int appSource) throws RemoteException {
            if (this.isPsdCallback != MediaCenterManager.this.isPsdMode()) {
                return;
            }
            MediaCenterManager.this.mHandler.post(new Runnable() { // from class: com.geely.lib.oneosapi.mediacenter.MediaCenterManager.SourceStateListenerImpl.1
                @Override // java.lang.Runnable
                public void run() {
                    Iterator it = MediaCenterManager.this.mSourceStateListeners.iterator();
                    while (it.hasNext()) {
                        ((SourceStateListener) it.next()).onSourceChanged(MediaCenterConstant.getAudioSourceEnum(audioSource), MediaCenterConstant.getAppSourceEnum(appSource));
                    }
                }
            });
        }

        @Override // com.geely.lib.oneosapi.mediacenter.listener.ISourceStateListener
        public void onPsdBtStateChanged(final boolean connected) throws RemoteException {
            if (this.isPsdCallback != MediaCenterManager.this.isPsdMode()) {
                return;
            }
            MediaCenterManager.this.mHandler.post(new Runnable() { // from class: com.geely.lib.oneosapi.mediacenter.MediaCenterManager.SourceStateListenerImpl.2
                @Override // java.lang.Runnable
                public void run() {
                    Iterator it = MediaCenterManager.this.mSourceStateListeners.iterator();
                    while (it.hasNext()) {
                        ((SourceStateListener) it.next()).onPsdBtStateChanged(connected);
                    }
                }
            });
        }

        @Override // com.geely.lib.oneosapi.mediacenter.listener.ISourceStateListener
        public void onWeCarFlowTabChanged(final int audioSource, final int appSource) throws RemoteException {
            if (this.isPsdCallback != MediaCenterManager.this.isPsdMode()) {
                return;
            }
            MediaCenterManager.this.mHandler.post(new Runnable() { // from class: com.geely.lib.oneosapi.mediacenter.MediaCenterManager.SourceStateListenerImpl.3
                @Override // java.lang.Runnable
                public void run() {
                    Iterator it = MediaCenterManager.this.mSourceStateListeners.iterator();
                    while (it.hasNext()) {
                        ((SourceStateListener) it.next()).onWeCarFlowTabChanged(MediaCenterConstant.getAudioSourceEnum(audioSource), MediaCenterConstant.getAppSourceEnum(appSource));
                    }
                }
            });
        }

        @Override // com.geely.lib.oneosapi.mediacenter.listener.ISourceStateListener.Stub, android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            try {
                return super.onTransact(code, data, reply, flags);
            } catch (RuntimeException e) {
                Log.e(MediaCenterManager.TAG, "onTransact: ", e);
                return false;
            }
        }
    }

    public void runHeartbeatPacket() {
        IMediaCenter iMediaCenter = this.mService;
        if (iMediaCenter != null) {
            try {
                iMediaCenter.setHeartbeatPacket(new IHeartbeatPacket.Stub() { // from class: com.geely.lib.oneosapi.mediacenter.MediaCenterManager.1
                    @Override // com.geely.lib.oneosapi.mediacenter.IHeartbeatPacket
                    public String getPackageName() throws RemoteException {
                        return MediaCenterManager.this.mContext.getPackageName();
                    }
                });
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void setPsdMode(boolean enable) {
        this.mPsdMode = enable;
    }

    public boolean isPsdMode() {
        return this.mPsdMode;
    }

    public boolean isPsdBtAudioSource(MediaCenterConstant.AudioSource audioSource, MediaCenterConstant.AppSource appSource) {
        IMediaCenter iMediaCenter = this.mService;
        if (iMediaCenter == null) {
            return false;
        }
        try {
            return iMediaCenter.isPsdBtAudioSource(audioSource.ordinal(), appSource.ordinal());
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isPsdBtAudioSource(MediaCenterConstant.AudioSource audioSource) {
        return isPsdBtAudioSource(audioSource, MediaCenterConstant.AppSource.UNKNOWN);
    }

    public void setPsdBtAudioSource(MediaCenterConstant.AudioSource audioSource, MediaCenterConstant.AppSource appSource, boolean enable) {
        IMediaCenter iMediaCenter = this.mService;
        if (iMediaCenter != null) {
            try {
                iMediaCenter.setPsdBtAudioSource(audioSource.ordinal(), appSource.ordinal(), enable);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void setPsdBtAudioSource(MediaCenterConstant.AudioSource audioSource, boolean enable) {
        setPsdBtAudioSource(audioSource, MediaCenterConstant.AppSource.UNKNOWN, enable);
    }

    public MediaCenterManager getPsdMediaCenter() {
        if (this.mPsdMediaCenterManager == null) {
            this.mPsdMediaCenterManager = new MediaCenterManager(this.mContext, this.mService.asBinder());
        }
        this.mPsdMediaCenterManager.setPsdMode(true);
        return this.mPsdMediaCenterManager;
    }

    public void setPsdBtChannel(MediaCenterConstant.AppSource app, boolean enable) {
        IMediaCenter iMediaCenter = this.mService;
        if (iMediaCenter != null) {
            try {
                iMediaCenter.setPsdBtChannel(app.ordinal(), enable);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isPsdBtDeviceConnected() {
        IMediaCenter iMediaCenter = this.mService;
        if (iMediaCenter == null) {
            return false;
        }
        try {
            return iMediaCenter.isPsdBtDeviceConnected();
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    public MediaCenterConstant.AppSource getFocusedAppSource() {
        int focusedAppSource;
        if (this.mService != null) {
            try {
                if (isPsdMode()) {
                    focusedAppSource = this.mService.getPsdFocusedAppSource();
                } else {
                    focusedAppSource = this.mService.getFocusedAppSource();
                }
                return MediaCenterConstant.getAppSourceEnum(focusedAppSource);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return MediaCenterConstant.AppSource.UNKNOWN;
    }

    public MediaCenterConstant.AudioSource getFocusedAudioSource() {
        int focusedAudioSource;
        if (this.mService != null) {
            try {
                if (isPsdMode()) {
                    focusedAudioSource = this.mService.getPsdFocusedAudioSource();
                } else {
                    focusedAudioSource = this.mService.getFocusedAudioSource();
                }
                return MediaCenterConstant.getAudioSourceEnum(focusedAudioSource);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return MediaCenterConstant.AudioSource.AUDIO_SOURCE_UNKNOWN;
    }

    public String getAppPackageName(MediaCenterConstant.AudioSource audioSource, MediaCenterConstant.AppSource appSource) {
        IMediaCenter iMediaCenter = this.mService;
        if (iMediaCenter == null) {
            return null;
        }
        try {
            return iMediaCenter.getAppPackageName(audioSource.ordinal(), appSource.ordinal());
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void setRebootPlay(MediaCenterConstant.RebootPlay rebootPlay) {
        if (this.mService != null) {
            try {
                Log.d(TAG, "setRebootPlay() called with : value = [" + rebootPlay.getValue() + "] by " + this.mContext.getPackageName());
                this.mService.setRebootPlay(rebootPlay.getValue());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public MediaCenterConstant.RebootPlay getRebootPlay() {
        if (this.mService != null) {
            try {
                Log.d(TAG, "getRebootPlay() called by " + this.mContext.getPackageName());
                return MediaCenterConstant.getRebootPlay(this.mService.getRebootPlay());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return MediaCenterConstant.RebootPlay.OFF;
    }

    public void addRebootPlayChangeListener(RebootPlayChangeListener listener) {
        if (listener == null || this.mRebootPlayChangeListeners.contains(listener)) {
            return;
        }
        this.mRebootPlayChangeListeners.add(listener);
    }

    public void removeRebootPlayChangeListener(RebootPlayChangeListener listener) {
        if (listener != null) {
            this.mRebootPlayChangeListeners.remove(listener);
        }
    }

    protected class RebootPlayChangeListenerImpl extends IRebootPlayChangeListener.Stub {
        protected RebootPlayChangeListenerImpl() {
        }

        @Override // com.geely.lib.oneosapi.mediacenter.listener.IRebootPlayChangeListener
        public void onRebootPlayChanged(final int rebootPlayValue) throws RemoteException {
            MediaCenterManager.this.mHandler.post(new Runnable() { // from class: com.geely.lib.oneosapi.mediacenter.MediaCenterManager.RebootPlayChangeListenerImpl.1
                @Override // java.lang.Runnable
                public void run() {
                    Iterator it = MediaCenterManager.this.mRebootPlayChangeListeners.iterator();
                    while (it.hasNext()) {
                        ((RebootPlayChangeListener) it.next()).onRebootPlayChanged(MediaCenterConstant.getRebootPlay(rebootPlayValue));
                    }
                }
            });
        }
    }
}