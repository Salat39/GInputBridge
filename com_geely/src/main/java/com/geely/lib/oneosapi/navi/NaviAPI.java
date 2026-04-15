package com.geely.lib.oneosapi.navi;

import com.geely.lib.oneosapi.navi.base.NaviAPIBase;
import com.geely.lib.oneosapi.navi.model.base.NaviBaseModel;
import com.geely.lib.oneosapi.navi.model.base.NaviErrorModel;
import com.geely.lib.oneosapi.navi.model.base.NaviProtocolID;
import com.geely.lib.oneosapi.navi.model.client.DimDisplayMode;
import com.geely.lib.oneosapi.navi.model.client.MapDownloadOfflineData;
import com.geely.lib.oneosapi.navi.model.client.MapGeoDecodeBean;
import com.geely.lib.oneosapi.navi.model.client.MapOperaGetTrafficSummaryInfo;
import com.geely.lib.oneosapi.navi.model.client.MapOperaShowMyLocation;
import com.geely.lib.oneosapi.navi.model.client.MapOperaSwitchAR;
import com.geely.lib.oneosapi.navi.model.client.MapOperaSwitchTraffic;
import com.geely.lib.oneosapi.navi.model.client.MapOperaViewMode;
import com.geely.lib.oneosapi.navi.model.client.MapOperaZoomInOut;
import com.geely.lib.oneosapi.navi.model.client.MapReportNaviType;
import com.geely.lib.oneosapi.navi.model.client.NaviChangeRoadType;
import com.geely.lib.oneosapi.navi.model.client.NaviGetFrontTrafficRadio;
import com.geely.lib.oneosapi.navi.model.client.NaviGetHighwayExit;
import com.geely.lib.oneosapi.navi.model.client.NaviRoutePlan;
import com.geely.lib.oneosapi.navi.model.client.NaviRouteSelect;
import com.geely.lib.oneosapi.navi.model.client.NaviSetCircleFence;
import com.geely.lib.oneosapi.navi.model.client.NaviSpecialPoi;
import com.geely.lib.oneosapi.navi.model.client.NaviSwitchCruise;
import com.geely.lib.oneosapi.navi.model.client.NaviSwitchFullView;
import com.geely.lib.oneosapi.navi.model.client.NaviViaModify;
import com.geely.lib.oneosapi.navi.model.client.RequestFrequentPois;
import com.geely.lib.oneosapi.navi.model.client.RequestHistoryPois;
import com.geely.lib.oneosapi.navi.model.client.RoutePlanStrategyBean;
import com.geely.lib.oneosapi.navi.model.client.SearchAlongWay;
import com.geely.lib.oneosapi.navi.model.client.SearchAround;
import com.geely.lib.oneosapi.navi.model.client.SearchByKeyword;
import com.geely.lib.oneosapi.navi.model.client.SearchResultPageChange;
import com.geely.lib.oneosapi.navi.model.client.SpeedLimitInfo;
import com.geely.lib.oneosapi.navi.model.client.StartNaviModel;
import com.geely.lib.oneosapi.navi.model.client.SwitchMapPresentation;
import com.geely.lib.oneosapi.navi.model.client.UserFavoritePoiEdit;
import com.geely.lib.oneosapi.navi.model.client.UserGetFavoritePois;
import com.geely.lib.oneosapi.navi.model.client.UserIdBindModel;
import com.geely.lib.oneosapi.navi.model.client.UserLogoutModel;
import com.geely.lib.oneosapi.navi.model.client.UserOpenFavorite;
import com.geely.lib.oneosapi.navi.model.service.RspSpeedLimitFocus;

/* loaded from: classes.dex */
public abstract class NaviAPI extends NaviAPIBase {
    public static final String TAG = "NaviAPI";

    public abstract int invokeAPIAsync(NaviBaseModel requestModel, INaviAPICallback naviAPICallback);

    public abstract NaviBaseModel invokeAPISync(NaviBaseModel requestModel);

    public abstract boolean isConnected();

    public abstract int launchMap(int mapVendor, INaviAPICallback naviAPICallback);

    public abstract void release();

    public abstract void setNaviEventListener(INaviEventListener listener);

    public int getTrafficSummaryInfo(MapOperaGetTrafficSummaryInfo trafficSummaryInfo, INaviAPICallback naviAPICallback) {
        return invokeAPIAsync(trafficSummaryInfo, naviAPICallback);
    }

    public int showMyLocation(MapOperaShowMyLocation showMyLocationParam, INaviAPICallback naviAPICallback) {
        return invokeAPIAsync(showMyLocationParam, naviAPICallback);
    }

