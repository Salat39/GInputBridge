package com.geely.lib.oneosapi.input;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes.dex */
public interface IInputListener extends IInterface {

    public static class Default implements IInputListener {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.geely.lib.oneosapi.input.IInputListener
        public void onDoubleClick(int keyCode, int softKeyFunction) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.input.IInputListener
        public void onHoldingPressStarted(int keyCode, int softKeyFunction) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.input.IInputListener
        public void onHoldingPressStopped(int keyCode, int softKeyFunction) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.input.IInputListener
        public void onKeyCodeEvent(int keyCode, int event, int softKeyFunction) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.input.IInputListener
        public void onLongPressTriggered(int keyCode, int softKeyFunction) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.input.IInputListener
        public void onShortClick(int keyCode, int softKeyFunction) throws RemoteException {
        }
    }

    void onDoubleClick(int keyCode, int softKeyFunction) throws RemoteException;

    void onHoldingPressStarted(int keyCode, int softKeyFunction) throws RemoteException;

    void onHoldingPressStopped(int keyCode, int softKeyFunction) throws RemoteException;

    void onKeyCodeEvent(int keyCode, int event, int softKeyFunction) throws RemoteException;

    void onLongPressTriggered(int keyCode, int softKeyFunction) throws RemoteException;

    void onShortClick(int keyCode, int softKeyFunction) throws RemoteException;

    public static abstract class Stub extends Binder implements IInputListener {
        private static final String DESCRIPTOR = "com.geely.lib.oneosapi.input.IInputListener";
        static final int TRANSACTION_onDoubleClick = 6;
        static final int TRANSACTION_onHoldingPressStarted = 3;
        static final int TRANSACTION_onHoldingPressStopped = 4;
        static final int TRANSACTION_onKeyCodeEvent = 1;
        static final int TRANSACTION_onLongPressTriggered = 5;
        static final int TRANSACTION_onShortClick = 2;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IInputListener asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface queryLocalInterface = obj.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IInputListener)) {
                return (IInputListener) queryLocalInterface;
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
                    onKeyCodeEvent(data.readInt(), data.readInt(), data.readInt());
                    reply.writeNoException();
                    return true;
                case 2:
                    data.enforceInterface(DESCRIPTOR);
                    onShortClick(data.readInt(), data.readInt());
                    reply.writeNoException();
                    return true;
                case 3:
                    data.enforceInterface(DESCRIPTOR);
                    onHoldingPressStarted(data.readInt(), data.readInt());
                    reply.writeNoException();
                    return true;
                case 4:
                    data.enforceInterface(DESCRIPTOR);
                    onHoldingPressStopped(data.readInt(), data.readInt());
                    reply.writeNoException();
                    return true;
                case 5:
                    data.enforceInterface(DESCRIPTOR);
                    onLongPressTriggered(data.readInt(), data.readInt());
                    reply.writeNoException();
                    return true;
                case 6:
                    data.enforceInterface(DESCRIPTOR);
                    onDoubleClick(data.readInt(), data.readInt());
                    reply.writeNoException();
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }

        private static class Proxy implements IInputListener {
            public static IInputListener sDefaultImpl;
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

            @Override // com.geely.lib.oneosapi.input.IInputListener
            public void onKeyCodeEvent(int keyCode, int event, int softKeyFunction) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(keyCode);
                    obtain.writeInt(event);
                    obtain.writeInt(softKeyFunction);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onKeyCodeEvent(keyCode, event, softKeyFunction);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.input.IInputListener
            public void onShortClick(int keyCode, int softKeyFunction) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(keyCode);
                    obtain.writeInt(softKeyFunction);
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onShortClick(keyCode, softKeyFunction);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.input.IInputListener
            public void onHoldingPressStarted(int keyCode, int softKeyFunction) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(keyCode);
                    obtain.writeInt(softKeyFunction);
                    if (!this.mRemote.transact(3, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onHoldingPressStarted(keyCode, softKeyFunction);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.input.IInputListener
            public void onHoldingPressStopped(int keyCode, int softKeyFunction) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(keyCode);
                    obtain.writeInt(softKeyFunction);
                    if (!this.mRemote.transact(4, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onHoldingPressStopped(keyCode, softKeyFunction);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.input.IInputListener
            public void onLongPressTriggered(int keyCode, int softKeyFunction) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(keyCode);
                    obtain.writeInt(softKeyFunction);
                    if (!this.mRemote.transact(5, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onLongPressTriggered(keyCode, softKeyFunction);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.input.IInputListener
            public void onDoubleClick(int keyCode, int softKeyFunction) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(keyCode);
                    obtain.writeInt(softKeyFunction);
                    if (!this.mRemote.transact(6, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onDoubleClick(keyCode, softKeyFunction);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IInputListener impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static IInputListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}