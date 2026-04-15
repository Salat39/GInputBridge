package com.geely.lib.oneosapi.navi.model.service;

import android.os.Parcel;
import android.os.Parcelable;
import com.geely.lib.oneosapi.navi.model.base.NaviBaseModel;

/* loaded from: classes.dex */
public class RspRouteSelected extends RspRoutePlanResult {
    public static final Parcelable.Creator<RspRouteSelected> CREATOR = new Parcelable.Creator<RspRouteSelected>() { // from class: com.geely.lib.oneosapi.navi.model.service.RspRouteSelected.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RspRouteSelected createFromParcel(Parcel source) {
            return new RspRouteSelected(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RspRouteSelected[] newArray(int size) {
            return new RspRouteSelected[size];
        }
    };
    private int routeSelectedIndex;

    public RspRouteSelected(NaviBaseModel reqModel) {
        super(reqModel);
    }

    public int getRouteSelectedIndex() {
        return this.routeSelectedIndex;
    }

    public void setRouteSelectedIndex(int routeSelectedIndex) {
        this.routeSelectedIndex = routeSelectedIndex;
    }

    @Override // com.geely.lib.oneosapi.navi.model.service.RspRoutePlanResult, com.geely.lib.oneosapi.navi.model.base.NaviBaseModel, android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeInt(this.routeSelectedIndex);
    }

    protected RspRouteSelected(Parcel in) {
        super(in);
        this.routeSelectedIndex = in.readInt();
    }

    @Override // com.geely.lib.oneosapi.navi.model.service.RspRoutePlanResult, com.geely.lib.oneosapi.navi.model.base.NaviBaseModel
    public String toString() {
        return "RspRouteSelected{routeSelectedIndex=" + this.routeSelectedIndex + "{base=" + super.toString() + "}; }";
    }
}