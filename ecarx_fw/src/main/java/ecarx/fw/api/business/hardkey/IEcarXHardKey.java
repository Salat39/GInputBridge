package ecarx.fw.api.business.hardkey;

public interface IEcarXHardKey {
    int[] requestHardKeyEvent(int[] keys, IEcarXHardKeyCallback callback, int cookie);
    void abandonHardKeyEvent(IEcarXHardKeyCallback callback, int cookie);

    long getInputSettingDuration(int type);
    int getInputSettingValue(int type);
    int getSteerWheelType();
    int getCustomFunctionType();
}
