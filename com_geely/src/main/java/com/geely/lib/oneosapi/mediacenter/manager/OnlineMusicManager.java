package com.geely.lib.oneosapi.mediacenter.manager;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import com.geely.lib.oneosapi.mediacenter.IMusicManager;
import com.geely.lib.oneosapi.mediacenter.MediaCenterManager;
import com.geely.lib.oneosapi.mediacenter.base.BaseMusicManager;
import com.geely.lib.oneosapi.mediacenter.bean.MediaData;
import com.geely.lib.oneosapi.mediacenter.bean.SearchBean;
import com.geely.lib.oneosapi.mediacenter.callback.ContentCallback;
import com.geely.lib.oneosapi.mediacenter.callback.MusicQueryCallback;
import com.geely.lib.oneosapi.mediacenter.callback.SearchSongCallback;
import com.geely.lib.oneosapi.mediacenter.callback.UserInfoCallback;
import com.geely.lib.oneosapi.mediacenter.constant.MediaCenterConstant;
import com.geely.lib.oneosapi.mediacenter.listener.OnlineDeviceStateListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;

/* loaded from: classes.dex */
public class OnlineMusicManager extends BaseMusicManager {
    public static final String CHANNEL_BOOK = "channel_book";
    public static final String CHANNEL_BROADCAST = "channel_broadcast";
    public static final String CHANNEL_MUSIC = "channel_music";
    public static final String CHANNEL_NEWS = "channel_news";
    public static final String CHANNEL_RADIO = "channel_radio";
    private final int mAudioSource;

    @Deprecated
    public void getOnlineUserInfoAsync(MediaCenterConstant.AppSource app) {
    }

    public OnlineMusicManager(Context context, IMusicManager service, MediaCenterManager mediaCenterManager) {
        super(context, service, mediaCenterManager);
        this.mAudioSource = MediaCenterConstant.AudioSource.AUDIO_SOURCE_ONLINE.ordinal();
    }

    @Override // com.geely.lib.oneosapi.mediacenter.base.BaseMusicManager
    public int getAudioSource() {
        return this.mAudioSource;
    }

    public void addOnlineDeviceStateListener(OnlineDeviceStateListener listener) {
        addDeviceStateListener(listener);
    }

