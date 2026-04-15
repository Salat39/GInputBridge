package com.geely.lib.oneosapi.navi.model.client;

import android.os.Parcel;
import android.os.Parcelable;
import com.geely.lib.oneosapi.navi.model.base.NaviBaseModel;
import com.geely.lib.oneosapi.navi.model.base.NaviProtocolID;
import com.geely.lib.oneosapi.navi.model.base.PoiType;

/* loaded from: classes.dex */
public class SearchAlongWay extends NaviBaseModel {
    public static final int ACTION_JUST_INFO = 0;
    public static final int ACTION_SEARCH_IN_MAP = 1;
    public static final Parcelable.Creator<SearchAlongWay> CREATOR = new Parcelable.Creator<SearchAlongWay>() { // from class: com.geely.lib.oneosapi.navi.model.client.SearchAlongWay.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SearchAlongWay createFromParcel(Parcel source) {
            return new SearchAlongWay(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SearchAlongWay[] newArray(int size) {
            return new SearchAlongWay[size];
        }
    };
    public static final int SEARCH_ALONG_WAY_ATM = 2;
    public static final int SEARCH_ALONG_WAY_CHARGING_STATION = 5;
    public static final int SEARCH_ALONG_WAY_FOOD = 7;
    public static final int SEARCH_ALONG_WAY_GAS_STATION = 4;
    public static final int SEARCH_ALONG_WAY_HOTEL = 6;
    public static final int SEARCH_ALONG_WAY_OTHER = 0;
    public static final int SEARCH_ALONG_WAY_SERVICE_AREA = 8;
    public static final int SEARCH_ALONG_WAY_SERVICE_CENTRE = 3;
    public static final int SEARCH_ALONG_WAY_TOILET = 1;
    private int action;
    private int keyWordType;
    private String mKeyWord;
    private PoiType mPoiType;
    private int searchType;
    private int sortRule;

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public SearchAlongWay(int searchType, String mKeyWord, PoiType poiType) {
        this.searchType = searchType;
        this.mKeyWord = mKeyWord;
        this.mPoiType = poiType;
        setProtocolID(NaviProtocolID.SEARCH_ALONG_WAY);
    }

    public void setSearchType(int searchType) {
        this.searchType = searchType;
    }

    public int getSearchType() {
        return this.searchType;
    }

    public String getKeyWord() {
        return this.mKeyWord;
    }

    public void setKeyWord(String mKeyWord) {
        this.mKeyWord = mKeyWord;
    }

    public PoiType getPoiType() {
        return this.mPoiType;
    }

    public void setPoiType(PoiType mPoiType) {
        this.mPoiType = mPoiType;
    }

    public int getAction() {
        return this.action;
    }

    public void setAction(int action) {
        this.action = action;
    }

    public int getSortRule() {
        return this.sortRule;
    }

    public void setSortRule(int sortRule) {
        this.sortRule = sortRule;
    }

    public int getKeyWordType() {
        return this.keyWordType;
    }

    public void setKeyWordType(int keyWordType) {
        this.keyWordType = keyWordType;
    }

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel, android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeInt(this.searchType);
        dest.writeString(this.mKeyWord);
        dest.writeParcelable(this.mPoiType, flags);
        dest.writeInt(this.action);
        dest.writeInt(this.sortRule);
        dest.writeInt(this.keyWordType);
    }

    protected SearchAlongWay(Parcel in) {
        super(in);
        this.searchType = in.readInt();
        this.mKeyWord = in.readString();
        this.mPoiType = (PoiType) in.readParcelable(PoiType.class.getClassLoader());
        this.action = in.readInt();
        this.sortRule = in.readInt();
        this.keyWordType = in.readInt();
    }

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel
    public String toString() {
        super.toString();
        return "SearchAlongWay{searchType=" + this.searchType + ", mKeyWord='" + this.mKeyWord + "', mPoiType=" + this.mPoiType + ", action=" + this.action + ", sortRule=" + this.sortRule + ", keyWordType=" + this.keyWordType + '}';
    }
}