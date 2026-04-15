package com.ecarx.xui.adaptapi.diminteraction;

import android.net.Uri;
import android.os.Bundle;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

public interface IMediaInteraction {
    public static final int DATA_TYPE_GET_CURRENT_PROGRESS = 4;
    public static final int DATA_TYPE_GET_CURRENT_SOURCE_TYPE = 1;
    public static final int DATA_TYPE_GET_PLAYBACK_INFO = 3;
    public static final int DATA_TYPE_GET_PLAYLIST = 2;
    public static final int DATA_TYPE_GET_SOURCE_TYPE_LIST = 0;
    public static final int DATA_TYPE_OP_ADD_FAVORITE = 17;
    public static final int DATA_TYPE_OP_ADD_SUBSCRIPTION = 19;
    public static final int DATA_TYPE_OP_FAST_BACKWARD = 10;
    public static final int DATA_TYPE_OP_FAST_FORWARD = 9;
    public static final int DATA_TYPE_OP_LOOP_MODE_ALL = 23;
    public static final int DATA_TYPE_OP_LOOP_MODE_NEXT = 22;
    public static final int DATA_TYPE_OP_LOOP_MODE_SHUFFLE = 25;
    public static final int DATA_TYPE_OP_LOOP_MODE_SINGLE = 24;
    public static final int DATA_TYPE_OP_MUTE = 15;
    public static final int DATA_TYPE_OP_NEXT = 5;
    public static final int DATA_TYPE_OP_PAUSE = 8;
    public static final int DATA_TYPE_OP_PLAY = 7;
    public static final int DATA_TYPE_OP_PREVIEW = 6;
    public static final int DATA_TYPE_OP_RADIO_SCAN = 21;
    public static final int DATA_TYPE_OP_RM_FAVORITE = 18;
    public static final int DATA_TYPE_OP_RM_SUBSCRIPTION = 20;
    public static final int DATA_TYPE_OP_SEEK_NEXT = 12;
    public static final int DATA_TYPE_OP_SEEK_PREV = 13;
    public static final int DATA_TYPE_OP_SEEK_STOP = 14;
    public static final int DATA_TYPE_OP_STOP = 11;
    public static final int DATA_TYPE_OP_UNMUTE = 16;
    public static final String EXT_KEY_ACTION_STATE_MESSAGE = "EXT_KEY_ACTION_STATE_MESSAGE";
    public static final String EXT_KEY_ACTION_TYPE = "EXT_KEY_ACTION_TYPE";
    public static final String EXT_KEY_ADD_FAVORITE_STATES = "EXT_KEY_ADD_FAVORITE_STATES";
    public static final String EXT_KEY_MEDIA_UUID = "EXT_KEY_MEDIA_UUID";
    public static final String EXT_KEY_RADIO_SCAN_STATES = "EXT_KEY_RADIO_SCAN_STATES";

    public static final int SOURCE_TYPE_LOCAL = 0;
    public static final int SOURCE_TYPE_USB = 1;
    public static final int SOURCE_TYPE_BT = 2;
    public static final int SOURCE_TYPE_FM = 3;
    public static final int SOURCE_TYPE_AM = 4;
    public static final int SOURCE_TYPE_AUX = 5;
    public static final int SOURCE_TYPE_ONLINE = 6;
    public static final int SOURCE_TYPE_USB2 = 7;
    public static final int SOURCE_TYPE_STATION = 8;
    public static final int SOURCE_TYPE_NET_NEWS = 9;
    public static final int SOURCE_TYPE_NET_VIDEO = 10;
    public static final int SOURCE_TYPE_DAB = 11;
    public static final int SOURCE_TYPE_FAVORITE_FM = 33;
    public static final int SOURCE_TYPE_FAVORITE_AM = 34;
    public static final int SOURCE_TYPE_FAVORITE_MUSIC = 35;
    public static final int SOURCE_TYPE_AM_SCAN_LIST = 36;
    public static final int SOURCE_TYPE_FM_SCAN_LIST = 37;

    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface DataType {
    }

    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface ExtensionKey {
    }

    public interface IMedia {
        String getAlbum();

        String getArtist();

        Uri getArtwork();

        long getDuration();

        int getFavoriteState();

        Uri getLyric();

        String getLyricContent();

        Uri getMediaPath();

        int getPlayingItemPositionInQueue();

        String getRadioFrequency();

        String getRadioStationName();

        int getSourceType();

        String getTitle();

        String getUUID();
    }

    public interface IMediaInteractionCallback {
        void onMediaHighlighted(IMedia iMedia);

        void onMediaSelected(IMedia iMedia);

        void onSourceSelected(int i);

        void onUpdateMediaStatusRequest(int i);
    }

    public interface IPlaybackInfo {
        public static final int LOOP_MODE_ALL = 0;
        public static final int LOOP_MODE_SHUFFLE = 2;
        public static final int LOOP_MODE_SINGLE = 1;
        public static final int PLAYBACK_STATUS_PAUSED = 0;
        public static final int PLAYBACK_STATUS_PLAYING = 1;
        public static final int RADIO_MODE_CAROUSEL = 1;
        public static final int RADIO_MODE_PLAYING = 0;
        public static final int RADIO_MODE_SCAN = 4;
        public static final int RADIO_MODE_SEEK_NEXT = 3;
        public static final int RADIO_MODE_SEEK_PREV = 2;

        @Retention(RetentionPolicy.SOURCE)
        public @interface LoopMode {
        }

        @Retention(RetentionPolicy.SOURCE)
        public @interface PlaybackStatus {
        }

        @Retention(RetentionPolicy.SOURCE)
        public @interface RadioMode {
        }

        String getAlbum();

        String getArtist();

        Uri getArtwork();

        String getCurrentLyricSentence();

        long getDuration();

        int getFavoriteState();

        int getLoopMode();

        Uri getLyric();

        String getLyricContent();

        Uri getMediaPath();

        Uri getNextArtwork();

        int getPlaybackStatus();

        int getPlayingItemPositionInQueue();

        Uri getPreviousArtwork();

        String getRadioFrequency();

        int getRadioMode();

        String getRadioStationName();

        int getSourceType();

        String getTitle();

        String getUUID();
    }

    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface SourceType {
    }

    void setMediaInteractionCallback(IMediaInteractionCallback iMediaInteractionCallback);

    void unsetMediaInteractionCallback(IMediaInteractionCallback iMediaInteractionCallback);

    void updateCurrentProgress(long j);

    void updateCurrentSourceType(int i);

    void updateExtensionInfo(Bundle bundle);

    void updateMediaMuteState(int i);

    void updateMediaSourceTypeList(int[] iArr);

    void updatePlaybackInfo(IPlaybackInfo iPlaybackInfo);

    void updatePlaylist(int i, List<IMedia> list);
}

