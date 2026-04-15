package com.geely.lib.oneosapi.launcher.bean;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class ShortcutCompat implements Parcelable {
    public static final Parcelable.Creator<ShortcutCompat> CREATOR = new Parcelable.Creator<ShortcutCompat>() { // from class: com.geely.lib.oneosapi.launcher.bean.ShortcutCompat.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ShortcutCompat createFromParcel(Parcel in) {
            return new ShortcutCompat(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ShortcutCompat[] newArray(int size) {
            return new ShortcutCompat[size];
        }
    };
    private String shortcutId;
    private Intent shortcutIntent;
    private String shortcutTitle;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public ShortcutCompat() {
    }

    protected ShortcutCompat(Parcel in) {
        this.shortcutId = in.readString();
        this.shortcutTitle = in.readString();
        this.shortcutIntent = (Intent) in.readParcelable(Intent.class.getClassLoader());
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.shortcutId);
        dest.writeString(this.shortcutTitle);
        dest.writeParcelable(this.shortcutIntent, flags);
    }

    public String getShortcutId() {
        return this.shortcutId;
    }

    public void setShortcutId(String shortcutId) {
        this.shortcutId = shortcutId;
    }

    public String getShortcutTitle() {
        return this.shortcutTitle;
    }

    public Intent getShortcutIntent() {
        return this.shortcutIntent;
    }

    public void setShortcutTitle(String shortcutTitle) {
        this.shortcutTitle = shortcutTitle;
    }

    public void setShortcutIntent(Intent shortcutIntent) {
        this.shortcutIntent = shortcutIntent;
    }
}