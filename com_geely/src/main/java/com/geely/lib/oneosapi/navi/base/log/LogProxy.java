package com.geely.lib.oneosapi.navi.base.log;

import android.util.Log;

/* loaded from: classes.dex */
public final class LogProxy {
    private boolean enable = false;
    private int level;
    private ILog logImpl;

    public void setLogEnable(boolean enable) {
        this.enable = enable;
    }

    public void setLogLevel(int level) {
        this.level = level;
    }

    public void setLogImpl(ILog logImpl) {
        this.logImpl = logImpl;
    }

    /* renamed from: v */
    public void m22v(String tag, String msg) {
        if (!this.enable || this.level > 2) {
            return;
        }
        ILog iLog = this.logImpl;
        if (iLog == null) {
            Log.v(tag, msg);
        } else {
            iLog.m14v(tag, msg);
        }
    }

    /* renamed from: v */
    public void m23v(String tag, String msg, Throwable tr) {
        if (!this.enable || this.level > 2) {
            return;
        }
        ILog iLog = this.logImpl;
        if (iLog == null) {
            Log.v(tag, msg, tr);
            return;
        }
        iLog.m14v(tag, msg + '\n' + Log.getStackTraceString(tr));
    }

    /* renamed from: d */
    public void m16d(String tag, String msg) {
        if (!this.enable || this.level > 3) {
            return;
        }
        ILog iLog = this.logImpl;
        if (iLog == null) {
            Log.d(tag, msg);
        } else {
            iLog.m11d(tag, msg);
        }
    }

    /* renamed from: d */
    public void m17d(String tag, String msg, Throwable tr) {
        if (!this.enable || this.level > 3) {
            return;
        }
        ILog iLog = this.logImpl;
        if (iLog == null) {
            Log.d(tag, msg, tr);
            return;
        }
        iLog.m11d(tag, msg + '\n' + Log.getStackTraceString(tr));
    }

    /* renamed from: i */
    public void m20i(String tag, String msg) {
        if (!this.enable || this.level > 4) {
            return;
        }
        ILog iLog = this.logImpl;
        if (iLog == null) {
            Log.i(tag, msg);
        } else {
            iLog.m13i(tag, msg);
        }
    }

    /* renamed from: i */
    public void m21i(String tag, String msg, Throwable tr) {
        if (!this.enable || this.level > 4) {
            return;
        }
        ILog iLog = this.logImpl;
        if (iLog == null) {
            Log.i(tag, msg, tr);
            return;
        }
        iLog.m13i(tag, msg + '\n' + Log.getStackTraceString(tr));
    }

    /* renamed from: w */
    public void m24w(String tag, String msg) {
        if (!this.enable || this.level > 5) {
            return;
        }
        ILog iLog = this.logImpl;
        if (iLog == null) {
            Log.w(tag, msg);
        } else {
            iLog.m15w(tag, msg);
        }
    }

    /* renamed from: w */
    public void m25w(String tag, String msg, Throwable tr) {
        if (!this.enable || this.level > 5) {
            return;
        }
        ILog iLog = this.logImpl;
        if (iLog == null) {
            Log.w(tag, msg, tr);
            return;
        }
        iLog.m15w(tag, msg + '\n' + Log.getStackTraceString(tr));
    }

    /* renamed from: e */
    public void m18e(String tag, String msg) {
        if (!this.enable || this.level > 6) {
            return;
        }
        ILog iLog = this.logImpl;
        if (iLog == null) {
            Log.e(tag, msg);
        } else {
            iLog.m12e(tag, msg);
        }
    }

    /* renamed from: e */
    public void m19e(String tag, String msg, Throwable tr) {
        if (!this.enable || this.level > 6) {
            return;
        }
        ILog iLog = this.logImpl;
        if (iLog == null) {
            Log.e(tag, msg, tr);
            return;
        }
        iLog.m12e(tag, msg + '\n' + Log.getStackTraceString(tr));
    }
}