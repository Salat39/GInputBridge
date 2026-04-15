package com.ecarx.xui.adaptapi.diminteraction;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: C:\Users\Andrei\AppData\Local\Temp\jadx-7518071738844169467\classes.dex */
public class NaviInfo implements Parcelable {
    public static final Parcelable.Creator<NaviInfo> CREATOR = new Parcelable.Creator<NaviInfo>() { // from class: com.ecarx.xui.adaptapi.diminteraction.NaviInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NaviInfo createFromParcel(Parcel parcel) {
            return new NaviInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NaviInfo[] newArray(int i) {
            return new NaviInfo[i];
        }
    };
    private String extra;
    private double lat;
    private double lng;
    private String name;
    private long remainDistance;
    private long remainTime;

    protected NaviInfo(Parcel parcel) {
        this.name = parcel.readString();
        this.lat = parcel.readDouble();
        this.lng = parcel.readDouble();
        this.remainDistance = parcel.readLong();
        this.remainTime = parcel.readLong();
        this.extra = parcel.readString();
    }

    public NaviInfo(String str, double d, double d2, long j, long j2) {
        this.name = str;
        this.lat = d;
        this.lng = d2;
        this.remainDistance = j;
        this.remainTime = j2;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public double gatLatitude() {
        return this.lat;
    }

    public double gatLongitude() {
        return this.lng;
    }

    public String getExtra() {
        return this.extra;
    }

    public String getName() {
        return this.name;
    }

    public long getRemainDistance() {
        return this.remainDistance;
    }

    public long getRemainTime() {
        return this.remainTime;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.name);
        parcel.writeDouble(this.lat);
        parcel.writeDouble(this.lng);
        parcel.writeLong(this.remainDistance);
        parcel.writeLong(this.remainTime);
        parcel.writeString(this.extra);
    }
}