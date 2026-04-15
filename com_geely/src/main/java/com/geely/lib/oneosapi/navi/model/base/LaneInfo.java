package com.geely.lib.oneosapi.navi.model.base;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class LaneInfo implements Parcelable {
    public static final Parcelable.Creator<LaneInfo> CREATOR = new Parcelable.Creator<LaneInfo>() { // from class: com.geely.lib.oneosapi.navi.model.base.LaneInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public LaneInfo createFromParcel(Parcel source) {
            return new LaneInfo(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public LaneInfo[] newArray(int size) {
            return new LaneInfo[size];
        }
    };
    private int laneIconId;
    private int laneIndex;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public LaneInfo() {
    }

    public int getLaneIndex() {
        return this.laneIndex;
    }

    public void setLaneIndex(int laneIndex) {
        this.laneIndex = laneIndex;
    }

    public int getLaneIconId() {
        return this.laneIconId;
    }

    public void setLaneIconId(int laneIconId) {
        this.laneIconId = laneIconId;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.laneIndex);
        dest.writeInt(this.laneIconId);
    }

    protected LaneInfo(Parcel in) {
        this.laneIndex = in.readInt();
        this.laneIconId = in.readInt();
    }

    public String toString() {
        return "LaneInfo{laneIndex='" + this.laneIndex + "', laneIconId='" + this.laneIconId + "'}";
    }
}