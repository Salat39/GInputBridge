package com.geely.lib.oneosapi.navi.ipc;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class ISetMapFeaturesRunningStateBean implements Parcelable {
    public static final Parcelable.Creator<ISetMapFeaturesRunningStateBean> CREATOR = new Parcelable.Creator<ISetMapFeaturesRunningStateBean>() { // from class: com.geely.lib.oneosapi.navi.ipc.ISetMapFeaturesRunningStateBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ISetMapFeaturesRunningStateBean createFromParcel(Parcel in) {
            return new ISetMapFeaturesRunningStateBean(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ISetMapFeaturesRunningStateBean[] newArray(int size) {
            return new ISetMapFeaturesRunningStateBean[size];
        }
    };
    private Bundle extras;
    private int mapFeaturesCanRun;
    private long requestTimeout;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public ISetMapFeaturesRunningStateBean() {
    }

    protected ISetMapFeaturesRunningStateBean(Parcel in) {
        this.mapFeaturesCanRun = in.readInt();
        this.requestTimeout = in.readLong();
        this.extras = in.readBundle(Bundle.class.getClassLoader());
    }

    public void readFromParcel(Parcel in) {
        this.mapFeaturesCanRun = in.readInt();
        this.requestTimeout = in.readLong();
        this.extras = in.readBundle(Bundle.class.getClassLoader());
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mapFeaturesCanRun);
        dest.writeLong(this.requestTimeout);
        dest.writeBundle(this.extras);
    }

    public int getMapFeaturesCanRun() {
        return this.mapFeaturesCanRun;
    }

    public void setMapFeaturesCanRun(int mapFeaturesCanRun) {
        this.mapFeaturesCanRun = mapFeaturesCanRun;
    }

    public long getRequestTimeout() {
        return this.requestTimeout;
    }

    public void setRequestTimeout(long requestTimeout) {
        this.requestTimeout = requestTimeout;
    }

    public Bundle getExtras() {
        return this.extras;
    }

    public void setExtras(Bundle extras) {
        this.extras = extras;
    }

    public String toString() {
        return "ISetMapFeaturesRunningStateBean{mapFeaturesCanRun=" + this.mapFeaturesCanRun + ", requestTimeout=" + this.requestTimeout + ", extras=" + this.extras + '}';
    }
}