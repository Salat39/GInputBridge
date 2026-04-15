package com.geely.lib.oneosapi.launcher.listener;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes.dex */
public interface ILauncherPageSwitchListener extends IInterface {

    public static class Default implements ILauncherPageSwitchListener {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.geely.lib.oneosapi.launcher.listener.ILauncherPageSwitchListener
        public void onPageSwitch(int currentPage) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.launcher.listener.ILauncherPageSwitchListener
        public void onPsdPageSwitch(int currentPage) throws RemoteException {
        }
    }

    void onPageSwitch(int currentPage) throws RemoteException;

    void onPsdPageSwitch(int currentPage) throws RemoteException;

    public static abstract class Stub extends Binder implements ILauncherPageSwitchListener {
        private static final String DESCRIPTOR = "com.geely.lib.oneosapi.launcher.listener.ILauncherPageSwitchListener";
        static final int TRANSACTION_onPageSwitch = 1;
        static final int TRANSACTION_onPsdPageSwitch = 2;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ILauncherPageSwitchListener asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface queryLocalInterface = obj.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof ILauncherPageSwitchListener)) {
                return (ILauncherPageSwitchListener) queryLocalInterface;
            }
            return new Proxy(obj);
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            if (code == 1) {
                data.enforceInterface(DESCRIPTOR);
                onPageSwitch(data.readInt());
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
            onPsdPageSwitch(data.readInt());
            reply.writeNoException();
            return true;
        }

        private static class Proxy implements ILauncherPageSwitchListener {
            public static ILauncherPageSwitchListener sDefaultImpl;
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

            @Override // com.geely.lib.oneosapi.launcher.listener.ILauncherPageSwitchListener
            public void onPageSwitch(int currentPage) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(currentPage);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onPageSwitch(currentPage);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.launcher.listener.ILauncherPageSwitchListener
            public void onPsdPageSwitch(int currentPage) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(currentPage);
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onPsdPageSwitch(currentPage);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ILauncherPageSwitchListener impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static ILauncherPageSwitchListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}