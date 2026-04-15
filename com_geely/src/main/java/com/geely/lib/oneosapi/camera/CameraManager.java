package com.geely.lib.oneosapi.camera;

import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.geely.lib.oneosapi.camera.ICameraInterface;
import com.geely.lib.oneosapi.common.ServiceBaseManager;
import com.geely.lib.oneosapi.phone.inter.IBluetoothService;

/* loaded from: classes.dex */
public class CameraManager implements ServiceBaseManager {
    private static final String TAG = CameraManager.class.getSimpleName();
    private ICameraInterface mService;

    public CameraManager(IBinder binder) {
        initVrManger(binder);
    }

    @Override // com.geely.lib.oneosapi.common.ServiceBaseManager
    public void setService(IBinder binder) {
        initVrManger(binder);
    }

    public boolean isAlive() {
        ICameraInterface iCameraInterface = this.mService;
        return iCameraInterface != null && iCameraInterface.asBinder().isBinderAlive();
    }

    private void initVrManger(IBinder binder) {
        if (binder != null) {
            this.mService = ICameraInterface.Stub.asInterface(binder);
        }
    }

    public void openCamera() {
        ICameraInterface iCameraInterface = this.mService;
        if (iCameraInterface != null) {
            try {
                iCameraInterface.openCamera();
                return;
            } catch (RemoteException e) {
                e.printStackTrace();
                return;
            }
        }
        serviceIsNull();
    }

    private void serviceIsNull() {
        Log.d(TAG, "VrServiceIsNull");
    }

    public void closeCamera() {
        ICameraInterface iCameraInterface = this.mService;
        if (iCameraInterface != null) {
            try {
                iCameraInterface.closeCamera();
                return;
            } catch (RemoteException e) {
                e.printStackTrace();
                return;
            }
        }
        serviceIsNull();
    }

    public void onTakePhoto() {
        ICameraInterface iCameraInterface = this.mService;
        if (iCameraInterface != null) {
            try {
                iCameraInterface.onTakePhoto();
                return;
            } catch (RemoteException e) {
                e.printStackTrace();
                return;
            }
        }
        serviceIsNull();
    }

    public void onTakeVideo() {
        ICameraInterface iCameraInterface = this.mService;
        if (iCameraInterface != null) {
            try {
                iCameraInterface.onTakeVideo();
                return;
            } catch (RemoteException e) {
                e.printStackTrace();
                return;
            }
        }
        serviceIsNull();
    }

    public void onTakeTimeTakenPhoto() {
        ICameraInterface iCameraInterface = this.mService;
        if (iCameraInterface != null) {
            try {
                iCameraInterface.onTakeTimeTakenPhoto();
                return;
            } catch (RemoteException e) {
                e.printStackTrace();
                return;
            }
        }
        serviceIsNull();
    }

    public void onTakeTimeLapseRecording() {
        ICameraInterface iCameraInterface = this.mService;
        if (iCameraInterface != null) {
            try {
                iCameraInterface.onTakeTimeLapseRecording();
                return;
            } catch (RemoteException e) {
                e.printStackTrace();
                return;
            }
        }
        serviceIsNull();
    }

    public void onVideoMute() {
        ICameraInterface iCameraInterface = this.mService;
        if (iCameraInterface != null) {
            try {
                iCameraInterface.onVideoMute();
                return;
            } catch (RemoteException e) {
                e.printStackTrace();
                return;
            }
        }
        serviceIsNull();
    }

    public void onVideoUnMute() {
        ICameraInterface iCameraInterface = this.mService;
        if (iCameraInterface != null) {
            try {
                iCameraInterface.onVideoUnMute();
                return;
            } catch (RemoteException e) {
                e.printStackTrace();
                return;
            }
        }
        serviceIsNull();
    }

