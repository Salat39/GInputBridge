package com.geely.lib.oneosapi.user;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.geely.lib.oneosapi.user.IAuthorizeCallback;
import com.geely.lib.oneosapi.user.IAutoAccountInfo;
import com.geely.lib.oneosapi.user.IFeedbackListener;
import com.geely.lib.oneosapi.user.ILaunchLoginResultCallback;
import com.geely.lib.oneosapi.user.ILoginCallback;
import com.geely.lib.oneosapi.user.IScanQRCodeListener;
import com.geely.lib.oneosapi.user.IThirdAppAuthCallback;
import com.geely.lib.oneosapi.user.bean.AccountInfo;
import com.geely.lib.oneosapi.user.bean.SourceAccountInfo;
import com.geely.lib.oneosapi.user.bean.UserInfo;
import java.util.List;

/* loaded from: classes.dex */
public interface IUserService extends IInterface {

    public static class Default implements IUserService {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.geely.lib.oneosapi.user.IUserService
        public boolean fastLogin(UserInfo userInfo) throws RemoteException {
            return false;
        }

        @Override // com.geely.lib.oneosapi.user.IUserService
        public String getAccessToken() throws RemoteException {
            return null;
        }

        @Override // com.geely.lib.oneosapi.user.IUserService
        public AccountInfo getAccountInfo() throws RemoteException {
            return null;
        }

        @Override // com.geely.lib.oneosapi.user.IUserService
        public String getCommonData(int type) throws RemoteException {
            return null;
        }

        @Override // com.geely.lib.oneosapi.user.IUserService
        public String getCurrentUserProfile() throws RemoteException {
            return null;
        }

        @Override // com.geely.lib.oneosapi.user.IUserService
        public String getEmergencyMobile() throws RemoteException {
            return null;
        }

        @Override // com.geely.lib.oneosapi.user.IUserService
        public List<UserInfo> getHistoricalAccountInfo() throws RemoteException {
            return null;
        }

        @Override // com.geely.lib.oneosapi.user.IUserService
        public void getLoginQrcode(String symbol, IScanQRCodeListener listener) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.user.IUserService
        public UserInfo getLoginUser() throws RemoteException {
            return null;
        }

        @Override // com.geely.lib.oneosapi.user.IUserService
        public String getPolicyContent(String docType, String docVersion) throws RemoteException {
            return null;
        }

        @Override // com.geely.lib.oneosapi.user.IUserService
        public SourceAccountInfo getSourceAccountInfo() throws RemoteException {
            return null;
        }

        @Override // com.geely.lib.oneosapi.user.IUserService
        public String getToken() throws RemoteException {
            return null;
        }

        @Override // com.geely.lib.oneosapi.user.IUserService
        public boolean getUserAuthorizationStatus(String brandCode, IAuthorizeCallback callback) throws RemoteException {
            return false;
        }

        @Override // com.geely.lib.oneosapi.user.IUserService
        public String getUserProfileName() throws RemoteException {
            return null;
        }

        @Override // com.geely.lib.oneosapi.user.IUserService
        public boolean hasLogin() throws RemoteException {
            return false;
        }

        @Override // com.geely.lib.oneosapi.user.IUserService
        public boolean hideBackView() throws RemoteException {
            return false;
        }

        @Override // com.geely.lib.oneosapi.user.IUserService
        public boolean isAutoAccountAuthorized(IAuthorizeCallback callback) throws RemoteException {
            return false;
        }

        @Override // com.geely.lib.oneosapi.user.IUserService
        public boolean isBackShow() throws RemoteException {
            return false;
        }

        @Override // com.geely.lib.oneosapi.user.IUserService
        public boolean isThirdAppAuthorized(String brandCode, IThirdAppAuthCallback callback) throws RemoteException {
            return false;
        }

        @Override // com.geely.lib.oneosapi.user.IUserService
        public boolean launchLogin() throws RemoteException {
            return false;
        }

        @Override // com.geely.lib.oneosapi.user.IUserService
        public boolean launchLoginForResult(String packageName, int requestCode, ILaunchLoginResultCallback callback) throws RemoteException {
            return false;
        }

        @Override // com.geely.lib.oneosapi.user.IUserService
        public boolean launchLoginWithParam(String packageName, String appName, boolean isBack) throws RemoteException {
            return false;
        }

