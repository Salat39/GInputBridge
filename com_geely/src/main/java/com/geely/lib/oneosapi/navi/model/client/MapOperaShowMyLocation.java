package com.geely.lib.oneosapi.navi.model.client;

import android.os.Parcel;
import android.os.Parcelable;
import com.geely.lib.oneosapi.navi.model.base.NaviBaseModel;
import com.geely.lib.oneosapi.navi.model.base.NaviProtocolID;

/* loaded from: classes.dex */
public class MapOperaShowMyLocation extends NaviBaseModel {
    public static final int ACTION_JUST_INFO = 1;
    public static final int ACTION_SHOW_IN_MAP = 0;
    public static final Parcelable.Creator<MapOperaShowMyLocation> CREATOR = new Parcelable.Creator<MapOperaShowMyLocation>() { // from class: com.geely.lib.oneosapi.navi.model.client.MapOperaShowMyLocation.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MapOperaShowMyLocation createFromParcel(Parcel source) {
            return new MapOperaShowMyLocation(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MapOperaShowMyLocation[] newArray(int size) {
            return new MapOperaShowMyLocation[size];
        }
    };
    private int action;

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int getAction() {
        return this.action;
    }

    public void setAction(int action) {
        this.action = action;
    }

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel, android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeInt(this.action);
    }

    public MapOperaShowMyLocation() {
        this(0);
    }

    public MapOperaShowMyLocation(int action) {
        this.action = action;
        setProtocolID(NaviProtocolID.MAP_OP_SHOW_MY_LOCATION);
    }

    protected MapOperaShowMyLocation(Parcel in) {
        super(in);
        this.action = in.readInt();
    }

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel
    public String toString() {
        return "MapOperaShowMyLocation{action=" + this.action + "{base=" + super.toString() + "}; }";
    }
}