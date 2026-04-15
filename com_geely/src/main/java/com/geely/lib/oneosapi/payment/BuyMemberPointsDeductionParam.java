package com.geely.lib.oneosapi.payment;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class BuyMemberPointsDeductionParam implements Parcelable {
    public static final Parcelable.Creator<BuyMemberPointsDeductionParam> CREATOR = new Parcelable.Creator<BuyMemberPointsDeductionParam>() { // from class: com.geely.lib.oneosapi.payment.BuyMemberPointsDeductionParam.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BuyMemberPointsDeductionParam createFromParcel(Parcel in) {
            return new BuyMemberPointsDeductionParam(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BuyMemberPointsDeductionParam[] newArray(int size) {
            return new BuyMemberPointsDeductionParam[size];
        }
    };
    private long chosenIntegral;
    private boolean use;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public BuyMemberPointsDeductionParam() {
    }

    public BuyMemberPointsDeductionParam(long chosenIntegral, boolean use) {
        this.chosenIntegral = chosenIntegral;
        this.use = use;
    }

    protected BuyMemberPointsDeductionParam(Parcel in) {
        this.chosenIntegral = in.readLong();
        this.use = in.readByte() != 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.chosenIntegral);
        parcel.writeByte(this.use ? (byte) 1 : (byte) 0);
    }

    public long getChosenIntegral() {
        return this.chosenIntegral;
    }

    public void setChosenIntegral(long chosenIntegral) {
        this.chosenIntegral = chosenIntegral;
    }

    public boolean isUse() {
        return this.use;
    }

    public void setUse(boolean use) {
        this.use = use;
    }
}