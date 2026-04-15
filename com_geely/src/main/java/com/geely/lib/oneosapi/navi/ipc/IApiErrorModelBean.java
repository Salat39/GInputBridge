package com.geely.lib.oneosapi.navi.ipc;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class IApiErrorModelBean implements Parcelable {
    public static final Parcelable.Creator<IApiErrorModelBean> CREATOR = new Parcelable.Creator<IApiErrorModelBean>() { // from class: com.geely.lib.oneosapi.navi.ipc.IApiErrorModelBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public IApiErrorModelBean createFromParcel(Parcel in) {
            return new IApiErrorModelBean(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public IApiErrorModelBean[] newArray(int size) {
            return new IApiErrorModelBean[size];
        }
    };
    private String errorMessage;
    private Bundle extras;
    private int resultCode;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public IApiErrorModelBean() {
    }

    protected IApiErrorModelBean(Parcel in) {
        this.errorMessage = in.readString();
        this.resultCode = in.readInt();
        this.extras = in.readBundle(Bundle.class.getClassLoader());
    }

    public void readFromParcel(Parcel in) {
        this.errorMessage = in.readString();
        this.resultCode = in.readInt();
        this.extras = in.readBundle(Bundle.class.getClassLoader());
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.errorMessage);
        dest.writeInt(this.resultCode);
        dest.writeBundle(this.extras);
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
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
        return "IAPIErrorModelBean{errorMessage='" + this.errorMessage + "', resultCode=" + this.resultCode + ", extras=" + this.extras + '}';
    }
}