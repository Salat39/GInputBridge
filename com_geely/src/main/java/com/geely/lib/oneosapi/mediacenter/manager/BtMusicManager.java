package com.geely.lib.oneosapi.mediacenter.manager;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.os.RemoteException;
import com.geely.lib.oneosapi.mediacenter.IMusicManager;
import com.geely.lib.oneosapi.mediacenter.MediaCenterManager;
import com.geely.lib.oneosapi.mediacenter.base.BaseMusicManager;
import com.geely.lib.oneosapi.mediacenter.constant.MediaCenterConstant;
import com.geely.lib.oneosapi.mediacenter.listener.BtDeviceStateListener;

/* loaded from: classes.dex */
public class BtMusicManager extends BaseMusicManager {
    private final int mAudioSource;

    public BtMusicManager(Context context, IMusicManager service, MediaCenterManager mediaCenterManager) {
        super(context, service, mediaCenterManager);
        this.mAudioSource = MediaCenterConstant.AudioSource.AUDIO_SOURCE_BT.ordinal();
    }

    @Override // com.geely.lib.oneosapi.mediacenter.base.BaseMusicManager
    public int getAudioSource() {
        return this.mAudioSource;
    }

    public void addBtDeviceStateListener(BtDeviceStateListener listener) {
        addDeviceStateListener(listener);
    }

    public void connectBtDevice(String address) {
        if (this.mService != null) {
            try {
                this.mService.connectBtDevice(getAudioSource(), address);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void setActiveBtDevice(String address) {
        if (this.mService != null) {
            try {
                this.mService.setActiveBtDevice(getAudioSource(), address);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void requestAudioSource() {
        this.mMediaCenterManager.requestAudioSource(MediaCenterConstant.AudioSource.AUDIO_SOURCE_BT);
    }

    public BluetoothDevice getConnectedDevice() {
        if (this.mService == null) {
            return null;
        }
        try {
            return this.mService.getConnectedDevice(getAudioSource());
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean getBtState() {
        if (this.mService == null) {
            return false;
        }
        try {
            return this.mService.getBtState(getAudioSource());
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }
}