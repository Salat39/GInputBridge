package com.geely.lib.oneosapi.navi.model.client;

import android.os.Parcel;
import android.os.Parcelable;
import com.geely.lib.oneosapi.navi.model.base.NaviBaseModel;
import com.geely.lib.oneosapi.navi.model.base.NaviProtocolID;

/* loaded from: classes.dex */
public class SpeedLimitInfo extends NaviBaseModel implements Parcelable {
    public static final Parcelable.Creator<SpeedLimitInfo> CREATOR = new Parcelable.Creator<SpeedLimitInfo>() { // from class: com.geely.lib.oneosapi.navi.model.client.SpeedLimitInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SpeedLimitInfo createFromParcel(Parcel source) {
            return new SpeedLimitInfo(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SpeedLimitInfo[] newArray(int size) {
            return new SpeedLimitInfo[size];
        }
    };
    public static final int ROAD_COUNTY = 3;
    public static final int ROAD_EXPRESS = 6;
    public static final int ROAD_HIGH_SPEED = 0;
    public static final int ROAD_NATION = 1;
    public static final int ROAD_NOMAL = 9;
    public static final int ROAD_NO_NAV = 10;
    public static final int ROAD_PRIMARY = 7;
    public static final int ROAD_PROV = 2;
    public static final int ROAD_SECONDARY = 8;
    public static final int ROAD_TOWN = 4;
    public static final int ROAD_VILLAGE = 5;
    private ElecLimitingInfo elecLimitingInfo;
    private JctWayInfo jctWayInfo;
    private RoadInfo roadInfo;

    public SpeedLimitInfo() {
        setProtocolID(NaviProtocolID.SPEED_LIMIT_INFO);
    }

    public ElecLimitingInfo getElecLimitingInfo() {
        return this.elecLimitingInfo;
    }

    public void setElecLimitingInfo(ElecLimitingInfo elecLimitingInfo) {
        this.elecLimitingInfo = elecLimitingInfo;
    }

    public JctWayInfo getJctWayInfo() {
        return this.jctWayInfo;
    }

    public void setJctWayInfo(JctWayInfo jctWayInfo) {
        this.jctWayInfo = jctWayInfo;
    }

    public RoadInfo getRoadInfo() {
        return this.roadInfo;
    }

    public void setRoadInfo(RoadInfo roadInfo) {
        this.roadInfo = roadInfo;
    }

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel, android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeParcelable(this.elecLimitingInfo, flags);
        dest.writeParcelable(this.jctWayInfo, flags);
        dest.writeParcelable(this.roadInfo, flags);
    }

    protected SpeedLimitInfo(Parcel in) {
        super(in);
        this.elecLimitingInfo = (ElecLimitingInfo) in.readParcelable(ElecLimitingInfo.class.getClassLoader());
        this.jctWayInfo = (JctWayInfo) in.readParcelable(JctWayInfo.class.getClassLoader());
        this.roadInfo = (RoadInfo) in.readParcelable(RoadInfo.class.getClassLoader());
    }

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel
    public String toString() {
        super.toString();
        StringBuilder sb = new StringBuilder("SpeedLimitInfo{");
        sb.append("elecLimitingInfo=");
        ElecLimitingInfo elecLimitingInfo = this.elecLimitingInfo;
        sb.append(elecLimitingInfo == null ? "" : elecLimitingInfo.toString());
        sb.append(", jctWayInfo='");
        JctWayInfo jctWayInfo = this.jctWayInfo;
        sb.append(jctWayInfo == null ? "" : jctWayInfo.toString());
        sb.append(", roadInfo='");
        RoadInfo roadInfo = this.roadInfo;
        sb.append(roadInfo != null ? roadInfo.toString() : "");
        sb.append('}');
        return sb.toString();
    }

    public static class ElecLimitingInfo implements Parcelable, Cloneable {
        public static final Parcelable.Creator<ElecLimitingInfo> CREATOR = new Parcelable.Creator<ElecLimitingInfo>() { // from class: com.geely.lib.oneosapi.navi.model.client.SpeedLimitInfo.ElecLimitingInfo.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public ElecLimitingInfo createFromParcel(Parcel in) {
                return new ElecLimitingInfo(in);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public ElecLimitingInfo[] newArray(int size) {
                return new ElecLimitingInfo[size];
            }
        };
        private int dist;
        private double latitude;
        private double longitude;
        private int speed;

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public double getLong() {
            return this.longitude;
        }

