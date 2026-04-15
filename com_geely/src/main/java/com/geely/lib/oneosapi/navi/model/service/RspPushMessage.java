package com.geely.lib.oneosapi.navi.model.service;

import android.os.Parcel;
import android.os.Parcelable;
import com.geely.lib.oneosapi.navi.model.base.NaviBaseModel;

/* loaded from: classes.dex */
public class RspPushMessage extends NaviBaseModel implements Parcelable {
    public static final Parcelable.Creator<RspPushMessage> CREATOR = new Parcelable.Creator<RspPushMessage>() { // from class: com.geely.lib.oneosapi.navi.model.service.RspPushMessage.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RspPushMessage createFromParcel(Parcel source) {
            return new RspPushMessage(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RspPushMessage[] newArray(int size) {
            return new RspPushMessage[size];
        }
    };
    public static final int SHOW_NOTIFICATION = 0;
    public static final int SHOW_WIDGET_CLOSE = 2;
    public static final int SHOW_WIDGET_NAVI = 3;
    public static final int SHOW_WIDGET_TIMEOUT = 1;
    private int actionType;

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public RspPushMessage(NaviBaseModel reqModel) {
        copyBaseInfo(reqModel);
    }

    public int getActionType() {
        return this.actionType;
    }

    public void setActionType(int actionType) {
        this.actionType = actionType;
    }

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel, android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeInt(this.actionType);
    }

    protected RspPushMessage(Parcel in) {
        super(in);
        this.actionType = in.readInt();
    }

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel
    public String toString() {
        return "RspPushMessage{actionType=" + this.actionType + "{base=" + super.toString() + "}; }";
    }
}