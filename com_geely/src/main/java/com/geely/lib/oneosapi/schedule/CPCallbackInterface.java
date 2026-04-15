package com.geely.lib.oneosapi.schedule;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.geely.lib.oneosapi.schedule.bean.CPHolidayInfo;
import com.geely.lib.oneosapi.schedule.bean.CPScheduleInfo;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public interface CPCallbackInterface extends IInterface {

    public static class Default implements CPCallbackInterface {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.geely.lib.oneosapi.schedule.CPCallbackInterface
        public void callbackCalendarEventInfo(List<CPScheduleInfo> list) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.schedule.CPCallbackInterface
        public void callbackCalendarImportInfo(List<CPHolidayInfo> list) throws RemoteException {
        }
    }

    void callbackCalendarEventInfo(List<CPScheduleInfo> list) throws RemoteException;

    void callbackCalendarImportInfo(List<CPHolidayInfo> list) throws RemoteException;

    public static abstract class Stub extends Binder implements CPCallbackInterface {
        private static final String DESCRIPTOR = "com.geely.lib.oneosapi.schedule.CPCallbackInterface";
        static final int TRANSACTION_callbackCalendarEventInfo = 1;
        static final int TRANSACTION_callbackCalendarImportInfo = 2;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static CPCallbackInterface asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface queryLocalInterface = obj.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof CPCallbackInterface)) {
                return (CPCallbackInterface) queryLocalInterface;
            }
            return new Proxy(obj);
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            if (code == 1) {
                data.enforceInterface(DESCRIPTOR);
                ArrayList createTypedArrayList = data.createTypedArrayList(CPScheduleInfo.CREATOR);
                callbackCalendarEventInfo(createTypedArrayList);
                reply.writeNoException();
                reply.writeTypedList(createTypedArrayList);
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
            ArrayList createTypedArrayList2 = data.createTypedArrayList(CPHolidayInfo.CREATOR);
            callbackCalendarImportInfo(createTypedArrayList2);
            reply.writeNoException();
            reply.writeTypedList(createTypedArrayList2);
            return true;
        }

        private static class Proxy implements CPCallbackInterface {
            public static CPCallbackInterface sDefaultImpl;
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

            @Override // com.geely.lib.oneosapi.schedule.CPCallbackInterface
            public void callbackCalendarEventInfo(List<CPScheduleInfo> list) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeTypedList(list);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().callbackCalendarEventInfo(list);
                    } else {
                        obtain2.readException();
                        obtain2.readTypedList(list, CPScheduleInfo.CREATOR);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.schedule.CPCallbackInterface
            public void callbackCalendarImportInfo(List<CPHolidayInfo> list) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeTypedList(list);
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().callbackCalendarImportInfo(list);
                    } else {
                        obtain2.readException();
                        obtain2.readTypedList(list, CPHolidayInfo.CREATOR);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(CPCallbackInterface impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static CPCallbackInterface getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}