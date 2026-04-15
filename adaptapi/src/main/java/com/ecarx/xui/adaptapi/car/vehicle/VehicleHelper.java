package com.ecarx.xui.adaptapi.car.vehicle;

import android.content.Context;

import com.ecarx.xui.adaptapi.FunctionStatus;
import com.ecarx.xui.adaptapi.car.base.ICarFunction;

import ecarx.car.ECarXCar;
import ecarx.car.hardware.signal.CarSignalManager;


public class VehicleHelper implements ICarFunction {

    public VehicleHelper(Context context) {
    }

    public void initCarSignalManager(ECarXCar eCarXCar, CarSignalManager carSignalManager) {

    }

    public void onECarXCarServiceDeath() {

    }

    @Override
    public FunctionStatus isFunctionSupported(int i) {
        return null;
    }

    @Override
    public FunctionStatus isFunctionSupported(int i, int i2) {
        return FunctionStatus.notavailable;
    }

    @Override
    public FunctionStatus isFunctionSupported(int i, int i2, int i3) {
        return FunctionStatus.notavailable;
    }

    @Override
    public int[] getSupportedFunctionZones(int i) {
        return null;
    }

    @Override
    public int[] getSupportedFunctionValue(int i) {
        return null;
    }

    @Override
    public int[] getSupportedFunctionValue(int i, int i2) {
        return null;
    }

    @Override
    public boolean setFunctionValue(int i, int i2) {
        return false;
    }

    @Override
    public boolean setFunctionValue(int i, int i2, int i3) {
        return false;
    }

    @Override
    public int getFunctionValue(int i) {
        return 0;
    }

    @Override
    public int getFunctionValue(int i, int i2) {
        return 0;
    }

    @Override
    public boolean setCustomizeFunctionValue(int i, float f) {
        return false;
    }

    @Override
    public boolean setCustomizeFunctionValue(int i, int i2, float f) {
        return false;
    }

    @Override
    public float getCustomizeFunctionValue(int i) {
        return 0f;
    }

    @Override
    public float getCustomizeFunctionValue(int i, int i2) {
        return 0f;
    }

    @Override
    public boolean registerFunctionValueWatcher(ICarFunction.IFunctionValueWatcher iFunctionValueWatcher) {
        return false;
    }

    @Override
    public boolean registerFunctionValueWatcher(int i, ICarFunction.IFunctionValueWatcher iFunctionValueWatcher) {
        return false;
    }

    @Override
    public boolean registerFunctionValueWatcher(int[] iArr, ICarFunction.IFunctionValueWatcher iFunctionValueWatcher) {
        return false;
    }

    @Override
    public boolean unregisterFunctionValueWatcher(ICarFunction.IFunctionValueWatcher iFunctionValueWatcher) {
        return false;
    }
}
