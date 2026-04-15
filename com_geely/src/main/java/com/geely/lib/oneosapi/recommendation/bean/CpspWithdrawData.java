package com.geely.lib.oneosapi.recommendation.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class CpspWithdrawData implements Parcelable {
    public static final Parcelable.Creator<CpspWithdrawData> CREATOR = new Parcelable.Creator<CpspWithdrawData>() { // from class: com.geely.lib.oneosapi.recommendation.bean.CpspWithdrawData.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CpspWithdrawData createFromParcel(Parcel in) {
            return new CpspWithdrawData(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CpspWithdrawData[] newArray(int size) {
            return new CpspWithdrawData[size];
        }
    };
    private String describe;
    private String messageId;
    private String title;
    private String type;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public CpspWithdrawData() {
    }

    protected CpspWithdrawData(Parcel in) {
        this.type = in.readString();
        this.messageId = in.readString();
        this.title = in.readString();
        this.describe = in.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.type);
        dest.writeString(this.messageId);
        dest.writeString(this.title);
        dest.writeString(this.describe);
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessageId() {
        return this.messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescribe() {
        return this.describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String toString() {
        return "CpspWithDrawData{type='" + this.type + "', messageId='" + this.messageId + "', title='" + this.title + "', describe='" + this.describe + "'}";
    }
}