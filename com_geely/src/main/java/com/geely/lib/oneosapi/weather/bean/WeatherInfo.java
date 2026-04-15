package com.geely.lib.oneosapi.weather.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class WeatherInfo implements Parcelable {
    public static final Parcelable.Creator<WeatherInfo> CREATOR = new Parcelable.Creator<WeatherInfo>() { // from class: com.geely.lib.oneosapi.weather.bean.WeatherInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WeatherInfo createFromParcel(Parcel in) {
            return new WeatherInfo(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WeatherInfo[] newArray(int size) {
            return new WeatherInfo[size];
        }
    };
    private String addTime;
    private String allMaxTemp;
    private String allMinTemp;
    private String aqi;
    private String atmoPres;
    private String carbonMonoxide;
    private String cloudCover;
    private String currentPrecipitation;
    private String currentRelaHumid;
    private String currentTemp;
    private String currentWinDir;
    private String currentWind;
    private String dewPointTemp;
    private String nitrogenDioxide;
    private String ozone;
    private String pm;
    private String pmt;
    private String sensiTemp;
    private String snowDepth;
    private String sulfurDioxide;
    private String uvInten;
    private String visibility;
    private String weathPheno;
    private String windDirection;
    private String windSpeed;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    protected WeatherInfo(Parcel in) {
        this.addTime = in.readString();
        this.aqi = in.readString();
        this.atmoPres = in.readString();
        this.cloudCover = in.readString();
        this.carbonMonoxide = in.readString();
        this.currentPrecipitation = in.readString();
        this.currentRelaHumid = in.readString();
        this.currentTemp = in.readString();
        this.currentWinDir = in.readString();
        this.currentWind = in.readString();
        this.dewPointTemp = in.readString();
        this.nitrogenDioxide = in.readString();
        this.ozone = in.readString();
        this.pm = in.readString();
        this.pmt = in.readString();
        this.sensiTemp = in.readString();
        this.snowDepth = in.readString();
        this.sulfurDioxide = in.readString();
        this.uvInten = in.readString();
        this.visibility = in.readString();
        this.weathPheno = in.readString();
        this.windDirection = in.readString();
        this.windSpeed = in.readString();
        this.allMaxTemp = in.readString();
        this.allMinTemp = in.readString();
    }

    public String getAllMaxTemp() {
        return this.allMaxTemp;
    }

    public void setAllMaxTemp(String allMaxTemp) {
        this.allMaxTemp = allMaxTemp;
    }

    public String getAllMinTemp() {
        return this.allMinTemp;
    }

    public void setAllMinTemp(String allMinTemp) {
        this.allMinTemp = allMinTemp;
    }

    public String getAddTime() {
        return this.addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public String getAqi() {
        return this.aqi;
    }

    public void setAqi(String aqi) {
        this.aqi = aqi;
    }

    public String getAtmoPres() {
        return this.atmoPres;
    }

    public void setAtmoPres(String atmoPres) {
        this.atmoPres = atmoPres;
    }

    public String getCloudCover() {
        return this.cloudCover;
    }

    public void setCloudCover(String cloudCover) {
        this.cloudCover = cloudCover;
    }

    public String getCarbonMonoxide() {
        return this.carbonMonoxide;
    }

    public void setCarbonMonoxide(String carbonMonoxide) {
        this.carbonMonoxide = carbonMonoxide;
    }

    public String getCurrentPrecipitation() {
        return this.currentPrecipitation;
    }

    public void setCurrentPrecipitation(String currentPrecipitation) {
        this.currentPrecipitation = currentPrecipitation;
    }

    public String getCurrentRelaHumid() {
        return this.currentRelaHumid;
    }

    public void setCurrentRelaHumid(String currentRelaHumid) {
        this.currentRelaHumid = currentRelaHumid;
    }

    public String getCurrentTemp() {
        return this.currentTemp;
    }

    public void setCurrentTemp(String currentTemp) {
        this.currentTemp = currentTemp;
    }

    public String getCurrentWinDir() {
        return this.currentWinDir;
    }

    public void setCurrentWinDir(String currentWinDir) {
        this.currentWinDir = currentWinDir;
    }

    public String getCurrentWind() {
        return this.currentWind;
    }

    public void setCurrentWind(String currentWind) {
        this.currentWind = currentWind;
    }

    public String getDewPointTemp() {
        return this.dewPointTemp;
    }

    public void setDewPointTemp(String dewPointTemp) {
        this.dewPointTemp = dewPointTemp;
    }

    public String getNitrogenDioxide() {
        return this.nitrogenDioxide;
    }

    public void setNitrogenDioxide(String nitrogenDioxide) {
        this.nitrogenDioxide = nitrogenDioxide;
    }

    public String getOzone() {
        return this.ozone;
    }

    public void setOzone(String ozone) {
        this.ozone = ozone;
    }

    public String getPm() {
        return this.pm;
    }

    public void setPm(String pm) {
        this.pm = pm;
    }

    public String getPmt() {
        return this.pmt;
    }

    public void setPmt(String pmt) {
        this.pmt = pmt;
    }

    public String getSensiTemp() {
        return this.sensiTemp;
    }

    public void setSensiTemp(String sensiTemp) {
        this.sensiTemp = sensiTemp;
    }

    public String getSnowDepth() {
        return this.snowDepth;
    }

    public void setSnowDepth(String snowDepth) {
        this.snowDepth = snowDepth;
    }

    public String getSulfurDioxide() {
        return this.sulfurDioxide;
    }

    public void setSulfurDioxide(String sulfurDioxide) {
        this.sulfurDioxide = sulfurDioxide;
    }

    public String getUvInten() {
        return this.uvInten;
    }

    public void setUvInten(String uvInten) {
        this.uvInten = uvInten;
    }

    public String getVisibility() {
        return this.visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public String getWeathPheno() {
        return this.weathPheno;
    }

    public void setWeathPheno(String weathPheno) {
        this.weathPheno = weathPheno;
    }

    public String getWindDirection() {
        return this.windDirection;
    }

    public void setWindDirection(String windDirection) {
        this.windDirection = windDirection;
    }

    public String getWindSpeed() {
        return this.windSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.addTime);
        dest.writeString(this.aqi);
        dest.writeString(this.atmoPres);
        dest.writeString(this.cloudCover);
        dest.writeString(this.carbonMonoxide);
        dest.writeString(this.currentPrecipitation);
        dest.writeString(this.currentRelaHumid);
        dest.writeString(this.currentTemp);
        dest.writeString(this.currentWinDir);
        dest.writeString(this.currentWind);
        dest.writeString(this.dewPointTemp);
        dest.writeString(this.nitrogenDioxide);
        dest.writeString(this.ozone);
        dest.writeString(this.pm);
        dest.writeString(this.pmt);
        dest.writeString(this.sensiTemp);
        dest.writeString(this.snowDepth);
        dest.writeString(this.sulfurDioxide);
        dest.writeString(this.uvInten);
        dest.writeString(this.visibility);
        dest.writeString(this.weathPheno);
        dest.writeString(this.windDirection);
        dest.writeString(this.windSpeed);
        dest.writeString(this.allMaxTemp);
        dest.writeString(this.allMinTemp);
    }
}