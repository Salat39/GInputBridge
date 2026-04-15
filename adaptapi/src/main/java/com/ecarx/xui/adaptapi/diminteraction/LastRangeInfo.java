package com.ecarx.xui.adaptapi.diminteraction;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: C:\Users\Andrei\AppData\Local\Temp\jadx-7518071738844169467\classes.dex */
public class LastRangeInfo implements Parcelable {
    public static final Parcelable.Creator<LastRangeInfo> CREATOR = new Parcelable.Creator<LastRangeInfo>() { // from class: com.ecarx.xui.adaptapi.diminteraction.LastRangeInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public LastRangeInfo createFromParcel(Parcel parcel) {
            return new LastRangeInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public LastRangeInfo[] newArray(int i) {
            return new LastRangeInfo[i];
        }
    };
    private double latitudeE6;
    private double latitudeE61;
    private double longitudeE6;
    private double longitudeE61;
    private String msgSubTitle;
    private String name;

    public LastRangeInfo(double d, double d2, double d3, double d4, String str, String str2) {
        this.longitudeE6 = d;
        this.latitudeE6 = d2;
        this.longitudeE61 = d3;
        this.latitudeE61 = d4;
        this.name = str;
        this.msgSubTitle = str2;
    }

    protected LastRangeInfo(Parcel parcel) {
        this.longitudeE6 = parcel.readDouble();
        this.latitudeE6 = parcel.readDouble();
        this.longitudeE61 = parcel.readDouble();
        this.latitudeE61 = parcel.readDouble();
        this.name = parcel.readString();
        this.msgSubTitle = parcel.readString();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public double getLatitudeE6() {
        return this.latitudeE6;
    }

    public double getLatitudeE61() {
        return this.latitudeE61;
    }

    public double getLongitudeE6() {
        return this.longitudeE6;
    }

    public double getLongitudeE61() {
        return this.longitudeE61;
    }

    public String getMsgSubTitle() {
        return this.msgSubTitle;
    }

    public String getName() {
        return this.name;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(this.longitudeE6);
        parcel.writeDouble(this.latitudeE6);
        parcel.writeDouble(this.longitudeE61);
        parcel.writeDouble(this.latitudeE61);
        parcel.writeString(this.name);
        parcel.writeString(this.msgSubTitle);
    }
}