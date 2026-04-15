package com.geely.lib.oneosapi.navi.model.base;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class ResponseModel extends NaviBaseModel implements Parcelable {
    public static final Parcelable.Creator<ResponseModel> CREATOR = new Parcelable.Creator<ResponseModel>() { // from class: com.geely.lib.oneosapi.navi.model.base.ResponseModel.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ResponseModel createFromParcel(Parcel source) {
            return new ResponseModel(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ResponseModel[] newArray(int size) {
            return new ResponseModel[size];
        }
    };
    private int responseCode;
    private String responseString;

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int getResponseCode() {
        return this.responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseString() {
        return this.responseString;
    }

    public void setResponseString(String responseString) {
        this.responseString = responseString;
    }

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel, android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeInt(this.responseCode);
        dest.writeString(this.responseString);
    }

    public ResponseModel(NaviBaseModel reqModel) {
        copyBaseInfo(reqModel);
    }

    protected ResponseModel(Parcel in) {
        super(in);
        this.responseCode = in.readInt();
        this.responseString = in.readString();
    }

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel
    public String toString() {
        super.toString();
        return "ResponseModel{responseCode=" + this.responseCode + ", responseString='" + this.responseString + "'}";
    }
}