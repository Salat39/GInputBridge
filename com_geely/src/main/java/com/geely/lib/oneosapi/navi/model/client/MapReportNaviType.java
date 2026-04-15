package com.geely.lib.oneosapi.navi.model.client;

import android.os.Parcel;
import android.os.Parcelable;
import com.geely.lib.oneosapi.navi.model.base.NaviBaseModel;
import com.geely.lib.oneosapi.navi.model.base.NaviProtocolID;

/* loaded from: classes.dex */
public class MapReportNaviType extends NaviBaseModel implements Parcelable {
    public static final Parcelable.Creator<MapReportNaviType> CREATOR = new Parcelable.Creator<MapReportNaviType>() { // from class: com.geely.lib.oneosapi.navi.model.client.MapReportNaviType.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MapReportNaviType createFromParcel(Parcel source) {
            return new MapReportNaviType(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MapReportNaviType[] newArray(int size) {
            return new MapReportNaviType[size];
        }
    };
    public static final int TYPE_CONCISE = 0;
    public static final int TYPE_DETAIL = 1;
    public static final int TYPE_MUTE = 2;
    private int reportNaviType;

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public MapReportNaviType(int reportNaviType) {
        this.reportNaviType = reportNaviType;
        setProtocolID(NaviProtocolID.MAP_OP_SET_REPORT_NAVI_TYPE);
    }

    public int getReportNaviType() {
        return this.reportNaviType;
    }

    public void setReportNaviType(int reportNaviType) {
        this.reportNaviType = reportNaviType;
    }

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel, android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeInt(this.reportNaviType);
    }

    protected MapReportNaviType(Parcel in) {
        super(in);
        this.reportNaviType = in.readInt();
    }

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel
    public String toString() {
        super.toString();
        return "MapReportNaviType{reportNaviType=" + this.reportNaviType + '}';
    }
}