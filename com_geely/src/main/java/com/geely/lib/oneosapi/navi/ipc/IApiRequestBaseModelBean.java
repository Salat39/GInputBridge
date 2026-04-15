package com.geely.lib.oneosapi.navi.ipc;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class IApiRequestBaseModelBean implements Parcelable {
    public static final Parcelable.Creator<IApiRequestBaseModelBean> CREATOR = new Parcelable.Creator<IApiRequestBaseModelBean>() { // from class: com.geely.lib.oneosapi.navi.ipc.IApiRequestBaseModelBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public IApiRequestBaseModelBean createFromParcel(Parcel in) {
            return new IApiRequestBaseModelBean(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public IApiRequestBaseModelBean[] newArray(int size) {
            return new IApiRequestBaseModelBean[size];
        }
    };
    private Bundle extras;
    private long requestTimeout;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public IApiRequestBaseModelBean() {
    }

    protected IApiRequestBaseModelBean(Parcel in) {
        this.requestTimeout = in.readLong();
        this.extras = in.readBundle(Bundle.class.getClassLoader());
    }

    public void readFromParcel(Parcel in) {
        this.requestTimeout = in.readLong();
        this.extras = in.readBundle(Bundle.class.getClassLoader());
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.requestTimeout);
        dest.writeBundle(this.extras);
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
        return "IAPIRequestBaseModelBean{requestTimeout=" + this.requestTimeout + ", extras=" + this.extras + '}';
    }
}