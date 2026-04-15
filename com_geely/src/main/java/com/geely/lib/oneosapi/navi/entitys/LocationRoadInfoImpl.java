package com.geely.lib.oneosapi.navi.entitys;

import android.text.TextUtils;
import android.util.Log;
import com.geely.lib.oneosapi.navi.utils.Utils;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class LocationRoadInfoImpl implements ILocationRoadInfo {
    private String fromPoiAddr;
    private double fromPoiLatitude;
    private double fromPoiLongitude;
    private String fromPoiName;
    private String toPoiAddr;
    private double toPoiLatitude;
    private double toPoiLongitude;
    private String toPoiName;

    public LocationRoadInfoImpl(String json) {
        if (TextUtils.isEmpty(json)) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(json);
            setToPoiName(jSONObject.getString("ToPoiName"));
            setToPoiAddr(jSONObject.getString("ToPoiAddr"));
            setToPoiLatitude(Utils.string2double(jSONObject.getString("ToPoiLatitude"), -1.0d));
            setToPoiLongitude(Utils.string2double(jSONObject.getString("ToPoiLongitude"), -1.0d));
            setFromPoiName(jSONObject.getString("FromPoiName"));
            setFromPoiAddr(jSONObject.getString("FromPoiAddr"));
            setFromPoiLatitude(Utils.string2double(jSONObject.getString("FromPoiLatitude"), -1.0d));
            setFromPoiLongitude(Utils.string2double(jSONObject.getString("FromPoiLongitude"), -1.0d));
            Log.i("", toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void setToPoiName(String toPoiName) {
        this.toPoiName = toPoiName;
    }

    public void setToPoiAddr(String toPoiAddr) {
        this.toPoiAddr = toPoiAddr;
    }

    public void setToPoiLatitude(double toPoiLatitude) {
        this.toPoiLatitude = toPoiLatitude;
    }

    public void setToPoiLongitude(double toPoiLongitude) {
        this.toPoiLongitude = toPoiLongitude;
    }

    public void setFromPoiName(String fromPoiName) {
        this.fromPoiName = fromPoiName;
    }

    public void setFromPoiAddr(String fromPoiAddr) {
        this.fromPoiAddr = fromPoiAddr;
    }

    public void setFromPoiLongitude(double fromPoiLongitude) {
        this.fromPoiLongitude = fromPoiLongitude;
    }

    public void setFromPoiLatitude(double fromPoiLatitude) {
        this.fromPoiLatitude = fromPoiLatitude;
    }

    @Override // com.geely.lib.oneosapi.navi.entitys.ILocationRoadInfo
    public String getToPoiName() {
        return this.toPoiName;
    }

    @Override // com.geely.lib.oneosapi.navi.entitys.ILocationRoadInfo
    public String getToPoiAddr() {
        return this.toPoiAddr;
    }

    @Override // com.geely.lib.oneosapi.navi.entitys.ILocationRoadInfo
    public double getToPoiLatitude() {
        return this.toPoiLatitude;
    }

    @Override // com.geely.lib.oneosapi.navi.entitys.ILocationRoadInfo
    public double getToPoiLongitude() {
        return this.toPoiLongitude;
    }

    @Override // com.geely.lib.oneosapi.navi.entitys.ILocationRoadInfo
    public String getFromPoiName() {
        return this.fromPoiName;
    }

    @Override // com.geely.lib.oneosapi.navi.entitys.ILocationRoadInfo
    public String getFromPoiAddr() {
        return this.fromPoiAddr;
    }

    @Override // com.geely.lib.oneosapi.navi.entitys.ILocationRoadInfo
    public double getFromPoiLongitude() {
        return this.fromPoiLongitude;
    }

    @Override // com.geely.lib.oneosapi.navi.entitys.ILocationRoadInfo
    public double getFromPoiLatitude() {
        return this.fromPoiLatitude;
    }
}