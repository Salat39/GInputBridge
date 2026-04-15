package com.geely.lib.oneosapi.user;

import android.content.Context;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.geely.lib.oneosapi.common.ServiceBaseManager;
import com.geely.lib.oneosapi.user.IAuthorizeCallback;
import com.geely.lib.oneosapi.user.IAutoAccountInfo;
import com.geely.lib.oneosapi.user.IFeedbackListener;
import com.geely.lib.oneosapi.user.ILaunchLoginResultCallback;
import com.geely.lib.oneosapi.user.ILoginCallback;
import com.geely.lib.oneosapi.user.IScanQRCodeListener;
import com.geely.lib.oneosapi.user.IThirdAppAuthCallback;
import com.geely.lib.oneosapi.user.IUserService;
import com.geely.lib.oneosapi.user.bean.AccountInfo;
import com.geely.lib.oneosapi.user.bean.SourceAccountInfo;
import com.geely.lib.oneosapi.user.bean.UserInfo;
import java.util.List;

/* loaded from: classes.dex */
public class UserManager implements ServiceBaseManager {
    private static final String TAG = "UserManager";
    private Context mContext;
    private IUserService mService;

    public static abstract class AutoAccountInfo extends IAutoAccountInfo.Stub {
    }

    public static abstract class FeedbackListener extends IFeedbackListener.Stub {
    }

    public UserManager(Context context, IBinder binder) {
        this.mContext = context;
        this.mService = IUserService.Stub.asInterface(binder);
    }

    @Override // com.geely.lib.oneosapi.common.ServiceBaseManager
    public void setService(IBinder binder) {
        if (binder != null) {
            this.mService = IUserService.Stub.asInterface(binder);
        }
    }

    public boolean isConnectService() {
        return isServiceAlive();
    }

    private boolean isServiceAlive() {
        IUserService iUserService = this.mService;
        return iUserService != null && iUserService.asBinder().isBinderAlive();
    }

