package com.geely.lib.oneosapi.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class BookInfo implements Parcelable {
    public static final Parcelable.Creator<BookInfo> CREATOR = new Parcelable.Creator<BookInfo>() { // from class: com.geely.lib.oneosapi.bean.BookInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BookInfo createFromParcel(Parcel in) {
            return new BookInfo(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BookInfo[] newArray(int size) {
            return new BookInfo[size];
        }
    };
    private int chapter_idx;
    private String chapter_name;
    private int chapter_offset;
    private int chapter_uid;
    private int chapter_word_cnt;
    private String endTTS;
    private int paid;
    private String startTTS;
    private long synckey;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public BookInfo() {
    }

    protected BookInfo(Parcel in) {
        this.synckey = in.readLong();
        this.chapter_uid = in.readInt();
        this.chapter_idx = in.readInt();
        this.chapter_name = in.readString();
        this.chapter_word_cnt = in.readInt();
        this.paid = in.readInt();
        this.chapter_offset = in.readInt();
        this.startTTS = in.readString();
        this.endTTS = in.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.synckey);
        dest.writeInt(this.chapter_uid);
        dest.writeInt(this.chapter_idx);
        dest.writeString(this.chapter_name);
        dest.writeInt(this.chapter_word_cnt);
        dest.writeInt(this.paid);
        dest.writeInt(this.chapter_offset);
        dest.writeString(this.startTTS);
        dest.writeString(this.endTTS);
    }
}