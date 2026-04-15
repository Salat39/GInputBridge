package com.geely.lib.oneosapi.navi.model.service;

import android.os.Parcel;
import android.os.Parcelable;
import com.geely.lib.oneosapi.navi.model.base.NaviBaseModel;
import com.geely.lib.oneosapi.navi.model.base.SearchPoi;
import com.geely.lib.oneosapi.navi.model.base.SuggestionCity;
import java.util.List;

/* loaded from: classes.dex */
public class RspPoiSearchResult extends NaviBaseModel implements Parcelable {
    public static final Parcelable.Creator<RspPoiSearchResult> CREATOR = new Parcelable.Creator<RspPoiSearchResult>() { // from class: com.geely.lib.oneosapi.navi.model.service.RspPoiSearchResult.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RspPoiSearchResult createFromParcel(Parcel source) {
            return new RspPoiSearchResult(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RspPoiSearchResult[] newArray(int size) {
            return new RspPoiSearchResult[size];
        }
    };
    List<SearchPoi> mPOIList;
    List<SuggestionCity> mSuggestCities;

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    protected RspPoiSearchResult(Parcel in) {
        super(in);
        this.mSuggestCities = in.createTypedArrayList(SuggestionCity.CREATOR);
        this.mPOIList = in.createTypedArrayList(SearchPoi.CREATOR);
    }

    public RspPoiSearchResult(NaviBaseModel reqModel) {
        copyBaseInfo(reqModel);
    }

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel, android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeTypedList(this.mSuggestCities);
        dest.writeTypedList(this.mPOIList);
    }

    public List<SuggestionCity> getSuggestCities() {
        return this.mSuggestCities;
    }

    public void setSuggestCities(List<SuggestionCity> mSuggestCities) {
        this.mSuggestCities = mSuggestCities;
    }

    public List<SearchPoi> getPOIList() {
        return this.mPOIList;
    }

    public void setPOIList(List<SearchPoi> mPOIList) {
        this.mPOIList = mPOIList;
    }

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel
    public String toString() {
        return "RspPoiSearchResult{mSuggestCities=" + this.mSuggestCities + "mPOIList=" + this.mPOIList + "{base=" + super.toString() + "}; }";
    }
}