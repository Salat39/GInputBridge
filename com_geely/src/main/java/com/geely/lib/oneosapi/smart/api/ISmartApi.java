package com.geely.lib.oneosapi.smart.api;

/* loaded from: classes.dex */
public interface ISmartApi {
    void controlACTemp(String location, String setType, int targetTemp, int adjustTemp, IResultCallback callback);

    void controlDeviceMode(String location, int deviceType, int mode, IResultCallback callback);

    void controlDeviceSpeed(String location, int deviceType, int speed, IResultCallback callback);

    void controlDeviceSwitch(String location, int deviceType, boolean isOpen, IResultCallback callback);

    void controlSceneMode(boolean isOpen, String sceneName, IResultCallback callback);
}