package com.geely.lib.oneosapi.user;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes.dex */
public interface ISourceAccountInfo extends IInterface {

    public static class Default implements ISourceAccountInfo {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.geely.lib.oneosapi.user.ISourceAccountInfo
        public String getAccountAvatar() throws RemoteException {
            return null;
        }

        @Override // com.geely.lib.oneosapi.user.ISourceAccountInfo
        public int getAccountLoginStatus() throws RemoteException {
            return 0;
        }

        @Override // com.geely.lib.oneosapi.user.ISourceAccountInfo
        public String getBindingAck() throws RemoteException {
            return null;
        }

        @Override // com.geely.lib.oneosapi.user.ISourceAccountInfo
        public String getBindingId() throws RemoteException {
            return null;
        }

        @Override // com.geely.lib.oneosapi.user.ISourceAccountInfo
        public String getCarLoginEnvironment() throws RemoteException {
            return null;
        }

        @Override // com.geely.lib.oneosapi.user.ISourceAccountInfo
        public String getDeviceId() throws RemoteException {
            return null;
        }

        @Override // com.geely.lib.oneosapi.user.ISourceAccountInfo
        public String getDeviceNo() throws RemoteException {
            return null;
        }

        @Override // com.geely.lib.oneosapi.user.ISourceAccountInfo
        public String getSourceAccount() throws RemoteException {
            return null;
        }

        @Override // com.geely.lib.oneosapi.user.ISourceAccountInfo
        public String getSourceAccountId() throws RemoteException {
            return null;
        }

        @Override // com.geely.lib.oneosapi.user.ISourceAccountInfo
        public String getSourceAccountName() throws RemoteException {
            return null;
        }

        @Override // com.geely.lib.oneosapi.user.ISourceAccountInfo
        public String getSourceAccountToken() throws RemoteException {
            return null;
        }

        @Override // com.geely.lib.oneosapi.user.ISourceAccountInfo
        public String getSourceApp() throws RemoteException {
            return null;
        }

        @Override // com.geely.lib.oneosapi.user.ISourceAccountInfo
        public String getSourceAppName() throws RemoteException {
            return null;
        }

        @Override // com.geely.lib.oneosapi.user.ISourceAccountInfo
        public String getSourceAutoAccount() throws RemoteException {
            return null;
        }

        @Override // com.geely.lib.oneosapi.user.ISourceAccountInfo
        public String getUserRequestTime() throws RemoteException {
            return null;
        }
    }

    String getAccountAvatar() throws RemoteException;

    int getAccountLoginStatus() throws RemoteException;

    String getBindingAck() throws RemoteException;

    String getBindingId() throws RemoteException;

    String getCarLoginEnvironment() throws RemoteException;

    String getDeviceId() throws RemoteException;

    String getDeviceNo() throws RemoteException;

    String getSourceAccount() throws RemoteException;

    String getSourceAccountId() throws RemoteException;

    String getSourceAccountName() throws RemoteException;

    String getSourceAccountToken() throws RemoteException;

    String getSourceApp() throws RemoteException;

    String getSourceAppName() throws RemoteException;

    String getSourceAutoAccount() throws RemoteException;

    String getUserRequestTime() throws RemoteException;

