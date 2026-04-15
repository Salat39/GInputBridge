package com.geely.lib.oneosapi.navi.model.service;

import android.os.Parcel;
import android.os.Parcelable;
import com.geely.lib.oneosapi.navi.model.base.NaviBaseModel;

/* loaded from: classes.dex */
public class RspGuideInfo extends NaviBaseModel implements Parcelable, Cloneable {
    public static final Parcelable.Creator<RspGuideInfo> CREATOR = new Parcelable.Creator<RspGuideInfo>() { // from class: com.geely.lib.oneosapi.navi.model.service.RspGuideInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RspGuideInfo createFromParcel(Parcel source) {
            return new RspGuideInfo(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RspGuideInfo[] newArray(int size) {
            return new RspGuideInfo[size];
        }
    };
    public static final int GUIDE_TYPE_CRUISE = 2;
    public static final int GUIDE_TYPE_GPS = 0;
    public static final int GUIDE_TYPE_SIMULATE = 1;
    private int cameraDistance;
    private int cameraSpeed;
    private int cameraType;
    private String curRoadName;
    private int guideType;
    private String nextRoadName;
    private int nextServiceAreaDistance;
    private String nextServiceAreaName;
    private int nextServiceAreaType;
    private int roadType;
    private int routeRemainDistance;
    private int routeRemainTime;
    private int routeRemainTrafficLightNum;
    private int secondServiceAreaDistance;
    private String secondServiceAreaName;
    private int secondServiceAreaType;
    private int segRemainDistance;
    private int segRemainTime;
    private int trafficLightNum;
    private int turnId;
    private String turnSvg;

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int getTrafficLightNum() {
        return this.trafficLightNum;
    }

    public void setTrafficLightNum(int trafficLightNum) {
        this.trafficLightNum = trafficLightNum;
    }

    public int getRouteRemainTrafficLightNum() {
        return this.routeRemainTrafficLightNum;
    }

    public void setRouteRemainTrafficLightNum(int routeRemainTrafficLightNum) {
        this.routeRemainTrafficLightNum = routeRemainTrafficLightNum;
    }

    public int getNextServiceAreaDistance() {
        return this.nextServiceAreaDistance;
    }

    public void setNextServiceAreaDistance(int nextServiceAreaDistance) {
        this.nextServiceAreaDistance = nextServiceAreaDistance;
    }

    public int getNextServiceAreaType() {
        return this.nextServiceAreaType;
    }

    public void setNextServiceAreaType(int nextServiceAreaType) {
        this.nextServiceAreaType = nextServiceAreaType;
    }

    public String getNextServiceAreaName() {
        return this.nextServiceAreaName;
    }

    public void setNextServiceAreaName(String nextServiceAreaName) {
        this.nextServiceAreaName = nextServiceAreaName;
    }

    public int getSecondServiceAreaDistance() {
        return this.secondServiceAreaDistance;
    }

    public void setSecondServiceAreaDistance(int secondServiceAreaDistance) {
        this.secondServiceAreaDistance = secondServiceAreaDistance;
    }

    public int getSecondServiceAreaType() {
        return this.secondServiceAreaType;
    }

    public void setSecondServiceAreaType(int secondServiceAreaType) {
        this.secondServiceAreaType = secondServiceAreaType;
    }

    public String getSecondServiceAreaName() {
        return this.secondServiceAreaName;
    }

    public void setSecondServiceAreaName(String secondServiceAreaName) {
        this.secondServiceAreaName = secondServiceAreaName;
    }

    public String getTurnSvg() {
        return this.turnSvg;
    }

    public void setTurnSvg(String turnSvg) {
        this.turnSvg = turnSvg;
    }

    public RspGuideInfo(NaviBaseModel reqModel) {
        copyBaseInfo(reqModel);
    }

    public int getGuideType() {
        return this.guideType;
    }

    public void setGuideType(int guideType) {
        this.guideType = guideType;
    }

    public int getRoadType() {
        return this.roadType;
    }

    public void setRoadType(int roadType) {
        this.roadType = roadType;
    }

    public int getRouteRemainDistance() {
        return this.routeRemainDistance;
    }

    public void setRouteRemainDistance(int routeRemainDistance) {
        this.routeRemainDistance = routeRemainDistance;
    }

    public int getRouteRemainTime() {
        return this.routeRemainTime;
    }

    public void setRouteRemainTime(int routeRemainTime) {
        this.routeRemainTime = routeRemainTime;
    }

    public int getSegRemainDistance() {
        return this.segRemainDistance;
    }

    public void setSegRemainDistance(int segRemainDistance) {
        this.segRemainDistance = segRemainDistance;
    }

    public int getSegRemainTime() {
        return this.segRemainTime;
    }

    public void setSegRemainTime(int segRemainTime) {
        this.segRemainTime = segRemainTime;
    }

    public int getTurnId() {
        return this.turnId;
    }

    public void setTurnId(int turnId) {
        this.turnId = turnId;
    }

    public String getCurRoadName() {
        return this.curRoadName;
    }

    public void setCurRoadName(String curRoadName) {
        this.curRoadName = curRoadName;
    }

    public String getNextRoadName() {
        return this.nextRoadName;
    }

    public void setNextRoadName(String nextRoadName) {
        this.nextRoadName = nextRoadName;
    }

    public int getCameraType() {
        return this.cameraType;
    }

    public void setCameraType(int cameraType) {
        this.cameraType = cameraType;
    }

    public int getCameraSpeed() {
        return this.cameraSpeed;
    }

    public void setCameraSpeed(int cameraSpeed) {
        this.cameraSpeed = cameraSpeed;
    }

    public int getCameraDistance() {
        return this.cameraDistance;
    }

    public void setCameraDistance(int cameraDistance) {
        this.cameraDistance = cameraDistance;
    }

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel, android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeInt(this.guideType);
        dest.writeInt(this.roadType);
        dest.writeInt(this.routeRemainDistance);
        dest.writeInt(this.routeRemainTime);
        dest.writeInt(this.segRemainDistance);
        dest.writeInt(this.segRemainTime);
        dest.writeInt(this.turnId);
        dest.writeString(this.curRoadName);
        dest.writeString(this.nextRoadName);
        dest.writeInt(this.trafficLightNum);
        dest.writeInt(this.routeRemainTrafficLightNum);
        dest.writeInt(this.cameraType);
        dest.writeInt(this.cameraSpeed);
        dest.writeInt(this.cameraDistance);
    }

