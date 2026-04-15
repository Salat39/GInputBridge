package com.geely.lib.oneosapi.schedule.bean;

import android.os.Parcel;
import android.os.Parcelable;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class CPScheduleInfo implements Parcelable {
    public static final Parcelable.Creator<CPScheduleInfo> CREATOR = new Parcelable.Creator<CPScheduleInfo>() { // from class: com.geely.lib.oneosapi.schedule.bean.CPScheduleInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CPScheduleInfo createFromParcel(Parcel in) {
            return new CPScheduleInfo(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CPScheduleInfo[] newArray(int size) {
            return new CPScheduleInfo[size];
        }
    };
    private String endTime;
    private long eventID;
    private String location;
    private String startTime;
    private String title;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    protected CPScheduleInfo(Parcel in) {
        this.location = in.readString();
        this.endTime = in.readString();
        this.title = in.readString();
        this.startTime = in.readString();
        this.eventID = in.readLong();
    }

    public CPScheduleInfo() {
    }

    public CPScheduleInfo(JSONObject json) {
        this.location = json.optString("location");
        this.endTime = json.optString("endTime");
        this.title = json.optString("title");
        this.startTime = json.optString("startTime");
        this.eventID = json.optLong("eventID");
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.location);
        parcel.writeString(this.endTime);
        parcel.writeString(this.title);
        parcel.writeString(this.startTime);
        parcel.writeLong(this.eventID);
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEndTime() {
        return this.endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStartTime() {
        return this.startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public static Parcelable.Creator<CPScheduleInfo> getCREATOR() {
        return CREATOR;
    }

    public long getEventID() {
        return this.eventID;
    }

    public void setEventID(long eventID) {
        this.eventID = eventID;
    }

    public String toString() {
        return "CPScheduleInfo{location='" + this.location + "', endTime='" + this.endTime + "', title='" + this.title + "', startTime='" + this.startTime + "', eventID=" + this.eventID + '}';
    }

    public static class Location implements Parcelable {
        public static final Parcelable.Creator<Location> CREATOR = new Parcelable.Creator<Location>() { // from class: com.geely.lib.oneosapi.schedule.bean.CPScheduleInfo.Location.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public Location createFromParcel(Parcel in) {
                return new Location(in);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public Location[] newArray(int size) {
                return new Location[size];
            }
        };

        /* renamed from: b */
        private Double f77b;

        /* renamed from: c */
        private String f78c;

        /* renamed from: d */
        private Double f79d;

        /* renamed from: e */
        private String f80e;

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public Location() {
        }

        public Location(JSONObject json) {
            this.f77b = Double.valueOf(json.optDouble("latitude"));
            this.f78c = json.optString("type");
            this.f79d = Double.valueOf(json.optDouble("longitude"));
            this.f80e = json.optString("address");
        }

        protected Location(Parcel in) {
            new Parcelable.Creator<Location>() { // from class: com.geely.lib.oneosapi.schedule.bean.CPScheduleInfo.Location.1NamelessClass_1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // android.os.Parcelable.Creator
                public Location createFromParcel(Parcel parcel) {
                    return null;
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // android.os.Parcelable.Creator
                public Location[] newArray(int i) {
                    return new Location[0];
                }
            };
            if (in.readByte() == 0) {
                this.f77b = null;
            } else {
                this.f77b = Double.valueOf(in.readDouble());
            }
            this.f78c = in.readString();
            if (in.readByte() == 0) {
                this.f79d = null;
            } else {
                this.f79d = Double.valueOf(in.readDouble());
            }
            this.f80e = in.readString();
        }

        /* renamed from: a */
        public final void m26a(Double var1) {
            this.f77b = var1;
        }

        /* renamed from: a */
        public final void m27a(String var1) {
            this.f78c = var1;
        }

        /* renamed from: b */
        public final void m28b(Double var1) {
            this.f79d = var1;
        }

        /* renamed from: b */
        public final void m29b(String var1) {
            this.f80e = var1;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            if (this.f77b == null) {
                parcel.writeByte((byte) 0);
            } else {
                parcel.writeByte((byte) 1);
                parcel.writeDouble(this.f77b.doubleValue());
            }
            parcel.writeString(this.f78c);
            if (this.f79d == null) {
                parcel.writeByte((byte) 0);
            } else {
                parcel.writeByte((byte) 1);
                parcel.writeDouble(this.f79d.doubleValue());
            }
            parcel.writeString(this.f80e);
        }

        public String toString() {
            return "{address=" + this.f80e + ", type=" + this.f78c + ", longitude='" + this.f79d + ", latitude=" + this.f77b + '}';
        }
    }
}