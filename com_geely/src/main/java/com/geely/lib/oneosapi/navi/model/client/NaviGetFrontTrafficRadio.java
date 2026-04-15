package com.geely.lib.oneosapi.navi.model.client;

import android.os.Parcel;
import android.os.Parcelable;
import com.geely.lib.oneosapi.navi.model.base.NaviBaseModel;
import com.geely.lib.oneosapi.navi.model.base.NaviProtocolID;

/* loaded from: classes.dex */
public class NaviGetFrontTrafficRadio extends NaviBaseModel implements Parcelable {
    public static final Parcelable.Creator<NaviGetFrontTrafficRadio> CREATOR = new Parcelable.Creator<NaviGetFrontTrafficRadio>() { // from class: com.geely.lib.oneosapi.navi.model.client.NaviGetFrontTrafficRadio.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NaviGetFrontTrafficRadio createFromParcel(Parcel source) {
            return new NaviGetFrontTrafficRadio(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NaviGetFrontTrafficRadio[] newArray(int size) {
            return new NaviGetFrontTrafficRadio[size];
        }
    };
    private int frontDistance;

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public NaviGetFrontTrafficRadio(int frontDistance) {
        this.frontDistance = frontDistance;
        setProtocolID(NaviProtocolID.NAVI_OP_FRONT_TRAFFIC_RADIO);
    }

    public int getFrontDistance() {
        return this.frontDistance;
    }

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel, android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeInt(this.frontDistance);
    }

    protected NaviGetFrontTrafficRadio(Parcel in) {
        super(in);
        this.frontDistance = in.readInt();
    }

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel
    public String toString() {
        return "NaviGetFrontTrafficRadio{frontDistance=" + this.frontDistance + "{base=" + super.toString() + "}; }";
    }
}