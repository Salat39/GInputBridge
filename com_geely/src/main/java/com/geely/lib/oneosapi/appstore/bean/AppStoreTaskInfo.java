package com.geely.lib.oneosapi.appstore.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class AppStoreTaskInfo implements Parcelable {
    public static final Parcelable.Creator<AppStoreTaskInfo> CREATOR = new Parcelable.Creator<AppStoreTaskInfo>() { // from class: com.geely.lib.oneosapi.appstore.bean.AppStoreTaskInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AppStoreTaskInfo createFromParcel(Parcel in) {
            return new AppStoreTaskInfo(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AppStoreTaskInfo[] newArray(int size) {
            return new AppStoreTaskInfo[size];
        }
    };
    public String appName;
    public float downloadProgress;
    public String expand;
    public String hash;
    public String iconUrl;
    public String id;
    public float installProgress;
    public String packageName;
    public String path;
    public long soFar;
    public int status;
    public long total;
    public int type;
    public String url;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public AppStoreTaskInfo() {
    }

    protected AppStoreTaskInfo(Parcel in) {
        readFromParcel(in);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.packageName);
        dest.writeString(this.appName);
        dest.writeFloat(this.installProgress);
        dest.writeFloat(this.downloadProgress);
        dest.writeString(this.iconUrl);
        dest.writeString(this.url);
        dest.writeString(this.path);
        dest.writeString(this.hash);
        dest.writeInt(this.type);
        dest.writeString(this.expand);
        dest.writeInt(this.status);
        dest.writeLong(this.soFar);
        dest.writeLong(this.total);
    }

    public void readFromParcel(Parcel in) {
        this.id = in.readString();
        this.packageName = in.readString();
        this.appName = in.readString();
        this.installProgress = in.readFloat();
        this.downloadProgress = in.readFloat();
        this.iconUrl = in.readString();
        this.url = in.readString();
        this.path = in.readString();
        this.hash = in.readString();
        this.type = in.readInt();
        this.expand = in.readString();
        this.status = in.readInt();
        this.soFar = in.readLong();
        this.total = in.readLong();
    }
}