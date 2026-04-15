package com.geely.lib.oneosapi.launcher.listener;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes.dex */
public interface IEnterOrExitHomePageChangeListener extends IInterface {

    public static class Default implements IEnterOrExitHomePageChangeListener {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.geely.lib.oneosapi.launcher.listener.IEnterOrExitHomePageChangeListener
        public void onHomePageChanged(boolean isHome) throws RemoteException {
        }
    }

    void onHomePageChanged(boolean isHome) throws RemoteException;

    public static abstract class Stub extends Binder implements IEnterOrExitHomePageChangeListener {
        private static final String DESCRIPTOR = "com.geely.lib.oneosapi.launcher.listener.IEnterOrExitHomePageChangeListener";
        static final int TRANSACTION_onHomePageChanged = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IEnterOrExitHomePageChangeListener asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface queryLocalInterface = obj.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IEnterOrExitHomePageChangeListener)) {
                return (IEnterOrExitHomePageChangeListener) queryLocalInterface;
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
            onHomePageChanged(data.readInt() != 0);
            reply.writeNoException();
            return true;
        }

        private static class Proxy implements IEnterOrExitHomePageChangeListener {
            public static IEnterOrExitHomePageChangeListener sDefaultImpl;
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

            @Override // com.geely.lib.oneosapi.launcher.listener.IEnterOrExitHomePageChangeListener
            public void onHomePageChanged(boolean isHome) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(isHome ? 1 : 0);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onHomePageChanged(isHome);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IEnterOrExitHomePageChangeListener impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static IEnterOrExitHomePageChangeListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}