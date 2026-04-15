package com.geely.lib.oneosapi.mediacenter.listener;

import com.geely.lib.oneosapi.mediacenter.bean.DeviceInfo;
import com.geely.lib.oneosapi.mediacenter.constant.MediaCenterConstant;

/* loaded from: classes.dex */
public interface QueryUsbMediaListener {
    void onMediaQueryFinished(MediaCenterConstant.AudioSource source, DeviceInfo info);

    void onMediaQueryStarted(MediaCenterConstant.AudioSource source, DeviceInfo info);
}