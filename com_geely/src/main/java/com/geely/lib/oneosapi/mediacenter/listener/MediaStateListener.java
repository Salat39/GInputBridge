package com.geely.lib.oneosapi.mediacenter.listener;

import com.geely.lib.oneosapi.mediacenter.bean.MediaData;
import com.geely.lib.oneosapi.mediacenter.constant.MediaCenterConstant;
import java.util.List;

/* loaded from: classes.dex */
public interface MediaStateListener {
    void onFavorStateChanged(MediaCenterConstant.AudioSource source, MediaCenterConstant.AppSource appSource, MediaData mediaData);

    void onLrcLoad(MediaCenterConstant.AudioSource source, MediaCenterConstant.AppSource appSource, String lrc, long time);

    void onMediaDataChanged(MediaCenterConstant.AudioSource source, MediaCenterConstant.AppSource appSource, MediaData mediaData);

    void onPlayListChanged(MediaCenterConstant.AudioSource source, MediaCenterConstant.AppSource appSource, List<MediaData> list);

    void onPlayModeChange(MediaCenterConstant.AudioSource source, MediaCenterConstant.AppSource appSource, MediaCenterConstant.PlayMode mode);

    void onPlayPositionChanged(MediaCenterConstant.AudioSource source, MediaCenterConstant.AppSource appSource, long current, long total);

    void onPlayStateChanged(MediaCenterConstant.AudioSource source, MediaCenterConstant.AppSource appSource, MediaCenterConstant.PlayState state);
}