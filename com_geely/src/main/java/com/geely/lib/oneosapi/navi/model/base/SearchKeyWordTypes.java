package com.geely.lib.oneosapi.navi.model.base;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.SOURCE)
/* loaded from: classes.dex */
public @interface SearchKeyWordTypes {
    public static final int DEFAULT = 0;
    public static final int FOOD = 1;
    public static final int HOTEL = 2;
    public static final int SHOP = 3;
}