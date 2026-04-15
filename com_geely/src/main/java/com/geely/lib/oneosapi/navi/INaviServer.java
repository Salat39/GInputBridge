package com.geely.lib.oneosapi.navi;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.view.MotionEvent;
import android.view.Surface;
import com.geely.lib.oneosapi.navi.INaviCallback;
import com.geely.lib.oneosapi.navi.INaviEventNotify;
import com.geely.lib.oneosapi.navi.INaviInfo;
import com.geely.lib.oneosapi.navi.INaviObserver;
import com.geely.lib.oneosapi.navi.ipc.IApiRequestBaseModelBean;
import com.geely.lib.oneosapi.navi.ipc.IEditFavoriteRequestBean;
import com.geely.lib.oneosapi.navi.ipc.IKeywordSearchRequestBean;
import com.geely.lib.oneosapi.navi.ipc.ILatLngBean;
import com.geely.lib.oneosapi.navi.ipc.IModifyViaPoiRequestBean;
import com.geely.lib.oneosapi.navi.ipc.IRoutePlanRequestBean;
import com.geely.lib.oneosapi.navi.ipc.ISearchAlongWayRequestBean;
import com.geely.lib.oneosapi.navi.ipc.ISearchAroundRequestBean;
import com.geely.lib.oneosapi.navi.ipc.ISetMapFeaturesRunningStateBean;
import com.geely.lib.oneosapi.navi.ipc.IViaRoadRequestBean;
import com.geely.lib.oneosapi.navi.ipc.TransferModel;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public interface INaviServer extends IInterface {

    public static class Default implements INaviServer {
        @Override // com.geely.lib.oneosapi.navi.INaviServer
        public boolean addNaviEventNotify(INaviEventNotify eventNotify) throws RemoteException {
            return false;
        }

        @Override // com.geely.lib.oneosapi.navi.INaviServer
        public boolean addSurface(Surface surface, int type, int height, int width) throws RemoteException {
            return false;
        }

        @Override // com.geely.lib.oneosapi.navi.INaviServer
        public boolean addSurfaceWithRoadView(Surface surface, int type, int height, int width, int roadViewHeight, int roadViewWidth) throws RemoteException {
            return false;
        }

        @Override // com.geely.lib.oneosapi.navi.INaviServer
        public boolean addSurfaceWithRoadViewAndOffset(Surface surface, int type, int height, int width, int roadViewHeight, int roadViewWidth, int offsetX, int offsetY) throws RemoteException {
            return false;
        }

        @Override // com.geely.lib.oneosapi.navi.INaviServer
        public void alongTheWaySearch(int var1, ISearchAlongWayRequestBean searchAlongWayRequestBean) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.geely.lib.oneosapi.navi.INaviServer
        public void backToMap() throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.navi.INaviServer
        public void cancelNavi() throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.navi.INaviServer
        public void commonRequest(IApiRequestBaseModelBean iapiRequestBaseModel) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.navi.INaviServer
        public void dispatchTouchEvent(MotionEvent ev) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.navi.INaviServer
        public void downloadOfflineMap(List<String> list) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.navi.INaviServer
        public void editFavoritePois(IEditFavoriteRequestBean editFavoriteRequestBean) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.navi.INaviServer
        public void exitMap() throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.navi.INaviServer
        public void getArrivedFrontPoiInfo(String var) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.navi.INaviServer
        public void getFavoritePois(int var) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.navi.INaviServer
        public void getFrequentPois(int var) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.navi.INaviServer
        public void getFrontTraffic(int var) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.navi.INaviServer
        public void getGuideInfo() throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.navi.INaviServer
        public void getHighwayExitInfo(int var) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.navi.INaviServer
        public void getHistoryPois(int var) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.navi.INaviServer
        public String getLocationRoadInfo() throws RemoteException {
            return null;
        }

        @Override // com.geely.lib.oneosapi.navi.INaviServer
        public void getMapSupportedFeatures() throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.navi.INaviServer
        public String getPackageName() throws RemoteException {
            return null;
        }

        @Override // com.geely.lib.oneosapi.navi.INaviServer
        public void getRemainNopInfo() throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.navi.INaviServer
        public void getRoutePlanInfo() throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.navi.INaviServer
        public void getRunningMapFeatures() throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.navi.INaviServer
        public void getSpeedLimitInfo() throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.navi.INaviServer
        public void getTrafficSummaryInfo(String var) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.navi.INaviServer
        public boolean goCompany() throws RemoteException {
            return false;
        }

        @Override // com.geely.lib.oneosapi.navi.INaviServer
        public void goFavorite(int var) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.navi.INaviServer
        public boolean goHome() throws RemoteException {
            return false;
        }

        @Override // com.geely.lib.oneosapi.navi.INaviServer
        public void goOfflineMapDownload() throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.navi.INaviServer
        public TransferModel invokeNaviAPI(TransferModel reqModel) throws RemoteException {
            return null;
        }

        @Override // com.geely.lib.oneosapi.navi.INaviServer
        public int invokeNaviAPIAsync(TransferModel reqModel, INaviObserver observer) throws RemoteException {
            return 0;
        }

        @Override // com.geely.lib.oneosapi.navi.INaviServer
        public void launchMap() throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.navi.INaviServer
        public boolean mapIsLaunched() throws RemoteException {
            return false;
        }

        @Override // com.geely.lib.oneosapi.navi.INaviServer
        public void mapZoomInOut(int var) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.navi.INaviServer
        public void modifyNaviVia(IModifyViaPoiRequestBean modifyViaPoiRequestBean) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.navi.INaviServer
        public boolean muteNaviAudio() throws RemoteException {
            return false;
        }

        @Override // com.geely.lib.oneosapi.navi.INaviServer
        public void naviFullView() throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.navi.INaviServer
        public boolean naviTo(String message) throws RemoteException {
            return false;
        }

        @Override // com.geely.lib.oneosapi.navi.INaviServer
        public boolean naviToByNaviInfo(INaviInfo naviInfo) throws RemoteException {
            return false;
        }

        @Override // com.geely.lib.oneosapi.navi.INaviServer
        public void openDestinationSearch(IKeywordSearchRequestBean keywordSearchRequestBean) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.navi.INaviServer
        public void register(INaviCallback iRemoteCallback) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.navi.INaviServer
        public boolean registerNaviObserver(INaviObserver observer) throws RemoteException {
            return false;
        }

        @Override // com.geely.lib.oneosapi.navi.INaviServer
        public boolean removeNaviEventNotify(INaviEventNotify eventNotify) throws RemoteException {
            return false;
        }

        @Override // com.geely.lib.oneosapi.navi.INaviServer
        public boolean removedSurface(Surface surface, int type) throws RemoteException {
            return false;
        }

        @Override // com.geely.lib.oneosapi.navi.INaviServer
        public void requestGeoDecoder(ILatLngBean latLngBean) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.navi.INaviServer
        public void requestRoutePlanOrNavi(long uid, IRoutePlanRequestBean routePlanRequestBean) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.navi.INaviServer
        public void requestRoutePlanOrNaviViaRoad(long uid, IRoutePlanRequestBean iRoutePlanRequestBean, IViaRoadRequestBean iViaRoadRequest) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.navi.INaviServer
        public void rerouting() throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.navi.INaviServer
        public void searchAround(long uid, int var1, ISearchAroundRequestBean searchAroundRequestBean) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.navi.INaviServer
        public void searchByKeyword(long uid, int var1, IKeywordSearchRequestBean keywordSearchRequestBean) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.navi.INaviServer
        public boolean searchNearby(String keyword, double longitude, double latitude) throws RemoteException {
            return false;
        }

        @Override // com.geely.lib.oneosapi.navi.INaviServer
        public void searchPoiNearbyBurnout(int i, IKeywordSearchRequestBean bean) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.navi.INaviServer
        public void selectRoute(int var) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.navi.INaviServer
        public void setMapFeaturesRunningState(ISetMapFeaturesRunningStateBean setMapFeaturesRunningStateBean) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.navi.INaviServer
        public void setMapViewMode(int var) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.navi.INaviServer
        public void setReportNaviType(int i) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.navi.INaviServer
        public void setRoutePlanStrategy(int var) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.navi.INaviServer
        public void showMyLocation(boolean var) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.navi.INaviServer
        public void specialPoiNavi(int guidanceType, int poiType) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.navi.INaviServer
        public boolean stopNavi() throws RemoteException {
            return false;
        }

        @Override // com.geely.lib.oneosapi.navi.INaviServer
        public void switchARNavigation(boolean var) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.navi.INaviServer
        public void switchTraffic(boolean var) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.navi.INaviServer
        public boolean unmuteNaviAudio() throws RemoteException {
            return false;
        }

        @Override // com.geely.lib.oneosapi.navi.INaviServer
        public void unrRegister(INaviCallback iRemoteCallback) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.navi.INaviServer
        public boolean unregisterNaviObserver(INaviObserver observer) throws RemoteException {
            return false;
        }
    }

    boolean addNaviEventNotify(INaviEventNotify eventNotify) throws RemoteException;

    boolean addSurface(Surface surface, int type, int height, int width) throws RemoteException;

    boolean addSurfaceWithRoadView(Surface surface, int type, int height, int width, int roadViewHeight, int roadViewWidth) throws RemoteException;

    boolean addSurfaceWithRoadViewAndOffset(Surface surface, int type, int height, int width, int roadViewHeight, int roadViewWidth, int offsetX, int offsetY) throws RemoteException;

    void alongTheWaySearch(int var1, ISearchAlongWayRequestBean searchAlongWayRequestBean) throws RemoteException;

    void backToMap() throws RemoteException;

    void cancelNavi() throws RemoteException;

    void commonRequest(IApiRequestBaseModelBean iapiRequestBaseModel) throws RemoteException;

    void dispatchTouchEvent(MotionEvent ev) throws RemoteException;

    void downloadOfflineMap(List<String> list) throws RemoteException;

    void editFavoritePois(IEditFavoriteRequestBean editFavoriteRequestBean) throws RemoteException;

    void exitMap() throws RemoteException;

    void getArrivedFrontPoiInfo(String var) throws RemoteException;

    void getFavoritePois(int var) throws RemoteException;

    void getFrequentPois(int var) throws RemoteException;

    void getFrontTraffic(int var) throws RemoteException;

    void getGuideInfo() throws RemoteException;

    void getHighwayExitInfo(int var) throws RemoteException;

    void getHistoryPois(int var) throws RemoteException;

    String getLocationRoadInfo() throws RemoteException;

    void getMapSupportedFeatures() throws RemoteException;

    String getPackageName() throws RemoteException;

    void getRemainNopInfo() throws RemoteException;

    void getRoutePlanInfo() throws RemoteException;

    void getRunningMapFeatures() throws RemoteException;

    void getSpeedLimitInfo() throws RemoteException;

    void getTrafficSummaryInfo(String var) throws RemoteException;

    boolean goCompany() throws RemoteException;

    void goFavorite(int var) throws RemoteException;

    boolean goHome() throws RemoteException;

    void goOfflineMapDownload() throws RemoteException;

    TransferModel invokeNaviAPI(TransferModel reqModel) throws RemoteException;

    int invokeNaviAPIAsync(TransferModel reqModel, INaviObserver observer) throws RemoteException;

    void launchMap() throws RemoteException;

    boolean mapIsLaunched() throws RemoteException;

    void mapZoomInOut(int var) throws RemoteException;

    void modifyNaviVia(IModifyViaPoiRequestBean modifyViaPoiRequestBean) throws RemoteException;

    boolean muteNaviAudio() throws RemoteException;

    void naviFullView() throws RemoteException;

    boolean naviTo(String message) throws RemoteException;

    boolean naviToByNaviInfo(INaviInfo naviInfo) throws RemoteException;

    void openDestinationSearch(IKeywordSearchRequestBean keywordSearchRequestBean) throws RemoteException;

    void register(INaviCallback iRemoteCallback) throws RemoteException;

    boolean registerNaviObserver(INaviObserver observer) throws RemoteException;

    boolean removeNaviEventNotify(INaviEventNotify eventNotify) throws RemoteException;

    boolean removedSurface(Surface surface, int type) throws RemoteException;

    void requestGeoDecoder(ILatLngBean latLngBean) throws RemoteException;

    void requestRoutePlanOrNavi(long uid, IRoutePlanRequestBean routePlanRequestBean) throws RemoteException;

    void requestRoutePlanOrNaviViaRoad(long uid, IRoutePlanRequestBean iRoutePlanRequestBean, IViaRoadRequestBean iViaRoadRequest) throws RemoteException;

    void rerouting() throws RemoteException;

    void searchAround(long uid, int var1, ISearchAroundRequestBean searchAroundRequestBean) throws RemoteException;

    void searchByKeyword(long uid, int var1, IKeywordSearchRequestBean keywordSearchRequestBean) throws RemoteException;

    boolean searchNearby(String keyword, double longitude, double latitude) throws RemoteException;

    void searchPoiNearbyBurnout(int i, IKeywordSearchRequestBean bean) throws RemoteException;

    void selectRoute(int var) throws RemoteException;

    void setMapFeaturesRunningState(ISetMapFeaturesRunningStateBean setMapFeaturesRunningStateBean) throws RemoteException;

    void setMapViewMode(int var) throws RemoteException;

    void setReportNaviType(int i) throws RemoteException;

    void setRoutePlanStrategy(int var) throws RemoteException;

    void showMyLocation(boolean var) throws RemoteException;

    void specialPoiNavi(int guidanceType, int poiType) throws RemoteException;

    boolean stopNavi() throws RemoteException;

    void switchARNavigation(boolean var) throws RemoteException;

    void switchTraffic(boolean var) throws RemoteException;

    boolean unmuteNaviAudio() throws RemoteException;

    void unrRegister(INaviCallback iRemoteCallback) throws RemoteException;

    boolean unregisterNaviObserver(INaviObserver observer) throws RemoteException;

    public static abstract class Stub extends Binder implements INaviServer {
        private static final String DESCRIPTOR = "com.geely.lib.oneosapi.navi.INaviServer";
        static final int TRANSACTION_addNaviEventNotify = 66;
        static final int TRANSACTION_addSurface = 46;
        static final int TRANSACTION_addSurfaceWithRoadView = 47;
        static final int TRANSACTION_addSurfaceWithRoadViewAndOffset = 54;
        static final int TRANSACTION_alongTheWaySearch = 32;
        static final int TRANSACTION_backToMap = 16;
        static final int TRANSACTION_cancelNavi = 6;
        static final int TRANSACTION_commonRequest = 5;
        static final int TRANSACTION_dispatchTouchEvent = 49;
        static final int TRANSACTION_downloadOfflineMap = 28;
        static final int TRANSACTION_editFavoritePois = 38;
        static final int TRANSACTION_exitMap = 17;
        static final int TRANSACTION_getArrivedFrontPoiInfo = 43;
        static final int TRANSACTION_getFavoritePois = 39;
        static final int TRANSACTION_getFrequentPois = 41;
        static final int TRANSACTION_getFrontTraffic = 8;
        static final int TRANSACTION_getGuideInfo = 9;
        static final int TRANSACTION_getHighwayExitInfo = 10;
        static final int TRANSACTION_getHistoryPois = 40;
        static final int TRANSACTION_getLocationRoadInfo = 63;
        static final int TRANSACTION_getMapSupportedFeatures = 35;
        static final int TRANSACTION_getPackageName = 45;
        static final int TRANSACTION_getRemainNopInfo = 53;
        static final int TRANSACTION_getRoutePlanInfo = 11;
        static final int TRANSACTION_getRunningMapFeatures = 36;
        static final int TRANSACTION_getSpeedLimitInfo = 42;
        static final int TRANSACTION_getTrafficSummaryInfo = 25;
        static final int TRANSACTION_goCompany = 58;
        static final int TRANSACTION_goFavorite = 26;
        static final int TRANSACTION_goHome = 57;
        static final int TRANSACTION_goOfflineMapDownload = 29;
        static final int TRANSACTION_invokeNaviAPI = 64;
        static final int TRANSACTION_invokeNaviAPIAsync = 65;
        static final int TRANSACTION_launchMap = 15;
        static final int TRANSACTION_mapIsLaunched = 18;
        static final int TRANSACTION_mapZoomInOut = 20;
        static final int TRANSACTION_modifyNaviVia = 14;
        static final int TRANSACTION_muteNaviAudio = 55;
        static final int TRANSACTION_naviFullView = 22;
        static final int TRANSACTION_naviTo = 61;
        static final int TRANSACTION_naviToByNaviInfo = 62;
        static final int TRANSACTION_openDestinationSearch = 19;
        static final int TRANSACTION_register = 3;
        static final int TRANSACTION_registerNaviObserver = 1;
        static final int TRANSACTION_removeNaviEventNotify = 67;
        static final int TRANSACTION_removedSurface = 48;
        static final int TRANSACTION_requestGeoDecoder = 33;
        static final int TRANSACTION_requestRoutePlanOrNavi = 13;
        static final int TRANSACTION_requestRoutePlanOrNaviViaRoad = 52;
        static final int TRANSACTION_rerouting = 44;
        static final int TRANSACTION_searchAround = 31;
        static final int TRANSACTION_searchByKeyword = 30;
        static final int TRANSACTION_searchNearby = 59;
        static final int TRANSACTION_searchPoiNearbyBurnout = 51;
        static final int TRANSACTION_selectRoute = 7;
        static final int TRANSACTION_setMapFeaturesRunningState = 37;
        static final int TRANSACTION_setMapViewMode = 21;
        static final int TRANSACTION_setReportNaviType = 50;
        static final int TRANSACTION_setRoutePlanStrategy = 34;
        static final int TRANSACTION_showMyLocation = 24;
        static final int TRANSACTION_specialPoiNavi = 12;
        static final int TRANSACTION_stopNavi = 60;
        static final int TRANSACTION_switchARNavigation = 27;
        static final int TRANSACTION_switchTraffic = 23;
        static final int TRANSACTION_unmuteNaviAudio = 56;
        static final int TRANSACTION_unrRegister = 4;
        static final int TRANSACTION_unregisterNaviObserver = 2;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static INaviServer asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface queryLocalInterface = obj.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof INaviServer)) {
                return (INaviServer) queryLocalInterface;
            }
            return new Proxy(obj);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return false;
        }

        private static class Proxy implements INaviServer {
            public static INaviServer sDefaultImpl;
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

            @Override // com.geely.lib.oneosapi.navi.INaviServer
            public boolean registerNaviObserver(INaviObserver observer) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(observer != null ? observer.asBinder() : null);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().registerNaviObserver(observer);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.navi.INaviServer
            public boolean unregisterNaviObserver(INaviObserver observer) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(observer != null ? observer.asBinder() : null);
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().unregisterNaviObserver(observer);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.navi.INaviServer
            public void register(INaviCallback iRemoteCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iRemoteCallback != null ? iRemoteCallback.asBinder() : null);
                    if (!this.mRemote.transact(3, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().register(iRemoteCallback);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.navi.INaviServer
            public void unrRegister(INaviCallback iRemoteCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iRemoteCallback != null ? iRemoteCallback.asBinder() : null);
                    if (!this.mRemote.transact(4, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unrRegister(iRemoteCallback);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.navi.INaviServer
            public void commonRequest(IApiRequestBaseModelBean iapiRequestBaseModel) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (iapiRequestBaseModel != null) {
                        obtain.writeInt(1);
                        iapiRequestBaseModel.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(5, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().commonRequest(iapiRequestBaseModel);
                        return;
                    }
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        iapiRequestBaseModel.readFromParcel(obtain2);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.navi.INaviServer
            public void cancelNavi() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(6, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().cancelNavi();
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.navi.INaviServer
            public void selectRoute(int var) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(var);
                    if (!this.mRemote.transact(7, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().selectRoute(var);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.navi.INaviServer
            public void getFrontTraffic(int var) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(var);
                    if (!this.mRemote.transact(8, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().getFrontTraffic(var);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.navi.INaviServer
            public void getGuideInfo() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(9, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().getGuideInfo();
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.navi.INaviServer
            public void getHighwayExitInfo(int var) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(var);
                    if (!this.mRemote.transact(10, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().getHighwayExitInfo(var);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.navi.INaviServer
            public void getRoutePlanInfo() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(11, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().getRoutePlanInfo();
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.navi.INaviServer
            public void specialPoiNavi(int guidanceType, int poiType) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(guidanceType);
                    obtain.writeInt(poiType);
                    if (!this.mRemote.transact(12, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().specialPoiNavi(guidanceType, poiType);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.navi.INaviServer
            public void requestRoutePlanOrNavi(long uid, IRoutePlanRequestBean routePlanRequestBean) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeLong(uid);
                    if (routePlanRequestBean != null) {
                        obtain.writeInt(1);
                        routePlanRequestBean.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(13, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().requestRoutePlanOrNavi(uid, routePlanRequestBean);
                        return;
                    }
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        routePlanRequestBean.readFromParcel(obtain2);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.navi.INaviServer
            public void modifyNaviVia(IModifyViaPoiRequestBean modifyViaPoiRequestBean) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (modifyViaPoiRequestBean != null) {
                        obtain.writeInt(1);
                        modifyViaPoiRequestBean.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(14, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().modifyNaviVia(modifyViaPoiRequestBean);
                        return;
                    }
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        modifyViaPoiRequestBean.readFromParcel(obtain2);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.navi.INaviServer
            public void launchMap() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(15, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().launchMap();
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.navi.INaviServer
            public void backToMap() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(16, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().backToMap();
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.navi.INaviServer
            public void exitMap() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(17, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().exitMap();
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.navi.INaviServer
            public boolean mapIsLaunched() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(18, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().mapIsLaunched();
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.navi.INaviServer
            public void openDestinationSearch(IKeywordSearchRequestBean keywordSearchRequestBean) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (keywordSearchRequestBean != null) {
                        obtain.writeInt(1);
                        keywordSearchRequestBean.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(19, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().openDestinationSearch(keywordSearchRequestBean);
                        return;
                    }
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        keywordSearchRequestBean.readFromParcel(obtain2);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.navi.INaviServer
            public void mapZoomInOut(int var) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(var);
                    if (!this.mRemote.transact(20, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().mapZoomInOut(var);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.navi.INaviServer
            public void setMapViewMode(int var) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(var);
                    if (!this.mRemote.transact(21, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setMapViewMode(var);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.navi.INaviServer
            public void naviFullView() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(22, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().naviFullView();
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.navi.INaviServer
            public void switchTraffic(boolean var) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(var ? 1 : 0);
                    if (!this.mRemote.transact(23, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().switchTraffic(var);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.navi.INaviServer
            public void showMyLocation(boolean var) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(var ? 1 : 0);
                    if (!this.mRemote.transact(24, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().showMyLocation(var);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.navi.INaviServer
            public void getTrafficSummaryInfo(String var) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(var);
                    if (!this.mRemote.transact(25, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().getTrafficSummaryInfo(var);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.navi.INaviServer
            public void goFavorite(int var) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(var);
                    if (!this.mRemote.transact(26, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().goFavorite(var);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.navi.INaviServer
            public void switchARNavigation(boolean var) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(var ? 1 : 0);
                    if (!this.mRemote.transact(27, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().switchARNavigation(var);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.navi.INaviServer
            public void downloadOfflineMap(List<String> list) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStringList(list);
                    if (!this.mRemote.transact(28, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().downloadOfflineMap(list);
                    } else {
                        obtain2.readException();
                        obtain2.readStringList(list);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.navi.INaviServer
            public void goOfflineMapDownload() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(29, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().goOfflineMapDownload();
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.navi.INaviServer
            public void searchByKeyword(long uid, int var1, IKeywordSearchRequestBean keywordSearchRequestBean) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeLong(uid);
                    obtain.writeInt(var1);
                    if (keywordSearchRequestBean != null) {
                        obtain.writeInt(1);
                        keywordSearchRequestBean.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(30, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().searchByKeyword(uid, var1, keywordSearchRequestBean);
                        return;
                    }
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        keywordSearchRequestBean.readFromParcel(obtain2);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.navi.INaviServer
            public void searchAround(long uid, int var1, ISearchAroundRequestBean searchAroundRequestBean) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeLong(uid);
                    obtain.writeInt(var1);
                    if (searchAroundRequestBean != null) {
                        obtain.writeInt(1);
                        searchAroundRequestBean.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(31, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().searchAround(uid, var1, searchAroundRequestBean);
                        return;
                    }
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        searchAroundRequestBean.readFromParcel(obtain2);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.navi.INaviServer
            public void alongTheWaySearch(int var1, ISearchAlongWayRequestBean searchAlongWayRequestBean) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(var1);
                    if (searchAlongWayRequestBean != null) {
                        obtain.writeInt(1);
                        searchAlongWayRequestBean.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(32, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().alongTheWaySearch(var1, searchAlongWayRequestBean);
                        return;
                    }
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        searchAlongWayRequestBean.readFromParcel(obtain2);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.navi.INaviServer
            public void requestGeoDecoder(ILatLngBean latLngBean) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (latLngBean != null) {
                        obtain.writeInt(1);
                        latLngBean.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(33, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().requestGeoDecoder(latLngBean);
                        return;
                    }
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        latLngBean.readFromParcel(obtain2);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.navi.INaviServer
            public void setRoutePlanStrategy(int var) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(var);
                    if (!this.mRemote.transact(34, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setRoutePlanStrategy(var);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.navi.INaviServer
            public void getMapSupportedFeatures() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(35, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().getMapSupportedFeatures();
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.navi.INaviServer
            public void getRunningMapFeatures() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(36, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().getRunningMapFeatures();
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.navi.INaviServer
            public void setMapFeaturesRunningState(ISetMapFeaturesRunningStateBean setMapFeaturesRunningStateBean) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (setMapFeaturesRunningStateBean != null) {
                        obtain.writeInt(1);
                        setMapFeaturesRunningStateBean.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(37, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setMapFeaturesRunningState(setMapFeaturesRunningStateBean);
                        return;
                    }
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        setMapFeaturesRunningStateBean.readFromParcel(obtain2);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.navi.INaviServer
            public void editFavoritePois(IEditFavoriteRequestBean editFavoriteRequestBean) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (editFavoriteRequestBean != null) {
                        obtain.writeInt(1);
                        editFavoriteRequestBean.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(38, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().editFavoritePois(editFavoriteRequestBean);
                        return;
                    }
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        editFavoriteRequestBean.readFromParcel(obtain2);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.navi.INaviServer
            public void getFavoritePois(int var) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(var);
                    if (!this.mRemote.transact(39, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().getFavoritePois(var);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.navi.INaviServer
            public void getHistoryPois(int var) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(var);
                    if (!this.mRemote.transact(40, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().getHistoryPois(var);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.navi.INaviServer
            public void getFrequentPois(int var) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(var);
                    if (!this.mRemote.transact(41, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().getFrequentPois(var);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.navi.INaviServer
            public void getSpeedLimitInfo() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(42, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().getSpeedLimitInfo();
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.navi.INaviServer
            public void getArrivedFrontPoiInfo(String var) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(var);
                    if (!this.mRemote.transact(43, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().getArrivedFrontPoiInfo(var);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.navi.INaviServer
            public void rerouting() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(44, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().rerouting();
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.navi.INaviServer
            public String getPackageName() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(45, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPackageName();
                    }
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.navi.INaviServer
            public boolean addSurface(Surface surface, int type, int height, int width) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (surface != null) {
                        obtain.writeInt(1);
                        surface.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeInt(type);
                    obtain.writeInt(height);
                    obtain.writeInt(width);
                    if (!this.mRemote.transact(46, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().addSurface(surface, type, height, width);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.navi.INaviServer
            public boolean addSurfaceWithRoadView(Surface surface, int type, int height, int width, int roadViewHeight, int roadViewWidth) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (surface != null) {
                        obtain.writeInt(1);
                        surface.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeInt(type);
                    obtain.writeInt(height);
                    obtain.writeInt(width);
                    obtain.writeInt(roadViewHeight);
                    obtain.writeInt(roadViewWidth);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    if (!this.mRemote.transact(47, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        boolean addSurfaceWithRoadView = Stub.getDefaultImpl().addSurfaceWithRoadView(surface, type, height, width, roadViewHeight, roadViewWidth);
                        obtain2.recycle();
                        obtain.recycle();
                        return addSurfaceWithRoadView;
                    }
                    obtain2.readException();
                    boolean z = obtain2.readInt() != 0;
                    obtain2.recycle();
                    obtain.recycle();
                    return z;
                } catch (Throwable th2) {
                    obtain2.recycle();
                    obtain.recycle();
                }
                return false;
            }

            @Override // com.geely.lib.oneosapi.navi.INaviServer
            public boolean removedSurface(Surface surface, int type) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (surface != null) {
                        obtain.writeInt(1);
                        surface.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeInt(type);
                    if (!this.mRemote.transact(48, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().removedSurface(surface, type);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.navi.INaviServer
            public void dispatchTouchEvent(MotionEvent ev) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (ev != null) {
                        obtain.writeInt(1);
                        ev.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(49, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().dispatchTouchEvent(ev);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.navi.INaviServer
            public void setReportNaviType(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (!this.mRemote.transact(50, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setReportNaviType(i);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.navi.INaviServer
            public void searchPoiNearbyBurnout(int i, IKeywordSearchRequestBean bean) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (bean != null) {
                        obtain.writeInt(1);
                        bean.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(51, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().searchPoiNearbyBurnout(i, bean);
                        return;
                    }
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        bean.readFromParcel(obtain2);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.navi.INaviServer
            public void requestRoutePlanOrNaviViaRoad(long uid, IRoutePlanRequestBean iRoutePlanRequestBean, IViaRoadRequestBean iViaRoadRequest) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeLong(uid);
                    if (iRoutePlanRequestBean != null) {
                        obtain.writeInt(1);
                        iRoutePlanRequestBean.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (iViaRoadRequest != null) {
                        obtain.writeInt(1);
                        iViaRoadRequest.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(52, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().requestRoutePlanOrNaviViaRoad(uid, iRoutePlanRequestBean, iViaRoadRequest);
                        return;
                    }
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        iRoutePlanRequestBean.readFromParcel(obtain2);
                    }
                    if (obtain2.readInt() != 0) {
                        iViaRoadRequest.readFromParcel(obtain2);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.navi.INaviServer
            public void getRemainNopInfo() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(53, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().getRemainNopInfo();
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.navi.INaviServer
            public boolean addSurfaceWithRoadViewAndOffset(Surface surface, int type, int height, int width, int roadViewHeight, int roadViewWidth, int offsetX, int offsetY) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (surface != null) {
                        obtain.writeInt(1);
                        surface.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeInt(type);
                    obtain.writeInt(height);
                    obtain.writeInt(width);
                    obtain.writeInt(roadViewHeight);
                    obtain.writeInt(roadViewWidth);
                    obtain.writeInt(offsetX);
                    obtain.writeInt(offsetY);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    if (!this.mRemote.transact(54, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        boolean addSurfaceWithRoadViewAndOffset = Stub.getDefaultImpl().addSurfaceWithRoadViewAndOffset(surface, type, height, width, roadViewHeight, roadViewWidth, offsetX, offsetY);
                        obtain2.recycle();
                        obtain.recycle();
                        return addSurfaceWithRoadViewAndOffset;
                    }
                    obtain2.readException();
                    boolean z = obtain2.readInt() != 0;
                    obtain2.recycle();
                    obtain.recycle();
                    return z;
                } catch (Throwable th2) {
                    
                    obtain2.recycle();
                    obtain.recycle();
                    
                }

                return false;
            }

            @Override // com.geely.lib.oneosapi.navi.INaviServer
            public boolean muteNaviAudio() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(55, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().muteNaviAudio();
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.navi.INaviServer
            public boolean unmuteNaviAudio() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(56, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().unmuteNaviAudio();
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.navi.INaviServer
            public boolean goHome() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(57, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().goHome();
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.navi.INaviServer
            public boolean goCompany() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(58, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().goCompany();
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.navi.INaviServer
            public boolean searchNearby(String keyword, double longitude, double latitude) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(keyword);
                    obtain.writeDouble(longitude);
                    obtain.writeDouble(latitude);
                    try {
                        if (!this.mRemote.transact(59, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                            boolean searchNearby = Stub.getDefaultImpl().searchNearby(keyword, longitude, latitude);
                            obtain2.recycle();
                            obtain.recycle();
                            return searchNearby;
                        }
                        obtain2.readException();
                        boolean z = obtain2.readInt() != 0;
                        obtain2.recycle();
                        obtain.recycle();
                        return z;
                    } catch (Throwable th) {
                        th = th;
                        obtain2.recycle();
                        obtain.recycle();
                        
                    }
                } catch (Throwable th2) {
                    
                }
                return false;
            }

            @Override // com.geely.lib.oneosapi.navi.INaviServer
            public boolean stopNavi() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(60, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().stopNavi();
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.navi.INaviServer
            public boolean naviTo(String message) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(message);
                    if (!this.mRemote.transact(61, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().naviTo(message);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.navi.INaviServer
            public boolean naviToByNaviInfo(INaviInfo naviInfo) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(naviInfo != null ? naviInfo.asBinder() : null);
                    if (!this.mRemote.transact(62, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().naviToByNaviInfo(naviInfo);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.navi.INaviServer
            public String getLocationRoadInfo() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(63, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getLocationRoadInfo();
                    }
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.navi.INaviServer
            public TransferModel invokeNaviAPI(TransferModel reqModel) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (reqModel != null) {
                        obtain.writeInt(1);
                        reqModel.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(64, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().invokeNaviAPI(reqModel);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? TransferModel.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.navi.INaviServer
            public int invokeNaviAPIAsync(TransferModel reqModel, INaviObserver observer) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (reqModel != null) {
                        obtain.writeInt(1);
                        reqModel.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(observer != null ? observer.asBinder() : null);
                    if (!this.mRemote.transact(65, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().invokeNaviAPIAsync(reqModel, observer);
                    }
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.navi.INaviServer
            public boolean addNaviEventNotify(INaviEventNotify eventNotify) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(eventNotify != null ? eventNotify.asBinder() : null);
                    if (!this.mRemote.transact(66, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().addNaviEventNotify(eventNotify);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.navi.INaviServer
            public boolean removeNaviEventNotify(INaviEventNotify eventNotify) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(eventNotify != null ? eventNotify.asBinder() : null);
                    if (!this.mRemote.transact(67, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().removeNaviEventNotify(eventNotify);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(INaviServer impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static INaviServer getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}