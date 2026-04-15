package com.geely.lib.oneosapi.smart;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.geely.lib.oneosapi.smart.IResultCallback;
import com.geely.lib.oneosapi.smart.scene.IGetSceneListCallBack;
import com.geely.lib.oneosapi.smart.scene.ISceneChangeCallBack;

/* loaded from: classes.dex */
public interface ISmartApi extends IInterface {

    public static class Default implements ISmartApi {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.geely.lib.oneosapi.smart.ISmartApi
        public void controlACTemp(String location, String setType, int targetTemp, int adjustTemp, IResultCallback callback) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.smart.ISmartApi
        public void controlDeviceMode(String location, int deviceType, int mode, IResultCallback callback) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.smart.ISmartApi
        public void controlDeviceSpeed(String location, int deviceType, int speed, IResultCallback callback) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.smart.ISmartApi
        public void controlDeviceSwitch(String location, int deviceType, boolean isOpen, IResultCallback callback) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.smart.ISmartApi
        public void controlSceneMode(boolean isOpen, String sceneName, IResultCallback callback) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.smart.ISmartApi
        public void getAllSceneList(IGetSceneListCallBack callback) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.smart.ISmartApi
        public void registerSceneChangeListener(ISceneChangeCallBack callback) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.smart.ISmartApi
        public void unregisterSceneChangeListener(ISceneChangeCallBack callback) throws RemoteException {
        }
    }

    void controlACTemp(String location, String setType, int targetTemp, int adjustTemp, IResultCallback callback) throws RemoteException;

    void controlDeviceMode(String location, int deviceType, int mode, IResultCallback callback) throws RemoteException;

    void controlDeviceSpeed(String location, int deviceType, int speed, IResultCallback callback) throws RemoteException;

    void controlDeviceSwitch(String location, int deviceType, boolean isOpen, IResultCallback callback) throws RemoteException;

    void controlSceneMode(boolean isOpen, String sceneName, IResultCallback callback) throws RemoteException;

    void getAllSceneList(IGetSceneListCallBack callback) throws RemoteException;

    void registerSceneChangeListener(ISceneChangeCallBack callback) throws RemoteException;

    void unregisterSceneChangeListener(ISceneChangeCallBack callback) throws RemoteException;

    public static abstract class Stub extends Binder implements ISmartApi {
        private static final String DESCRIPTOR = "com.geely.lib.oneosapi.smart.ISmartApi";
        static final int TRANSACTION_controlACTemp = 4;
        static final int TRANSACTION_controlDeviceMode = 2;
        static final int TRANSACTION_controlDeviceSpeed = 3;
        static final int TRANSACTION_controlDeviceSwitch = 1;
        static final int TRANSACTION_controlSceneMode = 5;
        static final int TRANSACTION_getAllSceneList = 6;
        static final int TRANSACTION_registerSceneChangeListener = 7;
        static final int TRANSACTION_unregisterSceneChangeListener = 8;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ISmartApi asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface queryLocalInterface = obj.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof ISmartApi)) {
                return (ISmartApi) queryLocalInterface;
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
                    controlDeviceSwitch(data.readString(), data.readInt(), data.readInt() != 0, IResultCallback.Stub.asInterface(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case 2:
                    data.enforceInterface(DESCRIPTOR);
                    controlDeviceMode(data.readString(), data.readInt(), data.readInt(), IResultCallback.Stub.asInterface(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case 3:
                    data.enforceInterface(DESCRIPTOR);
                    controlDeviceSpeed(data.readString(), data.readInt(), data.readInt(), IResultCallback.Stub.asInterface(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case 4:
                    data.enforceInterface(DESCRIPTOR);
                    controlACTemp(data.readString(), data.readString(), data.readInt(), data.readInt(), IResultCallback.Stub.asInterface(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case 5:
                    data.enforceInterface(DESCRIPTOR);
                    controlSceneMode(data.readInt() != 0, data.readString(), IResultCallback.Stub.asInterface(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case 6:
                    data.enforceInterface(DESCRIPTOR);
                    getAllSceneList(IGetSceneListCallBack.Stub.asInterface(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case 7:
                    data.enforceInterface(DESCRIPTOR);
                    registerSceneChangeListener(ISceneChangeCallBack.Stub.asInterface(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case 8:
                    data.enforceInterface(DESCRIPTOR);
                    unregisterSceneChangeListener(ISceneChangeCallBack.Stub.asInterface(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }

        private static class Proxy implements ISmartApi {
            public static ISmartApi sDefaultImpl;
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

            @Override // com.geely.lib.oneosapi.smart.ISmartApi
            public void controlDeviceSwitch(String location, int deviceType, boolean isOpen, IResultCallback callback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(location);
                    obtain.writeInt(deviceType);
                    obtain.writeInt(isOpen ? 1 : 0);
                    obtain.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().controlDeviceSwitch(location, deviceType, isOpen, callback);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.smart.ISmartApi
            public void controlDeviceMode(String location, int deviceType, int mode, IResultCallback callback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(location);
                    obtain.writeInt(deviceType);
                    obtain.writeInt(mode);
                    obtain.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().controlDeviceMode(location, deviceType, mode, callback);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.smart.ISmartApi
            public void controlDeviceSpeed(String location, int deviceType, int speed, IResultCallback callback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(location);
                    obtain.writeInt(deviceType);
                    obtain.writeInt(speed);
                    obtain.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    if (!this.mRemote.transact(3, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().controlDeviceSpeed(location, deviceType, speed, callback);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.smart.ISmartApi
            public void controlACTemp(String location, String setType, int targetTemp, int adjustTemp, IResultCallback callback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(location);
                    obtain.writeString(setType);
                    obtain.writeInt(targetTemp);
                    obtain.writeInt(adjustTemp);
                    obtain.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    if (!this.mRemote.transact(4, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().controlACTemp(location, setType, targetTemp, adjustTemp, callback);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.smart.ISmartApi
            public void controlSceneMode(boolean isOpen, String sceneName, IResultCallback callback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(isOpen ? 1 : 0);
                    obtain.writeString(sceneName);
                    obtain.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    if (!this.mRemote.transact(5, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().controlSceneMode(isOpen, sceneName, callback);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.smart.ISmartApi
            public void getAllSceneList(IGetSceneListCallBack callback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    if (!this.mRemote.transact(6, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().getAllSceneList(callback);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.smart.ISmartApi
            public void registerSceneChangeListener(ISceneChangeCallBack callback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    if (!this.mRemote.transact(7, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerSceneChangeListener(callback);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.smart.ISmartApi
            public void unregisterSceneChangeListener(ISceneChangeCallBack callback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    if (!this.mRemote.transact(8, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterSceneChangeListener(callback);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ISmartApi impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static ISmartApi getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}