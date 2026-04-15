package com.geely.lib.oneosapi.navi.model.client;

import android.os.Parcel;
import android.os.Parcelable;
import com.geely.lib.oneosapi.navi.model.base.NaviBaseModel;
import com.geely.lib.oneosapi.navi.model.base.NaviProtocolID;
import com.geely.lib.oneosapi.navi.model.base.PoiType;

/* loaded from: classes.dex */
public class SearchByKeyword extends NaviBaseModel implements Parcelable {
    public static final Parcelable.Creator<SearchByKeyword> CREATOR = new Parcelable.Creator<SearchByKeyword>() { // from class: com.geely.lib.oneosapi.navi.model.client.SearchByKeyword.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SearchByKeyword createFromParcel(Parcel in) {
            return new SearchByKeyword(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SearchByKeyword[] newArray(int size) {
            return new SearchByKeyword[size];
        }
    };

    @Deprecated
    public static final int SEARCH_TYPE_AROUND = 1;
    public static final int SEARCH_TYPE_KEYWORD = 0;
    public static final int SEARCH_TYPE_SET_COMPANY = 2;
    public static final int SEARCH_TYPE_SET_HOME = 1;
    public static final int SORT_RULE_DEFAULT = 0;
    public static final int SORT_RULE_DISTANCE = 1;
    private int action;
    private int keyWordType;
    private String keywords;
    private PoiType poiType;
    private String searchCity;
    private int searchType;
    private int sortRule;

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int getKeyWordType() {
        return this.keyWordType;
    }

    public void setKeyWordType(int keyWordType) {
        this.keyWordType = keyWordType;
    }

    public int getSortRule() {
        return this.sortRule;
    }

    public void setSortRule(int sortRule) {
        this.sortRule = sortRule;
    }

    public PoiType getPoiType() {
        return this.poiType;
    }

    public void setPoiType(PoiType poiType) {
        this.poiType = poiType;
    }

    public int getAction() {
        return this.action;
    }

    public void setAction(int action) {
        this.action = action;
    }

    public SearchByKeyword(NaviBaseModel reqModel) {
        copyBaseInfo(reqModel);
        setProtocolID(NaviProtocolID.SEARCH_BY_KEYWORD);
    }

    public SearchByKeyword(int searchType, String keywords, String searchCity) {
        this.searchType = searchType;
        this.keywords = keywords;
        this.searchCity = searchCity;
        setProtocolID(NaviProtocolID.SEARCH_BY_KEYWORD);
    }

    public SearchByKeyword(String keywords, String searchCity) {
        this.keywords = keywords;
        this.searchCity = searchCity;
        setProtocolID(NaviProtocolID.SEARCH_BY_KEYWORD);
    }

    protected SearchByKeyword(Parcel in) {
        super(in);
        this.searchType = in.readInt();
        this.keywords = in.readString();
        this.searchCity = in.readString();
        this.action = in.readInt();
        this.poiType = (PoiType) in.readParcelable(PoiType.class.getClassLoader());
        this.sortRule = in.readInt();
        this.keyWordType = in.readInt();
    }

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel, android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeInt(this.searchType);
        dest.writeString(this.keywords);
        dest.writeString(this.searchCity);
        dest.writeInt(this.action);
        dest.writeParcelable(this.poiType, flags);
        dest.writeInt(this.sortRule);
        dest.writeInt(this.keyWordType);
    }

    public int getSearchType() {
        return this.searchType;
    }

    public void setSearchType(int searchType) {
        this.searchType = searchType;
    }

    public String getKeywords() {
        return this.keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getSearchCity() {
        return this.searchCity;
    }

    public void setSearchCity(String searchCity) {
        this.searchCity = searchCity;
    }

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel
    public String toString() {
        super.toString();
        return "SearchByKeyword{sortRule=" + this.sortRule + ", keyWordType=" + this.keyWordType + ", searchType=" + this.searchType + ", keywords='" + this.keywords + "', searchCity='" + this.searchCity + "', action=" + this.action + ", poiType=" + this.poiType + '}';
    }
}