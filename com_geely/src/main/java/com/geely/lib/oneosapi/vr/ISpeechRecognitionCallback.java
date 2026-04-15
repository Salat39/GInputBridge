package com.geely.lib.oneosapi.vr;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes.dex */
public interface ISpeechRecognitionCallback extends IInterface {

    public static class Default implements ISpeechRecognitionCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.geely.lib.oneosapi.vr.ISpeechRecognitionCallback
        public void onPartialResult(String partialText) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.vr.ISpeechRecognitionCallback
        public void onStart() throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.vr.ISpeechRecognitionCallback
        public void onStop(int cause, String resultText) throws RemoteException {
        }
    }

    void onPartialResult(String partialText) throws RemoteException;

    void onStart() throws RemoteException;

    void onStop(int cause, String resultText) throws RemoteException;

    public static abstract class Stub extends Binder implements ISpeechRecognitionCallback {
        private static final String DESCRIPTOR = "com.geely.lib.oneosapi.vr.ISpeechRecognitionCallback";
        static final int TRANSACTION_onPartialResult = 2;
        static final int TRANSACTION_onStart = 1;
        static final int TRANSACTION_onStop = 3;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ISpeechRecognitionCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface queryLocalInterface = obj.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof ISpeechRecognitionCallback)) {
                return (ISpeechRecognitionCallback) queryLocalInterface;
            }
            return new Proxy(obj);
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            if (code == 1) {
                data.enforceInterface(DESCRIPTOR);
                onStart();
                reply.writeNoException();
                return true;
            }
            if (code == 2) {
                data.enforceInterface(DESCRIPTOR);
                onPartialResult(data.readString());
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
            onStop(data.readInt(), data.readString());
            reply.writeNoException();
            return true;
        }

        private static class Proxy implements ISpeechRecognitionCallback {
            public static ISpeechRecognitionCallback sDefaultImpl;
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

            @Override // com.geely.lib.oneosapi.vr.ISpeechRecognitionCallback
            public void onStart() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onStart();
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.vr.ISpeechRecognitionCallback
            public void onPartialResult(String partialText) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(partialText);
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onPartialResult(partialText);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.vr.ISpeechRecognitionCallback
            public void onStop(int cause, String resultText) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(cause);
                    obtain.writeString(resultText);
                    if (!this.mRemote.transact(3, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onStop(cause, resultText);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ISpeechRecognitionCallback impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static ISpeechRecognitionCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}