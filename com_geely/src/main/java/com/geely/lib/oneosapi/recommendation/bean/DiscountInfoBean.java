package com.geely.lib.oneosapi.recommendation.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class DiscountInfoBean implements Parcelable {
    public static final Parcelable.Creator<DiscountInfoBean> CREATOR = new Parcelable.Creator<DiscountInfoBean>() { // from class: com.geely.lib.oneosapi.recommendation.bean.DiscountInfoBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DiscountInfoBean createFromParcel(Parcel in) {
            return new DiscountInfoBean(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DiscountInfoBean[] newArray(int size) {
            return new DiscountInfoBean[size];
        }
    };
    private String item;
    private String memo;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public DiscountInfoBean() {
    }

    protected DiscountInfoBean(Parcel in) {
        this.item = in.readString();
        this.memo = in.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.item);
        dest.writeString(this.memo);
    }

    public String getItem() {
        return this.item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getMemo() {
        return this.memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}