package com.geely.lib.oneosapi.navi.model.service;

import android.os.Parcel;
import android.os.Parcelable;
import com.geely.lib.oneosapi.navi.model.base.NaviBaseModel;
import com.geely.lib.oneosapi.navi.model.base.PoiInfo;
import com.geely.lib.oneosapi.navi.model.base.SuggestionCity;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class RspPoiResult extends NaviBaseModel implements Parcelable {
    public static final Parcelable.Creator<RspPoiResult> CREATOR = new Parcelable.Creator<RspPoiResult>() { // from class: com.geely.lib.oneosapi.navi.model.service.RspPoiResult.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RspPoiResult createFromParcel(Parcel source) {
            return new RspPoiResult(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RspPoiResult[] newArray(int size) {
            return new RspPoiResult[size];
        }
    };
    private List<PoiInfo> poiInfoList;
    private List<SuggestionCity> suggestionCityList;

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public RspPoiResult(NaviBaseModel reqModel) {
        this.suggestionCityList = new ArrayList();
        this.poiInfoList = new ArrayList();
        copyBaseInfo(reqModel);
    }

    public List<SuggestionCity> getSuggestionCityList() {
        return this.suggestionCityList;
    }

    public void setSuggestionCityList(List<SuggestionCity> suggestionCityList) {
        this.suggestionCityList = suggestionCityList;
    }

    public List<PoiInfo> getPoiInfoList() {
        return this.poiInfoList;
    }

    public void setPoiInfoList(List<PoiInfo> poiInfoList) {
        this.poiInfoList = poiInfoList;
    }

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel, android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeTypedList(this.suggestionCityList);
        dest.writeTypedList(this.poiInfoList);
    }

    protected RspPoiResult(Parcel in) {
        super(in);
        this.suggestionCityList = new ArrayList();
        this.poiInfoList = new ArrayList();
        this.suggestionCityList = in.createTypedArrayList(SuggestionCity.CREATOR);
        this.poiInfoList = in.createTypedArrayList(PoiInfo.CREATOR);
    }

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel
    public String toString() {
        return "RspPoiResult{suggestionCityList=" + this.suggestionCityList + ", poiInfoList=" + this.poiInfoList + "{base=" + super.toString() + "}; }";
    }
}