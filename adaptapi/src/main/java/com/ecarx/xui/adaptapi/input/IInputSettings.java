package com.ecarx.xui.adaptapi.input;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: ecarx.adaptapi.jar:com/ecarx/xui/adaptapi/input/IInputSettings.class */
public interface IInputSettings {
    public static final int CUSTOM_FUNCTION_ADAS = 2;
    public static final int CUSTOM_FUNCTION_MEDIA = 1;
    public static final int CUSTOM_FUNCTION_NONE = 0;
    public static final int DURATION_BUTTON_STUCK = 2;
    public static final int DURATION_HOLD_PRESS_INTERVAL_TRIGGER = 3;
    public static final int DURATION_HOLD_SHORT = 1;
    public static final int INPUT_SETTING_LONG_PRESS_VOLUME_ADJUSTMENT_RATE = 2;
    public static final int INPUT_SETTING_MAX_STEP_TO_STEP = 5;
    public static final int INPUT_SETTING_MOVE_OPERATION_DECREASE_RATE = 6;
    public static final int INPUT_SETTING_MOVE_OPERATION_INCREASE_RATE = 7;
    public static final int INPUT_SETTING_SHORT_PRESS_VOLUME_ADJUSTMENT = 1;
    public static final int INPUT_SETTING_SWIPE_VOLUME_ADJUSTMENT_RATE = 4;
    public static final int STEER_WHEEL_TYPE_COORDINATE = 3;
    public static final int STEER_WHEEL_TYPE_MECHANICAL = 1;
    public static final int STEER_WHEEL_TYPE_MECHANICAL2 = 4;
    public static final int STEER_WHEEL_TYPE_MECHANICAL_WITH_PADDLE = 5;
    public static final int STEER_WHEEL_TYPE_MOVING = 2;

    @Documented
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: ecarx.adaptapi.jar:com/ecarx/xui/adaptapi/input/IInputSettings$CustomFunctionType.class */
    public @interface CustomFunctionType {
    }

    @Documented
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: ecarx.adaptapi.jar:com/ecarx/xui/adaptapi/input/IInputSettings$DurationType.class */
    public @interface DurationType {
    }

    @Documented
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: ecarx.adaptapi.jar:com/ecarx/xui/adaptapi/input/IInputSettings$InputSetting.class */
    public @interface InputSetting {
    }

    @Documented
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: ecarx.adaptapi.jar:com/ecarx/xui/adaptapi/input/IInputSettings$SteerWheelType.class */
    public @interface SteerWheelType {
    }

    int getCustomFunctionType();

    long getInputSettingDuration(int i);

    int getInputSettingValue(int i);

    int getSteerWheelType();
}
