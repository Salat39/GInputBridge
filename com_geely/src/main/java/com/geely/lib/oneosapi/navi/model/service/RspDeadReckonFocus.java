package com.geely.lib.oneosapi.navi.model.service;

import android.os.Parcel;
import android.os.Parcelable;
import com.geely.lib.oneosapi.navi.model.base.NaviBaseModel;

/* loaded from: classes.dex */
public class RspDeadReckonFocus extends NaviBaseModel implements Parcelable {
    public static final Parcelable.Creator<RspDeadReckonFocus> CREATOR = new Parcelable.Creator<RspDeadReckonFocus>() { // from class: com.geely.lib.oneosapi.navi.model.service.RspDeadReckonFocus.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RspDeadReckonFocus createFromParcel(Parcel source) {
            return new RspDeadReckonFocus(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RspDeadReckonFocus[] newArray(int size) {
            return new RspDeadReckonFocus[size];
        }
    };
    private String focusMapPackage;
    private int focusMapVendor;

    public RspDeadReckonFocus(int focusMapVendor, String focusMapPackage) {
        this.focusMapVendor = focusMapVendor;
        this.focusMapPackage = focusMapPackage;
    }

    public int getFocusMapVendor() {
        return this.focusMapVendor;
    }

    public void setFocusMapVendor(int focusMapVendor) {
        this.focusMapVendor = focusMapVendor;
    }

    public String getFocusMapPackage() {
        return this.focusMapPackage;
    }

    public void setFocusMapPackage(String focusMapPackage) {
        this.focusMapPackage = focusMapPackage;
    }

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel, android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeInt(this.focusMapVendor);
        dest.writeString(this.focusMapPackage);
    }

    protected RspDeadReckonFocus(Parcel in) {
        super(in);
        this.focusMapVendor = in.readInt();
        this.focusMapPackage = in.readString();
    }

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel
    public String toString() {
        super.toString();
        return "RspDeadReckonFocus{focusMapVendor=" + this.focusMapVendor + "focusMapPackage=" + this.focusMapPackage + '}';
    }
}