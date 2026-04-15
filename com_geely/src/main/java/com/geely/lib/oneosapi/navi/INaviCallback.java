package com.geely.lib.oneosapi.navi;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.geely.lib.oneosapi.navi.ipc.ICommuteInfoBean;
import com.geely.lib.oneosapi.navi.ipc.IFavoritesInfoBean;
import com.geely.lib.oneosapi.navi.ipc.IGuidingInfoModelBean;
import com.geely.lib.oneosapi.navi.ipc.IHighwayExitInfoModelBean;
import com.geely.lib.oneosapi.navi.ipc.IHistoryPoisBean;
import com.geely.lib.oneosapi.navi.ipc.ILanesInfoBean;
import com.geely.lib.oneosapi.navi.ipc.ILocationInfoModelBean;
import com.geely.lib.oneosapi.navi.ipc.IParkingPoisBean;
import com.geely.lib.oneosapi.navi.ipc.IPoiInfoBean;
import com.geely.lib.oneosapi.navi.ipc.IRoutePlanInfoBean;
import com.geely.lib.oneosapi.navi.ipc.ITrafficConditionInfoBean;
import com.geely.lib.oneosapi.navi.ipc.ITripReporterBean;

/* loaded from: classes.dex */
public interface INaviCallback extends IInterface {

