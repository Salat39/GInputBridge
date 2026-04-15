package com.geely.lib.oneosapi.mediacenter.listener;

import com.geely.lib.oneosapi.mediacenter.bean.DeviceInfo;
import com.geely.lib.oneosapi.mediacenter.bean.MusicFileData;
import com.geely.lib.oneosapi.mediacenter.constant.MediaCenterConstant;
import java.util.List;

/* loaded from: classes.dex */
public abstract class OnlineDeviceStateListener implements DeviceStateListener {
    @Override // com.geely.lib.oneosapi.mediacenter.listener.DeviceStateListener
    public final void onBluetoothDeviceChange(MediaCenterConstant.AudioSource source, List<DeviceInfo> deviceInfoList) {
    }

    @Override // com.geely.lib.oneosapi.mediacenter.listener.DeviceStateListener
    public final void onScanPathFinish(MediaCenterConstant.AudioSource source, List<MusicFileData> musicFileDataList) {
    }
}