    public int switchMapTraffic(MapOperaSwitchTraffic switchTraffic, INaviAPICallback naviAPICallback) {
        return invokeAPIAsync(switchTraffic, naviAPICallback);
    }

    public int switchMapViewMode(MapOperaViewMode viewMode, INaviAPICallback naviAPICallback) {
        return invokeAPIAsync(viewMode, naviAPICallback);
    }

    public int mapZoomInOut(MapOperaZoomInOut zoomInOut, INaviAPICallback naviAPICallback) {
        return invokeAPIAsync(zoomInOut, naviAPICallback);
    }

    public int switchFullView(NaviSwitchFullView switchFullView, INaviAPICallback naviAPICallback) {
        return invokeAPIAsync(switchFullView, naviAPICallback);
    }

    public int switchMapARGuide(MapOperaSwitchAR switchAR, INaviAPICallback naviAPICallback) {
        return invokeAPIAsync(switchAR, naviAPICallback);
    }

    public int goOfflineMapDown(INaviAPICallback naviAPICallback) {
        NaviBaseModel naviBaseModel = new NaviBaseModel();
        naviBaseModel.setProtocolID(NaviProtocolID.MAP_OP_ENTER_DOWNLOAD);
        return invokeAPIAsync(naviBaseModel, naviAPICallback);
    }

    public int downloadOfflineMap(MapDownloadOfflineData downloadOfflineData, INaviAPICallback naviAPICallback) {
        return invokeAPIAsync(downloadOfflineData, naviAPICallback);
    }

    public int getFrontTraffic(NaviGetFrontTrafficRadio getFrontTrafficRadio, INaviAPICallback naviAPICallback) {
        return invokeAPIAsync(getFrontTrafficRadio, naviAPICallback);
    }

    public int getHighwayExitInfo(NaviGetHighwayExit highwayExit, INaviAPICallback naviAPICallback) {
        return invokeAPIAsync(highwayExit, naviAPICallback);
    }

    public int routePlanOrNavi(NaviRoutePlan naviRoutePlan, INaviAPICallback naviAPICallback) {
        return invokeAPIAsync(naviRoutePlan, naviAPICallback);
    }

    public int setRoutePrefer(RoutePlanStrategyBean naviRoutePrefer, INaviAPICallback naviAPICallback) {
        return invokeAPIAsync(naviRoutePrefer, naviAPICallback);
    }

    public int routeSelect(NaviRouteSelect routeSelectParam, INaviAPICallback naviAPICallback) {
        return invokeAPIAsync(routeSelectParam, naviAPICallback);
    }

    public int specialPoiNavi(NaviSpecialPoi naviSpecialPoi, INaviAPICallback naviAPICallback) {
        return invokeAPIAsync(naviSpecialPoi, naviAPICallback);
    }

    public int specialPoiNavi(NaviSpecialPoi naviSpecialPoi, final int routePlanStrategy, final INaviAPICallback naviAPICallback) {
        return invokeAPIAsync(naviSpecialPoi, new INaviAPICallback() { // from class: com.geely.lib.oneosapi.navi.NaviAPI.1
            @Override // com.geely.lib.oneosapi.navi.INaviAPICallback
            public void onSuccess(NaviBaseModel naviBaseModel) {
                NaviAPI.this.setRoutePrefer(new RoutePlanStrategyBean(routePlanStrategy), new INaviAPICallback() { // from class: com.geely.lib.oneosapi.navi.NaviAPI.1.1
                    @Override // com.geely.lib.oneosapi.navi.INaviAPICallback
                    public void onSuccess(NaviBaseModel naviBaseModel1) {
                        naviAPICallback.onSuccess(naviBaseModel1);
                    }

                    @Override // com.geely.lib.oneosapi.navi.INaviAPICallback
                    public void onFail(NaviErrorModel naviErrorModel) {
                        if (10046 != naviErrorModel.getErrorCode()) {
                            naviAPICallback.onFail(naviErrorModel);
                        }
                    }
                });
            }

            @Override // com.geely.lib.oneosapi.navi.INaviAPICallback
            public void onFail(NaviErrorModel naviErrorModel) {
                naviAPICallback.onFail(naviErrorModel);
            }
        });
    }

    public int naviViaEdit(NaviViaModify naviViaModify, INaviAPICallback naviAPICallback) {
        return invokeAPIAsync(naviViaModify, naviAPICallback);
    }

    public int searchByKeyword(SearchByKeyword searchByKeywordParam, INaviAPICallback naviAPICallback) {
        return invokeAPIAsync(searchByKeywordParam, naviAPICallback);
    }

    public int searchAround(SearchAround searchAroundParam, INaviAPICallback naviAPICallback) {
        return invokeAPIAsync(searchAroundParam, naviAPICallback);
    }

