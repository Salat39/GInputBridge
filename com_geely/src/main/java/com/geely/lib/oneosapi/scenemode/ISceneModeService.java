package com.geely.lib.oneosapi.scenemode;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.geely.lib.oneosapi.scenemode.ISceneModeServiceChangedListener;

/* loaded from: classes.dex */
public interface ISceneModeService extends IInterface {

    public static class Default implements ISceneModeService {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.geely.lib.oneosapi.scenemode.ISceneModeService
        public void enterIntoModeById(String sceneId) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.scenemode.ISceneModeService
        public void executeModeById(String sceneId, boolean isOpen) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.scenemode.ISceneModeService
        public int executeSceneModeById(String sceneId, boolean isOpen) throws RemoteException {
            return 0;
        }

        @Override // com.geely.lib.oneosapi.scenemode.ISceneModeService
        public int getSceneModeFrontState() throws RemoteException {
            return 0;
        }

        @Override // com.geely.lib.oneosapi.scenemode.ISceneModeService
        public String getSceneModeName() throws RemoteException {
            return null;
        }

        @Override // com.geely.lib.oneosapi.scenemode.ISceneModeService
        public int getSceneModeOpenState() throws RemoteException {
            return 0;
        }

        @Override // com.geely.lib.oneosapi.scenemode.ISceneModeService
        public void registerSceneModeServiceChangedListener(ISceneModeServiceChangedListener listener) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.scenemode.ISceneModeService
        public void unRegisterSceneModeServiceChangedListener(ISceneModeServiceChangedListener listener) throws RemoteException {
        }
    }

    void enterIntoModeById(String sceneId) throws RemoteException;

    void executeModeById(String sceneId, boolean isOpen) throws RemoteException;

    int executeSceneModeById(String sceneId, boolean isOpen) throws RemoteException;

    int getSceneModeFrontState() throws RemoteException;

    String getSceneModeName() throws RemoteException;

    int getSceneModeOpenState() throws RemoteException;

    void registerSceneModeServiceChangedListener(ISceneModeServiceChangedListener listener) throws RemoteException;

    void unRegisterSceneModeServiceChangedListener(ISceneModeServiceChangedListener listener) throws RemoteException;

    public static abstract class Stub extends Binder implements ISceneModeService {
        private static final String DESCRIPTOR = "com.geely.lib.oneosapi.scenemode.ISceneModeService";
        static final int TRANSACTION_enterIntoModeById = 3;
        static final int TRANSACTION_executeModeById = 1;
        static final int TRANSACTION_executeSceneModeById = 2;
        static final int TRANSACTION_getSceneModeFrontState = 5;
        static final int TRANSACTION_getSceneModeName = 6;
        static final int TRANSACTION_getSceneModeOpenState = 4;
        static final int TRANSACTION_registerSceneModeServiceChangedListener = 7;
        static final int TRANSACTION_unRegisterSceneModeServiceChangedListener = 8;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ISceneModeService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface queryLocalInterface = obj.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof ISceneModeService)) {
                return (ISceneModeService) queryLocalInterface;
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
                    executeModeById(data.readString(), data.readInt() != 0);
                    reply.writeNoException();
                    return true;
                case 2:
                    data.enforceInterface(DESCRIPTOR);
                    int executeSceneModeById = executeSceneModeById(data.readString(), data.readInt() != 0);
                    reply.writeNoException();
                    reply.writeInt(executeSceneModeById);
                    return true;
                case 3:
                    data.enforceInterface(DESCRIPTOR);
                    enterIntoModeById(data.readString());
                    reply.writeNoException();
                    return true;
                case 4:
                    data.enforceInterface(DESCRIPTOR);
                    int sceneModeOpenState = getSceneModeOpenState();
                    reply.writeNoException();
                    reply.writeInt(sceneModeOpenState);
                    return true;
                case 5:
                    data.enforceInterface(DESCRIPTOR);
                    int sceneModeFrontState = getSceneModeFrontState();
                    reply.writeNoException();
                    reply.writeInt(sceneModeFrontState);
                    return true;
                case 6:
                    data.enforceInterface(DESCRIPTOR);
                    String sceneModeName = getSceneModeName();
                    reply.writeNoException();
                    reply.writeString(sceneModeName);
                    return true;
                case 7:
                    data.enforceInterface(DESCRIPTOR);
                    registerSceneModeServiceChangedListener(ISceneModeServiceChangedListener.Stub.asInterface(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case 8:
                    data.enforceInterface(DESCRIPTOR);
                    unRegisterSceneModeServiceChangedListener(ISceneModeServiceChangedListener.Stub.asInterface(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }

        private static class Proxy implements ISceneModeService {
            public static ISceneModeService sDefaultImpl;
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

            @Override // com.geely.lib.oneosapi.scenemode.ISceneModeService
            public void executeModeById(String sceneId, boolean isOpen) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(sceneId);
                    obtain.writeInt(isOpen ? 1 : 0);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().executeModeById(sceneId, isOpen);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.scenemode.ISceneModeService
            public int executeSceneModeById(String sceneId, boolean isOpen) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(sceneId);
                    obtain.writeInt(isOpen ? 1 : 0);
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().executeSceneModeById(sceneId, isOpen);
                    }
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.scenemode.ISceneModeService
            public void enterIntoModeById(String sceneId) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(sceneId);
                    if (!this.mRemote.transact(3, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().enterIntoModeById(sceneId);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.scenemode.ISceneModeService
            public int getSceneModeOpenState() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(4, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSceneModeOpenState();
                    }
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.scenemode.ISceneModeService
            public int getSceneModeFrontState() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(5, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSceneModeFrontState();
                    }
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.scenemode.ISceneModeService
            public String getSceneModeName() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(6, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSceneModeName();
                    }
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.scenemode.ISceneModeService
            public void registerSceneModeServiceChangedListener(ISceneModeServiceChangedListener listener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    if (!this.mRemote.transact(7, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerSceneModeServiceChangedListener(listener);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.scenemode.ISceneModeService
            public void unRegisterSceneModeServiceChangedListener(ISceneModeServiceChangedListener listener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    if (!this.mRemote.transact(8, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unRegisterSceneModeServiceChangedListener(listener);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ISceneModeService impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static ISceneModeService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}