package com.ecarx.xui.adaptapi.binder;

/* loaded from: C:\Users\Andrei\AppData\Local\Temp\jadx-7518071738844169467\classes.dex */
public interface IConnectable {

    public interface IConnectWatcher {
        void onConnected();

        void onDisConnected();
    }

    void connect();

    void disconnect();

    void registerConnectWatcher(IConnectWatcher iConnectWatcher);

    void unregisterConnectWatcher();
}