package com.geely.lib.oneosapi.navi.model.service;

import android.os.Parcel;
import android.os.Parcelable;
import com.geely.lib.oneosapi.navi.model.base.NaviBaseModel;

/* loaded from: classes.dex */
public class RspTrafficSummaryInfo extends NaviBaseModel implements Parcelable {
    public static final Parcelable.Creator<RspTrafficSummaryInfo> CREATOR = new Parcelable.Creator<RspTrafficSummaryInfo>() { // from class: com.geely.lib.oneosapi.navi.model.service.RspTrafficSummaryInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RspTrafficSummaryInfo createFromParcel(Parcel source) {
            return new RspTrafficSummaryInfo(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RspTrafficSummaryInfo[] newArray(int size) {
            return new RspTrafficSummaryInfo[size];
        }
    };
    private String trafficSummaryInfo;

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public RspTrafficSummaryInfo(NaviBaseModel reqModel) {
        copyBaseInfo(reqModel);
    }

    public String getTrafficSummaryInfo() {
        return this.trafficSummaryInfo;
    }

    public void setTrafficSummaryInfo(String trafficSummaryInfo) {
        this.trafficSummaryInfo = trafficSummaryInfo;
    }

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel, android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(this.trafficSummaryInfo);
    }

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel
    public void readFromParcel(Parcel in) {
        super.readFromParcel(in);
        this.trafficSummaryInfo = in.readString();
    }

    protected RspTrafficSummaryInfo(Parcel in) {
        super(in);
        this.trafficSummaryInfo = in.readString();
    }

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel
    public String toString() {
        return "RspTrafficSummaryInfo{trafficSummaryInfo=" + this.trafficSummaryInfo + "{base=" + super.toString() + "}; }";
    }
}