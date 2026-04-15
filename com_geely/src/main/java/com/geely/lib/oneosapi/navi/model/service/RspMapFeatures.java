package com.geely.lib.oneosapi.navi.model.service;

import android.os.Parcel;
import android.os.Parcelable;
import com.geely.lib.oneosapi.navi.model.base.NaviBaseModel;

/* loaded from: classes.dex */
public class RspMapFeatures extends NaviBaseModel implements Parcelable {
    public static final Parcelable.Creator<RspMapFeatures> CREATOR = new Parcelable.Creator<RspMapFeatures>() { // from class: com.geely.lib.oneosapi.navi.model.service.RspMapFeatures.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RspMapFeatures createFromParcel(Parcel in) {
            return new RspMapFeatures(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RspMapFeatures[] newArray(int size) {
            return new RspMapFeatures[size];
        }
    };
    private int mapFeatures;

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    protected RspMapFeatures(Parcel in) {
        super(in);
        this.mapFeatures = in.readInt();
    }

    public int getMapFeatures() {
        return this.mapFeatures;
    }

    public void setMapFeatures(int mapFeatures) {
        this.mapFeatures = mapFeatures;
    }

    public RspMapFeatures(NaviBaseModel reqModel) {
        copyBaseInfo(reqModel);
    }

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel, android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeInt(this.mapFeatures);
    }

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel
    public String toString() {
        super.toString();
        return "RspMapFeatures{mapFeatures=" + this.mapFeatures + '}';
    }
}