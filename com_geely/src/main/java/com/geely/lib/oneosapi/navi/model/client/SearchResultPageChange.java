package com.geely.lib.oneosapi.navi.model.client;

import android.os.Parcel;
import android.os.Parcelable;
import com.geely.lib.oneosapi.navi.model.base.NaviBaseModel;
import com.geely.lib.oneosapi.navi.model.base.NaviProtocolID;

/* loaded from: classes.dex */
public class SearchResultPageChange extends NaviBaseModel implements Parcelable {
    public static final Parcelable.Creator<SearchResultPageChange> CREATOR = new Parcelable.Creator<SearchResultPageChange>() { // from class: com.geely.lib.oneosapi.navi.model.client.SearchResultPageChange.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SearchResultPageChange createFromParcel(Parcel source) {
            return new SearchResultPageChange(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SearchResultPageChange[] newArray(int size) {
            return new SearchResultPageChange[size];
        }
    };
    public static final int NEXT_PAGE = 0;
    public static final int PREVIOUS_PAGE = 1;
    private int action;

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

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel, android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeInt(this.action);
    }

    public SearchResultPageChange() {
        this(0);
    }

    public SearchResultPageChange(int action) {
        this.action = 0;
        this.action = action;
        setProtocolID(NaviProtocolID.SEARCH_REQUEST_CHANGE_PAGE);
    }

    protected SearchResultPageChange(Parcel in) {
        super(in);
        this.action = 0;
        this.action = in.readInt();
    }

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel
    public String toString() {
        return "SearchResultPageChange{action=" + this.action + ",base=" + super.toString() + '}';
    }
}