package com.ecarx.xui.adaptapi;

import android.content.Context;

import com.ecarx.xui.adaptapi.car.VehicleType;

import java.util.function.Function;

import ecarx.car.ECarXCar;
import ecarx.car.hardware.ECarXCarPropertyValue;
import ecarx.car.hardware.signal.CarSignalManager;
import ecarx.car.hardware.signal.SignalFilter;
import ecarx.car.hardware.vehicle.ECarXCarSetManager;

public abstract class AbsCarSignal {
    protected ECarXCar mECarXCar;
    protected ECarXCarSetManager mECarXCarSetManager;

    static /* synthetic */ boolean lambda$carConfigAnyMatch$4(int i, int i2) {
        return i == i2;
    }

    static /* synthetic */ boolean lambda$carModeAnyMatch$2(int i, int i2) {
        return i == i2;
    }

    static /* synthetic */ boolean lambda$intValueAnyMatch$5(int i, int i2) {
        return i == i2;
    }

    static /* synthetic */ boolean lambda$usageModeAnyMatch$3(int i, int i2) {
        return i == i2;
    }

    protected void initPAFilter() {
    }

    protected void initSignalFilter() {
    }

    public void onECarXCarServiceDeath() {
    }

    protected void onErrorEvent(int i, int i2) {
    }

    protected void onInitCarSignalManager() {
    }

    protected AbsCarSignal(Context context) {
    }

    public SignalFilter getPAFilter() {
        return null;
    }

    public SignalFilter getSignalFilter() {
        return null;
    }

    public ECarXCar getECarXCar() {
        return null;
    }

    public void initCarSignalManager(ECarXCar eCarXCar, CarSignalManager carSignalManager) {
    }

    public boolean isECarXCarConnected() {
        return false;
    }

    protected <E> E getValue(ECarXCarPropertyValue eCarXCarPropertyValue) {
        return null;
    }

    protected void addPAFilter(Integer num) {
    }

    protected void addSignalFilter(Integer num) {
    }

    protected void onChangeEvent(ECarXCarPropertyValue eCarXCarPropertyValue) {
    }

    protected void recordPADate(int i, Object obj) {
    }

    protected void recordSignalDate(ECarXCarPropertyValue eCarXCarPropertyValue) {
    }

    public <PA_TYPE> PA_TYPE getPAData(final int i) {
        return null;
    }

    public /* synthetic */ Object lambda$getPAData$0$AbsCarSignal(int i) {
        return null;
    }

    public int getSignalValue(final int i, final int i2) {
        return 0;
    }

    public /* synthetic */ Integer lambda$getSignalValue$1$AbsCarSignal(int i, int i2) {
        return null;
    }

    public int getSignalValueNew(int i) {
        return 0;
    }

    public int getSignalValue(int i) {
        return getSignalValue(i, Integer.MIN_VALUE);
    }

    public FunctionStatus getFunctionStatus(int i) {
        return null;
    }

    public int getIntValue(int i, int i2) {
        return 0;
    }

    public int getConvertIntData(int i, Function<Integer, Integer> function, int i2) {
        return 0;
    }

    public boolean carModeAnyMatch(int... iArr) {
        return false;
    }

    public boolean usageModeAnyMatch(int... iArr) {
        return false;
    }

    public boolean carConfigAnyMatch(int i, int... iArr) {
        return false;
    }

    public boolean intValueAnyMatch(final int i, int... iArr) {
        return false;
    }

    public boolean isDriverRightSide() {
        return false;
    }

    public VehicleType getVehicleType() {
        return null;
    }
}
