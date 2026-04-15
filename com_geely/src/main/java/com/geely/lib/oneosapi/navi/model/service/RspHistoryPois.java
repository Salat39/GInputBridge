package com.geely.lib.oneosapi.navi.model.service;

import android.os.Parcel;
import android.os.Parcelable;
import com.geely.lib.oneosapi.navi.model.base.NaviBaseModel;
import com.geely.lib.oneosapi.navi.model.base.PoiInfo;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class RspHistoryPois extends NaviBaseModel implements Parcelable {
    public static final Parcelable.Creator<RspHistoryPois> CREATOR = new Parcelable.Creator<RspHistoryPois>() { // from class: com.geely.lib.oneosapi.navi.model.service.RspHistoryPois.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RspHistoryPois createFromParcel(Parcel source) {
            return new RspHistoryPois(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RspHistoryPois[] newArray(int size) {
            return new RspHistoryPois[size];
        }
    };
    private List<PoiInfo> poiList;

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public RspHistoryPois(NaviBaseModel reqModel) {
        this.poiList = new ArrayList();
        copyBaseInfo(reqModel);
    }

    public List<PoiInfo> getPoiList() {
        return this.poiList;
    }

    public void setPoiList(List<PoiInfo> poiList) {
        this.poiList = poiList;
    }

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel, android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeTypedList(this.poiList);
    }

    protected RspHistoryPois(Parcel in) {
        super(in);
        this.poiList = new ArrayList();
        this.poiList = in.createTypedArrayList(PoiInfo.CREATOR);
    }

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel
    public String toString() {
        return "RspHistoryPois{poiList=" + this.poiList + "; {base=" + super.toString() + "}; }";
    }
}