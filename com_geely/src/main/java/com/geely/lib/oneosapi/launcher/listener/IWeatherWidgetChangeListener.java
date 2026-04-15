package com.geely.lib.oneosapi.launcher.listener;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes.dex */
public interface IWeatherWidgetChangeListener extends IInterface {

    public static class Default implements IWeatherWidgetChangeListener {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.geely.lib.oneosapi.launcher.listener.IWeatherWidgetChangeListener
        public void onWeatherWidgetChanged(int position, int widgetId) throws RemoteException {
        }
    }

    void onWeatherWidgetChanged(int position, int widgetId) throws RemoteException;

    public static abstract class Stub extends Binder implements IWeatherWidgetChangeListener {
        private static final String DESCRIPTOR = "com.geely.lib.oneosapi.launcher.listener.IWeatherWidgetChangeListener";
        static final int TRANSACTION_onWeatherWidgetChanged = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IWeatherWidgetChangeListener asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface queryLocalInterface = obj.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IWeatherWidgetChangeListener)) {
                return (IWeatherWidgetChangeListener) queryLocalInterface;
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
            onWeatherWidgetChanged(data.readInt(), data.readInt());
            reply.writeNoException();
            return true;
        }

        private static class Proxy implements IWeatherWidgetChangeListener {
            public static IWeatherWidgetChangeListener sDefaultImpl;
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

            @Override // com.geely.lib.oneosapi.launcher.listener.IWeatherWidgetChangeListener
            public void onWeatherWidgetChanged(int position, int widgetId) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(position);
                    obtain.writeInt(widgetId);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onWeatherWidgetChanged(position, widgetId);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IWeatherWidgetChangeListener impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static IWeatherWidgetChangeListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}