    public int searchAlongWay(SearchAlongWay searchAlongWayParam, INaviAPICallback naviAPICallback) {
        return invokeAPIAsync(searchAlongWayParam, naviAPICallback);
    }

    public int cancelNavi(INaviAPICallback naviAPICallback) {
        NaviBaseModel naviBaseModel = new NaviBaseModel();
        naviBaseModel.setProtocolID(NaviProtocolID.NAVI_OP_CANCEL);
        return invokeAPIAsync(naviBaseModel, naviAPICallback);
    }

    public int editFavoritePois(UserFavoritePoiEdit favoritePoiEdit, INaviAPICallback naviAPICallback) {
        return invokeAPIAsync(favoritePoiEdit, naviAPICallback);
    }

    public int getFavoritePois(UserGetFavoritePois favoritePois, INaviAPICallback naviAPICallback) {
        return invokeAPIAsync(favoritePois, naviAPICallback);
    }

    public int getFrequentPois(RequestFrequentPois requestFrequentPois, INaviAPICallback naviAPICallback) {
        return invokeAPIAsync(requestFrequentPois, naviAPICallback);
    }

    public int getGuideInfo(INaviAPICallback naviAPICallback) {
        NaviBaseModel naviBaseModel = new NaviBaseModel();
        naviBaseModel.setProtocolID(NaviProtocolID.NAVI_OP_GET_GUIDE_INFO);
        return invokeAPIAsync(naviBaseModel, naviAPICallback);
    }

    @Deprecated
    public int getLastLocation(MapOperaShowMyLocation mapOperaShowMyLocation, INaviAPICallback naviAPICallback) {
        return invokeAPIAsync(mapOperaShowMyLocation, naviAPICallback);
    }

    public NaviBaseModel getNaviStatus() {
        NaviBaseModel naviBaseModel = new NaviBaseModel();
        naviBaseModel.setProtocolID(NaviProtocolID.NAVI_OP_GET_MAP_STATUS);
        return invokeAPISync(naviBaseModel);
    }

    public int getRouteInfo(INaviAPICallback naviAPICallback) {
        NaviBaseModel naviBaseModel = new NaviBaseModel();
        naviBaseModel.setProtocolID(NaviProtocolID.NAVI_OP_ROUTE_INFO);
        return invokeAPIAsync(naviBaseModel, naviAPICallback);
    }

    public int exitMap(INaviAPICallback naviAPICallback) {
        NaviBaseModel naviBaseModel = new NaviBaseModel();
        naviBaseModel.setProtocolID(NaviProtocolID.MAP_OP_EXIT);
        return invokeAPIAsync(naviBaseModel, naviAPICallback);
    }

    public int getHistoryPois(RequestHistoryPois requestHistoryPois, INaviAPICallback naviAPICallback) {
        return invokeAPIAsync(requestHistoryPois, naviAPICallback);
    }

    public int searchPOINearbyBurnout(SearchByKeyword searchByKeywordParam, INaviAPICallback naviAPICallback) {
        return invokeAPIAsync(searchByKeywordParam, naviAPICallback);
    }

    public int requestAntiGeo(MapGeoDecodeBean antiGEO, INaviAPICallback naviAPICallback) {
        return invokeAPIAsync(antiGEO, naviAPICallback);
    }

    public int setCircleFence(NaviSetCircleFence reqModel, INaviAPICallback naviAPICallback) {
        return invokeAPIAsync(reqModel, naviAPICallback);
    }

    public int switchDimNaviMode(DimDisplayMode dimDisplayMode, INaviAPICallback naviAPICallback) {
        return invokeAPIAsync(dimDisplayMode, naviAPICallback);
    }

    public int getDimDisplayMode(DimDisplayMode dimDisplayMode, INaviAPICallback naviAPICallback) {
        return invokeAPIAsync(dimDisplayMode, naviAPICallback);
    }

    public int requestSpeedLimitFocus(RspSpeedLimitFocus rspSpeedLimitFocus, INaviAPICallback naviAPICallback) {
        naviAPICallback.onSuccess(rspSpeedLimitFocus);
        return 0;
    }

    public int sendSpeedLimitInfo(SpeedLimitInfo speedLimitInfo, INaviAPICallback naviAPICallback) {
        naviAPICallback.onSuccess(speedLimitInfo);
        return 0;
    }

    public int setReportNaviType(MapReportNaviType reportNaviType, INaviAPICallback naviAPICallback) {
        return invokeAPIAsync(reportNaviType, naviAPICallback);
    }

