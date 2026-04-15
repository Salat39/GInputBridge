package com.geely.lib.oneosapi.mediacenter.listener;

import com.geely.lib.oneosapi.mediacenter.bean.MediaData;
import com.geely.lib.oneosapi.mediacenter.constant.MediaCenterConstant;
import java.util.List;

/* loaded from: classes.dex */
public interface MusicStateListener {
    void onFavorStateChanged(MediaCenterConstant.AudioSource source, MediaData mediaData);

    void onLrcLoad(MediaCenterConstant.AudioSource source, String lrc, long time);

    void onMediaDataChanged(MediaCenterConstant.AudioSource source, MediaData mediaData);

    void onPlayListChanged(MediaCenterConstant.AudioSource source, List<MediaData> list);

    void onPlayModeChange(MediaCenterConstant.AudioSource source, MediaCenterConstant.PlayMode mode);

    void onPlayPositionChanged(MediaCenterConstant.AudioSource source, long current, long total);

    void onPlayStateChanged(MediaCenterConstant.AudioSource source, MediaCenterConstant.PlayState state);
}