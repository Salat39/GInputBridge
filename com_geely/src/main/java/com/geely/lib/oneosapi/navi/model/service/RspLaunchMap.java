package com.geely.lib.oneosapi.navi.model.service;

import android.os.Parcelable;
import com.geely.lib.oneosapi.navi.model.base.NaviBaseModel;

/* loaded from: classes.dex */
public class RspLaunchMap extends NaviBaseModel implements Parcelable {
    private int mapLaunchedType;

    public int getMapLaunchedType() {
        return this.mapLaunchedType;
    }

    public void setMapLaunchedType(int mapLaunchedType) {
        this.mapLaunchedType = mapLaunchedType;
    }
}