package com.geely.lib.oneosapi.navi.model.service;

import android.os.Parcel;
import android.os.Parcelable;
import com.geely.lib.oneosapi.navi.model.base.NaviBaseModel;
import com.geely.lib.oneosapi.navi.model.base.NaviProtocolID;
import com.geely.lib.oneosapi.navi.model.base.PoiInfo;

/* loaded from: classes.dex */
public class RspSearchLastDeparture extends NaviBaseModel implements Parcelable {
    public static final Parcelable.Creator<RspSearchLastDeparture> CREATOR = new Parcelable.Creator<RspSearchLastDeparture>() { // from class: com.geely.lib.oneosapi.navi.model.service.RspSearchLastDeparture.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RspSearchLastDeparture createFromParcel(Parcel source) {
            return new RspSearchLastDeparture(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RspSearchLastDeparture[] newArray(int size) {
            return new RspSearchLastDeparture[size];
        }
    };
    private PoiInfo poiInfo;

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public RspSearchLastDeparture(PoiInfo poiInfo) {
        this.poiInfo = poiInfo;
        setProtocolID(NaviProtocolID.SEARCH_LAST_DEPARTURE);
    }

    public PoiInfo getPoiInfo() {
        return this.poiInfo;
    }

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel, android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeParcelable(this.poiInfo, flags);
    }

    protected RspSearchLastDeparture(Parcel in) {
        super(in);
        this.poiInfo = (PoiInfo) in.readParcelable(PoiInfo.class.getClassLoader());
    }

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel
    public String toString() {
        return "SearchLastDeparture{poiName=" + this.poiInfo.getName() + ", LatLng=" + this.poiInfo.getLatLng() + "{base=" + super.toString() + "}; }";
    }
}