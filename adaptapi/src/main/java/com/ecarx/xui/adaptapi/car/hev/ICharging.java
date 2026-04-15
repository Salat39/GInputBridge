package com.ecarx.xui.adaptapi.car.hev;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Calendar;

public interface ICharging {
    public static final int BATTERY_MODEL_STATE_1 = 606078721;
    public static final int BATTERY_MODEL_STATE_2 = 606078722;
    public static final int BATTERY_MODEL_STATE_3 = 606078723;
    public static final int BATTERY_MODEL_STATE_4 = 606078724;
    public static final int BATT_CHRGNP_DCHRGNP_DEFAULT = 609224449;
    public static final int BATT_CHRGNP_DCHRGNP_DYNAMIC = 609224448;
    public static final int BATT_CHRGNP_DCHRGNP_NONE = 609224450;
    public static final int BATT_CHRGNP_DC_DEFAULT = 609224193;
    public static final int BATT_CHRGNP_DC_DYNAMIC = 609224192;
    public static final int BATT_CHRGNP_DC_NONE = 609224194;
    public static final int BATT_CHRGNP_DEFAULT = 609223937;
    public static final int BATT_CHRGNP_DYNAMIC = 609223936;
    public static final int BATT_CHRGNP_NONE = 609223938;
    public static final int CHARGE_FUNC_ADJUST_MAX_CURRENT = 605031168;
    public static final int CHARGE_FUNC_AUTO_DISCHARGE_LEVEL = 605161216;
    public static final int CHARGE_FUNC_BATTERY_CHARGING_CURRENT_POWER = 606080000;
    public static final int CHARGE_FUNC_BATTERY_DISCHARGING_CURRENT_POWER = 606080256;
    public static final int CHARGE_FUNC_BATTERY_SOC_CALIBRATION = 606079744;
    public static final int CHARGE_FUNC_BATTERY_STABILITY = 605031680;
    public static final int CHARGE_FUNC_BATTERY_TEMP_MAINTAIN_STATUS = 605032192;
    public static final int CHARGE_FUNC_CHARGE_IMMEDIATELY = 609222656;
    public static final int CHARGE_FUNC_CHARGE_PHEV_BATTERY_COLOR = 606079488;
    public static final int CHARGE_FUNC_CHARGING = 605028608;
    public static final int CHARGE_FUNC_CHARGING_CURRENT = 605029888;
    public static final int CHARGE_FUNC_CHARGING_CURRENT_MAX = 605030144;
    public static final int CHARGE_FUNC_CHARGING_CURRENT_MIN = 605030400;
    public static final int CHARGE_FUNC_CHARGING_CURRENT_STEP = 605030656;
    public static final int CHARGE_FUNC_CHARGING_DISCHARGING_STATE = 605225984;
    public static final int CHARGE_FUNC_CHARGING_ENERGY = 605291776;
    public static final int CHARGE_FUNC_CHARGING_ESTIMATED_TIME = 605291264;
    public static final int CHARGE_FUNC_CHARGING_PLUG_STATE = 605225472;
    public static final int CHARGE_FUNC_CHARGING_PLUG_TYPE = 605225216;
    public static final int CHARGE_FUNC_CHARGING_SOC = 605028864;
    public static final int CHARGE_FUNC_CHARGING_SOC_MAX = 605029120;
    public static final int CHARGE_FUNC_CHARGING_SOC_MIN = 605029376;
    public static final int CHARGE_FUNC_CHARGING_SOC_STEP = 605029632;
    public static final int CHARGE_FUNC_CHARGING_SPEED = 605291520;
    public static final int CHARGE_FUNC_CHARGING_STATE = 605225728;
    public static final int CHARGE_FUNC_CHARGING_WORK_CURRENT = 605291008;
    public static final int CHARGE_FUNC_CHARGING_WORK_VOLTAGE = 605290752;
    public static final int CHARGE_FUNC_DISCHARGE_STOP_BY_TARGET_STATE = 606079232;
    public static final int CHARGE_FUNC_DISCHARGING_ENETGY = 605357056;
    public static final int CHARGE_FUNC_DISCHARGING_ESTIMATED_TIME = 605356800;
    public static final int CHARGE_FUNC_DISCHARGING_SOC = 605160192;
    public static final int CHARGE_FUNC_DISCHARGING_SOC_MAX = 605160448;
    public static final int CHARGE_FUNC_DISCHARGING_SOC_MIN = 605160704;
    public static final int CHARGE_FUNC_DISCHARGING_SOC_STEP = 605160960;
    public static final int CHARGE_FUNC_DISCHARGING_SWITCH_V2L = 605159936;
    public static final int CHARGE_FUNC_DISCHARGING_SWITCH_V2V = 605159680;
    public static final int CHARGE_FUNC_DISCHARGING_WORK_CURRENT = 605356544;
    public static final int CHARGE_FUNC_DISCHARGING_WORK_VOLTAGE = 605356288;
    public static final int CHARGE_FUNC_DISTANCE_INTERVAL_MAINTAIN = 609225216;
    public static final int CHARGE_FUNC_DISTANCE_PROTECTION = 609222912;
    public static final int CHARGE_FUNC_DISTANCE_PROTECTION_UNIT = 609223168;
    public static final int CHARGE_FUNC_ENDURANCE_MILEAGE = 606079744;
    public static final int CHARGE_FUNC_EXTENDED_BATTERY_LIFE = 605031424;
    public static final int CHARGE_FUNC_EXTERNAL_CHARGING_LIGHT = 605031936;
    public static final int CHARGE_FUNC_EXTERNAL_POWER_SUPPLY = 606078976;
    public static final int CHARGE_FUNC_FUEL_TO_BATT_NOTWORK_TOAST = 609225984;
    public static final int CHARGE_FUNC_GEAR_LVL_INDCN = 609225728;
    public static final int CHARGE_FUNC_HV_BATT_ACCHRGNP = 609223936;
    public static final int CHARGE_FUNC_HV_BATT_CHRG = 609223424;
    public static final int CHARGE_FUNC_HV_BATT_CHRG_TIME = 609224960;
    public static final int CHARGE_FUNC_HV_BATT_DCCHRGNP = 609224192;
    public static final int CHARGE_FUNC_HV_BATT_DCHRGNP = 609224448;
    public static final int CHARGE_FUNC_HV_BATT_EGY_SOC = 609224704;
    public static final int CHARGE_FUNC_HV_DIS_CHRG_STS = 609223680;
    public static final int CHARGE_FUNC_MAINTAIN_BATTERY_TEMP = 605030912;
    public static final int CHARGE_FUNC_PHEV_BATTERY_CHARGING_MODE = 605357568;
    public static final int CHARGE_FUNC_PHEV_PARKING_POWER = 605358080;
    public static final int CHARGE_FUNC_PHEV_RARELY_CHARGING_MODE = 605357824;
    public static final int CHARGE_FUNC_PRE_CHARGING = 605094144;
    public static final int CHARGE_FUNC_PRE_CHARGING_CURRENT = 605094656;
    public static final int CHARGE_FUNC_PRE_CHARGING_IMMEDIATELY = 605095424;
    public static final int CHARGE_FUNC_PRE_CHARGING_SOC = 605094400;
    public static final int CHARGE_FUNC_PRE_CHARGING_STATUS = 605094912;
    public static final int CHARGE_FUNC_PRE_CHARGING_TYPE = 605095168;
    public static final int CHARGE_FUNC_TIME_INTERVAL_MAINTAIN = 609225472;
    public static final int CHARGE_FUNC_TRAVEL_APPOINT_CHARGING = 606077184;
    public static final int CHARGE_FUNC_TRAVEL_APPOINT_CHARGING_CUS_FRI = 606082304;
    public static final int CHARGE_FUNC_TRAVEL_APPOINT_CHARGING_CUS_MON = 606081280;
    public static final int CHARGE_FUNC_TRAVEL_APPOINT_CHARGING_CUS_SAT = 606082560;
    public static final int CHARGE_FUNC_TRAVEL_APPOINT_CHARGING_CUS_SUN = 606082816;
    public static final int CHARGE_FUNC_TRAVEL_APPOINT_CHARGING_CUS_THUR = 606082048;
    public static final int CHARGE_FUNC_TRAVEL_APPOINT_CHARGING_CUS_TUE = 606081536;
    public static final int CHARGE_FUNC_TRAVEL_APPOINT_CHARGING_CUS_WED = 606081792;
    public static final int CHARGE_FUNC_TRAVEL_APPOINT_CHARGING_MODE = 606077440;
    public static final int CHARGE_FUNC_TRAVEL_APPOINT_CHARGING_ONCE = 606077696;
    public static final int CHARGE_FUNC_TRAVEL_APPOINT_CHARGING_PEAK_VALLEY_PERIOD = 606085120;
    public static final int CHARGE_FUNC_TRAVEL_APPOINT_CHARGING_RESTORE = 606077952;
    public static final int CHARGE_FUNC_TRAVEL_APPOINT_CHARGING_SAVE = 606078464;
    public static final int CHARGE_FUNC_TRAVEL_APPOINT_CHARGING_START_TIME = 606080512;
    public static final int CHARGE_FUNC_TRAVEL_HVAC = 606078208;
    public static final int CHARGE_FUNC_WARM_UP = 605030944;
    public static final int CHARGE_FUNC_WARM_UP_LEVEL = 605030928;
    public static final int CHARGE_PLUG_AC = 605225217;
    public static final int CHARGE_PLUG_DC = 605225218;
    public static final int CHARGE_PLUG_DISCHARGE = 605225219;
    public static final int CHARGE_PLUG_NOTMATCH = 605225220;
    public static final int CHARGE_PLUG_STATE_CHARGING = 605225474;
    public static final int CHARGE_PLUG_STATE_CHARGING_FAIL = 605225486;
    public static final int CHARGE_PLUG_STATE_CHARGING_PAUSE = 605225483;
    public static final int CHARGE_PLUG_STATE_COMPLETED = 605225475;
    public static final int CHARGE_PLUG_STATE_CONNECTED = 605225481;
    public static final int CHARGE_PLUG_STATE_DISCHARGING = 605225478;
    public static final int CHARGE_PLUG_STATE_DISCHARGING_COMPLETED = 605225479;
    public static final int CHARGE_PLUG_STATE_DISCHARGING_END = 605225488;
    public static final int CHARGE_PLUG_STATE_DISCHARGING_FAIL = 605225487;
    public static final int CHARGE_PLUG_STATE_DISCHARGING_PAUSE = 605225484;
    public static final int CHARGE_PLUG_STATE_DISCONNECTED = 605225482;
    public static final int CHARGE_PLUG_STATE_ERROR = 605225477;
    public static final int CHARGE_PLUG_STATE_HEATING = 605225480;
    public static final int CHARGE_PLUG_STATE_MULTI = 605225476;
    public static final int CHARGE_PLUG_STATE_PREPARED = 605225473;
    public static final int CHARGE_PLUG_STATE_RESERVE_WAITING = 605225485;
    public static final int CHARGE_PLUG_STATE_TARGET_VALUE_OWER_THAN_CURRENT = 606078720;
    public static final int CHARGE_PLUG_STATE_UNKNOWN = 255;
    public static final int CHARGE_PLUG_UNKNOWN = 255;
    public static final int CHARGE_PLUG_WAITING = 605225221;
    public static final int CHARGING_PLUG_STATE_CONNECTED_WAITING = 605225493;
    public static final int CHARGING_PLUG_STATE_DISCONNECTED = 605225489;
    public static final int CHARGING_PLUG_STATE_DIS_CHRGN_CONNECTED = 605225492;
    public static final int CHARGING_PLUG_STATE_FAULT = 605225495;
    public static final int CHARGING_PLUG_STATE_NONE = 605225496;
    public static final int CHARGING_PLUG_STATE_QUICK_CHRGN_CONNECTED = 605225491;
    public static final int CHARGING_PLUG_STATE_SLOW_CHRGN_CONNECTED = 605225490;
    public static final int CHARGING_PLUG_STATE_WRONG_OPERATION = 605225494;
    public static final int DST_LONG_KM = 609223169;
    public static final int DST_LONG_MILES = 609223170;
    public static final int DST_LONG_UKWNUNIT = 609223171;
    public static final int GEAR_LVL_ONE = 609225730;
    public static final int GEAR_LVL_THREE = 609225732;
    public static final int GEAR_LVL_TWO = 609225731;
    public static final int GEAR_NO_INDICATION = 609225729;
    public static final int HV_BOOST_CHARGING = 609223428;
    public static final int HV_CHRG_DEFAULT = 609223424;
    public static final int HV_DIS_CHRG_DEFAULT = 609223680;
    public static final int HV_DIS_CHRG_DISCHARGING = 609223681;
    public static final int HV_DIS_CHRG_END_DIS = 609223682;
    public static final int HV_DIS_CHRG_FAULT_DIS = 609223683;
    public static final int HV_DIS_CHRG_NONE = 609223684;
    public static final int HV_END_CHARGING = 609223430;
    public static final int HV_FAULT_CHARGING = 609223431;
    public static final int HV_HEATING = 609223432;
    public static final int HV_NONE = 609223434;
    public static final int HV_QUICK_CHARGING = 609223426;
    public static final int HV_RESERVING = 609223433;
    public static final int HV_SLOW_CHARGING = 609223425;
    public static final int HV_SUPER_CHARGING = 609223427;
    public static final int HV_WIRELESS_CHARGING = 609223429;
    public static final int PHEV_CHARGE_MODE_ACTIVE = 605357569;
    public static final int PHEV_CHARGE_MODE_OFF = 605357571;
    public static final int PHEV_CHARGE_MODE_SOC_HOLD = 605357570;
    public static final int POWER_CHARGE_MODE_FAIL = 606078979;
    public static final int POWER_CHARGE_MODE_FINISH = 606078980;
    public static final int POWER_CHARGE_MODE_FUEL_LOW = 606078981;
    public static final int POWER_CHARGE_MODE_OFF = 606078978;
    public static final int POWER_CHARGE_MODE_ON = 606078977;
    public static final int POWER_CHARGE_MODE_TIMEOUT = 606078982;
    public static final int PRE_CHARGING_STATUS_CANCELED = 605094918;
    public static final int PRE_CHARGING_STATUS_CANCEL_FAILED = 605094919;
    public static final int PRE_CHARGING_STATUS_CHARGING = 605094917;
    public static final int PRE_CHARGING_STATUS_FAILED = 605094914;
    public static final int PRE_CHARGING_STATUS_FAILURE = 605094915;
    public static final int PRE_CHARGING_STATUS_SCHEDULING = 605094916;
    public static final int PRE_CHARGING_STATUS_SUCCEED = 605094913;
    public static final int PRE_CHARGING_STATUS_TIMEOUT = 605094920;
    public static final int PRE_CHARGING_STATUS_UNKNOWN = 255;
    public static final int TRAVEL_APPOINT_CHARGING_MODE_ALLWEEK = 606077443;
    public static final int TRAVEL_APPOINT_CHARGING_MODE_CUSTOM = 606077444;
    public static final int TRAVEL_APPOINT_CHARGING_MODE_ONCE = 606077441;
    public static final int TRAVEL_APPOINT_CHARGING_MODE_UNKNOWN = 255;
    public static final int TRAVEL_APPOINT_CHARGING_MODE_WEEKDATS = 606077442;
    public static final int WARM_UP_ECO = 605030929;
    public static final int WARM_UP_SPORT = 605030930;

