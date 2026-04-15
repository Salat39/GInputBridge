package com.geely.lib.oneosapi.recommendation.bean.lynk;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

/* loaded from: classes.dex */
public class LynkContentBean implements Parcelable {
    public static final Parcelable.Creator<LynkContentBean> CREATOR = new Parcelable.Creator<LynkContentBean>() { // from class: com.geely.lib.oneosapi.recommendation.bean.lynk.LynkContentBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public LynkContentBean createFromParcel(Parcel in) {
            return new LynkContentBean(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public LynkContentBean[] newArray(int size) {
            return new LynkContentBean[size];
        }
    };
    private List<LynkContentDataBean> data;
    private String type;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public LynkContentBean() {
    }

    protected LynkContentBean(Parcel in) {
        this.type = in.readString();
        this.data = in.createTypedArrayList(LynkContentDataBean.CREATOR);
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

    public List<LynkContentDataBean> getData() {
        return this.data;
    }

    public void setData(List<LynkContentDataBean> data) {
        this.data = data;
    }

    public String toString() {
        return "CpspContentBean{type='" + this.type + "', data=" + this.data + '}';
    }
}