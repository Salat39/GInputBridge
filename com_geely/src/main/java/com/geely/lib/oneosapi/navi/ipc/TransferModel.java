package com.geely.lib.oneosapi.navi.ipc;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import com.geely.lib.oneosapi.navi.model.base.NaviBaseModel;

/* loaded from: classes.dex */
public class TransferModel<T extends NaviBaseModel> implements Parcelable {
    public static final Parcelable.Creator<TransferModel> CREATOR = new Parcelable.Creator<TransferModel>() { // from class: com.geely.lib.oneosapi.navi.ipc.TransferModel.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public TransferModel createFromParcel(Parcel source) {
            return new TransferModel(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public TransferModel[] newArray(int size) {
            return new TransferModel[size];
        }
    };
    private T naviBaseModel;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public T getNaviBaseModel() {
        return this.naviBaseModel;
    }

    public void setNaviBaseModel(T naviBaseModel) {
        this.naviBaseModel = naviBaseModel;
        if (naviBaseModel.getCallbackId() == 0) {
            naviBaseModel.setCallbackId(naviBaseModel.genRandomId());
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.naviBaseModel.getClass().getName());
        dest.writeParcelable(this.naviBaseModel, flags);
    }

    public TransferModel() {
    }

    protected TransferModel(Parcel in) {
        String readString = in.readString();
        try {
            this.naviBaseModel = (T) in.readParcelable(Class.forName(readString).getClassLoader());
        } catch (Exception unused) {
            Log.w("jtag", "createTransferModel from parcel cls:" + readString);
        }
    }

    public void readFromParcel(Parcel in) {
        String readString = in.readString();
        try {
            this.naviBaseModel = (T) in.readParcelable(Class.forName(readString).getClassLoader());
        } catch (Exception unused) {
            Log.w("jtag", "createTransferModel from parcel cls:" + readString);
        }
    }
}