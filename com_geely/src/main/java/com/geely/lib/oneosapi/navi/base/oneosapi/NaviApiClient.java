package com.geely.lib.oneosapi.navi.base.oneosapi;

/* loaded from: classes.dex */
public abstract class NaviApiClient {

    public interface Callback {
        void onAPIFailed(int errorCode, String errorMessage);

        void onAPIReady();
    }
}