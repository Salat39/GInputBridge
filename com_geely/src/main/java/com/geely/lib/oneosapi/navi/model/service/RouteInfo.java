package com.geely.lib.oneosapi.navi.model.service;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class RouteInfo implements Parcelable {
    public static final Parcelable.Creator<RouteInfo> CREATOR = new Parcelable.Creator<RouteInfo>() { // from class: com.geely.lib.oneosapi.navi.model.service.RouteInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RouteInfo createFromParcel(Parcel source) {
            return new RouteInfo(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RouteInfo[] newArray(int size) {
            return new RouteInfo[size];
        }
    };
    private double distance;
    private String distanceAuto;
    private String routeTag;
    private int strategy;
    private double time;
    private String timeAuto;
    private int tolls;
    private List<TrafficInfo> trafficInfos;
    private int trafficLights;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public RouteInfo() {
        this.timeAuto = "";
        this.distanceAuto = "";
        this.trafficInfos = new ArrayList();
    }

    public String getRouteTag() {
        return this.routeTag;
    }

    public void setRouteTag(String routeTag) {
        this.routeTag = routeTag;
    }

    public int getStrategy() {
        return this.strategy;
    }

    public void setStrategy(int strategy) {
        this.strategy = strategy;
    }

    public double getTime() {
        return this.time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public double getDistance() {
        return this.distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getTimeAuto() {
        return this.timeAuto;
    }

    public void setTimeAuto(String timeAuto) {
        this.timeAuto = timeAuto;
    }

    public String getDistanceAuto() {
        return this.distanceAuto;
    }

    public void setDistanceAuto(String distanceAuto) {
        this.distanceAuto = distanceAuto;
    }

    public int getTrafficLights() {
        return this.trafficLights;
    }

    public void setTrafficLights(int trafficLights) {
        this.trafficLights = trafficLights;
    }

    public int getTolls() {
        return this.tolls;
    }

    public void setTolls(int tolls) {
        this.tolls = tolls;
    }

    public List<TrafficInfo> getTrafficInfos() {
        return this.trafficInfos;
    }

    public void setTrafficInfos(List<TrafficInfo> trafficInfos) {
        this.trafficInfos = trafficInfos;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.routeTag);
        dest.writeInt(this.strategy);
        dest.writeDouble(this.time);
        dest.writeDouble(this.distance);
        dest.writeString(this.timeAuto);
        dest.writeString(this.distanceAuto);
        dest.writeInt(this.trafficLights);
        dest.writeInt(this.tolls);
        dest.writeList(this.trafficInfos);
    }

    protected RouteInfo(Parcel in) {
        this.timeAuto = "";
        this.distanceAuto = "";
        this.trafficInfos = new ArrayList();
        this.routeTag = in.readString();
        this.strategy = in.readInt();
        this.time = in.readDouble();
        this.distance = in.readDouble();
        this.timeAuto = in.readString();
        this.distanceAuto = in.readString();
        this.trafficLights = in.readInt();
        this.tolls = in.readInt();
        ArrayList arrayList = new ArrayList();
        this.trafficInfos = arrayList;
        in.readList(arrayList, TrafficInfo.class.getClassLoader());
    }

    public String toString() {
        return "RouteInfo{routeTag='" + this.routeTag + "', strategy=" + this.strategy + ", time=" + this.time + ", distance=" + this.distance + ", timeAuto='" + this.timeAuto + "', distanceAuto='" + this.distanceAuto + "', trafficLights=" + this.trafficLights + ", tolls=" + this.tolls + ", trafficInfos=" + this.trafficInfos + '}';
    }
}