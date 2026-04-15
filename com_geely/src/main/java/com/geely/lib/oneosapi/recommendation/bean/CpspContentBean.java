package com.geely.lib.oneosapi.recommendation.bean;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

/* loaded from: classes.dex */
public class CpspContentBean implements Parcelable {
    public static final Parcelable.Creator<CpspContentBean> CREATOR = new Parcelable.Creator<CpspContentBean>() { // from class: com.geely.lib.oneosapi.recommendation.bean.CpspContentBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CpspContentBean createFromParcel(Parcel in) {
            return new CpspContentBean(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CpspContentBean[] newArray(int size) {
            return new CpspContentBean[size];
        }
    };
    private List<CpspContentDataBean> data;
    private String type;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public CpspContentBean() {
    }

    protected CpspContentBean(Parcel in) {
        this.type = in.readString();
        this.data = in.createTypedArrayList(CpspContentDataBean.CREATOR);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.type);
        dest.writeTypedList(this.data);
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<CpspContentDataBean> getData() {
        return this.data;
    }

    public void setData(List<CpspContentDataBean> data) {
        this.data = data;
    }

    public String toString() {
        return "CpspContentBean{type='" + this.type + "', data=" + this.data + '}';
    }
}