    public int getSpeedLimitInfo(INaviAPICallback naviAPICallback) {
        NaviBaseModel naviBaseModel = new NaviBaseModel();
        naviBaseModel.setProtocolID(NaviProtocolID.SPEED_LIMIT_INFO);
        return invokeAPIAsync(naviBaseModel, naviAPICallback);
    }

    public int openFavorite(UserOpenFavorite userOpenFavorite, INaviAPICallback naviAPICallback) {
        return invokeAPIAsync(userOpenFavorite, naviAPICallback);
    }

    public int openPoiHistory(INaviAPICallback naviAPICallback) {
        NaviBaseModel naviBaseModel = new NaviBaseModel();
        naviBaseModel.setProtocolID(NaviProtocolID.USER_OPEN_NAVI_HISTORY);
        return invokeAPIAsync(naviBaseModel, naviAPICallback);
    }

    public int backToMap(INaviAPICallback naviAPICallback) {
        NaviBaseModel naviBaseModel = new NaviBaseModel();
        naviBaseModel.setProtocolID(NaviProtocolID.MAP_OP_BACK_TO_MAP);
        return invokeAPIAsync(naviBaseModel, naviAPICallback);
    }

    public int changeRoadType(NaviChangeRoadType naviChangeRoadType, INaviAPICallback naviAPICallback) {
        return invokeAPIAsync(naviChangeRoadType, naviAPICallback);
    }

    public int switchCruise(NaviSwitchCruise naviSwitchCruise, INaviAPICallback naviAPICallback) {
        return invokeAPIAsync(naviSwitchCruise, naviAPICallback);
    }

    public int getSupportMapFeatures(INaviAPICallback naviAPICallback) {
        NaviBaseModel naviBaseModel = new NaviBaseModel();
        naviBaseModel.setProtocolID(NaviProtocolID.SETTING_GET_MAP_RUNNING_FEATURES);
        return invokeAPIAsync(naviBaseModel, naviAPICallback);
    }

    public int mapInOnNaviStatus(INaviAPICallback naviAPICallback) {
        NaviBaseModel naviBaseModel = new NaviBaseModel();
        naviBaseModel.setProtocolID(NaviProtocolID.MAP_OP_IS_IN_NAVI_STATUS);
        return invokeAPIAsync(naviBaseModel, naviAPICallback);
    }

    public int mapAppIsLaunched(INaviAPICallback naviAPICallback) {
        NaviBaseModel naviBaseModel = new NaviBaseModel();
        naviBaseModel.setProtocolID(NaviProtocolID.MAP_OP_IS_MAP_APP_LAUNCHED);
        return invokeAPIAsync(naviBaseModel, naviAPICallback);
    }

    public int bindUserIdToAMap(UserIdBindModel userIdBindModel, INaviAPICallback naviAPICallback) {
        return invokeAPIAsync(userIdBindModel, naviAPICallback);
    }

    public int logoutUserIdToAMap(UserLogoutModel userLogoutModel, INaviAPICallback naviAPICallback) {
        return invokeAPIAsync(userLogoutModel, naviAPICallback);
    }

    public int reportFontTurnInfo(INaviAPICallback naviAPICallback) {
        NaviBaseModel naviBaseModel = new NaviBaseModel();
        naviBaseModel.setProtocolID(NaviProtocolID.NAVI_OP_REPORT_FONT_TURN_INFO);
        return invokeAPIAsync(naviBaseModel, naviAPICallback);
    }

    public int changeSearchResultPage(SearchResultPageChange searchResultPageChange, INaviAPICallback naviAPICallback) {
        return invokeAPIAsync(searchResultPageChange, naviAPICallback);
    }

    public int getCurrPageHotWord(INaviAPICallback naviAPICallback) {
        NaviBaseModel naviBaseModel = new NaviBaseModel();
        naviBaseModel.setProtocolID(NaviProtocolID.RSP_CURR_PAGE_HOT_WORD);
        return invokeAPIAsync(naviBaseModel, naviAPICallback);
    }

    public int switchMapPresentation(SwitchMapPresentation switchMapPresentation, INaviAPICallback naviAPICallback) {
        return invokeAPIAsync(switchMapPresentation, naviAPICallback);
    }

    public int searchLastDeparture(INaviAPICallback naviAPICallback) {
        NaviBaseModel naviBaseModel = new NaviBaseModel();
        naviBaseModel.setProtocolID(NaviProtocolID.SEARCH_LAST_DEPARTURE);
        return invokeAPIAsync(naviBaseModel, naviAPICallback);
    }

    public int startNavi(int naviType, INaviAPICallback naviAPICallback) {
        return invokeAPIAsync(new StartNaviModel(), naviAPICallback);
    }
}