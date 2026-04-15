package com.geely.lib.oneosapi;

import android.content.Context;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.geely.lib.oneosapi.appstore.AppStoreManager;
import com.geely.lib.oneosapi.camera.CameraManager;
import com.geely.lib.oneosapi.common.ServiceBaseManager;
import com.geely.lib.oneosapi.device.DeviceManager;
import com.geely.lib.oneosapi.gesture.GestureManager;
import com.geely.lib.oneosapi.input.KeyInputManager;
import com.geely.lib.oneosapi.launcher.LauncherManager;
import com.geely.lib.oneosapi.launcher.ShortcutManager;
import com.geely.lib.oneosapi.linkmanager.LinkManager;
import com.geely.lib.oneosapi.listener.ApiConnectCallBack;
import com.geely.lib.oneosapi.listener.ServiceConnectionListener;
import com.geely.lib.oneosapi.mediacenter.MediaCenterManager;
import com.geely.lib.oneosapi.mqtt.MqttManager;
import com.geely.lib.oneosapi.navi.NaviManager;
import com.geely.lib.oneosapi.ota.OtaManager;
import com.geely.lib.oneosapi.payment.PaymentManager;
import com.geely.lib.oneosapi.phone.PhoneManager;
import com.geely.lib.oneosapi.recommendation.RecommendationManager;
import com.geely.lib.oneosapi.scenemode.SceneModeManager;
import com.geely.lib.oneosapi.schedule.ScheduleManager;
import com.geely.lib.oneosapi.smart.SmartHomeManager;
import com.geely.lib.oneosapi.systemui.ControlBoardManager;
import com.geely.lib.oneosapi.systemui.DockBarManager;
import com.geely.lib.oneosapi.systemui.StatusBarPublicManager;
import com.geely.lib.oneosapi.theme.ThemeManager;
import com.geely.lib.oneosapi.user.UserManager;
import com.geely.lib.oneosapi.vims.VimsManager;
import com.geely.lib.oneosapi.vr.VrManager;
import com.geely.lib.oneosapi.weather.WeatherManager;
import com.geely.lib.oneosapi.wechat.WeChatManager;

/* loaded from: classes.dex */
public class OneOSApiManager implements ServiceConnectionListener {
    private static final String TAG = "OneOSApiManager";
    private static volatile OneOSApiManager sInstance;
    private AppStoreManager mAppStoreManager;
    private volatile CameraManager mCameraManager;
    private final Context mContext;
    private ControlBoardManager mControlBoardManager;
    private DeviceManager mDeviceManager;
    private DockBarManager mDockBarManager;
    private GestureManager mGestureManager;
    private KeyInputManager mKeyInputManager;
    private LauncherManager mLauncherManager;
    private LinkManager mLinkManager;
    private volatile MediaCenterManager mMediaCenterManager;
    private MqttManager mMqttManager;
    private volatile NaviManager mNaviManager;
    private volatile OtaManager mOtaManager;
    private PaymentManager mPaymentManager;
    private PhoneManager mPhoneManager;
    private RecommendationManager mRecommendationManager;
    private SceneModeManager mSceneModeManager;
    private ScheduleManager mScheduleManager;
    private final ServiceConnectionManager mServiceConnectionManager;
    private ShortcutManager mShortcutManager;
    private SmartHomeManager mSmartHomeManager;
    private StatusBarPublicManager mStatusBarPublicManager;
    private ThemeManager mThemeManager;
    private UserManager mUserManager;
    private VimsManager mVimsManager;
    private volatile VrManager mVrManager;
    private WeatherManager mWeatherManager;
    private WeChatManager mWechatManager;

    public static OneOSApiManager getInstance(Context context) {
        if (sInstance == null) {
            synchronized (OneOSApiManager.class) {
                if (sInstance == null) {
                    sInstance = new OneOSApiManager(context.getApplicationContext());
                }
            }
        }
        return sInstance;
    }

    public void init() {
        this.mServiceConnectionManager.connect();
    }

