package com.ecarx.xui.adaptapi.car.userprofile;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public interface ICarKey {
    public static final int DIGITAL_KEY_TYPE_OWNER = 0;
    public static final int DIGITAL_KEY_TYPE_SHARED = 1;

    @Retention(RetentionPolicy.SOURCE)
    public @interface DigitalKeyType {
    }

    public interface ICarKeyObserver {
        void multipleKeyFound(boolean z);

        void onKeyReadResult(int i);

        void timeout();
    }

    public interface IDigitalKeyCallback {
        void onDigitalKeyResponse(int i, DigitalKeyInfo digitalKeyInfo);

        void onDigitalResResponse(int i, int[] iArr);
    }

    void cancelDiscovery();

    boolean createDigitalKey(int i);

    boolean delAllDigitalKey();

    boolean delDigitalKeyItem(int i);

    DigitalKeyInfo[] getDigitalKeys();

    int[] getHangupIds();

    int[] getRecoverIds();

    void readRealKey();

    boolean registerCarKeyObserver(ICarKeyObserver iCarKeyObserver);

    boolean registerDigitalKeyCallback(IDigitalKeyCallback iDigitalKeyCallback);

    void startDiscovery();

    void unbindCarKey(int i);

    boolean unregisterCarKeyObserver(ICarKeyObserver iCarKeyObserver);

    boolean unregisterDigitalKeyCallback(IDigitalKeyCallback iDigitalKeyCallback);
}
