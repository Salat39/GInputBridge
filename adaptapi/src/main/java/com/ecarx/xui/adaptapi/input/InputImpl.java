package com.ecarx.xui.adaptapi.input;

import android.content.Context;
import android.os.HandlerThread;
import android.util.Log;

import com.ecarx.xui.adaptapi.FunctionStatus;

import ecarx.fw.api.ECarXAPI;
import ecarx.fw.api.business.IEcarXBusiness;
import ecarx.fw.api.business.hardkey.IEcarXHardKey;
import ecarx.fw.api.exceptions.APIInstantiationException;

/* loaded from: ecarx.adaptapi.jar:com/ecarx/xui/adaptapi/input/InputImpl.class */
public class InputImpl extends Input implements IInputSettings {
    private static final String CLIENT_GKUI = "ecarx.xsf.inputservice";
    private static final String CLIENT_HICAR = "com.ecarx.hmi";
    private static final String CLIENT_MONITOR = "com.ecarx.hardkeytest";
    private static final boolean DEBUG = true;
    private static final String TAG = "InputImpl";
    private static InputImpl sInstance;
    private Context mContext;
    private int mCookie;
    private HandlerThread mHandlerThread;
    private IEcarXHardKey mHardKeyManager;
    private IKeyCallback mKeyCallback;
    private final Object mLock = new Object();

    private InputImpl(Context context) {
        this.mCookie = 0;
        this.mContext = context;
        initHardKeyManager(context);
        String packageName = this.mContext.getPackageName();
        Log.v(TAG, "enter constructor calling app: " + packageName);
        if (packageName.equals(CLIENT_MONITOR)) {
            this.mCookie = 36;
        } else if (packageName.equals(CLIENT_GKUI)) {
            this.mCookie = 0;
        } else if (packageName.equals(CLIENT_HICAR)) {
            this.mCookie = 1;
        } else {
            Log.w(TAG, "app was treated as cookie_gkui");
        }
        HandlerThread handlerThread = new HandlerThread("InputImplHandler");
        this.mHandlerThread = handlerThread;
        handlerThread.start();
    }

    public static InputImpl create(Context context) {
        InputImpl inputImpl;
        synchronized (InputImpl.class) {
            if (context != null) {
                try {
                    sInstance = new InputImpl(context);
                } finally {
                }
            }
            inputImpl = sInstance;
        }
        return inputImpl;
    }

    private void initHardKeyManager(Context context) {
        try {
            this.mHardKeyManager = ((IEcarXBusiness) ECarXAPI.creator(IEcarXBusiness.class).create(context)).getEcarXHardKey();
        } catch (APIInstantiationException e) {
            e.printStackTrace();
        }
    }

    private boolean isServiceConnected() {
        if (this.mHardKeyManager != null) {
            return true;
        }
        Log.i(TAG, "service disconnected!, plz connect hardkeyservice");
        return false;
    }

    @Override // com.ecarx.xui.adaptapi.input.Input
    public boolean abandonKeysInterception(IKeyCallback iKeyCallback) {
        if (!isServiceConnected()) {
            return false;
        }
        this.mKeyCallback = null;
        int i = this.mCookie;
        if (i == -1) {
            Log.w(TAG, "unsupport client abandon key interception");
            return false;
        }
        return true;
    }

    @Override // com.ecarx.xui.adaptapi.input.IInputSettings
    public int getCustomFunctionType() {
        if (isServiceConnected()) {
            return this.mHardKeyManager.getCustomFunctionType();
        }
        return -1;
    }

    @Override // com.ecarx.xui.adaptapi.input.IInputSettings
    public long getInputSettingDuration(int i) {
        if (isServiceConnected()) {
            return this.mHardKeyManager.getInputSettingDuration(i);
        }
        return -1L;
    }

    @Override // com.ecarx.xui.adaptapi.input.IInputSettings
    public int getInputSettingValue(int i) {
        if (isServiceConnected()) {
            return this.mHardKeyManager.getInputSettingValue(i);
        }
        return -1;
    }

    @Override // com.ecarx.xui.adaptapi.input.Input
    public IInputSettings getInputSettings() {
        if (sInstance == null) {
            sInstance = new InputImpl(this.mContext);
        }
        return sInstance;
    }

    @Override // com.ecarx.xui.adaptapi.input.IInputSettings
    public int getSteerWheelType() {
        if (isServiceConnected()) {
            return this.mHardKeyManager.getSteerWheelType();
        }
        return -1;
    }

    @Override // com.ecarx.xui.adaptapi.input.Input
    public FunctionStatus isInputSettingsSupported() {
        return FunctionStatus.active;
    }

    @Override // com.ecarx.xui.adaptapi.input.Input
    public int[] requestKeysInterception(int[] iArr, IKeyCallback iKeyCallback) {
        if (!isServiceConnected()) {
            return new int[0];
        }
        int i = this.mCookie;
        if (i == -1) {
            Log.w(TAG, "unsupport client request key interception");
            return new int[0];
        }
        this.mKeyCallback = iKeyCallback;
        return null;
    }
}
