package com.geely.lib.oneosapi.navi.observer;

import com.geely.lib.oneosapi.navi.entitys.IGuideInfo;
import com.geely.lib.oneosapi.navi.entitys.ILocationRoadInfo;
import com.geely.lib.oneosapi.navi.entitys.RoadConditions;

/* loaded from: classes.dex */
public abstract class GuideInfoObserver implements IGuideInfoObserver {
    @Override // com.geely.lib.oneosapi.navi.observer.IGuideInfoObserver
    public void onGuideInfo(IGuideInfo info) {
    }

    @Override // com.geely.lib.oneosapi.navi.observer.IGuideInfoObserver
    public void onLocationRoadInfo(ILocationRoadInfo info) {
    }

    @Override // com.geely.lib.oneosapi.navi.observer.IGuideInfoObserver
    public void onRoadConditions(RoadConditions roadConditions) {
    }
}