    public boolean hasLogin() {
        if (isServiceAlive()) {
            try {
                return this.mService.hasLogin();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        Log.e(TAG, "hasLogin: service is not alive");
        return false;
    }

    public String getAccessToken() {
        if (isServiceAlive()) {
            try {
                return this.mService.getAccessToken();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        Log.e(TAG, "getAccessToken: service is not alive");
        return null;
    }

    public boolean launchLogin() {
        if (isServiceAlive()) {
            try {
                return this.mService.launchLogin();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        Log.e(TAG, "launchLogin: service is not alive");
        return false;
    }

    public boolean launchLoginWithParam(String packageName, String appName, boolean isBack) {
        try {
            return this.mService.launchLoginWithParam(packageName, appName, isBack);
        } catch (RemoteException e) {
            e.printStackTrace();
            Log.e(TAG, "launchLoginWithParam: service is not alive");
            return false;
        }
    }

    public UserInfo getLoginUser() {
        if (isServiceAlive()) {
            try {
                return this.mService.getLoginUser();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        Log.e(TAG, "getLoginUser: service is not alive");
        return null;
    }

    public boolean setLoginCallback(BaseLoginCallBack callback) {
        Log.e(TAG, "setLoginCallback getCallingPid：" + Binder.getCallingPid());
        if (isServiceAlive()) {
            try {
                return this.mService.setLoginCallback(callback);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        Log.e(TAG, "setLoginCallback: service is not alive");
        return false;
    }

    public boolean notifyTokenExpired() {
        if (isServiceAlive()) {
            try {
                return this.mService.notifyTokenExpired();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        Log.e(TAG, "notifyTokenExpired: service is not alive");
        return false;
    }

    public String login(String param) {
        if (isServiceAlive()) {
            try {
                return this.mService.login(param);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        Log.e(TAG, "login: service is not alive");
        return null;
    }

    public boolean logout() {
        if (isServiceAlive()) {
            try {
                return this.mService.logout();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        Log.e(TAG, "logout: service is not alive");
        return false;
    }

    public String getToken() {
        if (isServiceAlive()) {
            try {
                return this.mService.getToken();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        Log.e(TAG, "getToken: ");
        return null;
    }

    public String refreshToken() {
        if (isServiceAlive()) {
            try {
                return this.mService.refreshToken();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        Log.e(TAG, "refreshToken: service is not alive");
        return null;
    }

    public String getEmergencyMobile() {
        if (isServiceAlive()) {
            try {
                return this.mService.getEmergencyMobile();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        Log.e(TAG, "setEmergencyMobile: service is not alive");
        return null;
    }

    public void setEmergencyMobile(String mobile) {
        if (isServiceAlive()) {
            try {
                this.mService.setEmergencyMobile(mobile);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        Log.e(TAG, "setEmergencyMobile: service is not alive");
    }

    public boolean launchToLogin(boolean isBack) {
        if (isServiceAlive()) {
            try {
                return this.mService.launchToLogin(isBack);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        Log.e(TAG, "launchToLogin: service is not alive");
        return false;
    }

    public boolean unregisterLoginCallback(BaseLoginCallBack callback) {
        if (isServiceAlive()) {
            try {
                return this.mService.unregisterLoginCallback(callback);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        Log.e(TAG, "unregisterLoginCallback: service is not alive");
        return false;
    }

    public UserInfo refreshUserInfo() {
        if (isServiceAlive()) {
            try {
                return this.mService.refreshUserInfo();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        Log.e(TAG, "refreshUserInfo: service is not alive");
        return null;
    }

    public List<UserInfo> getHistoricalAccountInfo() {
        if (isServiceAlive()) {
            try {
                return this.mService.getHistoricalAccountInfo();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        Log.e(TAG, "getHistoricalAccountInfo: service is not alive");
        return null;
    }

    public boolean launchToUserFeedback(boolean isBack) {
        if (isServiceAlive()) {
            try {
                return this.mService.launchToUserFeedback(isBack);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        Log.e(TAG, "launchToUserFeedback: service is not alive");
        return false;
    }

    public boolean launchToFeedbackContent(boolean isBack) {
        if (isServiceAlive()) {
            try {
                return this.mService.launchToFeedbackContent(isBack);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        Log.e(TAG, "launchToUserFeedback: service is not alive");
        return false;
    }

    public void startRecognition() {
        if (isServiceAlive()) {
            try {
                this.mService.startRecognition();
                return;
            } catch (RemoteException e) {
                e.printStackTrace();
                return;
            }
        }
        Log.e(TAG, "startRecognition: service is not alive");
    }

    public void restartRecognition() {
        if (isServiceAlive()) {
            try {
                this.mService.restartRecognition();
                return;
            } catch (RemoteException e) {
                e.printStackTrace();
                return;
            }
        }
        Log.e(TAG, "restartRecognition: service is not alive");
    }

    public void submitFeedback(FeedbackListener listener) {
        if (isServiceAlive()) {
            try {
                this.mService.submitFeedback(listener);
                return;
            } catch (RemoteException e) {
                e.printStackTrace();
                return;
            }
        }
        Log.e(TAG, "submitFeedback: service is not alive");
    }

    public boolean fastLogin(UserInfo userInfo) {
        if (isServiceAlive()) {
            try {
                return this.mService.fastLogin(userInfo);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        Log.e(TAG, "fastLogin: service is not alive");
        return false;
    }

    public void sendAutoAccountInfo(AutoAccountInfo AutoAccountInfo2) {
        if (isServiceAlive()) {
            try {
                this.mService.sendAutoAccountInfo(AutoAccountInfo2);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        Log.e(TAG, "sendAutoAccountInfo: service is not alive");
    }

    public boolean isAutoAccountAuthorized(AuthorizeCallback callback) {
        if (isServiceAlive()) {
            try {
                return this.mService.isAutoAccountAuthorized(callback);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        Log.e(TAG, "isAutoAccountAuthorized: service is not alive");
        return false;
    }

    public SourceAccountInfo getSourceAccountInfo() {
        if (isServiceAlive()) {
            try {
                return this.mService.getSourceAccountInfo();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        Log.e(TAG, "getSourceAccountInfo: service is not alive");
        return null;
    }

    String getPolicyContent(String docType, String docVersion) {
        if (isServiceAlive()) {
            try {
                return this.mService.getPolicyContent(docType, docVersion);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        Log.e(TAG, "getPolicyContent: service is not alive");
        return null;
    }

    public void getLoginQrcode(String symbol, ScanQRCodeListener listener) {
        if (isServiceAlive()) {
            try {
                this.mService.getLoginQrcode(symbol, listener);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        Log.e(TAG, "getLoginQrcode: service is not alive");
    }

    public boolean isBackShow() {
        if (isServiceAlive()) {
            try {
                return this.mService.isBackShow();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        Log.e(TAG, "isBackShow: service is not alive");
        return false;
    }

    public boolean showBackView() {
        if (isServiceAlive()) {
            try {
                return this.mService.showBackView();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        Log.e(TAG, "showBackView: service is not alive");
        return false;
    }

    public boolean hideBackView() {
        if (isServiceAlive()) {
            try {
                return this.mService.hideBackView();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        Log.e(TAG, "hideBackView: service is not alive");
        return false;
    }

    public String getCurrentUserProfile() {
        if (isServiceAlive()) {
            try {
                return this.mService.getCurrentUserProfile();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        Log.e(TAG, "getCurrentUserProfile: service is not alive");
        return "M1";
    }

    public String getUserProfileName() {
        if (isServiceAlive()) {
            try {
                return this.mService.getUserProfileName();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        Log.e(TAG, "getUserProfileName: service is not alive");
        return "";
    }

    public boolean setCurrentUserProfile(String currentProfile) {
        if (isServiceAlive()) {
            try {
                return this.mService.setCurrentUserProfile(currentProfile);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        Log.e(TAG, "setCurrentUserProfile: service is not alive");
        return false;
    }

    public boolean saveUserProfile(String userProfile) {
        if (isServiceAlive()) {
            try {
                return this.mService.saveUserProfile(userProfile);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        Log.e(TAG, "userProfile: service is not alive");
        return false;
    }

    public AccountInfo getAccountInfo() {
        if (isServiceAlive()) {
            try {
                return this.mService.getAccountInfo();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        Log.e(TAG, "getAccountInfo: service is not alive");
        return null;
    }

    public boolean isThirdAppAuthorized(String brandCode, ThirdAppAuthCallback callback) {
        if (isServiceAlive()) {
            try {
                return this.mService.isThirdAppAuthorized(brandCode, callback);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        Log.e(TAG, "isThirdAppAuthorized: service is not alive");
        return false;
    }

    public void setCommonData(String data, int type) {
        if (isServiceAlive()) {
            try {
                this.mService.setCommonData(data, type);
                return;
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        Log.e(TAG, "setCommonData: service is not alive");
    }

    public String getCommonData(int type) {
        if (isServiceAlive()) {
            try {
                return this.mService.getCommonData(type);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        Log.e(TAG, "getCommonData: service is not alive");
        return null;
    }

    public boolean launchLoginForResult(String packageName, int requestCode, LaunchLoginResultCallback callback) {
        if (isServiceAlive()) {
            try {
                return this.mService.launchLoginForResult(packageName, requestCode, callback);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        Log.e(TAG, "launchLoginForResult: service is not alive");
        return false;
    }

    public boolean getUserAuthorizationStatus(String brandCode, AuthorizeCallback callback) {
        if (isServiceAlive()) {
            try {
                return this.mService.getUserAuthorizationStatus(brandCode, callback);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        Log.e(TAG, "getUserAuthorizationStatus: service is not alive");
        return false;
    }

    public static abstract class BaseLoginCallBack extends ILoginCallback.Stub {
        @Override // com.geely.lib.oneosapi.user.ILoginCallback
        public void onLogin() {
            Log.d(UserManager.TAG, "onLogin: ");
        }

        @Override // com.geely.lib.oneosapi.user.ILoginCallback
        public void onLogout() {
            Log.d(UserManager.TAG, "onLogout: ");
        }

        @Override // com.geely.lib.oneosapi.user.ILoginCallback
        public void onTokenChanged(String token) {
            Log.d(UserManager.TAG, "onTokenChanged() called with: token = [" + token + "]");
        }

        @Override // com.geely.lib.oneosapi.user.ILoginCallback
        public void onTokenRefresh(String token) {
            Log.d(UserManager.TAG, "onTokenRefresh() called with: token = [" + token + "]");
        }

        @Override // com.geely.lib.oneosapi.user.ILoginCallback
        public void onUserInfoChanged(UserInfo userInfo) {
            Log.d(UserManager.TAG, "onUserInfoChanged() called with: userInfo = [" + userInfo + "]");
        }
    }

    public static abstract class ScanQRCodeListener extends IScanQRCodeListener.Stub {
        @Override // com.geely.lib.oneosapi.user.IScanQRCodeListener
        public void getQrCodeStatus(String status, String info) {
            Log.d(UserManager.TAG, "getQrCodeStatus() called with: status = [" + status + "], info = [" + info + "]");
        }

        @Override // com.geely.lib.oneosapi.user.IScanQRCodeListener
        public void scanQrCodeStatus(String status, String info) {
            Log.d(UserManager.TAG, "scanQrCodeStatus() called with: status = [" + status + "], info = [" + info + "]");
        }
    }

    public static abstract class AuthorizeCallback extends IAuthorizeCallback.Stub {
        @Override // com.geely.lib.oneosapi.user.IAuthorizeCallback
        public void getAuthorizationStatus(boolean hasAuthorized) {
            Log.d(UserManager.TAG, "getAuthorizationStatus() called with: hasAuthorized = [" + hasAuthorized + "]");
        }
    }

    public static abstract class ThirdAppAuthCallback extends IThirdAppAuthCallback.Stub {
        @Override // com.geely.lib.oneosapi.user.IThirdAppAuthCallback
        public void isThirdAppAuthorized(String completeMobile) {
            Log.d(UserManager.TAG, "isThirdAppAuthorized() called with: completeMobile = [" + completeMobile + "]");
        }
    }

    public static abstract class LaunchLoginResultCallback extends ILaunchLoginResultCallback.Stub {
        @Override // com.geely.lib.oneosapi.user.ILaunchLoginResultCallback
        public void onLoginResult(int requestCode, int resultCode) {
            Log.d(UserManager.TAG, "onLoginResult() called requestCode " + requestCode + " resultCode " + resultCode);
        }
    }
}