        @Override // com.geely.lib.oneosapi.user.IUserService
        public boolean launchToFeedbackContent(boolean isBack) throws RemoteException {
            return false;
        }

        @Override // com.geely.lib.oneosapi.user.IUserService
        public boolean launchToLogin(boolean isBack) throws RemoteException {
            return false;
        }

        @Override // com.geely.lib.oneosapi.user.IUserService
        public boolean launchToUserFeedback(boolean isBack) throws RemoteException {
            return false;
        }

        @Override // com.geely.lib.oneosapi.user.IUserService
        public String login(String param) throws RemoteException {
            return null;
        }

        @Override // com.geely.lib.oneosapi.user.IUserService
        public boolean logout() throws RemoteException {
            return false;
        }

        @Override // com.geely.lib.oneosapi.user.IUserService
        public boolean notifyTokenExpired() throws RemoteException {
            return false;
        }

        @Override // com.geely.lib.oneosapi.user.IUserService
        public String refreshToken() throws RemoteException {
            return null;
        }

        @Override // com.geely.lib.oneosapi.user.IUserService
        public UserInfo refreshUserInfo() throws RemoteException {
            return null;
        }

        @Override // com.geely.lib.oneosapi.user.IUserService
        public void restartRecognition() throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.user.IUserService
        public boolean saveUserProfile(String userProfile) throws RemoteException {
            return false;
        }

        @Override // com.geely.lib.oneosapi.user.IUserService
        public void sendAutoAccountInfo(IAutoAccountInfo iAutoAccountInfo) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.user.IUserService
        public void setCommonData(String data, int type) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.user.IUserService
        public boolean setCurrentUserProfile(String currentProfile) throws RemoteException {
            return false;
        }

        @Override // com.geely.lib.oneosapi.user.IUserService
        public void setEmergencyMobile(String mobile) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.user.IUserService
        public boolean setLoginCallback(ILoginCallback callback) throws RemoteException {
            return false;
        }

        @Override // com.geely.lib.oneosapi.user.IUserService
        public boolean showBackView() throws RemoteException {
            return false;
        }

        @Override // com.geely.lib.oneosapi.user.IUserService
        public void startRecognition() throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.user.IUserService
        public void submitFeedback(IFeedbackListener listener) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.user.IUserService
        public boolean unregisterLoginCallback(ILoginCallback callback) throws RemoteException {
            return false;
        }
    }

    boolean fastLogin(UserInfo userInfo) throws RemoteException;

    String getAccessToken() throws RemoteException;

    AccountInfo getAccountInfo() throws RemoteException;

    String getCommonData(int type) throws RemoteException;

    String getCurrentUserProfile() throws RemoteException;

    String getEmergencyMobile() throws RemoteException;

    List<UserInfo> getHistoricalAccountInfo() throws RemoteException;

    void getLoginQrcode(String symbol, IScanQRCodeListener listener) throws RemoteException;

    UserInfo getLoginUser() throws RemoteException;

    String getPolicyContent(String docType, String docVersion) throws RemoteException;

    SourceAccountInfo getSourceAccountInfo() throws RemoteException;

    String getToken() throws RemoteException;

    boolean getUserAuthorizationStatus(String brandCode, IAuthorizeCallback callback) throws RemoteException;

    String getUserProfileName() throws RemoteException;

    boolean hasLogin() throws RemoteException;

    boolean hideBackView() throws RemoteException;

    boolean isAutoAccountAuthorized(IAuthorizeCallback callback) throws RemoteException;

    boolean isBackShow() throws RemoteException;

    boolean isThirdAppAuthorized(String brandCode, IThirdAppAuthCallback callback) throws RemoteException;

    boolean launchLogin() throws RemoteException;

    boolean launchLoginForResult(String packageName, int requestCode, ILaunchLoginResultCallback callback) throws RemoteException;

    boolean launchLoginWithParam(String packageName, String appName, boolean isBack) throws RemoteException;

    boolean launchToFeedbackContent(boolean isBack) throws RemoteException;

    boolean launchToLogin(boolean isBack) throws RemoteException;

    boolean launchToUserFeedback(boolean isBack) throws RemoteException;

    String login(String param) throws RemoteException;

    boolean logout() throws RemoteException;

