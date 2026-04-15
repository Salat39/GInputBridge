package com.geely.lib.oneosapi.navi.entitys;

/* loaded from: classes.dex */
public interface IGuideInfo {
    int getCameraDist();

    int getCameraIndex();

    int getCameraSpeed();

    int getCameraType();

    int getCarDirection();

    double getCarLatitude();

    double getCarLongitude();

    int getCurPointNum();

    String getCurRoadName();

    int getCurSegNum();

    int getCurSpeed();

    int getLimitedSpeed();

    int getNaviType();

    String getNextRoadName();

    int getRoadType();

    int getRoundAboutNum();

    int getRoundAllNum();

    int getRouteAllDis();

    int getRouteAllTime();

    int getRouteRemainDis();

    int getRouteRemainTime();

    int getSapaDist();

    String getSapaName();

    int getSapaNum();

    int getSapaType();

    int getSegRemainDis();

    int getSegRemainTime();

    int getTrafficLightNum();

    TurnInfoIcon getTurnInfoIcon();

    boolean hasArrived();
}