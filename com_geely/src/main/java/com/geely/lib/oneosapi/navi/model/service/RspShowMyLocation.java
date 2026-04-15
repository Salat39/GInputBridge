package com.geely.lib.oneosapi.navi.model.service;

import android.os.Parcel;
import android.os.Parcelable;
import com.geely.lib.oneosapi.navi.model.base.NaviBaseModel;

/* loaded from: classes.dex */
public class RspShowMyLocation extends NaviBaseModel implements Parcelable {
    public static final Parcelable.Creator<RspShowMyLocation> CREATOR = new Parcelable.Creator<RspShowMyLocation>() { // from class: com.geely.lib.oneosapi.navi.model.service.RspShowMyLocation.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RspShowMyLocation createFromParcel(Parcel source) {
            return new RspShowMyLocation(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RspShowMyLocation[] newArray(int size) {
            return new RspShowMyLocation[size];
        }
    };
    private String locationInfo;

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public RspShowMyLocation(NaviBaseModel reqModel) {
        copyBaseInfo(reqModel);
    }

    public String getLocationInfo() {
        return this.locationInfo;
    }

    public void setLocationInfo(String locationInfo) {
        this.locationInfo = locationInfo;
    }

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel, android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(this.locationInfo);
    }

    protected RspShowMyLocation(Parcel in) {
        super(in);
        this.locationInfo = in.readString();
    }

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel
    public String toString() {
        return "RspShowMyLocation{locationInfo=" + this.locationInfo + "{base=" + super.toString() + "}; }";
    }
}