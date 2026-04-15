package com.geely.lib.oneosapi.user;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes.dex */
public interface IAccountInfo extends IInterface {

    public static class Default implements IAccountInfo {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.geely.lib.oneosapi.user.IAccountInfo
        public String getDeviceId() throws RemoteException {
            return null;
        }

        @Override // com.geely.lib.oneosapi.user.IAccountInfo
        public String getDeviceVin() throws RemoteException {
            return null;
        }

        @Override // com.geely.lib.oneosapi.user.IAccountInfo
        public String getNickName() throws RemoteException {
            return null;
        }

        @Override // com.geely.lib.oneosapi.user.IAccountInfo
        public String getOpenId() throws RemoteException {
            return null;
        }

        @Override // com.geely.lib.oneosapi.user.IAccountInfo
        public String getToken() throws RemoteException {
            return null;
        }
    }

    String getDeviceId() throws RemoteException;

    String getDeviceVin() throws RemoteException;

    String getNickName() throws RemoteException;

    String getOpenId() throws RemoteException;

    String getToken() throws RemoteException;

    public static abstract class Stub extends Binder implements IAccountInfo {
        private static final String DESCRIPTOR = "com.geely.lib.oneosapi.user.IAccountInfo";
        static final int TRANSACTION_getDeviceId = 2;
        static final int TRANSACTION_getDeviceVin = 3;
        static final int TRANSACTION_getNickName = 4;
        static final int TRANSACTION_getOpenId = 1;
        static final int TRANSACTION_getToken = 5;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IAccountInfo asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface queryLocalInterface = obj.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IAccountInfo)) {
                return (IAccountInfo) queryLocalInterface;
            }
            return new Proxy(obj);
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            if (code == 1) {
                data.enforceInterface(DESCRIPTOR);
                String openId = getOpenId();
                reply.writeNoException();
                reply.writeString(openId);
                return true;
            }
            if (code == 2) {
                data.enforceInterface(DESCRIPTOR);
                String deviceId = getDeviceId();
                reply.writeNoException();
                reply.writeString(deviceId);
                return true;
            }
            if (code == 3) {
                data.enforceInterface(DESCRIPTOR);
                String deviceVin = getDeviceVin();
                reply.writeNoException();
                reply.writeString(deviceVin);
                return true;
            }
            if (code == 4) {
                data.enforceInterface(DESCRIPTOR);
                String nickName = getNickName();
                reply.writeNoException();
                reply.writeString(nickName);
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
            String token = getToken();
            reply.writeNoException();
            reply.writeString(token);
            return true;
        }

        private static class Proxy implements IAccountInfo {
            public static IAccountInfo sDefaultImpl;
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

            @Override // com.geely.lib.oneosapi.user.IAccountInfo
            public String getOpenId() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getOpenId();
                    }
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.user.IAccountInfo
            public String getDeviceId() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDeviceId();
                    }
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.user.IAccountInfo
            public String getDeviceVin() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(3, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDeviceVin();
                    }
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.user.IAccountInfo
            public String getNickName() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(4, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getNickName();
                    }
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.user.IAccountInfo
            public String getToken() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(5, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getToken();
                    }
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IAccountInfo impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static IAccountInfo getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}