    public void getOnlineUserInfoAsync(MediaCenterConstant.AppSource app, UserInfoCallback callback) {
        if (this.mService != null) {
            try {
                this.mService.getOnlineUserInfoAsync(getAudioSource(), app.ordinal(), callback.getCallbackImpl());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    @Deprecated
    public void searchSongByName(String songName) {
        if (this.mService != null) {
            try {
                this.mService.searchSongByName(getAudioSource(), songName);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void searchSongByNameAsync(String songName, SearchSongCallback callback) {
        if (this.mService != null) {
            try {
                this.mService.searchSongByNameAsync(getAudioSource(), songName, callback.getCallbackImpl());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void playSearchSongByNameItem(String songName, int index) {
        if (this.mService != null) {
            try {
                this.mService.playSearchSongByNameItem(getAudioSource(), songName, index);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void openFavor() {
        if (this.mService != null) {
            try {
                this.mService.openFavor(getAudioSource());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void closeFavor() {
        sendCommand(MediaCenterConstant.Command.CLOSE_FAVOR, null, null);
    }

    public void openHistory() {
        if (this.mService != null) {
            try {
                this.mService.openHistory(getAudioSource());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void getContent(String contentId, ContentCallback callback) {
        if (this.mService != null) {
            try {
                this.mService.getContent(getAudioSource(), contentId, callback.getCallbackImpl());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void playContent(int position, String content, int contentCode, boolean foreground) {
        if (this.mService != null) {
            try {
                this.mService.playContent(getAudioSource(), position, content, contentCode, foreground);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public List<MediaCenterConstant.AppSource> getRecentlyAppSource() {
        ArrayList arrayList = new ArrayList();
        if (this.mService != null) {
            try {
                List<String> recentlyAppSource = this.mService.getRecentlyAppSource(getAudioSource());
                if (recentlyAppSource != null) {
                    recentlyAppSource.forEach(new Consumer() { // from class: com.geely.lib.oneosapi.mediacenter.manager.-$$Lambda$OnlineMusicManager$D5ePv3NjowBDQYzMJGzGSapMSGU

                        @Override // java.util.function.Consumer
                        public final void accept(Object obj) {

                        }
                    });
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public void requestAudioSource() {
        this.mMediaCenterManager.requestAudioSource(MediaCenterConstant.AudioSource.AUDIO_SOURCE_ONLINE);
    }

    public void requestAudioSource(MediaCenterConstant.AppSource appSource) {
        this.mMediaCenterManager.requestAudioSource(MediaCenterConstant.AudioSource.AUDIO_SOURCE_ONLINE, appSource);
    }

    public void searchMediaForVR(String json) {
        if (this.mService != null) {
            try {
                this.mService.searchMediaForVR(getAudioSource(), json);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void openPlayList() {
        if (this.mService != null) {
            try {
                this.mService.openPlayList(getAudioSource());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void playFavor() {
        if (this.mService != null) {
            try {
                this.mService.playFavor(getAudioSource());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void startAppTab(String tab) {
        if (this.mService != null) {
            try {
                this.mService.startAppTab(getAudioSource(), tab);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void openLoginPage() {
        if (this.mService != null) {
            try {
                this.mService.openLoginPage(getAudioSource());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void replay() {
        if (this.mService != null) {
            try {
                this.mService.replay(getAudioSource());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void openLrcPage() {
        if (this.mService != null) {
            try {
                this.mService.openLrcPage(getAudioSource());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    @Deprecated
    public void semanticSearch(String emotion, String theme, String style, String scene) {
        if (this.mService != null) {
            try {
                this.mService.semanticSearch(getAudioSource(), emotion, theme, style, scene);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    @Deprecated
    public void semanticSearch(String type, HashMap<String, List<String>> map, MusicQueryCallback callback) {
        if (this.mService != null) {
            SearchBean searchBean = new SearchBean();
            searchBean.semantic.domain = type;
            if (type.equals("fm")) {
                searchBean.semantic.query = SearchBean.QUERY_VALUE_FM;
            } else {
                searchBean.semantic.query = SearchBean.QUERY_VALUE_MUSIC;
            }
            if (!map.isEmpty()) {
                ArrayList arrayList = new ArrayList();
                for (String str : map.keySet()) {
                    List<String> list = map.get(str);
                    if (list != null && !list.isEmpty()) {
                        SearchBean.Slot slot = new SearchBean.Slot();
                        slot.type = str;
                        slot.values = getValueList(list);
                        arrayList.add(slot);
                    }
                }
                searchBean.semantic.slots = arrayList;
            }
            String str2 = null;
            try {
                this.mService.semanticSearchAsync(getAudioSource(), str2, callback.getCallbackImpl());
            } catch (RemoteException e2) {
                e2.printStackTrace();
            }
        }
    }

    public void semanticSearch(String type, List<String> typeValues, String song, boolean autoPlay, boolean forceForeground, String queryText, MusicQueryCallback callback) {
        if (this.mService != null) {
            try {
                this.mService.semanticSearchAndPlay(getAudioSource(), type, typeValues, song, autoPlay, forceForeground, queryText, callback.getCallbackImpl());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    private List<SearchBean.Text> getValueList(List<String> value) {
        ArrayList arrayList = new ArrayList();
        for (String str : value) {
            SearchBean.Text text = new SearchBean.Text();
            text.text = str;
            arrayList.add(text);
        }
        return arrayList;
    }

    public void switchSourceQuality(int quality) {
        Bundle bundle = new Bundle();
        bundle.putInt(MediaCenterConstant.CommandArgsKey.QUALITY, quality);
        sendCommand(MediaCenterConstant.Command.SWITCH_SOURCE_QUALITY, bundle, null);
    }

    public int onUIWordingTriggered(String words) {
        if (this.mService == null) {
            return -1;
        }
        try {
            return this.mService.onUIWordingTriggered(getAudioSource(), words);
        } catch (RemoteException e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override // com.geely.lib.oneosapi.mediacenter.base.BaseMusicManager
    public MediaData getCurrentMediaData() {
        return getCurrentMediaData(MediaCenterConstant.AppSource.UNKNOWN);
    }

    public MediaData getCurrentMediaData(MediaCenterConstant.AppSource appSource) {
        Bundle bundle = new Bundle();
        bundle.putBoolean(MediaCenterConstant.CommandArgsKey.IS_PSD_MODE, this.mMediaCenterManager.isPsdMode());
        bundle.putInt(MediaCenterConstant.CommandArgsKey.APP_SOURCE, appSource.ordinal());
        Bundle currentMediaBundle = getCurrentMediaBundle(MediaCenterConstant.MediaBoundKey.MEDIA_DATA, bundle, null);
        currentMediaBundle.setClassLoader(MediaData.class.getClassLoader());
        return (MediaData) currentMediaBundle.getParcelable(MediaCenterConstant.MediaBoundKey.MEDIA_DATA);
    }

    @Override // com.geely.lib.oneosapi.mediacenter.base.BaseMusicManager
    public MediaCenterConstant.PlayState getCurrentPlayState() {
        return getCurrentPlayState(MediaCenterConstant.AppSource.UNKNOWN);
    }

    public MediaCenterConstant.PlayState getCurrentPlayState(MediaCenterConstant.AppSource appSource) {
        Bundle bundle = new Bundle();
        bundle.putBoolean(MediaCenterConstant.CommandArgsKey.IS_PSD_MODE, this.mMediaCenterManager.isPsdMode());
        bundle.putInt(MediaCenterConstant.CommandArgsKey.APP_SOURCE, appSource.ordinal());
        return MediaCenterConstant.getPlayStateEnum(getCurrentMediaBundle(MediaCenterConstant.MediaBoundKey.PLAY_STATE, bundle, null).getInt(MediaCenterConstant.MediaBoundKey.PLAY_STATE, -1));
    }

    @Override // com.geely.lib.oneosapi.mediacenter.base.BaseMusicManager
    public long getCurrentPosition() {
        return getCurrentPosition(MediaCenterConstant.AppSource.UNKNOWN);
    }

    public long getCurrentPosition(MediaCenterConstant.AppSource appSource) {
        Bundle bundle = new Bundle();
        bundle.putBoolean(MediaCenterConstant.CommandArgsKey.IS_PSD_MODE, this.mMediaCenterManager.isPsdMode());
        bundle.putInt(MediaCenterConstant.CommandArgsKey.APP_SOURCE, appSource.ordinal());
        return getCurrentMediaBundle(MediaCenterConstant.MediaBoundKey.PLAY_POSITION, bundle, null).getLong(MediaCenterConstant.MediaBoundKey.PLAY_POSITION, 0L);
    }

    @Override // com.geely.lib.oneosapi.mediacenter.base.BaseMusicManager
    public void play() {
        play(MediaCenterConstant.AppSource.UNKNOWN);
    }

    public void play(MediaCenterConstant.AppSource appSource) {
        Bundle bundle = new Bundle();
        bundle.putBoolean(MediaCenterConstant.CommandArgsKey.IS_PSD_MODE, this.mMediaCenterManager.isPsdMode());
        bundle.putInt(MediaCenterConstant.CommandArgsKey.APP_SOURCE, appSource.ordinal());
        sendCommand("play", bundle, null);
    }

    @Override // com.geely.lib.oneosapi.mediacenter.base.BaseMusicManager
    public void pause() {
        pause(MediaCenterConstant.AppSource.UNKNOWN);
    }

    public void pause(MediaCenterConstant.AppSource appSource) {
        Bundle bundle = new Bundle();
        bundle.putBoolean(MediaCenterConstant.CommandArgsKey.IS_PSD_MODE, this.mMediaCenterManager.isPsdMode());
        bundle.putInt(MediaCenterConstant.CommandArgsKey.APP_SOURCE, appSource.ordinal());
        sendCommand(MediaCenterConstant.Command.PAUSE, bundle, null);
    }

    @Override // com.geely.lib.oneosapi.mediacenter.base.BaseMusicManager
    public void prev() {
        prev(MediaCenterConstant.AppSource.UNKNOWN);
    }

    public void prev(MediaCenterConstant.AppSource appSource) {
        Bundle bundle = new Bundle();
        bundle.putBoolean(MediaCenterConstant.CommandArgsKey.IS_PSD_MODE, this.mMediaCenterManager.isPsdMode());
        bundle.putInt(MediaCenterConstant.CommandArgsKey.APP_SOURCE, appSource.ordinal());
        sendCommand(MediaCenterConstant.Command.PREV, bundle, null);
    }

    @Override // com.geely.lib.oneosapi.mediacenter.base.BaseMusicManager
    public void next() {
        next(MediaCenterConstant.AppSource.UNKNOWN);
    }

    public void next(MediaCenterConstant.AppSource appSource) {
        Bundle bundle = new Bundle();
        bundle.putBoolean(MediaCenterConstant.CommandArgsKey.IS_PSD_MODE, this.mMediaCenterManager.isPsdMode());
        bundle.putInt(MediaCenterConstant.CommandArgsKey.APP_SOURCE, appSource.ordinal());
        sendCommand(MediaCenterConstant.Command.NEXT, bundle, null);
    }

    @Override // com.geely.lib.oneosapi.mediacenter.base.BaseMusicManager
    public void cancelFavor(MediaData mediaData) {
        cancelFavor(mediaData, MediaCenterConstant.AppSource.UNKNOWN);
    }

    public void cancelFavor(MediaData mediaData, MediaCenterConstant.AppSource appSource) {
        Bundle bundle = new Bundle();
        bundle.putBoolean(MediaCenterConstant.CommandArgsKey.IS_PSD_MODE, this.mMediaCenterManager.isPsdMode());
        bundle.putInt(MediaCenterConstant.CommandArgsKey.APP_SOURCE, appSource.ordinal());
        sendCommand(MediaCenterConstant.Command.CANCEL_FAVOR, bundle, null);
    }

    @Override // com.geely.lib.oneosapi.mediacenter.base.BaseMusicManager
    public void addFavor(MediaData mediaData) {
        addFavor(mediaData, MediaCenterConstant.AppSource.UNKNOWN);
    }

    public void addFavor(MediaData mediaData, MediaCenterConstant.AppSource appSource) {
        Bundle bundle = new Bundle();
        bundle.putBoolean(MediaCenterConstant.CommandArgsKey.IS_PSD_MODE, this.mMediaCenterManager.isPsdMode());
        bundle.putInt(MediaCenterConstant.CommandArgsKey.APP_SOURCE, appSource.ordinal());
        sendCommand(MediaCenterConstant.Command.ADD_FAVOR, bundle, null);
    }

    public void openHistoryList(int classicType, boolean isAutoPlay) {
        Bundle bundle = new Bundle();
        bundle.putBoolean(MediaCenterConstant.CommandArgsKey.IS_AUTO_PLAY, isAutoPlay);
        bundle.putInt(MediaCenterConstant.CommandArgsKey.CLASSIC_TYPE, classicType);
        sendCommand(MediaCenterConstant.Command.OPEN_HISTORY_LIST, bundle, null);
    }

    public int isSupportChangeMode(MediaCenterConstant.PlayMode mode) {
        if (this.mService == null) {
            return 0;
        }
        try {
            return this.mService.isSupportChangeMode(getAudioSource(), mode.ordinal());
        } catch (RemoteException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public boolean isAgreedUserProtocol() {
        if (this.mService == null) {
            return false;
        }
        try {
            return this.mService.isAgreedUserProtocol(getAudioSource());
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isMusicQualitySwitch(int quality) {
        if (this.mService == null) {
            return false;
        }
        try {
            return this.mService.isMusicQualitySwitch(getAudioSource(), quality);
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    public int notifyVrStatusNotifierStatus(int value) {
        if (this.mService == null) {
            return 0;
        }
        try {
            return this.mService.notifyVrStatusNotifierStatus(getAudioSource(), value);
        } catch (RemoteException e) {
            e.printStackTrace();
            return 0;
        }
    }
}