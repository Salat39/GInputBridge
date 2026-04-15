package com.geely.lib.oneosapi.mediacenter.manager;

import android.content.Context;
import com.geely.lib.oneosapi.mediacenter.IRadioManager;
import com.geely.lib.oneosapi.mediacenter.MediaCenterManager;
import com.geely.lib.oneosapi.mediacenter.base.BaseRadioManager;
import com.geely.lib.oneosapi.mediacenter.constant.MediaCenterConstant;

/* loaded from: classes.dex */
public class RadioManager extends BaseRadioManager {
    public RadioManager(Context context, IRadioManager service, MediaCenterManager mediaCenterManager) {
        super(context, service, mediaCenterManager);
    }

    public void requestAudioSource() {
        this.mMediaCenterManager.requestAudioSource(MediaCenterConstant.AudioSource.AUDIO_SOURCE_RADIO);
    }
}