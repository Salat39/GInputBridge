package com.ecarx.xui.adaptapi.car.hev;


import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class BookTravelDaily implements Parcelable {
    public static final Parcelable.Creator<BookTravelDaily> CREATOR = new Parcelable.Creator<BookTravelDaily>() { // from class: com.ecarx.xui.adaptapi.car.hev.BookTravelDaily.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BookTravelDaily createFromParcel(Parcel parcel) {
            return new BookTravelDaily(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BookTravelDaily[] newArray(int i) {
            return new BookTravelDaily[i];
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
    private boolean selectedSts;
    private int travelDay;
    private long travelTime;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public BookTravelDaily(int i, boolean z, long j) {
        this.travelDay = i;
        this.selectedSts = z;
        this.travelTime = j;
    }

    protected BookTravelDaily(Parcel parcel) {
        this.travelDay = parcel.readInt();
        this.selectedSts = parcel.readInt() == 1;
        this.travelTime = parcel.readLong();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.travelDay);
        parcel.writeInt(this.selectedSts ? 1 : 0);
        parcel.writeLong(this.travelTime);
    }

    public int getTravelDay() {
        return this.travelDay;
    }

    public boolean isSelectedSts() {
        return this.selectedSts;
    }

    public long getTravelTime() {
        return this.travelTime;
    }
}
