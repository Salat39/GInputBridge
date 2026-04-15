package com.geely.lib.oneosapi.navi.listener;

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
public interface IMapEventListener {
    void onAdAreaChanged(ILocationInfoModelBean locationInfoModelBean);

    void onArrivedLastMile(IPoiInfoBean poiInfoBean);

    void onCommutePush(ICommuteInfoBean commuteInfoBean);

    void onDestinationParkingUpdate(IParkingPoisBean parkingPoisBean);

    void onFavoriteChanged(IFavoritesInfoBean favoritesInfoBean);

    void onGuideInfoChanged(IGuidingInfoModelBean guidingInfoModelBean);

    void onHighwayExitInfoUpdate(IHighwayExitInfoModelBean highwayExitInfoModelBean);

    void onHistoryPoisUpdateCallback(IHistoryPoisBean historyPoisBean);

    void onLanesInfoChanged(ILanesInfoBean lanesInfoBean);

    void onMapStatusChanged(int mapStatus);

    void onRouteInfoChanged(IRoutePlanInfoBean routePlanInfoBean);

    void onSendPOIToCar(IPoiInfoBean poiInfoBean);

    void onTrafficConditionsChanged(ITrafficConditionInfoBean trafficConditionInfoBean);

    void onTripReporterUpdate(ITripReporterBean tripReporter);
}