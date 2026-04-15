package com.geely.lib.oneosapi.navi.model.service;

import android.os.Parcel;
import android.os.Parcelable;
import com.geely.lib.oneosapi.navi.model.base.NaviBaseModel;
import com.geely.lib.oneosapi.navi.model.base.PoiInfo;
import java.util.List;

/* loaded from: classes.dex */
public class RspRoutePlanInfo extends RspRoutePlanResult implements Parcelable {
    public static final Parcelable.Creator<RspRoutePlanInfo> CREATOR = new Parcelable.Creator<RspRoutePlanInfo>() { // from class: com.geely.lib.oneosapi.navi.model.service.RspRoutePlanInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RspRoutePlanInfo createFromParcel(Parcel source) {
            return new RspRoutePlanInfo(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RspRoutePlanInfo[] newArray(int size) {
            return new RspRoutePlanInfo[size];
        }
    };
    private int currentRouteIndex;
    private PoiInfo endPOI;
    private List<RouteInfo> routeInfos;
    private PoiInfo startPOI;
    private List<PoiInfo> viaPOIInfos;

    public int getCurrentRouteIndex() {
        return this.currentRouteIndex;
    }

    public void setCurrentRouteIndex(int currentRouteIndex) {
        this.currentRouteIndex = currentRouteIndex;
    }

    public List<RouteInfo> getRouteInfos() {
        return this.routeInfos;
    }

    public void setRouteInfos(List<RouteInfo> routeInfos) {
        this.routeInfos = routeInfos;
    }

    public static Parcelable.Creator<RspRoutePlanInfo> getCREATOR() {
        return CREATOR;
    }

    public RspRoutePlanInfo(NaviBaseModel reqModel) {
        super(reqModel);
    }

    public List<PoiInfo> getViaPOIInfos() {
        return this.viaPOIInfos;
    }

    public void setViaPOIInfos(List<PoiInfo> viaPOIInfos) {
        this.viaPOIInfos = viaPOIInfos;
    }

    public PoiInfo getStartPOI() {
        return this.startPOI;
    }

    public void setStartPOI(PoiInfo startPOI) {
        this.startPOI = startPOI;
    }

    public PoiInfo getEndPOI() {
        return this.endPOI;
    }

    public void setEndPOI(PoiInfo endPOI) {
        this.endPOI = endPOI;
    }

    @Override // com.geely.lib.oneosapi.navi.model.service.RspRoutePlanResult, com.geely.lib.oneosapi.navi.model.base.NaviBaseModel, android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeParcelable(this.startPOI, flags);
        dest.writeParcelable(this.endPOI, flags);
        dest.writeTypedList(this.viaPOIInfos);
        dest.writeInt(this.currentRouteIndex);
        dest.writeTypedList(this.routeInfos);
    }

    protected RspRoutePlanInfo(Parcel in) {
        super(in);
        this.startPOI = (PoiInfo) in.readParcelable(PoiInfo.class.getClassLoader());
        this.endPOI = (PoiInfo) in.readParcelable(PoiInfo.class.getClassLoader());
        this.viaPOIInfos = in.createTypedArrayList(PoiInfo.CREATOR);
        this.currentRouteIndex = in.readInt();
        this.routeInfos = in.createTypedArrayList(RouteInfo.CREATOR);
    }

    @Override // com.geely.lib.oneosapi.navi.model.service.RspRoutePlanResult, com.geely.lib.oneosapi.navi.model.base.NaviBaseModel
    public String toString() {
        return "RspRoutePlanInfo{viaPOIInfos=" + this.viaPOIInfos + ", startPOI=" + this.startPOI + ", endPOI=" + this.endPOI + "{base=" + super.toString() + "}; }";
    }
}