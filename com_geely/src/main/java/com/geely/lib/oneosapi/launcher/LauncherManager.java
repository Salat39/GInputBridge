package com.geely.lib.oneosapi.launcher;

import android.content.Context;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.geely.lib.oneosapi.common.ServiceBaseManager;
import com.geely.lib.oneosapi.launcher.ILauncherService;
import com.geely.lib.oneosapi.launcher.listener.IEnterOrExitHomePageChangeListener;
import com.geely.lib.oneosapi.launcher.listener.ILauncherPageSwitchListener;
import com.geely.lib.oneosapi.launcher.listener.IMapWidgetChangeListener;
import com.geely.lib.oneosapi.launcher.listener.IWeatherWidgetChangeListener;
import com.geely.lib.oneosapi.launcher.listener.IWidgetListDisplayChangeListener;

/* loaded from: classes.dex */
public class LauncherManager implements ServiceBaseManager {
    private static final String TAG = "ShortcutManager";
    private ILauncherService mService;

    public LauncherManager(Context mContext, IBinder binder) {
        this.mService = ILauncherService.Stub.asInterface(binder);
    }

    @Override // com.geely.lib.oneosapi.common.ServiceBaseManager
    public void setService(IBinder binder) {
        this.mService = ILauncherService.Stub.asInterface(binder);
    }

    public boolean isAlive() {
        return isServiceAlive();
    }

    private boolean isServiceAlive() {
        ILauncherService iLauncherService = this.mService;
        return iLauncherService != null && iLauncherService.asBinder().isBinderAlive();
    }

    public void toggleWidget() {
        if (isServiceAlive()) {
            try {
                this.mService.toggleWidget();
                return;
            } catch (RemoteException e) {
                e.printStackTrace();
                return;
            }
        }
        Log.d(TAG, "toggleWidget: service is not alive");
    }

    public void selectWidgetMap(String classname) {
        if (isServiceAlive()) {
            try {
                this.mService.selectWidgetMap(classname);
                return;
            } catch (RemoteException e) {
                e.printStackTrace();
                return;
            }
        }
        Log.d(TAG, "selectWidgetMap: service is not alive");
    }

    public void mediaSourceListDisplay(boolean show, boolean isPsd) {
        if (isServiceAlive()) {
            try {
                this.mService.mediaSourceListDisplay(show, isPsd);
                return;
            } catch (RemoteException e) {
                e.printStackTrace();
                return;
            }
        }
        Log.d(TAG, "mediaSourceListDisplay: service is not alive");
    }

    public void registerMapWidgetListener(IMapWidgetChangeListener listener) {
        if (isServiceAlive()) {
            try {
                this.mService.registerMapWidgetListener(listener);
                return;
            } catch (RemoteException e) {
                e.printStackTrace();
                return;
            }
        }
        Log.d(TAG, "registerMapWidgetListener: service is not alive");
    }

    public void unRegisterMapWidgetListener(IMapWidgetChangeListener listener) {
        if (isServiceAlive()) {
            try {
                this.mService.unRegisterMapWidgetListener(listener);
                return;
            } catch (RemoteException e) {
                e.printStackTrace();
                return;
            }
        }
        Log.d(TAG, "unRegisterMapWidgetListener: service is not alive");
    }

    public void registerWeatherWidgetListener(IWeatherWidgetChangeListener listener) {
        if (isServiceAlive()) {
            try {
                this.mService.registerWeatherWidgetListener(listener);
                return;
            } catch (RemoteException e) {
                e.printStackTrace();
                return;
            }
        }
        Log.d(TAG, "registerWeatherWidgetListener: service is not alive");
    }

    public void unRegisterWeatherWidgetListener(IWeatherWidgetChangeListener listener) {
        if (isServiceAlive()) {
            try {
                this.mService.unRegisterWeatherWidgetListener(listener);
                return;
            } catch (RemoteException e) {
                e.printStackTrace();
                return;
            }
        }
        Log.d(TAG, "unRegisterWeatherWidgetListener: service is not alive");
    }

    public void registerEnterOrExitHomePageChangeListener(IEnterOrExitHomePageChangeListener listener) {
        if (isServiceAlive()) {
            try {
                this.mService.registerEnterOrExitHomePageChangeListener(listener);
                return;
            } catch (RemoteException e) {
                e.printStackTrace();
                return;
            }
        }
        Log.d(TAG, "unRegisterWeatherWidgetListener: service is not alive");
    }

