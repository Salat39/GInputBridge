package com.geely.lib.oneosapi.theme;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.geely.lib.oneosapi.theme.IApplyByLauncherCallback;
import com.geely.lib.oneosapi.theme.IDressApplyCallback;
import com.geely.lib.oneosapi.theme.IDressDelCallback;
import com.geely.lib.oneosapi.theme.IDressDlCallback;
import com.geely.lib.oneosapi.theme.IGetOwnerStaticWallpaperCallback;
import com.geely.lib.oneosapi.theme.IGetOwnerVideoWallpaperCallback;

/* loaded from: classes.dex */
public interface IThemeService extends IInterface {

    public static class Default implements IThemeService {
        @Override // com.geely.lib.oneosapi.theme.IThemeService
        public void applyDress(String cardGoodsVoDataJson, String taskEntityJson, IDressApplyCallback iDressApplyCb) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.theme.IThemeService
        public void applyLiveWallpaper() throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.theme.IThemeService
        public void applyStaticWallpaper() throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.theme.IThemeService
        public void applyWallpaperByLauncher(String cardGoodsVoDataJson, boolean isApplyCSD, boolean isApplyPSD, IApplyByLauncherCallback applyByLauncherCallback) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.geely.lib.oneosapi.theme.IThemeService
        public void delDressFile(String filePath, String goodsId, IDressDelCallback iDressDelCallback) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.theme.IThemeService
        public void downloadDress(String cardGoodsVoDataJson, String taskEntityJson, IDressDlCallback iDressDlCb) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.theme.IThemeService
        public void dressStaticWallpaper(int screenType) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.theme.IThemeService
        public int getCurrentWallpaperType() throws RemoteException {
            return 0;
        }

        @Override // com.geely.lib.oneosapi.theme.IThemeService
        public void getOwnerStaticWallpaperData(IGetOwnerStaticWallpaperCallback getOwnerStaticWallpaperCallback) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.theme.IThemeService
        public void getOwnerVideoWallpaperData(IGetOwnerVideoWallpaperCallback getOwnerVideoWallpaperCallback) throws RemoteException {
        }
    }

    void applyDress(String cardGoodsVoDataJson, String taskEntityJson, IDressApplyCallback iDressApplyCb) throws RemoteException;

    void applyLiveWallpaper() throws RemoteException;

    void applyStaticWallpaper() throws RemoteException;

    void applyWallpaperByLauncher(String cardGoodsVoDataJson, boolean isApplyCSD, boolean isApplyPSD, IApplyByLauncherCallback applyByLauncherCallback) throws RemoteException;

    void delDressFile(String filePath, String goodsId, IDressDelCallback iDressDelCallback) throws RemoteException;

    void downloadDress(String cardGoodsVoDataJson, String taskEntityJson, IDressDlCallback iDressDlCb) throws RemoteException;

    void dressStaticWallpaper(int screenType) throws RemoteException;

    int getCurrentWallpaperType() throws RemoteException;

    void getOwnerStaticWallpaperData(IGetOwnerStaticWallpaperCallback getOwnerStaticWallpaperCallback) throws RemoteException;

    void getOwnerVideoWallpaperData(IGetOwnerVideoWallpaperCallback getOwnerVideoWallpaperCallback) throws RemoteException;

