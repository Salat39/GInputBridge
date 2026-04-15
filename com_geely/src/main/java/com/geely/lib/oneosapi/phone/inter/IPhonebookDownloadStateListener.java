package com.geely.lib.oneosapi.phone.inter;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes.dex */
public interface IPhonebookDownloadStateListener extends IInterface {

    public static class Default implements IPhonebookDownloadStateListener {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.geely.lib.oneosapi.phone.inter.IPhonebookDownloadStateListener
        public void onDownloadStateListener(int type, int state) throws RemoteException {
        }
    }

    void onDownloadStateListener(int type, int state) throws RemoteException;

    public static abstract class Stub extends Binder implements IPhonebookDownloadStateListener {
        private static final String DESCRIPTOR = "com.geely.lib.oneosapi.phone.inter.IPhonebookDownloadStateListener";
        static final int TRANSACTION_onDownloadStateListener = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IPhonebookDownloadStateListener asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface queryLocalInterface = obj.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IPhonebookDownloadStateListener)) {
                return (IPhonebookDownloadStateListener) queryLocalInterface;
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
            onDownloadStateListener(data.readInt(), data.readInt());
            reply.writeNoException();
            return true;
        }

        private static class Proxy implements IPhonebookDownloadStateListener {
            public static IPhonebookDownloadStateListener sDefaultImpl;
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

            @Override // com.geely.lib.oneosapi.phone.inter.IPhonebookDownloadStateListener
            public void onDownloadStateListener(int type, int state) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(type);
                    obtain.writeInt(state);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onDownloadStateListener(type, state);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IPhonebookDownloadStateListener impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static IPhonebookDownloadStateListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}