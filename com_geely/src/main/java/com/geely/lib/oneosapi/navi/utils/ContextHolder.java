package com.geely.lib.oneosapi.navi.utils;

import android.content.Context;

/* loaded from: classes.dex */
public class ContextHolder {
    private static Context mContext;

    public static Context getContext() {
        Context context = mContext;
        if (context != null) {
            return context;
        }
        throw new RuntimeException("ContextHolder Not Init Yet!");
    }

    public static void setContext(Context context) {
        mContext = context;
    }
}