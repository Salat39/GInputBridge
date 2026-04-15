package com.geely.lib.oneosapi.navi.model.service;

import android.os.Parcel;
import android.os.Parcelable;
import com.geely.lib.oneosapi.navi.model.base.NaviBaseModel;
import com.geely.lib.oneosapi.navi.model.base.PoiInfo;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* loaded from: classes.dex */
public class RspGuideEvent extends NaviBaseModel implements Parcelable {
    public static final Parcelable.Creator<RspGuideEvent> CREATOR = new Parcelable.Creator<RspGuideEvent>() { // from class: com.geely.lib.oneosapi.navi.model.service.RspGuideEvent.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RspGuideEvent createFromParcel(Parcel source) {
            return new RspGuideEvent(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RspGuideEvent[] newArray(int size) {
            return new RspGuideEvent[size];
        }
    };
    private LocationInfo currentLocation;
    private int guideEventId;
    private int guideEventType;
    private long guideStartTime;
    private long guideStopTime;
    private long remainDistance;
    private long remainTimeInSeconds;
    private RouteInfo routeInfo;
    private LocationInfo startLocation;
    private PoiInfo startPoi;
    private LocationInfo targetLocation;
    private PoiInfo targetPoi;
    private int usedMap;

    @Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
    @Retention(RetentionPolicy.SOURCE)
    public @interface GuideEventTypes {
        public static final int GUIDING = 2;
        public static final int START = 0;
        public static final int STOP = 1;
    }

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public RspGuideEvent(NaviBaseModel reqModel) {
        copyBaseInfo(reqModel);
    }

    public LocationInfo getStartLocation() {
        return this.startLocation;
    }

    public void setStartLocation(LocationInfo startLocation) {
        this.startLocation = startLocation;
    }

    public LocationInfo getTargetLocation() {
        return this.targetLocation;
    }

    public void setTargetLocation(LocationInfo targetLocation) {
        this.targetLocation = targetLocation;
    }

    public PoiInfo getStartPoi() {
        return this.startPoi;
    }

    public void setStartPoi(PoiInfo startPoi) {
        this.startPoi = startPoi;
    }

    public PoiInfo getTargetPoi() {
        return this.targetPoi;
    }

    public void setTargetPoi(PoiInfo targetPoi) {
        this.targetPoi = targetPoi;
    }

    public LocationInfo getCurrentLocation() {
        return this.currentLocation;
    }

    public void setCurrentLocation(LocationInfo currentLocation) {
        this.currentLocation = currentLocation;
    }

    public RouteInfo getRouteInfo() {
        return this.routeInfo;
    }

    public void setRouteInfo(RouteInfo routeInfo) {
        this.routeInfo = routeInfo;
    }

    public int getUsedMap() {
        return this.usedMap;
    }

    public void setUsedMap(int usedMap) {
        this.usedMap = usedMap;
    }

    public int getGuideEventId() {
        return this.guideEventId;
    }

    public void setGuideEventId(int guideEventId) {
        this.guideEventId = guideEventId;
    }

    public int getGuideEventType() {
        return this.guideEventType;
    }

    public void setGuideEventType(int guideEventType) {
        this.guideEventType = guideEventType;
    }

    public long getGuideStartTime() {
        return this.guideStartTime;
    }

    public void setGuideStartTime(long guideStartTime) {
        this.guideStartTime = guideStartTime;
    }

    public long getGuideStopTime() {
        return this.guideStopTime;
    }

    public void setGuideStopTime(long guideStopTime) {
        this.guideStopTime = guideStopTime;
    }

    public long getRemainTimeInSeconds() {
        return this.remainTimeInSeconds;
    }

    public void setRemainTimeInSeconds(long remainTimeInSeconds) {
        this.remainTimeInSeconds = remainTimeInSeconds;
    }

    public long getRemainDistance() {
        return this.remainDistance;
    }

    public void setRemainDistance(long remainDistance) {
        this.remainDistance = remainDistance;
    }

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel, android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeParcelable(this.startLocation, flags);
        dest.writeParcelable(this.targetLocation, flags);
        dest.writeParcelable(this.currentLocation, flags);
        dest.writeParcelable(this.routeInfo, flags);
        dest.writeParcelable(this.startPoi, flags);
        dest.writeParcelable(this.targetPoi, flags);
        dest.writeInt(this.usedMap);
        dest.writeInt(this.guideEventId);
        dest.writeInt(this.guideEventType);
        dest.writeLong(this.guideStartTime);
        dest.writeLong(this.guideStopTime);
        dest.writeLong(this.remainTimeInSeconds);
        dest.writeLong(this.remainDistance);
    }

    protected RspGuideEvent(Parcel in) {
        super(in);
        this.startLocation = (LocationInfo) in.readParcelable(LocationInfo.class.getClassLoader());
        this.targetLocation = (LocationInfo) in.readParcelable(LocationInfo.class.getClassLoader());
        this.currentLocation = (LocationInfo) in.readParcelable(LocationInfo.class.getClassLoader());
        this.routeInfo = (RouteInfo) in.readParcelable(RouteInfo.class.getClassLoader());
        this.startPoi = (PoiInfo) in.readParcelable(PoiInfo.class.getClassLoader());
        this.targetPoi = (PoiInfo) in.readParcelable(PoiInfo.class.getClassLoader());
        this.usedMap = in.readInt();
        this.guideEventId = in.readInt();
        this.guideEventType = in.readInt();
        this.guideStartTime = in.readLong();
        this.guideStopTime = in.readLong();
        this.remainTimeInSeconds = in.readLong();
        this.remainDistance = in.readLong();
    }

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel
    public String toString() {
        return "RspGuideEvent{startLocation=" + this.startLocation + ", targetLocation=" + this.targetLocation + ", currentLocation=" + this.currentLocation + ", startPoi=" + this.startPoi + ", targetPoi=" + this.targetPoi + ", routeInfo=" + this.routeInfo + ", usedMap=" + this.usedMap + ", guideEventId=" + this.guideEventId + ", guideEventType=" + this.guideEventType + ", guideStartTime=" + this.guideStartTime + ", guideStopTime=" + this.guideStopTime + ", remainTimeInSeconds=" + this.remainTimeInSeconds + ", remainDistance=" + this.remainDistance + ", {base=" + super.toString() + "}; }";
    }
}