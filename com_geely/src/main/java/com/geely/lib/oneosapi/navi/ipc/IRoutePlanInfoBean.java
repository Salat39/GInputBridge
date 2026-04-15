package com.geely.lib.oneosapi.navi.ipc;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

/* loaded from: classes.dex */
public class IRoutePlanInfoBean implements Parcelable {
    public static final Parcelable.Creator<IRoutePlanInfoBean> CREATOR = new Parcelable.Creator<IRoutePlanInfoBean>() { // from class: com.geely.lib.oneosapi.navi.ipc.IRoutePlanInfoBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public IRoutePlanInfoBean createFromParcel(Parcel in) {
            return new IRoutePlanInfoBean(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public IRoutePlanInfoBean[] newArray(int size) {
            return new IRoutePlanInfoBean[size];
        }
    };
    private int currentRouteIndex;
    private IPoiInfoBean endPoi;
    private Bundle extras;
    private int resultCode;
    private List<IRouteInfoBean> routeInfos;
    private IPoiInfoBean startPoi;
    private List<IPoiInfoBean> viaPois;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public IRoutePlanInfoBean() {
    }

    protected IRoutePlanInfoBean(Parcel in) {
        this.startPoi = (IPoiInfoBean) in.readParcelable(IPoiInfoBean.class.getClassLoader());
        this.endPoi = (IPoiInfoBean) in.readParcelable(IPoiInfoBean.class.getClassLoader());
        this.viaPois = in.createTypedArrayList(IPoiInfoBean.CREATOR);
        this.routeInfos = in.createTypedArrayList(IRouteInfoBean.CREATOR);
        this.currentRouteIndex = in.readInt();
        this.resultCode = in.readInt();
        this.extras = in.readBundle(Bundle.class.getClassLoader());
    }

    public void readFromParcel(Parcel in) {
        this.startPoi = (IPoiInfoBean) in.readParcelable(IPoiInfoBean.class.getClassLoader());
        this.endPoi = (IPoiInfoBean) in.readParcelable(IPoiInfoBean.class.getClassLoader());
        this.viaPois = in.createTypedArrayList(IPoiInfoBean.CREATOR);
        this.routeInfos = in.createTypedArrayList(IRouteInfoBean.CREATOR);
        this.currentRouteIndex = in.readInt();
        this.resultCode = in.readInt();
        this.extras = in.readBundle(Bundle.class.getClassLoader());
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.startPoi, flags);
        dest.writeParcelable(this.endPoi, flags);
        dest.writeTypedList(this.viaPois);
        dest.writeTypedList(this.routeInfos);
        dest.writeInt(this.currentRouteIndex);
        dest.writeInt(this.resultCode);
        dest.writeBundle(this.extras);
    }

    public IPoiInfoBean getStartPoi() {
        return this.startPoi;
    }

    public void setStartPoi(IPoiInfoBean startPoi) {
        this.startPoi = startPoi;
    }

    public IPoiInfoBean getEndPoi() {
        return this.endPoi;
    }

    public void setEndPoi(IPoiInfoBean endPoi) {
        this.endPoi = endPoi;
    }

    public List<IPoiInfoBean> getViaPois() {
        return this.viaPois;
    }

    public void setViaPois(List<IPoiInfoBean> viaPois) {
        this.viaPois = viaPois;
    }

    public List<IRouteInfoBean> getRouteInfos() {
        return this.routeInfos;
    }

    public void setRouteInfos(List<IRouteInfoBean> routeInfos) {
        this.routeInfos = routeInfos;
    }

    public int getCurrentRouteIndex() {
        return this.currentRouteIndex;
    }

    public void setCurrentRouteIndex(int currentRouteIndex) {
        this.currentRouteIndex = currentRouteIndex;
    }

    public int getResultCode() {
        return this.resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public Bundle getExtras() {
        return this.extras;
    }

    public void setExtras(Bundle extras) {
        this.extras = extras;
    }

    public String toString() {
        return "IRoutePlanInfoBean{startPoi=" + this.startPoi + ", endPoi=" + this.endPoi + ", viaPois=" + this.viaPois + ", routeInfos=" + this.routeInfos + ", currentRouteIndex=" + this.currentRouteIndex + ", resultCode=" + this.resultCode + ", extras=" + this.extras + '}';
    }
}