    public void init(ApiConnectCallBack callBack) {
        this.mServiceConnectionManager.connect(callBack);
    }

    public void release() {
        this.mServiceConnectionManager.release();
        this.mDeviceManager = null;
        this.mNaviManager = null;
        this.mVrManager = null;
        this.mOtaManager = null;
        this.mMediaCenterManager = null;
        this.mWeatherManager = null;
        this.mVimsManager = null;
        this.mScheduleManager = null;
        this.mUserManager = null;
        this.mRecommendationManager = null;
        this.mSmartHomeManager = null;
        this.mMqttManager = null;
        this.mCameraManager = null;
        this.mLinkManager = null;
        this.mShortcutManager = null;
        this.mDockBarManager = null;
        this.mLauncherManager = null;
        this.mPaymentManager = null;
        this.mWechatManager = null;
    }

    public void registerServiceConnectionListener(ServiceConnectionListener listener) {
        this.mServiceConnectionManager.registerServiceConnectionListener(listener);
    }

    public void unregisterServiceConnectionListener(ServiceConnectionListener listener) {
        this.mServiceConnectionManager.unregisterServiceConnectionListener(listener);
    }

    private OneOSApiManager(Context context) {
        this.mContext = context;
        ServiceConnectionManager serviceConnectionManager = new ServiceConnectionManager(context);
        this.mServiceConnectionManager = serviceConnectionManager;
        serviceConnectionManager.registerServiceConnectionListener(this);
    }

    public DeviceManager getDeviceManager() {
        if (this.mDeviceManager == null && this.mServiceConnectionManager.isServiceBound()) {
            try {
                this.mDeviceManager = new DeviceManager(this.mContext, this.mServiceConnectionManager.getServiceManager().getService(0));
            } catch (RemoteException e) {
                e.printStackTrace();
                return null;
            }
        }
        return this.mDeviceManager;
    }

    public NaviManager getNaviManager() {
        if (this.mNaviManager == null) {
            synchronized (OneOSApiManager.class) {
                if (this.mNaviManager == null && this.mServiceConnectionManager.isServiceBound()) {
                    try {
                        IServiceManager serviceManager = this.mServiceConnectionManager.getServiceManager();
                        if (serviceManager != null) {
                            this.mNaviManager = new NaviManager(this.mContext, serviceManager.getService(1));
                        }
                    } catch (RemoteException e) {
                        e.printStackTrace();
                        return null;
                    }
                }
            }
        }
        return this.mNaviManager;
    }

    public GestureManager getGestureManager() {
        if (this.mGestureManager == null && this.mServiceConnectionManager.isServiceBound()) {
            try {
                IServiceManager serviceManager = this.mServiceConnectionManager.getServiceManager();
                if (serviceManager != null) {
                    this.mGestureManager = new GestureManager(this.mContext, serviceManager.getService(23));
                }
            } catch (RemoteException e) {
                e.printStackTrace();
                return null;
            }
        }
        return this.mGestureManager;
    }

    public VrManager getVrManager() {
        if (this.mVrManager == null) {
            synchronized (OneOSApiManager.class) {
                if (this.mVrManager == null && this.mServiceConnectionManager.isServiceBound()) {
                    try {
                        this.mVrManager = new VrManager(this.mContext, this.mServiceConnectionManager.getServiceManager().getService(2));
                    } catch (RemoteException e) {
                        e.printStackTrace();
                        return null;
                    }
                }
            }
        }
        return this.mVrManager;
    }

    public OtaManager getOtaManager() {
        if (this.mOtaManager == null) {
            synchronized (OneOSApiManager.class) {
                if (this.mOtaManager == null && this.mServiceConnectionManager.isServiceBound()) {
                    try {
                        this.mOtaManager = new OtaManager(this.mContext, this.mServiceConnectionManager.getServiceManager().getService(28));
                    } catch (RemoteException e) {
                        e.printStackTrace();
                        return null;
                    }
                }
            }
        }
        return this.mOtaManager;
    }

