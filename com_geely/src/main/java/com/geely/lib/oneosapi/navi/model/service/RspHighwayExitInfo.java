package com.geely.lib.oneosapi.navi.model.service;

import android.os.Parcel;
import android.os.Parcelable;
import com.geely.lib.oneosapi.navi.model.base.NaviBaseModel;

/* loaded from: classes.dex */
public class RspHighwayExitInfo extends NaviBaseModel implements Parcelable {
    public static final Parcelable.Creator<RspHighwayExitInfo> CREATOR = new Parcelable.Creator<RspHighwayExitInfo>() { // from class: com.geely.lib.oneosapi.navi.model.service.RspHighwayExitInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RspHighwayExitInfo createFromParcel(Parcel source) {
            return new RspHighwayExitInfo(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RspHighwayExitInfo[] newArray(int size) {
            return new RspHighwayExitInfo[size];
        }
    };
    private static final int CUR_VERSION = 2;
    private long etaDistance;
    private long etaTime;
    private int exitCount;
    private String exitDirection;
    private String exitNumStr;

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public RspHighwayExitInfo(NaviBaseModel reqModel) {
        copyBaseInfo(reqModel);
        setProtocolVersion(2);
    }

    public int getExitCount() {
        return this.exitCount;
    }

    public void setExitCount(int exitCount) {
        this.exitCount = exitCount;
    }

    public String getExitNumStr() {
        return this.exitNumStr;
    }

    public void setExitNumStr(String exitNumStr) {
        this.exitNumStr = exitNumStr;
    }

    public String getExitDirection() {
        return this.exitDirection;
    }

    public void setExitDirection(String exitDirection) {
        this.exitDirection = exitDirection;
    }

    public long getEtaDistance() {
        return this.etaDistance;
    }

    public void setEtaDistance(long etaDistance) {
        this.etaDistance = etaDistance;
    }

    public long getEtaTime() {
        return this.etaTime;
    }

    public void setEtaTime(long etaTime) {
        this.etaTime = etaTime;
    }

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel, android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(this.exitNumStr);
        dest.writeString(this.exitDirection);
        dest.writeLong(this.etaDistance);
        dest.writeLong(this.etaTime);
        if (getProtocolVersion() > 1) {
            dest.writeInt(this.exitCount);
        }
    }

    protected RspHighwayExitInfo(Parcel in) {
        super(in);
        this.exitNumStr = in.readString();
        this.exitDirection = in.readString();
        this.etaDistance = in.readLong();
        this.etaTime = in.readLong();
        if (getProtocolVersion() > 1) {
            this.exitCount = in.readInt();
        }
    }

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel
    public String toString() {
        return "RspHighwayExitInfo{exitCount='" + this.exitCount + "', exitNumStr='" + this.exitNumStr + "', exitDirection='" + this.exitDirection + "', etaDistance=" + this.etaDistance + ", etaTime=" + this.etaTime + "{base=" + super.toString() + "}; }";
    }
}