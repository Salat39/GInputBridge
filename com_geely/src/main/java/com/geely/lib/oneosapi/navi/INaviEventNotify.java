package com.geely.lib.oneosapi.navi;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.geely.lib.oneosapi.navi.ipc.TransferModel;

/* loaded from: classes.dex */
public interface INaviEventNotify extends IInterface {

    public static class Default implements INaviEventNotify {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.geely.lib.oneosapi.navi.INaviEventNotify
        public String getUID() throws RemoteException {
            return null;
        }

        @Override // com.geely.lib.oneosapi.navi.INaviEventNotify
        public void onNaviNotify(TransferModel notifyModel) throws RemoteException {
        }
    }

    String getUID() throws RemoteException;

    void onNaviNotify(TransferModel notifyModel) throws RemoteException;

    public static abstract class Stub extends Binder implements INaviEventNotify {
        private static final String DESCRIPTOR = "com.geely.lib.oneosapi.navi.INaviEventNotify";
        static final int TRANSACTION_getUID = 1;
        static final int TRANSACTION_onNaviNotify = 2;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static INaviEventNotify asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface queryLocalInterface = obj.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof INaviEventNotify)) {
                return (INaviEventNotify) queryLocalInterface;
            }
            return new Proxy(obj);
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            if (code == 1) {
                data.enforceInterface(DESCRIPTOR);
                String uid = getUID();
                reply.writeNoException();
                reply.writeString(uid);
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
            onNaviNotify(data.readInt() != 0 ? TransferModel.CREATOR.createFromParcel(data) : null);
            reply.writeNoException();
            return true;
        }

        private static class Proxy implements INaviEventNotify {
            public static INaviEventNotify sDefaultImpl;
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

            @Override // com.geely.lib.oneosapi.navi.INaviEventNotify
            public String getUID() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getUID();
                    }
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.navi.INaviEventNotify
            public void onNaviNotify(TransferModel notifyModel) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (notifyModel != null) {
                        obtain.writeInt(1);
                        notifyModel.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onNaviNotify(notifyModel);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(INaviEventNotify impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static INaviEventNotify getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}