package com.geely.lib.oneosapi.launcher;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.geely.lib.oneosapi.launcher.listener.IEnterOrExitHomePageChangeListener;
import com.geely.lib.oneosapi.launcher.listener.ILauncherPageSwitchListener;
import com.geely.lib.oneosapi.launcher.listener.IMapWidgetChangeListener;
import com.geely.lib.oneosapi.launcher.listener.IWeatherWidgetChangeListener;
import com.geely.lib.oneosapi.launcher.listener.IWidgetListDisplayChangeListener;

/* loaded from: classes.dex */
public interface ILauncherService extends IInterface {

    public static class Default implements ILauncherService {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.geely.lib.oneosapi.launcher.ILauncherService
        public void closeApplets(String appletsKey) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.launcher.ILauncherService
        public void hvacMassageDisplay(boolean isShow, boolean isMain) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.launcher.ILauncherService
        public boolean isWidgetsShowing(boolean isPsd) throws RemoteException {
            return false;
        }

        @Override // com.geely.lib.oneosapi.launcher.ILauncherService
        public void mediaSourceListDisplay(boolean show, boolean isPsd) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.launcher.ILauncherService
        public void openApplets(String appletsKey) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.launcher.ILauncherService
        public void registerEnterOrExitHomePageChangeListener(IEnterOrExitHomePageChangeListener listener) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.launcher.ILauncherService
        public void registerLauncherPageSwitchListener(ILauncherPageSwitchListener listener) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.launcher.ILauncherService
        public void registerMapWidgetListener(IMapWidgetChangeListener listener) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.launcher.ILauncherService
        public void registerWeatherWidgetListener(IWeatherWidgetChangeListener listener) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.launcher.ILauncherService
        public void registerWidgetListDisplayChangeListener(IWidgetListDisplayChangeListener listener) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.launcher.ILauncherService
        public void selectWidgetMap(String classname) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.launcher.ILauncherService
        public void showPsdMediaControlWidget(boolean isShow) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.launcher.ILauncherService
        public void startGlobalSearch(String keyWord) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.launcher.ILauncherService
        public void togglePsdWidget() throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.launcher.ILauncherService
        public void toggleWidget() throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.launcher.ILauncherService
        public void unRegisterEnterOrExitHomePageChangeListener(IEnterOrExitHomePageChangeListener listener) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.launcher.ILauncherService
        public void unRegisterLauncherPageSwitchListener(ILauncherPageSwitchListener listener) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.launcher.ILauncherService
        public void unRegisterMapWidgetListener(IMapWidgetChangeListener listener) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.launcher.ILauncherService
        public void unRegisterWeatherWidgetListener(IWeatherWidgetChangeListener listener) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.launcher.ILauncherService
        public void unRegisterWidgetListDisplayChangeListener(IWidgetListDisplayChangeListener listener) throws RemoteException {
        }
    }

    void closeApplets(String appletsKey) throws RemoteException;

    void hvacMassageDisplay(boolean isShow, boolean isMain) throws RemoteException;

    boolean isWidgetsShowing(boolean isPsd) throws RemoteException;

    void mediaSourceListDisplay(boolean show, boolean isPsd) throws RemoteException;

    void openApplets(String appletsKey) throws RemoteException;

    void registerEnterOrExitHomePageChangeListener(IEnterOrExitHomePageChangeListener listener) throws RemoteException;

    void registerLauncherPageSwitchListener(ILauncherPageSwitchListener listener) throws RemoteException;

    void registerMapWidgetListener(IMapWidgetChangeListener listener) throws RemoteException;

    void registerWeatherWidgetListener(IWeatherWidgetChangeListener listener) throws RemoteException;

    void registerWidgetListDisplayChangeListener(IWidgetListDisplayChangeListener listener) throws RemoteException;

    void selectWidgetMap(String classname) throws RemoteException;

    void showPsdMediaControlWidget(boolean isShow) throws RemoteException;

    void startGlobalSearch(String keyWord) throws RemoteException;

    void togglePsdWidget() throws RemoteException;

    void toggleWidget() throws RemoteException;

