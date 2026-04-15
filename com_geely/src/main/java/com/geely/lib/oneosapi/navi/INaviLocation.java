package com.geely.lib.oneosapi.navi;

/* loaded from: classes.dex */
public interface INaviLocation {
    public static final int GPS_ACCURACY_BAD = 0;
    public static final int GPS_ACCURACY_GOOD = 1;
    public static final int GPS_ACCURACY_UNKNOWN = -1;

    float getAccuracy();

    String getAdCode();

    String getAddress();

    double getAltitude();

    String getAoiName();

    float getBearing();

    String getCity();

    String getCityCode();

    String getCountry();

    String getDistrict();

    int getErrorCode();

    String getErrorInfo();

    int getGpsAccuracyStatus();

    double getLatitude();

    String getLocationDetail();

    int getLocationType();

    double getLongitude();

    String getPoiName();

    String getProvince();

    float getSpeed();

    String getStreet();

    String getStreetNum();
}