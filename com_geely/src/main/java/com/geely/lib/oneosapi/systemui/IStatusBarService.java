package com.geely.lib.oneosapi.systemui;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.geely.lib.oneosapi.systemui.listener.IStatusBarModeChangeListener;

/* loaded from: classes.dex */
public interface IStatusBarService extends IInterface {

    public static class Default implements IStatusBarService {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.geely.lib.oneosapi.systemui.IStatusBarService
        public void closeDialogByName(String dialogName) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.systemui.IStatusBarService
        public int getPsdStatusBarVisibility() throws RemoteException {
            return 0;
        }

        @Override // com.geely.lib.oneosapi.systemui.IStatusBarService
        public Bundle getStatusBarWindowMode() throws RemoteException {
            return null;
        }

        @Override // com.geely.lib.oneosapi.systemui.IStatusBarService
        public void registerStatusBarModeChangeListener(IStatusBarModeChangeListener listener, String pkg) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.systemui.IStatusBarService
        public void setCsdActivity(IBinder csdAcitvity) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.systemui.IStatusBarService
        public void setLeftTempStateVisibility(String leftTempStateContents, int visibility) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.systemui.IStatusBarService
        public void setPsdActivity(IBinder psdAcitvity) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.systemui.IStatusBarService
        public void setPsdStatusVisibility(int visibility) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.systemui.IStatusBarService
        public void showDialogByName(String dialogName) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.systemui.IStatusBarService
        public void unRegisterStatusBarModeChangeListener(IStatusBarModeChangeListener listener, String pkg) throws RemoteException {
        }
    }

    void closeDialogByName(String dialogName) throws RemoteException;

    int getPsdStatusBarVisibility() throws RemoteException;

    Bundle getStatusBarWindowMode() throws RemoteException;

    void registerStatusBarModeChangeListener(IStatusBarModeChangeListener listener, String pkg) throws RemoteException;

    void setCsdActivity(IBinder csdAcitvity) throws RemoteException;

    void setLeftTempStateVisibility(String leftTempStateContents, int visibility) throws RemoteException;

    void setPsdActivity(IBinder psdAcitvity) throws RemoteException;

    void setPsdStatusVisibility(int visibility) throws RemoteException;

    void showDialogByName(String dialogName) throws RemoteException;

    void unRegisterStatusBarModeChangeListener(IStatusBarModeChangeListener listener, String pkg) throws RemoteException;

    public static abstract class Stub extends Binder implements IStatusBarService {
        private static final String DESCRIPTOR = "com.geely.lib.oneosapi.systemui.IStatusBarService";
        static final int TRANSACTION_closeDialogByName = 5;
        static final int TRANSACTION_getPsdStatusBarVisibility = 6;
        static final int TRANSACTION_getStatusBarWindowMode = 8;
        static final int TRANSACTION_registerStatusBarModeChangeListener = 9;
        static final int TRANSACTION_setCsdActivity = 3;
        static final int TRANSACTION_setLeftTempStateVisibility = 7;
        static final int TRANSACTION_setPsdActivity = 4;
        static final int TRANSACTION_setPsdStatusVisibility = 2;
        static final int TRANSACTION_showDialogByName = 1;
        static final int TRANSACTION_unRegisterStatusBarModeChangeListener = 10;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IStatusBarService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface queryLocalInterface = obj.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IStatusBarService)) {
                return (IStatusBarService) queryLocalInterface;
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
                    showDialogByName(data.readString());
                    reply.writeNoException();
                    return true;
                case 2:
                    data.enforceInterface(DESCRIPTOR);
                    setPsdStatusVisibility(data.readInt());
                    reply.writeNoException();
                    return true;
                case 3:
                    data.enforceInterface(DESCRIPTOR);
                    setCsdActivity(data.readStrongBinder());
                    reply.writeNoException();
                    return true;
                case 4:
                    data.enforceInterface(DESCRIPTOR);
                    setPsdActivity(data.readStrongBinder());
                    reply.writeNoException();
                    return true;
                case 5:
                    data.enforceInterface(DESCRIPTOR);
                    closeDialogByName(data.readString());
                    reply.writeNoException();
                    return true;
                case 6:
                    data.enforceInterface(DESCRIPTOR);
                    int psdStatusBarVisibility = getPsdStatusBarVisibility();
                    reply.writeNoException();
                    reply.writeInt(psdStatusBarVisibility);
                    return true;
                case 7:
                    data.enforceInterface(DESCRIPTOR);
                    setLeftTempStateVisibility(data.readString(), data.readInt());
                    reply.writeNoException();
                    return true;
                case 8:
                    data.enforceInterface(DESCRIPTOR);
                    Bundle statusBarWindowMode = getStatusBarWindowMode();
                    reply.writeNoException();
                    if (statusBarWindowMode != null) {
                        reply.writeInt(1);
                        statusBarWindowMode.writeToParcel(reply, 1);
                    } else {
                        reply.writeInt(0);
                    }
                    return true;
                case 9:
                    data.enforceInterface(DESCRIPTOR);
                    registerStatusBarModeChangeListener(IStatusBarModeChangeListener.Stub.asInterface(data.readStrongBinder()), data.readString());
                    reply.writeNoException();
                    return true;
                case 10:
                    data.enforceInterface(DESCRIPTOR);
                    unRegisterStatusBarModeChangeListener(IStatusBarModeChangeListener.Stub.asInterface(data.readStrongBinder()), data.readString());
                    reply.writeNoException();
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }

        private static class Proxy implements IStatusBarService {
            public static IStatusBarService sDefaultImpl;
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

            @Override // com.geely.lib.oneosapi.systemui.IStatusBarService
            public void showDialogByName(String dialogName) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(dialogName);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().showDialogByName(dialogName);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.systemui.IStatusBarService
            public void setPsdStatusVisibility(int visibility) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(visibility);
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setPsdStatusVisibility(visibility);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.systemui.IStatusBarService
            public void setCsdActivity(IBinder csdAcitvity) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(csdAcitvity);
                    if (!this.mRemote.transact(3, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setCsdActivity(csdAcitvity);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.systemui.IStatusBarService
            public void setPsdActivity(IBinder psdAcitvity) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(psdAcitvity);
                    if (!this.mRemote.transact(4, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setPsdActivity(psdAcitvity);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.systemui.IStatusBarService
            public void closeDialogByName(String dialogName) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(dialogName);
                    if (!this.mRemote.transact(5, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().closeDialogByName(dialogName);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.systemui.IStatusBarService
            public int getPsdStatusBarVisibility() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(6, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPsdStatusBarVisibility();
                    }
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.systemui.IStatusBarService
            public void setLeftTempStateVisibility(String leftTempStateContents, int visibility) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(leftTempStateContents);
                    obtain.writeInt(visibility);
                    if (!this.mRemote.transact(7, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setLeftTempStateVisibility(leftTempStateContents, visibility);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.systemui.IStatusBarService
            public Bundle getStatusBarWindowMode() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(8, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getStatusBarWindowMode();
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.systemui.IStatusBarService
            public void registerStatusBarModeChangeListener(IStatusBarModeChangeListener listener, String pkg) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    obtain.writeString(pkg);
                    if (!this.mRemote.transact(9, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerStatusBarModeChangeListener(listener, pkg);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.systemui.IStatusBarService
            public void unRegisterStatusBarModeChangeListener(IStatusBarModeChangeListener listener, String pkg) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    obtain.writeString(pkg);
                    if (!this.mRemote.transact(10, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unRegisterStatusBarModeChangeListener(listener, pkg);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IStatusBarService impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static IStatusBarService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}