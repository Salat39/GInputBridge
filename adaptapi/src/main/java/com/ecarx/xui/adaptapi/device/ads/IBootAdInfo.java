package com.ecarx.xui.adaptapi.device.ads;

public interface IBootAdInfo {
    String getAdId();

    String getAdPath();

    long getDuration();

    long getEffectiveTime();

    long getExpiredTime();

    int getMaxTimes();

    String getMd5();
}
