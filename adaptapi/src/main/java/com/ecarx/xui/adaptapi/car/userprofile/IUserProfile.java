package com.ecarx.xui.adaptapi.car.userprofile;

import android.os.Bundle;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public interface IUserProfile {
    public static final int ACTION_TYPE_FACE_BIND = 33;
    public static final int ACTION_TYPE_FACE_UNBIND = 34;
    public static final int ACTION_TYPE_PROFILE_ADD = 1;
    public static final int ACTION_TYPE_PROFILE_APPLY = 6;
    public static final int ACTION_TYPE_PROFILE_BIND = 17;
    public static final int ACTION_TYPE_PROFILE_LOGIN = 3;
    public static final int ACTION_TYPE_PROFILE_LOGOUT = 4;
    public static final int ACTION_TYPE_PROFILE_REMOVE = 2;
    public static final int ACTION_TYPE_PROFILE_RESET = 7;
    public static final int ACTION_TYPE_PROFILE_SWITCH = 5;
    public static final int ACTION_TYPE_PROFILE_UNBIND = 18;
    public static final int ACTION_TYPE_PROFILE_UNBIND_RESET = 19;
    public static final int ADJUST_ITEM_ELECTRONIC_REAR_MIRROR = 16;
    public static final int ADJUST_ITEM_HUD = 8;
    public static final int ADJUST_ITEM_REAR_MIRROR = 4;
    public static final int ADJUST_ITEM_SEAT = 2;
    public static final int ADJUST_ITEM_WHEEL = 1;
    public static final int ERROR_CODE_TIMEOUT = 1;
    public static final int ERROR_CODE_UNKNOWN = 0;
    public static final int LOGIN_TYPE_CARKEY = 5;
    public static final int LOGIN_TYPE_DIGITALKEY = 6;
    public static final int LOGIN_TYPE_FACEID = 3;
    public static final int LOGIN_TYPE_FINGERPRINT = 2;
    public static final int LOGIN_TYPE_UNKNOWN = 0;
    public static final int LOGIN_TYPE_USER_ACC = 1;
    public static final int LOGIN_TYPE_VOICE = 4;
    public static final int STATUS_ADJUSTED = 2;
    public static final int STATUS_ADJUSTING = 1;
    public static final int STATUS_FAILED = 4;
    public static final int STATUS_PREPARE = 1;
    public static final int STATUS_PROGRESS = 2;
    public static final int STATUS_SUCCEED = 3;
    public static final int STATUS_UNKNOWN = 0;

    @Retention(RetentionPolicy.SOURCE)
    public @interface ActionStatus {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ActionType {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface AdjustItem {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface AdjustStatus {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ErrorCode {
    }

    public interface IPreferenceObserver {
        void onItemAdjusted(int i, int[] iArr);

        void onPreferenceStateChange(int i, int i2);

        void onPreferenceSwitched(int i);
    }

    public interface IUserProfileObserver {
        void onUserProfileActionError(int i, int i2);

        void onUserProfileActionStatus(int i, int i2, int i3);

        void onUserProfileAdded(int i);
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ProfileLoginType {
    }

    int addUserProfile();

    int addUserProfileCopyFrom(int i);

    boolean applyUserProfileData(int i, IProfile iProfile);

    int getCurrentId();

    int getPreferenceId(int i);

    int getProfileId(String str);

    int getProfileLoginType(int i);

    int getSupportUserProfileCount();

    String getUserId(int i);

    int[] getUserProfileAdjusted();

    int[] getUserProfileCreated();

    IProfile getUserProfileData(int i);

    boolean isNeedLogin();

    boolean loginUserProfile(int i);

    boolean logoutUserProfile(int i);

    boolean notifyUIDInfoToProfile(int i, String str, Bundle bundle);

    boolean refreshPreference(int i);

    boolean registerPreferenceObserver(IPreferenceObserver iPreferenceObserver);

    boolean registerUserProfileObserver(IUserProfileObserver iUserProfileObserver);

    boolean removeUserProfile(int i);

    boolean resetUserProfileDataDefault(int i);

    boolean restorePreference();

    boolean restorePreference(int i);

    boolean saveCurrentUserProfile();

    boolean saveToPreference(int i);

    boolean setDefaultPreference(int i);

    boolean stopRestorePreference();

    boolean switchPreference(int i);

    boolean switchUserProfile(int i, int i2);

    boolean unregisterPreferenceObserver(IPreferenceObserver iPreferenceObserver);

    boolean unregisterUserProfileObserver(IUserProfileObserver iUserProfileObserver);
}
