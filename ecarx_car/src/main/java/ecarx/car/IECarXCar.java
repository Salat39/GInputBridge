package ecarx.car;

import android.os.Binder;
import android.os.IBinder;

public interface IECarXCar {
    IECarXCar asInterface(IBinder i);

    public static IECarXCar Stub = i -> null;

}
