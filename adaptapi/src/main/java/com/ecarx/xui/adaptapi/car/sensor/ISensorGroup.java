package com.ecarx.xui.adaptapi.car.sensor;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes.dex */
public interface ISensorGroup {
    public static final int SENSOR_GROUP_TYPE_ACCE = 8389120;
    public static final int SENSOR_GROUP_TYPE_FOUR_WHEEL_BREAK_PRESSURE = 8389888;
    public static final int SENSOR_GROUP_TYPE_GYRO = 8388864;
    public static final int SENSOR_GROUP_TYPE_PULES = 8389376;
    public static final int SENSOR_GROUP_TYPE_SHAFT_TORQUE = 8390144;
    public static final int SENSOR_GROUP_TYPE_W4M = 8389632;

    public interface IMountAngle {
        float getPitchMountAngle();

        float getRollMountAngle();

        float getYawMountAngle();

        boolean hasMountAngle();
    }

    public interface ISensorGroupListener extends ISensor.ISensorListener {
        void onSensorGroupChanged(int i, ISensorGroupValue iSensorGroupValue);
    }

    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface SensorGroupType {
    }
}
