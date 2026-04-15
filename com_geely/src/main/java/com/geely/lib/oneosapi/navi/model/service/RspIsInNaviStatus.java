package com.geely.lib.oneosapi.navi.model.service;

import android.os.Parcel;
import android.os.Parcelable;
import com.geely.lib.oneosapi.navi.model.base.NaviBaseModel;

/* loaded from: classes.dex */
public class RspIsInNaviStatus extends NaviBaseModel implements Parcelable {
    public static final Parcelable.Creator<RspIsInNaviStatus> CREATOR = new Parcelable.Creator<RspIsInNaviStatus>() { // from class: com.geely.lib.oneosapi.navi.model.service.RspIsInNaviStatus.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RspIsInNaviStatus createFromParcel(Parcel source) {
            return new RspIsInNaviStatus(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RspIsInNaviStatus[] newArray(int size) {
            return new RspIsInNaviStatus[size];
        }
    };
    private int isInNaviStatus;

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int getIsInNaviStatus() {
        return this.isInNaviStatus;
    }

    public void setIsInNaviStatus(int isInNaviStatus) {
        this.isInNaviStatus = isInNaviStatus;
    }

    public RspIsInNaviStatus(int isInNaviStatus) {
        this.isInNaviStatus = isInNaviStatus;
    }

    protected RspIsInNaviStatus(Parcel in) {
        super(in);
        this.isInNaviStatus = in.readInt();
    }

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel, android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeInt(this.isInNaviStatus);
    }
}