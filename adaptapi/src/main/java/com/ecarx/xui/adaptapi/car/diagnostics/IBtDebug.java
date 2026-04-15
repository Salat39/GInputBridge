package com.ecarx.xui.adaptapi.car.diagnostics;

public interface IBtDebug {
    boolean enableDUTMode(boolean z);

    boolean enableSSPMode(boolean z);

    boolean enableVirtualSniffer(boolean z);

    boolean isDUTModeEnabled();

    boolean isSSPModeEnabled();

    boolean isVirtualSnifferEnabled();
}
