package com.geely.lib.oneosapi.navi.ipc;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

/* loaded from: classes.dex */
public class IPoiSearchResultBean implements Parcelable {
    public static final Parcelable.Creator<IPoiSearchResultBean> CREATOR = new Parcelable.Creator<IPoiSearchResultBean>() { // from class: com.geely.lib.oneosapi.navi.ipc.IPoiSearchResultBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public IPoiSearchResultBean createFromParcel(Parcel in) {
            return new IPoiSearchResultBean(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public IPoiSearchResultBean[] newArray(int size) {
            return new IPoiSearchResultBean[size];
        }
    };
    private Bundle extras;
    private List<ISearchPoiBean> poiList;
    private int resultCode;
    private List<ISuggestCityBean> suggestCitys;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public IPoiSearchResultBean() {
    }

    protected IPoiSearchResultBean(Parcel in) {
        this.suggestCitys = in.createTypedArrayList(ISuggestCityBean.CREATOR);
        this.poiList = in.createTypedArrayList(ISearchPoiBean.CREATOR);
        this.resultCode = in.readInt();
        this.extras = in.readBundle(Bundle.class.getClassLoader());
    }

    public void readFromParcel(Parcel in) {
        this.suggestCitys = in.createTypedArrayList(ISuggestCityBean.CREATOR);
        this.poiList = in.createTypedArrayList(ISearchPoiBean.CREATOR);
        this.resultCode = in.readInt();
        this.extras = in.readBundle(Bundle.class.getClassLoader());
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.suggestCitys);
        dest.writeTypedList(this.poiList);
        dest.writeInt(this.resultCode);
        dest.writeBundle(this.extras);
    }

    public List<ISuggestCityBean> getSuggestCitys() {
        return this.suggestCitys;
    }

    public void setSuggestCitys(List<ISuggestCityBean> suggestCitys) {
        this.suggestCitys = suggestCitys;
    }

    public List<ISearchPoiBean> getPoiList() {
        return this.poiList;
    }

    public void setPoiList(List<ISearchPoiBean> poiList) {
        this.poiList = poiList;
    }

    public int getResultCode() {
        return this.resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public Bundle getExtras() {
        return this.extras;
    }

    public void setExtras(Bundle extras) {
        this.extras = extras;
    }

    public String toString() {
        return "IPoiSearchResultBean{suggestCitys=" + this.suggestCitys + ", poiList=" + this.poiList + ", resultCode=" + this.resultCode + ", extras=" + this.extras + '}';
    }
}