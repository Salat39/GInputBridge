package com.geely.lib.oneosapi.navi.model.client;

import android.os.Parcel;
import android.os.Parcelable;
import com.geely.lib.oneosapi.navi.model.base.NaviBaseModel;
import com.geely.lib.oneosapi.navi.model.base.NaviProtocolID;

/* loaded from: classes.dex */
public class NaviChangeRoadType extends NaviBaseModel implements Parcelable {
    public static final Parcelable.Creator<NaviChangeRoadType> CREATOR = new Parcelable.Creator<NaviChangeRoadType>() { // from class: com.geely.lib.oneosapi.navi.model.client.NaviChangeRoadType.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NaviChangeRoadType createFromParcel(Parcel source) {
            return new NaviChangeRoadType(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NaviChangeRoadType[] newArray(int size) {
            return new NaviChangeRoadType[size];
        }
    };
    public static final int ROAD_TYPE_MAIN_ROAD = 0;
    public static final int ROAD_TYPE_ON_BRIDGE = 2;
    public static final int ROAD_TYPE_SIDE_ROAD = 1;
    public static final int ROAD_TYPE_UNDER_BRIDGE = 3;
    private int roadType;

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int getRoadType() {
        return this.roadType;
    }

    public void setRoadType(int action) {
        this.roadType = action;
    }

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel, android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeInt(this.roadType);
    }

    public NaviChangeRoadType() {
        this(0);
    }

    public NaviChangeRoadType(int roadType) {
        this.roadType = 0;
        this.roadType = roadType;
        setProtocolID(NaviProtocolID.NAVI_OP_CHANGE_ROAD_TYPE);
    }

    protected NaviChangeRoadType(Parcel in) {
        super(in);
        this.roadType = 0;
        this.roadType = in.readInt();
    }

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel
    public String toString() {
        return "NaviChangeRoadType{roadType=" + this.roadType + "{base=" + super.toString() + "}; }";
    }
}