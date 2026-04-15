package com.geely.lib.oneosapi.navi.model.client;

import android.os.Parcel;
import android.os.Parcelable;
import com.geely.lib.oneosapi.navi.model.base.NaviBaseModel;
import com.geely.lib.oneosapi.navi.model.base.NaviProtocolID;

/* loaded from: classes.dex */
public class NaviEventConfig extends NaviBaseModel implements Parcelable {
    public static final Parcelable.Creator<NaviEventConfig> CREATOR = new Parcelable.Creator<NaviEventConfig>() { // from class: com.geely.lib.oneosapi.navi.model.client.NaviEventConfig.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NaviEventConfig createFromParcel(Parcel source) {
            return new NaviEventConfig(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NaviEventConfig[] newArray(int size) {
            return new NaviEventConfig[size];
        }
    };
    public static final int NAVI_GUIDE_EVENT_ON = 1;
    public static final int NAVI_LANES_EVENT_ON = 2;
    public static final int NAVI_ROAD_CONDITION_EVENT_ON = 8;
    public static final int NAVI_SPEED_LIMMITED_EVENT_ON = 4;
    private long highFrequencyEventConfig;

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public NaviEventConfig() {
        setProtocolID(NaviProtocolID.NTF_NAVI_EVENT_CONFIG);
    }

    public long getHighFrequencyEventConfig() {
        return this.highFrequencyEventConfig;
    }

    public void setHighFrequencyEventConfig(long highFrequencyEventConfig) {
        this.highFrequencyEventConfig = highFrequencyEventConfig;
    }

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel, android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeLong(this.highFrequencyEventConfig);
    }

    protected NaviEventConfig(Parcel in) {
        super(in);
        this.highFrequencyEventConfig = in.readLong();
    }

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel
    public String toString() {
        return "NaviEventConfig{highFrequencyEventConfig=" + this.highFrequencyEventConfig + "{base=" + super.toString() + "}; }";
    }
}