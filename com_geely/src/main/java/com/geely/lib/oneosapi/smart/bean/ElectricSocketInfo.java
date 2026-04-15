package com.geely.lib.oneosapi.smart.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class ElectricSocketInfo implements Parcelable {
    public static final Parcelable.Creator<ElectricSocketInfo> CREATOR = new Parcelable.Creator<ElectricSocketInfo>() { // from class: com.geely.lib.oneosapi.smart.bean.ElectricSocketInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ElectricSocketInfo createFromParcel(Parcel in) {
            return new ElectricSocketInfo(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ElectricSocketInfo[] newArray(int size) {
            return new ElectricSocketInfo[size];
        }
    };
    private int switchOpen;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public ElectricSocketInfo() {
    }

    protected ElectricSocketInfo(Parcel in) {
        this.switchOpen = in.readInt();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.switchOpen);
    }

    public int getSwitchOpen() {
        return this.switchOpen;
    }

    public void setSwitchOpen(int switchOpen) {
        this.switchOpen = switchOpen;
    }

    public String toString() {
        return "ElectricSocketInfo{switchOpen=" + this.switchOpen + '}';
    }
}