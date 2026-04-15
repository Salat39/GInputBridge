package com.geely.lib.oneosapi.smart;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes.dex */
public interface ISceneInfo extends IInterface {

    public static class Default implements ISceneInfo {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.geely.lib.oneosapi.smart.ISceneInfo
        public int getErrorStatus() throws RemoteException {
            return 0;
        }

        @Override // com.geely.lib.oneosapi.smart.ISceneInfo
        public int getOpenStatus() throws RemoteException {
            return 0;
        }

        @Override // com.geely.lib.oneosapi.smart.ISceneInfo
        public String getSceneId() throws RemoteException {
            return null;
        }

        @Override // com.geely.lib.oneosapi.smart.ISceneInfo
        public String getType() throws RemoteException {
            return null;
        }
    }

    int getErrorStatus() throws RemoteException;

    int getOpenStatus() throws RemoteException;

    String getSceneId() throws RemoteException;

    String getType() throws RemoteException;

    public static abstract class Stub extends Binder implements ISceneInfo {
        private static final String DESCRIPTOR = "com.geely.lib.oneosapi.smart.ISceneInfo";
        static final int TRANSACTION_getErrorStatus = 4;
        static final int TRANSACTION_getOpenStatus = 2;
        static final int TRANSACTION_getSceneId = 1;
        static final int TRANSACTION_getType = 3;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ISceneInfo asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface queryLocalInterface = obj.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof ISceneInfo)) {
                return (ISceneInfo) queryLocalInterface;
            }
            return new Proxy(obj);
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            if (code == 1) {
                data.enforceInterface(DESCRIPTOR);
                String sceneId = getSceneId();
                reply.writeNoException();
                reply.writeString(sceneId);
                return true;
            }
            if (code == 2) {
                data.enforceInterface(DESCRIPTOR);
                int openStatus = getOpenStatus();
                reply.writeNoException();
                reply.writeInt(openStatus);
                return true;
            }
            if (code == 3) {
                data.enforceInterface(DESCRIPTOR);
                String type = getType();
                reply.writeNoException();
                reply.writeString(type);
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
            int errorStatus = getErrorStatus();
            reply.writeNoException();
            reply.writeInt(errorStatus);
            return true;
        }

        private static class Proxy implements ISceneInfo {
            public static ISceneInfo sDefaultImpl;
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

            @Override // com.geely.lib.oneosapi.smart.ISceneInfo
            public String getSceneId() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSceneId();
                    }
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.smart.ISceneInfo
            public int getOpenStatus() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getOpenStatus();
                    }
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.smart.ISceneInfo
            public String getType() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(3, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getType();
                    }
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.smart.ISceneInfo
            public int getErrorStatus() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(4, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getErrorStatus();
                    }
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ISceneInfo impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static ISceneInfo getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}