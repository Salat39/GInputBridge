package com.geely.lib.oneosapi.scenemode;

import android.content.Context;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.geely.lib.oneosapi.common.ServiceBaseManager;
import com.geely.lib.oneosapi.scenemode.ISceneModeService;
import com.geely.lib.oneosapi.scenemode.ISceneModeServiceChangedListener;

//public static final int SCENE_FUNC_CAMP_MOD_SCREEN_OFF_SWITCH = 788665856;
//
//public static final int SCENE_FUNC_CSD_DRIVER_THEATER_MODE = 788664080;
//
//public static final int SCENE_FUNC_CSD_PASSENGER_THEATER_MODE = 788664096;
//
//public static final int SCENE_FUNC_NAP_MODE = 788662272;
//
//public static final int SCENE_FUNC_PSD_PASSENGER_THEATER_MODE = 788664112;
//
//public static final int SCENE_FUNC_SEAT_ADJMT_REQ = 788664320;
//
//public static final int SCENE_FUNC_SEAT_BACK_TARG_POS_AG = 788664576;
//
//public static final int SCENE_FUNC_SEAT_CUSH_EXT_TARG_POS_PERC = 788665600;
//
//public static final int SCENE_FUNC_SEAT_CUSH_TILT_TARG_POS_PERC = 788665344;
//
//public static final int SCENE_FUNC_SEAT_HEI_TARG_POS_PERC = 788665088;
//
//public static final int SCENE_FUNC_SEAT_LEN_TARG_POS_PERC = 788664832;
//
//public static final int SCENE_FUNC_WASH_MODE = 788595200;

/* loaded from: classes.dex */
public class SceneModeManager implements ServiceBaseManager {
    public static final int EXECUTE_CANT_OPEN = 3;
    public static final int EXECUTE_FAIL = 2;
    public static final int EXECUTE_NOT_SUPPORT = 4;
    public static final int EXECUTE_SUCCESS = 1;
    private static final String TAG = "SceneModeManager";
    private ISceneModeService mService;

    public SceneModeManager(Context mContext, IBinder binder) {
        this.mService = ISceneModeService.Stub.asInterface(binder);
    }

    @Override // com.geely.lib.oneosapi.common.ServiceBaseManager
    public void setService(IBinder binder) {
        this.mService = ISceneModeService.Stub.asInterface(binder);
    }

    public boolean isServiceAlive() {
        ISceneModeService iSceneModeService = this.mService;
        return iSceneModeService != null && iSceneModeService.asBinder().isBinderAlive();
    }

    public void executeModeById(String sceneId, boolean isOpen) {
        Log.d(TAG, "executeModeById() called with: sceneId = [" + sceneId + "], isOpen = [" + isOpen + "]");
        if (isServiceAlive()) {
            try {
                this.mService.executeModeById(sceneId, isOpen);
                return;
            } catch (RemoteException e) {
                e.printStackTrace();
                return;
            }
        }
        Log.d(TAG, "executeModeById(): service is not alive");
    }

    public int executeSceneModeById(String sceneId, boolean isOpen) {
        Log.d(TAG, "executeModeById() called with: sceneId = [" + sceneId + "], isOpen = [" + isOpen + "]");
        if (isServiceAlive()) {
            try {
                return this.mService.executeSceneModeById(sceneId, isOpen);
            } catch (RemoteException e) {
                e.printStackTrace();
                return 2;
            }
        }
        Log.d(TAG, "executeModeById(): service is not alive");
        return 2;
    }

    public void enterIntoModeById(String sceneId) {
        Log.d(TAG, "enterIntoModeById() called with: sceneId = [" + sceneId + "]");
        if (isServiceAlive()) {
            try {
                this.mService.enterIntoModeById(sceneId);
                return;
            } catch (RemoteException e) {
                e.printStackTrace();
                return;
            }
        }
        Log.d(TAG, "enterIntoModeById(): service is not alive");
    }

    public int getSceneModeOpenState() {
        Log.d(TAG, "getSceneModeOpenState() called");
        if (isServiceAlive()) {
            try {
                return this.mService.getSceneModeOpenState();
            } catch (RemoteException e) {
                e.printStackTrace();
                return 0;
            }
        }
        Log.d(TAG, "getSceneModeOpenState(): service is not alive");
        return 0;
    }

    public int getSceneModeFrontState() {
        Log.d(TAG, "getSceneModeFrontState() called");
        if (isServiceAlive()) {
            try {
                return this.mService.getSceneModeFrontState();
            } catch (RemoteException e) {
                e.printStackTrace();
                return 0;
            }
        }
        Log.d(TAG, "getSceneModeFrontState(): service is not alive");
        return 0;
    }

    public String getSceneModeName() {
        Log.d(TAG, "getSceneModeName() called");
        if (isServiceAlive()) {
            try {
                return this.mService.getSceneModeName();
            } catch (RemoteException e) {
                e.printStackTrace();
                return "";
            }
        }
        Log.d(TAG, "getSceneModeName(): service is not alive");
        return "";
    }

    public void registerSceneModeServiceChangedListener(SceneModeServiceChangedListener listener) {
        Log.d(TAG, "registerSceneModeServiceChangedListener(" + listener + ")");
        if (isServiceAlive()) {
            try {
                this.mService.registerSceneModeServiceChangedListener(listener);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        Log.d(TAG, "registerSceneModeServiceChangedListener：end");
    }

    public void unRegisterSceneModeServiceChangedListener(SceneModeServiceChangedListener listener) {
        Log.d(TAG, "unRegisterSceneModeServiceChangedListener(" + listener + ")");
        if (isServiceAlive()) {
            try {
                this.mService.registerSceneModeServiceChangedListener(listener);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        Log.d(TAG, "unRegisterSceneModeServiceChangedListener：end");
    }

    public static abstract class SceneModeServiceChangedListener extends ISceneModeServiceChangedListener.Stub {
        @Override // com.geely.lib.oneosapi.scenemode.ISceneModeServiceChangedListener
        public void onOpenStateChanged(int state) {
            Log.d(SceneModeManager.TAG, "onOpenStateChanged = " + state);
        }

        @Override // com.geely.lib.oneosapi.scenemode.ISceneModeServiceChangedListener
        public void onFrontStateChanged(int state) {
            Log.d(SceneModeManager.TAG, "onFrontStateChanged = " + state);
        }

        @Override // com.geely.lib.oneosapi.scenemode.ISceneModeServiceChangedListener
        public void onSceneModeNameChanged(String name) {
            Log.d(SceneModeManager.TAG, "onSceneModeNameChanged = " + name);
        }
    }
}