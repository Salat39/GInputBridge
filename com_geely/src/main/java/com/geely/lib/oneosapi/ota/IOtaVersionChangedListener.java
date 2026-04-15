package com.geely.lib.oneosapi.ota;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes.dex */
public interface IOtaVersionChangedListener extends IInterface {

    public static class Default implements IOtaVersionChangedListener {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.geely.lib.oneosapi.ota.IOtaVersionChangedListener
        public void onOtaVersionChanged(boolean hasNewVersion) throws RemoteException {
        }
    }

    void onOtaVersionChanged(boolean hasNewVersion) throws RemoteException;

    public static abstract class Stub extends Binder implements IOtaVersionChangedListener {
        private static final String DESCRIPTOR = "com.geely.lib.oneosapi.ota.IOtaVersionChangedListener";
        static final int TRANSACTION_onOtaVersionChanged = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IOtaVersionChangedListener asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface queryLocalInterface = obj.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IOtaVersionChangedListener)) {
                return (IOtaVersionChangedListener) queryLocalInterface;
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
            onOtaVersionChanged(data.readInt() != 0);
            reply.writeNoException();
            return true;
        }

        private static class Proxy implements IOtaVersionChangedListener {
            public static IOtaVersionChangedListener sDefaultImpl;
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

            @Override // com.geely.lib.oneosapi.ota.IOtaVersionChangedListener
            public void onOtaVersionChanged(boolean hasNewVersion) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(hasNewVersion ? 1 : 0);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onOtaVersionChanged(hasNewVersion);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IOtaVersionChangedListener impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static IOtaVersionChangedListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}