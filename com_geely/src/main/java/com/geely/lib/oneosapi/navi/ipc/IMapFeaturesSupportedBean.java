package com.geely.lib.oneosapi.navi.ipc;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class IMapFeaturesSupportedBean implements Parcelable {
    public static final Parcelable.Creator<IMapFeaturesSupportedBean> CREATOR = new Parcelable.Creator<IMapFeaturesSupportedBean>() { // from class: com.geely.lib.oneosapi.navi.ipc.IMapFeaturesSupportedBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public IMapFeaturesSupportedBean createFromParcel(Parcel in) {
            return new IMapFeaturesSupportedBean(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public IMapFeaturesSupportedBean[] newArray(int size) {
            return new IMapFeaturesSupportedBean[size];
        }
    };
    private Bundle extras;
    private int mapFeatures;
    private int resultCode;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public IMapFeaturesSupportedBean() {
    }

    protected IMapFeaturesSupportedBean(Parcel in) {
        this.mapFeatures = in.readInt();
        this.resultCode = in.readInt();
        this.extras = in.readBundle(Bundle.class.getClassLoader());
    }

    public void readFromParcel(Parcel in) {
        this.mapFeatures = in.readInt();
        this.resultCode = in.readInt();
        this.extras = in.readBundle(Bundle.class.getClassLoader());
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mapFeatures);
        dest.writeInt(this.resultCode);
        dest.writeBundle(this.extras);
    }

    public int getMapFeatures() {
        return this.mapFeatures;
    }

    public void setMapFeatures(int mapFeatures) {
        this.mapFeatures = mapFeatures;
    }

    public int getResultCode() {
        return this.resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public Bundle getExtras() {
        return this.extras;
    }

    public void setExtras(Bundle extras) {
        this.extras = extras;
    }

    public String toString() {
        return "IMapFeaturesSupportedBean{mapFeatures=" + this.mapFeatures + ", resultCode=" + this.resultCode + ", extras=" + this.extras + '}';
    }
}