package com.geely.lib.oneosapi.linkmanager;

/* loaded from: classes.dex */
public class Const {

    public static class ConnectBrand {
        public static final int CARLIFE = 4;
        public static final int CARLINK = 2;
        public static final int CARPLAY = 3;
        public static final int HICAR = 1;
        public static final int INVALID = 0;
    }

    public static class ConnectStatus {
        public static final int CONNECTED = 1;
        public static final int DISCONNECTED = 0;
    }

    public static class ConnectType {
        public static final int INVALID = 0;
        public static final int WIRED = 1;
        public static final int WIRELESS = 2;
    }

    public static class ModemCallState {
        public static final int IDLE = 1;
        public static final int OFFHOOK = 3;
        public static final int RINGING = 2;
        public static final int UNKNOWN_STATE = 0;
    }

    public static class PlayState {
        public static final int PLAY = 1;
        public static final int STOP = 0;
    }
}