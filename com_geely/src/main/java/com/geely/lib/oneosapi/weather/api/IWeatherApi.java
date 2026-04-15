package com.geely.lib.oneosapi.weather.api;

/* loaded from: classes.dex */
public interface IWeatherApi {
    boolean getCurrentCity();

    String getLocationCity();

    boolean getLocationPermission();

    void getWeatherByCityId(String cityID, IWeatherAPICallback callback);

    void getWeatherByLocation(double lon, double lat, IWeatherAPICallback callback);

    boolean setCurrentCity(boolean status);

    boolean setLocationPermission(boolean status);
}