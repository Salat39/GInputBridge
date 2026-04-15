package com.geely.lib.oneosapi.user.bean;

import android.os.Parcel;
import android.os.Parcelable;
import com.geely.lib.oneosapi.user.api.IUserInfo;

/* loaded from: classes.dex */
public class UserInfo implements Parcelable, IUserInfo {
    public static final Parcelable.Creator<UserInfo> CREATOR = new Parcelable.Creator<UserInfo>() { // from class: com.geely.lib.oneosapi.user.bean.UserInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public UserInfo createFromParcel(Parcel source) {
            return new UserInfo(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public UserInfo[] newArray(int size) {
            return new UserInfo[size];
        }
    };
    public String Sex;
    public String accountType;
    public String address;
    public int age;
    public String avatarUrl;
    public String birthday;
    public String carModel;
    public String dateOfBirth;
    public String email;
    public boolean isGIDUser;
    public String isNoSecretLogin;
    public String level;
    public String levelName;
    public String mobile;
    public String name;
    public String nickname;
    public String userId;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.geely.lib.oneosapi.user.api.IUserInfo
    public String getLoginAccountType() {
        return null;
    }

    public UserInfo() {
    }

    public UserInfo(String mobile, String name, String sex, int age, String dateOfBirth, String userId, String carModel, String level, String levelName, String address, String nickname, boolean isGIDUser, String avatarUrl, String email, String birthday, String accountType, String isNoSecretLogin) {
        this.mobile = mobile;
        this.name = name;
        this.Sex = sex;
        this.age = age;
        this.dateOfBirth = dateOfBirth;
        this.userId = userId;
        this.carModel = carModel;
        this.level = level;
        this.levelName = levelName;
        this.address = address;
        this.nickname = nickname;
        this.isGIDUser = isGIDUser;
        this.avatarUrl = avatarUrl;
        this.email = email;
        this.birthday = birthday;
        this.accountType = accountType;
        this.isNoSecretLogin = isNoSecretLogin;
    }

    protected UserInfo(Parcel in) {
        this.mobile = in.readString();
        this.name = in.readString();
        this.Sex = in.readString();
        this.age = in.readInt();
        this.dateOfBirth = in.readString();
        this.userId = in.readString();
        this.carModel = in.readString();
        this.level = in.readString();
        this.levelName = in.readString();
        this.address = in.readString();
        this.nickname = in.readString();
        this.isGIDUser = in.readByte() != 0;
        this.avatarUrl = in.readString();
        this.email = in.readString();
        this.birthday = in.readString();
        this.accountType = in.readString();
        this.isNoSecretLogin = in.readString();
    }

    public String getAccountType() {
        return this.accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    @Override // com.geely.lib.oneosapi.user.api.IUserInfo
    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override // com.geely.lib.oneosapi.user.api.IUserInfo
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override // com.geely.lib.oneosapi.user.api.IUserInfo
    public String getSex() {
        return this.Sex;
    }

    public void setSex(String sex) {
        this.Sex = sex;
    }

    @Override // com.geely.lib.oneosapi.user.api.IUserInfo
    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override // com.geely.lib.oneosapi.user.api.IUserInfo
    public String getDateOfBirth() {
        return this.dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override // com.geely.lib.oneosapi.user.api.IUserInfo
    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override // com.geely.lib.oneosapi.user.api.IUserInfo
    public String getCarModel() {
        return this.carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    @Override // com.geely.lib.oneosapi.user.api.IUserInfo
    public String getLevel() {
        return this.level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    @Override // com.geely.lib.oneosapi.user.api.IUserInfo
    public String getLevelName() {
        return this.levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    @Override // com.geely.lib.oneosapi.user.api.IUserInfo
    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override // com.geely.lib.oneosapi.user.api.IUserInfo
    public String getNickname() {
        return this.nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override // com.geely.lib.oneosapi.user.api.IUserInfo
    public boolean isGIDUser() {
        return this.isGIDUser;
    }

    public void setGIDUser(boolean GIDUser) {
        this.isGIDUser = GIDUser;
    }

    @Override // com.geely.lib.oneosapi.user.api.IUserInfo
    public String getAvatarUrl() {
        return this.avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    @Override // com.geely.lib.oneosapi.user.api.IUserInfo
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override // com.geely.lib.oneosapi.user.api.IUserInfo
    public String getBirthday() {
        return this.birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    @Override // com.geely.lib.oneosapi.user.api.IUserInfo
    public String getIsNoSecretLogin() {
        return this.isNoSecretLogin;
    }

    public void setIsNoSecretLogin(String isNoSecretLogin) {
        this.isNoSecretLogin = isNoSecretLogin;
    }

    public String toString() {
        return "UserInfo{mobile='" + this.mobile + "', name='" + this.name + "', Sex='" + this.Sex + "', age=" + this.age + ", dateOfBirth='" + this.dateOfBirth + "', userId='" + this.userId + "', carModel='" + this.carModel + "', level='" + this.level + "', levelName='" + this.levelName + "', address='" + this.address + "', nickname='" + this.nickname + "', isGIDUser=" + this.isGIDUser + ", avatarUrl='" + this.avatarUrl + "', email='" + this.email + "', birthday='" + this.birthday + "', accountType=" + this.accountType + ", isNoSecretLogin=" + this.isNoSecretLogin + '}';
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mobile);
        parcel.writeString(this.name);
        parcel.writeString(this.Sex);
        parcel.writeInt(this.age);
        parcel.writeString(this.dateOfBirth);
        parcel.writeString(this.userId);
        parcel.writeString(this.carModel);
        parcel.writeString(this.level);
        parcel.writeString(this.levelName);
        parcel.writeString(this.address);
        parcel.writeString(this.nickname);
        parcel.writeByte(this.isGIDUser ? (byte) 1 : (byte) 0);
        parcel.writeString(this.avatarUrl);
        parcel.writeString(this.email);
        parcel.writeString(this.birthday);
        parcel.writeString(this.accountType);
        parcel.writeString(this.isNoSecretLogin);
    }

    public void readFromParcel(Parcel source) {
        this.mobile = source.readString();
        this.name = source.readString();
        this.Sex = source.readString();
        this.age = source.readInt();
        this.dateOfBirth = source.readString();
        this.userId = source.readString();
        this.carModel = source.readString();
        this.level = source.readString();
        this.levelName = source.readString();
        this.address = source.readString();
        this.nickname = source.readString();
        this.isGIDUser = source.readByte() != 0;
        this.avatarUrl = source.readString();
        this.email = source.readString();
        this.birthday = source.readString();
        this.accountType = source.readString();
        this.isNoSecretLogin = source.readString();
    }
}