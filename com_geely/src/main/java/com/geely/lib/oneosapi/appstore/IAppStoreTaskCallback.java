package com.geely.lib.oneosapi.appstore;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.geely.lib.oneosapi.appstore.bean.AppStoreTaskInfo;

/* loaded from: classes.dex */
public interface IAppStoreTaskCallback extends IInterface {

    public static class Default implements IAppStoreTaskCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.geely.lib.oneosapi.appstore.IAppStoreTaskCallback
        public void onAppTaskExecutionStatusChanged(AppStoreTaskInfo taskInfoList, int stastus) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.appstore.IAppStoreTaskCallback
        public void onAppTaskOperationStatusChanged(AppStoreTaskInfo taskInfoList, int type, boolean isSuccess) throws RemoteException {
        }
    }

    void onAppTaskExecutionStatusChanged(AppStoreTaskInfo taskInfoList, int stastus) throws RemoteException;

    void onAppTaskOperationStatusChanged(AppStoreTaskInfo taskInfoList, int type, boolean isSuccess) throws RemoteException;

    public static abstract class Stub extends Binder implements IAppStoreTaskCallback {
        private static final String DESCRIPTOR = "com.geely.lib.oneosapi.appstore.IAppStoreTaskCallback";
        static final int TRANSACTION_onAppTaskExecutionStatusChanged = 2;
        static final int TRANSACTION_onAppTaskOperationStatusChanged = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IAppStoreTaskCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface queryLocalInterface = obj.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IAppStoreTaskCallback)) {
                return (IAppStoreTaskCallback) queryLocalInterface;
            }
            return new Proxy(obj);
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            if (code == 1) {
                data.enforceInterface(DESCRIPTOR);
                onAppTaskOperationStatusChanged(data.readInt() != 0 ? AppStoreTaskInfo.CREATOR.createFromParcel(data) : null, data.readInt(), data.readInt() != 0);
                reply.writeNoException();
                return true;
            }
            if (code != 2) {
                if (code == 1598968902) {
                    reply.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(code, data, reply, flags);
            }
            data.enforceInterface(DESCRIPTOR);
            onAppTaskExecutionStatusChanged(data.readInt() != 0 ? AppStoreTaskInfo.CREATOR.createFromParcel(data) : null, data.readInt());
            reply.writeNoException();
            return true;
        }

        private static class Proxy implements IAppStoreTaskCallback {
            public static IAppStoreTaskCallback sDefaultImpl;
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

            @Override // com.geely.lib.oneosapi.appstore.IAppStoreTaskCallback
            public void onAppTaskOperationStatusChanged(AppStoreTaskInfo taskInfoList, int type, boolean isSuccess) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (taskInfoList != null) {
                        obtain.writeInt(1);
                        taskInfoList.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeInt(type);
                    obtain.writeInt(isSuccess ? 1 : 0);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onAppTaskOperationStatusChanged(taskInfoList, type, isSuccess);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.appstore.IAppStoreTaskCallback
            public void onAppTaskExecutionStatusChanged(AppStoreTaskInfo taskInfoList, int stastus) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (taskInfoList != null) {
                        obtain.writeInt(1);
                        taskInfoList.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeInt(stastus);
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onAppTaskExecutionStatusChanged(taskInfoList, stastus);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IAppStoreTaskCallback impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static IAppStoreTaskCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}