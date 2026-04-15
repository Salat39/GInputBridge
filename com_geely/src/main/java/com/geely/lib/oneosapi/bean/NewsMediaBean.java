package com.geely.lib.oneosapi.bean;

import android.os.Parcelable;

/* loaded from: classes.dex */
public class NewsMediaBean extends BaseMediaBean implements Parcelable {
    String publishTime;

    public String getPublishTime() {
        return this.publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }
}