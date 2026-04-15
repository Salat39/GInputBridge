package com.geely.lib.oneosapi.wechat;

/* loaded from: classes.dex */
public abstract class BaseWeChatManager {
    public static final String TAG = BaseWeChatManager.class.getSimpleName();
    public IWeChatManager mService;

    public abstract int getWeChatVoidState();

    public void initService(IWeChatManager service) {
        this.mService = service;
    }

    public boolean isAlive() {
        return this.mService != null;
    }
}