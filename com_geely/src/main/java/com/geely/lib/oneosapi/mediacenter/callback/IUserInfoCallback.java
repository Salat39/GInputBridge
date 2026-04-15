package com.geely.lib.oneosapi.mediacenter.callback;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.geely.lib.oneosapi.mediacenter.bean.OnlineUserInfo;

/* loaded from: classes.dex */
public interface IUserInfoCallback extends IInterface {

    public static class Default implements IUserInfoCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.geely.lib.oneosapi.mediacenter.callback.IUserInfoCallback
        public void onUserInfoResult(int source, int app, OnlineUserInfo userInfo) throws RemoteException {
        }
    }

    void onUserInfoResult(int source, int app, OnlineUserInfo userInfo) throws RemoteException;

    public static abstract class Stub extends Binder implements IUserInfoCallback {
        private static final String DESCRIPTOR = "com.geely.lib.oneosapi.mediacenter.callback.IUserInfoCallback";
        static final int TRANSACTION_onUserInfoResult = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IUserInfoCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface queryLocalInterface = obj.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IUserInfoCallback)) {
                return (IUserInfoCallback) queryLocalInterface;
            }
            return new Proxy(obj);
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            if (code != 1) {
                if (code == 1598968902) {
                    reply.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(code, data, reply, flags);
            }
            data.enforceInterface(DESCRIPTOR);
            onUserInfoResult(data.readInt(), data.readInt(), data.readInt() != 0 ? OnlineUserInfo.CREATOR.createFromParcel(data) : null);
            reply.writeNoException();
            return true;
        }

        private static class Proxy implements IUserInfoCallback {
            public static IUserInfoCallback sDefaultImpl;
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

            @Override // com.geely.lib.oneosapi.mediacenter.callback.IUserInfoCallback
            public void onUserInfoResult(int source, int app, OnlineUserInfo userInfo) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(source);
                    obtain.writeInt(app);
                    if (userInfo != null) {
                        obtain.writeInt(1);
                        userInfo.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onUserInfoResult(source, app, userInfo);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IUserInfoCallback impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static IUserInfoCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}