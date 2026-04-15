package com.geely.lib.oneosapi.recommendation.bean.banner;

import android.os.Parcel;
import android.os.Parcelable;
import com.geely.lib.oneosapi.recommendation.bean.JumpActionBean;

/* loaded from: classes.dex */
public class BannerData implements Parcelable {
    public static final Parcelable.Creator<BannerData> CREATOR = new Parcelable.Creator<BannerData>() { // from class: com.geely.lib.oneosapi.recommendation.bean.banner.BannerData.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BannerData createFromParcel(Parcel in) {
            return new BannerData(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BannerData[] newArray(int size) {
            return new BannerData[size];
        }
    };
    private String broadcastContent;
    private String cardType;
    private String deliveryPosition;
    private String describe;
    private Long expiration;
    private JumpActionBean jumpAction;
    private String messageId;
    private Long push;
    private String resourcesType;
    private String title;
    private String type;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public BannerData() {
    }

    protected BannerData(Parcel in) {
        this.type = in.readString();
        this.messageId = in.readString();
        this.title = in.readString();
        this.describe = in.readString();
        if (in.readByte() == 0) {
            this.push = null;
        } else {
            this.push = Long.valueOf(in.readLong());
        }
        if (in.readByte() == 0) {
            this.expiration = null;
        } else {
            this.expiration = Long.valueOf(in.readLong());
        }
        this.broadcastContent = in.readString();
        this.resourcesType = in.readString();
        this.jumpAction = (JumpActionBean) in.readParcelable(JumpActionBean.class.getClassLoader());
        this.deliveryPosition = in.readString();
        this.cardType = in.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.type);
        dest.writeString(this.messageId);
        dest.writeString(this.title);
        dest.writeString(this.describe);
        if (this.push == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(this.push.longValue());
        }
        if (this.expiration == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(this.expiration.longValue());
        }
        dest.writeString(this.broadcastContent);
        dest.writeString(this.resourcesType);
        dest.writeParcelable(this.jumpAction, flags);
        dest.writeString(this.deliveryPosition);
        dest.writeString(this.cardType);
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessageId() {
        return this.messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescribe() {
        return this.describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public Long getPush() {
        return this.push;
    }

    public void setPush(Long push) {
        this.push = push;
    }

    public Long getExpiration() {
        return this.expiration;
    }

    public void setExpiration(Long expiration) {
        this.expiration = expiration;
    }

    public String getBroadcastContent() {
        return this.broadcastContent;
    }

    public void setBroadcastContent(String broadcastContent) {
        this.broadcastContent = broadcastContent;
    }

    public String getResourcesType() {
        return this.resourcesType;
    }

    public void setResourcesType(String resourcesType) {
        this.resourcesType = resourcesType;
    }

    public JumpActionBean getJumpAction() {
        return this.jumpAction;
    }

    public void setJumpAction(JumpActionBean jumpAction) {
        this.jumpAction = jumpAction;
    }

    public String getDeliveryPosition() {
        return this.deliveryPosition;
    }

    public void setDeliveryPosition(String deliveryPosition) {
        this.deliveryPosition = deliveryPosition;
    }

    public String getCardType() {
        return this.cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String toString() {
        return "BannerData{type='" + this.type + "', messageId='" + this.messageId + "', title='" + this.title + "', describe='" + this.describe + "', push=" + this.push + ", expiration=" + this.expiration + ", broadcastContent='" + this.broadcastContent + "', resourcesType='" + this.resourcesType + "', jumpAction=" + this.jumpAction + ", deliveryPosition='" + this.deliveryPosition + "', cardType='" + this.cardType + "'}";
    }
}