package com.geely.lib.oneosapi.navi.model.service;

import android.os.Parcel;
import android.os.Parcelable;
import com.geely.lib.oneosapi.navi.model.base.NaviBaseModel;

/* loaded from: classes.dex */
public class RspMapStatus extends NaviBaseModel implements Parcelable {
    public static final Parcelable.Creator<RspMapStatus> CREATOR = new Parcelable.Creator<RspMapStatus>() { // from class: com.geely.lib.oneosapi.navi.model.service.RspMapStatus.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RspMapStatus createFromParcel(Parcel var1) {
            return new RspMapStatus(var1);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RspMapStatus[] newArray(int var1) {
            return new RspMapStatus[var1];
        }
    };
    private int mapStatus;

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int getMapStatus() {
        return this.mapStatus;
    }

    public void setMapStatus(int mapStatus) {
        this.mapStatus = mapStatus;
    }

    public RspMapStatus(NaviBaseModel var1) {
        copyBaseInfo(var1);
    }

    protected RspMapStatus(Parcel parcel) {
        super(parcel);
        this.mapStatus = parcel.readInt();
    }

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel, android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeInt(this.mapStatus);
    }

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel
    public String toString() {
        super.toString();
        return "RspMapStatus{mapStatus=" + this.mapStatus + '}';
    }
}