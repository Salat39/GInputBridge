package com.geely.lib.oneosapi.mediacenter.listener;

import com.geely.lib.oneosapi.mediacenter.bean.DeviceInfo;
import com.geely.lib.oneosapi.mediacenter.bean.MusicFileData;
import com.geely.lib.oneosapi.mediacenter.bean.OnlineUserInfo;
import com.geely.lib.oneosapi.mediacenter.bean.SearchResult;
import com.geely.lib.oneosapi.mediacenter.constant.MediaCenterConstant;
import java.util.List;

/* loaded from: classes.dex */
public interface DeviceStateListener {
    void onAppDied(MediaCenterConstant.AppSource appSource);

    void onAppExistStateChanged(MediaCenterConstant.AudioSource audioSourceEnum, MediaCenterConstant.AppSource appSourceEnum, boolean existed);

    void onBluetoothDeviceChange(MediaCenterConstant.AudioSource source, List<DeviceInfo> deviceInfoList);

    void onDeviceError(MediaCenterConstant.AudioSource source, int error, String errorMsg);

    void onDeviceStateChanged(MediaCenterConstant.AudioSource source, MediaCenterConstant.DeviceState state, DeviceInfo info);

    void onScanPathFinish(MediaCenterConstant.AudioSource source, List<MusicFileData> musicFileDataList);

    @Deprecated
    void onSearchSongResult(MediaCenterConstant.AudioSource source, MediaCenterConstant.AppSource appSource, List<SearchResult> searchResults);

    @Deprecated
    void onUserInfoResult(MediaCenterConstant.AudioSource source, MediaCenterConstant.AppSource appSource, OnlineUserInfo userInfo);
}