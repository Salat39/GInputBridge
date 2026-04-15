package com.geely.lib.oneosapi.mediacenter.callback;

import android.os.Handler;
import android.os.Looper;
import android.os.RemoteException;
import com.geely.lib.oneosapi.mediacenter.base.BaseCallback;
import com.geely.lib.oneosapi.mediacenter.bean.MusicFileData;
import com.geely.lib.oneosapi.mediacenter.callback.IScanPathCallback;
import com.geely.lib.oneosapi.mediacenter.constant.MediaCenterConstant;
import java.util.List;

/* loaded from: classes.dex */
public abstract class ScanPathCallback extends BaseCallback<IScanPathCallback> {
    public abstract void onScanPathFinish(MediaCenterConstant.AudioSource source, List<MusicFileData> musicFileDataList);

    public ScanPathCallback() {
        setCallbackImpl(new IScanPathCallback.Stub() { // from class: com.geely.lib.oneosapi.mediacenter.callback.ScanPathCallback.1
            @Override // com.geely.lib.oneosapi.mediacenter.callback.IScanPathCallback
            public void onScanPathFinish(final int source, final List<MusicFileData> musicFileDataList) throws RemoteException {
                new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.geely.lib.oneosapi.mediacenter.callback.ScanPathCallback.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        ScanPathCallback.this.onScanPathFinish(MediaCenterConstant.getAudioSourceEnum(source), musicFileDataList);
                    }
                });
            }
        });
    }
}