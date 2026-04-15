package com.geely.lib.oneosapi.navi.model.client;

import android.os.Parcel;
import android.os.Parcelable;
import com.geely.lib.oneosapi.navi.model.base.NaviBaseModel;
import com.geely.lib.oneosapi.navi.model.base.NaviProtocolID;

/* loaded from: classes.dex */
public class RoutePlanStrategyBean extends NaviBaseModel implements Parcelable {
    public static final Parcelable.Creator<RoutePlanStrategyBean> CREATOR = new Parcelable.Creator<RoutePlanStrategyBean>() { // from class: com.geely.lib.oneosapi.navi.model.client.RoutePlanStrategyBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RoutePlanStrategyBean createFromParcel(Parcel source) {
            return new RoutePlanStrategyBean(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RoutePlanStrategyBean[] newArray(int size) {
            return new RoutePlanStrategyBean[size];
        }
    };
    private int routePlanStrategy;

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public RoutePlanStrategyBean(int routePlanStrategy) {
        setProtocolID(NaviProtocolID.SETTING_SET_ROUTE_PLAN_STRATEGY);
        this.routePlanStrategy = routePlanStrategy;
    }

    public int getRoutePlanStrategy() {
        return this.routePlanStrategy;
    }

    public void setRoutePlanStrategy(int routePlanStrategy) {
        this.routePlanStrategy = routePlanStrategy;
    }

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel, android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeInt(this.routePlanStrategy);
    }

    protected RoutePlanStrategyBean(Parcel in) {
        super(in);
        this.routePlanStrategy = in.readInt();
    }

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel
    public String toString() {
        super.toString();
        return "RoutePlanStrategyBean{routePlanStrategy=" + this.routePlanStrategy + '}';
    }
}