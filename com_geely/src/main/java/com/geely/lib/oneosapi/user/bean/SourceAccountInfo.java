package com.geely.lib.oneosapi.user.bean;

import android.os.Parcel;
import android.os.Parcelable;
import com.geely.lib.oneosapi.user.api.ISourceAccountInfo;

/* loaded from: classes.dex */
public class SourceAccountInfo implements Parcelable, ISourceAccountInfo {
    public static final Parcelable.Creator<SourceAccountInfo> CREATOR = new Parcelable.Creator<SourceAccountInfo>() { // from class: com.geely.lib.oneosapi.user.bean.SourceAccountInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SourceAccountInfo createFromParcel(Parcel source) {
            return new SourceAccountInfo(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SourceAccountInfo[] newArray(int size) {
            return new SourceAccountInfo[size];
        }
    };
    public String accountAvatar;
    public int accountLoginStatus;
    public String bindingAck;
    public String bindingId;
    public String carLoginEnvironment;
    public String deviceId;
    public String deviceNo;
    public String sourceAccount;
    public String sourceAccountId;
    public String sourceAccountName;
    public String sourceAccountToken;
    public String sourceApp;
    public String sourceAppName;
    public String sourceAutoAccount;
    public String userRequestTime;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public SourceAccountInfo() {
    }

    public SourceAccountInfo(String sourceApp, String sourceAppName, int accountLoginStatus, String sourceAccountToken, String sourceAutoAccount, String sourceAccount, String deviceId, String deviceNo, String sourceAccountId, String sourceAccountName, String accountAvatar, String userRequestTime, String bindingId, String bindingAck, String carLoginEnvironment) {
        this.sourceApp = sourceApp;
        this.sourceAppName = sourceAppName;
        this.accountLoginStatus = accountLoginStatus;
        this.sourceAccountToken = sourceAccountToken;
        this.sourceAutoAccount = sourceAutoAccount;
        this.sourceAccount = sourceAccount;
        this.deviceId = deviceId;
        this.deviceNo = deviceNo;
        this.sourceAccountId = sourceAccountId;
        this.sourceAccountName = sourceAccountName;
        this.accountAvatar = accountAvatar;
        this.userRequestTime = userRequestTime;
        this.bindingId = bindingId;
        this.bindingAck = bindingAck;
        this.carLoginEnvironment = carLoginEnvironment;
    }

    protected SourceAccountInfo(Parcel in) {
        this.sourceApp = in.readString();
        this.sourceAppName = in.readString();
        this.accountLoginStatus = in.readInt();
        this.sourceAccountToken = in.readString();
        this.sourceAutoAccount = in.readString();
        this.sourceAccount = in.readString();
        this.deviceId = in.readString();
        this.deviceNo = in.readString();
        this.sourceAccountId = in.readString();
        this.sourceAccountName = in.readString();
        this.accountAvatar = in.readString();
        this.userRequestTime = in.readString();
        this.bindingId = in.readString();
        this.bindingAck = in.readString();
        this.carLoginEnvironment = in.readString();
    }

    @Override // com.geely.lib.oneosapi.user.api.ISourceAccountInfo
    public String getSourceApp() {
        return this.sourceApp;
    }

    public void setSourceApp(String sourceApp) {
        this.sourceApp = sourceApp;
    }

    @Override // com.geely.lib.oneosapi.user.api.ISourceAccountInfo
    public String getSourceAppName() {
        return this.sourceAppName;
    }

    public void setSourceAppName(String sourceAppName) {
        this.sourceAppName = sourceAppName;
    }

    @Override // com.geely.lib.oneosapi.user.api.ISourceAccountInfo
    public int getAccountLoginStatus() {
        return this.accountLoginStatus;
    }

    public void setAccountLoginStatus(int accountLoginStatus) {
        this.accountLoginStatus = accountLoginStatus;
    }

    @Override // com.geely.lib.oneosapi.user.api.ISourceAccountInfo
    public String getSourceAccountToken() {
        return this.sourceAccountToken;
    }

    public void setSourceAccountToken(String sourceAccountToken) {
        this.sourceAccountToken = sourceAccountToken;
    }

    @Override // com.geely.lib.oneosapi.user.api.ISourceAccountInfo
    public String getSourceAutoAccount() {
        return this.sourceAutoAccount;
    }

    public void setSourceAutoAccount(String sourceAutoAccount) {
        this.sourceAutoAccount = sourceAutoAccount;
    }

    @Override // com.geely.lib.oneosapi.user.api.ISourceAccountInfo
    public String getSourceAccount() {
        return this.sourceAccount;
    }

    public void setSourceAccount(String sourceAccount) {
        this.sourceAccount = sourceAccount;
    }

    @Override // com.geely.lib.oneosapi.user.api.ISourceAccountInfo
    public String getDeviceId() {
        return this.deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    @Override // com.geely.lib.oneosapi.user.api.ISourceAccountInfo
    public String getDeviceNo() {
        return this.deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }

    @Override // com.geely.lib.oneosapi.user.api.ISourceAccountInfo
    public String getSourceAccountId() {
        return this.sourceAccountId;
    }

    public void setSourceAccountId(String sourceAccountId) {
        this.sourceAccountId = sourceAccountId;
    }

    @Override // com.geely.lib.oneosapi.user.api.ISourceAccountInfo
    public String getSourceAccountName() {
        return this.sourceAccountName;
    }

    public void setSourceAccountName(String sourceAccountName) {
        this.sourceAccountName = sourceAccountName;
    }

    @Override // com.geely.lib.oneosapi.user.api.ISourceAccountInfo
    public String getAccountAvatar() {
        return this.accountAvatar;
    }

    public void setAccountAvatar(String accountAvatar) {
        this.accountAvatar = accountAvatar;
    }

    @Override // com.geely.lib.oneosapi.user.api.ISourceAccountInfo
    public String getUserRequestTime() {
        return this.userRequestTime;
    }

    public void setUserRequestTime(String userRequestTime) {
        this.userRequestTime = userRequestTime;
    }

    @Override // com.geely.lib.oneosapi.user.api.ISourceAccountInfo
    public String getBindingId() {
        return this.bindingId;
    }

    public void setBindingId(String bindingId) {
        this.bindingId = bindingId;
    }

    @Override // com.geely.lib.oneosapi.user.api.ISourceAccountInfo
    public String getBindingAck() {
        return this.bindingAck;
    }

    public void setBindingAck(String bindingAck) {
        this.bindingAck = bindingAck;
    }

    @Override // com.geely.lib.oneosapi.user.api.ISourceAccountInfo
    public String getCarLoginEnvironment() {
        return this.carLoginEnvironment;
    }

    public void setCarLoginEnvironment(String carLoginEnvironment) {
        this.carLoginEnvironment = carLoginEnvironment;
    }

    public String toString() {
        return "SourceAccountInfo{sourceApp='" + this.sourceApp + "', sourceAppName='" + this.sourceAppName + "', accountLoginStatus=" + this.accountLoginStatus + ", sourceAccountToken='" + this.sourceAccountToken + "', sourceAutoAccount='" + this.sourceAutoAccount + "', sourceAccount='" + this.sourceAccount + "', deviceId='" + this.deviceId + "', deviceNo='" + this.deviceNo + "', sourceAccountId='" + this.sourceAccountId + "', sourceAccountName='" + this.sourceAccountName + "', accountAvatar='" + this.accountAvatar + "', userRequestTime='" + this.userRequestTime + "', bindingId='" + this.bindingId + "', bindingAck='" + this.bindingAck + "', carLoginEnvironment='" + this.carLoginEnvironment + "'}";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.sourceApp);
        dest.writeString(this.sourceAppName);
        dest.writeInt(this.accountLoginStatus);
        dest.writeString(this.sourceAccountToken);
        dest.writeString(this.sourceAutoAccount);
        dest.writeString(this.sourceAccount);
        dest.writeString(this.deviceId);
        dest.writeString(this.deviceNo);
        dest.writeString(this.sourceAccountId);
        dest.writeString(this.sourceAccountName);
        dest.writeString(this.accountAvatar);
        dest.writeString(this.userRequestTime);
        dest.writeString(this.bindingId);
        dest.writeString(this.bindingAck);
        dest.writeString(this.carLoginEnvironment);
    }

    public void readFromParcel(Parcel source) {
        this.sourceApp = source.readString();
        this.sourceAppName = source.readString();
        this.accountLoginStatus = source.readInt();
        this.sourceAccountToken = source.readString();
        this.sourceAutoAccount = source.readString();
        this.sourceAccount = source.readString();
        this.deviceId = source.readString();
        this.deviceNo = source.readString();
        this.sourceAccountId = source.readString();
        this.sourceAccountName = source.readString();
        this.accountAvatar = source.readString();
        this.userRequestTime = source.readString();
        this.bindingId = source.readString();
        this.bindingAck = source.readString();
        this.carLoginEnvironment = source.readString();
    }
}