    public static abstract class Stub extends Binder implements IThemeService {
        private static final String DESCRIPTOR = "com.geely.lib.oneosapi.theme.IThemeService";
        static final int TRANSACTION_applyDress = 4;
        static final int TRANSACTION_applyLiveWallpaper = 2;
        static final int TRANSACTION_applyStaticWallpaper = 1;
        static final int TRANSACTION_applyWallpaperByLauncher = 9;
        static final int TRANSACTION_delDressFile = 5;
        static final int TRANSACTION_downloadDress = 3;
        static final int TRANSACTION_dressStaticWallpaper = 10;
        static final int TRANSACTION_getCurrentWallpaperType = 6;
        static final int TRANSACTION_getOwnerStaticWallpaperData = 7;
        static final int TRANSACTION_getOwnerVideoWallpaperData = 8;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IThemeService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface queryLocalInterface = obj.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IThemeService)) {
                return (IThemeService) queryLocalInterface;
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
                    applyStaticWallpaper();
                    reply.writeNoException();
                    return true;
                case 2:
                    data.enforceInterface(DESCRIPTOR);
                    applyLiveWallpaper();
                    reply.writeNoException();
                    return true;
                case 3:
                    data.enforceInterface(DESCRIPTOR);
                    downloadDress(data.readString(), data.readString(), IDressDlCallback.Stub.asInterface(data.readStrongBinder()));
                    return true;
                case 4:
                    data.enforceInterface(DESCRIPTOR);
                    applyDress(data.readString(), data.readString(), IDressApplyCallback.Stub.asInterface(data.readStrongBinder()));
                    return true;
                case 5:
                    data.enforceInterface(DESCRIPTOR);
                    delDressFile(data.readString(), data.readString(), IDressDelCallback.Stub.asInterface(data.readStrongBinder()));
                    return true;
                case 6:
                    data.enforceInterface(DESCRIPTOR);
                    int currentWallpaperType = getCurrentWallpaperType();
                    reply.writeNoException();
                    reply.writeInt(currentWallpaperType);
                    return true;
                case 7:
                    data.enforceInterface(DESCRIPTOR);
                    getOwnerStaticWallpaperData(IGetOwnerStaticWallpaperCallback.Stub.asInterface(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case 8:
                    data.enforceInterface(DESCRIPTOR);
                    getOwnerVideoWallpaperData(IGetOwnerVideoWallpaperCallback.Stub.asInterface(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case 9:
                    data.enforceInterface(DESCRIPTOR);
                    applyWallpaperByLauncher(data.readString(), data.readInt() != 0, data.readInt() != 0, IApplyByLauncherCallback.Stub.asInterface(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case 10:
                    data.enforceInterface(DESCRIPTOR);
                    dressStaticWallpaper(data.readInt());
                    reply.writeNoException();
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }

        private static class Proxy implements IThemeService {
            public static IThemeService sDefaultImpl;
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

            @Override // com.geely.lib.oneosapi.theme.IThemeService
            public void applyStaticWallpaper() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().applyStaticWallpaper();
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.theme.IThemeService
            public void applyLiveWallpaper() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().applyLiveWallpaper();
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.theme.IThemeService
            public void downloadDress(String cardGoodsVoDataJson, String taskEntityJson, IDressDlCallback iDressDlCb) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(cardGoodsVoDataJson);
                    obtain.writeString(taskEntityJson);
                    obtain.writeStrongBinder(iDressDlCb != null ? iDressDlCb.asBinder() : null);
                    if (this.mRemote.transact(3, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().downloadDress(cardGoodsVoDataJson, taskEntityJson, iDressDlCb);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.theme.IThemeService
            public void applyDress(String cardGoodsVoDataJson, String taskEntityJson, IDressApplyCallback iDressApplyCb) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(cardGoodsVoDataJson);
                    obtain.writeString(taskEntityJson);
                    obtain.writeStrongBinder(iDressApplyCb != null ? iDressApplyCb.asBinder() : null);
                    if (this.mRemote.transact(4, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().applyDress(cardGoodsVoDataJson, taskEntityJson, iDressApplyCb);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.theme.IThemeService
            public void delDressFile(String filePath, String goodsId, IDressDelCallback iDressDelCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(filePath);
                    obtain.writeString(goodsId);
                    obtain.writeStrongBinder(iDressDelCallback != null ? iDressDelCallback.asBinder() : null);
                    if (this.mRemote.transact(5, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().delDressFile(filePath, goodsId, iDressDelCallback);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.theme.IThemeService
            public int getCurrentWallpaperType() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(6, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCurrentWallpaperType();
                    }
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.theme.IThemeService
            public void getOwnerStaticWallpaperData(IGetOwnerStaticWallpaperCallback getOwnerStaticWallpaperCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(getOwnerStaticWallpaperCallback != null ? getOwnerStaticWallpaperCallback.asBinder() : null);
                    if (!this.mRemote.transact(7, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().getOwnerStaticWallpaperData(getOwnerStaticWallpaperCallback);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.theme.IThemeService
            public void getOwnerVideoWallpaperData(IGetOwnerVideoWallpaperCallback getOwnerVideoWallpaperCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(getOwnerVideoWallpaperCallback != null ? getOwnerVideoWallpaperCallback.asBinder() : null);
                    if (!this.mRemote.transact(8, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().getOwnerVideoWallpaperData(getOwnerVideoWallpaperCallback);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.theme.IThemeService
            public void applyWallpaperByLauncher(String cardGoodsVoDataJson, boolean isApplyCSD, boolean isApplyPSD, IApplyByLauncherCallback applyByLauncherCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(cardGoodsVoDataJson);
                    int i = 1;
                    obtain.writeInt(isApplyCSD ? 1 : 0);
                    if (!isApplyPSD) {
                        i = 0;
                    }
                    obtain.writeInt(i);
                    obtain.writeStrongBinder(applyByLauncherCallback != null ? applyByLauncherCallback.asBinder() : null);
                    if (!this.mRemote.transact(9, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().applyWallpaperByLauncher(cardGoodsVoDataJson, isApplyCSD, isApplyPSD, applyByLauncherCallback);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.theme.IThemeService
            public void dressStaticWallpaper(int screenType) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(screenType);
                    if (!this.mRemote.transact(10, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().dressStaticWallpaper(screenType);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IThemeService impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static IThemeService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}