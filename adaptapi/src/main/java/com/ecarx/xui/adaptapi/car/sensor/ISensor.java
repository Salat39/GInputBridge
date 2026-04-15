package com.ecarx.xui.adaptapi.car.sensor;

import com.ecarx.xui.adaptapi.FunctionStatus;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public interface ISensor {
    public static final int RATE_DR = 16;
    public static final int RATE_FAST = 1;
    public static final int RATE_FASTEST = 0;
    public static final int RATE_NORMAL = 3;
    public static final int RATE_SLOW = 4;
    public static final int RATE_SLOWEST = 5;
    public static final int RATE_UI = 2;
    public static final int SENSOR_TYPE_ABS = 2101504;
    public static final int SENSOR_TYPE_ABS_WARNING = 1058304;
    public static final int SENSOR_TYPE_ACCELERATOR_DEPTH = 1053696;
    public static final int SENSOR_TYPE_AIRBAG_STATUS_DRIVER = 2109696;
    public static final int SENSOR_TYPE_AIRBAG_STATUS_PASSENGER = 2109952;
    public static final int SENSOR_TYPE_ALRM_STS = 2122496;
    public static final int SENSOR_TYPE_AQI_AMBIENT = 1049600;
    public static final int SENSOR_TYPE_AQI_BACK_ROW = 1049872;
    public static final int SENSOR_TYPE_AQI_INDOOR = 1049856;
    public static final int SENSOR_TYPE_AQI_LEVEL_AMBIENT = 2106112;
    public static final int SENSOR_TYPE_AQI_LEVEL_BACK_ROW = 2106384;
    public static final int SENSOR_TYPE_AQI_LEVEL_INDOOR = 2106368;
    public static final int SENSOR_TYPE_BATTERY_CURRENT = 1051168;
    public static final int SENSOR_TYPE_BRAKE_DEPTH = 1053440;
    public static final int SENSOR_TYPE_BRAKE_FLUID_LEVEL = 2098688;
    public static final int SENSOR_TYPE_BRAKE_PRESSURE = 1053456;
    public static final int SENSOR_TYPE_BRK_WARNING = 1058048;
    public static final int SENSOR_TYPE_CAR_MODE = 2102272;
    public static final int SENSOR_TYPE_CAR_SPEED = 1048832;
    public static final int SENSOR_TYPE_CAR_SPEED_ACCELERATION = 1054464;
    public static final int SENSOR_TYPE_CAR_SPEED_FROM_IPK = 1055232;
    public static final int SENSOR_TYPE_CO2_INDOOR = 1051904;
    public static final int SENSOR_TYPE_CO2_LEVEL_INDOOR = 2106624;
    public static final int SENSOR_TYPE_DAY_NIGHT = 2101248;
    public static final int SENSOR_TYPE_DRIVER_TIREDNESS_STATUS = 3149824;
    public static final int SENSOR_TYPE_ENDURANCE_MILEAGE = 1050624;
    public static final int SENSOR_TYPE_ENDURANCE_MILEAGE_EV = 1054976;
    public static final int SENSOR_TYPE_ENDURANCE_MILEAGE_FUEL = 1054720;
    public static final int SENSOR_TYPE_ENGINE_COOLANT_LEVEL = 2098432;
    public static final int SENSOR_TYPE_ENGINE_COOLANT_TEMPERATURE = 1052416;
    public static final int SENSOR_TYPE_ENGINE_OIL_LEVEL = 2098176;
    public static final int SENSOR_TYPE_ENGINE_OIL_PERC = 1057792;
    public static final int SENSOR_TYPE_ENGINE_START_STOP_STATE = 2103040;
    public static final int SENSOR_TYPE_ENGINE_STATE = 2102784;
    public static final int SENSOR_TYPE_ESC_WARNING = 1058560;
    public static final int SENSOR_TYPE_EV_BATTERY_LEVEL = 1051136;
    public static final int SENSOR_TYPE_EV_BATTERY_STATE = 2102528;
    public static final int SENSOR_TYPE_EV_BATTERY_TEMPERATURE = 1051152;
    public static final int SENSOR_TYPE_EYE_BALL_TRACK_STATE = 3148800;
    public static final int SENSOR_TYPE_FUEL_LEVEL = 1050112;
    public static final int SENSOR_TYPE_GEAR = 2097664;
    public static final int SENSOR_TYPE_HANDBRAKE_STATE = 2097920;
    public static final int SENSOR_TYPE_HVSYSRLY_STS = 3150080;
    public static final int SENSOR_TYPE_IGNITION_STATE = 2097408;
    public static final int SENSOR_TYPE_LANE_DEPARTURE = 3149056;
    public static final int SENSOR_TYPE_LIGHT = 2100992;
    public static final int SENSOR_TYPE_MOTO_SPEED_FRONT = 1057024;
    public static final int SENSOR_TYPE_MOTO_SPEED_REAR = 1057280;
    public static final int SENSOR_TYPE_MOTO_TORQUE_FRONT = 1056512;
    public static final int SENSOR_TYPE_MOTO_TORQUE_REAR = 1056768;
    public static final int SENSOR_TYPE_ODOMETER = 1050368;
    public static final int SENSOR_TYPE_PM25_AMBIENT = 1049088;
    public static final int SENSOR_TYPE_PM25_BACK_ROW = 1049360;
    public static final int SENSOR_TYPE_PM25_INDOOR = 1049344;
    public static final int SENSOR_TYPE_PM25_LEVEL_AMBIENT = 2105600;
    public static final int SENSOR_TYPE_PM25_LEVEL_BACK_ROW = 2105872;
    public static final int SENSOR_TYPE_PM25_LEVEL_INDOOR = 2105856;
    public static final int SENSOR_TYPE_PM25_STATE_AMBIENT = 2106880;
    public static final int SENSOR_TYPE_PM25_STATE_BACK_ROW = 2107152;
    public static final int SENSOR_TYPE_PM25_STATE_INDOOR = 2107136;
    public static final int SENSOR_TYPE_RAIN = 1052160;
    public static final int SENSOR_TYPE_RAIN_SENSOR_STATE = 3149568;
    public static final int SENSOR_TYPE_REAR_WHEEL_ANGEL = 1057536;
    public static final int SENSOR_TYPE_ROLLBAR_POSITION_FRONT = 1055488;
    public static final int SENSOR_TYPE_ROLLBAR_POSITION_REAR = 1055744;
    public static final int SENSOR_TYPE_ROLLBAR_TORQUE_FRONT = 1056000;
    public static final int SENSOR_TYPE_ROLLBAR_TORQUE_REAR = 1056256;
    public static final int SENSOR_TYPE_RPM = 1050880;
    public static final int SENSOR_TYPE_SAFE_BELT_DRIVER = 2101760;
    public static final int SENSOR_TYPE_SAFE_BELT_DRIVER_ORIGIN_STATUS = 2167296;
    public static final int SENSOR_TYPE_SAFE_BELT_PASSENGER = 2102016;
    public static final int SENSOR_TYPE_SAFE_BELT_ROW2_CENTER = 2103808;
    public static final int SENSOR_TYPE_SAFE_BELT_ROW2_LEFT = 2103296;
    public static final int SENSOR_TYPE_SAFE_BELT_ROW2_RIGHT = 2103552;
    public static final int SENSOR_TYPE_SEAT_OCCUPATION_STATUS_DRIVER = 2110208;
    public static final int SENSOR_TYPE_SEAT_OCCUPATION_STATUS_PASSENGER = 2110464;
    public static final int SENSOR_TYPE_SEAT_OCCUPATION_STATUS_SECOND_ROW_LEFT = 2110720;
    public static final int SENSOR_TYPE_SEAT_OCCUPATION_STATUS_SECOND_ROW_RIGHT = 2110976;
    public static final int SENSOR_TYPE_SEAT_PRESSURE_DRIVER = 1053952;
    public static final int SENSOR_TYPE_SEAT_PRESSURE_PASSENGER = 1054208;
    public static final int SENSOR_TYPE_SNSR_FR_WARNING = 1058816;
    public static final int SENSOR_TYPE_SNSR_LE_WARNING = 1059328;
    public static final int SENSOR_TYPE_SNSR_RE_WARNING = 1059072;
    public static final int SENSOR_TYPE_SNSR_RI_WARNING = 1059584;
    public static final int SENSOR_TYPE_STATUS_TWLIBRISTS = 2101008;
    public static final int SENSOR_TYPE_STEERING_WHEEL_ANGLE = 1052672;
    public static final int SENSOR_TYPE_STEERING_WHEEL_ANGLE_SPEED = 1052928;
    public static final int SENSOR_TYPE_TEMPERATURE_AMBIENT = 1051392;
    public static final int SENSOR_TYPE_TEMPERATURE_INDOOR = 1051648;
    public static final int SENSOR_TYPE_TIREDNESS_DRIVING_STATE = 3148544;
    public static final int SENSOR_TYPE_TOO_CLOSE_TO_FRONT_CAR = 3149312;
    public static final int SENSOR_TYPE_VEHICLE_WEIGHT = 1053184;
    public static final int SENSOR_TYPE_VEH_MTN_STATE = 3148288;
    public static final int SENSOR_TYPE_WARN_ENGINE_COOLANT_SYSTEM_FAULT = 3148032;
    public static final int SENSOR_TYPE_WARN_ENGINE_COOLANT_TEMP_HIGH = 3146752;
    public static final int SENSOR_TYPE_WARN_ENGINE_OIL_PRESSURE = 3146496;
    public static final int SENSOR_TYPE_WARN_ENGINE_OIL_SYSTEM_FAULT = 3147776;
    public static final int SENSOR_TYPE_WARN_EV_BATTERY_LOW = 3146240;
    public static final int SENSOR_TYPE_WARN_FUEL_RED = 3145984;
    public static final int SENSOR_TYPE_WARN_STEERING_ASSISTANCE_FAULT = 3147520;
    public static final int SENSOR_TYPE_WARN_TRANSMISSION_SYSTEM_FAULT = 3147264;
    public static final int SENSOR_TYPE_WARN_TRANSMISSION_TEMP_HIGH = 3147008;
    public static final int SETTING_FUNC_TELM_PHOTO_SWT = 2123520;
    public static final int SETTING_FUNC_VSTD_CTRL_REQ = 2123264;

    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface ContinuousSensor {
    }

    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface EventSensor {
    }

    public interface ISensorListener {
        void onSensorEventChanged(int i, int i2);

        void onSensorSupportChanged(int i, FunctionStatus functionStatus);

        void onSensorValueChanged(int i, float f);
    }

    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface SensorRate {
    }

    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface SensorType {
    }

    ISensorGroup.IMountAngle getMountAngle();

    int getSensorEvent(int i);

    ISensorGroupValue getSensorGroupLatestValue(int i);

    float getSensorLatestValue(int i);

    FunctionStatus isSensorSupported(int i);

    boolean registerListener(ISensorListener iSensorListener, int i);

    boolean registerListener(ISensorListener iSensorListener, int i, int i2);

    void unregisterListener(ISensorListener iSensorListener);
}
