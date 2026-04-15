package com.geely.lib.oneosapi.navi.model.base;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.SOURCE)
/* loaded from: classes.dex */
public @interface MapFeatures {
    public static final int DR = 1;
    public static final int GREEN_TRAVEL = 32;
    public static final int GUIDE = 2;
    public static final int SPEED_LIMIT = 16;
    public static final int V2DIM = 4;
    public static final int V2WIDGET = 8;
}