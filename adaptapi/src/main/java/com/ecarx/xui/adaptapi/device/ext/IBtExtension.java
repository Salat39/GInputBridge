package com.ecarx.xui.adaptapi.device.ext;

import com.ecarx.xui.adaptapi.device.ext.common.BtDevice;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

public interface IBtExtension {

    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface Profile {
        public static final int AUTO_CONNECT_PROFILE_A2DP = 2;
        public static final int AUTO_CONNECT_PROFILE_HFP = 1;
    }

    boolean cancelBtDiscovery();

    boolean getA2dpAutoRejectConnStatus();

    IA2dpExtension getA2dpExtension();

    int getBtState();

    String getConnectedPhoneNumber();

    int getHeadsetPower(BtDevice btDevice);

    boolean getHfpAutoRejectConnStatus();

    boolean getLocalOobData();

    IMultiBtPbapExtension getMultiBtPbapExtension();

    String getPSDBluetoothMacAddress();

    IPbapExtension getPbapExtension();

    boolean isBLEScanEnable();

    boolean isBtDiscovering();

    boolean isBtEnabled();

    boolean registerBtCallback(IBtExtensionCallback iBtExtensionCallback);

    boolean reqBtPair(String str);

    List<BtDevice> reqBtPairedDevices();

    boolean reqBtUnpair(String str);

    boolean setA2dpAutoRejectConnStatus(boolean z);

    boolean setBLEScanEnable(boolean z);

    boolean setBluetoothAutoConnect(String str, int i, boolean z);

    boolean setBluetoothAutoConnect(String str, boolean z);

    boolean setBtEnable(boolean z);

    boolean setHfpAutoRejectConnStatus(boolean z);

    boolean startBtDiscovery();

    boolean startDiscoveryOnlyClassic();

    boolean unregisterBtCallback(IBtExtensionCallback iBtExtensionCallback);
}