    public void unRegisterEnterOrExitHomePageChangeListener(IEnterOrExitHomePageChangeListener listener) {
        if (isServiceAlive()) {
            try {
                this.mService.unRegisterEnterOrExitHomePageChangeListener(listener);
                return;
            } catch (RemoteException e) {
                e.printStackTrace();
                return;
            }
        }
        Log.d(TAG, "unRegisterWeatherWidgetListener: service is not alive");
    }

    public void showPsdMediaControlWidget(boolean isShow) {
        if (isServiceAlive()) {
            try {
                this.mService.showPsdMediaControlWidget(isShow);
                return;
            } catch (RemoteException e) {
                e.printStackTrace();
                return;
            }
        }
        Log.d(TAG, "showPsdMediaControlWidget: service is not alive");
    }

    public boolean isWidgetsShowing(boolean isPsd) {
        if (isServiceAlive()) {
            try {
                return this.mService.isWidgetsShowing(isPsd);
            } catch (RemoteException e) {
                e.printStackTrace();
                return false;
            }
        }
        Log.d(TAG, "isWidgetsShowing: service is not alive");
        return false;
    }

    public void togglePsdWidget() {
        if (isServiceAlive()) {
            try {
                this.mService.togglePsdWidget();
                return;
            } catch (RemoteException e) {
                e.printStackTrace();
                return;
            }
        }
        Log.d(TAG, "togglePsdWidget: service is not alive");
    }

    public void registerWidgetListDisplayChangeListener(IWidgetListDisplayChangeListener listener) {
        if (isServiceAlive()) {
            try {
                this.mService.registerWidgetListDisplayChangeListener(listener);
                return;
            } catch (RemoteException e) {
                e.printStackTrace();
                return;
            }
        }
        Log.d(TAG, "registerWidgetListDisplayChangeListener: service is not alive");
    }

    public void unRegisterWidgetListDisplayChangeListener(IWidgetListDisplayChangeListener listener) {
        if (isServiceAlive()) {
            try {
                this.mService.unRegisterWidgetListDisplayChangeListener(listener);
                return;
            } catch (RemoteException e) {
                e.printStackTrace();
                return;
            }
        }
        Log.d(TAG, "unRegisterWidgetListDisplayChangeListener: service is not alive");
    }

    public void registerLauncherPageSwitchListener(ILauncherPageSwitchListener listener) {
        if (isServiceAlive()) {
            try {
                this.mService.registerLauncherPageSwitchListener(listener);
                return;
            } catch (RemoteException e) {
                e.printStackTrace();
                return;
            }
        }
        Log.d(TAG, "registerLauncherPageSwitchListener: service is not alive");
    }

    public void unRegisterLauncherPageSwitchListener(ILauncherPageSwitchListener listener) {
        if (isServiceAlive()) {
            try {
                this.mService.unRegisterLauncherPageSwitchListener(listener);
                return;
            } catch (RemoteException e) {
                e.printStackTrace();
                return;
            }
        }
        Log.d(TAG, "unRegisterLauncherPageSwitchListener: service is not alive");
    }

    public void startGlobalSearch(String keyWord) {
        if (isServiceAlive()) {
            try {
                this.mService.startGlobalSearch(keyWord);
                return;
            } catch (RemoteException e) {
                e.printStackTrace();
                return;
            }
        }
        Log.d(TAG, "startGlobalSearch: service is not alive");
    }

    public void openApplets(String appletsKey) {
        if (isServiceAlive()) {
            try {
                this.mService.openApplets(appletsKey);
                return;
            } catch (RemoteException e) {
                e.printStackTrace();
                return;
            }
        }
        Log.d(TAG, "openApplets: service is not alive");
    }

    public void closeApplets(String appletsKey) {
        if (isServiceAlive()) {
            try {
                this.mService.closeApplets(appletsKey);
                return;
            } catch (RemoteException e) {
                e.printStackTrace();
                return;
            }
        }
        Log.d(TAG, "closeApplets: service is not alive");
    }

    public void hvacMassageDisplay(boolean isShow, boolean isMain) {
        if (isServiceAlive()) {
            try {
                this.mService.hvacMassageDisplay(isShow, isMain);
                return;
            } catch (RemoteException e) {
                e.printStackTrace();
                return;
            }
        }
        Log.d(TAG, "hvacMassageDisplay: service is not alive");
    }
}