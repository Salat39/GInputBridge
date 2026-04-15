package com.geely.lib.oneosapi.gesture;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.geely.lib.oneosapi.gesture.IGestureEventNotifyCallback;

/* loaded from: classes.dex */
public interface IGestureManager extends IInterface {

    public static class Default implements IGestureManager {
        @Override // com.geely.lib.oneosapi.gesture.IGestureManager
        public boolean addGestureDetectEventNotify(IGestureEventNotifyCallback eventNotify) throws RemoteException {
            return false;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.geely.lib.oneosapi.gesture.IGestureManager
        public boolean removeGestureDetectEventNotify(IGestureEventNotifyCallback eventNotify) throws RemoteException {
            return false;
        }
    }

    boolean addGestureDetectEventNotify(IGestureEventNotifyCallback eventNotify) throws RemoteException;

    boolean removeGestureDetectEventNotify(IGestureEventNotifyCallback eventNotify) throws RemoteException;

    public static abstract class Stub extends Binder implements IGestureManager {
        private static final String DESCRIPTOR = "com.geely.lib.oneosapi.gesture.IGestureManager";
        static final int TRANSACTION_addGestureDetectEventNotify = 1;
        static final int TRANSACTION_removeGestureDetectEventNotify = 2;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IGestureManager asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface queryLocalInterface = obj.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IGestureManager)) {
                return (IGestureManager) queryLocalInterface;
            }
            return new Proxy(obj);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                boolean addGestureDetectEventNotify = addGestureDetectEventNotify(IGestureEventNotifyCallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                parcel2.writeInt(addGestureDetectEventNotify ? 1 : 0);
                return true;
            }
            if (i != 2) {
                if (i == 1598968902) {
                    parcel2.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface(DESCRIPTOR);
            boolean removeGestureDetectEventNotify = removeGestureDetectEventNotify(IGestureEventNotifyCallback.Stub.asInterface(parcel.readStrongBinder()));
            parcel2.writeNoException();
            parcel2.writeInt(removeGestureDetectEventNotify ? 1 : 0);
            return true;
        }

        private static class Proxy implements IGestureManager {
            public static IGestureManager sDefaultImpl;
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

            @Override // com.geely.lib.oneosapi.gesture.IGestureManager
            public boolean addGestureDetectEventNotify(IGestureEventNotifyCallback eventNotify) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(eventNotify != null ? eventNotify.asBinder() : null);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().addGestureDetectEventNotify(eventNotify);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.gesture.IGestureManager
            public boolean removeGestureDetectEventNotify(IGestureEventNotifyCallback eventNotify) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(eventNotify != null ? eventNotify.asBinder() : null);
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().removeGestureDetectEventNotify(eventNotify);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IGestureManager impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static IGestureManager getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}