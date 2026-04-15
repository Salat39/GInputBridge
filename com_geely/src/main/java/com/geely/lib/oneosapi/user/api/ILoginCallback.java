package com.geely.lib.oneosapi.user.api;

import com.geely.lib.oneosapi.user.bean.UserInfo;

/* loaded from: classes.dex */
public interface ILoginCallback {
    void onLogin();

    void onLogout();

    void onTokenChanged(String token);

    void onTokenRefresh(String token);

    void onUserInfoChanged(UserInfo userInfo);
}