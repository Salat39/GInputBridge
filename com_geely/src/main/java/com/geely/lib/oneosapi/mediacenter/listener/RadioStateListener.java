package com.geely.lib.oneosapi.mediacenter.listener;

import com.geely.lib.oneosapi.mediacenter.bean.Frequency;
import java.util.List;

/* loaded from: classes.dex */
public interface RadioStateListener {
    void onCarouselPlayStart(boolean succeeded);

    void onCarouserPlayStop(int frequency);

    void onCarouserWaiting(int frequency, long leftTime);

    void onCollectionStationListChanged(List<Frequency> frequencyList);

    void onCollectionStationListReachesMax();

    void onCurrentBand(int band);

    void onCurrentFrequency(int frequency);

    void onOpenRadioResult(boolean succeeded);

    void onRadioClosed(boolean succeeded);

    void onRadioError(int errorCode);

    void onRadioMuteState(int state);

    void onRadioNameListChanged(List<Frequency> frequencyList);

    void onRadioStatusChanged(int status);

    void onScanStarted(int direction);

    void onScanStationListChanged(List<Frequency> frequencyList);

    void onScanStopped(boolean succeeded);

    void onSeekStarted(int direction);

    void onSeekStopped(boolean succeeded);

    void onSignalQualityChanged(int level);

    void onStationFrequency(int frequency);
}