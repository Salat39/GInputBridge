package com.geely.lib.oneosapi.launcher.listener;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes.dex */
public interface IMapWidgetChangeListener extends IInterface {

    public static class Default implements IMapWidgetChangeListener {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.geely.lib.oneosapi.launcher.listener.IMapWidgetChangeListener
        public void onMapWidgetChanged(String className) throws RemoteException {
        }
    }

    void onMapWidgetChanged(String className) throws RemoteException;

    public static abstract class Stub extends Binder implements IMapWidgetChangeListener {
        private static final String DESCRIPTOR = "com.geely.lib.oneosapi.launcher.listener.IMapWidgetChangeListener";
        static final int TRANSACTION_onMapWidgetChanged = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IMapWidgetChangeListener asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface queryLocalInterface = obj.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IMapWidgetChangeListener)) {
                return (IMapWidgetChangeListener) queryLocalInterface;
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
            onMapWidgetChanged(data.readString());
            reply.writeNoException();
            return true;
        }

        private static class Proxy implements IMapWidgetChangeListener {
            public static IMapWidgetChangeListener sDefaultImpl;
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

            @Override // com.geely.lib.oneosapi.launcher.listener.IMapWidgetChangeListener
            public void onMapWidgetChanged(String className) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(className);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onMapWidgetChanged(className);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IMapWidgetChangeListener impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static IMapWidgetChangeListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}