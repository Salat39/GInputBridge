package com.geely.lib.oneosapi.navi;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes.dex */
public interface RemoteConnectionCallback extends IInterface {

    public static class Default implements RemoteConnectionCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.geely.lib.oneosapi.navi.RemoteConnectionCallback
        public void onConnected(String serviceName, IBinder binder) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.navi.RemoteConnectionCallback
        public void onDisconnected(String serviceName, int reason) throws RemoteException {
        }
    }

    void onConnected(String serviceName, IBinder binder) throws RemoteException;

    void onDisconnected(String serviceName, int reason) throws RemoteException;

    public static abstract class Stub extends Binder implements RemoteConnectionCallback {
        private static final String DESCRIPTOR = "com.geely.lib.oneosapi.navi.RemoteConnectionCallback";
        static final int TRANSACTION_onConnected = 1;
        static final int TRANSACTION_onDisconnected = 2;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static RemoteConnectionCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface queryLocalInterface = obj.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof RemoteConnectionCallback)) {
                return (RemoteConnectionCallback) queryLocalInterface;
            }
            return new Proxy(obj);
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            if (code == 1) {
                data.enforceInterface(DESCRIPTOR);
                onConnected(data.readString(), data.readStrongBinder());
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
            onDisconnected(data.readString(), data.readInt());
            reply.writeNoException();
            return true;
        }

        private static class Proxy implements RemoteConnectionCallback {
            public static RemoteConnectionCallback sDefaultImpl;
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

            @Override // com.geely.lib.oneosapi.navi.RemoteConnectionCallback
            public void onConnected(String serviceName, IBinder binder) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(serviceName);
                    obtain.writeStrongBinder(binder);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onConnected(serviceName, binder);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.navi.RemoteConnectionCallback
            public void onDisconnected(String serviceName, int reason) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(serviceName);
                    obtain.writeInt(reason);
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onDisconnected(serviceName, reason);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(RemoteConnectionCallback impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static RemoteConnectionCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}