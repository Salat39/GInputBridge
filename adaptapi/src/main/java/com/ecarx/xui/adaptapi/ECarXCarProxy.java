package com.ecarx.xui.adaptapi;

import android.content.Context;
import android.os.Handler;

import com.ecarx.xui.adaptapi.binder.IConnectable;

import ecarx.car.ECarXCar;
import ecarx.car.hardware.signal.CarSignalManager;

/* loaded from: C:\Users\Andrei\AppData\Local\Temp\jadx-7518071738844169467\classes.dex */
public class ECarXCarProxy {

    public interface ECarXCarProxyMethod {
        void onECarXCarServiceConnected(ECarXCar eCarXCar, CarSignalManager carSignalManager);

        void onECarXCarServiceDeath();
    }

    public ECarXCarProxy(Context context, ECarXCarProxyMethod eCarXCarProxyMethod) {

    }

    public void callECarXCarServiceDeath() {
    }

    public void cleanup() {

    }

    public CarSignalManager getCarSignalManager() {
        return null;
    }

    public ECarXCar getECarXCar() {

        return null;
    }

    public void initECarXCar() {
    }

    public boolean isECarXCarConnected() {
        return false;
    }

    public void registerConnectWatcher(IConnectable.IConnectWatcher iConnectWatcher) {
    }

    public void stopReconnection() {
    }

    public void unregisterConnectWatcher() {
    }
}
