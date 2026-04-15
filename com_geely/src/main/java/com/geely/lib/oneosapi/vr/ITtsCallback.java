package com.geely.lib.oneosapi.vr;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes.dex */
public interface ITtsCallback extends IInterface {

    public static class Default implements ITtsCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.geely.lib.oneosapi.vr.ITtsCallback
        public void onError(String var1, int var2) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.vr.ITtsCallback
        public void onInterrupt(String var1) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.vr.ITtsCallback
        public void onSpeechFinish(String var1) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.vr.ITtsCallback
        public void onSpeechStart(String var1, String var2) throws RemoteException {
        }
    }

    void onError(String var1, int var2) throws RemoteException;

    void onInterrupt(String var1) throws RemoteException;

    void onSpeechFinish(String var1) throws RemoteException;

    void onSpeechStart(String var1, String var2) throws RemoteException;

    public static abstract class Stub extends Binder implements ITtsCallback {
        private static final String DESCRIPTOR = "com.geely.lib.oneosapi.vr.ITtsCallback";
        static final int TRANSACTION_onError = 4;
        static final int TRANSACTION_onInterrupt = 3;
        static final int TRANSACTION_onSpeechFinish = 2;
        static final int TRANSACTION_onSpeechStart = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ITtsCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface queryLocalInterface = obj.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof ITtsCallback)) {
                return (ITtsCallback) queryLocalInterface;
            }
            return new Proxy(obj);
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            if (code == 1) {
                data.enforceInterface(DESCRIPTOR);
                onSpeechStart(data.readString(), data.readString());
                reply.writeNoException();
                return true;
            }
            if (code == 2) {
                data.enforceInterface(DESCRIPTOR);
                onSpeechFinish(data.readString());
                reply.writeNoException();
                return true;
            }
            if (code == 3) {
                data.enforceInterface(DESCRIPTOR);
                onInterrupt(data.readString());
                reply.writeNoException();
                return true;
            }
            if (code != 4) {
                if (code == 1598968902) {
                    reply.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(code, data, reply, flags);
            }
            data.enforceInterface(DESCRIPTOR);
            onError(data.readString(), data.readInt());
            reply.writeNoException();
            return true;
        }

        private static class Proxy implements ITtsCallback {
            public static ITtsCallback sDefaultImpl;
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

            @Override // com.geely.lib.oneosapi.vr.ITtsCallback
            public void onSpeechStart(String var1, String var2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(var1);
                    obtain.writeString(var2);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onSpeechStart(var1, var2);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.vr.ITtsCallback
            public void onSpeechFinish(String var1) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(var1);
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onSpeechFinish(var1);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.vr.ITtsCallback
            public void onInterrupt(String var1) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(var1);
                    if (!this.mRemote.transact(3, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onInterrupt(var1);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.vr.ITtsCallback
            public void onError(String var1, int var2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(var1);
                    obtain.writeInt(var2);
                    if (!this.mRemote.transact(4, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onError(var1, var2);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ITtsCallback impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static ITtsCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}