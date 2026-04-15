package com.geely.lib.oneosapi.navi.model.client;

import android.os.Parcel;
import android.os.Parcelable;
import com.geely.lib.oneosapi.navi.model.base.NaviBaseModel;
import com.geely.lib.oneosapi.navi.model.base.NaviProtocolID;
import com.geely.lib.oneosapi.navi.model.base.PoiTypeFilters;

/* loaded from: classes.dex */
public class RequestHistoryPois extends NaviBaseModel implements Parcelable {
    public static final Parcelable.Creator<RequestHistoryPois> CREATOR = new Parcelable.Creator<RequestHistoryPois>() { // from class: com.geely.lib.oneosapi.navi.model.client.RequestHistoryPois.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RequestHistoryPois createFromParcel(Parcel source) {
            return new RequestHistoryPois(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RequestHistoryPois[] newArray(int size) {
            return new RequestHistoryPois[size];
        }
    };
    private int maxCount;
    private long poiTypeFilter;

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public RequestHistoryPois() {
        this.poiTypeFilter = PoiTypeFilters.ALL;
        this.maxCount = 30;
        setProtocolID(NaviProtocolID.NAVI_OP_GET_HISTORY_POI);
    }

    public long getPoiTypeFilter() {
        return this.poiTypeFilter;
    }

    public RequestHistoryPois setPoiTypeFilter(long poiTypeFilter) {
        this.poiTypeFilter = poiTypeFilter;
        return this;
    }

    public int getMaxCount() {
        return this.maxCount;
    }

    public RequestHistoryPois setMaxCount(int maxCount) {
        this.maxCount = maxCount;
        return this;
    }

    protected RequestHistoryPois(Parcel in) {
        super(in);
        this.poiTypeFilter = PoiTypeFilters.ALL;
        this.maxCount = 30;
        this.poiTypeFilter = in.readLong();
        this.maxCount = in.readInt();
    }

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel, android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeLong(this.poiTypeFilter);
        dest.writeInt(this.maxCount);
    }

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel
    public String toString() {
        return "RequestHistoryPois{poiTypeFilter=" + this.poiTypeFilter + ", maxCount=" + this.maxCount + "; {base=" + super.toString() + "}; }";
    }
}