package com.geely.lib.oneosapi.navi.ipc;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class IHighwayExitInfoModelBean implements Parcelable {
    public static final Parcelable.Creator<IHighwayExitInfoModelBean> CREATOR = new Parcelable.Creator<IHighwayExitInfoModelBean>() { // from class: com.geely.lib.oneosapi.navi.ipc.IHighwayExitInfoModelBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public IHighwayExitInfoModelBean createFromParcel(Parcel in) {
            return new IHighwayExitInfoModelBean(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public IHighwayExitInfoModelBean[] newArray(int size) {
            return new IHighwayExitInfoModelBean[size];
        }
    };
    private int etaDistance;
    private int etaTime;
    private int exitBranchCount;
    private String exitDirection;
    private String exitSerialNumber;
    private Bundle extras;
    private int resultCode;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public IHighwayExitInfoModelBean() {
    }

    protected IHighwayExitInfoModelBean(Parcel in) {
        this.exitBranchCount = in.readInt();
        this.exitSerialNumber = in.readString();
        this.exitDirection = in.readString();
        this.etaDistance = in.readInt();
        this.etaTime = in.readInt();
        this.resultCode = in.readInt();
        this.extras = in.readBundle(Bundle.class.getClassLoader());
    }

    public void readFromParcel(Parcel in) {
        this.exitBranchCount = in.readInt();
        this.exitSerialNumber = in.readString();
        this.exitDirection = in.readString();
        this.etaDistance = in.readInt();
        this.etaTime = in.readInt();
        this.resultCode = in.readInt();
        this.extras = in.readBundle(Bundle.class.getClassLoader());
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.exitBranchCount);
        dest.writeString(this.exitSerialNumber);
        dest.writeString(this.exitDirection);
        dest.writeInt(this.etaDistance);
        dest.writeInt(this.etaTime);
        dest.writeInt(this.resultCode);
        dest.writeBundle(this.extras);
    }

    public int getExitBranchCount() {
        return this.exitBranchCount;
    }

    public void setExitBranchCount(int exitBranchCount) {
        this.exitBranchCount = exitBranchCount;
    }

    public String getExitSerialNumber() {
        return this.exitSerialNumber;
    }

    public void setExitSerialNumber(String exitSerialNumber) {
        this.exitSerialNumber = exitSerialNumber;
    }

    public String getExitDirection() {
        return this.exitDirection;
    }

    public void setExitDirection(String exitDirection) {
        this.exitDirection = exitDirection;
    }

    public int getEatDistance() {
        return this.etaDistance;
    }

    public void setEatDistance(int etaDistance) {
        this.etaDistance = etaDistance;
    }

    public int getEatTime() {
        return this.etaTime;
    }

    public void setEatTime(int etaTime) {
        this.etaTime = etaTime;
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

    public String toString() {
        return "IHighwayExitInfoModelBean{exitBranchCount=" + this.exitBranchCount + ", exitSerialNumber='" + this.exitSerialNumber + "', exitDirection='" + this.exitDirection + "', etaDistance=" + this.etaDistance + ", etaTime=" + this.etaTime + ", resultCode=" + this.resultCode + ", extras=" + this.extras + '}';
    }
}