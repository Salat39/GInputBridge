package com.ecarx.xui.adaptapi.input;

import android.content.Context;
import com.ecarx.xui.adaptapi.AdaptAPI;

/* loaded from: ecarx.adaptapi.jar:com/ecarx/xui/adaptapi/input/InputMethod.class */
public abstract class InputMethod extends AdaptAPI {
    public static InputMethod create(Context context) {
        return InputMethodImpl.create(context);
    }

    public abstract void registerInputMethodVisibleChangedListener(IInputMethodCallback iInputMethodCallback);

    public abstract void unregisterInputMethodVisibleChangedListener(IInputMethodCallback iInputMethodCallback);
}