package com.geely.lib.oneosapi.navi.model.client;

import android.os.Parcel;
import android.os.Parcelable;
import com.geely.lib.oneosapi.navi.model.base.NaviBaseModel;
import com.geely.lib.oneosapi.navi.model.base.NaviProtocolID;

/* loaded from: classes.dex */
public class UserGetFavoritePois extends NaviBaseModel implements Parcelable {
    public static final Parcelable.Creator<UserGetFavoritePois> CREATOR = new Parcelable.Creator<UserGetFavoritePois>() { // from class: com.geely.lib.oneosapi.navi.model.client.UserGetFavoritePois.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public UserGetFavoritePois createFromParcel(Parcel source) {
            return new UserGetFavoritePois(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public UserGetFavoritePois[] newArray(int size) {
            return new UserGetFavoritePois[size];
        }
    };
    public static final int FAVORITE_TYPE_ALL = 3;
    public static final int FAVORITE_TYPE_COMPANY = 2;
    public static final int FAVORITE_TYPE_HOME = 1;
    public static final int FAVORITE_TYPE_HOME_AND_COMPANY = 4;
    public static final int FAVORITE_TYPE_NORMAL = 0;
    private int favoriteType;

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public UserGetFavoritePois(int favType) {
        this.favoriteType = favType;
        setProtocolID(NaviProtocolID.USER_GET_FAVORITE_INFO);
    }

    public int getFavoriteType() {
        return this.favoriteType;
    }

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel, android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeInt(this.favoriteType);
    }

    protected UserGetFavoritePois(Parcel in) {
        super(in);
        this.favoriteType = in.readInt();
    }

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel
    public String toString() {
        return "UserGetFavoritePois{favoriteType=" + this.favoriteType + "{base=" + super.toString() + "}; }";
    }
}