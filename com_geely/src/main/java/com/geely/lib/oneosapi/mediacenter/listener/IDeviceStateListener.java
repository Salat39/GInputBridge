package com.geely.lib.oneosapi.mediacenter.listener;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.geely.lib.oneosapi.mediacenter.bean.DeviceInfo;
import com.geely.lib.oneosapi.mediacenter.bean.MusicFileData;
import com.geely.lib.oneosapi.mediacenter.bean.OnlineUserInfo;
import com.geely.lib.oneosapi.mediacenter.bean.SearchResult;
import java.util.List;

/* loaded from: classes.dex */
public interface IDeviceStateListener extends IInterface {

    public static class Default implements IDeviceStateListener {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.geely.lib.oneosapi.mediacenter.listener.IDeviceStateListener
        public void onAppDied(int app) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.mediacenter.listener.IDeviceStateListener
        public void onAppExistStateChanged(int source, int app, boolean existed) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.mediacenter.listener.IDeviceStateListener
        public void onBluetoothDeviceChange(int source, List<DeviceInfo> deviceInfoList) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.mediacenter.listener.IDeviceStateListener
        public void onDeviceError(int source, int error, String errorMsg) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.mediacenter.listener.IDeviceStateListener
        public void onDeviceStateChanged(int source, int state, DeviceInfo info) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.mediacenter.listener.IDeviceStateListener
        public void onMediaQueryFinished(int source, DeviceInfo info) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.mediacenter.listener.IDeviceStateListener
        public void onMediaQueryStarted(int source, DeviceInfo info) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.mediacenter.listener.IDeviceStateListener
        public void onScanPathFinish(int source, List<MusicFileData> musicFileDataList) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.mediacenter.listener.IDeviceStateListener
        public void onSearchSongResult(int source, int app, List<SearchResult> searchResults) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.mediacenter.listener.IDeviceStateListener
        public void onUserInfoResult(int source, int app, OnlineUserInfo userInfo) throws RemoteException {
        }
    }

    void onAppDied(int app) throws RemoteException;

    void onAppExistStateChanged(int source, int app, boolean existed) throws RemoteException;

    void onBluetoothDeviceChange(int source, List<DeviceInfo> deviceInfoList) throws RemoteException;

    void onDeviceError(int source, int error, String errorMsg) throws RemoteException;

    void onDeviceStateChanged(int source, int state, DeviceInfo info) throws RemoteException;

    void onMediaQueryFinished(int source, DeviceInfo info) throws RemoteException;

    void onMediaQueryStarted(int source, DeviceInfo info) throws RemoteException;

    void onScanPathFinish(int source, List<MusicFileData> musicFileDataList) throws RemoteException;

    void onSearchSongResult(int source, int app, List<SearchResult> searchResults) throws RemoteException;

    void onUserInfoResult(int source, int app, OnlineUserInfo userInfo) throws RemoteException;

