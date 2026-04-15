package com.geely.lib.oneosapi.recommendation.bean.mgky;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class MgkyData implements Parcelable {
    public static final Parcelable.Creator<MgkyData> CREATOR = new Parcelable.Creator<MgkyData>() { // from class: com.geely.lib.oneosapi.recommendation.bean.mgky.MgkyData.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MgkyData createFromParcel(Parcel in) {
            return new MgkyData(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MgkyData[] newArray(int size) {
            return new MgkyData[size];
        }
    };
    private String cardType;
    private String contentDesc;
    private String deliveryPosition;
    private Long expiration;
    private MgkyContentBean jumpAction;
    private String messageId;
    private Long push;
    private String resourcesType;
    private String title;
    private String type;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public MgkyData() {
    }

    protected MgkyData(Parcel in) {
        this.type = in.readString();
        this.messageId = in.readString();
        this.title = in.readString();
        this.contentDesc = in.readString();
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
        this.resourcesType = in.readString();
        this.jumpAction = (MgkyContentBean) in.readParcelable(MgkyContentBean.class.getClassLoader());
        this.deliveryPosition = in.readString();
        this.cardType = in.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.type);
        dest.writeString(this.messageId);
        dest.writeString(this.title);
        dest.writeString(this.contentDesc);
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
        dest.writeString(this.resourcesType);
        dest.writeParcelable(this.jumpAction, flags);
        dest.writeString(this.deliveryPosition);
        dest.writeString(this.cardType);
    }

    public String toString() {
        return "MgkyData{type='" + this.type + "', messageId='" + this.messageId + "', title='" + this.title + "', contentDesc='" + this.messageId + "', push=" + this.push + ", expiration=" + this.expiration + ", resourcesType='" + this.resourcesType + "', jumpAction=" + this.jumpAction + ", deliveryPosition='" + this.deliveryPosition + "', cardType='" + this.cardType + "'}";
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

    public String getResourcesType() {
        return this.resourcesType;
    }

    public void setResourcesType(String resourcesType) {
        this.resourcesType = resourcesType;
    }

    public MgkyContentBean getJumpAction() {
        return this.jumpAction;
    }

    public void setJumpAction(MgkyContentBean jumpAction) {
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
}