package com.geely.lib.oneosapi.navi.model.client;

import android.os.Parcel;
import android.os.Parcelable;
import com.geely.lib.oneosapi.navi.model.base.NaviBaseModel;
import com.geely.lib.oneosapi.navi.model.base.NaviProtocolID;

/* loaded from: classes.dex */
public class MapOperaSwitchTraffic extends NaviBaseModel implements Parcelable {
    public static final int ACTION_SWITCH_OFF = 1;
    public static final int ACTION_SWITCH_ON = 0;
    public static final Parcelable.Creator<MapOperaSwitchTraffic> CREATOR = new Parcelable.Creator<MapOperaSwitchTraffic>() { // from class: com.geely.lib.oneosapi.navi.model.client.MapOperaSwitchTraffic.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MapOperaSwitchTraffic createFromParcel(Parcel source) {
            return new MapOperaSwitchTraffic(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MapOperaSwitchTraffic[] newArray(int size) {
            return new MapOperaSwitchTraffic[size];
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

    public MapOperaSwitchTraffic() {
        this(0);
    }

    public MapOperaSwitchTraffic(int switchAction) {
        setProtocolID(NaviProtocolID.MAP_OP_SWITCH_TRAFFIC);
        this.action = switchAction;
    }

    protected MapOperaSwitchTraffic(Parcel in) {
        super(in);
        this.action = in.readInt();
    }

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel
    public String toString() {
        return "MapOperaSwitchTraffic{action=" + this.action + "{base=" + super.toString() + "}; }";
    }
}