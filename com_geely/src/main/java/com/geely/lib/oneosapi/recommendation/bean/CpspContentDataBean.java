package com.geely.lib.oneosapi.recommendation.bean;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

/* loaded from: classes.dex */
public class CpspContentDataBean implements Parcelable {
    public static final Parcelable.Creator<CpspContentDataBean> CREATOR = new Parcelable.Creator<CpspContentDataBean>() { // from class: com.geely.lib.oneosapi.recommendation.bean.CpspContentDataBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CpspContentDataBean createFromParcel(Parcel in) {
            return new CpspContentDataBean(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CpspContentDataBean[] newArray(int size) {
            return new CpspContentDataBean[size];
        }
    };
    private String albumId;
    private String albumName;
    private String buttonName;
    private int chnId;
    private String coverUrl;
    private String desc;
    private List<DiscountInfoBean> discountInfos;
    private String distance;
    private String focus;
    private String formattedHeat;
    private String heat;
    private int hot;
    private Long hotScore;
    private boolean isVIP;
    private String ivySubId;
    private String ivyTitle;
    private String label;
    private float latitude;
    private String link;
    private String logo;
    private float longitude;
    private String name;
    private String operateTime;
    private String pic;
    private boolean qipuId;
    private String releaseTime;
    private int score;
    private String showImg;
    private String storeName;
    private String thumbnailUrl;
    private String title;
    private String updateTip;
    private String vipType;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public CpspContentDataBean() {
    }

    protected CpspContentDataBean(Parcel in) {
        this.title = in.readString();
        if (in.readByte() == 0) {
            this.hotScore = null;
        } else {
            this.hotScore = Long.valueOf(in.readLong());
        }
        this.pic = in.readString();
        this.link = in.readString();
        this.storeName = in.readString();
        this.score = in.readInt();
        this.distance = in.readString();
        this.discountInfos = in.createTypedArrayList(DiscountInfoBean.CREATOR);
        this.label = in.readString();
        this.buttonName = in.readString();
        this.longitude = in.readFloat();
        this.latitude = in.readFloat();
        this.logo = in.readString();
        this.name = in.readString();
        this.chnId = in.readInt();
        this.vipType = in.readString();
        this.updateTip = in.readString();
        this.hot = in.readInt();
        this.isVIP = in.readByte() != 0;
        this.albumId = in.readString();
        this.albumName = in.readString();
        this.qipuId = in.readByte() != 0;
        this.focus = in.readString();
        this.desc = in.readString();
        this.operateTime = in.readString();
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
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.title);
        if (this.hotScore == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeLong(this.hotScore.longValue());
        }
        parcel.writeString(this.pic);
        parcel.writeString(this.link);
        parcel.writeString(this.storeName);
        parcel.writeInt(this.score);
        parcel.writeString(this.distance);
        parcel.writeTypedList(this.discountInfos);
        parcel.writeString(this.label);
        parcel.writeString(this.buttonName);
        parcel.writeFloat(this.longitude);
        parcel.writeFloat(this.latitude);
        parcel.writeString(this.logo);
        parcel.writeString(this.name);
        parcel.writeInt(this.chnId);
        parcel.writeString(this.vipType);
        parcel.writeString(this.updateTip);
        parcel.writeInt(this.hot);
        parcel.writeByte(this.isVIP ? (byte) 1 : (byte) 0);
        parcel.writeString(this.albumId);
        parcel.writeString(this.albumName);
        parcel.writeByte(this.qipuId ? (byte) 1 : (byte) 0);
        parcel.writeString(this.focus);
        parcel.writeString(this.desc);
        parcel.writeString(this.operateTime);
        parcel.writeString(this.ivySubId);
        parcel.writeString(this.ivyTitle);
        parcel.writeString(this.coverUrl);
        parcel.writeString(this.thumbnailUrl);
        parcel.writeString(this.releaseTime);
        parcel.writeString(this.heat);
        parcel.writeString(this.formattedHeat);
        parcel.writeString(this.showImg);
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

    public String getOperateTime() {
        return this.operateTime;
    }

    public void setOperateTime(String operateTime) {
        this.operateTime = operateTime;
    }

    public String getStoreName() {
        return this.storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getDistance() {
        return this.distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public List<DiscountInfoBean> getDiscountInfos() {
        return this.discountInfos;
    }

    public void setDiscountInfos(List<DiscountInfoBean> discountInfos) {
        this.discountInfos = discountInfos;
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getButtonName() {
        return this.buttonName;
    }

    public void setButtonName(String buttonName) {
        this.buttonName = buttonName;
    }

    public float getLongitude() {
        return this.longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLatitude() {
        return this.latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public String getLogo() {
        return this.logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
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

    public String toString() {
        return "CpspContentDataBean{title='" + this.title + "', hotScore=" + this.hotScore + ", pic='" + this.pic + "', link='" + this.link + "', storeName='" + this.storeName + "', score=" + this.score + ", distance='" + this.distance + "', discountInfos=" + this.discountInfos + ", label='" + this.label + "', buttonName='" + this.buttonName + "', longitude=" + this.longitude + ", latitude=" + this.latitude + ", logo='" + this.logo + "', name='" + this.name + "', chnId=" + this.chnId + ", vipType='" + this.vipType + "', updateTip='" + this.updateTip + "', hot=" + this.hot + ", isVIP=" + this.isVIP + ", albumId='" + this.albumId + "', albumName='" + this.albumName + "', qipuId=" + this.qipuId + ", focus='" + this.focus + "', desc='" + this.desc + "', operateTime='" + this.operateTime + "', ivySubId='" + this.ivySubId + "', ivyTitle='" + this.ivyTitle + "', coverUrl='" + this.coverUrl + "', thumbnailUrl='" + this.thumbnailUrl + "', releaseTime='" + this.releaseTime + "', heat='" + this.heat + "', formattedHeat='" + this.formattedHeat + "', showImg='" + this.showImg + "'}";
    }
}