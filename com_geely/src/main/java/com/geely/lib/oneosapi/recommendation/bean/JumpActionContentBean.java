package com.geely.lib.oneosapi.recommendation.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class JumpActionContentBean implements Parcelable {
    public static final Parcelable.Creator<JumpActionContentBean> CREATOR = new Parcelable.Creator<JumpActionContentBean>() { // from class: com.geely.lib.oneosapi.recommendation.bean.JumpActionContentBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public JumpActionContentBean createFromParcel(Parcel in) {
            return new JumpActionContentBean(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public JumpActionContentBean[] newArray(int size) {
            return new JumpActionContentBean[size];
        }
    };
    private String actionKey;
    private String actionValue;
    private String audioMd5;
    private String audioName;
    private String audioUrl;
    private String audiokey;
    private String buttonName;
    private String cardForm;
    private String fileType;
    private String imageMd5;
    private String imageName;
    private String imageNightKey;
    private String imageNightMd5;
    private String imageNightName;
    private String imageNightUrl;
    private String imageUrl;
    private String imagekey;
    private Integer num;
    private String videoMd5;
    private String videoName;
    private String videoUrl;
    private String videokey;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public JumpActionContentBean() {
    }

    protected JumpActionContentBean(Parcel in) {
        this.actionKey = in.readString();
        this.actionValue = in.readString();
        this.buttonName = in.readString();
        this.audioName = in.readString();
        this.audioUrl = in.readString();
        this.audiokey = in.readString();
        this.audioMd5 = in.readString();
        this.imageName = in.readString();
        this.imageUrl = in.readString();
        this.imagekey = in.readString();
        this.imageMd5 = in.readString();
        this.imageNightUrl = in.readString();
        this.imageNightKey = in.readString();
        this.imageNightMd5 = in.readString();
        this.imageNightName = in.readString();
        this.videoName = in.readString();
        this.videoUrl = in.readString();
        this.videokey = in.readString();
        this.videoMd5 = in.readString();
        if (in.readByte() == 0) {
            this.num = null;
        } else {
            this.num = Integer.valueOf(in.readInt());
        }
        this.fileType = in.readString();
        this.cardForm = in.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.actionKey);
        dest.writeString(this.actionValue);
        dest.writeString(this.buttonName);
        dest.writeString(this.audioName);
        dest.writeString(this.audioUrl);
        dest.writeString(this.audiokey);
        dest.writeString(this.audioMd5);
        dest.writeString(this.imageName);
        dest.writeString(this.imageUrl);
        dest.writeString(this.imagekey);
        dest.writeString(this.imageMd5);
        dest.writeString(this.imageNightUrl);
        dest.writeString(this.imageNightKey);
        dest.writeString(this.imageNightMd5);
        dest.writeString(this.imageNightName);
        dest.writeString(this.videoName);
        dest.writeString(this.videoUrl);
        dest.writeString(this.videokey);
        dest.writeString(this.videoMd5);
        if (this.num == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(this.num.intValue());
        }
        dest.writeString(this.fileType);
        dest.writeString(this.cardForm);
    }

    public String getActionKey() {
        return this.actionKey;
    }

    public void setActionKey(String actionKey) {
        this.actionKey = actionKey;
    }

    public String getActionValue() {
        return this.actionValue;
    }

    public void setActionValue(String actionValue) {
        this.actionValue = actionValue;
    }

    public String getButtonName() {
        return this.buttonName;
    }

    public void setButtonName(String buttonName) {
        this.buttonName = buttonName;
    }

    public String getAudioName() {
        return this.audioName;
    }

    public void setAudioName(String audioName) {
        this.audioName = audioName;
    }

    public String getAudioUrl() {
        return this.audioUrl;
    }

    public void setAudioUrl(String audioUrl) {
        this.audioUrl = audioUrl;
    }

    public String getAudiokey() {
        return this.audiokey;
    }

    public void setAudiokey(String audiokey) {
        this.audiokey = audiokey;
    }

    public String getAudioMd5() {
        return this.audioMd5;
    }

    public void setAudioMd5(String audioMd5) {
        this.audioMd5 = audioMd5;
    }

    public String getImageName() {
        return this.imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImagekey() {
        return this.imagekey;
    }

    public void setImagekey(String imagekey) {
        this.imagekey = imagekey;
    }

    public String getImageMd5() {
        return this.imageMd5;
    }

    public void setImageMd5(String imageMd5) {
        this.imageMd5 = imageMd5;
    }

    public String getImageNightUrl() {
        return this.imageNightUrl;
    }

    public void setImageNightUrl(String imageNightUrl) {
        this.imageNightUrl = imageNightUrl;
    }

    public String getImageNightKey() {
        return this.imageNightKey;
    }

    public void setImageNightKey(String imageNightKey) {
        this.imageNightKey = imageNightKey;
    }

    public String getImageNightMd5() {
        return this.imageNightMd5;
    }

    public void setImageNightMd5(String imageNightMd5) {
        this.imageNightMd5 = imageNightMd5;
    }

    public String getImageNightName() {
        return this.imageNightName;
    }

    public void setImageNightName(String imageNightName) {
        this.imageNightName = imageNightName;
    }

    public String getVideoName() {
        return this.videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public String getVideoUrl() {
        return this.videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getVideokey() {
        return this.videokey;
    }

    public void setVideokey(String videokey) {
        this.videokey = videokey;
    }

    public String getVideoMd5() {
        return this.videoMd5;
    }

    public void setVideoMd5(String videoMd5) {
        this.videoMd5 = videoMd5;
    }

    public Integer getNum() {
        return this.num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getFileType() {
        return this.fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getCardForm() {
        return this.cardForm;
    }

    public void setCardForm(String cardForm) {
        this.cardForm = cardForm;
    }

    public String toString() {
        return "JumpActionContentBean{actionKey='" + this.actionKey + "', actionValue='" + this.actionValue + "', buttonName='" + this.buttonName + "', audioName='" + this.audioName + "', audioUrl='" + this.audioUrl + "', audiokey='" + this.audiokey + "', audioMd5='" + this.audioMd5 + "', imageName='" + this.imageName + "', imageUrl='" + this.imageUrl + "', imagekey='" + this.imagekey + "', imageMd5='" + this.imageMd5 + "', imageNightUrl='" + this.imageNightUrl + "', imageNightKey='" + this.imageNightKey + "', imageNightMd5='" + this.imageNightMd5 + "', imageNightName='" + this.imageNightName + "', videoName='" + this.videoName + "', videoUrl='" + this.videoUrl + "', videokey='" + this.videokey + "', videoMd5='" + this.videoMd5 + "', num=" + this.num + ", fileType='" + this.fileType + "', cardForm='" + this.cardForm + "'}";
    }
}