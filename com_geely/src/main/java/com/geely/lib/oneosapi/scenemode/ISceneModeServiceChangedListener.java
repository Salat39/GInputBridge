package com.geely.lib.oneosapi.scenemode;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes.dex */
public interface ISceneModeServiceChangedListener extends IInterface {

    public static class Default implements ISceneModeServiceChangedListener {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.geely.lib.oneosapi.scenemode.ISceneModeServiceChangedListener
        public void onFrontStateChanged(int state) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.scenemode.ISceneModeServiceChangedListener
        public void onOpenStateChanged(int state) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.scenemode.ISceneModeServiceChangedListener
        public void onSceneModeNameChanged(String name) throws RemoteException {
        }
    }

    void onFrontStateChanged(int state) throws RemoteException;

    void onOpenStateChanged(int state) throws RemoteException;

    void onSceneModeNameChanged(String name) throws RemoteException;

    public static abstract class Stub extends Binder implements ISceneModeServiceChangedListener {
        private static final String DESCRIPTOR = "com.geely.lib.oneosapi.scenemode.ISceneModeServiceChangedListener";
        static final int TRANSACTION_onFrontStateChanged = 2;
        static final int TRANSACTION_onOpenStateChanged = 1;
        static final int TRANSACTION_onSceneModeNameChanged = 3;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ISceneModeServiceChangedListener asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface queryLocalInterface = obj.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof ISceneModeServiceChangedListener)) {
                return (ISceneModeServiceChangedListener) queryLocalInterface;
            }
            return new Proxy(obj);
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            if (code == 1) {
                data.enforceInterface(DESCRIPTOR);
                onOpenStateChanged(data.readInt());
                reply.writeNoException();
                return true;
            }
            if (code == 2) {
                data.enforceInterface(DESCRIPTOR);
                onFrontStateChanged(data.readInt());
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
            onSceneModeNameChanged(data.readString());
            reply.writeNoException();
            return true;
        }

        private static class Proxy implements ISceneModeServiceChangedListener {
            public static ISceneModeServiceChangedListener sDefaultImpl;
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

            @Override // com.geely.lib.oneosapi.scenemode.ISceneModeServiceChangedListener
            public void onOpenStateChanged(int state) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(state);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onOpenStateChanged(state);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.scenemode.ISceneModeServiceChangedListener
            public void onFrontStateChanged(int state) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(state);
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onFrontStateChanged(state);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.scenemode.ISceneModeServiceChangedListener
            public void onSceneModeNameChanged(String name) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(name);
                    if (!this.mRemote.transact(3, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onSceneModeNameChanged(name);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ISceneModeServiceChangedListener impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static ISceneModeServiceChangedListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}