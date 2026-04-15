package com.geely.lib.oneosapi.mediacenter.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class OnlineUserInfo implements Parcelable {
    public static final Parcelable.Creator<OnlineUserInfo> CREATOR = new Parcelable.Creator<OnlineUserInfo>() { // from class: com.geely.lib.oneosapi.mediacenter.bean.OnlineUserInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public OnlineUserInfo createFromParcel(Parcel in) {
            return new OnlineUserInfo(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public OnlineUserInfo[] newArray(int size) {
            return new OnlineUserInfo[size];
        }
    };
    public String avatarUrl;
    public boolean isExpired;
    public boolean isLogin;
    public String nickName;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public OnlineUserInfo() {
    }

    protected OnlineUserInfo(Parcel in) {
        this.isLogin = in.readByte() != 0;
        this.isExpired = in.readByte() != 0;
        this.nickName = in.readString();
        this.avatarUrl = in.readString();
    }

    public String toString() {
        return "OnlineUserInfo{isLogin=" + this.isLogin + ", isExpired=" + this.isExpired + ", nickName='" + this.nickName + "', avatarUrl='" + this.avatarUrl + "'}";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte(this.isLogin ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.isExpired ? (byte) 1 : (byte) 0);
        parcel.writeString(this.nickName);
        parcel.writeString(this.avatarUrl);
    }
}