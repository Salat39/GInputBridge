package com.ecarx.xui.adaptapi.diminteraction;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import com.ecarx.xui.adaptapi.AbsCarSignal;

import java.util.List;

import ecarx.fw.api.diminteraction.EcarxMediaInteraction;

public class MediaInteraction extends AbsCarSignal implements IMediaInteraction {
    private static final String TAG = "MediaInteraction";

    @Override
    public void updateExtensionInfo(Bundle bundle) {
    }

    protected MediaInteraction(Context context) {
        super(context);
    }

    @Override
    public void updateMediaSourceTypeList(int[] iArr) {
    }

    @Override
    public void updateCurrentSourceType(int i) {
    }

    @Override
    public void updatePlaylist(int i, List<IMediaInteraction.IMedia> list) {
    }

    @Override
    public void updatePlaybackInfo(IMediaInteraction.IPlaybackInfo iPlaybackInfo) {
    }

    @Override
    public void updateCurrentProgress(long j) {
    }

    @Override
    public void updateMediaMuteState(int i) {
    }

    @Override
    public void setMediaInteractionCallback(IMediaInteraction.IMediaInteractionCallback iMediaInteractionCallback) {
    }

    @Override
    public void unsetMediaInteractionCallback(IMediaInteraction.IMediaInteractionCallback iMediaInteractionCallback) {
    }

    public class AdaptPlayInfo implements EcarxMediaInteraction.EcarxPlaybackInfo {
        String mAlbumName;
        String mArtistName;
        Uri mArtwork;
        String mCurrentLyricSentence;
        long mDuration;
        int mFavoriteState;
        int mLoopMode;
        Uri mLyric;
        String mLyricContent;
        Uri mMediaPath;
        Uri mNextArtwork;
        int mPlaybackStatus;
        int mPlayingItemPositionInQueue;
        Uri mPreviousArtwork;
        String mRadioFrequency;
        int mRadioMode;
        String mRadioStationName;
        int mSourceType;
        String mTitle;
        String mUUid;

        AdaptPlayInfo() {
        }

        public void setUUID(String str) {
            this.mUUid = str;
        }

        public String getUUID() {
            return this.mUUid;
        }

        public void setTitle(String str) {
            this.mTitle = str;
        }

        public String getTitle() {
            return this.mTitle;
        }

        public void setArtist(String str) {
            this.mArtistName = str;
        }

        public String getArtist() {
            return this.mArtistName;
        }

        public void setAlbum(String str) {
            this.mAlbumName = str;
        }

        public String getAlbum() {
            return this.mAlbumName;
        }

        public void setRadioFrequency(String str) {
            this.mRadioFrequency = str;
        }

        public String getRadioFrequency() {
            return this.mRadioFrequency;
        }

        public void setRadioStationName(String str) {
            this.mRadioStationName = str;
        }

        public String getRadioStationName() {
            return this.mRadioStationName;
        }

        public void setDuration(long j) {
            this.mDuration = j;
        }

        public long getDuration() {
            return this.mDuration;
        }

        public void setPlayingItemPositionInQueue(int i) {
            this.mPlayingItemPositionInQueue = i;
        }

        public int getPlayingItemPositionInQueue() {
            return this.mPlayingItemPositionInQueue;
        }

        public void setSourceType(int i) {
            this.mSourceType = i;
        }

        public int getSourceType() {
            return this.mSourceType;
        }

        public void setMediaPath(Uri uri) {
            this.mMediaPath = uri;
        }

        public Uri getMediaPath() {
            return this.mMediaPath;
        }

        public void setPlaybackStatus(int i) {
            this.mPlaybackStatus = i;
        }

        public int getPlaybackStatus() {
            return this.mPlaybackStatus;
        }

        public void setLyric(Uri uri) {
            this.mLyric = uri;
        }

        public Uri getLyric() {
            return this.mLyric;
        }

        public void setLyricContent(String str) {
            this.mLyricContent = str;
        }

