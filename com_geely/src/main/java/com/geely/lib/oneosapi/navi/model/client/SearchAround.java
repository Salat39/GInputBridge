package com.geely.lib.oneosapi.navi.model.client;

import android.os.Parcel;
import android.os.Parcelable;
import com.geely.lib.oneosapi.navi.model.base.LatLng;
import com.geely.lib.oneosapi.navi.model.base.NaviBaseModel;
import com.geely.lib.oneosapi.navi.model.base.NaviProtocolID;
import com.geely.lib.oneosapi.navi.model.base.PoiType;

/* loaded from: classes.dex */
public class SearchAround extends NaviBaseModel implements Parcelable {
    public static final int ACTION_JUST_INFO = 0;
    public static final int ACTION_SHOW_IN_MAP = 1;
    public static final Parcelable.Creator<SearchAround> CREATOR = new Parcelable.Creator<SearchAround>() { // from class: com.geely.lib.oneosapi.navi.model.client.SearchAround.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SearchAround createFromParcel(Parcel source) {
            return new SearchAround(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SearchAround[] newArray(int size) {
            return new SearchAround[size];
        }
    };
    public static final int SEARCH_TYPE_AROUND = 1;
    public static final int SEARCH_TYPE_KEYWORD = 0;
    private int action;
    private LatLng centerLatLng;
    private int keyWordType;
    private String keywords;
    private PoiType poiType;
    private int searchRadius;
    private int searchType;
    private int sortRule;

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int getAction() {
        return this.action;
    }

    public void setAction(int action) {
        this.action = action;
    }

    public LatLng getCenterLatLng() {
        return this.centerLatLng;
    }

    public void setCenterLatLng(LatLng centerLatLng) {
        this.centerLatLng = centerLatLng;
    }

    public PoiType getPoiType() {
        return this.poiType;
    }

    public void setPoiType(PoiType poiType) {
        this.poiType = poiType;
    }

    public SearchAround() {
        this.searchRadius = 3000;
        setProtocolID(NaviProtocolID.SEARCH_AROUND);
        setProtocolVersion(10);
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

    public int getSearchRadius() {
        return this.searchRadius;
    }

    public void setSearchRadius(int searchRadius) {
        this.searchRadius = searchRadius;
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
        dest.writeString(this.keywords);
        dest.writeInt(this.searchRadius);
        if (getProtocolVersion() >= 10) {
            dest.writeInt(this.action);
        }
        dest.writeParcelable(this.centerLatLng, flags);
        dest.writeParcelable(this.poiType, flags);
        dest.writeInt(this.sortRule);
        dest.writeInt(this.keyWordType);
    }

    protected SearchAround(Parcel in) {
        super(in);
        this.searchRadius = 3000;
        this.searchType = in.readInt();
        this.keywords = in.readString();
        this.searchRadius = in.readInt();
        if (getProtocolVersion() >= 10) {
            this.action = in.readInt();
        }
        this.centerLatLng = (LatLng) in.readParcelable(LatLng.class.getClassLoader());
        this.poiType = (PoiType) in.readParcelable(PoiType.class.getClassLoader());
        this.sortRule = in.readInt();
        this.keyWordType = in.readInt();
    }

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel
    public String toString() {
        super.toString();
        return "SearchAround{searchType=" + this.searchType + ", keywords='" + this.keywords + "', searchRadius=" + this.searchRadius + ", action=" + this.action + ", centerLatLng=" + this.centerLatLng + ", poiType=" + this.poiType + ", sortRule=" + this.sortRule + ", keyWordType=" + this.keyWordType + '}';
    }
}