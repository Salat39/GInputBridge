package com.geely.lib.oneosapi.theme;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes.dex */
public interface IGetOwnerStaticWallpaperCallback extends IInterface {

    public static class Default implements IGetOwnerStaticWallpaperCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.geely.lib.oneosapi.theme.IGetOwnerStaticWallpaperCallback
        public void getOwnerStaticWallpaperCallback(int code, String data) throws RemoteException {
        }
    }

    void getOwnerStaticWallpaperCallback(int code, String data) throws RemoteException;

    public static abstract class Stub extends Binder implements IGetOwnerStaticWallpaperCallback {
        private static final String DESCRIPTOR = "com.geely.lib.oneosapi.theme.IGetOwnerStaticWallpaperCallback";
        static final int TRANSACTION_getOwnerStaticWallpaperCallback = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IGetOwnerStaticWallpaperCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface queryLocalInterface = obj.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IGetOwnerStaticWallpaperCallback)) {
                return (IGetOwnerStaticWallpaperCallback) queryLocalInterface;
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
            getOwnerStaticWallpaperCallback(data.readInt(), data.readString());
            reply.writeNoException();
            return true;
        }

        private static class Proxy implements IGetOwnerStaticWallpaperCallback {
            public static IGetOwnerStaticWallpaperCallback sDefaultImpl;
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

            @Override // com.geely.lib.oneosapi.theme.IGetOwnerStaticWallpaperCallback
            public void getOwnerStaticWallpaperCallback(int code, String data) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(code);
                    obtain.writeString(data);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().getOwnerStaticWallpaperCallback(code, data);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IGetOwnerStaticWallpaperCallback impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static IGetOwnerStaticWallpaperCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}