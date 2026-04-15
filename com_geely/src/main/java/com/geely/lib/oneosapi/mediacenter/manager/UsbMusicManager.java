package com.geely.lib.oneosapi.mediacenter.manager;

import android.content.Context;
import android.os.RemoteException;
import com.geely.lib.oneosapi.mediacenter.IMusicManager;
import com.geely.lib.oneosapi.mediacenter.MediaCenterManager;
import com.geely.lib.oneosapi.mediacenter.base.BaseMusicManager;
import com.geely.lib.oneosapi.mediacenter.bean.MediaData;
import com.geely.lib.oneosapi.mediacenter.bean.MusicFileData;
import com.geely.lib.oneosapi.mediacenter.constant.MediaCenterConstant;
import com.geely.lib.oneosapi.mediacenter.listener.QueryUsbMediaListener;
import com.geely.lib.oneosapi.mediacenter.listener.UsbDeviceStateListener;
import java.util.List;

/* loaded from: classes.dex */
public class UsbMusicManager extends BaseMusicManager {
    private final int mAudioSource;

    public UsbMusicManager(Context context, IMusicManager service, MediaCenterManager mediaCenterManager) {
        super(context, service, mediaCenterManager);
        this.mAudioSource = MediaCenterConstant.AudioSource.AUDIO_SOURCE_USB.ordinal();
    }

    @Override // com.geely.lib.oneosapi.mediacenter.base.BaseMusicManager
    public int getAudioSource() {
        return this.mAudioSource;
    }

    public void addUsbDeviceStateListener(UsbDeviceStateListener listener) {
        addDeviceStateListener(listener);
    }

    public void addQueryUsbMediaListener(QueryUsbMediaListener listener) {
        addUsbMediaQueryListener(listener);
    }

    public void startScanPath(String path) {
        if (this.mService != null) {
            try {
                this.mService.startScanPath(getAudioSource(), path);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public List<MusicFileData> getCurrentPathFiles() {
        if (this.mService == null) {
            return null;
        }
        try {
            return this.mService.getCurrentPathFiles(getAudioSource());
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void playMusicFile(MusicFileData musicFileData) {
        if (musicFileData != null) {
            MediaData mediaData = new MediaData();
            mediaData.uri = musicFileData.absolutePath;
            mediaData.name = musicFileData.name;
            mediaData.duration = musicFileData.duration;
            playItem(mediaData);
        }
    }

    public void requestAudioSource() {
        this.mMediaCenterManager.requestAudioSource(MediaCenterConstant.AudioSource.AUDIO_SOURCE_USB);
    }
}