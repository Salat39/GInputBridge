package com.geely.lib.oneosapi.navi.ipc;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

/* loaded from: classes.dex */
public class ILanesInfoBean implements Parcelable {
    public static final Parcelable.Creator<ILanesInfoBean> CREATOR = new Parcelable.Creator<ILanesInfoBean>() { // from class: com.geely.lib.oneosapi.navi.ipc.ILanesInfoBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ILanesInfoBean createFromParcel(Parcel in) {
            return new ILanesInfoBean(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ILanesInfoBean[] newArray(int size) {
            return new ILanesInfoBean[size];
        }
    };
    private Bundle extras;
    private boolean isLanesEnable;
    private List<ILaneModelBean> lanes;
    private int resultCode;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public ILanesInfoBean() {
    }

    protected ILanesInfoBean(Parcel in) {
        this.isLanesEnable = in.readByte() != 0;
        this.lanes = in.createTypedArrayList(ILaneModelBean.CREATOR);
        this.resultCode = in.readInt();
        this.extras = in.readBundle(Bundle.class.getClassLoader());
    }

    public void readFromParcel(Parcel in) {
        this.isLanesEnable = in.readByte() != 0;
        this.lanes = in.createTypedArrayList(ILaneModelBean.CREATOR);
        this.resultCode = in.readInt();
        this.extras = in.readBundle(Bundle.class.getClassLoader());
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte(this.isLanesEnable ? (byte) 1 : (byte) 0);
        parcel.writeTypedList(this.lanes);
        parcel.writeInt(this.resultCode);
        parcel.writeBundle(this.extras);
    }

    public boolean isLanesEnable() {
        return this.isLanesEnable;
    }

    public void setLanesEnable(boolean lanesEnable) {
        this.isLanesEnable = lanesEnable;
    }

    public List<ILaneModelBean> getLanes() {
        return this.lanes;
    }

    public void setLanes(List<ILaneModelBean> lanes) {
        this.lanes = lanes;
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
        return "ILanesInfoBean{isLanesEnable=" + this.isLanesEnable + ", lanes=" + this.lanes + ", resultCode=" + this.resultCode + ", extras=" + this.extras + '}';
    }
}