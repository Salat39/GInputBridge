package com.geely.lib.oneosapi.theme;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes.dex */
public interface IApplyByLauncherCallback extends IInterface {

    public static class Default implements IApplyByLauncherCallback {
        @Override // com.geely.lib.oneosapi.theme.IApplyByLauncherCallback
        public void applyByLauncherCallback(int code, float downLoadProgress) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    void applyByLauncherCallback(int code, float downLoadProgress) throws RemoteException;

    public static abstract class Stub extends Binder implements IApplyByLauncherCallback {
        private static final String DESCRIPTOR = "com.geely.lib.oneosapi.theme.IApplyByLauncherCallback";
        static final int TRANSACTION_applyByLauncherCallback = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IApplyByLauncherCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface queryLocalInterface = obj.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IApplyByLauncherCallback)) {
                return (IApplyByLauncherCallback) queryLocalInterface;
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
            applyByLauncherCallback(data.readInt(), data.readFloat());
            reply.writeNoException();
            return true;
        }

        private static class Proxy implements IApplyByLauncherCallback {
            public static IApplyByLauncherCallback sDefaultImpl;
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

            @Override // com.geely.lib.oneosapi.theme.IApplyByLauncherCallback
            public void applyByLauncherCallback(int code, float downLoadProgress) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(code);
                    obtain.writeFloat(downLoadProgress);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().applyByLauncherCallback(code, downLoadProgress);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IApplyByLauncherCallback impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static IApplyByLauncherCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}