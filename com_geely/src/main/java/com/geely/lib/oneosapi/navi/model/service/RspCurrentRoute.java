package com.geely.lib.oneosapi.navi.model.service;

import android.os.Parcel;
import android.os.Parcelable;
import com.geely.lib.oneosapi.navi.model.base.NaviBaseModel;
import com.geely.lib.oneosapi.navi.model.base.PoiInfo;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class RspCurrentRoute extends NaviBaseModel implements Parcelable {
    public static final Parcelable.Creator<RspCurrentRoute> CREATOR = new Parcelable.Creator<RspCurrentRoute>() { // from class: com.geely.lib.oneosapi.navi.model.service.RspCurrentRoute.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RspCurrentRoute createFromParcel(Parcel source) {
            return new RspCurrentRoute(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RspCurrentRoute[] newArray(int size) {
            return new RspCurrentRoute[size];
        }
    };
    private PoiInfo endPoi;
    private RouteInfo routeInfo;
    private PoiInfo startPoi;
    private List<PoiInfo> viaPois;

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public RspCurrentRoute(NaviBaseModel reqModel) {
        copyBaseInfo(reqModel);
    }

    public RouteInfo getRouteInfo() {
        return this.routeInfo;
    }

    public void setRouteInfo(RouteInfo routeInfo) {
        this.routeInfo = routeInfo;
    }

    public PoiInfo getStartPoi() {
        return this.startPoi;
    }

    public void setStartPoi(PoiInfo startPoi) {
        this.startPoi = startPoi;
    }

    public PoiInfo getEndPoi() {
        return this.endPoi;
    }

    public void setEndPoi(PoiInfo endPoi) {
        this.endPoi = endPoi;
    }

    public List<PoiInfo> getViaPois() {
        return this.viaPois;
    }

    public void setViaPois(List<PoiInfo> viaPois) {
        this.viaPois = viaPois;
    }

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel, android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeParcelable(this.routeInfo, flags);
        dest.writeParcelable(this.startPoi, flags);
        dest.writeParcelable(this.endPoi, flags);
        dest.writeList(this.viaPois);
    }

    protected RspCurrentRoute(Parcel in) {
        super(in);
        this.routeInfo = (RouteInfo) in.readParcelable(RouteInfo.class.getClassLoader());
        this.startPoi = (PoiInfo) in.readParcelable(PoiInfo.class.getClassLoader());
        this.endPoi = (PoiInfo) in.readParcelable(PoiInfo.class.getClassLoader());
        ArrayList arrayList = new ArrayList();
        this.viaPois = arrayList;
        in.readList(arrayList, RspCurrentRoute.class.getClassLoader());
    }

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel
    public String toString() {
        return "RspCurrentRoute{routeInfo=" + this.routeInfo + ", startPoi=" + this.startPoi + ", endPoi=" + this.endPoi + ", viaPois=" + this.viaPois + "{base=" + super.toString() + "}; }";
    }
}