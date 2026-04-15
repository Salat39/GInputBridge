package com.geely.lib.oneosapi.phone.telecom;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class GlyCallLogInfo implements Parcelable {
    public static final Parcelable.Creator<GlyCallLogInfo> CREATOR = new Parcelable.Creator<GlyCallLogInfo>() { // from class: com.geely.lib.oneosapi.phone.telecom.GlyCallLogInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public GlyCallLogInfo createFromParcel(Parcel in) {
            return new GlyCallLogInfo(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public GlyCallLogInfo[] newArray(int size) {
            return new GlyCallLogInfo[size];
        }
    };
    private int mCallType;
    private long mDate;
    private String mNumber;
    private String mNumberPrivate;
    private String mNumberTrim;
    private String mUserName;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String toString() {
        return "CallLogInfo{}";
    }

    public GlyCallLogInfo(String mUserName, String mNumber, long date, int callType, String numberTrim, String numberPrivate) {
        this.mUserName = mUserName;
        this.mNumber = mNumber;
        this.mDate = date;
        this.mCallType = callType;
        this.mNumberTrim = numberTrim;
        this.mNumberPrivate = numberPrivate;
    }

    protected GlyCallLogInfo(Parcel in) {
        this.mCallType = in.readInt();
        this.mNumber = in.readString();
        this.mNumberPrivate = in.readString();
        this.mNumberTrim = in.readString();
        this.mUserName = in.readString();
        this.mDate = in.readLong();
    }

    public String getmNumberTrim() {
        return this.mNumberTrim;
    }

    public void setmNumberTrim(String mNumberTrim) {
        this.mNumberTrim = mNumberTrim;
    }

    public int getmCallType() {
        return this.mCallType;
    }

    public void setmCallType(int mCallType) {
        this.mCallType = mCallType;
    }

    public String getmUserName() {
        return this.mUserName;
    }

    public void setmUserName(String mUserName) {
        this.mUserName = mUserName;
    }

    public String getmNumber() {
        return this.mNumber;
    }

    public void setmNumber(String mNumber) {
        this.mNumber = mNumber;
    }

    public long getmDate() {
        return this.mDate;
    }

    public void setmDate(long mDate) {
        this.mDate = mDate;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mCallType);
        parcel.writeString(this.mNumber);
        parcel.writeString(this.mNumberPrivate);
        parcel.writeString(this.mNumberTrim);
        parcel.writeString(this.mUserName);
        parcel.writeLong(this.mDate);
    }

    public void readFromParcel(Parcel source) {
        this.mCallType = source.readInt();
        this.mNumber = source.readString();
        this.mNumberPrivate = source.readString();
        this.mNumberTrim = source.readString();
        this.mUserName = source.readString();
        this.mDate = source.readLong();
    }
}