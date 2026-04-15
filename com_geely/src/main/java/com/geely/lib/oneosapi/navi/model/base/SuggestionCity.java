package com.geely.lib.oneosapi.navi.model.base;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class SuggestionCity implements Parcelable {
    public static final Parcelable.Creator<SuggestionCity> CREATOR = new Parcelable.Creator<SuggestionCity>() { // from class: com.geely.lib.oneosapi.navi.model.base.SuggestionCity.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SuggestionCity createFromParcel(Parcel source) {
            return new SuggestionCity(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SuggestionCity[] newArray(int size) {
            return new SuggestionCity[size];
        }
    };
    private String cityCode;
    private String cityName;
    private int suggestionNum;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public SuggestionCity() {
    }

    public SuggestionCity(String city, String code) {
        this.cityName = city;
        this.cityCode = code;
    }

    public int getSuggestionNum() {
        return this.suggestionNum;
    }

    public void setSuggestionNum(int suggestionNum) {
        this.suggestionNum = suggestionNum;
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

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.suggestionNum);
        dest.writeString(this.cityName);
        dest.writeString(this.cityCode);
    }

    protected SuggestionCity(Parcel in) {
        this.suggestionNum = in.readInt();
        this.cityName = in.readString();
        this.cityCode = in.readString();
    }

    public String toString() {
        return "SuggestionCity{suggestionNum=" + this.suggestionNum + ", cityName='" + this.cityName + "', cityCode='" + this.cityCode + "'}";
    }
}