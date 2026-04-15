package com.geely.lib.oneosapi.recommendation.bean.aqy;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class AqyContentDataBean implements Parcelable {
    public static final Parcelable.Creator<AqyContentDataBean> CREATOR = new Parcelable.Creator<AqyContentDataBean>() { // from class: com.geely.lib.oneosapi.recommendation.bean.aqy.AqyContentDataBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AqyContentDataBean createFromParcel(Parcel in) {
            return new AqyContentDataBean(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AqyContentDataBean[] newArray(int size) {
            return new AqyContentDataBean[size];
        }
    };
    private String albumId;
    private String albumName;
    private int chnId;
    private String desc;
    private String focus;
    private int hot;
    private boolean isVIP;
    private String link;
    private String name;
    private String pic;
    private boolean qipuId;
    private String updateTip;
    private String vipType;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public AqyContentDataBean() {
    }

    protected AqyContentDataBean(Parcel in) {
        this.name = in.readString();
        this.chnId = in.readInt();
        this.vipType = in.readString();
        this.updateTip = in.readString();
        this.hot = in.readInt();
        this.link = in.readString();
        this.pic = in.readString();
        this.isVIP = in.readByte() != 0;
        this.albumId = in.readString();
        this.albumName = in.readString();
        this.qipuId = in.readByte() != 0;
        this.focus = in.readString();
        this.desc = in.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.name);
        parcel.writeInt(this.chnId);
        parcel.writeString(this.vipType);
        parcel.writeString(this.updateTip);
        parcel.writeInt(this.hot);
        parcel.writeString(this.link);
        parcel.writeString(this.pic);
        parcel.writeByte(this.isVIP ? (byte) 1 : (byte) 0);
        parcel.writeString(this.albumId);
        parcel.writeString(this.albumName);
        parcel.writeByte(this.qipuId ? (byte) 1 : (byte) 0);
        parcel.writeString(this.focus);
        parcel.writeString(this.desc);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getChnId() {
        return this.chnId;
    }

    public void setChnId(int chnId) {
        this.chnId = chnId;
    }

    public String getVipType() {
        return this.vipType;
    }

    public void setVipType(String vipType) {
        this.vipType = vipType;
    }

    public String getUpdateTip() {
        return this.updateTip;
    }

    public void setUpdateTip(String updateTip) {
        this.updateTip = updateTip;
    }

    public int getHot() {
        return this.hot;
    }

    public void setHot(int hot) {
        this.hot = hot;
    }

    public String getLink() {
        return this.link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPic() {
        return this.pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public boolean isVIP() {
        return this.isVIP;
    }

    public void setVIP(boolean VIP) {
        this.isVIP = VIP;
    }

    public String getAlbumId() {
        return this.albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    public String getAlbumName() {
        return this.albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public boolean isQipuId() {
        return this.qipuId;
    }

    public void setQipuId(boolean qipuId) {
        this.qipuId = qipuId;
    }

    public String getFocus() {
        return this.focus;
    }

    public void setFocus(String focus) {
        this.focus = focus;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String toString() {
        return "AqyContentDataBean{name='" + this.name + "', chnId=" + this.chnId + ", vipType='" + this.vipType + "', updateTip='" + this.updateTip + "', hot=" + this.hot + ", link='" + this.link + "', pic='" + this.pic + "', isVIP=" + this.isVIP + ", albumId='" + this.albumId + "', albumName='" + this.albumName + "', qipuId=" + this.qipuId + ", focus='" + this.focus + "', desc='" + this.desc + "'}";
    }
}