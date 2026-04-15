package com.geely.lib.oneosapi.weather.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class ResponseWeatherBean implements Parcelable {
    public static final Parcelable.Creator<ResponseWeatherBean> CREATOR = new Parcelable.Creator<ResponseWeatherBean>() { // from class: com.geely.lib.oneosapi.weather.bean.ResponseWeatherBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ResponseWeatherBean createFromParcel(Parcel in) {
            return new ResponseWeatherBean(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ResponseWeatherBean[] newArray(int size) {
            return new ResponseWeatherBean[size];
        }
    };
    private int code;
    private WeatherInfo data;
    private String message;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    protected ResponseWeatherBean(Parcel in) {
        this.code = in.readInt();
        this.data = (WeatherInfo) in.readParcelable(WeatherInfo.class.getClassLoader());
        this.message = in.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.code);
        dest.writeParcelable(this.data, flags);
        dest.writeString(this.message);
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public WeatherInfo getData() {
        return this.data;
    }

    public void setData(WeatherInfo data) {
        this.data = data;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}