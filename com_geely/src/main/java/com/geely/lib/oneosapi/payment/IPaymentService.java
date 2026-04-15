package com.geely.lib.oneosapi.payment;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.geely.lib.oneosapi.payment.IPaymentCallback;

/* loaded from: classes.dex */
public interface IPaymentService extends IInterface {

    public static class Default implements IPaymentService {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.geely.lib.oneosapi.payment.IPaymentService
        public void isPaySuccessCallback(int code, String msg) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.payment.IPaymentService
        public void startPayment(OrderRenderParam orderInfo, IPaymentCallback isPaySuccessCallback) throws RemoteException {
        }
    }

    void isPaySuccessCallback(int code, String msg) throws RemoteException;

    void startPayment(OrderRenderParam orderInfo, IPaymentCallback isPaySuccessCallback) throws RemoteException;

    public static abstract class Stub extends Binder implements IPaymentService {
        private static final String DESCRIPTOR = "com.geely.lib.oneosapi.payment.IPaymentService";
        static final int TRANSACTION_isPaySuccessCallback = 2;
        static final int TRANSACTION_startPayment = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IPaymentService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface queryLocalInterface = obj.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IPaymentService)) {
                return (IPaymentService) queryLocalInterface;
            }
            return new Proxy(obj);
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            if (code == 1) {
                data.enforceInterface(DESCRIPTOR);
                startPayment(data.readInt() != 0 ? OrderRenderParam.CREATOR.createFromParcel(data) : null, IPaymentCallback.Stub.asInterface(data.readStrongBinder()));
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
            isPaySuccessCallback(data.readInt(), data.readString());
            reply.writeNoException();
            return true;
        }

        private static class Proxy implements IPaymentService {
            public static IPaymentService sDefaultImpl;
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

            @Override // com.geely.lib.oneosapi.payment.IPaymentService
            public void startPayment(OrderRenderParam orderInfo, IPaymentCallback isPaySuccessCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (orderInfo != null) {
                        obtain.writeInt(1);
                        orderInfo.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(isPaySuccessCallback != null ? isPaySuccessCallback.asBinder() : null);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().startPayment(orderInfo, isPaySuccessCallback);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.payment.IPaymentService
            public void isPaySuccessCallback(int code, String msg) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(code);
                    obtain.writeString(msg);
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().isPaySuccessCallback(code, msg);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IPaymentService impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static IPaymentService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}