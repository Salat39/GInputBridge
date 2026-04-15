package com.geely.lib.oneosapi.weather;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.geely.lib.oneosapi.weather.IWeatherAPICallback;
import com.geely.lib.oneosapi.weather.IWeatherCallback;

/* loaded from: classes.dex */
public interface IWeatherService extends IInterface {

    public static class Default implements IWeatherService {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.geely.lib.oneosapi.weather.IWeatherService
        public boolean getCurrentCity() throws RemoteException {
            return false;
        }

        @Override // com.geely.lib.oneosapi.weather.IWeatherService
        public String getLocationCity() throws RemoteException {
            return null;
        }

        @Override // com.geely.lib.oneosapi.weather.IWeatherService
        public boolean getLocationPermission() throws RemoteException {
            return false;
        }

        @Override // com.geely.lib.oneosapi.weather.IWeatherService
        public void getWeatherByCityId(String cityID, IWeatherAPICallback callback) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.weather.IWeatherService
        public void getWeatherByLocation(double lon, double lat, IWeatherAPICallback callback) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.weather.IWeatherService
        public void getWeatherInfoByCityId(String cityID, IWeatherCallback callback) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.weather.IWeatherService
        public void getWeatherInfoByLocation(double lon, double lat, IWeatherCallback callback) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.weather.IWeatherService
        public boolean setCurrentCity(boolean status) throws RemoteException {
            return false;
        }

        @Override // com.geely.lib.oneosapi.weather.IWeatherService
        public boolean setLocationPermission(boolean status) throws RemoteException {
            return false;
        }
    }

    boolean getCurrentCity() throws RemoteException;

    String getLocationCity() throws RemoteException;

    boolean getLocationPermission() throws RemoteException;

    void getWeatherByCityId(String cityID, IWeatherAPICallback callback) throws RemoteException;

    void getWeatherByLocation(double lon, double lat, IWeatherAPICallback callback) throws RemoteException;

    void getWeatherInfoByCityId(String cityID, IWeatherCallback callback) throws RemoteException;

    void getWeatherInfoByLocation(double lon, double lat, IWeatherCallback callback) throws RemoteException;

    boolean setCurrentCity(boolean status) throws RemoteException;

    boolean setLocationPermission(boolean status) throws RemoteException;

    public static abstract class Stub extends Binder implements IWeatherService {
        private static final String DESCRIPTOR = "com.geely.lib.oneosapi.weather.IWeatherService";
        static final int TRANSACTION_getCurrentCity = 6;
        static final int TRANSACTION_getLocationCity = 7;
        static final int TRANSACTION_getLocationPermission = 5;
        static final int TRANSACTION_getWeatherByCityId = 2;
        static final int TRANSACTION_getWeatherByLocation = 1;
        static final int TRANSACTION_getWeatherInfoByCityId = 9;
        static final int TRANSACTION_getWeatherInfoByLocation = 8;
        static final int TRANSACTION_setCurrentCity = 4;
        static final int TRANSACTION_setLocationPermission = 3;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IWeatherService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface queryLocalInterface = obj.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IWeatherService)) {
                return (IWeatherService) queryLocalInterface;
            }
            return new Proxy(obj);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            switch (i) {
                case 1:
                    parcel.enforceInterface(DESCRIPTOR);
                    getWeatherByLocation(parcel.readDouble(), parcel.readDouble(), IWeatherAPICallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    getWeatherByCityId(parcel.readString(), IWeatherAPICallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean locationPermission = setLocationPermission(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(locationPermission ? 1 : 0);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean currentCity = setCurrentCity(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(currentCity ? 1 : 0);
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean locationPermission2 = getLocationPermission();
                    parcel2.writeNoException();
                    parcel2.writeInt(locationPermission2 ? 1 : 0);
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean currentCity2 = getCurrentCity();
                    parcel2.writeNoException();
                    parcel2.writeInt(currentCity2 ? 1 : 0);
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    String locationCity = getLocationCity();
                    parcel2.writeNoException();
                    parcel2.writeString(locationCity);
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    getWeatherInfoByLocation(parcel.readDouble(), parcel.readDouble(), IWeatherCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    getWeatherInfoByCityId(parcel.readString(), IWeatherCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IWeatherService {
            public static IWeatherService sDefaultImpl;
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

            @Override // com.geely.lib.oneosapi.weather.IWeatherService
            public void getWeatherByLocation(double lon, double lat, IWeatherAPICallback callback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeDouble(lon);
                    obtain.writeDouble(lat);
                    obtain.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().getWeatherByLocation(lon, lat, callback);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.weather.IWeatherService
            public void getWeatherByCityId(String cityID, IWeatherAPICallback callback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(cityID);
                    obtain.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().getWeatherByCityId(cityID, callback);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.weather.IWeatherService
            public boolean setLocationPermission(boolean status) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(status ? 1 : 0);
                    if (!this.mRemote.transact(3, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setLocationPermission(status);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.weather.IWeatherService
            public boolean setCurrentCity(boolean status) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(status ? 1 : 0);
                    if (!this.mRemote.transact(4, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setCurrentCity(status);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.weather.IWeatherService
            public boolean getLocationPermission() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(5, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getLocationPermission();
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.weather.IWeatherService
            public boolean getCurrentCity() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(6, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCurrentCity();
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.weather.IWeatherService
            public String getLocationCity() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(7, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getLocationCity();
                    }
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.weather.IWeatherService
            public void getWeatherInfoByLocation(double lon, double lat, IWeatherCallback callback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeDouble(lon);
                    obtain.writeDouble(lat);
                    obtain.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    if (!this.mRemote.transact(8, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().getWeatherInfoByLocation(lon, lat, callback);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.weather.IWeatherService
            public void getWeatherInfoByCityId(String cityID, IWeatherCallback callback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(cityID);
                    obtain.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    if (!this.mRemote.transact(9, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().getWeatherInfoByCityId(cityID, callback);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IWeatherService impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static IWeatherService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}