package com.geely.lib.oneosapi.listener;

/* loaded from: classes.dex */
public interface ServiceConnectionListener {
    void onServiceBinderUpdated(int binderType);

    void onServiceConnectionChanged(boolean connectionState);
}