    public MediaCenterManager getMediaCenterManager() {
        if (this.mMediaCenterManager == null) {
            synchronized (OneOSApiManager.class) {
                if (this.mMediaCenterManager == null && this.mServiceConnectionManager.isServiceBound()) {
                    try {
                        this.mMediaCenterManager = new MediaCenterManager(this.mContext, this.mServiceConnectionManager.getServiceManager().getService(3));
                    } catch (RemoteException e) {
                        e.printStackTrace();
                        return null;
                    }
                }
            }
        }
        return this.mMediaCenterManager;
    }

    public WeatherManager getWeatherManager() {
        if (this.mWeatherManager == null && this.mServiceConnectionManager.isServiceBound()) {
            try {
                this.mWeatherManager = new WeatherManager(this.mContext, this.mServiceConnectionManager.getServiceManager().getService(4));
            } catch (RemoteException e) {
                e.printStackTrace();
                return null;
            }
        }
        return this.mWeatherManager;
    }

    public ControlBoardManager getControlBoardManager() {
        if (this.mControlBoardManager == null) {
            Log.d(TAG, "isServiceBound." + this.mServiceConnectionManager.isServiceBound());
            if (this.mServiceConnectionManager.isServiceBound()) {
                try {
                    this.mControlBoardManager = new ControlBoardManager(this.mContext, this.mServiceConnectionManager.getServiceManager().getService(12));
                } catch (RemoteException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }
        return this.mControlBoardManager;
    }

    public SceneModeManager getSceneModeManager() {
        if (this.mSceneModeManager == null) {
            Log.d(TAG, "isServiceBound." + this.mServiceConnectionManager.isServiceBound());
            if (this.mServiceConnectionManager.isServiceBound()) {
                try {
                    this.mSceneModeManager = new SceneModeManager(this.mContext, this.mServiceConnectionManager.getServiceManager().getService(13));
                } catch (RemoteException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }
        return this.mSceneModeManager;
    }

    public StatusBarPublicManager getStatusBarPublicManager() {
        if (this.mStatusBarPublicManager == null) {
            Log.d(TAG, "isServiceBound." + this.mServiceConnectionManager.isServiceBound());
            if (this.mServiceConnectionManager.isServiceBound()) {
                try {
                    this.mStatusBarPublicManager = new StatusBarPublicManager(this.mContext, this.mServiceConnectionManager.getServiceManager().getService(11));
                } catch (RemoteException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }
        return this.mStatusBarPublicManager;
    }

    public PhoneManager getPhoneManager() {
        if (this.mPhoneManager == null && this.mServiceConnectionManager.isServiceBound()) {
            try {
                IServiceManager serviceManager = this.mServiceConnectionManager.getServiceManager();
                Log.d(TAG, "getPhoneManager serviceManager." + serviceManager);
                IBinder service = serviceManager.getService(14);
                Log.d(TAG, "getPhoneManager phoneService." + service);
                this.mPhoneManager = new PhoneManager(this.mContext, service);
            } catch (RemoteException e) {
                e.printStackTrace();
                return null;
            }
        }
        return this.mPhoneManager;
    }

    public VimsManager getVimsManager() {
        if (this.mVimsManager == null && this.mServiceConnectionManager.isServiceBound()) {
            try {
                this.mVimsManager = new VimsManager(this.mContext, this.mServiceConnectionManager.getServiceManager().getService(5));
            } catch (RemoteException e) {
                e.printStackTrace();
                return null;
            }
        }
        return this.mVimsManager;
    }

    public ScheduleManager getScheduleManager() {
        if (this.mScheduleManager == null && this.mServiceConnectionManager.isServiceBound()) {
            try {
                this.mScheduleManager = new ScheduleManager(this.mContext, this.mServiceConnectionManager.getServiceManager().getService(6));
            } catch (RemoteException e) {
                e.printStackTrace();
                return null;
            }
        }
        return this.mScheduleManager;
    }

    public UserManager getUserManager() {
        if (this.mUserManager == null && this.mServiceConnectionManager.isServiceBound()) {
            try {
                this.mUserManager = new UserManager(this.mContext, this.mServiceConnectionManager.getServiceManager().getService(7));
            } catch (RemoteException e) {
                e.printStackTrace();
                return null;
            }
        }
        return this.mUserManager;
    }

    public KeyInputManager getKeyInputManager() {
        if (this.mKeyInputManager == null && this.mServiceConnectionManager.isServiceBound()) {
            try {
                this.mKeyInputManager = new KeyInputManager(this.mContext, this.mServiceConnectionManager.getServiceManager().getService(8));
            } catch (RemoteException e) {
                e.printStackTrace();
                return null;
            }
        }
        return this.mKeyInputManager;
    }

    public RecommendationManager getRecommendationManager() {
        if (this.mRecommendationManager == null && this.mServiceConnectionManager.isServiceBound()) {
            try {
                this.mRecommendationManager = new RecommendationManager(this.mContext, this.mServiceConnectionManager.getServiceManager().getService(10));
            } catch (RemoteException e) {
                e.printStackTrace();
                return null;
            }
        }
        return this.mRecommendationManager;
    }

    public SmartHomeManager getSmartHomeManager() {
        if (this.mSmartHomeManager == null) {
            if (this.mServiceConnectionManager.isServiceBound()) {
                try {
                    this.mSmartHomeManager = new SmartHomeManager(this.mContext, this.mServiceConnectionManager.getServiceManager().getService(15));
                } catch (RemoteException e) {
                    e.printStackTrace();
                    Log.d(TAG, "getSmartHomeManager():exception:" + e);
                    return null;
                }
            } else {
                Log.d(TAG, "getSmartHomeManager():isNotServiceBound");
            }
        }
        return this.mSmartHomeManager;
    }

    public CameraManager getCameraManager() {
        if (this.mCameraManager == null) {
            synchronized (OneOSApiManager.class) {
                if (this.mCameraManager == null && this.mServiceConnectionManager.isServiceBound()) {
                    try {
                        this.mCameraManager = new CameraManager(this.mServiceConnectionManager.getServiceManager().getService(17));
                    } catch (RemoteException e) {
                        e.printStackTrace();
                        return null;
                    }
                }
            }
        }
        return this.mCameraManager;
    }

    public LinkManager getLinkManager() {
        if (this.mLinkManager == null && this.mServiceConnectionManager.isServiceBound()) {
            try {
                this.mLinkManager = new LinkManager(this.mContext, this.mServiceConnectionManager.getServiceManager().getService(19));
            } catch (RemoteException e) {
                e.printStackTrace();
                return null;
            }
        }
        return this.mLinkManager;
    }

    public ThemeManager getThemeManager() {
        if (this.mThemeManager == null && this.mServiceConnectionManager.isServiceBound()) {
            Log.d(TAG, "getThemeManager: mServiceConnectionManager.isServiceBound(): " + this.mServiceConnectionManager.isServiceBound());
            try {
                this.mThemeManager = new ThemeManager(this.mContext, this.mServiceConnectionManager.getServiceManager().getService(18));
            } catch (RemoteException e) {
                e.printStackTrace();
                return null;
            }
        }
        return this.mThemeManager;
    }

    public ShortcutManager getShortcutManager() {
        if (this.mShortcutManager == null && this.mServiceConnectionManager.isServiceBound()) {
            Log.d(TAG, "getThemeManager: mServiceConnectionManager.isServiceBound(): " + this.mServiceConnectionManager.isServiceBound());
            try {
                this.mShortcutManager = new ShortcutManager(this.mContext, this.mServiceConnectionManager.getServiceManager().getService(20));
            } catch (RemoteException e) {
                e.printStackTrace();
                return null;
            }
        }
        return this.mShortcutManager;
    }

    public DockBarManager getDockBarManager() {
        if (this.mDockBarManager == null && this.mServiceConnectionManager.isServiceBound()) {
            Log.d(TAG, "getThemeManager: mServiceConnectionManager.isServiceBound(): " + this.mServiceConnectionManager.isServiceBound());
            try {
                this.mDockBarManager = new DockBarManager(this.mContext, this.mServiceConnectionManager.getServiceManager().getService(21));
            } catch (RemoteException e) {
                e.printStackTrace();
                return null;
            }
        }
        return this.mDockBarManager;
    }

    public LauncherManager getLauncherManager() {
        if (this.mLauncherManager == null && this.mServiceConnectionManager.isServiceBound()) {
            Log.d(TAG, "getThemeManager: mServiceConnectionManager.isServiceBound(): " + this.mServiceConnectionManager.isServiceBound());
            try {
                this.mLauncherManager = new LauncherManager(this.mContext, this.mServiceConnectionManager.getServiceManager().getService(22));
            } catch (RemoteException e) {
                e.printStackTrace();
                return null;
            }
        }
        return this.mLauncherManager;
    }

    public PaymentManager getPaymentManager() {
        if (this.mPaymentManager == null) {
            Log.d(TAG, "getPaymentManager: mServiceConnectionManager.isServiceBound(): " + this.mServiceConnectionManager.isServiceBound());
            if (this.mServiceConnectionManager.isServiceBound()) {
                try {
                    this.mPaymentManager = new PaymentManager(this.mContext, this.mServiceConnectionManager.getServiceManager().getService(24));
                } catch (RemoteException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }
        return this.mPaymentManager;
    }

    public WeChatManager getWechatManager() {
        if (this.mWechatManager == null) {
            Log.d(TAG, "getWechatManager: mServiceConnectionManager.isServiceBound(): " + this.mServiceConnectionManager.isServiceBound());
            if (this.mServiceConnectionManager.isServiceBound()) {
                try {
                    this.mWechatManager = new WeChatManager(this.mServiceConnectionManager.getServiceManager().getService(27));
                } catch (RemoteException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }
        return this.mWechatManager;
    }

    public AppStoreManager getAppStoreManager() {
        if (this.mAppStoreManager == null) {
            Log.d(TAG, "getAppStoreManager: mServiceConnectionManager.isServiceBound(): " + this.mServiceConnectionManager.isServiceBound());
            if (this.mServiceConnectionManager.isServiceBound()) {
                try {
                    this.mAppStoreManager = new AppStoreManager(this.mServiceConnectionManager.getServiceManager().getService(29));
                } catch (RemoteException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }
        return this.mAppStoreManager;
    }

    private void updateServiceBinder(ServiceBaseManager manager, int serviceType) {
        if (manager != null) {
            try {
                IServiceManager serviceManager = this.mServiceConnectionManager.getServiceManager();
                if (serviceManager != null) {
                    manager.setService(serviceManager.getService(serviceType));
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    @Override // com.geely.lib.oneosapi.listener.ServiceConnectionListener
    public void onServiceConnectionChanged(boolean connectionState) {
        Log.i(TAG, "onBinderStateChanged  binderState:" + connectionState);
        if (connectionState) {
            updateServiceBinder(this.mDeviceManager, 0);
            updateServiceBinder(this.mNaviManager, 1);
            updateServiceBinder(this.mVrManager, 2);
            updateServiceBinder(this.mMediaCenterManager, 3);
            updateServiceBinder(this.mWeatherManager, 4);
            updateServiceBinder(this.mVimsManager, 5);
            updateServiceBinder(this.mScheduleManager, 6);
            updateServiceBinder(this.mUserManager, 7);
            updateServiceBinder(this.mKeyInputManager, 8);
            updateServiceBinder(this.mRecommendationManager, 10);
            updateServiceBinder(this.mControlBoardManager, 12);
            updateServiceBinder(this.mSceneModeManager, 13);
            updateServiceBinder(this.mStatusBarPublicManager, 11);
            updateServiceBinder(this.mPhoneManager, 14);
            updateServiceBinder(this.mSmartHomeManager, 15);
            updateServiceBinder(this.mMqttManager, 16);
            updateServiceBinder(this.mCameraManager, 17);
            updateServiceBinder(this.mThemeManager, 18);
            updateServiceBinder(this.mLinkManager, 19);
            updateServiceBinder(this.mShortcutManager, 20);
            updateServiceBinder(this.mDockBarManager, 21);
            updateServiceBinder(this.mLauncherManager, 22);
            updateServiceBinder(this.mGestureManager, 23);
            updateServiceBinder(this.mPaymentManager, 24);
            updateServiceBinder(this.mWechatManager, 27);
            updateServiceBinder(this.mOtaManager, 28);
            updateServiceBinder(this.mAppStoreManager, 29);
        }
    }

    @Override // com.geely.lib.oneosapi.listener.ServiceConnectionListener
    public void onServiceBinderUpdated(int binderType) {
        Log.i(TAG, "onBinderUpdate  binderType:" + binderType);
        switch (binderType) {
            case 0:
                updateServiceBinder(this.mDeviceManager, binderType);
                break;
            case 1:
                updateServiceBinder(this.mNaviManager, binderType);
                break;
            case 2:
                updateServiceBinder(this.mVrManager, binderType);
                break;
            case 3:
                updateServiceBinder(this.mMediaCenterManager, binderType);
                break;
            case 4:
                updateServiceBinder(this.mWeatherManager, binderType);
                break;
            case 5:
                updateServiceBinder(this.mVimsManager, binderType);
                break;
            case 6:
                updateServiceBinder(this.mScheduleManager, binderType);
                break;
            case 7:
                updateServiceBinder(this.mUserManager, binderType);
                break;
            case 8:
                updateServiceBinder(this.mKeyInputManager, binderType);
                break;
            case 10:
                updateServiceBinder(this.mRecommendationManager, binderType);
                break;
            case 11:
                updateServiceBinder(this.mStatusBarPublicManager, binderType);
                break;
            case 12:
                updateServiceBinder(this.mControlBoardManager, binderType);
                break;
            case 13:
                updateServiceBinder(this.mSceneModeManager, binderType);
                break;
            case 14:
                updateServiceBinder(this.mPhoneManager, binderType);
                break;
            case 15:
                updateServiceBinder(this.mSmartHomeManager, binderType);
                break;
            case 16:
                updateServiceBinder(this.mMqttManager, binderType);
                break;
            case 17:
                updateServiceBinder(this.mCameraManager, binderType);
                break;
            case 18:
                updateServiceBinder(this.mThemeManager, binderType);
                break;
            case 19:
                updateServiceBinder(this.mLinkManager, binderType);
                break;
            case 20:
                updateServiceBinder(this.mShortcutManager, binderType);
                break;
            case 21:
                updateServiceBinder(this.mDockBarManager, binderType);
                break;
            case 22:
                updateServiceBinder(this.mLauncherManager, binderType);
                break;
            case 23:
                updateServiceBinder(this.mGestureManager, binderType);
                break;
            case 24:
                updateServiceBinder(this.mPaymentManager, binderType);
                break;
            case 27:
                updateServiceBinder(this.mWechatManager, binderType);
                break;
            case 28:
                updateServiceBinder(this.mOtaManager, binderType);
                break;
            case 29:
                updateServiceBinder(this.mAppStoreManager, binderType);
                break;
        }
    }

    public boolean addService(int type, IBinder binder) {
        Log.d(TAG, "addService() called with: type = [" + type + "], binder = [" + binder + "]");
        if (!this.mServiceConnectionManager.isServiceBound()) {
            return false;
        }
        Log.d(TAG, "isServiceBound");
        try {
            this.mServiceConnectionManager.getServiceManager().addService(type, binder);
            return true;
        } catch (RemoteException e) {
            e.printStackTrace();
            Log.d(TAG, "RemoteException." + e.getMessage());
            return false;
        }
    }
}