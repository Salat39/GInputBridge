package com.geely.lib.oneosapi.navi.model.base;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.SOURCE)
/* loaded from: classes.dex */
public @interface SearchSortTypes {
    public static final int DEFAULT = 0;
    public static final int FURTHEST = 2;
    public static final int HIGHEST_AVERAGE = 5;
    public static final int HIGHEST_RATING = 3;
    public static final int LOWEST_AVERAGE = 6;
    public static final int LOWEST_RATING = 4;
    public static final int NEAREST = 1;
}