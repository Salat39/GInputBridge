package com.geely.lib.oneosapi.smart.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class LightInfo implements Parcelable {
    public static final Parcelable.Creator<LightInfo> CREATOR = new Parcelable.Creator<LightInfo>() { // from class: com.geely.lib.oneosapi.smart.bean.LightInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public LightInfo createFromParcel(Parcel in) {
            return new LightInfo(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public LightInfo[] newArray(int size) {
            return new LightInfo[size];
        }
    };
    private int switchOpen;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public LightInfo() {
    }

    protected LightInfo(Parcel in) {
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
        return "LightInfo{switchOpen=" + this.switchOpen + '}';
    }
}