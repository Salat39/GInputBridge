package com.geely.lib.oneosapi.navi.model.service;

import android.os.Parcel;
import android.os.Parcelable;
import com.geely.lib.oneosapi.navi.model.base.NaviBaseModel;

/* loaded from: classes.dex */
public class RspNaviStatus extends NaviBaseModel implements Parcelable, Cloneable {
    public static final Parcelable.Creator<RspNaviStatus> CREATOR = new Parcelable.Creator<RspNaviStatus>() { // from class: com.geely.lib.oneosapi.navi.model.service.RspNaviStatus.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RspNaviStatus createFromParcel(Parcel var1) {
            return new RspNaviStatus(var1);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RspNaviStatus[] newArray(int var1) {
            return new RspNaviStatus[var1];
        }
    };
    private NaviStatus naviStatus;

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    protected RspNaviStatus(Parcel var1) {
        super(var1);
        this.naviStatus = (NaviStatus) var1.readParcelable(NaviStatus.class.getClassLoader());
    }

    public RspNaviStatus(NaviBaseModel var1) {
        copyBaseInfo(var1);
    }

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel
    /* renamed from: clone */
    public RspNaviStatus mo324clone() {
        RspNaviStatus rspNaviStatus = (RspNaviStatus) super.mo324clone();
        NaviStatus naviStatus = this.naviStatus;
        if (naviStatus != null) {
            rspNaviStatus.naviStatus = naviStatus.m329clone();
        }
        return rspNaviStatus;
    }

    public NaviStatus getNaviStatus() {
        return this.naviStatus;
    }

    public void setNaviStatus(NaviStatus var1) {
        this.naviStatus = var1;
    }

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel
    public String toString() {
        return "RspNaviStatus{naviStatus=" + this.naviStatus + "{base=" + super.toString() + "}; }";
    }

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel, android.os.Parcelable
    public void writeToParcel(Parcel var1, int var2) {
        super.writeToParcel(var1, var2);
        var1.writeParcelable(this.naviStatus, var2);
    }
}