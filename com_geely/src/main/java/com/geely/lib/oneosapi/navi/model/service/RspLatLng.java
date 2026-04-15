package com.geely.lib.oneosapi.navi.model.service;

import android.os.Parcel;
import android.os.Parcelable;
import com.geely.lib.oneosapi.navi.model.base.LatLng;
import com.geely.lib.oneosapi.navi.model.base.NaviBaseModel;

/* loaded from: classes.dex */
public class RspLatLng extends NaviBaseModel implements Parcelable {
    public static final Parcelable.Creator<RspLatLng> CREATOR = new Parcelable.Creator<RspLatLng>() { // from class: com.geely.lib.oneosapi.navi.model.service.RspLatLng.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RspLatLng createFromParcel(Parcel in) {
            return new RspLatLng(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RspLatLng[] newArray(int size) {
            return new RspLatLng[size];
        }
    };
    private LatLng mLatlng;

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    protected RspLatLng(Parcel in) {
        this.mLatlng = (LatLng) in.readParcelable(LatLng.class.getClassLoader());
    }

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel, android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeParcelable(this.mLatlng, flags);
    }

    public LatLng getmLatlng() {
        return this.mLatlng;
    }

    public void setmLatlng(LatLng mLatlng) {
        this.mLatlng = mLatlng;
    }
}