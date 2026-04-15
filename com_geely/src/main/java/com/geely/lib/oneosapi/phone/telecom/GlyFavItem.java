package com.geely.lib.oneosapi.phone.telecom;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

/* loaded from: classes.dex */
public class GlyFavItem implements Parcelable {
    public static final Parcelable.Creator<GlyFavItem> CREATOR = new Parcelable.Creator<GlyFavItem>() { // from class: com.geely.lib.oneosapi.phone.telecom.GlyFavItem.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public GlyFavItem createFromParcel(Parcel in) {
            return new GlyFavItem(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public GlyFavItem[] newArray(int size) {
            return new GlyFavItem[size];
        }
    };
    private String mContactName;
    private long mContainId;
    private String mFirstLetter;
    private boolean mIsFav;
    private List<String> mPhoneNumber;
    private List<String> mPhoneNumberTrim;
    private String mPicUrl;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String toString() {
        return "GlyFavItem{}";
    }

    protected GlyFavItem(Parcel in) {
        this.mIsFav = in.readByte() != 0;
        this.mContainId = in.readLong();
        this.mPicUrl = in.readString();
        this.mContactName = in.readString();
        this.mPhoneNumber = in.createStringArrayList();
        this.mPhoneNumberTrim = in.createStringArrayList();
        this.mFirstLetter = in.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte(this.mIsFav ? (byte) 1 : (byte) 0);
        parcel.writeLong(this.mContainId);
        parcel.writeString(this.mPicUrl);
        parcel.writeString(this.mContactName);
        parcel.writeStringList(this.mPhoneNumber);
        parcel.writeStringList(this.mPhoneNumberTrim);
        parcel.writeString(this.mFirstLetter);
    }

    public GlyFavItem(boolean isFav, long containId, String picUrl, String mContactName, List<String> mPhoneNumber, List<String> mPhoneNumberTrim) {
        this.mIsFav = isFav;
        this.mContainId = containId;
        this.mPicUrl = picUrl;
        this.mContactName = mContactName;
        this.mPhoneNumber = mPhoneNumber;
        this.mPhoneNumberTrim = mPhoneNumberTrim;
    }

    public boolean ismIsFav() {
        return this.mIsFav;
    }

    public void setmIsFav(boolean mIsFav) {
        this.mIsFav = mIsFav;
    }

    public long getmContainId() {
        return this.mContainId;
    }

    public void setmContainId(long mContainId) {
        this.mContainId = mContainId;
    }

    public String getmPicUrl() {
        return this.mPicUrl;
    }

    public void setmPicUrl(String mPicUrl) {
        this.mPicUrl = mPicUrl;
    }

    public String getmContactName() {
        return this.mContactName;
    }

    public void setmContactName(String mContactName) {
        this.mContactName = mContactName;
    }

    public List<String> getmPhoneNumber() {
        return this.mPhoneNumber;
    }

    public void setmPhoneNumber(List<String> mPhoneNumber) {
        this.mPhoneNumber = mPhoneNumber;
    }

    public List<String> getmPhoneNumberTrim() {
        return this.mPhoneNumberTrim;
    }

    public void setmPhoneNumberTrim(List<String> mPhoneNumberTrim) {
        this.mPhoneNumberTrim = mPhoneNumberTrim;
    }

    public String getmFirstLetter() {
        return this.mFirstLetter;
    }

    public void setmFirstLetter(String mFirstLetter) {
        this.mFirstLetter = mFirstLetter;
    }
}