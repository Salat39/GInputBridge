package com.geely.lib.oneosapi.navi.base;

import com.geely.lib.oneosapi.navi.base.log.ILog;
import com.geely.lib.oneosapi.navi.base.log.LogProxy;

/* loaded from: classes.dex */
public class NaviAPIBase {
    protected LogProxy log = new LogProxy();

    public void setLogEnable(boolean enable) {
        this.log.setLogEnable(enable);
    }

    public void setLogLevel(int level) {
        this.log.setLogLevel(level);
    }

    public void setLogImpl(ILog logImpl) {
        this.log.setLogImpl(logImpl);
    }
}