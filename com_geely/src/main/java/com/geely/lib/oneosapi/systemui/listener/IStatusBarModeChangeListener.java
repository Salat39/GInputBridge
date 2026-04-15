package com.geely.lib.oneosapi.systemui.listener;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes.dex */
public interface IStatusBarModeChangeListener extends IInterface {

    public static class Default implements IStatusBarModeChangeListener {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.geely.lib.oneosapi.systemui.listener.IStatusBarModeChangeListener
        public void onStatusBarModeChange(Bundle bundle) throws RemoteException {
        }
    }

    void onStatusBarModeChange(Bundle bundle) throws RemoteException;

    public static abstract class Stub extends Binder implements IStatusBarModeChangeListener {
        private static final String DESCRIPTOR = "com.geely.lib.oneosapi.systemui.listener.IStatusBarModeChangeListener";
        static final int TRANSACTION_onStatusBarModeChange = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IStatusBarModeChangeListener asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface queryLocalInterface = obj.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IStatusBarModeChangeListener)) {
                return (IStatusBarModeChangeListener) queryLocalInterface;
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
            onStatusBarModeChange(data.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(data) : null);
            reply.writeNoException();
            return true;
        }

        private static class Proxy implements IStatusBarModeChangeListener {
            public static IStatusBarModeChangeListener sDefaultImpl;
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

            @Override // com.geely.lib.oneosapi.systemui.listener.IStatusBarModeChangeListener
            public void onStatusBarModeChange(Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onStatusBarModeChange(bundle);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IStatusBarModeChangeListener impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static IStatusBarModeChangeListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}