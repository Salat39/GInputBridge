package com.geely.lib.oneosapi.mediacenter.callback;

import android.os.Handler;
import android.os.Looper;
import android.os.RemoteException;
import com.geely.lib.oneosapi.mediacenter.base.BaseCallback;
import com.geely.lib.oneosapi.mediacenter.bean.SearchResult;
import com.geely.lib.oneosapi.mediacenter.callback.ISearchSongCallback;
import com.geely.lib.oneosapi.mediacenter.constant.MediaCenterConstant;
import java.util.List;

/* loaded from: classes.dex */
public abstract class SearchSongCallback extends BaseCallback<ISearchSongCallback> {
    public abstract void onSearchSongResult(MediaCenterConstant.AudioSource source, MediaCenterConstant.AppSource appSource, List<SearchResult> searchResults);

    public SearchSongCallback() {
        setCallbackImpl(new ISearchSongCallback.Stub() { // from class: com.geely.lib.oneosapi.mediacenter.callback.SearchSongCallback.1
            @Override // com.geely.lib.oneosapi.mediacenter.callback.ISearchSongCallback
            public void onSearchSongResult(final int source, final int app, final List<SearchResult> searchResults) throws RemoteException {
                new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.geely.lib.oneosapi.mediacenter.callback.SearchSongCallback.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        SearchSongCallback.this.onSearchSongResult(MediaCenterConstant.getAudioSourceEnum(source), MediaCenterConstant.getAppSourceEnum(app), searchResults);
                    }
                });
            }
        });
    }
}