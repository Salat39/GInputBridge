package com.geely.lib.oneosapi.smart.api;

/* loaded from: classes.dex */
public interface ISmartCallback {
    void onAirConditionerInfoChanged(IAirConditionerInfo airConditionInfo);

    void onAirPurificationInfoChanged(IAirPurificationInfo airPurificationInfo);

    void onElectricSocketInfoChanged(IElectricSocketInfo electricSocketInfo);

    void onGoHomeModeChanged(ISceneInfo sceneInfo);

    void onLeaveHomeModeChanged(ISceneInfo sceneInfo);

    void onLightInfoChanged(ILightInfo lightInfo);

    void onSweepingRobotInfoChanged(ISweepingRobotInfo sweepRobotInfo);
}