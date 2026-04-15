package com.geely.lib.oneosapi.mediacenter.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class SearchResult implements Parcelable {
    public static final Parcelable.Creator<SearchResult> CREATOR = new Parcelable.Creator<SearchResult>() { // from class: com.geely.lib.oneosapi.mediacenter.bean.SearchResult.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SearchResult createFromParcel(Parcel in) {
            return new SearchResult(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SearchResult[] newArray(int size) {
            return new SearchResult[size];
        }
    };
    public String SongName;
    public String albumPic;
    public String singer;
    public String songId;
    public String unplayableReason;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public SearchResult() {
    }

    protected SearchResult(Parcel in) {
        this.albumPic = in.readString();
        this.singer = in.readString();
        this.songId = in.readString();
        this.SongName = in.readString();
        this.unplayableReason = in.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.albumPic);
        dest.writeString(this.singer);
        dest.writeString(this.songId);
        dest.writeString(this.SongName);
        dest.writeString(this.unplayableReason);
    }
}