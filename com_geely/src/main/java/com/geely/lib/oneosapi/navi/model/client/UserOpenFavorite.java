package com.geely.lib.oneosapi.navi.model.client;

import android.os.Parcel;
import android.os.Parcelable;
import com.geely.lib.oneosapi.navi.model.base.NaviBaseModel;
import com.geely.lib.oneosapi.navi.model.base.NaviProtocolID;

/* loaded from: classes.dex */
public class UserOpenFavorite extends NaviBaseModel implements Parcelable {
    public static final Parcelable.Creator<UserOpenFavorite> CREATOR = new Parcelable.Creator<UserOpenFavorite>() { // from class: com.geely.lib.oneosapi.navi.model.client.UserOpenFavorite.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public UserOpenFavorite createFromParcel(Parcel source) {
            return new UserOpenFavorite(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public UserOpenFavorite[] newArray(int size) {
            return new UserOpenFavorite[size];
        }
    };
    public static final int TYPE_ALL = 3;
    public static final int TYPE_COMPANY = 2;
    public static final int TYPE_HOME = 1;
    public static final int TYPE_NORMAL = 0;
    private int favType;

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int getFavType() {
        return this.favType;
    }

    public void setFavType(int favType) {
        this.favType = favType;
    }

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel, android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeInt(this.favType);
    }

    public UserOpenFavorite() {
        this(0);
    }

    public UserOpenFavorite(int favType) {
        setProtocolID(NaviProtocolID.USER_OPEN_FAVORITE);
        this.favType = favType;
    }

    protected UserOpenFavorite(Parcel in) {
        super(in);
        this.favType = in.readInt();
    }

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel
    public String toString() {
        return "UserOpenFavorite{favType=" + this.favType + "{base=" + super.toString() + "}; }";
    }
}