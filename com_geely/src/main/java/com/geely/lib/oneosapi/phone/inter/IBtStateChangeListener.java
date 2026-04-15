package com.geely.lib.oneosapi.phone.inter;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes.dex */
public interface IBtStateChangeListener extends IInterface {

    public static class Default implements IBtStateChangeListener {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.geely.lib.oneosapi.phone.inter.IBtStateChangeListener
        public void onBtProfileChange(int profrle, int status) throws RemoteException {
        }
    }

    void onBtProfileChange(int profrle, int status) throws RemoteException;

    public static abstract class Stub extends Binder implements IBtStateChangeListener {
        private static final String DESCRIPTOR = "com.geely.lib.oneosapi.phone.inter.IBtStateChangeListener";
        static final int TRANSACTION_onBtProfileChange = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IBtStateChangeListener asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface queryLocalInterface = obj.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IBtStateChangeListener)) {
                return (IBtStateChangeListener) queryLocalInterface;
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
            onBtProfileChange(data.readInt(), data.readInt());
            reply.writeNoException();
            return true;
        }

        private static class Proxy implements IBtStateChangeListener {
            public static IBtStateChangeListener sDefaultImpl;
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

            @Override // com.geely.lib.oneosapi.phone.inter.IBtStateChangeListener
            public void onBtProfileChange(int profrle, int status) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(profrle);
                    obtain.writeInt(status);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onBtProfileChange(profrle, status);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IBtStateChangeListener impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static IBtStateChangeListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}