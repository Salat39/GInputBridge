package com.geely.lib.oneosapi.navi.model.client;

import android.os.Parcel;
import android.os.Parcelable;
import com.geely.lib.oneosapi.navi.model.base.LatLng;
import com.geely.lib.oneosapi.navi.model.base.NaviBaseModel;
import com.geely.lib.oneosapi.navi.model.base.NaviProtocolID;

/* loaded from: classes.dex */
public class UserFavoritePoiEdit extends NaviBaseModel implements Parcelable {
    public static final Parcelable.Creator<UserFavoritePoiEdit> CREATOR = new Parcelable.Creator<UserFavoritePoiEdit>() { // from class: com.geely.lib.oneosapi.navi.model.client.UserFavoritePoiEdit.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public UserFavoritePoiEdit createFromParcel(Parcel source) {
            return new UserFavoritePoiEdit(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public UserFavoritePoiEdit[] newArray(int size) {
            return new UserFavoritePoiEdit[size];
        }
    };
    public static final int FAVORITE_TYPE_COMPANY = 2;
    public static final int FAVORITE_TYPE_HOME = 1;
    public static final int FAVORITE_TYPE_NORMAL = 0;
    private String address;
    private int favoriteType;
    private LatLng latLng;
    private String poiName;

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public UserFavoritePoiEdit(int favType) {
        this.favoriteType = favType;
        setProtocolID(NaviProtocolID.USER_FAVORITE_EDIT);
    }

    public int getFavoriteType() {
        return this.favoriteType;
    }

    public String getPoiName() {
        return this.poiName;
    }

    public void setPoiName(String poiName) {
        this.poiName = poiName;
    }

    public LatLng getLatLng() {
        return this.latLng;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel, android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeInt(this.favoriteType);
        dest.writeString(this.poiName);
        dest.writeParcelable(this.latLng, flags);
        dest.writeString(this.address);
    }

    protected UserFavoritePoiEdit(Parcel in) {
        super(in);
        this.favoriteType = in.readInt();
        this.poiName = in.readString();
        this.latLng = (LatLng) in.readParcelable(LatLng.class.getClassLoader());
        this.address = in.readString();
    }

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel
    public String toString() {
        return "UserFavoritePoiEdit{favoriteType=" + this.favoriteType + ", poiName='" + this.poiName + "', latLng=" + this.latLng + ", address='" + this.address + "'{base=" + super.toString() + "}; }";
    }
}