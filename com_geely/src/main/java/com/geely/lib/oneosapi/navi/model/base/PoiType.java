package com.geely.lib.oneosapi.navi.model.base;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class PoiType implements Parcelable {
    public static final Parcelable.Creator<PoiType> CREATOR = new Parcelable.Creator<PoiType>() { // from class: com.geely.lib.oneosapi.navi.model.base.PoiType.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PoiType createFromParcel(Parcel source) {
            return new PoiType(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PoiType[] newArray(int size) {
            return new PoiType[size];
        }
    };
    private String typeCode;
    private String typeName;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getTypeCode() {
        return this.typeCode;
    }

    public PoiType setTypeCode(String typeCode) {
        this.typeCode = typeCode;
        return this;
    }

    public String getTypeName() {
        return this.typeName;
    }

    public PoiType setTypeName(String typeName) {
        this.typeName = typeName;
        return this;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.typeCode);
        dest.writeString(this.typeName);
    }

    public PoiType() {
    }

    protected PoiType(Parcel in) {
        this.typeCode = in.readString();
        this.typeName = in.readString();
    }

    public String toString() {
        return "PoiType{typeCode='" + this.typeCode + "', typeName='" + this.typeName + "'}";
    }
}