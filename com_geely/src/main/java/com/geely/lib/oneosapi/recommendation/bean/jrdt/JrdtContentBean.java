package com.geely.lib.oneosapi.recommendation.bean.jrdt;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

/* loaded from: classes.dex */
public class JrdtContentBean implements Parcelable {
    public static final Parcelable.Creator<JrdtContentBean> CREATOR = new Parcelable.Creator<JrdtContentBean>() { // from class: com.geely.lib.oneosapi.recommendation.bean.jrdt.JrdtContentBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public JrdtContentBean createFromParcel(Parcel in) {
            return new JrdtContentBean(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public JrdtContentBean[] newArray(int size) {
            return new JrdtContentBean[size];
        }
    };
    private List<JrdtContentDataBean> data;
    private String type;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public JrdtContentBean() {
    }

    protected JrdtContentBean(Parcel in) {
        this.type = in.readString();
        this.data = in.createTypedArrayList(JrdtContentDataBean.CREATOR);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.type);
        dest.writeTypedList(this.data);
    }

    public String toString() {
        return "JrdtContentBean{type='" + this.type + "', data=" + this.data + '}';
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<JrdtContentDataBean> getData() {
        return this.data;
    }

    public void setData(List<JrdtContentDataBean> data) {
        this.data = data;
    }
}