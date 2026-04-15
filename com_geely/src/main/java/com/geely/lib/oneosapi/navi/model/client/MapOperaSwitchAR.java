package com.geely.lib.oneosapi.navi.model.client;

import android.os.Parcel;
import android.os.Parcelable;
import com.geely.lib.oneosapi.navi.model.base.NaviBaseModel;
import com.geely.lib.oneosapi.navi.model.base.NaviProtocolID;

/* loaded from: classes.dex */
public class MapOperaSwitchAR extends NaviBaseModel implements Parcelable {
    public static final int ACTION_SWITCH_OFF = 1;
    public static final int ACTION_SWITCH_ON = 0;
    public static final Parcelable.Creator<MapOperaSwitchAR> CREATOR = new Parcelable.Creator<MapOperaSwitchAR>() { // from class: com.geely.lib.oneosapi.navi.model.client.MapOperaSwitchAR.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MapOperaSwitchAR createFromParcel(Parcel source) {
            return new MapOperaSwitchAR(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MapOperaSwitchAR[] newArray(int size) {
            return new MapOperaSwitchAR[size];
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

    public MapOperaSwitchAR() {
        this(0);
    }

    public MapOperaSwitchAR(int switchAction) {
        setProtocolID(NaviProtocolID.MAP_OP_SWITCH_AR);
        this.action = switchAction;
    }

    protected MapOperaSwitchAR(Parcel in) {
        super(in);
        this.action = in.readInt();
    }

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel
    public String toString() {
        return "MapOperaSwitchAR{action=" + this.action + "{base=" + super.toString() + "}; }";
    }
}