package com.ecarx.xui.adaptapi.input;

import android.content.Context;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;

/* loaded from: ecarx.adaptapi.jar:com/ecarx/xui/adaptapi/input/InputMethodImpl.class */
public class InputMethodImpl extends InputMethod {
    private static final String TAG = "InputMethodImpl";
    public static Context mContext;
    private IInputMethodCallback mInputMethodCallback = null;
    private InputMethodManager mInputMethodManager;

    private InputMethodImpl(Context context) {

    }

    public static InputMethodImpl create(Context context) {
        synchronized (InputMethodImpl.class) {
            try {
                if (context == null) {
                    Log.d(TAG, " InputMethodImpl context null");
                    return null;
                }
                Log.d(TAG, " InputMethodImpl create");
                return new InputMethodImpl(context);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // com.ecarx.xui.adaptapi.input.InputMethod
    public void registerInputMethodVisibleChangedListener(IInputMethodCallback iInputMethodCallback) {
        Log.d(TAG, "registerInputMethodVisibleChangedListener");
        this.mInputMethodCallback = iInputMethodCallback;
    }

    @Override // com.ecarx.xui.adaptapi.input.InputMethod
    public void unregisterInputMethodVisibleChangedListener(IInputMethodCallback iInputMethodCallback) {
        Log.d(TAG, "unregisterInputMethodVisibleChangedListener");
        this.mInputMethodCallback = null;
    }
}