package com.geely.lib.oneosapi.mediacenter;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.geely.lib.oneosapi.mediacenter.listener.ICarSpeedListener;

/* loaded from: classes.dex */
public interface ICarSpeedManager extends IInterface {

    public static class Default implements ICarSpeedManager {
        @Override // com.geely.lib.oneosapi.mediacenter.ICarSpeedManager
        public void addVehicleSpeedChangeListener(ICarSpeedListener listener) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.geely.lib.oneosapi.mediacenter.ICarSpeedManager
        public boolean isTrad() throws RemoteException {
            return false;
        }

        @Override // com.geely.lib.oneosapi.mediacenter.ICarSpeedManager
        public void removeVehicleSpeedChangeListener(ICarSpeedListener listener) throws RemoteException {
        }
    }

    void addVehicleSpeedChangeListener(ICarSpeedListener listener) throws RemoteException;

    boolean isTrad() throws RemoteException;

    void removeVehicleSpeedChangeListener(ICarSpeedListener listener) throws RemoteException;

    public static abstract class Stub extends Binder implements ICarSpeedManager {
        private static final String DESCRIPTOR = "com.geely.lib.oneosapi.mediacenter.ICarSpeedManager";
        static final int TRANSACTION_addVehicleSpeedChangeListener = 2;
        static final int TRANSACTION_isTrad = 1;
        static final int TRANSACTION_removeVehicleSpeedChangeListener = 3;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ICarSpeedManager asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface queryLocalInterface = obj.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof ICarSpeedManager)) {
                return (ICarSpeedManager) queryLocalInterface;
            }
            return new Proxy(obj);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                boolean isTrad = isTrad();
                parcel2.writeNoException();
                parcel2.writeInt(isTrad ? 1 : 0);
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                addVehicleSpeedChangeListener(ICarSpeedListener.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            if (i != 3) {
                if (i == 1598968902) {
                    parcel2.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface(DESCRIPTOR);
            removeVehicleSpeedChangeListener(ICarSpeedListener.Stub.asInterface(parcel.readStrongBinder()));
            parcel2.writeNoException();
            return true;
        }

        private static class Proxy implements ICarSpeedManager {
            public static ICarSpeedManager sDefaultImpl;
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

            @Override // com.geely.lib.oneosapi.mediacenter.ICarSpeedManager
            public boolean isTrad() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isTrad();
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.mediacenter.ICarSpeedManager
            public void addVehicleSpeedChangeListener(ICarSpeedListener listener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().addVehicleSpeedChangeListener(listener);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.mediacenter.ICarSpeedManager
            public void removeVehicleSpeedChangeListener(ICarSpeedListener listener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    if (!this.mRemote.transact(3, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().removeVehicleSpeedChangeListener(listener);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ICarSpeedManager impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static ICarSpeedManager getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}