package com.geely.lib.oneosapi.navi.model.service;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class RpsSearchPoi implements Parcelable {
    public static final Parcelable.Creator<RpsSearchPoi> CREATOR = new Parcelable.Creator<RpsSearchPoi>() { // from class: com.geely.lib.oneosapi.navi.model.service.RpsSearchPoi.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RpsSearchPoi createFromParcel(Parcel in) {
            return new RpsSearchPoi(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RpsSearchPoi[] newArray(int size) {
            return new RpsSearchPoi[size];
        }
    };
    private String mSearchPoiTags;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    protected RpsSearchPoi(Parcel in) {
        this.mSearchPoiTags = in.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mSearchPoiTags);
    }
}