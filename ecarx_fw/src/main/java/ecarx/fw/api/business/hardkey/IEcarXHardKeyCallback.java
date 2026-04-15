package ecarx.fw.api.business.hardkey;

public interface IEcarXHardKeyCallback {
    boolean onKeyPressedCallback(int keyCode);
    boolean onKeyReleasedCallback(int keyCode);
    boolean onKeyCanceledCallback(int keyCode);
}
