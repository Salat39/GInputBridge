package com.geely.lib.oneosapi.navi.model.base;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class SearchPoi extends PoiInfo {
    public static final Parcelable.Creator<SearchPoi> CREATOR = new Parcelable.Creator<SearchPoi>() { // from class: com.geely.lib.oneosapi.navi.model.base.SearchPoi.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SearchPoi createFromParcel(Parcel in) {
            return new SearchPoi(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SearchPoi[] newArray(int size) {
            return new SearchPoi[size];
        }
    };
    private int mSearchPoiTags;

    @Override // com.geely.lib.oneosapi.navi.model.base.PoiInfo, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public SearchPoi() {
    }

    public int getSearchPoiTags() {
        return this.mSearchPoiTags;
    }

    public void setSearchPoiTags(int mSearchPoiTags) {
        this.mSearchPoiTags = mSearchPoiTags;
    }

    public SearchPoi(Parcel in) {
        super(in);
        this.mSearchPoiTags = in.readInt();
    }

    @Override // com.geely.lib.oneosapi.navi.model.base.PoiInfo, android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeInt(this.mSearchPoiTags);
    }
}