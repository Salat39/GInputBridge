package com.geely.lib.oneosapi.navi.ipc;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class ISearchAroundRequestBean implements Parcelable {
    public static final Parcelable.Creator<ISearchAroundRequestBean> CREATOR = new Parcelable.Creator<ISearchAroundRequestBean>() { // from class: com.geely.lib.oneosapi.navi.ipc.ISearchAroundRequestBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ISearchAroundRequestBean createFromParcel(Parcel in) {
            return new ISearchAroundRequestBean(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ISearchAroundRequestBean[] newArray(int size) {
            return new ISearchAroundRequestBean[size];
        }
    };
    private ILatLngBean centerLatLng;
    private Bundle extras;
    private String keyword;
    private IPoiTypeBean poiType;
    private long requestTimeout;
    private int searchRadius;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public ISearchAroundRequestBean() {
    }

    protected ISearchAroundRequestBean(Parcel in) {
        this.keyword = in.readString();
        this.searchRadius = in.readInt();
        this.centerLatLng = (ILatLngBean) in.readParcelable(ILatLngBean.class.getClassLoader());
        this.poiType = (IPoiTypeBean) in.readParcelable(IPoiTypeBean.class.getClassLoader());
        this.requestTimeout = in.readLong();
        this.extras = in.readBundle(Bundle.class.getClassLoader());
    }

    public void readFromParcel(Parcel in) {
        this.keyword = in.readString();
        this.searchRadius = in.readInt();
        this.centerLatLng = (ILatLngBean) in.readParcelable(ILatLngBean.class.getClassLoader());
        this.poiType = (IPoiTypeBean) in.readParcelable(IPoiTypeBean.class.getClassLoader());
        this.requestTimeout = in.readLong();
        this.extras = in.readBundle(Bundle.class.getClassLoader());
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.keyword);
        dest.writeInt(this.searchRadius);
        dest.writeParcelable(this.centerLatLng, flags);
        dest.writeParcelable(this.poiType, flags);
        dest.writeLong(this.requestTimeout);
        dest.writeBundle(this.extras);
    }

    public String getKeyword() {
        return this.keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public int getSearchRadius() {
        return this.searchRadius;
    }

    public void setSearchRadius(int searchRadius) {
        this.searchRadius = searchRadius;
    }

    public ILatLngBean getCenterLatLng() {
        return this.centerLatLng;
    }

    public void setCenterLatLng(ILatLngBean centerLatLng) {
        this.centerLatLng = centerLatLng;
    }

    public IPoiTypeBean getPoiType() {
        return this.poiType;
    }

    public void setPoiType(IPoiTypeBean poiType) {
        this.poiType = poiType;
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
        return "ISearchAroundRequestBean{keyword='" + this.keyword + "', searchRadius=" + this.searchRadius + ", centerLatLng=" + this.centerLatLng + ", poiType=" + this.poiType + ", requestTimeout=" + this.requestTimeout + ", extras=" + this.extras + '}';
    }
}