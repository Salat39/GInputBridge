package com.ecarx.xui.adaptapi.car.base;

import com.ecarx.xui.adaptapi.FunctionStatus;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public interface ICarFunction {
    public static final int CAR_MODULE_ADAS = 671088640;
    public static final int CAR_MODULE_AMBIENCE_LIGHT = 704643072;
    public static final int CAR_MODULE_AUDIO = 771751936;
    public static final int CAR_MODULE_BCM = 553648128;
    public static final int CAR_MODULE_COMMON = 0;
    public static final int CAR_MODULE_DAYMODE = 687865856;
    public static final int CAR_MODULE_DRIVE_MODE = 570425344;
    public static final int CAR_MODULE_HUD = 654311424;
    public static final int CAR_MODULE_HVAC = 268435456;
    public static final int CAR_MODULE_HYBRID = 603979776;
    public static final int CAR_MODULE_LAMP = 721420288;
    public static final int CAR_MODULE_PAS = 587202560;
    public static final int CAR_MODULE_SAFETY = 738197504;
    public static final int CAR_MODULE_SCENE = 788529152;
    public static final int CAR_MODULE_SEAT = 754974720;
    public static final int CAR_MODULE_SETTING = 536870912;
    public static final int CAR_MODULE_UNIT = 620756992;
    public static final int CAR_MODULE_VENDOR = Integer.MIN_VALUE;
    public static final int CAR_MODULE_VOICE = -2130706432;
    public static final int CAR_MODULE_WPC = 637534208;
    public static final int COMMON_VALUE_DEFAULT = 2;
    public static final int COMMON_VALUE_ERROR = 253;
    public static final int COMMON_VALUE_NONE = 254;
    public static final int COMMON_VALUE_OFF = 0;
    public static final int COMMON_VALUE_ON = 1;
    public static final int COMMON_VALUE_UNKNOWN = 255;

    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface CarFunction {
    }

    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface CarFunctionFlt {
    }

    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface CarFunctionInt {
    }

    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface CarFunctionValue {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface CarModule {
    }

    public interface IFunctionValueWatcher {
        void onCustomizeFunctionValueChanged(int i, int i2, float f);

        void onFunctionChanged(int i);

        void onFunctionValueChanged(int i, int i2, int i3);

        void onSupportedFunctionStatusChanged(int i, int i2, FunctionStatus functionStatus);

        void onSupportedFunctionValueChanged(int i, int[] iArr);
    }

    float getCustomizeFunctionValue(int i) throws UnsupportedOperationException;

    float getCustomizeFunctionValue(int i, int i2) throws UnsupportedOperationException;

    int getFunctionValue(int i) throws UnsupportedOperationException;

    int getFunctionValue(int i, int i2) throws UnsupportedOperationException;

    int[] getSupportedFunctionValue(int i) throws UnsupportedOperationException;

    int[] getSupportedFunctionValue(int i, int i2) throws UnsupportedOperationException;

    int[] getSupportedFunctionZones(int i) throws UnsupportedOperationException;

    FunctionStatus isFunctionSupported(int i);

    FunctionStatus isFunctionSupported(int i, int i2);

    FunctionStatus isFunctionSupported(int i, int i2, int i3);

    boolean registerFunctionValueWatcher(int i, IFunctionValueWatcher iFunctionValueWatcher) throws UnsupportedOperationException;

    boolean registerFunctionValueWatcher(IFunctionValueWatcher iFunctionValueWatcher) throws UnsupportedOperationException;

    boolean registerFunctionValueWatcher(int[] iArr, IFunctionValueWatcher iFunctionValueWatcher) throws UnsupportedOperationException;

    boolean setCustomizeFunctionValue(int i, float f) throws UnsupportedOperationException;

    boolean setCustomizeFunctionValue(int i, int i2, float f) throws UnsupportedOperationException;

    boolean setFunctionValue(int i, int i2) throws UnsupportedOperationException;

    boolean setFunctionValue(int i, int i2, int i3) throws UnsupportedOperationException;

    boolean unregisterFunctionValueWatcher(IFunctionValueWatcher iFunctionValueWatcher) throws UnsupportedOperationException;
}
