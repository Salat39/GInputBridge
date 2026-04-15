package com.geely.lib.oneosapi.navi.model.service;

import android.os.Parcel;
import android.os.Parcelable;
import com.geely.lib.oneosapi.navi.model.base.NaviBaseModel;
import com.geely.lib.oneosapi.navi.model.base.NaviProtocolID;

/* loaded from: classes.dex */
public class RspShowOnDimFocus extends NaviBaseModel implements Parcelable {
    public static final Parcelable.Creator<RspShowOnDimFocus> CREATOR = new Parcelable.Creator<RspShowOnDimFocus>() { // from class: com.geely.lib.oneosapi.navi.model.service.RspShowOnDimFocus.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RspShowOnDimFocus createFromParcel(Parcel source) {
            return new RspShowOnDimFocus(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RspShowOnDimFocus[] newArray(int size) {
            return new RspShowOnDimFocus[size];
        }
    };
    private String focusMapPackage;
    private int focusMapVendor;

    public RspShowOnDimFocus(int focusMapVendor, String focusMapPackage) {
        this.focusMapVendor = focusMapVendor;
        this.focusMapPackage = focusMapPackage;
        setProtocolID(NaviProtocolID.RSP_SHOW_ON_DIM_FOCUS);
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

    protected RspShowOnDimFocus(Parcel in) {
        super(in);
        this.focusMapVendor = in.readInt();
        this.focusMapPackage = in.readString();
    }

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel
    public String toString() {
        super.toString();
        return "RspShowOnDimFocus{focusMapVendor=" + this.focusMapVendor + "focusMapPackage=" + this.focusMapPackage + '}';
    }
}