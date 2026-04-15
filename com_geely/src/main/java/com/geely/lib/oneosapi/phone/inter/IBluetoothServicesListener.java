package com.geely.lib.oneosapi.phone.inter;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.geely.lib.oneosapi.phone.telecom.GlyCallItem;

/* loaded from: classes.dex */
public interface IBluetoothServicesListener extends IInterface {

    public static class Default implements IBluetoothServicesListener {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.geely.lib.oneosapi.phone.inter.IBluetoothServicesListener
        public void onCallAdded(GlyCallItem callItem) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.phone.inter.IBluetoothServicesListener
        public void onCallAddedOther(GlyCallItem callItem, GlyCallItem otherCallItem) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.phone.inter.IBluetoothServicesListener
        public void onCallViewStateChange(int statue) throws RemoteException {
        }
    }

    void onCallAdded(GlyCallItem callItem) throws RemoteException;

    void onCallAddedOther(GlyCallItem callItem, GlyCallItem otherCallItem) throws RemoteException;

    void onCallViewStateChange(int statue) throws RemoteException;

    public static abstract class Stub extends Binder implements IBluetoothServicesListener {
        private static final String DESCRIPTOR = "com.geely.lib.oneosapi.phone.inter.IBluetoothServicesListener";
        static final int TRANSACTION_onCallAdded = 1;
        static final int TRANSACTION_onCallAddedOther = 3;
        static final int TRANSACTION_onCallViewStateChange = 2;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IBluetoothServicesListener asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface queryLocalInterface = obj.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IBluetoothServicesListener)) {
                return (IBluetoothServicesListener) queryLocalInterface;
            }
            return new Proxy(obj);
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            GlyCallItem createFromParcel;
            if (code == 1) {
                data.enforceInterface(DESCRIPTOR);
                createFromParcel = data.readInt() != 0 ? GlyCallItem.CREATOR.createFromParcel(data) : null;
                onCallAdded(createFromParcel);
                reply.writeNoException();
                if (createFromParcel != null) {
                    reply.writeInt(1);
                    createFromParcel.writeToParcel(reply, 1);
                } else {
                    reply.writeInt(0);
                }
                return true;
            }
            if (code == 2) {
                data.enforceInterface(DESCRIPTOR);
                onCallViewStateChange(data.readInt());
                reply.writeNoException();
                return true;
            }
            if (code != 3) {
                if (code == 1598968902) {
                    reply.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(code, data, reply, flags);
            }
            data.enforceInterface(DESCRIPTOR);
            GlyCallItem createFromParcel2 = data.readInt() != 0 ? GlyCallItem.CREATOR.createFromParcel(data) : null;
            createFromParcel = data.readInt() != 0 ? GlyCallItem.CREATOR.createFromParcel(data) : null;
            onCallAddedOther(createFromParcel2, createFromParcel);
            reply.writeNoException();
            if (createFromParcel2 != null) {
                reply.writeInt(1);
                createFromParcel2.writeToParcel(reply, 1);
            } else {
                reply.writeInt(0);
            }
            if (createFromParcel != null) {
                reply.writeInt(1);
                createFromParcel.writeToParcel(reply, 1);
            } else {
                reply.writeInt(0);
            }
            return true;
        }

        private static class Proxy implements IBluetoothServicesListener {
            public static IBluetoothServicesListener sDefaultImpl;
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

            @Override // com.geely.lib.oneosapi.phone.inter.IBluetoothServicesListener
            public void onCallAdded(GlyCallItem callItem) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (callItem != null) {
                        obtain.writeInt(1);
                        callItem.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onCallAdded(callItem);
                        return;
                    }
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        callItem.readFromParcel(obtain2);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.phone.inter.IBluetoothServicesListener
            public void onCallViewStateChange(int statue) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(statue);
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onCallViewStateChange(statue);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.phone.inter.IBluetoothServicesListener
            public void onCallAddedOther(GlyCallItem callItem, GlyCallItem otherCallItem) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (callItem != null) {
                        obtain.writeInt(1);
                        callItem.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (otherCallItem != null) {
                        obtain.writeInt(1);
                        otherCallItem.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(3, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onCallAddedOther(callItem, otherCallItem);
                        return;
                    }
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        callItem.readFromParcel(obtain2);
                    }
                    if (obtain2.readInt() != 0) {
                        otherCallItem.readFromParcel(obtain2);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IBluetoothServicesListener impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static IBluetoothServicesListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}