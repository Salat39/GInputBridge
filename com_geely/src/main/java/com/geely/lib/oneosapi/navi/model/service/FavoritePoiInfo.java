package com.geely.lib.oneosapi.navi.model.service;

import android.os.Parcel;
import android.os.Parcelable;
import com.geely.lib.oneosapi.navi.model.base.PoiInfo;

/* loaded from: classes.dex */
public class FavoritePoiInfo implements Parcelable {
    public static final Parcelable.Creator<FavoritePoiInfo> CREATOR = new Parcelable.Creator<FavoritePoiInfo>() { // from class: com.geely.lib.oneosapi.navi.model.service.FavoritePoiInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public FavoritePoiInfo createFromParcel(Parcel source) {
            return new FavoritePoiInfo(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public FavoritePoiInfo[] newArray(int size) {
            return new FavoritePoiInfo[size];
        }
    };
    private int favoriteType;
    private PoiInfo poiInfo;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public FavoritePoiInfo() {
    }

    public int getFavoriteType() {
        return this.favoriteType;
    }

    public void setFavoriteType(int favoriteType) {
        this.favoriteType = favoriteType;
    }

    public PoiInfo getPoiInfo() {
        return this.poiInfo;
    }

    public void setPoiInfo(PoiInfo poiInfo) {
        this.poiInfo = poiInfo;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.favoriteType);
        dest.writeParcelable(this.poiInfo, flags);
    }

    protected FavoritePoiInfo(Parcel in) {
        this.favoriteType = in.readInt();
        this.poiInfo = (PoiInfo) in.readParcelable(PoiInfo.class.getClassLoader());
    }

    public String toString() {
        return "FavoritePoiInfo{favoriteType=" + this.favoriteType + ", poiInfo=" + this.poiInfo + '}';
    }
}