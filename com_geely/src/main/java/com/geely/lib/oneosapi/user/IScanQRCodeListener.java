package com.geely.lib.oneosapi.user;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes.dex */
public interface IScanQRCodeListener extends IInterface {

    public static class Default implements IScanQRCodeListener {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.geely.lib.oneosapi.user.IScanQRCodeListener
        public void getQrCodeStatus(String status, String info) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.user.IScanQRCodeListener
        public void scanQrCodeStatus(String status, String info) throws RemoteException {
        }
    }

    void getQrCodeStatus(String status, String info) throws RemoteException;

    void scanQrCodeStatus(String status, String info) throws RemoteException;

    public static abstract class Stub extends Binder implements IScanQRCodeListener {
        private static final String DESCRIPTOR = "com.geely.lib.oneosapi.user.IScanQRCodeListener";
        static final int TRANSACTION_getQrCodeStatus = 1;
        static final int TRANSACTION_scanQrCodeStatus = 2;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IScanQRCodeListener asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface queryLocalInterface = obj.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IScanQRCodeListener)) {
                return (IScanQRCodeListener) queryLocalInterface;
            }
            return new Proxy(obj);
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            if (code == 1) {
                data.enforceInterface(DESCRIPTOR);
                getQrCodeStatus(data.readString(), data.readString());
                reply.writeNoException();
                return true;
            }
            if (code != 2) {
                if (code == 1598968902) {
                    reply.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(code, data, reply, flags);
            }
            data.enforceInterface(DESCRIPTOR);
            scanQrCodeStatus(data.readString(), data.readString());
            reply.writeNoException();
            return true;
        }

        private static class Proxy implements IScanQRCodeListener {
            public static IScanQRCodeListener sDefaultImpl;
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

            @Override // com.geely.lib.oneosapi.user.IScanQRCodeListener
            public void getQrCodeStatus(String status, String info) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(status);
                    obtain.writeString(info);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().getQrCodeStatus(status, info);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.user.IScanQRCodeListener
            public void scanQrCodeStatus(String status, String info) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(status);
                    obtain.writeString(info);
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().scanQrCodeStatus(status, info);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IScanQRCodeListener impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static IScanQRCodeListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}