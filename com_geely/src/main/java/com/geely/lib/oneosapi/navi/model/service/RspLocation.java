package com.geely.lib.oneosapi.navi.model.service;

import android.os.Parcel;
import android.os.Parcelable;
import com.geely.lib.oneosapi.navi.model.base.NaviBaseModel;

/* loaded from: classes.dex */
public class RspLocation extends NaviBaseModel implements Parcelable, Cloneable {
    public static final Parcelable.Creator<RspLocation> CREATOR = new Parcelable.Creator<RspLocation>() { // from class: com.geely.lib.oneosapi.navi.model.service.RspLocation.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RspLocation createFromParcel(Parcel source) {
            return new RspLocation(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RspLocation[] newArray(int size) {
            return new RspLocation[size];
        }
    };
    private LocationInfo locationInfo;

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public RspLocation(NaviBaseModel reqModel) {
        copyBaseInfo(reqModel);
    }

    public LocationInfo getLocationInfo() {
        return this.locationInfo;
    }

    public void setLocationInfo(LocationInfo locationInfo) {
        this.locationInfo = locationInfo;
    }

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel, android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeParcelable(this.locationInfo, flags);
    }

    protected RspLocation(Parcel in) {
        super(in);
        this.locationInfo = (LocationInfo) in.readParcelable(LocationInfo.class.getClassLoader());
    }

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel
    public String toString() {
        return "RspLocation{locationInfo=" + this.locationInfo + "{base=" + super.toString() + "}; }";
    }

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel
    /* renamed from: clone */
    public RspLocation mo324clone() {
        RspLocation rspLocation = (RspLocation) super.mo324clone();
        LocationInfo locationInfo = this.locationInfo;
        if (locationInfo != null) {
            rspLocation.locationInfo = locationInfo.m328clone();
        }
        return rspLocation;
    }
}