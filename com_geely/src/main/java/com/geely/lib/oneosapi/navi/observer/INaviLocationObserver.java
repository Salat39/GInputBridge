package com.geely.lib.oneosapi.navi.observer;

import com.geely.lib.oneosapi.navi.INaviLocation;

/* loaded from: classes.dex */
public interface INaviLocationObserver {
    void onCityChanged(INaviLocation iECarXLocation);

    void onDistrictChanged(INaviLocation iECarXLocation);

    void onLocation(INaviLocation iECarXLocation);
}