    boolean notifyTokenExpired() throws RemoteException;

    String refreshToken() throws RemoteException;

    UserInfo refreshUserInfo() throws RemoteException;

    void restartRecognition() throws RemoteException;

    boolean saveUserProfile(String userProfile) throws RemoteException;

    void sendAutoAccountInfo(IAutoAccountInfo iAutoAccountInfo) throws RemoteException;

    void setCommonData(String data, int type) throws RemoteException;

    boolean setCurrentUserProfile(String currentProfile) throws RemoteException;

    void setEmergencyMobile(String mobile) throws RemoteException;

    boolean setLoginCallback(ILoginCallback callback) throws RemoteException;

    boolean showBackView() throws RemoteException;

    void startRecognition() throws RemoteException;

    void submitFeedback(IFeedbackListener listener) throws RemoteException;

    boolean unregisterLoginCallback(ILoginCallback callback) throws RemoteException;

    public static abstract class Stub extends Binder implements IUserService {
        private static final String DESCRIPTOR = "com.geely.lib.oneosapi.user.IUserService";
        static final int TRANSACTION_fastLogin = 19;
        static final int TRANSACTION_getAccessToken = 2;
        static final int TRANSACTION_getAccountInfo = 32;
        static final int TRANSACTION_getCommonData = 39;
        static final int TRANSACTION_getCurrentUserProfile = 28;
        static final int TRANSACTION_getEmergencyMobile = 12;
        static final int TRANSACTION_getHistoricalAccountInfo = 17;
        static final int TRANSACTION_getLoginQrcode = 24;
        static final int TRANSACTION_getLoginUser = 5;
        static final int TRANSACTION_getPolicyContent = 23;
        static final int TRANSACTION_getSourceAccountInfo = 22;
        static final int TRANSACTION_getToken = 10;
        static final int TRANSACTION_getUserAuthorizationStatus = 41;
        static final int TRANSACTION_getUserProfileName = 29;
        static final int TRANSACTION_hasLogin = 1;
        static final int TRANSACTION_hideBackView = 27;
        static final int TRANSACTION_isAutoAccountAuthorized = 21;
        static final int TRANSACTION_isBackShow = 25;
        static final int TRANSACTION_isThirdAppAuthorized = 33;
        static final int TRANSACTION_launchLogin = 3;
        static final int TRANSACTION_launchLoginForResult = 40;
        static final int TRANSACTION_launchLoginWithParam = 4;
        static final int TRANSACTION_launchToFeedbackContent = 34;
        static final int TRANSACTION_launchToLogin = 14;
        static final int TRANSACTION_launchToUserFeedback = 18;
        static final int TRANSACTION_login = 8;
        static final int TRANSACTION_logout = 9;
        static final int TRANSACTION_notifyTokenExpired = 7;
        static final int TRANSACTION_refreshToken = 11;
        static final int TRANSACTION_refreshUserInfo = 16;
        static final int TRANSACTION_restartRecognition = 36;
        static final int TRANSACTION_saveUserProfile = 31;
        static final int TRANSACTION_sendAutoAccountInfo = 20;
        static final int TRANSACTION_setCommonData = 38;
        static final int TRANSACTION_setCurrentUserProfile = 30;
        static final int TRANSACTION_setEmergencyMobile = 13;
        static final int TRANSACTION_setLoginCallback = 6;
        static final int TRANSACTION_showBackView = 26;
        static final int TRANSACTION_startRecognition = 35;
        static final int TRANSACTION_submitFeedback = 37;
        static final int TRANSACTION_unregisterLoginCallback = 15;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IUserService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface queryLocalInterface = obj.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IUserService)) {
                return (IUserService) queryLocalInterface;
            }
            return new Proxy(obj);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            switch (i) {
                case 1:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean hasLogin = hasLogin();
                    parcel2.writeNoException();
                    parcel2.writeInt(hasLogin ? 1 : 0);
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    String accessToken = getAccessToken();
                    parcel2.writeNoException();
                    parcel2.writeString(accessToken);
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean launchLogin = launchLogin();
                    parcel2.writeNoException();
                    parcel2.writeInt(launchLogin ? 1 : 0);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean launchLoginWithParam = launchLoginWithParam(parcel.readString(), parcel.readString(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(launchLoginWithParam ? 1 : 0);
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    UserInfo loginUser = getLoginUser();
                    parcel2.writeNoException();
                    if (loginUser != null) {
                        parcel2.writeInt(1);
                        loginUser.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean loginCallback = setLoginCallback(ILoginCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(loginCallback ? 1 : 0);
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean notifyTokenExpired = notifyTokenExpired();
                    parcel2.writeNoException();
                    parcel2.writeInt(notifyTokenExpired ? 1 : 0);
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    String login = login(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeString(login);
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean logout = logout();
                    parcel2.writeNoException();
                    parcel2.writeInt(logout ? 1 : 0);
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    String token = getToken();
                    parcel2.writeNoException();
                    parcel2.writeString(token);
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    String refreshToken = refreshToken();
                    parcel2.writeNoException();
                    parcel2.writeString(refreshToken);
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    String emergencyMobile = getEmergencyMobile();
                    parcel2.writeNoException();
                    parcel2.writeString(emergencyMobile);
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    setEmergencyMobile(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean launchToLogin = launchToLogin(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(launchToLogin ? 1 : 0);
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean unregisterLoginCallback = unregisterLoginCallback(ILoginCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(unregisterLoginCallback ? 1 : 0);
                    return true;
                case 16:
                    parcel.enforceInterface(DESCRIPTOR);
                    UserInfo refreshUserInfo = refreshUserInfo();
                    parcel2.writeNoException();
                    if (refreshUserInfo != null) {
                        parcel2.writeInt(1);
                        refreshUserInfo.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 17:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<UserInfo> historicalAccountInfo = getHistoricalAccountInfo();
                    parcel2.writeNoException();
                    parcel2.writeTypedList(historicalAccountInfo);
                    return true;
                case 18:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean launchToUserFeedback = launchToUserFeedback(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(launchToUserFeedback ? 1 : 0);
                    return true;
                case 19:
                    parcel.enforceInterface(DESCRIPTOR);
                    UserInfo createFromParcel = parcel.readInt() != 0 ? UserInfo.CREATOR.createFromParcel(parcel) : null;
                    boolean fastLogin = fastLogin(createFromParcel);
                    parcel2.writeNoException();
                    parcel2.writeInt(fastLogin ? 1 : 0);
                    if (createFromParcel != null) {
                        parcel2.writeInt(1);
                        createFromParcel.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 20:
                    parcel.enforceInterface(DESCRIPTOR);
                    sendAutoAccountInfo(IAutoAccountInfo.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 21:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean isAutoAccountAuthorized = isAutoAccountAuthorized(IAuthorizeCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(isAutoAccountAuthorized ? 1 : 0);
                    return true;
                case 22:
                    parcel.enforceInterface(DESCRIPTOR);
                    SourceAccountInfo sourceAccountInfo = getSourceAccountInfo();
                    parcel2.writeNoException();
                    if (sourceAccountInfo != null) {
                        parcel2.writeInt(1);
                        sourceAccountInfo.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 23:
                    parcel.enforceInterface(DESCRIPTOR);
                    String policyContent = getPolicyContent(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeString(policyContent);
                    return true;
                case 24:
                    parcel.enforceInterface(DESCRIPTOR);
                    getLoginQrcode(parcel.readString(), IScanQRCodeListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 25:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean isBackShow = isBackShow();
                    parcel2.writeNoException();
                    parcel2.writeInt(isBackShow ? 1 : 0);
                    return true;
                case 26:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean showBackView = showBackView();
                    parcel2.writeNoException();
                    parcel2.writeInt(showBackView ? 1 : 0);
                    return true;
                case 27:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean hideBackView = hideBackView();
                    parcel2.writeNoException();
                    parcel2.writeInt(hideBackView ? 1 : 0);
                    return true;
                case 28:
                    parcel.enforceInterface(DESCRIPTOR);
                    String currentUserProfile = getCurrentUserProfile();
                    parcel2.writeNoException();
                    parcel2.writeString(currentUserProfile);
                    return true;
                case 29:
                    parcel.enforceInterface(DESCRIPTOR);
                    String userProfileName = getUserProfileName();
                    parcel2.writeNoException();
                    parcel2.writeString(userProfileName);
                    return true;
                case 30:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean currentUserProfile2 = setCurrentUserProfile(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(currentUserProfile2 ? 1 : 0);
                    return true;
                case 31:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean saveUserProfile = saveUserProfile(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(saveUserProfile ? 1 : 0);
                    return true;
                case 32:
                    parcel.enforceInterface(DESCRIPTOR);
                    AccountInfo accountInfo = getAccountInfo();
                    parcel2.writeNoException();
                    if (accountInfo != null) {
                        parcel2.writeInt(1);
                        accountInfo.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 33:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean isThirdAppAuthorized = isThirdAppAuthorized(parcel.readString(), IThirdAppAuthCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(isThirdAppAuthorized ? 1 : 0);
                    return true;
                case 34:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean launchToFeedbackContent = launchToFeedbackContent(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(launchToFeedbackContent ? 1 : 0);
                    return true;
                case 35:
                    parcel.enforceInterface(DESCRIPTOR);
                    startRecognition();
                    parcel2.writeNoException();
                    return true;
                case 36:
                    parcel.enforceInterface(DESCRIPTOR);
                    restartRecognition();
                    parcel2.writeNoException();
                    return true;
                case 37:
                    parcel.enforceInterface(DESCRIPTOR);
                    submitFeedback(IFeedbackListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 38:
                    parcel.enforceInterface(DESCRIPTOR);
                    setCommonData(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 39:
                    parcel.enforceInterface(DESCRIPTOR);
                    String commonData = getCommonData(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeString(commonData);
                    return true;
                case 40:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean launchLoginForResult = launchLoginForResult(parcel.readString(), parcel.readInt(), ILaunchLoginResultCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(launchLoginForResult ? 1 : 0);
                    return true;
                case 41:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean userAuthorizationStatus = getUserAuthorizationStatus(parcel.readString(), IAuthorizeCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(userAuthorizationStatus ? 1 : 0);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IUserService {
            public static IUserService sDefaultImpl;
            private IBinder mRemote;

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.geely.lib.oneosapi.user.IUserService
            public boolean hasLogin() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().hasLogin();
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.user.IUserService
            public String getAccessToken() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAccessToken();
                    }
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.user.IUserService
            public boolean launchLogin() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(3, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().launchLogin();
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.user.IUserService
            public boolean launchLoginWithParam(String packageName, String appName, boolean isBack) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(packageName);
                    obtain.writeString(appName);
                    obtain.writeInt(isBack ? 1 : 0);
                    if (!this.mRemote.transact(4, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().launchLoginWithParam(packageName, appName, isBack);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.user.IUserService
            public UserInfo getLoginUser() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(5, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getLoginUser();
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? UserInfo.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.user.IUserService
            public boolean setLoginCallback(ILoginCallback callback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    if (!this.mRemote.transact(6, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setLoginCallback(callback);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.user.IUserService
            public boolean notifyTokenExpired() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(7, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().notifyTokenExpired();
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.user.IUserService
            public String login(String param) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(param);
                    if (!this.mRemote.transact(8, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().login(param);
                    }
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.user.IUserService
            public boolean logout() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(9, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().logout();
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.user.IUserService
            public String getToken() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(10, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getToken();
                    }
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.user.IUserService
            public String refreshToken() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(11, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().refreshToken();
                    }
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.user.IUserService
            public String getEmergencyMobile() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(12, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getEmergencyMobile();
                    }
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.user.IUserService
            public void setEmergencyMobile(String mobile) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(mobile);
                    if (!this.mRemote.transact(13, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setEmergencyMobile(mobile);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.user.IUserService
            public boolean launchToLogin(boolean isBack) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(isBack ? 1 : 0);
                    if (!this.mRemote.transact(14, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().launchToLogin(isBack);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.user.IUserService
            public boolean unregisterLoginCallback(ILoginCallback callback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    if (!this.mRemote.transact(15, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().unregisterLoginCallback(callback);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.user.IUserService
            public UserInfo refreshUserInfo() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(16, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().refreshUserInfo();
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? UserInfo.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.user.IUserService
            public List<UserInfo> getHistoricalAccountInfo() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(17, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getHistoricalAccountInfo();
                    }
                    obtain2.readException();
                    return obtain2.createTypedArrayList(UserInfo.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.user.IUserService
            public boolean launchToUserFeedback(boolean isBack) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(isBack ? 1 : 0);
                    if (!this.mRemote.transact(18, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().launchToUserFeedback(isBack);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.user.IUserService
            public boolean fastLogin(UserInfo userInfo) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean z = true;
                    if (userInfo != null) {
                        obtain.writeInt(1);
                        userInfo.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(19, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().fastLogin(userInfo);
                    }
                    obtain2.readException();
                    if (obtain2.readInt() == 0) {
                        z = false;
                    }
                    if (obtain2.readInt() != 0) {
                        userInfo.readFromParcel(obtain2);
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.user.IUserService
            public void sendAutoAccountInfo(IAutoAccountInfo iAutoAccountInfo) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iAutoAccountInfo != null ? iAutoAccountInfo.asBinder() : null);
                    if (!this.mRemote.transact(20, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().sendAutoAccountInfo(iAutoAccountInfo);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.user.IUserService
            public boolean isAutoAccountAuthorized(IAuthorizeCallback callback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    if (!this.mRemote.transact(21, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isAutoAccountAuthorized(callback);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.user.IUserService
            public SourceAccountInfo getSourceAccountInfo() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(22, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSourceAccountInfo();
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? SourceAccountInfo.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.user.IUserService
            public String getPolicyContent(String docType, String docVersion) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(docType);
                    obtain.writeString(docVersion);
                    if (!this.mRemote.transact(23, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPolicyContent(docType, docVersion);
                    }
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.user.IUserService
            public void getLoginQrcode(String symbol, IScanQRCodeListener listener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(symbol);
                    obtain.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    if (!this.mRemote.transact(24, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().getLoginQrcode(symbol, listener);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.user.IUserService
            public boolean isBackShow() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(25, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isBackShow();
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.user.IUserService
            public boolean showBackView() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(26, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().showBackView();
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.user.IUserService
            public boolean hideBackView() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(27, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().hideBackView();
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.user.IUserService
            public String getCurrentUserProfile() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(28, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCurrentUserProfile();
                    }
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.user.IUserService
            public String getUserProfileName() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(29, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getUserProfileName();
                    }
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.user.IUserService
            public boolean setCurrentUserProfile(String currentProfile) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(currentProfile);
                    if (!this.mRemote.transact(30, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setCurrentUserProfile(currentProfile);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.user.IUserService
            public boolean saveUserProfile(String userProfile) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(userProfile);
                    if (!this.mRemote.transact(31, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().saveUserProfile(userProfile);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.user.IUserService
            public AccountInfo getAccountInfo() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(32, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAccountInfo();
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? AccountInfo.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.user.IUserService
            public boolean isThirdAppAuthorized(String brandCode, IThirdAppAuthCallback callback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(brandCode);
                    obtain.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    if (!this.mRemote.transact(33, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isThirdAppAuthorized(brandCode, callback);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.user.IUserService
            public boolean launchToFeedbackContent(boolean isBack) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(isBack ? 1 : 0);
                    if (!this.mRemote.transact(34, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().launchToFeedbackContent(isBack);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.user.IUserService
            public void startRecognition() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(35, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().startRecognition();
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.user.IUserService
            public void restartRecognition() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(36, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().restartRecognition();
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.user.IUserService
            public void submitFeedback(IFeedbackListener listener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    if (!this.mRemote.transact(37, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().submitFeedback(listener);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.user.IUserService
            public void setCommonData(String data, int type) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(data);
                    obtain.writeInt(type);
                    if (!this.mRemote.transact(38, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setCommonData(data, type);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.user.IUserService
            public String getCommonData(int type) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(type);
                    if (!this.mRemote.transact(39, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCommonData(type);
                    }
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.user.IUserService
            public boolean launchLoginForResult(String packageName, int requestCode, ILaunchLoginResultCallback callback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(packageName);
                    obtain.writeInt(requestCode);
                    obtain.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    if (!this.mRemote.transact(40, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().launchLoginForResult(packageName, requestCode, callback);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.user.IUserService
            public boolean getUserAuthorizationStatus(String brandCode, IAuthorizeCallback callback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(brandCode);
                    obtain.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    if (!this.mRemote.transact(41, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getUserAuthorizationStatus(brandCode, callback);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IUserService impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static IUserService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}