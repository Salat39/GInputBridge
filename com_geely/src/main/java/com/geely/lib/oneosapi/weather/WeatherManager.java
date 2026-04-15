package com.geely.lib.oneosapi.weather;

import android.content.Context;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.geely.lib.oneosapi.common.ServiceBaseManager;
import com.geely.lib.oneosapi.weather.IWeatherAPICallback;
import com.geely.lib.oneosapi.weather.IWeatherCallback;
import com.geely.lib.oneosapi.weather.IWeatherService;
import com.geely.lib.oneosapi.weather.bean.WeatherInfo;

/* loaded from: classes.dex */
public class WeatherManager implements ServiceBaseManager {
    private static final String TAG = "WeatherManager";
    private Context mContext;
    private IWeatherService mService;

    public WeatherManager(Context context, IBinder binder) {
        this.mContext = context;
        this.mService = IWeatherService.Stub.asInterface(binder);
    }

    @Override // com.geely.lib.oneosapi.common.ServiceBaseManager
    public void setService(IBinder binder) {
        this.mService = IWeatherService.Stub.asInterface(binder);
    }

    private boolean isServiceAlive() {
        IWeatherService iWeatherService = this.mService;
        return iWeatherService != null && iWeatherService.asBinder().isBinderAlive();
    }

    public void getWeatherByLocation(double lon, double lat, BaseWeatherAPICallback callback) {
        if (isServiceAlive()) {
            try {
                this.mService.getWeatherByLocation(lon, lat, callback);
                return;
            } catch (RemoteException e) {
                e.printStackTrace();
                return;
            }
        }
        Log.d(TAG, "getWeatherByLocation: service is not alive");
    }

    public void getWeatherByCityId(String cityID, BaseWeatherAPICallback callback) {
        if (isServiceAlive()) {
            try {
                this.mService.getWeatherByCityId(cityID, callback);
                return;
            } catch (RemoteException e) {
                e.printStackTrace();
                return;
            }
        }
        Log.d(TAG, "getWeatherByCityId: service is not alive");
    }

    public boolean setLocationPermission(boolean status) {
        if (isServiceAlive()) {
            try {
                return this.mService.setLocationPermission(status);
            } catch (RemoteException e) {
                e.printStackTrace();
                return false;
            }
        }
        Log.d(TAG, "setLocationPermission: service is not alive");
        return false;
    }

    public boolean setCurrentCity(boolean status) {
        if (isServiceAlive()) {
            try {
                return this.mService.setCurrentCity(status);
            } catch (RemoteException e) {
                e.printStackTrace();
                return false;
            }
        }
        Log.d(TAG, "setCurrentCity: service is not alive");
        return false;
    }

    public boolean getLocationPermission() {
        if (isServiceAlive()) {
            try {
                return this.mService.getLocationPermission();
            } catch (RemoteException e) {
                e.printStackTrace();
                return false;
            }
        }
        Log.d(TAG, "getLocationPermission: service is not alive");
        return false;
    }

    public boolean getCurrentCity() {
        if (isServiceAlive()) {
            try {
                return this.mService.getCurrentCity();
            } catch (RemoteException e) {
                e.printStackTrace();
                return false;
            }
        }
        Log.d(TAG, "getCurrentCity: service is not alive");
        return false;
    }

    public String getLocationCity() {
        if (isServiceAlive()) {
            try {
                return this.mService.getLocationCity();
            } catch (RemoteException e) {
                e.printStackTrace();
                return null;
            }
        }
        Log.d(TAG, "getLocationCity: service is not alive");
        return null;
    }

    public void getWeatherInfoByLocation(double lon, double lat, BaseWeatherCallback callback) {
        if (isServiceAlive()) {
            try {
                Log.d(TAG, "getWeatherInfoByLocation: ");
                this.mService.getWeatherInfoByLocation(lon, lat, callback);
                return;
            } catch (RemoteException e) {
                e.printStackTrace();
                return;
            }
        }
        Log.d(TAG, "getWeatherInfoByLocation: service is not alive");
    }

    public void getWeatherInfoByCityId(String cityID, BaseWeatherCallback callback) {
        if (isServiceAlive()) {
            try {
                Log.d(TAG, "getWeatherInfoByCityId: ");
                this.mService.getWeatherInfoByCityId(cityID, callback);
                return;
            } catch (RemoteException e) {
                e.printStackTrace();
                return;
            }
        }
        Log.d(TAG, "getWeatherInfoByCityId: service is not alive");
    }

    public static abstract class BaseWeatherAPICallback extends IWeatherAPICallback.Stub {
        @Override // com.geely.lib.oneosapi.weather.IWeatherAPICallback
        public void callback(String json) {
            Log.d(WeatherManager.TAG, "BaseWeatherAPICallback: " + json);
        }
    }

    public static abstract class BaseWeatherCallback extends IWeatherCallback.Stub {
        @Override // com.geely.lib.oneosapi.weather.IWeatherCallback
        public void callback(int code, String message, WeatherInfo info) {
            Log.d(WeatherManager.TAG, "BaseWeatherCallback() called with: code = [" + code + "], message = [" + message + "], info = [" + info + "]");
        }
    }
}