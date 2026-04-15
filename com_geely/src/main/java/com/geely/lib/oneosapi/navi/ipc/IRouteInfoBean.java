package com.geely.lib.oneosapi.navi.ipc;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

/* loaded from: classes.dex */
public class IRouteInfoBean implements Parcelable {
    public static final Parcelable.Creator<IRouteInfoBean> CREATOR = new Parcelable.Creator<IRouteInfoBean>() { // from class: com.geely.lib.oneosapi.navi.ipc.IRouteInfoBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public IRouteInfoBean createFromParcel(Parcel in) {
            return new IRouteInfoBean(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public IRouteInfoBean[] newArray(int size) {
            return new IRouteInfoBean[size];
        }
    };
    private Bundle extras;
    private int routeDistance;
    private int routeStrategy;
    private String routeTag;
    private int routeTime;
    private List<ITrafficSegInfoBean> trafficInfos;
    private int trafficLights;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public IRouteInfoBean() {
    }

    protected IRouteInfoBean(Parcel in) {
        this.routeTag = in.readString();
        this.routeStrategy = in.readInt();
        this.routeTime = in.readInt();
        this.routeDistance = in.readInt();
        this.trafficLights = in.readInt();
        this.trafficInfos = in.createTypedArrayList(ITrafficSegInfoBean.CREATOR);
        this.extras = in.readBundle(Bundle.class.getClassLoader());
    }

    public void readFromParcel(Parcel in) {
        this.routeTag = in.readString();
        this.routeStrategy = in.readInt();
        this.routeTime = in.readInt();
        this.routeDistance = in.readInt();
        this.trafficLights = in.readInt();
        this.trafficInfos = in.createTypedArrayList(ITrafficSegInfoBean.CREATOR);
        this.extras = in.readBundle(Bundle.class.getClassLoader());
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.routeTag);
        dest.writeInt(this.routeStrategy);
        dest.writeInt(this.routeTime);
        dest.writeInt(this.routeDistance);
        dest.writeInt(this.trafficLights);
        dest.writeTypedList(this.trafficInfos);
        dest.writeBundle(this.extras);
    }

    public String getRouteTag() {
        return this.routeTag;
    }

    public void setRouteTag(String routeTag) {
        this.routeTag = routeTag;
    }

    public int getRouteStrategy() {
        return this.routeStrategy;
    }

    public void setRouteStrategy(int routeStrategy) {
        this.routeStrategy = routeStrategy;
    }

    public int getRouteTime() {
        return this.routeTime;
    }

    public void setRouteTime(int routeTime) {
        this.routeTime = routeTime;
    }

    public int getRouteDistance() {
        return this.routeDistance;
    }

    public void setRouteDistance(int routeDistance) {
        this.routeDistance = routeDistance;
    }

    public int getTrafficLights() {
        return this.trafficLights;
    }

    public void setTrafficLights(int trafficLights) {
        this.trafficLights = trafficLights;
    }

    public List<ITrafficSegInfoBean> getTrafficInfos() {
        return this.trafficInfos;
    }

    public void setTrafficInfos(List<ITrafficSegInfoBean> trafficInfos) {
        this.trafficInfos = trafficInfos;
    }

    public Bundle getExtras() {
        return this.extras;
    }

    public void setExtras(Bundle extras) {
        this.extras = extras;
    }

    public String toString() {
        return "IRouteInfoBean{routeTag='" + this.routeTag + "', routeStrategy=" + this.routeStrategy + ", routeTime=" + this.routeTime + ", routeDistance=" + this.routeDistance + ", trafficLights=" + this.trafficLights + ", trafficInfos=" + this.trafficInfos + ", extras=" + this.extras + '}';
    }
}