package com.geely.lib.oneosapi.navi.ipc;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class ILaneModelBean implements Parcelable {
    public static final Parcelable.Creator<ILaneModelBean> CREATOR = new Parcelable.Creator<ILaneModelBean>() { // from class: com.geely.lib.oneosapi.navi.ipc.ILaneModelBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ILaneModelBean createFromParcel(Parcel in) {
            return new ILaneModelBean(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ILaneModelBean[] newArray(int size) {
            return new ILaneModelBean[size];
        }
    };
    private Bundle extras;
    private int laneIconId;
    private int laneIndex;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public ILaneModelBean() {
    }

    protected ILaneModelBean(Parcel in) {
        this.laneIndex = in.readInt();
        this.laneIconId = in.readInt();
        this.extras = in.readBundle(Bundle.class.getClassLoader());
    }

    public void readFromParcel(Parcel in) {
        this.laneIndex = in.readInt();
        this.laneIconId = in.readInt();
        this.extras = in.readBundle(Bundle.class.getClassLoader());
    }

    public int getLaneIndex() {
        return this.laneIndex;
    }

    public void setLaneIndex(int laneIndex) {
        this.laneIndex = laneIndex;
    }

    public int getLaneIconId() {
        return this.laneIconId;
    }

    public void setLaneIconId(int laneIconId) {
        this.laneIconId = laneIconId;
    }

    public Bundle getExtras() {
        return this.extras;
    }

    public void setExtras(Bundle extras) {
        this.extras = extras;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.laneIndex);
        dest.writeInt(this.laneIconId);
        dest.writeBundle(this.extras);
    }

    public String toString() {
        return "ILaneModelBean{laneIndex=" + this.laneIndex + ", laneIconId=" + this.laneIconId + ", extras=" + this.extras + '}';
    }
}