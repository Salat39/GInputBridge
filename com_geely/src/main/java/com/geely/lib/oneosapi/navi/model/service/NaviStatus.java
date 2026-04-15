package com.geely.lib.oneosapi.navi.model.service;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class NaviStatus implements Cloneable, Parcelable {
    public static final Parcelable.Creator<NaviStatus> CREATOR = new Parcelable.Creator<NaviStatus>() { // from class: com.geely.lib.oneosapi.navi.model.service.NaviStatus.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NaviStatus createFromParcel(Parcel var1) {
            return new NaviStatus(var1);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NaviStatus[] newArray(int var1) {
            return new NaviStatus[var1];
        }
    };
    public static final int DAYNIGHT_STATUS_DAY = 0;
    public static final int DAYNIGHT_STATUS_NIGHT = 1;
    public static final int DAYNIGHT_STATUS_UNKNOWN = -1;
    public static final int GUIDE_STATUS_CRUISE = 2;
    public static final int GUIDE_STATUS_GPS = 0;
    public static final int GUIDE_STATUS_NOGUIDE = 3;
    public static final int GUIDE_STATUS_SIMULATE = 1;
    public static final int GUIDE_STATUS_UNKNOWN = -1;
    public static final int MAP_STATUS_BACKGOURND = 4;
    public static final int MAP_STATUS_FINISHED = 1;
    public static final int MAP_STATUS_FOREGROUND = 3;
    public static final int MAP_STATUS_STARTED = 0;
    public static final int MAP_STATUS_UNKNOWN = -1;
    public static final int MAP_TYPE_AMAP = 0;
    public static final int MAP_TYPE_BAIDU = 1;
    public static final int MAP_TYPE_TENCENT = 2;
    public static final int ROUTE_VIEW_IN = 1;
    public static final int ROUTE_VIEW_OUT = 2;
    public static final int ROUTE_VIEW_UNKNOWN = -1;
    public static final int TRAFFIC_STATUS_OFF = 2;
    public static final int TRAFFIC_STATUS_ON = 1;
    public static final int TRAFFIC_STATUS_UNKNOWN = -1;
    public static final int VIEW_MODE_2D_FRONT_UP = 0;
    public static final int VIEW_MODE_2D_NORTHWARD = 1;
    public static final int VIEW_MODE_3D_FRONT_UP = 2;
    public static final int VIEW_MODE_UNKNOWN = -1;
    public static final int ZOOM_STATUS_MAX = 2;
    public static final int ZOOM_STATUS_MID = 0;
    public static final int ZOOM_STATUS_MIN = 1;
    public static final int ZOOM_STATUS_UNKNOWN = -1;
    private boolean bArrivedDestination;
    private int dayNightStatus;
    private int dayNightStatusVendor;
    private int guideStatus;
    private int guideStatusVendor;
    private int mapStatus;
    private int mapStatusVendor;
    private int mapType;
    private int routeViewStatus;
    private int routeViewStatusVendor;
    private int trafficStatus;
    private int trafficStatusVendor;
    private int viewMode;
    private int viewModeVendor;
    private int zoomStatus;
    private int zoomStatusVendor;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public NaviStatus() {
        this(0);
    }

    public NaviStatus(int var1) {
        this.mapType = var1;
        resetStatus(var1);
    }

    protected NaviStatus(Parcel var1) {
        this.mapType = var1.readInt();
        this.mapStatus = var1.readInt();
        this.guideStatus = var1.readInt();
        this.routeViewStatus = var1.readInt();
        this.trafficStatus = var1.readInt();
        this.zoomStatus = var1.readInt();
        this.viewMode = var1.readInt();
        this.dayNightStatus = var1.readInt();
        this.mapStatusVendor = var1.readInt();
        this.guideStatusVendor = var1.readInt();
        this.routeViewStatusVendor = var1.readInt();
        this.trafficStatusVendor = var1.readInt();
        this.zoomStatusVendor = var1.readInt();
        this.viewModeVendor = var1.readInt();
        this.dayNightStatusVendor = var1.readInt();
        this.bArrivedDestination = var1.readByte() != 0;
    }

    /* renamed from: clone, reason: merged with bridge method [inline-methods] */
    public NaviStatus m329clone() {
        try {
            return (NaviStatus) super.clone();
        } catch (CloneNotSupportedException unused) {
            return new NaviStatus();
        }
    }

    public int getDayNightStatus() {
        return this.dayNightStatus;
    }

    public int getDayNightStatusVendor() {
        return this.dayNightStatusVendor;
    }

    public int getGuideStatus() {
        return this.guideStatus;
    }

    public int getGuideStatusVendor() {
        return this.guideStatusVendor;
    }

    public int getMapStatus() {
        return this.mapStatus;
    }

    public int getMapStatusVendor() {
        return this.mapStatusVendor;
    }

    public int getMapType() {
        return this.mapType;
    }

    public int getRouteViewStatus() {
        return this.routeViewStatus;
    }

    public int getRouteViewStatusVendor() {
        return this.routeViewStatusVendor;
    }

    public int getTrafficStatus() {
        return this.trafficStatus;
    }

    public int getTrafficStatusVendor() {
        return this.trafficStatusVendor;
    }

    public int getViewMode() {
        return this.viewMode;
    }

    public int getViewModeVendor() {
        return this.viewModeVendor;
    }

    public int getZoomStatus() {
        return this.zoomStatus;
    }

    public int getZoomStatusVendor() {
        return this.zoomStatusVendor;
    }

    public boolean isArrivedDestination() {
        return this.bArrivedDestination;
    }

    public void resetStatus() {
        resetStatus(0);
    }

    public void resetStatus(int var1) {
        this.mapStatus = -1;
        this.guideStatus = -1;
        this.routeViewStatus = -1;
        this.trafficStatus = -1;
        this.zoomStatus = -1;
        this.viewMode = -1;
        this.dayNightStatus = -1;
        this.mapStatusVendor = var1;
        this.guideStatusVendor = var1;
        this.routeViewStatusVendor = var1;
        this.trafficStatusVendor = var1;
        this.zoomStatusVendor = var1;
        this.viewModeVendor = var1;
        this.dayNightStatusVendor = var1;
        this.bArrivedDestination = false;
    }

    public void setArrivedDestination(boolean var1) {
        this.bArrivedDestination = var1;
    }

    public void setDayNightStatus(int var1) {
        this.dayNightStatus = var1;
    }

    public void setDayNightStatusVendor(int var1) {
        this.dayNightStatusVendor = var1;
    }

    public void setGuideStatus(int var1) {
        this.guideStatus = var1;
    }

    public void setGuideStatusVendor(int var1) {
        this.guideStatusVendor = var1;
    }

    public void setMapStatus(int var1) {
        this.mapStatus = var1;
    }

    public void setMapStatusVendor(int var1) {
        this.mapStatusVendor = var1;
    }

    public void setMapType(int var1) {
        this.mapType = var1;
    }

    public void setRouteViewStatus(int var1) {
        this.routeViewStatus = var1;
    }

    public void setRouteViewStatusVendor(int var1) {
        this.routeViewStatusVendor = var1;
    }

    public void setTrafficStatus(int var1) {
        this.trafficStatus = var1;
    }

    public void setTrafficStatusVendor(int var1) {
        this.trafficStatusVendor = var1;
    }

    public void setViewMode(int var1) {
        this.viewMode = var1;
    }

    public void setViewModeVendor(int var1) {
        this.viewModeVendor = var1;
    }

    public void setZoomStatus(int var1) {
        this.zoomStatus = var1;
    }

    public void setZoomStatusVendor(int var1) {
        this.zoomStatusVendor = var1;
    }

    public String toString() {
        return "NaviStatus{mapType=" + this.mapType + ", mapStatus=" + this.mapStatus + ", mapStatusVendor=" + this.mapStatusVendor + ", guideStatus=" + this.guideStatus + ", guideStatusVendor=" + this.guideStatusVendor + ", routeViewStatus=" + this.routeViewStatus + ", routeViewStatusVendor=" + this.routeViewStatusVendor + ", trafficStatus=" + this.trafficStatus + ", trafficStatusVendor=" + this.trafficStatusVendor + ", zoomStatus=" + this.zoomStatus + ", zoomStatusVendor=" + this.zoomStatusVendor + ", viewMode=" + this.viewMode + ", viewModeVendor=" + this.viewModeVendor + ", dayNightStatus=" + this.dayNightStatus + ", dayNightStatusVendor=" + this.dayNightStatusVendor + ", bArrivedDestination=" + this.bArrivedDestination + '}';
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mapType);
        parcel.writeInt(this.mapStatus);
        parcel.writeInt(this.guideStatus);
        parcel.writeInt(this.routeViewStatus);
        parcel.writeInt(this.trafficStatus);
        parcel.writeInt(this.zoomStatus);
        parcel.writeInt(this.viewMode);
        parcel.writeInt(this.dayNightStatus);
        parcel.writeInt(this.mapStatusVendor);
        parcel.writeInt(this.guideStatusVendor);
        parcel.writeInt(this.routeViewStatusVendor);
        parcel.writeInt(this.trafficStatusVendor);
        parcel.writeInt(this.zoomStatusVendor);
        parcel.writeInt(this.viewModeVendor);
        parcel.writeInt(this.dayNightStatusVendor);
        parcel.writeByte(this.bArrivedDestination ? (byte) 1 : (byte) 0);
    }
}