package com.ecarx.xui.adaptapi.diminteraction;

import android.os.Bundle;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: C:\Users\Andrei\AppData\Local\Temp\jadx-7518071738844169467\classes.dex */
public interface IVendorInteraction {
    public static final String EXT_KEY_APP_PACKAGE_NAME = "EXT_KEY_APP_PACKAGE_NAME";
    public static final int VENDOR_ACTION_TYPE_NONE = 0;
    public static final int VENDOR_DATA_TYPE_DEFAULT = 0;
    public static final int VENDOR_DATA_TYPE_ILLEGAL = Integer.MIN_VALUE;

    public interface IVendorInformation {
        int getType();

        Bundle getVendorExtras();
    }

    public interface IVendorInteractionCallback {
        void onInteractionAction(int i, IVendorInformation iVendorInformation);
    }

    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface VendorActionType {
    }

    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface VendorDataType {
    }

    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface VendorExtrasKey {
    }

    boolean registerVendorInteractionCallback(IVendorInteractionCallback iVendorInteractionCallback);

    boolean unRegisterVendorInteractionCallback(IVendorInteractionCallback iVendorInteractionCallback);

    boolean updateVendorInformation(IVendorInformation iVendorInformation);
}