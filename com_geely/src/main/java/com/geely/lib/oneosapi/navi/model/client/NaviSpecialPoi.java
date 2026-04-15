package com.geely.lib.oneosapi.navi.model.client;

import android.os.Parcel;
import android.os.Parcelable;
import com.geely.lib.oneosapi.navi.model.base.NaviBaseModel;
import com.geely.lib.oneosapi.navi.model.base.NaviProtocolID;

/* loaded from: classes.dex */
public class NaviSpecialPoi extends NaviBaseModel implements Parcelable {
    public static final int ACTION_NAVI = 0;
    public static final int ACTION_ROUTE_PLANNING = 1;
    public static final Parcelable.Creator<NaviSpecialPoi> CREATOR = new Parcelable.Creator<NaviSpecialPoi>() { // from class: com.geely.lib.oneosapi.navi.model.client.NaviSpecialPoi.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NaviSpecialPoi createFromParcel(Parcel source) {
            return new NaviSpecialPoi(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NaviSpecialPoi[] newArray(int size) {
            return new NaviSpecialPoi[size];
        }
    };
    public static final int POI_TYPE_COMPANY = 1;
    public static final int POI_TYPE_HOME = 0;
    private int action;
    private int poiType;

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public NaviSpecialPoi(int poiType, int action) {
        this.poiType = poiType;
        this.action = action;
        setProtocolID(NaviProtocolID.NAVI_OP_SPECIAL_POI);
    }

    public int getPoiType() {
        return this.poiType;
    }

    public int getAction() {
        return this.action;
    }

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel, android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeInt(this.poiType);
        dest.writeInt(this.action);
    }

    protected NaviSpecialPoi(Parcel in) {
        super(in);
        this.poiType = in.readInt();
        this.action = in.readInt();
    }

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel
    public String toString() {
        return "NaviSpecialPoi{poiType=" + this.poiType + ", action=" + this.action + "{base=" + super.toString() + "}; }";
    }
}