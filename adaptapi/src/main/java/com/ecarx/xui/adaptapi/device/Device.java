package com.ecarx.xui.adaptapi.device;

import android.content.Context;

import com.ecarx.xui.adaptapi.AdaptAPI;
import com.ecarx.xui.adaptapi.device.ads.IAdvertise;
import com.ecarx.xui.adaptapi.device.daynigntmode.IDayNightMode;
import com.ecarx.xui.adaptapi.device.ext.IBtExtension;
import com.ecarx.xui.adaptapi.device.ext.ISystemMode;
import com.ecarx.xui.adaptapi.device.log.IDeviceLog;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public abstract class Device extends AdaptAPI {
    public static final int OPERATOR_DEFAULT = 0;
    public static final int OPERATOR_GCV = 8;
    public static final int OPERATOR_GEELY = 1;
    public static final int OPERATOR_GEOMETRY = 7;
    public static final int OPERATOR_LOTUS = 5;
    public static final int OPERATOR_LYNKCO = 2;
    public static final int OPERATOR_PROTON = 6;
    public static final int OPERATOR_SMART = 9;
    public static final int OPERATOR_VOLVO_CARS = 3;
    public static final int OPERATOR_VOLVO_TRUCKS = 4;
    public static final int OPERATOR_ZEEKR = 16;

    @Retention(RetentionPolicy.SOURCE)
    @interface OperatorCode {
    }

    public abstract IAdvertise getAdvertise();

    public abstract float getAvailableStorageSize();

    public abstract IBtExtension getBtExtension();

    @Deprecated
    public abstract IDayNightMode getDayNightMode();

    public abstract IDeviceLog getDeviceLog();

    public abstract String getIhuId();

    @Deprecated
    public abstract int getMaxScreenBrightness();

    public abstract String getMcuHardwareVersion();

    public abstract String getMcuSoftwareVersion();

    public abstract int getMcuSoftwareVersionInt();

    @Deprecated
    public abstract int getMinScreenBrightness();

    public abstract String getMpuHardwareVersion();

    public abstract String getMpuSoftwareVersion();

    public abstract String getOSID();

    public abstract int getOperatorCode();

    public abstract String getOperatorName();

    public abstract String getPartNo();

    public abstract String getProjectCode();

    public abstract String getRolloffConfigCode();

    public abstract String getSerialNo();

    public abstract String getSupplierCode();

    public abstract ISystemMode getSystemMode();

    public abstract float getTotalStorageSize();

    public abstract String getVSVID();

    public abstract String getVehicleCountryCode();

    public abstract String getVehicleType();

    public abstract String getVehicleTypeConfig();

    public abstract String getVin();

    public abstract String getXdsn();

    public abstract boolean isDVRCameraConfigured();

    public abstract boolean isMobileNetworkConfigured();

    public abstract boolean isRearViewCameraConfigured();

    public abstract boolean isTboxConfigured();

    @Deprecated
    public abstract boolean setDayNightMode(int i);

    public static Device create(Context context) {
        return null;
//        return DeviceImp.create(context);
    }
}
