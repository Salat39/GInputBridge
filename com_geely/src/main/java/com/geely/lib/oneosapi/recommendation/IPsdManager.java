package com.geely.lib.oneosapi.recommendation;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.geely.lib.oneosapi.recommendation.callback.IPsdRecCallback;
import com.geely.lib.oneosapi.recommendation.callback.IWidgetCallback;

/* loaded from: classes.dex */
public interface IPsdManager extends IInterface {

    public static class Default implements IPsdManager {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.geely.lib.oneosapi.recommendation.IPsdManager
        public void getPsdPullInfos(boolean isOpen) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.recommendation.IPsdManager
        public void getPsdRecInfos() throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.recommendation.IPsdManager
        public void getWidgetInfos() throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.recommendation.IPsdManager
        public void getWidgetPullInfos(boolean isOpen) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.recommendation.IPsdManager
        public boolean subscribePsdRecCallback(IPsdRecCallback callback) throws RemoteException {
            return false;
        }

        @Override // com.geely.lib.oneosapi.recommendation.IPsdManager
        public boolean subscribeWidgetCallback(IWidgetCallback callback) throws RemoteException {
            return false;
        }

        @Override // com.geely.lib.oneosapi.recommendation.IPsdManager
        public boolean unSubscribePsdRecCallback(IPsdRecCallback callback) throws RemoteException {
            return false;
        }

        @Override // com.geely.lib.oneosapi.recommendation.IPsdManager
        public boolean unSubscribeWidgetCallback(IWidgetCallback callback) throws RemoteException {
            return false;
        }
    }

    void getPsdPullInfos(boolean isOpen) throws RemoteException;

    void getPsdRecInfos() throws RemoteException;

    void getWidgetInfos() throws RemoteException;

    void getWidgetPullInfos(boolean isOpen) throws RemoteException;

    boolean subscribePsdRecCallback(IPsdRecCallback callback) throws RemoteException;

    boolean subscribeWidgetCallback(IWidgetCallback callback) throws RemoteException;

    boolean unSubscribePsdRecCallback(IPsdRecCallback callback) throws RemoteException;

    boolean unSubscribeWidgetCallback(IWidgetCallback callback) throws RemoteException;

    public static abstract class Stub extends Binder implements IPsdManager {
        private static final String DESCRIPTOR = "com.geely.lib.oneosapi.recommendation.IPsdManager";
        static final int TRANSACTION_getPsdPullInfos = 7;
        static final int TRANSACTION_getPsdRecInfos = 1;
        static final int TRANSACTION_getWidgetInfos = 4;
        static final int TRANSACTION_getWidgetPullInfos = 8;
        static final int TRANSACTION_subscribePsdRecCallback = 2;
        static final int TRANSACTION_subscribeWidgetCallback = 5;
        static final int TRANSACTION_unSubscribePsdRecCallback = 3;
        static final int TRANSACTION_unSubscribeWidgetCallback = 6;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IPsdManager asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface queryLocalInterface = obj.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IPsdManager)) {
                return (IPsdManager) queryLocalInterface;
            }
            return new Proxy(obj);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            switch (i) {
                case 1:
                    parcel.enforceInterface(DESCRIPTOR);
                    getPsdRecInfos();
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean subscribePsdRecCallback = subscribePsdRecCallback(IPsdRecCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(subscribePsdRecCallback ? 1 : 0);
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean unSubscribePsdRecCallback = unSubscribePsdRecCallback(IPsdRecCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(unSubscribePsdRecCallback ? 1 : 0);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    getWidgetInfos();
                    parcel2.writeNoException();
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean subscribeWidgetCallback = subscribeWidgetCallback(IWidgetCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(subscribeWidgetCallback ? 1 : 0);
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean unSubscribeWidgetCallback = unSubscribeWidgetCallback(IWidgetCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(unSubscribeWidgetCallback ? 1 : 0);
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    getPsdPullInfos(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    getWidgetPullInfos(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IPsdManager {
            public static IPsdManager sDefaultImpl;
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

            @Override // com.geely.lib.oneosapi.recommendation.IPsdManager
            public void getPsdRecInfos() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().getPsdRecInfos();
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.recommendation.IPsdManager
            public boolean subscribePsdRecCallback(IPsdRecCallback callback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().subscribePsdRecCallback(callback);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.recommendation.IPsdManager
            public boolean unSubscribePsdRecCallback(IPsdRecCallback callback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    if (!this.mRemote.transact(3, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().unSubscribePsdRecCallback(callback);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.recommendation.IPsdManager
            public void getWidgetInfos() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(4, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().getWidgetInfos();
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.recommendation.IPsdManager
            public boolean subscribeWidgetCallback(IWidgetCallback callback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    if (!this.mRemote.transact(5, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().subscribeWidgetCallback(callback);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.recommendation.IPsdManager
            public boolean unSubscribeWidgetCallback(IWidgetCallback callback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    if (!this.mRemote.transact(6, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().unSubscribeWidgetCallback(callback);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.recommendation.IPsdManager
            public void getPsdPullInfos(boolean isOpen) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(isOpen ? 1 : 0);
                    if (!this.mRemote.transact(7, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().getPsdPullInfos(isOpen);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.recommendation.IPsdManager
            public void getWidgetPullInfos(boolean isOpen) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(isOpen ? 1 : 0);
                    if (!this.mRemote.transact(8, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().getWidgetPullInfos(isOpen);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IPsdManager impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static IPsdManager getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}