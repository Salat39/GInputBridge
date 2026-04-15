package com.ecarx.xui.adaptapi.car.hev;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/* loaded from: classes.dex */
public class BookTravelUsedInfo implements Parcelable {
    public static Parcelable.Creator<BookTravelUsedInfo> CREATOR = new Parcelable.Creator<BookTravelUsedInfo>() { // from class: com.ecarx.xui.adaptapi.car.hev.BookTravelUsedInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BookTravelUsedInfo createFromParcel(Parcel parcel) {
            return new BookTravelUsedInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BookTravelUsedInfo[] newArray(int i) {
            return new BookTravelUsedInfo[i];
        }
    };
    public static final int SWTSTS_OFF = 0;
    public static final int SWTSTS_ON = 1;
    public int battPreHeatgActvdSts;
    public ArrayList bookTravelOneDayInfoSet;
    public int bookTrvlClimaActvdSts;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public BookTravelUsedInfo() {
        this.bookTravelOneDayInfoSet = new ArrayList<>();
        this.battPreHeatgActvdSts = 0;
        this.bookTrvlClimaActvdSts = 0;
    }

    public BookTravelUsedInfo(BookTravelUsedInfo bookTravelUsedInfo) {
        this.bookTravelOneDayInfoSet = bookTravelUsedInfo.bookTravelOneDayInfoSet;
        this.battPreHeatgActvdSts = bookTravelUsedInfo.battPreHeatgActvdSts;
        this.bookTrvlClimaActvdSts = bookTravelUsedInfo.bookTrvlClimaActvdSts;
    }

    public BookTravelUsedInfo(Parcel parcel) {
        readFromParcel(parcel);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeList(this.bookTravelOneDayInfoSet);
        parcel.writeInt(this.battPreHeatgActvdSts);
        parcel.writeInt(this.bookTrvlClimaActvdSts);
    }

    public void readFromParcel(Parcel parcel) {
        this.bookTravelOneDayInfoSet = parcel.readArrayList(BookTravelOneDayInfo.class.getClassLoader());
        this.battPreHeatgActvdSts = parcel.readInt();
        this.bookTrvlClimaActvdSts = parcel.readInt();
    }

    public String toString() {
        return "BookTravelUsedInfo{bookTravelOneDayInfoSet=" + this.bookTravelOneDayInfoSet + ", battPreHeatgActvdSts=" + this.battPreHeatgActvdSts + ", bookTrvlClimaActvdSts=" + this.bookTrvlClimaActvdSts + '}';
    }
}
