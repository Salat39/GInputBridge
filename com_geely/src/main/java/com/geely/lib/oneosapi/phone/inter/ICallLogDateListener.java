package com.geely.lib.oneosapi.phone.inter;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.geely.lib.oneosapi.phone.telecom.GlyCallLogInfo;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public interface ICallLogDateListener extends IInterface {

    public static class Default implements ICallLogDateListener {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.geely.lib.oneosapi.phone.inter.ICallLogDateListener
        public void onCallLogDate(List<GlyCallLogInfo> callLogList) throws RemoteException {
        }
    }

    void onCallLogDate(List<GlyCallLogInfo> callLogList) throws RemoteException;

    public static abstract class Stub extends Binder implements ICallLogDateListener {
        private static final String DESCRIPTOR = "com.geely.lib.oneosapi.phone.inter.ICallLogDateListener";
        static final int TRANSACTION_onCallLogDate = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ICallLogDateListener asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface queryLocalInterface = obj.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof ICallLogDateListener)) {
                return (ICallLogDateListener) queryLocalInterface;
            }
            return new Proxy(obj);
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            if (code != 1) {
                if (code == 1598968902) {
                    reply.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(code, data, reply, flags);
            }
            data.enforceInterface(DESCRIPTOR);
            ArrayList createTypedArrayList = data.createTypedArrayList(GlyCallLogInfo.CREATOR);
            onCallLogDate(createTypedArrayList);
            reply.writeNoException();
            reply.writeTypedList(createTypedArrayList);
            return true;
        }

        private static class Proxy implements ICallLogDateListener {
            public static ICallLogDateListener sDefaultImpl;
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

            @Override // com.geely.lib.oneosapi.phone.inter.ICallLogDateListener
            public void onCallLogDate(List<GlyCallLogInfo> callLogList) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeTypedList(callLogList);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onCallLogDate(callLogList);
                    } else {
                        obtain2.readException();
                        obtain2.readTypedList(callLogList, GlyCallLogInfo.CREATOR);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ICallLogDateListener impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static ICallLogDateListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}