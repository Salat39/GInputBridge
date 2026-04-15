package com.geely.lib.oneosapi.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class NewsItemBean implements Parcelable {
    public static final Parcelable.Creator<NewsItemBean> CREATOR = new Parcelable.Creator<NewsItemBean>() { // from class: com.geely.lib.oneosapi.bean.NewsItemBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NewsItemBean createFromParcel(Parcel in) {
            return new NewsItemBean(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NewsItemBean[] newArray(int size) {
            return new NewsItemBean[size];
        }
    };
    private String docid;
    private String pubtime;
    private String shortcut;

    private String sourceInfo;
    private String srcfrom;
    private String title;
    private String voice_summary;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public NewsItemBean() {
    }

    protected NewsItemBean(Parcel in) {
        this.docid = in.readString();
        this.srcfrom = in.readString();
        this.title = in.readString();
        this.shortcut = in.readString();
        this.voice_summary = in.readString();
        this.pubtime = in.readString();
        this.sourceInfo = in.readString();
    }

    public String getDocid() {
        return this.docid;
    }

    public void setDocid(String docid) {
        this.docid = docid;
    }

    public String getSrcfrom() {
        return this.srcfrom;
    }

    public void setSrcfrom(String srcfrom) {
        this.srcfrom = srcfrom;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortcut() {
        return this.shortcut;
    }

    public void setShortcut(String shortcut) {
        this.shortcut = shortcut;
    }

    public String getVoice_summary() {
        return this.voice_summary;
    }

    public void setVoice_summary(String voice_summary) {
        this.voice_summary = voice_summary;
    }

    public String getPubtime() {
        return this.pubtime;
    }

    public void setPubtime(String pubtime) {
        this.pubtime = pubtime;
    }

    public String getSourceInfo() {
        return this.sourceInfo;
    }

    public void setSourceInfo(String sourceInfo) {
        this.sourceInfo = sourceInfo;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.docid);
        dest.writeString(this.srcfrom);
        dest.writeString(this.title);
        dest.writeString(this.shortcut);
        dest.writeString(this.voice_summary);
        dest.writeString(this.pubtime);
        dest.writeString(this.sourceInfo);
    }
}