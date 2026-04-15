package com.geely.lib.oneosapi.navi.observer;

import com.geely.lib.oneosapi.navi.entitys.IGuideInfo;
import com.geely.lib.oneosapi.navi.entitys.ILocationRoadInfo;
import com.geely.lib.oneosapi.navi.entitys.RoadConditions;

/* loaded from: classes.dex */
public interface IGuideInfoObserver {
    void onGuideInfo(IGuideInfo iGuideInfo);

    void onLocationRoadInfo(ILocationRoadInfo iLocationRoadInfo);

    void onRoadConditions(RoadConditions roadConditions);
}