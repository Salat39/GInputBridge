package com.geely.lib.oneosapi.mediacenter.listener;

import com.geely.lib.oneosapi.mediacenter.bean.DeviceInfo;
import com.geely.lib.oneosapi.mediacenter.bean.OnlineUserInfo;
import com.geely.lib.oneosapi.mediacenter.bean.SearchResult;
import com.geely.lib.oneosapi.mediacenter.constant.MediaCenterConstant;
import java.util.List;

/* loaded from: classes.dex */
public abstract class UsbDeviceStateListener implements DeviceStateListener {
    @Override // com.geely.lib.oneosapi.mediacenter.listener.DeviceStateListener
    public final void onAppDied(MediaCenterConstant.AppSource appSource) {
    }

    @Override // com.geely.lib.oneosapi.mediacenter.listener.DeviceStateListener
    public final void onAppExistStateChanged(MediaCenterConstant.AudioSource audioSourceEnum, MediaCenterConstant.AppSource appSourceEnum, boolean existed) {
    }

    @Override // com.geely.lib.oneosapi.mediacenter.listener.DeviceStateListener
    public final void onBluetoothDeviceChange(MediaCenterConstant.AudioSource source, List<DeviceInfo> deviceInfoList) {
    }

    @Override // com.geely.lib.oneosapi.mediacenter.listener.DeviceStateListener
    public final void onSearchSongResult(MediaCenterConstant.AudioSource source, MediaCenterConstant.AppSource appSource, List<SearchResult> searchResults) {
    }

    @Override // com.geely.lib.oneosapi.mediacenter.listener.DeviceStateListener
    public final void onUserInfoResult(MediaCenterConstant.AudioSource source, MediaCenterConstant.AppSource appSource, OnlineUserInfo userInfo) {
    }
}