package com.geely.lib.oneosapi.navi.model.service;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.geely.lib.oneosapi.navi.ipc.ILatLngBean;
import com.geely.lib.oneosapi.navi.model.base.NaviBaseModel;

/* loaded from: classes.dex */
public class RspLocationInfo extends NaviBaseModel implements Parcelable {
    public static final Parcelable.Creator<RspLocationInfo> CREATOR = new Parcelable.Creator<RspLocationInfo>() { // from class: com.geely.lib.oneosapi.navi.model.service.RspLocationInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RspLocationInfo createFromParcel(Parcel in) {
            return new RspLocationInfo(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RspLocationInfo[] newArray(int size) {
            return new RspLocationInfo[size];
        }
    };
    private String adCode;
    private String adName;
    private String address;
    private String city;
    private String cityCode;
    private String country;
    private String countryCode;
    private Bundle extras;
    private String formatedAddress;
    private ILatLngBean latLng;
    private String province;
    private int resultCode;

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public RspLocationInfo() {
    }

    public RspLocationInfo(NaviBaseModel reqModel) {
        copyBaseInfo(reqModel);
    }

    protected RspLocationInfo(Parcel in) {
        super(in);
        this.latLng = (ILatLngBean) in.readParcelable(ILatLngBean.class.getClassLoader());
        this.country = in.readString();
        this.countryCode = in.readString();
        this.province = in.readString();
        this.city = in.readString();
        this.cityCode = in.readString();
        this.adName = in.readString();
        this.adCode = in.readString();
        this.address = in.readString();
        this.formatedAddress = in.readString();
        this.resultCode = in.readInt();
        this.extras = in.readBundle(Bundle.class.getClassLoader());
    }

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel
    public void readFromParcel(Parcel in) {
        super.readFromParcel(in);
        this.latLng = (ILatLngBean) in.readParcelable(ILatLngBean.class.getClassLoader());
        this.country = in.readString();
        this.countryCode = in.readString();
        this.province = in.readString();
        this.city = in.readString();
        this.cityCode = in.readString();
        this.adName = in.readString();
        this.adCode = in.readString();
        this.address = in.readString();
        this.formatedAddress = in.readString();
        this.resultCode = in.readInt();
        this.extras = in.readBundle(Bundle.class.getClassLoader());
    }

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel, android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeParcelable(this.latLng, flags);
        dest.writeString(this.country);
        dest.writeString(this.countryCode);
        dest.writeString(this.province);
        dest.writeString(this.city);
        dest.writeString(this.cityCode);
        dest.writeString(this.adName);
        dest.writeString(this.adCode);
        dest.writeString(this.address);
        dest.writeString(this.formatedAddress);
        dest.writeInt(this.resultCode);
        dest.writeBundle(this.extras);
    }

    public ILatLngBean getLatLng() {
        return this.latLng;
    }

    public void setLatLng(ILatLngBean latLng) {
        this.latLng = latLng;
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

    public String getAdName() {
        return this.adName;
    }

    public void setAdName(String adName) {
        this.adName = adName;
    }

    public String getAdCode() {
        return this.adCode;
    }

    public void setAdCode(String adCode) {
        this.adCode = adCode;
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

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel
    public String toString() {
        super.toString();
        return "RspLocationInfo{latLng=" + this.latLng.toString() + ", country='" + this.country + "', countryCode='" + this.countryCode + "', province='" + this.province + "', city='" + this.city + "', cityCode='" + this.cityCode + "', adName='" + this.adName + "', adCode='" + this.adCode + "', address='" + this.address + "', formatedAddress='" + this.formatedAddress + "', resultCode=" + this.resultCode + ", extras=" + this.extras + '}';
    }
}