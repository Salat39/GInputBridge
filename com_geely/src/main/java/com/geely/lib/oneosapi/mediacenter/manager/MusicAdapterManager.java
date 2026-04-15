package com.geely.lib.oneosapi.mediacenter.manager;

import android.content.Context;
import android.util.Log;
import com.geely.lib.oneosapi.mediacenter.MediaCenterManager;
import com.geely.lib.oneosapi.mediacenter.base.BaseMusicManager;
import com.geely.lib.oneosapi.mediacenter.bean.MediaData;
import com.geely.lib.oneosapi.mediacenter.callback.MusicListCallback;
import com.geely.lib.oneosapi.mediacenter.constant.MediaCenterConstant;
import com.geely.lib.oneosapi.mediacenter.listener.MediaStateListener;
import com.geely.lib.oneosapi.mediacenter.listener.MusicStateListener;
import com.geely.lib.oneosapi.mediacenter.listener.SourceStateListener;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.BiConsumer;

/* loaded from: classes.dex */
public class MusicAdapterManager {
    public static final int FAILED = 0;
    public static final int SUCCESS = 1;
    private static final String TAG = "MusicAdapterManager";
    public static final int UNSUPPORTED = -1;
    protected Context mContext;
    protected MediaCenterManager mMediaCenterManager;
    private final CopyOnWriteArrayList<MusicStateListener> mMusicStateListeners = new CopyOnWriteArrayList<>();
    private final CopyOnWriteArrayList<SourceStateListener> mSourceStateListeners = new CopyOnWriteArrayList<>();
    private final SourceStateListener mSourceStateListenerImpl = new SourceStateListener() { // from class: com.geely.lib.oneosapi.mediacenter.manager.MusicAdapterManager.1

        @Override // com.geely.lib.oneosapi.mediacenter.listener.SourceStateListener
        public void onSourceChanged(MediaCenterConstant.AudioSource source, MediaCenterConstant.AppSource appSource) {
            Iterator it = MusicAdapterManager.this.mSourceStateListeners.iterator();
            while (it.hasNext()) {
                ((SourceStateListener) it.next()).onSourceChanged(source, appSource);
            }
        }
    };
    private final MusicStateListenerImpl mMusicStateListenerImpl = new MusicStateListenerImpl();

    /* renamed from: com.geely.lib.oneosapi.mediacenter.manager.MusicAdapterManager$1 */
    class C08911 implements SourceStateListener {
        C08911() {
        }

        @Override // com.geely.lib.oneosapi.mediacenter.listener.SourceStateListener
        public void onSourceChanged(MediaCenterConstant.AudioSource source, MediaCenterConstant.AppSource appSource) {
            Iterator it = MusicAdapterManager.this.mSourceStateListeners.iterator();
            while (it.hasNext()) {
                ((SourceStateListener) it.next()).onSourceChanged(source, appSource);
            }
        }
    }

