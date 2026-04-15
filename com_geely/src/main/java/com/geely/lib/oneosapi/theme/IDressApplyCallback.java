package com.geely.lib.oneosapi.theme;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes.dex */
public interface IDressApplyCallback extends IInterface {

    public static class Default implements IDressApplyCallback {
        @Override // com.geely.lib.oneosapi.theme.IDressApplyCallback
        public void applyCompletedCallback(String cardGoodsVoDataJson, String taskEntityJson) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.theme.IDressApplyCallback
        public void applyErrorCallback(String cardGoodsVoDataJson, String taskEntityJson) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.theme.IDressApplyCallback
        public void applyProgressCallback(String cardGoodsVoDataJson, String taskEntityJson) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.theme.IDressApplyCallback
        public void applyStartedCallback(String cardGoodsVoDataJson, String taskEntityJson) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    void applyCompletedCallback(String cardGoodsVoDataJson, String taskEntityJson) throws RemoteException;

    void applyErrorCallback(String cardGoodsVoDataJson, String taskEntityJson) throws RemoteException;

    void applyProgressCallback(String cardGoodsVoDataJson, String taskEntityJson) throws RemoteException;

    void applyStartedCallback(String cardGoodsVoDataJson, String taskEntityJson) throws RemoteException;

    public static abstract class Stub extends Binder implements IDressApplyCallback {
        private static final String DESCRIPTOR = "com.geely.lib.oneosapi.theme.IDressApplyCallback";
        static final int TRANSACTION_applyCompletedCallback = 3;
        static final int TRANSACTION_applyErrorCallback = 4;
        static final int TRANSACTION_applyProgressCallback = 2;
        static final int TRANSACTION_applyStartedCallback = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IDressApplyCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface queryLocalInterface = obj.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IDressApplyCallback)) {
                return (IDressApplyCallback) queryLocalInterface;
            }
            return new Proxy(obj);
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            if (code == 1) {
                data.enforceInterface(DESCRIPTOR);
                applyStartedCallback(data.readString(), data.readString());
                reply.writeNoException();
                return true;
            }
            if (code == 2) {
                data.enforceInterface(DESCRIPTOR);
                applyProgressCallback(data.readString(), data.readString());
                reply.writeNoException();
                return true;
            }
            if (code == 3) {
                data.enforceInterface(DESCRIPTOR);
                applyCompletedCallback(data.readString(), data.readString());
                reply.writeNoException();
                return true;
            }
            if (code != 4) {
                if (code == 1598968902) {
                    reply.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(code, data, reply, flags);
            }
            data.enforceInterface(DESCRIPTOR);
            applyErrorCallback(data.readString(), data.readString());
            reply.writeNoException();
            return true;
        }

        private static class Proxy implements IDressApplyCallback {
            public static IDressApplyCallback sDefaultImpl;
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

            @Override // com.geely.lib.oneosapi.theme.IDressApplyCallback
            public void applyStartedCallback(String cardGoodsVoDataJson, String taskEntityJson) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(cardGoodsVoDataJson);
                    obtain.writeString(taskEntityJson);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().applyStartedCallback(cardGoodsVoDataJson, taskEntityJson);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.theme.IDressApplyCallback
            public void applyProgressCallback(String cardGoodsVoDataJson, String taskEntityJson) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(cardGoodsVoDataJson);
                    obtain.writeString(taskEntityJson);
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().applyProgressCallback(cardGoodsVoDataJson, taskEntityJson);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.theme.IDressApplyCallback
            public void applyCompletedCallback(String cardGoodsVoDataJson, String taskEntityJson) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(cardGoodsVoDataJson);
                    obtain.writeString(taskEntityJson);
                    if (!this.mRemote.transact(3, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().applyCompletedCallback(cardGoodsVoDataJson, taskEntityJson);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.theme.IDressApplyCallback
            public void applyErrorCallback(String cardGoodsVoDataJson, String taskEntityJson) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(cardGoodsVoDataJson);
                    obtain.writeString(taskEntityJson);
                    if (!this.mRemote.transact(4, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().applyErrorCallback(cardGoodsVoDataJson, taskEntityJson);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IDressApplyCallback impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static IDressApplyCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}