package com.geely.lib.oneosapi.navi.base.vehicle;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes.dex */
public final class VehicleZone {
    public static final int ZONE_ALL = Integer.MIN_VALUE;
    public static final int ZONE_ROW_1_ALL = 8;
    public static final int ZONE_ROW_1_CENTER = 2;
    public static final int ZONE_ROW_1_LEFT = 1;
    public static final int ZONE_ROW_1_RIGHT = 4;
    public static final int ZONE_ROW_2_ALL = 128;
    public static final int ZONE_ROW_2_CENTER = 32;
    public static final int ZONE_ROW_2_LEFT = 16;
    public static final int ZONE_ROW_2_RIGHT = 64;
    public static final int ZONE_ROW_3_ALL = 2048;
    public static final int ZONE_ROW_3_CENTER = 512;
    public static final int ZONE_ROW_3_LEFT = 256;
    public static final int ZONE_ROW_3_RIGHT = 1024;
    public static final int ZONE_ROW_4_ALL = 32768;
    public static final int ZONE_ROW_4_CENTER = 8192;
    public static final int ZONE_ROW_4_LEFT = 4096;
    public static final int ZONE_ROW_4_RIGHT = 16384;

    @Retention(RetentionPolicy.SOURCE)
    public @interface ZoneType {
    }

    private VehicleZone() {
    }
}