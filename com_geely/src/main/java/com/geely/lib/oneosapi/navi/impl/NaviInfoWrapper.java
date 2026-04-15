package com.geely.lib.oneosapi.navi.impl;

import android.os.RemoteException;
import com.geely.lib.oneosapi.navi.INaviInfo;

/* loaded from: classes.dex */
public class NaviInfoWrapper extends INaviInfo.Stub {
    public static final String TAG = NaviInfoWrapper.class.getSimpleName();
    private INaviInfo m_naviInfo;

    NaviInfoWrapper(INaviInfo naviInfo) {
        this.m_naviInfo = naviInfo;
    }

    @Override // com.geely.lib.oneosapi.navi.INaviInfo
    public String getMessageID() throws RemoteException {
        INaviInfo iNaviInfo = this.m_naviInfo;
        if (iNaviInfo != null) {
            return iNaviInfo.getMessageID();
        }
        return null;
    }

    @Override // com.geely.lib.oneosapi.navi.INaviInfo
    public String getSender() throws RemoteException {
        INaviInfo iNaviInfo = this.m_naviInfo;
        if (iNaviInfo != null) {
            return iNaviInfo.getSender();
        }
        return null;
    }

    @Override // com.geely.lib.oneosapi.navi.INaviInfo
    public String getTitle() throws RemoteException {
        INaviInfo iNaviInfo = this.m_naviInfo;
        if (iNaviInfo != null) {
            return iNaviInfo.getTitle();
        }
        return null;
    }

    @Override // com.geely.lib.oneosapi.navi.INaviInfo
    public double getLatitude() throws RemoteException {
        INaviInfo iNaviInfo = this.m_naviInfo;
        if (iNaviInfo != null) {
            return iNaviInfo.getLatitude();
        }
        return 0.0d;
    }

    @Override // com.geely.lib.oneosapi.navi.INaviInfo
    public double getLongitude() throws RemoteException {
        INaviInfo iNaviInfo = this.m_naviInfo;
        if (iNaviInfo != null) {
            return iNaviInfo.getLongitude();
        }
        return 0.0d;
    }
}