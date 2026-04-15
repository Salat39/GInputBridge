package com.geely.lib.oneosapi.navi;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.geely.lib.oneosapi.navi.INaviServer;

/* loaded from: classes.dex */
public interface IServerCallback extends IInterface {

    public static class Default implements IServerCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.geely.lib.oneosapi.navi.IServerCallback
        public String getKey() throws RemoteException {
            return null;
        }

        @Override // com.geely.lib.oneosapi.navi.IServerCallback
        public void initFailed(int errorCode, String errorMessage) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.navi.IServerCallback
        public void initSuccess(INaviServer naviservice) throws RemoteException {
        }
    }

    String getKey() throws RemoteException;

    void initFailed(int errorCode, String errorMessage) throws RemoteException;

    void initSuccess(INaviServer naviservice) throws RemoteException;

    public static abstract class Stub extends Binder implements IServerCallback {
        private static final String DESCRIPTOR = "com.geely.lib.oneosapi.navi.IServerCallback";
        static final int TRANSACTION_getKey = 1;
        static final int TRANSACTION_initFailed = 3;
        static final int TRANSACTION_initSuccess = 2;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IServerCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface queryLocalInterface = obj.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IServerCallback)) {
                return (IServerCallback) queryLocalInterface;
            }
            return new Proxy(obj);
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            if (code == 1) {
                data.enforceInterface(DESCRIPTOR);
                String key = getKey();
                reply.writeNoException();
                reply.writeString(key);
                return true;
            }
            if (code == 2) {
                data.enforceInterface(DESCRIPTOR);
                initSuccess(INaviServer.Stub.asInterface(data.readStrongBinder()));
                reply.writeNoException();
                return true;
            }
            if (code != 3) {
                if (code == 1598968902) {
                    reply.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(code, data, reply, flags);
            }
            data.enforceInterface(DESCRIPTOR);
            initFailed(data.readInt(), data.readString());
            reply.writeNoException();
            return true;
        }

        private static class Proxy implements IServerCallback {
            public static IServerCallback sDefaultImpl;
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

            @Override // com.geely.lib.oneosapi.navi.IServerCallback
            public String getKey() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getKey();
                    }
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.navi.IServerCallback
            public void initSuccess(INaviServer naviservice) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(naviservice != null ? naviservice.asBinder() : null);
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().initSuccess(naviservice);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.navi.IServerCallback
            public void initFailed(int errorCode, String errorMessage) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(errorCode);
                    obtain.writeString(errorMessage);
                    if (!this.mRemote.transact(3, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().initFailed(errorCode, errorMessage);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IServerCallback impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static IServerCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}