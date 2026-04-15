package com.geely.lib.oneosapi.navi.ipc;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class IEditFavoriteRequestBean implements Parcelable {
    public static final Parcelable.Creator<IEditFavoriteRequestBean> CREATOR = new Parcelable.Creator<IEditFavoriteRequestBean>() { // from class: com.geely.lib.oneosapi.navi.ipc.IEditFavoriteRequestBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public IEditFavoriteRequestBean createFromParcel(Parcel in) {
            return new IEditFavoriteRequestBean(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public IEditFavoriteRequestBean[] newArray(int size) {
            return new IEditFavoriteRequestBean[size];
        }
    };
    private int action;
    private Bundle extras;
    private int favoriteType;
    private IPoiInfoBean poi;
    private long requestTimeout;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public IEditFavoriteRequestBean() {
    }

    protected IEditFavoriteRequestBean(Parcel in) {
        this.favoriteType = in.readInt();
        this.action = in.readInt();
        this.poi = (IPoiInfoBean) in.readParcelable(IPoiInfoBean.class.getClassLoader());
        this.requestTimeout = in.readLong();
        this.extras = in.readBundle(Bundle.class.getClassLoader());
    }

    public void readFromParcel(Parcel in) {
        this.favoriteType = in.readInt();
        this.action = in.readInt();
        this.poi = (IPoiInfoBean) in.readParcelable(IPoiInfoBean.class.getClassLoader());
        this.requestTimeout = in.readLong();
        this.extras = in.readBundle(Bundle.class.getClassLoader());
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.favoriteType);
        dest.writeInt(this.action);
        dest.writeParcelable(this.poi, flags);
        dest.writeLong(this.requestTimeout);
        dest.writeBundle(this.extras);
    }

    public int getFavoriteType() {
        return this.favoriteType;
    }

    public void setFavoriteType(int favoriteType) {
        this.favoriteType = favoriteType;
    }

    public int getAction() {
        return this.action;
    }

    public void setAction(int action) {
        this.action = action;
    }

    public IPoiInfoBean getPoi() {
        return this.poi;
    }

    public void setPoi(IPoiInfoBean poi) {
        this.poi = poi;
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
        return "IEditFavoriteRequestBean{favoriteType=" + this.favoriteType + ", action=" + this.action + ", poi=" + this.poi + ", requestTimeout=" + this.requestTimeout + ", extras=" + this.extras + '}';
    }
}