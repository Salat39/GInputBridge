package com.geely.lib.oneosapi.user.bean;

import android.os.Parcel;
import android.os.Parcelable;
import com.geely.lib.oneosapi.user.api.IAccountInfo;

/* loaded from: classes.dex */
public class AccountInfo implements Parcelable, IAccountInfo {
    public static final Parcelable.Creator<AccountInfo> CREATOR = new Parcelable.Creator<AccountInfo>() { // from class: com.geely.lib.oneosapi.user.bean.AccountInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AccountInfo createFromParcel(Parcel source) {
            return new AccountInfo(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AccountInfo[] newArray(int size) {
            return new AccountInfo[size];
        }
    };
    public String deviceId;
    public String deviceVin;
    public String nickName;
    public String openId;
    public String token;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public AccountInfo() {
    }

    public AccountInfo(String deviceVin, String deviceId, String openId, String nickName, String token) {
        this.deviceVin = deviceVin;
        this.deviceId = deviceId;
        this.openId = openId;
        this.nickName = nickName;
        this.token = token;
    }

    protected AccountInfo(Parcel in) {
        this.deviceVin = in.readString();
        this.deviceId = in.readString();
        this.openId = in.readString();
        this.nickName = in.readString();
        this.token = in.readString();
    }

    public String toString() {
        return "AccountInfo{deviceVin='" + this.deviceVin + "', deviceId='" + this.deviceId + "', openId='" + this.openId + "', nickName='" + this.nickName + "', token='" + this.token + "'}";
    }

    @Override // com.geely.lib.oneosapi.user.api.IAccountInfo
    public String getDeviceVin() {
        return this.deviceVin;
    }

    public void setDeviceVin(String deviceVin) {
        this.deviceVin = deviceVin;
    }

    @Override // com.geely.lib.oneosapi.user.api.IAccountInfo
    public String getDeviceId() {
        return this.deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    @Override // com.geely.lib.oneosapi.user.api.IAccountInfo
    public String getOpenId() {
        return this.openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    @Override // com.geely.lib.oneosapi.user.api.IAccountInfo
    public String getNickName() {
        return this.nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @Override // com.geely.lib.oneosapi.user.api.IAccountInfo
    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.deviceVin);
        dest.writeString(this.deviceId);
        dest.writeString(this.openId);
        dest.writeString(this.nickName);
        dest.writeString(this.token);
    }

    public void readFromParcel(Parcel source) {
        this.deviceVin = source.readString();
        this.deviceId = source.readString();
        this.openId = source.readString();
        this.nickName = source.readString();
        this.token = source.readString();
    }
}