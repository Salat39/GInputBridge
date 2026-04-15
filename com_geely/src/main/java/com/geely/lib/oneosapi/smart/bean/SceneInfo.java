package com.geely.lib.oneosapi.smart.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class SceneInfo implements Parcelable {
    public static final int BRAND_JINGDONG = 2;
    public static final int BRAND_XIAODU = 1;
    public static final Parcelable.Creator<SceneInfo> CREATOR = new Parcelable.Creator<SceneInfo>() { // from class: com.geely.lib.oneosapi.smart.bean.SceneInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SceneInfo createFromParcel(Parcel source) {
            return new SceneInfo(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SceneInfo[] newArray(int size) {
            return new SceneInfo[size];
        }
    };
    private int brand;
    private String name;
    private int openStatus;
    private String sceneId;
    private String type;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SceneInfo() {
    }

    public String getSceneId() {
        return this.sceneId;
    }

    public void setSceneId(String sceneId) {
        this.sceneId = sceneId;
    }

    public int getOpenStatus() {
        return this.openStatus;
    }

    public void setOpenStatus(int openStatus) {
        this.openStatus = openStatus;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getBrand() {
        return this.brand;
    }

    public void setBrand(int brand) {
        this.brand = brand;
    }

    public String toString() {
        return "SceneInfo{sceneId='" + this.sceneId + "', name='" + this.name + "', openStatus=" + this.openStatus + ", type='" + this.type + "', brand=" + this.brand + '}';
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.sceneId);
        dest.writeString(this.name);
        dest.writeInt(this.openStatus);
        dest.writeString(this.type);
        dest.writeInt(this.brand);
    }

    public void readFromParcel(Parcel source) {
        this.sceneId = source.readString();
        this.name = source.readString();
        this.openStatus = source.readInt();
        this.type = source.readString();
        this.brand = source.readInt();
    }

    protected SceneInfo(Parcel in) {
        this.sceneId = in.readString();
        this.name = in.readString();
        this.openStatus = in.readInt();
        this.type = in.readString();
        this.brand = in.readInt();
    }
}