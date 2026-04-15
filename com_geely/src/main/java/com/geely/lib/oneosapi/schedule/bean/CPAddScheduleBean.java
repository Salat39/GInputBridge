package com.geely.lib.oneosapi.schedule.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class CPAddScheduleBean implements Parcelable {
    public static final Parcelable.Creator<CPAddScheduleBean> CREATOR = new Parcelable.Creator<CPAddScheduleBean>() { // from class: com.geely.lib.oneosapi.schedule.bean.CPAddScheduleBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CPAddScheduleBean createFromParcel(Parcel in) {
            return new CPAddScheduleBean(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CPAddScheduleBean[] newArray(int size) {
            return new CPAddScheduleBean[size];
        }
    };
    private String content;
    private TimeInfo endTime;
    private String location;
    private String repeatInfo;
    private String repeatType;
    private TimeInfo startTime;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public TimeInfo getStartTime() {
        return this.startTime;
    }

    public void setStartTime(TimeInfo startTime) {
        this.startTime = startTime;
    }

    public TimeInfo getEndTime() {
        return this.endTime;
    }

    public void setEndTime(TimeInfo endTime) {
        this.endTime = endTime;
    }

    public String getRepeatType() {
        return this.repeatType;
    }

    public void setRepeatType(String repeatType) {
        this.repeatType = repeatType;
    }

    public String getRepeatInfo() {
        return this.repeatInfo;
    }

    public void setRepeatInfo(String repeatInfo) {
        this.repeatInfo = repeatInfo;
    }

    public CPAddScheduleBean() {
    }

    public CPAddScheduleBean(String content, TimeInfo startTime, TimeInfo endTime, String repeatType, String repeatInfo) {
        this.content = content;
        this.startTime = startTime;
        this.endTime = endTime;
        this.repeatType = repeatType;
        this.repeatInfo = repeatInfo;
    }

    protected CPAddScheduleBean(Parcel in) {
        this.content = in.readString();
        this.startTime = (TimeInfo) in.readParcelable(TimeInfo.class.getClassLoader());
        this.endTime = (TimeInfo) in.readParcelable(TimeInfo.class.getClassLoader());
        this.repeatType = in.readString();
        this.repeatInfo = in.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.content);
        dest.writeParcelable(this.startTime, flags);
        dest.writeParcelable(this.endTime, flags);
        dest.writeString(this.repeatType);
        dest.writeString(this.repeatInfo);
    }

    public void readFromParcel(Parcel in) {
        this.content = in.readString();
        this.startTime = (TimeInfo) in.readParcelable(TimeInfo.class.getClassLoader());
        this.endTime = (TimeInfo) in.readParcelable(TimeInfo.class.getClassLoader());
        this.repeatType = in.readString();
        this.repeatInfo = in.readString();
    }

    public String toString() {
        return "CPAddScheduleBean{content='" + this.content + "', startTime=" + this.startTime + ", endTime=" + this.endTime + ", repeatType='" + this.repeatType + "', repeatInfo='" + this.repeatInfo + "'}";
    }

    public static class TimeInfo implements Parcelable {
        public static final Parcelable.Creator<TimeInfo> CREATOR = new Parcelable.Creator<TimeInfo>() { // from class: com.geely.lib.oneosapi.schedule.bean.CPAddScheduleBean.TimeInfo.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public TimeInfo createFromParcel(Parcel in) {
                return new TimeInfo(in);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public TimeInfo[] newArray(int size) {
                return new TimeInfo[size];
            }
        };
        private String date;
        private String time;

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public TimeInfo() {
        }

        public TimeInfo(String date, String time) {
            this.date = date;
            this.time = time;
        }

        public String getDate() {
            return this.date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getTime() {
            return this.time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.date);
            dest.writeString(this.time);
        }

        public void readFromParcel(Parcel in) {
            this.date = in.readString();
            this.time = in.readString();
        }

        protected TimeInfo(Parcel in) {
            this.date = in.readString();
            this.time = in.readString();
        }

        public String toString() {
            return "TimeInfo{date='" + this.date + "', time='" + this.time + "'}";
        }
    }
}