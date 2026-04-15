package com.geely.lib.oneosapi.mediacenter.listener;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes.dex */
public interface ISourceStateListener extends IInterface {

    public static class Default implements ISourceStateListener {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.geely.lib.oneosapi.mediacenter.listener.ISourceStateListener
        public void onPsdBtStateChanged(boolean connected) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.mediacenter.listener.ISourceStateListener
        public void onSourceChanged(int audioSource, int appSource) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.mediacenter.listener.ISourceStateListener
        public void onWeCarFlowTabChanged(int audioSource, int appSource) throws RemoteException {
        }
    }

    void onPsdBtStateChanged(boolean connected) throws RemoteException;

    void onSourceChanged(int audioSource, int appSource) throws RemoteException;

    void onWeCarFlowTabChanged(int audioSource, int appSource) throws RemoteException;

    public static abstract class Stub extends Binder implements ISourceStateListener {
        private static final String DESCRIPTOR = "com.geely.lib.oneosapi.mediacenter.listener.ISourceStateListener";
        static final int TRANSACTION_onPsdBtStateChanged = 2;
        static final int TRANSACTION_onSourceChanged = 1;
        static final int TRANSACTION_onWeCarFlowTabChanged = 3;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ISourceStateListener asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface queryLocalInterface = obj.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof ISourceStateListener)) {
                return (ISourceStateListener) queryLocalInterface;
            }
            return new Proxy(obj);
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            if (code == 1) {
                data.enforceInterface(DESCRIPTOR);
                onSourceChanged(data.readInt(), data.readInt());
                reply.writeNoException();
                return true;
            }
            if (code == 2) {
                data.enforceInterface(DESCRIPTOR);
                onPsdBtStateChanged(data.readInt() != 0);
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
            onWeCarFlowTabChanged(data.readInt(), data.readInt());
            reply.writeNoException();
            return true;
        }

        private static class Proxy implements ISourceStateListener {
            public static ISourceStateListener sDefaultImpl;
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

            @Override // com.geely.lib.oneosapi.mediacenter.listener.ISourceStateListener
            public void onSourceChanged(int audioSource, int appSource) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(audioSource);
                    obtain.writeInt(appSource);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onSourceChanged(audioSource, appSource);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.mediacenter.listener.ISourceStateListener
            public void onPsdBtStateChanged(boolean connected) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(connected ? 1 : 0);
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onPsdBtStateChanged(connected);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.mediacenter.listener.ISourceStateListener
            public void onWeCarFlowTabChanged(int audioSource, int appSource) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(audioSource);
                    obtain.writeInt(appSource);
                    if (!this.mRemote.transact(3, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onWeCarFlowTabChanged(audioSource, appSource);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ISourceStateListener impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static ISourceStateListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}