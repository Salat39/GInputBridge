package ecarx.car;

import android.content.Context;

public class ECarXCar {

    public ECarXCar createCar(Context c, IECarXCar i) {
        return null;
    }

    public Object getCarManager(String v) {
        return null;
    }

    public Object getCarManager(String v, IECarXCar c) {
        return null;
    }

    public boolean isConnected() {
        return false;
    }

    public void registerDieCallback(ECarXCar.CarServiceDieCallback callback) {

    }

    public void unregisterDieCallback() {
    }

    public interface CarServiceDieCallback {
        public void onServiceDeath();
    }
}
