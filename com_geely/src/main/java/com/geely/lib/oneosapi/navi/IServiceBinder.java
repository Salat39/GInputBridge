package com.geely.lib.oneosapi.navi;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.geely.lib.oneosapi.navi.IServerCallback;

/* loaded from: classes.dex */
public interface IServiceBinder extends IInterface {

    public static class Default implements IServiceBinder {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.geely.lib.oneosapi.navi.IServiceBinder
        public int getDefaultMap() throws RemoteException {
            return 0;
        }

        @Override // com.geely.lib.oneosapi.navi.IServiceBinder
        public void getNaviServer(int mapVendor, boolean isLaunchMap, IServerCallback initCallback) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.navi.IServiceBinder
        public int setDefaultMap(int mapVendor) throws RemoteException {
            return 0;
        }
    }

    int getDefaultMap() throws RemoteException;

    void getNaviServer(int mapVendor, boolean isLaunchMap, IServerCallback initCallback) throws RemoteException;

    int setDefaultMap(int mapVendor) throws RemoteException;

    public static abstract class Stub extends Binder implements IServiceBinder {
        private static final String DESCRIPTOR = "com.geely.lib.oneosapi.navi.IServiceBinder";
        static final int TRANSACTION_getDefaultMap = 3;
        static final int TRANSACTION_getNaviServer = 1;
        static final int TRANSACTION_setDefaultMap = 2;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IServiceBinder asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface queryLocalInterface = obj.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IServiceBinder)) {
                return (IServiceBinder) queryLocalInterface;
            }
            return new Proxy(obj);
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            if (code == 1) {
                data.enforceInterface(DESCRIPTOR);
                getNaviServer(data.readInt(), data.readInt() != 0, IServerCallback.Stub.asInterface(data.readStrongBinder()));
                reply.writeNoException();
                return true;
            }
            if (code == 2) {
                data.enforceInterface(DESCRIPTOR);
                int defaultMap = setDefaultMap(data.readInt());
                reply.writeNoException();
                reply.writeInt(defaultMap);
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
            int defaultMap2 = getDefaultMap();
            reply.writeNoException();
            reply.writeInt(defaultMap2);
            return true;
        }

        private static class Proxy implements IServiceBinder {
            public static IServiceBinder sDefaultImpl;
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

            @Override // com.geely.lib.oneosapi.navi.IServiceBinder
            public void getNaviServer(int mapVendor, boolean isLaunchMap, IServerCallback initCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(mapVendor);
                    obtain.writeInt(isLaunchMap ? 1 : 0);
                    obtain.writeStrongBinder(initCallback != null ? initCallback.asBinder() : null);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().getNaviServer(mapVendor, isLaunchMap, initCallback);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.navi.IServiceBinder
            public int setDefaultMap(int mapVendor) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(mapVendor);
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setDefaultMap(mapVendor);
                    }
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.navi.IServiceBinder
            public int getDefaultMap() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(3, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDefaultMap();
                    }
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IServiceBinder impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static IServiceBinder getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}