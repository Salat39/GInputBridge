package com.geely.lib.oneosapi.vr;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes.dex */
public interface IVREngineStatusChangedListener extends IInterface {

    public static class Default implements IVREngineStatusChangedListener {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.geely.lib.oneosapi.vr.IVREngineStatusChangedListener
        public void onVREngineStatusChanged(int status) throws RemoteException {
        }
    }

    void onVREngineStatusChanged(int status) throws RemoteException;

    public static abstract class Stub extends Binder implements IVREngineStatusChangedListener {
        private static final String DESCRIPTOR = "com.geely.lib.oneosapi.vr.IVREngineStatusChangedListener";
        static final int TRANSACTION_onVREngineStatusChanged = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IVREngineStatusChangedListener asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface queryLocalInterface = obj.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IVREngineStatusChangedListener)) {
                return (IVREngineStatusChangedListener) queryLocalInterface;
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
            onVREngineStatusChanged(data.readInt());
            reply.writeNoException();
            return true;
        }

        private static class Proxy implements IVREngineStatusChangedListener {
            public static IVREngineStatusChangedListener sDefaultImpl;
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

            @Override // com.geely.lib.oneosapi.vr.IVREngineStatusChangedListener
            public void onVREngineStatusChanged(int status) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(status);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onVREngineStatusChanged(status);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IVREngineStatusChangedListener impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static IVREngineStatusChangedListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}