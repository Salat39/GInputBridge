package com.geely.lib.oneosapi.input;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.geely.lib.oneosapi.input.IInputListener;

/* loaded from: classes.dex */
public interface IInputManager extends IInterface {

    public static class Default implements IInputManager {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.geely.lib.oneosapi.input.IInputManager
        public int getControlIndex() throws RemoteException {
            return 0;
        }

        @Override // com.geely.lib.oneosapi.input.IInputManager
        public boolean interceptKeyCode(int keyCode, String packageName) throws RemoteException {
            return false;
        }

        @Override // com.geely.lib.oneosapi.input.IInputManager
        public void registerListener(IInputListener listener, String packageName, int[] keyCodes) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.input.IInputManager
        public boolean releaseKeyCode(int keyCode, String packageName) throws RemoteException {
            return false;
        }

        @Override // com.geely.lib.oneosapi.input.IInputManager
        public void unregisterListener(IInputListener listener, String packageName) throws RemoteException {
        }
    }

    int getControlIndex() throws RemoteException;

    boolean interceptKeyCode(int keyCode, String packageName) throws RemoteException;

    void registerListener(IInputListener listener, String packageName, int[] keyCodes) throws RemoteException;

    boolean releaseKeyCode(int keyCode, String packageName) throws RemoteException;

    void unregisterListener(IInputListener listener, String packageName) throws RemoteException;

    public static abstract class Stub extends Binder implements IInputManager {
        private static final String DESCRIPTOR = "com.geely.lib.oneosapi.input.IInputManager";
        static final int TRANSACTION_getControlIndex = 5;
        static final int TRANSACTION_interceptKeyCode = 1;
        static final int TRANSACTION_registerListener = 3;
        static final int TRANSACTION_releaseKeyCode = 2;
        static final int TRANSACTION_unregisterListener = 4;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IInputManager asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface queryLocalInterface = obj.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IInputManager)) {
                return (IInputManager) queryLocalInterface;
            }
            return new Proxy(obj);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                boolean interceptKeyCode = interceptKeyCode(parcel.readInt(), parcel.readString());
                parcel2.writeNoException();
                parcel2.writeInt(interceptKeyCode ? 1 : 0);
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                boolean releaseKeyCode = releaseKeyCode(parcel.readInt(), parcel.readString());
                parcel2.writeNoException();
                parcel2.writeInt(releaseKeyCode ? 1 : 0);
                return true;
            }
            if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                registerListener(IInputListener.Stub.asInterface(parcel.readStrongBinder()), parcel.readString(), parcel.createIntArray());
                parcel2.writeNoException();
                return true;
            }
            if (i == 4) {
                parcel.enforceInterface(DESCRIPTOR);
                unregisterListener(IInputListener.Stub.asInterface(parcel.readStrongBinder()), parcel.readString());
                parcel2.writeNoException();
                return true;
            }
            if (i != 5) {
                if (i == 1598968902) {
                    parcel2.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface(DESCRIPTOR);
            int controlIndex = getControlIndex();
            parcel2.writeNoException();
            parcel2.writeInt(controlIndex);
            return true;
        }

        private static class Proxy implements IInputManager {
            public static IInputManager sDefaultImpl;
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

            @Override // com.geely.lib.oneosapi.input.IInputManager
            public boolean interceptKeyCode(int keyCode, String packageName) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(keyCode);
                    obtain.writeString(packageName);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().interceptKeyCode(keyCode, packageName);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.input.IInputManager
            public boolean releaseKeyCode(int keyCode, String packageName) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(keyCode);
                    obtain.writeString(packageName);
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().releaseKeyCode(keyCode, packageName);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.input.IInputManager
            public void registerListener(IInputListener listener, String packageName, int[] keyCodes) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    obtain.writeString(packageName);
                    obtain.writeIntArray(keyCodes);
                    if (!this.mRemote.transact(3, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerListener(listener, packageName, keyCodes);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.input.IInputManager
            public void unregisterListener(IInputListener listener, String packageName) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    obtain.writeString(packageName);
                    if (!this.mRemote.transact(4, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterListener(listener, packageName);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.input.IInputManager
            public int getControlIndex() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(5, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getControlIndex();
                    }
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IInputManager impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static IInputManager getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}