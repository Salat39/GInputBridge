package com.geely.lib.oneosapi.navi.model.client;

import android.os.Parcel;
import android.os.Parcelable;
import com.geely.lib.oneosapi.navi.model.base.NaviBaseModel;
import com.geely.lib.oneosapi.navi.model.base.NaviProtocolID;

/* loaded from: classes.dex */
public class UserIdBindModel extends NaviBaseModel implements Parcelable {
    public static final Parcelable.Creator<UserIdBindModel> CREATOR = new Parcelable.Creator<UserIdBindModel>() { // from class: com.geely.lib.oneosapi.navi.model.client.UserIdBindModel.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public UserIdBindModel createFromParcel(Parcel in) {
            return new UserIdBindModel(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public UserIdBindModel[] newArray(int size) {
            return new UserIdBindModel[size];
        }
    };
    private String bindUserDeviceId;
    private String bindUserLoginAvata;
    private String bindUserLoginId;
    private String bindUserLoginName;
    private String bindUserRequestTime;
    private String carLoginEnvironment;
    private String sourceApp;
    private String sourceAppName;

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getCarLoginEnvironment() {
        return this.carLoginEnvironment;
    }

    public void setCarLoginEnvironment(String carLoginEnvironment) {
        this.carLoginEnvironment = carLoginEnvironment;
    }

    public String getSourceApp() {
        return this.sourceApp;
    }

    public void setSourceApp(String sourceApp) {
        this.sourceApp = sourceApp;
    }

    public String getSourceAppName() {
        return this.sourceAppName;
    }

    public void setSourceAppName(String sourceAppName) {
        this.sourceAppName = sourceAppName;
    }

    public String getBindUserLoginId() {
        return this.bindUserLoginId;
    }

    public void setBindUserLoginId(String bindUserLoginId) {
        this.bindUserLoginId = bindUserLoginId;
    }

    public String getBindUserLoginName() {
        return this.bindUserLoginName;
    }

    public void setBindUserLoginName(String bindUserLoginName) {
        this.bindUserLoginName = bindUserLoginName;
    }

    public String getBindUserLoginAvata() {
        return this.bindUserLoginAvata;
    }

    public void setBindUserLoginAvata(String bindUserLoginAvata) {
        this.bindUserLoginAvata = bindUserLoginAvata;
    }

    public String getBindUserRequestTime() {
        return this.bindUserRequestTime;
    }

    public void setBindUserRequestTime(String bindUserRequestTime) {
        this.bindUserRequestTime = bindUserRequestTime;
    }

    public String getBindUserDeviceId() {
        return this.bindUserDeviceId;
    }

    public void setBindUserDeviceId(String bindUserDeviceId) {
        this.bindUserDeviceId = bindUserDeviceId;
    }

    public UserIdBindModel() {
        setProtocolID(NaviProtocolID.USER_BIND_USER_TO_AMAP);
    }

    public UserIdBindModel(NaviBaseModel reqModel) {
        copyBaseInfo(reqModel);
        setProtocolID(NaviProtocolID.USER_BIND_USER_TO_AMAP);
    }

    protected UserIdBindModel(Parcel in) {
        super(in);
        this.carLoginEnvironment = in.readString();
        this.sourceApp = in.readString();
        this.sourceAppName = in.readString();
        this.bindUserLoginId = in.readString();
        this.bindUserLoginName = in.readString();
        this.bindUserLoginAvata = in.readString();
        this.bindUserRequestTime = in.readString();
        this.bindUserDeviceId = in.readString();
    }

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel, android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(this.carLoginEnvironment);
        dest.writeString(this.sourceApp);
        dest.writeString(this.sourceAppName);
        dest.writeString(this.bindUserLoginId);
        dest.writeString(this.bindUserLoginName);
        dest.writeString(this.bindUserLoginAvata);
        dest.writeString(this.bindUserRequestTime);
        dest.writeString(this.bindUserDeviceId);
    }
}