package com.ecarx.xui.adaptapi.diminteraction;

import android.graphics.Bitmap;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: C:\Users\Andrei\AppData\Local\Temp\jadx-7518071738844169467\classes.dex */
public interface IPhoneCallInteraction {
    public static final int CALL_STATUS_BUSY = 9;
    public static final int CALL_STATUS_CALLBACK_REJECTED = 13;
    public static final int CALL_STATUS_CONNECTING = 2;
    public static final int CALL_STATUS_CONNECT_FAILED = 3;
    public static final int CALL_STATUS_DATA_UPLOADING = 4;
    public static final int CALL_STATUS_DATA_UPLOAD_FAILED = 5;
    public static final int CALL_STATUS_DIALING = 6;
    public static final int CALL_STATUS_DIAL_FAILED = 7;
    public static final int CALL_STATUS_END = 15;
    public static final int CALL_STATUS_HANGING_UP = 14;
    public static final int CALL_STATUS_IDLE = 0;
    public static final int CALL_STATUS_IN_COMING_CALL = 12;
    public static final int CALL_STATUS_OFFHOOK = 11;
    public static final int CALL_STATUS_ON_CALL = 16;
    public static final int CALL_STATUS_ON_HOLD = 17;
    public static final int CALL_STATUS_REJECTED = 10;
    public static final int CALL_STATUS_RINGING = 8;
    public static final int CALL_STATUS_START_FAILED = 1;

    @Retention(RetentionPolicy.SOURCE)
    public @interface CallStatus {
    }

    public interface ICallInfo {
        int getActiveCallId();

        Bitmap getAvatar();

        int getCallCount();

        long getCallDuration();

        int getCallId();

        int getCallStatus();

        String getCompany();

        String getContactName();

        String getContactNumber();

        String getTitle();

        boolean isHandFree();

        boolean isMicOnVehicleMuted();

        boolean isRingtoneMuted();
    }

    public interface IPhoneCallInteractionCallback {
        void onAnswerAndEndCall(String str);

        void onAnswerAndHoldCall(String str);

        void onAnswerCall(String str);

        void onCallInfoUpdateRequired();

        void onEndCall(String str);

        void onRejectCall(String str);

        void onRequestConnectedMobileDeviceInfo();

        void onSwitchCall();

        void onSwitchChannel(int i);

        void onSwitchMicMode(int i);

        void onSwitchRingtoneMuteMode(int i);

        void placeCall(String str);
    }

    void registerPhoneCallback(IPhoneCallInteractionCallback iPhoneCallInteractionCallback);

    void unRegisterPhoneCallback(IPhoneCallInteractionCallback iPhoneCallInteractionCallback);

    void updateCallInfo(ICallInfo iCallInfo);

    void updateConnectedMobileDeviceState(String str, int i, int i2);

    void updateSecondCallInfo(ICallInfo iCallInfo);

    void writeCallLog2DBCompleted();

    void writeContact2DBCompleted();
}