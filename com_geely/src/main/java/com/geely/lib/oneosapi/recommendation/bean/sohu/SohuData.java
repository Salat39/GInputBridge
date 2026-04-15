package com.geely.lib.oneosapi.recommendation.bean.sohu;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class SohuData implements Parcelable {
    public static final Parcelable.Creator<SohuData> CREATOR = new Parcelable.Creator<SohuData>() { // from class: com.geely.lib.oneosapi.recommendation.bean.sohu.SohuData.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SohuData createFromParcel(Parcel in) {
            return new SohuData(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SohuData[] newArray(int size) {
            return new SohuData[size];
        }
    };
    private String buttonName;
    private String cardType;
    private SohuContentBean cpspContent;
    private String deliveryPosition;
    private Long expiration;
    private String messageId;
    private Long push;
    private String resourcesType;
    private String type;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public SohuData() {
    }

    protected SohuData(Parcel in) {
        this.type = in.readString();
        this.messageId = in.readString();
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
        this.buttonName = in.readString();
        this.cpspContent = (SohuContentBean) in.readParcelable(SohuContentBean.class.getClassLoader());
        this.deliveryPosition = in.readString();
        this.cardType = in.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.type);
        dest.writeString(this.messageId);
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
        dest.writeString(this.buttonName);
        dest.writeParcelable(this.cpspContent, flags);
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

    public String getButtonName() {
        return this.buttonName;
    }

    public void setButtonName(String buttonName) {
        this.buttonName = buttonName;
    }

    public SohuContentBean getCpspContent() {
        return this.cpspContent;
    }

    public void setCpspContent(SohuContentBean cpspContent) {
        this.cpspContent = cpspContent;
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
        return "SohuData{type='" + this.type + "', messageId='" + this.messageId + "', push=" + this.push + ", expiration=" + this.expiration + ", resourcesType='" + this.resourcesType + "', buttonName='" + this.buttonName + "', cpspContent=" + this.cpspContent + ", deliveryPosition='" + this.deliveryPosition + "', cardType='" + this.cardType + "'}";
    }
}