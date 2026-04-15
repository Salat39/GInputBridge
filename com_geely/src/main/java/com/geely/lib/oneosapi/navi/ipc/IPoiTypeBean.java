package com.geely.lib.oneosapi.navi.ipc;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class IPoiTypeBean implements Parcelable {
    public static final Parcelable.Creator<IPoiTypeBean> CREATOR = new Parcelable.Creator<IPoiTypeBean>() { // from class: com.geely.lib.oneosapi.navi.ipc.IPoiTypeBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public IPoiTypeBean createFromParcel(Parcel in) {
            return new IPoiTypeBean(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public IPoiTypeBean[] newArray(int size) {
            return new IPoiTypeBean[size];
        }
    };
    private Bundle extras;
    private String typeCode;
    private String typeName;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public IPoiTypeBean() {
    }

    protected IPoiTypeBean(Parcel in) {
        this.typeCode = in.readString();
        this.typeName = in.readString();
        this.extras = in.readBundle(Bundle.class.getClassLoader());
    }

    public void readFromParcel(Parcel in) {
        this.typeCode = in.readString();
        this.typeName = in.readString();
        this.extras = in.readBundle(Bundle.class.getClassLoader());
    }

    public String getTypeCode() {
        return this.typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getTypeName() {
        return this.typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Bundle getExtras() {
        return this.extras;
    }

    public void setExtras(Bundle extras) {
        this.extras = extras;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.typeCode);
        dest.writeString(this.typeName);
        dest.writeBundle(this.extras);
    }

    public String toString() {
        return "IPoiTypeBean{typeCode='" + this.typeCode + "', typeName='" + this.typeName + "', extras=" + this.extras + '}';
    }
}