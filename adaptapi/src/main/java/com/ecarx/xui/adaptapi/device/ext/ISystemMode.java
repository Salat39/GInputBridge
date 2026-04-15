package com.ecarx.xui.adaptapi.device.ext;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public interface ISystemMode {
    public static final int ENTERTAINMENT_MODE_OFF = 1049089;
    public static final int ENTERTAINMENT_MODE_ON = 1049090;
    public static final int ENTERTAINMENT_MODE_UNKNOWN = -1;
    public static final int INFOTAINMENT_MODE_OFF = 1052673;
    public static final int INFOTAINMENT_MODE_ON = 1052674;
    public static final int INFOTAINMENT_MODE_UNKNOWN = -1;
    public static final int PARTIAL_MODE_OFF = 1048833;
    public static final int PARTIAL_MODE_ON = 1048834;
    public static final int PARTIAL_MODE_UNKNOWN = -1;
    public static final int SYSTEM_MODE_ENTERTAINMENT = 1049088;
    public static final int SYSTEM_MODE_INFOTAINMENT = 1052672;
    public static final int SYSTEM_MODE_PARTIAL = 1048832;
    public static final int SYSTEM_MODE_STATE_UNKNOWN = -1;

    public interface DIMAnimationStateCallback {
        void onDIMAnimationStateResponse(int i);
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface EntertainmentModeState {
    }

    public interface ISystemModeListener {
        void onSystemModeStateChanged(int i, int i2);
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface InfotainmentModeState {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ModeState {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface PartialModeState {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ProvisionStatus {
        public static final int END = 1;
        public static final int START = 0;
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface SystemMode {
    }

    void closeBootUpAnimation();

    int getSystemModeState(int i);

    boolean registerDIMAnimationStateCallback(DIMAnimationStateCallback dIMAnimationStateCallback);

    boolean registerListener(int i, ISystemModeListener iSystemModeListener);

    void setDisplayWakeState(int i, boolean z);

    void setProvisionStatus(int i, DIMAnimationStateCallback dIMAnimationStateCallback);

    boolean unregisterDIMAnimationStateCallback(DIMAnimationStateCallback dIMAnimationStateCallback);

    boolean unregisterListener(ISystemModeListener iSystemModeListener);
}
