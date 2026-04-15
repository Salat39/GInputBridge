package com.geely.lib.oneosapi.navi.model.client;

import android.os.Parcel;
import android.os.Parcelable;
import com.geely.lib.oneosapi.navi.model.base.NaviBaseModel;
import com.geely.lib.oneosapi.navi.model.base.NaviProtocolID;

/* loaded from: classes.dex */
public class NaviPushMSGData extends NaviBaseModel {
    public static final Parcelable.Creator<NaviPushMSGData> CREATOR = new Parcelable.Creator<NaviPushMSGData>() { // from class: com.geely.lib.oneosapi.navi.model.client.NaviPushMSGData.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NaviPushMSGData createFromParcel(Parcel source) {
            return new NaviPushMSGData(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NaviPushMSGData[] newArray(int size) {
            return new NaviPushMSGData[size];
        }
    };
    public static final int PRIORITY_HIGH = 2;
    public static final int PRIORITY_LOW = 0;
    public static final int PRIORITY_MIDDLE = 1;
    private int countDownTime;
    private double poiLatitude;
    private double poiLongitude;
    private String poiName;
    private int priority;
    private String sender;
    private String subTitle;
    private String title;

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public NaviPushMSGData() {
        setProtocolID(NaviProtocolID.NAVI_PUSH_MESSAGE);
    }

    public int getPriority() {
        return this.priority;
    }

    public NaviPushMSGData setPriority(int priority) {
        this.priority = priority;
        return this;
    }

    public int getCountDownTime() {
        return this.countDownTime;
    }

    public NaviPushMSGData setCountDownTime(int countDownTime) {
        this.countDownTime = countDownTime;
        return this;
    }

    public String getTitle() {
        return this.title;
    }

    public NaviPushMSGData setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getSubTitle() {
        return this.subTitle;
    }

    public NaviPushMSGData setSubTitle(String subTitle) {
        this.subTitle = subTitle;
        return this;
    }

    public String getPoiName() {
        return this.poiName;
    }

    public NaviPushMSGData setPoiName(String name) {
        this.poiName = name;
        return this;
    }

    public double getPoiLatitude() {
        return this.poiLatitude;
    }

    public NaviPushMSGData setPoiLatitude(double poiLatitude) {
        this.poiLatitude = poiLatitude;
        return this;
    }

    public double getPoiLongitude() {
        return this.poiLongitude;
    }

    public NaviPushMSGData setPoiLongitude(double poiLongitude) {
        this.poiLongitude = poiLongitude;
        return this;
    }

    public String getSender() {
        return this.sender;
    }

    public NaviPushMSGData setSender(String sender) {
        this.sender = sender;
        return this;
    }

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel, android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeInt(this.priority);
        dest.writeInt(this.countDownTime);
        dest.writeString(this.title);
        dest.writeString(this.subTitle);
        dest.writeString(this.poiName);
        dest.writeDouble(this.poiLatitude);
        dest.writeDouble(this.poiLongitude);
        dest.writeString(this.sender);
    }

    protected NaviPushMSGData(Parcel in) {
        super(in);
        this.priority = in.readInt();
        this.countDownTime = in.readInt();
        this.title = in.readString();
        this.subTitle = in.readString();
        this.poiName = in.readString();
        this.poiLatitude = in.readDouble();
        this.poiLongitude = in.readDouble();
        this.sender = in.readString();
    }

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel
    public String toString() {
        return "NaviPushMSGData{priority=" + this.priority + ", priority='" + this.priority + "', countDownTime=" + this.countDownTime + ", title='" + this.title + "', subTitle='" + this.subTitle + "', poiName='" + this.poiName + "', poiLatitude='" + this.poiLatitude + "', poiLongitude='" + this.poiLongitude + "', sender='" + this.sender + "'{base=" + super.toString() + "}; }";
    }
}