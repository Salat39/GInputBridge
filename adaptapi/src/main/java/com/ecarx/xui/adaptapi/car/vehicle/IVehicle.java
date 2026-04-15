package com.ecarx.xui.adaptapi.car.vehicle;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public interface IVehicle {
    public static final int ABNORMAL_VEHICLE_ALARM_SOUND = 539497986;
    public static final int ABNORMAL_VEHICLE_ALARM_VISUAL_SOUND = 539497985;
    public static final int ACTIVE = 538379521;
    public static final int AMBIENCE_LIGHT_EXPERIENCE_CUSTOM = 537526530;
    public static final int AMBIENCE_LIGHT_EXPERIENCE_FULL = 537526529;
    public static final int AMBIENCE_LIGHT_MAINCOLOR_BREATHE_MODE = 537526790;
    public static final int AMBIENCE_LIGHT_MAINCOLOR_DRIVERMODE = 537526786;
    public static final int AMBIENCE_LIGHT_MAINCOLOR_MUSIC = 537526788;
    public static final int AMBIENCE_LIGHT_MAINCOLOR_NONE = 0;
    public static final int AMBIENCE_LIGHT_MAINCOLOR_NON_POLAR = 537526789;
    public static final int AMBIENCE_LIGHT_MAINCOLOR_SETCOLOR = 537526787;
    public static final int AMBIENCE_LIGHT_MAINCOLOR_SPEED_MODE = 537526791;
    public static final int AMBIENCE_LIGHT_MAINCOLOR_TEMPERATURE_MODE = 537526793;
    public static final int AMBIENCE_LIGHT_MAINCOLOR_THEME = 537526785;
    public static final int AMBIENCE_LIGHT_MAINCOLOR_WEATHER = 537526792;
    public static final int ARTIFICIAL_SOUND_TYPE_1 = 538575873;
    public static final int ARTIFICIAL_SOUND_TYPE_2 = 538575874;
    public static final int ARTIFICIAL_SOUND_TYPE_3 = 538575875;
    public static final int ARTIFICIAL_SOUND_TYPE_4 = 538575876;
    public static final int ARTIFICIAL_SOUND_TYPE_5 = 538575877;
    public static final int ARTIFICIAL_SOUND_TYPE_6 = 538575878;
    public static final int ARTIFICIAL_SOUND_TYPE_7 = 538575879;
    public static final int ARTIFICIAL_SOUND_TYPE_8 = 538575880;
    public static final int ARTIFICIAL_SOUND_TYPE_NONE = 0;
    public static final int AUTO_CLOSE_WINDOW_KEY_LONG_PRESS = 537396226;
    public static final int AUTO_CLOSE_WINDOW_OFF = 0;
    public static final int AUTO_CLOSE_WINDOW_VEHICLE_LOCK = 537396225;
    public static final int BATTERY_ELE_VEHICLE = 4;
    public static final int BRIGHTNESS_DRIVING_HIGH = 537528066;
    public static final int BRIGHTNESS_DRIVING_LOW = 537528065;
    public static final int BRIGHTNESS_DRIVING_OFF = 0;
    public static final int BRIGHTNESS_STATIONARY_HIGH = 537527810;
    public static final int BRIGHTNESS_STATIONARY_LOW = 537527809;
    public static final int BRIGHTNESS_STATIONARY_OFF = 0;
    public static final int CAR_LOCATOR_REMINDER_MODE_LIGHT = 538313730;
    public static final int CAR_LOCATOR_REMINDER_MODE_LIGHT_SOUND = 538313731;
    public static final int CAR_LOCATOR_REMINDER_MODE_OFF = 0;
    public static final int CAR_LOCATOR_REMINDER_MODE_SOUND = 538313729;
    public static final int CDC_MODE_LEVEL1 = 540285441;
    public static final int CDC_MODE_LEVEL2 = 540285442;
    public static final int CDC_MODE_LEVEL3 = 540285443;
    public static final int COM_ENGINE_ONLY = 1;
    public static final int CONNECTED = 538379523;
    public static final int DANGEROUS_ROAD_SOUND = 539498242;
    public static final int DANGEROUS_ROAD_VISUAL_SOUND = 539498241;
    public static final int DAYMODE_SETTING_AUTO = 538247427;
    public static final int DAYMODE_SETTING_CUSTOM = 538247428;
    public static final int DAYMODE_SETTING_DAY = 538247425;
    public static final int DAYMODE_SETTING_NIGHT = 538247426;
    public static final int DAYMODE_SETTING_OFF = 0;
    public static final int DAYMODE_SETTING_SUNRISE_AND_SUNSET = 538247429;
    public static final int DOOR_OPEN_WARN_VOLUME_HIGH = 538051075;
    public static final int DOOR_OPEN_WARN_VOLUME_LOW = 538051073;
    public static final int DOOR_OPEN_WARN_VOLUME_MID = 538051074;
    public static final int DOOR_OPEN_WARN_VOLUME_OFF = 0;
    public static final int DTE_TYPE_ACTURAL = 1;
    public static final int DTE_TYPE_STANDARD = 0;
    public static final int EMERGENCY_VEHICLE_SOUND = 539498498;
    public static final int EMERGENCY_VEHICLE_VISUAL_SOUND = 539498497;
    public static final int ENERGY_PREDICTION_ACTIVE = 538904834;
    public static final int ENERGY_PREDICTION_CLOSE = 538904832;
    public static final int ENERGY_PREDICTION_STANDBY = 538904833;
    public static final int ENERGY_REGENERATION_LEVEL_AUTO = 537003268;
    public static final int ENERGY_REGENERATION_LEVEL_HIGH = 537003267;
    public static final int ENERGY_REGENERATION_LEVEL_LOW = 537003265;
    public static final int ENERGY_REGENERATION_LEVEL_MID = 537003266;
    public static final int ENERGY_REGENERATION_LEVEL_OFF = 0;
    public static final int ENVIRONMENTA_PROTECTION_LEVEL_1 = 539495681;
    public static final int ENVIRONMENTA_PROTECTION_LEVEL_2 = 539495682;
    public static final int ENVIRONMENTA_PROTECTION_LEVEL_3 = 539495683;
    public static final int ENVIRONMENTA_PROTECTION_LEVEL_4 = 539495684;
    public static final int ENVIRONMENTA_PROTECTION_LEVEL_5 = 539495685;
    public static final int ENVIRONMENTA_PROTECTION_LEVEL_6 = 539495686;
    public static final int ENVIRONMENTA_PROTECTION_LEVEL_7 = 539495688;
    public static final int ENVIRONMENTA_PROTECTION_LEVEL_8 = 539495689;
    public static final int ENVIRONMENTA_PROTECTION_LEVEL_9 = 539495696;
    public static final int ENVIRONMENTA_PROTECTION_LEVEL_NO_RESPONSE = 539495680;
    public static final int ESM_SOUND_ENERGYPULSE = 540281602;
    public static final int ESM_SOUND_OFF = 0;
    public static final int ESM_SOUND_Pixel = 540281603;
    public static final int ESM_SOUND_SPACEWALK = 540281601;
    public static final int ESM_VOLUME_LEVEL_HIGH = 538575363;
    public static final int ESM_VOLUME_LEVEL_LOW = 538575361;
    public static final int ESM_VOLUME_LEVEL_MID = 538575362;
    public static final int ESM_VOLUME_LEVEL_OFF = 0;
    public static final int FESTIVAL_TYPE_CHRISTMAS = 539496201;
    public static final int FESTIVAL_TYPE_DRAGON_BOAT_FESTIVAL = 539496197;
    public static final int FESTIVAL_TYPE_LABOR_DAY = 539496196;
    public static final int FESTIVAL_TYPE_MID_AUTUMN_FESTIVAL = 539496199;
    public static final int FESTIVAL_TYPE_NATIONAL_DAY = 539496200;
    public static final int FESTIVAL_TYPE_NEW_YEAR = 539496193;
    public static final int FESTIVAL_TYPE_NULL = 539496192;
    public static final int FESTIVAL_TYPE_SPRING_FESTIVAL = 539496194;
    public static final int FESTIVAL_TYPE_TANABATA_FESTIVAL = 539496198;
    public static final int FESTIVAL_TYPE_VALENTINE_DAY = 539496195;
    public static final int FINISHED = 538379522;
    public static final int FORWARD_COLLISION_WARN_SNVTY_HIGH = 537788931;
    public static final int FORWARD_COLLISION_WARN_SNVTY_LOW = 537788929;
    public static final int FORWARD_COLLISION_WARN_SNVTY_NORMAL = 537788930;
    public static final int FORWARD_COLLISION_WARN_SNVTY_OFF = 0;
    public static final int GEAR_NOTP = 538379526;
    public static final int GLOVEBOX_LOCK_STATE_IDLE = 537854720;
    public static final int GLOVEBOX_LOCK_STATE_LOCK = 537854721;
    public static final int GLOVEBOX_LOCK_STATE_UNLOCK = 537854722;
    public static final int HMI_THEMES_VALUE_CLEAR = 536936705;
    public static final int HMI_THEMES_VALUE_HYPER = 536936708;
    public static final int HMI_THEMES_VALUE_INTER = 536936707;
    public static final int HMI_THEMES_VALUE_LOUDER = 536936706;
    public static final int HOME_SAFE_LIGHT_VALUE_30S = 537134849;
    public static final int HOME_SAFE_LIGHT_VALUE_60S = 537134850;
    public static final int HOME_SAFE_LIGHT_VALUE_90S = 537134851;
    public static final int HOME_SAFE_LIGHT_VALUE_OFF = 0;
    public static final int HOOD_OPEN = 538379524;
    public static final int HV_SYSTEM_ERROR = 538379530;
    public static final int HYBRID_VEHICLE = 2;
    public static final int KEYLESS_UNLOCKING_ALL_DOORS = 537920513;
    public static final int KEYLESS_UNLOCKING_OFF = 0;
    public static final int KEYLESS_UNLOCKING_SINGLE_DOOR = 537920514;
    public static final int LAMP_AUTOLIGHT_VALUE_EARLIER = 537133827;
    public static final int LAMP_AUTOLIGHT_VALUE_LATER = 537133825;
    public static final int LAMP_AUTOLIGHT_VALUE_NORMAL = 537133826;
    public static final int LAMP_EXTERIOR_LIGHT_CONTROL_AHBC = 537136644;
    public static final int LAMP_EXTERIOR_LIGHT_CONTROL_AUTOMATIC = 537136643;
    public static final int LAMP_EXTERIOR_LIGHT_CONTROL_LOWBEAM = 537136642;
    public static final int LAMP_EXTERIOR_LIGHT_CONTROL_OFF = 0;
    public static final int LAMP_EXTERIOR_LIGHT_CONTROL_POS_LIGHT = 537136641;
    public static final int LAMP_HIGHBEAM_ACTIVE_VALUE_FAST = 537133571;
    public static final int LAMP_HIGHBEAM_ACTIVE_VALUE_NORMAL = 537133570;
    public static final int LAMP_HIGHBEAM_ACTIVE_VALUE_SLOW = 537133569;
    public static final int LANE_CHANGE_WARNING_MODE_OFF = 0;
    public static final int LANE_CHANGE_WARNING_MODE_SOUND = 537330706;
    public static final int LANE_CHANGE_WARNING_MODE_VISUAL = 537330705;
    public static final int LANE_CHANGE_WARNING_MODE_VISUAL_SOUND = 537330707;
    public static final int LANE_CHANGE_WARNING_SOUND_HIGH = 537330691;
    public static final int LANE_CHANGE_WARNING_SOUND_LOW = 537330689;
    public static final int LANE_CHANGE_WARNING_SOUND_MID = 537330690;
    public static final int LANE_CHANGE_WARNING_SOUND_OFF = 0;
    public static final int LANE_KEEPING_AID_MODE_INTV = 537330178;
    public static final int LANE_KEEPING_AID_MODE_OFF = 0;
    public static final int LANE_KEEPING_AID_MODE_WARN = 537330179;
    public static final int LANE_KEEPING_AID_MODE_WARN_INTV = 537330177;
    public static final int LANE_KEEPING_AID_WARNING_HAPTIC = 537330946;
    public static final int LANE_KEEPING_AID_WARNING_OFF = 0;
    public static final int LANE_KEEPING_AID_WARNING_SOUND = 537330945;
    public static final int LANE_KEEPING_AID_WARNING_SOUND_HAPTIC = 537330947;
    public static final int LAUNCH_MODE_BATTERY_POWER_LOW = 1;
    public static final int LAUNCH_MODE_BATTERY_TEMPER_HIGHER = 2;
    public static final int LAUNCH_MODE_BATTERY_TEMPER_LOWER = 3;
    public static final int LAUNCH_MODE_BRAKE_PEDAL_RELEASE_OVERTIME = 11;
    public static final int LAUNCH_MODE_DISABLE = 0;
    public static final int LAUNCH_MODE_ESC_NOT_TURN_OFF = 4;
    public static final int LAUNCH_MODE_NO_CANCEL_TRAILER = 5;
    public static final int LAUNCH_MODE_NO_CLOSE_DOORS = 9;
    public static final int LAUNCH_MODE_NO_CLOSE_DRIVER_BELT = 8;
    public static final int LAUNCH_MODE_NO_RELEASE_EPB = 7;
    public static final int LAUNCH_MODE_NO_STRAIGHT_STEERING_WHEEL = 6;
    public static final int LAUNCH_MODE_STANDBY_OVERTIME = 10;
    public static final int LAUNCH_MODE_USE_FREQUENCY_TOO_FASE = 12;
    public static final int LEFT_TURN_ASSIST_SOUND = 539499778;
    public static final int LEFT_TURN_ASSIST_VISUAL_SOUND = 539499777;
    public static final int LOW_FUEL = 538379525;
    public static final int LV_SYSTEM_ERROR = 538379529;
    public static final int MAX_NUMBER = 538379531;
    public static final int MAX_TIME = 538379527;
    public static final int MHEV = 5;
    public static final int MIRROR_DIMMING_DARK = 537460993;
    public static final int MIRROR_DIMMING_LIGHT = 537460995;
    public static final int MIRROR_DIMMING_NORMAL = 537460994;
    public static final int MIRROR_DIMMING_OFF = 0;
    public static final int MIRROR_DIPPING_BOTH = 537461507;
    public static final int MIRROR_DIPPING_DRIVER = 537461505;
    public static final int MIRROR_DIPPING_OFF = 0;
    public static final int MIRROR_DIPPING_PASSENGER = 537461506;
    public static final int MSG_BATTERY_MODE = 539430403;
    public static final int MSG_DRIVING_MODEL = 539430402;
    public static final int MSG_ENERGY_NONE = 539430400;
    public static final int MSG_EXCESSIVE_ENGINE_SPEED = 539430404;
    public static final int MSG_NAVIGATION_DESTINATION_NOT_SET = 539430406;
    public static final int MSG_PATH_HAS_YAWED = 539430407;
    public static final int MSG_VEHICLE_MODEL = 539430401;
    public static final int MSG_WEAK_NAVIGATION_SIGNAL = 539430405;
    public static final int MULTI_FUNC_KNOB_IDLE = 0;
    public static final int MULTI_FUNC_KNOB_PRESSED = 1;
    public static final int MULTI_FUNC_KNOB_RELEASED = 2;
    public static final int NOT_SET = 538379520;
    public static final int NO_VAILD = 538379528;
    public static final int PARKING_COMFORT_MODE_TIMER_0 = 0;
    public static final int PARKING_COMFORT_MODE_TIMER_120 = 538640646;
    public static final int PARKING_COMFORT_MODE_TIMER_15 = 538640641;
    public static final int PARKING_COMFORT_MODE_TIMER_30 = 538640642;
    public static final int PARKING_COMFORT_MODE_TIMER_45 = 538640643;
    public static final int PARKING_COMFORT_MODE_TIMER_60 = 538640644;
    public static final int PARKING_COMFORT_MODE_TIMER_90 = 538640645;
    public static final int PARK_ASSIST_SYS_VOLUME_HIGH = 537723395;
    public static final int PARK_ASSIST_SYS_VOLUME_LOW = 537723393;
    public static final int PARK_ASSIST_SYS_VOLUME_MID = 537723394;
    public static final int PARK_ASSIST_SYS_VOLUME_OFF = 0;
    public static final int PEB_MODE_MSP = 537264642;
    public static final int PEB_MODE_OFF = 0;
    public static final int PEB_MODE_PEB = 537264641;
    public static final int PGEAR_UNLOCK_TYP_OFF = 2;
    public static final int PGEAR_UNLOCK_TYP_ON = 1;
    public static final int PLUGIN_HY_VEHICLE = 3;
    public static final int PRIVATE_LOCK_REMINDER_OFF = 0;
    public static final int PRIVATE_LOCK_REMINDER_ON = 1;
    public static final int PRIVATE_LOCK_REMINDER_REMINDER = 537854465;
    public static final int RAINSENSORSENSILVL_LVL1 = 0;
    public static final int RAINSENSORSENSILVL_LVL2 = 1;
    public static final int RAINSENSORSENSILVL_LVL3 = 2;
    public static final int RAINSENSORSENSILVL_LVL4 = 3;
    public static final int RAINSENSORSENSILVL_LVL5 = 4;
    public static final int RAINSENSORSENSILVL_LVL6 = 5;
    public static final int RAINSENSORSENSILVL_LVL7 = 6;
    public static final int REAR_SPOILER_ADJUST_OFF = 0;
    public static final int REAR_SPOILER_ADJUST_P1 = 538510337;
    public static final int REAR_SPOILER_ADJUST_P2 = 538510338;
    public static final int RED_LIGHT_SOUND = 539498754;
    public static final int RED_LIGHT_VISUAL_SOUND = 539498753;
    public static final int REFUELING_SWT_NO_ACTION = 538379008;
    public static final int REFUELING_SWT_UNLCK = 538379009;
    public static final int REMOTE_UNLOCKING_ALL_DOORS = 537920769;
    public static final int REMOTE_UNLOCKING_OFF = 0;
    public static final int REMOTE_UNLOCKING_SINGLE_DOOR = 537920770;
    public static final int RESERVED1 = 538379532;
    public static final int RESERVED2 = 538379533;
    public static final int REVERSE_OVERTAKING_SOUND = 539499522;
    public static final int REVERSE_OVERTAKING_VISUAL_SOUND = 539499521;
    public static final int ROTATED_WHEELS_WARNING_INFO_LEFTWARD = 538772225;
    public static final int ROTATED_WHEELS_WARNING_INFO_NONE = 0;
    public static final int ROTATED_WHEELS_WARNING_INFO_RIGHTWARD = 538772226;
    public static final int SCREEN_SAVER_TIME_10 = 539035394;
    public static final int SCREEN_SAVER_TIME_5 = 539035393;
    public static final int SCREEN_SAVER_TIME_NEVER = 539035395;
    public static final int SETTING_DHU_FAST_START_MODE = 539512832;
    public static final int SETTING_FUNC_ABNORMAL_VEHICLE_ALARM = 539492608;
    public static final int SETTING_FUNC_ABNORMAL_VEHICLE_ALARM_MODE = 539497984;
    public static final int SETTING_FUNC_AMBIENCE_LIGHT_BOTZONES = 537527040;
    public static final int SETTING_FUNC_AMBIENCE_LIGHT_BRIGHTNESS_DRIVING = 537528064;
    public static final int SETTING_FUNC_AMBIENCE_LIGHT_BRIGHTNESS_STATIONARY = 537527808;
    public static final int SETTING_FUNC_AMBIENCE_LIGHT_COLOR_SET = 537528576;
    public static final int SETTING_FUNC_AMBIENCE_LIGHT_COLOR_TYPE = 537528832;
    public static final int SETTING_FUNC_AMBIENCE_LIGHT_EXPERIENCE = 537526528;
    public static final int SETTING_FUNC_AMBIENCE_LIGHT_INTERACTIVE_EFFECT = 537528320;
    public static final int SETTING_FUNC_AMBIENCE_LIGHT_MAINCOLOR = 537526784;
    public static final int SETTING_FUNC_AMBIENCE_LIGHT_MAINZONES = 537527552;
    public static final int SETTING_FUNC_AMBIENCE_LIGHT_TOPZONES = 537527296;
    public static final int SETTING_FUNC_ARTIFICIAL_SOUND_PREVIEW = 539428608;
    public static final int SETTING_FUNC_ARTIFICIAL_SOUND_SWITCH = 538575616;
    public static final int SETTING_FUNC_ARTIFICIAL_SOUND_TYPE = 538575872;
    public static final int SETTING_FUNC_AUDIBLE_LOCKING_FEEDBACK = 537920256;
    public static final int SETTING_FUNC_AUTONOMOUS_EMERGENCY_BRAKING = 537333248;
    public static final int SETTING_FUNC_AUTO_CLOSE_ROOF_RAINY = 537395968;
    public static final int SETTING_FUNC_AUTO_CLOSE_WINDOW = 537396224;
    public static final int SETTING_FUNC_AUTO_HOLD = 537265152;
    public static final int SETTING_FUNC_AUTO_REAR_WIPING = 537657856;
    public static final int SETTING_FUNC_AUTO_SHOW_MODE = 540279296;
    public static final int SETTING_FUNC_AUTO_SHOW_MODE_ENTER_CONDITIONS = 540279040;
    public static final int SETTING_FUNC_AUTO_SHOW_MODE_ICON = 540280832;
    public static final int SETTING_FUNC_AUTO_SHOW_MODE_POPUP = 540280064;
    public static final int SETTING_FUNC_AUTO_SHOW_MODE_TEXT = 540280576;
    public static final int SETTING_FUNC_AUTO_SHOW_MODE_TEXT_FALSE = 1;
    public static final int SETTING_FUNC_AUTO_SHOW_MODE_TEXT_GEAR = 2;
    public static final int SETTING_FUNC_AUTO_SHOW_MODE_TEXT_NORMAL = 0;
    public static final int SETTING_FUNC_AUTO_TRAILER_LAMP_CHECK = 537135872;
    public static final int SETTING_FUNC_AVAS_SOUND_TYPE = 538576640;
    public static final int SETTING_FUNC_AVAS_SOUND_TYPE_NAME = 538576896;
    public static final int SETTING_FUNC_AVAS_SOUND_TYPE_PATH = 538577152;
    public static final int SETTING_FUNC_AVAS_SWITCH = 538576128;
    public static final int SETTING_FUNC_AVAS_VOLUME = 538576384;
    public static final int SETTING_FUNC_BLIND_CAMERA_SYNC_RT_TURN = 538772480;
    public static final int SETTING_FUNC_BREATH_SCREEN_MODE = 540284928;
    public static final int SETTING_FUNC_BRIGHTNESS_DAY = 538247936;
    public static final int SETTING_FUNC_BRIGHTNESS_MAX = 538248448;
    public static final int SETTING_FUNC_BRIGHTNESS_MIN = 538248704;
    public static final int SETTING_FUNC_BRIGHTNESS_NIGHT = 538248192;
    public static final int SETTING_FUNC_BRIGHTNESS_STEP = 538248960;
    public static final int SETTING_FUNC_CAR_LOCATOR = 538312960;
    public static final int SETTING_FUNC_CAR_LOCATOR_REMINDER_MODE = 538313728;
    public static final int SETTING_FUNC_CDC_MODE = 540285440;
    public static final int SETTING_FUNC_CDC_MODE_WARNING = 540285696;
    public static final int SETTING_FUNC_CENTRAL_LOCK = 537921792;
    public static final int SETTING_FUNC_CONGESTION_AHEAD_ALARM = 539493376;
    public static final int SETTING_FUNC_CPM_SWITCH = 540213504;
    public static final int SETTING_FUNC_CSD_POSITION = 539504640;
    public static final int SETTING_FUNC_DANGEROUS_ROAD_ALARM = 539492864;
    public static final int SETTING_FUNC_DANGEROUS_ROAD_ALARM_MODE = 539498240;
    public static final int SETTING_FUNC_DATA_COLLECTION = 539361792;
    public static final int SETTING_FUNC_DAYLIGHT_SAVING_TIME = 538640896;
    public static final int SETTING_FUNC_DAYMODE_SETTING = 538247424;
    public static final int SETTING_FUNC_DAYMODE_SYNC = 538247680;
    public static final int SETTING_FUNC_DIGITAL_KEY = 539496448;
    public static final int SETTING_FUNC_DIGITAL_KEY_PAIRING_FAILED = 539497473;
    public static final int SETTING_FUNC_DIGITAL_KEY_REQ_STS = 539496704;
    public static final int SETTING_FUNC_DIGITAL_KEY_RES_TIMEOUT = 539563008;
    public static final int SETTING_FUNC_DIGITAL_KEY_SUSPENSION = 539497472;
    public static final int SETTING_FUNC_DIGITAL_KEY_TERMINATION = 539497216;
    public static final int SETTING_FUNC_DIGITAL_KEY_TRACKING_RESULT = 539497475;
    public static final int SETTING_FUNC_DIGITAL_KEY_TRACKING_WAIT = 539497474;
    public static final int SETTING_FUNC_DIGITAL_KEY_UNPAIR = 539496960;
    public static final int SETTING_FUNC_DIM_HOLIDAY_WALLPAPER = 538904320;
    public static final int SETTING_FUNC_DOOR_OPEN_WARN_ACTIVE = 538050816;
    public static final int SETTING_FUNC_DOOR_OPEN_WARN_VOLUME = 538051072;
    public static final int SETTING_FUNC_DRIVER_ALERT_CONTROL = 537002496;
    public static final int SETTING_FUNC_DRIVER_MODE_SOUND_SWITCH = 539429888;
    public static final int SETTING_FUNC_DRIVER_PERFOR_PERMANENT = 537003776;
    public static final int SETTING_FUNC_DRIVER_PERFOR_SUPPORT = 537003520;
    public static final int SETTING_FUNC_DRVR_SEB = 538379264;
    public static final int SETTING_FUNC_EASY_INGRESS_EGRESS = 538378496;
    public static final int SETTING_FUNC_EASY_INGRESS_EGRESS_MODE = 538378752;
    public static final int SETTING_FUNC_ELECTRIC_MILEAGE_DISPLAY_MODE = 540281088;
    public static final int SETTING_FUNC_ELECTRIC_MILEAGE_DISPLAY_SWITCH = 539429632;
    public static final int SETTING_FUNC_ELECTRONIC_PARKING = 540148736;
    public static final int SETTING_FUNC_ELE_SEATBELT_COMFORT = 537333504;
    public static final int SETTING_FUNC_EMERGENCY_VEHICLE_ALARM = 539493120;
    public static final int SETTING_FUNC_EMERGENCY_VEHICLE_ALARM_MODE = 539498496;
    public static final int SETTING_FUNC_EMGY_LANE_KEEP_AID = 537331200;
    public static final int SETTING_FUNC_EMGY_LANE_OCC_WARNING = 537332480;
    public static final int SETTING_FUNC_ENERGY_PREDICTION_MSG = 539430400;
    public static final int SETTING_FUNC_ENERGY_PREDICTION_OPTIMIZATION = 538904832;
    public static final int SETTING_FUNC_ENERGY_PREDICTION_SWITCH = 538903808;
    public static final int SETTING_FUNC_ENERGY_REGENERATION = 537003264;
    public static final int SETTING_FUNC_ENGINE_MAINTENANCE_TIME_RESET = 539430656;
    public static final int SETTING_FUNC_ENGINE_STOP_START = 537002240;
    public static final int SETTING_FUNC_ENTER_AUTO_SHOW_MODE = 540279808;
    public static final int SETTING_FUNC_ENTER_AUTO_SHOW_MODE_RE = 540280320;
    public static final int SETTING_FUNC_ENVIRONMENTAL_PROTECTION_LEVEL = 539495680;
    public static final int SETTING_FUNC_ESC_SPORT_MODE = 537002752;
    public static final int SETTING_FUNC_ESM_SWITCH = 538575104;
    public static final int SETTING_FUNC_ESM_TYPE = 540281600;
    public static final int SETTING_FUNC_ESM_VOLUME = 538575360;
    public static final int SETTING_FUNC_EVASIVE_MANEUVER_AID = 537332736;
    public static final int SETTING_FUNC_EXTERNAL_ARTIFICIAL_SOUND_SWITCH = 538577408;
    public static final int SETTING_FUNC_EXTERNAL_ARTIFICIAL_SOUND_TYPE = 538577664;
    public static final int SETTING_FUNC_EYE_BALL_TRACK = 539427584;
    public static final int SETTING_FUNC_E_PEDAL = 538444032;
    public static final int SETTING_FUNC_FACE_CAMERA_COVER = 540147968;
    public static final int SETTING_FUNC_FACE_RECOGNITION = 540279552;
    public static final int SETTING_FUNC_FACE_RECOGNITION_RESULT = 540281344;
    public static final int SETTING_FUNC_FACIAL_RECOGNITION = 538706432;
    public static final int SETTING_FUNC_FORWARD_COLLISION_WARN = 537788672;
    public static final int SETTING_FUNC_FORWARD_COLLISION_WARN_SNVTY = 537788928;
    public static final int SETTING_FUNC_FRONT_CROSS_TRAFFIC_ALERT = 537331968;
    public static final int SETTING_FUNC_FRONT_WIPER_IDLE = 537658112;
    public static final int SETTING_FUNC_GLOVEBOX_LOCK = 537854720;
    public static final int SETTING_FUNC_HDC_SWITCH = 537265408;
    public static final int SETTING_FUNC_HEAD_RESTRAINT_AUDIO = 539100160;
    public static final int SETTING_FUNC_HEAD_RESTRAINT_AUDIO_TYPE = 539099904;
    public static final int SETTING_FUNC_HILL_START_ASSIST = 539429376;
    public static final int SETTING_FUNC_HMI_THEMES_CHANGE = 536936704;
    public static final int SETTING_FUNC_HOLOGRAPHIC_ACTIVATED = 539500032;
    public static final int SETTING_FUNC_HOLOGRAPHIC_BACKLIGHT_LEVEL = 539500544;
    public static final int SETTING_FUNC_HOLOGRAPHIC_BACKLIGHT_MODE = 539500288;
    public static final int SETTING_FUNC_HUD_ACTIVE = 537985280;
    public static final int SETTING_FUNC_HUD_CALIBRATION = 537985536;
    public static final int SETTING_FUNC_HV_BATT_EGY_SOC = 605489152;
    public static final int SETTING_FUNC_HV_BATT_HEAT_POP = 538379776;
    public static final int SETTING_FUNC_HV_BATT_HEAT_TOAST = 538380032;
    public static final int SETTING_FUNC_INTELLIGENT_FUEL_SAVE = 538904064;
    public static final int SETTING_FUNC_INTERNAL_COMMUNICATION = 538902784;
    public static final int SETTING_FUNC_INTERNAL_COMMUNICATION_VOLUME = 538903040;
    public static final int SETTING_FUNC_JOURNAL_LOGS = 538313472;
    public static final int SETTING_FUNC_KEYLESS_UNLOCKING = 537920512;
    public static final int SETTING_FUNC_LAMP_ADAPTIVE_FRONT_LIGHT = 537136384;
    public static final int SETTING_FUNC_LAMP_APPROACH_LIGHT = 537135360;
    public static final int SETTING_FUNC_LAMP_AUTOLIGHT = 537133824;
    public static final int SETTING_FUNC_LAMP_AUTOMATIC_COURTESY_LIGHT = 537134592;
    public static final int SETTING_FUNC_LAMP_AUX_HIGHBEAM = 537134080;
    public static final int SETTING_FUNC_LAMP_BENDINGLIGHT = 537134336;
    public static final int SETTING_FUNC_LAMP_CORNERING_LIGHT = 537135616;
    public static final int SETTING_FUNC_LAMP_DAYTIME_RUNNING_LIGHT = 537135104;
    public static final int SETTING_FUNC_LAMP_EXTERIOR_LIGHT_CONTROL = 537136640;
    public static final int SETTING_FUNC_LAMP_HIGHBEAM_ACTIVE = 537133568;
    public static final int SETTING_FUNC_LAMP_HOME_SAFE_LIGHT = 537134848;
    public static final int SETTING_FUNC_LAMP_TRIPLE_FLASH = 537133312;
    public static final int SETTING_FUNC_LANE_CHANGE_ASSIST = 537331456;
    public static final int SETTING_FUNC_LANE_CHANGE_WARING = 537330432;
    public static final int SETTING_FUNC_LANE_CHANGE_WARNING_MODE = 537330704;
    public static final int SETTING_FUNC_LANE_CHANGE_WARNING_SOUND = 537330688;
    public static final int SETTING_FUNC_LANE_KEEPING_AID = 537329920;
    public static final int SETTING_FUNC_LANE_KEEPING_AID_MODE = 537330176;
    public static final int SETTING_FUNC_LANE_KEEPING_AID_WARNING = 537330944;
    public static final int SETTING_FUNC_LAUNCH_MODE = 539428864;
    public static final int SETTING_FUNC_LAUNCH_MODE_INDCN = 539429120;
    public static final int SETTING_FUNC_LEFT_TURN_ASSIST_ALARM = 539499264;
    public static final int SETTING_FUNC_LEFT_TURN_ASSIST_ALARM_MODE = 539499776;
    public static final int SETTING_FUNC_LIFE_DETECTION = 539427328;
    public static final int SETTING_FUNC_LOCK_REAR_SEAT_DISPLAY = 538706176;
    public static final int SETTING_FUNC_LOCK_RFDM_REMOTE_CONTROLLER = 538707968;
    public static final int SETTING_FUNC_MAINTENANCE_MILEAGE_RESET = 538968320;
    public static final int SETTING_FUNC_MAINTENANCE_TIME_RESET = 538968576;
    public static final int SETTING_FUNC_MANUAL_TRAILER_LAMP_CHECK = 537136128;
    public static final int SETTING_FUNC_MIRROR_AUTO_FOLDING = 537461248;
    public static final int SETTING_FUNC_MIRROR_DIMMING = 537460992;
    public static final int SETTING_FUNC_MIRROR_DIPPING = 537461504;
    public static final int SETTING_FUNC_MIRROR_DIPPING_SWITCH = 537461760;
    public static final int SETTING_FUNC_MULTIMEDIA_GESTURE = 539494144;
    public static final int SETTING_FUNC_MULTI_FUNC_KNOB_DIRECTION = 540285952;
    public static final int SETTING_FUNC_MULTI_FUNC_KNOB_PRESS_STATUS = 540286464;
    public static final int SETTING_FUNC_MULTI_FUNC_KNOB_ROTATE_STEP = 540286208;
    public static final int SETTING_FUNC_MUSIC_GESTURE = 539494656;
    public static final int SETTING_FUNC_PARK_ASSIST_SYS_ACTIVATED = 537723136;
    public static final int SETTING_FUNC_PARK_ASSIST_SYS_VOLUME = 537723392;
    public static final int SETTING_FUNC_PARK_COMFORT_BELT_DOOR_OPEN = 540284416;
    public static final int SETTING_FUNC_PARK_COMFORT_MODE_COUNTDOWN_TIMER = 540284672;
    public static final int SETTING_FUNC_PARK_COMFORT_MODE_OFF_REASON = 538838272;
    public static final int SETTING_FUNC_PARK_COMFORT_MODE_TIMER = 538837248;
    public static final int SETTING_FUNC_PARK_COMFORT_MODE_TIMER_MAX = 538837504;
    public static final int SETTING_FUNC_PARK_COMFORT_MODE_TIMER_MIN = 538837760;
    public static final int SETTING_FUNC_PARK_COMFORT_MODE_TIMER_STEP = 538838016;
    public static final int SETTING_FUNC_PASSENGER_AIRBAG = 539428096;
    public static final int SETTING_FUNC_PASSIVE_ARMING = 537921280;
    public static final int SETTING_FUNC_PBC_AUTO_APPLY = 537264384;
    public static final int SETTING_FUNC_PBC_DOUBLE_EPB_SWITCH = 537268480;
    public static final int SETTING_FUNC_PBC_EPB_SWITCH = 537268224;
    public static final int SETTING_FUNC_PCM_TIMER = 538640640;
    public static final int SETTING_FUNC_PDC_SWITCH = 537264896;
    public static final int SETTING_FUNC_PEB_MODE = 537264640;
    public static final int SETTING_FUNC_PGEAR_UNLOCK = 540148480;
    public static final int SETTING_FUNC_PRIVATE_LOCK = 537854208;
    public static final int SETTING_FUNC_PRIVATE_LOCK_REMINDER = 537854464;
    public static final int SETTING_FUNC_PSD_SCREEN_SWITCH = 539495936;
    public static final int SETTING_FUNC_RAIN_SENSOR_SENSITIVITY = 540148224;
    public static final int SETTING_FUNC_REAR_COLLISION_WARNING = 537333760;
    public static final int SETTING_FUNC_REAR_CROSS_TRAFFIC_ALERT = 537332224;
    public static final int SETTING_FUNC_REAR_MIRROR_STREAM = 540214016;
    public static final int SETTING_FUNC_REAR_MIRR_STREAM_SWITCH = 539100416;
    public static final int SETTING_FUNC_REAR_MIRR_STREAM_VERTICAL_VISUAL = 539100672;
    public static final int SETTING_FUNC_REAR_MIRR_STREAM_VERTICAL_VISUAL_MAX = 539101184;
    public static final int SETTING_FUNC_REAR_MIRR_STREAM_VERTICAL_VISUAL_MIN = 539100928;
    public static final int SETTING_FUNC_REAR_MIRR_STREAM_VERTICAL_VISUAL_STEP = 539101440;
    public static final int SETTING_FUNC_REAR_MIRR_STREAM_ZOOM_LEVEL = 539101696;
    public static final int SETTING_FUNC_REAR_SPOILER_ADJUST = 538510336;
    public static final int SETTING_FUNC_REAR_SPOILER_ADJUST_FAIL = 538511104;
    public static final int SETTING_FUNC_REAR_SPOILER_POSITION_ADJUST_MODE = 538510848;
    public static final int SETTING_FUNC_REAR_SPOILER_POSN_REQUEST = 538510592;
    public static final int SETTING_FUNC_REAR_WINDOW_CLEAN = 537395712;
    public static final int SETTING_FUNC_REDUCED_GUARD = 537921536;
    public static final int SETTING_FUNC_RED_LIGHT_ALARM = 539493888;
    public static final int SETTING_FUNC_RED_LIGHT_ALARM_MODE = 539498752;
    public static final int SETTING_FUNC_REFUELING_SWT = 538379008;
    public static final int SETTING_FUNC_REMOTE_DIAGNOSTICS = 539362048;
    public static final int SETTING_FUNC_REMOTE_UNLOCKING = 537920768;
    public static final int SETTING_FUNC_RESET_SETTINGS_DEFAULT = 538181888;
    public static final int SETTING_FUNC_REVERSE_OVERTAKING_ALARM = 539499008;
    public static final int SETTING_FUNC_REVERSE_OVERTAKING_ALARM_MODE = 539499520;
    public static final int SETTING_FUNC_RMS_ACTIVE = 538116352;
    public static final int SETTING_FUNC_ROTATED_WHEELS_WARNING = 538771968;
    public static final int SETTING_FUNC_ROTATED_WHEELS_WARNING_INFO = 538772224;
    public static final int SETTING_FUNC_RSD_SWITCH = 540213760;
    public static final int SETTING_FUNC_RVDC = 539361536;
    public static final int SETTING_FUNC_SAILING_MODE = 537003008;
    public static final int SETTING_FUNC_SAY_HI = 539427840;
    public static final int SETTING_FUNC_SCREEN_SAVER_CUSTOM_NAME = 539034624;
    public static final int SETTING_FUNC_SCREEN_SAVER_CUSTOM_PICTURE = 539035136;
    public static final int SETTING_FUNC_SCREEN_SAVER_CUSTOM_TEXT = 539034880;
    public static final int SETTING_FUNC_SCREEN_SAVER_STYLE = 539034368;
    public static final int SETTING_FUNC_SCREEN_SAVER_TIME = 539035392;
    public static final int SETTING_FUNC_SEATBELT_WARNING_SOUND = 537333505;
    public static final int SETTING_FUNC_SEB_POP = 538379520;
    public static final int SETTING_FUNC_SET_WALLPAPER_TO_DIM = 540283136;
    public static final int SETTING_FUNC_SOUND_WARNING_VOLUME = 538771712;

    @Deprecated
    public static final int SETTING_FUNC_SPEED_CONTROL = 537068032;
    public static final int SETTING_FUNC_SPEED_CONTROL_MODE = 537069056;
    public static final int SETTING_FUNC_SPEED_GUIDANCE_ALARM = 539493632;

    @Deprecated
    public static final int SETTING_FUNC_SPEED_LIMITATION = 537067776;
    public static final int SETTING_FUNC_SPEED_LIMITATION_MODE = 537068800;
    public static final int SETTING_FUNC_SPEED_LOCKING = 537921024;
    public static final int SETTING_FUNC_STEERING_ASSISTANCE_LEVEL = 537331712;
    public static final int SETTING_FUNC_STEERING_WHEEL_ANGLE_WARN_SWITCH = 539430144;
    public static final int SETTING_FUNC_SUNROOF_TRANSPARENCY_AUTO = 537396992;
    public static final int SETTING_FUNC_SUPER_POWER_SAVING = 539516928;
    public static final int SETTING_FUNC_SUSPENSION_DEACTIVATION_DAMPENING = 538509824;
    public static final int SETTING_FUNC_SUSPENSION_DRIVER_ENTRY_CONTROL = 538510080;
    public static final int SETTING_FUNC_SUSPENSION_HEIGHT_ADJUST = 538509568;
    public static final int SETTING_FUNC_TCAM_5G_SWITCH = 538314496;
    public static final int SETTING_FUNC_TCAM_RESET = 538314240;
    public static final int SETTING_FUNC_TEM_PROVISIONING_STATE = 538313984;
    public static final int SETTING_FUNC_THREE_SCREEN_ANIMATION = 540148992;
    public static final int SETTING_FUNC_TRACK_GESTURE = 539494400;
    public static final int SETTING_FUNC_TRACK_MODE = 538904576;
    public static final int SETTING_FUNC_TRAFFIC_LIGHT_ATTENTION = 537332992;
    public static final int SETTING_FUNC_TRAFFIC_SIGN_RECOGNITION = 537592064;
    public static final int SETTING_FUNC_TRAFFIC_SIGN_RECOGNITION_ALERT = 537592320;
    public static final int SETTING_FUNC_TRAILER_MODE = 537268736;
    public static final int SETTING_FUNC_TWOSTEP_UNLOCKING = 537922048;
    public static final int SETTING_FUNC_UNLOCK_P_GEAR = 539427072;
    public static final int SETTING_FUNC_VEHICLE_SAFETY_ALARM = 539494912;
    public static final int SETTING_FUNC_VEHICLE_SAFETY_ALARM_MODE = 539497728;
    public static final int SETTING_FUNC_VISIBLE_LOCKING_FEEDBACK = 537919744;
    public static final int SETTING_FUNC_VISIBLE_UNLOCKING_FEEDBACK = 537920000;
    public static final int SETTING_FUNC_VOICE_CONTROL_LOCKING = 540284160;
    public static final int SETTING_FUNC_VOICE_KEY_DISTANCE = 540283904;
    public static final int SETTING_FUNC_VOICE_POWER_MODE_SET = 540283648;
    public static final int SETTING_FUNC_VOICE_RECOGNITION = 538706688;
    public static final int SETTING_FUNC_VOICE_SEARCH_KEY = 540283392;
    public static final int SETTING_FUNC_VOLUME_LIMIT = 539495168;
    public static final int SETTING_FUNC_VOLUME_LIMIT_MAX = 539495424;
    public static final int SETTING_FUNC_VSTD_FAIL_TO_OPEN_TELM = 536953600;
    public static final int SETTING_FUNC_VSTD_VFC_VEHICLE_SENTRY_FT_DET_CH = 536954368;
    public static final int SETTING_FUNC_VSTD_VFC_VEHICLE_SENTRY_FT_DET_PS = 536954624;
    public static final int SETTING_FUNC_VSTD_VFC_VEHICLE_SENTRY_FT_DET_SV = 536954880;
    public static final int SETTING_FUNC_VSTD_VIDEO_UPLOAD_STATUS = 540285184;
    public static final int SETTING_FUNC_WAITING_MODE = 539428352;
    public static final int SETTING_FUNC_WALLPAPER_FESTIVAL_TYPE = 539496192;
    public static final int SETTING_FUNC_WALLPAPER_SYNC = 539033856;
    public static final int SETTING_FUNC_WALLPAPER_SYNC_STYLE = 539034112;
    public static final int SETTING_FUNC_WELCOME_SOUND = 539099392;
    public static final int SETTING_FUNC_WELCOME_SOUND_TYPE = 539099648;
    public static final int SETTING_FUNC_WINDOW_CLOSE_SUNCURTAIN = 537395456;
    public static final int SETTING_FUNC_WINDOW_PINCH_WARN = 537396480;
    public static final int SETTING_FUNC_WINDOW_TRANSPARENCY_AUTO = 539508736;
    public static final int SETTING_FUNC_WINDOW_VENTILATE = 537396736;
    public static final int SETTING_FUNC_WINDSCREEN_SERVICE_POSITION = 537657600;
    public static final int SETTING_FUNC_XCALL_KEY_LOCK = 538313216;
    public static final int SETTING_TYPE_VSTD_MODE_STS = 536951296;
    public static final int SETTING_VALUE_VSTD_MODE_STS_ALARM = 536951301;
    public static final int SETTING_VALUE_VSTD_MODE_STS_ALERT = 536951300;
    public static final int SETTING_VALUE_VSTD_MODE_STS_OFF = 536951297;
    public static final int SETTING_VALUE_VSTD_MODE_STS_ON = 536951299;
    public static final int SETTING_VALUE_VSTD_MODE_STS_STANDBY = 536951298;
    public static final int SETTING_VALUE_VSTD_VIDEO_UPLOAD_COLLECTING = 540285186;
    public static final int SETTING_VALUE_VSTD_VIDEO_UPLOAD_COMPLETE = 540285187;
    public static final int SETTING_VALUE_VSTD_VIDEO_UPLOAD_ERROR = 540285188;
    public static final int SETTING_VALUE_VSTD_VIDEO_UPLOAD_INITIAL = 540285185;
    public static final int SOUND_WARNING_VOLUME_LEVEL_HIGH = 538771715;
    public static final int SOUND_WARNING_VOLUME_LEVEL_LOW = 538771713;
    public static final int SOUND_WARNING_VOLUME_LEVEL_MID = 538771714;
    public static final int SOUND_WARNING_VOLUME_LEVEL_OFF = 0;
    public static final int SPEED_CONTROL_MODE_ACC = 537069058;
    public static final int SPEED_CONTROL_MODE_CC = 537069057;
    public static final int SPEED_CONTROL_MODE_GPILOT = 537069059;
    public static final int SPEED_CONTROL_MODE_OFF = 0;
    public static final int SPEED_LIMITATION_MODE_ASL = 537068802;
    public static final int SPEED_LIMITATION_MODE_AVSL = 537068801;
    public static final int SPEED_LIMITATION_MODE_OFF = 0;
    public static final int STEERING_ASSISTANCE_LEVEL_AUTO = 537331716;
    public static final int STEERING_ASSISTANCE_LEVEL_HIGH = 537331713;
    public static final int STEERING_ASSISTANCE_LEVEL_LOW = 537331715;
    public static final int STEERING_ASSISTANCE_LEVEL_MEDIUM = 537331714;
    public static final int STEERING_ASSISTANCE_LEVEL_OFF = 0;
    public static final int SUSPENSION_HEIGHT_ADJUST_LEVEL_HIGH_1 = 538509570;
    public static final int SUSPENSION_HEIGHT_ADJUST_LEVEL_HIGH_2 = 538509569;
    public static final int SUSPENSION_HEIGHT_ADJUST_LEVEL_LOW_1 = 538509572;
    public static final int SUSPENSION_HEIGHT_ADJUST_LEVEL_LOW_2 = 538509573;
    public static final int SUSPENSION_HEIGHT_ADJUST_LEVEL_NORMAL = 538509571;
    public static final int SUSPENSION_HEIGHT_ADJUST_LEVEL_OFF = 0;

    @Deprecated
    public static final int TWOSTEP_UNLOCKING_ALL_DOORS = 537922049;
    public static final int TWOSTEP_UNLOCKING_OFF = 0;
    public static final int TWOSTEP_UNLOCKING_ON = 1;

    @Deprecated
    public static final int TWOSTEP_UNLOCKING_SINGLE_DOOR = 537922050;
    public static final int VEHICLE_SAFETY_ALARM_VISUAL = 539497730;
    public static final int VEHICLE_SAFETY_ALARM_VISUAL_SOUND = 539497729;
    public static final int VOICE_DISTANCE_KEY0 = 1;
    public static final int VOICE_DISTANCE_KEY1 = 2;
    public static final int VOICE_DISTANCE_KEY2 = 3;
    public static final int VOICE_DISTANCE_KEY3 = 4;
    public static final int VOICE_DISTANCE_KEY4 = 5;
    public static final int VOICE_KEY_DISTANCE_DEFAULT = 540283909;
    public static final int VOICE_KEY_DISTANCE_FOUND_OUTSIDE = 540283907;
    public static final int VOICE_KEY_DISTANCE_FOUND_OUT_TAIL = 540283906;
    public static final int VOICE_KEY_DISTANCE_INSIDE = 540283905;
    public static final int VOICE_KEY_DISTANCE_NOT_FOUND = 540283908;
    public static final int VOICE_KEY_SEARCH_STATUS_COMPLETE = 540283396;
    public static final int VOICE_KEY_SEARCH_STATUS_FAILED = 540283395;
    public static final int VOICE_KEY_SEARCH_STATUS_IDLE = 540283393;
    public static final int VOICE_KEY_SEARCH_STATUS_IN_PROGRESS = 540283394;

    @Retention(RetentionPolicy.SOURCE)
    public @interface AVASSoundType {
        public static final int AVAS_SOUND_TYPE_1 = 538576641;
        public static final int AVAS_SOUND_TYPE_2 = 538576642;
        public static final int AVAS_SOUND_TYPE_3 = 538576643;
        public static final int AVAS_SOUND_TYPE_4 = 538576644;
        public static final int AVAS_SOUND_TYPE_5 = 538576645;
        public static final int AVAS_SOUND_TYPE_6 = 538576646;
        public static final int AVAS_SOUND_TYPE_7 = 538576647;
        public static final int AVAS_SOUND_TYPE_8 = 538576648;
        public static final int AVAS_SOUND_TYPE_NONE = 0;
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface AVASVolumeLevel {
        public static final int AVAS_VOLUME_LEVEL_HIGH = 538576387;
        public static final int AVAS_VOLUME_LEVEL_LOW = 538576385;
        public static final int AVAS_VOLUME_LEVEL_MID = 538576386;
        public static final int AVAS_VOLUME_LEVEL_OFF = 0;
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface AbnormalVehicleAlarmMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface AmbienceLightExperienceMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface AmbienceLightMainColorMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ArtificialSoundType {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface AutoCLoseWindowMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface AutoShowModeStatus {
        public static final int MEET = 540279042;
        public static final int NORMAL = 540279041;
        public static final int NOT_MEET = 540279043;
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface AutoShowModeText {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface BrightnessDrivingMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface BrightnessStationaryMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface CDCModeLevel {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface CarLocatorReminderMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ChrgSrtEngRes {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface CsdDispPosnStatus {
        public static final int DISPPOSN_POSNA = 539504641;
        public static final int DISPPOSN_POSNB = 539504642;
        public static final int DISPPOSN_UNKWN = 539504641;
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface DTEShowType {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface DangerousRoadAlarmMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface DayModeSettings {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface DigitalKeyReqStsStatus {
        public static final int CREATING = 1;
        public static final int DELETING = 3;
        public static final int IDLE = 1;
        public static final int PREPARING_DATA = 6;
        public static final int RESETTING = 2;
        public static final int SEARCHING_CARD = 4;
        public static final int SEARCHING_PHONE = 5;
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface DigitalKeySuspensionStatus {
        public static final int IDLE = 0;
        public static final int REJECT_BNCM = 5;
        public static final int REJECT_CARMOD = 3;
        public static final int REJECT_KEY_NOT_EXIST = 4;
        public static final int REJECT_USGMOD = 2;
        public static final int SUCCESS = 1;
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface DigitalKeyTerminationStatus {
        public static final int IDLE = 0;
        public static final int REJECT_BNCM = 6;
        public static final int REJECT_CARMOD = 3;
        public static final int REJECT_KEY_NOT_EXIST = 4;
        public static final int REJECT_NO_KEY = 5;
        public static final int REJECT_USGMOD = 2;
        public static final int SUCCESS = 1;
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface DigitalKeyUnpairStatus {
        public static final int IDLE = 0;
        public static final int REJECT_BNCM = 6;
        public static final int REJECT_CARMOD = 3;
        public static final int REJECT_NO_KEY = 5;
        public static final int REJECT_NO_MOBILE = 4;
        public static final int REJECT_USGMOD = 2;
        public static final int SUCCESS = 1;
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface DoorOpenWarnVolumeMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ESCMode {
        public static final int ESC_MODE_DRIFT = 537002753;
        public static final int ESC_MODE_OFF = 0;
        public static final int ESC_MODE_STANDARD = 1;
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ESMVolumeLevel {
    }

    public @interface EasyIngressEgressMode {
        public static final int GET_OFF = 538378754;
        public static final int GET_ON = 538378753;
        public static final int GET_ON_AND_OFF = 538378755;
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface EmergencyVehicleAlarmMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface EnergyPredictionMsg {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface EnergyPredictionStatus {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface EnergyRegenerationLevel {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface EnvironmentaProtectionLevel {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface EsmFuncType {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ExteriorLightControlValue {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface FestivalType {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ForwardCollisionWarnSnvtyMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface GlovboxLockState {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface HeadRestraintAudioType {
        public static final int SETTING_FUNC_HEAD_RESTRAINT_AUDIO_DRVING = 539099906;
        public static final int SETTING_FUNC_HEAD_RESTRAINT_AUDIO_PRIVATE = 539099907;
        public static final int SETTING_FUNC_HEAD_RESTRAINT_AUDIO_SHARE = 539099905;
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface HmiThemesValue {
    }

    public @interface HolographicBacklightMode {
        public static final int AUTO = 539500289;
        public static final int MANUAL = 539500290;
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface HomeSafeLightValue {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface KeyResponseType {
        public static final int CREATE_TIMEOUT = 2;
        public static final int DELETE_ALL_TIMEOUT = 3;
        public static final int DELETE_TIMEOUT = 1;
        public static final int IDLE = 0;
        public static final int READ_KEYLIST_TIMEOUT = 4;
        public static final int RESERVE_TIME = 5;
        public static final int RESERVE_TIME_FOUR = 8;
        public static final int RESERVE_TIME_THREE = 7;
        public static final int RESERVE_TIME_TWO = 6;
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface KeylessUnlockingValue {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface LampAutoLightValue {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface LampHighbeamActiveValue {
    }

    public @interface LaneChangeWarningMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface LaneChangeWarningSoundMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface LaneKeepingAidMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface LaneKeepingAidWarningMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface LaunchModeIndcnStatus {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface LeftTurnAssistAlarmMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface MirrorDimmingLevel {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface MirrorDippingMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface MultiFuncKnobStatus {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface PEBWorkMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface PairingStatus {
        public static final int AUTHB_FAL = 35;
        public static final int AUTH_FAL = 34;
        public static final int CERTIFICATE_INVALID = 12;
        public static final int ERROR_MSG = 21;
        public static final int EXCHANGE_COMFAL = 36;
        public static final int GETDATAFAL = 25;
        public static final int IDLE = 0;
        public static final int PROTOCOL_MISMATCH = 20;
        public static final int RESEVRVED = 1;
        public static final int SECURE_CREATFAL = 22;
        public static final int TIMEOUT_FIVE = 33;
        public static final int TIMEOUT_FOUR = 32;
        public static final int TIMEOUT_ONE = 18;
        public static final int TIMEOUT_THREE = 24;
        public static final int TIMEOUT_TWO = 19;
        public static final int UTC_TI_LOST = 9;
        public static final int VERIFIER_UPDATED = 2;
        public static final int WRITE_DATAFAL = 23;
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ParkAssistSysVolumeMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ParkComfortModeOffReasonMode {
        public static final int LOW_SOC = 538838273;
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ParkingComfortModeTimer {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ParkingGearUnlockTyp {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface PowerBattery {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface PrivateLockReminder {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface RainSensitivityTyp {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface RearSpoilerAdjustFailStatus {
        public static final int NORMAL = 0;
        public static final int PLEASE_CLOSE = 2;
        public static final int STALL = 1;
        public static final int TRAILER_MODE = 3;
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface RearSpoilerAdjustMode {
        public static final int AUTO = 538510850;
        public static final int MANUAL = 538510849;
        public static final int OFF = 0;
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface RearSpoilerPositionAdjust {
        public static final int REAR_SPOILER_POSN_ADJUST_OFF = 0;
        public static final int REAR_SPOILER_POSN_SHORT_PRESS_P1 = 538510593;
        public static final int REAR_SPOILER_POSN_SHORT_PRESS_P2 = 538510594;
        public static final int REAR_SPOILER_POSN_SHORT_PRESS_P3 = 538510595;
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface RearSpoilernAdjustLevel {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface RedLightAlarmMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface RefuelingSwtTyp {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface RemoteUnlockingValue {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ReverseOvertakingAlarmMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface RotatedWheelsWarningInfo {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ScreenSaveTime {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ScreenSaverStyle {
        public static final int SCREEN_SAVER_STYLE_1 = 539034369;
        public static final int SCREEN_SAVER_STYLE_2 = 539034370;
        public static final int SCREEN_SAVER_STYLE_3 = 539034371;
        public static final int SCREEN_SAVER_STYLE_4 = 539034372;
        public static final int SCREEN_SAVER_STYLE_5 = 539034373;
        public static final int SCREEN_SAVER_STYLE_CUSTOM = 539034623;
        public static final int SCREEN_SAVER_STYLE_NONE = 0;
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface SoundWarningVolume {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface SpeedControlMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface SpeedLimitationMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface SteeringAssistanceLevel {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface StreamMirrorZoomLevel {
        public static final int STREAM_MIRROR_ZOOM_LEVEL_LARGE = 2;
        public static final int STREAM_MIRROR_ZOOM_LEVEL_MIDDLE = 1;
        public static final int STREAM_MIRROR_ZOOM_LEVEL_NORMAL = 0;
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface SuspensionAdjustLevel {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface TrackResultType {
        public static final int IDLE = 0;
        public static final int RECEIPT_INVALID = 3;
        public static final int SUCCESS = 1;
        public static final int TRACKING_RECEIPT = 2;
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface TrackWaitType {
        public static final int IDLE = 0;
        public static final int SUCCESS = 1;
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface TwoStepUnlockingValue {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface VehicleFunction {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface VehicleSafetyAlarmMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface VoiceKeyDistance {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface VoiceKeyDistanceZone {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface VoiceKeyStatus {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface VstdModeSts {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface VstdVideoUploadSts {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface WallpaperStyle {
        public static final int WALLPAPER_STYLE_1 = 539034113;
        public static final int WALLPAPER_STYLE_2 = 539034114;
        public static final int WALLPAPER_STYLE_3 = 539034115;
        public static final int WALLPAPER_STYLE_4 = 539034116;
        public static final int WALLPAPER_STYLE_5 = 539034117;
        public static final int WALLPAPER_STYLE_AUTUMN_EQUINOX = 539034133;
        public static final int WALLPAPER_STYLE_BEGINNING_AUTUMN = 539034130;
        public static final int WALLPAPER_STYLE_BEGINNING_SPRING = 539034118;
        public static final int WALLPAPER_STYLE_BEGINNING_SUMMER = 539034124;
        public static final int WALLPAPER_STYLE_BEGINNING_WINTER = 539034136;
        public static final int WALLPAPER_STYLE_CHINESE_VALENTINE_DAY = 539034146;
        public static final int WALLPAPER_STYLE_CHRISTMAS = 539034150;
        public static final int WALLPAPER_STYLE_COLD_DEW = 539034134;
        public static final int WALLPAPER_STYLE_DRAGON_BOAT = 539034145;
        public static final int WALLPAPER_STYLE_END_HEAT = 539034131;
        public static final int WALLPAPER_STYLE_FROST_DESCENT = 539034135;
        public static final int WALLPAPER_STYLE_FULLNESS_GRAIN = 539034125;
        public static final int WALLPAPER_STYLE_GRAIN_BEARD = 539034126;
        public static final int WALLPAPER_STYLE_GRAIN_RAIN = 539034123;
        public static final int WALLPAPER_STYLE_GREATER_COLD = 539034141;
        public static final int WALLPAPER_STYLE_GREATER_HEAT = 539034129;
        public static final int WALLPAPER_STYLE_GREATER_SNOW = 539034138;
        public static final int WALLPAPER_STYLE_LABOUR_DAY = 539034144;
        public static final int WALLPAPER_STYLE_LESSER_COLD = 539034140;
        public static final int WALLPAPER_STYLE_LESSER_HEAT = 539034128;
        public static final int WALLPAPER_STYLE_LESSER_SNOW = 539034137;
        public static final int WALLPAPER_STYLE_MID_AUTUMN = 539034147;
        public static final int WALLPAPER_STYLE_NATIONAL_DAY = 539034148;
        public static final int WALLPAPER_STYLE_NEW_YEAR_DAY = 539034142;
        public static final int WALLPAPER_STYLE_NONE = 0;
        public static final int WALLPAPER_STYLE_PURE_BRIGHTNESS = 539034122;
        public static final int WALLPAPER_STYLE_RAIN_WATER = 539034119;
        public static final int WALLPAPER_STYLE_SPRING_EQUINOX = 539034121;
        public static final int WALLPAPER_STYLE_SPRING_FESTIVAL = 539034149;
        public static final int WALLPAPER_STYLE_SUMMER_SOLSTICE = 539034127;
        public static final int WALLPAPER_STYLE_VALENTINE_DAY = 539034143;
        public static final int WALLPAPER_STYLE_WAKING_INSECTS = 539034120;
        public static final int WALLPAPER_STYLE_WHITE_DEW = 539034132;
        public static final int WALLPAPER_STYLE_WINTER_SOLSTICE = 539034139;
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface WelcomeSoundType {
        public static final int WELCOME_SOUND_TYPE_1 = 539099649;
        public static final int WELCOME_SOUND_TYPE_2 = 539099650;
        public static final int WELCOME_SOUND_TYPE_3 = 539099651;
        public static final int WELCOME_SOUND_TYPE_NONE = 0;
    }
}
