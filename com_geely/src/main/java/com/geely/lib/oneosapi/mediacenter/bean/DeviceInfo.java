package com.geely.lib.oneosapi.mediacenter.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class DeviceInfo implements Parcelable {
    public static final Parcelable.Creator<DeviceInfo> CREATOR = new Parcelable.Creator<DeviceInfo>() { // from class: com.geely.lib.oneosapi.mediacenter.bean.DeviceInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DeviceInfo createFromParcel(Parcel in) {
            return new DeviceInfo(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DeviceInfo[] newArray(int size) {
            return new DeviceInfo[size];
        }
    };
    public String address;
    public String name;
    public String path;
    public int source;
    public int state;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public DeviceInfo(int source, String name, String path, String address, int state) {
        this.source = source;
        this.name = name;
        this.path = path;
        this.address = address;
        this.state = state;
    }

    public DeviceInfo() {
    }

    protected DeviceInfo(Parcel in) {
        readFromParcel(in);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flag) {
        dest.writeInt(this.source);
        dest.writeString(this.name);
        dest.writeString(this.path);
        dest.writeString(this.address);
        dest.writeInt(this.state);
    }

    public void readFromParcel(Parcel in) {
        this.source = in.readInt();
        this.name = in.readString();
        this.path = in.readString();
        this.address = in.readString();
        this.state = in.readInt();
    }

    public String toString() {
        return "DeviceInfo{source=" + this.source + ", name='" + this.name + "', path='" + this.path + "', address='" + this.address + "', state=" + this.state + '}';
    }
}