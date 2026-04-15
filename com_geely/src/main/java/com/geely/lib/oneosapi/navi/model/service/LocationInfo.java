package com.geely.lib.oneosapi.navi.model.service;

import android.os.Parcel;
import android.os.Parcelable;
import com.geely.lib.oneosapi.navi.model.base.LatLng;

/* loaded from: classes.dex */
public class LocationInfo implements Parcelable, Cloneable {
    public static final Parcelable.Creator<LocationInfo> CREATOR = new Parcelable.Creator<LocationInfo>() { // from class: com.geely.lib.oneosapi.navi.model.service.LocationInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public LocationInfo createFromParcel(Parcel source) {
            return new LocationInfo(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public LocationInfo[] newArray(int size) {
            return new LocationInfo[size];
        }
    };
    private double accuracy;
    private String adcode;
    private String address;
    private double altitude;
    private float bearing;
    private String city;
    private String cityCode;
    private String country;
    private String countryCode;
    private long fixTime;
    private String formatedAddress;
    private long geoDecodeTime;
    private LatLng lastGeoDecodeLatLng;
    private LatLng latLng;
    private String province;
    private float speed;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public LatLng getLatLng() {
        return this.latLng;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }

    public double getAccuracy() {
        return this.accuracy;
    }

    public void setAccuracy(double accuracy) {
        this.accuracy = accuracy;
    }

    public double getAltitude() {
        return this.altitude;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }

    public float getSpeed() {
        return this.speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getBearing() {
        return this.bearing;
    }

    public void setBearing(float bearing) {
        this.bearing = bearing;
    }

    public long getFixTime() {
        return this.fixTime;
    }

    public void setFixTime(long fixTime) {
        this.fixTime = fixTime;
    }

    public LatLng getLastGeoDecodeLatLng() {
        return this.lastGeoDecodeLatLng;
    }

    public void setLastGeoDecodeLatLng(LatLng lastGeoDecodeLatLng) {
        this.lastGeoDecodeLatLng = lastGeoDecodeLatLng;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountryCode() {
        return this.countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getProvince() {
        return this.province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCityCode() {
        return this.cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getAdcode() {
        return this.adcode;
    }

    public void setAdcode(String adcode) {
        this.adcode = adcode;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFormatedAddress() {
        return this.formatedAddress;
    }

    public void setFormatedAddress(String formatedAddress) {
        this.formatedAddress = formatedAddress;
    }

    public long getGeoDecodeTime() {
        return this.geoDecodeTime;
    }

    public void setGeoDecodeTime(long geoDecodeTime) {
        this.geoDecodeTime = geoDecodeTime;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.latLng, flags);
        dest.writeDouble(this.accuracy);
        dest.writeDouble(this.altitude);
        dest.writeFloat(this.speed);
        dest.writeFloat(this.bearing);
        dest.writeLong(this.fixTime);
        dest.writeParcelable(this.lastGeoDecodeLatLng, flags);
        dest.writeString(this.country);
        dest.writeString(this.countryCode);
        dest.writeString(this.province);
        dest.writeString(this.city);
        dest.writeString(this.cityCode);
        dest.writeString(this.adcode);
        dest.writeString(this.address);
        dest.writeString(this.formatedAddress);
        dest.writeLong(this.geoDecodeTime);
    }

    public LocationInfo() {
    }

    protected LocationInfo(Parcel in) {
        this.latLng = (LatLng) in.readParcelable(LatLng.class.getClassLoader());
        this.accuracy = in.readDouble();
        this.altitude = in.readDouble();
        this.speed = in.readFloat();
        this.bearing = in.readFloat();
        this.fixTime = in.readLong();
        this.lastGeoDecodeLatLng = (LatLng) in.readParcelable(LatLng.class.getClassLoader());
        this.country = in.readString();
        this.countryCode = in.readString();
        this.province = in.readString();
        this.city = in.readString();
        this.cityCode = in.readString();
        this.adcode = in.readString();
        this.address = in.readString();
        this.formatedAddress = in.readString();
        this.geoDecodeTime = in.readLong();
    }

    public String toString() {
        return "LocationInfo{latLng=" + this.latLng + ", accuracy=" + this.accuracy + ", altitude=" + this.altitude + ", speed=" + this.speed + ", bearing=" + this.bearing + ", fixTime=" + this.fixTime + ", lastGeoDecodeLatLng=" + this.lastGeoDecodeLatLng + ", country='" + this.country + "', countryCode='" + this.countryCode + "', province='" + this.province + "', city='" + this.city + "', cityCode='" + this.cityCode + "', adcode='" + this.adcode + "', address='" + this.address + "', formatedAddress='" + this.formatedAddress + "', geoDecodeTime=" + this.geoDecodeTime + '}';
    }

    /* renamed from: clone, reason: merged with bridge method [inline-methods] */
    public LocationInfo m328clone() {
        try {
            LocationInfo locationInfo = (LocationInfo) super.clone();
            if (this.latLng != null) {
                locationInfo.latLng = this.latLng.m323clone();
            }
            if (this.lastGeoDecodeLatLng != null) {
                locationInfo.lastGeoDecodeLatLng = this.lastGeoDecodeLatLng.m323clone();
            }
            return locationInfo;
        } catch (CloneNotSupportedException unused) {
            return new LocationInfo();
        }
    }
}