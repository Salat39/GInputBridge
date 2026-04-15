package com.ecarx.xui.adaptapi.diminteraction;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: C:\Users\Andrei\AppData\Local\Temp\jadx-7518071738844169467\classes.dex */
public class NaviStatus implements Parcelable {
    public static final Parcelable.Creator<NaviStatus> CREATOR = new Parcelable.Creator<NaviStatus>() { // from class: com.ecarx.xui.adaptapi.diminteraction.NaviStatus.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NaviStatus createFromParcel(Parcel parcel) {
            return new NaviStatus(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NaviStatus[] newArray(int i) {
            return new NaviStatus[i];
        }
    };
    private boolean isYawing;
    private int status;

    public NaviStatus(int i, boolean z) {
        this.status = i;
        this.isYawing = z;
    }

    protected NaviStatus(Parcel parcel) {
        this.status = parcel.readInt();
        this.isYawing = parcel.readInt() == 1;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int getStatus() {
        return this.status;
    }

    public boolean isYawing() {
        return this.isYawing;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.status);
        parcel.writeInt(this.isYawing ? 1 : 0);
    }
}