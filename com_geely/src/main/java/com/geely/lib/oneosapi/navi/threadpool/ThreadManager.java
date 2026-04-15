package com.geely.lib.oneosapi.navi.threadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/* loaded from: classes.dex */
public class ThreadManager {
    private static final ScheduledExecutorService mExecutorService = Executors.newSingleThreadScheduledExecutor();

    public static void execute(Runnable runnable) {
        mExecutorService.execute(runnable);
    }
}