    @Retention(RetentionPolicy.SOURCE)
    public @interface ACChrgnPData {
    }

    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface BatteryColorState {
        public static final int PHEV_BATTERY_COLOR_BLUE = 606079492;
        public static final int PHEV_BATTERY_COLOR_BLUE2 = 606079493;
        public static final int PHEV_BATTERY_COLOR_DEFAULT = 606079496;
        public static final int PHEV_BATTERY_COLOR_GRAY = 606079489;
        public static final int PHEV_BATTERY_COLOR_GRAYYELLOW = 606079490;
        public static final int PHEV_BATTERY_COLOR_GREEN = 606079491;
        public static final int PHEV_BATTERY_COLOR_RED = 606079495;
        public static final int PHEV_BATTERY_COLOR_YELLOW = 606079494;
    }

    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface BatteryModelState {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface BatteryTempMaintainStatus {
        public static final int BATTERY_TEMP_MAINTAIN_STATUS_IDLE = 605032196;
        public static final int BATTERY_TEMP_MAINTAIN_STATUS_MAINTAIN = 605032193;
        public static final int BATTERY_TEMP_MAINTAIN_STATUS_OFF = 0;
        public static final int BATTERY_TEMP_MAINTAIN_STATUS_ON = 1;
        public static final int BATTERY_TEMP_MAINTAIN_STATUS_PRESTART = 605032195;
        public static final int BATTERY_TEMP_MAINTAIN_STATUS_STANDBY = 605032194;
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ChargingDisChargingState {
        public static final int ACCHRGNFLTVEHSIDE = 605226023;
        public static final int AC_CHARGING = 605225986;
        public static final int AC_CHARGINGSUSPEND = 605226021;
        public static final int AC_CHRGNFLTCHRGRSIDE = 605226004;
        public static final int BOOKING = 605225990;
        public static final int BOOSTCHARGING = 605226024;
        public static final int BOOSTCHARGINGFLT = 605226025;
        public static final int CHARGING_CMPL = 605225988;
        public static final int CHARGING_END = 605225987;
        public static final int CHARING_FALUT = 605226001;
        public static final int DC_CHARGING = 605226005;
        public static final int DC_CHARGINGEND = 605226022;
        public static final int DC_CHRGNFLTCHRGRSIDECOMFLT = 605226019;
        public static final int DC_CHRGNFLTCHRGRSIDECONFLT = 605226016;
        public static final int DC_CHRGNFLTCHRGRSIDEEMGYFLT = 605226018;
        public static final int DC_CHRGNFLTCHRGRSIDEHWFLT = 605226017;
        public static final int DC_CHRGNFLTCHRGRSIDETEMPFLT = 605226009;
        public static final int DC_CHRGNFLTVEHSIDE = 605226008;
        public static final int DEFAULT = 2;
        public static final int DISCHARING = 605225992;
        public static final int DISCHARING_CMPL = 605226000;
        public static final int DISCHARING_END = 605225993;
        public static final int DISCHARING_FALUT = 605226002;
        public static final int HEATING = 605225989;
        public static final int NO_CHARGING = 605225985;
        public static final int NO_DISCHARING = 605225991;
        public static final int SUPERCHARGING = 605226020;
        public static final int WIRELESSCHARGING = 605226032;
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ChargingFunction {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ChargingPlugState {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ChargingPlugType {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ChargingTimeSettingType {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ChrgnPlugState {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface DCChrgnPData {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface DChrgnPData {
    }

    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface DisChargeStopState {
        public static final int DISCHARGE_STOP_STATE_REACH = 606079234;
        public static final int DISCHARGE_STOP_STATE_UN_REACH = 606079233;
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface DistanceProtectionUnit {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface HvBattChrgStatus {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface HvBattDisSts {
    }

    public interface IChargingListener {
        void onPreChargingTimeChanged(Calendar[] calendarArr);
    }

    public interface ITravelChargingListener extends IChargingListener {
        void onChargingTimeSettingChanged(int i, Calendar[] calendarArr);
    }

    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface PHEVPowerBatteryMode {
    }

    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface PowerSupplyMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface PreChargingStatus {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface PreChargingType {
        public static final int CHARGE_FUNC_PRE_CHARGING_TYPE_CYCLE = 605095170;
        public static final int CHARGE_FUNC_PRE_CHARGING_TYPE_OFF = 605095168;
        public static final int CHARGE_FUNC_PRE_CHARGING_TYPE_SINGLE = 605095169;
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface PtGearLevelIndcn {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface TravelAppointChargingMode {
    }

    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface WarmUpLevel {
    }

    Calendar[] getChargingTimeSetting(int i);

    Calendar[] getHistoricalDischargeCapacityTime();

    Float[] getHistoricalDischargeCapacityValue();

    Calendar[] getPreChargingTime();

    void registerListener(IChargingListener iChargingListener);

    boolean setChargingTimeSetting(int i, Calendar[] calendarArr);

    boolean setPreChargingTime(Calendar calendar, Calendar calendar2);

    void unregisterListener(IChargingListener iChargingListener);
}
