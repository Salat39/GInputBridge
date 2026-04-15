package com.geely.lib.oneosapi.navi.impl;

import android.os.RemoteException;
import com.geely.lib.oneosapi.navi.INaviCallback;
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
import com.geely.lib.oneosapi.navi.listener.IMapEventListener;

/* loaded from: classes.dex */
public class NaviCallbackImpl extends INaviCallback.Stub {
    private IMapEventListener eventListener;

    public NaviCallbackImpl(IMapEventListener eventListener) {
        this.eventListener = eventListener;
    }

    @Override // com.geely.lib.oneosapi.navi.INaviCallback
    public void onMapStatusChanged(int var) throws RemoteException {
        this.eventListener.onMapStatusChanged(var);
    }

    @Override // com.geely.lib.oneosapi.navi.INaviCallback
    public void onFavoriteChanged(IFavoritesInfoBean favoritesInfoBean) throws RemoteException {
        this.eventListener.onFavoriteChanged(favoritesInfoBean);
    }

    @Override // com.geely.lib.oneosapi.navi.INaviCallback
    public void onRouteInfoChanged(IRoutePlanInfoBean routePlanInfoBean) throws RemoteException {
        this.eventListener.onRouteInfoChanged(routePlanInfoBean);
    }

    @Override // com.geely.lib.oneosapi.navi.INaviCallback
    public void onGuideInfoChanged(IGuidingInfoModelBean guidingInfoModelBean) throws RemoteException {
        this.eventListener.onGuideInfoChanged(guidingInfoModelBean);
    }

    @Override // com.geely.lib.oneosapi.navi.INaviCallback
    public void onHighwayExitInfoUpdate(IHighwayExitInfoModelBean highwayExitInfoModelBean) throws RemoteException {
        this.eventListener.onHighwayExitInfoUpdate(highwayExitInfoModelBean);
    }

    @Override // com.geely.lib.oneosapi.navi.INaviCallback
    public void onSendPOIToCar(IPoiInfoBean poiInfoBean) throws RemoteException {
        this.eventListener.onSendPOIToCar(poiInfoBean);
    }

    @Override // com.geely.lib.oneosapi.navi.INaviCallback
    public void onArrivedLastMile(IPoiInfoBean poiInfoBean) throws RemoteException {
        this.eventListener.onArrivedLastMile(poiInfoBean);
    }

    @Override // com.geely.lib.oneosapi.navi.INaviCallback
    public void onAdAreaChanged(ILocationInfoModelBean locationInfoModelBean) throws RemoteException {
        this.eventListener.onAdAreaChanged(locationInfoModelBean);
    }

    @Override // com.geely.lib.oneosapi.navi.INaviCallback
    public void onTrafficConditionsChanged(ITrafficConditionInfoBean trafficConditionInfoBean) throws RemoteException {
        this.eventListener.onTrafficConditionsChanged(trafficConditionInfoBean);
    }

    @Override // com.geely.lib.oneosapi.navi.INaviCallback
    public void onLanesInfoChanged(ILanesInfoBean lanesInfoBean) throws RemoteException {
        this.eventListener.onLanesInfoChanged(lanesInfoBean);
    }

    @Override // com.geely.lib.oneosapi.navi.INaviCallback
    public void onCommutePush(ICommuteInfoBean commuteInfoBean) throws RemoteException {
        this.eventListener.onCommutePush(commuteInfoBean);
    }

    @Override // com.geely.lib.oneosapi.navi.INaviCallback
    public void onDestinationParkingUpdate(IParkingPoisBean parkingPoisBean) throws RemoteException {
        this.eventListener.onDestinationParkingUpdate(parkingPoisBean);
    }

    @Override // com.geely.lib.oneosapi.navi.INaviCallback
    public void onTripReporterUpdate(ITripReporterBean tripReporter) throws RemoteException {
        this.eventListener.onTripReporterUpdate(tripReporter);
    }

    @Override // com.geely.lib.oneosapi.navi.INaviCallback
    public void onHistoryPoisUpdateCallback(IHistoryPoisBean historyPoisBean) throws RemoteException {
        this.eventListener.onHistoryPoisUpdateCallback(historyPoisBean);
    }
}