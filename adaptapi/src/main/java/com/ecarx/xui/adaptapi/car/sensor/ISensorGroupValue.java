package com.ecarx.xui.adaptapi.car.sensor;

public interface ISensorGroupValue {

    public interface IAcc3dValue extends ISensorGroupValue {
        int getAxis();

        float getValuePitch();

        float getValueRoll();

        float getValueYaw();
    }

    public interface IFourWheelBreakPressure extends ISensorGroupValue {
        float getVFLBreakPressure();

        float getVFRBreakPressure();

        float getVRLBreakPressure();

        float getVRRBreakPressure();
    }

    public interface IGyroValue extends ISensorGroupValue {
        int getAxis();

        float getTemperature();

        float getValuePitch();

        float getValueRoll();

        float getValueYaw();
    }

    public interface IShaftTorque extends ISensorGroupValue {
        float getFrontShaftTorque();

        float getRearShaftTorque();
    }

    public interface ISpeedPulseValue extends ISensorGroupValue {
        float getSpeedValue();
    }

    public interface IW4mValue extends ISensorGroupValue {
        int getGearState();

        float getLatAcc();

        float getLonAcc();

        float getSteerAngle();

        float getVFLSpeed();

        float getVFRSpeed();

        float getVRLSpeed();

        float getVRRSpeed();

        float getYawRate();
    }

    int getInterval();

    int getSensorGroupType();

    long getTickTime();
}
