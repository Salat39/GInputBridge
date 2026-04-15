package com.geely.lib.oneosapi.navi.model.service;

import android.os.Parcel;
import android.os.Parcelable;
import com.geely.lib.oneosapi.navi.model.base.NaviBaseModel;

/* loaded from: classes.dex */
public class RspMapPresentation extends NaviBaseModel implements Parcelable {
    public static final Parcelable.Creator<RspMapPresentation> CREATOR = new Parcelable.Creator<RspMapPresentation>() { // from class: com.geely.lib.oneosapi.navi.model.service.RspMapPresentation.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RspMapPresentation createFromParcel(Parcel in) {
            return new RspMapPresentation(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RspMapPresentation[] newArray(int size) {
            return new RspMapPresentation[size];
        }
    };
    private int resultCode;

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int getResultCode() {
        return this.resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public RspMapPresentation(NaviBaseModel naviBaseModel) {
        copyBaseInfo(naviBaseModel);
    }

    protected RspMapPresentation(Parcel in) {
        super(in);
        this.resultCode = in.readInt();
    }

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel, android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeInt(this.resultCode);
    }

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel
    public String toString() {
        super.toString();
        return "RspMapPresentation{resultCode=" + this.resultCode + '}';
    }
}