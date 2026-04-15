package com.geely.lib.oneosapi.mediacenter.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class Frequency implements Parcelable, Comparable<Frequency> {
    public static final Parcelable.Creator<Frequency> CREATOR = new Parcelable.Creator<Frequency>() { // from class: com.geely.lib.oneosapi.mediacenter.bean.Frequency.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Frequency createFromParcel(Parcel source) {
            return new Frequency(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Frequency[] newArray(int size) {
            return new Frequency[size];
        }
    };
    private int band;
    private boolean collection;
    private String ensembleName;
    private String ensembleRdsPty;
    private int frequency;
    private int iconId;
    private String selector;
    private String serviceName;
    private int signalQuality;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public Frequency() {
    }

    public Frequency(int frequency, int band, String ensembleName, String serviceName, String ensembleRdsPty, int iconId, int signalQuality, String selector) {
        this.frequency = frequency;
        this.band = band;
        this.ensembleName = ensembleName;
        this.serviceName = serviceName;
        this.ensembleRdsPty = ensembleRdsPty;
        this.iconId = iconId;
        this.signalQuality = signalQuality;
        this.selector = selector;
    }

    public Frequency(int frequency, int band, String ensembleName, String serviceName, String ensembleRdsPty, int iconId, int signalQuality, String selector, boolean collection) {
        this.frequency = frequency;
        this.band = band;
        this.ensembleName = ensembleName;
        this.serviceName = serviceName;
        this.ensembleRdsPty = ensembleRdsPty;
        this.iconId = iconId;
        this.signalQuality = signalQuality;
        this.selector = selector;
        this.collection = collection;
    }

    public boolean isCollection() {
        return this.collection;
    }

    public void setCollection(boolean collection) {
        this.collection = collection;
    }

    public int getFrequency() {
        return this.frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public int getBand() {
        return this.band;
    }

    public void setBand(int band) {
        this.band = band;
    }

    public String getEnsembleName() {
        return this.ensembleName;
    }

    public void setEnsembleName(String ensembleName) {
        this.ensembleName = ensembleName;
    }

    public String getServiceName() {
        return this.serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getEnsembleRdsPty() {
        return this.ensembleRdsPty;
    }

    public void setEnsembleRdsPty(String ensembleRdsPty) {
        this.ensembleRdsPty = ensembleRdsPty;
    }

    public int getIconId() {
        return this.iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }

    public int getSignalQuality() {
        return this.signalQuality;
    }

    public void setSignalQuality(int signalQuality) {
        this.signalQuality = signalQuality;
    }

    public String getSelector() {
        return this.selector;
    }

    public void setSelector(String selector) {
        this.selector = selector;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.frequency);
        parcel.writeInt(this.band);
        parcel.writeString(this.ensembleName);
        parcel.writeString(this.serviceName);
        parcel.writeString(this.ensembleRdsPty);
        parcel.writeInt(this.iconId);
        parcel.writeInt(this.signalQuality);
        parcel.writeString(this.selector);
        parcel.writeInt(this.collection ? 1 : 0);
    }

    public void readFromParcel(Parcel in) {
        this.frequency = in.readInt();
        this.band = in.readInt();
        this.ensembleName = in.readString();
        this.serviceName = in.readString();
        this.ensembleRdsPty = in.readString();
        this.iconId = in.readInt();
        this.signalQuality = in.readInt();
        this.selector = in.readString();
        this.collection = in.readInt() == 1;
    }

    protected Frequency(Parcel in) {
        this.frequency = in.readInt();
        this.band = in.readInt();
        this.ensembleName = in.readString();
        this.serviceName = in.readString();
        this.ensembleRdsPty = in.readString();
        this.iconId = in.readInt();
        this.signalQuality = in.readInt();
        this.selector = in.readString();
        this.collection = in.readInt() == 1;
    }

    @Override // java.lang.Comparable
    public int compareTo(Frequency o) {
        return this.frequency - o.frequency;
    }

    public String toString() {
        return "{frequency=" + this.frequency + ", band=" + this.band + ", iconId=" + this.iconId + ", ensembleName=" + this.ensembleName + ", serviceName=" + this.serviceName + ", ensembleRdsPty=" + this.ensembleRdsPty + ", signalQuality=" + this.signalQuality + ", selector=" + this.selector + ", collection=" + this.collection + '}';
    }
}