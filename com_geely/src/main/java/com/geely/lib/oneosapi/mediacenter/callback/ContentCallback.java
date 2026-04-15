package com.geely.lib.oneosapi.mediacenter.callback;

import android.os.Handler;
import android.os.Looper;
import android.os.RemoteException;
import com.geely.lib.oneosapi.mediacenter.base.BaseCallback;
import com.geely.lib.oneosapi.mediacenter.callback.IContentCallback;
import com.geely.lib.oneosapi.mediacenter.constant.MediaCenterConstant;

/* loaded from: classes.dex */
public abstract class ContentCallback extends BaseCallback<IContentCallback> {
    public abstract void onContentResult(MediaCenterConstant.AudioSource source, MediaCenterConstant.AppSource app, String content);

    public ContentCallback() {
        setCallbackImpl(new IContentCallback.Stub() { // from class: com.geely.lib.oneosapi.mediacenter.callback.ContentCallback.1
            @Override // com.geely.lib.oneosapi.mediacenter.callback.IContentCallback
            public void onContentResult(final int source, final int app, final String content) throws RemoteException {
                new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.geely.lib.oneosapi.mediacenter.callback.ContentCallback.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        ContentCallback.this.onContentResult(MediaCenterConstant.getAudioSourceEnum(source), MediaCenterConstant.getAppSourceEnum(app), content);
                    }
                });
            }
        });
    }
}