    public static class Default implements INaviCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.geely.lib.oneosapi.navi.INaviCallback
        public void onAdAreaChanged(ILocationInfoModelBean locationInfoModelBean) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.navi.INaviCallback
        public void onArrivedLastMile(IPoiInfoBean poiInfoBean) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.navi.INaviCallback
        public void onCommutePush(ICommuteInfoBean commuteInfoBean) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.navi.INaviCallback
        public void onDestinationParkingUpdate(IParkingPoisBean parkingPoisBean) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.navi.INaviCallback
        public void onFavoriteChanged(IFavoritesInfoBean favoritesInfoBean) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.navi.INaviCallback
        public void onGuideInfoChanged(IGuidingInfoModelBean guidingInfoModelBean) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.navi.INaviCallback
        public void onHighwayExitInfoUpdate(IHighwayExitInfoModelBean highwayExitInfoModelBean) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.navi.INaviCallback
        public void onHistoryPoisUpdateCallback(IHistoryPoisBean historyPoisBean) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.navi.INaviCallback
        public void onLanesInfoChanged(ILanesInfoBean lanesInfoBean) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.navi.INaviCallback
        public void onMapStatusChanged(int var) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.navi.INaviCallback
        public void onRouteInfoChanged(IRoutePlanInfoBean routePlanInfoBean) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.navi.INaviCallback
        public void onSendPOIToCar(IPoiInfoBean poiInfoBean) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.navi.INaviCallback
        public void onTrafficConditionsChanged(ITrafficConditionInfoBean trafficConditionInfoBean) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.navi.INaviCallback
        public void onTripReporterUpdate(ITripReporterBean tripReporter) throws RemoteException {
        }
    }

    void onAdAreaChanged(ILocationInfoModelBean locationInfoModelBean) throws RemoteException;

    void onArrivedLastMile(IPoiInfoBean poiInfoBean) throws RemoteException;

    void onCommutePush(ICommuteInfoBean commuteInfoBean) throws RemoteException;

    void onDestinationParkingUpdate(IParkingPoisBean parkingPoisBean) throws RemoteException;

    void onFavoriteChanged(IFavoritesInfoBean favoritesInfoBean) throws RemoteException;

    void onGuideInfoChanged(IGuidingInfoModelBean guidingInfoModelBean) throws RemoteException;

    void onHighwayExitInfoUpdate(IHighwayExitInfoModelBean highwayExitInfoModelBean) throws RemoteException;

    void onHistoryPoisUpdateCallback(IHistoryPoisBean historyPoisBean) throws RemoteException;

    void onLanesInfoChanged(ILanesInfoBean lanesInfoBean) throws RemoteException;

    void onMapStatusChanged(int var) throws RemoteException;

    void onRouteInfoChanged(IRoutePlanInfoBean routePlanInfoBean) throws RemoteException;

    void onSendPOIToCar(IPoiInfoBean poiInfoBean) throws RemoteException;

    void onTrafficConditionsChanged(ITrafficConditionInfoBean trafficConditionInfoBean) throws RemoteException;

    void onTripReporterUpdate(ITripReporterBean tripReporter) throws RemoteException;

    public static abstract class Stub extends Binder implements INaviCallback {
        private static final String DESCRIPTOR = "com.geely.lib.oneosapi.navi.INaviCallback";
        static final int TRANSACTION_onAdAreaChanged = 8;
        static final int TRANSACTION_onArrivedLastMile = 7;
        static final int TRANSACTION_onCommutePush = 11;
        static final int TRANSACTION_onDestinationParkingUpdate = 12;
        static final int TRANSACTION_onFavoriteChanged = 2;
        static final int TRANSACTION_onGuideInfoChanged = 4;
        static final int TRANSACTION_onHighwayExitInfoUpdate = 5;
        static final int TRANSACTION_onHistoryPoisUpdateCallback = 14;
        static final int TRANSACTION_onLanesInfoChanged = 10;
        static final int TRANSACTION_onMapStatusChanged = 1;
        static final int TRANSACTION_onRouteInfoChanged = 3;
        static final int TRANSACTION_onSendPOIToCar = 6;
        static final int TRANSACTION_onTrafficConditionsChanged = 9;
        static final int TRANSACTION_onTripReporterUpdate = 13;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static INaviCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface queryLocalInterface = obj.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof INaviCallback)) {
                return (INaviCallback) queryLocalInterface;
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
                    onMapStatusChanged(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    IFavoritesInfoBean createFromParcel = parcel.readInt() != 0 ? IFavoritesInfoBean.CREATOR.createFromParcel(parcel) : null;
                    onFavoriteChanged(createFromParcel);
                    parcel2.writeNoException();
                    if (createFromParcel != null) {
                        parcel2.writeInt(1);
                        createFromParcel.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    IRoutePlanInfoBean createFromParcel2 = parcel.readInt() != 0 ? IRoutePlanInfoBean.CREATOR.createFromParcel(parcel) : null;
                    onRouteInfoChanged(createFromParcel2);
                    parcel2.writeNoException();
                    if (createFromParcel2 != null) {
                        parcel2.writeInt(1);
                        createFromParcel2.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    IGuidingInfoModelBean createFromParcel3 = parcel.readInt() != 0 ? IGuidingInfoModelBean.CREATOR.createFromParcel(parcel) : null;
                    onGuideInfoChanged(createFromParcel3);
                    parcel2.writeNoException();
                    if (createFromParcel3 != null) {
                        parcel2.writeInt(1);
                        createFromParcel3.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    IHighwayExitInfoModelBean createFromParcel4 = parcel.readInt() != 0 ? IHighwayExitInfoModelBean.CREATOR.createFromParcel(parcel) : null;
                    onHighwayExitInfoUpdate(createFromParcel4);
                    parcel2.writeNoException();
                    if (createFromParcel4 != null) {
                        parcel2.writeInt(1);
                        createFromParcel4.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    IPoiInfoBean createFromParcel5 = parcel.readInt() != 0 ? IPoiInfoBean.CREATOR.createFromParcel(parcel) : null;
                    onSendPOIToCar(createFromParcel5);
                    parcel2.writeNoException();
                    if (createFromParcel5 != null) {
                        parcel2.writeInt(1);
                        createFromParcel5.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    IPoiInfoBean createFromParcel6 = parcel.readInt() != 0 ? IPoiInfoBean.CREATOR.createFromParcel(parcel) : null;
                    onArrivedLastMile(createFromParcel6);
                    parcel2.writeNoException();
                    if (createFromParcel6 != null) {
                        parcel2.writeInt(1);
                        createFromParcel6.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    ILocationInfoModelBean createFromParcel7 = parcel.readInt() != 0 ? ILocationInfoModelBean.CREATOR.createFromParcel(parcel) : null;
                    onAdAreaChanged(createFromParcel7);
                    parcel2.writeNoException();
                    if (createFromParcel7 != null) {
                        parcel2.writeInt(1);
                        createFromParcel7.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    ITrafficConditionInfoBean createFromParcel8 = parcel.readInt() != 0 ? ITrafficConditionInfoBean.CREATOR.createFromParcel(parcel) : null;
                    onTrafficConditionsChanged(createFromParcel8);
                    parcel2.writeNoException();
                    if (createFromParcel8 != null) {
                        parcel2.writeInt(1);
                        createFromParcel8.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    ILanesInfoBean createFromParcel9 = parcel.readInt() != 0 ? ILanesInfoBean.CREATOR.createFromParcel(parcel) : null;
                    onLanesInfoChanged(createFromParcel9);
                    parcel2.writeNoException();
                    if (createFromParcel9 != null) {
                        parcel2.writeInt(1);
                        createFromParcel9.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    ICommuteInfoBean createFromParcel10 = parcel.readInt() != 0 ? ICommuteInfoBean.CREATOR.createFromParcel(parcel) : null;
                    onCommutePush(createFromParcel10);
                    parcel2.writeNoException();
                    if (createFromParcel10 != null) {
                        parcel2.writeInt(1);
                        createFromParcel10.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    IParkingPoisBean createFromParcel11 = parcel.readInt() != 0 ? IParkingPoisBean.CREATOR.createFromParcel(parcel) : null;
                    onDestinationParkingUpdate(createFromParcel11);
                    parcel2.writeNoException();
                    if (createFromParcel11 != null) {
                        parcel2.writeInt(1);
                        createFromParcel11.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    ITripReporterBean createFromParcel12 = parcel.readInt() != 0 ? ITripReporterBean.CREATOR.createFromParcel(parcel) : null;
                    onTripReporterUpdate(createFromParcel12);
                    parcel2.writeNoException();
                    if (createFromParcel12 != null) {
                        parcel2.writeInt(1);
                        createFromParcel12.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    IHistoryPoisBean createFromParcel13 = parcel.readInt() != 0 ? IHistoryPoisBean.CREATOR.createFromParcel(parcel) : null;
                    onHistoryPoisUpdateCallback(createFromParcel13);
                    parcel2.writeNoException();
                    if (createFromParcel13 != null) {
                        parcel2.writeInt(1);
                        createFromParcel13.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements INaviCallback {
            public static INaviCallback sDefaultImpl;
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

            @Override // com.geely.lib.oneosapi.navi.INaviCallback
            public void onMapStatusChanged(int var) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(var);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onMapStatusChanged(var);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.navi.INaviCallback
            public void onFavoriteChanged(IFavoritesInfoBean favoritesInfoBean) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (favoritesInfoBean != null) {
                        obtain.writeInt(1);
                        favoritesInfoBean.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onFavoriteChanged(favoritesInfoBean);
                        return;
                    }
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        favoritesInfoBean.readFromParcel(obtain2);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.navi.INaviCallback
            public void onRouteInfoChanged(IRoutePlanInfoBean routePlanInfoBean) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (routePlanInfoBean != null) {
                        obtain.writeInt(1);
                        routePlanInfoBean.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(3, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onRouteInfoChanged(routePlanInfoBean);
                        return;
                    }
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        routePlanInfoBean.readFromParcel(obtain2);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.navi.INaviCallback
            public void onGuideInfoChanged(IGuidingInfoModelBean guidingInfoModelBean) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (guidingInfoModelBean != null) {
                        obtain.writeInt(1);
                        guidingInfoModelBean.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(4, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onGuideInfoChanged(guidingInfoModelBean);
                        return;
                    }
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        guidingInfoModelBean.readFromParcel(obtain2);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.navi.INaviCallback
            public void onHighwayExitInfoUpdate(IHighwayExitInfoModelBean highwayExitInfoModelBean) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (highwayExitInfoModelBean != null) {
                        obtain.writeInt(1);
                        highwayExitInfoModelBean.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(5, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onHighwayExitInfoUpdate(highwayExitInfoModelBean);
                        return;
                    }
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        highwayExitInfoModelBean.readFromParcel(obtain2);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.navi.INaviCallback
            public void onSendPOIToCar(IPoiInfoBean poiInfoBean) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (poiInfoBean != null) {
                        obtain.writeInt(1);
                        poiInfoBean.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(6, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onSendPOIToCar(poiInfoBean);
                        return;
                    }
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        poiInfoBean.readFromParcel(obtain2);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.navi.INaviCallback
            public void onArrivedLastMile(IPoiInfoBean poiInfoBean) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (poiInfoBean != null) {
                        obtain.writeInt(1);
                        poiInfoBean.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(7, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onArrivedLastMile(poiInfoBean);
                        return;
                    }
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        poiInfoBean.readFromParcel(obtain2);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.navi.INaviCallback
            public void onAdAreaChanged(ILocationInfoModelBean locationInfoModelBean) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (locationInfoModelBean != null) {
                        obtain.writeInt(1);
                        locationInfoModelBean.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(8, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onAdAreaChanged(locationInfoModelBean);
                        return;
                    }
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        locationInfoModelBean.readFromParcel(obtain2);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.navi.INaviCallback
            public void onTrafficConditionsChanged(ITrafficConditionInfoBean trafficConditionInfoBean) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (trafficConditionInfoBean != null) {
                        obtain.writeInt(1);
                        trafficConditionInfoBean.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(9, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onTrafficConditionsChanged(trafficConditionInfoBean);
                        return;
                    }
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        trafficConditionInfoBean.readFromParcel(obtain2);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.navi.INaviCallback
            public void onLanesInfoChanged(ILanesInfoBean lanesInfoBean) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (lanesInfoBean != null) {
                        obtain.writeInt(1);
                        lanesInfoBean.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(10, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onLanesInfoChanged(lanesInfoBean);
                        return;
                    }
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        lanesInfoBean.readFromParcel(obtain2);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.navi.INaviCallback
            public void onCommutePush(ICommuteInfoBean commuteInfoBean) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (commuteInfoBean != null) {
                        obtain.writeInt(1);
                        commuteInfoBean.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(11, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onCommutePush(commuteInfoBean);
                        return;
                    }
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        commuteInfoBean.readFromParcel(obtain2);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.navi.INaviCallback
            public void onDestinationParkingUpdate(IParkingPoisBean parkingPoisBean) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (parkingPoisBean != null) {
                        obtain.writeInt(1);
                        parkingPoisBean.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(12, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onDestinationParkingUpdate(parkingPoisBean);
                        return;
                    }
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        parkingPoisBean.readFromParcel(obtain2);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.navi.INaviCallback
            public void onTripReporterUpdate(ITripReporterBean tripReporter) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (tripReporter != null) {
                        obtain.writeInt(1);
                        tripReporter.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(13, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onTripReporterUpdate(tripReporter);
                        return;
                    }
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        tripReporter.readFromParcel(obtain2);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.navi.INaviCallback
            public void onHistoryPoisUpdateCallback(IHistoryPoisBean historyPoisBean) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (historyPoisBean != null) {
                        obtain.writeInt(1);
                        historyPoisBean.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(14, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onHistoryPoisUpdateCallback(historyPoisBean);
                        return;
                    }
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        historyPoisBean.readFromParcel(obtain2);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(INaviCallback impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static INaviCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}