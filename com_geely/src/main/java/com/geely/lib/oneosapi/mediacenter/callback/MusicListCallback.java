package com.geely.lib.oneosapi.mediacenter.callback;

import android.os.Handler;
import android.os.Looper;
import android.os.RemoteException;
import com.geely.lib.oneosapi.mediacenter.base.BaseCallback;
import com.geely.lib.oneosapi.mediacenter.bean.MediaData;
import com.geely.lib.oneosapi.mediacenter.callback.IMusicListCallback;
import com.geely.lib.oneosapi.mediacenter.constant.MediaCenterConstant;
import java.util.List;

/* loaded from: classes.dex */
public abstract class MusicListCallback extends BaseCallback<IMusicListCallback> {
    protected abstract void onPlayListChanged(MediaCenterConstant.AudioSource source, List<MediaData> list);

    public MusicListCallback() {
        setCallbackImpl(new IMusicListCallback.Stub() { // from class: com.geely.lib.oneosapi.mediacenter.callback.MusicListCallback.1
            @Override // com.geely.lib.oneosapi.mediacenter.callback.IMusicListCallback
            public void onPlayListChanged(final int source, final List<MediaData> list) throws RemoteException {
                new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.geely.lib.oneosapi.mediacenter.callback.MusicListCallback.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        MusicListCallback.this.onPlayListChanged(MediaCenterConstant.getAudioSourceEnum(source), list);
                    }
                });
            }
        });
    }
}