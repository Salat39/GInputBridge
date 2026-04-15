package com.geely.lib.oneosapi.navi.model.client;

import android.os.Parcel;
import android.os.Parcelable;
import com.geely.lib.oneosapi.navi.model.base.NaviBaseModel;
import com.geely.lib.oneosapi.navi.model.base.NaviProtocolID;
import com.geely.lib.oneosapi.navi.model.base.PoiInfo;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class NaviRoutePlan extends NaviBaseModel implements Parcelable {
    public static final int ACTION_JUST_INFO = 2;
    public static final int ACTION_NAVI = 1;
    public static final int ACTION_ROUTE_PLAN = 0;
    public static final Parcelable.Creator<NaviRoutePlan> CREATOR = new Parcelable.Creator<NaviRoutePlan>() { // from class: com.geely.lib.oneosapi.navi.model.client.NaviRoutePlan.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NaviRoutePlan createFromParcel(Parcel source) {
            return new NaviRoutePlan(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NaviRoutePlan[] newArray(int size) {
            return new NaviRoutePlan[size];
        }
    };
    private int action;
    private PoiInfo destPoiInfo;
    private NaviViaRoadBean naviViaRoadBean;
    private PoiInfo startPoiInfo;
    private int strategy;
    private List<PoiInfo> viaPoiInfos;

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public NaviRoutePlan(PoiInfo destPoiInfo) {
        this.viaPoiInfos = new ArrayList();
        this.destPoiInfo = destPoiInfo;
        this.strategy = -1;
        setProtocolID(NaviProtocolID.NAVI_OP_ROUTE_PLAN);
    }

    public NaviRoutePlan(PoiInfo startPoiInfo, PoiInfo destPoiInfo) {
        this.viaPoiInfos = new ArrayList();
        this.destPoiInfo = destPoiInfo;
        this.startPoiInfo = startPoiInfo;
        this.strategy = -1;
        setProtocolID(NaviProtocolID.NAVI_OP_ROUTE_PLAN);
    }

    public PoiInfo getStartPoiInfo() {
        return this.startPoiInfo;
    }

    public void setStartPoiInfo(PoiInfo startPoiInfo) {
        this.startPoiInfo = startPoiInfo;
    }

    public int getAction() {
        return this.action;
    }

    public void setAction(int action) {
        this.action = action;
    }

    public int getStrategy() {
        return this.strategy;
    }

    public void setStrategy(int strategy) {
        this.strategy = strategy;
    }

    public PoiInfo getDestPoiInfo() {
        return this.destPoiInfo;
    }

    public void setDestPoiInfo(PoiInfo destPoiInfo) {
        this.destPoiInfo = destPoiInfo;
    }

    public List<PoiInfo> getViaPoiInfos() {
        return this.viaPoiInfos;
    }

    public void setViaPoiInfos(List<PoiInfo> viaPoiInfos) {
        this.viaPoiInfos = viaPoiInfos;
    }

    public NaviViaRoadBean getNaviViaRoadBean() {
        return this.naviViaRoadBean;
    }

    public void setNaviViaRoadBean(NaviViaRoadBean naviViaRoadBean) {
        this.naviViaRoadBean = naviViaRoadBean;
    }

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel, android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeInt(this.action);
        dest.writeInt(this.strategy);
        dest.writeParcelable(this.destPoiInfo, flags);
        dest.writeParcelable(this.startPoiInfo, flags);
        dest.writeParcelable(this.naviViaRoadBean, flags);
        dest.writeTypedList(this.viaPoiInfos);
    }

    protected NaviRoutePlan(Parcel in) {
        super(in);
        this.viaPoiInfos = new ArrayList();
        this.action = in.readInt();
        this.strategy = in.readInt();
        this.destPoiInfo = (PoiInfo) in.readParcelable(PoiInfo.class.getClassLoader());
        this.startPoiInfo = (PoiInfo) in.readParcelable(PoiInfo.class.getClassLoader());
        this.naviViaRoadBean = (NaviViaRoadBean) in.readParcelable(NaviViaRoadBean.class.getClassLoader());
        this.viaPoiInfos = in.createTypedArrayList(PoiInfo.CREATOR);
    }

    @Override // com.geely.lib.oneosapi.navi.model.base.NaviBaseModel
    public String toString() {
        super.toString();
        return "NaviRoutePlan{action=" + this.action + ", strategy=" + this.strategy + ", destPoiInfo=" + this.destPoiInfo + ", startPoiInfo=" + this.startPoiInfo + ", viaPoiInfos=" + this.viaPoiInfos + ", naviViaRoadBean=" + this.naviViaRoadBean + '}';
    }
}