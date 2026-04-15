package ecarx.fw.api.diminteraction;

import android.net.Uri;

import java.util.List;

public class EcarxMediaInteraction {
    public void setMediaInteractionCallback(EcarxMediaInteraction.EcarxMediaInteractionCallback v) {
    }

    public void unsetMediaInteractionCallback(EcarxMediaInteraction.EcarxMediaInteractionCallback v) {
    }

    public void updateCurrentProgress(long v) {
    }

    public void updateCurrentSourceType(int v) {
    }

    public void updateMediaMuteState(int v) {
    }

    public void updateMediaSourceTypeList(int[] v) {
    }

    public void updatePlaybackInfo(EcarxMediaInteraction.EcarxPlaybackInfo v) {
    }

    public void updatePlaylist(int s, List<?> v) {
    }

    public interface EcarxPlaybackInfo {
        String getUUID();

        void setUUID(String uuid);

        String getTitle();

        void setTitle(String title);

        String getArtist();

        void setArtist(String artist);

        String getAlbum();

        void setAlbum(String album);

        String getRadioFrequency();

        void setRadioFrequency(String radioFrequency);

        String getRadioStationName();

        void setRadioStationName(String radioStationName);

        long getDuration();

        void setDuration(long duration);

        int getPlayingItemPositionInQueue();

        void setPlayingItemPositionInQueue(int position);

        int getSourceType();

        void setSourceType(int sourceType);

        Uri getMediaPath();

        void setMediaPath(Uri mediaPath);

        int getPlaybackStatus();

        void setPlaybackStatus(int playbackStatus);

        Uri getLyric();

        void setLyric(Uri lyric);

        String getLyricContent();

        void setLyricContent(String lyricContent);

        String getCurrentLyricSentence();

        void setCurrentLyricSentence(String currentLyricSentence);

        Uri getPreviousArtwork();

        void setPreviousArtwork(Uri previousArtwork);

        Uri getArtwork();

        void setArtwork(Uri artwork);

        Uri getNextArtwork();

        void setNextArtwork(Uri nextArtwork);

        int getLoopMode();

        void setLoopMode(int loopMode);

        int getRadioMode();

        void setRadioMode(int radioMode);

        int getFavoriteState();

        void setFavoriteState(int favoriteState);
    }

    public interface EcarxMediaInteractionCallback {
        void onMediaHighlighted(EcarxMedia ecarxMedia);

        void onMediaSelected(EcarxMedia ecarxMedia);

        void onSourceSelected(int sourceId);

        void onUpdateMediaStatusRequest(int status);
    }

    public interface EcarxMedia {
        String getUUID();

        void setUUID(String uuid);

        String getTitle();

        void setTitle(String title);

        String getArtist();

        void setArtist(String artist);

        String getAlbum();

        void setAlbum(String album);

        String getRadioFrequency();

        void setRadioFrequency(String radioFrequency);

        String getRadioStationName();

        void setRadioStationName(String radioStationName);

        long getDuration();

        void setDuration(long duration);

        int getPlayingItemPositionInQueue();

        void setPlayingItemPositionInQueue(int position);

        int getSourceType();

        void setSourceType(int sourceType);

        Uri getMediaPath();

        void setMediaPath(Uri mediaPath);

        Uri getLyric();

        void setLyric(Uri lyric);

        String getLyricContent();

        void setLyricContent(String lyricContent);

        Uri getArtwork();

        void setArtwork(Uri artwork);

        int getFavoriteState();

        void setFavoriteState(int favoriteState);
    }
}
