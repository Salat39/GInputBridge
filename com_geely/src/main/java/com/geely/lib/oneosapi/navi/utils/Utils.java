package com.geely.lib.oneosapi.navi.utils;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.text.TextUtils;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/* loaded from: classes.dex */
public class Utils {
    public static int string2int(String string, int def) {
        if (TextUtils.isEmpty(string)) {
            return def;
        }
        try {
            return Integer.valueOf(string).intValue();
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return def;
        }
    }

    public static double string2double(String string, double def) {
        if (TextUtils.isEmpty(string)) {
            return def;
        }
        try {
            return Double.valueOf(string).doubleValue();
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return def;
        }
    }

    public static boolean string2boolean(String string, boolean def) {
        if (TextUtils.isEmpty(string)) {
            return def;
        }
        try {
            return Boolean.valueOf(string.toLowerCase()).booleanValue();
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return def;
        }
    }

    public static String md5(String string) {
        if (TextUtils.isEmpty(string)) {
            return "";
        }
        try {
            byte[] digest = MessageDigest.getInstance("MD5").digest(string.getBytes("utf-8"));
            StringBuilder sb = new StringBuilder();
            return sb.toString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        } catch (NoSuchAlgorithmException e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static Intent createExplicitFromImplicitIntent(Context context, Intent implicitIntent) {
        List<ResolveInfo> queryIntentServices = context.getPackageManager().queryIntentServices(implicitIntent, 0);
        if (queryIntentServices == null || queryIntentServices.size() != 1) {
            return null;
        }
        ResolveInfo resolveInfo = queryIntentServices.get(0);
        ComponentName componentName = new ComponentName(resolveInfo.serviceInfo.packageName, resolveInfo.serviceInfo.name);
        Intent intent = new Intent(implicitIntent);
        intent.setComponent(componentName);
        return intent;
    }
}