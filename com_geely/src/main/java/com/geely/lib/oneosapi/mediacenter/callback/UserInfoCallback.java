package com.geely.lib.oneosapi.mediacenter.callback;

import android.os.Handler;
import android.os.Looper;
import android.os.RemoteException;
import com.geely.lib.oneosapi.mediacenter.base.BaseCallback;
import com.geely.lib.oneosapi.mediacenter.bean.OnlineUserInfo;
import com.geely.lib.oneosapi.mediacenter.callback.IUserInfoCallback;
import com.geely.lib.oneosapi.mediacenter.constant.MediaCenterConstant;

/* loaded from: classes.dex */
public abstract class UserInfoCallback extends BaseCallback<IUserInfoCallback> {
    public abstract void onUserInfoResult(MediaCenterConstant.AudioSource source, MediaCenterConstant.AppSource app, OnlineUserInfo userInfo);

    public UserInfoCallback() {
        setCallbackImpl(new IUserInfoCallback.Stub() { // from class: com.geely.lib.oneosapi.mediacenter.callback.UserInfoCallback.1
            @Override // com.geely.lib.oneosapi.mediacenter.callback.IUserInfoCallback
            public void onUserInfoResult(final int source, final int app, final OnlineUserInfo userInfo) throws RemoteException {
                new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.geely.lib.oneosapi.mediacenter.callback.UserInfoCallback.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        UserInfoCallback.this.onUserInfoResult(MediaCenterConstant.getAudioSourceEnum(source), MediaCenterConstant.getAppSourceEnum(app), userInfo);
                    }
                });
            }
        });
    }
}