package com.geely.lib.oneosapi.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class SingerItemBean implements Parcelable {
    public static final Parcelable.Creator<SingerItemBean> CREATOR = new Parcelable.Creator<SingerItemBean>() { // from class: com.geely.lib.oneosapi.bean.SingerItemBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SingerItemBean createFromParcel(Parcel in) {
            return new SingerItemBean(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SingerItemBean[] newArray(int size) {
            return new SingerItemBean[size];
        }
    };
    private String album_num;
    private long singer_id;
    private String singer_mid;
    private String singer_name;
    private String singer_pic;
    private String singer_title;
    private String song_num;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public SingerItemBean() {
    }

    protected SingerItemBean(Parcel in) {
        this.singer_id = in.readLong();
        this.singer_mid = in.readString();
        this.singer_name = in.readString();
        this.singer_pic = in.readString();
        this.album_num = in.readString();
        this.song_num = in.readString();
        this.singer_title = in.readString();
    }

    public String getAlbum_num() {
        return this.album_num;
    }

    public void setAlbum_num(String album_num) {
        this.album_num = album_num;
    }

    public String getSong_num() {
        return this.song_num;
    }

    public void setSong_num(String song_num) {
        this.song_num = song_num;
    }

    public String getSinger_pic() {
        return this.singer_pic;
    }

    public void setSinger_pic(String singer_pic) {
        this.singer_pic = singer_pic;
    }

    public String toString() {
        return "singer_id : " + this.singer_id + " singer_mid : " + this.singer_mid + " singer_name : " + this.singer_name + " singer_title " + this.singer_title;
    }

    public long getSinger_id() {
        return this.singer_id;
    }

    public void setSinger_id(long singer_id) {
        this.singer_id = singer_id;
    }

    public String getSinger_mid() {
        return this.singer_mid;
    }

    public void setSinger_mid(String singer_mid) {
        this.singer_mid = singer_mid;
    }

    public String getSinger_name() {
        return this.singer_name;
    }

    public void setSinger_name(String singer_name) {
        this.singer_name = singer_name;
    }

    public String getSinger_title() {
        return this.singer_title;
    }

    public void setSinger_title(String singer_title) {
        this.singer_title = singer_title;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.singer_id);
        dest.writeString(this.singer_mid);
        dest.writeString(this.singer_name);
        dest.writeString(this.singer_pic);
        dest.writeString(this.album_num);
        dest.writeString(this.song_num);
        dest.writeString(this.singer_title);
    }
}