package com.geely.lib.oneosapi.ota;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.geely.lib.oneosapi.ota.IOtaVersionChangedListener;

/* loaded from: classes.dex */
public interface IOtaService extends IInterface {

    public static class Default implements IOtaService {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.geely.lib.oneosapi.ota.IOtaService
        public String getCurrentCarVersionName() throws RemoteException {
            return null;
        }

        @Override // com.geely.lib.oneosapi.ota.IOtaService
        public String getSysBssId() throws RemoteException {
            return null;
        }

        @Override // com.geely.lib.oneosapi.ota.IOtaService
        public String getUpgradeVersionName() throws RemoteException {
            return null;
        }

        @Override // com.geely.lib.oneosapi.ota.IOtaService
        public boolean hasNewVersion() throws RemoteException {
            return false;
        }

        @Override // com.geely.lib.oneosapi.ota.IOtaService
        public void registerOtaVersionChangedListener(IOtaVersionChangedListener listener, String packageName) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.ota.IOtaService
        public void unregisterOtaVersionChangedListener(IOtaVersionChangedListener listener, String packageName) throws RemoteException {
        }
    }

    String getCurrentCarVersionName() throws RemoteException;

    String getSysBssId() throws RemoteException;

    String getUpgradeVersionName() throws RemoteException;

    boolean hasNewVersion() throws RemoteException;

    void registerOtaVersionChangedListener(IOtaVersionChangedListener listener, String packageName) throws RemoteException;

    void unregisterOtaVersionChangedListener(IOtaVersionChangedListener listener, String packageName) throws RemoteException;

    public static abstract class Stub extends Binder implements IOtaService {
        private static final String DESCRIPTOR = "com.geely.lib.oneosapi.ota.IOtaService";
        static final int TRANSACTION_getCurrentCarVersionName = 2;
        static final int TRANSACTION_getSysBssId = 3;
        static final int TRANSACTION_getUpgradeVersionName = 1;
        static final int TRANSACTION_hasNewVersion = 4;
        static final int TRANSACTION_registerOtaVersionChangedListener = 5;
        static final int TRANSACTION_unregisterOtaVersionChangedListener = 6;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IOtaService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface queryLocalInterface = obj.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IOtaService)) {
                return (IOtaService) queryLocalInterface;
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
                    String upgradeVersionName = getUpgradeVersionName();
                    parcel2.writeNoException();
                    parcel2.writeString(upgradeVersionName);
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    String currentCarVersionName = getCurrentCarVersionName();
                    parcel2.writeNoException();
                    parcel2.writeString(currentCarVersionName);
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    String sysBssId = getSysBssId();
                    parcel2.writeNoException();
                    parcel2.writeString(sysBssId);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean hasNewVersion = hasNewVersion();
                    parcel2.writeNoException();
                    parcel2.writeInt(hasNewVersion ? 1 : 0);
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    registerOtaVersionChangedListener(IOtaVersionChangedListener.Stub.asInterface(parcel.readStrongBinder()), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    unregisterOtaVersionChangedListener(IOtaVersionChangedListener.Stub.asInterface(parcel.readStrongBinder()), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IOtaService {
            public static IOtaService sDefaultImpl;
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

            @Override // com.geely.lib.oneosapi.ota.IOtaService
            public String getUpgradeVersionName() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getUpgradeVersionName();
                    }
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.ota.IOtaService
            public String getCurrentCarVersionName() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCurrentCarVersionName();
                    }
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.ota.IOtaService
            public String getSysBssId() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(3, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSysBssId();
                    }
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.ota.IOtaService
            public boolean hasNewVersion() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(4, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().hasNewVersion();
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.ota.IOtaService
            public void registerOtaVersionChangedListener(IOtaVersionChangedListener listener, String packageName) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    obtain.writeString(packageName);
                    if (!this.mRemote.transact(5, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerOtaVersionChangedListener(listener, packageName);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.ota.IOtaService
            public void unregisterOtaVersionChangedListener(IOtaVersionChangedListener listener, String packageName) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    obtain.writeString(packageName);
                    if (!this.mRemote.transact(6, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterOtaVersionChangedListener(listener, packageName);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IOtaService impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static IOtaService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}