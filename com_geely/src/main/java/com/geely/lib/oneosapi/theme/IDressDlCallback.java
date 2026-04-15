package com.geely.lib.oneosapi.theme;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes.dex */
public interface IDressDlCallback extends IInterface {

    public static class Default implements IDressDlCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.geely.lib.oneosapi.theme.IDressDlCallback
        public void dlResultCallback(String cardGoodsVoDataJson, String taskEntityJson) throws RemoteException {
        }
    }

    void dlResultCallback(String cardGoodsVoDataJson, String taskEntityJson) throws RemoteException;

    public static abstract class Stub extends Binder implements IDressDlCallback {
        private static final String DESCRIPTOR = "com.geely.lib.oneosapi.theme.IDressDlCallback";
        static final int TRANSACTION_dlResultCallback = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IDressDlCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface queryLocalInterface = obj.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IDressDlCallback)) {
                return (IDressDlCallback) queryLocalInterface;
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
            dlResultCallback(data.readString(), data.readString());
            reply.writeNoException();
            return true;
        }

        private static class Proxy implements IDressDlCallback {
            public static IDressDlCallback sDefaultImpl;
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

            @Override // com.geely.lib.oneosapi.theme.IDressDlCallback
            public void dlResultCallback(String cardGoodsVoDataJson, String taskEntityJson) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(cardGoodsVoDataJson);
                    obtain.writeString(taskEntityJson);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().dlResultCallback(cardGoodsVoDataJson, taskEntityJson);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IDressDlCallback impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static IDressDlCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}