        public String getLyricContent() {
            return this.mLyricContent;
        }

        public void setCurrentLyricSentence(String str) {
            this.mCurrentLyricSentence = str;
        }

        public String getCurrentLyricSentence() {
            return this.mCurrentLyricSentence;
        }

        public void setPreviousArtwork(Uri uri) {
            this.mPreviousArtwork = uri;
        }

        public Uri getPreviousArtwork() {
            return this.mPreviousArtwork;
        }

        public void setArtwork(Uri uri) {
            this.mArtwork = uri;
        }

        public Uri getArtwork() {
            return this.mArtwork;
        }

        public void setNextArtwork(Uri uri) {
            this.mNextArtwork = uri;
        }

        public Uri getNextArtwork() {
            return this.mNextArtwork;
        }

        public void setLoopMode(int i) {
            this.mLoopMode = i;
        }

        public int getLoopMode() {
            return this.mLoopMode;
        }

        public void setRadioMode(int i) {
            this.mRadioMode = i;
        }

        public int getRadioMode() {
            return this.mRadioMode;
        }

        public void setFavoriteState(int i) {
            this.mFavoriteState = i;
        }

        public int getFavoriteState() {
            return this.mFavoriteState;
        }
    }

    class AdaptMedia implements EcarxMediaInteraction.EcarxMedia {
        String mAlbumName;
        String mArtistName;
        Uri mArtwork;
        long mDuration;
        int mFavoriteState;
        Uri mLyric;
        String mLyricContent;
        Uri mMediaPath;
        int mPlayingItemPositionInQueue;
        String mRadioFrequency;
        String mRadioStationName;
        int mSourceType;
        String mTitle;
        String mUUid;

        AdaptMedia() {
        }

        public void setUUID(String str) {
            this.mUUid = str;
        }

        public String getUUID() {
            return this.mUUid;
        }

        public void setTitle(String str) {
            this.mTitle = str;
        }

        public String getTitle() {
            return this.mTitle;
        }

        public void setArtist(String str) {
            this.mArtistName = str;
        }

        public String getArtist() {
            return this.mArtistName;
        }

        public void setAlbum(String str) {
            this.mAlbumName = str;
        }

        public String getAlbum() {
            return this.mAlbumName;
        }

        public void setRadioFrequency(String str) {
            this.mRadioFrequency = str;
        }

        public String getRadioFrequency() {
            return this.mRadioFrequency;
        }

        public void setRadioStationName(String str) {
            this.mRadioStationName = str;
        }

        public String getRadioStationName() {
            return this.mRadioStationName;
        }

        public void setDuration(long j) {
            this.mDuration = j;
        }

        public long getDuration() {
            return this.mDuration;
        }

        public void setPlayingItemPositionInQueue(int i) {
            this.mPlayingItemPositionInQueue = i;
        }

        public int getPlayingItemPositionInQueue() {
            return this.mPlayingItemPositionInQueue;
        }

        public void setSourceType(int i) {
            this.mSourceType = i;
        }

        public int getSourceType() {
            return this.mSourceType;
        }

        public void setMediaPath(Uri uri) {
            this.mMediaPath = uri;
        }

        public Uri getMediaPath() {
            return this.mMediaPath;
        }

        public void setLyric(Uri uri) {
            this.mLyric = uri;
        }

        public Uri getLyric() {
            return this.mLyric;
        }

        public void setLyricContent(String str) {
            this.mLyricContent = str;
        }

        public String getLyricContent() {
            return this.mLyricContent;
        }

        public void setArtwork(Uri uri) {
            this.mArtwork = uri;
        }

        public Uri getArtwork() {
            return this.mArtwork;
        }

        public void setFavoriteState(int i) {
            this.mFavoriteState = i;
        }

        public int getFavoriteState() {
            return this.mFavoriteState;
        }
    }

