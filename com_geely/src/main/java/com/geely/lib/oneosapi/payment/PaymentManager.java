package com.geely.lib.oneosapi.payment;

import android.content.Context;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.geely.lib.oneosapi.common.ServiceBaseManager;
import com.geely.lib.oneosapi.payment.IPaymentCallback;
import com.geely.lib.oneosapi.payment.IPaymentService;

/* loaded from: classes.dex */
public class PaymentManager implements ServiceBaseManager {
    private static final String TAG = "PaymentManager";
    private Context mContetx;
    private IPaymentService mService;

    @Override // com.geely.lib.oneosapi.common.ServiceBaseManager
    public void setService(IBinder binder) {
        this.mService = IPaymentService.Stub.asInterface(binder);
    }

    public PaymentManager(Context context, IBinder binder) {
        this.mContetx = context;
        this.mService = IPaymentService.Stub.asInterface(binder);
    }

    public void startPayment(OrderRenderParam orderInfo, BasePaymentCallback isPaySuccessCallback) {
        if (isServiceAlive()) {
            try {
                this.mService.startPayment(orderInfo, isPaySuccessCallback);
                return;
            } catch (RemoteException e) {
                e.printStackTrace();
                return;
            }
        }
        Log.d(TAG, "startPayment: service is not alive");
    }

    public void isPaySuccessCallback(int code, String msg) {
        if (isServiceAlive()) {
            try {
                this.mService.isPaySuccessCallback(code, msg);
                return;
            } catch (RemoteException e) {
                e.printStackTrace();
                return;
            }
        }
        Log.d(TAG, "startPayment: service is not alive");
    }

    private boolean isServiceAlive() {
        IPaymentService iPaymentService = this.mService;
        return iPaymentService != null && iPaymentService.asBinder().isBinderAlive();
    }

    public static abstract class BasePaymentCallback extends IPaymentCallback.Stub {
        @Override // com.geely.lib.oneosapi.payment.IPaymentCallback
        public void isPaySuccessCallback(int code, String message) {
            Log.d(PaymentManager.TAG, "BasePaymentCallback() called with: code = [" + code + "], message = [" + message + "]");
        }
    }
}