    public void onTakeInnerPhoto() {
        ICameraInterface iCameraInterface = this.mService;
        if (iCameraInterface != null) {
            try {
                iCameraInterface.onTakeInnerPhoto();
                return;
            } catch (RemoteException e) {
                e.printStackTrace();
                return;
            }
        }
        serviceIsNull();
    }

    public void onTakeOuterPhoto() {
        ICameraInterface iCameraInterface = this.mService;
        if (iCameraInterface != null) {
            try {
                iCameraInterface.onTakeOuterPhoto();
                return;
            } catch (RemoteException e) {
                e.printStackTrace();
                return;
            }
        }
        serviceIsNull();
    }

    public void onTakeInnerVideo() {
        ICameraInterface iCameraInterface = this.mService;
        if (iCameraInterface != null) {
            try {
                iCameraInterface.onTakeInnerVideo();
                return;
            } catch (RemoteException e) {
                e.printStackTrace();
                return;
            }
        }
        serviceIsNull();
    }

    public void onTakeOuterVideo() {
        ICameraInterface iCameraInterface = this.mService;
        if (iCameraInterface != null) {
            try {
                iCameraInterface.onTakeOuterVideo();
                return;
            } catch (RemoteException e) {
                e.printStackTrace();
                return;
            }
        }
        serviceIsNull();
    }

    public void onStopVideoRecord() {
        ICameraInterface iCameraInterface = this.mService;
        if (iCameraInterface != null) {
            try {
                iCameraInterface.onStopVideoRecord();
                return;
            } catch (RemoteException e) {
                e.printStackTrace();
                return;
            }
        }
        serviceIsNull();
    }

    public void onTakeStart() {
        ICameraInterface iCameraInterface = this.mService;
        if (iCameraInterface != null) {
            try {
                iCameraInterface.onTakeStart();
                return;
            } catch (RemoteException e) {
                e.printStackTrace();
                return;
            }
        }
        serviceIsNull();
    }

    public void onTakePhotoAgain() {
        ICameraInterface iCameraInterface = this.mService;
        if (iCameraInterface != null) {
            try {
                iCameraInterface.onTakePhotoAgain();
                return;
            } catch (RemoteException e) {
                e.printStackTrace();
                return;
            }
        }
        serviceIsNull();
    }

    public boolean onTakePhoto(int zone, int delayTime) {
        ICameraInterface iCameraInterface = this.mService;
        if (iCameraInterface != null) {
            try {
                return iCameraInterface.onTakePhotoWithParams(zone, delayTime);
            } catch (RemoteException e) {
                e.printStackTrace();
                return false;
            }
        }
        serviceIsNull();
        return false;
    }

    public boolean onTakeVideo(int open, int zone, int delayTime) {
        ICameraInterface iCameraInterface = this.mService;
        if (iCameraInterface != null) {
            try {
                return iCameraInterface.onTakeVideoToggle(open, zone, delayTime);
            } catch (RemoteException e) {
                e.printStackTrace();
                return false;
            }
        }
        serviceIsNull();
        return false;
    }

    public boolean onSetVideoMute(int mute) {
        ICameraInterface iCameraInterface = this.mService;
        if (iCameraInterface != null) {
            try {
                return iCameraInterface.onSetVideoVolume(mute);
            } catch (RemoteException e) {
                e.printStackTrace();
                return false;
            }
        }
        serviceIsNull();
        return false;
    }

    public boolean isFunctionSupported(int function, int zone) {
        ICameraInterface iCameraInterface = this.mService;
        if (iCameraInterface != null) {
            try {
                return iCameraInterface.isFunctionSupported(function, zone);
            } catch (RemoteException e) {
                e.printStackTrace();
                return false;
            }
        }
        serviceIsNull();
        return false;
    }

    public int getCameraState() {
        ICameraInterface iCameraInterface = this.mService;
        if (iCameraInterface != null) {
            try {
                return iCameraInterface.getCameraState();
            } catch (RemoteException e) {
                e.printStackTrace();
                return -100;
            }
        }
        serviceIsNull();
        return -100;
    }
}