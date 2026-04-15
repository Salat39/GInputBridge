package com.geely.lib.oneosapi.navi.model.client;

import android.os.Parcel;
import android.os.Parcelable;
import com.geely.lib.oneosapi.navi.model.base.LatLng;
import com.geely.lib.oneosapi.navi.model.base.NaviBaseModel;
import com.geely.lib.oneosapi.navi.model.base.NaviProtocolID;

/* loaded from: classes.dex */
public class MapOperaAntiGeo extends NaviBaseModel {
    public static final Parcelable.Creator<MapOperaAntiGeo> CREATOR = new Parcelable.Creator<MapOperaAntiGeo>() { // from class: com.geely.lib.oneosapi.navi.model.client.MapOperaAntiGeo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MapOperaAntiGeo createFromParcel(Parcel source) {
            return new MapOperaAntiGeo(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MapOperaAntiGeo[] newArray(int size) {
            return new MapOperaAntiGeo[size];
        }
    };
    private LatLng latLng;

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public MapOperaAntiGeo(LatLng latLng) {
        this.latLng = latLng;
        setProtocolID(NaviProtocolID.MAP_OP_GET_ANTI_GEO);
    }

    public LatLng getLatLng() {
        return this.latLng;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel, android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeParcelable(this.latLng, flags);
    }

    protected MapOperaAntiGeo(Parcel in) {
        super(in);
        this.latLng = (LatLng) in.readParcelable(LatLng.class.getClassLoader());
    }

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel
    public String toString() {
        return "MapOperaAntiGeo{latLng=" + this.latLng + "{base=" + super.toString() + "}; }";
    }
}