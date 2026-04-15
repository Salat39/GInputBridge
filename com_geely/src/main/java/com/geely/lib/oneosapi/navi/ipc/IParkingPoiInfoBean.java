package com.geely.lib.oneosapi.navi.ipc;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class IParkingPoiInfoBean implements Parcelable {
    public static final Parcelable.Creator<IParkingPoiInfoBean> CREATOR = new Parcelable.Creator<IParkingPoiInfoBean>() { // from class: com.geely.lib.oneosapi.navi.ipc.IParkingPoiInfoBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public IParkingPoiInfoBean createFromParcel(Parcel in) {
            return new IParkingPoiInfoBean(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public IParkingPoiInfoBean[] newArray(int size) {
            return new IParkingPoiInfoBean[size];
        }
    };
    private String address;
    private int distance;
    private Bundle extras;
    private ILatLngBean latLng;
    private String name;
    private String parkingPrice;
    private int parkingType;
    private String phoneNumber;
    private IPoiTypeBean poiType;
    private int remainParkingSpaces;
    private long requestTimeout;
    private String uid;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public IParkingPoiInfoBean() {
    }

    protected IParkingPoiInfoBean(Parcel in) {
        this.name = in.readString();
        this.uid = in.readString();
        this.distance = in.readInt();
        this.latLng = (ILatLngBean) in.readParcelable(ILatLngBean.class.getClassLoader());
        this.address = in.readString();
        this.poiType = (IPoiTypeBean) in.readParcelable(IPoiTypeBean.class.getClassLoader());
        this.phoneNumber = in.readString();
        this.parkingType = in.readInt();
        this.parkingPrice = in.readString();
        this.remainParkingSpaces = in.readInt();
        this.requestTimeout = in.readLong();
        this.extras = in.readBundle(Bundle.class.getClassLoader());
    }

    public void readFromParcel(Parcel in) {
        this.name = in.readString();
        this.uid = in.readString();
        this.distance = in.readInt();
        this.latLng = (ILatLngBean) in.readParcelable(ILatLngBean.class.getClassLoader());
        this.address = in.readString();
        this.poiType = (IPoiTypeBean) in.readParcelable(IPoiTypeBean.class.getClassLoader());
        this.phoneNumber = in.readString();
        this.parkingType = in.readInt();
        this.parkingPrice = in.readString();
        this.remainParkingSpaces = in.readInt();
        this.requestTimeout = in.readLong();
        this.extras = in.readBundle(Bundle.class.getClassLoader());
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.uid);
        dest.writeInt(this.distance);
        dest.writeParcelable(this.latLng, flags);
        dest.writeString(this.address);
        dest.writeParcelable(this.poiType, flags);
        dest.writeString(this.phoneNumber);
        dest.writeInt(this.parkingType);
        dest.writeString(this.parkingPrice);
        dest.writeInt(this.remainParkingSpaces);
        dest.writeLong(this.requestTimeout);
        dest.writeBundle(this.extras);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUid() {
        return this.uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public int getDistance() {
        return this.distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public ILatLngBean getLatLng() {
        return this.latLng;
    }

    public void setLatLng(ILatLngBean latLng) {
        this.latLng = latLng;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public IPoiTypeBean getPoiType() {
        return this.poiType;
    }

    public void setPoiType(IPoiTypeBean poiType) {
        this.poiType = poiType;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getParkingType() {
        return this.parkingType;
    }

    public void setParkingType(int parkingType) {
        this.parkingType = parkingType;
    }

    public String getParkingPrice() {
        return this.parkingPrice;
    }

    public void setParkingPrice(String parkingPrice) {
        this.parkingPrice = parkingPrice;
    }

    public int getRemainParkingSpaces() {
        return this.remainParkingSpaces;
    }

    public void setRemainParkingSpaces(int remainParkingSpaces) {
        this.remainParkingSpaces = remainParkingSpaces;
    }

    public long getRequestTimeout() {
        return this.requestTimeout;
    }

    public void setRequestTimeout(long requestTimeout) {
        this.requestTimeout = requestTimeout;
    }

    public Bundle getExtras() {
        return this.extras;
    }

    public void setExtras(Bundle extras) {
        this.extras = extras;
    }

    public String toString() {
        return "IParkingPoiInfoBean{name='" + this.name + "', uid='" + this.uid + "', distance=" + this.distance + ", latLng=" + this.latLng + ", address='" + this.address + "', poiType=" + this.poiType + ", phoneNumber='" + this.phoneNumber + "', parkingType=" + this.parkingType + ", parkingPrice='" + this.parkingPrice + "', remainParkingSpaces=" + this.remainParkingSpaces + ", requestTimeout=" + this.requestTimeout + ", extras=" + this.extras + '}';
    }
}