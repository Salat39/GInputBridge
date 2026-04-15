package com.geely.lib.oneosapi.schedule.bean;

import android.os.Parcel;
import android.os.Parcelable;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class CPHolidayInfo implements Parcelable {
    public static final Parcelable.Creator<CPHolidayInfo> CREATOR = new Parcelable.Creator<CPHolidayInfo>() { // from class: com.geely.lib.oneosapi.schedule.bean.CPHolidayInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CPHolidayInfo createFromParcel(Parcel in) {
            return new CPHolidayInfo(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CPHolidayInfo[] newArray(int size) {
            return new CPHolidayInfo[size];
        }
    };
    private long eventID;
    private String eventsTitle;
    private String importEventType;
    private String importTime;
    private String solarTern;
    private String week;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public CPHolidayInfo() {
    }

    public CPHolidayInfo(JSONObject json) {
        this.importEventType = json.optString("dayOfHoliday");
        this.eventsTitle = json.optString("workOrHoliday");
        this.importTime = json.optString("holidayName");
        this.week = json.optString("dayOfWeek");
        this.solarTern = json.optString("date");
        this.eventID = json.optLong("eventID");
    }

    protected CPHolidayInfo(Parcel in) {
        this.importEventType = in.readString();
        this.eventsTitle = in.readString();
        this.importTime = in.readString();
        this.week = in.readString();
        this.solarTern = in.readString();
        this.eventID = in.readLong();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.importEventType);
        parcel.writeString(this.eventsTitle);
        parcel.writeString(this.importTime);
        parcel.writeString(this.week);
        parcel.writeString(this.solarTern);
        parcel.writeLong(this.eventID);
    }

    public String getImportEventType() {
        return this.importEventType;
    }

    public void setImportEventType(String importEventType) {
        this.importEventType = importEventType;
    }

    public String getEventsTitle() {
        return this.eventsTitle;
    }

    public void setEventsTitle(String eventsTitle) {
        this.eventsTitle = eventsTitle;
    }

    public String getImportTime() {
        return this.importTime;
    }

    public void setImportTime(String importTime) {
        this.importTime = importTime;
    }

    public String getWeek() {
        return this.week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getSolarTern() {
        return this.solarTern;
    }

    public void setSolarTern(String solarTern) {
        this.solarTern = solarTern;
    }

    public long getEventID() {
        return this.eventID;
    }

    public void setEventID(long eventID) {
        this.eventID = eventID;
    }

    public String toString() {
        return "CPHolidayInfo{importEventType='" + this.importEventType + "', eventsTitle='" + this.eventsTitle + "', importTime='" + this.importTime + "', week='" + this.week + "', solarTern='" + this.solarTern + "', eventID=" + this.eventID + '}';
    }
}