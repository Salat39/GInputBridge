package com.geely.lib.oneosapi.navi.model.client;

import android.os.Parcel;
import android.os.Parcelable;
import com.geely.lib.oneosapi.navi.model.base.NaviBaseModel;
import com.geely.lib.oneosapi.navi.model.base.NaviProtocolID;

/* loaded from: classes.dex */
public class StartNaviModel extends NaviBaseModel implements Parcelable {
    public static final Parcelable.Creator<StartNaviModel> CREATOR = new Parcelable.Creator<StartNaviModel>() { // from class: com.geely.lib.oneosapi.navi.model.client.StartNaviModel.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public StartNaviModel createFromParcel(Parcel in) {
            return new StartNaviModel(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public StartNaviModel[] newArray(int size) {
            return new StartNaviModel[size];
        }
    };
    private int naviType;

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public StartNaviModel() {
        setProtocolID(NaviProtocolID.NAVI_OP_START_NAVI);
    }

    public StartNaviModel(NaviBaseModel reqModel) {
        copyBaseInfo(reqModel);
        setProtocolID(NaviProtocolID.NAVI_OP_START_NAVI);
    }

    protected StartNaviModel(Parcel in) {
        super(in);
        this.naviType = in.readInt();
    }

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel, android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeInt(this.naviType);
    }

    public int getNaviType() {
        return this.naviType;
    }

    public void setNaviType(int naviType) {
        this.naviType = naviType;
    }

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel
    public String toString() {
        return "StartNaviModel{, naviType=" + this.naviType + "', {base=" + super.toString() + "};'}";
    }
}