package com.ecarx.xui.adaptapi.car.roadpath;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public interface IRoadPath {
    public static final int CONF_PATH_INIT = 0;
    public static final int CONF_PATH_INVALID = 2;
    public static final int CONF_PATH_RESERVED = 3;
    public static final int CONF_PATH_VALID = 1;

    @Retention(RetentionPolicy.SOURCE)
    public @interface ConfPath {
    }

    public interface RoadDataStatusCallBack {
        void onRoadCondState(boolean z);

        void onRoadSlopeState(boolean z);
    }

    void addCallback(RoadDataStatusCallBack roadDataStatusCallBack);

    void removeCallback(RoadDataStatusCallBack roadDataStatusCallBack);

    boolean setConfCurrSpd(int i);

    boolean setConfPath(int i);

    boolean setRoadCondData(int[] iArr);

    boolean setRoadSlopeData(int[] iArr);
}
