package com.geely.lib.oneosapi.user.api;

/* loaded from: classes.dex */
public interface IFeedbackCommandListener {
    void onFeedbackRecordRestart();

    void onFeedbackRecordStart();

    boolean onFeedbackSubmit();

    void onRecordFinish();
}