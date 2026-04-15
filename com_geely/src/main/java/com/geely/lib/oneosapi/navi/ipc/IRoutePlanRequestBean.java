package com.geely.lib.oneosapi.navi.ipc;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

/* loaded from: classes.dex */
public class IRoutePlanRequestBean implements Parcelable {
    public static final Parcelable.Creator<IRoutePlanRequestBean> CREATOR = new Parcelable.Creator<IRoutePlanRequestBean>() { // from class: com.geely.lib.oneosapi.navi.ipc.IRoutePlanRequestBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public IRoutePlanRequestBean createFromParcel(Parcel in) {
            return new IRoutePlanRequestBean(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public IRoutePlanRequestBean[] newArray(int size) {
            return new IRoutePlanRequestBean[size];
        }
    };
    private int action;
    private IPoiInfoBean destPoi;
    private Bundle extras;
    private long requestTimeout;
    private int routePlanStrategy;
    private IPoiInfoBean startPoi;
    private List<IPoiInfoBean> viaPois;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public IRoutePlanRequestBean() {
    }

    protected IRoutePlanRequestBean(Parcel in) {
        this.action = in.readInt();
        this.routePlanStrategy = in.readInt();
        this.startPoi = (IPoiInfoBean) in.readParcelable(IPoiInfoBean.class.getClassLoader());
        this.destPoi = (IPoiInfoBean) in.readParcelable(IPoiInfoBean.class.getClassLoader());
        this.viaPois = in.createTypedArrayList(IPoiInfoBean.CREATOR);
        this.requestTimeout = in.readLong();
        this.extras = in.readBundle(Bundle.class.getClassLoader());
    }

    public void readFromParcel(Parcel in) {
        this.action = in.readInt();
        this.routePlanStrategy = in.readInt();
        this.startPoi = (IPoiInfoBean) in.readParcelable(IPoiInfoBean.class.getClassLoader());
        this.destPoi = (IPoiInfoBean) in.readParcelable(IPoiInfoBean.class.getClassLoader());
        this.viaPois = in.createTypedArrayList(IPoiInfoBean.CREATOR);
        this.requestTimeout = in.readLong();
        this.extras = in.readBundle(Bundle.class.getClassLoader());
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.action);
        dest.writeInt(this.routePlanStrategy);
        dest.writeParcelable(this.startPoi, flags);
        dest.writeParcelable(this.destPoi, flags);
        dest.writeTypedList(this.viaPois);
        dest.writeLong(this.requestTimeout);
        dest.writeBundle(this.extras);
    }

    public int getAction() {
        return this.action;
    }

    public void setAction(int action) {
        this.action = action;
    }

    public int getRoutePlanStrategy() {
        return this.routePlanStrategy;
    }

    public void setRoutePlanStrategy(int routePlanStrategy) {
        this.routePlanStrategy = routePlanStrategy;
    }

    public IPoiInfoBean getStartPoi() {
        return this.startPoi;
    }

    public void setStartPoi(IPoiInfoBean startPoi) {
        this.startPoi = startPoi;
    }

    public IPoiInfoBean getDestPoi() {
        return this.destPoi;
    }

    public void setDestPoi(IPoiInfoBean destPoi) {
        this.destPoi = destPoi;
    }

    public List<IPoiInfoBean> getViaPois() {
        return this.viaPois;
    }

    public void setViaPois(List<IPoiInfoBean> viaPois) {
        this.viaPois = viaPois;
    }

    public long getRequestTimeout() {
        return this.requestTimeout;
    }

    public void setRequestTimeout(long requestTimeout) {
        this.requestTimeout = requestTimeout;
    }

    public Bundle getExtras() {
        return this.extras;
    }

    public void setExtras(Bundle extras) {
        this.extras = extras;
    }

    public String toString() {
        return "IRoutePlanRequestBean{action=" + this.action + ", routePlanStrategy=" + this.routePlanStrategy + ", startPoi=" + this.startPoi + ", destPoi=" + this.destPoi + ", viaPois=" + this.viaPois + ", requestTimeout=" + this.requestTimeout + ", extras=" + this.extras + '}';
    }
}