        public double getLat() {
            return this.latitude;
        }

        public int getDist() {
            return this.dist;
        }

        public int getSpeed() {
            return this.speed;
        }

        public ElecLimitingInfo(double lat, double lng, int dist, int speed) {
            this.latitude = lat;
            this.longitude = lng;
            this.dist = dist;
            this.speed = speed;
        }

        public double getLongitude() {
            return this.longitude;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeDouble(this.latitude);
            dest.writeDouble(this.longitude);
            dest.writeInt(this.dist);
            dest.writeInt(this.speed);
        }

        protected ElecLimitingInfo(Parcel in) {
            this.latitude = in.readDouble();
            this.longitude = in.readDouble();
            this.dist = in.readInt();
            this.speed = in.readInt();
        }

        public String toString() {
            return "ElecLimitingInfo{latitude=" + this.latitude + ", longitude=" + this.longitude + ", speed=" + this.speed + ", dist='" + this.dist + "'}";
        }

        /* renamed from: clone, reason: merged with bridge method [inline-methods] */
        public ElecLimitingInfo m325clone() {
            try {
                return (ElecLimitingInfo) super.clone();
            } catch (CloneNotSupportedException unused) {
                return new ElecLimitingInfo(0.0d, 0.0d, 0, 0);
            }
        }
    }

    public static class JctWayInfo implements Parcelable, Cloneable {
        public static final Parcelable.Creator<JctWayInfo> CREATOR = new Parcelable.Creator<JctWayInfo>() { // from class: com.geely.lib.oneosapi.navi.model.client.SpeedLimitInfo.JctWayInfo.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public JctWayInfo createFromParcel(Parcel in) {
                return new JctWayInfo(in);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public JctWayInfo[] newArray(int size) {
                return new JctWayInfo[size];
            }
        };
        private int dist;
        private double latitude;
        private double longitude;

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public double getLong() {
            return this.longitude;
        }

        public double getLat() {
            return this.latitude;
        }

        public int getDist() {
            return this.dist;
        }

        public JctWayInfo(double lat, double lng, int dist) {
            this.latitude = lat;
            this.longitude = lng;
            this.dist = dist;
        }

        public double getLongitude() {
            return this.longitude;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeDouble(this.latitude);
            dest.writeDouble(this.longitude);
            dest.writeInt(this.dist);
        }

        protected JctWayInfo(Parcel in) {
            this.latitude = in.readDouble();
            this.longitude = in.readDouble();
            this.dist = in.readInt();
        }

        public String toString() {
            return "JctWayInfo{latitude=" + this.latitude + ", longitude=" + this.longitude + ", dist='" + this.dist + "'}";
        }

        /* renamed from: clone, reason: merged with bridge method [inline-methods] */
        public JctWayInfo m326clone() {
            try {
                return (JctWayInfo) super.clone();
            } catch (CloneNotSupportedException unused) {
                return new JctWayInfo(0.0d, 0.0d, 0);
            }
        }
    }

    public static class RoadInfo implements Parcelable, Cloneable {
        public static final Parcelable.Creator<RoadInfo> CREATOR = new Parcelable.Creator<RoadInfo>() { // from class: com.geely.lib.oneosapi.navi.model.client.SpeedLimitInfo.RoadInfo.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public RoadInfo createFromParcel(Parcel in) {
                return new RoadInfo(in);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public RoadInfo[] newArray(int size) {
                return new RoadInfo[size];
            }
        };
        private int roadType;
        private String roadname;

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public RoadInfo(String roadname, int roadType) {
            this.roadname = roadname;
            this.roadType = roadType;
        }

        protected RoadInfo(Parcel in) {
            this.roadname = in.readString();
            this.roadType = in.readInt();
        }

        public String getRoadname() {
            return this.roadname;
        }

        public int getRoadType() {
            return this.roadType;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.roadname);
            dest.writeInt(this.roadType);
        }

        public String toString() {
            return "RoadInfo{roadname=" + this.roadname + ", roadType='" + this.roadType + "'}";
        }

        /* renamed from: clone, reason: merged with bridge method [inline-methods] */
        public RoadInfo m327clone() {
            try {
                return (RoadInfo) super.clone();
            } catch (CloneNotSupportedException unused) {
                return new RoadInfo("", 0);
            }
        }
    }
}