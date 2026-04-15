package com.geely.lib.oneosapi.navi.ipc;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class ISpeedLimitInfoBean implements Parcelable {
    public static final Parcelable.Creator<ISpeedLimitInfoBean> CREATOR = new Parcelable.Creator<ISpeedLimitInfoBean>() { // from class: com.geely.lib.oneosapi.navi.ipc.ISpeedLimitInfoBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ISpeedLimitInfoBean createFromParcel(Parcel in) {
            return new ISpeedLimitInfoBean(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ISpeedLimitInfoBean[] newArray(int size) {
            return new ISpeedLimitInfoBean[size];
        }
    };
    private int distance;
    private Bundle extras;
    private boolean isOverspeed;
    private int resultCode;
    private int speedLimit;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public ISpeedLimitInfoBean() {
    }

    protected ISpeedLimitInfoBean(Parcel in) {
        this.isOverspeed = in.readByte() != 0;
        this.distance = in.readInt();
        this.speedLimit = in.readInt();
        this.resultCode = in.readInt();
        this.extras = in.readBundle(Bundle.class.getClassLoader());
    }

    public void readFromParcel(Parcel in) {
        this.isOverspeed = in.readByte() != 0;
        this.distance = in.readInt();
        this.speedLimit = in.readInt();
        this.resultCode = in.readInt();
        this.extras = in.readBundle(Bundle.class.getClassLoader());
    }

    public boolean isOverspeed() {
        return this.isOverspeed;
    }

    public void setOverspeed(boolean overspeed) {
        this.isOverspeed = overspeed;
    }

    public int getDistance() {
        return this.distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getSpeedLimit() {
        return this.speedLimit;
    }

    public void setSpeedLimit(int speedLimit) {
        this.speedLimit = speedLimit;
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

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte(this.isOverspeed ? (byte) 1 : (byte) 0);
        parcel.writeInt(this.distance);
        parcel.writeInt(this.speedLimit);
        parcel.writeInt(this.resultCode);
        parcel.writeBundle(this.extras);
    }

    public String toString() {
        return "ISpeedLimitInfoImp{isOverspeed=" + this.isOverspeed + ", distance=" + this.distance + ", speedLimit=" + this.speedLimit + ", resultCode=" + this.resultCode + ", extras=" + this.extras + '}';
    }
}