package com.geely.lib.oneosapi.recommendation.bean;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

/* loaded from: classes.dex */
public class ResponseBean implements Parcelable {
    public static final Parcelable.Creator<ResponseBean> CREATOR = new Parcelable.Creator<ResponseBean>() { // from class: com.geely.lib.oneosapi.recommendation.bean.ResponseBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ResponseBean createFromParcel(Parcel in) {
            return new ResponseBean(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ResponseBean[] newArray(int size) {
            return new ResponseBean[size];
        }
    };
    private int code;
    private List<CpspData> data;
    private String message;
    private String type;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public ResponseBean() {
    }

    protected ResponseBean(Parcel in) {
        this.code = in.readInt();
        this.data = in.createTypedArrayList(CpspData.CREATOR);
        this.message = in.readString();
        this.type = in.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.code);
        dest.writeTypedList(this.data);
        dest.writeString(this.message);
        dest.writeString(this.type);
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<CpspData> getData() {
        return this.data;
    }

    public void setData(List<CpspData> data) {
        this.data = data;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String toString() {
        return "ResponseBean{code='" + this.code + "'message='" + this.message + "'type='" + this.type + "', data=" + this.data + '}';
    }
}