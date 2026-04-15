package com.geely.lib.oneosapi.recommendation.bean.sohu;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class SohuContentDataBean implements Parcelable {
    public static final Parcelable.Creator<SohuContentDataBean> CREATOR = new Parcelable.Creator<SohuContentDataBean>() { // from class: com.geely.lib.oneosapi.recommendation.bean.sohu.SohuContentDataBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SohuContentDataBean createFromParcel(Parcel in) {
            return new SohuContentDataBean(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SohuContentDataBean[] newArray(int size) {
            return new SohuContentDataBean[size];
        }
    };
    private Long hotScore;
    private String link;
    private String pic;
    private String title;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public SohuContentDataBean() {
    }

    protected SohuContentDataBean(Parcel in) {
        this.title = in.readString();
        if (in.readByte() == 0) {
            this.hotScore = null;
        } else {
            this.hotScore = Long.valueOf(in.readLong());
        }
        this.pic = in.readString();
        this.link = in.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        if (this.hotScore == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(this.hotScore.longValue());
        }
        dest.writeString(this.pic);
        dest.writeString(this.link);
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getHotScore() {
        return this.hotScore;
    }

    public void setHotScore(Long hotScore) {
        this.hotScore = hotScore;
    }

    public String getPic() {
        return this.pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getLink() {
        return this.link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String toString() {
        return "SohuContentDataBean{title='" + this.title + "', hotScore=" + this.hotScore + ", pic='" + this.pic + "', link='" + this.link + "'}";
    }
}