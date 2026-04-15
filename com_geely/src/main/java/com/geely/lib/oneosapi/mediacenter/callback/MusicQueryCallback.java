package com.geely.lib.oneosapi.mediacenter.callback;

import android.os.Handler;
import android.os.Looper;
import android.os.RemoteException;
import com.geely.lib.oneosapi.mediacenter.base.BaseCallback;
import com.geely.lib.oneosapi.mediacenter.callback.IMusicQueryCallback;

/* loaded from: classes.dex */
public abstract class MusicQueryCallback extends BaseCallback<IMusicQueryCallback> {
    public abstract void onError(int code);

    public abstract void onSuccess(int code, String name);

    public MusicQueryCallback() {
        setCallbackImpl(new IMusicQueryCallback.Stub() { // from class: com.geely.lib.oneosapi.mediacenter.callback.MusicQueryCallback.1
            @Override // com.geely.lib.oneosapi.mediacenter.callback.IMusicQueryCallback
            public void onSuccess(final int code, final String name) throws RemoteException {
                new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.geely.lib.oneosapi.mediacenter.callback.MusicQueryCallback.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        MusicQueryCallback.this.onSuccess(code, name);
                    }
                });
            }

            @Override // com.geely.lib.oneosapi.mediacenter.callback.IMusicQueryCallback
            public void onError(final int code) throws RemoteException {
                new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.geely.lib.oneosapi.mediacenter.callback.MusicQueryCallback.1.2
                    @Override // java.lang.Runnable
                    public void run() {
                        MusicQueryCallback.this.onError(code);
                    }
                });
            }
        });
    }
}