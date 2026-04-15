package com.geely.lib.oneosapi.navi.ipc;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class IViaRoadRequestBean implements Parcelable {
    public static final Parcelable.Creator<IViaRoadRequestBean> CREATOR = new Parcelable.Creator<IViaRoadRequestBean>() { // from class: com.geely.lib.oneosapi.navi.ipc.IViaRoadRequestBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public IViaRoadRequestBean createFromParcel(Parcel in) {
            return new IViaRoadRequestBean(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public IViaRoadRequestBean[] newArray(int size) {
            return new IViaRoadRequestBean[size];
        }
    };
    private String roadName;
    private int type;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public IViaRoadRequestBean() {
    }

    public void readFromParcel(Parcel in) {
        this.type = in.readInt();
        this.roadName = in.readString();
    }

    protected IViaRoadRequestBean(Parcel in) {
        this.type = in.readInt();
        this.roadName = in.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.type);
        dest.writeString(this.roadName);
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setRoadName(String roadName) {
        this.roadName = roadName;
    }

    public int getType() {
        return this.type;
    }

    public String getRoadName() {
        return this.roadName;
    }
}