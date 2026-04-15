package com.ecarx.xui.adaptapi.diminteraction;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Deprecated
/* loaded from: C:\Users\Andrei\AppData\Local\Temp\jadx-7518071738844169467\classes.dex */
public interface IClimateInteraction {
    public static final int CAR_MODULE_HVAC = 268435456;
    public static final int HVAC_FUNC_TEMP = 268828928;
    public static final int HVAC_FUNC_TEMP_MAX = 268829184;
    public static final int HVAC_FUNC_TEMP_MIN = 268829440;
    public static final int HVAC_FUNC_TEMP_UNIT = 268830208;
    public static final int TEMPERATURE_UNIT_C = 268830209;
    public static final int TEMPERATURE_UNIT_F = 268830210;

    @Retention(RetentionPolicy.SOURCE)
    public @interface ClimateFunction {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface TemperatureUnit {
    }

    boolean updateFunctionValue(int i, float f);

    boolean updateFunctionValue(int i, int i2);
}