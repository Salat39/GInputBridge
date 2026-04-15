package com.geely.lib.oneosapi.navi.model.client;

import android.os.Parcel;
import android.os.Parcelable;
import com.geely.lib.oneosapi.navi.model.base.NaviBaseModel;
import com.geely.lib.oneosapi.navi.model.base.NaviProtocolID;

/* loaded from: classes.dex */
public class MapOperaViewMode extends NaviBaseModel implements Parcelable {
    public static final Parcelable.Creator<MapOperaViewMode> CREATOR = new Parcelable.Creator<MapOperaViewMode>() { // from class: com.geely.lib.oneosapi.navi.model.client.MapOperaViewMode.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MapOperaViewMode createFromParcel(Parcel source) {
            return new MapOperaViewMode(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MapOperaViewMode[] newArray(int size) {
            return new MapOperaViewMode[size];
        }
    };
    public static final int MODE_2D_FRONT_UP = 0;
    public static final int MODE_2D_NORTHWARD = 1;
    public static final int MODE_3D_FRONT_UP = 2;
    public static final int MODE_LOOP_SWITCH = 3;
    private int viewMode;

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int getViewMode() {
        return this.viewMode;
    }

    public void setViewMode(int mode) {
        this.viewMode = mode;
    }

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel, android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeInt(this.viewMode);
    }

    public MapOperaViewMode() {
        this(0);
    }

    public MapOperaViewMode(int viewMode) {
        this.viewMode = viewMode;
        setProtocolID(NaviProtocolID.MAP_OP_VIEW_MODE);
    }

    protected MapOperaViewMode(Parcel in) {
        super(in);
        this.viewMode = in.readInt();
    }

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel
    public String toString() {
        return "MapOperaViewMode{viewMode=" + this.viewMode + "{base=" + super.toString() + "}; }";
    }
}