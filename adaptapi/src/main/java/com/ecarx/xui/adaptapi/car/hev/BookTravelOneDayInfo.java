package com.ecarx.xui.adaptapi.car.hev;


import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class BookTravelOneDayInfo implements Parcelable {
    public static Parcelable.Creator<BookTravelOneDayInfo> CREATOR = new Parcelable.Creator<BookTravelOneDayInfo>() { // from class: com.ecarx.xui.adaptapi.car.hev.BookTravelOneDayInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BookTravelOneDayInfo createFromParcel(Parcel parcel) {
            return new BookTravelOneDayInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BookTravelOneDayInfo[] newArray(int i) {
            return new BookTravelOneDayInfo[i];
        }
    };
    public static final int SELECTEDDAY_FRI = 5;
    public static final int SELECTEDDAY_MON = 1;
    public static final int SELECTEDDAY_NOSET = 0;
    public static final int SELECTEDDAY_SAT = 6;
    public static final int SELECTEDDAY_SUN = 7;
    public static final int SELECTEDDAY_THU = 4;
    public static final int SELECTEDDAY_TUE = 2;
    public static final int SELECTEDDAY_WED = 3;
    public int day;
    public boolean selectedSts;
    public long travelTime;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public BookTravelOneDayInfo() {
        this.selectedSts = false;
        this.day = 0;
        this.travelTime = 0L;
    }

    public BookTravelOneDayInfo(boolean z, int i, long j) {
        this.selectedSts = z;
        this.day = i;
        this.travelTime = j;
    }

    public BookTravelOneDayInfo(BookTravelOneDayInfo bookTravelOneDayInfo) {
        this.selectedSts = bookTravelOneDayInfo.selectedSts;
        this.day = bookTravelOneDayInfo.day;
        this.travelTime = bookTravelOneDayInfo.travelTime;
    }

    public BookTravelOneDayInfo(Parcel parcel) {
        readFromParcel(parcel);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {

    }

    public void readFromParcel(Parcel parcel) {

    }

    public String toString() {
        return "BookTravelOneDayInfo{selectedSts=" + this.selectedSts + ", day=" + this.day + ", travelTime=" + this.travelTime + '}';
    }
}
