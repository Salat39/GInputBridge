package com.geely.lib.oneosapi.navi.model.client;

import android.os.Parcel;
import android.os.Parcelable;
import com.geely.lib.oneosapi.navi.model.base.LatLng;
import com.geely.lib.oneosapi.navi.model.base.NaviBaseModel;
import com.geely.lib.oneosapi.navi.model.base.NaviProtocolID;

/* loaded from: classes.dex */
public class MapGeoDecodeBean extends NaviBaseModel implements Parcelable {
    public static final Parcelable.Creator<MapGeoDecodeBean> CREATOR = new Parcelable.Creator<MapGeoDecodeBean>() { // from class: com.geely.lib.oneosapi.navi.model.client.MapGeoDecodeBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MapGeoDecodeBean createFromParcel(Parcel source) {
            return new MapGeoDecodeBean(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MapGeoDecodeBean[] newArray(int size) {
            return new MapGeoDecodeBean[size];
        }
    };
    private LatLng latLng;

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public MapGeoDecodeBean(LatLng latLng) {
        setProtocolID(NaviProtocolID.SEARCH_REQUEST_GEO_DECODER);
        this.latLng = latLng;
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

    protected MapGeoDecodeBean(Parcel in) {
        super(in);
        this.latLng = (LatLng) in.readParcelable(LatLng.class.getClassLoader());
    }

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel
    public String toString() {
        super.toString();
        return "MapGeoDecodeBean{latLng=" + this.latLng.toString() + '}';
    }
}