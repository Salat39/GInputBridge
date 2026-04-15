package com.geely.lib.oneosapi.payment;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class FullReductionCouponParam implements Parcelable {
    public static final Parcelable.Creator<FullReductionCouponParam> CREATOR = new Parcelable.Creator<FullReductionCouponParam>() { // from class: com.geely.lib.oneosapi.payment.FullReductionCouponParam.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public FullReductionCouponParam createFromParcel(Parcel in) {
            return new FullReductionCouponParam(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public FullReductionCouponParam[] newArray(int size) {
            return new FullReductionCouponParam[size];
        }
    };
    private String couponAccountCode;
    private String couponAccountId;
    private boolean use;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public FullReductionCouponParam() {
    }

    public FullReductionCouponParam(String couponAccountCode, String couponAccountId, boolean use) {
        this.couponAccountCode = couponAccountCode;
        this.couponAccountId = couponAccountId;
        this.use = use;
    }

    protected FullReductionCouponParam(Parcel in) {
        this.couponAccountCode = in.readString();
        this.couponAccountId = in.readString();
        this.use = in.readByte() != 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.couponAccountCode);
        parcel.writeString(this.couponAccountId);
        parcel.writeByte(this.use ? (byte) 1 : (byte) 0);
    }

    public String getCouponAccountCode() {
        return this.couponAccountCode;
    }

    public void setCouponAccountCode(String couponAccountCode) {
        this.couponAccountCode = couponAccountCode;
    }

    public String getCouponAccountId() {
        return this.couponAccountId;
    }

    public void setCouponAccountId(String couponAccountId) {
        this.couponAccountId = couponAccountId;
    }

    public boolean isUse() {
        return this.use;
    }

    public void setUse(boolean use) {
        this.use = use;
    }
}