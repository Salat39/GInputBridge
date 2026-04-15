package com.geely.lib.oneosapi.systemui;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes.dex */
public interface IDockBarService extends IInterface {

    public static class Default implements IDockBarService {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.geely.lib.oneosapi.systemui.IDockBarService
        public void canSlide(boolean flag) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.systemui.IDockBarService
        public int getDockStatus() throws RemoteException {
            return 0;
        }

        @Override // com.geely.lib.oneosapi.systemui.IDockBarService
        public void onScrollChanged(float progress) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.systemui.IDockBarService
        public void playAnimSub(boolean isPlay) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.systemui.IDockBarService
        public void playAnimSubPsd(boolean isPlay) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.systemui.IDockBarService
        public void showDock(int status) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.systemui.IDockBarService
        public void showDockNoAnim(int status) throws RemoteException {
        }
    }

    void canSlide(boolean flag) throws RemoteException;

    int getDockStatus() throws RemoteException;

    void onScrollChanged(float progress) throws RemoteException;

    void playAnimSub(boolean isPlay) throws RemoteException;

    void playAnimSubPsd(boolean isPlay) throws RemoteException;

    void showDock(int status) throws RemoteException;

    void showDockNoAnim(int status) throws RemoteException;

    public static abstract class Stub extends Binder implements IDockBarService {
        private static final String DESCRIPTOR = "com.geely.lib.oneosapi.systemui.IDockBarService";
        static final int TRANSACTION_canSlide = 2;
        static final int TRANSACTION_getDockStatus = 7;
        static final int TRANSACTION_onScrollChanged = 6;
        static final int TRANSACTION_playAnimSub = 3;
        static final int TRANSACTION_playAnimSubPsd = 4;
        static final int TRANSACTION_showDock = 1;
        static final int TRANSACTION_showDockNoAnim = 5;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IDockBarService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface queryLocalInterface = obj.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IDockBarService)) {
                return (IDockBarService) queryLocalInterface;
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
                    showDock(data.readInt());
                    reply.writeNoException();
                    return true;
                case 2:
                    data.enforceInterface(DESCRIPTOR);
                    canSlide(data.readInt() != 0);
                    reply.writeNoException();
                    return true;
                case 3:
                    data.enforceInterface(DESCRIPTOR);
                    playAnimSub(data.readInt() != 0);
                    reply.writeNoException();
                    return true;
                case 4:
                    data.enforceInterface(DESCRIPTOR);
                    playAnimSubPsd(data.readInt() != 0);
                    reply.writeNoException();
                    return true;
                case 5:
                    data.enforceInterface(DESCRIPTOR);
                    showDockNoAnim(data.readInt());
                    reply.writeNoException();
                    return true;
                case 6:
                    data.enforceInterface(DESCRIPTOR);
                    onScrollChanged(data.readFloat());
                    reply.writeNoException();
                    return true;
                case 7:
                    data.enforceInterface(DESCRIPTOR);
                    int dockStatus = getDockStatus();
                    reply.writeNoException();
                    reply.writeInt(dockStatus);
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }

        private static class Proxy implements IDockBarService {
            public static IDockBarService sDefaultImpl;
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

            @Override // com.geely.lib.oneosapi.systemui.IDockBarService
            public void showDock(int status) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(status);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().showDock(status);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.systemui.IDockBarService
            public void canSlide(boolean flag) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(flag ? 1 : 0);
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().canSlide(flag);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.systemui.IDockBarService
            public void playAnimSub(boolean isPlay) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(isPlay ? 1 : 0);
                    if (!this.mRemote.transact(3, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().playAnimSub(isPlay);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.systemui.IDockBarService
            public void playAnimSubPsd(boolean isPlay) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(isPlay ? 1 : 0);
                    if (!this.mRemote.transact(4, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().playAnimSubPsd(isPlay);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.systemui.IDockBarService
            public void showDockNoAnim(int status) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(status);
                    if (!this.mRemote.transact(5, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().showDockNoAnim(status);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.systemui.IDockBarService
            public void onScrollChanged(float progress) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeFloat(progress);
                    if (!this.mRemote.transact(6, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onScrollChanged(progress);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.systemui.IDockBarService
            public int getDockStatus() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(7, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDockStatus();
                    }
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IDockBarService impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static IDockBarService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}