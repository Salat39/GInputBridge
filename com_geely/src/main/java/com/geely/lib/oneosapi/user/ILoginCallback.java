package com.geely.lib.oneosapi.user;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.geely.lib.oneosapi.user.bean.UserInfo;

/* loaded from: classes.dex */
public interface ILoginCallback extends IInterface {

    public static class Default implements ILoginCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.geely.lib.oneosapi.user.ILoginCallback
        public void onLogin() throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.user.ILoginCallback
        public void onLogout() throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.user.ILoginCallback
        public void onTokenChanged(String token) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.user.ILoginCallback
        public void onTokenRefresh(String token) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.user.ILoginCallback
        public void onUserInfoChanged(UserInfo userInfo) throws RemoteException {
        }
    }

    void onLogin() throws RemoteException;

    void onLogout() throws RemoteException;

    void onTokenChanged(String token) throws RemoteException;

    void onTokenRefresh(String token) throws RemoteException;

    void onUserInfoChanged(UserInfo userInfo) throws RemoteException;

    public static abstract class Stub extends Binder implements ILoginCallback {
        private static final String DESCRIPTOR = "com.geely.lib.oneosapi.user.ILoginCallback";
        static final int TRANSACTION_onLogin = 1;
        static final int TRANSACTION_onLogout = 2;
        static final int TRANSACTION_onTokenChanged = 4;
        static final int TRANSACTION_onTokenRefresh = 3;
        static final int TRANSACTION_onUserInfoChanged = 5;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ILoginCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface queryLocalInterface = obj.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof ILoginCallback)) {
                return (ILoginCallback) queryLocalInterface;
            }
            return new Proxy(obj);
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            if (code == 1) {
                data.enforceInterface(DESCRIPTOR);
                onLogin();
                reply.writeNoException();
                return true;
            }
            if (code == 2) {
                data.enforceInterface(DESCRIPTOR);
                onLogout();
                reply.writeNoException();
                return true;
            }
            if (code == 3) {
                data.enforceInterface(DESCRIPTOR);
                onTokenRefresh(data.readString());
                reply.writeNoException();
                return true;
            }
            if (code == 4) {
                data.enforceInterface(DESCRIPTOR);
                onTokenChanged(data.readString());
                reply.writeNoException();
                return true;
            }
            if (code != 5) {
                if (code == 1598968902) {
                    reply.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(code, data, reply, flags);
            }
            data.enforceInterface(DESCRIPTOR);
            onUserInfoChanged(data.readInt() != 0 ? UserInfo.CREATOR.createFromParcel(data) : null);
            reply.writeNoException();
            return true;
        }

        private static class Proxy implements ILoginCallback {
            public static ILoginCallback sDefaultImpl;
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

            @Override // com.geely.lib.oneosapi.user.ILoginCallback
            public void onLogin() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onLogin();
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.user.ILoginCallback
            public void onLogout() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onLogout();
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.user.ILoginCallback
            public void onTokenRefresh(String token) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(token);
                    if (!this.mRemote.transact(3, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onTokenRefresh(token);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.user.ILoginCallback
            public void onTokenChanged(String token) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(token);
                    if (!this.mRemote.transact(4, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onTokenChanged(token);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.user.ILoginCallback
            public void onUserInfoChanged(UserInfo userInfo) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (userInfo != null) {
                        obtain.writeInt(1);
                        userInfo.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(5, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onUserInfoChanged(userInfo);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ILoginCallback impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static ILoginCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}