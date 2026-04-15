package com.ecarx.xui.adaptapi.car.userprofile;

import android.os.Parcel;
import android.os.Parcelable;

public class DigitalKeyInfo implements Parcelable {
    public static final Parcelable.Creator<DigitalKeyInfo> CREATOR = new Parcelable.Creator<DigitalKeyInfo>() { // from class: com.ecarx.xui.adaptapi.car.userprofile.DigitalKeyInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DigitalKeyInfo createFromParcel(Parcel parcel) {
            return new DigitalKeyInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DigitalKeyInfo[] newArray(int i) {
            return new DigitalKeyInfo[i];
        }
    };
    private static final String TAG = "DigitalKeyInfo";
    private String KeyName;
    private int KeyPairingState;
    private long creatTimeUTCTi;
    private int keyId;
    private int keyType;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public void setSlotID(int i) {
    }

    protected DigitalKeyInfo(Parcel parcel) {
    }

    public String toString() {
        return "";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {

    }

    public int getKeyType() {
        return this.keyType;
    }

    public void setKeyType(int i) {
        this.keyType = i;
    }

    public int getKeyId() {
        return this.keyId;
    }

    public void setKeyId(int i) {
        this.keyId = i;
    }

    public int getKeyPairingState() {
        return this.KeyPairingState;
    }

    public void setKeyPairingState(int i) {
        this.KeyPairingState = i;
    }

    public long getCreatTimeUTCTi() {
        return this.creatTimeUTCTi;
    }

    public void setCreatTimeUTCTi(long j) {
        this.creatTimeUTCTi = j;
    }

    public String getKeyName() {
        return this.KeyName;
    }

    public void setKeyName(String str) {
        this.KeyName = str;
    }

    public int getSlotID() {
        return 1;
    }
}
