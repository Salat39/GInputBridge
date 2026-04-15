package com.geely.lib.oneosapi.navi.model.service;

import android.os.Parcel;
import android.os.Parcelable;
import com.geely.lib.oneosapi.navi.model.base.NaviBaseModel;

/* loaded from: classes.dex */
public class NaviTripReporter extends NaviBaseModel {
    public static final Parcelable.Creator<NaviTripReporter> CREATOR = new Parcelable.Creator<NaviTripReporter>() { // from class: com.geely.lib.oneosapi.navi.model.service.NaviTripReporter.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NaviTripReporter createFromParcel(Parcel source) {
            return new NaviTripReporter(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NaviTripReporter[] newArray(int size) {
            return new NaviTripReporter[size];
        }
    };
    private String tripAveSpeed;
    private String tripDriveDistance;
    private String tripDriveTime;

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public NaviTripReporter() {
        setProtocolID(1007);
    }

    public String getTripDriveDistance() {
        return this.tripDriveDistance;
    }

    public void setTripDriveDistance(String tripDriveDistance) {
        this.tripDriveDistance = tripDriveDistance;
    }

    public String getTripDriveTime() {
        return this.tripDriveTime;
    }

    public void setTripDriveTime(String tripDriveTime) {
        this.tripDriveTime = tripDriveTime;
    }

    public String getTripAveSpeed() {
        return this.tripAveSpeed;
    }

    public void setTripAveSpeed(String tripAveSpeed) {
        this.tripAveSpeed = tripAveSpeed;
    }

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel, android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(this.tripDriveDistance);
        dest.writeString(this.tripDriveTime);
        dest.writeString(this.tripAveSpeed);
    }

    protected NaviTripReporter(Parcel in) {
        super(in);
        this.tripDriveDistance = in.readString();
        this.tripDriveTime = in.readString();
        this.tripAveSpeed = in.readString();
    }

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel
    public String toString() {
        return "NaviTripReporter{tripDriveDistance=" + this.tripDriveDistance + "tripDriveTime=" + this.tripDriveTime + "tripAveSpeed=" + this.tripAveSpeed + "{base=" + super.toString() + "}; }";
    }
}