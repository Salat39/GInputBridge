package com.geely.lib.oneosapi.navi.model.service;

import android.os.Parcel;
import android.os.Parcelable;
import com.geely.lib.oneosapi.navi.model.base.NaviBaseModel;

/* loaded from: classes.dex */
public class RspUserIdBind extends NaviBaseModel implements Parcelable {
    public static final Parcelable.Creator<RspUserIdBind> CREATOR = new Parcelable.Creator<RspUserIdBind>() { // from class: com.geely.lib.oneosapi.navi.model.service.RspUserIdBind.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RspUserIdBind createFromParcel(Parcel source) {
            return new RspUserIdBind(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RspUserIdBind[] newArray(int size) {
            return new RspUserIdBind[size];
        }
    };
    private String autoUserAvatar;
    private String autoUserId;
    private String autoUserName;
    private int bindingSuccess;

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getAutoUserId() {
        return this.autoUserId;
    }

    public void setAutoUserId(String autoUserId) {
        this.autoUserId = autoUserId;
    }

    public String getAutoUserAvatar() {
        return this.autoUserAvatar;
    }

    public void setAutoUserAvatar(String autoUserAvatar) {
        this.autoUserAvatar = autoUserAvatar;
    }

    public String getAutoUserName() {
        return this.autoUserName;
    }

    public void setAutoUserName(String autoUserName) {
        this.autoUserName = autoUserName;
    }

    public int isBindingSuccess() {
        return this.bindingSuccess;
    }

    public void setBindingSuccess(int bindingSuccess) {
        this.bindingSuccess = bindingSuccess;
    }

    public RspUserIdBind() {
    }

    protected RspUserIdBind(Parcel in) {
        super(in);
        this.autoUserId = in.readString();
        this.autoUserAvatar = in.readString();
        this.autoUserName = in.readString();
        this.bindingSuccess = in.readInt();
    }

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel, android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(this.autoUserId);
        dest.writeString(this.autoUserAvatar);
        dest.writeString(this.autoUserName);
        dest.writeInt(this.bindingSuccess);
    }
}