    protected RspGuideInfo(Parcel in) {
        super(in);
        this.guideType = in.readInt();
        this.roadType = in.readInt();
        this.routeRemainDistance = in.readInt();
        this.routeRemainTime = in.readInt();
        this.segRemainDistance = in.readInt();
        this.segRemainTime = in.readInt();
        this.turnId = in.readInt();
        this.curRoadName = in.readString();
        this.nextRoadName = in.readString();
        this.trafficLightNum = in.readInt();
        this.routeRemainTrafficLightNum = in.readInt();
        this.cameraType = in.readInt();
        this.cameraSpeed = in.readInt();
        this.cameraDistance = in.readInt();
    }

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel
    public String toString() {
        super.toString();
        return "RspGuideInfo{guideType=" + this.guideType + ", roadType=" + this.roadType + ", routeRemainDistance=" + this.routeRemainDistance + ", routeRemainTime=" + this.routeRemainTime + ", segRemainDistance=" + this.segRemainDistance + ", segRemainTime=" + this.segRemainTime + ", turnId=" + this.turnId + ", curRoadName='" + this.curRoadName + "', nextRoadName='" + this.nextRoadName + "', nextServiceAreaDistance=" + this.nextServiceAreaDistance + ", nextServiceAreaType=" + this.nextServiceAreaType + ", nextServiceAreaName='" + this.nextServiceAreaName + "', secondServiceAreaDistance=" + this.secondServiceAreaDistance + ", secondServiceAreaType=" + this.secondServiceAreaType + ", secondServiceAreaName='" + this.secondServiceAreaName + "', turnSvg='" + this.turnSvg + "', trafficLightNum=" + this.trafficLightNum + ", routeRemainTrafficLightNum=" + this.routeRemainTrafficLightNum + ", cameraType='" + this.cameraType + "', cameraSpeed=" + this.cameraSpeed + ", cameraDistance=" + this.cameraDistance + '}';
    }

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel
    /* renamed from: clone */
    public RspGuideInfo mo324clone() {
        return (RspGuideInfo) super.mo324clone();
    }
}