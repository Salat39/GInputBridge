package com.geely.lib.oneosapi.navi.ipc;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

/* loaded from: classes.dex */
public class IFavoritesInfoBean implements Parcelable {
    public static final Parcelable.Creator<IFavoritesInfoBean> CREATOR = new Parcelable.Creator<IFavoritesInfoBean>() { // from class: com.geely.lib.oneosapi.navi.ipc.IFavoritesInfoBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public IFavoritesInfoBean createFromParcel(Parcel in) {
            return new IFavoritesInfoBean(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public IFavoritesInfoBean[] newArray(int size) {
            return new IFavoritesInfoBean[size];
        }
    };
    private Bundle extras;
    private List<IFavoritePoiBean> favoritePois;
    private int resultCode;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public IFavoritesInfoBean() {
    }

    protected IFavoritesInfoBean(Parcel in) {
        this.favoritePois = in.createTypedArrayList(IFavoritePoiBean.CREATOR);
        this.resultCode = in.readInt();
        this.extras = in.readBundle(Bundle.class.getClassLoader());
    }

    public void readFromParcel(Parcel in) {
        this.favoritePois = in.createTypedArrayList(IFavoritePoiBean.CREATOR);
        this.resultCode = in.readInt();
        this.extras = in.readBundle(Bundle.class.getClassLoader());
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.favoritePois);
        dest.writeInt(this.resultCode);
        dest.writeBundle(this.extras);
    }

    public List<IFavoritePoiBean> getFavoritePois() {
        return this.favoritePois;
    }

    public void setFavoritePois(List<IFavoritePoiBean> favoritePois) {
        this.favoritePois = favoritePois;
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
        return "IFavoritesInfoBean{favoritePois=" + this.favoritePois + ", resultCode=" + this.resultCode + ", extras=" + this.extras + '}';
    }
}