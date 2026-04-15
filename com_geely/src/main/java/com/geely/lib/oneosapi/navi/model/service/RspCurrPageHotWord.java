package com.geely.lib.oneosapi.navi.model.service;

import android.os.Parcel;
import android.os.Parcelable;
import com.geely.lib.oneosapi.navi.model.base.NaviBaseModel;

/* loaded from: classes.dex */
public class RspCurrPageHotWord extends NaviBaseModel implements Parcelable {
    public static final Parcelable.Creator<RspCurrPageHotWord> CREATOR = new Parcelable.Creator<RspCurrPageHotWord>() { // from class: com.geely.lib.oneosapi.navi.model.service.RspCurrPageHotWord.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RspCurrPageHotWord createFromParcel(Parcel source) {
            return new RspCurrPageHotWord(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RspCurrPageHotWord[] newArray(int size) {
            return new RspCurrPageHotWord[size];
        }
    };
    private String hotWord;

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public void setHotWord(String hotWord) {
        this.hotWord = hotWord;
    }

    public String getHotWord() {
        return this.hotWord;
    }

    public RspCurrPageHotWord() {
    }

    protected RspCurrPageHotWord(Parcel parcel) {
        super(parcel);
        this.hotWord = parcel.readString();
    }

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel, android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(this.hotWord);
    }

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel
    public String toString() {
        return "RspCurrPageHotWord{hotWord=" + this.hotWord + "{base=" + super.toString() + "}; }";
    }
}