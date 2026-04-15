package com.geely.lib.oneosapi.navi.model.service;

import android.os.Parcel;
import android.os.Parcelable;
import com.geely.lib.oneosapi.navi.model.base.NaviBaseModel;

/* loaded from: classes.dex */
public class RspUserLogout extends NaviBaseModel implements Parcelable {
    public static final Parcelable.Creator<RspUserLogout> CREATOR = new Parcelable.Creator<RspUserLogout>() { // from class: com.geely.lib.oneosapi.navi.model.service.RspUserLogout.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RspUserLogout createFromParcel(Parcel source) {
            return new RspUserLogout(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RspUserLogout[] newArray(int size) {
            return new RspUserLogout[size];
        }
    };
    private int autoLogoutResult;

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public RspUserLogout(int autoLogoutResult) {
        this.autoLogoutResult = autoLogoutResult;
    }

    protected RspUserLogout(Parcel in) {
        super(in);
        this.autoLogoutResult = in.readInt();
    }

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel, android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeInt(this.autoLogoutResult);
    }
}