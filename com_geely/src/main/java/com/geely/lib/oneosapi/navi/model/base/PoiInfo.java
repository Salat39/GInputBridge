package com.geely.lib.oneosapi.navi.model.base;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class PoiInfo implements Parcelable {
    public static final Parcelable.Creator<PoiInfo> CREATOR = new Parcelable.Creator<PoiInfo>() { // from class: com.geely.lib.oneosapi.navi.model.base.PoiInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PoiInfo createFromParcel(Parcel source) {
            return new PoiInfo(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PoiInfo[] newArray(int size) {
            return new PoiInfo[size];
        }
    };
    private String address;
    private int distance;
    private LatLng latLng;
    private String name;
    private PoiType type;
    private String uid;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public PoiInfo() {
    }

    public String getName() {
        return this.name;
    }

    public PoiInfo setName(String name) {
        this.name = name;
        return this;
    }

    public String getUid() {
        return this.uid;
    }

    public PoiInfo setUid(String uid) {
        this.uid = uid;
        return this;
    }

    public PoiType getType() {
        return this.type;
    }

    public PoiInfo setType(PoiType type) {
        this.type = type;
        return this;
    }

    public String getAddress() {
        return this.address;
    }

    public PoiInfo setAddress(String address) {
        this.address = address;
        return this;
    }

    public int getDistance() {
        return this.distance;
    }

    public PoiInfo setDistance(int distance) {
        this.distance = distance;
        return this;
    }

    public LatLng getLatLng() {
        return this.latLng;
    }

    public PoiInfo setLatLng(LatLng latLng) {
        this.latLng = latLng;
        return this;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.uid);
        dest.writeParcelable(this.type, flags);
        dest.writeString(this.address);
        dest.writeInt(this.distance);
        dest.writeParcelable(this.latLng, flags);
    }

    protected PoiInfo(Parcel in) {
        this.name = in.readString();
        this.uid = in.readString();
        this.type = (PoiType) in.readParcelable(PoiType.class.getClassLoader());
        this.address = in.readString();
        this.distance = in.readInt();
        this.latLng = (LatLng) in.readParcelable(LatLng.class.getClassLoader());
    }

    public String toString() {
        return "PoiInfo{name='" + this.name + "', uid='" + this.uid + "', type=" + this.type + ", address='" + this.address + "', distance=" + this.distance + ", latLng=" + this.latLng + '}';
    }
}