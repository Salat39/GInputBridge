package com.geely.lib.oneosapi.navi.ipc;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class ISuggestCityBean implements Parcelable {
    public static final Parcelable.Creator<ISuggestCityBean> CREATOR = new Parcelable.Creator<ISuggestCityBean>() { // from class: com.geely.lib.oneosapi.navi.ipc.ISuggestCityBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ISuggestCityBean createFromParcel(Parcel in) {
            return new ISuggestCityBean(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ISuggestCityBean[] newArray(int size) {
            return new ISuggestCityBean[size];
        }
    };
    private String cityCode;
    private String cityName;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public ISuggestCityBean() {
    }

    protected ISuggestCityBean(Parcel in) {
        this.cityName = in.readString();
        this.cityCode = in.readString();
    }

    public void readFromParcel(Parcel in) {
        this.cityName = in.readString();
        this.cityCode = in.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.cityName);
        dest.writeString(this.cityCode);
    }

    public String getCityName() {
        return this.cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityCode() {
        return this.cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String toString() {
        return "ISuggestCityBean{cityName='" + this.cityName + "', cityCode='" + this.cityCode + "'}";
    }
}