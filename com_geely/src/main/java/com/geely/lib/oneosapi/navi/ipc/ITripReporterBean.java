package com.geely.lib.oneosapi.navi.ipc;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class ITripReporterBean implements Parcelable {
    public static final Parcelable.Creator<ITripReporterBean> CREATOR = new Parcelable.Creator<ITripReporterBean>() { // from class: com.geely.lib.oneosapi.navi.ipc.ITripReporterBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ITripReporterBean createFromParcel(Parcel in) {
            return new ITripReporterBean(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ITripReporterBean[] newArray(int size) {
            return new ITripReporterBean[size];
        }
    };
    private Bundle extras;
    private int highestSpeed;
    private int resultCode;
    private int tripAveSpeed;
    private int tripDriveDistance;
    private int tripDriveTime;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public ITripReporterBean() {
    }

    public void readFromParcel(Parcel in) {
        this.tripDriveDistance = in.readInt();
        this.tripDriveTime = in.readInt();
        this.tripAveSpeed = in.readInt();
        this.highestSpeed = in.readInt();
        this.resultCode = in.readInt();
        this.extras = in.readBundle(Bundle.class.getClassLoader());
    }

    protected ITripReporterBean(Parcel in) {
        this.tripDriveDistance = in.readInt();
        this.tripDriveTime = in.readInt();
        this.tripAveSpeed = in.readInt();
        this.highestSpeed = in.readInt();
        this.resultCode = in.readInt();
        this.extras = in.readBundle(Bundle.class.getClassLoader());
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.tripDriveDistance);
        dest.writeInt(this.tripDriveTime);
        dest.writeInt(this.tripAveSpeed);
        dest.writeInt(this.highestSpeed);
        dest.writeInt(this.resultCode);
        dest.writeBundle(this.extras);
    }

    public int getTripDriveDistance() {
        return this.tripDriveDistance;
    }

    public void setTripDriveDistance(int tripDriveDistance) {
        this.tripDriveDistance = tripDriveDistance;
    }

    public int getTripDriveTime() {
        return this.tripDriveTime;
    }

    public void setTripDriveTime(int tripDriveTime) {
        this.tripDriveTime = tripDriveTime;
    }

    public int getTripAveSpeed() {
        return this.tripAveSpeed;
    }

    public void setTripAveSpeed(int tripAveSpeed) {
        this.tripAveSpeed = tripAveSpeed;
    }

    public int getHighestSpeed() {
        return this.highestSpeed;
    }

    public void setHighestSpeed(int highestSpeed) {
        this.highestSpeed = highestSpeed;
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
}