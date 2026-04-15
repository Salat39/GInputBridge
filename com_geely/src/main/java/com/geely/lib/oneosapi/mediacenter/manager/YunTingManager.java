package com.geely.lib.oneosapi.mediacenter.manager;

import android.content.Context;
import com.geely.lib.oneosapi.mediacenter.IMusicManager;
import com.geely.lib.oneosapi.mediacenter.MediaCenterManager;
import com.geely.lib.oneosapi.mediacenter.base.BaseMusicManager;
import com.geely.lib.oneosapi.mediacenter.constant.MediaCenterConstant;

/* loaded from: classes.dex */
public class YunTingManager extends BaseMusicManager {
    public YunTingManager(Context context, IMusicManager service, MediaCenterManager mediaCenterManager) {
        super(context, service, mediaCenterManager);
    }

    public void requestAudioSource() {
        this.mMediaCenterManager.requestAudioSource(MediaCenterConstant.AudioSource.AUDIO_SOURCE_YUNTING);
    }

    @Override // com.geely.lib.oneosapi.mediacenter.base.BaseMusicManager
    public int getAudioSource() {
        return MediaCenterConstant.AudioSource.AUDIO_SOURCE_YUNTING.ordinal();
    }
}