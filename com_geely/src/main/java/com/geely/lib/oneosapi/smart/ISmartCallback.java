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
import com.geely.lib.oneosapi.smart.ISceneInfo;
import com.geely.lib.oneosapi.smart.ISweepingRobotInfo;

/* loaded from: classes.dex */
public interface ISmartCallback extends IInterface {

    public static class Default implements ISmartCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.geely.lib.oneosapi.smart.ISmartCallback
        public void onAirConditionerInfoChanged(IAirConditionerInfo airConditionInfo) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.smart.ISmartCallback
        public void onAirPurificationInfoChanged(IAirPurificationInfo airPurificationInfo) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.smart.ISmartCallback
        public void onElectricSocketInfoChanged(IElectricSocketInfo electricSocketInfo) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.smart.ISmartCallback
        public void onGoHomeModeChanged(ISceneInfo sceneInfo) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.smart.ISmartCallback
        public void onLeaveHomeModeChanged(ISceneInfo sceneInfo) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.smart.ISmartCallback
        public void onLightInfoChanged(ILightInfo lightInfo) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.smart.ISmartCallback
        public void onSweepingRobotInfoChanged(ISweepingRobotInfo sweepRobotInfo) throws RemoteException {
        }
    }

    void onAirConditionerInfoChanged(IAirConditionerInfo airConditionInfo) throws RemoteException;

    void onAirPurificationInfoChanged(IAirPurificationInfo airPurificationInfo) throws RemoteException;

    void onElectricSocketInfoChanged(IElectricSocketInfo electricSocketInfo) throws RemoteException;

    void onGoHomeModeChanged(ISceneInfo sceneInfo) throws RemoteException;

    void onLeaveHomeModeChanged(ISceneInfo sceneInfo) throws RemoteException;

    void onLightInfoChanged(ILightInfo lightInfo) throws RemoteException;

    void onSweepingRobotInfoChanged(ISweepingRobotInfo sweepRobotInfo) throws RemoteException;

    public static abstract class Stub extends Binder implements ISmartCallback {
        private static final String DESCRIPTOR = "com.geely.lib.oneosapi.smart.ISmartCallback";
        static final int TRANSACTION_onAirConditionerInfoChanged = 1;
        static final int TRANSACTION_onAirPurificationInfoChanged = 2;
        static final int TRANSACTION_onElectricSocketInfoChanged = 3;
        static final int TRANSACTION_onGoHomeModeChanged = 6;
        static final int TRANSACTION_onLeaveHomeModeChanged = 7;
        static final int TRANSACTION_onLightInfoChanged = 4;
        static final int TRANSACTION_onSweepingRobotInfoChanged = 5;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ISmartCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface queryLocalInterface = obj.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof ISmartCallback)) {
                return (ISmartCallback) queryLocalInterface;
            }
            return new Proxy(obj);
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            if (code == 1598968902) {
                reply.writeString(DESCRIPTOR);
                return true;
            }
            switch (code) {
                case 1:
                    data.enforceInterface(DESCRIPTOR);
                    onAirConditionerInfoChanged(IAirConditionerInfo.Stub.asInterface(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case 2:
                    data.enforceInterface(DESCRIPTOR);
                    onAirPurificationInfoChanged(IAirPurificationInfo.Stub.asInterface(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case 3:
                    data.enforceInterface(DESCRIPTOR);
                    onElectricSocketInfoChanged(IElectricSocketInfo.Stub.asInterface(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case 4:
                    data.enforceInterface(DESCRIPTOR);
                    onLightInfoChanged(ILightInfo.Stub.asInterface(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case 5:
                    data.enforceInterface(DESCRIPTOR);
                    onSweepingRobotInfoChanged(ISweepingRobotInfo.Stub.asInterface(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case 6:
                    data.enforceInterface(DESCRIPTOR);
                    onGoHomeModeChanged(ISceneInfo.Stub.asInterface(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case 7:
                    data.enforceInterface(DESCRIPTOR);
                    onLeaveHomeModeChanged(ISceneInfo.Stub.asInterface(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }

        private static class Proxy implements ISmartCallback {
            public static ISmartCallback sDefaultImpl;
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

            @Override // com.geely.lib.oneosapi.smart.ISmartCallback
            public void onAirConditionerInfoChanged(IAirConditionerInfo airConditionInfo) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(airConditionInfo != null ? airConditionInfo.asBinder() : null);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onAirConditionerInfoChanged(airConditionInfo);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.smart.ISmartCallback
            public void onAirPurificationInfoChanged(IAirPurificationInfo airPurificationInfo) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(airPurificationInfo != null ? airPurificationInfo.asBinder() : null);
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onAirPurificationInfoChanged(airPurificationInfo);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.smart.ISmartCallback
            public void onElectricSocketInfoChanged(IElectricSocketInfo electricSocketInfo) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(electricSocketInfo != null ? electricSocketInfo.asBinder() : null);
                    if (!this.mRemote.transact(3, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onElectricSocketInfoChanged(electricSocketInfo);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.smart.ISmartCallback
            public void onLightInfoChanged(ILightInfo lightInfo) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(lightInfo != null ? lightInfo.asBinder() : null);
                    if (!this.mRemote.transact(4, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onLightInfoChanged(lightInfo);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.smart.ISmartCallback
            public void onSweepingRobotInfoChanged(ISweepingRobotInfo sweepRobotInfo) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(sweepRobotInfo != null ? sweepRobotInfo.asBinder() : null);
                    if (!this.mRemote.transact(5, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onSweepingRobotInfoChanged(sweepRobotInfo);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.smart.ISmartCallback
            public void onGoHomeModeChanged(ISceneInfo sceneInfo) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(sceneInfo != null ? sceneInfo.asBinder() : null);
                    if (!this.mRemote.transact(6, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onGoHomeModeChanged(sceneInfo);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.smart.ISmartCallback
            public void onLeaveHomeModeChanged(ISceneInfo sceneInfo) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(sceneInfo != null ? sceneInfo.asBinder() : null);
                    if (!this.mRemote.transact(7, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onLeaveHomeModeChanged(sceneInfo);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ISmartCallback impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static ISmartCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}