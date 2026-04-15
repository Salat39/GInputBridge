package com.geely.lib.oneosapi.mediacenter.manager;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;
import com.geely.lib.oneosapi.mediacenter.MediaCenterManager;
import com.geely.lib.oneosapi.mediacenter.bean.Frequency;
import com.geely.lib.oneosapi.mediacenter.bean.MediaData;
import com.geely.lib.oneosapi.mediacenter.constant.MediaCenterConstant;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class MediaAdapterManager extends MusicAdapterManager {
    private static final String TAG = "MediaAdapterManager";

    public MediaAdapterManager(Context context, MediaCenterManager mediaCenterManager) {
        super(context, mediaCenterManager);
    }

    @Override // com.geely.lib.oneosapi.mediacenter.manager.MusicAdapterManager
    public int play() {
        if (getCurrentAudioSource() == MediaCenterConstant.AudioSource.AUDIO_SOURCE_RADIO) {
            return this.mMediaCenterManager.getRadioManager().play() ? 1 : 0;
        }
        return super.play();
    }

    @Override // com.geely.lib.oneosapi.mediacenter.manager.MusicAdapterManager
    public int pause() {
        if (getCurrentAudioSource() == MediaCenterConstant.AudioSource.AUDIO_SOURCE_RADIO) {
            return this.mMediaCenterManager.getRadioManager().pause() ? 1 : 0;
        }
        return super.pause();
    }

    @Override // com.geely.lib.oneosapi.mediacenter.manager.MusicAdapterManager
    public int next() {
        if (getCurrentAudioSource() == MediaCenterConstant.AudioSource.AUDIO_SOURCE_RADIO) {
            return this.mMediaCenterManager.getRadioManager().seekAsync(0) ? 1 : 0;
        }
        return super.next();
    }

    @Override // com.geely.lib.oneosapi.mediacenter.manager.MusicAdapterManager
    public int prev() {
        if (getCurrentAudioSource() == MediaCenterConstant.AudioSource.AUDIO_SOURCE_RADIO) {
            return this.mMediaCenterManager.getRadioManager().seekAsync(1) ? 1 : 0;
        }
        return super.prev();
    }

    public int playSavedSet(String action, String mode, String item) {
        return getCurrentAudioSource() == MediaCenterConstant.AudioSource.AUDIO_SOURCE_ONLINE ? 1 : -1;
    }

    @Deprecated
    public int addFavor(String index, String orientation, String mode, String item) {
        return getCurrentAudioSource() == MediaCenterConstant.AudioSource.AUDIO_SOURCE_ONLINE ? 1 : -1;
    }

    public int onMusicEnquire(String item) {
        return getCurrentAudioSource() == MediaCenterConstant.AudioSource.AUDIO_SOURCE_ONLINE ? 1 : -1;
    }

    public int searchMusic(MediaData params) {
        return getCurrentAudioSource() == MediaCenterConstant.AudioSource.AUDIO_SOURCE_ONLINE ? 1 : -1;
    }

    public int searchFM(MediaData params) {
        return getCurrentAudioSource() == MediaCenterConstant.AudioSource.AUDIO_SOURCE_ONLINE ? 1 : -1;
    }

    public int searchNews(MediaData params) {
        return getCurrentAudioSource() == MediaCenterConstant.AudioSource.AUDIO_SOURCE_ONLINE ? 1 : -1;
    }

    public int bookSong(String songName) {
        return getCurrentAudioSource() == MediaCenterConstant.AudioSource.AUDIO_SOURCE_ONLINE ? 1 : -1;
    }

    public int playIndex(String index, String orientation) {
        return getCurrentAudioSource() == MediaCenterConstant.AudioSource.AUDIO_SOURCE_ONLINE ? 1 : -1;
    }

    @Deprecated
    public int onView(String action, String mode) {
        return getCurrentAudioSource() == MediaCenterConstant.AudioSource.AUDIO_SOURCE_ONLINE ? 1 : -1;
    }

    public int setBandAsync(int i) {
        if (getCurrentAudioSource() == MediaCenterConstant.AudioSource.AUDIO_SOURCE_RADIO) {
            return this.mMediaCenterManager.getRadioManager().setBandAsync(i) ? 1 : 0;
        }
        return -1;
    }

    public int setFrequency(int i) {
        if (getCurrentAudioSource() == MediaCenterConstant.AudioSource.AUDIO_SOURCE_RADIO) {
            return this.mMediaCenterManager.getRadioManager().setFrequency(i) ? 1 : 0;
        }
        return -1;
    }

    public int scanAsync() {
        if (getCurrentAudioSource() == MediaCenterConstant.AudioSource.AUDIO_SOURCE_RADIO) {
            return this.mMediaCenterManager.getRadioManager().scanAsync() ? 1 : 0;
        }
        return -1;
    }

    public int addFavor() {
        return addFavor(null);
    }

    public int cancelFavor() {
        return cancelFavor(null);
    }

    @Override // com.geely.lib.oneosapi.mediacenter.manager.MusicAdapterManager
    public boolean isCurrentMediaFavored() {
        MediaCenterConstant.AudioSource currentAudioSource = getCurrentAudioSource();
        if (currentAudioSource == MediaCenterConstant.AudioSource.AUDIO_SOURCE_RADIO) {
            RadioManager radioManager = this.mMediaCenterManager.getRadioManager();
            List<Frequency> collectionStationsList = radioManager.getCollectionStationsList();
            if (collectionStationsList != null) {
                Iterator<Frequency> it = collectionStationsList.iterator();
                while (it.hasNext()) {
                    if (it.next().getFrequency() == radioManager.getFrequency()) {
                        return true;
                    }
                }
            }
            return false;
        }
        if (currentAudioSource == MediaCenterConstant.AudioSource.AUDIO_SOURCE_YUNTING) {
            MediaData currentMediaData = this.mMediaCenterManager.getMusicManagerMap().get(currentAudioSource).getCurrentMediaData();
            if (currentMediaData != null) {
                return currentMediaData.isFavored;
            }
            return false;
        }
        return super.isCurrentMediaFavored();
    }

    public int searchMedia(String json) {
        this.mMediaCenterManager.getOnlineMusicManager().searchMediaForVR(json);
        return 1;
    }

    /* renamed from: com.geely.lib.oneosapi.mediacenter.manager.MediaAdapterManager$1 */
    static /* synthetic */ class C08901 {

        /* renamed from: $SwitchMap$com$geely$lib$oneosapi$mediacenter$constant$MediaCenterConstant$AudioSource */
        static final /* synthetic */ int[] f75xcbf1b1f7;

        static {
            int[] iArr = new int[MediaCenterConstant.AudioSource.values().length];
            f75xcbf1b1f7 = iArr;
            try {
                iArr[MediaCenterConstant.AudioSource.AUDIO_SOURCE_ONLINE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f75xcbf1b1f7[MediaCenterConstant.AudioSource.AUDIO_SOURCE_RADIO.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public int playFavor() {
        int i = C08901.f75xcbf1b1f7[getCurrentAudioSource().ordinal()];
        if (i == 1) {
            return playMusicFavor();
        }
        if (i != 2) {
            return -1;
        }
        return playRadioFavor();
    }

    public int playRadioFavor() {
        if (C08901.f75xcbf1b1f7[getCurrentAudioSource().ordinal()] != 2) {
            return -1;
        }
        return this.mMediaCenterManager.getRadioManager().playRadioFavor();
    }

    public void playYunTingFavorList(String pkgFrom, boolean foreground, int displayId) {
        Log.d(TAG, "playYunTingFavorList pkgFrom:" + pkgFrom + " foreground:" + foreground + " displayId:" + displayId);
        PackageManager packageManager = this.mContext.getPackageManager();
        if (packageManager != null) {
            Intent launchIntentForPackage = packageManager.getLaunchIntentForPackage("com.tencent.wecarflow");
            if (launchIntentForPackage == null) {
                Log.d(TAG, "not found multiMedia");
                return;
            }
            if (foreground) {
                launchIntentForPackage.putExtra("jumpView", "yunting");
                launchIntentForPackage.putExtra("intent", "play_favor");
                if (displayId > 0) {
                    if (Build.VERSION.SDK_INT >= 23) {
                        ActivityOptions makeBasic = ActivityOptions.makeBasic();
                        if (Build.VERSION.SDK_INT >= 26) {
                            makeBasic.setLaunchDisplayId(displayId);
                            this.mContext.startActivity(launchIntentForPackage, makeBasic.toBundle());
                            return;
                        }
                        return;
                    }
                    return;
                }
                this.mContext.startActivity(launchIntentForPackage);
            }
        }
    }

    public void openYunTingFavorList(String pkgFrom, int displayId) {
        Log.d(TAG, "openYunTingFavorList pkgFrom:" + pkgFrom + " displayId:" + displayId);
        PackageManager packageManager = this.mContext.getPackageManager();
        if (packageManager != null) {
            Intent launchIntentForPackage = packageManager.getLaunchIntentForPackage("com.tencent.wecarflow");
            if (launchIntentForPackage != null) {
                launchIntentForPackage.putExtra("jumpView", "yunting");
                launchIntentForPackage.putExtra("intent", "open_favor");
                if (displayId > 0) {
                    if (Build.VERSION.SDK_INT >= 23) {
                        ActivityOptions makeBasic = ActivityOptions.makeBasic();
                        if (Build.VERSION.SDK_INT >= 26) {
                            makeBasic.setLaunchDisplayId(displayId);
                            this.mContext.startActivity(launchIntentForPackage, makeBasic.toBundle());
                            return;
                        }
                        return;
                    }
                    return;
                }
                this.mContext.startActivity(launchIntentForPackage);
                return;
            }
            Log.d(TAG, "not found multiMedia");
        }
    }

    public void openYunTingCategoryList(String pkgFrom, int displayId) {
        Log.d(TAG, "openYunTingCategoryList pkgFrom:" + pkgFrom + " displayId:" + displayId);
        PackageManager packageManager = this.mContext.getPackageManager();
        if (packageManager != null) {
            Intent launchIntentForPackage = packageManager.getLaunchIntentForPackage("com.tencent.wecarflow");
            if (launchIntentForPackage != null) {
                launchIntentForPackage.putExtra("jumpView", "yunting");
                launchIntentForPackage.putExtra("intent", "open_category");
                if (displayId > 0) {
                    if (Build.VERSION.SDK_INT >= 23) {
                        ActivityOptions makeBasic = ActivityOptions.makeBasic();
                        if (Build.VERSION.SDK_INT >= 26) {
                            makeBasic.setLaunchDisplayId(displayId);
                            this.mContext.startActivity(launchIntentForPackage, makeBasic.toBundle());
                            return;
                        }
                        return;
                    }
                    return;
                }
                this.mContext.startActivity(launchIntentForPackage);
                return;
            }
            Log.d(TAG, "not found multiMedia");
        }
    }

    public int openWeCarFlowTab(String tab) {
        this.mMediaCenterManager.getOnlineMusicManager().startAppTab(tab);
        return 1;
    }

    public int openLrcPage() {
        if (C08901.f75xcbf1b1f7[getCurrentAudioSource().ordinal()] != 1) {
            return -1;
        }
        this.mMediaCenterManager.getOnlineMusicManager().openLrcPage();
        return 1;
    }

    public int openLoginPage() {
        if (C08901.f75xcbf1b1f7[getCurrentAudioSource().ordinal()] != 1) {
            return -1;
        }
        this.mMediaCenterManager.getOnlineMusicManager().openLoginPage();
        return 1;
    }

    public int switchSourceQuality(int quality) {
        if (getCurrentAudioSource() != MediaCenterConstant.AudioSource.AUDIO_SOURCE_ONLINE || !isMusicQualitySwitch(quality)) {
            return -1;
        }
        this.mMediaCenterManager.getOnlineMusicManager().switchSourceQuality(quality);
        return 1;
    }

    public boolean isWeCarFlowAgreedUserProtocol() {
        return this.mMediaCenterManager.getOnlineMusicManager().isAgreedUserProtocol();
    }

    public boolean isMusicQualitySwitch(int quality) {
        return this.mMediaCenterManager.getOnlineMusicManager().isMusicQualitySwitch(quality);
    }

    public boolean isSupportChangeMode(MediaCenterConstant.PlayMode mode) {
        return this.mMediaCenterManager.getOnlineMusicManager().isSupportChangeMode(mode) == 1;
    }

    public int notifyVrStatusNotifierStatus(int value) {
        return this.mMediaCenterManager.getOnlineMusicManager().notifyVrStatusNotifierStatus(value);
    }
}