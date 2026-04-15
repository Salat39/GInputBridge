package com.geely.lib.oneosapi.navi.entitys;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

/* loaded from: classes.dex */
public class RoadConditions implements Parcelable {
    public static final Parcelable.Creator<RoadConditions> CREATOR = new Parcelable.Creator<RoadConditions>() { // from class: com.geely.lib.oneosapi.navi.entitys.RoadConditions.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RoadConditions createFromParcel(Parcel source) {
            return new RoadConditions(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RoadConditions[] newArray(int size) {
            return new RoadConditions[size];
        }
    };
    public static final int TMC_STATUS_AMBLE = 2;
    public static final int TMC_STATUS_INVALID = -1;
    public static final int TMC_STATUS_NO_TRAFFIC = 0;
    public static final int TMC_STATUS_TRAFFIC_CLEAR = 1;
    public static final int TMC_STATUS_TRAFFIC_JAM = 3;
    public static final int TMC_STATUS_TRAFFIC_JAM_HEAVY = 4;
    public static final int TMC_STATUS_TRAVELED = 10;
    private int mFinishDistance;
    private int mRemainDistance;
    private List<TMCSegment> mTMCSegmentList;
    private int mTotalDistance;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public static class TMCSegment implements Parcelable {
        public static final Parcelable.Creator<TMCSegment> CREATOR = new Parcelable.Creator<TMCSegment>() { // from class: com.geely.lib.oneosapi.navi.entitys.RoadConditions.TMCSegment.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public TMCSegment createFromParcel(Parcel source) {
                return new TMCSegment(source);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public TMCSegment[] newArray(int size) {
                return new TMCSegment[size];
            }
        };
        private int mSegDistance;
        private int mSegNum;
        private int mSegPercent;
        private int mTMCStatus;

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.mSegNum);
            dest.writeInt(this.mTMCStatus);
            dest.writeInt(this.mSegDistance);
            dest.writeInt(this.mSegPercent);
        }

        public TMCSegment() {
        }

        protected TMCSegment(Parcel in) {
            this.mSegNum = in.readInt();
            this.mTMCStatus = in.readInt();
            this.mSegDistance = in.readInt();
            this.mSegPercent = in.readInt();
        }

        public int getSegNum() {
            return this.mSegNum;
        }

        public int getTMCStatus() {
            return this.mTMCStatus;
        }

        public int getSegDistance() {
            return this.mSegDistance;
        }

        public int getSegPercent() {
            return this.mSegPercent;
        }

        public void setSegNum(int segNum) {
            this.mSegNum = segNum;
        }

        public void setTMCStatus(int tmcStatus) {
            this.mTMCStatus = tmcStatus;
        }

        public void setSegDistance(int segDistance) {
            this.mSegDistance = segDistance;
        }

        public void setSegPercent(int segPercent) {
            this.mSegPercent = segPercent;
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mTotalDistance);
        dest.writeInt(this.mRemainDistance);
        dest.writeInt(this.mFinishDistance);
        dest.writeTypedList(this.mTMCSegmentList);
    }

    public RoadConditions() {
    }

    protected RoadConditions(Parcel in) {
        this.mTotalDistance = in.readInt();
        this.mRemainDistance = in.readInt();
        this.mFinishDistance = in.readInt();
        this.mTMCSegmentList = in.createTypedArrayList(TMCSegment.CREATOR);
    }

    public int getTotalDistance() {
        return this.mTotalDistance;
    }

    public int getRemainDistance() {
        return this.mRemainDistance;
    }

    public int getFinishDistance() {
        return this.mFinishDistance;
    }

    public List<TMCSegment> getTMCSegmentList() {
        return this.mTMCSegmentList;
    }

    public void setTotalDistance(int totalDistance) {
        this.mTotalDistance = totalDistance;
    }

    public void setRemainDistance(int remainDistance) {
        this.mRemainDistance = remainDistance;
    }

    public void setFinishDistance(int finishDistance) {
        this.mFinishDistance = finishDistance;
    }

    public void setTMCSegmentList(List<TMCSegment> tmcSegmentList) {
        this.mTMCSegmentList = tmcSegmentList;
    }
}