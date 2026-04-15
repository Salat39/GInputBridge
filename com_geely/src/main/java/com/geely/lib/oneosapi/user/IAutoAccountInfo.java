package com.geely.lib.oneosapi.user;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes.dex */
public interface IAutoAccountInfo extends IInterface {

    public static class Default implements IAutoAccountInfo {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.geely.lib.oneosapi.user.IAutoAccountInfo
        public String getAutoUserAvatar() throws RemoteException {
            return null;
        }

        @Override // com.geely.lib.oneosapi.user.IAutoAccountInfo
        public String getAutoUserId() throws RemoteException {
            return null;
        }

        @Override // com.geely.lib.oneosapi.user.IAutoAccountInfo
        public String getAutoUserName() throws RemoteException {
            return null;
        }

        @Override // com.geely.lib.oneosapi.user.IAutoAccountInfo
        public boolean isBindingSuccess() throws RemoteException {
            return false;
        }
    }

    String getAutoUserAvatar() throws RemoteException;

    String getAutoUserId() throws RemoteException;

    String getAutoUserName() throws RemoteException;

    boolean isBindingSuccess() throws RemoteException;

    public static abstract class Stub extends Binder implements IAutoAccountInfo {
        private static final String DESCRIPTOR = "com.geely.lib.oneosapi.user.IAutoAccountInfo";
        static final int TRANSACTION_getAutoUserAvatar = 2;
        static final int TRANSACTION_getAutoUserId = 1;
        static final int TRANSACTION_getAutoUserName = 3;
        static final int TRANSACTION_isBindingSuccess = 4;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IAutoAccountInfo asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface queryLocalInterface = obj.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IAutoAccountInfo)) {
                return (IAutoAccountInfo) queryLocalInterface;
            }
            return new Proxy(obj);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                String autoUserId = getAutoUserId();
                parcel2.writeNoException();
                parcel2.writeString(autoUserId);
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                String autoUserAvatar = getAutoUserAvatar();
                parcel2.writeNoException();
                parcel2.writeString(autoUserAvatar);
                return true;
            }
            if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                String autoUserName = getAutoUserName();
                parcel2.writeNoException();
                parcel2.writeString(autoUserName);
                return true;
            }
            if (i != 4) {
                if (i == 1598968902) {
                    parcel2.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface(DESCRIPTOR);
            boolean isBindingSuccess = isBindingSuccess();
            parcel2.writeNoException();
            parcel2.writeInt(isBindingSuccess ? 1 : 0);
            return true;
        }

        private static class Proxy implements IAutoAccountInfo {
            public static IAutoAccountInfo sDefaultImpl;
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

            @Override // com.geely.lib.oneosapi.user.IAutoAccountInfo
            public String getAutoUserId() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAutoUserId();
                    }
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.user.IAutoAccountInfo
            public String getAutoUserAvatar() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAutoUserAvatar();
                    }
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.user.IAutoAccountInfo
            public String getAutoUserName() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(3, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAutoUserName();
                    }
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.user.IAutoAccountInfo
            public boolean isBindingSuccess() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(4, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isBindingSuccess();
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IAutoAccountInfo impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static IAutoAccountInfo getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}