package com.geely.lib.oneosapi.navi.entitys;

/* loaded from: classes.dex */
public enum NaviStateType {
    Unknow(-1),
    NaviInit(0),
    NaviInitSuccess(1),
    NaviOnDestory(2),
    NaviOnStart(3),
    NaviOnStop(4),
    NaviStartPlan(5),
    NaviPlanSuccess(6),
    NaviPlanFail(7),
    NaviStart(8),
    NaviStop(9),
    NaviStartSimulate(10),
    NaviPauseSimulate(11),
    NaviStopSimulate(12),
    NaviStartTTS(13),
    NaviStopTTS(14),
    NaviZoomUp(15),
    NaviZoomDown(16),
    Navi2DCarUp(17),
    Navi2DNorthUp(18),
    Navi3DCarUp(19),
    NaviOpenTMC(20),
    NaviCloseTMC(21),
    NaviZoomUpMax(22),
    NaviZoomDownMin(23),
    NaviStartBroadcast(24),
    NaviStopBroadcast(25),
    NaviFavoriteHomeChange(26),
    NaviFavoriteCompanyChange(27),
    NaviSearchAround(28),
    NaviSearchAddress(29),
    NaviSendToCar(30),
    NaviParkCardDismiss(31),
    NaviContinueDialogTip(32),
    NaviPlanFailTip(33),
    NaviHandExitOpen(34),
    NaviHandExitClose(35),
    NaviSimpleFavoriteChange(36),
    NaviModleDay(37),
    NaviModleNight(38);

    private int code;

    NaviStateType(int code) {
        this.code = 0;
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public static NaviStateType toCameraType(int code) {
        for (NaviStateType naviStateType : values()) {
            if (naviStateType.getCode() == code) {
                return naviStateType;
            }
        }
        return Unknow;
    }
}