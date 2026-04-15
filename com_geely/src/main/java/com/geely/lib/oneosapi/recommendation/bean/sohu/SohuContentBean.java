package com.geely.lib.oneosapi.recommendation.bean.sohu;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

/* loaded from: classes.dex */
public class SohuContentBean implements Parcelable {
    public static final Parcelable.Creator<SohuContentBean> CREATOR = new Parcelable.Creator<SohuContentBean>() { // from class: com.geely.lib.oneosapi.recommendation.bean.sohu.SohuContentBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SohuContentBean createFromParcel(Parcel in) {
            return new SohuContentBean(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SohuContentBean[] newArray(int size) {
            return new SohuContentBean[size];
        }
    };
    private List<SohuContentDataBean> data;
    private String type;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public SohuContentBean() {
    }

    protected SohuContentBean(Parcel in) {
        this.type = in.readString();
        this.data = in.createTypedArrayList(SohuContentDataBean.CREATOR);
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

    public List<SohuContentDataBean> getData() {
        return this.data;
    }

    public void setData(List<SohuContentDataBean> data) {
        this.data = data;
    }

    public String toString() {
        return "CpspContentBean{type='" + this.type + "', data=" + this.data + '}';
    }
}