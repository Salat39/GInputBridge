package com.geely.lib.oneosapi.input;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class KeyCodeEvent implements Parcelable {
    public static final int ACTION_CANCEL = 3;
    public static final int ACTION_DOWN = 0;
    public static final int ACTION_UP = 1;
    public static final Parcelable.Creator<KeyCodeEvent> CREATOR = new Parcelable.Creator<KeyCodeEvent>() { // from class: com.geely.lib.oneosapi.input.KeyCodeEvent.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public KeyCodeEvent createFromParcel(Parcel in) {
            return new KeyCodeEvent(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public KeyCodeEvent[] newArray(int size) {
            return new KeyCodeEvent[size];
        }
    };
    private int action;
    private int keyCode;
    private int softKeyFunction;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public KeyCodeEvent() {
    }

    public KeyCodeEvent(int keyCode, int action) {
        this.keyCode = keyCode;
        this.action = action;
    }

    public int getKeyCode() {
        return this.keyCode;
    }

    public void setKeyCode(int keyCode) {
        this.keyCode = keyCode;
    }

    public int getSoftKeyFunction() {
        return this.softKeyFunction;
    }

    public void setSoftKeyFunction(int softKeyFunction) {
        this.softKeyFunction = softKeyFunction;
    }

    public int getAction() {
        return this.action;
    }

    public void setAction(int action) {
        this.action = action;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.keyCode);
        dest.writeInt(this.softKeyFunction);
        dest.writeInt(this.action);
    }

    protected KeyCodeEvent(Parcel in) {
        this.keyCode = in.readInt();
        this.softKeyFunction = in.readInt();
        this.action = in.readInt();
    }
}