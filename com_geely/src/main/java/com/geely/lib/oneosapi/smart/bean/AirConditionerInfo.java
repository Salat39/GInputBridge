package com.geely.lib.oneosapi.smart.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class AirConditionerInfo implements Parcelable {
    public static final Parcelable.Creator<AirConditionerInfo> CREATOR = new Parcelable.Creator<AirConditionerInfo>() { // from class: com.geely.lib.oneosapi.smart.bean.AirConditionerInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AirConditionerInfo createFromParcel(Parcel in) {
            return new AirConditionerInfo(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AirConditionerInfo[] newArray(int size) {
            return new AirConditionerInfo[size];
        }
    };
    private int switchOpen;
    private String temperature;
    private int windSpeed;
    private String workModel;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public AirConditionerInfo() {
    }

    public AirConditionerInfo(int switchOpen, String temperature, String workModel, int windSpeed) {
        this.switchOpen = switchOpen;
        this.temperature = temperature;
        this.workModel = workModel;
        this.windSpeed = windSpeed;
    }

    protected AirConditionerInfo(Parcel in) {
        this.switchOpen = in.readInt();
        this.temperature = in.readString();
        this.workModel = in.readString();
        this.windSpeed = in.readInt();
    }

    public int getSwitchOpen() {
        return this.switchOpen;
    }

    public void setSwitchOpen(int switchOpen) {
        this.switchOpen = switchOpen;
    }

    public String getTemperature() {
        return this.temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getWorkModel() {
        return this.workModel;
    }

    public void setWorkModel(String workModel) {
        this.workModel = workModel;
    }

    public int getWindSpeed() {
        return this.windSpeed;
    }

    public void setWindSpeed(int windSpeed) {
        this.windSpeed = windSpeed;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.switchOpen);
        dest.writeString(this.temperature);
        dest.writeString(this.workModel);
        dest.writeInt(this.windSpeed);
    }

    public String toString() {
        return "AirConditionerInfo{switchOpen=" + this.switchOpen + ", temperature='" + this.temperature + "', workModel='" + this.workModel + "', windSpeed='" + this.windSpeed + "'}";
    }
}