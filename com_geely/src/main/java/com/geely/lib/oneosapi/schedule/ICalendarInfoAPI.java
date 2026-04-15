package com.geely.lib.oneosapi.schedule;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.geely.lib.oneosapi.schedule.CPCallbackInterface;
import com.geely.lib.oneosapi.schedule.bean.CPAddScheduleBean;

/* loaded from: classes.dex */
public interface ICalendarInfoAPI extends IInterface {

    public static class Default implements ICalendarInfoAPI {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.geely.lib.oneosapi.schedule.ICalendarInfoAPI
        public void getCalendarEvents(CPCallbackInterface callback) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.schedule.ICalendarInfoAPI
        public void getCalendarImportEvents(CPCallbackInterface callback) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.schedule.ICalendarInfoAPI
        public void launchAddImportSchedule() throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.schedule.ICalendarInfoAPI
        public void launchAddImportScheduleParameter(long eventID) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.schedule.ICalendarInfoAPI
        public void launchAddSchedule() throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.schedule.ICalendarInfoAPI
        public void launchAddScheduleParameter(long eventID) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.schedule.ICalendarInfoAPI
        public void launchScheduleHome() throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.schedule.ICalendarInfoAPI
        public int setAddCalendarEvent(CPAddScheduleBean info) throws RemoteException {
            return 0;
        }
    }

    void getCalendarEvents(CPCallbackInterface callback) throws RemoteException;

    void getCalendarImportEvents(CPCallbackInterface callback) throws RemoteException;

    void launchAddImportSchedule() throws RemoteException;

    void launchAddImportScheduleParameter(long eventID) throws RemoteException;

    void launchAddSchedule() throws RemoteException;

    void launchAddScheduleParameter(long eventID) throws RemoteException;

    void launchScheduleHome() throws RemoteException;

    int setAddCalendarEvent(CPAddScheduleBean info) throws RemoteException;

    public static abstract class Stub extends Binder implements ICalendarInfoAPI {
        private static final String DESCRIPTOR = "com.geely.lib.oneosapi.schedule.ICalendarInfoAPI";
        static final int TRANSACTION_getCalendarEvents = 2;
        static final int TRANSACTION_getCalendarImportEvents = 1;
        static final int TRANSACTION_launchAddImportSchedule = 8;
        static final int TRANSACTION_launchAddImportScheduleParameter = 7;
        static final int TRANSACTION_launchAddSchedule = 6;
        static final int TRANSACTION_launchAddScheduleParameter = 5;
        static final int TRANSACTION_launchScheduleHome = 4;
        static final int TRANSACTION_setAddCalendarEvent = 3;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ICalendarInfoAPI asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface queryLocalInterface = obj.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof ICalendarInfoAPI)) {
                return (ICalendarInfoAPI) queryLocalInterface;
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
                    getCalendarImportEvents(CPCallbackInterface.Stub.asInterface(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case 2:
                    data.enforceInterface(DESCRIPTOR);
                    getCalendarEvents(CPCallbackInterface.Stub.asInterface(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case 3:
                    data.enforceInterface(DESCRIPTOR);
                    int addCalendarEvent = setAddCalendarEvent(data.readInt() != 0 ? CPAddScheduleBean.CREATOR.createFromParcel(data) : null);
                    reply.writeNoException();
                    reply.writeInt(addCalendarEvent);
                    return true;
                case 4:
                    data.enforceInterface(DESCRIPTOR);
                    launchScheduleHome();
                    reply.writeNoException();
                    return true;
                case 5:
                    data.enforceInterface(DESCRIPTOR);
                    launchAddScheduleParameter(data.readLong());
                    reply.writeNoException();
                    return true;
                case 6:
                    data.enforceInterface(DESCRIPTOR);
                    launchAddSchedule();
                    reply.writeNoException();
                    return true;
                case 7:
                    data.enforceInterface(DESCRIPTOR);
                    launchAddImportScheduleParameter(data.readLong());
                    reply.writeNoException();
                    return true;
                case 8:
                    data.enforceInterface(DESCRIPTOR);
                    launchAddImportSchedule();
                    reply.writeNoException();
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }

        private static class Proxy implements ICalendarInfoAPI {
            public static ICalendarInfoAPI sDefaultImpl;
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

            @Override // com.geely.lib.oneosapi.schedule.ICalendarInfoAPI
            public void getCalendarImportEvents(CPCallbackInterface callback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().getCalendarImportEvents(callback);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.schedule.ICalendarInfoAPI
            public void getCalendarEvents(CPCallbackInterface callback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().getCalendarEvents(callback);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.schedule.ICalendarInfoAPI
            public int setAddCalendarEvent(CPAddScheduleBean info) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (info != null) {
                        obtain.writeInt(1);
                        info.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(3, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setAddCalendarEvent(info);
                    }
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.schedule.ICalendarInfoAPI
            public void launchScheduleHome() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(4, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().launchScheduleHome();
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.schedule.ICalendarInfoAPI
            public void launchAddScheduleParameter(long eventID) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeLong(eventID);
                    if (!this.mRemote.transact(5, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().launchAddScheduleParameter(eventID);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.schedule.ICalendarInfoAPI
            public void launchAddSchedule() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(6, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().launchAddSchedule();
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.schedule.ICalendarInfoAPI
            public void launchAddImportScheduleParameter(long eventID) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeLong(eventID);
                    if (!this.mRemote.transact(7, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().launchAddImportScheduleParameter(eventID);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.schedule.ICalendarInfoAPI
            public void launchAddImportSchedule() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(8, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().launchAddImportSchedule();
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ICalendarInfoAPI impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static ICalendarInfoAPI getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}