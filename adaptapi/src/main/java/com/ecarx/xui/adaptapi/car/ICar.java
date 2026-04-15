package com.ecarx.xui.adaptapi.car;

import com.ecarx.xui.adaptapi.car.base.ICarFunction;
import com.ecarx.xui.adaptapi.car.base.ICarInfo;
import com.ecarx.xui.adaptapi.car.diagnostics.IDiagnostics;
import com.ecarx.xui.adaptapi.car.hev.IHev;
import com.ecarx.xui.adaptapi.car.roadpath.IRoadPath;
import com.ecarx.xui.adaptapi.car.sensor.ISensor;
import com.ecarx.xui.adaptapi.car.userprofile.ICarKey;
import com.ecarx.xui.adaptapi.car.userprofile.IUserProfile;

public interface ICar {
    ICarInfo getCarInfoManager();

    ICarKey getCarKeyManager();

    IDiagnostics getDiagnosticManager();

    IHev getHevManager();

    ICarFunction getICarFunction();

    IRoadPath getRoadPathManager();

    ISensor getSensorManager();

    IUserProfile getUserProfileManager();
}
