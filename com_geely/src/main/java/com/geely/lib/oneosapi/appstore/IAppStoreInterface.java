package com.geely.lib.oneosapi.appstore;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.geely.lib.oneosapi.appstore.IAppStoreTaskCallback;
import com.geely.lib.oneosapi.appstore.bean.AppStoreTaskInfo;
import java.util.List;

/* loaded from: classes.dex */
public interface IAppStoreInterface extends IInterface {

    public static class Default implements IAppStoreInterface {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.geely.lib.oneosapi.appstore.IAppStoreInterface
        public void cancelDownload(String taskId) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.appstore.IAppStoreInterface
        public List<AppStoreTaskInfo> getAllPendingDownloadApp() throws RemoteException {
            return null;
        }

        @Override // com.geely.lib.oneosapi.appstore.IAppStoreInterface
        public boolean registerTaskCallBack(IAppStoreTaskCallback callback) throws RemoteException {
            return false;
        }

        @Override // com.geely.lib.oneosapi.appstore.IAppStoreInterface
        public void startDownload(String taskId) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.appstore.IAppStoreInterface
        public void stopDownload(String taskId) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.appstore.IAppStoreInterface
        public boolean unregisterTaskCallBack(IAppStoreTaskCallback callback) throws RemoteException {
            return false;
        }
    }

    void cancelDownload(String taskId) throws RemoteException;

    List<AppStoreTaskInfo> getAllPendingDownloadApp() throws RemoteException;

    boolean registerTaskCallBack(IAppStoreTaskCallback callback) throws RemoteException;

    void startDownload(String taskId) throws RemoteException;

    void stopDownload(String taskId) throws RemoteException;

    boolean unregisterTaskCallBack(IAppStoreTaskCallback callback) throws RemoteException;

    public static abstract class Stub extends Binder implements IAppStoreInterface {
        private static final String DESCRIPTOR = "com.geely.lib.oneosapi.appstore.IAppStoreInterface";
        static final int TRANSACTION_cancelDownload = 4;
        static final int TRANSACTION_getAllPendingDownloadApp = 1;
        static final int TRANSACTION_registerTaskCallBack = 5;
        static final int TRANSACTION_startDownload = 3;
        static final int TRANSACTION_stopDownload = 2;
        static final int TRANSACTION_unregisterTaskCallBack = 6;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IAppStoreInterface asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface queryLocalInterface = obj.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IAppStoreInterface)) {
                return (IAppStoreInterface) queryLocalInterface;
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
                    List<AppStoreTaskInfo> allPendingDownloadApp = getAllPendingDownloadApp();
                    parcel2.writeNoException();
                    parcel2.writeTypedList(allPendingDownloadApp);
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    stopDownload(parcel.readString());
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    startDownload(parcel.readString());
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    cancelDownload(parcel.readString());
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean registerTaskCallBack = registerTaskCallBack(IAppStoreTaskCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(registerTaskCallBack ? 1 : 0);
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean unregisterTaskCallBack = unregisterTaskCallBack(IAppStoreTaskCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(unregisterTaskCallBack ? 1 : 0);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IAppStoreInterface {
            public static IAppStoreInterface sDefaultImpl;
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

            @Override // com.geely.lib.oneosapi.appstore.IAppStoreInterface
            public List<AppStoreTaskInfo> getAllPendingDownloadApp() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAllPendingDownloadApp();
                    }
                    obtain2.readException();
                    return obtain2.createTypedArrayList(AppStoreTaskInfo.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.appstore.IAppStoreInterface
            public void stopDownload(String taskId) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(taskId);
                    if (this.mRemote.transact(2, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().stopDownload(taskId);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.appstore.IAppStoreInterface
            public void startDownload(String taskId) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(taskId);
                    if (this.mRemote.transact(3, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().startDownload(taskId);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.appstore.IAppStoreInterface
            public void cancelDownload(String taskId) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(taskId);
                    if (this.mRemote.transact(4, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().cancelDownload(taskId);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.appstore.IAppStoreInterface
            public boolean registerTaskCallBack(IAppStoreTaskCallback callback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    if (!this.mRemote.transact(5, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().registerTaskCallBack(callback);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.appstore.IAppStoreInterface
            public boolean unregisterTaskCallBack(IAppStoreTaskCallback callback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    if (!this.mRemote.transact(6, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().unregisterTaskCallBack(callback);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IAppStoreInterface impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static IAppStoreInterface getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}