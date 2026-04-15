package com.geely.lib.oneosapi.smart;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.geely.lib.oneosapi.smart.IAirConditionerInfo;
import com.geely.lib.oneosapi.smart.IAirPurificationInfo;
import com.geely.lib.oneosapi.smart.IElectricSocketInfo;
import com.geely.lib.oneosapi.smart.ILightInfo;
import com.geely.lib.oneosapi.smart.ISmartApi;
import com.geely.lib.oneosapi.smart.ISmartCallback;
import com.geely.lib.oneosapi.smart.ISweepingRobotInfo;

/* loaded from: classes.dex */
public interface ISmartHomeService extends IInterface {

    public static class Default implements ISmartHomeService {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.geely.lib.oneosapi.smart.ISmartHomeService
        public IAirConditionerInfo getAirConditionInfo() throws RemoteException {
            return null;
        }

        @Override // com.geely.lib.oneosapi.smart.ISmartHomeService
        public IAirPurificationInfo getAirPurificationInfo() throws RemoteException {
            return null;
        }

        @Override // com.geely.lib.oneosapi.smart.ISmartHomeService
        public IElectricSocketInfo getElectricSocketInfo() throws RemoteException {
            return null;
        }

        @Override // com.geely.lib.oneosapi.smart.ISmartHomeService
        public ILightInfo getLightInfo() throws RemoteException {
            return null;
        }

        @Override // com.geely.lib.oneosapi.smart.ISmartHomeService
        public ISmartApi getSmartApi() throws RemoteException {
            return null;
        }

        @Override // com.geely.lib.oneosapi.smart.ISmartHomeService
        public ISweepingRobotInfo getSweepingRobotInfo() throws RemoteException {
            return null;
        }

        @Override // com.geely.lib.oneosapi.smart.ISmartHomeService
        public boolean registerSmartHomeListener(ISmartCallback callback) throws RemoteException {
            return false;
        }

        @Override // com.geely.lib.oneosapi.smart.ISmartHomeService
        public boolean unregisterSmartHomeListener(ISmartCallback callback) throws RemoteException {
            return false;
        }
    }

    IAirConditionerInfo getAirConditionInfo() throws RemoteException;

    IAirPurificationInfo getAirPurificationInfo() throws RemoteException;

    IElectricSocketInfo getElectricSocketInfo() throws RemoteException;

    ILightInfo getLightInfo() throws RemoteException;

    ISmartApi getSmartApi() throws RemoteException;

    ISweepingRobotInfo getSweepingRobotInfo() throws RemoteException;

    boolean registerSmartHomeListener(ISmartCallback callback) throws RemoteException;

    boolean unregisterSmartHomeListener(ISmartCallback callback) throws RemoteException;

    public static abstract class Stub extends Binder implements ISmartHomeService {
        private static final String DESCRIPTOR = "com.geely.lib.oneosapi.smart.ISmartHomeService";
        static final int TRANSACTION_getAirConditionInfo = 3;
        static final int TRANSACTION_getAirPurificationInfo = 4;
        static final int TRANSACTION_getElectricSocketInfo = 5;
        static final int TRANSACTION_getLightInfo = 6;
        static final int TRANSACTION_getSmartApi = 8;
        static final int TRANSACTION_getSweepingRobotInfo = 7;
        static final int TRANSACTION_registerSmartHomeListener = 1;
        static final int TRANSACTION_unregisterSmartHomeListener = 2;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ISmartHomeService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface queryLocalInterface = obj.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof ISmartHomeService)) {
                return (ISmartHomeService) queryLocalInterface;
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
                    boolean registerSmartHomeListener = registerSmartHomeListener(ISmartCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(registerSmartHomeListener ? 1 : 0);
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean unregisterSmartHomeListener = unregisterSmartHomeListener(ISmartCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(unregisterSmartHomeListener ? 1 : 0);
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    IAirConditionerInfo airConditionInfo = getAirConditionInfo();
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(airConditionInfo != null ? airConditionInfo.asBinder() : null);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    IAirPurificationInfo airPurificationInfo = getAirPurificationInfo();
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(airPurificationInfo != null ? airPurificationInfo.asBinder() : null);
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    IElectricSocketInfo electricSocketInfo = getElectricSocketInfo();
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(electricSocketInfo != null ? electricSocketInfo.asBinder() : null);
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    ILightInfo lightInfo = getLightInfo();
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(lightInfo != null ? lightInfo.asBinder() : null);
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    ISweepingRobotInfo sweepingRobotInfo = getSweepingRobotInfo();
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(sweepingRobotInfo != null ? sweepingRobotInfo.asBinder() : null);
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    ISmartApi smartApi = getSmartApi();
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(smartApi != null ? smartApi.asBinder() : null);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements ISmartHomeService {
            public static ISmartHomeService sDefaultImpl;
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

            @Override // com.geely.lib.oneosapi.smart.ISmartHomeService
            public boolean registerSmartHomeListener(ISmartCallback callback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().registerSmartHomeListener(callback);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.smart.ISmartHomeService
            public boolean unregisterSmartHomeListener(ISmartCallback callback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().unregisterSmartHomeListener(callback);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.smart.ISmartHomeService
            public IAirConditionerInfo getAirConditionInfo() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(3, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAirConditionInfo();
                    }
                    obtain2.readException();
                    return IAirConditionerInfo.Stub.asInterface(obtain2.readStrongBinder());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.smart.ISmartHomeService
            public IAirPurificationInfo getAirPurificationInfo() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(4, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAirPurificationInfo();
                    }
                    obtain2.readException();
                    return IAirPurificationInfo.Stub.asInterface(obtain2.readStrongBinder());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.smart.ISmartHomeService
            public IElectricSocketInfo getElectricSocketInfo() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(5, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getElectricSocketInfo();
                    }
                    obtain2.readException();
                    return IElectricSocketInfo.Stub.asInterface(obtain2.readStrongBinder());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.smart.ISmartHomeService
            public ILightInfo getLightInfo() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(6, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getLightInfo();
                    }
                    obtain2.readException();
                    return ILightInfo.Stub.asInterface(obtain2.readStrongBinder());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.smart.ISmartHomeService
            public ISweepingRobotInfo getSweepingRobotInfo() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(7, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSweepingRobotInfo();
                    }
                    obtain2.readException();
                    return ISweepingRobotInfo.Stub.asInterface(obtain2.readStrongBinder());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.smart.ISmartHomeService
            public ISmartApi getSmartApi() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(8, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSmartApi();
                    }
                    obtain2.readException();
                    return ISmartApi.Stub.asInterface(obtain2.readStrongBinder());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ISmartHomeService impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static ISmartHomeService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}