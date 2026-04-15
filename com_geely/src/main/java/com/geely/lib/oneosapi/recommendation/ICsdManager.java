package com.geely.lib.oneosapi.recommendation;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.geely.lib.oneosapi.recommendation.callback.ICsdRecCallback;

/* loaded from: classes.dex */
public interface ICsdManager extends IInterface {

    public static class Default implements ICsdManager {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.geely.lib.oneosapi.recommendation.ICsdManager
        public void getCsdPullInfos(boolean isOpen) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.recommendation.ICsdManager
        public void getCsdRecInfos() throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.recommendation.ICsdManager
        public boolean subscribeCsdRecCallback(ICsdRecCallback callback) throws RemoteException {
            return false;
        }

        @Override // com.geely.lib.oneosapi.recommendation.ICsdManager
        public boolean unSubscribeCsdRecCallback(ICsdRecCallback callback) throws RemoteException {
            return false;
        }
    }

    void getCsdPullInfos(boolean isOpen) throws RemoteException;

    void getCsdRecInfos() throws RemoteException;

    boolean subscribeCsdRecCallback(ICsdRecCallback callback) throws RemoteException;

    boolean unSubscribeCsdRecCallback(ICsdRecCallback callback) throws RemoteException;

    public static abstract class Stub extends Binder implements ICsdManager {
        private static final String DESCRIPTOR = "com.geely.lib.oneosapi.recommendation.ICsdManager";
        static final int TRANSACTION_getCsdPullInfos = 4;
        static final int TRANSACTION_getCsdRecInfos = 1;
        static final int TRANSACTION_subscribeCsdRecCallback = 2;
        static final int TRANSACTION_unSubscribeCsdRecCallback = 3;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ICsdManager asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface queryLocalInterface = obj.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof ICsdManager)) {
                return (ICsdManager) queryLocalInterface;
            }
            return new Proxy(obj);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                getCsdRecInfos();
                parcel2.writeNoException();
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                boolean subscribeCsdRecCallback = subscribeCsdRecCallback(ICsdRecCallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                parcel2.writeInt(subscribeCsdRecCallback ? 1 : 0);
                return true;
            }
            if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                boolean unSubscribeCsdRecCallback = unSubscribeCsdRecCallback(ICsdRecCallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                parcel2.writeInt(unSubscribeCsdRecCallback ? 1 : 0);
                return true;
            }
            if (i != 4) {
                if (i == 1598968902) {
                    parcel2.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface(DESCRIPTOR);
            getCsdPullInfos(parcel.readInt() != 0);
            parcel2.writeNoException();
            return true;
        }

        private static class Proxy implements ICsdManager {
            public static ICsdManager sDefaultImpl;
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

            @Override // com.geely.lib.oneosapi.recommendation.ICsdManager
            public void getCsdRecInfos() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().getCsdRecInfos();
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.recommendation.ICsdManager
            public boolean subscribeCsdRecCallback(ICsdRecCallback callback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().subscribeCsdRecCallback(callback);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.recommendation.ICsdManager
            public boolean unSubscribeCsdRecCallback(ICsdRecCallback callback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    if (!this.mRemote.transact(3, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().unSubscribeCsdRecCallback(callback);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.recommendation.ICsdManager
            public void getCsdPullInfos(boolean isOpen) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(isOpen ? 1 : 0);
                    if (!this.mRemote.transact(4, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().getCsdPullInfos(isOpen);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ICsdManager impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static ICsdManager getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}