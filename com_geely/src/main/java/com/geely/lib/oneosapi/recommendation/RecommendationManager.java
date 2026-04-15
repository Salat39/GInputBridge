package com.geely.lib.oneosapi.recommendation;

import android.content.Context;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.geely.lib.oneosapi.common.ServiceBaseManager;
import com.geely.lib.oneosapi.recommendation.IRecommendService;
import com.geely.lib.oneosapi.recommendation.callback.IWithdrawCallback;
import com.geely.lib.oneosapi.recommendation.manager.AnalyticsManager;
import com.geely.lib.oneosapi.recommendation.manager.CsdManager;
import com.geely.lib.oneosapi.recommendation.manager.PsdManager;

/* loaded from: classes.dex */
public class RecommendationManager implements ServiceBaseManager {
    private static final String TAG = "RecommendationManager";
    private AnalyticsManager mAnalyticsManager;
    private Context mContext;
    private CsdManager mCsdManager;
    private PsdManager mPsdManager;
    private IRecommendService mService;

    private void initAnalyticsManager() {
    }

    public RecommendationManager(Context context, IBinder binder) {
        this.mContext = context;
        initRecommendationManger(binder);
    }

    @Override // com.geely.lib.oneosapi.common.ServiceBaseManager
    public void setService(IBinder binder) {
        Log.d(TAG, "setService: ..." + binder);
        initRecommendationManger(binder);
    }

    private void initRecommendationManger(IBinder binder) {
        this.mService = IRecommendService.Stub.asInterface(binder);
        initCsdManager();
        initPsdManager();
        initAnalyticsManager();
    }

    private boolean isServiceAlive() {
        IRecommendService iRecommendService = this.mService;
        return iRecommendService != null && iRecommendService.asBinder().isBinderAlive();
    }

    private void initCsdManager() {
        Log.d(TAG, "initCsdManager() called" + this.mService);
        if (isServiceAlive()) {
            try {
                if (this.mCsdManager == null) {
                    this.mCsdManager = new CsdManager(this.mContext, this.mService.getCsdManager());
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    private void initPsdManager() {
        Log.d(TAG, "initPsdManager() called" + this.mService);
        if (isServiceAlive()) {
            try {
                if (this.mPsdManager == null) {
                    this.mPsdManager = new PsdManager(this.mContext, this.mService.getPsdManager());
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public PsdManager getPsdManager() {
        return this.mPsdManager;
    }

    public CsdManager getCsdManager() {
        return this.mCsdManager;
    }

    public void subscribeWithdrawCallback(WithdrawCallback callback) {
        IRecommendService iRecommendService = this.mService;
        if (iRecommendService != null) {
            try {
                iRecommendService.subscribeWithdrawCallback(callback);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void unSubscribeWithdrawCallback(WithdrawCallback callback) {
        IRecommendService iRecommendService = this.mService;
        if (iRecommendService != null) {
            try {
                iRecommendService.unSubscribeWithdrawCallback(callback);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public static abstract class WithdrawCallback extends IWithdrawCallback.Stub {
        @Override // com.geely.lib.oneosapi.recommendation.callback.IWithdrawCallback
        public void onResult(String json) {
            Log.d(RecommendationManager.TAG, "WithdrawCallback: withdrawInfo " + json);
        }
    }
}