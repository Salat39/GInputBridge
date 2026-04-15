package com.geely.lib.oneosapi;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.geely.lib.oneosapi.IServiceManagerCallback;

/* loaded from: classes.dex */
public interface IServiceManager extends IInterface {

    public static class Default implements IServiceManager {
        @Override // com.geely.lib.oneosapi.IServiceManager
        public void addService(int type, IBinder binder) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.geely.lib.oneosapi.IServiceManager
        public IBinder getPermissionService(int type, String pkg) throws RemoteException {
            return null;
        }

        @Override // com.geely.lib.oneosapi.IServiceManager
        public IBinder getService(int type) throws RemoteException {
            return null;
        }

        @Override // com.geely.lib.oneosapi.IServiceManager
        public void registerCallback(IServiceManagerCallback callback) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.IServiceManager
        public void unregisterCallback(IServiceManagerCallback callback) throws RemoteException {
        }
    }

    void addService(int type, IBinder binder) throws RemoteException;

    IBinder getPermissionService(int type, String pkg) throws RemoteException;

    IBinder getService(int type) throws RemoteException;

    void registerCallback(IServiceManagerCallback callback) throws RemoteException;

    void unregisterCallback(IServiceManagerCallback callback) throws RemoteException;

    public static abstract class Stub extends Binder implements IServiceManager {
        private static final String DESCRIPTOR = "com.geely.lib.oneosapi.IServiceManager";
        static final int TRANSACTION_addService = 1;
        static final int TRANSACTION_getPermissionService = 3;
        static final int TRANSACTION_getService = 2;
        static final int TRANSACTION_registerCallback = 4;
        static final int TRANSACTION_unregisterCallback = 5;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IServiceManager asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface queryLocalInterface = obj.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IServiceManager)) {
                return (IServiceManager) queryLocalInterface;
            }
            return new Proxy(obj);
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            if (code == 1) {
                data.enforceInterface(DESCRIPTOR);
                addService(data.readInt(), data.readStrongBinder());
                reply.writeNoException();
                return true;
            }
            if (code == 2) {
                data.enforceInterface(DESCRIPTOR);
                IBinder service = getService(data.readInt());
                reply.writeNoException();
                reply.writeStrongBinder(service);
                return true;
            }
            if (code == 3) {
                data.enforceInterface(DESCRIPTOR);
                IBinder permissionService = getPermissionService(data.readInt(), data.readString());
                reply.writeNoException();
                reply.writeStrongBinder(permissionService);
                return true;
            }
            if (code == 4) {
                data.enforceInterface(DESCRIPTOR);
                registerCallback(IServiceManagerCallback.Stub.asInterface(data.readStrongBinder()));
                return true;
            }
            if (code == 5) {
                data.enforceInterface(DESCRIPTOR);
                unregisterCallback(IServiceManagerCallback.Stub.asInterface(data.readStrongBinder()));
                return true;
            }
            if (code == 1598968902) {
                reply.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(code, data, reply, flags);
        }

        private static class Proxy implements IServiceManager {
            public static IServiceManager sDefaultImpl;
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

            @Override // com.geely.lib.oneosapi.IServiceManager
            public void addService(int type, IBinder binder) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(type);
                    obtain.writeStrongBinder(binder);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().addService(type, binder);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.IServiceManager
            public IBinder getService(int type) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(type);
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getService(type);
                    }
                    obtain2.readException();
                    return obtain2.readStrongBinder();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.IServiceManager
            public IBinder getPermissionService(int type, String pkg) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(type);
                    obtain.writeString(pkg);
                    if (!this.mRemote.transact(3, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPermissionService(type, pkg);
                    }
                    obtain2.readException();
                    return obtain2.readStrongBinder();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.IServiceManager
            public void registerCallback(IServiceManagerCallback callback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    if (this.mRemote.transact(4, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().registerCallback(callback);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.IServiceManager
            public void unregisterCallback(IServiceManagerCallback callback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    if (this.mRemote.transact(5, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().unregisterCallback(callback);
                } finally {
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IServiceManager impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static IServiceManager getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}