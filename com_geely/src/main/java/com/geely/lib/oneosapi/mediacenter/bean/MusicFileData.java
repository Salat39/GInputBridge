package com.geely.lib.oneosapi.mediacenter.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class MusicFileData implements Parcelable {
    public static final Parcelable.Creator<MusicFileData> CREATOR = new Parcelable.Creator<MusicFileData>() { // from class: com.geely.lib.oneosapi.mediacenter.bean.MusicFileData.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MusicFileData createFromParcel(Parcel in) {
            return new MusicFileData(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MusicFileData[] newArray(int size) {
            return new MusicFileData[size];
        }
    };
    public String absolutePath;
    public String artist;
    public long duration;
    public int fileCount;
    public boolean isFolder;
    public String name;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public MusicFileData() {
    }

    protected MusicFileData(Parcel in) {
        readFromParcel(in);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.isFolder ? 1 : 0);
        parcel.writeString(this.name);
        parcel.writeString(this.absolutePath);
        parcel.writeInt(this.fileCount);
        parcel.writeString(this.artist);
        parcel.writeLong(this.duration);
    }

    public void readFromParcel(Parcel in) {
        this.isFolder = in.readInt() != 0;
        this.name = in.readString();
        this.absolutePath = in.readString();
        this.fileCount = in.readInt();
        this.artist = in.readString();
        this.duration = in.readLong();
    }
}