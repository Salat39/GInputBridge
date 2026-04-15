package com.geely.lib.oneosapi.navi.model.service;

import android.os.Parcel;
import android.os.Parcelable;
import com.geely.lib.oneosapi.navi.model.base.NaviBaseModel;

/* loaded from: classes.dex */
public class RspIsMapAppLaunched extends NaviBaseModel implements Parcelable {
    public static final Parcelable.Creator<RspIsMapAppLaunched> CREATOR = new Parcelable.Creator<RspIsMapAppLaunched>() { // from class: com.geely.lib.oneosapi.navi.model.service.RspIsMapAppLaunched.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RspIsMapAppLaunched createFromParcel(Parcel source) {
            return new RspIsMapAppLaunched(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RspIsMapAppLaunched[] newArray(int size) {
            return new RspIsMapAppLaunched[size];
        }
    };
    private int isMapLaunched;

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int getIsMapLaunched() {
        return this.isMapLaunched;
    }

    public void setIsMapLaunched(int isMapLaunched) {
        this.isMapLaunched = isMapLaunched;
    }

    public RspIsMapAppLaunched(int isMapLaunched) {
        this.isMapLaunched = isMapLaunched;
    }

    protected RspIsMapAppLaunched(Parcel in) {
        super(in);
        this.isMapLaunched = in.readInt();
    }

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel, android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeInt(this.isMapLaunched);
    }
}