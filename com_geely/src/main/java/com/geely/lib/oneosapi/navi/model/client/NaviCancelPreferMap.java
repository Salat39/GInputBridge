package com.geely.lib.oneosapi.navi.model.client;

import android.os.Parcel;
import android.os.Parcelable;
import com.geely.lib.oneosapi.navi.model.base.NaviBaseModel;
import com.geely.lib.oneosapi.navi.model.base.NaviProtocolID;

/* loaded from: classes.dex */
public class NaviCancelPreferMap extends NaviBaseModel implements Parcelable {
    public static final Parcelable.Creator<NaviCancelPreferMap> CREATOR = new Parcelable.Creator<NaviCancelPreferMap>() { // from class: com.geely.lib.oneosapi.navi.model.client.NaviCancelPreferMap.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NaviCancelPreferMap createFromParcel(Parcel source) {
            return new NaviCancelPreferMap(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NaviCancelPreferMap[] newArray(int size) {
            return new NaviCancelPreferMap[size];
        }
    };
    private int mapVendor;

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public NaviCancelPreferMap(int vendor) {
        this.mapVendor = vendor;
        setProtocolID(NaviProtocolID.NAVI_OP_CANCEL_PREFER_MAP);
    }

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel
    public int getMapVendor() {
        return this.mapVendor;
    }

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel, android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeInt(this.mapVendor);
    }

    protected NaviCancelPreferMap(Parcel in) {
        super(in);
        this.mapVendor = in.readInt();
    }

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel
    public String toString() {
        return "NaviCancelPreferMap{mapVendor=" + this.mapVendor + "{base=" + super.toString() + "}; }";
    }
}