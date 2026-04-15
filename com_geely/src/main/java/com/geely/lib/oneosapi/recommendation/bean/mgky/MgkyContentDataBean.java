package com.geely.lib.oneosapi.recommendation.bean.mgky;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class MgkyContentDataBean implements Parcelable {
    public static final Parcelable.Creator<MgkyContentDataBean> CREATOR = new Parcelable.Creator<MgkyContentDataBean>() { // from class: com.geely.lib.oneosapi.recommendation.bean.mgky.MgkyContentDataBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MgkyContentDataBean createFromParcel(Parcel in) {
            return new MgkyContentDataBean(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MgkyContentDataBean[] newArray(int size) {
            return new MgkyContentDataBean[size];
        }
    };
    private String actionKey;
    private String actionValue;
    private String audioMd5;
    private String audioName;
    private String audioUrl;
    private String audiokey;
    private String buttonName;
    private String contentDesc;
    private String fileType;
    private String imageKey;
    private String imageMd5;
    private String imageName;
    private String imageUrl;
    private String num;
    private String title;
    private String videoKey;
    private String videoMd5;
    private String videoName;
    private String videoUrl;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public MgkyContentDataBean() {
    }

    protected MgkyContentDataBean(Parcel in) {
        this.title = in.readString();
        this.contentDesc = in.readString();
        this.num = in.readString();
        this.actionKey = in.readString();
        this.actionValue = in.readString();
        this.buttonName = in.readString();
        this.fileType = in.readString();
        this.imageKey = in.readString();
        this.videoKey = in.readString();
        this.audiokey = in.readString();
        this.imageUrl = in.readString();
        this.imageMd5 = in.readString();
        this.videoUrl = in.readString();
        this.videoMd5 = in.readString();
        this.audioUrl = in.readString();
        this.audioMd5 = in.readString();
        this.imageName = in.readString();
        this.videoName = in.readString();
        this.audioName = in.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.contentDesc);
        dest.writeString(this.num);
        dest.writeString(this.actionKey);
        dest.writeString(this.actionValue);
        dest.writeString(this.buttonName);
        dest.writeString(this.fileType);
        dest.writeString(this.imageKey);
        dest.writeString(this.videoKey);
        dest.writeString(this.audiokey);
        dest.writeString(this.imageUrl);
        dest.writeString(this.imageMd5);
        dest.writeString(this.videoUrl);
        dest.writeString(this.videoMd5);
        dest.writeString(this.audioUrl);
        dest.writeString(this.audioMd5);
        dest.writeString(this.imageName);
        dest.writeString(this.videoName);
        dest.writeString(this.audioName);
    }

    public String toString() {
        return "MgkyContentDataBean{title='" + this.title + "', contentDesc='" + this.contentDesc + "', num='" + this.num + "', actionKey='" + this.actionKey + "', actionValue=" + this.actionValue + ", buttonName=" + this.buttonName + ", fileType='" + this.fileType + "', imageKey='" + this.imageKey + "', videoKey=" + this.videoKey + ", audiokey='" + this.audiokey + "', imageUrl='" + this.imageUrl + "', imageMd5='" + this.imageMd5 + "', videoUrl='" + this.videoUrl + "', videoMd5='" + this.videoMd5 + "', audioUrl='" + this.audioUrl + "', audioMd5='" + this.audioMd5 + "', imageName='" + this.imageName + "', videoName='" + this.videoName + "', audioName='" + this.audioName + "'}";
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContentDesc() {
        return this.contentDesc;
    }

    public void setContentDesc(String contentDesc) {
        this.contentDesc = contentDesc;
    }

    public String getNum() {
        return this.num;
    }

    public void setNum(String num) {
        this.num = num;
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

    public String getFileType() {
        return this.fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getImageKey() {
        return this.imageKey;
    }

    public void setImageKey(String imageKey) {
        this.imageKey = imageKey;
    }

    public String getVideoKey() {
        return this.videoKey;
    }

    public void setVideoKey(String videoKey) {
        this.videoKey = videoKey;
    }

    public String getAudiokey() {
        return this.audiokey;
    }

    public void setAudiokey(String audiokey) {
        this.audiokey = audiokey;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageMd5() {
        return this.imageMd5;
    }

    public void setImageMd5(String imageMd5) {
        this.imageMd5 = imageMd5;
    }

    public String getVideoUrl() {
        return this.videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getVideoMd5() {
        return this.videoMd5;
    }

    public void setVideoMd5(String videoMd5) {
        this.videoMd5 = videoMd5;
    }

    public String getAudioUrl() {
        return this.audioUrl;
    }

    public void setAudioUrl(String audioUrl) {
        this.audioUrl = audioUrl;
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

    public String getVideoName() {
        return this.videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public String getAudioName() {
        return this.audioName;
    }

    public void setAudioName(String audioName) {
        this.audioName = audioName;
    }
}