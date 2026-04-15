package com.ecarx.xui.adaptapi.device.ext;

public interface IMultiBtPbapExtension extends IPbapExtension {

    public interface IMultiPbapListener {
        void onSyncPhonebookStatusChanged(String str, int i, int i2);
    }

    int getPhoneBookContactsCount(String str);

    int getSyncPhonebookStatus(String str);

    boolean registerMultiPbapListener(IMultiPbapListener iMultiPbapListener);

    boolean syncPhonebook(String str, int i);

    boolean unregisterMultiPbapListener(IMultiPbapListener iMultiPbapListener);
}
