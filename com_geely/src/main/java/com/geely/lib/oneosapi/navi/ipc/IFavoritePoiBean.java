package com.geely.lib.oneosapi.navi.ipc;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class IFavoritePoiBean implements Parcelable {
    public static final Parcelable.Creator<IFavoritePoiBean> CREATOR = new Parcelable.Creator<IFavoritePoiBean>() { // from class: com.geely.lib.oneosapi.navi.ipc.IFavoritePoiBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public IFavoritePoiBean createFromParcel(Parcel in) {
            return new IFavoritePoiBean(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public IFavoritePoiBean[] newArray(int size) {
            return new IFavoritePoiBean[size];
        }
    };
    private String address;
    private int distance;
    private Bundle extras;
    private int favoriteType;
    private ILatLngBean latLng;
    private String name;
    private String phoneNumber;
    private IPoiTypeBean poiType;
    private long requestTimeout;
    private String uid;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public IFavoritePoiBean() {
    }

    protected IFavoritePoiBean(Parcel in) {
        this.favoriteType = in.readInt();
        this.name = in.readString();
        this.uid = in.readString();
        this.distance = in.readInt();
        this.latLng = (ILatLngBean) in.readParcelable(ILatLngBean.class.getClassLoader());
        this.address = in.readString();
        this.poiType = (IPoiTypeBean) in.readParcelable(IPoiTypeBean.class.getClassLoader());
        this.phoneNumber = in.readString();
        this.requestTimeout = in.readLong();
        this.extras = in.readBundle(Bundle.class.getClassLoader());
    }

    public void readFromParcel(Parcel in) {
        this.favoriteType = in.readInt();
        this.name = in.readString();
        this.uid = in.readString();
        this.distance = in.readInt();
        this.latLng = (ILatLngBean) in.readParcelable(ILatLngBean.class.getClassLoader());
        this.address = in.readString();
        this.poiType = (IPoiTypeBean) in.readParcelable(IPoiTypeBean.class.getClassLoader());
        this.phoneNumber = in.readString();
        this.requestTimeout = in.readLong();
        this.extras = in.readBundle(Bundle.class.getClassLoader());
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.favoriteType);
        dest.writeString(this.name);
        dest.writeString(this.uid);
        dest.writeInt(this.distance);
        dest.writeParcelable(this.latLng, flags);
        dest.writeString(this.address);
        dest.writeParcelable(this.poiType, flags);
        dest.writeString(this.phoneNumber);
        dest.writeLong(this.requestTimeout);
        dest.writeBundle(this.extras);
    }

    public int getFavoriteType() {
        return this.favoriteType;
    }

    public void setFavoriteType(int favoriteType) {
        this.favoriteType = favoriteType;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUid() {
        return this.uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public int getDistance() {
        return this.distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public ILatLngBean getLatLng() {
        return this.latLng;
    }

    public void setLatLng(ILatLngBean latLng) {
        this.latLng = latLng;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public IPoiTypeBean getPoiType() {
        return this.poiType;
    }

    public void setPoiType(IPoiTypeBean poiType) {
        this.poiType = poiType;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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
        return "IFavoritePoiBean{favoriteType=" + this.favoriteType + ", name='" + this.name + "', uid='" + this.uid + "', distance=" + this.distance + ", latLng=" + this.latLng + ", address='" + this.address + "', poiType=" + this.poiType + ", phoneNumber='" + this.phoneNumber + "', requestTimeout=" + this.requestTimeout + ", extras=" + this.extras + '}';
    }
}