package com.geely.lib.oneosapi.gesture;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class GestureModel implements Parcelable {
    public static final Parcelable.Creator<GestureModel> CREATOR = new Parcelable.Creator<GestureModel>() { // from class: com.geely.lib.oneosapi.gesture.GestureModel.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public GestureModel createFromParcel(Parcel source) {
            return new GestureModel(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public GestureModel[] newArray(int size) {
            return new GestureModel[size];
        }
    };
    private String gesture;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public GestureModel() {
    }

    public GestureModel(String gesture) {
        this.gesture = gesture;
    }

    public String getGesture() {
        return this.gesture;
    }

    public void setGesture(String gesture) {
        this.gesture = gesture;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.gesture);
    }

    protected GestureModel(Parcel in) {
        this.gesture = in.readString();
    }

    public void readFromParcel(Parcel in) {
        this.gesture = in.readString();
    }

    public String toString() {
        return "GestureModel{gesture='" + this.gesture + "'}";
    }
}