package com.geely.lib.oneosapi.navi.ipc;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

/* loaded from: classes.dex */
public class IFrequentPoisBean implements Parcelable {
    public static final Parcelable.Creator<IFrequentPoisBean> CREATOR = new Parcelable.Creator<IFrequentPoisBean>() { // from class: com.geely.lib.oneosapi.navi.ipc.IFrequentPoisBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public IFrequentPoisBean createFromParcel(Parcel in) {
            return new IFrequentPoisBean(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public IFrequentPoisBean[] newArray(int size) {
            return new IFrequentPoisBean[size];
        }
    };
    private Bundle extras;
    private List<IPoiInfoBean> frequentPois;
    private int resultCode;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public IFrequentPoisBean() {
    }

    protected IFrequentPoisBean(Parcel in) {
        this.frequentPois = in.createTypedArrayList(IPoiInfoBean.CREATOR);
        this.resultCode = in.readInt();
        this.extras = in.readBundle(Bundle.class.getClassLoader());
    }

    public void readFromParcel(Parcel in) {
        this.frequentPois = in.createTypedArrayList(IPoiInfoBean.CREATOR);
        this.resultCode = in.readInt();
        this.extras = in.readBundle(Bundle.class.getClassLoader());
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.frequentPois);
        dest.writeInt(this.resultCode);
        dest.writeBundle(this.extras);
    }

    public List<IPoiInfoBean> getFrequentPois() {
        return this.frequentPois;
    }

    public void setFrequentPois(List<IPoiInfoBean> frequentPois) {
        this.frequentPois = frequentPois;
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
        return "IFrequentPoisBean{frequentPois=" + this.frequentPois + ", resultCode=" + this.resultCode + ", extras=" + this.extras + '}';
    }
}