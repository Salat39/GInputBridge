package com.geely.lib.oneosapi.navi.observer;

import com.geely.lib.oneosapi.navi.INaviLocation;

/* loaded from: classes.dex */
public abstract class NaviLocationObserver implements INaviLocationObserver {
    @Override // com.geely.lib.oneosapi.navi.observer.INaviLocationObserver
    public void onCityChanged(INaviLocation location) {
    }

    @Override // com.geely.lib.oneosapi.navi.observer.INaviLocationObserver
    public void onDistrictChanged(INaviLocation location) {
    }

    @Override // com.geely.lib.oneosapi.navi.observer.INaviLocationObserver
    public void onLocation(INaviLocation location) {
    }
}