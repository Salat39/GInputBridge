package com.geely.lib.oneosapi.navi.base.vehicle;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes.dex */
public final class VehicleSeat {
    public static final int SEAT_ROW_1_CENTER = 2;
    public static final int SEAT_ROW_1_LEFT = 1;
    public static final int SEAT_ROW_1_RIGHT = 4;
    public static final int SEAT_ROW_2_CENTER = 32;
    public static final int SEAT_ROW_2_LEFT = 16;
    public static final int SEAT_ROW_2_RIGHT = 64;
    public static final int SEAT_ROW_3_CENTER = 512;
    public static final int SEAT_ROW_3_LEFT = 256;
    public static final int SEAT_ROW_3_RIGHT = 1024;

    @Retention(RetentionPolicy.SOURCE)
    public @interface SeatType {
    }

    private VehicleSeat() {
    }
}