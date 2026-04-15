package com.geely.lib.oneosapi.smart;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes.dex */
public interface IAirPurificationInfo extends IInterface {

    public static class Default implements IAirPurificationInfo {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.geely.lib.oneosapi.smart.IAirPurificationInfo
        public String getId() throws RemoteException {
            return null;
        }

        @Override // com.geely.lib.oneosapi.smart.IAirPurificationInfo
        public int getSwitchOpen() throws RemoteException {
            return 0;
        }

        @Override // com.geely.lib.oneosapi.smart.IAirPurificationInfo
        public int getWindSpeed() throws RemoteException {
            return 0;
        }

        @Override // com.geely.lib.oneosapi.smart.IAirPurificationInfo
        public String getWorkModel() throws RemoteException {
            return null;
        }
    }

    String getId() throws RemoteException;

    int getSwitchOpen() throws RemoteException;

    int getWindSpeed() throws RemoteException;

    String getWorkModel() throws RemoteException;

    public static abstract class Stub extends Binder implements IAirPurificationInfo {
        private static final String DESCRIPTOR = "com.geely.lib.oneosapi.smart.IAirPurificationInfo";
        static final int TRANSACTION_getId = 4;
        static final int TRANSACTION_getSwitchOpen = 1;
        static final int TRANSACTION_getWindSpeed = 2;
        static final int TRANSACTION_getWorkModel = 3;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IAirPurificationInfo asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface queryLocalInterface = obj.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IAirPurificationInfo)) {
                return (IAirPurificationInfo) queryLocalInterface;
            }
            return new Proxy(obj);
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            if (code == 1) {
                data.enforceInterface(DESCRIPTOR);
                int switchOpen = getSwitchOpen();
                reply.writeNoException();
                reply.writeInt(switchOpen);
                return true;
            }
            if (code == 2) {
                data.enforceInterface(DESCRIPTOR);
                int windSpeed = getWindSpeed();
                reply.writeNoException();
                reply.writeInt(windSpeed);
                return true;
            }
            if (code == 3) {
                data.enforceInterface(DESCRIPTOR);
                String workModel = getWorkModel();
                reply.writeNoException();
                reply.writeString(workModel);
                return true;
            }
            if (code != 4) {
                if (code == 1598968902) {
                    reply.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(code, data, reply, flags);
            }
            data.enforceInterface(DESCRIPTOR);
            String id = getId();
            reply.writeNoException();
            reply.writeString(id);
            return true;
        }

        private static class Proxy implements IAirPurificationInfo {
            public static IAirPurificationInfo sDefaultImpl;
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

            @Override // com.geely.lib.oneosapi.smart.IAirPurificationInfo
            public int getSwitchOpen() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSwitchOpen();
                    }
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.smart.IAirPurificationInfo
            public int getWindSpeed() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getWindSpeed();
                    }
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.smart.IAirPurificationInfo
            public String getWorkModel() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(3, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getWorkModel();
                    }
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.smart.IAirPurificationInfo
            public String getId() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(4, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getId();
                    }
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IAirPurificationInfo impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static IAirPurificationInfo getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}