    public static abstract class Stub extends Binder implements IDeviceStateListener {
        private static final String DESCRIPTOR = "com.geely.lib.oneosapi.mediacenter.listener.IDeviceStateListener";
        static final int TRANSACTION_onAppDied = 8;
        static final int TRANSACTION_onAppExistStateChanged = 7;
        static final int TRANSACTION_onBluetoothDeviceChange = 6;
        static final int TRANSACTION_onDeviceError = 2;
        static final int TRANSACTION_onDeviceStateChanged = 1;
        static final int TRANSACTION_onMediaQueryFinished = 10;
        static final int TRANSACTION_onMediaQueryStarted = 9;
        static final int TRANSACTION_onScanPathFinish = 3;
        static final int TRANSACTION_onSearchSongResult = 5;
        static final int TRANSACTION_onUserInfoResult = 4;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IDeviceStateListener asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface queryLocalInterface = obj.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IDeviceStateListener)) {
                return (IDeviceStateListener) queryLocalInterface;
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
                    onDeviceStateChanged(parcel.readInt(), parcel.readInt(), parcel.readInt() != 0 ? DeviceInfo.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    onDeviceError(parcel.readInt(), parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    onScanPathFinish(parcel.readInt(), parcel.createTypedArrayList(MusicFileData.CREATOR));
                    parcel2.writeNoException();
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    onUserInfoResult(parcel.readInt(), parcel.readInt(), parcel.readInt() != 0 ? OnlineUserInfo.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    onSearchSongResult(parcel.readInt(), parcel.readInt(), parcel.createTypedArrayList(SearchResult.CREATOR));
                    parcel2.writeNoException();
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    onBluetoothDeviceChange(parcel.readInt(), parcel.createTypedArrayList(DeviceInfo.CREATOR));
                    parcel2.writeNoException();
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    onAppExistStateChanged(parcel.readInt(), parcel.readInt(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    onAppDied(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    onMediaQueryStarted(parcel.readInt(), parcel.readInt() != 0 ? DeviceInfo.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    onMediaQueryFinished(parcel.readInt(), parcel.readInt() != 0 ? DeviceInfo.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IDeviceStateListener {
            public static IDeviceStateListener sDefaultImpl;
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

            @Override // com.geely.lib.oneosapi.mediacenter.listener.IDeviceStateListener
            public void onDeviceStateChanged(int source, int state, DeviceInfo info) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(source);
                    obtain.writeInt(state);
                    if (info != null) {
                        obtain.writeInt(1);
                        info.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onDeviceStateChanged(source, state, info);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.mediacenter.listener.IDeviceStateListener
            public void onDeviceError(int source, int error, String errorMsg) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(source);
                    obtain.writeInt(error);
                    obtain.writeString(errorMsg);
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onDeviceError(source, error, errorMsg);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.mediacenter.listener.IDeviceStateListener
            public void onScanPathFinish(int source, List<MusicFileData> musicFileDataList) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(source);
                    obtain.writeTypedList(musicFileDataList);
                    if (!this.mRemote.transact(3, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onScanPathFinish(source, musicFileDataList);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.mediacenter.listener.IDeviceStateListener
            public void onUserInfoResult(int source, int app, OnlineUserInfo userInfo) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(source);
                    obtain.writeInt(app);
                    if (userInfo != null) {
                        obtain.writeInt(1);
                        userInfo.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(4, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onUserInfoResult(source, app, userInfo);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.mediacenter.listener.IDeviceStateListener
            public void onSearchSongResult(int source, int app, List<SearchResult> searchResults) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(source);
                    obtain.writeInt(app);
                    obtain.writeTypedList(searchResults);
                    if (!this.mRemote.transact(5, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onSearchSongResult(source, app, searchResults);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.mediacenter.listener.IDeviceStateListener
            public void onBluetoothDeviceChange(int source, List<DeviceInfo> deviceInfoList) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(source);
                    obtain.writeTypedList(deviceInfoList);
                    if (!this.mRemote.transact(6, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onBluetoothDeviceChange(source, deviceInfoList);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.mediacenter.listener.IDeviceStateListener
            public void onAppExistStateChanged(int source, int app, boolean existed) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(source);
                    obtain.writeInt(app);
                    obtain.writeInt(existed ? 1 : 0);
                    if (!this.mRemote.transact(7, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onAppExistStateChanged(source, app, existed);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.mediacenter.listener.IDeviceStateListener
            public void onAppDied(int app) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(app);
                    if (!this.mRemote.transact(8, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onAppDied(app);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.mediacenter.listener.IDeviceStateListener
            public void onMediaQueryStarted(int source, DeviceInfo info) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(source);
                    if (info != null) {
                        obtain.writeInt(1);
                        info.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(9, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onMediaQueryStarted(source, info);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.mediacenter.listener.IDeviceStateListener
            public void onMediaQueryFinished(int source, DeviceInfo info) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(source);
                    if (info != null) {
                        obtain.writeInt(1);
                        info.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(10, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onMediaQueryFinished(source, info);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IDeviceStateListener impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static IDeviceStateListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}