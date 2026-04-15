package com.geely.lib.oneosapi.navi.entitys;

import com.geely.lib.oneosapi.navi.model.base.NaviProtocolID;

/* loaded from: classes.dex */
public enum MapStatusTypes {
    APP_BACKGROUND(11),
    APP_FINISH(9),
    APP_FOREGROUND(10),
    APP_START(7),
    APP_START_FINISH(8),
    AR_GUIDE_OFF(121),
    AR_GUIDE_ON(120),
    ARRIVE_DESTINATION(18),
    CAR_UP_2D(27),
    CAR_UP_3D(25),
    CRUISE_START(28),
    CRUISE_STOP(29),
    ENTER_TUNNEL(9002),
    FULL_VIEW_OFF(123),
    FULL_VIEW_ON(122),
    GUIDE_START(16),
    GUIDE_STOP(17),
    GUIDE_VEHICLE_YAW(9004),
    LEAVE_TUNNEL(9003),
    MAP_2_DIM_STARTED(116),
    MAP_2_DIM_STOPPED(117),
    MUTE_OFF(41),
    NORTH_UP_2D(26),
    PERMANENT_MUTE_ON(40),
    PSD_APP_BACKGROUND(201),
    PSD_APP_FOREGROUND(200),
    REROUTING(NaviProtocolID.APP_LAUNCH_MAP),
    ROUTE_FAILED(15),
    ROUTE_START(13),
    ROUTE_SUCCESS(14),
    ROUTE_VIEW_IN(0),
    ROUTE_VIEW_OUT(1),
    SIMULATE_GUIDE_PAUSE(34),
    SIMULATE_GUIDE_START(33),
    SIMULATE_GUIDE_STOP(35),
    TEMPORARY_MUTE_ON(42),
    TMC_OFF(24),
    TMC_ON(23),
    WIDGET_FRIST_FRAME_DARWN(124),
    WIDGET_FRIST_FRAME_DARWN_FINISH(125),
    ZOOM_IN(20),
    ZOOM_MAX(21),
    ZOOM_MIN(22),
    ZOOM_OUT(19),
    USER_EXIT_POI_SEARCH_LIST(202);

    private int code;

    MapStatusTypes(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}