package com.geely.lib.oneosapi.mediacenter.manager;

import android.content.Context;
import com.geely.lib.oneosapi.mediacenter.IMusicManager;
import com.geely.lib.oneosapi.mediacenter.MediaCenterManager;
import com.geely.lib.oneosapi.mediacenter.base.BaseMusicManager;
import com.geely.lib.oneosapi.mediacenter.constant.MediaCenterConstant;
import com.geely.lib.oneosapi.mediacenter.listener.CPAADeviceStateListener;

/* loaded from: classes.dex */
public class CPAAMusicManager extends BaseMusicManager {
    private final int mAudioSource;

    public CPAAMusicManager(Context context, IMusicManager service, MediaCenterManager mediaCenterManager) {
        super(context, service, mediaCenterManager);
        this.mAudioSource = MediaCenterConstant.AudioSource.AUDIO_SOURCE_CPAA.ordinal();
    }

    @Override // com.geely.lib.oneosapi.mediacenter.base.BaseMusicManager
    public int getAudioSource() {
        return this.mAudioSource;
    }

    public void requestAudioSource() {
        this.mMediaCenterManager.requestAudioSource(MediaCenterConstant.AudioSource.AUDIO_SOURCE_CPAA);
    }

    public void addCPAADeviceStateListener(CPAADeviceStateListener listener) {
        addDeviceStateListener(listener);
    }
}