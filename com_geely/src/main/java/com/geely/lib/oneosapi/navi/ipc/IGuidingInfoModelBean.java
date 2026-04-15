package com.geely.lib.oneosapi.navi.ipc;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class IGuidingInfoModelBean implements Parcelable {
    public static final Parcelable.Creator<IGuidingInfoModelBean> CREATOR = new Parcelable.Creator<IGuidingInfoModelBean>() { // from class: com.geely.lib.oneosapi.navi.ipc.IGuidingInfoModelBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public IGuidingInfoModelBean createFromParcel(Parcel in) {
            return new IGuidingInfoModelBean(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public IGuidingInfoModelBean[] newArray(int size) {
            return new IGuidingInfoModelBean[size];
        }
    };
    private int cameraDistance;
    private int cameraSpeed;
    private int cameraType;
    private float carDirection;
    private String currentRoadName;
    private Bundle extras;
    private int guideType;
    private String nextRoadName;
    private int nextServiceAreaDistance;
    private String nextServiceAreaName;
    private int nextServiceAreaType;
    private int nextTurnDistance;
    private int nextTurnTime;
    private int remainDistance;
    private double remainEctricity;
    private double remainOil;
    private int remainTime;
    private int resultCode;
    private int roadType;
    private int serviceAreaDistance;
    private String serviceAreaName;
    private int serviceAreaType;
    private int turnIconId;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public IGuidingInfoModelBean() {
        this.roadType = -1;
    }

    public void readFromParcel(Parcel in) {
        this.guideType = in.readInt();
        this.turnIconId = in.readInt();
        this.remainDistance = in.readInt();
        this.remainTime = in.readInt();
        this.nextTurnDistance = in.readInt();
        this.nextTurnTime = in.readInt();
        this.nextRoadName = in.readString();
        this.currentRoadName = in.readString();
        this.carDirection = in.readFloat();
        this.roadType = in.readInt();
        this.serviceAreaType = in.readInt();
        this.serviceAreaDistance = in.readInt();
        this.serviceAreaName = in.readString();
        this.nextServiceAreaType = in.readInt();
        this.nextServiceAreaDistance = in.readInt();
        this.nextServiceAreaName = in.readString();
        this.cameraType = in.readInt();
        this.cameraSpeed = in.readInt();
        this.cameraDistance = in.readInt();
        this.remainEctricity = in.readDouble();
        this.remainOil = in.readDouble();
        this.resultCode = in.readInt();
        this.extras = in.readBundle(Bundle.class.getClassLoader());
    }

    protected IGuidingInfoModelBean(Parcel in) {
        this.roadType = -1;
        this.guideType = in.readInt();
        this.turnIconId = in.readInt();
        this.remainDistance = in.readInt();
        this.remainTime = in.readInt();
        this.nextTurnDistance = in.readInt();
        this.nextTurnTime = in.readInt();
        this.nextRoadName = in.readString();
        this.currentRoadName = in.readString();
        this.carDirection = in.readFloat();
        this.roadType = in.readInt();
        this.serviceAreaType = in.readInt();
        this.serviceAreaDistance = in.readInt();
        this.serviceAreaName = in.readString();
        this.nextServiceAreaType = in.readInt();
        this.nextServiceAreaDistance = in.readInt();
        this.nextServiceAreaName = in.readString();
        this.cameraType = in.readInt();
        this.cameraSpeed = in.readInt();
        this.cameraDistance = in.readInt();
        this.remainEctricity = in.readDouble();
        this.remainOil = in.readDouble();
        this.resultCode = in.readInt();
        this.extras = in.readBundle(Bundle.class.getClassLoader());
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.guideType);
        dest.writeInt(this.turnIconId);
        dest.writeInt(this.remainDistance);
        dest.writeInt(this.remainTime);
        dest.writeInt(this.nextTurnDistance);
        dest.writeInt(this.nextTurnTime);
        dest.writeString(this.nextRoadName);
        dest.writeString(this.currentRoadName);
        dest.writeFloat(this.carDirection);
        dest.writeInt(this.roadType);
        dest.writeInt(this.serviceAreaType);
        dest.writeInt(this.serviceAreaDistance);
        dest.writeString(this.serviceAreaName);
        dest.writeInt(this.nextServiceAreaType);
        dest.writeInt(this.nextServiceAreaDistance);
        dest.writeString(this.nextServiceAreaName);
        dest.writeInt(this.cameraType);
        dest.writeInt(this.cameraSpeed);
        dest.writeInt(this.cameraDistance);
        dest.writeDouble(this.remainEctricity);
        dest.writeDouble(this.remainOil);
        dest.writeInt(this.resultCode);
        dest.writeBundle(this.extras);
    }

    public double getRemainEctricity() {
        return this.remainEctricity;
    }

    public void setRemainEctricity(double remainEctricity) {
        this.remainEctricity = remainEctricity;
    }

    public double getRemainOil() {
        return this.remainOil;
    }

    public void setRemainOil(double remainOil) {
        this.remainOil = remainOil;
    }

    public int getGuideType() {
        return this.guideType;
    }

    public void setGuideType(int guideType) {
        this.guideType = guideType;
    }

    public int getTurnIconId() {
        return this.turnIconId;
    }

    public void setTurnIconId(int turnIconId) {
        this.turnIconId = turnIconId;
    }

    public int getRemainDistance() {
        return this.remainDistance;
    }

    public void setRemainDistance(int remainDistance) {
        this.remainDistance = remainDistance;
    }

    public int getRemainTime() {
        return this.remainTime;
    }

    public void setRemainTime(int remainTime) {
        this.remainTime = remainTime;
    }

    public int getNextTurnDistance() {
        return this.nextTurnDistance;
    }

    public void setNextTurnDistance(int nextTurnDistance) {
        this.nextTurnDistance = nextTurnDistance;
    }

    public int getNextTurnTime() {
        return this.nextTurnTime;
    }

    public void setNextTurnTime(int nextTurnTime) {
        this.nextTurnTime = nextTurnTime;
    }

    public String getNextRoadName() {
        return this.nextRoadName;
    }

    public void setNextRoadName(String nextRoadName) {
        this.nextRoadName = nextRoadName;
    }

    public String getCurrentRoadName() {
        return this.currentRoadName;
    }

    public void setCurrentRoadName(String currentRoadName) {
        this.currentRoadName = currentRoadName;
    }

    public float getCarDirection() {
        return this.carDirection;
    }

    public void setCarDirection(float carDirection) {
        this.carDirection = carDirection;
    }

    public int getRoadType() {
        return this.roadType;
    }

    public void setRoadType(int roadType) {
        this.roadType = roadType;
    }

    public int getServiceAreaType() {
        return this.serviceAreaType;
    }

    public void setServiceAreaType(int serviceAreaType) {
        this.serviceAreaType = serviceAreaType;
    }

    public int getServiceAreaDistance() {
        return this.serviceAreaDistance;
    }

    public void setServiceAreaDistance(int serviceAreaDistance) {
        this.serviceAreaDistance = serviceAreaDistance;
    }

    public String getServiceAreaName() {
        return this.serviceAreaName;
    }

    public void setServiceAreaName(String serviceAreaName) {
        this.serviceAreaName = serviceAreaName;
    }

    public int getNextServiceAreaType() {
        return this.nextServiceAreaType;
    }

    public void setNextServiceAreaType(int nextServiceAreaType) {
        this.nextServiceAreaType = nextServiceAreaType;
    }

    public int getNextServiceAreaDistance() {
        return this.nextServiceAreaDistance;
    }

    public void setNextServiceAreaDistance(int nextServiceAreaDistance) {
        this.nextServiceAreaDistance = nextServiceAreaDistance;
    }

    public String getNextServiceAreaName() {
        return this.nextServiceAreaName;
    }

    public void setNextServiceAreaName(String nextServiceAreaName) {
        this.nextServiceAreaName = nextServiceAreaName;
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
        return "IGuidingInfoModelBean{guideType=" + this.guideType + ", turnIconId=" + this.turnIconId + ", remainDistance=" + this.remainDistance + ", remainTime=" + this.remainTime + ", nextTurnDistance=" + this.nextTurnDistance + ", nextTurnTime=" + this.nextTurnTime + ", nextRoadName='" + this.nextRoadName + "', currentRoadName='" + this.currentRoadName + "', carDirection=" + this.carDirection + ", roadType=" + this.roadType + ", serviceAreaType=" + this.serviceAreaType + ", serviceAreaDistance=" + this.serviceAreaDistance + ", serviceAreaName='" + this.serviceAreaName + "', nextServiceAreaType=" + this.nextServiceAreaType + ", nextServiceAreaDistance=" + this.nextServiceAreaDistance + ", nextServiceAreaName='" + this.nextServiceAreaName + "', cameraType=" + this.cameraType + ", cameraSpeed=" + this.cameraSpeed + ", cameraDistance=" + this.cameraDistance + ", remainEctricity=" + this.remainEctricity + ", remainOil=" + this.remainOil + ", resultCode=" + this.resultCode + ", extras=" + this.extras + '}';
    }
}