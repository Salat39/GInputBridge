package com.geely.lib.oneosapi.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class MusicInfo implements Parcelable {
    public static final Parcelable.Creator<MusicInfo> CREATOR = new Parcelable.Creator<MusicInfo>() { // from class: com.geely.lib.oneosapi.bean.MusicInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MusicInfo createFromParcel(Parcel in) {
            return new MusicInfo(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MusicInfo[] newArray(int size) {
            return new MusicInfo[size];
        }
    };
    public long currentPlayTime;
    public boolean hasProgress;
    public String id;
    public boolean isFavor;
    public boolean isFavorAble;
    public String musicAuthor;
    public String musicName;
    public String musicPic;
    public long totalPlayTime;
    public String unplayCode;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public MusicInfo() {
        this.hasProgress = true;
    }

    protected MusicInfo(Parcel in) {
        this.hasProgress = true;
        this.musicName = in.readString();
        this.musicPic = in.readString();
        this.musicAuthor = in.readString();
        this.totalPlayTime = in.readLong();
        this.currentPlayTime = in.readLong();
        this.unplayCode = in.readString();
        this.id = in.readString();
        this.isFavorAble = in.readByte() != 0;
        this.isFavor = in.readByte() != 0;
        this.hasProgress = in.readByte() != 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.musicName);
        parcel.writeString(this.musicPic);
        parcel.writeString(this.musicAuthor);
        parcel.writeLong(this.totalPlayTime);
        parcel.writeLong(this.currentPlayTime);
        parcel.writeString(this.unplayCode);
        parcel.writeString(this.id);
        parcel.writeByte(this.isFavorAble ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.isFavor ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.hasProgress ? (byte) 1 : (byte) 0);
    }

    public String toString() {
        return "MusicInfo{musicName='" + this.musicName + "', musicPic='" + this.musicPic + "', musicAuthor='" + this.musicAuthor + "', totalPlayTime=" + this.totalPlayTime + ", currentPlayTime=" + this.currentPlayTime + ", unplayCode='" + this.unplayCode + "', id='" + this.id + "', isFavorAble=" + this.isFavorAble + ", isFavor=" + this.isFavor + ", hasProgress=" + this.hasProgress + '}';
    }
}