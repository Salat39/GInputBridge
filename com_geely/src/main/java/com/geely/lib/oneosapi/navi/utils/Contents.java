package com.geely.lib.oneosapi.navi.utils;

import android.content.Context;
import com.geely.lib.oneosapi.C0836R;
import com.geely.lib.oneosapi.navi.model.base.MapVendor;

/* loaded from: classes.dex */
public class Contents {
    public static final String AMAP_PACKAGE = "com.autonavi.amapauto";
    public static final String BAIDU_PACKAGE = "com.baidu.naviauto";

    public static int mapPackage2Vendor(String mapPackage) {
        if (BAIDU_PACKAGE.equals(mapPackage)) {
            return 1;
        }
        if (AMAP_PACKAGE.equals(mapPackage)) {
            return 0;
        }
        return MapVendor.NO_MAP_RUNNING;
    }

    public static String getSetDefaultMapTitle(int mapVendor, Context mContext) {
        if (mapVendor == 0) {
            return mContext.getResources().getString(C0836R.string.set_default_map_auto);
        }
        return mContext.getResources().getString(C0836R.string.set_default_map_baidu);
    }

    public static String getSetDefaultMapContent(int mapVendor, Context mContext) {
        if (mapVendor == 0) {
            return mContext.getResources().getString(C0836R.string.close_baidu_map);
        }
        return mContext.getResources().getString(C0836R.string.close_auto_map);
    }

    public static String getOpenNewMapTitle(int mapVendor, Context mContext, int defaultMap) {
        if (mapVendor == 0) {
            return mContext.getResources().getString(C0836R.string.open_auto_map);
        }
        if (mapVendor == 0) {
            return mContext.getResources().getString(C0836R.string.open_baidu_map);
        }
        if (defaultMap == 0) {
            return mContext.getResources().getString(C0836R.string.open_auto_map);
        }
        if (1 == defaultMap) {
            return mContext.getResources().getString(C0836R.string.open_baidu_map);
        }
        return mContext.getResources().getString(C0836R.string.open_auto_map);
    }
}