package com.geely.lib.oneosapi.navi.ipc;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class ISearchAlongWayRequestBean implements Parcelable {
    public static final Parcelable.Creator<ISearchAlongWayRequestBean> CREATOR = new Parcelable.Creator<ISearchAlongWayRequestBean>() { // from class: com.geely.lib.oneosapi.navi.ipc.ISearchAlongWayRequestBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ISearchAlongWayRequestBean createFromParcel(Parcel in) {
            return new ISearchAlongWayRequestBean(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ISearchAlongWayRequestBean[] newArray(int size) {
            return new ISearchAlongWayRequestBean[size];
        }
    };
    private Bundle extras;
    private String keyword;
    private IPoiTypeBean poiType;
    private long requestTimeout;
    private int searchType;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public ISearchAlongWayRequestBean() {
    }

    protected ISearchAlongWayRequestBean(Parcel in) {
        this.searchType = in.readInt();
        this.keyword = in.readString();
        this.poiType = (IPoiTypeBean) in.readParcelable(IPoiTypeBean.class.getClassLoader());
        this.requestTimeout = in.readLong();
        this.extras = in.readBundle(Bundle.class.getClassLoader());
    }

    public void readFromParcel(Parcel in) {
        this.searchType = in.readInt();
        this.keyword = in.readString();
        this.poiType = (IPoiTypeBean) in.readParcelable(IPoiTypeBean.class.getClassLoader());
        this.requestTimeout = in.readLong();
        this.extras = in.readBundle(Bundle.class.getClassLoader());
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.searchType);
        dest.writeString(this.keyword);
        dest.writeParcelable(this.poiType, flags);
        dest.writeLong(this.requestTimeout);
        dest.writeBundle(this.extras);
    }

    public int getSearchType() {
        return this.searchType;
    }

    public void setSearchType(int searchType) {
        this.searchType = searchType;
    }

    public String getKeyword() {
        return this.keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
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
        return "ISearchAlongWayRequestBean{searchType=" + this.searchType + ", keyword='" + this.keyword + "', poiType=" + this.poiType + ", requestTimeout=" + this.requestTimeout + ", extras=" + this.extras + '}';
    }
}