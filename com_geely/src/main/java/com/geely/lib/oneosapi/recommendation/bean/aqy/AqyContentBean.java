package com.geely.lib.oneosapi.recommendation.bean.aqy;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

/* loaded from: classes.dex */
public class AqyContentBean implements Parcelable {
    public static final Parcelable.Creator<AqyContentBean> CREATOR = new Parcelable.Creator<AqyContentBean>() { // from class: com.geely.lib.oneosapi.recommendation.bean.aqy.AqyContentBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AqyContentBean createFromParcel(Parcel in) {
            return new AqyContentBean(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AqyContentBean[] newArray(int size) {
            return new AqyContentBean[size];
        }
    };
    private List<AqyContentDataBean> data;
    private String type;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public AqyContentBean() {
    }

    protected AqyContentBean(Parcel in) {
        this.type = in.readString();
        this.data = in.createTypedArrayList(AqyContentDataBean.CREATOR);
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

    public List<AqyContentDataBean> getData() {
        return this.data;
    }

    public void setData(List<AqyContentDataBean> data) {
        this.data = data;
    }

    public String toString() {
        return "CpspContentBean{type='" + this.type + "', data=" + this.data + '}';
    }
}