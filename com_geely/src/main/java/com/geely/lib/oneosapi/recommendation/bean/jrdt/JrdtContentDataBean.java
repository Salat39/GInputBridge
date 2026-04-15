package com.geely.lib.oneosapi.recommendation.bean.jrdt;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class JrdtContentDataBean implements Parcelable {
    public static final Parcelable.Creator<JrdtContentDataBean> CREATOR = new Parcelable.Creator<JrdtContentDataBean>() { // from class: com.geely.lib.oneosapi.recommendation.bean.jrdt.JrdtContentDataBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public JrdtContentDataBean createFromParcel(Parcel in) {
            return new JrdtContentDataBean(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public JrdtContentDataBean[] newArray(int size) {
            return new JrdtContentDataBean[size];
        }
    };
    private String coverUrl;
    private String formattedHeat;
    private String heat;
    private String ivySubId;
    private String ivyTitle;
    private String releaseTime;
    private String showImg;
    private String thumbnailUrl;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public JrdtContentDataBean() {
    }

    protected JrdtContentDataBean(Parcel in) {
        this.ivySubId = in.readString();
        this.ivyTitle = in.readString();
        this.coverUrl = in.readString();
        this.thumbnailUrl = in.readString();
        this.releaseTime = in.readString();
        this.heat = in.readString();
        this.formattedHeat = in.readString();
        this.showImg = in.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.ivySubId);
        dest.writeString(this.ivyTitle);
        dest.writeString(this.coverUrl);
        dest.writeString(this.thumbnailUrl);
        dest.writeString(this.releaseTime);
        dest.writeString(this.heat);
        dest.writeString(this.formattedHeat);
        dest.writeString(this.showImg);
    }

    public String toString() {
        return "JrdtContentDataBean{ivySubId='" + this.ivySubId + "', ivyTitle='" + this.ivyTitle + "', coverUrl=" + this.coverUrl + ", thumbnailUrl=" + this.thumbnailUrl + ", releaseTime='" + this.releaseTime + "', heat='" + this.heat + "', formattedHeat=" + this.formattedHeat + ", showImg='" + this.showImg + "'}";
    }

    public String getIvySubId() {
        return this.ivySubId;
    }

    public void setIvySubId(String ivySubId) {
        this.ivySubId = ivySubId;
    }

    public String getIvyTitle() {
        return this.ivyTitle;
    }

    public void setIvyTitle(String ivyTitle) {
        this.ivyTitle = ivyTitle;
    }

    public String getCoverUrl() {
        return this.coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public String getThumbnailUrl() {
        return this.thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getReleaseTime() {
        return this.releaseTime;
    }

    public void setReleaseTime(String releaseTime) {
        this.releaseTime = releaseTime;
    }

    public String getHeat() {
        return this.heat;
    }

    public void setHeat(String heat) {
        this.heat = heat;
    }

    public String getFormattedHeat() {
        return this.formattedHeat;
    }

    public void setFormattedHeat(String formattedHeat) {
        this.formattedHeat = formattedHeat;
    }

    public String getShowImg() {
        return this.showImg;
    }

    public void setShowImg(String showImg) {
        this.showImg = showImg;
    }
}