    private AdaptMedia iMediaToEcarxMedia(IMediaInteraction.IMedia iMedia) {
        AdaptMedia adaptMedia = new AdaptMedia();
        adaptMedia.setUUID(iMedia.getUUID());
        adaptMedia.setTitle(iMedia.getTitle());
        adaptMedia.setArtist(iMedia.getArtist());
        adaptMedia.setAlbum(iMedia.getAlbum());
        adaptMedia.setRadioFrequency(iMedia.getRadioFrequency());
        adaptMedia.setRadioStationName(iMedia.getRadioStationName());
        adaptMedia.setDuration(iMedia.getDuration());
        adaptMedia.setPlayingItemPositionInQueue(iMedia.getPlayingItemPositionInQueue());
        adaptMedia.setSourceType(iMedia.getSourceType());
        adaptMedia.setMediaPath(iMedia.getMediaPath());
        adaptMedia.setLyricContent(iMedia.getLyricContent());
        adaptMedia.setLyric(iMedia.getLyric());
        adaptMedia.setArtwork(iMedia.getArtwork());
        adaptMedia.setFavoriteState(iMedia.getFavoriteState());
        return adaptMedia;
    }

    class AppMedia implements IMediaInteraction.IMedia {
        String mAlbumName;
        String mArtistName;
        Uri mArtwork;
        long mDuration;
        int mFavoriteState;
        Uri mLyric;
        String mLyricContent;
        Uri mMediaPath;
        int mPlayingItemPositionInQueue;
        String mRadioFrequency;
        String mRadioStationName;
        int mSourceType;
        String mTitle;
        String mUUid;

        AppMedia() {
        }

        public void setUUID(String str) {
            this.mUUid = str;
        }

        @Override
        public String getUUID() {
            return this.mUUid;
        }

        public void setTitle(String str) {
            this.mTitle = str;
        }

        @Override
        public String getTitle() {
            return this.mTitle;
        }

        public void setArtist(String str) {
            this.mArtistName = str;
        }

        @Override
        public String getArtist() {
            return this.mArtistName;
        }

        public void setAlbum(String str) {
            this.mAlbumName = str;
        }

        @Override
        public String getAlbum() {
            return this.mAlbumName;
        }

        public void setRadioFrequency(String str) {
            this.mRadioFrequency = str;
        }

        @Override
        public String getRadioFrequency() {
            return this.mRadioFrequency;
        }

        public void setRadioStationName(String str) {
            this.mRadioStationName = str;
        }

        @Override
        public String getRadioStationName() {
            return this.mRadioStationName;
        }

        public void setDuration(long j) {
            this.mDuration = j;
        }

        @Override
        public long getDuration() {
            return this.mDuration;
        }

        public void setPlayingItemPositionInQueue(int i) {
            this.mPlayingItemPositionInQueue = i;
        }

        @Override
        public int getPlayingItemPositionInQueue() {
            return this.mPlayingItemPositionInQueue;
        }

        public void setSourceType(int i) {
            this.mSourceType = i;
        }

        @Override
        public int getSourceType() {
            return this.mSourceType;
        }

        public void setMediaPath(Uri uri) {
            this.mMediaPath = uri;
        }

        @Override
        public Uri getMediaPath() {
            return this.mMediaPath;
        }

        public void setLyric(Uri uri) {
            this.mLyric = uri;
        }

        @Override
        public Uri getLyric() {
            return this.mLyric;
        }

        public void setLyricContent(String str) {
            this.mLyricContent = str;
        }

        @Override
        public String getLyricContent() {
            return this.mLyricContent;
        }

        public void setArtwork(Uri uri) {
            this.mArtwork = uri;
        }

        @Override
        public Uri getArtwork() {
            return this.mArtwork;
        }

        public void setFavoriteState(int i) {
            this.mFavoriteState = i;
        }

        @Override
        public int getFavoriteState() {
            return this.mFavoriteState;
        }
    }

    private AppMedia ecarxMediaToIMedia(EcarxMediaInteraction.EcarxMedia ecarxMedia) {
        return null;
    }
}
