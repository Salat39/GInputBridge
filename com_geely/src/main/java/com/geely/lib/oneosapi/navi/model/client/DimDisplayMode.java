package com.geely.lib.oneosapi.navi.model.client;

import android.os.Parcel;
import android.os.Parcelable;
import com.geely.lib.oneosapi.navi.model.base.NaviBaseModel;

/* loaded from: classes.dex */
public class DimDisplayMode extends NaviBaseModel implements Parcelable {
    public static final Parcelable.Creator<DimDisplayMode> CREATOR = new Parcelable.Creator<DimDisplayMode>() { // from class: com.geely.lib.oneosapi.navi.model.client.DimDisplayMode.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DimDisplayMode createFromParcel(Parcel source) {
            return new DimDisplayMode(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DimDisplayMode[] newArray(int size) {
            return new DimDisplayMode[size];
        }
    };
    public static final int DIM_DISPLAY_AR = 3;
    public static final int DIM_DISPLAY_FULL = 2;
    public static final int DIM_DISPLAY_OFF = 0;
    public static final int DIM_DISPLAY_SIMPLIFY = 1;
    private int dimDisplayMode;

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public DimDisplayMode(int dimDisplayMode) {
        setProtocolID(4100);
        setDimDisplayMode(dimDisplayMode);
    }

    public int getDimDisplayMode() {
        return this.dimDisplayMode;
    }

    public void setDimDisplayMode(int dimDisplayMode) {
        this.dimDisplayMode = dimDisplayMode;
    }

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel, android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeInt(this.dimDisplayMode);
    }

    protected DimDisplayMode(Parcel in) {
        super(in);
        this.dimDisplayMode = in.readInt();
    }

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel
    public String toString() {
        super.toString();
        return "DimDisplayMode{dimDisplayMode=" + this.dimDisplayMode + '}';
    }
}