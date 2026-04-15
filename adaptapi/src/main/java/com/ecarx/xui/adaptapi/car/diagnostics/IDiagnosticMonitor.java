package com.ecarx.xui.adaptapi.car.diagnostics;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public interface IDiagnosticMonitor {
    public static final int MONITOR_STATUS_FAULT = 2;
    public static final int MONITOR_STATUS_RUNNING = 1;
    public static final int MONITOR_STATUS_UNKNOWN = 0;
    public static final int MONITOR_TYPE_AUD_STATUS = 8195;
    public static final int MONITOR_TYPE_BT_LINK_QUALITY = 4116;
    public static final int MONITOR_TYPE_CAN_STATUS = 12290;
    public static final int MONITOR_TYPE_CCSM_STATUS = 8199;
    public static final int MONITOR_TYPE_CPU_USAGE = 4113;
    public static final int MONITOR_TYPE_CSD_STATUS = 8194;
    public static final int MONITOR_TYPE_CSD_TEMP = 4098;
    public static final int MONITOR_TYPE_ETHERNET_VCM_STATUS = 12295;
    public static final int MONITOR_TYPE_FLEXRAY_STATUS = 12289;
    public static final int MONITOR_TYPE_FRAME_RATE = 4115;
    public static final int MONITOR_TYPE_IHU_STATUS = 8193;
    public static final int MONITOR_TYPE_IHU_TEMP = 4097;
    public static final int MONITOR_TYPE_LIN_CCSM_STATUS = 12297;
    public static final int MONITOR_TYPE_LIN_WPC_STATUS = 12298;
    public static final int MONITOR_TYPE_LVDS_CSD_STATUS = 12292;
    public static final int MONITOR_TYPE_LVDS_PAC_STATUS = 12293;
    public static final int MONITOR_TYPE_LVDS_WAM_STATUS = 12294;
    public static final int MONITOR_TYPE_MEMORY_USAGE = 4114;
    public static final int MONITOR_TYPE_PAC_STATUS = 8200;
    public static final int MONITOR_TYPE_TEM2_STATUS = 8197;
    public static final int MONITOR_TYPE_USB_TEM_STATUS = 12296;
    public static final int MONITOR_TYPE_VCM_STATUS = 8198;
    public static final int MONITOR_TYPE_WPC_STATUS = 8196;

    public interface IMonitorListener {
        void onMonitorStatusChanged(int i, int i2);

        void onMonitorValueChanged(int i, float f);
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface MonitorStatus {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface MonitorType {
    }

    boolean registerListener(IMonitorListener iMonitorListener, int i);

    boolean registerListener(IMonitorListener iMonitorListener, int[] iArr);

    boolean setMonitorEnable(int i, boolean z);

    boolean unregisterListener(IMonitorListener iMonitorListener);
}