    public static abstract class Stub extends Binder implements ISourceAccountInfo {
        private static final String DESCRIPTOR = "com.geely.lib.oneosapi.user.ISourceAccountInfo";
        static final int TRANSACTION_getAccountAvatar = 11;
        static final int TRANSACTION_getAccountLoginStatus = 3;
        static final int TRANSACTION_getBindingAck = 14;
        static final int TRANSACTION_getBindingId = 13;
        static final int TRANSACTION_getCarLoginEnvironment = 15;
        static final int TRANSACTION_getDeviceId = 7;
        static final int TRANSACTION_getDeviceNo = 8;
        static final int TRANSACTION_getSourceAccount = 6;
        static final int TRANSACTION_getSourceAccountId = 9;
        static final int TRANSACTION_getSourceAccountName = 10;
        static final int TRANSACTION_getSourceAccountToken = 4;
        static final int TRANSACTION_getSourceApp = 1;
        static final int TRANSACTION_getSourceAppName = 2;
        static final int TRANSACTION_getSourceAutoAccount = 5;
        static final int TRANSACTION_getUserRequestTime = 12;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ISourceAccountInfo asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface queryLocalInterface = obj.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof ISourceAccountInfo)) {
                return (ISourceAccountInfo) queryLocalInterface;
            }
            return new Proxy(obj);
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            if (code == 1598968902) {
                reply.writeString(DESCRIPTOR);
                return true;
            }
            switch (code) {
                case 1:
                    data.enforceInterface(DESCRIPTOR);
                    String sourceApp = getSourceApp();
                    reply.writeNoException();
                    reply.writeString(sourceApp);
                    return true;
                case 2:
                    data.enforceInterface(DESCRIPTOR);
                    String sourceAppName = getSourceAppName();
                    reply.writeNoException();
                    reply.writeString(sourceAppName);
                    return true;
                case 3:
                    data.enforceInterface(DESCRIPTOR);
                    int accountLoginStatus = getAccountLoginStatus();
                    reply.writeNoException();
                    reply.writeInt(accountLoginStatus);
                    return true;
                case 4:
                    data.enforceInterface(DESCRIPTOR);
                    String sourceAccountToken = getSourceAccountToken();
                    reply.writeNoException();
                    reply.writeString(sourceAccountToken);
                    return true;
                case 5:
                    data.enforceInterface(DESCRIPTOR);
                    String sourceAutoAccount = getSourceAutoAccount();
                    reply.writeNoException();
                    reply.writeString(sourceAutoAccount);
                    return true;
                case 6:
                    data.enforceInterface(DESCRIPTOR);
                    String sourceAccount = getSourceAccount();
                    reply.writeNoException();
                    reply.writeString(sourceAccount);
                    return true;
                case 7:
                    data.enforceInterface(DESCRIPTOR);
                    String deviceId = getDeviceId();
                    reply.writeNoException();
                    reply.writeString(deviceId);
                    return true;
                case 8:
                    data.enforceInterface(DESCRIPTOR);
                    String deviceNo = getDeviceNo();
                    reply.writeNoException();
                    reply.writeString(deviceNo);
                    return true;
                case 9:
                    data.enforceInterface(DESCRIPTOR);
                    String sourceAccountId = getSourceAccountId();
                    reply.writeNoException();
                    reply.writeString(sourceAccountId);
                    return true;
                case 10:
                    data.enforceInterface(DESCRIPTOR);
                    String sourceAccountName = getSourceAccountName();
                    reply.writeNoException();
                    reply.writeString(sourceAccountName);
                    return true;
                case 11:
                    data.enforceInterface(DESCRIPTOR);
                    String accountAvatar = getAccountAvatar();
                    reply.writeNoException();
                    reply.writeString(accountAvatar);
                    return true;
                case 12:
                    data.enforceInterface(DESCRIPTOR);
                    String userRequestTime = getUserRequestTime();
                    reply.writeNoException();
                    reply.writeString(userRequestTime);
                    return true;
                case 13:
                    data.enforceInterface(DESCRIPTOR);
                    String bindingId = getBindingId();
                    reply.writeNoException();
                    reply.writeString(bindingId);
                    return true;
                case 14:
                    data.enforceInterface(DESCRIPTOR);
                    String bindingAck = getBindingAck();
                    reply.writeNoException();
                    reply.writeString(bindingAck);
                    return true;
                case 15:
                    data.enforceInterface(DESCRIPTOR);
                    String carLoginEnvironment = getCarLoginEnvironment();
                    reply.writeNoException();
                    reply.writeString(carLoginEnvironment);
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }

        private static class Proxy implements ISourceAccountInfo {
            public static ISourceAccountInfo sDefaultImpl;
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

            @Override // com.geely.lib.oneosapi.user.ISourceAccountInfo
            public String getSourceApp() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSourceApp();
                    }
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.user.ISourceAccountInfo
            public String getSourceAppName() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSourceAppName();
                    }
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.user.ISourceAccountInfo
            public int getAccountLoginStatus() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(3, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAccountLoginStatus();
                    }
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.user.ISourceAccountInfo
            public String getSourceAccountToken() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(4, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSourceAccountToken();
                    }
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.user.ISourceAccountInfo
            public String getSourceAutoAccount() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(5, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSourceAutoAccount();
                    }
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.user.ISourceAccountInfo
            public String getSourceAccount() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(6, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSourceAccount();
                    }
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.user.ISourceAccountInfo
            public String getDeviceId() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(7, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDeviceId();
                    }
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.user.ISourceAccountInfo
            public String getDeviceNo() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(8, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDeviceNo();
                    }
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.user.ISourceAccountInfo
            public String getSourceAccountId() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(9, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSourceAccountId();
                    }
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.user.ISourceAccountInfo
            public String getSourceAccountName() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(10, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSourceAccountName();
                    }
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.user.ISourceAccountInfo
            public String getAccountAvatar() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(11, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAccountAvatar();
                    }
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.user.ISourceAccountInfo
            public String getUserRequestTime() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(12, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getUserRequestTime();
                    }
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.user.ISourceAccountInfo
            public String getBindingId() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(13, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getBindingId();
                    }
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.user.ISourceAccountInfo
            public String getBindingAck() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(14, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getBindingAck();
                    }
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.user.ISourceAccountInfo
            public String getCarLoginEnvironment() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(15, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCarLoginEnvironment();
                    }
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ISourceAccountInfo impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static ISourceAccountInfo getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}