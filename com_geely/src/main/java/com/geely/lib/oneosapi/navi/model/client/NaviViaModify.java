package com.geely.lib.oneosapi.navi.model.client;

import android.os.Parcel;
import android.os.Parcelable;
import com.geely.lib.oneosapi.navi.model.base.NaviBaseModel;
import com.geely.lib.oneosapi.navi.model.base.NaviProtocolID;
import com.geely.lib.oneosapi.navi.model.base.PoiInfo;

/* loaded from: classes.dex */
public class NaviViaModify extends NaviBaseModel implements Parcelable {
    public static final int ACTION_ADD = 0;
    public static final int ACTION_REMOVE = 1;
    public static final int ACTION_REMOVE_ALL = -1;
    public static final Parcelable.Creator<NaviViaModify> CREATOR = new Parcelable.Creator<NaviViaModify>() { // from class: com.geely.lib.oneosapi.navi.model.client.NaviViaModify.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NaviViaModify createFromParcel(Parcel source) {
            return new NaviViaModify(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NaviViaModify[] newArray(int size) {
            return new NaviViaModify[size];
        }
    };
    private int action;
    private int viaIndex;
    private PoiInfo viaPoi;

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public NaviViaModify(int action) {
        this.action = action;
        setProtocolID(NaviProtocolID.NAVI_OP_VIA_MODIFY);
    }

    public NaviViaModify(int action, PoiInfo via) {
        this.action = action;
        this.viaPoi = via;
        setProtocolID(NaviProtocolID.NAVI_OP_VIA_MODIFY);
    }

    public int getViaIndex() {
        return this.viaIndex;
    }

    public void setViaIndex(int viaIndex) {
        this.viaIndex = viaIndex;
    }

    public int getAction() {
        return this.action;
    }

    public PoiInfo getViaPoi() {
        return this.viaPoi;
    }

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel, android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeInt(this.action);
        dest.writeParcelable(this.viaPoi, flags);
        dest.writeInt(this.viaIndex);
    }

    protected NaviViaModify(Parcel in) {
        super(in);
        this.action = in.readInt();
        this.viaPoi = (PoiInfo) in.readParcelable(PoiInfo.class.getClassLoader());
        this.viaIndex = in.readInt();
    }

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel
    public String toString() {
        return "NaviViaModify{action=" + this.action + ", viaPoi=" + this.viaPoi + ", viaIndex=" + this.viaIndex + "{base=" + super.toString() + "}; }";
    }
}