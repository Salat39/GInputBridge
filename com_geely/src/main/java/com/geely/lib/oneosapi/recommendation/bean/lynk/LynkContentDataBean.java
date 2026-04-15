package com.geely.lib.oneosapi.recommendation.bean.lynk;

import android.os.Parcel;
import android.os.Parcelable;
import com.geely.lib.oneosapi.recommendation.bean.DiscountInfoBean;
import java.util.List;

/* loaded from: classes.dex */
public class LynkContentDataBean implements Parcelable {
    public static final Parcelable.Creator<LynkContentDataBean> CREATOR = new Parcelable.Creator<LynkContentDataBean>() { // from class: com.geely.lib.oneosapi.recommendation.bean.lynk.LynkContentDataBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public LynkContentDataBean createFromParcel(Parcel in) {
            return new LynkContentDataBean(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public LynkContentDataBean[] newArray(int size) {
            return new LynkContentDataBean[size];
        }
    };
    private List<DiscountInfoBean> discountInfos;
    private String distance;
    private float latitude;
    private String logo;
    private float longitude;
    private String operateTime;
    private int score;
    private String storeName;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public LynkContentDataBean() {
    }

    protected LynkContentDataBean(Parcel in) {
        this.storeName = in.readString();
        this.score = in.readInt();
        this.distance = in.readString();
        this.discountInfos = in.createTypedArrayList(DiscountInfoBean.CREATOR);
        this.longitude = in.readFloat();
        this.latitude = in.readFloat();
        this.logo = in.readString();
        this.operateTime = in.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.storeName);
        dest.writeInt(this.score);
        dest.writeString(this.distance);
        dest.writeTypedList(this.discountInfos);
        dest.writeFloat(this.longitude);
        dest.writeFloat(this.latitude);
        dest.writeString(this.logo);
        dest.writeString(this.operateTime);
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

    public String getOperateTime() {
        return this.operateTime;
    }

    public void setOperateTime(String operateTime) {
        this.operateTime = operateTime;
    }

    public String toString() {
        return "LynkContentDataBean{storeName='" + this.storeName + "', score=" + this.score + ", distance='" + this.distance + "', discountInfos=" + this.discountInfos + ", longitude=" + this.longitude + ", latitude=" + this.latitude + ", logo='" + this.logo + "', operateTime='" + this.operateTime + "'}";
    }
}