    void unRegisterEnterOrExitHomePageChangeListener(IEnterOrExitHomePageChangeListener listener) throws RemoteException;

    void unRegisterLauncherPageSwitchListener(ILauncherPageSwitchListener listener) throws RemoteException;

    void unRegisterMapWidgetListener(IMapWidgetChangeListener listener) throws RemoteException;

    void unRegisterWeatherWidgetListener(IWeatherWidgetChangeListener listener) throws RemoteException;

    void unRegisterWidgetListDisplayChangeListener(IWidgetListDisplayChangeListener listener) throws RemoteException;

    public static abstract class Stub extends Binder implements ILauncherService {
        private static final String DESCRIPTOR = "com.geely.lib.oneosapi.launcher.ILauncherService";
        static final int TRANSACTION_closeApplets = 18;
        static final int TRANSACTION_hvacMassageDisplay = 19;
        static final int TRANSACTION_isWidgetsShowing = 20;
        static final int TRANSACTION_mediaSourceListDisplay = 3;
        static final int TRANSACTION_openApplets = 17;
        static final int TRANSACTION_registerEnterOrExitHomePageChangeListener = 8;
        static final int TRANSACTION_registerLauncherPageSwitchListener = 14;
        static final int TRANSACTION_registerMapWidgetListener = 4;
        static final int TRANSACTION_registerWeatherWidgetListener = 6;
        static final int TRANSACTION_registerWidgetListDisplayChangeListener = 12;
        static final int TRANSACTION_selectWidgetMap = 2;
        static final int TRANSACTION_showPsdMediaControlWidget = 10;
        static final int TRANSACTION_startGlobalSearch = 16;
        static final int TRANSACTION_togglePsdWidget = 11;
        static final int TRANSACTION_toggleWidget = 1;
        static final int TRANSACTION_unRegisterEnterOrExitHomePageChangeListener = 9;
        static final int TRANSACTION_unRegisterLauncherPageSwitchListener = 15;
        static final int TRANSACTION_unRegisterMapWidgetListener = 5;
        static final int TRANSACTION_unRegisterWeatherWidgetListener = 7;
        static final int TRANSACTION_unRegisterWidgetListDisplayChangeListener = 13;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ILauncherService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface queryLocalInterface = obj.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof ILauncherService)) {
                return (ILauncherService) queryLocalInterface;
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
                    toggleWidget();
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    selectWidgetMap(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    mediaSourceListDisplay(parcel.readInt() != 0, parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    registerMapWidgetListener(IMapWidgetChangeListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    unRegisterMapWidgetListener(IMapWidgetChangeListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    registerWeatherWidgetListener(IWeatherWidgetChangeListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    unRegisterWeatherWidgetListener(IWeatherWidgetChangeListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    registerEnterOrExitHomePageChangeListener(IEnterOrExitHomePageChangeListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    unRegisterEnterOrExitHomePageChangeListener(IEnterOrExitHomePageChangeListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    showPsdMediaControlWidget(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    togglePsdWidget();
                    parcel2.writeNoException();
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    registerWidgetListDisplayChangeListener(IWidgetListDisplayChangeListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    unRegisterWidgetListDisplayChangeListener(IWidgetListDisplayChangeListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    registerLauncherPageSwitchListener(ILauncherPageSwitchListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    unRegisterLauncherPageSwitchListener(ILauncherPageSwitchListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 16:
                    parcel.enforceInterface(DESCRIPTOR);
                    startGlobalSearch(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 17:
                    parcel.enforceInterface(DESCRIPTOR);
                    openApplets(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 18:
                    parcel.enforceInterface(DESCRIPTOR);
                    closeApplets(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 19:
                    parcel.enforceInterface(DESCRIPTOR);
                    hvacMassageDisplay(parcel.readInt() != 0, parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 20:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean isWidgetsShowing = isWidgetsShowing(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(isWidgetsShowing ? 1 : 0);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements ILauncherService {
            public static ILauncherService sDefaultImpl;
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

            @Override // com.geely.lib.oneosapi.launcher.ILauncherService
            public void toggleWidget() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().toggleWidget();
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.launcher.ILauncherService
            public void selectWidgetMap(String classname) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(classname);
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().selectWidgetMap(classname);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.launcher.ILauncherService
            public void mediaSourceListDisplay(boolean show, boolean isPsd) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    int i = 1;
                    obtain.writeInt(show ? 1 : 0);
                    if (!isPsd) {
                        i = 0;
                    }
                    obtain.writeInt(i);
                    if (!this.mRemote.transact(3, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().mediaSourceListDisplay(show, isPsd);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.launcher.ILauncherService
            public void registerMapWidgetListener(IMapWidgetChangeListener listener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    if (!this.mRemote.transact(4, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerMapWidgetListener(listener);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.launcher.ILauncherService
            public void unRegisterMapWidgetListener(IMapWidgetChangeListener listener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    if (!this.mRemote.transact(5, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unRegisterMapWidgetListener(listener);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.launcher.ILauncherService
            public void registerWeatherWidgetListener(IWeatherWidgetChangeListener listener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    if (!this.mRemote.transact(6, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerWeatherWidgetListener(listener);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.launcher.ILauncherService
            public void unRegisterWeatherWidgetListener(IWeatherWidgetChangeListener listener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    if (!this.mRemote.transact(7, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unRegisterWeatherWidgetListener(listener);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.launcher.ILauncherService
            public void registerEnterOrExitHomePageChangeListener(IEnterOrExitHomePageChangeListener listener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    if (!this.mRemote.transact(8, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerEnterOrExitHomePageChangeListener(listener);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.launcher.ILauncherService
            public void unRegisterEnterOrExitHomePageChangeListener(IEnterOrExitHomePageChangeListener listener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    if (!this.mRemote.transact(9, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unRegisterEnterOrExitHomePageChangeListener(listener);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.launcher.ILauncherService
            public void showPsdMediaControlWidget(boolean isShow) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(isShow ? 1 : 0);
                    if (!this.mRemote.transact(10, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().showPsdMediaControlWidget(isShow);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.launcher.ILauncherService
            public void togglePsdWidget() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(11, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().togglePsdWidget();
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.launcher.ILauncherService
            public void registerWidgetListDisplayChangeListener(IWidgetListDisplayChangeListener listener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    if (!this.mRemote.transact(12, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerWidgetListDisplayChangeListener(listener);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.launcher.ILauncherService
            public void unRegisterWidgetListDisplayChangeListener(IWidgetListDisplayChangeListener listener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    if (!this.mRemote.transact(13, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unRegisterWidgetListDisplayChangeListener(listener);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.launcher.ILauncherService
            public void registerLauncherPageSwitchListener(ILauncherPageSwitchListener listener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    if (!this.mRemote.transact(14, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerLauncherPageSwitchListener(listener);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.launcher.ILauncherService
            public void unRegisterLauncherPageSwitchListener(ILauncherPageSwitchListener listener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    if (!this.mRemote.transact(15, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unRegisterLauncherPageSwitchListener(listener);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.launcher.ILauncherService
            public void startGlobalSearch(String keyWord) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(keyWord);
                    if (!this.mRemote.transact(16, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().startGlobalSearch(keyWord);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.launcher.ILauncherService
            public void openApplets(String appletsKey) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(appletsKey);
                    if (!this.mRemote.transact(17, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().openApplets(appletsKey);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.launcher.ILauncherService
            public void closeApplets(String appletsKey) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(appletsKey);
                    if (!this.mRemote.transact(18, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().closeApplets(appletsKey);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.launcher.ILauncherService
            public void hvacMassageDisplay(boolean isShow, boolean isMain) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    int i = 1;
                    obtain.writeInt(isShow ? 1 : 0);
                    if (!isMain) {
                        i = 0;
                    }
                    obtain.writeInt(i);
                    if (!this.mRemote.transact(19, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().hvacMassageDisplay(isShow, isMain);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.launcher.ILauncherService
            public boolean isWidgetsShowing(boolean isPsd) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(isPsd ? 1 : 0);
                    if (!this.mRemote.transact(20, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isWidgetsShowing(isPsd);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ILauncherService impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static ILauncherService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}