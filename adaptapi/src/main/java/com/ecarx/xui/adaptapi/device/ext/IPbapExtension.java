package com.ecarx.xui.adaptapi.device.ext;

import android.os.Bundle;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public interface IPbapExtension {
    public static final int STATUS_COMPLETED = 3;
    public static final int STATUS_FAILED = 4;
    public static final int STATUS_IN_SYNC = 2;
    public static final int STATUS_NOT_SUPPORT = 5;
    public static final int STATUS_START = 1;
    public static final int STATUS_UNKNOWN = 0;
    public static final int TYPE_CALL_LOG = 2;
    public static final int TYPE_CONTACT = 1;
    public static final int TYPE_DEFAULT = 0;
    public static final int TYPE_FAVORITE = 3;

    @Retention(RetentionPolicy.SOURCE)
    public @interface ContactType {
    }

    public interface IPbapListener {
        void onSyncPhonebookStatusChanged(int i, int i2);
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface SyncStatus {
    }

    String getDecryptContactName(String str, String str2, Bundle bundle);

    int getPhoneBookContactsCount();

    int getSyncPhonebookStatus();

    boolean registerPbapListener(IPbapListener iPbapListener);

    boolean syncPhonebook(int i);

    boolean unregisterPbapListener(IPbapListener iPbapListener);
}
