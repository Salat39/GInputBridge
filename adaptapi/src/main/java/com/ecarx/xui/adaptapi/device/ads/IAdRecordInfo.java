package com.ecarx.xui.adaptapi.device.ads;

public interface IAdRecordInfo {
    String getAdId();

    int getClickCount();

    long getDisplayDuration();

    long getDisplayTimestamp();

    boolean isSkiped();
}