    public MusicAdapterManager(Context context, MediaCenterManager mediaCenterManager) {
        this.mContext = context;
        this.mMediaCenterManager = mediaCenterManager;
        mediaCenterManager.addSourceStateListener(this.mSourceStateListenerImpl);
        mediaCenterManager.getMusicManagerMap().forEach(new BiConsumer() { // from class: com.geely.lib.oneosapi.mediacenter.manager.-$$Lambda$MusicAdapterManager$cUF2l4e4Gzb4AtYRKyjXBiQkMe8

            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                MusicAdapterManager.this.lambda$new$0$MusicAdapterManager((MediaCenterConstant.AudioSource) obj, (BaseMusicManager) obj2);
            }
        });
    }

    public /* synthetic */ void lambda$new$0$MusicAdapterManager(MediaCenterConstant.AudioSource audioSource, BaseMusicManager baseMusicManager) {
        baseMusicManager.addMusicStateListener(this.mMusicStateListenerImpl);
    }

    @Deprecated
    public MediaCenterConstant.AudioSource getCurrentSource() {
        return this.mMediaCenterManager.getCurrentAudioSource();
    }

    public MediaCenterConstant.AudioSource getCurrentAudioSource() {
        MediaCenterConstant.AudioSource currentAudioSource = this.mMediaCenterManager.getCurrentAudioSource();
        Log.d(TAG, "getCurrentAudioSource() returned: " + currentAudioSource);
        return currentAudioSource;
    }

    public MediaCenterConstant.AppSource getCurrentAppSource() {
        MediaCenterConstant.AppSource currentAppSource = this.mMediaCenterManager.getCurrentAppSource();
        Log.d(TAG, "getCurrentAppSource() returned: " + currentAppSource);
        return currentAppSource;
    }

    /* renamed from: com.geely.lib.oneosapi.mediacenter.manager.MusicAdapterManager$2 */
    static /* synthetic */ class C08922 {

        /* renamed from: $SwitchMap$com$geely$lib$oneosapi$mediacenter$constant$MediaCenterConstant$AudioSource */
        static final /* synthetic */ int[] f76xcbf1b1f7;

        static {
            int[] iArr = new int[MediaCenterConstant.AudioSource.values().length];
            f76xcbf1b1f7 = iArr;
            try {
                iArr[MediaCenterConstant.AudioSource.AUDIO_SOURCE_UNKNOWN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f76xcbf1b1f7[MediaCenterConstant.AudioSource.AUDIO_SOURCE_USB.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f76xcbf1b1f7[MediaCenterConstant.AudioSource.AUDIO_SOURCE_ONLINE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f76xcbf1b1f7[MediaCenterConstant.AudioSource.AUDIO_SOURCE_YUNTING.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f76xcbf1b1f7[MediaCenterConstant.AudioSource.AUDIO_SOURCE_BT.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f76xcbf1b1f7[MediaCenterConstant.AudioSource.AUDIO_SOURCE_CPAA.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f76xcbf1b1f7[MediaCenterConstant.AudioSource.AUDIO_SOURCE_RADIO.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    public int playItem(MediaData mediaData) {
        MediaCenterConstant.AudioSource currentAudioSource = getCurrentAudioSource();
        int i = C08922.f76xcbf1b1f7[currentAudioSource.ordinal()];
        if (i == 1) {
            return 0;
        }
        if (i != 2 && i != 3 && i != 4) {
            return -1;
        }
        this.mMediaCenterManager.getMusicManagerMap().get(currentAudioSource).playItem(mediaData);
        return 1;
    }

    public int play() {
        MediaCenterConstant.AudioSource currentAudioSource = getCurrentAudioSource();
        switch (C08922.f76xcbf1b1f7[currentAudioSource.ordinal()]) {
            case 1:
                return 0;
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                this.mMediaCenterManager.getMusicManagerMap().get(currentAudioSource).play();
                return 1;
            default:
                return -1;
        }
    }

    public int pause() {
        MediaCenterConstant.AudioSource currentAudioSource = getCurrentAudioSource();
        switch (C08922.f76xcbf1b1f7[currentAudioSource.ordinal()]) {
            case 1:
                return 0;
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                this.mMediaCenterManager.getMusicManagerMap().get(currentAudioSource).pause();
                return 1;
            default:
                return -1;
        }
    }

    public int next() {
        MediaCenterConstant.AudioSource currentAudioSource = getCurrentAudioSource();
        switch (C08922.f76xcbf1b1f7[currentAudioSource.ordinal()]) {
            case 1:
                return 0;
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                this.mMediaCenterManager.getMusicManagerMap().get(currentAudioSource).next();
                return 1;
            default:
                return -1;
        }
    }

    public int prev() {
        MediaCenterConstant.AudioSource currentAudioSource = getCurrentAudioSource();
        switch (C08922.f76xcbf1b1f7[currentAudioSource.ordinal()]) {
            case 1:
                return 0;
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                this.mMediaCenterManager.getMusicManagerMap().get(currentAudioSource).prev();
                return 1;
            default:
                return -1;
        }
    }

    public int replay() {
        if (C08922.f76xcbf1b1f7[getCurrentAudioSource().ordinal()] != 3) {
            return -1;
        }
        this.mMediaCenterManager.getOnlineMusicManager().replay();
        return 1;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public int setPlayMode(int mode) {
        MediaCenterConstant.AudioSource currentAudioSource = getCurrentAudioSource();
        switch (C08922.f76xcbf1b1f7[currentAudioSource.ordinal()]) {
            case 1:
                return 0;
            case 2:
            case 4:
                this.mMediaCenterManager.getMusicManagerMap().get(currentAudioSource).setPlayMode(MediaCenterConstant.getPlayModeEnum(mode));
                return 1;
            case 3:
                OnlineMusicManager onlineMusicManager = (OnlineMusicManager) this.mMediaCenterManager.getMusicManagerMap().get(MediaCenterConstant.AudioSource.AUDIO_SOURCE_ONLINE);
                if (onlineMusicManager.isSupportChangeMode(MediaCenterConstant.getPlayModeEnum(mode)) != 1) {
                    return -1;
                }
                onlineMusicManager.setPlayMode(MediaCenterConstant.getPlayModeEnum(mode));
                return 1;
            case 5:
            case 6:
            case 7:
                return -1;
            default:
                return 1;
        }
    }

    public int seekTo(long time) {
        MediaCenterConstant.AudioSource currentAudioSource = getCurrentAudioSource();
        if (currentAudioSource != MediaCenterConstant.AudioSource.AUDIO_SOURCE_ONLINE) {
            return -1;
        }
        this.mMediaCenterManager.getMusicManagerMap().get(currentAudioSource).seekTo(time);
        return 1;
    }

    public int fastForward(long time) {
        MediaCenterConstant.AudioSource currentAudioSource = getCurrentAudioSource();
        if (currentAudioSource != MediaCenterConstant.AudioSource.AUDIO_SOURCE_ONLINE) {
            return -1;
        }
        this.mMediaCenterManager.getMusicManagerMap().get(currentAudioSource).fastForward(time);
        return 1;
    }

    public int fastRewind(long time) {
        MediaCenterConstant.AudioSource currentAudioSource = getCurrentAudioSource();
        if (currentAudioSource != MediaCenterConstant.AudioSource.AUDIO_SOURCE_ONLINE) {
            return -1;
        }
        this.mMediaCenterManager.getMusicManagerMap().get(currentAudioSource).fastRewind(time);
        return 1;
    }

    public MediaCenterConstant.PlayMode getPlayMode() {
        MediaCenterConstant.AudioSource currentAudioSource = getCurrentAudioSource();
        int i = C08922.f76xcbf1b1f7[currentAudioSource.ordinal()];
        if (i == 2 || i == 3 || i == 4 || i == 5 || i == 6) {
            return this.mMediaCenterManager.getMusicManagerMap().get(currentAudioSource).getPlayMode();
        }
        return null;
    }

    @Deprecated
    public List<MediaData> getPlayList() {
        MediaCenterConstant.AudioSource currentAudioSource = getCurrentAudioSource();
        int i = C08922.f76xcbf1b1f7[currentAudioSource.ordinal()];
        if (i == 2 || i == 3 || i == 4) {
            return this.mMediaCenterManager.getMusicManagerMap().get(currentAudioSource).getPlayList();
        }
        return null;
    }

    public void getPlayListAsync(MusicListCallback callback) {
        MediaCenterConstant.AudioSource currentAudioSource = getCurrentAudioSource();
        int i = C08922.f76xcbf1b1f7[currentAudioSource.ordinal()];
        if (i == 2 || i == 3 || i == 4 || i == 5) {
            this.mMediaCenterManager.getMusicManagerMap().get(currentAudioSource).getPlayListAsync(callback);
        }
    }

    public int addFavor(MediaData mediaData) {
        BaseMusicManager baseMusicManager;
        MediaData currentMediaData;
        List<MediaData> favorList;
        MediaCenterConstant.AudioSource currentAudioSource = getCurrentAudioSource();
        int i = C08922.f76xcbf1b1f7[currentAudioSource.ordinal()];
        if ((i != 3 && i != 4) || (currentMediaData = (baseMusicManager = this.mMediaCenterManager.getMusicManagerMap().get(currentAudioSource)).getCurrentMediaData()) == null || !currentMediaData.isFavorSupported) {
            return -1;
        }
        if (currentAudioSource == MediaCenterConstant.AudioSource.AUDIO_SOURCE_YUNTING && (favorList = baseMusicManager.getFavorList()) != null && favorList.size() == 20) {
            return 0;
        }
        baseMusicManager.addFavor(mediaData);
        return 1;
    }

    public boolean isCurrentMediaFavored() {
        MediaData currentMediaData;
        MediaCenterConstant.AudioSource currentAudioSource = getCurrentAudioSource();
        if (currentAudioSource != MediaCenterConstant.AudioSource.AUDIO_SOURCE_ONLINE || (currentMediaData = this.mMediaCenterManager.getMusicManagerMap().get(currentAudioSource).getCurrentMediaData()) == null) {
            return false;
        }
        return currentMediaData.isFavored;
    }

    public int cancelFavor(MediaData mediaData) {
        MediaCenterConstant.AudioSource currentAudioSource = getCurrentAudioSource();
        int i = C08922.f76xcbf1b1f7[currentAudioSource.ordinal()];
        if (i != 3 && i != 4) {
            return -1;
        }
        BaseMusicManager baseMusicManager = this.mMediaCenterManager.getMusicManagerMap().get(currentAudioSource);
        if (baseMusicManager.getCurrentMediaData() != null && !baseMusicManager.getCurrentMediaData().isFavorSupported) {
            return -1;
        }
        if (baseMusicManager.getCurrentMediaData() == null || !baseMusicManager.getCurrentMediaData().isFavored) {
            return 0;
        }
        baseMusicManager.cancelFavor(mediaData);
        return 1;
    }

    public MediaData getCurrentMediaData() {
        MediaCenterConstant.AudioSource currentAudioSource = getCurrentAudioSource();
        int i = C08922.f76xcbf1b1f7[currentAudioSource.ordinal()];
        if (i == 2 || i == 3 || i == 4 || i == 5 || i == 6) {
            return this.mMediaCenterManager.getMusicManagerMap().get(currentAudioSource).getCurrentMediaData();
        }
        return null;
    }

    public MediaCenterConstant.PlayState getCurrentPlayState() {
        MediaCenterConstant.AudioSource currentAudioSource = getCurrentAudioSource();
        int i = C08922.f76xcbf1b1f7[currentAudioSource.ordinal()];
        if (i == 2 || i == 3 || i == 4 || i == 5 || i == 6) {
            return this.mMediaCenterManager.getMusicManagerMap().get(currentAudioSource).getCurrentPlayState();
        }
        return null;
    }

    public long getCurrentPosition() {
        MediaCenterConstant.AudioSource currentAudioSource = getCurrentAudioSource();
        int i = C08922.f76xcbf1b1f7[currentAudioSource.ordinal()];
        if (i == 2 || i == 3 || i == 4 || i == 5 || i == 6) {
            return this.mMediaCenterManager.getMusicManagerMap().get(currentAudioSource).getCurrentPosition();
        }
        return 0L;
    }

    public int openHistory() {
        if (C08922.f76xcbf1b1f7[getCurrentAudioSource().ordinal()] != 3) {
            return -1;
        }
        this.mMediaCenterManager.getOnlineMusicManager().openHistory();
        return 1;
    }

    public int openHistoryList(int classicType, boolean isAutoPlay) {
        if (C08922.f76xcbf1b1f7[getCurrentAudioSource().ordinal()] != 3) {
            return -1;
        }
        this.mMediaCenterManager.getOnlineMusicManager().openHistoryList(classicType, isAutoPlay);
        return 1;
    }

    public int openFavor() {
        if (C08922.f76xcbf1b1f7[getCurrentAudioSource().ordinal()] != 3) {
            return -1;
        }
        this.mMediaCenterManager.getOnlineMusicManager().openFavor();
        return 1;
    }

    public int closeFavor() {
        if (C08922.f76xcbf1b1f7[getCurrentAudioSource().ordinal()] != 3) {
            return -1;
        }
        this.mMediaCenterManager.getOnlineMusicManager().closeFavor();
        return 1;
    }

    public int openPlayList() {
        if (C08922.f76xcbf1b1f7[getCurrentAudioSource().ordinal()] != 3) {
            return -1;
        }
        this.mMediaCenterManager.getOnlineMusicManager().openPlayList();
        return 1;
    }

    public int playMusicFavor() {
        if (C08922.f76xcbf1b1f7[getCurrentAudioSource().ordinal()] != 3) {
            return -1;
        }
        this.mMediaCenterManager.getOnlineMusicManager().playFavor();
        return 1;
    }

    public int startAppTab(String tab) {
        this.mMediaCenterManager.getOnlineMusicManager().startAppTab(tab);
        return 1;
    }

    public void requestAudioSource(MediaCenterConstant.AudioSource source, MediaCenterConstant.AppSource app) {
        this.mMediaCenterManager.requestAudioSource(source, app);
    }

    public void requestAudioSource(MediaCenterConstant.AudioSource source) {
        this.mMediaCenterManager.requestAudioSource(source);
    }

    public void addMusicStateListener(MusicStateListener listener) {
        if (listener == null || this.mMusicStateListeners.contains(listener)) {
            return;
        }
        this.mMusicStateListeners.add(listener);
    }

    public void addSourceStateListener(SourceStateListener listener) {
        if (listener == null || this.mSourceStateListeners.contains(listener)) {
            return;
        }
        this.mSourceStateListeners.add(listener);
    }

    private class MusicStateListenerImpl implements MusicStateListener {
        private MusicStateListenerImpl() {
        }

        /* synthetic */ MusicStateListenerImpl(MusicAdapterManager musicAdapterManager, C08911 c08911) {
            this();
        }

        @Override // com.geely.lib.oneosapi.mediacenter.listener.MusicStateListener
        public void onMediaDataChanged(MediaCenterConstant.AudioSource source, MediaData mediaData) {
            Iterator it = MusicAdapterManager.this.mMusicStateListeners.iterator();
            while (it.hasNext()) {
                ((MusicStateListener) it.next()).onMediaDataChanged(source, mediaData);
            }
        }

        @Override // com.geely.lib.oneosapi.mediacenter.listener.MusicStateListener
        public void onPlayPositionChanged(MediaCenterConstant.AudioSource source, long current, long total) {
            Iterator it = MusicAdapterManager.this.mMusicStateListeners.iterator();
            while (it.hasNext()) {
                ((MusicStateListener) it.next()).onPlayPositionChanged(source, current, total);
            }
        }

        @Override // com.geely.lib.oneosapi.mediacenter.listener.MusicStateListener
        public void onPlayStateChanged(MediaCenterConstant.AudioSource source, MediaCenterConstant.PlayState state) {
            Iterator it = MusicAdapterManager.this.mMusicStateListeners.iterator();
            while (it.hasNext()) {
                ((MusicStateListener) it.next()).onPlayStateChanged(source, state);
            }
        }

        @Override // com.geely.lib.oneosapi.mediacenter.listener.MusicStateListener
        public void onPlayListChanged(MediaCenterConstant.AudioSource source, List<MediaData> list) {
            Iterator it = MusicAdapterManager.this.mMusicStateListeners.iterator();
            while (it.hasNext()) {
                ((MusicStateListener) it.next()).onPlayListChanged(source, list);
            }
        }

        @Override // com.geely.lib.oneosapi.mediacenter.listener.MusicStateListener
        public void onFavorStateChanged(MediaCenterConstant.AudioSource source, MediaData mediaData) {
            Iterator it = MusicAdapterManager.this.mMusicStateListeners.iterator();
            while (it.hasNext()) {
                ((MusicStateListener) it.next()).onFavorStateChanged(source, mediaData);
            }
        }

        @Override // com.geely.lib.oneosapi.mediacenter.listener.MusicStateListener
        public void onLrcLoad(MediaCenterConstant.AudioSource source, String lrc, long time) {
            Iterator it = MusicAdapterManager.this.mMusicStateListeners.iterator();
            while (it.hasNext()) {
                ((MusicStateListener) it.next()).onLrcLoad(source, lrc, time);
            }
        }

        @Override // com.geely.lib.oneosapi.mediacenter.listener.MusicStateListener
        public void onPlayModeChange(MediaCenterConstant.AudioSource source, MediaCenterConstant.PlayMode mode) {
            Iterator it = MusicAdapterManager.this.mMusicStateListeners.iterator();
            while (it.hasNext()) {
                ((MusicStateListener) it.next()).onPlayModeChange(source, mode);
            }
        }
    }

    public void removeMusicStateListener(MusicStateListener listener) {
        if (listener != null) {
            this.mMusicStateListeners.remove(listener);
        }
    }

    public void removeSourceStateListener(SourceStateListener listener) {
        if (listener != null) {
            this.mSourceStateListeners.remove(listener);
        }
    }

    public UsbMusicManager getUsbMusicManager() {
        return this.mMediaCenterManager.getUsbMusicManager();
    }

    public BtMusicManager getBtMusicManager() {
        return this.mMediaCenterManager.getBtMusicManager();
    }

    public OnlineMusicManager getOnlineMusicManager() {
        return this.mMediaCenterManager.getOnlineMusicManager();
    }

    public YunTingManager getYunTingManager() {
        return this.mMediaCenterManager.getYunTingManager();
    }

    public BaseMusicManager getMusicManager(MediaCenterConstant.AudioSource audioSource) {
        return this.mMediaCenterManager.getMusicManagerMap().get(audioSource);
    }

    public void addMediaStateListener(MediaStateListener listener) {
        this.mMediaCenterManager.getMusicManagerMap().forEach(new BiConsumer() { // from class: com.geely.lib.oneosapi.mediacenter.manager.-$$Lambda$MusicAdapterManager$KIsy0KXNCIy48oPNw2qrqWvWyqY


            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {

            }
        });
    }

    public void removeMediaStateListener(MediaStateListener listener) {
        this.mMediaCenterManager.getMusicManagerMap().forEach(new BiConsumer() { // from class: com.geely.lib.oneosapi.mediacenter.manager.-$$Lambda$MusicAdapterManager$8GCr7GB9oq_b1-dDb1y8tX8OWsA


            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {

            }
        });
    }
}