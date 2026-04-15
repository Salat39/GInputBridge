package com.ecarx.xui.adaptapi.car.hev;

import com.ecarx.xui.adaptapi.FunctionStatus;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

public interface ITripData {
    public static final int AUTO_RESET_OPTION_4_HOURS = 612369153;
    public static final int AUTO_RESET_OPTION_CHARGING = 612369154;
    public static final int AUTO_RESET_OPTION_PARKING = 612369156;
    public static final int AUTO_RESET_OPTION_PARKING_OIL = 612369155;
    public static final int RESET_OPTION_AUTO = 612368641;
    public static final int RESET_OPTION_MANUAL = 612368642;
    public static final int TRIP_AIR_CONDITION_PERCENTAGE = 12294;
    public static final int TRIP_COM_UI_SWITCH_TYPE_1 = 612370177;
    public static final int TRIP_COM_UI_SWITCH_TYPE_2 = 612370178;
    public static final int TRIP_COM_UI_SWITCH_TYPE_3 = 612370179;
    public static final int TRIP_COM_UI_SWITCH_TYPE_4 = 612370180;
    public static final int TRIP_COM_UI_SWITCH_TYPE_5 = 612370181;
    public static final int TRIP_COM_UI_SWITCH_TYPE_6 = 612370182;
    public static final int TRIP_DC_ALL = 612369926;
    public static final int TRIP_DC_AVERAGE_FUEL_CONSUMPTION = 612369924;
    public static final int TRIP_DC_AVERAGE_POWER_CONSUMPTION = 612369925;
    public static final int TRIP_DC_AVERAGE_SPEED = 612369922;
    public static final int TRIP_DC_SUBTOTAL_MILEAGE = 612369921;
    public static final int TRIP_DC_TRAVEL_TIME = 612369923;
    public static final int TRIP_DI_AVG_ENE_CONSUMPTION = 8195;
    public static final int TRIP_DI_AVG_EN_CONSUMPTION_ARRAY_100KM = 20484;
    public static final int TRIP_DI_AVG_EN_CONSUMPTION_ARRAY_50KM = 20483;
    public static final int TRIP_DI_AVG_OIL_CONSUMPTION_ARRAY_100KM = 20482;
    public static final int TRIP_DI_AVG_OIL_CONSUMPTION_ARRAY_50KM = 20481;
    public static final int TRIP_DI_AVG_SPEED = 8194;
    public static final int TRIP_DI_INSTANT_ENE_CONSUMPTION = 8196;
    public static final int TRIP_DI_TOTAL_REGENERATION = 8193;
    public static final int TRIP_DI_TPTF_REPORT_ARRAY_SCENE_1 = 24577;
    public static final int TRIP_DI_TPTF_REPORT_ARRAY_SCENE_2 = 24578;
    public static final int TRIP_DI_TPTF_REPORT_ARRAY_SCENE_3 = 24579;
    public static final int TRIP_DI_TPTF_REPORT_ARRAY_SCENE_4 = 24580;
    public static final int TRIP_ED_BATTERY_CLIMATE_PERCENTAGE = 12290;
    public static final int TRIP_ED_CABIN_CLIMATE_PERCENTAGE = 12291;
    public static final int TRIP_ED_ENTERTAINMENT_PERCENTAGE = 12293;
    public static final int TRIP_ED_LIGHT_PERCENTAGE = 12292;
    public static final int TRIP_ED_OTHER_PERCENTAGE = 12543;
    public static final int TRIP_ED_PROPULSION_PERCENTAGE = 12289;
    public static final int TRIP_ED_SOUND_PERCENTAGE = 12295;
    public static final int TRIP_FUNC_AIRCDNEGY_DISTBN = 612370944;
    public static final int TRIP_FUNC_AUTO_RESET_OPTION = 612369152;
    public static final int TRIP_FUNC_AVERAGE_CONSUME_100 = 612372480;
    public static final int TRIP_FUNC_AVERAGE_CONSUME_50 = 612371968;
    public static final int TRIP_FUNC_AVERAGE_EN_CONSUME_100 = 612372992;
    public static final int TRIP_FUNC_AVERAGE_EN_CONSUME_50 = 612372736;
    public static final int TRIP_FUNC_BATTTHERMEGY_DISTBN = 612371200;
    public static final int TRIP_FUNC_CURRENT_TRIP_RESET = 612370432;
    public static final int TRIP_FUNC_DIM_UI_SWITCH = 612370176;
    public static final int TRIP_FUNC_DRIVING_COMPUTER = 612369920;
    public static final int TRIP_FUNC_DRVREGY_DISTBN = 612370688;
    public static final int TRIP_FUNC_ENERGY_RESET = 612371712;
    public static final int TRIP_FUNC_OTHEREGY_DISTBN = 612371456;
    public static final int TRIP_FUNC_RESET = 612368896;
    public static final int TRIP_FUNC_RESET_OPTION = 612368640;
    public static final int TRIP_FUNC_TRIP_RNG_SWT = 612373248;
    public static final int TRIP_FUNC_TRIP_RNG_SWT_100KM = 612373250;
    public static final int TRIP_FUNC_TRIP_RNG_SWT_50KM = 612373249;
    public static final int TRIP_INFO_TYPE_AVG_CONSUMPTION_ARRAY = 20480;
    public static final int TRIP_INFO_TYPE_AVG_ENERGY = 4096;
    public static final int TRIP_INFO_TYPE_AVG_ENERGY_100 = 16384;
    public static final int TRIP_INFO_TYPE_DEFAULT = 0;
    public static final int TRIP_INFO_TYPE_DRIVING_INFO = 8192;
    public static final int TRIP_INFO_TYPE_ENERGY_DISTRIBUTION = 12288;
    public static final int TRIP_INFO_TYPE_TPTF_REPORT_ARRAY = 24576;
    public static final int TRIP_TYPE_ARRAY = 2;
    public static final int TRIP_TYPE_DEFAULT = 0;
    public static final int TRIP_TYPE_TRIP2 = 1;

