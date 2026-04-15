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
public class RspCircleFenceEvent extends NaviBaseModel implements Parcelable {
    public static final Parcelable.Creator<RspCircleFenceEvent> CREATOR = new Parcelable.Creator<RspCircleFenceEvent>() { // from class: com.geely.lib.oneosapi.navi.model.service.RspCircleFenceEvent.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RspCircleFenceEvent createFromParcel(Parcel source) {
            return new RspCircleFenceEvent(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RspCircleFenceEvent[] newArray(int size) {
            return new RspCircleFenceEvent[size];
        }
    };
    private LocationInfo currentLocation;
    private int eventType;
    private boolean targetIsHome;
    private PoiInfo targetPoiInfo;

    @Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
    @Retention(RetentionPolicy.SOURCE)
    public @interface FenceEventTypes {
        public static final int ENTER = 0;
        public static final int LEAVE = 1;
    }

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public RspCircleFenceEvent(NaviBaseModel reqModel) {
        copyBaseInfo(reqModel);
    }

    public int getEventType() {
        return this.eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }

    public PoiInfo getTargetPoiInfo() {
        return this.targetPoiInfo;
    }

    public void setTargetPoiInfo(PoiInfo targetPoiInfo) {
        this.targetPoiInfo = targetPoiInfo;
    }

    public boolean isTargetIsHome() {
        return this.targetIsHome;
    }

    public void setTargetIsHome(boolean targetIsHome) {
        this.targetIsHome = targetIsHome;
    }

    public LocationInfo getCurrentLocation() {
        return this.currentLocation;
    }

    public void setCurrentLocation(LocationInfo currentLocation) {
        this.currentLocation = currentLocation;
    }

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeInt(this.eventType);
        parcel.writeParcelable(this.targetPoiInfo, i);
        parcel.writeByte(this.targetIsHome ? (byte) 1 : (byte) 0);
        parcel.writeParcelable(this.currentLocation, i);
    }

    protected RspCircleFenceEvent(Parcel in) {
        super(in);
        this.eventType = in.readInt();
        this.targetPoiInfo = (PoiInfo) in.readParcelable(PoiInfo.class.getClassLoader());
        this.targetIsHome = in.readByte() != 0;
        this.currentLocation = (LocationInfo) in.readParcelable(LocationInfo.class.getClassLoader());
    }

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel
    public String toString() {
        return "RspCircleFenceEvent{eventType=" + this.eventType + ", targetPoiInfo=" + this.targetPoiInfo + ", targetIsHome=" + this.targetIsHome + ", currentLocation=" + this.currentLocation + ", {base=" + super.toString() + "}; }";
    }
}