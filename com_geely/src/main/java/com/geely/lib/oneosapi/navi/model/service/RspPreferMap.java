package com.geely.lib.oneosapi.navi.model.service;

import android.os.Parcel;
import android.os.Parcelable;
import com.geely.lib.oneosapi.navi.model.base.NaviBaseModel;

/* loaded from: classes.dex */
public class RspPreferMap extends NaviBaseModel implements Parcelable {
    public static final Parcelable.Creator<RspPreferMap> CREATOR = new Parcelable.Creator<RspPreferMap>() { // from class: com.geely.lib.oneosapi.navi.model.service.RspPreferMap.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RspPreferMap createFromParcel(Parcel source) {
            return new RspPreferMap(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RspPreferMap[] newArray(int size) {
            return new RspPreferMap[size];
        }
    };
    private int mapVendor;

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public RspPreferMap(NaviBaseModel reqModel) {
        copyBaseInfo(reqModel);
    }

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel
    public int getMapVendor() {
        return this.mapVendor;
    }

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel
    public void setMapVendor(int mapVendor) {
        this.mapVendor = mapVendor;
    }

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel, android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeInt(this.mapVendor);
    }

    protected RspPreferMap(Parcel in) {
        super(in);
        this.mapVendor = in.readInt();
    }

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel
    public String toString() {
        return "RspPreferMap{mapVendor=" + this.mapVendor + "{base=" + super.toString() + "}; }";
    }
}