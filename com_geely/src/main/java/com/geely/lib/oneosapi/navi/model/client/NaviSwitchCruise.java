package com.geely.lib.oneosapi.navi.model.client;

import android.os.Parcel;
import android.os.Parcelable;
import com.geely.lib.oneosapi.navi.model.base.NaviBaseModel;
import com.geely.lib.oneosapi.navi.model.base.NaviProtocolID;

/* loaded from: classes.dex */
public class NaviSwitchCruise extends NaviBaseModel implements Parcelable {
    public static final int ACTION_SWITCH_OFF = 1;
    public static final int ACTION_SWITCH_ON = 0;
    public static final Parcelable.Creator<NaviSwitchCruise> CREATOR = new Parcelable.Creator<NaviSwitchCruise>() { // from class: com.geely.lib.oneosapi.navi.model.client.NaviSwitchCruise.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NaviSwitchCruise createFromParcel(Parcel source) {
            return new NaviSwitchCruise(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NaviSwitchCruise[] newArray(int size) {
            return new NaviSwitchCruise[size];
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

    public NaviSwitchCruise() {
        this(0);
    }

    public NaviSwitchCruise(int switchAction) {
        setProtocolID(NaviProtocolID.NAVI_OP_SWITCH_CRUISE);
        this.action = switchAction;
    }

    protected NaviSwitchCruise(Parcel in) {
        super(in);
        this.action = in.readInt();
    }

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel
    public String toString() {
        return "NaviSwitchCruise{action=" + this.action + "{base=" + super.toString() + "}; }";
    }
}