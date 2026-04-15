package com.geely.lib.oneosapi.navi.ipc;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class IModifyViaPoiRequestBean implements Parcelable {
    public static final Parcelable.Creator<IModifyViaPoiRequestBean> CREATOR = new Parcelable.Creator<IModifyViaPoiRequestBean>() { // from class: com.geely.lib.oneosapi.navi.ipc.IModifyViaPoiRequestBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public IModifyViaPoiRequestBean createFromParcel(Parcel in) {
            return new IModifyViaPoiRequestBean(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public IModifyViaPoiRequestBean[] newArray(int size) {
            return new IModifyViaPoiRequestBean[size];
        }
    };
    private int action;
    private Bundle extras;
    private long requestTimeout;
    private IPoiInfoBean viaPoi;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public IModifyViaPoiRequestBean() {
    }

    protected IModifyViaPoiRequestBean(Parcel in) {
        this.action = in.readInt();
        this.viaPoi = (IPoiInfoBean) in.readParcelable(IPoiInfoBean.class.getClassLoader());
        this.requestTimeout = in.readLong();
        this.extras = in.readBundle(Bundle.class.getClassLoader());
    }

    public void readFromParcel(Parcel in) {
        this.action = in.readInt();
        this.viaPoi = (IPoiInfoBean) in.readParcelable(IPoiInfoBean.class.getClassLoader());
        this.requestTimeout = in.readLong();
        this.extras = in.readBundle(Bundle.class.getClassLoader());
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.action);
        dest.writeParcelable(this.viaPoi, flags);
        dest.writeLong(this.requestTimeout);
        dest.writeBundle(this.extras);
    }

    public int getAction() {
        return this.action;
    }

    public void setAction(int action) {
        this.action = action;
    }

    public IPoiInfoBean getViaPoi() {
        return this.viaPoi;
    }

    public void setViaPoi(IPoiInfoBean viaPoi) {
        this.viaPoi = viaPoi;
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
        return "IModifyViaPoiRequestBean{action=" + this.action + ", viaPoi=" + this.viaPoi + ", requestTimeout=" + this.requestTimeout + ", extras=" + this.extras + '}';
    }
}