package com.geely.lib.oneosapi.mediacenter.listener;

import com.geely.lib.oneosapi.mediacenter.constant.MediaCenterConstant;

/* loaded from: classes.dex */
public interface SourceStateListener {
    default void onPsdBtStateChanged(boolean connected) {
    }

    void onSourceChanged(MediaCenterConstant.AudioSource source, MediaCenterConstant.AppSource appSource);

    default void onWeCarFlowTabChanged(MediaCenterConstant.AudioSource source, MediaCenterConstant.AppSource appSource) {
    }
}