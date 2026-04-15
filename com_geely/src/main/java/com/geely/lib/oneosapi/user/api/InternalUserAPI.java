package com.geely.lib.oneosapi.user.api;

import com.geely.lib.oneosapi.user.bean.AccountInfo;
import com.geely.lib.oneosapi.user.bean.SourceAccountInfo;
import com.geely.lib.oneosapi.user.bean.UserInfo;

/* loaded from: classes.dex */
public interface InternalUserAPI {
    boolean fastLogin(UserInfo userInfo);

    String getAccessToken();

    AccountInfo getAccountInfo();

    String getCommonData(int type);

    String getCurrentUserProfile();

    IUserInfo getLoginUser();

    String getPolicyContent(String docType, String docVersion);

    SourceAccountInfo getSourceAccountInfo();

    String getToken();

    String getUserProfileName();

    boolean hasLogin();

    boolean hideBackView();

    boolean isAutoAccountAuthorized(IAuthorizeCallback callback);

    boolean isBackShow();

    boolean isThirdAppAuthorized(String brandCode, IThirdAppAuthCallback callback);

    boolean launchLogin();

    boolean launchLogin(String packageName, String appName, boolean isBack);

    boolean launchToLogin(boolean isBack);

    boolean launchToUserFeedback(boolean isBack);

    String login(String param);

    Boolean logout();

    boolean notifyTokenExpired();

    String refreshToken();

    UserInfo refreshUserInfo();

    boolean saveUserProfile(String userProfile);

    void sendAutoAccountInfo(IAutoAccountInfo iAutoAccountInfo);

    void setCommonData(String data, int type);

    boolean setCurrentUserProfile(String currentProfile);

    boolean setLoginCallback(ILoginCallback callback);

    boolean showBackView();

    boolean unregisterLoginCallback(ILoginCallback callback);
}