    @Retention(RetentionPolicy.SOURCE)
    public @interface AutoResetTripOption {
    }

    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface ComputerInfoIds {
    }

    public interface IAverageConsumption {
        float[] getAverageConsumptionEn100km();

        float[] getAverageConsumptionEn50km();

        float[] getAverageConsumptionOil100km();

        float[] getAverageConsumptionOil50km();

        int getLastedUpdateItemFlag();
    }

    public interface IAvgEnergyInfo extends ITripInfo {
        float getAvgEleConsumption();

        float getAvgEnergyFeedback();

        float getAvgFuelConsumption();
    }

    public interface IBookTravelListener {
        void onBookTravelChanged(int i, int i2, List<BookTravelDaily> list);

        void onBookTravelError();

        void onBookTravelSetResponse(int i);
    }

    public interface IDrivingInfo extends ITripInfo {
        float getEleDrivingPercentage();

        int getTripDistance();

        int getTripDistanceByEle();

        int getTripDistanceByFuel();

        int getTripDistanceInCurrentDay();

        long getTripDuration();

        float getTripEleConsumption();

        float getTripFuelConsumption();
    }

    public interface ITPTFReport {
        int getLastedUpdateItemFlag();

        int[] getTPTFReportDataScene1();

        int[] getTPTFReportDataScene2();

        int[] getTPTFReportDataScene3();

        int[] getTPTFReportDataScene4();
    }

    public interface ITPTFReportListener {
        void onReportUpdate(ITPTFReport iTPTFReport);
    }

    public interface ITripInfo {
        boolean containsInfoId(int i);

        int[] getContainsInfoIds();

        int getFrequencyUnit();

        int getInfoType();

        float getInfoValue(int i);

        float[] getInfoValues(int i);

        int getTripType();
    }

    public interface ITripInfoListener extends ITripListener {
        void onTripDataReset(int i);

        void onTripInfoUpdate(ITripInfo iTripInfo);
    }

    @Deprecated
    public interface ITripListener {
        void onAvgEnergyInfoUpdate(IAvgEnergyInfo iAvgEnergyInfo);

        void onDrivingInfoUpdate(IDrivingInfo iDrivingInfo);
    }

    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface InfoIds {
    }

    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface InfoType {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ResetTripOption {
    }

    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface SceneType {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface TripComUISwitchType {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface TripFunction {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface TripRngSwtType {
    }

    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface TripType {
    }

    boolean addBookTravelListener(IBookTravelListener iBookTravelListener);

    IAvgEnergyInfo getLatestAvgEnergyInfo();

    IDrivingInfo getLatestDrivingInfo();

    ITripInfo getLatestTripInfo(int i, int i2);

    int[] getSupportedUpdateFrequencyUnit();

    ITPTFReport getTPTFReportInfo();

    ITripInfo[] getTripInfo(int i, int i2);

    @Deprecated
    int getUpdateFrequencyUnit();

    FunctionStatus isTripDataSupported();

    FunctionStatus isTripDataSupported(int i);

    FunctionStatus isTripDataSupported(int i, int i2);

    void registerTPTFReportListener(ITPTFReportListener iTPTFReportListener);

    void registerTripListener(int i, ITripListener iTripListener);

    void registerTripListener(ITripListener iTripListener);

    boolean removeBookTravelListener(IBookTravelListener iBookTravelListener);

    boolean reqBookTravel();

    boolean setBookTravelUsed(BookTravelUsedInfo bookTravelUsedInfo);

    @Deprecated
    void setUpdateFrequencyUnit(int i);

    void unregisterTPTFReportListener(ITPTFReportListener iTPTFReportListener);

    void unregisterTripListener(ITripListener iTripListener);
}
