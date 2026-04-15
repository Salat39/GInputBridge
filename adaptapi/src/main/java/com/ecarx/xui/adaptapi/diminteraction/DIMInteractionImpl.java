package com.ecarx.xui.adaptapi.diminteraction;

import android.content.Context;

import com.ecarx.xui.adaptapi.ECarXCarProxy;
import com.ecarx.xui.adaptapi.binder.IConnectable;

import ecarx.car.ECarXCar;
import ecarx.car.hardware.signal.CarSignalManager;

/* loaded from: C:\Users\Andrei\AppData\Local\Temp\jadx-7518071738844169467\classes.dex */
public class DIMInteractionImpl extends DimInteraction implements IConnectable, ECarXCarProxy.ECarXCarProxyMethod {

    public DIMInteractionImpl(Context context) {

    }

    @Override // com.ecarx.xui.adaptapi.binder.IConnectable
    public void connect() {
    }

    @Override // com.ecarx.xui.adaptapi.binder.IConnectable
    public void disconnect() {
    }

    @Override // com.ecarx.xui.adaptapi.diminteraction.DimInteraction
    public IClimateInteraction getClimateInteraction() {
        return null;
    }

    @Override // com.ecarx.xui.adaptapi.diminteraction.DimInteraction
    public IContactsInteraction getContactsInteraction() {
        return null;
    }

    @Override // com.ecarx.xui.adaptapi.diminteraction.DimInteraction
    public IDimMenuInteraction getDimMenuInteraction() {
        return null;
    }

    @Override // com.ecarx.xui.adaptapi.diminteraction.DimInteraction
    public IMediaInteraction getMediaInteraction() {

        return null;
    }

    @Override // com.ecarx.xui.adaptapi.diminteraction.DimInteraction
    public INaviInteraction getNaviInteraction() {
        return null;
    }

    @Override // com.ecarx.xui.adaptapi.diminteraction.DimInteraction
    public IPhoneCallInteraction getPhoneCallInteraction() {

        return null;
    }

    @Override // com.ecarx.xui.adaptapi.diminteraction.DimInteraction
    public int getShowPresentationOption() {
        return 1;
    }

    @Override // com.ecarx.xui.adaptapi.diminteraction.DimInteraction
    public int getSupportedRankingType() {
        return 0;
    }

    @Override // com.ecarx.xui.adaptapi.diminteraction.DimInteraction
    public IVendorInteraction getVendorInteraction() {
        return null;
    }

    @Override // com.ecarx.xui.adaptapi.diminteraction.DimInteraction
    public IVrInteraction getVrInteraction() {

        return null;
    }

    @Override // com.ecarx.xui.adaptapi.ECarXCarProxy.ECarXCarProxyMethod
    public void onECarXCarServiceConnected(ECarXCar eCarXCar, CarSignalManager carSignalManager) {

    }

    @Override // com.ecarx.xui.adaptapi.ECarXCarProxy.ECarXCarProxyMethod
    public void onECarXCarServiceDeath() {

    }

    @Override // com.ecarx.xui.adaptapi.binder.IConnectable
    public void registerConnectWatcher(IConnectable.IConnectWatcher iConnectWatcher) {

    }

    @Override // com.ecarx.xui.adaptapi.diminteraction.DimInteraction
    public void registerInteractionCallback(DimInteraction.IInteractionCallback iInteractionCallback) {

    }

    @Override // com.ecarx.xui.adaptapi.diminteraction.DimInteraction
    public void setPresentationToDimSwitch(int i, String str, String str2, boolean z) {
    }

    @Override // com.ecarx.xui.adaptapi.binder.IConnectable
    public void unregisterConnectWatcher() {

    }

    @Override // com.ecarx.xui.adaptapi.diminteraction.DimInteraction
    public void unregisterInteractionCallback(DimInteraction.IInteractionCallback iInteractionCallback) {

    }

    @Override // com.ecarx.xui.adaptapi.diminteraction.DimInteraction
    public void updateAvgFuleRanking(int i, String str) {

    }
}