package com.geely.lib.oneosapi.appstore.constant;

/* loaded from: classes.dex */
public interface TaskExecutionStatus {
    public static final int DOWNLOAD_COMPLETED = -3;
    public static final int DOWNLOAD_CONNECTED = 2;
    public static final int DOWNLOAD_ERROR = -1;
    public static final int DOWNLOAD_PAUSED = -2;
    public static final int DOWNLOAD_PENDING = 1;
    public static final int DOWNLOAD_PROGRESS = 3;
    public static final int DOWNLOAD_STARTED = 6;
    public static final int DOWNLOAD_WAIT_NETWORK = -4;
    public static final int INSTALL_COMPLETED = 103;
    public static final int INSTALL_ERROR = 104;
    public static final int INSTALL_PENDING = 100;
    public static final int INSTALL_PROGRESS = 102;
    public static final int INSTALL_STARTED = 101;
    public static final int INVALID = 0;
}