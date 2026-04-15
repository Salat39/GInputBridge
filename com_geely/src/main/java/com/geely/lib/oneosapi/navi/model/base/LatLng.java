package com.geely.lib.oneosapi.navi.model.base;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class LatLng implements Parcelable, Cloneable {
    public static final String COORD_TYPE_BD09LL = "bd09ll";
    public static final String COORD_TYPE_BD09MC = "bd09mc";
    public static final String COORD_TYPE_GCJ02 = "gcj02";
    public static final String COORD_TYPE_WGS84 = "wgs84";
    public static final Parcelable.Creator<LatLng> CREATOR = new Parcelable.Creator<LatLng>() { // from class: com.geely.lib.oneosapi.navi.model.base.LatLng.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public LatLng createFromParcel(Parcel source) {
            return new LatLng(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public LatLng[] newArray(int size) {
            return new LatLng[size];
        }
    };
    private String coordType;
    private double latitude;
    private double longitude;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public LatLng(double lat, double lng) {
        this.latitude = lat;
        this.longitude = lng;
        this.coordType = COORD_TYPE_GCJ02;
    }

    public LatLng(double lat, double lng, String coordType) {
        this.latitude = lat;
        this.longitude = lng;
        this.coordType = coordType;
    }

    public double getLatitude() {
        return this.latitude;
    }

    public LatLng setLatitude(double latitude) {
        this.latitude = latitude;
        return this;
    }

    public double getLongitude() {
        return this.longitude;
    }

    public LatLng setLongitude(double longitude) {
        this.longitude = longitude;
        return this;
    }

    public String getCoordType() {
        return this.coordType;
    }

    public LatLng setCoordType(String coordType) {
        this.coordType = coordType;
        return this;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(this.latitude);
        dest.writeDouble(this.longitude);
        dest.writeString(this.coordType);
    }

    protected LatLng(Parcel in) {
        this.latitude = in.readDouble();
        this.longitude = in.readDouble();
        this.coordType = in.readString();
    }

    public String toString() {
        return "LatLng{latitude=" + this.latitude + ", longitude=" + this.longitude + ", coordType='" + this.coordType + "'}";
    }

    /* renamed from: clone, reason: merged with bridge method [inline-methods] */
    public LatLng m323clone() {
        try {
            return (LatLng) super.clone();
        } catch (CloneNotSupportedException unused) {
            return new LatLng(0.0d, 0.0d);
        }
    }
}