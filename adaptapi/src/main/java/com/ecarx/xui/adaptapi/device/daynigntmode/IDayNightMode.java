package com.ecarx.xui.adaptapi.device.daynigntmode;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public interface IDayNightMode {
    public static final int DISPLAY_DAY = 1;
    public static final int DISPLAY_INVALID = 0;
    public static final int DISPLAY_MODE_AUTO = 3;
    public static final int DISPLAY_MODE_CUSTOM = 4;
    public static final int DISPLAY_MODE_DAY = 1;
    public static final int DISPLAY_MODE_INVALID = 0;
    public static final int DISPLAY_MODE_NIGHT = 2;
    public static final int DISPLAY_NIGHT = 2;

    @Retention(RetentionPolicy.SOURCE)
    public @interface DayNight {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface DayNightMode {
    }

    public interface IDayNightChangeCallBack {
        void onDayNightChanged(int i);

        void onDayNightModeChange(int i);
    }

    int getDayNight();

    int getDayNightMode();

    boolean registerDayNightModeCallBack(IDayNightChangeCallBack iDayNightChangeCallBack);

    boolean unregisterDayNigntModeCallBack(IDayNightChangeCallBack iDayNightChangeCallBack);
}
