package com.geely.lib.oneosapi.navi.base.vehicle;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes.dex */
public final class VehicleDoor {
    public static final int DOOR_HOOD = 268435456;
    public static final int DOOR_REAR = 536870912;
    public static final int DOOR_ROW_1_LEFT = 1;
    public static final int DOOR_ROW_1_RIGHT = 4;
    public static final int DOOR_ROW_2_LEFT = 16;
    public static final int DOOR_ROW_2_RIGHT = 64;
    public static final int DOOR_ROW_3_LEFT = 256;
    public static final int DOOR_ROW_3_RIGHT = 1024;

    @Retention(RetentionPolicy.SOURCE)
    public @interface DoorType {
    }

    private VehicleDoor() {
    }
}