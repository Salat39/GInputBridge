package com.geely.lib.oneosapi.navi;

import com.geely.lib.oneosapi.navi.model.base.NaviBaseModel;
import com.geely.lib.oneosapi.navi.model.base.NaviErrorModel;

/* loaded from: classes.dex */
public interface INaviAPICallback {
    void onFail(NaviErrorModel naviErrorModel);

    void onSuccess(NaviBaseModel naviBaseModel);
}